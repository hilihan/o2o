package cn.mark.o2o.service;

import java.io.File;

import cn.mark.o2o.dto.ShopExecution;
import cn.mark.o2o.entity.Shop;

public interface ShopService {
	ShopExecution addShop(Shop shop,File shopImg);
}
