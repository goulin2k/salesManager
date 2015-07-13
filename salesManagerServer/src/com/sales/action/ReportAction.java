package com.sales.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import my.com.ibatis.core.dao.support.Page;
import com.sales.common.Constants;
import com.sales.common.NormalFun;
import com.sales.common.WorkCalendar;
import com.sales.model.K3OrderStatistics;
import com.sales.model.SSalesQuotation;
import com.sales.model.SUser;
import com.sales.model.SaleProcess;
import com.sales.service.K3OrderStatisticsService;
import com.sales.service.ReportService;
import com.sales.service.SUserService;

/** 
 * @author  
 * @version 创建时间：2014-3-1 下午04:41:58 
 *  
 */
public class ReportAction extends BaseAction{
	
	private ReportService reportService;
	private List quotationList;
	private SSalesQuotation quotation; 
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE;; // 页面大小
	private Page page ;
	private Integer roleId;
	private List saleProcList;
	private Date startTime;
	private Date endTime;
	private SaleProcess saleProcess;
	
	private int averageBillCheck;//平均业务审核时间
    private int averageFinCheck;//平均财务审核时间
    private int averagePorequestCheck;//平均采购审核时间
    private int averageStockin;//平均采购入库时间
    private int averageStockout;//平均领料通知
    private int averageSaleOut;//平均出库
    private int averageReplyDate;//平均回复天数
    private int maxReplyDate;//最长回复天数
    private int minReplyDate;//最短回复天数
    
    private K3OrderStatisticsService orderStatisticsService;
    private List<K3OrderStatistics> orderStatisticsList;		//销售统计数据列表
    private List<String> columns;
    private long colsLength;
    private Map<String, Double[]> mapStatistics;
    private Double amountAll;
    
    private static final int QUERY_TYPE_DAY = 1;
    private static final int QUERY_TYPE_WEEK = 2;
    private static final int QUERY_TYPE_MONTH = 3;
    
    public SaleProcess getSaleProcess() {
		return saleProcess;
	}

	public void setSaleProcess(SaleProcess saleProcess) {
		this.saleProcess = saleProcess;
	}

	public int getAverageBillCheck() {
		return averageBillCheck;
	}

	public void setAverageBillCheck(int averageBillCheck) {
		this.averageBillCheck = averageBillCheck;
	}

	public int getAverageFinCheck() {
		return averageFinCheck;
	}

	public void setAverageFinCheck(int averageFinCheck) {
		this.averageFinCheck = averageFinCheck;
	}

	public int getAveragePorequestCheck() {
		return averagePorequestCheck;
	}

	public void setAveragePorequestCheck(int averagePorequestCheck) {
		this.averagePorequestCheck = averagePorequestCheck;
	}

	public int getAverageStockin() {
		return averageStockin;
	}

	public void setAverageStockin(int averageStockin) {
		this.averageStockin = averageStockin;
	}

	public int getAverageStockout() {
		return averageStockout;
	}

	public void setAverageStockout(int averageStockout) {
		this.averageStockout = averageStockout;
	}

	public int getAverageSaleOut() {
		return averageSaleOut;
	}

	public void setAverageSaleOut(int averageSaleOut) {
		this.averageSaleOut = averageSaleOut;
	}

	public int getAverageReplyDate() {
		return averageReplyDate;
	}

	public void setAverageReplyDate(int averageReplyDate) {
		this.averageReplyDate = averageReplyDate;
	}

	public int getMaxReplyDate() {
		return maxReplyDate;
	}

	public void setMaxReplyDate(int maxReplyDate) {
		this.maxReplyDate = maxReplyDate;
	}

	public int getMinReplyDate() {
		return minReplyDate;
	}

