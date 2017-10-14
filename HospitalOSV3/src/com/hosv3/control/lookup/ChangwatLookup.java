/*
 * PrefixLookup.java
 *
 * Created on 28 กรกฎาคม 2548, 13:58 น.
 */

package com.hosv3.control.lookup;

//import com.henbe.connection.*;
import com.hospital_os.usecase.connection.*;
import com.hosv3.control.*;

/**
 *
 * @not deprecated because use henbe package
 * @author  kingland
 */
public class ChangwatLookup implements LookupControlInf{
    private LookupControl theLookup;
    /** Creates a new instance of PrefixLookup */
    public ChangwatLookup(LookupControl lookup){
        theLookup = lookup;
    }
    
    
    public java.util.Vector listData(String str) {
        return theLookup.listChangwat(str);
    }
    
    public CommonInf readHosData(String str) {
        return theLookup.readAddressById(str);
    }
    
}
