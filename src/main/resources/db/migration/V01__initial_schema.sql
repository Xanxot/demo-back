create table if not exists outlay_group
(
    id bigint not null primary key,
    outlay_name text not null,
    is_deleted bool default false
);
create sequence if not exists SEQ_OUTLAY_GROUP increment 1 start 1;

