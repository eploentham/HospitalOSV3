package com.hosv3.gui.component.nan;

import com.hospital_os.object.*;
//import com.hospital_os.utility.XPersistent;
//import com.hospital_os.usecase.connection.CommonInf;
//import com.hosv3.utility.Constant;
//import com.hosv3.utility.*;
import java.util.*;

public class PhysicalExamNan 
{ 
    public String 	ga_normal	 ="";
    public String 	ga_pale	 ="";
    public String 	ga_not_pale	 ="";
    public String 	ga_jaun	 ="";
    public String 	ga_no_jaun	 ="";
    public String 	ga_gcs	 ="";
    public String 	ga_gcs_e	 ="";
    public String 	ga_gcs_m	 ="";
    public String 	ga_gcs_v	 ="";
    public String 	ga_dehy_no	 ="";
    public String 	ga_dehy_mod	 ="";
    public String 	ga_dehy_mild	 ="";
    public String 	ga_dehy_ser	 ="";
    public String 	ga_look	 ="";
    public String 	ga_feb	 ="";
    public String 	ga_dys	 ="";
    public String 	ga_tach	 ="";
    public String 	ga_other	 ="";
    public String 	hee_normal	 ="";
    public String 	hee_pale	 ="";
    public String 	hee_not_pa	 ="";
    public String 	hee_inj 	 ="";
    public String 	hee_not_inj	 ="";
    public String 	hee_ict 	 ="";
    public String 	hee_no_ict	 ="";
    public String 	hee_cer_nor	 ="";
    public String 	hee_cer_pal	 ="";
    public String 	hee_ton_nor	 ="";
    public String 	hee_ton_en	 ="";
    public String 	hee_ton_ex	 ="";
    public String 	hee_ton_oth	 ="";
    public String 	hee_eye_pter	 ="";
    public String 	hee_eye_pter_pos ="";
    public String 	hee_eye_inj	 ="";
    public String 	hee_eye_inj_pos  ="";
    public String 	hee_eye_opa	 ="";
    public String 	hee_eye_opa_pos  ="";
    public String 	hee_eye_her	 ="";
    public String 	hee_eye_her_pos  ="";
    public String 	hee_thyr_nor	 ="";
    public String 	hee_thyr_en	 ="";
    public String 	hee_thyr_oth	 ="";
    public String 	hea_re	 ="";
    public String 	hea_gal	 ="";
    public String 	hea_irr	 ="";
    public String 	hea_mur	 ="";
    public String 	hea_mur_sys= "";
    public String 	hea_mur_dia ="";
    public String 	hea_mur_sys_gra ="";
    public String 	hea_mur_sys_area ="";
    public String 	hea_mur_dias_gra ="";
    public String 	hea_mur_dias_area ="";
    public String 	hea_no_mur ="";
    public String 	hea_oth ="";
    public String 	lung_cle	 ="";
    public String 	lung_whe	 ="";
    public String 	lung_whe_pos  ="";
    public String 	lung_rho	 ="";
    public String 	lung_rho_pos  ="";
    public String 	lung_cre_fine	 ="";
    public String 	lung_cre_fine_pos  ="";
    public String 	lung_cre_coa	 ="";
    public String 	lung_cre_coa_pos  ="";
    public String 	abd_con_nor	 ="";
    public String 	abd_con_dis	 ="";
    public String 	abd_con_sca	 ="";
    public String 	abd_bow_nor	 ="";
    public String 	abd_bow_inc	 ="";
    public String 	abd_bow_dec	 ="";
    public String 	abd_pal_soft	 ="";
    public String 	abd_pal_gua	 ="";
    public String 	abd_pal_rig	 ="";
    public String 	abd_pal_ten	 ="";
    public String 	abd_pal_ten_area ="";
    public String 	abd_pal_oth	 ="";
    public String 	abd_kok_reg	 ="";
    public String 	abd_kok_dark	 ="";
    public String 	abd_kok_clr	 ="";
    public String 	skin_nor	 ="";
    public String 	skin_bul	 ="";
    public String 	skin_tin	 ="";
    public String 	skin_ecz	 ="";
    public String 	skin_exf	 ="";
    public String 	skin_pus	 ="";
    public String 	skin_sca	 ="";
    public String 	skin_oth	 ="";
    public String 	ext_nor	 ="";
    public String 	ext_pit	 ="";
    public String 	ext_non_pit ="";
    public String 	ext_ten	 ="";
    public String 	ext_ten_dec	 ="";
    public String 	ext_oth	 ="";
    public String 	neu_gro	 ="";
    public String 	neu_mot_up_l	 ="";
    public String 	neu_mot_up_l_gra ="";
    public String 	neu_mot_up_r	 ="";
    public String 	neu_mot_up_r_gra ="";
    public String 	neu_mot_low_l	 ="";
    public String 	neu_mot_low_l_gra="";
    public String 	neu_mot_low_r	 ="";
    public String 	neu_mot_low_r_gra="";
    public String 	neu_cn	 ="";
    public String 	neu_cn_num	 ="";
    public String 	neu_cn_data	 ="";
    public String 	neu_cer_ata	 ="";
    public String 	neu_cer_ata_data ="";
    public String 	neu_cer_ftn	 ="";
    public String 	neu_cer_ftn_data ="";
    public String 	neu_ref_l	 ="";
    public String 	neu_ref_l_vol	 ="";
    public String 	neu_ref_r	 ="";
    public String 	neu_ref_r_vol	 ="";
    public String 	neu_oth	 ="";
    public String 	others	 ="";
    

    
    public PhysicalExamNan(){
        
    }
    
