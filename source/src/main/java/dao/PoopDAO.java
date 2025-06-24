package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.AllDto;

	public class PoopDAO{
	//登録//
	public int insert(AllDto poop) {
		Connection conn = null;
		int ans = 0;
	
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
	
			// SQL文を準備する
			String sql = "INSERT INTO Bc VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			
			pStmt.setInt(1, poop.getPoopId());
		
			pStmt.setString(2, poop.getTlName());
		
			pStmt.setTimestamp(3, java.sql.Timestamp.valueOf (poop.getNowTime()));
		
			pStmt.setString(4, poop.getPhoto());
			
			pStmt.setInt(5, poop.getColor());
		
			pStmt.setInt(6, poop.getHardness());
		
			pStmt.setBoolean(7, poop.isAbnormal());
		
			pStmt.setInt(8, poop.getPoopDogId());
			
			pStmt.setString(9, poop.getMemo());
			
			pStmt.setDate(10, java.sql.Date.valueOf (poop.getDate()));
			
		
			// SQL文を実行する
			
			ans = pStmt.executeUpdate();
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
		return ans;
	}
	
	//うんちリストを作る
	public  List<AllDto> pooplistSelect(String userNameId){
		Connection conn = null;
		List<AllDto> poopList = new ArrayList<AllDto>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			// SQL文を準備する
			String sql = "SELECT schoolId, nameId, poopId, tlName, nowTime, photo, color, hardness, abnormal, dogId,"
					+ " memo , date FROM POOP JOIN WANKO ON POOP.dogId=WANKO.dogId "
			+"WHERE POOP.dogId=? "
			+"ORDER BY Poop.dogId";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			pStmt.setString(1, userNameId);
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする　うんち写真と犬名前保存
			while (rs.next()) {
				AllDto poop = new  AllDto();
				AllDto unchi= new AllDto();
				unchi.setPhoto(rs.getString("photo"));
				unchi.setTlName(rs.getString("dogId"));
				unchi.setTlName(rs.getString("tlName"));
				unchi.setHardness(rs.getInt("hardness"));
				unchi.setColor(rs.getInt("color"));
				unchi.setTlName(rs.getString("date"));
				
				poopList.add(poop);
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			poopList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			poopList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					poopList = null;
					
					
			}
		}
	}
		return poopList;
	}

	//検索	
	// 引数 poop指定された項目で検索して、取得されたデータのリストを返す
	public List<AllDto> select(AllDto poop) {
		Connection conn = null;
		List<AllDto> poopList = new ArrayList<AllDto>();
	
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "poopId, tlName, nowTime, photo, color, hardness, abnormal, dogId, memo , date FROM Poop WHERE poopId LIKE? OR tlName LIKE? OR nowTime LIKE? OR photo LIKE? OR color LIKE? OR hardness LIKE? OR abnormal LIKE? OR dogId LIKE? OR memo LIKE? OR date LIKE? ORDER BY number";
			PreparedStatement pStmt = conn.prepareStatement(sql);
	
			// SQL文を完成させる
			if (poop.getTlName() != null) {
				pStmt.setString(1, "%" + poop.getPoopId() + "%");
				pStmt.setString(2, "%" + poop.getPoopId() + "%");
				pStmt.setString(3, "%" + poop.getPoopId() + "%");
				pStmt.setString(4, "%" + poop.getPoopId() + "%");
				pStmt.setString(5, "%" + poop.getPoopId() + "%");
				pStmt.setString(6, "%" + poop.getPoopId() + "%");
				pStmt.setString(7, "%" + poop.getPoopId() + "%");
				pStmt.setString(8, "%" + poop.getPoopId() + "%");
				pStmt.setString(9, "%" + poop.getPoopId() + "%");
				pStmt.setString(10, "%" + poop.getPoopId() + "%");
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
			
			while (rs.next()) {
				AllDto unchi=new AllDto();
				unchi.setPoopId(rs.getInt("poopid"));
				unchi.setTlName(rs.getString("tlname"));
				unchi.setNowTime(rs.getTimestamp("nowTime").toLocalDateTime());
				unchi.setPhoto(rs.getString("photo"));
				unchi.setColor(rs.getInt("color"));
				unchi.setHardness(rs.getInt("hardness"));
				unchi.setTlName(rs.getString("abnormal"));
				unchi.setTlName(rs.getString("dogId"));
				unchi.setTlName(rs.getString("memo"));
				unchi.setTlName(rs.getString("date"));
			
				poopList.add(unchi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			poopList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			poopList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					poopList = null;
				}
			}
		}

		// 結果を返す
		return poopList;
	}

	//更新
	// 引数poopで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(AllDto poop) {
		Connection conn = null;
		boolean result = false;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
		
			// SQL文を準備する
			String sql = "UPDATE Bc SET poopId=?, tlName=?, nowTime=?, photo=?, color=?, hardness=?, abnormal=?, dogId=?, memo=?, date=? WHERE number=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				
				pStmt.setInt(1, poop.getPoopId());
			
				pStmt.setString(2, poop.getTlName());
			
				pStmt.setTimestamp(3, java.sql.Timestamp.valueOf (poop.getNowTime()));
			
				pStmt.setString(4, poop.getPhoto());
				
				pStmt.setInt(5, poop.getColor());
			
				pStmt.setInt(6, poop.getHardness());
			
				pStmt.setBoolean(7, poop.isAbnormal());
			
				pStmt.setInt(8, poop.getPoopDogId());
				
				pStmt.setString(9, poop.getMemo());
				
				pStmt.setDate(10, java.sql.Date.valueOf (poop.getDate()));
				
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
				

	//削除
	//引数 poopで指定された番号のレコードを削除し、成功したらtrueを返す
	public boolean delete(AllDto poop) {
		Connection conn = null;
		boolean result = false;
		
			try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
	
			// SQL文を準備する
			String sql = "DELETE FROM Bc WHERE number=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			pStmt.setInt(1, poop.getPoopId());
			
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
