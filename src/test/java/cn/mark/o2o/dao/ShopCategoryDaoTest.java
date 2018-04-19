package cn.mark.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.mark.o2o.BaseTest;
import cn.mark.o2o.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest {
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Test
	public void testQueryShopCategory() {
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(null);
//		assertEquals(2, shopCategoryList.size());
//		
//		ShopCategory shopCategory =  new ShopCategory();
//		ShopCategory parentCategory =  new ShopCategory();
//		parentCategory.setShopCategoryId(36L);
//		shopCategory.setParent(parentCategory);
//		shopCategoryList = shopCategoryDao.queryShopCategory(shopCategory);
//		assertEquals(1, shopCategoryList.size());
//		System.out.println(shopCategoryList.get(0).getShopCategoryName());
	}
}
