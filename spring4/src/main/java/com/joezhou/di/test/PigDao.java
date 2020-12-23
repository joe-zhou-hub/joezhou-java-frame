package com.joezhou.di.test;

import org.springframework.stereotype.Repository;

/**
 * @author JoeZhou
 */
@Repository
public class PigDao {
    public void info() {
        System.out.println("PigDao.info...");
    }
}