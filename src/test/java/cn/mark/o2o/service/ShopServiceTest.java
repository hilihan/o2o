package cn.mark.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.mark.o2o.BaseTest;
import cn.mark.o2o.dto.ShopExecution;
import cn.mark.o2o.entity.Area;
import cn.mark.o2o.entity.PersonInfo;
import cn.mark.o2o.entity.Shop;
import cn.mark.o2o.entity.ShopCategory;
import cn.mark.o2o.enums.ShopStateEnum;

public class ShopServiceTest extends BaseTest {
	@Autowired
	private ShopService shopService;
	
	@Test
	public void testAddShop() {
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
		shop.setShopName("测试的店铺2");
		shop.setShopDesc("test2");
		shop.setShopAddr("test2");
		shop.setPhone("test2");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		
		File shopImg = new File("/Users/mark/Downloads/Doraemon.jpg");
		
		ShopExecution result = shopService.addShop(shop, shopImg);
		
		assertEquals(ShopStateEnum.CHECK.getState(), result.getState());
	}
}
