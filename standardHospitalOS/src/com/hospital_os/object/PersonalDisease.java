/*
 * PersonalDisease.java
 *
 * Created on 5 æƒ…¿“§¡ 2549, 11:30 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class PersonalDisease extends Persistent{
       
    public String patient_id = "";
    public String description = "";
    public String sick_date = "";
    public String staff_record = "";
    public String record_date_time="";
    /** Creates a new instance of PersonalDisease */
    public PersonalDisease() {
    }
    
}
