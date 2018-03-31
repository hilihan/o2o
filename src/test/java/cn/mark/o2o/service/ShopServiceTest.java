package cn.mark.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.mark.o2o.BaseTest;
import cn.mark.o2o.dto.ImageHolder;
import cn.mark.o2o.dto.ShopExecution;
import cn.mark.o2o.entity.Area;
import cn.mark.o2o.entity.PersonInfo;
import cn.mark.o2o.entity.Shop;
import cn.mark.o2o.entity.ShopCategory;
import cn.mark.o2o.enums.ShopStateEnum;
import jdk.nashorn.internal.ir.annotations.Ignore;

public class ShopServiceTest extends BaseTest {
	@Autowired
	private ShopService shopService;
	
	@Test
	public void testGetShopList() {
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(9L);
		shopCondition.setOwner(owner);
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(36L);
		shopCondition.setShopCategory(sc);
		ShopExecution se = shopService.getShopList(shopCondition, 2, 3);
		System.out.println("店铺列表数为：" + se.getShopList().size());
		System.out.println("店铺总数数为：" + se.getCount());
	}
	
	@Test
	@Ignore
	public void testModifyShop() throws FileNotFoundException {
		Shop shop = new Shop();
		shop.setShopName("味千拉面");
		shop.setShopId(39L);
		shop.setShopDesc("味千（中国）控股有限公司是一家以内地为主营业务基地的经营商。截至2012年底，味千中国的快速休闲连锁餐厅网络遍布中国120个主要城市的商业地段，在上海、香港、北京、深圳、广州、杭州、南京、福州、大连、成都、武汉等地区设有661家分店，2012年营业额达30.43亿港元。");
		File shopImg = new File("/Users/mark/Desktop/1.jpg");
		InputStream is = new FileInputStream(shopImg);
		
		ShopExecution shopExecution = shopService.modifyShop(shop, new ImageHolder(shopImg.getName(),is));
		System.out.println("新的图片地址为："+shopExecution.getShop().getShopImg());
	}
	
	@Test
	@Ignore
	public void testAddShop() throws FileNotFoundException {
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
		InputStream is = new FileInputStream(shopImg);
		ShopExecution result = shopService.addShop(shop,new ImageHolder(shopImg.getName(),is));
		
		assertEquals(ShopStateEnum.CHECK.getState(), result.getState());
	}
}
