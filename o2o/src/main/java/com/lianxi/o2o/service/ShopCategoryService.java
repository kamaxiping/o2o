package com.lianxi.o2o.service;

import java.util.List;

import com.lianxi.o2o.entity.ShopCategory;

public interface ShopCategoryService {
	/**
	 * 根据传入的条件返回指定的商品类型列表
	 * @param shopCategoryCondition
	 * @return
	 */
  List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
