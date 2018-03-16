package cn.mark.o2o.util;

import javax.servlet.http.HttpServletRequest;

import com.google.code.kaptcha.Constants;

public class CodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest request) {
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
