package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.AllDto;





public class ReportDAO {
	// 引数report指定された項目で検索して、取得されたデータのリストを返す  途中
	public List<AllDto> select(String userNameId) {
		Connection conn = null;
		List<AllDto> reportList = new ArrayList<AllDto>();
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			
			String sql = "SELECT reportId, dogPhoto, dogName, USER.name, REPORT.reportDate, reportDogId "
			           + "FROM USER "
			           + "JOIN WANKO ON USER.userNameId = WANKO.wankoNameId "
			           + "JOIN REPORT ON REPORT.reportDogId = WANKO.wankoDogId "
			           + "WHERE USER.userNameId = ? "
			           + "ORDER BY WANKO.wankoDogId";

			System.out.println("userNameId: " + userNameId);
			System.out.println("SQL: " + sql);


			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			pStmt.setString(1, userNameId);
		
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				AllDto alldto = new AllDto();
				    alldto.setReportId(rs.getInt("reportId")); 
					alldto.setDogPhoto(rs.getString("dogPhoto")); 
					alldto.setDogName(rs.getString("dogName"));
					alldto.setName(rs.getString("name"));
					alldto.setReportDate(rs.getDate("reportDate").toLocalDate());
					alldto.setReportDogId(rs.getInt("reportDogId"));
					System.out.println("repot" + alldto);		
				reportList.add(alldto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			reportList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			reportList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					reportList = null;
				}
			}
		}
		System.out.println("レポート" + reportList);
		// 結果を返す
		return reportList;
	}

		
		// 引数で指定されたレコードを登録し、成功したらtrueを返す
		public boolean insert(AllDto report) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2? "
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true ",
						"root", "password");

				// SQL文を準備する
				String sql = "INSERT INTO Report VALUES (0, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を完成させる
					pStmt.setBoolean(1, report.getFood());
					pStmt.setInt(2, report.getWalk());
					pStmt.setBoolean(3, report.isReportState());
					pStmt.setString(4, report.getTraining());
					pStmt.setString(5, report.getReportMemo());
					pStmt.setDate(6, java.sql.Date.valueOf(report.getReportDate()));
					pStmt.setInt(7, report.getReportDogId());
					
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
		
		
		// 引数reportで指定されたレコードを更新し、成功したらtrueを返す
		public boolean update(AllDto report) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2? "
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true ",
						"root", "password");

				// SQL文を準備する 
				String sql = "UPDATE Report SET food=?, walk=?, reportState=?, training=?, reportMemo=?, reportDate=?  WHERE reportId";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を完成させる
				pStmt.setBoolean(1, report.getFood());
				pStmt.setInt(2, report.getWalk());
				pStmt.setBoolean(3, report.isReportState());
				pStmt.setString(4, report.getTraining());
				pStmt.setString(5, report.getReportMemo());
				pStmt.setDate(6, java.sql.Date.valueOf(report.getReportDate()));
				pStmt.setInt(7, report.getReportDogId());
				
				
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

		

		
		
		//削除のとこ
		public boolean delete(AllDto report) {
			Connection conn = null;
			boolean result = false;
			
			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2? "
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する 
				String sql = "DELETE FROM AllDto WHERE reportId=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を完成させる
				pStmt.setInt(1, report.getReportId());
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

		// 引数report指定された項目で検索して一覧がクリックされたら報告詳細表示
		public List<AllDto> select(int reportId) {
			Connection conn = null;
			List<AllDto> rdList = new ArrayList<AllDto>();

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Dzriver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "SELECT reportId FROM report WHERE reportId LIKE ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (reportId != 0) {
					pStmt.setString(1, "%" + reportId + "%");
				} else {
					pStmt.setString(1, "%");
				}

				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					AllDto dto = new AllDto();
					dto.setReportId(rs.getInt("reportId"));
					rdList.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				rdList = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				rdList = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						rdList = null;
					}
				}
			}

			// 結果を返す
			return rdList;
		}

		//レポート詳細
		public List<AllDto> oReportDetail(int wankoDogId){
			Connection conn = null;
			List<AllDto> ord = new ArrayList<AllDto>();
			try {
				// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");
				// SQL文を準備する
				String sql = "SELECT reportId, dogName, food, reportDate, walk, reportState, training, reportMemo "
						+ "FROM REPORT JOIN WANKO "
						+ "ON REPORT.reportDogId = WANKO.wankoDogId "
						+ "WHERE WANKO.wankoDogId=? "
						+ "ORDER BY WANKO.wankoDogId ";
			
				System.out.println(sql);
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を完成させる
				pStmt.setInt(1, wankoDogId);

				
				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();
				
				// 結果表をコレクションにコピーする　犬写真と犬名前保存
				while (rs.next()) {
					AllDto rp = new  AllDto();
					rp.setReportId(rs.getInt("reportId"));
					rp.setDogName(rs.getString("dogName"));
					rp.setFood(rs.getBoolean("food"));
					rp.setReportDate(rs.getDate("reportDate").toLocalDate());
					rp.setWalk(rs.getInt("walk"));
					rp.setReportState(rs.getBoolean("reportState"));
					rp.setTraining(rs.getString("training"));
					rp.setReportMemo(rs.getString("reportMemo"));
					
					System.out.println("aa" + rp);
					
					ord.add(rp);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				ord = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				ord = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						ord = null;	
				}
			}
		}
			return ord;
		}

		
}

