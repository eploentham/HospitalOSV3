
 alter table tmp_f18_anc add vn varchar(255);
 alter table tmp_f18_appoint add vn varchar(255);
 alter table tmp_f18_card add vn varchar(255);
 alter table tmp_f18_chronic add vn varchar(255);
 alter table tmp_f18_death add vn varchar(255);
 alter table tmp_f18_diag add vn varchar(255);
 alter table tmp_f18_drug add vn varchar(255);
 alter table tmp_f18_epi add vn varchar(255);
 alter table tmp_f18_fp add vn varchar(255);
 alter table tmp_f18_home add vn varchar(255);
 alter table tmp_f18_mch add vn varchar(255);
 alter table tmp_f18_nutri add vn varchar(255);
 alter table tmp_f18_opa add vn varchar(255);
 alter table tmp_f18_person add vn varchar(255);
 alter table tmp_f18_pp add vn varchar(255);
 alter table tmp_f18_proced add vn varchar(255);
 alter table tmp_f18_service add vn varchar(255);
 alter table tmp_f18_surveil add vn varchar(255);
 alter table tmp_f18_woman add vn varchar(255);
 alter table tmp_pp_anc add vn varchar(255);
 alter table tmp_pp_cancer add vn varchar(255);
 alter table tmp_pp_consult add vn varchar(255);
 alter table tmp_pp_dent add vn varchar(255);
 alter table tmp_pp_dentchild add vn varchar(255);
 alter table tmp_pp_depression add vn varchar(255);
 alter table tmp_pp_dmht add vn varchar(255);
 alter table tmp_pp_epi add vn varchar(255);
 alter table tmp_pp_fp add vn varchar(255);
 alter table tmp_pp_grow add vn varchar(255); 
 alter table tmp_pp_papsmear add vn varchar(255);
 alter table tmp_pp_pnc add vn varchar(255); 
 alter table tmp_pp_service add vn varchar(255);
 alter table tmp_pp_thalassemia add vn varchar(255);

 INSERT INTO s_nhso_version("s_version_id", "version_number", "version_description", "version_application_number",  "version_database_number", "version_update_time")
 VALUES('9701000000122', '22', 'Hospital OS, Community Edition', '3.14.1051', '0.10.180110', '2553-01-18,12:00:00');

insert into s_script_update_log values ('nhso','update_nhso_ph22.sql',(select current_date) || ','|| (select  current_time),'update NHSO ph21 -> ph22');
