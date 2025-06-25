create database a2;

use a2;


create table USER(
		userNameId varchar(50) PRIMARY KEY,
		ruby varchar(50) NOT NULL ,
		birth date ,
		name varchar(50) NOT NULL ,
		pw varchar(50) NOT NULL ,
		uPhone varchar(50) NOT NULL ,
		uPhone2 varchar(50)  ,
		address varchar(50) NOT NULL ,
		UserUniqueId boolean NOT NULL ,
		userSchoolId INT NOT NULL
		);
		


create table WANKO (
		dogName varchar(50) NOT NULL,
		dogBreed varchar(50) NOT NULL,
		dogBirth date NOT NULL,
		gender boolean NOT NULL, 
		state varchar(300),
		wakuchin varchar(300) NOT NULL,
		wankoDogId int PRIMARY KEY AUTO_INCREMENT,
		wankoNameId varchar(50),
		FOREIGN KEY (wankoNameId) REFERENCES USER(userNameId) ,
		dogPhoto varchar(300),
		kyosei boolean NOT NULL,
		dogRegist date,
		remarks1 varchar(300),
		remarks2 varchar(300),
		remarks3 varchar(300),
		remarks4 varchar(300),
		remarks5 varchar(300),
		injection varchar(300),
		rabies varchar(300)
		
		);
	

create table POOP (
		poopId int PRIMARY KEY AUTO_INCREMENT,
		tName varchar(50),
		nowTime time,
		photo  varchar(300),
		color INT,
		hardness INT,
		abnormal boolean NOT NULL,
		PoopDogId int,
		FOREIGN KEY (PoopDogId) REFERENCES WANKO(wankoDogId),
		memo varchar(300),
		date datetime default CURRENT_TIMESTAMP,
		check(color BETWEEN 1 AND 5),
		check(hardness BETWEEN 1 AND 5)
		);


		
create table CALENDAR(
		calendarId int PRIMARY KEY AUTO_INCREMENT,
		calendarDate date NOT NULL,
		title varchar(50),
		time time,
		calendarMemo varchar(300),
		calendarDogId int,
		FOREIGN KEY (calendarDogId) REFERENCES WANKO(wankoDogId)
		);

		




