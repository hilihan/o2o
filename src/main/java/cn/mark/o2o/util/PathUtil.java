package cn.mark.o2o.util;

import java.io.File;

public class PathUtil {
	
	private static String seperator = System.getProperty("file.separator");
	
	/**
	 * 获取图片根路径
	 * @return
	 */
	public static String getImgBasePath() {
		//获取当前项目所在的操作系统环境
		String os = System.getProperty("os.name");
		String basePath = "";
		if(os.toLowerCase().startsWith("win")) {
			basePath = "D:/projectdev/image";
		} else {
			basePath = "/Users/mark/Downloads/o2o_dev_img";
		}
		basePath = basePath.replace("/", seperator);
		
		return basePath;
	}
	
	/**
	 * 获取店铺下商品图片路径
	 * @param shopId
	 * @return
	 */
	public static String getShopImagePath(long shopId) {
		String imagePath = "/upload/item/shop/" + shopId + "/";
		return imagePath.replace("/", seperator);
	}
}
