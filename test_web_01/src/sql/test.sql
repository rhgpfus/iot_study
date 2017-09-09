CREATE TABLE `user` (
	`user_no` INT(11) NOT NULL AUTO_INCREMENT,
	`id` VARCHAR(100) NULL DEFAULT NULL,
	`name` VARCHAR(100) NULL DEFAULT NULL,
	`password` VARCHAR(100) NULL DEFAULT NULL,
	`hobby` VARCHAR(200) NULL DEFAULT NULL,
	PRIMARY KEY (`user_no`),
	UNIQUE INDEX `id` (`id`)
);

create database jsp_study;
use jsp_study;

select * from user;

insert into user(id,name,password,hobby)
values('s','고똥','s','수면');

drop table board;
create table board(
b_num int AUTO_INCREMENT primary key,
title varchar(300) not null,
content text,
reg_date datetime not null,
writer int not null
);

select * from board;

insert into board(title, content, reg_Date, writer)
values('제목4','내용4',now(),4);


insert into user(id, name, password, hobby, admin)
values('3','삼번','3','여행',0);

alter table user
add column admin char(1);


select * from user;

update user
set admin ='1';

desc board;
