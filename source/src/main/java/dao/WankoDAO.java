package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.AllDto;



public class WankoDAO {
	

		//ログインわんこ表示用
		public AllDto logdog(String userNameId) {
			Connection conn = null;
			AllDto logdogdto = null;
			
			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");
				
				// SQL文を準備する　USER　テーブルに　WANKO　テーブルジョイン
				String sql = "SELECT userNameId, dogPhoto, dogName, wankoNameId "
						+ "FROM USER JOIN WANKO "
						+ "ON USER.userNameId = WANKO.wankoNameId "
						+ "WHERE USER.userNameId=? "
						+ "ORDER BY USER.userNameId ";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を完成させる
				pStmt.setString(1, userNameId);
				
				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();
				
				// 結果表をコレクションにコピーする　犬写真と犬名前保存
				while (rs.next()) {
					logdogdto = new  AllDto();
					logdogdto.setDogPhoto(rs.getString("dogPhoto"));
					logdogdto.setDogName(rs.getString("dogName"));
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
				logdogdto = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				logdogdto = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						logdogdto = null;
						
						
				}
			}
		}
		return logdogdto;
		}
		//ログインわんこ処理ここまで

		// 引数card指定された項目で検索して、取得されたデータのリストを返す
		public List<AllDto> select(AllDto wanko) {
			Connection conn = null;
			List<AllDto> wankoList = new ArrayList<AllDto>();

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "SELECT dogName,dogBreed,dogBirth,gender,state,wakuchin,wankoDogId,wankoNameId,dogPhoto,kyosei,dogRegist,remarks1,remarks2,remarks3,remarks4,remarks5,injection,rabies "
						+ "FROM AllDto WHERE dogName LIKE ? OR dogBreed LIKE ? OR dogBirth LIKE ? OR gender LIKE ? OR state LIKE ? OR wakuchin LIKE ? OR wankoDogId LIKE ? OR wankoNameId LIKE ? OR dogPhoto LIKE ? OR kyosei LIKE ?"
						+ "OR dogReist LIKE ? OR remarks1 LIKE ? OR remarks2 LIKE ? OR remarks3 LIKE ? OR remarks4 LIKE ? OR remarks5 LIKE ? OR injection LIKE ? OR rabies LIKE ? ";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を完成させる
				//中途半端なSQL文を完成させるため
				
				if (wanko.getDogName() != "") {
					pStmt.setString(1, "%" + wanko.getDogName() + "%");
					pStmt.setString(2, "%" + wanko.getDogBreed() + "%");
					pStmt.setString(3, "%" + wanko.getDogBirth() + "%");
					pStmt.setBoolean(4,  wanko.isGender());
					pStmt.setString(5, "%" + wanko.getState() + "%");
					pStmt.setString(6, "%" + wanko.getWakuchin() + "%");
					pStmt.setString(7, "%" + wanko.getWankoDogId() + "%");
					pStmt.setString(8, "%" + wanko.getWankoNameId() + "%");
					pStmt.setString(9, "%" + wanko.getDogPhoto() + "%");
					pStmt.setBoolean(10, wanko.isKyosei());
					pStmt.setString(11, "%" + wanko.getDogRegist() + "%");
					pStmt.setString(12, "%" + wanko.getRemarks1() + "%");
					pStmt.setString(13, "%" + wanko.getRemarks2() + "%");
					pStmt.setString(14, "%" + wanko.getRemarks3() + "%");
					pStmt.setString(15, "%" + wanko.getRemarks4() + "%");
					pStmt.setString(16, "%" + wanko.getRemarks5() + "%");
					pStmt.setString(17, "%" + wanko.getInjection() + "%");
					pStmt.setString(18, "%" + wanko.getRabies() + "%");
				} else {
					pStmt.setString(1, "%");
					pStmt.setString(2, "%");
					pStmt.setNull(3, java.sql.Types.DATE);
					pStmt.setString(4, "%");
					pStmt.setString(5, "%");
					pStmt.setString(6, "%");
					pStmt.setString(7, "%");
					pStmt.setString(8, "%");
					pStmt.setString(9, "%");
					pStmt.setString(10, "%");
					pStmt.setString(11, "%");
					pStmt.setString(12, "%");
					pStmt.setString(13, "%");
					pStmt.setString(14, "%");
					pStmt.setString(15, "%");
					pStmt.setString(16, "%");
					pStmt.setString(17, "%");
					pStmt.setString(18, "%");
				}
			
				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();
				
				while (rs.next()) {
					AllDto inu=new AllDto();
					inu.setDogName(rs.getString("dogname"));
					inu.setDogBreed(rs.getString("dogbreed"));
					inu.setDogBirth(rs.getDate("dogBirth").toLocalDate());
					inu.setGender(rs.getBoolean("gender"));
					inu.setState(rs.getString("state"));
					inu.setWakuchin(rs.getString("wakuchin"));
					inu.setWankoDogId(rs.getInt("wankoDogId"));
					inu.setWankoNameId(rs.getString("wankoNameId"));
					inu.setDogPhoto(rs.getString("dogPhoto"));
					inu.setKyosei(rs.getBoolean("kyosei"));
					inu.setDogRegist(rs.getDate("dogRegist").toLocalDate());
					inu.setRemarks1(rs.getString("remarks1"));
					inu.setRemarks2(rs.getString("remarks2"));
					inu.setRemarks3(rs.getString("remarks3"));
					inu.setRemarks4(rs.getString("remarks4"));
					inu.setRemarks5(rs.getString("remarks5"));
					inu.setInjection(rs.getString("injection"));
					inu.setRabies(rs.getString("rabies"));
					
					wankoList.add(inu);
					
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
		
		// わんこ新規登録　トレーナー用　　　　　↓　型と名前
				public int insert(String dogPhoto, String dogName, String name, String wakuchin, String kyosei, String gender, String dogBreed, String dogBirth, String remarks ) {
					Connection conn = null;
					int ans = 0;
					
					try {
						// JDBCドライバを読み込む
						Class.forName("com.mysql.cj.jdbc.Driver");

						// データベースに接続する
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
								+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
								"root", "password");
						
						// SQL文を準備する　　　　　　　　　　↓テーブル名　　　　　　↓登録する数？作る
						String sql = "INSERT INTO WANKO VALUES (?,?,?,?,?,?,?,?,?)" ;
						PreparedStatement pStmt = conn.prepareStatement(sql);
						
						//SQLを完成させる
						pStmt.setString(1, dogPhoto);
						pStmt.setString(2, dogName);
						pStmt.setString(3, name);
						pStmt.setString(4, wakuchin);
						pStmt.setString(5, kyosei);
						pStmt.setString(6, gender);
						pStmt.setString(7, dogBreed);
						pStmt.setString(8, dogBirth);
						pStmt.setString(9, remarks);
						
						// SQL文を実行し、結果（int型)を取得する
						ans = pStmt.executeUpdate();
						
						// SQL文を実行する
					} catch (SQLException e) {
						e.printStackTrace();
						//インサートできない
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
						//根本的になんかおかしい
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
								return ans;
				}
		
		// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
		public boolean insert(AllDto wanko) {
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
				String sql = "INSERT INTO AllDto VALUES ( 0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" ;
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				pStmt.setString(1, wanko.getDogName());
				pStmt.setString(2, wanko.getDogBreed());
				pStmt.setDate(3, java.sql.Date.valueOf(wanko.getDogBirth()));
				pStmt.setBoolean(4, wanko.isGender());
				pStmt.setString(5, wanko.getState());
				pStmt.setString(6, wanko.getWakuchin());
				pStmt.setInt(7, wanko.getWankoDogId());
				pStmt.setString(8, wanko.getWankoNameId());
				pStmt.setString(9, wanko.getDogPhoto());
				pStmt.setBoolean(10, wanko.isKyosei());
				pStmt.setDate(11, java.sql.Date.valueOf(wanko.getDogRegist()));
				pStmt.setString(12, wanko.getRemarks1());
				pStmt.setString(13, wanko.getRemarks2());
				pStmt.setString(14, wanko.getRemarks3());
				pStmt.setString(15, wanko.getRemarks4());
				pStmt.setString(16, wanko.getRemarks5());
				pStmt.setString(17, wanko.getInjection());
				pStmt.setString(18, wanko.getRabies());
			
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
		public boolean update(AllDto wanko) {
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
				String sql = "UPDATE AllDto SET dogname=?, dogbreed=?, dogbirth=?, gender=?, state=?, wakuchin=?, wankonameid=?, dogphoto=?, kyosei=?, dogregist=?, remarks1=?, remarks2=?, remarks3=?, remarks4=?, remarks5=?, injection=?, rabies=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				pStmt.setString(1, wanko.getDogName());
				pStmt.setString(2, wanko.getDogBreed());
				pStmt.setDate(3, java.sql.Date.valueOf(wanko.getDogBirth()));
				pStmt.setBoolean(4, wanko.isGender());
				pStmt.setString(5, wanko.getState());
				pStmt.setString(6, wanko.getWakuchin());
				pStmt.setInt(7, wanko.getWankoDogId());
				pStmt.setString(8, wanko.getWankoNameId());
				pStmt.setString(9, wanko.getDogPhoto());
				pStmt.setBoolean(10, wanko.isKyosei());
				pStmt.setDate(11, java.sql.Date.valueOf(wanko.getDogRegist()));
				pStmt.setString(12, wanko.getRemarks1());
				pStmt.setString(13, wanko.getRemarks2());
				pStmt.setString(14, wanko.getRemarks3());
				pStmt.setString(15, wanko.getRemarks4());
				pStmt.setString(16, wanko.getRemarks5());
				pStmt.setString(17, wanko.getInjection());
				pStmt.setString(18, wanko.getRabies());
				
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
		
		// 引数cardで指定された番号のレコードを削除し、成功したらtrueを返す
		public boolean delete(AllDto wanko) {
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
				String sql = "DELETE FROM AllDto WHERE wankodogid=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を完成させる
				pStmt.setInt(1, wanko.getWankoDogId());
				
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

		
	