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
		String sql = "SELECT nameId, ruby, birth, name, pw, uPhone, uPhone2, address, schoolId";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();

		// 結果表をコレクションにコピーする
//		while (rs.next()) {
//			AllDto us = new AllDto(rs.getInt("number"),
//						   rs.getString("company"),
//						   rs.getString("department"),
//						   rs.getString("position"),
//						   rs.getString("name"),
//						   rs.getString("zipcode"),
//						   rs.getString("address"),
//						   rs.getString("phone"),
//						   rs.getString("fax"),
//						   rs.getString("email"),
//						   rs.getString("remarks"));
			// bc.setNumber(rs.getInt("number"));とかでも書ける
//			cardList.add(bc);
//		}
		while (rs.next()) {
			AllDto us = new AllDto();
			AllDto.setUserNameId(rs.getString("nameId"));
							
			
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
	
	
	
	
	
	
	
	// 引数userで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(AllDto user) {
	Connection conn = null;
	boolean result = false;
	
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/A2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "USER");
			
			// SQL文を準備する
			String sql = "INSERT INTO AllDto VALUES (nameId, ruby, birth, name, pw, uPhone, uPhone2, address, schoolId)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			if (user.getnameId() != null) {
				pStmt.setString(1, user.getnameId());
			} else {
				pStmt.setString(1, "");
			}
			if (user.getruby() != null) {
				pStmt.setString(2, user.getruby());
			} else {
				pStmt.setString(2, "");
			}
			if (user.getPosition() != null) {
				pStmt.setString(3, user.getPosition());
			} else {
				pStmt.setString(3, "");
			}
			if (user.getbirth() != null) {
				pStmt.setString(4, user.getbirth());
			} else {
				pStmt.setString(4, "");
			}
			if (user.getpw() != null) {
				pStmt.setString(5, user.getpw());
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
			if (user.getaddress() != null) {
				pStmt.setString(8, user.getaddress());
			} else {
				pStmt.setString(8, "");
			}
			if (user.getschoolId() != null) {
				pStmt.setString(9, user.getschoolId());
			} else {
				pStmt.setString(9, "");
			}
		
		//更新
			
		}
	
			
			
			
	}		
			
			
			
			
}
