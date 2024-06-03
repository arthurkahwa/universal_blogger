create table tbluser
(
    id serial
        constraint tbluser_pk
            primary key,
    username VARCHAR not null,
    email      varchar not null
);

create table tblpost
(
    id       serial
        constraint tblpost_pk
            primary key,
    userid integer not null
        constraint tblpost_tbluser_id_fk
            references tbluser on delete cascade on update cascade,
    title    varchar not null,
    body     varchar not null
);