    /**
     * เป็นการ generate PhysicalExam เพื่อให้มีการสร้างข้อมูลสำหรับการบันทึกลงฐานข้อมูลได้อย่างสะดวก
     * ลักษณะการทำการคือ
     * ตรวจสอบ root ว่าไม่เป็น 0 ค่าว่าง หรือ - หรือไม่หากใช่จึงจะ สร้าง Object ให้และ add เข้าระบบ
     * 
     */
    public boolean initPhysicalExam(Vector v,String body,String detail,String flag)
    {
        return initPhysicalExam(v,body,detail,flag,"2");
    }
    public boolean initPhysicalExam(Vector v,String body,String detail,String flag,String root_flag)
    {
        return initPhysicalExam(v,body,detail,flag,root_flag, "1");
    }
    public boolean initPhysicalExam(Vector v,String body,String detail,String flag,String root_flag,String root_root_flag)
    {
        if(root_root_flag.equals("0") || root_root_flag.equals("") || root_root_flag.equals("-"))
            return false;
        if(root_flag.equals("0") || root_flag.equals("") || root_flag.equals("-"))
            return false;
        if(flag.equals("0") || flag.equals("") || flag.equals("-"))
            return false;
        PhysicalExam pe = new PhysicalExam();
        pe.physical_body = body;
        pe.detail = detail;
        if(root_flag.equals("1"))
            if(pe.detail.equals("")) 
                pe.detail = flag; 
            else
                pe.detail = pe.detail + "   " + flag;
        
        v.add(pe);
        return true;
    }
    public Vector getForTextArea()
    {
        Vector vPhysicalExam = new Vector();
        initPhysicalExam(vPhysicalExam,"GA", "normal", 	ga_normal	);
        initPhysicalExam(vPhysicalExam,"GA", "pale", 	ga_pale	);
        initPhysicalExam(vPhysicalExam,"GA", "not pale", 	ga_not_pale	);
        initPhysicalExam(vPhysicalExam,"GA", "jaundice", 	ga_jaun	);
        initPhysicalExam(vPhysicalExam,"GA", "no jaundice", 	ga_no_jaun	);
        initPhysicalExam(vPhysicalExam,"GA", "GCS E", 	ga_gcs_e,ga_gcs	);
        initPhysicalExam(vPhysicalExam,"GA", "GCS M", 	ga_gcs_m,ga_gcs	);
        initPhysicalExam(vPhysicalExam,"GA", "GCS V", 	ga_gcs_v,ga_gcs	);
        initPhysicalExam(vPhysicalExam,"GA", "dehydration no", 	ga_dehy_no	);
        initPhysicalExam(vPhysicalExam,"GA", "dehydration moderate", 	ga_dehy_mod	);
        initPhysicalExam(vPhysicalExam,"GA", "dehydration mild", 	ga_dehy_mild	);
        initPhysicalExam(vPhysicalExam,"GA", "dehydration severe", 	ga_dehy_ser	);
        initPhysicalExam(vPhysicalExam,"GA", "look sick", 	ga_look	);
        initPhysicalExam(vPhysicalExam,"GA", "febrile", 	ga_feb	);
        initPhysicalExam(vPhysicalExam,"GA", "dyspnea", 	ga_dys	);
        initPhysicalExam(vPhysicalExam,"GA", "tachpnea", 	ga_tach	);
        initPhysicalExam(vPhysicalExam,"GA", " ", 	ga_other,"1");
        initPhysicalExam(vPhysicalExam,"Heent",	"normal", hee_normal	);
        initPhysicalExam(vPhysicalExam,"Heent","pale conjunctiva",	hee_pale	);
        initPhysicalExam(vPhysicalExam,"Heent","not pale conjunctiva",	hee_not_pa	);
        initPhysicalExam(vPhysicalExam,"Heent","inject pharynx",	hee_inj	);
        initPhysicalExam(vPhysicalExam,"Heent","not inject pharynx",	hee_not_inj	);
        initPhysicalExam(vPhysicalExam,"Heent","icteric sclera",	hee_ict	);
        initPhysicalExam(vPhysicalExam,"Heent","no icteric sclera",	hee_no_ict	);
        initPhysicalExam(vPhysicalExam,"Heent","cervical LN normal",	hee_cer_nor	);
        initPhysicalExam(vPhysicalExam,"Heent","cervical LN palpable",	hee_cer_pal	);
        initPhysicalExam(vPhysicalExam,"Heent","eye pterygium Position",	hee_eye_pter_pos, hee_eye_pter	);
        initPhysicalExam(vPhysicalExam,"Heent","eye inject conjuctiva Position",	hee_eye_inj_pos,hee_eye_inj	);
        initPhysicalExam(vPhysicalExam,"Heent","eye opaque len Position",	hee_eye_opa_pos,hee_eye_opa	);
        initPhysicalExam(vPhysicalExam,"Heent","eye hordeolum Position",	hee_eye_her_pos,hee_eye_her	);
        initPhysicalExam(vPhysicalExam,"Heent","tonsils normal",	hee_ton_nor	);
        initPhysicalExam(vPhysicalExam,"Heent","tonsils enlarged",	hee_ton_en	);
        initPhysicalExam(vPhysicalExam,"Heent","tonsils exudate",	hee_ton_ex	);
        initPhysicalExam(vPhysicalExam,"Heent","tonsils others result",hee_ton_oth,"1"	);
        initPhysicalExam(vPhysicalExam,"Heent","thyroids normal",hee_thyr_nor	);
        initPhysicalExam(vPhysicalExam,"Heent","thyroids enlarged",hee_thyr_en	);
        initPhysicalExam(vPhysicalExam,"Heent","thyroids others result",hee_thyr_oth,"1"	);
        initPhysicalExam(vPhysicalExam,"Heart","Beat Regular",	hea_re	);
        initPhysicalExam(vPhysicalExam,"Heart","Beat Gallop",	hea_gal	);
        initPhysicalExam(vPhysicalExam,"Heart","Beat Irregular",hea_irr	);
        initPhysicalExam(vPhysicalExam,"Heart","murmur systolic",hea_mur_sys, hea_mur	);
        initPhysicalExam(vPhysicalExam,"Heart","murmur diastolic",hea_mur_dia, hea_mur	);
        initPhysicalExam(vPhysicalExam,"Heart","murmur systolic Grade",hea_mur_sys_gra,hea_mur_sys, hea_mur	);
        initPhysicalExam(vPhysicalExam,"Heart","murmur systolic Area",hea_mur_sys_area,hea_mur_sys, hea_mur	);
        initPhysicalExam(vPhysicalExam,"Heart","murmur diastolic Grade",hea_mur_dias_gra,hea_mur_dia, hea_mur);
        initPhysicalExam(vPhysicalExam,"Heart","murmur diastolic Area",hea_mur_dias_area,hea_mur_dia, hea_mur);
        initPhysicalExam(vPhysicalExam,"Heart","no murmur",	hea_no_mur	);
        initPhysicalExam(vPhysicalExam,"Heart"," ",hea_oth,"1");
        initPhysicalExam(vPhysicalExam,"Lung","Clear",	lung_cle	);
        initPhysicalExam(vPhysicalExam,"Lung","Wheezing Position",	lung_whe_pos,lung_whe	);
        initPhysicalExam(vPhysicalExam,"Lung","Rhonchi Position",	lung_rho_pos,lung_rho	);
        initPhysicalExam(vPhysicalExam,"Lung","Crepitation fine Position",	lung_cre_fine_pos,lung_cre_fine	);
        initPhysicalExam(vPhysicalExam,"Lung","Crepitation coarse Position",	lung_cre_coa_pos,lung_cre_coa	);
        initPhysicalExam(vPhysicalExam,"Abdomen","contour normal",	abd_con_nor	);
        initPhysicalExam(vPhysicalExam,"Abdomen","contour distend",	abd_con_dis	);
        initPhysicalExam(vPhysicalExam,"Abdomen","contour scaphoid",	abd_con_sca	);
        initPhysicalExam(vPhysicalExam,"Abdomen","bowel sound normal",	abd_bow_nor	);
        initPhysicalExam(vPhysicalExam,"Abdomen","bowel sound increased",abd_bow_inc	);
        initPhysicalExam(vPhysicalExam,"Abdomen","bowel sound decreased",abd_bow_dec	);
        initPhysicalExam(vPhysicalExam,"Abdomen","palpation soft",abd_pal_soft	);
        initPhysicalExam(vPhysicalExam,"Abdomen","palpation guarding",abd_pal_gua);
        initPhysicalExam(vPhysicalExam,"Abdomen","palpation rigidity",abd_pal_rig);
        initPhysicalExam(vPhysicalExam,"Abdomen","palpation tender (area)",abd_pal_ten_area,abd_pal_ten);
        initPhysicalExam(vPhysicalExam,"Abdomen","เคาะ ปกติ",abd_kok_reg	);
        initPhysicalExam(vPhysicalExam,"Abdomen","เคาะ ทึบ",abd_kok_dark	);
        initPhysicalExam(vPhysicalExam,"Abdomen","เคาะ โปร่ง",abd_kok_clr	);
        initPhysicalExam(vPhysicalExam,"Abdomen"," ",abd_pal_oth,"1");
        initPhysicalExam(vPhysicalExam,"Skin","normal",skin_nor	);
        initPhysicalExam(vPhysicalExam,"Skin","bullous",skin_bul);
        initPhysicalExam(vPhysicalExam,"Skin","tined",skin_tin	);
        initPhysicalExam(vPhysicalExam,"Skin","eczema",	skin_ecz);
        initPhysicalExam(vPhysicalExam,"Skin","exfoliative",skin_exf);
        initPhysicalExam(vPhysicalExam,"Skin","pustula",skin_pus);
        initPhysicalExam(vPhysicalExam,"Skin","scale",skin_sca);
        initPhysicalExam(vPhysicalExam,"Skin","",skin_oth,"1");
        initPhysicalExam(vPhysicalExam,"Extremity","normal",ext_nor	);
        initPhysicalExam(vPhysicalExam,"Extremity","pitting edema",ext_pit	);
        initPhysicalExam(vPhysicalExam,"Extremity","non pitting edema",	ext_non_pit	);
        initPhysicalExam(vPhysicalExam,"Extremity","tender at",ext_ten_dec,ext_ten	);
        initPhysicalExam(vPhysicalExam,"Extremity"," ",ext_oth,"1"	);
        initPhysicalExam(vPhysicalExam,"Neuro","Grossly intact",neu_gro	);
        initPhysicalExam(vPhysicalExam,"Neuro","Motor Grade Upper L=",	neu_mot_up_l_gra,neu_mot_up_l	);
        initPhysicalExam(vPhysicalExam,"Neuro","Motor Grade Upper R=",	neu_mot_up_r_gra,neu_mot_up_r	);
        initPhysicalExam(vPhysicalExam,"Neuro","Motor Grade Lower L=",	neu_mot_low_l_gra,neu_mot_low_l	);
        initPhysicalExam(vPhysicalExam,"Neuro","Motor Grade Lower R=",	neu_mot_low_r_gra,neu_mot_low_r	);
        initPhysicalExam(vPhysicalExam,"Neuro","CN value",neu_cn_num,neu_cn	);
        initPhysicalExam(vPhysicalExam,"Neuro","CN type",neu_cn_data,neu_cn	);
        initPhysicalExam(vPhysicalExam,"Neuro","Cerebellar Ataxia is",neu_cer_ata_data,neu_cer_ata	);
        initPhysicalExam(vPhysicalExam,"Neuro","Cerebellar FTN is",neu_cer_ftn_data,neu_cer_ftn	);
        initPhysicalExam(vPhysicalExam,"Neuro","Reflex L ",neu_ref_l_vol,neu_ref_l);
        initPhysicalExam(vPhysicalExam,"Neuro","Reflex R ",neu_ref_r_vol,neu_ref_r);
        initPhysicalExam(vPhysicalExam,"Neuro"," ",neu_oth,"1"	);
        initPhysicalExam(vPhysicalExam,"Others (describe)"," ",others,"1"	);
        return vPhysicalExam;
    }
    
