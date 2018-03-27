package cn.mark.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.mark.o2o.BaseTest;
import cn.mark.o2o.entity.ProductCategory;

public class ProductCategoryDaoTest extends BaseTest {
	
	@Autowired private ProductCategoryDao productCategoryDao;

	@Test
	public void testQueryByShopId() throws Exception{
		long shopId = 41;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		System.out.println("该店铺自定义类别数为：" + productCategoryList.size());
	}
	
	@Test
	public void testBatchInsertProductCategory() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryName("商品类别1");
		productCategory.setPriority(1);
		productCategory.setCreateTime(new Date());
		productCategory.setShopId(41L);
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("商品类别2");
		productCategory2.setPriority(2);
		productCategory2.setCreateTime(new Date());
		productCategory2.setShopId(41L);
		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		productCategoryList.add(productCategory);
		productCategoryList.add(productCategory2);
		int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
		assertEquals(2, effectedNum);
	}
}
