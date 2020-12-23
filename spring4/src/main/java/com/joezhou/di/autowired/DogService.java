package com.joezhou.di.autowired;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author JoeZhou
 */
public class DogService {

    private DogDao dogDao;

    @Autowired
    public DogService(DogDao dogDao){
        this.dogDao = dogDao;
    }

    public void info() {
        dogDao.info();
    }
}