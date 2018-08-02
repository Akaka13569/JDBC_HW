//3.使用課堂的employee表格加以改寫，新增一個欄位可以存放員工的照片。
//利用BlobDemo.java範例將所有圖檔以批次方式新增至資料庫中。
//註：所有照片置於res資料夾中，為了方便撰寫程式，建議所有照片的副檔名要一致，圖檔名稱以員工編號命名


package com.charlielin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeWork5 {

	public static void main(String[] args) throws IOException {
//		File infile = new File("D:\\JDBC\\workspace\\Jdbc_HW\\res","emp.txt");
//		FileReader fr = new FileReader(infile);
//		BufferedReader br = new BufferedReader(fr);
//		String line = null;
//		while((line = br.readLine()) != null) {
//			System.out.println(line);
//		}
		
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//註冊驅動程式
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=jdbc";//URL連接SQL資料庫
			conn = DriverManager.getConnection(connUrl, "sa", "passw0rd");//輸入SQL的帳號密碼

//			String qryStmt = "SELECT * FROM employee;";//SQL指令查詢全部
//			PreparedStatement pstmt = conn.prepareStatement(qryStmt);//用PreparedStatement動態執行SQL指令
//			ResultSet rs = pstmt.executeQuery();//回傳查詢結果

			String updateStmt = "insert into employee values (?, ?, ?, ?, ?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(updateStmt);
			File infile = new File("D:\\JDBC\\workspace\\Jdbc_HW\\res","emp.txt");
			FileReader fr = new FileReader(infile);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			
			while((line = br.readLine()) != null) {
				String[] str1 = line.split(",");
				pstmt.setString(1, str1[0]);
				pstmt.setString(2, str1[1]);
				pstmt.setString(3, str1[2]);
				pstmt.setString(4, str1[3]);
				pstmt.setString(5, str1[4]);
				pstmt.setString(6, str1[5]);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			pstmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}
}
