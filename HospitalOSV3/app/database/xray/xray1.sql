create table b_xraymodule_config(
server_path varchar(255),
delay varchar(255),
lasttime varchar(255),
url1 varchar(255),
url2 varchar(255),
PRIMARY KEY("server_path"));

create table t_xraymodule_log(
seq varchar(255),
order_number varchar(255),
xn varchar(255),
patient_hn varchar(255),
patient_dob varchar(255),
patient_other_name varchar(255),
patient_eng_name varchar(255),
patient_sex varchar(255),
patient_type varchar(255),
order_doctor_code varchar(255),
order_doctor_name varchar(255),
order_dept_code varchar(255),
order_dept_name varchar(255),
order_date varchar(255),
order_time varchar(255),
xray_code varchar(255),
xray_desc varchar(255),
modality varchar(255),
order_status varchar(255),
hos_odt varchar(255),
PRIMARY KEY("order_number")
);

create table s_xraymodule_version(
app_version varchar(255),
db_version varchar(255),
date_time varchar(255),
PRIMARY KEY("app_version")
);
insert into s_xraymodule_version values ('1.0.1','1.0.1','2552-09-08,12:12:00');
insert into b_xraymodule_config values ('/home/henbe/Desktop/','15000','2552-07-23,21:53:50');


