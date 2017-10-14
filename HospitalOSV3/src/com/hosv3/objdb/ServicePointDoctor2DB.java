//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;
import com.hospital_os.objdb.*;
import java.util.*;

public class ServicePointDoctor2DB extends ServicePointDoctorDB
{
    
    public ServicePointDoctor2DB(ConnectionInf db)
    {
        super(db);
    }
    
    //////////////////////////////////////////////////////////////////////////////
    public ServicePointDoctor selectByDoctor(String dc) throws Exception
    {
        Vector vc = new Vector();
        String sql="select * from " + dbObj.table
        + " where " + dbObj.doctor_id
        + " = '" + dc + "'";
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return (ServicePointDoctor)vc.get(0);
    }
  
    
}
