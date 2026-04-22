create table if not exists users(
	id int not null auto_increment,
	username varchar(60) not null,
	password varchar(60) not null,
	enabled int not null,
	primary key (id)
);

create table if not exists authorities(
	id int not null auto_increment,
	username varchar(60) not null,
	authority varchar(60) not null,
	primary key(id)
);

insert into users(username, password,enabled) values('Arkar','12345','1');
insert into authorities(username, authority) values('Arkar','write');