/**
 * 
 */
package com.sales.model;

/**
 * @author apple
 *
 */
public class ProcessHistory extends BaseObject {
	private static final String CR_ID_STR = "?crId=";
	private static final String CA_ID_STR = "?caId=";
	private static final String ER_ID_STR = "?erId=";
	private static final String EA_ID_STR = "?eaId=";
	private static final String OVERTIME_ID_STR = "?otId=";
	private static final String TRIP_ID_STR = "?tripId=";
	private static final String LEAVE_ID_STR = "?leaveId=";
	private String processType;
	private String deptName;
	private String userName;
	private String activityName;
	private String appTime;
	private String detailLink;
	private int typeId;
	private Integer detailId;
	
	/**
	 * @return the typeId
	 */
	public int getTypeId() {
		return typeId;
	}
	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	/**
	 * @return the processType
	 */
	public String getProcessType() {
		return processType;
	}
	/**
	 * @param processType the processType to set
	 */
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the activityName
	 */
	public String getActivityName() {
		return activityName;
	}
	/**
	 * @param activityName the activityName to set
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	/**
	 * @return the appTime
	 */
	public String getAppTime() {
		return appTime;
	}
	/**
	 * @param appTime the appTime to set
	 */
	public void setAppTime(String appTime) {
		this.appTime = appTime;
	}
	/**
	 * @return the detailLink
	 */
	public String getDetailLink() {
		return detailLink;
	}
	/**
	 * @param detailLink the detailLink to set
	 */
	public void setDetailLink(String detailLink) {
		this.detailLink = detailLink;
		getDetailId();
		
	}
	/**
	 * @return the detailId
	 */
	public Integer getDetailId() {
		int i = -1;
		if (detailLink == null)
			return null;
		switch(typeId) {
		case 1:
			i = detailLink.indexOf(LEAVE_ID_STR);
			if(i>0) {
				detailId = Integer.parseInt(detailLink.substring(i+LEAVE_ID_STR.length()));
			}
			break;
		case 2:
			i = detailLink.indexOf(OVERTIME_ID_STR);
			if(i>0) {
				detailId = Integer.parseInt(detailLink.substring(i+OVERTIME_ID_STR.length()));
			}
			break;
		case 3:
			i = detailLink.indexOf(TRIP_ID_STR);
			if(i>0) {
				detailId = Integer.parseInt(detailLink.substring(i+TRIP_ID_STR.length()));
			}
			break;
		case 4:
			i = detailLink.indexOf(EA_ID_STR);
			if(i>0) {
				detailId = Integer.parseInt(detailLink.substring(i+EA_ID_STR.length()));
			}
			break;
		case 5:
			i = detailLink.indexOf(ER_ID_STR);
			if(i>0) {
				detailId = Integer.parseInt(detailLink.substring(i+ER_ID_STR.length()));
			}
			break;
		case 6:
			i = detailLink.indexOf(CA_ID_STR);
			if(i>0) {
				detailId = Integer.parseInt(detailLink.substring(i+CA_ID_STR.length()));
			}
			break;
		case 7:
			i = detailLink.indexOf(CR_ID_STR);
			if(i>0) {
				detailId = Integer.parseInt(detailLink.substring(i+CR_ID_STR.length()));
			}
			break;
		default:
			break;
		}
		return detailId;
	}
	
	
}
