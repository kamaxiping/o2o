package com.lianxi.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.lianxi.o2o.BaseTest;
import com.lianxi.o2o.entity.Product;
import com.lianxi.o2o.entity.ProductCategory;
import com.lianxi.o2o.entity.Shop;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 测试回环
public class ProductDaoTest extends BaseTest {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;
	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Test
	@Ignore
	public void CtestQueryProduct() {
		long shopId = 4l;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		assertEquals(4, productCategoryList.size());
		System.out.println("店铺类型数：" + productCategoryList.size());
	}

	@Test
	@Ignore
	public void testBQueryProductList() throws Exception {
		Product productCondition = new Product();
		// 分页查询，预期返回三条结果
		List<Product> productList = productDao.queryProductList(productCondition, 0, 3);
		assertEquals(3, productList.size());
		// 查询名称为测试的商品总数
		int count = productDao.queryProductCount(productCondition);
		assertEquals(8, count);
		// 使用商品名称模糊查询，预期返回两条结果
		productCondition.setProductName("测试");
		productList = productDao.queryProductList(productCondition, 0, 3);
		assertEquals(3, productList.size());
		count = productDao.queryProductCount(productCondition);
		assertEquals(4, count);

	}

	@Test
	@Ignore
	public void testInsertProduct() throws Exception {
		Shop shop1 = new Shop();
		shop1.setShopId(4L);
		ProductCategory productCategory1 = new ProductCategory();
		productCategory1.setProductCategoryId(1L);
		Product product1 = new Product();
		product1.setProductName("测试1");
		product1.setProductDesc("测试DESC1");
		product1.setImgAddr("test1");
		product1.setPriority(1);
		product1.setEnableStatus(1);
		product1.setCreateTime(new Date());
		product1.setLastEditTime(new Date());
		product1.setShop(shop1);
		product1.setProductCategory(productCategory1);
		Product product2 = new Product();
		product2.setProductName("测试2");
		product2.setProductDesc("测试DESC2");
		product2.setImgAddr("test2");
		product2.setPriority(1);
		product2.setEnableStatus(1);
		product2.setCreateTime(new Date());
		product2.setLastEditTime(new Date());
		product2.setShop(shop1);
		product2.setProductCategory(productCategory1);
		Product product3 = new Product();
		product3.setProductName("测试3");
		product3.setProductDesc("测试DESC3");
		product3.setImgAddr("test3");
		product3.setPriority(1);
		product3.setEnableStatus(1);
		product3.setCreateTime(new Date());
		product3.setLastEditTime(new Date());
		product3.setShop(shop1);
		product3.setProductCategory(productCategory1);

		int effectedNum = productDao.insertProduct(product1);
		assertEquals(1, effectedNum);
		effectedNum = productDao.insertProduct(product2);
		assertEquals(1, effectedNum);
		effectedNum = productDao.insertProduct(product3);
		assertEquals(1, effectedNum);
	}

	@Test
	@Ignore
	public void AtestDeleteProductCategory() throws Exception {
		long shopId = 5l;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		for (ProductCategory pc : productCategoryList) {
			if ("商品类别1".equals(pc.getProductCategoryName()) || "商品类别2".equals(pc.getProductCategoryName())) {
				int effectedNum = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), pc.getShopId());
				assertEquals(1, effectedNum);
			}
		}
	}
	@Test
	public void testUpdateProductCategoryToNull() {
		//将productCategoryId为2的商品类别下面的商品的商品类别置为空
		int effectedNum=productDao.updateProductCategoryToNull(1);
		assertEquals(8,effectedNum);
	}

}
