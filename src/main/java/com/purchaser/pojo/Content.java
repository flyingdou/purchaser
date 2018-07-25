package com.purchaser.pojo;

import java.util.Date;

/**
 * 内容链接表(tb_content)
 * @author Administrator
 *
 */
public class Content {
    private Long id;

    // 内容名称
    private String name;

    // 内容图片
    private String image;

    // 内容描述
    private String remark;

    // 内容类型
    private String contentType;

    // 内容分类
    private String classification;

    // 内容链接
    private String contentUrl;
    
    // 0: 不置顶, 1: 置顶
    private int setTop;

    // 创建日期
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType == null ? null : contentType.trim();
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification == null ? null : classification.trim();
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl == null ? null : contentUrl.trim();
    }

    public int getSetTop() {
		return setTop;
	}

	public void setSetTop(int setTop) {
		this.setTop = setTop;
	}

	public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}