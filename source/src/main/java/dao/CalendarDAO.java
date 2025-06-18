package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.AllDto;


public class CalendarDAO {
	//登録メソッド
	public boolean insert(AllDto regiCalendar) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = " INSERT INTO CALENDAR VALUES (0, ?, ?, ?, ?, ?) ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
					
			pStmt.setInt(1, regiCalendar.getCalendarId());
			pStmt.setDate(2, java.sql.Date.valueOf(regiCalendar.getCalendarDate()));
			pStmt.setString(3, regiCalendar.getTitle());
			pStmt.setTime(4, java.sql.Time.valueOf(regiCalendar.getTime()));
			pStmt.setInt(5, regiCalendar.getCalendarMemo());
			pStmt.setInt(6, regiCalendar.getCalendarDogId());
			

			
//
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	

		// 結果を返す
		return result;
	}
}
