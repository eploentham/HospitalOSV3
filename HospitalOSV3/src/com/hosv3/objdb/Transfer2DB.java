//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java
package com.hosv3.objdb;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import com.hospital_os.objdb.*;
        
import java.util.*;
import java.sql.*;

public class Transfer2DB extends TransferDB
{
    public Transfer2DB(ConnectionInf db)
    {
        super(db);
    }
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */

    public int insert(Transfer o) throws Exception
    {
         String sql="";
         Transfer p=o;
         p.generateOID(idtable);
         sql="insert into " + dbObj.table + " ("
         + dbObj.pk_field
         + " ,"	+ dbObj.visit_id
         + " ,"	+ dbObj.patient_id
         + " ,"	+ dbObj.assign_time
         //       + " ,"	+ dbObj.clinic_code
         + " ,"	+ dbObj.doctor_code
         + " ,"	+ dbObj.status
         + " ,"  + dbObj.service_point_id
         + " ,"  + dbObj.service_start_time
         + " ,"  + dbObj.service_finish_time
         + " ,"  + dbObj.ward_id
         + " ) values ('"
         + p.getObjectId()
         + "','" + p.visit_id
         + "','" + p.patient_id
         + "','" + p.assign_time
         //        + "','" + p.clinic_code
         + "','" + p.doctor_code
         + "','" + p.status
         + "','" + p.service_point_id
         + "','" + p.service_start_time
         + "','" + p.service_finish_time
         + "','" + p.ward_id
         + "')";
         sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
         return theConnectionInf.eUpdate(sql);
    }
    
    public Transfer selectTimeByVisitIDKeyID(String visit_id,String service_point_id) throws Exception
    {
        String sql = "SELECT * FROM " + dbObj.table
        + " WHERE " + dbObj.visit_id + " = '" + visit_id + "'"
        //     + " AND " + dbObj.service_point_id + " = '" + service_point_id + "'"
        + " AND " + dbObj.status + " <> '" + "3" + "'";
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (Transfer)v.get(0);
    }
    
    public int update(Transfer o) throws Exception
    {
         String sql="update " + dbObj.table + " set ";
         Transfer p = o;
         String field =""
         + "', " + dbObj.visit_id + "='" + p.visit_id
         + "', " + dbObj.patient_id + "='" + p.patient_id
         + "', " + dbObj.assign_time + "='" + p.assign_time
         //        + "', " + dbObj.clinic_code + "='" + p.clinic_code
         + "', " + dbObj.doctor_code + "='" + p.doctor_code
         + "', " + dbObj.status + "='" + p.status
         + "', " + dbObj.service_point_id + "='" + p.service_point_id
         + "', " + dbObj.service_start_time + "='" + p.service_start_time
         + "', " + dbObj.service_finish_time + "='" + p.service_finish_time
         + "', " + dbObj.ward_id + "='" + p.ward_id
         + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
         sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
         return theConnectionInf.eUpdate(sql);
    }
    public int updateServiceStartTime(String service_id , String visit_id , String start_time) throws Exception
    {
         String sql="update " + dbObj.table + " set ";
         
         String field =""
         + "', " + dbObj.service_start_time + "='" + start_time
         + "', " + dbObj.status + "='" + "2"
         + "' where " + dbObj.service_point_id + "='" + service_id +"'"
         + " and " + dbObj.visit_id + "='" + visit_id +"'"
         + " and " + dbObj.status + "='" + "1" +"'" ;
         sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
         
         return theConnectionInf.eUpdate(sql);
    }
    
    public int updateServiceFinishTime(String service_id , String visit_id , String start_time) throws Exception
    {
          String sql="update " + dbObj.table + " set ";
          
          String field =""
          + "', " + dbObj.service_finish_time + "='" + start_time
          + "' where " + dbObj.service_point_id + "='" + service_id +"'"
          + " and " + dbObj.visit_id + "='" + visit_id +"'" ;
          sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
          return theConnectionInf.eUpdate(sql);
    }
    
    public int updateStartTimeVisit(String visit_id , String start_time) throws Exception
    {
         String sql="update " + dbObj.table + " set ";
         
         String field =""
         + "', " + dbObj.service_start_time + "='" + start_time
         + "', " + dbObj.status + "='" + "2"
         + "' where " + dbObj.visit_id + "='" + visit_id +"'"
         + " and " + dbObj.status + "='" + "1" +"'" ;
         sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
         
         return theConnectionInf.eUpdate(sql);
    }
    public int updateFinishTimeVisit(String visit_id , String start_time) throws Exception
    {
         String sql="update " + dbObj.table + " set ";
         
         String field =""
         + "', " + dbObj.service_finish_time + "='" + start_time
         + "', " + dbObj.status + " ='" + "3"
         + "' where " + dbObj.visit_id + " ='" + visit_id +"'"
         + " and " + dbObj.status + " ='" + "2" +"'" ;
         sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
         
         return theConnectionInf.eUpdate(sql);
    }
    
