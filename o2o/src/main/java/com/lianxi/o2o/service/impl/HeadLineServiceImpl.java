package com.lianxi.o2o.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lianxi.o2o.dao.HeadLineDao;
import com.lianxi.o2o.entity.HeadLine;
import com.lianxi.o2o.service.HeadLineService;
@Service
public class HeadLineServiceImpl implements HeadLineService {
	@Autowired
	private HeadLineDao headLineDao;
	@Override
	public List<HeadLine> getHeadLine(HeadLine headLineCondition) throws IOException {
		return headLineDao.queryHeadLine(headLineCondition);
	}
}
