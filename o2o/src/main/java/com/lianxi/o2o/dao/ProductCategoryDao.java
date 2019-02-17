package com.lianxi.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lianxi.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	/**
	 * 通过shopId查询店铺商品类型
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> queryProductCategoryList(Long shopId);
	/**
	 * 批量新增商品类别
	 * @param productCategoryList
	 * @return
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);
	/**
	 * 删除指定商品类别
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId")long productCategoryId,@Param("shopId") long shopId);
}
