//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;


import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;
import com.hospital_os.objdb.*;
import java.util.*;

public class Death2DB extends DeathDB
{
    

    public Death2DB(ConnectionInf db){
        super(db);
    }
    public Death selectByPatientId(String patient_id) throws Exception
    {
        return selectByPtid(patient_id);
    }
    public Death selectByPtid(String patient_id) throws Exception
    {
        return selectByPtid(patient_id,"1");
    }
    public Death selectByPtid(String patient_id,String active) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.patient_id
        + " = '" + patient_id + "'";
        if(!active.equals(""))
            sql += " and "+ dbObj.active + " = '" + active+"'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (Death)v.get(0);
    }
    //////////////////////////////////////////////////////////////////////////////
}
