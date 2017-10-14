/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java*/
package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
public class TransferDB
{
    public ConnectionInf theConnectionInf;
    public Transfer dbObj;
    final public String idtable = "249";/*"213";*/
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public TransferDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Transfer();
        initConfig();
    }
    
    public TransferDB()
    {
        theConnectionInf= null;
        dbObj = new Transfer();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="t_visit_service";
        dbObj.pk_field="t_visit_service_id";
        dbObj.visit_id   ="t_visit_id";
        dbObj.patient_id   ="t_patient_id";
        dbObj.assign_time   ="assign_date_time";
        dbObj.doctor_code   ="visit_service_staff_doctor";
        dbObj.status   ="f_visit_service_status_id";
        dbObj.service_point_id="b_service_point_id";
        dbObj.service_start_time="visit_service_treatment_date_time";
        dbObj.service_finish_time ="visit_service_finish_date_time";
        dbObj.ward_id = "b_visit_ward_id";
        /*
        dbObj.table = "transfer";
        dbObj.pk_field = "tranferance_id";
        dbObj.visit_id = "visit_id";
        dbObj.patient_id = "patient_id";
        dbObj.assign_time = "assign_time";
        dbObj.doctor_code = "doctor_code";
        dbObj.status = "status";
        dbObj.service_point_id = "service_point_id";
         */
        return true;
    }
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(Transfer o) throws Exception
    {    
         
         Transfer p=o;
         p.generateOID(idtable);
         StringBuffer sql = new StringBuffer("insert into " ).append( dbObj.table ).append( " ("
         ).append( dbObj.pk_field
         ).append( " ,"	).append( dbObj.visit_id
         ).append( " ,"	).append( dbObj.patient_id
         ).append( " ,"	).append( dbObj.assign_time
         /*       ).append( " ,"	).append( dbObj.clinic_code*/
         ).append( " ,"	).append( dbObj.doctor_code
         ).append( " ,"	).append( dbObj.status
         ).append( " ,"  ).append( dbObj.service_point_id
         ).append( " ,"  ).append( dbObj.service_start_time
         ).append( " ,"  ).append( dbObj.service_finish_time
         ).append( " ) values ('"
         ).append( p.getObjectId()
         ).append( "','" ).append( p.visit_id
         ).append( "','" ).append( p.patient_id
         ).append( "','" ).append( p.assign_time
         /*        ).append( "','" ).append( p.clinic_code*/
         ).append( "','" ).append( p.doctor_code
         ).append( "','" ).append( p.status
         
         ).append( "','" ).append( p.service_point_id
         ).append( "','" ).append( p.service_start_time
         ).append( "','" ).append( p.service_finish_time
         
         ).append( "')");
         
         return theConnectionInf.eUpdate(sql.toString());
    }
    
    public Transfer selectTimeByVisitIDKeyID(String visit_id,String service_point_id) throws Exception
    {
        StringBuffer sql = new StringBuffer("SELECT * FROM " ).append( dbObj.table
        ).append( " WHERE " ).append( dbObj.visit_id ).append( " = '" ).append( visit_id ).append( "'"
        /*     ).append( " AND " ).append( dbObj.service_point_id ).append( " = '" ).append( service_point_id ).append( "'"*/
        ).append( " AND " ).append( dbObj.status ).append( " <> '" ).append( "3" ).append( "'");
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (Transfer)v.get(0);
    }
    
    public int update(Transfer o) throws Exception
    {    
         Transfer p = o;
         StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
         ).append( dbObj.visit_id ).append( "='" ).append( p.visit_id
         ).append( "', " ).append( dbObj.patient_id ).append( "='" ).append( p.patient_id
         ).append( "', " ).append( dbObj.assign_time ).append( "='" ).append( p.assign_time
         /*        ).append( "', " ).append( dbObj.clinic_code ).append( "='" ).append( p.clinic_code*/
         ).append( "', " ).append( dbObj.doctor_code ).append( "='" ).append( p.doctor_code
         ).append( "', " ).append( dbObj.status ).append( "='" ).append( p.status
         ).append( "', " ).append( dbObj.service_point_id ).append( "='" ).append( p.service_point_id
         ).append( "', " ).append( dbObj.service_start_time ).append( "='" ).append( p.service_start_time
         ).append( "', " ).append( dbObj.service_finish_time ).append( "='" ).append( p.service_finish_time
         ).append( "' where " ).append( dbObj.pk_field ).append( "='" ).append( p.getObjectId() ).append("'");
         
         return theConnectionInf.eUpdate(sql.toString());
    }
    public int updateServiceStartTime(String service_id , String visit_id , String start_time) throws Exception
    {    
         StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
         ).append( dbObj.service_start_time ).append( "='" ).append( start_time
         ).append( "', " ).append( dbObj.status ).append( "='" ).append( "2"
         ).append( "' where " ).append( dbObj.service_point_id ).append( "='" ).append( service_id ).append("'"
         ).append( " and " ).append( dbObj.visit_id ).append( "='" ).append( visit_id ).append("'"
         ).append( " and " ).append( dbObj.status ).append( "='" ).append( "1" ).append("'" );
         
         
         return theConnectionInf.eUpdate(sql.toString());
    }
    
    public int updateServiceFinishTime(String service_id , String visit_id , String start_time) throws Exception
    {     
          
          StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
          ).append( dbObj.service_finish_time ).append( "='" ).append( start_time
          ).append( "' where " ).append( dbObj.service_point_id ).append( "='" ).append( service_id ).append("'"
          ).append( " and " ).append( dbObj.visit_id ).append( "='" ).append( visit_id ).append("'") ;
          
          return theConnectionInf.eUpdate(sql.toString());
    }
    
    public int updateStartTimeVisit(String visit_id , String start_time) throws Exception
    {    
         StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
         ).append( dbObj.service_start_time ).append( "='" ).append( start_time
         ).append( "', " ).append( dbObj.status ).append( "='" ).append( "2"
         ).append( "' where " ).append( dbObj.visit_id ).append( "='" ).append( visit_id ).append("'"
         ).append( " and " ).append( dbObj.status ).append( "='" ).append( "1" ).append("'") ;
         
         
         return theConnectionInf.eUpdate(sql.toString());
    }
    public int updateFinishTimeVisit(String visit_id , String start_time) throws Exception
    {    
         StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
                 ).append( dbObj.service_finish_time ).append( "='" ).append( start_time
         ).append( "', " ).append( dbObj.status ).append( "='" ).append( "3"
         ).append( "' where " ).append( dbObj.visit_id ).append( "='" ).append( visit_id ).append("'"
         ).append( " and " ).append( dbObj.status ).append( "='" ).append( "2" ).append("'") ;
         
         
         return theConnectionInf.eUpdate(sql.toString());
    }
    
    public Transfer selectOrderByFinishTime(String visit_id ) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table ).append("" ).append(
        " where " ).append( dbObj.visit_id ).append( "='").append( visit_id ).append( "'" ).append(
        " order by " ).append( dbObj.service_finish_time ).append( " DESC");
        Vector v=eQuery(sql.toString());
        if(v.size() == 0)
            return null;
        else
            return (Transfer)v.get(0);
        
    }
    
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public int delete(String vn) throws Exception
    {   
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.visit_id ).append( "='" ).append( vn ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Transfer selectByPK(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field
        ).append( " = '" ).append( pk ).append( "'");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (Transfer)v.get(0);
    }
    /*///list doctor by visit/////////////////////////////////////////////*/
    public Transfer selectDoctorByVN(String vn) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.visit_id ).append( " = '" ).append( vn )
                .append( "' and " ).append( dbObj.doctor_code )
                .append( " <> ''  order by  " ).append( dbObj.assign_time ).append(" DESC ");
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (Transfer)v.get(0);
    }
    /*///service point and doctor/////////////////////////////////////////////*/
    public Vector selectBySpDc(String sp,String dc) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table ).append( " where "
        ).append( dbObj.service_point_id ).append( " = '" ).append( sp ).append( "' and "
        ).append( dbObj.doctor_code ).append( " = '" ).append( dc ).append( "' and ( "
        ).append( dbObj.status ).append( " = '1' or " ).append( dbObj.status ).append( " = '2') "
        ).append( " order by " ).append( dbObj.assign_time);
        
        Vector v = eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectByVisitId(String vn) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table ).append( " where "
        ).append( dbObj.visit_id ).append( " = '" ).append( vn ).append( "'" ).append(
        " order by " ).append( dbObj.assign_time);
        
        Vector v = eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAll() throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        
        Vector v = eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectNotFinish() throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table ).append( " where "
        ).append( dbObj.status ).append( " = '1' or " ).append( dbObj.status ).append( " = '2' ");
        Vector v = eQuery(sql.toString());
        if(v.size()==0)	return null;
        else        return v;
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public int udateUnlockVisit(String visit_id) throws Exception
    {   
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set ").append( dbObj.status ).append( " ='1' "
        
        ).append( "where " ).append( dbObj.visit_id ).append( "='" ).append( visit_id ).append( "' " ).append(
        " and " ).append( dbObj.status ).append( " ='2' " );
        return theConnectionInf.eUpdate(sql.toString());
        
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Transfer queryTransferInServiceByVisitID(String visit_id) throws Exception
    {
        StringBuffer sql = new StringBuffer(" Select  * from " ).append( dbObj.table ).append( "" ).append(
        " Where " ).append( dbObj.status ).append( " = '2'" ).append(
        " AND " ).append( dbObj.visit_id ).append( " = '" ).append(visit_id ).append( "'");
        
        Vector v = eQuery(sql.toString());
        
        if(v.size()==0)	return null;
        else        return (Transfer)v.get(0);
        
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public int udateFinishByVisit(String visit_id) throws Exception
    {   
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set ").append( dbObj.status ).append( " = '3' "
        ).append( " , " ).append( dbObj.service_finish_time ).append( "= '"  ).append( Gutil.getTextCurrentDateTime(theConnectionInf) ).append( "'"
        ).append( " where " ).append( dbObj.visit_id ).append( "= '" ).append( visit_id ).append( "' " ).append(
        " and (" ).append( dbObj.status ).append( " ='1' " ).append(
        " or " ).append( dbObj.status ).append( " ='2') ");
        return theConnectionInf.eUpdate(sql.toString());
        
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectByServicePointId(String serviceId) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.service_point_id
        ).append( " = '" ).append( serviceId ).append( "' and (" ).append( dbObj.status ).append( " = '1' or " ).append( dbObj.status ).append( " = '2') "
        ).append( " order by " ).append( dbObj.assign_time );
        /* and (" ).append( dbObj.status ).append( " = '1' or " ).append( dbObj.status ).append( " = '2')*/
        Vector v = eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectDoctorByVisitId(String vn) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table ).append( " where "
        ).append( dbObj.visit_id ).append( " = '" ).append( vn ).append( "' and trim(" ).append( dbObj.doctor_code ).append( ") <> '' " ).append(
        " order by " ).append( dbObj.assign_time ).append( " desc ");
        
        Vector v = eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector eQuery(String sql) throws Exception
    {
        Transfer p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
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
            
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    /**
     *
     * @deprecate
     */
    public Vector selectByHn(String hn) throws Exception
    {
        return selectByPtid(hn);
    }
    public Vector selectByPtid(String hn) throws Exception
    {
        StringBuffer sql = new StringBuffer("select visit_id from " ).append( dbObj.table
        ).append( " group by " ).append( dbObj.visit_id ).append( ","
        ).append( dbObj.patient_id
        ).append( " having (((" ).append( dbObj.patient_id ).append( ") = '" ).append( hn ).append( "'))");
        
        Vector v=e1Query(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector e1Query(String sql) throws Exception
    {
        Transfer p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        while(rs.next())
        {
            p = new Transfer();
            p.visit_id = rs.getString(dbObj.visit_id);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
}
