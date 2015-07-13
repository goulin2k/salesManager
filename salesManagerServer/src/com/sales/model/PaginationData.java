/**
 * 
 */
package com.sales.model;

import java.util.List;

/**
 * @author Administrator
 *
 */
public class PaginationData extends BaseObject {
	private int total;
	private List<Object> rows;
	private int pageSize;
	private int pages;
	private int pageNumber;
	private int msgType;
	/**
	 * 
	 */
	public PaginationData() {
		super();
		this.total = 0;
		this.pageSize = 10;
		this.pageNumber = 1;
		this.pages = 0;
		this.msgType = 0;
	}
	
	
	/**
	 * @param total
	 * @param rows
	 */
	public PaginationData(int total, int pageSize, 
			int pageNumber, List<Object> rows, int msgType) {
		this();
		this.total = total;
		this.rows = rows;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.pages = ((total%pageSize) == 0) ? 
				(total / pageSize) : (total / pageSize + 1);
		this.msgType = msgType;
	}
	
	public PaginationData(int total, int pageSize, 
			int pageNumber, List<Object> rows) {
		this();
		this.total = total;
		this.rows = rows;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.pages = ((total%pageSize) == 0) ? 
				(total / pageSize) : (total / pageSize + 1);
		this.msgType = 0;
	}


	public int getPages() {
		return pages;
	}


	public void setPages(int pages) {
		this.pages = pages;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getPageNumber() {
		return pageNumber;
	}


	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}


	/**
	 * @return
	 */
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public List<Object> getRows() {
		return rows;
	}
	
	public void setRows(List<Object> rows) {
		this.rows = rows;
	}


	/**
	 * @return the msgType
	 */
	public int getMsgType() {
		return msgType;
	}


	/**
	 * @param msgType the msgType to set
	 */
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	
	

}
