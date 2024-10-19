create table if not exists users(id identity primary key, name varchar(255) not null);
truncate table users;
insert into USERS(name) values('Lucy');
insert into USERS(name) values('Vlad');