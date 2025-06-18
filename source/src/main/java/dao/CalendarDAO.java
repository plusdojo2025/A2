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
	
	// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
		//更新のupdateメソッド
		public boolean update(AllDto card) {
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
				String sql = "UPDATE tbl_Bc SET company=?, department=?, position=?, name=?,"
						+ "ruby=?, zipcode=?, address=?, phone=?, fax=?, email=?, url=?, remarks=?  "
						+ "WHERE number=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (card.getCompany() != null) {
					pStmt.setString(1, card.getCompany());
				} else {
					pStmt.setString(1, "");
				}
				if (card.getDepartment() != null) {
					pStmt.setString(2, card.getDepartment());
				} else {
					pStmt.setString(2, "");
				}
				if (card.getPosition() != null) {
					pStmt.setString(3, card.getPosition());
				} else {
					pStmt.setString(3, "");
				}
				if (card.getName() != null) {
					pStmt.setString(4, card.getName());
				} else {
					pStmt.setString(4, "");
				}
				if (card.getRuby() != null) {
					pStmt.setString(5, card.getRuby());
				} else {
					pStmt.setString(5, "");
				}
				if (card.getZipcode() != null) {
					pStmt.setString(6, card.getZipcode());
				} else {
					pStmt.setString(6, "");
				}
				if (card.getAddress() != null) {
					pStmt.setString(7, card.getAddress());
				} else {
					pStmt.setString(7, "");
				}
				if (card.getPhone() != null) {
					pStmt.setString(8, card.getPhone());
				} else {
					pStmt.setString(8, "");
				}
				if (card.getFax() != null) {
					pStmt.setString(9, card.getFax());
				} else {
					pStmt.setString(9, "");
				}
				if (card.getEmail() != null) {
					pStmt.setString(10, card.getEmail());
				} else {
					pStmt.setString(10, "");
				}
				//if (card.getDate() != null) {
				//	pStmt.setString(11, card.getDate());
				//} else {
				//	pStmt.setString(11, "");
				//}
				if (card.getUrl() != null) {
					pStmt.setString(11, card.getUrl());
				} else {
					pStmt.setString(11, "");
				}
				if (card.getRemarks() != null) {
					pStmt.setString(12, card.getRemarks());
				} else {
					pStmt.setString(12, "");
				}
				pStmt.setInt(13, card.getNumber());

				
				// SQL文を実行する
				//何件かが帰ってくる（ここにＳeｌｅｃｔは使えない）
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

