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
	public List<AllDto> select(AllDto report) {
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
			
			String sql = "SELECT dogPhoto, reportDate, dogName, name "
					+ "FROM USER JOIN WANKO "
					+ "ON USER.userNameId = WANKO.wankoNameId "
					+ "WHERE WANKO.wankoNameId=? "
					+ "ORDER BY USER.userNameId " ;
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			if (report.getState() != null) {
				pStmt.setString(1, "%" + report.getState() + "%");
				pStmt.setString(2, "%" + report.getState() + "%");
				pStmt.setString(3, "%" + report.getState() + "%");
				pStmt.setString(4, "%" + report.getState() + "%");
				pStmt.setString(5, "%" + report.getState() + "%");
				pStmt.setString(6, "%" + report.getState() + "%");
				pStmt.setString(7, "%" + report.getState() + "%");
				pStmt.setString(8, "%" + report.getState() + "%");
			} else {
				pStmt.setString(1, "%");
				pStmt.setString(2, "%");
				pStmt.setString(3, "%");
				pStmt.setString(4, "%");
				pStmt.setString(5, "%");
				pStmt.setString(6, "%");
				pStmt.setString(7, "%");
				pStmt.setString(8, "%");
			}
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				AllDto alldto = new AllDto();
					alldto.setReportId(rs.getInt("reportId")); 
					alldto.setFood(rs.getBoolean("food"));
					alldto.setWalk(rs.getInt("walk"));
					alldto.setState(rs.getString("state"));
					alldto.setTraining(rs.getString("training"));
					alldto.setReportMemo(rs.getString("reportMemo"));
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
		public List<AllDto> oReportDetail(String detail){
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
				String sql = "SELECT dogName, food, reportDate, walk, reportState, training, reportMemo "
						+ "FROM REPORT JOIN WANKO "
						+ "ON REPORT.reportDogId = WANKO.wankoDogId "
						+ "WHERE WANKO.wankoDogId=? "
						+ "ORDER BY WANKO.wankoDogId ";
			
				
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を完成させる
				pStmt.setString(1, detail);
				
				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();
				
				// 結果表をコレクションにコピーする　犬写真と犬名前保存
				while (rs.next()) {
					AllDto rp = new  AllDto();
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

