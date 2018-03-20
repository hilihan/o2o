package cn.mark.o2o.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.mark.o2o.BaseTest;
import cn.mark.o2o.entity.Area;

public class AreaDaoTest extends BaseTest{
	@Autowired
	private AreaDao areaDao;
	
	@Test
	public void testQueryArea() {
		List<Area> areaList = areaDao.queryArea();
		Assert.assertEquals(4, areaList.size());
	}
}
