package com.charlielin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class OutputEmp2 {
	public static void main(String[] args) throws IOException {
		
		File f1 = new File("D:\\JDBC\\workspace\\advanced\\res","output1.txt");//宣告F1變數並創建output1.txt文件
		FileWriter fw = new FileWriter(f1);//用FileWriter類別 f1當參數的FileWriter()建構子 new一個fw物件 
		
		Connection conn = null;
		try {     
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=jdbc";
			conn = DriverManager.getConnection(connUrl, "sa", "passw0rd");
			
			String qryStmt = "SELECT * FROM employee";
			PreparedStatement stmt = conn.prepareStatement(qryStmt);
			ResultSet rs = stmt.executeQuery();
			
			
			StringBuffer sb1= new StringBuffer();//用StringBuffer類別 StringBuffer()建構子 new一個sb1物件 
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			
			for(int i = 1; i <= count; i++) {
				System.out.print(rsmd.getColumnLabel(i) + rsmd.getColumnType(i) + ",");
				
				sb1.append(rsmd.getColumnLabel(i) + rsmd.getColumnType(i) + ",");//利用StringBuffer的append方法把字串新增進去
				
			} 
			
			fw.write(sb1.toString() + "\r\n" );//把sb1轉成String寫入第一行資料
			
			System.out.print("\n");
			
			sb1 = new StringBuffer();//把sb1洗成空白
			
			while(rs.next()) {
	     		for(int i = 1; i <= count; i++) {
	         		System.out.print(rs.getString(i) + ",");
	         		
	     		    sb1.append(rs.getString(i) + ",");}
	     			
	     		System.out.print("\n");
	     		
	     		fw.write(sb1.toString() + "\r\n" );//把sb1轉成String寫入資料
	     		sb1 = new StringBuffer();//把sb1洗成空白
			}
			rs.close();
			stmt.close();
			
			fw.close();//關閉FileWriter才會使它寫出資料到文件
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
		}
	}// end of main()
}// end of class ResultSetMetaDataDemo 
