package com.joezhou.mapper;

import com.joezhou.pojo.Worker;

import java.util.List;

/**
 * @author JoeZhou
 */
public interface WorkerMapper {

    /**
     * 根据主键查询一条信息
     *
     * @param id 主键
     * @return 对应主键的一条信息
     */
    Worker findById(int id);

    /**
     * 添加一条工人信息
     *
     * @param worker 工人实体
     */
    void insert(Worker worker);

    /**
     * 按照姓名模糊查询所有符合条件的工人
     *
     * @param name 姓名的模糊查询部分
     * @return 所有符合条件的工人
     */
    List<Worker> findLikeName(String name);

    /**
     * 根据主键修改一条工人信息
     *
     * @param worker 工人实体
     */
    void updateById(Worker worker);

    /**
     * 根据主键删除工人
     *
     * @param id 主键
     */
    void deleteById(int id);
}