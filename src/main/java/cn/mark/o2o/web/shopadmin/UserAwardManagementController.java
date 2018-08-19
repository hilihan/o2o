package cn.mark.o2o.web.shopadmin;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import cn.mark.o2o.dto.ShopAuthMapExecution;
import cn.mark.o2o.dto.UserAccessToken;
import cn.mark.o2o.dto.UserAwardMapExecution;
import cn.mark.o2o.dto.WechatInfo;
import cn.mark.o2o.entity.Award;
import cn.mark.o2o.entity.PersonInfo;
import cn.mark.o2o.entity.Shop;
import cn.mark.o2o.entity.ShopAuthMap;
import cn.mark.o2o.entity.UserAwardMap;
import cn.mark.o2o.entity.WechatAuth;
import cn.mark.o2o.enums.UserAwardMapStateEnum;
import cn.mark.o2o.service.PersonInfoService;
import cn.mark.o2o.service.ShopAuthMapService;
import cn.mark.o2o.service.UserAwardMapService;
import cn.mark.o2o.service.WechatAuthService;
import cn.mark.o2o.util.HttpServletRequestUtil;
import cn.mark.o2o.util.wechat.WechatUtil;

@Controller
@RequestMapping("/shopadmin")
public class UserAwardManagementController {
	@Autowired
	private UserAwardMapService userAwardMapService;
	@Autowired
	private PersonInfoService personInfoService;
	@Autowired
	private ShopAuthMapService shopAuthMapService;
	@Autowired
	private WechatAuthService wechatAuthService;

	/**
	 * 列出某个店铺的用户奖品领取情况列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listuserawardmapsbyshop", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listUserAwardMapsByShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 从session里获取店铺信息
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		// 获取分页信息
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		// 空值判断
		if ((pageIndex > -1) && (pageSize > -1) && (currentShop != null) && (currentShop.getShopId() != null)) {
			UserAwardMap userAwardMap = new UserAwardMap();
			userAwardMap.setShop(currentShop);
			// 从请求中获取奖品名
			String awardName = HttpServletRequestUtil.getString(request, "awardName");
			if (awardName != null) {
				// 如果需要按照奖品名称搜索，则添加搜索条件
				Award award = new Award();
				award.setAwardName(awardName);
				userAwardMap.setAward(award);
			}
			// 分页返回结果
			UserAwardMapExecution ue = userAwardMapService.listReceivedUserAwardMap(userAwardMap, pageIndex, pageSize);
			modelMap.put("userAwardMapList", ue.getUserAwardMapList());
			modelMap.put("count", ue.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
		}
		return modelMap;
	}

	/**
	 * 操作员扫顾客的奖品二维码并派发奖品，证明顾客已领取过
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/exchangeaward", method = RequestMethod.GET)
	private String exchangeAward(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取负责扫描二维码的店员信息
		WechatAuth auth = getOperatorInfo(request);
		if (auth != null) {
			// 通过userId获取店员信息
			PersonInfo operator = personInfoService.getPersonInfoById(auth.getPersonInfo().getUserId());
			// 设置上用户的session
			request.getSession().setAttribute("user", operator);
			// 解析微信回传过来的自定义参数state,由于之前进行了编码，这里需要解码一下
			String qrCodeinfo = new String(
					URLDecoder.decode(HttpServletRequestUtil.getString(request, "state"), "UTF-8"));
			ObjectMapper mapper = new ObjectMapper();
			WechatInfo wechatInfo = null;
			try {
				// 将解码后的内容用aaa去替换掉之前生成二维码的时候加入的aaa前缀，转换成WechatInfo实体类
				wechatInfo = mapper.readValue(qrCodeinfo.replace("aaa", "\""), WechatInfo.class);
			} catch (Exception e) {
				return "shop/operationfail";
			}
			// 校验二维码是否已经过期
			if (!checkQRCodeInfo(wechatInfo)) {
				return "shop/operationfail";
			}
			// 获取用户奖品映射主键
			Long userAwardId = wechatInfo.getUserAwardId();
			// 获取顾客Id
			Long customerId = wechatInfo.getCustomerId();
			// 将顾客信息，操作员信息以及奖品信息封装成userAwardMap
			UserAwardMap userAwardMap = compactUserAwardMap4Exchange(customerId, userAwardId, operator);
			if (userAwardMap != null) {
				try {
					// 检查该员工是否具有扫码权限
					if (!checkShopAuth(operator.getUserId(), userAwardMap)) {
						return "shop/operationfail";
					}
					// 修改奖品的领取状态
					UserAwardMapExecution se = userAwardMapService.modifyUserAwardMap(userAwardMap);
					if (se.getState() == UserAwardMapStateEnum.SUCCESS.getState()) {
						return "shop/operationsuccess";
					}
				} catch (RuntimeException e) {
					return "shop/operationfail";
				}

			}
		}
		return "shop/operationfail";
	}

	/**
	 * 获取扫描二维码的店员信息
	 * 
	 * @param request
	 * @return
	 */
	private WechatAuth getOperatorInfo(HttpServletRequest request) {
		String code = request.getParameter("code");
		WechatAuth auth = null;
		if (null != code) {
			UserAccessToken token;
			try {
				token = WechatUtil.getUserAccessToken(code);
				String openId = token.getOpenId();
				request.getSession().setAttribute("openId", openId);
				auth = wechatAuthService.getWechatAuthByOpenId(openId);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return auth;
	}

	/**
	 * 根据二维码携带的createTime判断其是否超过了10分钟，超过十分钟则认为过期
	 * 
	 * @param wechatInfo
	 * @return
	 */
	private boolean checkQRCodeInfo(WechatInfo wechatInfo) {
		// 空值判断
		if (wechatInfo != null && wechatInfo.getUserAwardId() != null && wechatInfo.getCustomerId() != null
				&& wechatInfo.getCreateTime() != null) {
			long nowTime = System.currentTimeMillis();
			if ((nowTime - wechatInfo.getCreateTime()) <= 600000) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 封装用户奖品映射实体类，以供扫码使用，主要将其领取状态变为已领取
	 * 
	 * @param customerId
	 * @param userAwardId
	 * @return
	 */
	private UserAwardMap compactUserAwardMap4Exchange(Long customerId, Long userAwardId, PersonInfo operator) {
		UserAwardMap userAwardMap = null;
		// 空值判断
		if (customerId != null && userAwardId != null && operator != null) {
			// 获取原有userAwardMap信息
			userAwardMap = userAwardMapService.getUserAwardMapById(userAwardId);
			userAwardMap.setUsedStatus(1);
			PersonInfo customer = new PersonInfo();
			customer.setUserId(customerId);
			userAwardMap.setUser(customer);
			userAwardMap.setOperator(operator);
		}
		return userAwardMap;
	}

	/**
	 * 检查员工是否具有授权权限
	 * 
	 * @param userId
	 * @param userAwardMap
	 * @return
	 */
	private boolean checkShopAuth(long userId, UserAwardMap userAwardMap) {
		// 取出该店铺所有的授权信息
		ShopAuthMapExecution shopAuthMapExecution = shopAuthMapService
				.listShopAuthMapByShopId(userAwardMap.getShop().getShopId(), 1, 1000);
		// 逐条遍历，看看扫描二维码的员工是否具有扫码权限
		for (ShopAuthMap shopAuthMap : shopAuthMapExecution.getShopAuthMapList()) {
			if (shopAuthMap.getEmployee().getUserId() == userId && shopAuthMap.getEnableStatus() == 1) {
				return true;
			}
		}
		return false;
	}
}
