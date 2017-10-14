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
import com.hosv3.control.*;

/**
 *
 * @not deprecated because use henbe package
 * @author  henbe
 */
public class DoseUomLookup implements LookupControlInf{
    
    private LookupControl thePC;
    /** Creates a new instance of PrescriberLookup */
    public DoseUomLookup(LookupControl pc) {
        thePC = pc;
    }
    
    public java.util.Vector listData(String str) {
        return thePC.listUom(str);
    }
    
    public CommonInf readData(String pk) {
        return thePC.readUomById(pk);
    }
    public CommonInf readHosData(String pk) {
        return thePC.readUomById(pk);
    }
}
