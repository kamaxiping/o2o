package com.lianxi.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lianxi.o2o.dao.ProductCategoryDao;
import com.lianxi.o2o.dao.ProductDao;
import com.lianxi.o2o.dto.ProductCategoryExecution;
import com.lianxi.o2o.entity.ProductCategory;
import com.lianxi.o2o.enums.ProductCategoryStateEnum;
import com.lianxi.o2o.exception.ProductCategoryOperationException;
import com.lianxi.o2o.service.ProductCategoryService;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Autowired
	private ProductDao productDao;
	@Override
	public List<ProductCategory> getProductCategoryList(Long shopId) {
		return productCategoryDao.queryProductCategoryList(shopId);
	}
	@Override
	@Transactional
	public ProductCategoryExecution batchAddProductCateogory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException {
		if(productCategoryList!=null&&productCategoryList.size()>0){
			try{
				int effectedNum=productCategoryDao.batchInsertProductCategory(productCategoryList);
				if(effectedNum<=0){
					throw new ProductCategoryOperationException("店铺类别创建失败");
				}else{
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}
			}catch(Exception e){
				throw new ProductCategoryOperationException("batchAddProductCategory error:"+e.getMessage());
			}
		}else{
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}
	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException {
		//TODO 将此类别下的商品里的类别id置为空
		try{
			int effectedNum=productDao.updateProductCategoryToNull(productCategoryId);
			if(effectedNum<0){
				throw new ProductCategoryOperationException("商品类别更新失败");
			}
		}catch(Exception e){
			throw new ProductCategoryOperationException("deleteProductCategory error:"+e.getMessage());
		}
		try{
			int effectedNum=productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if(effectedNum<=0){
				throw new ProductCategoryOperationException("商品类别删除失败");
			}else{
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		}catch(Exception e){
			throw new ProductCategoryOperationException("deleteProductCategory error:"+e.getMessage());
		}
	}

}
