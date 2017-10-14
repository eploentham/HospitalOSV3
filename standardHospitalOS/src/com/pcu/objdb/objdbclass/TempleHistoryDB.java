/*
 * TempleHistoryDB.java
 *
 * Created on 4 กรกฎาคม 2548, 17:19 น.
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
public class TempleHistoryDB {
    
    /** Creates a new instance of TempleHistoryDB */
    public TempleHistoryDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public TempleHistory dbObj;
    final private String idtable = "745";
    
    public TempleHistoryDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new TempleHistory();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_temple_history";        
        dbObj.pk_field = "t_health_temple_history_id";
        dbObj.temple_id ="t_health_temple_id";
        dbObj.temple_personel = "temple_personel";   
        dbObj.temple_amount_personel ="temple_amount_personel";
        dbObj.temple_staff_record = "temple_staff_record";
        dbObj.temple_staff_modify ="temple_staff_modify";
        dbObj.temple_record_date_time = "temple_record_date_time";   
        dbObj.temple_modify_date_time ="temple_modify_date_time";        
        
        return true;
    }
    
    public int insert(TempleHistory o) throws Exception
    {
        String sql="";
        TempleHistory p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.temple_id        
        + " ,"  + dbObj.temple_staff_record
        + " ,"  + dbObj.temple_staff_modify
        + " ,"	+ dbObj.temple_record_date_time            
        + " ,"  + dbObj.temple_modify_date_time 
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.temple_id        
        + "','" + p.temple_staff_record
        + "','" + p.temple_staff_modify
        + "','" + p.temple_record_date_time
        + "','" + p.temple_modify_date_time 
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(TempleHistory o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        TempleHistory p=o;
        String field =""
        + "', " + dbObj.temple_id + "='" + p.temple_id        
        + "', " + dbObj.temple_staff_record + "='" + p.temple_staff_record
        + "', " + dbObj.temple_staff_modify + "='" + p.temple_staff_modify
        + "', " + dbObj.temple_record_date_time + "='" + p.temple_record_date_time
        + "', " + dbObj.temple_modify_date_time + "='" + p.temple_modify_date_time
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(TempleHistory o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public String readTempleHistoryByTempleId(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.temple_id + " = '" + search + "' order by "
                + dbObj.temple_record_date_time + " desc";            
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return ((TempleHistory)v.get(0)).getObjectId();
    }
    
    public Vector listTempleHistoryByTempleId(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.temple_id + " = '" + search + "' order by "
                + dbObj.temple_record_date_time + " desc";             
        
        return eQuery(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        TempleHistory p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new TempleHistory();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.temple_id = rs.getString(dbObj.temple_id);            
            p.temple_staff_record = rs.getString(dbObj.temple_staff_record);
            p.temple_staff_modify = rs.getString(dbObj.temple_staff_modify);
            p.temple_record_date_time = rs.getString(dbObj.temple_record_date_time);
            p.temple_modify_date_time = rs.getString(dbObj.temple_modify_date_time);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
