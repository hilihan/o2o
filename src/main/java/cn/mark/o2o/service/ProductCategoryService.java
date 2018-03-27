package cn.mark.o2o.service;

import java.util.List;

import cn.mark.o2o.dto.ProductCategoryExecution;
import cn.mark.o2o.entity.ProductCategory;
import cn.mark.o2o.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {
	/**
	 * 查询指定某个店铺下的所有商品类别信息
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> getProductCategoryList(long shopId);
	/**
	 * 批量新增商品类别
	 * @param productCategoryList
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;
}
