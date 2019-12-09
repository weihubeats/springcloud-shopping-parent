package com.mybatis.service.impl;


import com.mybatis.dao.CountryDao;
import com.mybatis.po.CountryPo;
import com.mybatis.service.CountryService;
import com.mybatis.utils.PageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建日期 2019-07-22
 * 
 * @author 公共组
 * <br>
 * <b>功能：</b>CountryPo service接口实现类<br>
 */
@Service("countryService")
public class  CountryServiceImpl implements CountryService {
	@Autowired
    private CountryDao countryDao;

	@Override
	public PageSupport<CountryPo> getList(int startindex, int pagesize, CountryPo countryPo) {
		List<CountryPo> rows = countryDao.getList(startindex, pagesize,countryPo);

		PageSupport<CountryPo> pageSupport = new PageSupport<CountryPo>();

		pageSupport.setRows(rows);

		long count = countryDao.count(countryPo);

		pageSupport.setTotal(count);

		return pageSupport;
	}

	@Override
	public CountryPo detail(Long id) {
		return countryDao.detail(id);
	}

	@Override
	public int update(CountryPo countryPo) {
		return countryDao.update(countryPo);
	}

	@Override
	public int delete(Long id) {
		return countryDao.delete(id);
	}


	@Override
	public int save(CountryPo countryPo) {
		
		return countryDao.save(countryPo);
	}

	@Override
	public List<CountryPo> getAllList() {
		return countryDao.getAllList();
	}

	@Override
	public CountryPo getCountryByName(String name) {
		return countryDao.findCountryByName(name);
	}
}
