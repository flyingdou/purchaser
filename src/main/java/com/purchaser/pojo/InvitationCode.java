package com.purchaser.pojo;

import java.util.Date;

/**
 * 邀请码表(tb_invitation_code)
 * @author Administrator
 *
 */
public class InvitationCode {
    private Long id;

    // 活动表外键
    private Long active;

    // 邀请码
    private String code;
    
    // 适用类型(0:嘉宾, 1:厂商)
    private Integer type;

    // 是否有效(0:未使用, 1:已使用)
    private Integer effective;

    // 创建日期
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getEffective() {
        return effective;
    }

    public void setEffective(Integer effective) {
        this.effective = effective;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}