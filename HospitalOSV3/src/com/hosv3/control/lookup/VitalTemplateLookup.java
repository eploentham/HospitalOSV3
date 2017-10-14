/*
 * VitalTemplateLookup.java
 *
 * Created on 20 กันยายน 2548, 9:48 น.
 */

package com.hosv3.control.lookup;

//import com.henbe.connection.*;
import com.hosv3.control.*;
import com.hospital_os.usecase.connection.*;

/**
 * @not deprecated because use henbe package bad readData
 * @author  kingland
 */
public class VitalTemplateLookup implements LookupControlInf {
    
    private LookupControl theLookup;
    /** Creates a new instance of VitalTemplateLookup */
    public VitalTemplateLookup(LookupControl lookup) {
        theLookup = lookup;
    }
    
    
    public java.util.Vector listData(String str) {
        if(str==null || str.equals("") || str.equals("%"))
            return null;
        else
            return theLookup.listVitalTemplate(str, "0");
    }
    public CommonInf readHosData(String str) {
        return null;
    }
    
}
