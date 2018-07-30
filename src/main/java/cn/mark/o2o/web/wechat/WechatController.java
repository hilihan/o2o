package cn.mark.o2o.web.wechat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.mark.o2o.util.wechat.SignUtil;

@Controller
@RequestMapping("/wechat")
public class WechatController {
	private static Logger logger = LoggerFactory.getLogger(WechatController.class);
	
	@RequestMapping(method= {RequestMethod.GET})
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("微信get.......");
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			if(SignUtil.checkSignature(signature, timestamp, nonce)) {
				logger.debug("微信进入校验成功");
				out.print(echostr);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}finally {
			if(out != null ) {
				out.close();
			}
		}
	}
}
