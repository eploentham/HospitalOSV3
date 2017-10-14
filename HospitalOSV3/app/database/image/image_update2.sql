CREATE TABLE b_image_config_pics (
    b_config_pics_id character varying(250) NOT NULL,
    config_pics_url_view character varying(255),
    config_pics_path_save character varying(255),
    config_pics_running_hn character varying(255),
    config_pics_era character varying(255)
);

CREATE UNIQUE INDEX image_image_link
    ON public.image_image_link( image_link_id);

CREATE UNIQUE INDEX image_path_pkey
    ON public.image_path(code);

CREATE UNIQUE INDEX image_version_pkey
    ON public.image_version(code);

insert into image_version values ('2','1.0.140807','Start From Hospital 3.7.160907');


