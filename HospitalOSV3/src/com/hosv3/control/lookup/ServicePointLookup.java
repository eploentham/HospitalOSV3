/*
 * PlanLookup.java
 *
 * Created on 27 กรกฎาคม 2548, 15:22 น.
 */

package com.hosv3.control.lookup;

import com.hospital_os.object.*;

//import com.henbe.connection.*;
import com.hosv3.control.*;
//import com.hosv3.object.*;
//import com.hosv3.utility.*;
//import java.util.*;
//import com.henbe.connection.*;
import com.hosv3.utility.connection.*;
import com.hospital_os.usecase.connection.*;

/**
 *
 * @vnot deprecated because use henbe package bad read Data
 * @author  kingland
 */
public class ServicePointLookup implements LookupControlInf,ExecuteControlInf{
    
    boolean is_lookup = true;
    private LookupControl theLookup;
    private SetupControl theEC;
    /** Creates a new instance of PlanLookup */
    public ServicePointLookup(LookupControl lookup) {
       theLookup = lookup;
    }
    
    public ServicePointLookup(SetupControl lookup) {
        is_lookup = false;
        theEC = lookup;
    }
    
    public java.util.Vector listData(String str) {
//        Vector v_get ;
//        Vector v_out = new Vector();
//        if(str==null || str.equals("") || str.equals("%"))
//           v_get = theLookup.listServicePoint();
//        else
//           v_get = theLookup.listServicePoint(str);
//        for(int i=0,size=v_get.size();i<size;i++){
//            v_out.add(new ServicePoint2((ServicePoint)v_get.get(i)));
//        }
//                return v_out;
        if(str==null || str.equals("") || str.equals("%"))
            return null;
        else
            return theLookup.listServicePoint(str);
    }
    
    public CommonInf readHosData(String str) {
//        return new ServicePoint((ServicePoint)theLookup.readServicePointById(str));
        return null;
    }
    
    public boolean execute(Object str) {
    theEC.addServicePoint((ServicePoint)str);
    return true;
    }
}
