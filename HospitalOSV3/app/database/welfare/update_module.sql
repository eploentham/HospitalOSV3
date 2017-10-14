CREATE TABLE b_walfare_ucdata
(
  welfare_ucdata_id varchar(255) NOT NULL,
  welfare_ucdata_pid varchar(13),
  welfare_ucdata_prefix varchar(255),
  welfare_ucdata_fname varchar(255),
  welfare_ucdata_lname varchar(255),
  welfare_ucdata_sex varchar(255),
  welfare_ucdata_birthdate varchar(255),
  welfare_ucdata_age varchar(255),
  welfare_ucdata_subinscl varchar(255),
  welfare_ucdata_hmain varchar(255),
  welfare_ucdata_hsub varchar(255),
  welfare_ucdata_cardid varchar(255),
  welfare_ucdata_notdate varchar(255),
  welfare_ucdata_startdate varchar(255),
  welfare_ucdata_expdate varchar(255),
  welfare_ucdate_mainscl varchar(255),
  welfare_ucdata_nation varchar(255),
  welfare_ucdata_occupa varchar(255),
  welfare_ucdata_address varchar(255),
  welfare_ucdata_moo varchar(255),
  welfare_ucdata_road varchar(255),
  welfare_ucdata_tambon varchar(255),
  welfare_ucdata_amphur varchar(255),
  welfare_ucdata_province varchar(255),
  CONSTRAINT b_walfare_ucdata_pkey PRIMARY KEY (welfare_ucdata_id)
) 
WITH OIDS;
ALTER TABLE b_walfare_ucdata OWNER TO postgres;

CREATE TABLE b_welfare_insclasses
(
  welfare_insclasses_id varchar(255) NOT NULL,
  welfare_insclasses_number varchar(255),
  welfare_insclasses_description varchar(255),
  welfare_insclasses_active varchar(1),
  CONSTRAINT b_welfare_insclasses_pkey PRIMARY KEY (welfare_insclasses_id)
) 
WITH OIDS;
ALTER TABLE b_welfare_insclasses OWNER TO postgres;

CREATE TABLE b_welfare_map_plan
(
  b_contract_plans_id varchar(255) NOT NULL,
  welfare_insclasses_id varchar(255),
  plan_incup varchar(1),
  CONSTRAINT b_welfare_map_plan_pkey PRIMARY KEY (b_contract_plans_id)
) 
WITH OIDS;
ALTER TABLE b_welfare_map_plan OWNER TO postgres;

