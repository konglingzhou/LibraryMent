package com.cqeec.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Administrator
 * @descrition数据库连接公共类
 */
public class DBUTIL {
	static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Libraryment";
	static String user = "sa";
	static String pwd = "";
	private static Connection con = null;//单例模式
	
	
	/**
	 * 
	 * @return
	 * @descrition 获取数据库连接
	 */
	public static Connection getConn() {
		if(con == null) {
			try {
				//加载驱动
				Class.forName(driver);
				con = DriverManager.getConnection(url, user, pwd);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return con;
	}
}
