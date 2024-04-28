create table book_crosser.drifting_request
(
    id           bigint auto_increment
        primary key,
    book_id      bigint      not null,
    requester_id bigint      not null,
    uploader_id  bigint      not null,
    status       varchar(20) not null,
    created_at   datetime    not null,
    updated_at   datetime    not null,
    owner_id     bigint      null,
    constraint unique_book_requester
        unique (book_id, requester_id),
    constraint drifting_request_ibfk_1
        foreign key (book_id) references book_crosser.books (id),
    constraint drifting_request_ibfk_2
        foreign key (requester_id) references book_crosser.users (id),
    constraint drifting_request_ibfk_3
        foreign key (uploader_id) references book_crosser.users (id),
    constraint fk_drifting_request_owner
        foreign key (owner_id) references book_crosser.users (id)
);

create index requester_id
    on book_crosser.drifting_request (requester_id);

create index uploader_id
    on book_crosser.drifting_request (uploader_id);

