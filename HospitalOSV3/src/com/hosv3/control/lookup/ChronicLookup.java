/*
 * ChronicLookup.java
 *
 * Created on 17 ÁÔ¶Ø¹ÒÂ¹ 2549, 10:02 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.control.lookup;
import com.hospital_os.usecase.connection.*;
//import com.hosv3.utility.Constant;
import com.hosv3.control.*;
//import com.hosv3.utility.*;
/**
 *
 * @author amp
 */
public class ChronicLookup implements LookupControlInf
{
    private LookupControl thePC;
    /** Creates a new instance of ChronicLookup */
    public ChronicLookup(LookupControl pc)
    {
        thePC = pc;
    }
    
    public java.util.Vector listData(String str) 
    {
        return thePC.listGroupChronicEng(str);
    }
    
    public CommonInf readData(String pk) 
    {
        return thePC.readGroupChronicByCode(pk);
    }

    public CommonInf readHosData(String pk)
    {
       return thePC.readGroupChronicByCode(pk);
    }
}
