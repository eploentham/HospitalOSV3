/*
 * BodyOrganLookup.java
 *
 * Created on 7 เมษายน 2549, 10:56 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.control.lookup;
//import com.henbe.connection.*;
import com.hospital_os.usecase.connection.*;
//import com.hosv3.object.*;
import com.hosv3.control.*;
//import com.hosv3.utility.*;
import com.hosv3.utility.connection.*;
import com.hospital_os.utility.ComboFix;

/**
 *
 * @not deprecated because use henbe package and don't have readData function detail
 * @author amp
 */
public class BodyOrganLookup implements LookupControlInf,ExecuteControlInf
{
    private LookupControl theLookup;
    private VitalControl theEC;
    /** Creates a new instance of BodyOrganLookup */
    public BodyOrganLookup(LookupControl lookup)
    {
        theLookup = lookup;
    }
    
    public BodyOrganLookup(VitalControl lookup)
    {
        theEC = lookup;
    }


    public java.util.Vector listData(String str)
    {
        if(str==null || str.equals("") || str.equals("%"))
            return null;
        else
            return theLookup.listBodyOrgan(str);
    }

    public CommonInf readData(String str)
    {
        return null;
    }
    
    public CommonInf readHosData(String str)
    {
        return null;
    }
    public boolean execute(Object str) 
    {
        theEC.addBodyOrgan(((ComboFix)str).name);        
        return true;
    }
}
