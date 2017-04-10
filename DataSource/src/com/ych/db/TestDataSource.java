package com.ych.db;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.ych.inter.ITestDataSource;

public class TestDataSource implements ITestDataSource {

	  /** 
     * DS���� 
     *  
     * @param testDAO 
     * @param ds 
     * @param count 
     * @throws SQLException 
     */  
    public long queryDs(TestDAO testDAO, DataSource ds, int count) throws SQLException {  
        // [Ԥ�����ݿ����ӳ�] ��ѯ10���Գ�ʼ�����ӳ�  
        for (int i = 0; i < 50; i++) {  
            testDAO.query(ds.getConnection());  
        }  
        // ��ʼʱ��  
        long startMillis = System.currentTimeMillis();  
        // ѭ����ѯ  
        for (int i = 0; i < count; i++) {  
            testDAO.query(ds.getConnection());  
        }  
        // ����ʱ��  
        long endMillis = System.currentTimeMillis();  
        // �������ʱ��  
        long runMillis = endMillis - startMillis;  
        return runMillis;  
    }  
	
}
