/*
 * Chronic2DB.java
 *
 * Created on 15 กรกฎาคม 2548, 11:02 น.
 */

package com.hosv3.objdb;
import com.hospital_os.objdb.ChronicDB;
//import com.hospital_os.object.*;
import com.hospital_os.usecase.connection.*;
//import java.util.*;

 /*
 * @author  kingland
 */
public class Chronic2DB extends ChronicDB{
    
    /** Creates a new instance of Chronic2DB */
    public Chronic2DB(ConnectionInf db) {
        super(db);
    }
/////////////////////////////////////////////////////////////////////////////
//    public Chronic selectByPatientAndIcd(String patient_id,String icd) throws Exception
//    {
//        String sql="select * from " + dbObj.table
//        + " where " + dbObj.patient_id
//        + " = '" + patient_id + "' and " + dbObj.chronic_id
//        + " = '" + icd + "'";
//        Vector v=eQuery(sql);
//        if(v.size()==0)
//            return null;
//        else
//            return (Chronic)v.get(0);
//    }
///////////////////////////////////////////////////////////////////////////////    
//    public Vector selectByHnDate(String hn,String dateFrom ,String dateTo) throws Exception
//    {
//        Vector vc = new Vector();
//        String sql;
//        if(hn.trim().length() !=0)
//        {
//            sql="select * from " + dbObj.table
//            + " where " + dbObj.hn + "='" + hn + "' and " + dbObj.date_dx
//            + " >= '" + dateFrom + "' and " + dbObj.date_dx
//            + " <= '" + dateTo + "'" + " order by " + dbObj.hn ;
//        }
//        else
//        {
//            sql="select * from " + dbObj.table
//            + " where " + dbObj.date_dx
//            + " >= '" + dateFrom + "' and " + dbObj.date_dx
//            + " <= '" + dateTo  + "'" + " order by " + dbObj.hn;
//        }
//        
//        vc = eQuery(sql);
//        
//        if(vc.size()==0)
//            return null;
//        else
//            return vc;
//    }
//    ///////////////////////////////////////////////////////////
//     public Vector selectByStatusDate(String status,String dateStart ,String dateEnd) throws Exception
//    {
//        Vector vc = new Vector();
//        String sql;
//        if(status.equalsIgnoreCase("0") || status.equalsIgnoreCase("")) 
//        {
//            sql="select * from " + dbObj.table
//            + " where " + dbObj.date_dx
//            + " >= '" + dateStart + "' and " + dbObj.date_dx
//            + " <= '" + dateEnd + "'" + " order by "+ dbObj.hn;
//        }
//        else
//        {
//            sql="select * from " + dbObj.table
//            + " where " + dbObj.date_dx
//            + " >= '" + dateStart + "' and " + dbObj.date_dx
//            + " <= '" + dateEnd + "' and " + dbObj.type_dish 
//            + " = '" +status+ "'" + " order by "+ dbObj.hn ;
//        }
//        
//        vc = eQuery(sql);
//        
//        if(vc.size()==0)
//            return null;
//        else
//            return vc;
//    }
    
}
