/*
 * AccidentTypeLookup.java
 *
 * Created on 2 ÁÔ¶Ø¹ÒÂ¹ 2549, 13:28 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.control.lookup;
//import com.henbe.connection.*;
import com.hospital_os.usecase.connection.*;
import com.hosv3.control.*;
//import com.hosv3.utility.*;
import java.util.Vector;

/**
 *
 * @not deprecated because use henbe package
 * @author Padungrat(tong)
 */
public class AccidentTypeLookup implements LookupControlInf{
    private LookupControl theLookup;    
    Vector vAccidentType;
    /** Creates a new instance of AccidentTypeLookup */
    public AccidentTypeLookup(LookupControl lookup){
        theLookup = lookup;
    }
    
    
    public java.util.Vector listData(String str) {
         if(str==null || str.equals("") || str.equals("%"))
            vAccidentType=  theLookup.listAccidentType();
        else
            vAccidentType = theLookup.listAccidentType(str);
        return vAccidentType;
    }
    
//    public com.henbe.connection.CommonInf readData(String str) {
//        return theLookup.readAccidentTypeById(str);
//    }
    public CommonInf readHosData(String str) {
        return theLookup.readAccidentTypeById(str);
    }
}
