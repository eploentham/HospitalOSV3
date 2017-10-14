package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
import java.util.*;

/**
 *@author henbe
 */
public class Option extends Persistent 
{
        public String verify = "0";
        public String execute = "0";
        public String dispense = "0";
        public String discontinue = "0";
        public String del_patient = "0";
        public String cancel_receipt = "0";
        public String discharge = "0";
        public String admit = "0";
        public String commit = "0";
        public String life = "0";
        public String inqueuevisit = "0";
        public String passwd_cancel_receipt = "";
        public String printJasper = "0"; /*ไม่ได้ใช้แล้ว ต้องการพิมพ์โดยใช้ Jasper หรือไม่ 0 ไม่ 1 ใช้*/
        public String autoUnlock = "0";/*ไม่ได้ใช้แล้ว */
        public String drug_interaction="0";
        public String drug_standard_allergy="1";
        /**
         * ลงรหัสโรคอัตโนมัติ
         * un @deprecated ซ้ำกันกับ doctor_discharge_end
         */
        public String auto_complete_visit = "0";
        /**
         * un @deprecated ซ้ำกันกับ use_set_icd10_map_dx
         *                     diag_icd10_auto
         */
        public String auto_diag_icd10 = "0";
        /**
         * un @deprecated ซ้ำกันกับ use_set_icd9_map_service
         */
        public String auto_diag_icd9 = "0";
        public String path_pharm = "";
        public String b1_command = "";
        public String b1_description = "";
        public String b1_icon = "";
        public String b2_command = "";
        public String b2_description = "";
        public String b2_icon = "";
        public String b3_command = "";
        public String b3_description = "";
        public String b3_icon = "";
        public String print_opdcard_con = "0";
        public String print_appoint_con = "0";
        public String print_drugrx_con = "0";
        public String print_receipt_con = "0";
        public String print_xraycard_con = "0";
        public String auto_add_prefix = "1";
        public String unused_pattern = "0";
        public String used_fixdigit_hn = "0";
        public String used_1open_con = "0";
        public String can_cancel_admit = "0";
        public String show_dose = "0";
        public String vitalsign_secure = "0";
        public String future_date_visit = "0";

        static String KW_print_opdcard_con = "print_opdcard_con";
        static String KW_print_appoint_con = "print_appoint_con";
        static String KW_print_drugrx_con = "print_drugrx_con";
        static String KW_print_receipt_con = "print_receipt_con";
        static String KW_print_xraycard_con = "print_xraycard_con";
        static String KW_admit = "admit";
        static String KW_auto_complete_visit = "doctor_discharge_end";
        static String KW_auto_diag_icd10 = "set_icd10_form_map_dx";
        static String KW_auto_diag_icd9 = "use_set_icd9_map_service";
        static String KW_autoUnlock = "autounlock";/*ไม่ได้ใช้แล้ว */
        static String KW_b1_command = "b1_command";
        static String KW_b1_description = "b1_description";
        static String KW_b1_icon = "b1_icon";
        static String KW_b2_command = "b2_command";
        static String KW_b2_description = "b2_description";
        static String KW_b2_icon = "b2_icon";
        static String KW_b3_command = "b3_command";
        static String KW_b3_description = "b3_description";
        static String KW_b3_icon = "b3_icon";        
        static String KW_cancel_receipt = "cancel_receipt";
        static String KW_passwd_cancel_receipt = "cancel_receipt_password";
        static String KW_commit = "commit";
        static String KW_del_patient = "del_patient";
        static String KW_discharge = "discharge";
        static String KW_discontinue = "discontinue";
        static String KW_dispense = "dispense";
        static String KW_drug_interaction="drug_interaction";
        static String KW_drug_standard_allergy="drug_standard_allergy";
        static String KW_execute = "execute";
        static String KW_inqueuevisit = "in_queue_visit";
        static String KW_life = "training_system";
        static String KW_printJasper = "printJasper"; /*ไม่ได้ใช้แล้ว ต้องการพิมพ์โดยใช้ Jasper หรือไม่ 0 ไม่ 1 ใช้*/
        static String KW_verify = "verify";
        static String KW_stock_usage = "stock_usage";
        static String KW_auto_add_prefix = "auto_add_prefix";
        static String KW_unused_pattern = "unused_pattern";
        static String KW_used_fixdigit_hn = "used_fixdigit_hn";
        static String KW_used_1open_con = "used_1open_con";
        static String KW_can_cancel_admit = "can_cancel_admit";
        static String KW_show_dose = "show_dose";
        static String KW_vitalsign_secure = "vitalsign_secure";
        static String KW_future_date_visit = "future_date_visit";
    public Vector vOption = null;
//        static String KW_used_icd10_tm = "used_icd10_tm"; //icd10tm

//        public String used_icd10_tm = "0";    //icd10tm
    

