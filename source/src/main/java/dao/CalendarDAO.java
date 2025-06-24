package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

				// SQL文を完成させる　//検索はないのでここいらない？
				if (seleCalendar.getCalendarId() != 0) {
					pStmt.setString(1, "%" + seleCalendar.getCalendarId() + "%");
					pStmt.setString(2, "%" + seleCalendar.getCalendarDate() + "%");
					pStmt.setString(3, "%" + seleCalendar.getTitle() + "%");
					pStmt.setString(4, "%" + seleCalendar.getTime() + "%");
					pStmt.setString(5, "%" + seleCalendar.getCalendarMemo() + "%");
					pStmt.setString(6, "%" + seleCalendar.getCalendarDogId() + "%");
					
				} else {
					pStmt.setString(1, "%");
					pStmt.setNull(2, java.sql.Types.DATE);
					pStmt.setString(3, "%");
					pStmt.setString(4, "%");
					pStmt.setString(5, "%");
					pStmt.setString(6, "%");
				}

				// SQL文を実行し、結果表を取得する 
				//ResultSet型は何でも入れることができる。DAOの中じゃないと使えない。
				//コネクションと密接な関係があり、コネクションが消えたときに消えちゃう。
				//なのでArrayListに入れ替えておく必要がある
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする　//rs.next()は先生の板書を確認
					//DTOを作り出している。ｒｓ.getStringで取ってきたものを、えだまめの皮に入れてセットを作っている。
				while (rs.next()) {
					AllDto dto=new AllDto();
					dto.setCalendarId(rs.getInt("calendarId"));
					dto.setCalendarDate(rs.getDate("calendarDate").toLocalDate());
					dto.setTitle(rs.getString("title"));
					dto.setTime(rs.getTime("time").toLocalTime());
					dto.setCalendarMemo(rs.getString("calendarMemo"));
					dto.setCalendarDogId(rs.getInt("calendarDogId"));
					
					//addでcardListにbcを入れている　（cardListはArrayList)
					calendar.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				calendar = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				calendar = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						calendar = null;
					}
				}
			}

			// 結果を返す
			return calendar;
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
			String sql = " INSERT INTO CALENDAR VALUES (0, ?, ?, ?, ?, ?) ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
					
			//pStmt.setInt(1, regiCalendar.getCalendarId());
			pStmt.setDate(1, java.sql.Date.valueOf(regiCalendar.getCalendarDate()));
			pStmt.setString(2, regiCalendar.getTitle());
			pStmt.setTime(3, java.sql.Time.valueOf(regiCalendar.getTime()));
			pStmt.setString(4, regiCalendar.getCalendarMemo());
			pStmt.setInt(5, regiCalendar.getCalendarDogId());
			System.out.println(regiCalendar.getCalendarDogId()+"aaaaaaaa");
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
				String sql = "UPDATE CALENDAR SET calendarDate=?, title=?, time=?, calendarMemo=?,"
						+ "calendarDogId=? "
						+ "WHERE calendarId=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を完成させる
				pStmt.setDate(1, java.sql.Date.valueOf(upCalendar.getCalendarDate()));
				pStmt.setString(2, upCalendar.getTitle());
				pStmt.setTime(3, java.sql.Time.valueOf(upCalendar.getTime()));
				pStmt.setString(4, upCalendar.getCalendarMemo());
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
		
		
//削除のメソッド
		public boolean delete(AllDto delecalendar) {
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
				String sql = "DELETE FROM CALENDAR WHERE calendarId=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setInt(1, delecalendar.getCalendarId());

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
		
		
		//schedule_regiの一覧表示
				public List<AllDto> selectAll(LocalDate date, int schoolId){
					//どこのデータベースにつなぐかを入れるConn
					Connection conn = null;
					List<AllDto> resultList = new ArrayList<AllDto>();

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
									calendarDate, 
									WANKO.wankoDogId,
									calendarId,
									title,
									time,
									calendarMemo,
									calendarDogId,
									userUniqueId,
									userSchoolId
								FROM CALENDAR
								JOIN WANKO
								ON CALENDAR.calendarDogId=WANKO.wankoDogId
								JOIN USER
								ON WANKO.wankoNameId=USER.userNameId
								
								WHERE calendarDate = ? 
								AND userSchoolId = ?
								
								ORDER BY calendarId
								""";
						System.out.println(sql);
						PreparedStatement pStmt = conn.prepareStatement(sql);

						// SQL文を完成させる　
							System.out.println(date+"渡ってきたdateだよ");
							pStmt.setObject(1, date);
							pStmt.setInt(2, schoolId);
							
						// SQL文を実行し、結果表を取得する 
						ResultSet rs = pStmt.executeQuery();

						// 結果表をコレクションにコピーする　
						while (rs.next()) {
							AllDto dto=new AllDto();
							dto.setCalendarId(rs.getInt("calendarId"));
							dto.setCalendarDate(rs.getDate("calendarDate").toLocalDate());
							dto.setTitle(rs.getString("title"));
							dto.setTime(rs.getTime("time").toLocalTime());
							dto.setCalendarMemo(rs.getString("calendarMemo"));
							dto.setCalendarDogId(rs.getInt("calendarDogId"));
							
							//addでcardListにbcを入れている　（cardListはArrayList)
							resultList.add(dto);
						}
					} catch (SQLException e) {
						e.printStackTrace();
						resultList = null;
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
						resultList = null;
					} finally {
						// データベースを切断
						if (conn != null) {
							try {
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
								resultList = null;
							}
						}
					}

					// 結果を返す
					return resultList;
				}
				
				
				//schedule_regiのわんこ名選択用プルダウン(スクール側)
				public List<AllDto> selectWanko(int userSchoolId){
					//どこのデータベースにつなぐかを入れるConn
					Connection conn = null;
					List<AllDto> wankoList = new ArrayList<AllDto>();
					
					try {
						// JDBCドライバを読み込む
						Class.forName("com.mysql.cj.jdbc.Driver");

						// データベースに接続する ([webapp2] [root] [password]がいじるところ)
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
								+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
								"root", "password");

						// SQL文を準備する
						String sql ="SELECT userNameId,dogName,wankoDogId "
								+ "FROM USER JOIN WANKO ON USER.userNameId=WANKO.wankoNameId "
								+ "WHERE USER.userSchoolId = ? ORDER BY dogName";
						PreparedStatement pStmt = conn.prepareStatement(sql);

						// SQL文を完成させる　
							System.out.println(userSchoolId+"渡ってきたSchoolIdだよ");
							pStmt.setObject(1, userSchoolId);
							
						// SQL文を実行し、結果表を取得する 
						ResultSet rs = pStmt.executeQuery();

						// 結果表をコレクションにコピーする　
						while (rs.next()) {
							AllDto dto=new AllDto();
							dto.setDogName(rs.getString("dogName"));
							dto.setWankoDogId(rs.getInt("WankoDogId"));
							//addでcardListにbcを入れている　
							wankoList.add(dto);
						}
					} catch (SQLException e) {
						e.printStackTrace();
						wankoList = null;
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
						wankoList = null;
					} finally {
						// データベースを切断
						if (conn != null) {
							try {
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
								wankoList = null;
							}
						}
					}

					// 結果を返す
					return wankoList;
				}
		
				


}

