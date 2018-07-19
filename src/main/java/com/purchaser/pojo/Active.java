package com.purchaser.pojo;

import java.util.Date;

/**
 * 活动表(tb_active)
 * @author Administrator
 *
 */
public class Active {
    private Long id;

    // 活动名称
    private String name;

    // 活动封面
    private String image;

    // 发起者(user表外键)
    private Long creator;

    // 会员价格
    private Integer price;

    // 嘉宾价格
    private Integer distinguishedPrice;

    // 厂商价格
    private Integer manufacturerPrice;

    // 开始日期
    private Date startDate;

    // 结束日期
    private Date endDate;

    // 报名结束日期
    private Date joinEndDate;

    // 联系电话
    private String telephone;

    // 主办单位
    private String hostUnit;

    // 人数上限
    private Integer upperLimit;

    // 地址
    private String address;

    // 经度
    private Long longitude;

    // 纬度
    private Long latitude;
    
    // 活动类型
    private int type;

    // 活动说明
    private String remark;

    // 发布日期
    private Date createDate;

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
        this.name = name == null ? null : name.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDistinguishedPrice() {
        return distinguishedPrice;
    }

    public void setDistinguishedPrice(Integer distinguishedPrice) {
        this.distinguishedPrice = distinguishedPrice;
    }

    public Integer getManufacturerPrice() {
        return manufacturerPrice;
    }

    public void setManufacturerPrice(Integer manufacturerPrice) {
        this.manufacturerPrice = manufacturerPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getJoinEndDate() {
        return joinEndDate;
    }

    public void setJoinEndDate(Date joinEndDate) {
        this.joinEndDate = joinEndDate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getHostUnit() {
        return hostUnit;
    }

    public void setHostUnit(String hostUnit) {
        this.hostUnit = hostUnit == null ? null : hostUnit.trim();
    }

    public Integer getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public String getRemark() {
        return remark;
    }

    public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}