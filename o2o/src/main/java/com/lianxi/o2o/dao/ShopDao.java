package com.lianxi.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lianxi.o2o.entity.Shop;

public interface ShopDao {
	/**
	 * * 分页查询店铺，可输入的条件有：店铺名（模糊），店铺状态，店铺类别，区域ID，owner
	 * 
	 * @param shopCondition
	 * @param rowIndex从第几行开始取数据
	 * @param pageSize 返回的条数
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);
	/**
	 * 返回queryShopList总数
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);
	/**
	 * 通过shop id 查询店铺
	 */
	Shop queryByShopId(long shopId);

	/**
	 * 店家注册
	 */
	int insertShop(Shop shop);

	/**
	 * 更新店铺
	 */
	int updateShop(Shop shop);
}
