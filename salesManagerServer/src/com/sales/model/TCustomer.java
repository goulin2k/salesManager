package com.sales.model;

import java.math.BigDecimal;
import java.util.Date;

import com.sales.common.NormalFun;

public class TCustomer {
    public Integer getLinkmanWriteStatus() {
		return linkmanWriteStatus;
	}

	public void setLinkmanWriteStatus(Integer linkmanWriteStatus) {

//		if(!(linkmanWriteStatus !=null && linkmanWriteStatus.intValue()>0))

		this.linkmanWriteStatus = linkmanWriteStatus;
	}

	/**
     
     * This field corresponds to the database column t_customer.FLevel
     *
     
     */
    private Integer flevel;

    /**
     
     * This field corresponds to the database column t_customer.customer_id
     *
     
     */
    private Integer customerId;

    /**
     
     * This field corresponds to the database column t_customer.FName
     *
     
     */
    private String fname;

    /**
     
     * This field corresponds to the database column t_customer.FNumber
     *
     
     */
    private String fnumber;

    /**
     
     * This field corresponds to the database column t_customer.FParentID
     *
     
     */
    private Integer fparentid;

    /**
     
     * This field corresponds to the database column t_customer.FDeleted
     *
     
     */
    private Boolean fdeleted;

    /**
     
     * This field corresponds to the database column t_customer.FDetail
     *
     
     */
    private Boolean fdetail;

    /**
     
     * This field corresponds to the database column t_customer.FFullName
     *
     
     */
    private String ffullname;

    /**
     
     * This field corresponds to the database column t_customer.FFullNumber
     *
     
     */
    private String ffullnumber;

    /**
     
     * This field corresponds to the database column t_customer.FShortNumber
     *
     
     */
    private String fshortnumber;

    /**
     
     * This field corresponds to the database column t_customer.FUnUsed
     *
     
     */
    private Boolean funused;

    /**
     
     * This field corresponds to the database column t_customer.FShortName
     *
     
     */
    private String fshortname;

    /**
     
     * This field corresponds to the database column t_customer.Faddress
     *
     
     */
    private String faddress;

    /**
     
     * This field corresponds to the database column t_customer.regionName
     *
     
     */
    private String regionname;

    /**
     
     * This field corresponds to the database column t_customer.tradeName
     *
     
     */
    private String tradename;

    /**
     
     * This field corresponds to the database column t_customer.FContact
     *
     
     */
    private String fcontact;

    /**
     
     * This field corresponds to the database column t_customer.FPhone
     *
     
     */
    private String fphone;

    /**
     
     * This field corresponds to the database column t_customer.FMobilePhone
     *
     
     */
    private String fmobilephone;

    /**
     
     * This field corresponds to the database column t_customer.FFax
     *
     
     */
    private String ffax;

    /**
     
     * This field corresponds to the database column t_customer.FPostalCode
     *
     
     */
    private String fpostalcode;

    /**
     
     * This field corresponds to the database column t_customer.FEmail
     *
     
     */
    private String femail;

    /**
     
     * This field corresponds to the database column t_customer.FBank
     *
     
     */
    private String fbank;

    /**
     
     * This field corresponds to the database column t_customer.FAccount
     *
     
     */
    private String faccount;

    /**
     
     * This field corresponds to the database column t_customer.FTaxNum
     *
     
     */
    private String ftaxnum;

    /**
     
     * This field corresponds to the database column t_customer.FIsCreditMgr
     *
     
     */
    private Boolean fiscreditmgr;

    /**
     
     * This field corresponds to the database column t_customer.saleMode
     *
     
     */
    private String salemode;

    /**
     
     * This field corresponds to the database column t_customer.FValueAddRate
     *
     
     */
    private BigDecimal fvalueaddrate;

    /**
     
     * This field corresponds to the database column t_customer.FCity
     *
     
     */
    private String fcity;

    /**
     
     * This field corresponds to the database column t_customer.FProvince
     *
     
     */
    private String fprovince;

    /**
     
     * This field corresponds to the database column t_customer.FCountry
     *
     
     */
    private String fcountry;

    /**
     
     * This field corresponds to the database column t_customer.FHomePage
     *
     
     */
    private String fhomepage;

    /**
     
     * This field corresponds to the database column t_customer.Fcorperate
     *
     
     */
    private String fcorperate;

    /**
     
     * This field corresponds to the database column t_customer.FCarryingAOS
     *
     
     */
    private Integer fcarryingaos;

    /**
     
     * This field corresponds to the database column t_customer.customerType
     *
     
     */
    private String customertype;

    /**
     
     * This field corresponds to the database column t_customer.SaleTypeName
     *
     
     */
    private String saletypename;

