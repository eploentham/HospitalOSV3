--drop table history_dlocation;
alter table history_dlocation add history_dlocation_select_visit text;
alter table history_dlocation add history_dlocation_jasper_filename varchar(255);
--drop table history_version;
insert into history_version values ('hs0021234567890202','1.02.270110','Start From Hospital 3.9');

