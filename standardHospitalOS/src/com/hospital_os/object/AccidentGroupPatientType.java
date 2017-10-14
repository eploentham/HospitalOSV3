/*
 * AccidentGroupPatientType.java
 *
 * Created on 27 æƒ…¿“§¡ 2549, 15:57 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Padungrat(tong)
 */
public class AccidentGroupPatientType extends AccidentGroup{
    //public String b_accident_group_patient_type_id;
    public String accident_group_patient_type_description = "";
    public String accident_group_patient_type_notice = "";
    public String accident_group_patient_type_color_code = "";
    public String accident_group_patient_type_active = "1";
    /** Creates a new instance of AccidentGroupPatientType */
    public AccidentGroupPatientType() {
    }
    
}
