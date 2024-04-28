create table book_crosser.books
(
    id          bigint auto_increment
        primary key,
    title       varchar(100) not null,
    author      varchar(100) not null,
    isbn        varchar(20)  null,
    owner_id    bigint       not null,
    uploader_id bigint       not null,
    description text         null,
    cover_url   varchar(200) null,
    longitude   double       null,
    latitude    double       null,
    status      varchar(20)  null,
    created_at  datetime     not null,
    updated_at  datetime     not null,
    constraint isbn
        unique (isbn),
    constraint books_ibfk_1
        foreign key (owner_id) references book_crosser.users (id),
    constraint books_ibfk_2
        foreign key (uploader_id) references book_crosser.users (id)
);

create index owner_id
    on book_crosser.books (owner_id);

create index uploader_id
    on book_crosser.books (uploader_id);

