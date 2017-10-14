/*
 * DrugInstructionLookup.java
 *
 * Created on 21 ¡Ã¡®Ò¤Á 2548, 10:55 ¹.
 */

package com.hosv3.control.lookup;

/*
 * PrescriberLookup.java
 *
 * Created on 9 ¾ÄÈ¨Ô¡ÒÂ¹ 2546, 9:23 ¹.
 */
import com.hospital_os.usecase.connection.*;
//import com.hosv3.utility.Constant;
import com.hosv3.control.*;
//import com.hosv3.utility.*;
/**
 *
 * @author  henbe
 */
public class OfficeLookup implements LookupControlInf{
    
    private LookupControl thePC;
    /** Creates a new instance of PrescriberLookup */
    public OfficeLookup(LookupControl pc) {
        thePC = pc;
    }
    
    public java.util.Vector listData(String str) {
        return thePC.listOfficeByName(str);
    }
    
    public CommonInf readData(String pk) {
        return thePC.readHospitalByCode(pk);
    }

    public CommonInf readHosData(String pk)
    {
         return thePC.readHospitalByCode(pk);
    }
    
}
