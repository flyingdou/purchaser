package com.purchaser.pojo;

import java.util.Date;

/**
 * 用户表(tb_user)
 * @author Administrator
 *
 */
public class User {
    private Long id;

    // 姓名
    private String name;

    // 性别(M:男, F:女)
    private String gender;

    // 身份证号
    private String idCardNum;

    // 籍贯
    private String concord;

    // 手机
    private String mobilephone;

    // 电子邮箱
    private String email;

    // 照片
    private String image;

    // 权限:0(普通注册用户), 1(后台管理员)
    private Integer power;

    // 注册日期
    private Date registerDate;
    
    // wechat_id 微信唯一识别码
    private String wechatId;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum == null ? null : idCardNum.trim();
    }

    public String getConcord() {
        return concord;
    }

    public void setConcord(String concord) {
        this.concord = concord == null ? null : concord.trim();
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
    
    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId == null ? null : wechatId.trim();
    }
    
}