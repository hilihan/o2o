package cn.mark.o2o.dao;

import java.util.List;

import cn.mark.o2o.entity.Area;

public interface AreaDao {
	/**
	 * 列出区域列表
	 * @return 所有区域集合
	 */
	List<Area> queryArea();
	
}
