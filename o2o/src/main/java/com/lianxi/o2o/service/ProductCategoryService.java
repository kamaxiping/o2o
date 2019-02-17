package com.lianxi.o2o.service;

import java.util.List;

import com.lianxi.o2o.dto.ProductCategoryExecution;
import com.lianxi.o2o.entity.ProductCategory;
import com.lianxi.o2o.exception.ProductCategoryOperationException;


public interface ProductCategoryService {
	/**
	 * 获取商品类别列表
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> getProductCategoryList(Long shopId);
	/**
	 * 
	 * @param productCategoryList
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	
	ProductCategoryExecution batchAddProductCateogory(List<ProductCategory> productCategoryList)
	throws ProductCategoryOperationException;
	/**
	 * 将此类别下的商品里的类别id置为空，再删除掉该商品类别
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId,long shopId)
	throws ProductCategoryOperationException;
}
