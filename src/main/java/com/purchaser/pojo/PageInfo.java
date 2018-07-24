package com.purchaser.pojo;

import java.util.List;

/**
 * 分页对象
 * 
 * @author 华文
 *
 */
public class PageInfo {

	private int currentPage;

	private int pageSize;

	private int totalCount;

	private int totalPage;

	private List<?> data;
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		int totalPage = this.totalCount % this.pageSize != 0 ? (this.totalCount / this.pageSize) + 1
				: this.totalCount / this.pageSize;
		this.totalPage = totalPage < 1 ? totalPage + 1 : totalPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
}
