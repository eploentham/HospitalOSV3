/*
 * AGRDB.java
 *
 * Created on 25 กรกฎาคม 2548, 14:46 น.
 */

package com.pcu.objdb.objdbclass;

import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
/**
 *
 * @author amp
 */
public class AGRDB
{
    
    /** Creates a new instance of AGRDB */
    public AGRDB()
    {
    }
    
    public ConnectionInf theConnectionInf;
    public AGR dbObj;
    final private String idtable = "766";
    
    public AGRDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new AGR();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_agr";        
        dbObj.pk_field = "t_health_agr_id";
        dbObj.village_id ="t_health_village_id";
        dbObj.agr_number = "agr_number";  
        dbObj.agr_name = "agr_name";        
        dbObj.agr_staff_record ="agr_staff_record";
        dbObj.agr_staff_modify = "agr_staff_modify";   
        dbObj.agr_staff_cancel ="agr_staff_cancel";
        dbObj.agr_record_date_time ="agr_record_date_time";
        dbObj.agr_modify_date_time = "agr_modify_date_time";   
        dbObj.agr_cancel_date_time ="agr_cancel_date_time";
        dbObj.agr_close ="agr_close";
        dbObj.agr_close_date_time = "agr_close_date_time";        
        dbObj.agr_close_note = "agr_close_note";
        dbObj.agr_active = "agr_active";  
        
        return true;
    }
    
    public int insert(AGR o) throws Exception
    {
        String sql="";
        AGR p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.village_id
        + " ,"  + dbObj.agr_number
        + " ,"  + dbObj.agr_name        
        + " ,"  + dbObj.agr_staff_record
        + " ,"	+ dbObj.agr_staff_modify
        + " ,"	+ dbObj.agr_staff_cancel  
        + " ,"  + dbObj.agr_record_date_time
        + " ,"  + dbObj.agr_modify_date_time
        + " ,"	+ dbObj.agr_cancel_date_time
        + " ,"	+ dbObj.agr_close
        + " ,"	+ dbObj.agr_close_date_time    
        + " ,"  + dbObj.agr_close_note
        + " ,"	+ dbObj.agr_active  
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.village_id
        + "','" + p.agr_number
        + "','" + p.agr_name        
        + "','" + p.agr_staff_record
        + "','" + p.agr_staff_modify
        + "','" + p.agr_staff_cancel
        + "','" + p.agr_record_date_time
        + "','" + p.agr_modify_date_time
        + "','" + p.agr_cancel_date_time
        + "','" + p.agr_close
        + "','" + p.agr_close_date_time
        + "','" + p.agr_close_note
        + "','" + p.agr_active
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(AGR o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        AGR p=o;
        String field =""
        + "', " + dbObj.village_id + "='" + p.village_id
        + "', " + dbObj.agr_number + "='" + p.agr_number
        + "', " + dbObj.agr_name + "='" + p.agr_name        
        + "', " + dbObj.agr_staff_record + "='" + p.agr_staff_record
        + "', " + dbObj.agr_staff_modify + "='" + p.agr_staff_modify
        + "', " + dbObj.agr_staff_cancel + "='" + p.agr_staff_cancel        
        + "', " + dbObj.agr_record_date_time + "='" + p.agr_record_date_time
        + "', " + dbObj.agr_modify_date_time + "='" + p.agr_modify_date_time
        + "', " + dbObj.agr_cancel_date_time + "='" + p.agr_cancel_date_time
        + "', " + dbObj.agr_close + "='" + p.agr_close
        + "', " + dbObj.agr_close_date_time + "='" + p.agr_close_date_time    
        + "', " + dbObj.agr_close_note + "='" + p.agr_close_note
        + "', " + dbObj.agr_active + "='" + p.agr_active        
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int updateAGRToActiveZeroByVillageId(String villageId) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        String field =""
        + "', " + dbObj.agr_active + "='0'"        
        + " where " + dbObj.village_id + "='" + villageId +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(AGR o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listAGRByNameOrNumber(String search,String villageId) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            if(search.trim().length() !=0)
            {
                sql = sql + "(" +  dbObj.agr_number
                + " like '%" + search + "%'" + " or "
                + dbObj.agr_name+ " like '%" + search + "%'" + ") and ";
            }
            if(!villageId.equals("0"))
            {
                sql = sql + dbObj.village_id + " = '" + villageId + "' and ";
            }
            sql = sql + dbObj.agr_active + " = '1' order by " + dbObj.agr_number;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        AGR p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new AGR();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.village_id = rs.getString(dbObj.village_id);
            p.agr_number = rs.getString(dbObj.agr_number);
            p.agr_name = rs.getString(dbObj.agr_name);            
            p.agr_staff_record = rs.getString(dbObj.agr_staff_record);
            p.agr_staff_modify = rs.getString(dbObj.agr_staff_modify);
            p.agr_staff_cancel = rs.getString(dbObj.agr_staff_cancel);  
            p.agr_record_date_time = rs.getString(dbObj.agr_record_date_time);
            p.agr_modify_date_time = rs.getString(dbObj.agr_modify_date_time);
            p.agr_cancel_date_time = rs.getString(dbObj.agr_cancel_date_time);
            p.agr_close = rs.getString(dbObj.agr_close);
            p.agr_close_date_time = rs.getString(dbObj.agr_close_date_time);  
            p.agr_close_note = rs.getString(dbObj.agr_close_note);
            p.agr_active = rs.getString(dbObj.agr_active);  
            list.add(p);
        }
        rs.close();
        return list;
    }    

    public Vector selectByNumberVillage(String temple_number, String village_id) throws Exception  {
        String sql="select * from " + dbObj.table + 
                " where " + dbObj.agr_number + " = '" + temple_number + "' " +
                " and " + dbObj.village_id + " = '" + village_id + "' " +
                " and " + dbObj.agr_active + " = '1'" +
                " order by " + dbObj.agr_number;
        return eQuery(sql);
    }
    
}
