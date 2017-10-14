/*
 * DrugDosePrint2DB.java
 *
 * Created on 19 ธันวาคม 2548, 8:45 น.
 */

package com.hosv3.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.*;
import com.hospital_os.object.*;
import java.util.*;
/**
 *
 * @author  Noom
 */
public class DrugDosePrint2DB  extends DrugDosePrintDB{
    
    /** Creates a new instance of DrugDosePrint2DB */
      public DrugDosePrint2DB(ConnectionInf db)
    {
        super(db);
    }
      
    public DrugDosePrint selectByDrugDosePrintValue(String value) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.item_drug_dose_value
        + " = '" + value + "'";
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (DrugDosePrint)v.get(0);
    }
    
    
}
