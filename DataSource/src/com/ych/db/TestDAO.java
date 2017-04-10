package com.ych.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDAO {

	public void query(Connection conn) throws SQLException {  
        if (conn != null) {  
            Statement statement = conn.createStatement();  
            ResultSet rs = statement.executeQuery("select * from student limit 1,5");  
            while (rs.next()) {  
                String username = rs.getString(2);  
                //System.out.println(username);  
            }  
            rs.close();  
            statement.close();  
            conn.close();  
        }  
    }  
	
}
