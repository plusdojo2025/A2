package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.AllDto;

public class UserDAO {
	// 引数userで指定された項目で検索して、取得されたデータのリストを返す
	public List<AllDto> select(AllDto user) {
	Connection conn = null;
	List<AllDto> userList = new ArrayList<AllDto>();

	try {
		// JDBCドライバを読み込む
		Class.forName("com.mysql.cj.jdbc.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
				+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
				"root", "USER");

		// SQL文を準備する
		String sql = "SELECT nameId, ruby, birth, name, pw, uPhone, uPhone2, address, "
				+ "userUniqueId, schoolId From AllDto WHERE nameId LIKE ? OR ruby LIKE ? OR "
				+ "birth LIKE ? OR name LIKE ? OR pw LIKE ? OR uPhone LIKE ? OR "
				+ "uPhone2 LIKE ? OR address LIKE ? OR userUniqueId LIKE ? OR"
				+ " userSchoolId LIKE ? ORDER BY number";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		if (user.getUserNameId() != null) {
			pStmt.setString(1, "%" + user.getUserNameId() + "%");
			pStmt.setString(2, "%" + user.getRuby() + "%");
			pStmt.setString(3, "%" + user.getBirth() + "%");
			pStmt.setString(4, "%" + user.getName() + "%");
			pStmt.setString(5, "%" + user.getPw() + "%");
			pStmt.setString(6, "%" + user.getuPhone() + "%");
			pStmt.setString(7, "%" + user.getuPhone2() + "%");
			pStmt.setString(8, "%" + user.getAddress() + "%");
			pStmt.setString(9, "%" + user.isUserUniqueId() + "%");
			pStmt.setString(10, "%" + user.getUserSchoolId() + "%");
		} else {
			pStmt.setString(1, "%");
			pStmt.setString(2, "%");
			pStmt.setString(3, "%");
			pStmt.setString(4, "%");
			pStmt.setString(5, "%");
			pStmt.setString(6, "%");
			pStmt.setString(7, "%");
			pStmt.setString(8, "%");
			pStmt.setString(9, "%");
			pStmt.setString(10, "%");
		}
		
		// SQL文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();

		// 結果表をコレクションにコピーする
		while (rs.next()) {

			AllDto us = new AllDto();
			us.setUserNameId(rs.getString("userNameId"));
			us.setRuby(rs.getString("ruby"));
			us.setBirth(rs.getDate("birth").toLocalDate());
			us.setName(rs.getString("name"));
			us.setPw(rs.getString("pw"));
			us.setuPhone(rs.getString("uPhone"));
			us.setuPhone2(rs.getString("uPhone2"));
			us.setAddress(rs.getString("address"));
			us.setUserUniqueId(rs.getBoolean("userUniqueId"));
			us.setUserSchoolId(rs.getInt("userSchoolId"));
										
			userList.add(us);						
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		userList = null;
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
		userList = null;
	} finally {
		// データベースを切断
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				userList = null;
			}
		}
	}

	// 結果を返す
	return userList;
	
	}
	
	// 引数userで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(AllDto user) {
	Connection conn = null;
	boolean result = false;
	
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "USER");
			
			// SQL文を準備する
			String sql = "INSERT INTO AllDto VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			if (user.getUserNameId() != null) {
				pStmt.setString(1, user.getUserNameId());
			} else {
				pStmt.setString(1, "");
			}
			if (user.getRuby() != null) {
				pStmt.setString(2, user.getRuby());
			} else {
				pStmt.setString(2, "");
			}
			if (user.getBirth() != null) {
				pStmt.setDate(3, java.sql.Date.valueOf(user.getBirth()));
			} else {
				pStmt.setString(3, "");
			}
			if (user.getName() != null) {
				pStmt.setString(4, user.getName());
			} else {
				pStmt.setString(4, "");
			}
			if (user.getPw() != null) {
				pStmt.setString(5, user.getPw());
			} else {
				pStmt.setString(5, "");
			}
			if (user.getuPhone() != null) {
				pStmt.setString(6, user.getuPhone());
			} else {
				pStmt.setString(6, "");
			}
			if (user.getuPhone2() != null) {
				pStmt.setString(7, user.getuPhone2());
			} else {
				pStmt.setString(7, "");
			}
			if (user.getAddress() != null) {
				pStmt.setString(8, user.getAddress());
			} else {
				pStmt.setString(8, "");
			}
			if (user.isUserUniqueId()) {
				pStmt.setBoolean(9, user.isUserUniqueId());
			} else {
				pStmt.setString(9, "");
			}
			if (user.getUserSchoolId() != 0) {
				pStmt.setInt(10, user.getUserSchoolId());
			} else {
				pStmt.setString(10, "");
			}
			
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
					//loginResult = false;
				}
			}
		}	
	
		// 結果を返す
		return result;
	}		
			
	// 引数userで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(AllDto user) {
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
			String sql = "UPDATE AllDto SET nameId=?, ruby=?, birth=?, name=?, pw=?, uPhone=?, uPhone2=?, fax=?, address=?, UserUniqueId=?, UserSchoolId=? WHERE number=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (user.getUserNameId() != null) {
				pStmt.setString(1, user.getUserNameId());
			} else {
				pStmt.setString(1, "");
			}
			if (user.getRuby() != null) {
				pStmt.setString(2, user.getRuby());
			} else {
				pStmt.setString(2, "");
			}
			if (user.getBirth() != null) {
				pStmt.setDate(3, java.sql.Date.valueOf(user.getBirth()));
			} else {
				pStmt.setString(3, "");
			}
			if (user.getName() != null) {
				pStmt.setString(4, user.getName());
			} else {
				pStmt.setString(4, "");
			}
			if (user.getPw() != null) {
				pStmt.setString(5, user.getPw());
			} else {
				pStmt.setString(5, "");
			}
			if (user.getuPhone() != null) {
				pStmt.setString(6, user.getuPhone());
			} else {
				pStmt.setString(6, "");
			}
			if (user.getuPhone2() != null) {
				pStmt.setString(7, user.getuPhone2());
			} else {
				pStmt.setString(7, "");
			}
			if (user.getAddress() != null) {
				pStmt.setString(8, user.getAddress());
			} else {
				pStmt.setString(8, "");
			}
			if (user.isUserUniqueId()) {
				pStmt.setBoolean(9, user.isUserUniqueId());
			} else {
				pStmt.setString(9, "");
			}
			if (user.getUserSchoolId() != 0) {
				pStmt.setInt(10, user.getUserSchoolId());
			} else {
				pStmt.setString(10, "");
			}	

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
	public boolean delete(AllDto user) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "USER");

			// SQL文を準備する
			String sql = "DELETE FROM USER WHERE userNameId=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, user.getUserNameId());

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

	public AllDto login(String id, String pw) {
		Connection conn = null;
		AllDto dto=null;
		//boolean loginResult = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SELECT文を準備する
			String sql = "SELECT * FROM USER WHERE userNameId=? AND pw=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//SQL文を完成
			pStmt.setString(1, id);
			pStmt.setString(2, pw);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
			
			// 結果をコピー
						while(rs.next()) {
							dto = new AllDto();
							dto.setUserNameId(id);
							dto.setName(rs.getString("name"));
							dto.setUserUniqueId(rs.getBoolean("userUniqueId"));
							dto.setUserSchoolId(rs.getInt("userSchoolId"));
						
			}
		} catch (SQLException e) {
			e.printStackTrace();
			dto = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			dto = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					dto = null;
				}
			}
		}

		// 結果を返す
		return dto;
	}

}
