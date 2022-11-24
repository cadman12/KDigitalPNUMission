-- Member Table DDL
drop table member if exists;
create table member (
	id int auto_increment primary key,
	pass varchar(10) not null,
	name varchar(20) not null,
	regidate date default current_timestamp not null
);
insert into member (pass, name) values (‘pass1’, ‘name1’);
insert into member (pass, name) values (‘pass2’, ‘name2’);
Insert into member (pass, name) values (‘pass3’, ‘name3’);
Insert into member (pass, name) values (‘pass4’, ‘name4’);
Insert into member (pass, name) values (‘pass5’, ‘name5’);

-- Log Table DDL
drop table dblog if exists;
create table dblog (
	id int auto_increment primary key,
	method varchar(10) not null,
	sqlstring varchar(256) not null,
	regidate date default current_timestamp not null,
	success boolean default true
);
