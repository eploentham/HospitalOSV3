/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;

/**
 *
 * @author LionHeart
 */
public class PPCareChild extends Persistent{
    String initString = "";
    public String t_visit_id = initString;
    public String t_health_pp_care_id = initString;
    public String t_health_family_id = initString;
    public String mother_pid = initString;
    public String gravida = initString;
    public String birth_date = initString;
    public String birth_place = initString;
    public String birth_method = initString;
    public String bddoctor = initString;
    public String born_at_hospital = initString;
    public String born_weight = initString;
    public String oxygen = initString;
    public String get_vitk = initString;
    public String result = initString;
    public void setObject(BornMch bornMch,PP pp)
    {
        if(bornMch!=null)
        {
            t_visit_id = bornMch.visit_id;
            birth_place = bornMch.bplace;
            birth_date = bornMch.bdate;
            birth_method = bornMch.birthmethod;
            bddoctor = bornMch.bdoctor;
            born_at_hospital = bornMch.bhosp;
            result = bornMch.btype;
        }
        if(pp!=null)
        {
            born_weight = pp.pp_weight;
            oxygen = pp.pp_lost_oxygen;
            get_vitk = pp.pp_vit_k;
            mother_pid = pp.pp_mother_pid;
        }
    }
}
