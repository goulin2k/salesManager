package com.sales.common;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sales.model.SaleBillStatus;

public class NormalFun {
	
	/**	从ISO编码字符串转换为GBK
	 * @param str
	 * @return
	 */
	public static String getGBStr(String str){
		if (str == null || str.trim().equalsIgnoreCase(""))
			return null;
        try{
            String temp_p=str;
            String temp=new String(temp_p.getBytes("ISO8859_1"), "GBK");
            return temp;
        }catch(Exception e){
            return null;
        }
    } 
	
	/**	从ISO编码字符串转换为UTF-8
	 * @param str
	 * @return
	 */
	public static String getUTF8(String str){
		if (str == null || str.trim().equalsIgnoreCase(""))
			return null;
        try{
            String temp_p=str;
            String temp=new String(temp_p.getBytes("ISO8859_1"), "UTF-8");
            return temp;
        }catch(Exception e){
            return null;
        }
    } 

	/**	从GBK编码字符串转换为UTF-8
	 * @param str
	 * @return
	 */
	public static String getUtf8Str(String str){
		if (str == null || str.trim().equalsIgnoreCase(""))
			return null;
        try{
            String temp_p=str;
            String temp=new String(temp_p.getBytes("GBK"), "UTF-8");
            return temp;
        }catch(Exception e){
            return null;
        }
    } 
	
	public static String getUTF8ToGBK(String str){
		if (str == null || str.trim().equalsIgnoreCase(""))
			return null;
        try{
            String temp_p=str;
            String temp=new String(temp_p.getBytes("GBK"), "UTF-8");
            return temp;
        }catch(Exception e){
            return null;
        }
    } 
	
	/**	从GBK编码字符串转换为ISO
	 * @param str
	 * @return
	 */
	public static String getISOToGBK(String str){
        try{
            String temp_p=str;
            String temp=new String(temp_p.getBytes("GBK"), "ISO8859_1");
            return temp;
        }catch(Exception e){
            return "null";
        }
    }
	/**	转换字符串数组为整形数组
	 * @param strArray
	 * @return int[]
	 */
	public static int[] converStringArrayToInt(String[] strArray) {
	    if (strArray == null)
	        return null;
        int[] intArray = new int[strArray.length];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        
        return intArray;
    }
	
	/**	转换字符串数组为整形数组
	 * @param strArray
	 * @return int[]
	 */
	public static long[] converStringArrayToLong(String[] strArray) {
	    if (strArray == null)
	        return null;
        long[] longArray = new long[strArray.length];
        for (int i = 0; i < longArray.length; i++) {
            longArray[i] = Long.parseLong(strArray[i]);
        }
        
        return longArray;
    }
	
	/**	转换字符串数组为double数组
	 * @param strArray
	 * @return double[]
	 */
	public static double[] converStringArrayToDouble(String[] strArray) {
	    if (strArray == null)
	        return null;
        double[] doubleArray = new double[strArray.length];
        for (int i = 0; i < doubleArray.length; i++) {
            doubleArray[i] = Double.parseDouble(strArray[i]);
        }
        
        return doubleArray;
        
    }
	
	
	
	
	
	/**	将List对象转换为字符串，用","分隔
	 * @param list
	 * @return
	 */
	public static String listToString(List list){
		StringBuffer str = new StringBuffer();
		if(list!=null && list.size()!=0){
			str.append("(");
			for(int i=0; i<list.size(); i++){
				str.append("'").append(list.get(i)).append("'").append(",");
			}
			str.deleteCharAt(str.length()-1);
			str.append(")");
		}
		return str.toString();
	}
	
	
	
	/**该Integer在该List中的位置
	 * @param list
	 * @param agentId
	 * @return
	 */
	public static int getPosition(List list,Integer integer){
		for(int i=0; i<list.size(); i++){
			Integer lb = (Integer)list.get(i);
			if(lb.equals(integer)){
				return i;
			}
		}
		return -1;
	}
	
	/**	将数组转换为“,”分隔的字符串
	 * @param strAarry
	 * @return
	 */
	public static String getQueryIn(String strAarry){
		if(strAarry == null || strAarry.equalsIgnoreCase("")){
			return "";
		}
		else{
			String[] str = strAarry.split(",");
			StringBuffer strIds = new StringBuffer();
			strIds.append("(");
			for(int i=0; i<str.length; i++){
				strIds.append("'").append(str[i]).append("'").append(",");
			}
			if(strIds.length() > 0){
				strIds.deleteCharAt(strIds.length()-1);
				strIds.append(")");
			}
			return strIds.toString();
		}
	}	
	
