package cn.mark.o2o.web.shopadmin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.mark.o2o.dto.ShopExecution;
import cn.mark.o2o.entity.PersonInfo;
import cn.mark.o2o.entity.Shop;
import cn.mark.o2o.enums.ShopStateEnum;
import cn.mark.o2o.service.ShopService;
import cn.mark.o2o.util.HttpServletRequestUtil;
import cn.mark.o2o.util.ImageUtil;
import cn.mark.o2o.util.PathUtil;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	@Autowired
	private ShopService shopService;
	
	@RequestMapping(value="/registershop",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> registerShop(HttpServletRequest request){
		Map<String,Object> modalMap = new HashMap<>();
		// 1.接收并转化相应的参数，包括店铺信息以及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modalMap.put("success", false);
			modalMap.put("errMsg", e.getMessage());
			return modalMap;
		}
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if(commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			shopImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
		} else {
			modalMap.put("success", false);
			modalMap.put("errMsg", "上传图片不能为空");
			return modalMap;
		}
		// 2.注册店铺
		if(shop != null && shopImg != null) {
			PersonInfo owner = new PersonInfo();
			owner.setUserId(1L);
			shop.setOwner(owner);
			
			File shopImgFile = new File(PathUtil.getImgBasePath() + ImageUtil.getRandomFileName());
			try {
				shopImgFile.createNewFile();
			} catch (Exception e) {
				modalMap.put("success", false);
				modalMap.put("errMsg", "上传图片不能为空");
				return modalMap;
			}
			try {
				inputStreamToFile(shopImg.getInputStream(), shopImgFile);
			} catch (IOException e) {
				modalMap.put("success", false);
				modalMap.put("errMsg", e.getMessage());
				return modalMap;
			}
			ShopExecution result = shopService.addShop(shop, shopImgFile);
			if(result.getState() == ShopStateEnum.CHECK.getState()) {
				modalMap.put("success", true);
			} else {
				modalMap.put("success", false);
				modalMap.put("errMsg", "请输入店铺信息");
				return modalMap;
			}
		} else {
			modalMap.put("success", false);
			modalMap.put("errMsg", "请输入店铺信息");
			return modalMap;
		}
		// 3.返回结果
		return modalMap;
	}
	
	private static void inputStreamToFile(InputStream ins,File file) {
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while((bytesRead - ins.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		} catch (Exception e) {
			throw new RuntimeException("调用inputStreamToFile产生异常：" + e.getMessage());
		} finally {
			try {
				if(os != null) {
					os.close();
				}
				if(ins != null) {
					ins.close();
				}
			} catch (IOException e) {
				throw new RuntimeException("调用inputStreamToFile关闭流产生异常：" + e.getMessage());
			}
		}
	}
}
