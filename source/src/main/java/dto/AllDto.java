package dto;

import java.io.Serializable;
import java.time.LocalDate;

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
	 private String rabies;		/*狂犬病*/

	 //ユーザーテーブル
	 private String userNameId;			/*飼い主ID*/
	 private String dogBreed;		/*ふりがな*/
	 private LocalDate dogBirth;	/*生年月日*/
	 private boolean gender;		/*飼い主の名前*/
	 private String state;			/*PW*/
	 private String wakuchin;		/*電話番号*/
	 private int dogId;				/*連絡先２*/
	 private String nameId;			/*住所*/
	 private String dogPhoto;		/*ワンコ写真*/
	 private boolean kyosei ;		/*識別ID*/
	 private LocalDate dogRegist;	/*スクールID*/
	 private String remarks1;		/*備考*/
	 
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

	public int getDogId() {
		return dogId;
	}

	public void setDogId(int dogId) {
		this.dogId = dogId;
	}

	public String getWankoNameId() {
		return nameId;
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
	
	 //ユーザーテーブル用ゲッターセッターの生成
}
	 
	 

