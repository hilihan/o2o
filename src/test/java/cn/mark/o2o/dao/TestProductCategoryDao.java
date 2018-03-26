package cn.mark.o2o.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.mark.o2o.BaseTest;
import cn.mark.o2o.entity.ProductCategory;

public class TestProductCategoryDao extends BaseTest {
	
	@Autowired private ProductCategoryDao productCategoryDao;

	@Test
	public void testQueryByShopId() throws Exception{
		long shopId = 41;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		System.out.println("该店铺自定义类别数为：" + productCategoryList.size());
		
	}
}
