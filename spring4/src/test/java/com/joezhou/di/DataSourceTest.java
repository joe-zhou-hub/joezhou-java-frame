package com.joezhou.di;

import com.joezhou.di.java.DataSourceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author JoeZhou
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataSourceConfig.class)
public class DataSourceTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection().isClosed());
    }

}
