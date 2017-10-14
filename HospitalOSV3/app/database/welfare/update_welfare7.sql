CREATE INDEX "t_patient_id_fkey"
	ON "public"."t_welfare_directdraw_patient"("t_patient_id");
CREATE INDEX "t_billing_id_fkey"
	ON "public"."t_welfare_billtrans"("t_billing_id");
CREATE INDEX "t_welfare_billtrans_id_fkey"
	ON "public"."t_welfare_opbills"("t_welfare_billtrans_id");

INSERT INTO s_welfare_version("s_version_id", "version_number", "version_description", "version_application_number", "version_database_number", "version_update_time")
VALUES('9701000000019', '6', 'Hospital OS, Community Edition', '1.06.120908', '3.15.120908', '2551-09-12 12:00:00');