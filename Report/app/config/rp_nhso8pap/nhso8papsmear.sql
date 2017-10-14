--PAP SMEAR 8 FILE
select
--table 3.1 bppcs_emp
b_site.b_visit_office_id || substring(t_nhso_screen_cancer.t_nhso_screen_cancer_id,6) as emp_id
,substring(t_nhso_screen_cancer.t_nhso_screen_cancer_id,4) as papid
,case when (nc.employee_firstname is not null)
      then nc.employee_firstname
      else '' end as name_check
,case when (nr.employee_firstname is not null)
      then nr.employee_firstname
      else '' end as name_rec
--table 3.2 bppcs_his
,b_site.b_visit_office_id || substring(t_nhso_screen_cancer.t_nhso_screen_cancer_id,6) as histry_cyt
,screen_cancer_pap_smear_verginal as verginal
,screen_cancer_pap_smear_cervcl as cervcl
,screen_cancer_pap_smear_endocvil as endocvil
,screen_cancer_pap_smear_endomtl as endomtl
,screen_cancer_pap_smear_hcother as other
,case when (screen_cancer_pap_smear_hcother = '1')
      then screen_cancer_pap_smear_hcotr_comt
      else '' end as otr_comt
,substring(screen_cancer_pap_smear_para , 0, 5) as para 
,substring(screen_cancer_pap_smear_last , 0, 5) as last
,substring(screen_cancer_pap_smear_lmp , 0, 11) as lmp
,substring(screen_cancer_pap_smear_previous , 0, 101) as previous
,substring(screen_cancer_pap_smear_clinical , 0, 101) as clinical
,substring(screen_cancer_pap_smear_clinicaldx , 0, 101) as clinicaldx
,screen_cancer_pap_smear_lastpap as lastpap
--table 3.3 bppcs_cyt
,b_site.b_visit_office_id || substring(t_nhso_screen_cancer.t_nhso_screen_cancer_id,6) as cytoid
,screen_cancer_pap_smear_specimen as specimen
,screen_cancer_pap_smear_general as general
--table 3.4 bppcs_sat
,b_site.b_visit_office_id || substring(t_nhso_screen_cancer.t_nhso_screen_cancer_id,6) as satisfacid
,case when (screen_cancer_pap_smear_satisfac_id = '1')
      then screen_cancer_pap_smear_presence
      else '0' end as presence
,case when (screen_cancer_pap_smear_satisfac_id = '1')
      then screen_cancer_pap_smear_absence
      else '0' end as absence
,case when (screen_cancer_pap_smear_satisfac_id = '1')
      then screen_cancer_pap_smear_smear
      else '0' end as smear
,case when (screen_cancer_pap_smear_satisfac_id = '1')
      then screen_cancer_pap_smear_patial
      else '0' end as patial
,case when (screen_cancer_pap_smear_satisfac_id = '1')
      then screen_cancer_pap_smear_scant
      else '0' end as scant
,case when (screen_cancer_pap_smear_satisfac_id = '1')
      then screen_cancer_pap_smear_sother
      else '0' end as other
,case when (screen_cancer_pap_smear_satisfac_id = '1' and screen_cancer_pap_smear_sother = '1')
      then screen_cancer_pap_smear_sotr_comt
      else '' end as other_comt
--table 3.5 bppcs_unsa
,b_site.b_visit_office_id || substring(t_nhso_screen_cancer.t_nhso_screen_cancer_id,6) as unsat_id
,case when (screen_cancer_pap_smear_unsat_id = '1')
      then screen_cancer_pap_smear_specimreg
      else '0' end as specimreg
,case when (screen_cancer_pap_smear_unsat_id = '1')
      then screen_cancer_pap_smear_specimprc
      else '0' end as specimprc
--table 3.6 bppcs_navi
,b_site.b_visit_office_id || substring(t_nhso_screen_cancer.t_nhso_screen_cancer_id,6) as navigat_id
,case when (screen_cancer_pap_smear_navigat_id = '1')
      then screen_cancer_pap_smear_trichspp
      else '0' end as trichspp
,case when (screen_cancer_pap_smear_navigat_id = '1')
      then screen_cancer_pap_smear_candida_spp
      else '0' end as candidaspp
,case when (screen_cancer_pap_smear_navigat_id = '1')
      then screen_cancer_pap_smear_bactvagin
      else '0' end as bactvagin
,case when (screen_cancer_pap_smear_navigat_id = '1')
      then screen_cancer_pap_smear_actinomy
      else '0' end as actinomy
,case when (screen_cancer_pap_smear_navigat_id = '1')
      then screen_cancer_pap_smear_hsv
      else '0' end as hsv
,case when (screen_cancer_pap_smear_navigat_id = '1')
      then screen_cancer_pap_smear_nilother
      else '0' end as other
,case when (screen_cancer_pap_smear_navigat_id = '1')
      then screen_cancer_pap_smear_inflam
      else '0' end as inflam
,case when (screen_cancer_pap_smear_navigat_id = '1')
      then screen_cancer_pap_smear_radiation
      else '0' end as radiation
,case when (screen_cancer_pap_smear_navigat_id = '1')
      then screen_cancer_pap_smear_iud
      else '0' end as iud
,case when (screen_cancer_pap_smear_navigat_id = '1')
      then screen_cancer_pap_smear_glandr
      else '0' end as glandr
,case when (screen_cancer_pap_smear_navigat_id = '1')
      then screen_cancer_pap_smear_atrophy
      else '0' end as atrophy
