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
import com.lianxi.o2o.entity.ProductImg;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//测试回环
public class ProductImgDaoTest extends BaseTest {
	@Autowired
	private ProductImgDao productImgDao;

/*	@Test
	public void CtestQueryProductCategory() {
		long shopId=4l;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		assertEquals(4, productCategoryList.size());
		System.out.println("店铺类型数："+productCategoryList.size());
	}*/
	@Test
	public void BtestBatchInsertProductImg() {
		ProductImg productImg=new ProductImg();
		productImg.setImgAddr("图片1");
		productImg.setImgDesc("测试图片1");
		productImg.setPriority(1);
		productImg.setCreateTime(new Date());
		productImg.setProductId(1L);
		ProductImg productImg2=new ProductImg();
		productImg2.setImgAddr("图片2");
		productImg2.setImgDesc("测试图片2");
		productImg2.setPriority(1);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(1L);
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg);
		productImgList.add(productImg2);
		int effectedNum=productImgDao.batchInsertProductImg(productImgList);
		assertEquals(2, effectedNum);
	}
	/*@Test
	public void AtestDeleteProductCategory() throws Exception{
		long shopId=5l;
		List<ProductCategory> productCategoryList=productCategoryDao.queryProductCategoryList(shopId);
		for(ProductCategory pc:productCategoryList){
			if("商品类别1".equals(pc.getProductCategoryName())||"商品类别2".equals(pc.getProductCategoryName())){
				int effectedNum=productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), pc.getShopId());
				assertEquals(1,effectedNum);
			}
		}
	}*/
}
