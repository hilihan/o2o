package cn.mark.o2o.dao;

import cn.mark.o2o.entity.Shop;

public interface ShopDao {
	/**
	 * 新增店铺
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	/**
	 * 更新店铺
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
}
