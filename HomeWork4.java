//2.�bres/emp.txt��r�ɤ��]�w����employee����ơA�N���妸�s�W�ܸ�Ʈw���C
//���G�@����Ƥ@�C�A�C�Ӹ���檺��ƥH�r��(,)�j�}


package com.charlielin;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeWork4 {

	public static void main(String[] args) {

		int batch = 3;
		int i = 0;
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//���U�X�ʵ{��
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=jdbc";//URL�s��SQL��Ʈw
			conn = DriverManager.getConnection(connUrl, "sa", "passw0rd");//��JSQL���b���K�X

			String qryStmt = "SELECT empno,salary FROM employee;";//SQL���O�d��empno,salary
			PreparedStatement pstmt = conn.prepareStatement(qryStmt);//��PreparedStatement�ʺA����SQL���O
			ResultSet rs = pstmt.executeQuery();//�^�Ǭd�ߵ��G

			String updateStmt = "UPDATE employee SET salary = ? WHERE empno = ?";
			// ��PreparedStatement pstmt1 = conn.prepareStatement(updstmt);���椣�i�o�˼g �n�g�o�ˡ�
			pstmt = conn.prepareStatement(updateStmt);

			while (rs.next()) {//�j��d�ߤU�@��
				pstmt.setDouble(1, rs.getDouble("salary") + 1);//�d�ߨ��~��salary+1
				pstmt.setInt(2, rs.getInt("empno"));//�d�ߨ�n���֪��~��+1
				pstmt.addBatch();//�����{���ܦ�list����
				i++;
				if (i % batch == 0) {//�p�G i ���H batch ���� 0 �N���U�]
					pstmt.executeBatch();//�@������addBatch() �ֿn��list���� �]�N�O3�����
					continue;
				}
//				pstmt.executeBatch();//�ݬO�_�n��list���󤺳ѤU���ⵧ��ƶ�i�h
			}

			qryStmt = "SELECT ename, salary FROM employee";
			pstmt = conn.prepareStatement(qryStmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.print("name = " + rs.getString("ename") + ", ");
				System.out.println("salary = " + rs.getDouble("salary"));
			}
			rs.close();
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
