package cn.mark.o2o.service;

import java.util.List;

import cn.mark.o2o.dto.ImageHolder;
import cn.mark.o2o.dto.ProductExecution;
import cn.mark.o2o.entity.Product;
import cn.mark.o2o.exceptions.ProductOperationException;

public interface ProductService {

	/**
	 * 添加商品信息以及图片处理
	 * @param product
	 * @param thumbnail 缩略图
	 * @param productImgList 商品图
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution addProduct(Product product,ImageHolder thumbnail,List<ImageHolder> productImgList) throws ProductOperationException;
	 
}