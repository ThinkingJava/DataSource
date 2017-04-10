package com.ych.db;

public class DbConfigParam {

    // 数据库驱动名称  
    public static String driver = "com.mysql.jdbc.Driver";  
    // 数据库连接地址  
    public static String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/studb?useUnicode=true&characterEncoding=UTF8&connectTimeout=1000&socketTimeout=3000";  
    // 数据库用户名  
    public static String user = "root";  
    // 数据库密码  
    public static String passwd = "root";  
    // 连接池初始化大小  
    public static int initialSize = 5;  
    // 连接池最小空闲  
    public static int minPoolSize = 10;  
    // 连接池最大连接数量  
    public static int maxPoolSize = 50;  
    // 最小逐出时间，100秒  
    public static int maxIdleTime = 100000;  
    // 连接失败重试次数  
    public static int retryAttempts = 10;  
    // 当连接池连接耗尽时获取连接数  
    public static int acquireIncrement = 5;  
  
    private DbConfigParam() {  
    }  
	
}
