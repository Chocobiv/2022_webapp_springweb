drop database if exists springweb;
create database springweb;
use springweb;
drop table if exists board;
create table board(
	bno int auto_increment primary key,
    btitle varchar(100),
    bcontent varchar(100)
);
