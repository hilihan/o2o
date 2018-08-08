package cn.mark.o2o.service;

import cn.mark.o2o.dto.ShopAuthMapExecution;
import cn.mark.o2o.entity.ShopAuthMap;
import cn.mark.o2o.exceptions.ShopAuthMapOperationException;

public interface ShopAuthMapService {
	/**
	 * 根据店铺Id分页显示该店铺的授权信息
	 * 
	 * @param shopId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ShopAuthMapExecution listShopAuthMapByShopId(Long shopId, Integer pageIndex, Integer pageSize);

	/**
	 * 根据shopAuthId返回对应的授权信息
	 * 
	 * @param shopAuthId
	 * @return
	 */
	ShopAuthMap getShopAuthMapById(Long shopAuthId);

	/**
	 * 添加授权信息
	 * 
	 * @param shopAuthMap
	 * @return
	 * @throws ShopAuthMapOperationException
	 */
	ShopAuthMapExecution addShopAuthMap(ShopAuthMap shopAuthMap) throws ShopAuthMapOperationException;

	/**
	 * 更新授权信息，包括职位，状态等
	 * 
	 * @param shopAuthId
	 * @param title
	 * @param titleFlag
	 * @param enableStatus
	 * @return
	 * @throws ShopAuthMapOperationException
	 */
	ShopAuthMapExecution modifyShopAuthMap(ShopAuthMap shopAuthMap) throws ShopAuthMapOperationException;

}