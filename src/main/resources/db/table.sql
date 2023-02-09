create table user_tb(
    id int auto_increment primary key,
    username varchar unique not null,
    password varchar not null,
    email varchar not null,
    create_at timestamp not null
);

create table board_tb(
    id int auto_increment primary key,
    title varchar(100) not null,
    content longtext not null,
    user_id int not null,
    create_at timestamp not null
);