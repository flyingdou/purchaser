package com.purchaser.pojo;

import java.util.Date;

/**
 * 订单表(tb_order)
 * @author Administrator
 *
 */
public class Order {
    private Long id;

    // 订单编号
    private String no;

    // 商品类型  A:活动订单     M:入会申请订单
    private String productType;

    // 商品id(根据商品类型链接外表)
    private Long productId;

    // 订单金额
    private Long money;
    
    private Long User;

    // 订单状态  0未支付    1已支付   
    private Integer status;

    // 支付时间
    private Date payTime;

    // 订单创建日期
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getUser() {
		return User;
	}

	public void setUser(Long user) {
		User = user;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}