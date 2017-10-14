/*
 * PrefixLookup.java
 *2
 * Created on 28 กรกฎาคม 2548, 13:58 น.
 */

package com.hosv3.control.lookup;

//import com.henbe.connection.LookupControlInf;
import com.hospital_os.usecase.connection.*;
import com.hosv3.utility.Constant;
import com.hosv3.control.LookupControl;
/**
 *
 * @not deprecated because use henbe package
 * @author  kingland
 */
public class AmpurLookup implements LookupControlInf{
    private LookupControl theLookup;
    /** Creates a new instance of PrefixLookup */
    public AmpurLookup(LookupControl lookup){
        theLookup = lookup;
    }
    
    public java.util.Vector listData(String str) {
        Constant.println("public java.util.Vector listData(String str) { AmpurLookup");
        return theLookup.listAmpur(str,theLookup.getSChangwat());
    }
    
//    public com.henbe.connection.CommonInf readData(String str) {
//        return theLookup.readAddressById(str);
//    }
    
    public CommonInf readHosData(String str) {
        return theLookup.readAddressById(str);
    }
    
}