    public Vector getPhysicalExamV()
    {
        Vector vPhysicalExam = new Vector();
        
        initPhysicalExam(vPhysicalExam,"GA", "normal", 	ga_normal	);
        initPhysicalExam(vPhysicalExam,"GA", "pale", 	ga_pale	);
        initPhysicalExam(vPhysicalExam,"GA", "not pale", 	ga_not_pale	);
        initPhysicalExam(vPhysicalExam,"GA", "jaundice", 	ga_jaun	);
        initPhysicalExam(vPhysicalExam,"GA", "no jaundice", 	ga_no_jaun	);
        initPhysicalExam(vPhysicalExam,"GA", "GCS", 	ga_gcs	);
        initPhysicalExam(vPhysicalExam,"GA", "GCS E", 	ga_gcs_e,ga_gcs	);
        initPhysicalExam(vPhysicalExam,"GA", "GCS M", 	ga_gcs_m,ga_gcs	);
        initPhysicalExam(vPhysicalExam,"GA", "GCS V", 	ga_gcs_v,ga_gcs	);
        initPhysicalExam(vPhysicalExam,"GA", "dehydration no", 	ga_dehy_no	);
        initPhysicalExam(vPhysicalExam,"GA", "dehydration moderate", 	ga_dehy_mod	);
        initPhysicalExam(vPhysicalExam,"GA", "dehydration mild", 	ga_dehy_mild	);
        initPhysicalExam(vPhysicalExam,"GA", "dehydration severe", 	ga_dehy_ser	);
        initPhysicalExam(vPhysicalExam,"GA", "look sick", 	ga_look	);
        initPhysicalExam(vPhysicalExam,"GA", "febrile", 	ga_feb	);
        initPhysicalExam(vPhysicalExam,"GA", "dyspnea", 	ga_dys	);
        initPhysicalExam(vPhysicalExam,"GA", "tachpnea", 	ga_tach	);
        initPhysicalExam(vPhysicalExam,"GA", " ", 	ga_other,"1");
        initPhysicalExam(vPhysicalExam,"Heent",	"normal", hee_normal	);
        initPhysicalExam(vPhysicalExam,"Heent","pale conjunctiva",	hee_pale	);
        initPhysicalExam(vPhysicalExam,"Heent","not pale conjunctiva",	hee_not_pa	);
        initPhysicalExam(vPhysicalExam,"Heent","inject pharynx",	hee_inj	);
        initPhysicalExam(vPhysicalExam,"Heent","not inject pharynx",	hee_not_inj	);
        initPhysicalExam(vPhysicalExam,"Heent","icteric sclera",	hee_ict	);
        initPhysicalExam(vPhysicalExam,"Heent","no icteric sclera",	hee_no_ict	);
        initPhysicalExam(vPhysicalExam,"Heent","cervical LN normal",	hee_cer_nor	);
        initPhysicalExam(vPhysicalExam,"Heent","cervical LN palpable",	hee_cer_pal	);
        initPhysicalExam(vPhysicalExam,"Heent","eye pterygium",	hee_eye_pter	);
        initPhysicalExam(vPhysicalExam,"Heent","eye pterygium Position",	hee_eye_pter_pos, hee_eye_pter	);
        initPhysicalExam(vPhysicalExam,"Heent","eye inject conjuctiva",	hee_eye_inj	);
        initPhysicalExam(vPhysicalExam,"Heent","eye inject conjuctiva Position",	hee_eye_inj_pos,hee_eye_inj	);
        initPhysicalExam(vPhysicalExam,"Heent","eye opaque len",	hee_eye_opa	);
        initPhysicalExam(vPhysicalExam,"Heent","eye opaque len Position",	hee_eye_opa_pos,hee_eye_opa	);
        initPhysicalExam(vPhysicalExam,"Heent","eye hordeolum",	hee_eye_her	);
        initPhysicalExam(vPhysicalExam,"Heent","eye hordeolum Position",	hee_eye_her_pos,hee_eye_her	);
        initPhysicalExam(vPhysicalExam,"Heent","tonsils normal",	hee_ton_nor	);
        initPhysicalExam(vPhysicalExam,"Heent","tonsils enlarged",	hee_ton_en	);
        initPhysicalExam(vPhysicalExam,"Heent","tonsils exudate",	hee_ton_ex	);
        initPhysicalExam(vPhysicalExam,"Heent","tonsils others result",hee_ton_oth,"1"	);
        initPhysicalExam(vPhysicalExam,"Heent","thyroids normal",hee_thyr_nor	);
        initPhysicalExam(vPhysicalExam,"Heent","thyroids enlarged",hee_thyr_en	);
        initPhysicalExam(vPhysicalExam,"Heent","thyroids others result",hee_thyr_oth,"1"	);
        initPhysicalExam(vPhysicalExam,"Heart","Beat Regular",	hea_re	);
        initPhysicalExam(vPhysicalExam,"Heart","Beat Gallop",	hea_gal	);
        initPhysicalExam(vPhysicalExam,"Heart","Beat Irregular",hea_irr	);
        initPhysicalExam(vPhysicalExam,"Heart","murmur", hea_mur);
        initPhysicalExam(vPhysicalExam,"Heart","murmur systolic",hea_mur_sys, hea_mur	);
        initPhysicalExam(vPhysicalExam,"Heart","murmur diastolic",hea_mur_dia, hea_mur	);
        initPhysicalExam(vPhysicalExam,"Heart","murmur systolic Grade",hea_mur_sys_gra,hea_mur_sys, hea_mur	);
        initPhysicalExam(vPhysicalExam,"Heart","murmur systolic Area",hea_mur_sys_area,hea_mur_sys, hea_mur	);
        initPhysicalExam(vPhysicalExam,"Heart","murmur diastolic Grade",hea_mur_dias_gra,hea_mur_dia, hea_mur);
        initPhysicalExam(vPhysicalExam,"Heart","murmur diastolic Area",hea_mur_dias_area,hea_mur_dia, hea_mur);
        initPhysicalExam(vPhysicalExam,"Heart","no murmur",	hea_no_mur	);
        initPhysicalExam(vPhysicalExam,"Heart"," ",hea_oth,"1");
        initPhysicalExam(vPhysicalExam,"Lung","Clear",	lung_cle	);
        initPhysicalExam(vPhysicalExam,"Lung","Wheezing",	lung_whe	);
        initPhysicalExam(vPhysicalExam,"Lung","Wheezing Position",	lung_whe_pos,lung_whe	);
        initPhysicalExam(vPhysicalExam,"Lung","Rhonchi",	lung_rho	);
        initPhysicalExam(vPhysicalExam,"Lung","Rhonchi Position",	lung_rho_pos,lung_rho	);
        initPhysicalExam(vPhysicalExam,"Lung","Crepitation fine",	lung_cre_fine	);
        initPhysicalExam(vPhysicalExam,"Lung","Crepitation fine Position",	lung_cre_fine_pos,lung_cre_fine	);
        initPhysicalExam(vPhysicalExam,"Lung","Crepitation coarse",	lung_cre_coa	);
        initPhysicalExam(vPhysicalExam,"Lung","Crepitation coarse Position",	lung_cre_coa_pos,lung_cre_coa	);
        initPhysicalExam(vPhysicalExam,"Abdomen","contour normal",	abd_con_nor	);
        initPhysicalExam(vPhysicalExam,"Abdomen","contour distend",	abd_con_dis	);
        initPhysicalExam(vPhysicalExam,"Abdomen","contour scaphoid",	abd_con_sca	);
        initPhysicalExam(vPhysicalExam,"Abdomen","bowel sound normal",	abd_bow_nor	);
        initPhysicalExam(vPhysicalExam,"Abdomen","bowel sound increased",abd_bow_inc	);
        initPhysicalExam(vPhysicalExam,"Abdomen","bowel sound decreased",abd_bow_dec	);
        initPhysicalExam(vPhysicalExam,"Abdomen","palpation soft",abd_pal_soft	);
        initPhysicalExam(vPhysicalExam,"Abdomen","palpation guarding",abd_pal_gua);
        initPhysicalExam(vPhysicalExam,"Abdomen","palpation rigidity",abd_pal_rig);
        initPhysicalExam(vPhysicalExam,"Abdomen","palpation tender",abd_pal_ten);
        initPhysicalExam(vPhysicalExam,"Abdomen","palpation tender (area)",abd_pal_ten_area,abd_pal_ten);
        initPhysicalExam(vPhysicalExam,"Abdomen","เคาะ ปกติ",abd_kok_reg	);
        initPhysicalExam(vPhysicalExam,"Abdomen","เคาะ ทึบ",abd_kok_dark	);
        initPhysicalExam(vPhysicalExam,"Abdomen","เคาะ โปร่ง",abd_kok_clr	);
        initPhysicalExam(vPhysicalExam,"Abdomen"," ",abd_pal_oth,"1");
        initPhysicalExam(vPhysicalExam,"Skin","normal",skin_nor	);
        initPhysicalExam(vPhysicalExam,"Skin","bullous",skin_bul);
        initPhysicalExam(vPhysicalExam,"Skin","tined",skin_tin	);
        initPhysicalExam(vPhysicalExam,"Skin","eczema",	skin_ecz);
        initPhysicalExam(vPhysicalExam,"Skin","exfoliative",skin_exf);
        initPhysicalExam(vPhysicalExam,"Skin","pustula",skin_pus);
        initPhysicalExam(vPhysicalExam,"Skin","scale",skin_sca);
        initPhysicalExam(vPhysicalExam,"Skin","",skin_oth,"1");
        initPhysicalExam(vPhysicalExam,"Extremity","normal",ext_nor	);
        initPhysicalExam(vPhysicalExam,"Extremity","pitting edema",ext_pit	);
        initPhysicalExam(vPhysicalExam,"Extremity","non pitting edema",	ext_non_pit	);
        initPhysicalExam(vPhysicalExam,"Extremity","tender",ext_ten	);
        initPhysicalExam(vPhysicalExam,"Extremity","tender at",ext_ten_dec,ext_ten	);
        initPhysicalExam(vPhysicalExam,"Extremity"," ",ext_oth,"1"	);
        initPhysicalExam(vPhysicalExam,"Neuro","Grossly intact",neu_gro	);
        initPhysicalExam(vPhysicalExam,"Neuro","Motor Grade Upper L",	neu_mot_up_l	);
        initPhysicalExam(vPhysicalExam,"Neuro","Motor Grade Upper L=",	neu_mot_up_l_gra,neu_mot_up_l	);
        initPhysicalExam(vPhysicalExam,"Neuro","Motor Grade Upper R",	neu_mot_up_r	);
        initPhysicalExam(vPhysicalExam,"Neuro","Motor Grade Upper R=",	neu_mot_up_r_gra,neu_mot_up_r	);
        initPhysicalExam(vPhysicalExam,"Neuro","Motor Grade Lower L",	neu_mot_low_l	);
        initPhysicalExam(vPhysicalExam,"Neuro","Motor Grade Lower L=",	neu_mot_low_l_gra,neu_mot_low_l	);
        initPhysicalExam(vPhysicalExam,"Neuro","Motor Grade Lower R",	neu_mot_low_r	);
        initPhysicalExam(vPhysicalExam,"Neuro","Motor Grade Lower R=",	neu_mot_low_r_gra,neu_mot_low_r	);
        initPhysicalExam(vPhysicalExam,"Neuro","CN",neu_cn	);
        initPhysicalExam(vPhysicalExam,"Neuro","CN value",neu_cn_num,neu_cn	);
        initPhysicalExam(vPhysicalExam,"Neuro","CN type",neu_cn_data,neu_cn	);
        initPhysicalExam(vPhysicalExam,"Neuro","Cerebellar Ataxia",neu_cer_ata	);
        initPhysicalExam(vPhysicalExam,"Neuro","Cerebellar Ataxia is",neu_cer_ata_data,neu_cer_ata	);
        initPhysicalExam(vPhysicalExam,"Neuro","Cerebellar FTN",neu_cer_ftn	);
        initPhysicalExam(vPhysicalExam,"Neuro","Cerebellar FTN is",neu_cer_ftn_data,neu_cer_ftn	);
        initPhysicalExam(vPhysicalExam,"Neuro","Reflex L",neu_ref_l);
        initPhysicalExam(vPhysicalExam,"Neuro","Reflex L ",neu_ref_l_vol,neu_ref_l);
        initPhysicalExam(vPhysicalExam,"Neuro","Reflex R",neu_ref_r);
        initPhysicalExam(vPhysicalExam,"Neuro","Reflex R ",neu_ref_r_vol,neu_ref_r);
        initPhysicalExam(vPhysicalExam,"Neuro"," ",neu_oth,"1"	);
        initPhysicalExam(vPhysicalExam,"Others (describe)"," ",others,"1"	);

        return vPhysicalExam;
    }
    /** 
     * หลักการและเหตุผลคือ
     * เราจะหา ค่าของ ตัวแปรใน pe โดยให้ detail เป็นคำสำคัญในการหา
     * else if(isMatch(pe,"Neuro","CN ")) {	neu_cn_num = getValue(pe,"CN ");   
     * หมายความว่าต้องการหาตัวเลขของ ใน Neuro จะมีการตรวจ CN และผลการตรวจ CN จะมีค่าอยู่ค่าหนึ่ง
     * ตัว pe จะเก็บว่า  Neuro  CN   3
     */
    public String getValue(PhysicalExam pe,String detail){
        int index = 0;
        if(!detail.equals(""))
            index = pe.detail.indexOf(detail)+ detail.length()+3;
        if(index>=0 && index <= pe.detail.length())
            return pe.detail.substring(index);
        else
            return "";
    }   
    public boolean isMatch(PhysicalExam pe,String body,String detail){
        if(pe.physical_body.equals(body))
        {
            //ถ้าคีย์ที่ใช้ค้นหามีคำเดียว ก็ต้องหั่นคำปลายทางที่ใช้ค้นหาด้วย
            //normal != no  by equal
            //no mal == no  by no (mal) == no
            //cbc aba != cbc abb  by equal
            String pe_detail = pe.detail;
            if(detail.split(" ").length==1)
            {
                pe_detail = pe.detail.split(" ")[0];
                if(pe_detail.equals(detail)){
                    return true;
                }
            }
            else if(pe_detail.startsWith(detail)){
                return true;
            }
            if(detail.equals(""))
                return true;
        }
        return false;
    }
    /**
     *  ต้องเรียงลำดับ String ที่ขึ้นต้นด้วยข้อความเดียวกัน เอาที่สั้นเอาไว้ทีหลัง
     *  เดี่ยวมันจะหาเจอก่อนทำให้ตัวที่ตามมาไม่ได้มีการ set ค่าให้กับมันจะผิดเสียเปล่าๆ
     */
    public void setPhysicalExamV(Vector v)
    {
        if(v==null)
            v = new Vector();
        for(int i=0,size=v.size();i<size;i++)
        {    
            PhysicalExam pe = (PhysicalExam)v.get(i);
            if(isMatch(pe,"GA","normal")) {	ga_normal	 = "1";   }
            else if(isMatch(pe,"GA","pale")) {	ga_pale	 = "1";   }
            else if(isMatch(pe,"GA","not pale")) {	ga_not_pale	 = "1";   }
            else if(isMatch(pe,"GA","jaundice")) {	ga_jaun	 = "1";   }
            else if(isMatch(pe,"GA","no jaundice")) {	ga_no_jaun	 = "1";   }
            else if(isMatch(pe,"GA","GCS E")) {	ga_gcs_e	 = getValue(pe, "GCS E");   }
            else if(isMatch(pe,"GA","GCS M")) {	ga_gcs_m	 = getValue(pe, "GCS M");   }
            else if(isMatch(pe,"GA","GCS V")) {	ga_gcs_v	 = getValue(pe, "GCS V");   }
            else if(isMatch(pe,"GA","GCS")) {	ga_gcs	 = "1";   }
            else if(isMatch(pe,"GA","dehydration no")) {	ga_dehy_no	 = "1";   }
            else if(isMatch(pe,"GA","dehydration moderate")) {	ga_dehy_mod	 = "1";   }
            else if(isMatch(pe,"GA","dehydration mild")) {	ga_dehy_mild	 = "1";   }
            else if(isMatch(pe,"GA","dehydration severe")) {	ga_dehy_ser	 = "1";   }
            else if(isMatch(pe,"GA","look sick")) {	ga_look	 = "1";   }
            else if(isMatch(pe,"GA","febrile")) {	ga_feb	 = "1";   }
            else if(isMatch(pe,"GA","dyspnea")) {	ga_dys	 = "1";   }
            else if(isMatch(pe,"GA","tachpnea")) {	ga_tach	 = "1";   }
            else if(isMatch(pe,"GA","")) {	ga_other    = getValue(pe, "");   }
            else if(isMatch(pe,"Heent",	"normal")) {	hee_normal= "1";}
            else if(isMatch(pe,"Heent","pale conjunctiva")) {		hee_pale= "1";}
            else if(isMatch(pe,"Heent","not pale conjunctiva")) {	hee_not_pa= "1";   }
            else if(isMatch(pe,"Heent","inject pharynx")) {		hee_inj= "1";   }
            else if(isMatch(pe,"Heent","not inject pharynx")) {		hee_not_inj= "1";   }
            else if(isMatch(pe,"Heent","icteric sclera")) {		hee_ict= "1";   }
            else if(isMatch(pe,"Heent","no icteric sclera")) {		hee_no_ict= "1";   }
            else if(isMatch(pe,"Heent","cervical LN normal")) {		hee_cer_nor= "1";   }
            else if(isMatch(pe,"Heent","cervical LN palpable")) {	hee_cer_pal= "1";   }
            else if(isMatch(pe,"Heent","eye pterygium Position")) {	hee_eye_pter_pos = getValue(pe, "eye pterygium Position"); }
            else if(isMatch(pe,"Heent","eye pterygium")) {		hee_eye_pter= "1";   }
            else if(isMatch(pe,"Heent","eye inject conjuctiva Position")) {	hee_eye_inj_pos = getValue(pe, "inject conjuctiva Position");   }   
            else if(isMatch(pe,"Heent","eye inject conjuctiva")) {	hee_eye_inj= "1";   }
            else if(isMatch(pe,"Heent","eye opaque len Position")) {	hee_eye_opa_pos= getValue(pe, "eye opaque len Position");  }
            else if(isMatch(pe,"Heent","eye opaque len")) {		hee_eye_opa= "1";   }
            else if(isMatch(pe,"Heent","eye hordeolum Position")) {	hee_eye_her_pos= getValue(pe, "eye hordeolum Position");    }
            else if(isMatch(pe,"Heent","eye hordeolum")) {		hee_eye_her= "1";   }
            else if(isMatch(pe,"Heent","tonsils normal")) {	hee_ton_nor= "1";   }
            else if(isMatch(pe,"Heent","tonsils enlarged")) {	hee_ton_en= "1";   }
            else if(isMatch(pe,"Heent","tonsils exudate")) {	hee_ton_ex= "1";   }
            else if(isMatch(pe,"Heent","tonsils others result")) {	hee_ton_oth = getValue(pe, "tonsils others result");   }
            else if(isMatch(pe,"Heent","thyroids normal")) {	hee_thyr_nor= "1";   }
            else if(isMatch(pe,"Heent","thyroids enlarged")) {	hee_thyr_en= "1";   }
            else if(isMatch(pe,"Heent","thyroids others result")) {	hee_thyr_oth= getValue(pe, "thyroids others result");   }
            else if(isMatch(pe,"Heart","Beat Regular")) {	hea_re= "1";   }
            else if(isMatch(pe,"Heart","Beat Gallop")) {	hea_gal= "1";   }
            else if(isMatch(pe,"Heart","Beat Irregular")) {	hea_irr= "1";   }
            else if(isMatch(pe,"Heart","murmur systolic Grade")) {	hea_mur_sys_gra = getValue(pe, "murmur systolic Grade") ;  }
            else if(isMatch(pe,"Heart","murmur systolic Area")) {	hea_mur_sys_area = getValue(pe, "murmur systolic Area") ;  }
            else if(isMatch(pe,"Heart","murmur diastolic Grade")) {	hea_mur_dias_gra = getValue(pe, "murmur diastolic Grade") ;  }
            else if(isMatch(pe,"Heart","murmur diastolic Area")) {	hea_mur_dias_area= getValue(pe, "murmur diastolic Area") ;  }
            else if(isMatch(pe,"Heart","murmur systolic")) { hea_mur_sys = getValue(pe,"murmur systolic");}
            else if(isMatch(pe,"Heart","murmur diastolic")) { hea_mur_dia = getValue(pe, "murmur diastolic") ;}
            else if(isMatch(pe,"Heart","murmur")) {	hea_mur= "1";   }
            else if(isMatch(pe,"Heart","no murmur")) {	hea_no_mur= "1";   }
            else if(isMatch(pe,"Heart","")) {	hea_oth= getValue(pe, "");}
            else if(isMatch(pe,"Lung","Clear")) {	lung_cle= "1";   }
            else if(isMatch(pe,"Lung","Wheezing Position")) {	lung_whe_pos = getValue(pe,"Wheezing Position"); }
            else if(isMatch(pe,"Lung","Wheezing")) {	lung_whe= "1";	}
            else if(isMatch(pe,"Lung","Rhonchi Position")) {	lung_rho_pos = getValue(pe,"Rhonchi Position");  }
            else if(isMatch(pe,"Lung","Rhonchi")) {	lung_rho = "1";	}
            else if(isMatch(pe,"Lung","Crepitation fine Position")) {	lung_cre_fine_pos = getValue(pe,"Crepitation fine Position"); }
            else if(isMatch(pe,"Lung","Crepitation fine")) {	lung_cre_fine = "1"; }
            else if(isMatch(pe,"Lung","Crepitation coarse Position")) {	lung_cre_coa_pos =getValue(pe,"Crepitation coarse Position"); }
            else if(isMatch(pe,"Lung","Crepitation coarse")) {	lung_cre_coa ="1"; }
            else if(isMatch(pe,"Abdomen","contour normal")) {		abd_con_nor= "1";   }
            else if(isMatch(pe,"Abdomen","contour distend")) {		abd_con_dis= "1";   }
            else if(isMatch(pe,"Abdomen","contour scaphoid")) {		abd_con_sca= "1";   }
            else if(isMatch(pe,"Abdomen","bowel sound normal")) {	abd_bow_nor= "1";   }
            else if(isMatch(pe,"Abdomen","bowel sound increased")) {	abd_bow_inc= "1";   }
            else if(isMatch(pe,"Abdomen","bowel sound decreased")) {	abd_bow_dec= "1";   }
            else if(isMatch(pe,"Abdomen","palpation soft")) {	abd_pal_soft= "1";   }
            else if(isMatch(pe,"Abdomen","palpation guarding")) {	abd_pal_gua = "1";  }
            else if(isMatch(pe,"Abdomen","palpation rigidity")) {	abd_pal_rig = "1";  }
            else if(isMatch(pe,"Abdomen","palpation tender (area)")) {	abd_pal_ten_area = getValue(pe,"palpation tender (area)");  }
            else if(isMatch(pe,"Abdomen","palpation tender")) {	abd_pal_ten = "1";  }
            else if(isMatch(pe,"Abdomen","เคาะ ปกติ")) { abd_kok_reg= "1";   }
            else if(isMatch(pe,"Abdomen","เคาะ ทึบ")) {  abd_kok_dark= "1";   }
            else if(isMatch(pe,"Abdomen","เคาะ โปร่ง")) { abd_kok_clr= "1";   }
            else if(isMatch(pe,"Abdomen","")) {	abd_pal_oth= getValue(pe, "");  }
            else if(isMatch(pe,"Skin","normal")) {	skin_nor= "1";   }
            else if(isMatch(pe,"Skin","bullous")) {	skin_bul = "1";  }
            else if(isMatch(pe,"Skin","tined")) {	skin_tin= "1";   }
            else if(isMatch(pe,"Skin","eczema")) {	skin_ecz = "1"; }
            else if(isMatch(pe,"Skin","exfoliative")) {	skin_exf = "1"; }
            else if(isMatch(pe,"Skin","pustula")) {	skin_pus = "1"; }
            else if(isMatch(pe,"Skin","scale")) {	skin_sca = "1"; }
            else if(isMatch(pe,"Skin","")) {	skin_oth= getValue(pe, ""); }
            else if(isMatch(pe,"Extremity","normal")) {	ext_nor= "1";   }
            else if(isMatch(pe,"Extremity","pitting edema")) {	ext_pit= "1";   }
            else if(isMatch(pe,"Extremity","non pitting edema")) {  ext_non_pit= "1";   }
            else if(isMatch(pe,"Extremity","tender at")) {	ext_ten_dec = getValue(pe, "tender at");   }
            else if(isMatch(pe,"Extremity","tender")) {	ext_ten= "1";   }
            else if(isMatch(pe,"Extremity","")) {	ext_oth= getValue(pe, "");   }
            else if(isMatch(pe,"Neuro","Grossly intact")) {	neu_gro= "1";   }
            else if(isMatch(pe,"Neuro","Motor Grade Upper L=")) {    neu_mot_up_l_gra = getValue(pe,"Motor Grade Upper L=");   }
            else if(isMatch(pe,"Neuro","Motor Grade Upper L")) {    neu_mot_up_l = "1";   }
            else if(isMatch(pe,"Neuro","Motor Grade Upper R=")) {    neu_mot_up_r_gra = getValue(pe,"Motor Grade Upper R=");   }
            else if(isMatch(pe,"Neuro","Motor Grade Upper R")) {    neu_mot_up_r = "1";   }
            else if(isMatch(pe,"Neuro","Motor Grade Lower L=")) {    neu_mot_low_l_gra = getValue(pe,"Motor Grade Lower L=");   }
            else if(isMatch(pe,"Neuro","Motor Grade Lower L")) {    neu_mot_low_l = "1";   }
            else if(isMatch(pe,"Neuro","Motor Grade Lower R=")) {    neu_mot_low_r_gra= getValue(pe,"Motor Grade Lower R=");   }
            else if(isMatch(pe,"Neuro","Motor Grade Lower R")) {    neu_mot_low_r = "1";   }
            else if(isMatch(pe,"Neuro","CN type")) {	neu_cn_data = getValue(pe,"CN type");   }
            else if(isMatch(pe,"Neuro","CN value")) {	neu_cn_num = getValue(pe,"CN value");   }
            else if(isMatch(pe,"Neuro","CN")) {	neu_cn= "1";   }
            else if(isMatch(pe,"Neuro","Cerebellar Ataxia is")) { neu_cer_ata_data =getValue(pe,"Cerebellar Ataxia is");   }
            else if(isMatch(pe,"Neuro","Cerebellar Ataxia")) {  neu_cer_ata= "1";  }
            else if(isMatch(pe,"Neuro","Cerebellar FTN is")) {	neu_cer_ftn_data = getValue(pe,"Cerebellar FTN is");   }
            else if(isMatch(pe,"Neuro","Cerebellar FTN")) {	neu_cer_ftn= "1";   }
            else if(isMatch(pe,"Neuro","Reflex L ")) {	neu_ref_l_vol = getValue(pe,"Reflex L ");  }
            else if(isMatch(pe,"Neuro","Reflex L")) {	neu_ref_l = "1";    }
            else if(isMatch(pe,"Neuro","Reflex R ")) {	neu_ref_r_vol = getValue(pe,"Reflex R ");  }
            else if(isMatch(pe,"Neuro","Reflex R")) {	neu_ref_r = "1";    }
            else if(isMatch(pe,"Neuro","")) {	neu_oth= getValue(pe, "");   }
            else if(isMatch(pe,"Others (describe)","")) {	others = getValue(pe, "");   }
            

        }
    }
}
