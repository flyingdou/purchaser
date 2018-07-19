package com.purchaser.pojo;

/**
 * 会员价格表(tb_member_price)
 * @author Administrator
 *
 */
public class MemberPrice {
    private Long id;

    // 会员类型
    private Integer type;

    // 类型名称
    private String typeName;

    // 价格
    private Long price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}