    /**
     
     * This field corresponds to the database column t_customer.SupplierName
     *
     
     */
    private String suppliername;

    /**
     
     * This field corresponds to the database column t_customer.currencyName
     *
     
     */
    private String currencyname;

    /**
     
     * This field corresponds to the database column t_customer.settleName
     *
     
     */
    private String settlename;

    /**
     
     * This field corresponds to the database column t_customer.FfavorPolicy
     *
     
     */
    private String ffavorpolicy;

    /**
     
     * This field corresponds to the database column t_customer.departmentName
     *
     
     */
    private String departmentname;

    /**
     
     * This field corresponds to the database column t_customer.employeeName
     *
     
     */
    private String employeename;

    /**
     
     * This field corresponds to the database column t_customer.FlastTradeDate
     *
     
     */
    private Date flasttradedate;

    /**
     
     * This field corresponds to the database column t_customer.FlastTradeAmount
     *
     
     */
    private Float flasttradeamount;

    /**
     
     * This field corresponds to the database column t_customer.FlastReceiveDate
     *
     
     */
    private Date flastreceivedate;

    /**
     
     * This field corresponds to the database column t_customer.FendTradeDate
     *
     
     */
    private Date fendtradedate;

    /**
     
     * This field corresponds to the database column t_customer.FlastRPAmount
     *
     
     */
    private Float flastrpamount;

    /**
     
     * This field corresponds to the database column t_customer.FmaxDealAmount
     *
     
     */
    private Float fmaxdealamount;

    /**
     
     
     *
     
     */
    private Float fminforereceiverate;

    /**
     
    
     *
     
     */
    private Float fminreserverate;

    /**
     
     
     *
     
     */
    private String debtlevelname;
    
    private String payCondition;
    private Integer payConditionId;
    
    private Integer customerLevel;
    
    private String couserName;
	private String cuserName;
	private Integer evaFinId;
	private Integer cLevelId;
	private Integer evaFinSalemagagerId;
	private Integer evaFinManagerId;
	private Integer evaFinSalemagagerMark;
	private Integer evaFinManagerMark;
	private Integer evaFinGenMark;
	private Integer customerLevelMark;
	
	private String salegenName;
	private String finmanagerName;
	private String ownerUserName;
	private Integer ownerUserId;
	private Integer salegenId;
	private Integer finmanagerId;
	private Integer customerWonerUserId;
	
	private Integer linkmanWriteStatus;		//联系人是否填�?
	private Double linkmanCompletely;		//联系人填写完整度%
	
	private int numFields;				//客户字段�?
	private int numNullFields;			//不完整的字段�?
	
	
	/**
	 * 添加完整的客户字段
	 */
	public void addCompletelyField() {
		numFields++;
	}
	
	/**
	 * 添加不完整的客户字段
	 */
	public void addUnCompletelyField(int count) {
		numNullFields += count;
		numFields += count;
	}
	
	/**
	 * @return the numFields
	 */
	public int getNumFields() {
		return numFields;
	}

	/**
	 * @param numFields the numFields to set
	 */
	public void setNumFields(int numFields) {
		this.numFields = numFields;
	}

	/**
	 * @return the numNullFields
	 */
	public int getNumNullFields() {
		return numNullFields;
	}

	/**
	 * @param numNullFields the numNullFields to set
	 */
	public void setNumNullFields(int numNullFields) {
		this.numNullFields = numNullFields;
	}

	public double getRateOfFieldNotNull() {
		return 1-(double)numNullFields/(double)numFields;
	}
	
	public String getPercentOfFieldNotNull() {
		double percent = (double)numNullFields/(double)numFields;
		return NormalFun.formatPercent(1-percent);
	}
	
	public Integer getOwnerUserId() {
		return ownerUserId;
	}

	public Integer getCustomerWonerUserId() {
		return customerWonerUserId;
	}

	public void setCustomerWonerUserId(Integer customerWonerUserId) {
		this.customerWonerUserId = customerWonerUserId;
	}

	public void setOwnerUserId(Integer ownerUserId) {
		this.ownerUserId = ownerUserId;
	}

	public Integer getSalegenId() {
		return salegenId;
	}

	public void setSalegenId(Integer salegenId) {
		this.salegenId = salegenId;
	}

	public Integer getFinmanagerId() {
		return finmanagerId;
	}

	public void setFinmanagerId(Integer finmanagerId) {
		this.finmanagerId = finmanagerId;
	}

	public String getSalegenName() {
		return salegenName;
	}

	public void setSalegenName(String salegenName) {
		this.salegenName = salegenName;
	}

	public String getFinmanagerName() {
		return finmanagerName;
	}

	public void setFinmanagerName(String finmanagerName) {
		this.finmanagerName = finmanagerName;
	}

