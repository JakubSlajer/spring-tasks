insert into `deutsche_telekom_test`.`app_users` (`id`, `first_name`, `last_name`) values ('User001', 'John Taragarien', 'Show');
insert into `deutsche_telekom_test`.`app_users` (`id`, `first_name`, `last_name`) values ('User002', '', 'Hodor');
insert into `deutsche_telekom_test`.`app_users` (`id`, `first_name`, `last_name`) values ('User003', 'John Dow', '');
insert into `deutsche_telekom_test`.`app_users` (`id`, `first_name`, `last_name`) values ('User004', null, '');
insert into `deutsche_telekom_test`.`app_users` (`id`, `first_name`, `last_name`) values ('User005', '', 'Targarien');

insert into `deutsche_telekom_test`.`tasks` (`id`, `task_data`, `acquired_by`) values ('1001', 'some data 1001', 'User001');
insert into `deutsche_telekom_test`.`tasks` (`id`, `task_data`, `acquired_by`) values ('1002', '', 'User002');
insert into `deutsche_telekom_test`.`tasks` (`id`, `task_data`, `acquired_by`) values ('1003', 'some data 1003', 'User003');
insert into `deutsche_telekom_test`.`tasks` (`id`, `task_data`, `acquired_by`) values ('1004', null, 'User001');
insert into `deutsche_telekom_test`.`tasks` (`id`, `task_data`, `acquired_by`) values ('1005', 'some data 1005', null);