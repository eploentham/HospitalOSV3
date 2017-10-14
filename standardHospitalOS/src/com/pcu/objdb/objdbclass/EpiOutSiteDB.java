/*
 * EpiOutSiteDB.java
 *
 * Created on 5 กันยายน 2548, 16:38 น.
 */

package com.pcu.objdb.objdbclass;
import com.hospital_os.utility.Gutil;
import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
/**
 *
 * @author jao
 */
public class EpiOutSiteDB {
    
    /** Creates a new instance of EpiOutSiteDB */
   public EpiOutSiteDB() {
    }
    public ConnectionInf theConnectionInf;
    public EpiOutSite dbObj;
    final private String idtable = "711";
    
    public EpiOutSiteDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new EpiOutSite();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_epi_outsite";        
        dbObj.pk_field = "t_health_epi_outsite_id";
        dbObj.epi_outsite_name ="t_health_epi_outsite_name";
        dbObj.epi_outsite_office = "b_epi_outsite_office_id";   
        dbObj.epi_outsite_date ="epi_outsite_date";
        dbObj.remark = "epi_outsite_remark";
        dbObj.patient_id = "t_patient_id";
        dbObj.hn = "epi_outsite_hn"; 
        dbObj.office = "b_visit_office_id";
        dbObj.record_date = "record_date_time";
        dbObj.modify_date = "modify_date_time";
        dbObj.cancel_date = "cancel_date_time";
        dbObj.staff_record = "health_epi_outsite_staff_record";
        dbObj.staff_modify = "health_epi_outsite_staff_modify";
        dbObj.staff_cancel = "health_epi_outsite_staff_cancel";
        dbObj.active = "epi_outsite_active";
        dbObj.family_id = "t_health_family_id";
        dbObj.health_epi_group_id = "b_health_epi_group_id";
        
        return true;
    }
    
    public int insert(EpiOutSite o) throws Exception
    {
        String sql="";
        EpiOutSite p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.epi_outsite_name
        + " ,"  + dbObj.epi_outsite_office
        + " ,"	+ dbObj.epi_outsite_date
        + " ,"	+ dbObj.remark
        + " ,"	+ dbObj.patient_id
        + " ,"	+ dbObj.hn
        + " ,"	+ dbObj.office
        + " ,"	+ dbObj.record_date
        + " ,"	+ dbObj.modify_date
        + " ,"	+ dbObj.cancel_date
        + " ,"	+ dbObj.staff_record
        + " ,"	+ dbObj.staff_modify
        + " ,"	+ dbObj.staff_cancel
        + " ,"	+ dbObj.active
        + " ,"	+ dbObj.family_id
        + " ,"	+ dbObj.health_epi_group_id
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.epi_outsite_name
        + "','" + p.epi_outsite_office
        + "','" + p.epi_outsite_date
        + "','" + p.remark
        + "','" + p.patient_id
        + "','" + p.hn
        + "','" + p.office
        + "','" + p.record_date
        + "','" + p.modify_date
        + "','" + p.cancel_date
        + "','" + p.staff_record
        + "','" + p.staff_modify
        + "','" + p.staff_cancel
        + "','" + p.active
        + "','" + p.family_id   
        + "','" + p.health_epi_group_id
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(EpiOutSite o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        EpiOutSite p=o;
        String field =""
        + "', " + dbObj.epi_outsite_name + "='" + p.epi_outsite_name
        + "', " + dbObj.epi_outsite_office + "='" + p.epi_outsite_office
        + "', " + dbObj.epi_outsite_date + "='" + p.epi_outsite_date
        + "', " + dbObj.remark+ "='" + p.remark
        + "', " + dbObj.patient_id + "='" + p.patient_id
        + "', " + dbObj.hn + "='" + p.hn
        + "', " + dbObj.office + "='" + p.office
        + "', " + dbObj.record_date + "='" + p.record_date
        + "', " + dbObj.modify_date + "='" + p.modify_date
        + "', " + dbObj.cancel_date + "='" + p.cancel_date
        + "', " + dbObj.staff_record + "='" + p.staff_record
        + "', " + dbObj.staff_modify + "='" + p.staff_modify
        + "', " + dbObj.staff_cancel + "='" + p.staff_cancel
        + "', " + dbObj.active + "='" + p.active    
        + "', " + dbObj.family_id + "='" + p.family_id
        + "', " + dbObj.health_epi_group_id + "='" + p.health_epi_group_id
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(EpiOutSite o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectByPatientID(String search) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.patient_id
                + " = '" + search + "'"
                +" and " + dbObj.active+ " = '1'" 
                +" order by "+dbObj.record_date ;
        return eQuery(sql);
    }
    
    public Vector selectByFamilyID(String search) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.family_id
                + " = '" + search + "'"
                +" and " + dbObj.active+ " = '1'" 
                +" order by "+dbObj.record_date ;
        return eQuery(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        EpiOutSite p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new EpiOutSite();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.epi_outsite_name = rs.getString(dbObj.epi_outsite_name);
            p.epi_outsite_office = rs.getString(dbObj.epi_outsite_office);
            p.epi_outsite_date = rs.getString(dbObj.epi_outsite_date);
            p.remark= rs.getString(dbObj.remark); 
            p.patient_id = rs.getString(dbObj.patient_id);            
            p.hn = rs.getString(dbObj.hn);            
            p.office = rs.getString(dbObj.office);            
            p.record_date = rs.getString(dbObj.record_date);            
            p.modify_date = rs.getString(dbObj.modify_date);            
            p.cancel_date = rs.getString(dbObj.cancel_date);            
            p.staff_record = rs.getString(dbObj.staff_record);            
            p.staff_modify = rs.getString(dbObj.staff_modify);
            p.staff_cancel = rs.getString(dbObj.staff_cancel); 
            p.active = rs.getString(dbObj.active); 
            p.family_id = rs.getString(dbObj.family_id);
            p.health_epi_group_id = rs.getString(dbObj.health_epi_group_id);
            list.add(p);
        }
        rs.close();
        return list;
    }
/////////////////////////////////////////////////////////////////////////////    
    public int updateFidByFid(String family_id,String family_from) throws Exception
    {
        String sql="update " + dbObj.table + " set " 
                + dbObj.family_id + "='" + family_id + "',"
                + dbObj.staff_cancel + "="+ dbObj.staff_cancel + "||'-'||'" + family_from + "'"
                + " where " + dbObj.family_id + "='" + family_from +"'";
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
