/*
 * AppointmentTemplateLookup.java
 *
 * Created on 11 สิงหาคม 2549, 15:28 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.control.lookup;

import com.hospital_os.usecase.connection.*;
//import com.hosv3.utility.Constant;
import com.hosv3.control.*;
//import com.hosv3.utility.*;
/**
 *
 * @author amp
 */
public class AppointmentTemplateLookup implements LookupControlInf
{
    private LookupControl thePC;
    
    /** Creates a new instance of AppointmentTemplateLookup */
    public AppointmentTemplateLookup(LookupControl pc)
    {
        thePC = pc;
    }
    
    public java.util.Vector listData(String str) 
    {
        return thePC.listAppointmentTemplate(str);
    }
    
    public CommonInf readHosData(String pk)
    {
         return thePC.readAppointmentTemplate(pk);         
    }
    
}
