package com.lianxi.o2o.web.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lianxi.o2o.entity.Area;
import com.lianxi.o2o.service.AreaService;

@Controller
@RequestMapping("superadmin")
public class AreaController {
	Logger logger=LoggerFactory.getLogger(AreaController.class);
	@Autowired
	private AreaService areaService;
	@RequestMapping(value = "/listarea", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryArea() {
		logger.info("=========start==============");
		long startTime=System.currentTimeMillis();
		Map<String, Object> modelArea = new HashMap<String, Object>();
		List<Area> areaList = new ArrayList<>();
		try {
			areaList = areaService.getAreaList();
			modelArea.put("areaList", areaList);
			modelArea.put("total", areaList.size());
		} catch (Exception e) {
			modelArea.put("success", false);
			modelArea.put("errMsg", e.getMessage());
		}
		logger.error("=====test errror=======");
		long endTime=System.currentTimeMillis();
		logger.debug("costTime:[{}ms]",endTime-startTime);
		logger.info("==========end==============");
		return modelArea;
	}
}
