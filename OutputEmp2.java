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
		
		File f1 = new File("D:\\JDBC\\workspace\\advanced\\res","output1.txt");//�ŧiF1�ܼƨóЫ�output1.txt���
		FileWriter fw = new FileWriter(f1);//��FileWriter���O f1��Ѽƪ�FileWriter()�غc�l new�@��fw���� 
		
		Connection conn = null;
		try {     
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=jdbc";
			conn = DriverManager.getConnection(connUrl, "sa", "passw0rd");
			
			String qryStmt = "SELECT * FROM employee";
			PreparedStatement stmt = conn.prepareStatement(qryStmt);
			ResultSet rs = stmt.executeQuery();
			
			
			StringBuffer sb1= new StringBuffer();//��StringBuffer���O StringBuffer()�غc�l new�@��sb1���� 
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			
			for(int i = 1; i <= count; i++) {
				System.out.print(rsmd.getColumnLabel(i) + rsmd.getColumnType(i) + ",");
				
				sb1.append(rsmd.getColumnLabel(i) + rsmd.getColumnType(i) + ",");//�Q��StringBuffer��append��k��r��s�W�i�h
				
			} 
			
			fw.write(sb1.toString() + "\r\n" );//��sb1�নString�g�J�Ĥ@����
			
			System.out.print("\n");
			
			sb1 = new StringBuffer();//��sb1�~���ť�
			
			while(rs.next()) {
	     		for(int i = 1; i <= count; i++) {
	         		System.out.print(rs.getString(i) + ",");
	         		
	     		    sb1.append(rs.getString(i) + ",");}
	     			
	     		System.out.print("\n");
	     		
	     		fw.write(sb1.toString() + "\r\n" );//��sb1�নString�g�J���
	     		sb1 = new StringBuffer();//��sb1�~���ť�
			}
			rs.close();
			stmt.close();
			
			fw.close();//����FileWriter�~�|�ϥ��g�X��ƨ���
			
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
