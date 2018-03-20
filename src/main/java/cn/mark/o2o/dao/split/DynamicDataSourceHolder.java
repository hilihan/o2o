package cn.mark.o2o.dao.split;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicDataSourceHolder {
	private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceHolder.class);
	private static ThreadLocal<String> contextHolder = new ThreadLocal<>();
	
	public static final String DB_MASTER = "master";
	public static final String DB_SLAVE = "slave";
	
	public static String getDBType() {
		String db = contextHolder.get();
		if(db == null) {
			db = DB_MASTER;
		}
		return db;
	}
	
	/**
	 * 设置线程的 dbType
	 * @param str
	 */
	public static void setDBType(String str) {
		logger.debug("所使用的数据源为：" + str);
		contextHolder.set(str);
	}

	/**
	 * 清理连接类型
	 */
	public static void clearDBType() {
		contextHolder.remove();
	}
}