	public String getOwnerUserName() {
		return ownerUserName;
	}

	public void setOwnerUserName(String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}

	public Integer getCustomerLevelMark() {
		return customerLevelMark;
	}

	public void setCustomerLevelMark(Integer customerLevelMark) {
		this.customerLevelMark = customerLevelMark;
	}

	public Integer getEvaFinSalemagagerMark() {
		return evaFinSalemagagerMark;
	}

	public void setEvaFinSalemagagerMark(Integer evaFinSalemagagerMark) {
		this.evaFinSalemagagerMark = evaFinSalemagagerMark;
	}

	public Integer getEvaFinManagerMark() {
		return evaFinManagerMark;
	}

	public void setEvaFinManagerMark(Integer evaFinManagerMark) {
		this.evaFinManagerMark = evaFinManagerMark;
	}

	public Integer getEvaFinGenMark() {
		return evaFinGenMark;
	}

	public void setEvaFinGenMark(Integer evaFinGenMark) {
		this.evaFinGenMark = evaFinGenMark;
	}

	private Integer evaFinGenId;

    public Integer getEvaFinSalemagagerId() {
		return evaFinSalemagagerId;
	}

	public void setEvaFinSalemagagerId(Integer evaFinSalemagagerId) {
		this.evaFinSalemagagerId = evaFinSalemagagerId;
	}

	public Integer getEvaFinManagerId() {
		return evaFinManagerId;
	}

	public void setEvaFinManagerId(Integer evaFinManagerId) {
		this.evaFinManagerId = evaFinManagerId;
	}

	public Integer getEvaFinGenId() {
		return evaFinGenId;
	}

	public void setEvaFinGenId(Integer evaFinGenId) {
		this.evaFinGenId = evaFinGenId;
	}

	public String getCouserName() {
		return couserName;
	}

	public void setCouserName(String couserName) {
		this.couserName = couserName;
	}

	public String getCuserName() {
		return cuserName;
	}

	public void setCuserName(String cuserName) {
		this.cuserName = cuserName;
	}

	public Integer getEvaFinId() {
		return evaFinId;
	}

	/**
	 * 财务评估等级
	 * @param evaFinId
	 */
	public void setEvaFinId(Integer evaFinId) {
		numFields++;
		if (evaFinId == null)
			numNullFields++;
		this.evaFinId = evaFinId;
	}

	public Integer getcLevelId() {
		return cLevelId;
	}

	public void setcLevelId(Integer cLevelId) {
//		numFields++;
//		if (cLevelId == null)

		this.cLevelId = cLevelId;
	}

	public Integer getCustomerLevel() {
		return customerLevel;
	}

	/**
	 * 客户等级
	 * @param customerLevel
	 */
	public void setCustomerLevel(Integer customerLevel) {
		numFields++;
		if (customerLevel == null)
			numNullFields++;
		this.customerLevel = customerLevel;
	}

	/**
     
     * This method returns the value of the database column t_customer.FLevel
     *
     * @return the value of t_customer.FLevel
     *
     
     */
    public Integer getFlevel() {
        return flevel;
    }

    /**
     
     * This method sets the value of the database column t_customer.FLevel
     *
     * @param flevel the value for t_customer.FLevel
     *
     
     */
    public void setFlevel(Integer flevel) {
    	
        this.flevel = flevel;
    }

    /**
     
     * This method returns the value of the database column t_customer.customer_id
     *
     * @return the value of t_customer.customer_id
     *
     
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     
     * This method sets the value of the database column t_customer.customer_id
     *
     * @param customerId the value for t_customer.customer_id
     *
     
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     
     * This method returns the value of the database column t_customer.FName
     *
     * @return the value of t_customer.FName
     *
     
     */
    public String getFname() {
        return fname;
    }

    /**
     
     * This method sets the value of the database column t_customer.FName
     *
     * @param fname the value for t_customer.FName
     *
     
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     
     * This method returns the value of the database column t_customer.FNumber
     *
     * @return the value of t_customer.FNumber
     *
     
     */
    public String getFnumber() {
        return fnumber;
    }

    /**
     
     * This method sets the value of the database column t_customer.FNumber
     *
     * @param fnumber the value for t_customer.FNumber
     *
     
     */
    public void setFnumber(String fnumber) {
        this.fnumber = fnumber;
    }

    /**
     
     * This method returns the value of the database column t_customer.FParentID
     *
     * @return the value of t_customer.FParentID
     *
     
     */
    public Integer getFparentid() {
        return fparentid;
    }

    /**
     
     * This method sets the value of the database column t_customer.FParentID
     *
     * @param fparentid the value for t_customer.FParentID
     *
     
     */
    public void setFparentid(Integer fparentid) {
        this.fparentid = fparentid;
    }

