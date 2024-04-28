create table book_crosser.book_comments
(
    id         bigint auto_increment
        primary key,
    book_id    bigint   not null,
    user_id    bigint   not null,
    content    text     not null,
    created_at datetime not null,
    updated_at datetime not null,
    constraint book_comments_ibfk_1
        foreign key (book_id) references book_crosser.books (id),
    constraint book_comments_ibfk_2
        foreign key (user_id) references book_crosser.users (id)
);

create index book_id
    on book_crosser.book_comments (book_id);

create index user_id
    on book_crosser.book_comments (user_id);

