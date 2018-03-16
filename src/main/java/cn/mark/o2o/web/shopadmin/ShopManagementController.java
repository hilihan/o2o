package cn.mark.o2o.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.mark.o2o.dto.ShopExecution;
import cn.mark.o2o.entity.Area;
import cn.mark.o2o.entity.PersonInfo;
import cn.mark.o2o.entity.Shop;
import cn.mark.o2o.entity.ShopCategory;
import cn.mark.o2o.enums.ShopStateEnum;
import cn.mark.o2o.exceptions.ShopOperationException;
import cn.mark.o2o.service.AreaService;
import cn.mark.o2o.service.ShopCategoryService;
import cn.mark.o2o.service.ShopService;
import cn.mark.o2o.util.CodeUtil;
import cn.mark.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value="/getshopinitinfo",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getShopInitInfo(){
		Map<String,Object> modelMap = new HashMap<>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		List<Area> areaList = new ArrayList<Area>();
		try {
			shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
			areaList = areaService.getAreaList();
			
			modelMap.put("success", true);
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	@RequestMapping(value="/registershop",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> registerShop(HttpServletRequest request){
		Map<String,Object> modalMap = new HashMap<>();
		if(!CodeUtil.checkVerifyCode(request)) {
			modalMap.put("success", false);
			modalMap.put("errMsg", "验证码错误！");
			return modalMap;
		}
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
			//TODO Session 
			owner.setUserId(9L);
			shop.setOwner(owner);
			ShopExecution result;
			try {
				result = shopService.addShop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());
				if(result.getState() == ShopStateEnum.CHECK.getState()) {
					modalMap.put("success", true);
				} else {
					modalMap.put("success", false);
					modalMap.put("errMsg", "请输入店铺信息");
					return modalMap;
				}
			} catch (ShopOperationException e) {
				modalMap.put("success", false);
				modalMap.put("errMsg", e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				modalMap.put("success", false);
				modalMap.put("errMsg", e.getMessage());
			}
			return modalMap;
		} else {
			modalMap.put("success", false);
			modalMap.put("errMsg", "请输入店铺信息");
			return modalMap;
		}
	}
	
	
}
