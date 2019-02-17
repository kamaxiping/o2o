package com.lianxi.o2o.service;

import java.io.IOException;
import java.util.List;

import com.lianxi.o2o.entity.HeadLine;

public interface HeadLineService {
	/**
	 * 根据传入的条件返回指定的头条列表
	 * @param headLineCondition
	 * @return
	 * @throws IOException
	 */
	List<HeadLine> getHeadLine(HeadLine headLineCondition) throws IOException;
}
