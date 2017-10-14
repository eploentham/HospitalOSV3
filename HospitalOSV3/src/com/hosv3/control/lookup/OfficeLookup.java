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
