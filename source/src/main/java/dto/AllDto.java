package dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AllDto implements Serializable {
	
	//わんこテーブル
	 private String dogName;		/*ワンコの名前*/
	 private String dogBreed;		/*犬種*/
	 private LocalDate dogBirth;	/*わんこの誕生日*/
	 private boolean gender;		/*性別*/
	 private String state;			/*普段の様子*/
	 private String wakuchin;		/*ワクチン歴写真*/
	 private int wankoDogId;				/*わんこID*/
	 private String wankoNameId;		/*飼い主ID*/
	 private String dogPhoto;		/*ワンコ写真*/
	 private boolean kyosei ;		/*去勢*/
	 private LocalDate dogRegist;	/*ワンコの登録日*/
	 private String remarks1;		/*備考*/
	 private String remarks2;		/*備考*/
	 private String remarks3;		/*備考*/
	 private String remarks4;		/*備考*/
	 private String remarks5;		/*備考*/
	 private String injection;		/*注射*/
	 private String rabies;			/*狂犬病*/

	 //ユーザーテーブル
	 private String userNameId;	/*飼い主ID*/
	 private String ruby;		/*ふりがな*/
	 private LocalDate birth;	/*生年月日*/
	 private String name;		/*飼い主の名前*/
	 private String pw;			/*PW*/
	 private String uPhone;		/*電話番号*/
	 private String uPhone2;	/*連絡先２*/
	 private String address;	/*住所*/
	 private boolean userUniqueId;	/*識別ID*/
	 private int userSchoolId;		/*スクールID*/
	 
	 //スクールテーブル
	 private int SchoolId;		/*スクールID*/
	 
	 //うんちテーブル
	 private int poopId;			/*うんちID*/
	 private String tlName;			/*トレーナー名前*/
	 private LocalDateTime nowTime;	/*時間*/
	 private String photo;			/*写真*/
	 private int color;				/*色*/
	 private int hardness;			/*硬さ*/
	 private boolean abnormal;		/*異常*/
	 private int PoopDogId;			/*わんこID*/
	 private String memo;			/*メモ*/
	 private LocalDate date;		/*日付*/
	 
	 //カレンダー
	 private int calendarId;			/*カレンダーID*/
	 private LocalDate calendarDate;			/*日付*/
	 private String title;	/*タイトル*/
	 private LocalTime time;			/*時間*/
	 private String calendarMemo;				/*メモ*/
	 private int calendarDogId;			/*わんこID*/

	 //報告テーブル
	 private int reportId;				/*報告ID*/
	 private boolean food;		/*ごはん*/
	 private int walk;				/*散歩*/
	 private boolean reportState;		/*様子*/
	 private String training;				/*トレーニング*/
	 private String reportMemo;			/*メモ*/
	 private LocalDate reportDate;		/*日付*/
	 private int reportDogId;			/*ワンコID*/
	 
	//ゲッターセッターの生成
	 //ワンコテーブル用ゲッターセッターの生成
	public String getDogName() {
		return dogName;
	}

	public void setDogName(String dogName) {
		this.dogName = dogName;
	}

	public String getDogBreed() {
		return dogBreed;
	}

	public void setDogBreed(String dogBreed) {
		this.dogBreed = dogBreed;
	}

	public LocalDate getDogBirth() {
		return dogBirth;
	}

	public void setDogBirth(LocalDate dogBirth) {
		this.dogBirth = dogBirth;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWakuchin() {
		return wakuchin;
	}

	public void setWakuchin(String wakuchin) {
		this.wakuchin = wakuchin;
	}

	public int getWankoDogId() {
		return wankoDogId;
	}

	public void setWankoDogId(int wankoDogId) {
		this.wankoDogId = wankoDogId;
	}

	public String getWankoNameId() {
		return wankoNameId;
	}

	public void setWankoNameId(String wankoNameId) {
		this.wankoNameId = wankoNameId;
	}

	public String getDogPhoto() {
		return dogPhoto;
	}

	public void setDogPhoto(String dogPhoto) {
		this.dogPhoto = dogPhoto;
	}

	public boolean isKyosei() {
		return kyosei;
	}

	public void setKyosei(boolean kyosei) {
		this.kyosei = kyosei;
	}

	public LocalDate getDogRegist() {
		return dogRegist;
	}

	public void setDogRegist(LocalDate dogRegist) {
		this.dogRegist = dogRegist;
	}

	public String getRemarks1() {
		return remarks1;
	}

	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}

	public String getRemarks2() {
		return remarks2;
	}

	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}

	public String getRemarks3() {
		return remarks3;
	}

	public void setRemarks3(String remarks3) {
		this.remarks3 = remarks3;
	}

	public String getRemarks4() {
		return remarks4;
	}

	public void setRemarks4(String remarks4) {
		this.remarks4 = remarks4;
	}

	public String getRemarks5() {
		return remarks5;
	}

	public void setRemarks5(String remarks5) {
		this.remarks5 = remarks5;
	}

	public String getInjection() {
		return injection;
	}

	public void setInjection(String injection) {
		this.injection = injection;
	}

	public String getRabies() {
		return rabies;
	}

	public void setRabies(String rabies) {
		this.rabies = rabies;
	}
	
