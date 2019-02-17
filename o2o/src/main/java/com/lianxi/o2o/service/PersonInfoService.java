package com.lianxi.o2o.service;

import com.lianxi.o2o.entity.PersonInfo;

public interface PersonInfoService {

	/**
	 * 根据用户Id获取personInfo信息
	 * @param userId
	 * @return
	 */
	PersonInfo getPersonInfoById(Long userId);

}
