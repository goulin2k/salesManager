package com.sales.common.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class AutoCompleteTag extends TagSupport {
	
	private String url;
	
	private String textName;
	
	private String textId;
	
	private String displayName;
	
	public int doStartTag() throws JspException {

		JspWriter writer = pageContext.getOut();
		try {
			writer.print(this.getAutoCompleteContent());
		} catch (Exception e) {
			throw new JspException(e);
		}

		return (EVAL_BODY_INCLUDE);
	}
	
	private String getAutoCompleteContent() {
		String content = "";
		content += "<script type=\"text/javascript\">";
		content += "$(function(){$(\"#"+this.textId+"\").autocomplete(\""+this.getBasePath()+url+"\",{";
		content += "minChars: 1,max: 15,scroll: true,autoFill: false,selectFirst :true,dataType : \"json\",";
		content += "parse: function(data){var rows = [];";
		content += "for(var i=0;i<data.jsonAutoList.length;i++){";
		content += "rows[rows.length] = { data:data.jsonAutoList[i],result:data.jsonAutoList[i]."+this.displayName+" };";
		content += "}return rows;},";
		content += "formatItem: function(item){return item."+this.displayName+";}";
		content += " }); });</script>";
		content += "<input name=\""+this.textName+"\"  type=\"text\" id=\""+this.textId+"\"/>";
		return content;
	}
	
	private String getBasePath() {
		return pageContext.getRequest().getScheme() + "://" + pageContext.getRequest().getServerName() + ":" + pageContext.getRequest().getServerPort() + ((HttpServletRequest) pageContext.getRequest()).getContextPath() + "/";
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTextName() {
		return textName;
	}

	public void setTextName(String textName) {
		this.textName = textName;
	}

	public String getTextId() {
		return textId;
	}

	public void setTextId(String textId) {
		this.textId = textId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
