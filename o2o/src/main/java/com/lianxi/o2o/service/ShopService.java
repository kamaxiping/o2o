package com.lianxi.o2o.service;

import com.lianxi.o2o.dto.ImageHolder;
import com.lianxi.o2o.dto.ShopExecution;
import com.lianxi.o2o.entity.Shop;
import com.lianxi.o2o.exception.ShopOperationException;

public interface ShopService {
	/**
	 * 根据shopCondition分页返回相应列表数据
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
	/**
	 * 通过shop id 查询shop信息
	 * 
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);
	/**
	 * 更新店铺信息，包括对图片的处理
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution modifyShop(Shop shop,ImageHolder thumbnail)throws ShopOperationException;
	/**
	 * 注册店铺信息，包括图片处理
	 * 
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 */
	ShopExecution addShop(Shop shop, ImageHolder thumbnail);
}
