/*
 * RelationContract.java
 *
 * Created on 18 ตุลาคม 2548, 10:59 น.
 */

package com.hosv3.control.lookup;

//import com.henbe.connection.*;
import com.hosv3.control.*;
import com.hospital_os.usecase.connection.*;
/**
 *
 * @not deprecated because use henbe package
 * @author  sumo1
 */
public class RelationContractLookup implements LookupControlInf{ 
    private LookupControl theLookup;
    /** Creates a new instance of PrefixLookup */
    public RelationContractLookup(LookupControl lookup){
        theLookup = lookup;
    }
    
    public java.util.Vector listData(String str) {
        if(str==null || str.equals("") || str.equals("%"))
            return theLookup.listRelation();
        else
            return theLookup.listRelation(str);
    }
    
    public CommonInf readHosData(String str) {
        return theLookup.readRelationById(str);
    }
    
}