    public Transfer selectOrderByFinishTime(String visit_id ) throws Exception
    {
        String sql="select * from " + dbObj.table +"" +
        " where " + dbObj.visit_id + "='"+ visit_id + "'" +
        " order by " + dbObj.service_finish_time + " DESC";
        Vector v=eQuery(sql);
        if(v.size() == 0)
            return null;
        else
            return (Transfer)v.get(0);
        
    }
    
    
    //////////////////////////////////////////////////////////////////////////////
    public int delete(String vn) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.visit_id + "='" + vn +"'";
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Transfer selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (Transfer)v.get(0);
    }
    /////list doctor by visit/////////////////////////////////////////////
    public Transfer selectDoctorByVN(String vn) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.visit_id + " = '" + vn + "' and " + dbObj.doctor_code + " <> ''  order by  " + dbObj.assign_time +" DESC ";
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (Transfer)v.get(0);
    }
    /////service point and doctor/////////////////////////////////////////////
    public Vector selectBySpDc(String sp,String dc) throws Exception
    {
        String sql = "select * from " + dbObj.table + " where "
        + dbObj.service_point_id + " = '" + sp + "' and "
        + dbObj.doctor_code + " = '" + dc + "' and ( "
        + dbObj.status + " = '1' or " + dbObj.status + " = '2') "
        + " order by " + dbObj.assign_time;
        
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectByVisitId(String vn) throws Exception
    {
        String sql = "select * from " + dbObj.table + " where "
        + dbObj.visit_id + " = '" + vn + "'" +
        " order by " + dbObj.assign_time;
        return eQuery(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll() throws Exception
    {
        String sql = "select * from " + dbObj.table;
        
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectNotFinish() throws Exception
    {
        String sql = "select * from " + dbObj.table + " where "
        + dbObj.status + " = '1' or " + dbObj.status + " = '2' ";
        Vector v = eQuery(sql);
        if(v.size()==0)	return null;
        else        return v;
    }
    
    //////////////////////////////////////////////////////////////////////////////
    public int udateUnlockVisit(String visit_id) throws Exception
    {
        String sql="update " + dbObj.table + " set "+ dbObj.status + " ='1' "
        
        + "where " + dbObj.visit_id + "='" + visit_id + "' " +
        " and " + dbObj.status + " ='2' " ;
        return theConnectionInf.eUpdate(sql);
        
    }
    //////////////////////////////////////////////////////////////////////////////
    public Transfer queryTransferInServiceByVisitID(String visit_id) throws Exception
    {
        String sql = " Select  * from " + dbObj.table + "" +
        " Where " + dbObj.status + " = '2'" +
        " AND " + dbObj.visit_id + " = '" +visit_id + "'";
        
        Vector v = eQuery(sql);
        
        if(v.size()==0)	return null;
        else        return (Transfer)v.get(0);
        
    }
    //////////////////////////////////////////////////////////////////////////////
    public int udateFinishByVisit(String visit_id) throws Exception
    {
        String sql="update " + dbObj.table + " set "+ dbObj.status + " = '3' "
        + " , " + dbObj.service_finish_time + "= '"  + Gutil.getTextCurrentDateTime(theConnectionInf) + "'"
        + " where " + dbObj.visit_id + "= '" + visit_id + "' " +
        " and (" + dbObj.status + " ='1' " +
        " or " + dbObj.status + " ='2') ";
        return theConnectionInf.eUpdate(sql);
    }
    
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectByServicePointId(String serviceId) throws Exception
    {
        String sql = "select * from " + dbObj.table
        + " where " + dbObj.service_point_id
        + " = '" + serviceId + "' and (" + dbObj.status + " = '1' or " + dbObj.status + " = '2') "
        + " order by " + dbObj.assign_time ;
        // and (" + dbObj.status + " = '1' or " + dbObj.status + " = '2')
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectDoctorByVisitId(String vn) throws Exception
    {
        String sql = "select * from " + dbObj.table + " where "
        + dbObj.visit_id + " = '" + vn + "' and trim(" + dbObj.doctor_code + ") <> '' order by " + dbObj.assign_time;
        
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector eQuery(String sql) throws Exception
    {
        Transfer p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Transfer();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.visit_id = rs.getString(dbObj.visit_id);
            p.patient_id = rs.getString(dbObj.patient_id);
            p.assign_time = rs.getString(dbObj.assign_time);
            p.doctor_code = rs.getString(dbObj.doctor_code);
            p.status = rs.getString(dbObj.status);
            p.service_point_id = rs.getString(dbObj.service_point_id);
            p.service_start_time = rs.getString(dbObj.service_start_time);
            p.service_finish_time = rs.getString(dbObj.service_finish_time);
            p.ward_id = rs.getString(dbObj.ward_id);
            list.add(p);
        }
        rs.close();
        return list;
    }
    //////////////////////////////////////////////////////////////////////////////
    
    public Vector selectByHn(String hn) throws Exception
    {
        String sql = "select visit_id from " + dbObj.table
        + " group by " + dbObj.visit_id + ","
        + dbObj.patient_id
        + " having (((" + dbObj.patient_id + ") = '" + hn + "'))";
        
        Vector v=e1Query(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector e1Query(String sql) throws Exception
    {
        Transfer p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Transfer();
            p.visit_id = rs.getString(dbObj.visit_id);
            list.add(p);
        }
        rs.close();
        return list;
    }
  ////////////////////////////////////////////////////////////////////////  
//    public int updateLockByVid(String vid) throws Exception
//    {
//        String sql = "UPDATE " + dbObj.table + ""
//        + " set " + dbObj.status + " = '" + 2 + "'"
//        + " where " + dbObj.visit_id + "='" + vid
//        + "'";
//        return theConnectionInf.eUpdate(sql);
//    }
}
