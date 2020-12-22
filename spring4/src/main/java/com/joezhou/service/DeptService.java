package com.joezhou.service;

import com.joezhou.dao.DeptDao;

import javax.annotation.Resource;

/**
 * @author JoeZhou
 */
public class DeptService {

    @Resource
    private DeptDao deptDao;

    public void info() {
        deptDao.info();
    }
}