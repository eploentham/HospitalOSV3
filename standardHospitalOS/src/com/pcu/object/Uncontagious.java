/*
 * Uncontagious.java
 *
 * Created on 21 กุมภาพันธ์ 2549, 17:52 น.
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
public class Uncontagious  extends Persistent 
{
    private static String init = "";
    public String family_id = init;
    public String disease_id= init;
    public String visit_id = init;
    public String patient_id = init;
    public String icd10 = init;
    public String getwell = init;
    public String staff_recode = init;
    public String staff_modify = init;
    public String staff_cancel = init;
    public String record_datetime = init;
    public String modify_datetime = init;
    public String cancel_datetime = init;
    public String survey_date = init;
    public String active = init;
    public String contagious_type = init;
    /** Creates a new instance of Uncontagious */
    public Uncontagious() 
    {
    }
    
}
