package com.purchaser.pojo;

import java.util.Date;

/**
 * 工作履历表(tb_experience)
 * @author Administrator
 *
 */
public class Experience {
    private Long id;

    // admirer表外键
    private Long admirer;
    
    // user表外键
    private Long user;

    // 开始时间
    private Date starttime;

    // 结束时间
    private Date endtime;

    // 所属公司
    private String companyName;

    // 在职岗位
    private String post;

    // 工作简介
    private String postDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdmirer() {
        return admirer;
    }

    public void setUser(Long user) {
        this.user = user;
    }
    
    public Long getUser() {
        return user;
    }

    public void setAdmirer(Long admirer) {
        this.admirer = admirer;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public String getPostDetail() {
        return postDetail;
    }

    public void setPostDetail(String postDetail) {
        this.postDetail = postDetail == null ? null : postDetail.trim();
    }
}