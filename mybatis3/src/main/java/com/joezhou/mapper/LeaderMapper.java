package com.joezhou.mapper;

import com.joezhou.pojo.Leader;

/**
 * @author JoeZhou
 */
public interface LeaderMapper {

    /**
     * 根据主键查询一条信息
     *
     * @param id 主键
     * @return 对应主键的一条信息
     */
    Leader findById(int id);

}