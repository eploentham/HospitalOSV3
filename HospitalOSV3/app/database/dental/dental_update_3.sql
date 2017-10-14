CREATE TABLE "f_dental_treatment_plan" ( 
	"dental_treatment_plan_id"	varchar(255) NOT NULL,
	"dental_treatment_plan"   	varchar(255) NULL,
	PRIMARY KEY("dental_treatment_plan_id")
);

INSERT INTO "f_dental_treatment_plan"("dental_treatment_plan_id", "dental_treatment_plan")
VALUES('1', 'ขูดหินปูน');

INSERT INTO "f_dental_treatment_plan"("dental_treatment_plan_id", "dental_treatment_plan")
VALUES('2', 'รักษาโรคปริทันต์');

INSERT INTO "f_dental_treatment_plan"("dental_treatment_plan_id", "dental_treatment_plan")
VALUES('3', 'อุดฟัน');

INSERT INTO "f_dental_treatment_plan"("dental_treatment_plan_id", "dental_treatment_plan")
VALUES('4', 'รักษารากฟัน');

INSERT INTO "f_dental_treatment_plan"("dental_treatment_plan_id", "dental_treatment_plan")
VALUES('5', 'ถอนฟัน');

INSERT INTO "f_dental_treatment_plan"("dental_treatment_plan_id", "dental_treatment_plan")
VALUES('6', 'ผ่าฟันคุด');

INSERT INTO "f_dental_treatment_plan"("dental_treatment_plan_id", "dental_treatment_plan")
VALUES('7', 'ใส่ฟัน');

INSERT INTO "f_dental_treatment_plan"("dental_treatment_plan_id", "dental_treatment_plan")
VALUES('8', 'ถ่ายภาพรังสี');

INSERT INTO "f_dental_treatment_plan"("dental_treatment_plan_id", "dental_treatment_plan")
VALUES('9', 'การรักษาอื่นๆ');

INSERT INTO "f_dental_treatment_plan"("dental_treatment_plan_id", "dental_treatment_plan")
VALUES('10', 'ฟันดี');

INSERT INTO public.f_dental_treatment_plan(dental_treatment_plan_id, dental_treatment_plan) 
    VALUES('11', 'กลุ่มรักษาทันตกรรมจากที่อื่น');

CREATE TABLE "f_dental_teeth" ( 
	"dental_teeth_id"	varchar(255) NOT NULL,
	"dental_teeth"   	varchar(255) NULL,
	PRIMARY KEY("dental_teeth_id")
);

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('11', '#11');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('12', '#12');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('13', '#13');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('14', '#14');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('15', '#15');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('16', '#16');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('17', '#17');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('18', '#18');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('21', '#21');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('22', '#22');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('23', '#23');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('24', '#24');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('25', '#25');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('26', '#26');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('27', '#27');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('28', '#28');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('31', '#31');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('32', '#32');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('33', '#33');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('34', '#34');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('35', '#35');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('36', '#36');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('37', '#37');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('38', '#38');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('41', '#41');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('42', '#42');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('43', '#43');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('44', '#44');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('45', '#45');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('46', '#46');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('47', '#47');

INSERT INTO "f_dental_teeth"("dental_teeth_id", "dental_teeth")
VALUES('48', '#48');

INSERT INTO public.b_item_subgroup(b_item_subgroup_id, item_subgroup_number, item_subgroup_description, f_item_group_id, item_subgroup_active) 
    VALUES('13000000000151', 'DT180100', 'กลุ่มรักษาทันตกรรมจากที่อื่น', '6', '1');

INSERT INTO s_dental_version VALUES ('9730000000004', '4', 'Dental Module Version 3', '1.4.070510', '1.3.070510', '2553-05-07 11:33:33');