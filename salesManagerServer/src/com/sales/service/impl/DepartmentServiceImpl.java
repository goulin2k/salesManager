package com.sales.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import com.sales.common.Constants;
import com.sales.dao.SDepartmentDAO;
import com.sales.model.SDepartment;
import com.sales.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {
	
	private SDepartmentDAO departmentDao;
	
	public String getDepartmentTree(Integer departmentId){ 
		List depList = departmentDao.getSDepartmentList(departmentId);
		SDepartment sd = (SDepartment) depList.get(0);
		JSONObject jo = new JSONObject();
		jo.put("id", sd.getDepartmentId());
		jo.put("text", sd.getName());
		if(sd.getChildDepartmentList()!=null && sd.getChildDepartmentList().size()>0){
			jo.put("state", "open");
			jo.put("children", getUnderTree(sd.getChildDepartmentList()));
		}
		return "[" + jo.toString() + "]";
	}
	
	private String getUnderTree(List underList){
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<underList.size(); i++){
			SDepartment sd = (SDepartment) underList.get(i);
			JSONObject jo = new JSONObject();
			jo.put("id", sd.getDepartmentId());
			jo.put("text", sd.getName());
			if(sd.getChildDepartmentList()!=null && sd.getChildDepartmentList().size()>0){
				jo.put("state", "open");
				jo.put("children", getUnderTree(sd.getChildDepartmentList()));
			}
			sb.append(jo.toString()).append(",");
		}
		if(sb.length() > 0){
			sb.delete(sb.length()-1, sb.length());
		} 
		return "[" + sb.toString() + "]";
	}
	
	public void addDepartment(SDepartment department){
		if(department.getHasChild() == null){
			department.setHasChild(0);
		}
		if(department.getParentId() == null){
			department.setParentId(0);
		}
		departmentDao.insert(department);
	}
	
	public void updateSDepartment(SDepartment department) {
		departmentDao.updateSDepartment(department);
	}
	
	public SDepartment getSDepartmentById(Integer departmentId) { 
        SDepartment department = departmentDao.getSDepartmentById(departmentId);
        return department;
    }
	
	public List<SDepartment> getUnderDepartmentListById(Integer departmentId) {
		// TODO Auto-generated method stub
		if(departmentId == null){
			departmentId = Constants.DEPARTMENT_ROOT;
		}
		List depList = departmentDao.getUnderDepartmentListById(departmentId);
		return depList;
	}
	
	public List getSDepartmentList(Integer departmentId){
		List depList = departmentDao.getSDepartmentList(departmentId);
		return depList;
	} 
	
	public List getSDepartByParentId(Integer parentId){
		List departList = departmentDao.getSDepartByParentId(parentId);
		return departList;		
	}

	public SDepartmentDAO getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(SDepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	public List<SDepartment> getAllDepartmentListById(Integer departmentId) {
		// TODO Auto-generated method stub
		if(departmentId == null){
			departmentId = Constants.DEPARTMENT_ROOT;
		}
		List depList = departmentDao.getDepAndChildById(departmentId);
		return depList;
	}

}
