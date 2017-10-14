/*
 * Maim.java
 *
 * Created on 22 กุมภาพันธ์ 2549, 10:07 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class Maim extends Persistent{
    private static String init = "";
    public String family_id = init;
    public String maim_id = init;
    public String patient_id = init;
    public String visit_id = init;
    public String maim_treat = init;
    public String maim_registry = init;
    public String staff_record = init;
    public String staff_modify = init;
    public String staff_cancel = init;
    public String record_date_time = init;
    public String modify_date_time = init;
    public String cancel_date_time = init;
    public String active = init;
    public String survey_date = init;
    public String description = init;

    /** Creates a new instance of Maim */
    public Maim() {
    }
    
}
