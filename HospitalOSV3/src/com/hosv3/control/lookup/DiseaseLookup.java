/*
 * PlanLookup.java
 *
 * Created on 27 กรกฎาคม 2548, 15:22 น.
 */

package com.hosv3.control.lookup;
import com.hosv3.control.*;
//import com.hosv3.utility.*;
//import com.pcu.object.*;
import com.hospital_os.usecase.connection.*;

/**
 *
 * @author  kingland
 */
public class DiseaseLookup implements LookupControlInf{
    
    private LookupControl theLookup;
    /** Creates a new instance of PlanLookup */
    public DiseaseLookup(LookupControl lookup) {
       theLookup = lookup;
    }
    public java.util.Vector listData(String str) {
        /*Vector vPlan;
        if(str==null || str.equals("") || str.equals("%"))
            vPlan = theLookup.listDisease();
        else
            vPlan = theLookup.listDisease(str);*/
        return theLookup.listDisease(str);
    }
    
    public CommonInf readData(String str) {
        return theLookup.readDiseaseById(str);
    }

    public CommonInf readHosData(String pk)
    {
        return theLookup.readDiseaseById(pk);
    }
}