create table REPORT(
		reportId int PRIMARY KEY AUTO_INCREMENT,
		food boolean,
		walk int NOT NULL,
		reportState boolean NOT NULL, 
		training varchar(300) NOT NULL,
		reportMemo varchar(300),
		reportDate date,
		reportDogId int,
		FOREIGN KEY (reportDogId) REFERENCES WANKO(wankoDogId)
		);

		CREATE TABLE chat (
    chat_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id_speaker VARCHAR(255) NOT NULL,
    user_id_listener VARCHAR(255) NOT NULL,
    talk VARCHAR(500),
    image VARCHAR(100),
    `check` INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
		

		

INSERT INTO USER(
		userNameId,ruby,birth,name,pw,uPhone,uPhone2,address,userUniqueId,userSchoolId
)		VALUES(
		'sample0000.co.jp','さんぷる　たろう','2000-01-01','三分琉　太郎','0000','00000000001','',
		'東京都新宿区',false,1
);
INSERT INTO USER(
		userNameId,ruby,birth,name,pw,uPhone,uPhone2,address,userUniqueId,userSchoolId
)  VALUES(
		'Morisou.ne.jp','もりた　そう','2015-05-05','森田　颯','morisou','050-0022-0022','',
		'神奈川県川崎市2-22-2',FALSE,2
);

INSERT INTO USER(
		userNameId,ruby,birth,name,pw,uPhone,uPhone2,address,userUniqueId,userSchoolId
)  		VALUES(
		'koufuku.co.jp','ふくもと　こうき','2003-03-25','福本　幸稀','koufuku','050-0033-0033','',
		'東京都立川市3-33-3',FALSE,2
);
INSERT INTO USER(
		userNameId,ruby,birth,name,pw,uPhone,uPhone2,address,userUniqueId,userSchoolId
)  		VALUES(
		'dojouser1@plusdojo.jp','SEプラス1',NULL,'SEplus1','#SEplus2025SEplus','000-1111-2222','',
		'東京都千代田区',TRUE,1
);
INSERT INTO USER(
		userNameId,ruby,birth,name,pw,uPhone,uPhone2,address,userUniqueId,userSchoolId
)  		VALUES(
		'dojouser2@plusdojo.jp','SEプラス2',NULL,'SEplus2','#SEplus2025SEplus','000-2222-3333','',
		'東京都千代田区',TRUE,2
);
INSERT INTO USER(
		userNameId,ruby,birth,name,pw,uPhone,uPhone2,address,userUniqueId,userSchoolId
)  		VALUES(
		'dojouser3@plusdojo.jp','SEプラス3','2000-01-11','SEplus3','#SEplus2025SEplus','000-3333-4444','',
		'東京都千代田区',FALSE,1
);



INSERT INTO WANKO(
		dogName,dogBreed,dogBirth,gender,state,
		wakuchin,wankoNameId,dogPhoto,kyosei,dogRegist,
		remarks1,remarks2,remarks3,remarks4,remarks5,injection,rabies
)		VALUES(
		'さん','コーギー','2010-10-10',TRUE,'元気いっぱい走り回っている',
		'upload/wakuchin/ワクチン歴.png','sample0000.co.jp',
		'upload/dogphoto/sampleわんこ.jpg',TRUE,'2015-10-10','コーギーだよ',
		'','','','','upload/injection/sample.png',''
);
INSERT INTO WANKO(
		dogName,dogBreed,dogBirth,gender,state,
		wakuchin,wankoNameId,dogPhoto,kyosei,dogRegist,
		remarks1,remarks2,remarks3,remarks4,remarks5,injection,rabies
)		VALUES(
		'アンディ','トイプードル','2020-08-15',TRUE,'元気いっぱい走り回っている',
		'upload/wakuchin/andy.waku.png','Morisou.ne.jp',
		'upload/dogphoto/andy.jpg',TRUE,'2025-06-30','アンディだよ',
		'','','','','','upload/rabies/andy.ra.png'
);

INSERT INTO WANKO(
		dogName,dogBreed,dogBirth,gender,state,
		wakuchin,wankoNameId,dogPhoto,kyosei,dogRegist,
		remarks1,remarks2,remarks3,remarks4,remarks5,injection,rabies
)		VALUES(
		'らん','シュナウザー','2015-03-22',FALSE,'甘えん坊な性格',
		'upload/wakuchin/ran.waku.png','koufuku.co.jp',
		'upload/dogphoto/ran.jpg',FALSE,'2025-06-25','らんだよ',
		'','','','','','upload/rabies/ran.ra.png'
);
INSERT INTO WANKO(
		dogName,dogBreed,dogBirth,gender,state,
		wakuchin,wankoNameId,dogPhoto,kyosei,dogRegist,
		remarks1,remarks2,remarks3,remarks4,remarks5,injection,rabies
)		VALUES(
		'ぷらす','ポメラニアン','2016-11-22',FALSE,'おとなしい性格',
		'upload/wakuchin/waku.webp','dojouser3@plusdojo.jp',
		'upload/dogphoto/pome.webp',FALSE,'2025-06-27','ぷらすだよ',
		'','','','','upload/injection/inje.png',''
);


INSERT INTO POOP(
		tName,photo,color,hardness,abnormal,PoopDogId,memo
)		VALUES(
		'わんたトレーナー','upload/poop/unchi.png','3','3',FALSE,1,'元気でいい子にしてました。'
);
INSERT INTO POOP(
		tName,photo,color,hardness,abnormal,PoopDogId,memo
)		VALUES(
		'にゃんたトレーナー','upload/poop/andy.po.png','3','3',FALSE,2,'いつもどおりでした。'
);

INSERT INTO POOP(
		tName,photo,color,hardness,abnormal,PoopDogId,memo
)		VALUES(
		'にゃんたトレーナー','upload/poop/ran.po.png','1','4',TRUE,3,'少し調子が悪そうです。'
);
INSERT INTO POOP(
		tName,photo,color,hardness,abnormal,PoopDogId,memo
)		VALUES(
		'わんたトレーナー','upload/poop/se.png','3','3',FALSE,4,'元気でいい子にしてました。'
);

INSERT INTO CALENDAR(
		calendarDate,title,time,calendarMemo,calendarDogId
)		VALUES(
		'2025-06-16','さんくん預かり','14:00','トレーニング内容(~)',1
);
INSERT INTO CALENDAR(
		calendarDate,title,time,calendarMemo,calendarDogId
)		VALUES(
		'2025-06-30','アンディくん預かり','11:00','トレーニング内容(~)',2
);
INSERT INTO CALENDAR(
		calendarDate,title,time,calendarMemo,calendarDogId
)		VALUES(
		'2025-06-25','らんちゃん預かり','13:00','トレーニング内容(~)',3
);
INSERT INTO CALENDAR(
		calendarDate,title,time,calendarMemo,calendarDogId
)		VALUES(
		'2025-06-27','プラスくん預かり','10:00','トレーニング内容(~)',4
);

INSERT INTO REPORT(
		food,walk,reportState,training,reportMemo,reportDate,reportDogId
)		VALUES(
		FALSE,50,TRUE,'散歩５０分、お座り覚える','ご飯をあまり食べませんでした。','2025-06-16',1
);
INSERT INTO REPORT(
		food,walk,reportState,training,reportMemo,reportDate,reportDogId
)		VALUES(
		TRUE,30,TRUE,'散歩３０分、お座り覚える','お座り覚えることができ、元気に過ごしていました。','2025-06-21',2
);
INSERT INTO REPORT(
		food,walk,reportState,training,reportMemo,reportDate,reportDogId
)		VALUES(
		TRUE,50,TRUE,'散歩５０分、まて、ふせ覚える','散歩楽しんでいました。','2025-06-22',3
);
INSERT INTO REPORT(
		food,walk,reportState,training,reportMemo,reportDate,reportDogId
)		VALUES(
		TRUE,50,TRUE,'散歩５０分、まて、ふせ覚える','散歩楽しんでいました。','2025-06-29',4
);


















