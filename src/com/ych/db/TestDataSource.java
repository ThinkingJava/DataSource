package com.ych.db;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.ych.inter.ITestDataSource;

public class TestDataSource implements ITestDataSource {

	  /** 
     * DS测试 
     *  
     * @param testDAO 
     * @param ds 
     * @param count 
     * @throws SQLException 
     */  
    public long queryDs(TestDAO testDAO, DataSource ds, int count) throws SQLException {  
        // [预热数据库连接池] 查询10次以初始化连接池  
        for (int i = 0; i < 50; i++) {  
            testDAO.query(ds.getConnection());  
        }  
        // 开始时间  
        long startMillis = System.currentTimeMillis();  
        // 循环查询  
        for (int i = 0; i < count; i++) {  
            testDAO.query(ds.getConnection());  
        }  
        // 结束时间  
        long endMillis = System.currentTimeMillis();  
        // 输出结束时间  
        long runMillis = endMillis - startMillis;  
        return runMillis;  
    }  
	
}
