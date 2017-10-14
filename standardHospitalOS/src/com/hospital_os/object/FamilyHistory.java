/*
 * FamilyHistory.java
 *
 * Created on 10 กุมภาพันธ์ 2549, 17:15 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class FamilyHistory extends Persistent
{
    public String patient_id="";
    /**
     *กลุ่มโรค fix FHistoryDiseaseGroup
     */
    public String topic = "";
    public String description = "";
    public String staff_record="";
    public String record_date_time="";
    
    /** Creates a new instance of FamilyHistory */
    public FamilyHistory()
    {
    }
    
}
