create table if not exists users_entity
(
    id bigint not null primary key,
    entity_name text not null
);
create table if not exists users_entity_rows
(
    id bigint not null primary key,
    row_id bigint not null,
    entity_id bigint not null
);
create sequence if not exists SEQ_USERS_ENTITY increment 1 start 1;
create sequence if not exists SEQ_USERS_ENTITY_ROWS increment 1 start 1;

alter table if exists users_entity_rows
    add foreign key (row_id) references outlay_rows on delete cascade,
    add foreign key (entity_id) references users_entity on delete cascade;