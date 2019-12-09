package com.mybatis.dao;

import java.util.List;

import com.mybatis.po.CountryPo;
import org.apache.ibatis.annotations.Param;
	
/**
 * 创建日期 2019-07-22
 * 
 * @author 公共组
 * <br>
 * <b>功能：</b>国家信息 Dao接口实现<br>
 */
public interface CountryDao{
	
	
	List<CountryPo> getList(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize, @Param("countryPo") CountryPo countryPo);
	
	long count(@Param("countryPo") CountryPo countryPo);
	
	
	CountryPo detail(@Param("id") Long id);
	
	int update(CountryPo countryPo);
	
	int delete(@Param("id") Long id);
	
	
	int save(CountryPo countryPo);
	//查询全部国家
	List<CountryPo> getAllList();

	//根据姓名查询国家
	CountryPo findCountryByName(@Param("countryName") String name);
	
}
