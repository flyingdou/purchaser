package com.purchaser.pojo;

import java.util.Date;

/**
 * 供应商表(tb_supplier)
 * @author Administrator
 *
 */
public class Supplier {
    private Long id;

    // 公司名称
    private String name;

    // 注册资本
    private Integer registeredCapital;

    // 法人代表
    private String representative;

    // 地址
    private String address;

    // 成立日期
    private Date createDate;

    // 职工人数
    private Integer workers;

    // 行业类别
    private Integer business;

    // 工厂面积
    private Integer factoryArea;

    // 官网
    private String officialNetwork;

    // 照片1
    private String image1;
    
    // 照片2
    private String image2;
    
    // 照片3
    private String image3;

    // 联系人
    private String contact;

    // 职务
    private String duty;

    // 联系手机
    private String mobilephone;

    // 电子邮箱
    private String email;

    // 传真号
    private String faxNumber;

    // 业务范围(其他说明)
    private String remark;

    // 创建者(user表外键)
    private Long creator;

    // 审核状态(0:未审核, 1:审核通过, 2:审核未通过)
    private Integer audit;

    // 申请日期
    private Date applyDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(Integer registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getRepresentative() {
		return representative;
	}

	public void setRepresentative(String representative) {
		this.representative = representative;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getWorkers() {
		return workers;
	}

	public void setWorkers(Integer workers) {
		this.workers = workers;
	}

	public Integer getBusiness() {
		return business;
	}

	public void setBusiness(Integer business) {
		this.business = business;
	}

	public Integer getFactoryArea() {
		return factoryArea;
	}

	public void setFactoryArea(Integer factoryArea) {
		this.factoryArea = factoryArea;
	}

	public String getOfficialNetwork() {
		return officialNetwork;
	}

	public void setOfficialNetwork(String officialNetwork) {
		this.officialNetwork = officialNetwork;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Integer getAudit() {
		return audit;
	}

	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
}