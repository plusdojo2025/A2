create database a2;



create table USER(
		nameId varchar(50) PRIMARY KEY,
		ruby varchar(50) NOT NULL ,
		birth date NOT NULL ,
		name varchar(50) NOT NULL ,
		pw varchar(50) NOT NULL ,
		uPhone varchar(50) NOT NULL ,
		uPhone2 varchar(50)  ,
		address varchar(50) NOT NULL ,
		schoolId INT NOT NULL,
		) ;
		
ALTER TABLE USER ADD uniqueId boolean; --TRUE企業、FALSE飼い主

		--TRUEオス、FALSEメス
create table WANKO (
		dogName varchar(50) NOT NULL,
		dogBreed varchar(50) NOT NULL,
		dogBirth date NOT NULL,
		gender boolean NOT NULL, 
		state varchar(300) NOT NULL,
		wakuchin varchar(300) NOT NULL,
		dogId int PRIMARY KEY AUTO_INCREMENT,
		nameId varchar(50),
		FOREIGN KEY (nameId) REFERENCES USER(nameId) ,
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
	
		--TRUEなし（１）、	FALSEあり（０）
create table POOP (
		poopId int PRIMARY KEY AUTO_INCREMENT,
		tName varchar(50) NOT NULL,
		nowTime datetime default CURRENT_TIMESTAMP,
		photo  varchar(300),
		color int NOT NULL,
		hardness int NOT NULL,
		abnormal boolean NOT NULL,
		dogId int,
		FOREIGN KEY (dogId) REFERENCES WANKO(dogId),
		memo varchar(300),
		date datetime default CURRENT_TIMESTAMP
		);
		--ウンチと硬さの５段階制限
		ALTER TABLE POOP
ADD CONSTRAINT chk_color CHECK (color BETWEEN 1 AND 5),
ADD CONSTRAINT chk_hardness CHECK (hardness BETWEEN 1 AND 5);
		
create table CALENDAR(
		carendarId int PRIMARY KEY AUTO_INCREMENT,
		date date NOT NULL,
		title varchar(50),
		time time,
		calendarMemo varchar(300),
		dogId int,
		FOREIGN KEY (dogId) REFERENCES WANKO(dogId)
		);

		



/*timeだと何時に散歩したのかintだと何分散歩したのか*/--T食べた　F食べてない TRUE異常なし、FALSE異常あり
create table REPORT(
		reportId int PRIMARY KEY AUTO_INCREMENT,
		food boolean,
		walk int NOT NULL,
		state boolean NOT NULL, 
		training varchar(300) NOT NULL,
		reportMemo varchar(300),
		reportDate date,
		dogId int,
		FOREIGN KEY (dogId) REFERENCES WANKO(dogId)
		);
		--テーブル作成ここまで
		--中身これから
		
		
--schoolIDにオートインクリメントで番号入れ		
INSERT INTO SCHOOL VALUES ();
INSERT INTO SCHOOL VALUES ();		
		
/*中身とりあえず１つデータ入れ*/
INSERT INTO USER(
		nameId,ruby,birth,name,pw,uPhone,uPhone2,address,uniqueId,schoolId
)		VALUES(
		'sample0000.co.jp','さんぷる　たろう','2000-01-01','三分琉　太郎','0000','00000000001','',
		'東京都新宿区',false,'1'
);


--dogIDはAIだから指定しない　　TRUE文字列じゃないから''なし
INSERT INTO WANKO(
		dogName,dogBreed,dogBirth,gender,state,
		wakuchin,nameId,dogPhoto,kyosei,dogRegist,
		remarks1,remarks2,remarks3,remarks4,remarks5,injection,rabies
)		VALUES(
		'さん','コーギー','2010-10-10',TRUE,'元気いっぱい走り回っている',
		'upload/wakuchin/ワクチン歴.png','sample0000.co.jp',
		'upload/dogphoto/sampleわんこ.jpg',TRUE,'2015-10-10','コーギーだよ',
		'','','','','upload/injection/sample.png',''
);

INSERT INTO POOP(
		tName,photo,color,hardness,abnormal,dogId,memo
)		VALUES(
		'わんたトレーナー','upload/poop/unchi.png','3','3',FALSE,'1','元気でいい子にしてました。'
);

INSERT INTO CALENDAR(
		date,title,time,calendarMemo,dogId
)		VALUES(
		'2025-06-16','さんくん預かり','14:00','トレーニング内容(~)','1'
);

INSERT INTO REPORT(
		food,walk,state,training,reportMemo,reportDate,dogId
)		VALUES(
		FALSE,50,TRUE,'散歩５０分、お座り覚える','ご飯をあまり食べませんでした。','2025-06-16','1'
);


INSERT INTO USER(
		nameId,ruby,birth,name,pw,uPhone,uPhone2,address,uniqueId,schoolId
)  VALUES(
		'Morisou.ne.jp','もりた　そう','2015-05-05','森田　颯','morisou','050-0022-0022','',
		'神奈川県川崎市2-22-2',FALSE,2
);

INSERT INTO USER(
		nameId,ruby,birth,name,pw,uPhone,uPhone2,address,uniqueId,schoolId
)  		VALUES(
		'koufuku.co.jp','ふくもと　こうき','2003-03-25','福本　幸稀','koufuku','050-0033-0033','',
		'東京都立川市3-33-3',FALSE,2
);

INSERT INTO WANKO(
		dogName,dogBreed,dogBirth,gender,state,
		wakuchin,nameId,dogPhoto,kyosei,dogRegist,
		remarks1,remarks2,remarks3,remarks4,remarks5,injection,rabies
)		VALUES(
		'アンディ','トイプードル','2020-08-15',TRUE,'元気いっぱい走り回っている',
		'upload/wakuchin/andy.waku.png','Morisou.ne.jp',
		'upload/dogphoto/andy.jpg',TRUE,'2025-06-30','アンディだよ',
		'','','','','','upload/rabies/andy.ra.png'
);

INSERT INTO WANKO(
		dogName,dogBreed,dogBirth,gender,state,
		wakuchin,nameId,dogPhoto,kyosei,dogRegist,
		remarks1,remarks2,remarks3,remarks4,remarks5,injection,rabies
)		VALUES(
		'らん','シュナウザー','2015-03-22',FALSE,'甘えん坊な性格',
		'upload/wakuchin/ran.waku.png','koufuku.co.jp',
		'upload/dogphoto/ran.jpg',FALSE,'2025-06-25','らんだよ',
		'','','','','','upload/rabies/ran.ra.png'
);

INSERT INTO POOP(
		tName,photo,color,hardness,abnormal,dogId,memo
)		VALUES(
		'にゃんたトレーナー','upload/poop/andy.po.png','3','3',FALSE,2,'いつもどおりでした。'
);

INSERT INTO POOP(
		tName,photo,color,hardness,abnormal,dogId,memo
)		VALUES(
		'にゃんたトレーナー','upload/poop/ran.po.png','1','4',TRUE,3,'少し調子が悪そうです。'
);

INSERT INTO CALENDAR(
		date,title,time,calendarMemo,dogId
)		VALUES(
		'2025-06-30','アンディくん預かり','11:00','トレーニング内容(~)',2
);
INSERT INTO CALENDAR(
		date,title,time,calendarMemo,dogId
)		VALUES(
		'2025-06-25','らんちゃん預かり','13:00','トレーニング内容(~)',3
);

INSERT INTO REPORT(
		food,walk,state,training,reportMemo,reportDate,dogId
)		VALUES(
		TRUE,30,TRUE,'散歩３０分、お座り覚える','お座り覚えることができ、元気に過ごしていました。','2025-06-21',2
);
INSERT INTO REPORT(
		food,walk,state,training,reportMemo,reportDate,dogId
)		VALUES(
		TRUE,50,TRUE,'散歩５０分、まて、ふせ覚える','散歩楽しんでいました。','2025-06-22',3
);







