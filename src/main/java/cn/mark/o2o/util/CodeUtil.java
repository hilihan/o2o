package cn.mark.o2o.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.kaptcha.Constants;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class CodeUtil {
	/**
	 * 验证码校验
	 * @param request
	 * @return
	 */
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
	
	/**
	 * 生成二维码的图片流
	 */
	public static BitMatrix generateQRCodeStream(String content, HttpServletResponse resp) {
		// 给响应添加头部信息，主要是告诉浏览器返回的是图片流
		resp.setHeader("Cache-Control", "no-store");
		resp.setHeader("Pragma", "no-cache");
		resp.setDateHeader("Expires", 0);
		resp.setContentType("image/png");
		// 设置图片的文字编码以及内边框距
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 0);
		BitMatrix bitMatrix;
		try {
			// 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
			bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 150, 150, hints);
		} catch (WriterException e) {
			e.printStackTrace();
			return null;
		}
		return bitMatrix;

	}
}
