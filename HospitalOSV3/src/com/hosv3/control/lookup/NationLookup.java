/*
 * DrugInstructionLookup.java
 *
 * Created on 21 �á�Ҥ� 2548, 10:55 �.
 */

package com.hosv3.control.lookup;

/*
 * PrescriberLookup.java
 *
 * Created on 9 ��Ȩԡ�¹ 2546, 9:23 �.
 */
//import com.henbe.connection.*;
//import com.henbe.entity.*;
import com.hospital_os.usecase.connection.*;
import com.hosv3.control.*;

/**
 *
 * @not deprecated because use henbe package
 * @author  henbe
 */
public class NationLookup implements LookupControlInf{
    
    private LookupControl thePC;
    /** Creates a new instance of PrescriberLookup */
    public NationLookup(LookupControl pc) {
        thePC = pc;
    }
    
    public java.util.Vector listData(String str) {
        if(str==null || str.equals("") || str.equals("%"))
            return thePC.listNation();
        else
            return thePC.listNation(str);
    }
    
    public CommonInf readHosData(String pk) {
        return thePC.readNationById(pk);
    }
    
}
