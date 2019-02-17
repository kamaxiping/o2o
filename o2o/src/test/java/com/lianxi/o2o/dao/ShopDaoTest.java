package com.lianxi.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lianxi.o2o.BaseTest;
import com.lianxi.o2o.entity.Area;
import com.lianxi.o2o.entity.PersonInfo;
import com.lianxi.o2o.entity.Shop;
import com.lianxi.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest {

	@Autowired
	private ShopDao shopDao;

	@Test
	@Ignore
	public void testQueryShopListAndCount() {
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);
		shopCondition.setOwner(owner);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 4);
		int x = shopDao.queryShopCount(shopCondition);
		System.out.println("店铺列表的大小：" + shopList.size());
		System.out.println("店铺总数：" + x);
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(14L);
		shopCondition.setShopCategory(sc);
		shopList = shopDao.queryShopList(shopCondition, 0, 2);
		x = shopDao.queryShopCount(shopCondition);
		System.out.println("xin店铺列表的大小：" + shopList.size());
		System.out.println("xin店铺总数：" + x);
	}

	@Test
//	@Ignore
	public void testQueryByShopId() {
		long shopId = 1l;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println("areaId:" + shop.getArea().getAreaId());
		System.out.println("areaName:" + shop.getArea().getAreaName());
	}

	@Test
	@Ignore
	public void testInsertShop() {
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		PersonInfo owner = new PersonInfo();
		Shop shop = new Shop();
		owner.setUserId(1L);
		area.setAreaId(1);
		shopCategory.setShopCategoryId(1L);
		shop.setArea(area);
		shop.setOwner(owner);
		shop.setShopCategory(shopCategory);
		shop.setShopName("蜜汁");
		shop.setShopDesc("咖啡");
		shop.setShopAddr("深圳");
		shop.setPhone("123567898453");
		shop.setShopImg("test");
		shop.setPriority(1);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("诚信经营");
		int x = shopDao.insertShop(shop);
		assertEquals(1, x);
	}

	@Ignore
	@Test
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(4l);
		shop.setShopName("蜜汁");
		shop.setShopDesc("咖啡");
		shop.setLastEditTime(new Date());
		shop.setAdvice("诚信经营,生意兴隆");
		int x = shopDao.updateShop(shop);
		assertEquals(1, x);
	}

}
