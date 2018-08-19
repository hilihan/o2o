package cn.mark.o2o.service;

import java.util.Date;
import java.util.List;

import cn.mark.o2o.entity.ProductSellDaily;

public interface ProductSellDailyService {
	/**
	 * 每日定时对所有店铺的商品销量进行统计
	 */
	void dailyCalculate();

	/**
	 * 根据查询条件返回商品日销售的统计列表
	 * 
	 * @param productSellDailyCondition
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	List<ProductSellDaily> listProductSellDaily(ProductSellDaily productSellDailyCondition, Date beginTime,
			Date endTime);
}