//ユーザーテーブル用ゲッターセッター生成
	public String getUserNameId() {
		return userNameId;
	}

	public void setUserNameId(String userNameId) {
		this.userNameId = userNameId;
	}

	public String getRuby() {
		return ruby;
	}

	public void setRuby(String ruby) {
		this.ruby = ruby;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getuPhone() {
		return uPhone;
	}

	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}

	public String getuPhone2() {
		return uPhone2;
	}

	public void setuPhone2(String uPhone2) {
		this.uPhone2 = uPhone2;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isUserUniqueId() {
		return userUniqueId;
	}

	public void setUserUniqueId(boolean userUniqueId) {
		this.userUniqueId = userUniqueId;
	}

	public int getUserSchoolId() {
		return userSchoolId;
	}

	public void setUserSchoolId(int userSchoolId) {
		this.userSchoolId = userSchoolId;
	}
	//追加したやつ
	public void IdPw(String userNameId, String pw) {
		this.userNameId = userNameId;
		this.pw = pw;
	}

	public void IdPw() {
		this.userNameId = "";
		this.pw = "";
	}

//スクールテーブル用ゲッターセッター生成
	public int getSchoolId() {
		return SchoolId;
	}

	public void setSchoolId(int schoolId) {
		SchoolId = schoolId;
	}

 //うんちテーブル用ゲッターセッターの生成
	public int getPoopId() {
		return poopId;
	}

	public void setPoopId(int poopId) {
		this.poopId = poopId;
	}

	public String getTlName() {
		return tlName;
	}

	public void setTlName(String tlName) {
		this.tlName = tlName;
	}

	public LocalDateTime getNowTime() {
		return nowTime;
	}

	public void setNowTime(LocalDateTime nowTime) {
		this.nowTime = nowTime;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getHardness() {
		return hardness;
	}

	public void setHardness(int hardness) {
		this.hardness = hardness;
	}

	public boolean isAbnormal() {
		return abnormal;
	}

	public void setAbnormal(boolean abnormal) {
		this.abnormal = abnormal;
	}

	public int getPoopDogId() {
		return PoopDogId;
	}

	public void setPoopDogId(int poopDogId) {
		PoopDogId = poopDogId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
//カレンダーテーブル用ゲッターセッターの生成
	
	public int getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(int calendarId) {
		this.calendarId = calendarId;
	}

	public LocalDate getCalendarDate() {
		return calendarDate;
	}

	public void setCalendarDate(LocalDate calendarDate) {
		this.calendarDate = calendarDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getCalendarMemo() {
		return calendarMemo;
	}

	public void setCalendarMemo(String calendarMemo) {
		this.calendarMemo = calendarMemo;
	}

	public int getCalendarDogId() {
		return calendarDogId;
	}

	public void setCalendarDogId(int calendarDogId) {
		this.calendarDogId = calendarDogId;
	}	
	
	
//ワンコテーブル用ゲッターセッターの生成

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public boolean getFood() {
		return food;
	}

	public void setFood(boolean food) {
		this.food = food;
	}

	public int getWalk() {
		return walk;
	}

	public void setWalk(int walk) {
		this.walk = walk;
	}

	public boolean isReportState() {
		return reportState;
	}

	public void setReportState(boolean reportState) {
		this.reportState = reportState;
	}

	public String getTraining() {
		return training;
	}

	public void setTraining(String training) {
		this.training = training;
	}

	public String getReportMemo() {
		return reportMemo;
	}

	public void setReportMemo(String reportMemo) {
		this.reportMemo = reportMemo;
	}

	public LocalDate getReportDate() {
		return reportDate;
	}

	public void setReportDate(LocalDate reportDate) {
		this.reportDate = reportDate;
	}

	public int getReportDogId() {
		return reportDogId;
	}

	public void setReportDogId(int reportDogId) {
		this.reportDogId = reportDogId;
	}
	
	
	 
}
	 
	 

