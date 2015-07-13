package com.sales.action; 

import java.util.List;

import net.sf.json.JSONArray;

import org.dom4j.io.XMLWriter;

import com.sales.common.Constants;
import com.sales.model.SDepartment; 
import com.sales.service.DepartmentService;

public class DepartmentAction extends BaseAction {
	
	private DepartmentService departmentService;
	
	private List departmentList;
	
	private SDepartment department;

	public SDepartment getDepartment() {
		return department;
	}

	public void setDepartment(SDepartment department) {
		this.department = department;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public List getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List departmentList) {
		this.departmentList = departmentList;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	} 
 
	public String editNew() { 
		this.department = this.departmentService.getSDepartmentById(this.department.getParentId()); 
		return "add";
	}
	
	public String add() {
		this.departmentService.addDepartment(this.department);
		departmentList = this.departmentService.getUnderDepartmentListById(this.department.getParentId());  
		this.department = this.departmentService.getSDepartmentById(this.department.getParentId());
    	return "list";
    }
 
	public String show() {
		this.department = this.departmentService.getSDepartmentById(this.department.getDepartmentId());
		return "show";
	}
	
	public String update() {
		this.departmentService.updateSDepartment(this.department);
		departmentList = this.departmentService.getUnderDepartmentListById(this.department.getParentId());  
		this.department = this.departmentService.getSDepartmentById(this.department.getParentId());
    	return "list";
    }
	
	public String treePage(){
		return "treePage";
	}
	
	public String index() {
		if(this.department == null){
			this.department = new SDepartment();
			this.department.setDepartmentId(1);
		}
		departmentList = this.departmentService.getUnderDepartmentListById(this.department.getDepartmentId()); 
		this.department = this.departmentService.getSDepartmentById(this.department.getDepartmentId()); 
		return "treePage";
	}
	
	public String tree() {
		String departmentId = request.getParameter("departmentId");
		if(departmentId==null || departmentId.equals("")){
			departmentId = "0";
		}
		String treeData = departmentService.getDepartmentTree(Integer.parseInt(departmentId)); 
		try{
			XMLWriter out = new XMLWriter(response.getOutputStream());
			out.write(treeData);
			out.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String list() {
		if(this.department == null){
			this.department = new SDepartment();
			this.department.setDepartmentId(1);
		}
		departmentList = this.departmentService.getUnderDepartmentListById(this.department.getDepartmentId()); 
		this.department = this.departmentService.getSDepartmentById(this.department.getDepartmentId()); 
		return "list";
	}
	
	public String treedemo() {
		return "treedemo";
	}
	
	public String jsonDepart() {   
		try{  
			List departList = this.departmentService.getSDepartByParentId(Constants.DEPARTMENT_ROOT_ID); 
			XMLWriter out = new XMLWriter(response.getOutputStream());
			String json = JSONArray.fromObject(departList).toString().replaceAll("\\,\"children\":\\[\\]", ""); 
			out.write(json);
			out.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
