/*
 * PPCare.java
 *
 * Created on 3 สิงหาคม 2548, 17:10 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class PPCare extends Persistent
{
    private static String init = "";
    public String patient_id = init;
    public String visit_id = init;	
    public String pp_care_number = init;
    public String pp_care_survey_place = init;
    public String pp_care_deliver_place = init;
    public String pp_care_mom_age = init;
    public String pp_care_dermis = init;
    public String pp_care_navel = init;
    public String pp_care_feces = init;
    public String pp_care_urine = init;
    public String pp_care_next_appointment = init;
    public String pp_care_health = init;
    public String pp_care_result = init;
    public String pp_care_state = init;
    public String pp_care_comment = init;
    public String pp_care_staff_record = init;
    public String pp_care_staff_modify = init;
    public String pp_care_staff_cancel = init;	
    public String pp_care_record_date_time = init;
    public String pp_care_modify_date_time = init;	
    public String pp_care_cancel_date_time = init;	
    public String pp_care_active = init;
    public String family_id = init;
    public String survey_date = init;

    public String pp_care_number2 = init;
    public String pp_care_survey_place2 = init;
    public String pp_care_deliver_place2 = init;
    public String pp_care_mom_age2 = init;
    public String pp_care_dermis2 = init;
    public String pp_care_navel2 = init;
    public String pp_care_feces2 = init;
    public String pp_care_urine2 = init;
    public String pp_care_next_appointment2 = init;
    public String pp_care_health2 = init;
    public String pp_care_result2 = init;
    public String pp_care_state2 = init;
//    public String pp_care_comment2 = init;
    public String pp_care_staff_record2 = init;
    public String pp_care_staff_modify2 = init;
    public String pp_care_staff_cancel2 = init;
    public String pp_care_record_date_time2 = init;
    public String pp_care_modify_date_time2 = init;
    public String pp_care_cancel_date_time2 = init;
    public String pp_care_active2 = init;
    public String family_id2 = init;
    public String survey_date2 = init;

    public String pp_care_number3 = init;
    public String pp_care_survey_place3 = init;
    public String pp_care_deliver_place3 = init;
    public String pp_care_mom_age3 = init;
    public String pp_care_dermis3 = init;
    public String pp_care_navel3 = init;
    public String pp_care_feces3 = init;
    public String pp_care_urine3 = init;
    public String pp_care_next_appointment3 = init;
    public String pp_care_health3 = init;
    public String pp_care_result3 = init;
    public String pp_care_state3 = init;
//    public String pp_care_comment3 = init;
    public String pp_care_staff_record3 = init;
    public String pp_care_staff_modify3 = init;
    public String pp_care_staff_cancel3 = init;
    public String pp_care_record_date_time3 = init;
    public String pp_care_modify_date_time3 = init;
    public String pp_care_cancel_date_time3 = init;
    public String pp_care_active3 = init;
    public String family_id3 = init;
    public String survey_date3 = init;
    public String t_health_pp_care_child_id = init;
    /** Creates a new instance of PPCare */
    public PPCare()
    {
    }
    public void setPP1(PPCare ppcare,String index)
    {
        if(ppcare!=null)
        {
            if(index.equals("1"))
            {
                pp_care_number = ppcare.pp_care_number;
                pp_care_survey_place = ppcare.pp_care_survey_place;
                pp_care_deliver_place = ppcare.pp_care_deliver_place;
                pp_care_mom_age = ppcare.pp_care_mom_age;
                pp_care_dermis = ppcare.pp_care_dermis;
                pp_care_navel = ppcare.pp_care_navel;
                pp_care_feces = ppcare.pp_care_feces;
                pp_care_urine = ppcare.pp_care_urine;
                pp_care_next_appointment = ppcare.pp_care_next_appointment;
                pp_care_health = ppcare.pp_care_health;
                pp_care_result = ppcare.pp_care_result;
                pp_care_staff_record = ppcare.pp_care_staff_record;
                pp_care_staff_modify = ppcare.pp_care_staff_modify;
                pp_care_staff_cancel = ppcare.pp_care_staff_cancel;
                pp_care_record_date_time = ppcare.pp_care_record_date_time;
                pp_care_modify_date_time = ppcare.pp_care_modify_date_time;
                pp_care_cancel_date_time = ppcare.pp_care_cancel_date_time;
                pp_care_state = ppcare.pp_care_state;
                pp_care_active = ppcare.pp_care_active;
                if(this.getObjectId()==null)
                    family_id = ppcare.family_id;
                survey_date = ppcare.survey_date;
            }
            if(index.equals("2"))
            {
                pp_care_number = ppcare.pp_care_number2;
                pp_care_survey_place = ppcare.pp_care_survey_place2;
                pp_care_deliver_place = ppcare.pp_care_deliver_place2;
                pp_care_mom_age = ppcare.pp_care_mom_age2;
                pp_care_dermis = ppcare.pp_care_dermis2;
                pp_care_navel = ppcare.pp_care_navel2;
                pp_care_feces = ppcare.pp_care_feces2;
                pp_care_urine = ppcare.pp_care_urine2;
                pp_care_next_appointment = ppcare.pp_care_next_appointment2;
                pp_care_health = ppcare.pp_care_health2;
                pp_care_result = ppcare.pp_care_result2;
                pp_care_state = ppcare.pp_care_state2;
                pp_care_staff_record = ppcare.pp_care_staff_record2;
                pp_care_staff_modify = ppcare.pp_care_staff_modify2;
                pp_care_staff_cancel = ppcare.pp_care_staff_cancel2;
                pp_care_record_date_time = ppcare.pp_care_record_date_time2;
                pp_care_modify_date_time = ppcare.pp_care_modify_date_time2;
                pp_care_cancel_date_time = ppcare.pp_care_cancel_date_time2;
                pp_care_active = ppcare.pp_care_active2;
                if(this.getObjectId()==null)
                    family_id = ppcare.family_id2;
                survey_date = ppcare.survey_date2;
            }
            if(index.equals("3"))
            {
                pp_care_number = ppcare.pp_care_number3;
                pp_care_survey_place = ppcare.pp_care_survey_place3;
                pp_care_deliver_place = ppcare.pp_care_deliver_place3;
                pp_care_mom_age = ppcare.pp_care_mom_age3;
                pp_care_dermis = ppcare.pp_care_dermis3;
                pp_care_navel = ppcare.pp_care_navel3;
                pp_care_feces = ppcare.pp_care_feces3;
                pp_care_urine = ppcare.pp_care_urine3;
                pp_care_next_appointment = ppcare.pp_care_next_appointment3;
                pp_care_health = ppcare.pp_care_health3;
                pp_care_result = ppcare.pp_care_result3;
                pp_care_state = ppcare.pp_care_state3;
                pp_care_staff_record = ppcare.pp_care_staff_record3;
                pp_care_staff_modify = ppcare.pp_care_staff_modify3;
                pp_care_staff_cancel = ppcare.pp_care_staff_cancel3;
                pp_care_record_date_time = ppcare.pp_care_record_date_time3;
                pp_care_modify_date_time = ppcare.pp_care_modify_date_time3;
                pp_care_cancel_date_time = ppcare.pp_care_cancel_date_time3;
                pp_care_active = ppcare.pp_care_active3;
                if(this.getObjectId()==null)
                    family_id = ppcare.family_id3;
                survey_date = ppcare.survey_date3;
            }
        }
    }
    public void setPP2(PPCare ppcare)
    {
        if(ppcare!=null)
        {
            pp_care_number2 = ppcare.pp_care_number;
            pp_care_survey_place2 = ppcare.pp_care_survey_place;
            pp_care_deliver_place2 = ppcare.pp_care_deliver_place;
            pp_care_mom_age2 = ppcare.pp_care_mom_age;
            pp_care_dermis2 = ppcare.pp_care_dermis;
            pp_care_navel2 = ppcare.pp_care_navel;
            pp_care_feces2 = ppcare.pp_care_feces;
            pp_care_urine2 = ppcare.pp_care_urine;
            pp_care_next_appointment2 = ppcare.pp_care_next_appointment;
            pp_care_health2 = ppcare.pp_care_health;
            pp_care_result2 = ppcare.pp_care_result;
            pp_care_state2 = ppcare.pp_care_state;
            pp_care_staff_record2 = ppcare.pp_care_staff_record;
            pp_care_staff_modify2 = ppcare.pp_care_staff_modify;
            pp_care_staff_cancel2 = ppcare.pp_care_staff_cancel;
            pp_care_record_date_time2 = ppcare.pp_care_record_date_time;
            pp_care_modify_date_time2 = ppcare.pp_care_modify_date_time;
            pp_care_cancel_date_time2 = ppcare.pp_care_cancel_date_time;
            pp_care_active2 = ppcare.pp_care_active;
            family_id2 = ppcare.family_id;
            survey_date2 = ppcare.survey_date;
        }
    }
    public void setPP3(PPCare ppcare)
    {
        if(ppcare!=null)
        {
            pp_care_number3 = ppcare.pp_care_number;
            pp_care_survey_place3 = ppcare.pp_care_survey_place;
            pp_care_deliver_place3 = ppcare.pp_care_deliver_place;
            pp_care_mom_age3 = ppcare.pp_care_mom_age;
            pp_care_dermis3 = ppcare.pp_care_dermis;
            pp_care_navel3 = ppcare.pp_care_navel;
            pp_care_feces3 = ppcare.pp_care_feces;
            pp_care_urine3 = ppcare.pp_care_urine;
            pp_care_next_appointment3 = ppcare.pp_care_next_appointment;
            pp_care_health3 = ppcare.pp_care_health;
            pp_care_result3 = ppcare.pp_care_result;
            pp_care_state3 = ppcare.pp_care_state;
            pp_care_staff_record3 = ppcare.pp_care_staff_record;
            pp_care_staff_modify3 = ppcare.pp_care_staff_modify;
            pp_care_staff_cancel3 = ppcare.pp_care_staff_cancel;
            pp_care_record_date_time3 = ppcare.pp_care_record_date_time;
            pp_care_modify_date_time3 = ppcare.pp_care_modify_date_time;
            pp_care_cancel_date_time3 = ppcare.pp_care_cancel_date_time;
            pp_care_active3 = ppcare.pp_care_active;
            family_id3 = ppcare.family_id;
            survey_date3 = ppcare.survey_date;
        }
    }
}
