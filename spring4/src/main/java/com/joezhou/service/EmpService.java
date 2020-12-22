package com.joezhou.service;

import com.joezhou.dao.EmpDao;
import lombok.Data;

/**
 * @author JoeZhou
 */
@Data
public class EmpService {
    private EmpDao empDao;

    public void insert() {
        empDao.insert();
    }
}