	/**	长字符串转化为长度为小于10个的短字符，用“……”补齐
	 * @param str
	 * @return
	 */
	public static String getShort(String str){
		if(str.length() < 10){
			return str;
		}
		else{
			return str.substring(0,10) + "...";
		}
	}
	
	/**	获得格式化后的当前日期(yyyy-MM-dd)
	 * @return
	 */
	public static String formatDateString() {	    
	    Date now = new Date();
	    return formatDateString(now);
	}
	
	/**	字符串转换为Date类型
	 * @param stringDate format:yyyy-MM-dd
	 * @return
	 * @throws FormatException
	 */
	public static Date toDate(String stringDate) throws ParseException {
		if (stringDate == null)
			return null;
		if(stringDate.equalsIgnoreCase(""))
			return null;
		try {
			SimpleDateFormat spdf = new SimpleDateFormat("yyyy-MM-dd");
			return spdf.parse(stringDate);
		}catch(ParseException pe) {
			throw pe;
		}
		
	}
	
	/**	将指定的字符串按照指定格式转换为Date类型
	 * @param stringDate
	 * @param format
	 * @return
	 * @throws FormatException
	 */
	public static Date toDate(String stringDate, String format) {
		if (stringDate == null)
			return null;
		if(stringDate.equalsIgnoreCase(""))
			return null;
		try {
			SimpleDateFormat spdf = new SimpleDateFormat(format);
			return spdf.parse(stringDate);
		}catch(ParseException pe) {
			pe.printStackTrace();
			return null;
		}
	}
	
	/**	获得格式化后的当前日期(yyyy-MM-dd)
	 * @param date
	 * @return
	 */
	public static String formatDateString(Date date) {
		if (date ==null) return null;
	    SimpleDateFormat spdf = new SimpleDateFormat("yyyy-MM-dd");
	    return spdf.format(date);
	}
	
	/**	获得格式化后的当前日期(yyyy-MM-dd)
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date formatStringDate(String date) throws ParseException{
	    SimpleDateFormat spdf = new SimpleDateFormat("yyyy-MM-dd");
	    return spdf.parse(date);
	}
	
	/**	获得格式化后的当前日期时间(yyyy-MM-dd hh:mm:ss)
	 * @return
	 */
	public static String formatDateTimeString() {	    
	    Date now = new Date();
	    return formatDateTimeString(now);
	}
	
	/**	获得格式化后的当前日期时间(yyyy-MM-dd hh:mm:ss)
	 * @return
	 */
	public static String formatDateTimeString(Date date) {
		if(date == null)
			return null;
	    SimpleDateFormat spdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return spdf.format(date);
	}
	
	/**	获得格式化后的当前日期时间(yyyy-MM-dd hh:mm)
	 * @return
	 */
	public static String formatDateTimeStringNoSecond(Date date) {
	    SimpleDateFormat spdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	    return spdf.format(date);
	}
	
