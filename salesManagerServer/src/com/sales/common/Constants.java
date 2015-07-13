package com.sales.common;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	
	/** 获取登录 用户信息的sessionID*/
	public final static  String USER_INFO = "USER_INFO";
	public static final String SESSION_ERROR_MSG = "errorMsg";
	
	/** 获取登录 用户权限的sessionID*/
	public final static  String USER_PERMISSION_MAP = "USER_PERMISSION_MAP";
	public final static String USER_FILTER_MARK="-1";
	
	/** 获取用户scriptsession的ID */
	public final static  String DWR_USERID = "DWR_USERID";
	
	/** Json格式的数据返回：保存，更新等操作 */
	public final static  String FORWARD_STR_JSON_RESULT = "jsonResult";
	
//	/** 请假单类型 */
//	public final static  int LEAVE_ANNUAL_TYPE = 1;  //年假
//	public final static  int LEAVE_CHANGE_TYPE = 2;  //换休
//	public final static  int LEAVE_OTHER_TYPE = 3;   //事假
//	
//	/** 车辆维修是否报保险 */
//	public final static  int INSURANCE_NO = 0;  //不报保险
//	public final static  int INSURANCE_YES = 1;  //报保险
	
	public final static int DEFAULT_PAGE_SIZE = 10;
	public final static int MAX_PAGE_SIZE = 1000;
	
	public final static int NOT_ASSASE_MARK = 36; //财务无评估标识
	public final static int NOT_CUSTOMER_LEVEL_MARK = 74; //客户等级无评估标识
	public final static int NOT_CUSTOMER_CREDIT_MARK = 75; //信用等级无评估标识
	
	/**	系统数据字典枚举类型		*/
	public final static Integer ENUMERATION_PROJECT_TYPE = 1;			//销售计划类型
	public final static Integer ENUMERATION_ACTIVITY_TYPE = 2;			//销售活动类型
	public final static Integer ENUMERATION_CUSTOMER_FIN_EVALUATION_TYPE = 3;  //客户财务评估
	public final static Integer ENUMERATION_CUSTOMER_CDT_EVALUATION_TYPE = 4;  //客户信用评估
	public final static Integer ENUMERATION_CUSTOMER_RP_EVALUATION_TYPE = 5;  //客户关系评估
	public final static Integer ENUMERATION_PROJECT_CHANCE_EVALUATION_TYPE = 6;  //项目机会评估
	public final static Integer ENUMERATION_CUSTOMER_LEVEL_TYPE = 7;  //客户等级？
	/**	END 系统数据字典枚举类型		*/
	
	public final static Integer QUOTATION_NOT_REPLY = 0;
	public final static Integer QUOTATION_REPLY = 1;
	
	/** 岗位类型 */
