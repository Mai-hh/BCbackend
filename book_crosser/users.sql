create table book_crosser.users
(
    id         bigint auto_increment
        primary key,
    username   varchar(50)  not null,
    email      varchar(100) not null,
    password   varchar(100) not null,
    avatar     varchar(200) null,
    bio        varchar(500) null,
    location   varchar(100) null,
    created_at datetime     not null,
    updated_at datetime     not null,
    latitude   double       null,
    longitude  double       null,
    constraint email
        unique (email)
);

