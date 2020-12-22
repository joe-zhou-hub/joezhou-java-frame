package com.joezhou.service;

import com.joezhou.dao.CatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JoeZhou
 */
@Service
public class CatService {
    private CatDao catDao;

    @Autowired
    public CatService(CatDao catDao) {
        this.catDao = catDao;
    }

    public void info() {
        catDao.info();
    }
}