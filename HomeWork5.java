//3.�ϥνҰ�employee���[�H��g�A�s�W�@�����i�H�s����u���Ӥ��C
//�Q��BlobDemo.java�d�ұN�Ҧ����ɥH�妸�覡�s�W�ܸ�Ʈw���C
//���G�Ҧ��Ӥ��m��res��Ƨ����A���F��K���g�{���A��ĳ�Ҧ��Ӥ������ɦW�n�@�P�A���ɦW�٥H���u�s���R�W


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
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//���U�X�ʵ{��
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=jdbc";//URL�s��SQL��Ʈw
			conn = DriverManager.getConnection(connUrl, "sa", "passw0rd");//��JSQL���b���K�X

//			String qryStmt = "SELECT * FROM employee;";//SQL���O�d�ߥ���
//			PreparedStatement pstmt = conn.prepareStatement(qryStmt);//��PreparedStatement�ʺA����SQL���O
//			ResultSet rs = pstmt.executeQuery();//�^�Ǭd�ߵ��G

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
