/**
 * 
 */
package com.sales.model;

import java.text.ParseException;
import java.util.Date;

import com.sales.common.NormalFun;

/**
 * @author Administrator
 *
 */
public class CalendarTask extends BaseObject {

	private int id;
	private String title;
	private String start;
	private String end;
	private boolean allDay;
	private String url;
	private boolean isCompleted;
	private String className;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}
	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = end;
	}
	/**
	 * @return the allDay
	 */
	public boolean isAllDay() {
		return allDay;
	}
	/**
	 * @param allDay the allDay to set
	 */
	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
	public String getClassName() {
		try {
			if(isCompleted)
				return "completedEvent";
			else {
				Date dt = NormalFun.formatStringDate(end);
				if(dt.before(new Date())) {
					return "delayEvent";
				}
				return "doingEvent";
			}
		} catch(ParseException e) {
			return "";
		}
	}
	
}
