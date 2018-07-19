package com.purchaser.pojo;

import java.util.Date;

/**
 * 签到表(tb_sign)
 * @author Administrator
 *
 */
public class Sign {
    private Long id;

    // 活动表外键
    private Long active;

    // 签到日期
    private Date signDate;

    // 签到用户
    private Long user;

    // 签到状态
    private Integer status;

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

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}