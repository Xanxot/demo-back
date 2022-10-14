create table if not exists outlay_rows
(
    id bigint not null primary key,
    outlay_group_id bigint not null,
    row_name text not null,
    salary bigint not null default 0,
    mim_exploitation bigint not null default 0,
    machine_operator_salary bigint not null default 0,
    materials bigint not null default 0,
    main_costs bigint not null default 0,
    support_costs bigint not null default 0,
    equipment_costs bigint not null default 0,
    overheads bigint not null default 0,
    estimated_profit bigint not null default 0,
    is_deleted bool default false,
    foreign key (outlay_group_id) references outlay_group on delete cascade
);
create sequence if not exists SEQ_OUTLAY_ROWS increment 1 start 1;