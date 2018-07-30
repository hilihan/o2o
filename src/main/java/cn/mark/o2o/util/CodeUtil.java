package cn.mark.o2o.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.google.code.kaptcha.Constants;

public class CodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		String verifyCodeExcepted = (String) request.getSession().getAttribute(
				Constants.KAPTCHA_SESSION_KEY
				);
		String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
		if(verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExcepted)) {
			return false;
		}
		return true;
	}
}
