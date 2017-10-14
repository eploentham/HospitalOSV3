/*
 * GrowPcuDB.java
 *
 * Created on 26 ÁÔ¶Ø¹ÒÂ¹ 2548, 16:45 ¹.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;

import com.pcu.object.*;
/**
 *
 * @author Administrator
 */
public class GrowPcuDB {
    
    /** Creates a new instance of GrowPcuDB */
    public GrowPcuDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public GrowPcu dbObj;
    final private String idtable = "743";
    
    public GrowPcuDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new GrowPcu();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_grow";        
        dbObj.pk_field = "t_health_grow_id";
        dbObj.patient_id ="t_patient_id";
        dbObj.grow_vn = "health_grow_vn";
        dbObj.office_id ="b_visit_office_id";
        dbObj.visit_id = "t_visit_id";
        dbObj.grow_hn = "health_grow_hn";        
        dbObj.record_date_time = "record_date_time";        
        dbObj.staff_record = "health_grow_staff_record";
        dbObj.active ="health_grow_active";     
        dbObj.family_id = "t_health_family_id";
        return true;
    }
    
    public int insert(GrowPcu o) throws Exception
    {
        String sql="";
        GrowPcu p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.patient_id 
        + " ,"  + dbObj.grow_vn 
        + " ,"  + dbObj.office_id
        + " ,"  + dbObj.visit_id 
        + " ,"  + dbObj.grow_hn 
        + " ,"  + dbObj.record_date_time 
        + " ,"  + dbObj.staff_record 
        + " ,"  + dbObj.active 
        + " ,"  + dbObj.family_id
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.patient_id 
        + "','" + p.grow_vn 
        + "','" + p.office_id
        + "','" + p.visit_id 
        + "','" + p.grow_hn 
        + "','" + p.record_date_time 
        + "','" + p.staff_record 
        + "','" + p.active 
        + "','" + p.family_id        
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(GrowPcu o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        GrowPcu p=o;        
        String field =""
        + "', " + dbObj.patient_id + "='" + p.patient_id
        + "', " + dbObj.grow_vn + "='" + p.grow_vn
        + "', " + dbObj.office_id + "='" + p.office_id
        + "', " + dbObj.visit_id + "='" + p.visit_id
        + "', " + dbObj.grow_hn + "='" + p.grow_hn
        + "', " + dbObj.record_date_time + "='" + p.record_date_time   
        + "', " + dbObj.staff_record + "='" + p.staff_record   
        + "', " + dbObj.active + "='" + p.active   
        + "', " + dbObj.family_id + "='" + p.family_id   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(GrowPcu o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vGrowPcu = new Vector();
        
        String sql ="select * from " 
                + dbObj.table;// + " order by "
                //+ dbObj.pk_field;
        
        return eQuery(sql);    
    }
    
    public Vector selectByPatientID(String patient_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.patient_id
        + " = '" + patient_id + "'"
        + " and " + dbObj.active + " = '1' ";
        return eQuery(sql);
    }
    
     public Vector selectByFamilyID(String family_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.family_id
        + " = '" + family_id + "'"
        + " and " + dbObj.active + " = '1' ";
        return eQuery(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        GrowPcu p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new GrowPcu();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.patient_id = rs.getString(dbObj.patient_id); 
            p.grow_vn = rs.getString(dbObj.grow_vn); 
            p.office_id = rs.getString(dbObj.office_id); 
            p.visit_id = rs.getString(dbObj.visit_id); 
            p.grow_hn = rs.getString(dbObj.grow_hn); 
            p.record_date_time = rs.getString(dbObj.record_date_time);    
            p.staff_record = rs.getString(dbObj.staff_record);    
            p.active = rs.getString(dbObj.active);    
            p.family_id = rs.getString(dbObj.family_id);    
            list.add(p);
        }
        rs.close();
        return list;
    }
/////////////////////////////////////////////////////////////////////////////    
    public int updateFidByFid(String family_id,String family_from) throws Exception
    {
        String sql="update " + dbObj.table + " set " 
                + dbObj.family_id + "='" + family_id
                + "' where " + dbObj.family_id + "='" + family_from +"'";
        return theConnectionInf.eUpdate(sql);
    }  
/////////////////////////////////////////////////////////////////////////////    
    public int updateFidByPtid(String family_id,String patient_id) throws Exception
    {
        String sql="update " + dbObj.table + " set " 
                + dbObj.family_id + "='" + family_id
                + "' where " + dbObj.patient_id + "='" + patient_id +"'";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }     


    public int selectCount() throws Exception {
             String sql="select count(*) as count from " + dbObj.table;
             ResultSet rs = theConnectionInf.eQuery(sql);
             if(rs.next()){
                 return rs.getInt("count");
             }
             return 0;
    }
    
    
}
