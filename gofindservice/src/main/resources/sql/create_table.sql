create table IF NOT EXISTS tb_user(
     id varchar(50) primary key,
     username varchar(100) not null unique,
     password varchar(64) not null,
     email varchar(150),
     weichat varchar(100),
     gender varchar(1),
     location varchar(50),
     create_time TIMESTAMP default CURRENT_TIMESTAMP,
     last_modify_time TIMESTAMP default CURRENT_TIMESTAMP,
     role_type varchar(20) not null
 ) ENGINE=InnoDB;