	public static Date formatStringDateTime(String date) {
		try{
		    SimpleDateFormat spdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    return spdf.parse(date);
		}catch(ParseException e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 格式化货币输出
	 * @param number
	 * @return
	 */
	public static String formatCurrency(Double number) {
		if(number == null)
			return null;
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
		return currencyFormat.format(number);

	}
	
	/**
	 * 百分数格式化输出
	 * @param number
	 * @return
	 */
	public static String formatPercent(Double number) {
		if(number == null) {
			return "0.0";
		}
		NumberFormat persentFormat = NumberFormat.getPercentInstance();
		return persentFormat.format(number);
	}
	
	/**
	 * @param number
	 * @return
	 */
	public static String formatDouble(Double number) {
		if(number == null) {
			return "0.0";
		}
		NumberFormat persentFormat = NumberFormat.getNumberInstance();
		return persentFormat.format(number);
	}
	
	/**是否为闰年
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
	    if (year / 4 * 4 != year){
	      return false; //不能被4整除
	    }
	    else if (year / 100 * 100 != year){
	      return true; //能被4整除，不能被100整除
	    }
	    else if (year / 400 * 400 != year){
	      return false; //能被100整除，不能被400整除
	    }
	    else{
	      return true; //能被400整除
	    }
	}

	/**	
	 * @param startDate1
	 * @param endDate1
	 * @param startDate2
	 * @param endDate2
	 * @return
	 * @throws ParseException
	 */
	public static boolean isConflict(String startDate1,String endDate1,
			Date startDate2, Date endDate2) throws ParseException{
		boolean flag = true;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if((endDate2.getTime()<sdf.parse(startDate1).getTime())
		    ||
		    (startDate2.getTime()>sdf.parse(endDate1).getTime())){
			flag = false;
		}
		return flag;
	}
	
	/**`替换制定字符串中的字符
	 * @param str
	 * @param ind
	 * @param rep
	 * @return
	 */
	public static String replace(String str,String ind,String rep){
	    int x = str.indexOf(ind);
	    while(x >= 0){
	    	str = str.substring(0,x)+rep+str.substring(x+1);
	    	x = str.indexOf(ind);
	    }
	    return str;
	}
	
	public static Integer getRowStart(int pagesize, int pageno) {
        return new Integer((pageno - 1) * pagesize + 1);
    }

    public static Integer getRowEnd(int pagesize, int pageno) {
        return new Integer(pageno * pagesize);
    }
	
    public static void addStringParameterToMap(Map map,String key,String value)
    {
    	if(value!=null && value.length()>0)
		{
			map.put(key, NormalFun.getGBStr(value));
		}
    }
    
    /**	将字符数组转换为字符串
     * @param array
     */
    public static String getString(String[] array) {
    	StringBuffer sb = new StringBuffer();
    	for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			if(i < array.length - 1) {
				sb.append(",");
			}
		}
    	return sb.toString();
    }
    
    /**	从url中获得指定参数值
     * @param queryString
     * @param parameterName
     * @return
     */
    public static String getQueryStringValue(String queryString, String parameterName) {
    	String value = null;
    	String tmp = null;
    	int start = 0;
    	int end = 0;
    	if(queryString == null || parameterName == null)
    		return null;
    	
    	start = queryString.indexOf(parameterName + "=");
    	if(start == -1) {
    		return null;
    	}
    	
    	end = queryString.indexOf("&", start);
    	if(end == -1) {
    		tmp = queryString.substring(start);
    	}else {
    		tmp = queryString.substring(start, end);
    	}
    	
    	start = tmp.indexOf("=");
    	value = tmp.substring(start+1);
    	
    	return value;	
    }
    
    /** 
     * 得到本月最后一天的日期 
     * @Methods Name getLastDayOfMonth 
     * @return Date 
     */  
    public static Date getLastDayOfMonth(Date date)   {     
        Calendar cDay = Calendar.getInstance();     
        cDay.setTime(date);  
        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));  
        return cDay.getTime();     
    }  
    
    /** 
     * 得到本季度最后一天的日期 
     * @Methods Name getLastDayOfQuarter 
     * @return Date 
     */  
    public static Date getLastDayOfQuarter(Date date)   {     
        Calendar cDay = Calendar.getInstance();     
        cDay.setTime(date);  
        int curMonth = cDay.get(Calendar.MONTH);  
        if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.MARCH){    
            cDay.set(Calendar.MONTH, Calendar.MARCH);  
        }  
        if (curMonth >= Calendar.APRIL && curMonth <= Calendar.JUNE){    
            cDay.set(Calendar.MONTH, Calendar.JUNE);  
        }  
        if (curMonth >= Calendar.JULY && curMonth <= Calendar.AUGUST) {    
            cDay.set(Calendar.MONTH, Calendar.AUGUST);  
        }  
        if (curMonth >= Calendar.OCTOBER && curMonth <= Calendar.DECEMBER) {    
            cDay.set(Calendar.MONTH, Calendar.DECEMBER);  
        }  
        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));  
        return cDay.getTime();     
    }  
    
    public static List getSaleBillList(List saleBillList){
    	List saleBillStatus = new ArrayList();    	
    	saleBillStatus.add(Constants.ORDER_RELATION_TYPE_ORFQ);
    	saleBillStatus.add(Constants.ORDER_RELATION_TYPE_REQUEST);
    	saleBillStatus.add(Constants.ORDER_RELATION_TYPE_POORDER);
    	saleBillStatus.add(Constants.ORDER_RELATION_TYPE_STOCKBILL);
    	saleBillStatus.add(Constants.ORDER_RELATION_TYPE_OUTSTOCK);
    	saleBillStatus.add(Constants.ORDER_RELATION_TYPE_STOCKBILLOUT);
    	saleBillStatus.add(Constants.ORDER_RELATION_TYPE_SALE);
    	saleBillStatus.add(Constants.ORDER_RELATION_TYPE_RECEIVEBILL);
    	saleBillStatus.add(Constants.ORDER_RELATION_TYPE_RETURNGOODS);
    	for (int i = 0; i < saleBillStatus.size(); i++) {
    		boolean addFlag = true;
			Integer relationType = (Integer) saleBillStatus.get(i);
			for (int j = 0; j < saleBillList.size(); j++) {
	    		SaleBillStatus saleBill = (SaleBillStatus) saleBillList.get(j);
				if(saleBill.getRelationType()!=null && saleBill.getRelationType().intValue()==relationType.intValue()){
					addFlag = false;
				}
			}
			if(addFlag){
				SaleBillStatus saleBillAdd = new SaleBillStatus();
				saleBillAdd.setRelationType(relationType);
				saleBillList.add(saleBillAdd);
			} 
		}
    	Collections.sort(saleBillList);
    	return saleBillList;
    }
    
    /**
     * 获得当前订单执行的最新状态在第几步
     * @param saleBillList
     * @return
     */
    public static int getSaleBillMaxStatus(List saleBillList){
    	int maxStep = 2;		//订单状态
    	for (Iterator iterator = saleBillList.iterator(); iterator.hasNext();) {
			SaleBillStatus billStatus = (SaleBillStatus) iterator.next();
			int relationType = billStatus.getRelationType().intValue();
			if(relationType == Constants.ORDER_RELATION_TYPE_ORFQ
					&& maxStep < 2)
				 maxStep = 2;
			if(relationType == Constants.ORDER_RELATION_TYPE_REQUEST 
					&& billStatus.getBillIdStr()!= null && maxStep < 3)
				maxStep = 3;
			if(relationType == Constants.ORDER_RELATION_TYPE_POORDER 
					&& billStatus.getBillIdStr()!= null && maxStep < 4)
				maxStep = 4;
			if(relationType == Constants.ORDER_RELATION_TYPE_STOCKBILL 
					&& billStatus.getBillIdStr()!= null && maxStep < 5)
				maxStep = 5;
			if(relationType == Constants.ORDER_RELATION_TYPE_OUTSTOCK 
					&& billStatus.getBillIdStr()!= null && maxStep < 6)
				maxStep = 6;
			if(relationType == Constants.ORDER_RELATION_TYPE_STOCKBILLOUT
					&& billStatus.getBillIdStr()!= null && maxStep < 7)
				maxStep = 7;
			if(relationType == Constants.ORDER_RELATION_TYPE_SALE
					&& billStatus.getBillIdStr()!= null && maxStep < 8)
				maxStep = 8;
			if(relationType == Constants.ORDER_RELATION_TYPE_RECEIVEBILL
					&& billStatus.getBillIdStr()!= null && maxStep < 9)
				maxStep = 9;
		}
    	return maxStep;
    }
    
    public static String getNextCode(String prefix,String curCode) {
    	String dateStr="yyyyMMdd";
    	try {
			dateStr = formatStringDate();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	if (curCode == null) {
    		return prefix+dateStr+"001";
    	} else {
    		int start = curCode.indexOf("2");
    		String curDateStr = curCode.substring(start,start+8);
    		if (dateStr.equals(curDateStr)) {
    			int curSerial = Integer.parseInt(curCode.substring(start+8));
    			int nextSerial = curSerial+1;
    			String tempNextSerialStr = String.valueOf(nextSerial);
    			String nextSerialStr = "";
    			if (tempNextSerialStr.length()==1) {
    				nextSerialStr = "00"+tempNextSerialStr;
    			} else if (tempNextSerialStr.length()==2) {
    				nextSerialStr = "0"+tempNextSerialStr;
    			} else {
    				nextSerialStr = tempNextSerialStr;
    			}
    			return prefix + dateStr + nextSerialStr;
    		}else {
    			return prefix+dateStr+"001";
    		}
    	}
    }
    
    public static void main(String[] args) {
        String s = getNextCode("QJ",null);
    }
    
	/**	按指定格式格式化当前日期
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String formatStringDate() throws ParseException{
	    SimpleDateFormat spdf = new SimpleDateFormat("yyyyMMdd");
	    return spdf.format(new Date());
	}
	
	/** 
	 * 处理字符串长度为空
	 * @param input
	 * @return
	 */
	public static String trimNull(String input) {
		if(input != null && input.trim().length()>0)
			return input.trim();
		return null;
	}
	
	public static String getNextQuotationCode(String curCode) throws Exception {
    	String dateStr = formatStringDate(); 
    	if (curCode==null || curCode.trim().equals("")) {
    		return dateStr + "01";
    	} 
    	else {
    		String curNum = String.valueOf(Integer.parseInt(curCode.substring(curCode.length()-2, curCode.length())) + 1); 
    		if (curNum.length() == 1) {
    			curNum = "0" + curNum;
			} 
    		return dateStr + curNum;
    	}
    }
	
}
