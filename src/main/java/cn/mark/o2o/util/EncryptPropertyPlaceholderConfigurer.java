package cn.mark.o2o.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * PropertyPlaceholderConfigurer
 * 
 * Spring容器初始化的时候，会读取xml或者annotation对Bean进行初始化。
 * 初始化的时候，这个PropertyPlaceholderConfigurer会拦截Bean的初始化，
 * 初始化的时候会对配置的${pname}进行替换，根据我们Properties中配置的进行替换。
 * 从而实现表达式的替换操作
 * 
 * @author mark
 *
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	// 需要加密的字段数组
	private String[] encryptPropNames = { "jdbc.username", "jdbc.password" };

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if (isEncryptProp(propertyName)) {
			// 对已加密的字段进行解密工作
			String decryptValue = DESUtil.getDecryptString(propertyValue);
			return decryptValue;
		} else {
			return propertyValue;
		}
	}

	/**
	 * 该属性是否已加密
	 * 
	 * @param propertyName
	 * @return
	 */
	private boolean isEncryptProp(String propertyName) {
		// 若等于需要加密的field，则进行加密
		for (String encryptpropertyName : encryptPropNames) {
			if (encryptpropertyName.equals(propertyName))
				return true;
		}
		return false;
	}

}
