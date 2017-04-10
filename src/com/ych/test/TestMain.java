package com.ych.test;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.ych.db.AllDataSource;
import com.ych.db.TestDAO;
import com.ych.db.TestDataSource;
import com.ych.inter.ITestDataSource;

public class TestMain {

	  /** 
     * 测试方式： 每种数据源配置信息尽量相同，以求结果更加准确 
     * 每种数据源做10次、100次、500次、1000次、2000次、4000次、8000次查询操作 每种查询重复100次，查看100次执行时间的波动图 
     *  
     * @param args 
     * @throws IOException 
     * @throws SQLException 
     * @throws InterruptedException 
     */  
    public static void main(String[] args) throws IOException, SQLException, InterruptedException {  
        // 准备数据源  
        TestDAO testDAO = new TestDAO();  
        ITestDataSource testDataSource = null;  
        {  
            System.out.println("===========单线程执行===============");  
            testDataSource = new TestDataSource();  
//            batchTestRunMillis(testDAO, testDataSource);  
            testThread(testDAO, testDataSource);
        }  
    }  
  
    long c3p0Millis = 0l;
	static long druidMillis = 0l;
	long proxoolMillis = 0l;
	static long tomcatMillis = 0l; 
    
    private static void testThread(final TestDAO testDAO, final ITestDataSource testDataSource){
    	  final int count = 50;  
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				 {// Druid数据源  
			            DataSource dataSource = AllDataSource.getDruidDataSource();  
			            try {
							druidMillis = testDataSource(testDataSource, "Druid", dataSource, testDAO, count);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
			            dataSource = null;  
			        }  
			}
		}).start();
    	
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				  {// Tomcat Jdbc Pool数据源  
			            DataSource dataSource = AllDataSource.getTomcatDataSource();  
			            try {
							tomcatMillis = testDataSource(testDataSource, "Tomcat Jdbc Pool", dataSource, testDAO, count);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
			            dataSource = null;  
			        }  
			}
		}).start();
    	

    }
    
    
    
    /** 
     * @param testDAO 
     * @param testDataSource 
     * @throws SQLException 
     * @throws InterruptedException 
     */  
    private static void batchTestRunMillis(TestDAO testDAO, ITestDataSource testDataSource) throws SQLException,  
            InterruptedException {  
        // testDataSource = new TestDataSourceByThread();  
        // 查询次数  
        int count = 500;  
        long c3p0Millis = 0l, druidMillis = 0l, proxoolMillis = 0l, tomcatMillis = 0l;  
//        {// Proxool数据源  
//            DataSource dataSource = AllDataSource.getProxoolDataSource();  
//            proxoolMillis = testDataSource(testDataSource, "Proxool", dataSource, testDAO, count);  
//            dataSource = null;  
//        }  
        
        {// Druid数据源  
            DataSource dataSource = AllDataSource.getDruidDataSource();  
            druidMillis = testDataSource(testDataSource, "Druid", dataSource, testDAO, count);  
            dataSource = null;  
        }  
        
        {// Tomcat Jdbc Pool数据源  
            DataSource dataSource = AllDataSource.getTomcatDataSource();  
            tomcatMillis = testDataSource(testDataSource, "Tomcat Jdbc Pool", dataSource, testDAO, count);  
            dataSource = null;  
        }  
        
        {  
            // c3p0数据源  
            DataSource dataSource = AllDataSource.getC3p0DataSource();  
            c3p0Millis = testDataSource(testDataSource, "C3P0", dataSource, testDAO, count);  
            dataSource = null;  
        }  


  
        System.out.println("统计结果：[C3P0]平均耗时" + c3p0Millis + "ms");  
        System.out.println("统计结果：[Druid]平均耗时" + druidMillis + "ms");  
//        System.out.println("统计结果：[Proxool]平均耗时" + proxoolMillis + "ms");  
        System.out.println("统计结果：[Tomcat]平均耗时" + tomcatMillis + "ms");  
    }  
  
    /** 
     * @param c3p0DataSource 
     * @param testDAO 
     * @param count 
     * @throws SQLException 
     * @throws InterruptedException 
     */  
    private static long testDataSource(ITestDataSource testDataSource, String dataSourceName,  
            DataSource c3p0DataSource, TestDAO testDAO, int count) throws SQLException, InterruptedException {  
        System.out.println();  
        System.out.println("查询次数为：" + count);  
        System.out.println("==========================" + dataSourceName + " 测试开始==========================");  
        // 测试c3p0  
        long[] runMillis = new long[100];  
        for (int i = runMillis.length - 1; i >= 0; i--) {  
            runMillis[i] = testDataSource.queryDs(testDAO, c3p0DataSource, count);  
            System.out.print("|" + i + "=" + runMillis[i] + "ms");  
            if (i % 10 == 0) {  
                System.out.println();  
            }  
        }  
        // 开始统计平均值  
        long avgMillis = 0l;  
        for (int i = runMillis.length - 1; i >= 0; i--) {  
            avgMillis += runMillis[i];  
        }  
        avgMillis = avgMillis / runMillis.length;  
        System.out.println();  
        System.out.println("==========================" + dataSourceName + " 测试结束==========================");  
        System.out.println("平均耗时：" + avgMillis + "ms");  
        return avgMillis;  
    }  
  
}  