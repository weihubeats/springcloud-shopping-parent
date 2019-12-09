package com.mybatis.service;


import com.mybatis.po.CountryPo;
import com.mybatis.utils.PageSupport;

import java.util.List;

/**
 * 创建日期 2019-07-22
 * 
 * @author 公共组 
 * <br>
 * <b>功能：</b>国家 service接口类<br>
 */
public interface CountryService{

		PageSupport<CountryPo> getList(int startindex, int pagesize, CountryPo countryPo);

		CountryPo detail(Long id);
		
		int update(CountryPo countryPo);
		
		int delete(Long id);
		
		int save(CountryPo countryPo);

		//得到全部国家
	    List<CountryPo> getAllList();

	    //根据姓名查找
	     CountryPo getCountryByName(String name);

}
