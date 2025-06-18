package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import dto.AllDto;



public class ReportDAO {
	
	// 引数report指定された項目で検索して、取得されたデータのリストを返す
	public List<AllDto> select(AllDto report) {
		Connection conn = null;
		List<AllDto> cardList = new ArrayList<AllDto>();
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			
			String sql = "SELECT reportId, food, walk, state, taraining, reportMemo, reportDate, dogId "
					+ "FROM AllDto WHERE  LIKE ? AND department LIKE ? AND name LIKE ? AND position LIKE ? ORDER BY number ASC";
			PreparedStatement pStmt = conn.prepareStatement(sql);

		}
	
		public boolean insert(AllDto report) {
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
				String sql = "INSERT INTO Report VALUES (0, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を完成させる
					pStmt.setBoolean(1, report.getFood());
					pStmt.setInt(2, report.getWalk());
					pStmt.setBoolean(3, report.getState());
					pStmt.setString(4, report.getTraining());
					pStmt.setString(5, report.getReportMemo());
					pStmt.setDate(6, report.getReportDate());
					pStmt.setInt(7, report.getDogId());
	
}
