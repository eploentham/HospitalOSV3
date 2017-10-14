/*
 * DrugWarehouseControl.java
 *
 * Created on 6 ÁÔ¶Ø¹ÒÂ¹ 2548, 11:47 ¹.
 */

package com.pcu.control;

import com.hospital_os.usecase.connection.*;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.object.*;

/**
 *
 * @author amp
 * @deprecated henbe unused
 */
public class DrugWarehouseControl {    
    ConnectionInf theConnectionInf;
    UpdateStatus theUS;
    HosDB thePcuDB;   
    /** Creates a new instance of DrugWarehouseControl */
    public DrugWarehouseControl() {
    }
    public DrugWarehouseControl(ConnectionInf con,HosDB hdb,UpdateStatus us)
    {
        theConnectionInf = con;
        thePcuDB = hdb;      
        theUS = us;
    }
    /**
     *@deprecated henbe unused
     **/
    public void saveDrugIn(DrugIn drugIn) 
    {        
       theConnectionInf.open();
       try{        
            if(drugIn.getObjectId() == null)
            {
                thePcuDB.theDrugInDB.insert(drugIn);
            }
            else
            {
                thePcuDB.theDrugInDB.update(drugIn); 
            }
       }
       catch(Exception ex)
       {    
            ex.printStackTrace();           
       }
       finally
       {
            theConnectionInf.close();       
       }
    }  
    
}
