/*
 * VitalTemplateLookup.java
 *
 * Created on 20 กันยายน 2548, 9:48 น.
 */

package com.hosv3.control.lookup;

//import com.henbe.connection.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;
import com.hosv3.control.*;
//import com.hosv3.utility.*;
import com.hosv3.utility.connection.*;

/**
 *
 * @not deprecated because use henbe package and bad read data
 * @author  kingland
 */
public class DxTemplateLookup implements LookupControlInf,ExecuteControlInf{
    
    boolean is_lookup = true;
    private LookupControl theLookup;
    private VitalControl theEC;
    /** Creates a new instance of VitalTemplateLookup */
    public DxTemplateLookup(LookupControl lookup) {
        theLookup = lookup;
    }
    public DxTemplateLookup(VitalControl lookup) {
        is_lookup = false;
        theEC = lookup;
    }
    public DxTemplateLookup(LookupControl lookup,VitalControl econ) {
        is_lookup = false;
        theLookup = lookup;
        theEC = econ;
    }
    public java.util.Vector listData(String str) {
        if(str==null || str.equals("") || str.equals("%"))
            return null;
        else
            return theLookup.listDxTemplateByName(str);
    }
    

//    public com.henbe.connection.CommonInf readData(String str) {
//        return null;
//    }

    public CommonInf readHosData(String str) {
        return null;
    }
    public boolean execute(Object str) {
        theEC.addDxTemplate((DxTemplate)str);
        return true;
    }
    
}
