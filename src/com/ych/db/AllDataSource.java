package com.ych.db;

import java.beans.PropertyVetoException;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class AllDataSource {

	 /** 
     * 获取c3p0数据源 
     *  
     */  
    public static ComboPooledDataSource getC3p0DataSource() {  
        // 设置参数  
        ComboPooledDataSource cpds = new ComboPooledDataSource();  
        try {  
            cpds.setDriverClass(DbConfigParam.driver);  
        } catch (PropertyVetoException e) {  
            e.printStackTrace();  
        }  
        cpds.setJdbcUrl(DbConfigParam.jdbcUrl);  
        cpds.setUser(DbConfigParam.user);  
        cpds.setPassword(DbConfigParam.passwd);  
        cpds.setInitialPoolSize(DbConfigParam.initialSize);  
        cpds.setMinPoolSize(DbConfigParam.minPoolSize);  
        cpds.setMaxPoolSize(DbConfigParam.maxPoolSize);  
        cpds.setMaxIdleTime(DbConfigParam.maxIdleTime);  
        cpds.setAcquireRetryAttempts(DbConfigParam.retryAttempts);  
        cpds.setAcquireIncrement(DbConfigParam.acquireIncrement);  
        cpds.setTestConnectionOnCheckin(false);  
        cpds.setTestConnectionOnCheckout(false);  
        return cpds;  
    }  
  
    /** 
     * 获取Druid数据源 
     *  
     */  
    public static DruidDataSource getDruidDataSource() {  
        DruidDataSource dds = new DruidDataSource();  
        dds.setUsername(DbConfigParam.user);  
        dds.setUrl(DbConfigParam.jdbcUrl);  
        dds.setPassword(DbConfigParam.passwd);  
        dds.setDriverClassName(DbConfigParam.driver);  
        dds.setInitialSize(DbConfigParam.initialSize);  
        dds.setMaxActive(DbConfigParam.maxPoolSize);  
        dds.setMaxWait(DbConfigParam.maxIdleTime);  
        dds.setTestWhileIdle(false);  
        dds.setTestOnReturn(false);  
        dds.setTestOnBorrow(false);  
        return dds;  
    }  
//  
//    /** 
//     * 获取Proxool数据源 
//     *  
//     */  
//    public static ProxoolDataSource getProxoolDataSource() {  
//        ProxoolDataSource pds = new ProxoolDataSource();  
//        pds.setAlias("mysql");  
//        pds.setUser(DbConfigParam.user);  
//        pds.setPassword(DbConfigParam.passwd);  
//        pds.setDriverUrl(DbConfigParam.jdbcUrl);  
//        pds.setDriver(DbConfigParam.driver);  
//        pds.setMaximumActiveTime(DbConfigParam.maxIdleTime);  
//        pds.setMaximumConnectionCount(DbConfigParam.maxPoolSize);  
//        pds.setMinimumConnectionCount(DbConfigParam.initialSize);  
//        pds.setPrototypeCount(DbConfigParam.minPoolSize);  
//        pds.setTestBeforeUse(false);  
//        pds.setTestAfterUse(false);  
//        return pds;  
//    }  
//  
    /** 
     * 获取Apache tomcat jdbc pool数据源 
     *  
     */  
    public static DataSource getTomcatDataSource() {  
        DataSource ds = new DataSource();  
        ds.setUrl(DbConfigParam.jdbcUrl);  
        ds.setUsername(DbConfigParam.user);  
        ds.setPassword(DbConfigParam.passwd);  
        ds.setDriverClassName(DbConfigParam.driver);  
        ds.setInitialSize(DbConfigParam.initialSize);  
        ds.setMaxIdle(DbConfigParam.minPoolSize);  
        ds.setMaxActive(DbConfigParam.maxPoolSize);  
        ds.setTestWhileIdle(false);  
        ds.setTestOnBorrow(false);  
        ds.setTestOnConnect(false);  
        ds.setTestOnReturn(false);  
        return ds;  
    }  
	

    
}