    /**
     
     * This method returns the value of the database column t_customer.FDeleted
     *
     * @return the value of t_customer.FDeleted
     *
     
     */
    public Boolean getFdeleted() {
        return fdeleted;
    }

    /**
     
     * This method sets the value of the database column t_customer.FDeleted
     *
     * @param fdeleted the value for t_customer.FDeleted
     *
     
     */
    public void setFdeleted(Boolean fdeleted) {
        this.fdeleted = fdeleted;
    }

    /**
     
     * This method returns the value of the database column t_customer.FDetail
     *
     * @return the value of t_customer.FDetail
     *
     
     */
    public Boolean getFdetail() {
        return fdetail;
    }

    /**
     
     * This method sets the value of the database column t_customer.FDetail
     *
     * @param fdetail the value for t_customer.FDetail
     *
     
     */
    public void setFdetail(Boolean fdetail) {
        this.fdetail = fdetail;
    }

    /**
     
     * This method returns the value of the database column t_customer.FFullName
     *
     * @return the value of t_customer.FFullName
     *
     
     */
    public String getFfullname() {
        return ffullname;
    }

    /**
     
     * This method sets the value of the database column t_customer.FFullName
     *
     * @param ffullname the value for t_customer.FFullName
     *
     
     */
    public void setFfullname(String ffullname) {
        this.ffullname = ffullname;
    }

    /**
     
     * This method returns the value of the database column t_customer.FFullNumber
     *
     * @return the value of t_customer.FFullNumber
     *
     
     */
    public String getFfullnumber() {
        return ffullnumber;
    }

    /**
     
     * This method sets the value of the database column t_customer.FFullNumber
     *
     * @param ffullnumber the value for t_customer.FFullNumber
     *
     
     */
    public void setFfullnumber(String ffullnumber) {
        this.ffullnumber = ffullnumber;
    }

    /**
     
     * This method returns the value of the database column t_customer.FShortNumber
     *
     * @return the value of t_customer.FShortNumber
     *
     
     */
    public String getFshortnumber() {
        return fshortnumber;
    }

    /**
     
     * This method sets the value of the database column t_customer.FShortNumber
     *
     * @param fshortnumber the value for t_customer.FShortNumber
     *
     
     */
    public void setFshortnumber(String fshortnumber) {
        this.fshortnumber = fshortnumber;
    }

    /**
     
     * This method returns the value of the database column t_customer.FUnUsed
     *
     * @return the value of t_customer.FUnUsed
     *
     
     */
    public Boolean getFunused() {
        return funused;
    }

    /**
     
     * This method sets the value of the database column t_customer.FUnUsed
     *
     * @param funused the value for t_customer.FUnUsed
     *
     
     */
    public void setFunused(Boolean funused) {
        this.funused = funused;
    }

    /**
     
     * This method returns the value of the database column t_customer.FShortName
     *
     * @return the value of t_customer.FShortName
     *
     
     */
    public String getFshortname() {
        return fshortname;
    }

    /**
     
     * This method sets the value of the database column t_customer.FShortName
     *
     * @param fshortname the value for t_customer.FShortName
     *
     
     */
    public void setFshortname(String fshortname) {
        this.fshortname = fshortname;
    }

    /**
     
     * This method returns the value of the database column t_customer.Faddress
     *
     * @return the value of t_customer.Faddress
     *
     
     */
    public String getFaddress() {
        return faddress;
    }

    /**
     
     * This method sets the value of the database column t_customer.Faddress
     *
     * @param faddress the value for t_customer.Faddress
     *
     
     */
    public void setFaddress(String faddress) {
    	numFields++;
		if (!(faddress != null && faddress.trim().length() >0))
			numNullFields++;
        this.faddress = faddress;
    }

    /**
     
     * This method returns the value of the database column t_customer.regionName
     *
     * @return the value of t_customer.regionName
     *
     
     */
    public String getRegionname() {
        return regionname;
    }

    /**
     
     * This method sets the value of the database column t_customer.regionName
     *
     * @param regionname the value for t_customer.regionName
     *
     
     */
    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    /**
     
     * This method returns the value of the database column t_customer.tradeName
     *
     * @return the value of t_customer.tradeName
     *
     
     */
    public String getTradename() {
        return tradename;
    }

    /**
     
     * This method sets the value of the database column t_customer.tradeName
     *
     * @param tradename the value for t_customer.tradeName
     *
     
     */
    public void setTradename(String tradename) {
        this.tradename = tradename;
    }

    /**
     
     * This method returns the value of the database column t_customer.FContact
     *
     * @return the value of t_customer.FContact
     *
     
     */
    public String getFcontact() {
        return fcontact;
    }

