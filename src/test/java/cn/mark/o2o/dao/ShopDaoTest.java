package cn.mark.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.mark.o2o.BaseTest;
import cn.mark.o2o.entity.Area;
import cn.mark.o2o.entity.PersonInfo;
import cn.mark.o2o.entity.Shop;
import cn.mark.o2o.entity.ShopCategory;
import jdk.nashorn.internal.ir.annotations.Ignore;

public class ShopDaoTest extends BaseTest{
	@Autowired
	private ShopDao shopDao;
	
	@Test
	@Ignore
	public void testInsertShop() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		
		owner.setUserId(9L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(36L);
		shop.setShopName("测试的店铺");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);
	}
	
	@Test
	@Ignore
	public void testUpdateShop() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		
		shop.setShopId(36L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		
		owner.setUserId(9L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(36L);
		shop.setShopName("测试的店铺1");
		shop.setShopDesc("test1");
		shop.setShopAddr("test1");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
	}
	
	@Test
	@Ignore
	public void testQueryByShopId() {
		long shopId = 36L;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println("areaId:" + shop.getArea().getAreaId());
		System.out.println("areaName:" + shop.getArea().getAreaName());
	}
	
	@Test
	public void testQueryShopList() {
//		Shop shopCondition = new Shop();
//		PersonInfo owner = new PersonInfo();
//		owner.setUserId(9L);
//		shopCondition.setOwner(owner);
//		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 10);
//		System.out.println("店铺列表的大小：" + shopList.size());
//		System.out.println("店铺列表的总数：" + shopDao.queryShopCount(shopCondition));
		
//		ShopCategory sc = new ShopCategory();
//		sc.setShopCategoryId(36L);
//		shopCondition.setShopCategory(sc);
//		
//		shopList = shopDao.queryShopList(shopCondition, 0, 2);
//		System.out.println("xin店铺列表的大小：" + shopList.size());
//		System.out.println("xin店铺列表的总数：" + shopDao.queryShopCount(shopCondition));
		
		Shop shopCondition = new Shop();
		ShopCategory childCategory = new ShopCategory();
		ShopCategory parentCategory = new ShopCategory();
		parentCategory.setShopCategoryId(36L);
		childCategory.setParent(parentCategory);
		shopCondition.setShopCategory(childCategory);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 10);
		System.out.println("店铺列表的大小：" + shopList.size());
		System.out.println("店铺列表的总数：" + shopDao.queryShopCount(shopCondition));
	}
}
