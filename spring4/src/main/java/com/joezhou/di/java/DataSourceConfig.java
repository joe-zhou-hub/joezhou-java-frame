package com.joezhou.di.java;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @PropertySource("classpath:jdbc/db.properties") 相当于
 * <bean id="propertyConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
 *     <property name="location" value="classpath:jdbc/db.properties"/>
 * </bean>
 * @author JoeZhou
 */
@Configuration
@PropertySource("classpath:jdbc/db.properties")
@ComponentScan("com.joezhou.di.java")
public class DataSourceConfig {

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.user}")
    private String user;

    @Value("${jdbc.password}")
    private String password;

    /**
     * 相当于：
     * <bean id="dataSource" class="com.jolbox.bonecp.BoneCPDataSource" destroy-method="close">
     *     <property name="driverClass" value="${jdbc.driver}"/>
     *     <property name="jdbcUrl" value="${jdbc.url}"/>
     *     <property name="username" value="${jdbc.user}"/>
     *     <property name="password" value="${jdbc.password}"/>
     *     <property name="maxConnectionsPerPartition" value="100"/>
     *     <property name="minConnectionsPerPartition" value="5"/>
     * </bean>
     *
     * @return 数据源对象
     */
    @Bean
    public DataSource dataSource() {
        BoneCPDataSource bonecpDataSource = new BoneCPDataSource();
        bonecpDataSource.setDriverClass(driver);
        bonecpDataSource.setJdbcUrl(url);
        bonecpDataSource.setUsername(user);
        bonecpDataSource.setPassword(password);
        bonecpDataSource.setMaxConnectionsPerPartition(100);
        bonecpDataSource.setMinConnectionsPerPartition(5);
        return bonecpDataSource;
    }
}