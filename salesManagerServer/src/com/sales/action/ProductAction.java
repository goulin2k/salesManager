package com.sales.action; 

import java.util.List;

import my.com.ibatis.core.dao.support.Page;
import net.sf.json.JSONObject;

import org.dom4j.io.XMLWriter;
 
import com.sales.common.Constants;
import com.sales.common.NormalFun;
import com.sales.model.TProduct; 
import com.sales.service.ProductService;

public class ProductAction extends BaseAction {
	
	private ProductService productService;	
	private List<TProduct> jsonAutoList;	
	private JSONObject jsonStr;
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE; // 页面大小
	private Page page;
	private List productList;
	
	public List getProductList() {
		return productList;
	}

	public void setProductList(List productList) {
		this.productList = productList;
	}

	public Long getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Long pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public JSONObject getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(JSONObject jsonStr) {
		this.jsonStr = jsonStr;
	}

	private TProduct product; 
	
	public TProduct getProduct() {
		return product;
	}

	public void setProduct(TProduct product) {
		this.product = product;
	}

	public List<TProduct> getJsonAutoList() {
		return jsonAutoList;
	}

	public void setJsonAutoList(List<TProduct> jsonAutoList) {
		this.jsonAutoList = jsonAutoList;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public String editNew() {
		String treeData="[{\"id\":1,\"text\":\"Folder1\",\"iconCls\":\"icon-ok\",\"children\":" +
				"[{\"id\":2,\"text\":\"File1\",\"checked\":true}," +
				"{\"id\":3,\"text\":\"Folder2\",\"state\":\"open\",\"children\":" +
					"[{\"id\":4,\"text\":\"File3\",\"checked\":true,\"iconCls\":\"icon-reload\"}," +
					"{\"id\": 8,\"text\":\"Async Folder\",\"state\":\"closed\"}]}]}," +
					"{\"text\":\"Languages\",\"state\":\"closed\",\"children\":[{\"text\":\"Java\"},{\"text\":\"C#\"}]}]"; 
		try{
			XMLWriter out = new XMLWriter(response.getOutputStream());
			out.write(treeData);
			out.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
 
	public String show() {
		return "show";
	}
	
	public String treePage(){
		return "treePage";
	}
	
	public String treeDate(){
		try{
			String productId = request.getParameter("productId");
			if(productId==null || productId.equals("")){
				productId = "0";
			}
			String s = productService.getProductTreeGrid(productId);
			XMLWriter out = new XMLWriter(response.getOutputStream());
			out.write(s);
			out.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String index() {
		return "treePage";
	}
	
	public String autoComplete() {
		String searchtxt = this.request.getParameter("q");
		jsonAutoList = this.productService.getProductListByCondition(searchtxt, null);
		//this.jsonArray = JSONArray.fromObject(jsonUserList);
		return Constants.FORWARD_STR_JSON_RESULT;
	}
	
	public String jsonProduct() { 
		String fNumber = request.getParameter("fNumber");
		TProduct product = this.productService.getProductByFNumber(fNumber);
		this.jsonStr = JSONObject.fromObject(product);
		return Constants.FORWARD_STR_JSON_RESULT;
	}
	
	public String list(){ 
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		if (this.product == null) {
			this.product = new TProduct();
		}
		if(this.product.getFNumber()==null || this.product.getFNumber().trim().equals("")){
			this.product.setFNumber(null);
		}
		if(this.product.getFName()==null || this.product.getFName().trim().equals("")){
			this.product.setFName(null);
		}
		else{
			this.product.setFName(this.product.getFName());
		}
		Integer totalCount = this.productService.getProductCount(this.product.getFNumber(), this.product.getFName());
		if(totalCount > 0){
			this.productList = this.productService.getProductList(this.product.getFNumber(), this.product.getFName(), pageNumber.intValue(), pageSize); 
		}
		this.page = new Page();
		this.page.setData(this.productList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		return "list";
	}

}
