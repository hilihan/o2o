package cn.mark.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.mark.o2o.BaseTest;
import cn.mark.o2o.dto.ImageHolder;
import cn.mark.o2o.dto.ProductExecution;
import cn.mark.o2o.entity.Product;
import cn.mark.o2o.entity.ProductCategory;
import cn.mark.o2o.entity.Shop;
import cn.mark.o2o.enums.ProductStateEnum;
import cn.mark.o2o.exceptions.ShopOperationException;

public class ProductServiceTest extends BaseTest {
	@Autowired
	private ProductService productService;

	@Test
	public void testAddProduct() throws ShopOperationException,FileNotFoundException{
		//创建shopId为41且productCategoryId为32的商品实例并给其成员变量赋值
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(41L);
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(32L);
		product.setShop(shop);
		product.setProductCategory(pc);
		
		product.setProductName("嫩牛五方翅桶");
		product.setProductDesc("辣翅6块+烤翅4块+面包升级香辣鸡腿堡1个+川辣嫩牛五方1个+九珍2杯");
		product.setPriority(10);
		product.setCreateTime(new Date());
		product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
		//创建缩略图文件流
		File thumbnailFile = new File("/Users/mark/Desktop/1.jpg");
		InputStream is = new FileInputStream(thumbnailFile);
		ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
		//创建两个商品详情图文件流并将它们添加到详情图列表中
		File productImg1 = new File("/Users/mark/Desktop/2.jpeg");
		InputStream is1 = new FileInputStream(productImg1);
		File productImg2 = new File("/Users/mark/Desktop/3.jpeg");
		InputStream is2 = new FileInputStream(productImg2);
		List<ImageHolder> productImgList = new ArrayList<>();
		productImgList.add(new ImageHolder(productImg1.getName(), is1));
		productImgList.add(new ImageHolder(productImg2.getName(), is2));
		//添加商品并验证
		ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
		assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
	}
}