//	public final static  int ORG_POSITION_TYPE = 1;  //行政岗位
//	public final static  int BUSSINESS_POSITION_TYPE = 2;  //业务岗位
	
	/** 绩效周期类型 */
	public final static  int YEAR_CYCLE_TYPE = 3;  //年度
	public final static  int SEASON_CYCLE_TYPE = 2;  //季度
	public final static  int MONTH_CYCLE_TYPE = 1;  //月度
	
	public final static Integer DEPARTMENT_ROOT_ID = 0;        //部门根节点id
	public final static Map K3STATUS = new HashMap(){{ put(0, "未审核"); put(1, "已审核"); put(2, "部分执行"); put(3, "全部执行"); }};
	public final static Map K3Cancellation = new HashMap(){{ put(0, "未作废"); put(1, "作废"); }};
	public final static Map K3Closed = new HashMap(){{ put(0, "未关闭"); put(1, "关闭"); }};
	
	//采购订单申请状态
	public final static Map K3_POORDER_STATUS = new HashMap(){
		{ put(0, "未审核"); put(1, "未执行"); put(2, "执行中");put(3, "执行中");put(4, "部分入库");put(5, "已关闭");}
	};
	
	/** 消息状态 */
	public final static  int INFO_STATUS_NEW = 1;  //未读
	public final static  int INFO_STATUS_READE = 0;  //已读
	public final static  int INFO_TYPE_SYSTEM = 1;  //系统消息
	public final static  int INFO_TYPE_USER = 2;  //用户消息
	
	public final static Integer ORDER_RELATION_TYPE_ORFQ = 1;
	public final static Integer ORDER_RELATION_TYPE_REQUEST = 2;
	public final static Integer ORDER_RELATION_TYPE_STOCKBILL = 3;
	public final static Integer ORDER_RELATION_TYPE_OUTSTOCK = 4;
	public final static Integer ORDER_RELATION_TYPE_STOCKBILLOUT = 5;
	public final static Integer ORDER_RELATION_TYPE_SALE = 6;
	public final static Integer ORDER_RELATION_TYPE_RECEIVEBILL = 7;
	public final static Integer ORDER_RELATION_TYPE_RETURNGOODS = 8;
	public final static Integer ORDER_RELATION_TYPE_POORDER = 11;			//销售订单-采购订单关联
	/** 新闻状态 */
	public final static Integer NEWS_STATUS_ISSUE = 1;  //发布
	public final static Integer NEWS_STATUS_CANCEL = 2;  //不发布
	/** 新闻类型 */
	public final static Integer NEWS_TYPE_NEWS = 1;  //新闻
	public final static Integer NEWS_TYPE_DISPATCH = 2;  //发文
	public final static Map NEWSSTATUS = new HashMap(){{ put(1, "发布"); put(2, "不发布"); }};
	public final static Map NEWSLISTSTATUS = new HashMap(){{ put(1, "已发布"); put(2, "未发布"); }};
	
	/** 日志类型 */
	public final static Integer LOG_TYPE_DEAL = 1;  //业务操作
	public final static Integer LOG_TYPE_SYNCHRONOUS = 2;  //用户登录
	public final static Integer ROLE_ADMIN = 1;
	public final static Integer ROLE_PURCHARSE = 7;
	public final static Integer ROLE_FINANCIAL = 8;
	public final static Integer ROLE_LOGISTICS = 13;
	public final static Integer ROLE_ORFQ = 3;
	
	public final static Integer DEPARTMENT_ROOT = 0;
	public final static Integer PROJECT_CREATE = 1;  //创建人
	public final static Integer PROJECT_RESPONSE = 2;  //负责人
	public final static Integer PROJECT_ATTENTION = 3;  //关注人
	
	/** 工作流相关 */
	public final static  String PROCESS_END = "结束"; 
	public final static  String PROCESS_TEMP = "暂存"; 
	
	public final static int LEAVE_TYPE = 1;
	public final static int OVERTIME_TYPE = 2;
	public final static int TRIP_APP_TYPE = 3;
	public final static int EXPENSE_APP_TYPE = 4;
	public final static int EXPENSE_REI_TYPE = 5;
	public final static int CAR_APP_TYPE = 6;
	public final static int CAR_REI_TYPE = 7;
	
	
	public final static String PARAM_QUOREPLY_SENDTO = "quotation_sendto_position";
	

	/** 销售订单审核状态 */
	public final static Integer ORFQ_VERIFY_NONE = 0;  //未审核
	public final static Integer ORFQ_VERIFY_YES = 1;  //通过
	public final static Integer ORFQ_VERIFY_NO = 2;  //不通过
	public final static Map ORFQ_VERIFY_STATUS = new HashMap(){{ put(1, "审核通过"); put(2, "审核不通过"); }};
	public final static Map ORFQ_VERIFY_NAME = new HashMap(){{ put(1, "一级审核"); put(2, "二级审核"); }};
	public final static Integer ORFQ_VERIFY_ONE = 1;  //一级审核
	public final static Integer ORFQ_VERIFY_TWO = 2;  //二级审核
	public final static Map ORFQ_VERIFY = new HashMap(){{ put(0, "未审核"); put(1, "审核通过"); put(2, "审核不通过"); }};	
	
	/** 流程编码前缀*/
	public final static String LEAVE_PREFIX = "QJ"; 
	public final static String OVERTIME_PREFIX = "OT"; 
	public final static String TRIP_APP_PREFIX = "TA";
	public final static String EXPENSE_APP_PREFIX = "EA";
	public final static String EXPENSE_REI_PREFIX = "ER";
	public final static String CAR_APP_PREFIX = "CA";
	public final static String CAR_REI_PREFIX = "CR";	

	public final static String ORFQ_BILL_NO_F = "AQ";
	public final static String ORFQ_CHECK_DATE = "orfq.checktime";
	public final static String ORFQ_DELETE_DATE = "orfq.deletetime";
	
}
