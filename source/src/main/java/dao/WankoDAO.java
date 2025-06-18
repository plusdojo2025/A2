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

public class WankoDAO {
	


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
				String sql = "SELECT dogName,dogBreed,dogBirth,gender,state,wakuchin,wankoDogId,wankoNameId,dogPhoto,kyosei,dogRegist,remarks1,remarks2,remarks3,remarks4,remarks5,injection,rabies FROM AllDto WHERE dogName LIKE ? AND wankoNameId LIKE ? AND dogRegist LIKE ? ";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を完成させる
				//中途半端なSQL文を完成させるため
				if (wanko.getDogName() != "") {
					pStmt.setString(1, "%" + wanko.getDogName() + "%");
				} else {
					pStmt.setString(1, "%");
				}
				if (wanko.getWankoNameId() != "") {
					pStmt.setString(2, "%" + wanko.getWankoNameId() + "%");
				} else {
					pStmt.setString(2, "%");
				}
				if (wanko.getDogRegist() != null) {
				    pStmt.setDate(3, java.sql.Date.valueOf(wanko.getDogRegist()));
				} else {
				    pStmt.setNull(3, java.sql.Types.DATE);
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
	
					    
				
					
			}
		}
}