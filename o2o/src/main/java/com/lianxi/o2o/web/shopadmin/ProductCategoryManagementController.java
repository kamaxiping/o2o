package com.lianxi.o2o.web.shopadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lianxi.o2o.dto.ProductCategoryExecution;
import com.lianxi.o2o.dto.Result;
import com.lianxi.o2o.entity.ProductCategory;
import com.lianxi.o2o.entity.Shop;
import com.lianxi.o2o.enums.ProductCategoryStateEnum;
import com.lianxi.o2o.exception.ProductCategoryOperationException;
import com.lianxi.o2o.service.ProductCategoryService;

@Controller
@RequestMapping(value = "/shopadmin", method = { RequestMethod.GET })
public class ProductCategoryManagementController {
	@Autowired
	private ProductCategoryService productCategoryService;

	@RequestMapping(value = "/getproductcategorylist", method = { RequestMethod.GET })
	@ResponseBody
	/*
	 * private Map<String, Object> getProductCategoryList(HttpServletRequest
	 * request) { long shopId=4L; Map<String, Object> modelMap = new
	 * HashMap<String, Object>(); List<ProductCategory> productCategoryList =
	 * new ArrayList<>(); try { productCategoryList =
	 * productCategoryService.getProductCategoryList(shopId);
	 * modelMap.put("productCategoryList", productCategoryList);
	 * modelMap.put("success", true); } catch (Exception e) {
	 * modelMap.put("success", false); modelMap.put("errMsg", e.getMessage()); }
	 * return modelMap; }
	 */
	private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {
		/*
		 * Shop shop=new Shop(); shop.setShopId(4L);
		 * request.getSession().setAttribute("currentShop", shop);
		 */
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		List<ProductCategory> list = null;
		if (currentShop != null && currentShop.getShopId() > 0) {
			list = productCategoryService.getProductCategoryList(currentShop.getShopId());
			return new Result<List<ProductCategory>>(true, list);
		} else {
			ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
			return new Result<List<ProductCategory>>(false, ps.getStateInfo(), ps.getState());
		}
	}

	@RequestMapping(value = "/addproductcategorys", method = { RequestMethod.POST })
	@ResponseBody
	private Map<String, Object> addProductCategorys(@RequestBody List<ProductCategory> productCategoryList,
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		for (ProductCategory pc : productCategoryList) {
			pc.setShopId(currentShop.getShopId());
		}
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				ProductCategoryExecution pe = productCategoryService.batchAddProductCateogory(productCategoryList);
				if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (ProductCategoryOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少输入一个商品类型");
		}
		return modelMap;
	}
	@RequestMapping(value = "/removeproductcategory", method = { RequestMethod.POST })
	@ResponseBody
	private Map<String, Object> removeProductCategorys(Long productCategoryId,
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (productCategoryId != null && productCategoryId > 0) {
			try {
				Shop currentShop=(Shop) request.getSession().getAttribute("currentShop");
				ProductCategoryExecution pe = productCategoryService.deleteProductCategory(productCategoryId, currentShop.getShopId());
				if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (ProductCategoryOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少选择一个商品类型");
		}
		return modelMap;
	}
}
