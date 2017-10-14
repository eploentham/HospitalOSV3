/*
 * PlanLookup.java
 *
 * Created on 27 กรกฎาคม 2548, 15:22 น.
 */

package com.hosv3.control.lookup;
//import com.henbe.connection.*;
import com.hosv3.control.*;
import com.hospital_os.usecase.connection.*;

/**
 *
 * @not deprecated because use henbe package
 * @author  kingland
 */
public class PlanLookup implements LookupControlInf{
    
    private LookupControl theLookup;
    /** Creates a new instance of PlanLookup */
    public PlanLookup(LookupControl lookup) {
       theLookup = lookup;
    }
    
    
    public java.util.Vector listData(String str) {
       return theLookup.listPlan(str);
    }
    
    public CommonInf readHosData(String str) {
        return theLookup.readPlanById(str);
    }
    
}
