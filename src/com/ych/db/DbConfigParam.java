package com.ych.db;

public class DbConfigParam {

    // ���ݿ���������  
    public static String driver = "com.mysql.jdbc.Driver";  
    // ���ݿ����ӵ�ַ  
    public static String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/studb?useUnicode=true&characterEncoding=UTF8&connectTimeout=1000&socketTimeout=3000";  
    // ���ݿ��û���  
    public static String user = "root";  
    // ���ݿ�����  
    public static String passwd = "root";  
    // ���ӳس�ʼ����С  
    public static int initialSize = 5;  
    // ���ӳ���С����  
    public static int minPoolSize = 10;  
    // ���ӳ������������  
    public static int maxPoolSize = 50;  
    // ��С���ʱ�䣬100��  
    public static int maxIdleTime = 100000;  
    // ����ʧ�����Դ���  
    public static int retryAttempts = 10;  
    // �����ӳ����Ӻľ�ʱ��ȡ������  
    public static int acquireIncrement = 5;  
  
    private DbConfigParam() {  
    }  
	
}
