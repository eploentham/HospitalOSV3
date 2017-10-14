/*
 * LabSet2DB.java
 *
 * Created on 27 กันยายน 2548, 15:18 น.
 */

package com.hosv3.objdb;
import com.hospital_os.usecase.connection.*;
import  com.hospital_os.objdb.LabSetDB;

import com.hospital_os.object.LabSet;

/**
 *
 * @author  kingland
 *@deprecated henbe unused
 */
public class LabSet2DB extends LabSetDB{
    
    /** Creates a new instance of LabSet2DB */
    public LabSet dbObj2;
    public LabSet2DB(ConnectionInf db){
        super(db);
    }
     public boolean initConfig()
    {
        super.initConfig();
        dbObj2 = new LabSet();
        dbObj2.table="b_item_lab_group";
        dbObj2.pk_field="b_item_lab_group_id";
        
        dbObj2.item_id   ="b_item_id";
        dbObj2.lab_group_id="b_item_lab_set_id";        
        dbObj2.item_name ="item_lab_group_item_name";
        return true;
    }
    
     
}
