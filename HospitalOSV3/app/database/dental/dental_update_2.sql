ALTER TABLE "t_dental_treat"
	ADD COLUMN "t_diag_icd_9_id" varchar(255) NULL;
ALTER TABLE "t_dental_order"
	ADD COLUMN "t_diag_icd_9_id" varchar(255) NULL;
ALTER TABLE "b_dental_item"
	ADD COLUMN "b_icd9_id" varchar(255) NULL;
ALTER TABLE "s_dental_version"
	ADD CONSTRAINT "s_dental_version_pk"
	PRIMARY KEY ("s_dental_version_id");
INSERT INTO s_dental_version VALUES ('9730000000003', '3', 'Dental Module Version 2', '1.4.080410', '1.2.080410', '2553-04-08 08:33:33');