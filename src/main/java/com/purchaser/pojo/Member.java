package com.purchaser.pojo;

import java.util.Date;

/**
 * 会员表(tb_member)
 * @author Administrator
 *
 */
public class Member {
    private Long id;

    /**
     *  user表外键
     */
    private Long user;

    /**
     *  职务
     */
    private String duty;

    /**
     *  所属公司
     */
    private String affiliation;

    /**
     *  企业类型
     */
    private Integer companyType;

    /**
     *  行业类别
     */
    private Integer business;

    /**
     *  会员类型(会员费用表外键)
     */
    private Integer type;

    /**
     *  审核状态(0:未审核, 1:审核通过, 2:审核未通过)
     */
    private Integer audit;

    /**
     *  申请日期
     */
    private Date applyDate;
    
     /**
      * 是否有效 (0:无效, 1:有效, 2:过了有效期，需重新付费生效)
      */
    private Integer valid;
    
    /**
     * 会员编号 
     */
    private String no;

    
    /**
     * setter && getter
     * @return
     */
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

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty == null ? null : duty.trim();
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation == null ? null : affiliation.trim();
    }

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public Integer getBusiness() {
        return business;
    }

    public void setBusiness(Integer business) {
        this.business = business;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
    
    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
    
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }
    
}