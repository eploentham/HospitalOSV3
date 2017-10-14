select
b_site.b_visit_office_id || substring(t_nhso_screen_cancer.t_nhso_screen_cancer_id,9) as pap_id
,b_site.b_visit_office_id ||  substr(t_nhso_service_pp.t_nhso_service_pp_id,11)  as t_service_id
,nc.employee_firstname || '  ' || nc.employee_lastname AS NAME_CHECK
,nr.employee_firstname || '  ' || nr.employee_lastname AS NAME_RECORD
,to_char(to_date(to_number(substr(t_nhso_screen_cancer.screen_cancer_record_date_time,1,4),'9999')-543 
    || substr(t_nhso_screen_cancer.screen_cancer_record_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd') AS date_record
,case when(screen_cancer_pap_smear_verginal IN ('0','1'))
    then screen_cancer_pap_smear_verginal
    else '0'
    end AS VERGINAL
,case when(screen_cancer_pap_smear_cervcl IN ('0','1')) 
    then  screen_cancer_pap_smear_cervcl
    else '0'
    end AS CERVICAL
,case when(screen_cancer_pap_smear_endocvil IN ('0','1'))
    then  screen_cancer_pap_smear_endocvil
    else '0'
    end AS ENDOCERVICAL
,case when(screen_cancer_pap_smear_endomtl  IN ('0','1'))
    then  screen_cancer_pap_smear_endomtl
    else '0'
    end AS ENDOMETRIAL
,case when(screen_cancer_pap_smear_hcother IN ('0','1'))
    then screen_cancer_pap_smear_hcother 
    else '0'
    end AS OTHERTHINK
,case when (screen_cancer_pap_smear_hcother = '1')
      then screen_cancer_pap_smear_hcotr_comt
      else '' 
    end as OTHER_COMMENT
,substring(screen_cancer_pap_smear_para ,1,4) AS PARA 
,t_nhso_screen_cancer.screen_cancer_pap_smear_last AS "LAST"
,case when length(trim(screen_cancer_pap_smear_lmp)) = 10 and position('-' in screen_cancer_pap_smear_lmp) = 5
           then to_char(to_date(to_number(substr(t_nhso_screen_cancer.screen_cancer_pap_smear_lmp,1,4),'9999')-543 
                            || substr(t_nhso_screen_cancer.screen_cancer_pap_smear_lmp,5,6),'yyyy-mm-dd'),'yyyymmdd') 
           else screen_cancer_pap_smear_lmp
end as LMP
,substring(screen_cancer_pap_smear_previous , 1, 100) AS PREVIOUSTREATMENT
,substring(screen_cancer_pap_smear_clinical , 1, 100) AS CLINICALFINDINGS
,substring(screen_cancer_pap_smear_clinicaldx , 1, 100) AS CLINICALDx
,to_char(to_date(to_number(substr(t_nhso_screen_cancer.screen_cancer_pap_smear_lastpap,1,4),'9999')-543 
    || substr(t_nhso_screen_cancer.screen_cancer_pap_smear_lastpap,5,6),'yyyy'),'yyyy') AS LASTPAPIN5YEAR
,case when (screen_cancer_pap_smear_specimen = '1' or screen_cancer_pap_smear_unsat_id = '1')
    then '1'
    else '0'
    end AS SATISFACID
,case when(screen_cancer_pap_smear_specimen IN ('0','1'))
    then screen_cancer_pap_smear_specimen
    else '0'
    end as SPECIMEN
,case when (screen_cancer_pap_smear_presence = '1')
      then screen_cancer_pap_smear_presence
      else '0' end AS PRESENCE
,case when (screen_cancer_pap_smear_absence = '1')
      then screen_cancer_pap_smear_absence
      else '0' end AS ABSENCE
,case when (screen_cancer_pap_smear_smear = '1')
      then screen_cancer_pap_smear_smear
      else '0' end AS SMEAR
,case when (screen_cancer_pap_smear_patial = '1')
      then screen_cancer_pap_smear_patial
      else '0' end AS PATIAL
,case when (screen_cancer_pap_smear_scant = '1')
      then screen_cancer_pap_smear_scant
      else '0' end AS SCANT
,case when (screen_cancer_pap_smear_sother = '1')
      then screen_cancer_pap_smear_sother
      else '0' end AS OTHERCYTO
,case when (screen_cancer_pap_smear_sother = '1')
      then screen_cancer_pap_smear_sotr_comt
      else '' end AS OTHER_COMMENT_CYTO
,case when(screen_cancer_pap_smear_unsat_id IN ('0','1'))
	then screen_cancer_pap_smear_unsat_id
	else '0' end AS UNSAT 
,case when (screen_cancer_pap_smear_unsat_id = '1')
      then screen_cancer_pap_smear_specimreg
      else '0' end AS SPECIMEN_REGICT
,case when (screen_cancer_pap_smear_specimprc = '1')
      then screen_cancer_pap_smear_specimprc
      else '0' end AS SPECIMEN_PROCESSED
,case when (screen_cancer_pap_smear_general = '1')
      then screen_cancer_pap_smear_general
      else '0' end AS GENERAL_CATEGERIZATION --
,case when(screen_cancer_pap_smear_navigat_id IN ('0','1'))
	then screen_cancer_pap_smear_navigat_id
	else '0'
	end AS NAVIGAT
,case when (screen_cancer_pap_smear_trichspp = '1')
      then screen_cancer_pap_smear_trichspp
      else '0' end AS TRICHSPP
,case when (screen_cancer_pap_smear_candida_spp = '1')
      then screen_cancer_pap_smear_candida_spp
      else '0' end AS candidaspp
,case when (screen_cancer_pap_smear_bactvagin = '1')
      then screen_cancer_pap_smear_bactvagin
      else '0' end AS BACTVAGIN
,case when (screen_cancer_pap_smear_actinomy = '1')
      then screen_cancer_pap_smear_actinomy
      else '0' end  AS ACTINOMY
,case when (screen_cancer_pap_smear_hsv = '1')
      then screen_cancer_pap_smear_hsv
      else '0' end AS HSV
,case when (screen_cancer_pap_smear_nilother = '1')
      then screen_cancer_pap_smear_nilother
      else '0' end AS OTHERORGANISM
,case when (screen_cancer_pap_smear_inflam = '1')
      then screen_cancer_pap_smear_inflam
      else '0' end AS INFLAM
,case when (screen_cancer_pap_smear_radiation = '1')
      then screen_cancer_pap_smear_radiation
      else '0' end AS RADIATION
,case when (screen_cancer_pap_smear_iud = '1')
      then screen_cancer_pap_smear_iud
      else '0' end AS IUD
,case when (screen_cancer_pap_smear_glandr = '1')
      then screen_cancer_pap_smear_glandr
      else '0' end AS GLANDR
,case when (screen_cancer_pap_smear_atrophy = '1')
      then screen_cancer_pap_smear_atrophy
      else '0' end AS ATROPHY
,case when(screen_cancer_pap_smear_epit_id IN ('0','1'))
	then screen_cancer_pap_smear_epit_id
	else '0'
	end AS EPITHELIAL 
,case when (screen_cancer_pap_smear_ofunsign = '1')
      then screen_cancer_pap_smear_ofunsign
      else '0' end AS OFUNSIGN
,case when (screen_cancer_pap_smear_cehsil = '1')
      then screen_cancer_pap_smear_cehsil
      else '0' end AS CEHSIL
,case when (screen_cancer_pap_smear_cehhpv = '1')
      then screen_cancer_pap_smear_cehhpv
      else '0' end AS CEHHPV
,case when (screen_cancer_pap_smear_cin_i = '1')
      then screen_cancer_pap_smear_cin_i
      else '0' end AS CIN_I
,case when (screen_cancer_pap_smear_cin_ii = '1')
      then screen_cancer_pap_smear_cin_ii
      else '0' end AS CIN_II
,case when (screen_cancer_pap_smear_cin_iii = '1')
      then screen_cancer_pap_smear_cin_iii
      else '0' end AS CIN_III
,case when (screen_cancer_pap_smear_withfeaturessus = '1')
      then screen_cancer_pap_smear_withfeaturessus
      else '0' end AS FEATURSSUS
,case when (screen_cancer_pap_smear_sil = '1')
      then screen_cancer_pap_smear_sil
      else '0' end AS SIL
,case when (screen_cancer_pap_smear_sil = '1')
      then screen_cancer_pap_smear_sil_commt
      else '' end AS COMMT
,case when (screen_cancer_pap_smear_squamcell = '1')
      then screen_cancer_pap_smear_squamcell
      else '0' end AS SQUAMCELL
,case when (screen_cancer_pap_smear_atyendo = '1')
      then screen_cancer_pap_smear_atyendo
      else '0' end AS ATYENDO
,case when (screen_cancer_pap_smear_atyendom = '1')
      then screen_cancer_pap_smear_atyendom
      else '0' end AS ATYENDOM
,case when (screen_cancer_pap_smear_atynotothsp = '1')
      then screen_cancer_pap_smear_atynotothsp
      else '0' end AS ATYNOTOTHS
,case when (screen_cancer_pap_smear_afavorneoce = '1')
      then screen_cancer_pap_smear_afavorneoce
      else '0' end AS AFAVORNEOC
,case when (screen_cancer_pap_smear_afaneome = '1')
      then screen_cancer_pap_smear_afaneome
      else '0' end as afaneome
,case when (screen_cancer_pap_smear_afaneopla = '1')
      then screen_cancer_pap_smear_afaneopla
      else '0' end as afaneopla
,case when (screen_cancer_pap_smear_endocervical = '1')
      then screen_cancer_pap_smear_endocervical
      else '0' end as endocervic
,case when (screen_cancer_pap_smear_aenemaence = '1')
      then screen_cancer_pap_smear_aenemaence
      else '0' end as aenemaence
,case when (screen_cancer_pap_smear_aenemaenme = '1')
      then screen_cancer_pap_smear_aenemaenme
      else '0' end as aenemaenme
,case when (screen_cancer_pap_smear_aenmanotsp = '1')
      then screen_cancer_pap_smear_aenmanotsp
      else '0' end as aenmanotsp
,case when (screen_cancer_pap_smear_otrmal_id = '1')
      then screen_cancer_pap_smear_otrmal_id
      else '0' end  AS OTRMAL 
,case when (screen_cancer_pap_smear_adqcarcinoma = '1')
      then screen_cancer_pap_smear_adqcarcinoma
      else '0' end as adqcarcino
,case when (screen_cancer_pap_smear_pdifscinoma = '1')
      then screen_cancer_pap_smear_pdifscinoma
      else '0' end as pdifscinoma
,case when (screen_cancer_pap_smear_smallundifcinoma = '1')
      then screen_cancer_pap_smear_smallundifcinoma
      else '0' end as smallundif
,case when (screen_cancer_pap_smear_cinoma = '1')
      then screen_cancer_pap_smear_cinoma
      else '0' end as cinoma
,case when (screen_cancer_pap_smear_cimors = '1')
      then screen_cancer_pap_smear_cimors
      else '0' end as cimors
,case when (screen_cancer_pap_smear_malignant = '1')
      then screen_cancer_pap_smear_malignant
      else '0' end as malignant
,case when (screen_cancer_pap_smear_malignantlmp = '1')
      then screen_cancer_pap_smear_malignantlmp
      else '0' end as malignantl
,case when (screen_cancer_pap_smear_extrcarcinoma = '1')
      then screen_cancer_pap_smear_extrcarcinoma
      else '0' end as extrcarcin
,case when(screen_cancer_inform_uterus_result IN ('0','1'))
	then  screen_cancer_inform_uterus_result
	else '0'
	end as RESULTPAPSMEAR_INFROM
,case when (screen_cancer_follow_uterus IN ('0','1')) 
	then  screen_cancer_follow_uterus
	else '0'
	end as FOLLOWUP
,t_nhso_service_pp.pp_totalpay as TOTALPAY
        ,t_visit.visit_vn as vn
from t_nhso_screen_cancer
    inner join t_nhso_service_pp on t_nhso_service_pp.pp_id = t_nhso_screen_cancer.t_nhso_screen_cancer_id
    inner join t_visit on t_visit.t_visit_id = t_nhso_screen_cancer.t_visit_id
    left join b_employee as nc on nc.b_employee_id = t_nhso_screen_cancer.screen_cancer_pap_smear_name_check
    left join b_employee as nr on nr.b_employee_id = t_nhso_screen_cancer.screen_cancer_pap_smear_name_record
    ,b_site
where
t_nhso_screen_cancer.screen_cancer_active = '1'
and t_nhso_service_pp.pp_name = 'PAPSMEAR'
and screen_cancer_uterus_check = '1'
and t_nhso_service_pp.pp_active = '1'
and substring(t_visit.visit_begin_visit_time,1,10) >= ?
and substring(t_visit.visit_begin_visit_time,1,10) <= ?