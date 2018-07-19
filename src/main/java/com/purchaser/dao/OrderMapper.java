package com.purchaser.dao;

import com.purchaser.pojo.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    /**
     * 根据订单编号，查询订单
     * @param no
     * @return
     */
    public Order findOrderByNo (String no);
}