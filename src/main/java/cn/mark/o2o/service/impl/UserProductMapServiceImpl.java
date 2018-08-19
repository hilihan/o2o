package cn.mark.o2o.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mark.o2o.dao.UserProductMapDao;
import cn.mark.o2o.dao.UserShopMapDao;
import cn.mark.o2o.dto.UserProductMapExecution;
import cn.mark.o2o.entity.PersonInfo;
import cn.mark.o2o.entity.Shop;
import cn.mark.o2o.entity.UserProductMap;
import cn.mark.o2o.entity.UserShopMap;
import cn.mark.o2o.enums.UserProductMapStateEnum;
import cn.mark.o2o.exceptions.UserProductMapOperationException;
import cn.mark.o2o.service.UserProductMapService;
import cn.mark.o2o.util.PageCalculator;

@Service
public class UserProductMapServiceImpl implements UserProductMapService {
	@Autowired
	private UserProductMapDao userProductMapDao;
	@Autowired
	private UserShopMapDao userShopMapDao;

	@Override
	public UserProductMapExecution listUserProductMap(UserProductMap userProductCondition, Integer pageIndex,
			Integer pageSize) {
		// 空值判断
		if (userProductCondition != null && pageIndex != null && pageSize != null) {
			// 页转行
			int beginIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
			// 依据查询条件分页取出列表
			List<UserProductMap> userProductMapList = userProductMapDao.queryUserProductMapList(userProductCondition,
					beginIndex, pageSize);
			// 按照同等的查询条件获取总数
			int count = userProductMapDao.queryUserProductMapCount(userProductCondition);
			UserProductMapExecution se = new UserProductMapExecution();
			se.setUserProductMapList(userProductMapList);
			se.setCount(count);
			return se;
		} else {
			return null;
		}

	}

	/**
	 * 添加消费记录
	 */
	@Override
	@Transactional
	public UserProductMapExecution addUserProductMap(UserProductMap userProductMap)
			throws UserProductMapOperationException {
		// 空值判断，主要确保顾客Id，店铺Id以及操作员Id非空
		if (userProductMap != null && userProductMap.getUser().getUserId() != null
				&& userProductMap.getShop().getShopId() != null && userProductMap.getOperator().getUserId() != null) {
			// 设定默认值
			userProductMap.setCreateTime(new Date());
			try {
				// 添加消费记录
				int effectedNum = userProductMapDao.insertUserProductMap(userProductMap);
				if (effectedNum <= 0) {
					throw new UserProductMapOperationException("添加消费记录失败");
				}
				// 若本次消费能够积分
				if (userProductMap.getPoint() != null && userProductMap.getPoint() > 0) {
					// 查询该顾客是否在店铺消费过
					UserShopMap userShopMap = userShopMapDao.queryUserShopMap(userProductMap.getUser().getUserId(),
							userProductMap.getShop().getShopId());
					if (userShopMap != null && userShopMap.getUserShopId() != null) {
						// 若之前消费过，即有过积分记录，则进行总积分的更新操作
						userShopMap.setPoint(userShopMap.getPoint() + userProductMap.getPoint());
						effectedNum = userShopMapDao.updateUserShopMapPoint(userShopMap);
						if (effectedNum <= 0) {
							throw new UserProductMapOperationException("更新积分信息失败");
						}
					} else {
						// 在店铺没有过消费记录，添加一条店铺积分信息(就跟初始化会员一样)
						userShopMap = compactUserShopMap4Add(userProductMap.getUser().getUserId(),
								userProductMap.getShop().getShopId(), userProductMap.getPoint());
						effectedNum = userShopMapDao.insertUserShopMap(userShopMap);
						if (effectedNum <= 0) {
							throw new UserProductMapOperationException("积分信息创建失败");
						}
					}
				}
				return new UserProductMapExecution(UserProductMapStateEnum.SUCCESS, userProductMap);
			} catch (Exception e) {
				throw new UserProductMapOperationException("添加授权失败:" + e.toString());
			}
		} else {
			return new UserProductMapExecution(UserProductMapStateEnum.NULL_USERPRODUCT_INFO);
		}
	}

	/**
	 * 封装顾客积分信息
	 * 
	 * @param userId
	 * @param shopId
	 * @param point
	 * @return
	 */
	private UserShopMap compactUserShopMap4Add(Long userId, Long shopId, Integer point) {
		UserShopMap userShopMap = null;
		// 空值判断
		if (userId != null && shopId != null) {
			userShopMap = new UserShopMap();
			PersonInfo customer = new PersonInfo();
			customer.setUserId(userId);
			Shop shop = new Shop();
			shop.setShopId(shopId);
			userShopMap.setUser(customer);
			userShopMap.setShop(shop);
			userShopMap.setCreateTime(new Date());
			userShopMap.setPoint(point);
		}
		return userShopMap;
	}

}
