/*
 * EmployeeLookup.java
 *
 * Created on 9 æƒ…¿“§¡ 2550, 18:21 π.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hosv3.control.lookup;

import com.hospital_os.usecase.connection.CommonInf;
import com.hospital_os.usecase.connection.LookupControlInf;
import com.hosv3.control.LookupControl;
import com.hosv3.control.SetupControl;
import com.hosv3.utility.connection.ExecuteControlInf;
import java.util.Vector;

/**
 *
 * @author Amp
 */
public class EmployeeLookup implements LookupControlInf,ExecuteControlInf
{
    boolean is_lookup = true;
    private LookupControl theLookup;
    private SetupControl theEC;
    
    /** Creates a new instance of EmployeeLookup */
    
    public EmployeeLookup(SetupControl lookup,LookupControl lc)
    {
        is_lookup = false;
        theEC = lookup;
        theLookup = lc;
    }

    public CommonInf readHosData(String pk)
    {
        return theLookup.readEmployeeById(pk);
    }

    public Vector listData(String str)
    {
        return theEC.listEmployeeSetup(str,"1",null,false);
    }

    public boolean execute(Object str)
    {
        return false;
    }
    
}
