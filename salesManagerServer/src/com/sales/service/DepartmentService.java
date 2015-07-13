package com.sales.service;

import java.util.List;

import com.sales.model.SDepartment;

public interface DepartmentService {
	
	public void addDepartment(SDepartment department);
	
	public void updateSDepartment(SDepartment department); 
	 
    public SDepartment getSDepartmentById(Integer departmentId); 
    
    public List<SDepartment> getUnderDepartmentListById(Integer departmentId);
    
    public List getSDepartmentList(Integer departmentId);
    
    public String getDepartmentTree(Integer departmentId);
    
    public List getSDepartByParentId(Integer parentId);
    
    public List<SDepartment> getAllDepartmentListById(Integer departmentId);

}