    /**
     
     * This method sets the value of the database column t_customer.FContact
     *
     * @param fcontact the value for t_customer.FContact
     *
     
     */
    public void setFcontact(String fcontact) {
    	
        this.fcontact = fcontact;
    }

    /**
     
     * This method returns the value of the database column t_customer.FPhone
     *
     * @return the value of t_customer.FPhone
     *
     
     */
    public String getFphone() {
        return fphone;
    }

    /**
     
     * This method sets the value of the database column t_customer.FPhone
     *
     * @param fphone the value for t_customer.FPhone
     *
     
     */
    public void setFphone(String fphone) {
    	numFields++;
		if (!(fphone != null && fphone.trim().length() >0))
			numNullFields++;
        this.fphone = fphone;
    }

    /**
     
     * This method returns the value of the database column t_customer.FMobilePhone
     *
     * @return the value of t_customer.FMobilePhone
     *
     
     */
    public String getFmobilephone() {
        return fmobilephone;
    }

    /**
     
     * This method sets the value of the database column t_customer.FMobilePhone
     *
     * @param fmobilephone the value for t_customer.FMobilePhone
     *
     
     */
    public void setFmobilephone(String fmobilephone) {
    	numFields++;
		if (!(fmobilephone != null && fmobilephone.trim().length() >0))
			numNullFields++;
        this.fmobilephone = fmobilephone;
    }

    /**
     
     * This method returns the value of the database column t_customer.FFax
     *
     * @return the value of t_customer.FFax
     *
     
     */
    public String getFfax() {
        return ffax;
    }

    /**
     
     * This method sets the value of the database column t_customer.FFax
     *
     * @param ffax the value for t_customer.FFax
     *
     
     */
    public void setFfax(String ffax) {
    	
        this.ffax = ffax;
    }

    /**
     
     * This method returns the value of the database column t_customer.FPostalCode
     *
     * @return the value of t_customer.FPostalCode
     *
     
     */
    public String getFpostalcode() {
        return fpostalcode;
    }

    /**
     
     * This method sets the value of the database column t_customer.FPostalCode
     *
     * @param fpostalcode the value for t_customer.FPostalCode
     *
     
     */
    public void setFpostalcode(String fpostalcode) {
    	
        this.fpostalcode = fpostalcode;
    }

    /**
     
     * This method returns the value of the database column t_customer.FEmail
     *
     * @return the value of t_customer.FEmail
     *
     
     */
    public String getFemail() {
        return femail;
    }

    /**
     
     * This method sets the value of the database column t_customer.FEmail
     *
     * @param femail the value for t_customer.FEmail
     *
     
     */
    public void setFemail(String femail) {
    	
        this.femail = femail;
    }

    /**
     
     * This method returns the value of the database column t_customer.FBank
     *
     * @return the value of t_customer.FBank
     *
     
     */
    public String getFbank() {
        return fbank;
    }

    /**
     
     * This method sets the value of the database column t_customer.FBank
     *
     * @param fbank the value for t_customer.FBank
     *
     
     */
    public void setFbank(String fbank) {
    	
        this.fbank = fbank;
    }

    /**
     
     * This method returns the value of the database column t_customer.FAccount
     *
     * @return the value of t_customer.FAccount
     *
     
     */
    public String getFaccount() {
        return faccount;
    }

    /**
     
     * This method sets the value of the database column t_customer.FAccount
     *
     * @param faccount the value for t_customer.FAccount
     *
     
     */
    public void setFaccount(String faccount) {
    	
        this.faccount = faccount;
    }

    /**
     
     * This method returns the value of the database column t_customer.FTaxNum
     *
     * @return the value of t_customer.FTaxNum
     *
     
     */
    public String getFtaxnum() {
        return ftaxnum;
    }

    /**
     
     * This method sets the value of the database column t_customer.FTaxNum
     *
     * @param ftaxnum the value for t_customer.FTaxNum
     *
     
     */
    public void setFtaxnum(String ftaxnum) {
    	
        this.ftaxnum = ftaxnum;
    }

    /**
     
     * This method returns the value of the database column t_customer.FIsCreditMgr
     *
     * @return the value of t_customer.FIsCreditMgr
     *
     
     */
    public Boolean getFiscreditmgr() {
        return fiscreditmgr;
    }

    /**
     
     * This method sets the value of the database column t_customer.FIsCreditMgr
     *
     * @param fiscreditmgr the value for t_customer.FIsCreditMgr
     *
     
     */
    public void setFiscreditmgr(Boolean fiscreditmgr) {
        this.fiscreditmgr = fiscreditmgr;
    }

