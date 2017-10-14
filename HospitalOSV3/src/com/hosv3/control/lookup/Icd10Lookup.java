/*
 * Icd9Lookup.java
 *
 * Created on 20 กันยายน 2548, 9:48 น.
 */

package com.hosv3.control.lookup;

//import com.henbe.connection.*;
import com.hospital_os.usecase.connection.*;
//import com.hosv3.object.*;
import com.hosv3.control.*;
//import com.hosv3.utility.*;
import com.hosv3.utility.connection.*;

/**
 *
 * @not deprecated because use henbe package and bad read data
 * @author  sumo
 */
public class Icd10Lookup implements LookupControlInf,ExecuteControlInf{
    
    boolean is_lookup = true;
    private LookupControl theLookup;
    private SetupControl theEC;
    /** Creates a new instance of VitalTemplateLookup */
    public Icd10Lookup(LookupControl lookup) {
        theLookup = lookup;
    }
    
    public java.util.Vector listData(String str) {
        if(str==null || str.equals("") || str.equals("%"))
            return null;
        else
            return theLookup.listIcd10ByIdNameGroup(str,"",false);
    }
    

    public CommonInf readHosData(String str) {
        return theLookup.readICD10ById(str);
    }
    public boolean execute(Object str) {
        return false;
    }
    
}
