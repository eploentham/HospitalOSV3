ALTER TABLE "public"."t_lab_event_log"
	ADD COLUMN "modify_date_time" varchar(255) NULL;

ALTER TABLE "public"."t_lab_event_log"
	ADD COLUMN "staff_modify" varchar(255) NULL;

ALTER TABLE "public"."t_lab_event_log"
	ADD COLUMN "active" varchar(1) NULL;

INSERT INTO s_lab_version VALUES ('9750000000002', '2', 'LAB, Community Edition', '1.06.020709', '1.06.020709', (select current_date) || ','|| (select current_time));

INSERT INTO s_script_update_log VALUES ('LAB_KVP','update_lab_kvp_2.sql',(select current_date) || ','|| (select current_time),'Alter table t_lab_event_log');