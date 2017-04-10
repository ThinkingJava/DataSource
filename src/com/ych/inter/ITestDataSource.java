package com.ych.inter;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.ych.db.TestDAO;

public interface ITestDataSource {

	/** 
     * Êý¾ÝÔ´²âÊÔ 
     *  
     * @param testDAO 
     * @param ds 
     * @param count 
     * @throws SQLException 
     * @throws InterruptedException 
     */  
    public long queryDs(TestDAO testDAO, DataSource ds, int count) throws SQLException, InterruptedException;  

	
}
