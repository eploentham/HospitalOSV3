//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;


import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.*;
import java.util.*;

public class Ward2DB extends WardDB
{
    

    public Ward2DB(ConnectionInf db){
        super(db);
    }
    /**
     *@deprecated henbe unused
     **/    
    public Vector selectAllByNameCF(String pk,String active) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where ";
        if(pk.trim().length() !=0)
        {
            sql = sql +"(" +  dbObj.ward_id
            + " like '" + pk + "'" + " or "
            + dbObj.description  + " like '" + pk + "'" + ") and ";
        }
        sql = sql + dbObj.active + " = '" + active + "'" + " order by "
        + dbObj.ward_id;
        Vector v=veQuery(sql);
        if(v.isEmpty())     return null;
        else                return v;
    }
     /**
     *
     **/    
    public Vector selectByNameActive(String pk,String active) throws Exception
    {
        String sql="select * from " + dbObj.table  + " where ";
        if(pk.trim().length() !=0)
        {
            sql = sql +"(" +  dbObj.ward_id
            + " like '" + pk + "'" + " or "
            + dbObj.description  + " like '" + pk + "'" + ") and ";
        }
        sql = sql + dbObj.active + " = '" + active + "'" + " order by "
        + dbObj.ward_id;
        return eQuery(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
}
