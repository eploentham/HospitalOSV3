ALTER TABLE "b_image_config_pics"
	ADD CONSTRAINT "b_config_pics_pkey"
	PRIMARY KEY ("b_config_pics_id");
ALTER TABLE "image_image_link"
	ADD CONSTRAINT "image_link_pkey"
	PRIMARY KEY ("image_link_id");

insert into image_version values ('5','1.0.020610','Start From Hospital 3.7.160907');