--table 3.7 bppcs_epit
,b_site.b_visit_office_id || substring(t_nhso_screen_cancer.t_nhso_screen_cancer_id,6) as epit_id
,case when (screen_cancer_pap_smear_epit_id = '1')
      then screen_cancer_pap_smear_ofunsign
      else '0' end as ofunsign
,case when (screen_cancer_pap_smear_epit_id = '1')
      then screen_cancer_pap_smear_cehsil
      else '0' end as cehsil
,case when (screen_cancer_pap_smear_epit_id = '1')
      then screen_cancer_pap_smear_cehhpv
      else '0' end as cehhpv
,case when (screen_cancer_pap_smear_epit_id = '1')
      then screen_cancer_pap_smear_cin_i
      else '0' end as cin_i
,case when (screen_cancer_pap_smear_epit_id = '1')
      then screen_cancer_pap_smear_cin_ii
      else '0' end as cin_ii
,case when (screen_cancer_pap_smear_epit_id = '1')
      then screen_cancer_pap_smear_cin_iii
      else '0' end as cin_iii
,case when (screen_cancer_pap_smear_epit_id = '1')
      then screen_cancer_pap_smear_withfeaturessus
      else '0' end as featurssus
,case when (screen_cancer_pap_smear_epit_id = '1')
      then screen_cancer_pap_smear_sil
      else '0' end as sil
,case when (screen_cancer_pap_smear_epit_id = '1'and screen_cancer_pap_smear_sil = '1')
      then screen_cancer_pap_smear_sil_commt
      else '' end as commt
,case when (screen_cancer_pap_smear_epit_id = '1')
      then screen_cancer_pap_smear_squamcell
      else '0' end as squamcell
,case when (screen_cancer_pap_smear_epit_id = '1')
      then screen_cancer_pap_smear_atyendo
      else '0' end as atyendo
,case when (screen_cancer_pap_smear_epit_id = '1')
      then screen_cancer_pap_smear_atyendom
      else '0' end as atyendom
,case when (screen_cancer_pap_smear_epit_id = '1')
      then screen_cancer_pap_smear_atynotothsp
      else '0' end as atynotoths
,case when (screen_cancer_pap_smear_epit_id = '1')
      then screen_cancer_pap_smear_afavorneoce
      else '0' end as afavorneoc
,case when (screen_cancer_pap_smear_epit_id = '1')
      then screen_cancer_pap_smear_afaneome
      else '0' end as afaneome
,case when (screen_cancer_pap_smear_epit_id = '1')
      then screen_cancer_pap_smear_afaneopla
      else '0' end as afaneopla
,case when (screen_cancer_pap_smear_epit_id = '1')
      then screen_cancer_pap_smear_endocervical
      else '0' end as endocervic
,case when (screen_cancer_pap_smear_epit_id = '1')
      then screen_cancer_pap_smear_aenemaence
      else '0' end as aenemaence
,case when (screen_cancer_pap_smear_epit_id = '1')
      then screen_cancer_pap_smear_aenemaenme
      else '0' end as aenemaenme
,case when (screen_cancer_pap_smear_epit_id = '1')
      then screen_cancer_pap_smear_aenmanotsp
      else '0' end as aenmanotsp
--table 3.8  bppcs_oth
,b_site.b_visit_office_id || substring(t_nhso_screen_cancer.t_nhso_screen_cancer_id,6) as otrmal_id
,case when (screen_cancer_pap_smear_otrmal_id = '1')
      then screen_cancer_pap_smear_adqcarcinoma
      else '0' end as adqcarcino
,case when (screen_cancer_pap_smear_otrmal_id = '1')
      then screen_cancer_pap_smear_pdifscinoma
      else '0' end as pdifscinoma
,case when (screen_cancer_pap_smear_otrmal_id = '1')
      then screen_cancer_pap_smear_smallundifcinoma
      else '0' end as smallundif
,case when (screen_cancer_pap_smear_otrmal_id = '1')
      then screen_cancer_pap_smear_cinoma
      else '0' end as cinoma
,case when (screen_cancer_pap_smear_otrmal_id = '1')
      then screen_cancer_pap_smear_cimors
      else '0' end as cimors
,case when (screen_cancer_pap_smear_otrmal_id = '1')
      then screen_cancer_pap_smear_malignant
      else '0' end as malignant
,case when (screen_cancer_pap_smear_otrmal_id = '1')
      then screen_cancer_pap_smear_malignantlmp
      else '0' end as malignantl
,case when (screen_cancer_pap_smear_otrmal_id = '1')
      then screen_cancer_pap_smear_extrcarcinoma
      else '0' end as extrcarcin
,b_site.b_visit_office_id  as pcucode
,to_char(to_date(to_number(substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd') as date_serv
,screen_cancer_active as active
from t_nhso_screen_cancer
inner join t_visit on t_visit.t_visit_id = t_nhso_screen_cancer.t_visit_id
left join b_employee as nc on nc.b_employee_id = t_nhso_screen_cancer.screen_cancer_pap_smear_name_check
left join b_employee as nr on nr.b_employee_id = t_nhso_screen_cancer.screen_cancer_pap_smear_name_record
,b_site
where
--t_nhso_screen_cancer.screen_cancer_active = '1'
substring(t_visit.visit_begin_visit_time,1,10) >= ?
AND substring(t_visit.visit_begin_visit_time,1,10) <= ?