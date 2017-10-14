/*
 * AgeSurvey.java
 *
 * Created on 6 ¡’π“§¡ 2549, 14:17 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.object;
import com.pcu.object.Survey;
/**
 *
 * @author kingland
 */
public class AgeSurvey extends Survey
{
    private static String init = "";
    public String survey_age_id  = init;
    public String survey_start = init;
    public String survey_end = init;
    public String survey_id = init;
    public String survey_record_datetime = init;
    public String survey_modify_datetime = init;    
    public String survey_cancle_datetime = init;
    public String survey_staff_record = init;
    public String survey_staff_modify = init;
    public String survey_staff_cancle = init;
    public String survey_active = init;
    public String survey_is_lifetime = init;
    /** Creates a new instance of AgeSurvey */
    public AgeSurvey() {
    }
    
}
