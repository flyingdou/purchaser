package com.purchaser.pojo;

import java.util.Date;

/**
 * 采购师表(tb_admirer)
 * @author Administrator
 *
 */
public class Admirer {
    private Long id;

    // user表外键
    private Long user;

    // 居住地
    private String address;

    // 学历
    private String study;

    // 语言能力
    private String language;

    // 资格认证
    private Long workExperience;

    // 自我评价
    private String selfEvaluation;

    // 创建者
    private Long creator;

    // 审核状态
    private Integer audit;

    // 申请日期
    private Date applyDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study == null ? null : study.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public Long getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(Long workExperience) {
        this.workExperience = workExperience;
    }

    public String getSelfEvaluation() {
        return selfEvaluation;
    }

    public void setSelfEvaluation(String selfEvaluation) {
        this.selfEvaluation = selfEvaluation == null ? null : selfEvaluation.trim();
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