    /**
     
     * This method returns the value of the database column t_customer.saleMode
     *
     * @return the value of t_customer.saleMode
     *
     
     */
    public String getSalemode() {
        return salemode;
    }

    /**
     
     * This method sets the value of the database column t_customer.saleMode
     *
     * @param salemode the value for t_customer.saleMode
     *
     
     */
    public void setSalemode(String salemode) {
    	
        this.salemode = salemode;
    }

    /**
     
     * This method returns the value of the database column t_customer.FValueAddRate
     *
     * @return the value of t_customer.FValueAddRate
     *
     
     */
    public BigDecimal getFvalueaddrate() {
        return fvalueaddrate;
    }

    /**
     
     * This method sets the value of the database column t_customer.FValueAddRate
     *
     * @param fvalueaddrate the value for t_customer.FValueAddRate
     *
     
     */
    public void setFvalueaddrate(BigDecimal fvalueaddrate) {
    	
        this.fvalueaddrate = fvalueaddrate;
    }

    /**
     
     * This method returns the value of the database column t_customer.FCity
     *
     * @return the value of t_customer.FCity
     *
     
     */
    public String getFcity() {
        return fcity;
    }

    /**
     
     * This method sets the value of the database column t_customer.FCity
     *
     * @param fcity the value for t_customer.FCity
     *
     
     */
    public void setFcity(String fcity) {
    	
        this.fcity = fcity;
    }

    /**
     
     * This method returns the value of the database column t_customer.FProvince
     *
     * @return the value of t_customer.FProvince
     *
     
     */
    public String getFprovince() {
        return fprovince;
    }

    /**
     
     * This method sets the value of the database column t_customer.FProvince
     *
     * @param fprovince the value for t_customer.FProvince
     *
     
     */
    public void setFprovince(String fprovince) {
    	
        this.fprovince = fprovince;
    }

    /**
     
     * This method returns the value of the database column t_customer.FCountry
     *
     * @return the value of t_customer.FCountry
     *
     
     */
    public String getFcountry() {
        return fcountry;
    }

    /**
     
     * This method sets the value of the database column t_customer.FCountry
     *
     * @param fcountry the value for t_customer.FCountry
     *
     
     */
    public void setFcountry(String fcountry) {
        this.fcountry = fcountry;
    }

    /**
     
     * This method returns the value of the database column t_customer.FHomePage
     *
     * @return the value of t_customer.FHomePage
     *
     
     */
    public String getFhomepage() {
        return fhomepage;
    }

    /**
     
     * This method sets the value of the database column t_customer.FHomePage
     *
     * @param fhomepage the value for t_customer.FHomePage
     *
     
     */
    public void setFhomepage(String fhomepage) {
    	
        this.fhomepage = fhomepage;
    }

    /**
     
     * This method returns the value of the database column t_customer.Fcorperate
     *
     * @return the value of t_customer.Fcorperate
     *
     
     */
    public String getFcorperate() {
        return fcorperate;
    }

    /**
     
     * This method sets the value of the database column t_customer.Fcorperate
     *
     * @param fcorperate the value for t_customer.Fcorperate
     *
     
     */
    public void setFcorperate(String fcorperate) {
    	
        this.fcorperate = fcorperate;
    }

    /**
     
     * This method returns the value of the database column t_customer.FCarryingAOS
     *
     * @return the value of t_customer.FCarryingAOS
     *
     
     */
    public Integer getFcarryingaos() {
        return fcarryingaos;
    }

    /**
     
     * This method sets the value of the database column t_customer.FCarryingAOS
     *
     * @param fcarryingaos the value for t_customer.FCarryingAOS
     *
     
     */
    public void setFcarryingaos(Integer fcarryingaos) {
    	
        this.fcarryingaos = fcarryingaos;
    }

    /**
     
     * This method returns the value of the database column t_customer.customerType
     *
     * @return the value of t_customer.customerType
     *
     
     */
    public String getCustomertype() {
        return customertype;
    }

    /**
     
     * This method sets the value of the database column t_customer.customerType
     *
     * @param customertype the value for t_customer.customerType
     *
     
     */
    public void setCustomertype(String customertype) {
    	
        this.customertype = customertype;
    }

    /**
     
     * This method returns the value of the database column t_customer.SaleTypeName
     *
     * @return the value of t_customer.SaleTypeName
     *
     
     */
    public String getSaletypename() {
        return saletypename;
    }

    /**
     
     * This method sets the value of the database column t_customer.SaleTypeName
     *
     * @param saletypename the value for t_customer.SaleTypeName
     *
     
     */
    public void setSaletypename(String saletypename) {
        this.saletypename = saletypename;
    }

