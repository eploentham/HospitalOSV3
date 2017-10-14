/*
 * DrugDoseShortcutLookup.java
 *
 * Created on 3 สิงหาคม 2549, 15:47 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.control.lookup;
//import com.hosv3.utility.Constant;
import com.hosv3.control.LookupControl;
//import com.hosv3.utility.*;
/**
 *
 * @author henbe
 */
public class DrugDoseShortcutLookup implements com.hospital_os.usecase.connection.LookupControlInf
{
    private LookupControl thePC;
    /** Creates a new instance of DrugDoseShortcut */
    public DrugDoseShortcutLookup(LookupControl pc)
    {
        thePC = pc;
    }

    public java.util.Vector listData(String str)
    {
        return thePC.listDrugDoseShortcutByName(str);
    }
    
    public com.hospital_os.usecase.connection.CommonInf readData(String pk)
    {
        return thePC.readDrugDoseShortcutByCode(pk);
    }
    
    public com.hospital_os.usecase.connection.CommonInf readHosData(String pk) {
        return thePC.readDrugDoseShortcutByCode(pk);
    }    
}
