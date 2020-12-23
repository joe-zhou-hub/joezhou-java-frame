package com.joezhou.di;

import com.joezhou.test.PigDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author JoeZhou
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/di/pig.xml")
public class PigTest {

    @Autowired
    private PigDao pigDao;

    @Test
    public void info() {
        pigDao.info();
    }
}
