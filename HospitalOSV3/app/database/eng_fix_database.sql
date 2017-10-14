
delete from f_accident_highway_inout_type;
COPY f_accident_highway_inout_type (f_accident_highway_inout_type_id, accident_highway_inout_type_description) FROM stdin;
1	export
0	import
\.

delete from f_accident_patient_status;
COPY f_accident_patient_status (f_accident_patient_status_id, accident_patient_status_description) FROM stdin;
1	injure
0	normal
2	medical leave more than 20 days
3	maimed
4	death
\.

delete from f_accident_patient_vechicle_type;
COPY f_accident_patient_vechicle_type (f_accident_patient_vechicle_type_id, accident_patient_vechicle_type_description) FROM stdin;
0	unknown
1	pedestrain
2	bicycle
3	motorcycle
4	private car
5	light truck
6	truck
8	bus
13	train
\.

delete from f_accident_protection_type;
COPY f_accident_protection_type (f_accident_protection_type_id, accident_protection_type_description) FROM stdin;
1	no safety belt and helmet
0	unknown
2	used safety belt and helmet
\.

delete from f_accident_road_type;
COPY f_accident_road_type (f_accident_road_type_id, accident_road_type_description) FROM stdin;
1	local road
0	main road
\.

delete from f_accident_victim_type;
COPY f_accident_victim_type (f_accident_victim_type_id, accident_victim_type_description) FROM stdin;
1	driver
2	passenger
4	pedestrain
0	unknown
\.

delete from f_address;
COPY f_address (f_address_id, address_description, address_tambon_type, address_amphur_id, address_changwat_id, address_region) FROM stdin;
100000	Bangkok	1	100000	100000	1
100100	Ladprao	2	100000	100000	1
100102	Pratumwan	3	100100	100000	1
100103	Bang Rak	3	100100	100000	1
100104	Bang Sue	3	100100	100000	1
100105	Muang Tong	3	100100	100000	1
\.

delete from f_answer;
COPY f_answer (f_answer_id, answer_description, answer_description_a, answer_description_b, answer_description_c, answer_description_d, answer_description_e) FROM stdin;
0	no	none	abnomal	not flow	not receive	not lack
1	yes	exist	normal	flow	receive	lack
\.

delete from f_chronic_discharge_status;
COPY f_chronic_discharge_status (f_chronic_discharge_status_id, chronic_discharge_status_description) FROM stdin;
1	complete recovered
2	death
3	recovering
4	unknown
5	observe/surveil
6	lost contact
7	complete treatment
8	inactive disease
9	refuse treatment
\.

delete from f_death_place_type;
COPY f_death_place_type (f_death_place_type_id, death_place_type_description) FROM stdin;
1	In
2	Out
\.

delete from f_employee_authentication;
COPY f_employee_authentication (f_employee_authentication_id, employee_authentication_description) FROM stdin;
1	Register
2	Nurse
3	Doctor
4	LAB
5	X-RAY
6	Pharmacy
7	Cashier
8	Medical Stats Officer
9	Admin
10	One Stop Service
11	Insurance Plan Officer
12	PCU
\.

delete from f_item_billing_group;
COPY f_item_billing_group (f_item_billing_group_id, item_billing_group_description) FROM stdin;
00	Lab
01	X-ray
02	Special laboratory
03	Surgeon
04	Other treatment
05	Drug and medical supply
06	ICU
07	Room
08	Food 
09	Other
\.

delete from f_item_day_time;
COPY f_item_day_time (f_item_day_time_id, item_day_time_description) FROM stdin;
0	none
1	morning
2	noon
3	evening
4	night
\.

delete from f_item_group;
COPY f_item_group (f_item_group_id, item_group_description) FROM stdin;
1	Drug
2	LAB
3	X-Ray
4	Supply
5	Service
\.

delete from f_opd_type;
delete from f_order_status;
COPY f_order_status (f_order_status_id, order_status_description) FROM stdin;
0	unconfirmed
1	confirmed
2	executed
3	discontinued
4	reported
5	dispensed
\.

delete from f_patient_area_status;
COPY f_patient_area_status (f_patient_area_status_id, patient_area_status_description) FROM stdin;
1	Residence - stay
2	Residence - absent
3	Non-residence
\.

delete from f_patient_blood_group;
COPY f_patient_blood_group (f_patient_blood_group_id, patient_blood_group_description) FROM stdin;
5	O
4	AB
3	B
2	A
1	unknown
\.

delete from f_patient_discharge_status;
COPY f_patient_discharge_status (f_patient_discharge_status_id, patient_discharge_status_description) FROM stdin;
1	dead
2	transfered
3	disappeared
\.