    /**
     
     * This method returns the value of the database column t_customer.SupplierName
     *
     * @return the value of t_customer.SupplierName
     *
     
     */
    public String getSuppliername() {
        return suppliername;
    }

    /**
     
     * This method sets the value of the database column t_customer.SupplierName
     *
     * @param suppliername the value for t_customer.SupplierName
     *
     
     */
    public void setSuppliername(String suppliername) {

//		if (!(suppliername != null && suppliername.trim().length() >0))

        this.suppliername = suppliername;
    }

    /**
     
     * This method returns the value of the database column t_customer.currencyName
     *
     * @return the value of t_customer.currencyName
     *
     
     */
    public String getCurrencyname() {
        return currencyname;
    }

    /**
     
     * This method sets the value of the database column t_customer.currencyName
     *
     * @param currencyname the value for t_customer.currencyName
     *
     
     */
    public void setCurrencyname(String currencyname) {
        this.currencyname = currencyname;
    }

    /**
     
     * This method returns the value of the database column t_customer.settleName
     *
     * @return the value of t_customer.settleName
     *
     
     */
    public String getSettlename() {
        return settlename;
    }

    /**
     
     * This method sets the value of the database column t_customer.settleName
     *
     * @param settlename the value for t_customer.settleName
     *
     
     */
    public void setSettlename(String settlename) {
    	numFields++;
		if (!(settlename != null 
				&& !settlename.equalsIgnoreCase("*") && settlename.trim().length() >0))
			numNullFields++;
        this.settlename = settlename;
    }

    /**
     
     * This method returns the value of the database column t_customer.FfavorPolicy
     *
     * @return the value of t_customer.FfavorPolicy
     *
     
     */
    public String getFfavorpolicy() {
        return ffavorpolicy;
    }

    /**
     
     * This method sets the value of the database column t_customer.FfavorPolicy
     *
     * @param ffavorpolicy the value for t_customer.FfavorPolicy
     *
     
     */
    public void setFfavorpolicy(String ffavorpolicy) {
        this.ffavorpolicy = ffavorpolicy;
    }

    /**
     
     * This method returns the value of the database column t_customer.departmentName
     *
     * @return the value of t_customer.departmentName
     *
     
     */
    public String getDepartmentname() {
        return departmentname;
    }

    /**
     
     * This method sets the value of the database column t_customer.departmentName
     *
     * @param departmentname the value for t_customer.departmentName
     *
     
     */
    public void setDepartmentname(String departmentname) {
    	numFields++;
		if (!(departmentname != null && departmentname.trim().length() >0))
			numNullFields++;
        this.departmentname = departmentname;
    }

    /**
     
     * This method returns the value of the database column t_customer.employeeName
     *
     * @return the value of t_customer.employeeName
     *
     
     */
    public String getEmployeename() {
        return employeename;
    }

    /**
     
     * This method sets the value of the database column t_customer.employeeName
     *
     * @param employeename the value for t_customer.employeeName
     *
     
     */
    public void setEmployeename(String employeename) {
    	numFields++;
		if (!(employeename != null && employeename.trim().length() >0))
			numNullFields++;
        this.employeename = employeename;
    }

    /**
     
     * This method returns the value of the database column t_customer.FlastTradeDate
     *
     * @return the value of t_customer.FlastTradeDate
     *
     
     */
    public Date getFlasttradedate() {
        return flasttradedate;
    }

    /**
     
     * This method sets the value of the database column t_customer.FlastTradeDate
     *
     * @param flasttradedate the value for t_customer.FlastTradeDate
     *
     
     */
    public void setFlasttradedate(Date flasttradedate) {

//		if (flasttradedate == null)

        this.flasttradedate = flasttradedate;
    }

    /**
     
     * This method returns the value of the database column t_customer.FlastTradeAmount
     *
     * @return the value of t_customer.FlastTradeAmount
     *
     
     */
    public Float getFlasttradeamount() {
        return flasttradeamount;
    }

    /**
     
     * This method sets the value of the database column t_customer.FlastTradeAmount
     *
     * @param flasttradeamount the value for t_customer.FlastTradeAmount
     *
     
     */
    public void setFlasttradeamount(Float flasttradeamount) {
    	
        this.flasttradeamount = flasttradeamount;
    }

    /**
     
     * This method returns the value of the database column t_customer.FlastReceiveDate
     *
     * @return the value of t_customer.FlastReceiveDate
     *
     
     */
    public Date getFlastreceivedate() {
        return flastreceivedate;
    }

    /**
     
     * This method sets the value of the database column t_customer.FlastReceiveDate
     *
     * @param flastreceivedate the value for t_customer.FlastReceiveDate
     *
     
     */
    public void setFlastreceivedate(Date flastreceivedate) {
    	
        this.flastreceivedate = flastreceivedate;
    }

