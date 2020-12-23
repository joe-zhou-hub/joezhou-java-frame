package com.joezhou.di.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JoeZhou
 */
@Service
public class PigService {

    private PigDao pigDao;

    @Autowired
    public PigService(PigDao pigDao) {
        this.pigDao = pigDao;
    }

    public void info() {
        pigDao.info();
    }
}