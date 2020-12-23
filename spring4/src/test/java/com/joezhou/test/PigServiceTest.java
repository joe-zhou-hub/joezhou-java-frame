package com.joezhou.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author JoeZhou
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/test/pig.xml")
public class PigServiceTest {

    @Autowired
    private PigDao pigDao;

    @Test
    public void info() {
        pigDao.info();
    }
}
