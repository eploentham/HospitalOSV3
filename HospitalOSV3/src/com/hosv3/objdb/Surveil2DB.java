//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.*;
import java.util.*;

public class Surveil2DB extends SurveilDB
{
    
    public Surveil2DB(ConnectionInf db)
    {
        super(db);
    }
    
    //////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////
    public Vector selectByHnDate(String hn,String dateFrom ,String dateTo) throws Exception
    {
        Vector vc = new Vector();
        String sql;
        if(hn.trim().length() !=0)
        {
            sql="select * from " + dbObj.table
            + " where " + dbObj.hn + "='" + hn + "' and " + dbObj.illdate
            + " >= '" + dateFrom + "' and " + dbObj.illdate
            + " <= '" + dateTo + "' order by "+ dbObj.illdate + " desc ";
        }
        else
        {
            sql="select * from " + dbObj.table
            + " where " + dbObj.illdate
            + " >= '" + dateFrom + "' and " + dbObj.illdate
            + " <= '" + dateTo  + "' order by "+ dbObj.illdate + " desc ";
        }
        
        vc = eQuery(sql);
        
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectByStatusDate(String status,String dateFrom ,String dateTo) throws Exception
    {
        Vector vc = new Vector();
        String sql;
        if(status.equalsIgnoreCase("0") || status.equalsIgnoreCase(""))
        {
            sql="select * from " + dbObj.table
            + " where " + dbObj.illdate
            + " >= '" + dateFrom + "' and " + dbObj.illdate
            + " <= '" + dateTo + "' order by "+ dbObj.illdate + " desc ";
        }
        else
        {
            sql="select * from " + dbObj.table
            + " where " + dbObj.patient_status + "='" + status + "' and " + dbObj.illdate
            + " >= '" + dateFrom + "' and " + dbObj.illdate
            + " <= '" + dateTo + "' order by "+ dbObj.illdate + " desc ";
        }
        
        return  eQuery(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll( ) throws Exception
    {
        Vector vc = new Vector();
        String sql ="select * from " + dbObj.table
        +" order by "+ dbObj.illdate + " desc ";
        
        
        vc = eQuery(sql);
        
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    //////////////////////////////////////////////////////////////////////////////
    
}
