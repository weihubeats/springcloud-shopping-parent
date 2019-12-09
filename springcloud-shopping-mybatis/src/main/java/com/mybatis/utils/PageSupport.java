package com.mybatis.utils;

import java.util.ArrayList;
import java.util.List;

public class PageSupport<T> {

	private List<T> rows = new ArrayList<T>();

	private long total = 0;

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		if (total >= 0L) {
			this.total = total;
		}
	}
}
	

	
	
	
	
