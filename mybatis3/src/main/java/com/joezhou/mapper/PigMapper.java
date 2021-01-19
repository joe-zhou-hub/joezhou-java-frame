package com.joezhou.mapper;

import com.joezhou.pojo.Pig;

import java.util.List;

/**
 * @author JoeZhou
 */
public interface PigMapper {

    /**
     * 分页查询小猪信息
     *
     * @return 部分小猪信息
     */
    List<Pig> paging();

}