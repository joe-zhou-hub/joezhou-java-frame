package com.joezhou.di.resource;

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