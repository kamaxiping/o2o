package com.lianxi.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lianxi.o2o.BaseTest;
import com.lianxi.o2o.dto.ImageHolder;
import com.lianxi.o2o.dto.ShopExecution;
import com.lianxi.o2o.entity.Area;
import com.lianxi.o2o.entity.PersonInfo;
import com.lianxi.o2o.entity.Shop;
import com.lianxi.o2o.entity.ShopCategory;
import com.lianxi.o2o.enums.ShopStateEnum;
import com.lianxi.o2o.exception.ShopOperationException;

public class ShopServiceTest extends BaseTest{
	@Autowired
	private ShopService shopService;
	@Test
	public void testGetShopList(){
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);
		shopCondition.setOwner(owner);
		ShopExecution se = shopService.getShopList(shopCondition, 1, 4);
		int x = se.getCount();
		System.out.println("店铺列表的大小：" + se.getShopList().size());
		System.out.println("店铺总数：" + x);
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(3L);
		shopCondition.setShopCategory(sc);
		se = shopService.getShopList(shopCondition, 2, 2);
		x = se.getCount();
		System.out.println("xin店铺列表的大小：" + se.getShopList().size());
		System.out.println("xin店铺总数：" + x);
	}
	@Test
	@Ignore
	public void testModifyShop() throws FileNotFoundException,ShopOperationException{
		Shop shop=new Shop();
		shop.setShopId(5L);
		shop.setShopName("修改后的店铺名称");
		File shopImg=new File("E:/work/image/dabai.jpg");
		InputStream is=new FileInputStream(shopImg);
		System.out.println(is);
		ImageHolder thumbnail=new ImageHolder("dabai.jpg",is);
		ShopExecution shopExecution=shopService.modifyShop(shop,thumbnail);
		System.out.println("新的图片地址为："+shopExecution.getShop().getShopImg());
	}
	@Test
	@Ignore
	public void testAddShop() throws FileNotFoundException,ShopOperationException{
		Area area=new Area();
		ShopCategory shopCategory=new ShopCategory();
		PersonInfo owner=new PersonInfo();
		Shop shop=new Shop();
		owner.setUserId(1L);
		area.setAreaId(1);
		shopCategory.setShopCategoryId(1L);
		shop.setArea(area);
		shop.setOwner(owner);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试的店铺3");
		shop.setShopDesc("test3");
		shop.setShopAddr("test3");
		shop.setPhone("test3");
		shop.setPriority(1);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg=new File("E:/work/image/lufei.jpg");
		InputStream is=new FileInputStream(shopImg);
		ImageHolder thumbnail=new ImageHolder(shopImg.getName(),is);
		ShopExecution se=shopService.addShop(shop,thumbnail);
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}
}
