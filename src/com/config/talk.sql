create table tbl_account
(
	id int not null primary key auto_increment,
	name varchar(30) not null unique,
    password char(20) not null
)CHARACTER SET 'utf8'
COLLATE 'utf8_general_ci';

create table tbl_talk
(
	id int not null primary key auto_increment,
    content varchar(255),
    srcAccountId int not null,
    targetAccountId int not null,
    time datetime not null default now(),
    foreign key(srcAccountId) references tbl_account(id),
    foreign key(targetAccountId) references tbl_account(id)
)CHARACTER SET 'utf8'
COLLATE 'utf8_general_ci';