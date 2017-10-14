/*
 * CheckHealth.java
 *
 * Created on 8 ¡’π“§¡ 2549, 13:41 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author kingland
 */
public class CheckHealth extends Persistent{
    private static String init = "";
    /** Creates a new instance of CheckHealth */
    public String family_id = init;
    public String family_age = init;
    public String patient_id = init;
    public String visit_id = init;
    public String check_date = init;
    public String record_datetime = init;
    public String modify_datetime = init;
    public String cancle_datetime = init;
    public String staff_record = init;
    public String staff_modify = init;
    public String staff_cancle = init;
    public String active = init;
    public String survey_id = init;
    public String age_survey_id = init;
    public String result = init;
    public String age_start = init;
    public String age_end = init;
    public String is_lifetime = init;
    
    public CheckHealth() {
    }
    
}