delete from f_patient_education_type;
COPY f_patient_education_type (f_patient_education_type_id, patient_education_type_description) FROM stdin;
01	uneducated
02	Primary school
03 	Secondary school
04	High school
05	Bachelor degree
06	Master degree or higher
11	unknown
\.

delete from f_patient_family_status;
COPY f_patient_family_status (f_patient_family_status_id, patient_family_status_description) FROM stdin;
1	family head
2	family member
\.

delete from f_patient_foreigner;
COPY f_patient_foreigner (f_patient_foreigner_id, patient_foreigner_description) FROM stdin;
4	tourist
3	registered labor
2	unregistered labor
1	none
\.

delete from f_patient_marriage_status;
COPY f_patient_marriage_status (f_patient_marriage_status_id, patient_marriage_status_description) FROM stdin;
1	single
2	couple
3	separate
4	divorce
5	widow
6	monk
\.

delete from f_patient_nation;
COPY f_patient_nation (f_patient_nation_id, patient_nation_description) FROM stdin;
0	unknown
1	Austria
2	England
3	France
4	Scotland
5	Thailand
6	USA
\.

delete from f_patient_occupation;
COPY f_patient_occupation (f_patient_occupation_id, patient_occupation_description) FROM stdin;
000	unemployment
101	actor
102	architect
103	dentist
105	doctor
106	engineer
107	surgeon
\.

delete from f_patient_prefix;
COPY f_patient_prefix (f_patient_prefix_id, patient_prefix_description, f_sex_id, f_tlock_id) FROM stdin;
002	Mr.	1	1
003	Miss	2	1
004	Mrs.	2	1
\.

delete from f_patient_relation;
COPY f_patient_relation (f_patient_relation_id, patient_relation_description) FROM stdin;
00	unknown
01	father
02	mother
03	brother
04	sister
05	spouse
\.

delete from f_patient_religion;
COPY f_patient_religion (f_patient_religion_id, patient_religion_description) FROM stdin;
0	unknown
1	Buddhism
2	Christianity
\.

delete from f_patient_tlock;
COPY f_patient_tlock (f_patient_tlock_id, patient_tlock_description) FROM stdin;
0	rarely used
1	often
2	change request
\.

delete from f_service_group;
COPY f_service_group (f_service_group_id, service_group_description) FROM stdin;
1	registration
2	screening
3	diagnosis
4	pharmacy
5	cashier
6	setup
7	medical stats
8	others
\.

delete from f_sex;
COPY f_sex (f_sex_id, sex_description) FROM stdin;
1	male
2	female
3	unknown
\.

delete from f_visit_ipd_discharge_status;
COPY f_visit_ipd_discharge_status (f_visit_ipd_discharge_status_id, visit_ipd_discharge_status_description) FROM stdin;
1	cured
2	improve
3	none improve
4	nomal give birth
5	not give birth
8	stillbirth
9	death
\.

delete from f_visit_ipd_discharge_type;
COPY f_visit_ipd_discharge_type (f_visit_ipd_discharge_type_id, visit_ipd_discharge_type_description) FROM stdin;
1	doctor allow
2	refuse treatment
3	escape
4	refer
5	other
8	dead with autopsy
9	dead without autopsy
\.

delete from f_visit_nutrition_level;
COPY f_visit_nutrition_level (f_visit_nutrition_level_id, visit_nutrition_level_description) FROM stdin;
N	normal
1	level 1
2	level 2
3	level 3
\.

delete from f_visit_opd_discharge_status;
COPY f_visit_opd_discharge_status (f_visit_opd_discharge_status_id, visit_opd_discharge_status_description) FROM stdin;
51	Back Home
52	Dead in OPD
53	Consult
54	Refer
55	Dead Outside Hospital
\.

delete from f_visit_service_status;
COPY f_visit_service_status (f_visit_service_status_id, visit_service_status_description) FROM stdin;
1	wait
2	diagnosis
3	finish
\.

delete from f_visit_status;
COPY f_visit_status (f_visit_status_id, visit_status_description) FROM stdin;
1	in process
2	wait icd
3	end process
4	cancelled
\.

delete from f_visit_type;
COPY f_visit_type (f_visit_type_id, visit_type_description) FROM stdin;
0	OPD
1	IPD
\.

delete from f_xray_film_size;
COPY f_xray_film_size (f_xray_film_size_id, xray_film_size_description) FROM stdin;
1	14" x 17"
2	12" x 15"
3	11" x 14"
4	7" x 17"
5	10" x 12"
6	8" x 10"
7	14" x 14"
\.


