/*
 * GuideLookup.java
 *
 * Created on 4 สิงหาคม 2549, 16:47 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.control.lookup;
//import com.henbe.connection.*;
import com.hospital_os.usecase.connection.*;
//import com.hosv3.object.*;
import com.hosv3.control.*;

/**
 *
 * @not deprecated because use henbe package and bad read data
 * @author sumo
 */
public class GuideLookup implements LookupControlInf
{
    boolean is_lookup = true;
    private LookupControl theLookup;
    /** Creates a new instance of GuideLookup */
    public GuideLookup(LookupControl lookup)
    {
        theLookup = lookup;
    }
    

    public java.util.Vector listData(String str)
    {
        if(str==null || str.equals("") || str.equals("%"))
            return null;
        else
            return theLookup.listGuide(str);
    }

    public CommonInf readData(String str)
    {
        return null;
    }
    public CommonInf readHosData(String str)
    {
        return null;
    }
}
