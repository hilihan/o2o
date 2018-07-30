package cn.mark.o2o.service;

import java.util.List;

import cn.mark.o2o.entity.ShopCategory;


public interface ShopCategoryService {
	
	public static final String SCLISTKEY = "shopcategorylist";
	
	/**
	 * 根据查询条件获取店铺类别列表
	 * @param shopCategoryCondition
	 * @return
	 */
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
