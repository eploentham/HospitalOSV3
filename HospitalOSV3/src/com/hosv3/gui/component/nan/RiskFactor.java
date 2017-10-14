/*
 * RiskFactor.java
 *
 * Created on 10 กุมภาพันธ์ 2549, 16:44 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.gui.component.nan;
import com.hospital_os.utility.XPersistent;
/**
 *
 * @author amp
 */
public class RiskFactor extends XPersistent 
{
    public String patient_id = "";
    public String description = "";
    public String staff_record = "";
    public String record_date_time = "";
        
    /** Creates a new instance of RiskFactor */
    public RiskFactor()
    {
    }
    
}