	public void setMinReplyDate(int minReplyDate) {
		this.minReplyDate = minReplyDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List getSaleProcList() {
		return saleProcList;
	}

	public void setSaleProcList(List saleProcList) {
		this.saleProcList = saleProcList;
	}

	public SSalesQuotation getQuotation() {
		return quotation;
	}

	public void setQuotation(SSalesQuotation quotation) {
		this.quotation = quotation;
	}

	public Long getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Long pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * @return the columns
	 */
	public List<String> getColumns() {
		return columns;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	/**
	 * @return the days
	 */
	public long getDays() {
		return colsLength;
	}

	/**
	 * @param days the days to set
	 */
	public void setDays(long days) {
		this.colsLength = days;
	}

	/**
	 * @return the statisticsMap
	 */
	public Map<String, Double[]> getMapStatistics() {
		return mapStatistics;
	}

	/**
	 * @param statisticsMap the statisticsMap to set
	 */
	public void setMapStatistics(Map<String, Double[]> statisticsMap) {
		this.mapStatistics = statisticsMap;
	}

	/**
	 * @return the amountAll
	 */
	public Double getAmountAll() {
		return amountAll;
	}

	/**
	 * @param amountAll the amountAll to set
	 */
	public void setAmountAll(Double amountAll) {
		this.amountAll = amountAll;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List getQuotationList() {
		return quotationList;
	}

	public void setQuotationList(List quotationList) {
		this.quotationList = quotationList;
	}

	/**
	 * @return the orderStatisticsList
	 */
	public List<K3OrderStatistics> getOrderStatisticsList() {
		return orderStatisticsList;
	}

	/**
	 * @param orderStatisticsList the orderStatisticsList to set
	 */
	public void setOrderStatisticsList(List<K3OrderStatistics> orderStatisticsList) {
		this.orderStatisticsList = orderStatisticsList;
	}

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
	
	/**
	 * @param orderStatisticsService the orderStatisticsService to set
	 */
	public void setOrderStatisticsService(
			K3OrderStatisticsService orderStatisticsService) {
		this.orderStatisticsService = orderStatisticsService;
	}

	/** 
	 * 销售流程统计 
	 *  
	 */
	public String salesProcessReport(){
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		if(this.saleProcess == null){
			this.saleProcess = new SaleProcess();
			if(startTime != null && endTime != null){
				SimpleDateFormat datef=new SimpleDateFormat("yyyy-MM-dd");  
				this.saleProcess.setStartTime(datef.format(startTime));
				this.saleProcess.setEndTime(datef.format(endTime));
			}else{
				Calendar cal = Calendar.getInstance(Locale.CHINA);   			  
				SimpleDateFormat datef=new SimpleDateFormat("yyyy-MM-dd");  
				//本周一           
				cal.setFirstDayOfWeek(Calendar.MONDAY); 
				cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				this.startTime = cal.getTime();   
				this.saleProcess.setStartTime(datef.format(startTime));
				//本周末 
				int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2; 
				cal.add(Calendar.DATE, -day_of_week); 
				cal.add(Calendar.DATE, 6);
				this.endTime = cal.getTime();  
				this.saleProcess.setEndTime(datef.format(endTime));
			}
		}
		Integer totalCount = this.reportService.getSaleProcCount(saleProcess);
		if(totalCount.intValue() > 0){
			this.saleProcList = this.reportService.getSaleProcPageList(saleProcess,pageNumber.intValue(), pageSize);
		}
		List allSaleProcList = this.reportService.getSaleProcList(saleProcess);
		int sumBillCheck = 0;
	    int sumFinCheck = 0;
	    int sumPorequestCheck = 0;
	    int sumStockin = 0;
	    int sumStockout = 0;
	    int sumSaleOut = 0;
		if(allSaleProcList.size()>0){
			int tempCount = allSaleProcList.size();
			for(int i=0;i<allSaleProcList.size();i++){
				SaleProcess proc = new SaleProcess();
				proc = (SaleProcess) allSaleProcList.get(i);
				sumBillCheck = sumBillCheck + (proc.getBillCheckHours() == null ? 0:  proc.getBillCheckHours());
				sumFinCheck = sumFinCheck + (proc.getFinCheckHours() == null ? 0:  proc.getFinCheckHours());
				sumPorequestCheck = sumPorequestCheck + (proc.getPorequestCheckHours() == null ? 0:  proc.getPorequestCheckHours());
				sumStockin = sumStockin + (proc.getStockinHours() == null ? 0:  proc.getStockinHours());
				sumStockout = sumStockout + (proc.getStockoutHours() == null ? 0:  proc.getStockoutHours());
				sumSaleOut = sumSaleOut + (proc.getSaleoutHours() == null ? 0:  proc.getSaleoutHours()) ;
			}
			this.averageBillCheck = sumBillCheck/tempCount;
		    this.averageFinCheck = sumFinCheck/tempCount;
		    this.averagePorequestCheck = sumPorequestCheck/tempCount;
		    this.averageStockin = sumStockin/tempCount;
		    this.averageStockout = sumStockout/tempCount;
		    this.averageSaleOut = sumSaleOut/tempCount;
		}
		this.page = new Page();
		this.page.setData(this.saleProcList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		return "salesProcessReport";
	}
	
	/** 
	 * 销售询价统计 
	 *  
	 */
	public String salesInquiryReport(){
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String childUserIds = (String) permissionMap.get("childUserFilter"); 
		SUser sUser = getLoginUser();
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		if(this.quotation == null){
			this.quotation = new SSalesQuotation();
			if(startTime != null && endTime != null){
				SimpleDateFormat datef=new SimpleDateFormat("yyyy-MM-dd");  
				this.quotation.setStartTime(datef.format(startTime));
				this.quotation.setEndTime(datef.format(endTime));
			}else{
				Calendar cal = Calendar.getInstance(Locale.CHINA);   			  
				SimpleDateFormat datef=new SimpleDateFormat("yyyy-MM-dd");  
				//本周一           
				cal.setFirstDayOfWeek(Calendar.MONDAY); 
				cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				this.startTime = cal.getTime();   
				this.quotation.setStartTime(datef.format(startTime));
				//本周末 
				int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2; 
				cal.add(Calendar.DATE, -day_of_week); 
				cal.add(Calendar.DATE, 6);
				this.endTime = cal.getTime();  
				this.quotation.setEndTime(datef.format(endTime));
			}
		}
		Integer totalCount = this.reportService.getQuotationCount(this.quotation, childUserIds, sUser.getRoleId());
		if(totalCount.intValue() > 0){
			this.quotationList = this.reportService.getQuotationList(this.quotation, pageNumber.intValue(), pageSize, childUserIds, sUser.getRoleId());
		}
		List allQuotationList = this.reportService.getAllQuotationListReplied(quotation);
		int sumReplyDate = 0;
	    this.maxReplyDate = 0;
	    this.minReplyDate = 0;
		if(allQuotationList.size()>0){
			int tempCount = allQuotationList.size();
			for(int i=0;i<allQuotationList.size();i++){
				SSalesQuotation sQqotation = new SSalesQuotation();
				sQqotation = (SSalesQuotation) allQuotationList.get(i);
				sumReplyDate = sumReplyDate + sQqotation.getReplyHours();
				if(maxReplyDate < sQqotation.getReplyHours()){
					this.maxReplyDate = sQqotation.getReplyHours();
				}
				if(minReplyDate > sQqotation.getReplyHours()){
					this.minReplyDate = sQqotation.getReplyHours();
				}
			}
			double averageDate = (double)sumReplyDate/tempCount;
			averageReplyDate = (int)Math.ceil(averageDate);
		}
		this.page = new Page();
		this.page.setData(this.quotationList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		this.roleId = sUser.getRoleId();
		return "salesInquiryReport";
	}
	
	/**
	 * 销售日报表
	 * @return
	 */
	public String orderDaily() {
		Calendar cal = Calendar.getInstance();
		columns = new ArrayList<String>();
		amountAll = 0.0;
		mapStatistics = new HashMap<String, Double[]>();
		Integer userId = getLoginUser().getUserId();
		
		initQueryTime(QUERY_TYPE_DAY);
		orderStatisticsList = orderStatisticsService.statByDay(
				startTime, endTime, userId);
		
		colsLength = WorkCalendar.diffDays(startTime, endTime) + 1;
		initDisplayColumn(QUERY_TYPE_DAY);
		
		for (Iterator iterator = orderStatisticsList.iterator(); iterator.hasNext();) {
			K3OrderStatistics st = (K3OrderStatistics) iterator.next();
			String userName = st.getEmpName();
			
			if(!mapStatistics.containsKey(userName)) {
				initRowData(userName);
			}			
			addRowData(QUERY_TYPE_DAY, st, userName);
		}
		
		/**TODO	FOR debug	
		for (Iterator iterator = mapStatistics.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, Double[]> e = (Entry) iterator.next();
			actionLog.warn(e.getKey());
			Double[] ss = e.getValue();
			for (int i = 0; i < ss.length; i++) {
				actionLog.warn("\t----" + i + "---\t" + ss[i].toString());
			}
		}	*/
		
		
		return "orderDaily";
	}

		
	/**
	 * 销售周报表
	 * @return
	 */
	public String orderWeekly() {
		columns = new ArrayList<String>();
		amountAll = 0.0;
		mapStatistics = new HashMap<String, Double[]>();
		Integer userId = getLoginUser().getUserId();
		
		initQueryTime(QUERY_TYPE_WEEK);
		orderStatisticsList = orderStatisticsService.statByWeek(
				startTime, endTime, userId);
		
		colsLength = WorkCalendar.diffWeeks(startTime, endTime) + 1;
		initDisplayColumn(QUERY_TYPE_WEEK);
		
		for (Iterator iterator = orderStatisticsList.iterator(); iterator.hasNext();) {
			K3OrderStatistics st = (K3OrderStatistics) iterator.next();
			String userName = st.getEmpName();
			
			if(!mapStatistics.containsKey(userName)) {
				initRowData(userName);
			}			
			addRowData(QUERY_TYPE_WEEK, st, userName);
		}
		
		/**TODO	FOR debug	*/
		for (Iterator iterator = mapStatistics.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, Double[]> e = (Entry) iterator.next();
			actionLog.warn(e.getKey());
			Double[] ss = e.getValue();
			for (int i = 0; i < ss.length; i++) {
				actionLog.warn("\t----" + i + "---\t" + ss[i].toString());
			}
		}	
		return "orderWeekly";
	}
	
	/**
	 * 销售月报表
	 * @return
	 */
	public String orderMonthly() {
		columns = new ArrayList<String>();
		amountAll = 0.0;
		mapStatistics = new HashMap<String, Double[]>();
		Integer userId = getLoginUser().getUserId();
		
		initQueryTime(QUERY_TYPE_MONTH);
		orderStatisticsList = orderStatisticsService.statByMonth(
				startTime, endTime, userId);
		
		colsLength = WorkCalendar.diffMonths(startTime, endTime) + 1;
		initDisplayColumn(QUERY_TYPE_MONTH);
		
		for (Iterator iterator = orderStatisticsList.iterator(); iterator.hasNext();) {
			K3OrderStatistics st = (K3OrderStatistics) iterator.next();
			String userName = st.getEmpName();
			
			if(!mapStatistics.containsKey(userName)) {
				initRowData(userName);
			}			
			addRowData(QUERY_TYPE_MONTH, st, userName);
		}
		
		/**TODO	FOR debug	*/
		for (Iterator iterator = mapStatistics.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, Double[]> e = (Entry) iterator.next();
			actionLog.warn(e.getKey());
			Double[] ss = e.getValue();
			for (int i = 0; i < ss.length; i++) {
				actionLog.warn("\t----" + i + "---\t" + ss[i].toString());
			}
		}	
		return "orderMonthly";
	}
	
	/**
	 * 销售收款月报表
	 * @return
	 */
	public String recieveMonthly() {
		return "recieveMonthly";
	}
	
	/**
	 * @param st
	 * @param rowKey
	 */
	private void addRowData(int queryType, K3OrderStatistics st, String rowKey) {
		long interval = 0;
		Calendar cal = Calendar.getInstance();
		Date colDate = null;
		Double[] values = (Double[])mapStatistics.get(rowKey);
		
		
		
		if(queryType == QUERY_TYPE_DAY) {
			cal.set(Calendar.YEAR, st.getYear());
			cal.set(Calendar.MONTH, st.getMonth()-1);
			cal.set(Calendar.DAY_OF_MONTH, st.getDay());
			colDate = cal.getTime();
			interval = WorkCalendar.diffDays(startTime, colDate);
		}
		else if(queryType == QUERY_TYPE_WEEK) {
			cal.set(Calendar.YEAR, st.getYear());
			cal.set(Calendar.WEEK_OF_YEAR, st.getWeek());
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			colDate = cal.getTime();
			interval = WorkCalendar.diffWeeks(startTime, colDate);
		}else if(queryType == QUERY_TYPE_MONTH) {
			cal.set(Calendar.YEAR, st.getYear());
			cal.set(Calendar.MONTH, st.getMonth()-1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			colDate = cal.getTime();
			interval = WorkCalendar.diffMonths(startTime, colDate);
		}
		
		if(interval < colsLength) {
			values[(new Long(interval)).intValue()]=st.getAmount();
			amountAll += st.getAmount();
			double sum = new Double(values[(new Long(colsLength)).intValue()]);
			sum += st.getAmount();
			values[(new Long(colsLength)).intValue()] = sum;
			mapStatistics.put(rowKey, values);
		}
	}

	/**
	 * @param rowKey
	 */
	private void initRowData(String rowKey) {
		Double[] vals = new Double[(new Long(colsLength)).intValue()+1]; //增加“小计”列
		for (int i = 0; i < vals.length; i++) {
			vals[i] = 0.0;
		}
		mapStatistics.put(rowKey, vals);
	}

	/**
	 * 
	 */
	private void initDisplayColumn(int queryType) {
		String colName = null;
		for(int i=0 ; i<colsLength; i++) {
			if(queryType == QUERY_TYPE_DAY) {
				Date date = WorkCalendar.addDays(startTime, i);
				colName = NormalFun.formatDateString(date);
			}else if(queryType == QUERY_TYPE_WEEK) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(startTime);
				cal.add(Calendar.WEEK_OF_YEAR, i);
				colName = cal.get(Calendar.YEAR) + "年 第" +  
						cal.get(Calendar.WEEK_OF_YEAR) + "周";
			}else if(queryType == QUERY_TYPE_MONTH) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(startTime);
				cal.add(Calendar.MONTH, i);
				colName = cal.get(Calendar.YEAR) + "年 第" +  
						(cal.get(Calendar.MONTH)+1) + "月";
			}
			columns.add(colName);
		}
		columns.add("小计");
	}

	/**
	 * 设置默认查询时间段
	 * 
	 */
	private void initQueryTime(int queryType) {
		Calendar cal = Calendar.getInstance();
		
		if(endTime == null)
			endTime = new Date();
		
		if(startTime == null) {	
			cal.setTime(endTime);
			if(queryType == QUERY_TYPE_DAY) {
				cal.add(Calendar.DAY_OF_MONTH, -10);
				startTime = cal.getTime();
			}else if(queryType == QUERY_TYPE_WEEK) {
				cal.add(Calendar.MONTH, -2);
				startTime = cal.getTime();
			}else if(queryType == QUERY_TYPE_MONTH) {
				cal.add(Calendar.MONTH, -10);
				startTime = cal.getTime();
			}
		}
	}


}
