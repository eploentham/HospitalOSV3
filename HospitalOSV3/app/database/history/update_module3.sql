ALTER TABLE "history_dlocation"
	ADD CONSTRAINT "history_dlocation_pkey"
	PRIMARY KEY ("history_dlocation_id");
ALTER TABLE "history_version"
	ADD CONSTRAINT "history_version_pkey"
	PRIMARY KEY ("code");

insert into history_version values ('hs0021234567890206','1.03.020610','Start From Hospital 3.9');