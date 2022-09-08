create table app_users (
    id varchar(128) not null,
    first_name varchar(255),
    last_name varchar(255),
    primary key (id),
    unique (id)

) ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

create table tasks (
    id varchar(128) not null,
    task_data varchar(512),
    acquired_by varchar(128),
    primary key (id),
    unique (id),
    foreign key (acquired_by) references app_users (id)

) ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;
