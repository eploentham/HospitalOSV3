/*
 * Clinic12Files.java
 *
 * Created on 27 มกราคม 2550, 16:35 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.object;

import com.reportcenter.object.Persist;

/**
 *
 * @author Ojika
 */ 
public class Clinic12Files extends Persist
{
    
    /** Creates a new instance of Clinic12Files */
    public String t_report_clinic_12files_id;
    public String report_clinic_12files_description;
    public String report_clinic_12files_description_en;
    
    public Clinic12Files()
    {
        //idTable = "831";
        tableName= "b_report_12files_std_clinic";
    }
    
}
