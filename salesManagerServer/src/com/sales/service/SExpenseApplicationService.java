package com.sales.service;

import java.util.List;

import com.sales.model.SExpenseApplication;
import com.sales.model.SUser;

public interface SExpenseApplicationService {

	public void addExpenseApplication(SExpenseApplication ea, SUser loginUser);
	
	public void updateExpenseApplication(SExpenseApplication ea);
	
	public List<SExpenseApplication> getExpenseApplicationListPage(SExpenseApplication ea);
	
	public Integer getExpenseApplicationListPageCount(SExpenseApplication ea);
	
	public SExpenseApplication getExpenseApplication(Integer eaId);
	
	public List<SExpenseApplication> getExpenseApplicationListForReimbursement(Integer userId);
	
	public void updateExpenseApplicationById(SExpenseApplication ea);
	
	public void deleteEa(Integer eaId);
}
