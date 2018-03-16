package cn.mark.o2o.service;

import java.io.InputStream;

import cn.mark.o2o.dto.ShopExecution;
import cn.mark.o2o.entity.Shop;
import cn.mark.o2o.exceptions.ShopOperationException;

public interface ShopService {
	ShopExecution addShop(Shop shop,InputStream shopImgInputstream,String fileName) throws ShopOperationException;
}