    /**
     
     * This method returns the value of the database column t_customer.FendTradeDate
     *
     * @return the value of t_customer.FendTradeDate
     *
     
     */
    public Date getFendtradedate() {
        return fendtradedate;
    }

    /**
     
     * This method sets the value of the database column t_customer.FendTradeDate
     *
     * @param fendtradedate the value for t_customer.FendTradeDate
     *
     
     */
    public void setFendtradedate(Date fendtradedate) {

//		if (fendtradedate == null)

        this.fendtradedate = fendtradedate;
    }

    /**
     
     * This method returns the value of the database column t_customer.FlastRPAmount
     *
     * @return the value of t_customer.FlastRPAmount
     *
     
     */
    public Float getFlastrpamount() {
        return flastrpamount;
    }

    /**
     
     * This method sets the value of the database column t_customer.FlastRPAmount
     *
     * @param flastrpamount the value for t_customer.FlastRPAmount
     *
     
     */
    public void setFlastrpamount(Float flastrpamount) {

//		if (flastrpamount == null)

        this.flastrpamount = flastrpamount;
    }

    /**
     
     * This method returns the value of the database column t_customer.FmaxDealAmount
     *
     * @return the value of t_customer.FmaxDealAmount
     *
     
     */
    public Float getFmaxdealamount() {
        return fmaxdealamount;
    }

    /**
     
     * This method sets the value of the database column t_customer.FmaxDealAmount
     *
     * @param fmaxdealamount the value for t_customer.FmaxDealAmount
     *
     
     */
    public void setFmaxdealamount(Float fmaxdealamount) {

//		if (fmaxdealamount == null)

        this.fmaxdealamount = fmaxdealamount;
    }

    /**
     
     * This method returns the value of the database column t_customer.FminForeReceiveRate
     *
     * @return the value of t_customer.FminForeReceiveRate
     *
     
     */
    public Float getFminforereceiverate() {
        return fminforereceiverate;
    }

    /**
     
     * This method sets the value of the database column t_customer.FminForeReceiveRate
     *
     * @param fminforereceiverate the value for t_customer.FminForeReceiveRate
     *
     
     */
    public void setFminforereceiverate(Float fminforereceiverate) {

//		if (fminforereceiverate == null)

        this.fminforereceiverate = fminforereceiverate;
    }

    /**
     
     * This method returns the value of the database column t_customer.FminReserverate
     *
     * @return the value of t_customer.FminReserverate
     *
     
     */
    public Float getFminreserverate() {
        return fminreserverate;
    }

    /**
     
     * This method sets the value of the database column t_customer.FminReserverate
     *
     * @param fminreserverate the value for t_customer.FminReserverate
     *
     
     */
    public void setFminreserverate(Float fminreserverate) {

//		if (fminreserverate == null)

        this.fminreserverate = fminreserverate;
    }

    /**
     
     * This method returns the value of the database column t_customer.debtLevelName
     *
     * @return the value of t_customer.debtLevelName
     *
     
     */
    public String getDebtlevelname() {
        return debtlevelname;
    }

    /**
     
     * This method sets the value of the database column t_customer.debtLevelName
     *
     * @param debtlevelname the value for t_customer.debtLevelName
     *
     
     */
    public void setDebtlevelname(String debtlevelname) {

//		if (!(debtlevelname != null && debtlevelname.trim().length() >0))

        this.debtlevelname = debtlevelname;
    }

	/**
	 * @return the payCondition
	 */
	public String getPayCondition() {
		return payCondition;
	}

	/**
	 * @param payCondition the payCondition to set
	 */
	public void setPayCondition(String payCondition) {
		numFields++;
		if (!(payCondition != null && payCondition.trim().length() >0))
			numNullFields++;
		this.payCondition = payCondition;
	}

	/**
	 * @return the payConditionId
	 */
	public Integer getPayConditionId() {
		return payConditionId;
	}

	/**
	 * @param payConditionId the payConditionId to set
	 */
	public void setPayConditionId(Integer payConditionId) {
		this.payConditionId = payConditionId;
	}

	/**
	 * @return the linkmanCompletely
	 */
	public Double getLinkmanCompletely() {
		return linkmanCompletely;
	}
	
	public String getLinkmanCompletelyFormat() {
		return NormalFun.formatPercent(linkmanCompletely);
	}

	/**
	 * @param linkmanCompletely the linkmanCompletely to set
	 */
	public void setLinkmanCompletely(Double linkmanCompletely) {
		numFields++;
		if(!(linkmanCompletely !=null && linkmanCompletely.intValue()==1))
			numNullFields++;
		this.linkmanCompletely = linkmanCompletely;
	}

}