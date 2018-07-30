package cn.mark.o2o.service;

import java.util.List;

import cn.mark.o2o.entity.Area;

public interface AreaService {
	
	public static final String AREALISTKEY = "arealist";
	
	List<Area> getAreaList();
}
