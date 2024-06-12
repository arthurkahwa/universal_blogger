create sequence id_counter;

alter sequence id_counter owner to postgres;

create table if not exists users
(
    id       serial not null
        constraint users_pk
            primary key,
    username varchar
        constraint users_uk
            unique
);

alter table users
    owner to postgres;

alter sequence id_counter owned by users.id;

create table if not exists todo
(
    id        serial
        constraint todo_pk
            primary key,
    userid    integer not null
        constraint todo_users_id_fk
            references users,
    title     varchar,
    completed boolean
);

alter table todo
    owner to postgres;

create table if not exists albums
(
    id     serial
        constraint albums_pk
            primary key,
    userid integer
        constraint albums_users_id_fk
            references users,
    title  varchar
);

alter table albums
    owner to postgres;

create table if not exists photos
(
    id      serial
        constraint photos_pk
            primary key,
    albumid integer
        constraint photos_albums_id_fk
            references albums,
    title   varchar,
    url     varchar
);

alter table photos
    owner to postgres;

create table if not exists posts
(
    id     serial
        constraint posts_pk
            primary key,
    userid integer
        constraint posts_users_id_fk
            references users,
    title  varchar,
    body   varchar
);

alter table posts
    owner to postgres;

create table if not exists comments
(
    id      serial
        constraint comments_pk
            primary key,
    postoid integer
        constraint comments_posts_id_fk
            references posts,
    email   varchar,
    body    varchar
);

alter table comments
    owner to postgres;


