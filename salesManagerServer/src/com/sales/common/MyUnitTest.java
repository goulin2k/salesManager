package com.sales.common;

import java.util.Date;

import org.springframework.test.AbstractTransactionalSpringContextTests;

import com.sales.model.SLog;
import com.sales.service.SLogService;

public class MyUnitTest extends AbstractTransactionalSpringContextTests {

	@Override
	protected String[] getConfigLocations() {
		String[] configLocations = { "file:WebRoot/WEB-INF/applicationContext-service.xml","file:WebRoot/WEB-INF/applicationContext-dao.xml","file:WebRoot/WEB-INF/applicationContext-common.xml"};
	    return configLocations;
	}
	
	public void testAddUser(){ 
//		User user = new User(); 
//		user.setName("lq");
//		user.setDesc("123456");
//		UserService userService = (UserService) applicationContext.getBean("userService");
//		Integer id = userService.addUser(user);
//		User user1 = userService.getUser(id); 
//		assertEquals(user.getName(), user1.getName()); 
		/**请假单新增**/
//		SLeave leave = new SLeave();
//		leave.setUserId(1);
//		leave.setStartTime(new Date());
//		leave.setEndTime(new Date());
//		leave.setComment("reason");
//		leave.setTypeId(Constants.LEAVE_ANNUAL_TYPE);
//		leave.setLeaveTime(new Date());	
//		SLeaveService leaveService = (SLeaveService) applicationContext.getBean("leaveService");
//		leaveService.addLeaveReceipt(leave);	
		/**请假单修改**/
//		SLeave leave = new SLeave();
//		leave.setLeaveId(1);
//		leave.setStartTime(new Date(2013,5,16));
//		leave.setEndTime(new Date(2013,5,17));
//		leave.setComment("reason1");
//		leave.setBackTime(new Date(2013,5,18));
//		leave.setLeaveTime(new Date());	
//		SLeaveService leaveService = (SLeaveService) applicationContext.getBean("leaveService");
//		leaveService.updateLeaveReceipt(leave);
		/**加班单新增**/
//		SOvertime ot = new SOvertime();
//		ot.setUserId(1);
//		ot.setStartTime(new Date());
//		ot.setHours(Float.valueOf("4.5"));
//		ot.setComment("comment");
//		ot.setOvertimeTime(new Date());
//		SOvertimeService service = (SOvertimeService) applicationContext.getBean("overtimeService");
//		service.addOvertimeReceipt(ot);
		/**加班单修改**/
//		SOvertime ot = new SOvertime();
//		ot.setOvertimeId(1);
//		ot.setHours(Float.valueOf("4"));
//		ot.setComment("comment11");
//		SOvertimeService service = (SOvertimeService) applicationContext.getBean("overtimeService");
//		service.updateOvertimeReceipt(ot);
		/**出差申请新增**/
//		STripApplication ta = new STripApplication();
//		ta.setUserId(1);
//		ta.setStartTime(new Date());
//		ta.setEndTime(new Date());
//		ta.setTripLocation("chengdu");
//		ta.setTripComment("tripcomment");
//		ta.setTripTime(new Date());
//		STripApplicationService service = (STripApplicationService) applicationContext.getBean("tripApplicationService");
//		service.addTripApplication(ta);
		/**出差申请修改**/
//		STripApplication ta = new STripApplication();
//		ta.setTripId(1);
//		ta.setTripLocation("chengdu11");
//		ta.setTripComment("tripcomment11");
//		STripApplicationService service = (STripApplicationService) applicationContext.getBean("tripApplicationService");
//		service.updateTripApplication(ta);
		/**出差报销新增**/
//		STripExpense te = new STripExpense();
//		te.setTripId(1);
//		te.setExpenseSum(Float.valueOf("1000.5"));
//		te.setUppercase("thousand");
//		te.setExpenseTime(new Date());
//		te.setTripExpenseCode("SN0001");
//		STripExpenseSerivce service = (STripExpenseSerivce) applicationContext.getBean("tripExpenseService");
//		service.addTripExpense(te);
		/**出差报销修改**/
//		STripExpense te = new STripExpense();
//		te.setTripExpenseId(1);
//		te.setExpenseSum(Float.valueOf("1000"));
//		te.setUppercase("thousand111");
//		te.setExpenseComment("expense comment");
//		STripExpenseSerivce service = (STripExpenseSerivce) applicationContext.getBean("tripExpenseService");
//		service.updateTripExpense(te);
		/**费用申请新增**/
//		SExpenseApplication ea = new SExpenseApplication();
//		ea.setTypeId(1);
//		ea.setExpenseApplicationCode("SN0001");
//		ea.setExpenseUserId(1);
//		ea.setLoanSum(Float.valueOf("2000"));
//		ea.setUppercase("thous");
//		ea.setExpenseTime(new Date());
//		SExpenseApplicationService service = (SExpenseApplicationService) applicationContext.getBean("expenseApplicationService");
//		service.addExpenseApplication(ea);
		/**费用申请修改**/
//		SExpenseApplication ea = new SExpenseApplication();
//		ea.setExpenseApplicationId(1);
//		ea.setExpenseApplicationCode("SN0002");
//		ea.setLoanSum(Float.valueOf("3000"));
//      ea.setComment("comment");
//		SExpenseApplicationService service = (SExpenseApplicationService) applicationContext.getBean("expenseApplicationService");
//		service.updateExpenseApplication(ea);
		/**费用报销新增**/
//		SExpenseReimbursement er = new SExpenseReimbursement();
//		er.setReimbursementCode("SN0001");
//		er.setExpenseApplicationId(1);
//		er.setReimbursementSum(Float.valueOf("4000"));
//		er.setUppercase("four thous");
//		er.setReimbursementTime(new Date());
//		SExpenseReimbursementService service = (SExpenseReimbursementService) applicationContext.getBean("expenseReimbursementService");
//		service.addExpenseReimbursement(er);
		/**费用报销修改**/
//		SExpenseReimbursement er = new SExpenseReimbursement();
//		er.setReimbursementId(1);
//		er.setReimbursementSum(Float.valueOf("4000.9"));
//		er.setReimbursementComment("reimbursementComment");
//		SExpenseReimbursementService service = (SExpenseReimbursementService) applicationContext.getBean("expenseReimbursementService");
//		service.updateExpenseReimbursement(er);
		/**车辆维修申请新增**/
//		SCarRepairApplication cra = new SCarRepairApplication();
//		cra.setCarApplicationCode("SN0001");
//		cra.setIsInsurance(Constants.INSURANCE_NO);
//		cra.setUserId(1);
//		cra.setApplicationTime(new Date());
//		cra.setRepairSum(Float.valueOf("5000"));
//		cra.setUppercase("five thous");
//		cra.setPlateNumber("car number");
//		SCarRepairApplicationService service = (SCarRepairApplicationService) applicationContext.getBean("carRepairApplicationService");
//		service.addCarRepairApplication(cra);
		/**车辆维修申请修改**/
//		SCarRepairApplication cra = new SCarRepairApplication();
//		cra.setCarApplicationId(1);
//		cra.setRepairSum(Float.valueOf("6000"));
//		cra.setUppercase("six thous");
//		cra.setRepairComment("repairComment");
//		SCarRepairApplicationService service = (SCarRepairApplicationService) applicationContext.getBean("carRepairApplicationService");
//		service.updateCarRepairApplication(cra);
		/**车辆维修报销新增**/
//		SCarRepairReimbursement crr = new SCarRepairReimbursement();
//		crr.setCarApplicationId(1);
//		crr.setReimbursementSum(Float.valueOf("5000"));
//		crr.setUppercase("five thous");
//		crr.setRepairFactoryPhone("02812345678");
//		SCarRepairReimbursementService service = (SCarRepairReimbursementService) applicationContext.getBean("carRepairReimbursementService");
//		service.addCarRepairReimbursement(crr);
		/**车辆维修报销修改**/
//		SCarRepairReimbursement crr = new SCarRepairReimbursement();
//		crr.setCarReimbursementId(1);
//		crr.setCarReimbursementCode("SN0001");
//		crr.setReimbursementComment("reimbursementComment");
//		SCarRepairReimbursementService service = (SCarRepairReimbursementService) applicationContext.getBean("carRepairReimbursementService");
//		service.updateCarRepairReimbursement(crr);
		/**日志新增**/
		SLog log = new SLog();
		log.setTypeId(2);
		log.setOperateUserId(1);
		log.setTitle("title");
		log.setLogContent("insert log");
		log.setLogTime(new Date());
		SLogService service = (SLogService) applicationContext.getBean("logService");
		service.addLog(log);
		
		
		
		setComplete(); //不需要提交数据库可以注释本句代码

		
	}

}
