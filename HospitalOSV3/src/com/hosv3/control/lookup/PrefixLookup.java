/*
 * PrefixLookup.java
 *
 * Created on 28 กรกฎาคม 2548, 13:58 น.
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
public class PrefixLookup implements LookupControlInf{
    private LookupControl theLookup;
    /** Creates a new instance of PrefixLookup */
    public PrefixLookup(LookupControl lookup){
        theLookup = lookup;
    }
    
    
    public java.util.Vector listData(String str) {
        if(str==null || str.equals("") || str.equals("%"))
            return theLookup.listPrefix();
        else
            return theLookup.listPrefix(str);
    }
    
    public CommonInf readHosData(String str) {
        return theLookup.readPrefixById(str);
    }
    
}
