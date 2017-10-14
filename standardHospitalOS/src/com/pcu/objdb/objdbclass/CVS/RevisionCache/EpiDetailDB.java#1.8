/*
 * EpiDetailDB.java
 *
 * Created on 24 มิถุนายน 2548, 10:56 น.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
import com.hospital_os.utility.*;
/**
 *
 * @author Administrator
 */
public class EpiDetailDB {
    
    /** Creates a new instance of EpiDetailDB */
    public EpiDetailDB() {
    }
    
    public ConnectionInf theConnectionInf;
    
    public EpiDetail dbObj;
    final private String idtable = "773";
    
    public EpiDetailDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new EpiDetail();
        initConfig(); 
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_epi_detail";        
        dbObj.pk_field = "t_health_epi_detail_id";        
        dbObj.epi_id = "t_health_epi_id";
        dbObj.epi_set_id = "b_health_epi_set_id";
        dbObj.epi_start = "health_epi_start";
        dbObj.epi_exp = "health_epi_exp";
        dbObj.patient_id = "t_patient_id";
        dbObj.visit_id = "t_visit_id";
        dbObj.staff_record = "health_epi_detail_staff_record";
        dbObj.record_date_time = "record_date_time";
        dbObj.active = "health_epi_detail_active";
        dbObj.lot = "health_epi_detail_lot";
        dbObj.family_id = "t_health_family_id";
        return true;
    }
    
    public int insert(EpiDetail o) throws Exception
    {
        String sql="";
        EpiDetail p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field        
        + " ,"  + dbObj.epi_id
        + " ,"  + dbObj.epi_set_id
        + " ,"  + dbObj.epi_start
        + " ,"  + dbObj.epi_exp
        + " ,"  + dbObj.patient_id
        + " ,"  + dbObj.visit_id
        + " ,"  + dbObj.staff_record
        + " ,"  + dbObj.record_date_time              
        + " ,"  + dbObj.active
        + " ,"  + dbObj.lot
        + " ,"  + dbObj.family_id
        + " ) values ('"
        + p.getObjectId()        
        + "','" +p.epi_id
        + "','" +p.epi_set_id
        + "','" +p.epi_start
        + "','" +p.epi_exp
        + "','" +p.patient_id
        + "','" +p.visit_id
        + "','" +p.staff_record
        + "','" +p.record_date_time            
        + "','" +p.active
        + "','" +p.lot
        + "','" +p.family_id        
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    public int update(EpiDetail o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        EpiDetail p=o;
        String field =""        
        + "', " + dbObj.epi_id+ "='" + p.epi_id
        + "', " + dbObj.epi_set_id+ "='" + p.epi_set_id
        + "', " + dbObj.epi_start+ "='" + p.epi_start
        + "', " + dbObj.epi_exp+ "='" + p.epi_exp
        + "', " + dbObj.patient_id+ "='" + p.patient_id
        + "', " + dbObj.visit_id+ "='" + p.visit_id
        + "', " + dbObj.staff_record+ "='" + p.staff_record
        + "', " + dbObj.record_date_time+ "='" + p.record_date_time        
        + "', " + dbObj.active+ "='" + p.active        
        + "', " + dbObj.lot+ "='" + p.lot        
        + "', " + dbObj.family_id+ "='" + p.family_id
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
     public Vector selectByEpiID(String epi_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.epi_id
        + " = '" + epi_id + "'"
        + " Order by " + dbObj.record_date_time ;
        return eQuery(sql);
     }
    
    public int delete(EpiDetail o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    /*
     * amp
     * ค้นหาประวัติการรับวัคซีน
     * Input : patient_id
     * Output: Vector ของ EpiInSite
     */
    public Vector selectByPatientID(String search) throws Exception 
    {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.patient_id
                + " = '" + search + "'"
                +" and " + dbObj.active+ " = '1'" 
                +" order by "+ dbObj.epi_start;
        return eQuery(sql);
    }
    
    /*
     * jao
     * ค้นหาประวัติการรับวัคซีน
     * Input : family_id
     * Output: Vector ของ EpiInSite
     */
    public Vector selectByFamilyID(String search) throws Exception 
    {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.family_id
                + " = '" + search + "'"
                +" and " + dbObj.active+ " = '1'" 
                +" order by "+ dbObj.epi_start;
        return eQuery(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        EpiDetail p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new EpiDetail();
            p.setObjectId(rs.getString(dbObj.pk_field));            
            p.epi_id = rs.getString(dbObj.epi_id);
            p.epi_set_id = rs.getString(dbObj.epi_set_id);
            p.epi_start = rs.getString(dbObj.epi_start);
            p.epi_exp = rs.getString(dbObj.epi_exp);
            p.patient_id = rs.getString(dbObj.patient_id);
            p.visit_id = rs.getString(dbObj.visit_id);
            p.staff_record = rs.getString(dbObj.staff_record);
            p.record_date_time = rs.getString(dbObj.record_date_time);                
            p.active = rs.getString(dbObj.active);
            p.lot = rs.getString(dbObj.lot);
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
