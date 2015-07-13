package com.sales.dao;

import java.util.List;

import com.sales.model.SDepartment;

public interface SDepartmentDAO {
     
    public void insert(SDepartment department);
 
    public void updateSDepartment(SDepartment department); 
 
    public SDepartment getSDepartmentById(Integer departmentId);
 
    public void deleteSDepartmentById(Integer departmentId);
    
    public List<SDepartment> getUnderDepartmentListById(Integer departmentId);
    
    public List getSDepartmentList(Integer departmentId);
    
    public List getSDepartByParentId(Integer parentId);
    
    public List<SDepartment> getDepAndChildById(Integer departmentId);
}