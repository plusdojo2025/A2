create database a2;


create table SCHOOL (
		schoolId int PRIMARY KEY
);


create table USER(
		nameId varchar(50) PRIMARY KEY,
		ruby varchar(50) NOT NULL ,
		birth date NOT NULL ,
		name varchar(50) NOT NULL ,
		pw varchar(50) NOT NULL ,
		uPhone varchar(50) NOT NULL ,
		uPhone2 varchar(50)  ,
		address varchar(50) NOT NULL ,
		schoolId INT,
		FOREIGN KEY (schoolId) REFERENCES SCHOOL(schoolId)
		) ;

		
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

		
create table CALENDAR(
		carendarId int PRIMARY KEY AUTO_INCREMENT,
		date date NOT NULL,
		title varchar(50),
		time date,
		carendarMemo varchar(300),
		dogId int,
		FOREIGN KEY (dogId) REFERENCES WANKO(dogId)
		);

		
ALTER TABLE USER ADD uniqueId boolean;


/*timeだと何時に散歩したのかintだと何分散歩したのか*/
create table REPORT(
		reportId int PRIMARY KEY AUTO_INCREMENT,
		food boolean,
		walk int NOT NULL,
		state boolean NOT NULL,
		training varchar(300) NOT NULL,
		reportMemo varchar(300),
		reportDate datetime,
		dogId int,
		FOREIGN KEY (dogId) REFERENCES WANKO(dogId)
		);


		
		
