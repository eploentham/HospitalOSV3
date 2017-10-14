/*
 * ItemLookup.java
 *
 * Created on 8 สิงหาคม 2549, 15:20 น.
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
//import com.hosv3.utility.*;
import com.hosv3.utility.connection.*;
import com.hospital_os.object.*;

/**
 *
 * @not deprecated because use henbe package and bad read Data
 * @author henbe
 */
public class ItemLookup implements LookupControlInf,ExecuteControlInf
{
    boolean is_lookup = true;
    private LookupControl theLookup;
    private SetupControl theEC;
    /** Creates a new instance of ItemLookup */
    public ItemLookup(LookupControl lookup) 
    {
        theLookup = lookup;
    }
    public ItemLookup(SetupControl lookup)
    {
        is_lookup = false;
        theEC = lookup;
    }

    public boolean execute(Object str)
    {
        theEC.addItemDx((Item)str);
        return true;
    }


    public java.util.Vector listData(String str)
    {
        if(str==null || str.equals("") || str.equals("%"))
            return null;
        else
            return theLookup.listItemByName(str);
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
