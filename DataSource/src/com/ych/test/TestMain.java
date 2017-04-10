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
     * ���Է�ʽ�� ÿ������Դ������Ϣ������ͬ������������׼ȷ 
     * ÿ������Դ��10�Ρ�100�Ρ�500�Ρ�1000�Ρ�2000�Ρ�4000�Ρ�8000�β�ѯ���� ÿ�ֲ�ѯ�ظ�100�Σ��鿴100��ִ��ʱ��Ĳ���ͼ 
     *  
     * @param args 
     * @throws IOException 
     * @throws SQLException 
     * @throws InterruptedException 
     */  
    public static void main(String[] args) throws IOException, SQLException, InterruptedException {  
        // ׼������Դ  
        TestDAO testDAO = new TestDAO();  
        ITestDataSource testDataSource = null;  
        {  
            System.out.println("===========���߳�ִ��===============");  
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
				 {// Druid����Դ  
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
				  {// Tomcat Jdbc Pool����Դ  
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
        // ��ѯ����  
        int count = 500;  
        long c3p0Millis = 0l, druidMillis = 0l, proxoolMillis = 0l, tomcatMillis = 0l;  
//        {// Proxool����Դ  
//            DataSource dataSource = AllDataSource.getProxoolDataSource();  
//            proxoolMillis = testDataSource(testDataSource, "Proxool", dataSource, testDAO, count);  
//            dataSource = null;  
//        }  
        
        {// Druid����Դ  
            DataSource dataSource = AllDataSource.getDruidDataSource();  
            druidMillis = testDataSource(testDataSource, "Druid", dataSource, testDAO, count);  
            dataSource = null;  
        }  
        
        {// Tomcat Jdbc Pool����Դ  
            DataSource dataSource = AllDataSource.getTomcatDataSource();  
            tomcatMillis = testDataSource(testDataSource, "Tomcat Jdbc Pool", dataSource, testDAO, count);  
            dataSource = null;  
        }  
        
        {  
            // c3p0����Դ  
            DataSource dataSource = AllDataSource.getC3p0DataSource();  
            c3p0Millis = testDataSource(testDataSource, "C3P0", dataSource, testDAO, count);  
            dataSource = null;  
        }  


  
        System.out.println("ͳ�ƽ����[C3P0]ƽ����ʱ" + c3p0Millis + "ms");  
        System.out.println("ͳ�ƽ����[Druid]ƽ����ʱ" + druidMillis + "ms");  
//        System.out.println("ͳ�ƽ����[Proxool]ƽ����ʱ" + proxoolMillis + "ms");  
        System.out.println("ͳ�ƽ����[Tomcat]ƽ����ʱ" + tomcatMillis + "ms");  
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
        System.out.println("��ѯ����Ϊ��" + count);  
        System.out.println("==========================" + dataSourceName + " ���Կ�ʼ==========================");  
        // ����c3p0  
        long[] runMillis = new long[100];  
        for (int i = runMillis.length - 1; i >= 0; i--) {  
            runMillis[i] = testDataSource.queryDs(testDAO, c3p0DataSource, count);  
            System.out.print("|" + i + "=" + runMillis[i] + "ms");  
            if (i % 10 == 0) {  
                System.out.println();  
            }  
        }  
        // ��ʼͳ��ƽ��ֵ  
        long avgMillis = 0l;  
        for (int i = runMillis.length - 1; i >= 0; i--) {  
            avgMillis += runMillis[i];  
        }  
        avgMillis = avgMillis / runMillis.length;  
        System.out.println();  
        System.out.println("==========================" + dataSourceName + " ���Խ���==========================");  
        System.out.println("ƽ����ʱ��" + avgMillis + "ms");  
        return avgMillis;  
    }  
  
}  