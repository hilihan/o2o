package cn.mark.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mark.o2o.dao.ProductCategoryDao;
import cn.mark.o2o.entity.ProductCategory;

@Service
public class ProductCategoryServiceImpl implements cn.mark.o2o.service.ProductCategoryService {

	@Autowired private ProductCategoryDao productCategoryDao;
	
	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		
		return productCategoryDao.queryProductCategoryList(shopId);
	}

}
