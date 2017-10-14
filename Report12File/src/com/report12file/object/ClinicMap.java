/*
 * ClinicMap.java
 *
 * Created on 27 มกราคม 2550, 08:52 น.
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
public class ClinicMap extends Persist
{
    
    /** Creates a new instance of Clinic */
    public String t_report_map_clinic_id;
    public String b_visit_clinic_id;
    public String visit_clinic_number;
    public String visit_clinic_description;
    public String t_report_clinic_12files_id;
    
    public ClinicMap()
    {    
        //idTable = "831";
        tableName= "b_report_12files_map_clinic";
    }
    
}
