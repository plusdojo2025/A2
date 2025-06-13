create database a2;

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
