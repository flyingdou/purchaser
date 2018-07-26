package com.purchaser.dao;

import java.util.List;
import java.util.Map;

import com.purchaser.pojo.Admirer;
import com.purchaser.pojo.AdmirerWithBLOBs;

public interface AdmirerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdmirerWithBLOBs record);

    int insertSelective(AdmirerWithBLOBs record);

    AdmirerWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdmirerWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AdmirerWithBLOBs record);

    int updateByPrimaryKey(Admirer record);
    
    /**
     * 查询采购师列表
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getAdmirerList(Map<String, Object> paramMap);
    
    
    /**
     * 查询采购师详情
     * @param paramMap
     * @return
     */
    Map<String, Object> admirerDetail (Map<String, Object> paramMap);
    
    
    /**
     * 通过采购师id查询user
     * @param paramMap
     * @return
     */
    Map<String, Object> getUserByAdmirer (Map<String, Object> paramMap);
    
}