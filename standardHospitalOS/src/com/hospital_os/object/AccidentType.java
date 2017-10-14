/*
 * AccidentType.java
 *
 * Created on 2 มิถุนายน 2549, 11:23 น.
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
public class AccidentType extends Persistent {
    
    public static String NOT_ACCIDENT = "3150000000001";
    /** Creates a new instance of AccidentType */
    public String accident_type_description = "";
    public String accident_type_number = "";
    public String accident_type_trauma = "";
    public String accident_type_active = "1";
    public AccidentType() {
    }
    
}
