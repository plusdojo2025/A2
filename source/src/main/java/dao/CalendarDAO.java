package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.AllDto;


public class CalendarDAO {
	
	// 引数card指定された項目で検索して、取得されたデータのリストを返す
		//検索メソッド
		public List<AllDto> select(AllDto seleCalendar) {
			//どこのデータベースにつなぐかを入れるConn
			Connection conn = null;
			List<AllDto> calendar = new ArrayList<AllDto>();

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する ([webapp2] [root] [password]がいじるところ)
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql ="""
						SELECT 	
							calendarId, 
							calendarDate, 
							title, 
							time, 
							calendarMemo, 
							calendarDogId, 
						FROM AllDate
						
						WHERE calendarId LIKE ? 
						OR calendarDate LIKE ? 
						OR title LIKE ? 
						OR time LIKE ?
						OR calendarMemo LIKE ? 
						OR calendarDogId LIKE ? 
						
						ORDER BY calendarId
						""";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる　//1と指定したところに一個目のはてながつながる。
				if (seleCalendar.getCalendarId() != "") {
					pStmt.setInt(1, "%" + seleCalendar.getCalendarId() + "%");
					pStmt.setString(2, "%" + seleCalendar.getCalendarDate() + "%");
					pStmt.setString(3, "%" + seleCalendar.getTitle() + "%");
					pStmt.setString(4, "%" + seleCalendar.getTime() + "%");
					pStmt.setString(5, "%" + seleCalendar.getCalendarMemo() + "%");
					pStmt.setString(6, "%" + seleCalendar.getCalendarDogId() + "%");
					
				} else {
					pStmt.setString(1, "%");
				}

				// SQL文を実行し、結果表を取得する 
				//ResultSet型は何でも入れることができる。DAOの中じゃないと使えない。
				//コネクションと密接な関係があり、コネクションが消えたときに消えちゃう。
				//なのでArrayListに入れ替えておく必要がある
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする　//rs.next()は先生の板書を確認
					//DTOを作り出している。ｒｓ.getStringで取ってきたものを、えだまめの皮に入れてセットを作っている。
				while (rs.next()) {
					Bc bc = new Bc(	rs.getInt("number"),
									rs.getString("company"), 
									rs.getString("department"),
									rs.getString("position"),  
									rs.getString("name"), 
									rs.getString("ruby"), 
									rs.getString("zipcode"), 
									rs.getString("address"), 
									rs.getString("phone"), 
									rs.getString("fax"), 
									rs.getString("email"), 
									//rs.getString("date"),
									rs.getString("url"),
									rs.getString("remarks"));
					//addでcardListにbcを入れている　（cardListはArrayList)
					cardList.add(bc);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				cardList = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				cardList = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						cardList = null;
					}
				}
			}

			// 結果を返す
			return cardList;
		}
	
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
			String sql = " INSERT INTO AllDto VALUES (0, ?, ?, ?, ?, ?) ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
					
			//pStmt.setInt(1, regiCalendar.getCalendarId());
			pStmt.setTimestamp(1, java.sql.Timestamp.valueOf(regiCalendar.getCalendarDate()));
			pStmt.setString(2, regiCalendar.getTitle());
			pStmt.setTime(3, java.sql.Time.valueOf(regiCalendar.getTime()));
			pStmt.setInt(4, regiCalendar.getCalendarMemo());
			pStmt.setInt(5, regiCalendar.getCalendarDogId());
			
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
		public boolean update(AllDto upCalendar) {
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
				String sql = "UPDATE AllDto SET calendarDate=?, title=?, time=?, calendarMemo=?,"
						+ "calendarDogId=? "
						+ "WHERE calendarId=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を完成させる
				pStmt.setTimestamp(1, java.sql.Timestamp.valueOf(upCalendar.getCalendarDate()));
				pStmt.setString(2, upCalendar.getTitle());
				pStmt.setTime(3, java.sql.Time.valueOf(upCalendar.getTime()));
				pStmt.setInt(4, upCalendar.getCalendarMemo());
				pStmt.setInt(5, upCalendar.getCalendarDogId());
				
				
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

