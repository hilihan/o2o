package cn.mark.o2o.service;

import java.io.InputStream;

import cn.mark.o2o.dto.ShopExecution;
import cn.mark.o2o.entity.Shop;
import cn.mark.o2o.exceptions.ShopOperationException;

public interface ShopService {
	/**
	 * 通过店铺Id获取店铺信息
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);
	
	/**
	 * 更新店铺信息，包括图片处理
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 */
	ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream,String fileName) throws ShopOperationException;
	
	/**
	 * 注册店铺信息，包括图片处理
	 * @param shop
	 * @param shopImgInputstream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution addShop(Shop shop,InputStream shopImgInputstream,String fileName) throws ShopOperationException;
}