   /**
    * @roseuid 3F658BBB036E
    */
   public Option() 
   {  
   }
   public boolean isShowDose(){
       return show_dose.equals("1");
   }
   public String toString(){
        String ret = "";
        ret += "\n" + verify;
        ret += "\n" + execute;
        ret += "\n" + dispense;
        ret += "\n" + discontinue;
        ret += "\n" + del_patient;
        ret += "\n" + cancel_receipt;
        ret += "\n" + discharge;
        ret += "\n" + admit;
        ret += "\n" + commit;
        ret += "\n" + life;
        ret += "\n" + inqueuevisit;
        ret += "\n" + passwd_cancel_receipt;
        ret += "\n" + printJasper; /*ไม่ได้ใช้แล้ว ต้องการพิมพ์โดยใช้ Jasper หรือไม่ 0 ไม่ 1 ใช้*/
        ret += "\n" + autoUnlock;/*ไม่ได้ใช้แล้ว */
        ret += "\n" + drug_interaction;
        ret += "\n" + drug_standard_allergy;
        ret += "\n" + auto_add_prefix;
        ret += "\n" + unused_pattern;
        ret += "\n" + used_fixdigit_hn;
        ret += "\n" + used_1open_con;
        ret += "\n" + can_cancel_admit;
        ret += "\n" + show_dose;
//        ret += "\n" + used_icd10_tm; //icd10tm
        return ret;
   }
    public Option(Vector option_dv) 
    {
        vOption = option_dv;
        for(int i=0;i<option_dv.size();i++)
        {
            OptionDetail od = (OptionDetail)option_dv.get(i);
            if(od.getObjectId().equals(KW_admit))	admit	 = od.name;
            else if(od.getObjectId().equals(KW_autoUnlock))	autoUnlock 	 = od.name;
            else if(od.getObjectId().equals(KW_cancel_receipt))	cancel_receipt 	 = od.name;
            else if(od.getObjectId().equals(KW_commit))	commit 	 = od.name;
            else if(od.getObjectId().equals(KW_del_patient))	del_patient	 = od.name;
            else if(od.getObjectId().equals(KW_discharge))	discharge	 = od.name;
            else if(od.getObjectId().equals(KW_discontinue))	discontinue	 = od.name;
            else if(od.getObjectId().equals(KW_dispense))	dispense	 = od.name;
            else if(od.getObjectId().equals(KW_execute))	execute	 = od.name;
            else if(od.getObjectId().equals(KW_inqueuevisit))	inqueuevisit	 = od.name;
            else if(od.getObjectId().equals(KW_life))	life	 = od.name;
            else if(od.getObjectId().equals(KW_passwd_cancel_receipt))	passwd_cancel_receipt	 = od.name;
            else if(od.getObjectId().equals(KW_printJasper))	printJasper	 = od.name;
            else if(od.getObjectId().equals(KW_verify))	verify	 = od.name;
            else if(od.getObjectId().equals(KW_auto_diag_icd10))	auto_diag_icd10	 = od.name;
            else if(od.getObjectId().equals(KW_auto_complete_visit))	auto_complete_visit	 = od.name;
            else if(od.getObjectId().equals(KW_drug_interaction))	drug_interaction	 = od.name;
            //else if(od.getObjectId().equals(KW_drug_standard_allergy))	drug_standard_allergy	 = od.name;
            else if(od.getObjectId().equals(KW_auto_diag_icd9))	auto_diag_icd9	 = od.name;
            else if(od.getObjectId().equals(KW_b1_command))	b1_command	 = od.name;
            else if(od.getObjectId().equals(KW_b1_description))	b1_description	 = od.name;
            else if(od.getObjectId().equals(KW_b1_icon))	b1_icon	 = od.name;
            else if(od.getObjectId().equals(KW_b2_command))	b2_command	 = od.name;
            else if(od.getObjectId().equals(KW_b2_description))	b2_description	 = od.name;
            else if(od.getObjectId().equals(KW_b2_icon))	b2_icon	 = od.name;
            else if(od.getObjectId().equals(KW_b3_command))	b3_command	 = od.name;
            else if(od.getObjectId().equals(KW_b3_description))	b3_description	 = od.name;
            else if(od.getObjectId().equals(KW_b3_icon))	b3_icon	 = od.name;
            else if(od.getObjectId().equals(KW_print_opdcard_con))	print_opdcard_con	 = od.name;
            else if(od.getObjectId().equals(KW_print_appoint_con))	print_appoint_con	 = od.name;
            else if(od.getObjectId().equals(KW_print_drugrx_con))	print_drugrx_con	 = od.name;
            else if(od.getObjectId().equals(KW_print_receipt_con))	print_receipt_con	 = od.name;
            else if(od.getObjectId().equals(KW_print_xraycard_con))	print_xraycard_con	 = od.name;
            else if(od.getObjectId().equals(KW_auto_add_prefix))	auto_add_prefix	 = od.name;
            else if(od.getObjectId().equals(KW_unused_pattern))	unused_pattern	 = od.name;
            else if(od.getObjectId().equals(KW_used_fixdigit_hn))	used_fixdigit_hn	 = od.name;
            else if(od.getObjectId().equals(KW_used_1open_con))	used_1open_con	 = od.name;
            else if(od.getObjectId().equals(KW_can_cancel_admit))	can_cancel_admit	 = od.name;
            else if(od.getObjectId().equals(KW_show_dose))	show_dose	 = od.name;
            else if(od.getObjectId().equals(KW_vitalsign_secure))	vitalsign_secure	 = od.name;
            else if(od.getObjectId().equals(KW_future_date_visit))	future_date_visit	 = od.name;
//            else if(od.getObjectId().equals(KW_used_icd10_tm)) used_icd10_tm = od.name;   //icd10tm
        }
    }   
    public Vector getOptionDetailV()
    {
        Vector vret = new Vector();
        vret.add(new OptionDetail(KW_admit,	admit	));
        vret.add(new OptionDetail(KW_autoUnlock,	autoUnlock 	));
        vret.add(new OptionDetail(KW_cancel_receipt,	cancel_receipt 	));
        vret.add(new OptionDetail(KW_commit,	commit 	));
        vret.add(new OptionDetail(KW_del_patient,	del_patient	));
        vret.add(new OptionDetail(KW_discharge,	discharge	));
        vret.add(new OptionDetail(KW_discontinue,	discontinue	));
        vret.add(new OptionDetail(KW_dispense,	dispense	));
        vret.add(new OptionDetail(KW_execute,	execute	));
        vret.add(new OptionDetail(KW_inqueuevisit,	inqueuevisit	));
        vret.add(new OptionDetail(KW_life,	life	));
        vret.add(new OptionDetail(KW_passwd_cancel_receipt,	passwd_cancel_receipt	));
        vret.add(new OptionDetail(KW_printJasper,	printJasper	));
        vret.add(new OptionDetail(KW_verify,	verify	));
        vret.add(new OptionDetail(KW_auto_diag_icd10,	auto_diag_icd10	));
        vret.add(new OptionDetail(KW_auto_complete_visit,	auto_complete_visit	));
        vret.add(new OptionDetail(KW_drug_interaction,	drug_interaction	));
        vret.add(new OptionDetail(KW_drug_standard_allergy,	drug_standard_allergy	));
        vret.add(new OptionDetail(KW_auto_diag_icd9,	auto_diag_icd9	));
        vret.add(new OptionDetail(KW_b1_command,b1_command	));
        vret.add(new OptionDetail(KW_b1_description,b1_description	));
        vret.add(new OptionDetail(KW_b1_icon,b1_icon	));
        vret.add(new OptionDetail(KW_b2_command,b2_command	));
        vret.add(new OptionDetail(KW_b2_description,b2_description	));
        vret.add(new OptionDetail(KW_b2_icon,b2_icon	));
        vret.add(new OptionDetail(KW_b3_command,b3_command	));
        vret.add(new OptionDetail(KW_b3_description,b3_description	));
        vret.add(new OptionDetail(KW_b3_icon,b3_icon	));
        vret.add(new OptionDetail(KW_print_appoint_con,print_appoint_con	));
        vret.add(new OptionDetail(KW_print_drugrx_con,print_drugrx_con	));
        vret.add(new OptionDetail(KW_print_opdcard_con,print_opdcard_con	));
        vret.add(new OptionDetail(KW_print_receipt_con,print_receipt_con	));
        vret.add(new OptionDetail(KW_print_xraycard_con,print_xraycard_con	));
        vret.add(new OptionDetail(KW_auto_add_prefix,auto_add_prefix	));
        vret.add(new OptionDetail(KW_unused_pattern,unused_pattern	));
        vret.add(new OptionDetail(KW_used_fixdigit_hn,used_fixdigit_hn	));
        vret.add(new OptionDetail(KW_used_1open_con,used_1open_con	));
        vret.add(new OptionDetail(KW_can_cancel_admit,can_cancel_admit	));
        vret.add(new OptionDetail(KW_show_dose,show_dose	));
        vret.add(new OptionDetail(KW_vitalsign_secure,vitalsign_secure	));
        vret.add(new OptionDetail(KW_future_date_visit,future_date_visit	));
//        vret.add(new OptionDetail(KW_used_icd10_tm,used_icd10_tm));  icd10tm
        return vret;
    }
    public boolean isUseDrugInteract(){
        return drug_interaction.equals("1");
    }
}
