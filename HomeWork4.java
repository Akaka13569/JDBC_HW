//2.在res/emp.txt文字檔中設定五筆employee的資料，將之批次新增至資料庫中。
//註：一筆資料一列，每個資料欄的資料以逗號(,)隔開


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
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//註冊驅動程式
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=jdbc";//URL連接SQL資料庫
			conn = DriverManager.getConnection(connUrl, "sa", "passw0rd");//輸入SQL的帳號密碼

			String qryStmt = "SELECT empno,salary FROM employee;";//SQL指令查詢empno,salary
			PreparedStatement pstmt = conn.prepareStatement(qryStmt);//用PreparedStatement動態執行SQL指令
			ResultSet rs = pstmt.executeQuery();//回傳查詢結果

			String updateStmt = "UPDATE employee SET salary = ? WHERE empno = ?";
			// ※PreparedStatement pstmt1 = conn.prepareStatement(updstmt);此行不可這樣寫 要寫這樣↓
			pstmt = conn.prepareStatement(updateStmt);

			while (rs.next()) {//迴圈查詢下一格
				pstmt.setDouble(1, rs.getDouble("salary") + 1);//查詢到薪水salary+1
				pstmt.setInt(2, rs.getInt("empno"));//查詢到要給誰的薪水+1
				pstmt.addBatch();//把執行程式變成list物件
				i++;
				if (i % batch == 0) {//如果 i 除以 batch 等於 0 就往下跑
					pstmt.executeBatch();//一次執行addBatch() 累積的list物件 也就是3筆資料
					continue;
				}
//				pstmt.executeBatch();//看是否要把list物件內剩下的兩筆資料塞進去
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
