package cn.mark.o2o.service;

import cn.mark.o2o.entity.PersonInfo;

public interface PersonInfoService {
	/**
	 * 根据用户ID获取personInfo信息
	 * @param userId
	 * @return
	 */
	PersonInfo getPersonInfoById(Long userId);
}
