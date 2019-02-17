package com.lianxi.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.lianxi.o2o.BaseTest;
import com.lianxi.o2o.entity.ProductCategory;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//测试回环
public class ProductCategoryDaoTest extends BaseTest {
	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Test
	public void CtestQueryProductCategory() {
		long shopId=4l;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		assertEquals(4, productCategoryList.size());
		System.out.println("店铺类型数："+productCategoryList.size());
	}
	@Test
	public void BtestBatchInsertProductCategory() {
		ProductCategory productCategory=new ProductCategory();
		productCategory.setProductCategoryName("商品类别1");
		productCategory.setPriority(1);
		productCategory.setCreateTime(new Date());
		productCategory.setShopId(5L);
		ProductCategory productCategory2=new ProductCategory();
		productCategory2.setProductCategoryName("商品类别2");
		productCategory2.setPriority(1);
		productCategory2.setCreateTime(new Date());
		productCategory2.setShopId(5L);
		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		productCategoryList.add(productCategory);
		productCategoryList.add(productCategory2);
		int effectedNum=productCategoryDao.batchInsertProductCategory(productCategoryList);
		assertEquals(2, effectedNum);
	}
	@Test
	public void AtestDeleteProductCategory() throws Exception{
		long shopId=5l;
		List<ProductCategory> productCategoryList=productCategoryDao.queryProductCategoryList(shopId);
		for(ProductCategory pc:productCategoryList){
			if("商品类别1".equals(pc.getProductCategoryName())||"商品类别2".equals(pc.getProductCategoryName())){
				int effectedNum=productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), pc.getShopId());
				assertEquals(1,effectedNum);
			}
		}
	}
}
