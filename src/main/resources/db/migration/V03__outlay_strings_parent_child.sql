alter table if exists outlay_rows
    add column if not exists parent bigint default null,
    add foreign key (parent) references outlay_rows on delete cascade,
drop column if exists outlay_group_id;



