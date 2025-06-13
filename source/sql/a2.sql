create table WANKO (
		dogName varchar(50) NOT NULL,
		dogBreed varchar(50) NOT NULL,
		dogBirth date NOT NULL,
		gender boolean NOT NULL,
		state varchar(300) NOT NULL,
		wakuchin varchar(300) NOT NULL,
		dogId int NOT NULL AUTO_INCREMENT,
		FOREIGN KEY (nameId) varchar(50) NOT NULL ,
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