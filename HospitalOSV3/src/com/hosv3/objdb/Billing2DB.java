//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;


import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.*;
import java.util.*;

public class Billing2DB extends BillingDB
{
    

    public Billing2DB(ConnectionInf db){
        super(db);
    }
    public Vector selectByPatientId(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.patient_id
        + " = '" + pk + "' and " + dbObj.active + " ='1' order by "
        + dbObj.receipt_date  + " desc"  ;
        
        return eQuery(sql);
    }    
    //////////////////////////////////////////////////////////////////////////////
}
