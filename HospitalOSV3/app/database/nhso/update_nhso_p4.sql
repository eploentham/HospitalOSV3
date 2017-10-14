
--เพิ่ม ตาราง เพื่อรองรับ Pattern ใหม่
--15-11-07
--aut
---ตาราง t_nhso_delivery
CREATE TABLE t_nhso_delivery (
	t_nhso_delivery_id character varying(255) NOT NULL,
	t_health_delivery_id character varying(255),
	nhso_delivery_pregnant_age character varying(255),
	CONSTRAINT t_nhso_delivery_pkey PRIMARY KEY (t_nhso_delivery_id)
);

--ดูแลหลังคลอดลูก
ALTER TABLE t_nhso_pp_care ADD COLUMN nhso_pp_care_navel_clean character varying(255);

--version 

INSERT INTO s_nhso_version("s_version_id", "version_number", "version_description", "version_application_number", "version_database_number", "version_update_time")
VALUES('9701000000017',	'4',	'Hospital OS, Community Edition',	'3.13.1150',	'0.1.161107',	'2550-11-16 18:00:00');

