package com.joezhou.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @author JoeZhou
 */
@Service
public class DataSourceService {

    private DataSourceDao dataSourceDao;

    @Autowired
    public DataSourceService(DataSourceDao dataSourceDao) {
        this.dataSourceDao = dataSourceDao;
    }

    public boolean getConnection() throws SQLException {
        return dataSourceDao.getConnection();
    }
}