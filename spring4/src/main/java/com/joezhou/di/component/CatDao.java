package com.joezhou.di.component;

import org.springframework.stereotype.Repository;

/**
 * @author JoeZhou
 */
@Repository
public class CatDao {
    public void info() {
        System.out.println("CatDao.info...");
    }
}