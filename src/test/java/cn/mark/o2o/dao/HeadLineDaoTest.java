package cn.mark.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.mark.o2o.BaseTest;
import cn.mark.o2o.entity.HeadLine;

public class HeadLineDaoTest extends BaseTest {
	
	@Autowired
	private HeadLineDao headLineDao;
	
	@Test
	public void testQueryHeadLine() {
		List<HeadLine> handLineList = headLineDao.queryHeadLine(new HeadLine());
		assertEquals(1, handLineList.size());
	}
	
}
