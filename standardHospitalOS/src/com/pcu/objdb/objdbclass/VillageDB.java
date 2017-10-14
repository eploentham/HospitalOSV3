/*
 * VillageDB.java
 *
 * Created on 13 มิถุนายน 2548, 10:05 น.
 */

package com.pcu.objdb.objdbclass;

import java.util.*;
import java.sql.*; 

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;

import com.pcu.object.*;
/**
 *
 * @author amp
 */
public class VillageDB {
    
    /** Creates a new instance of VillageDB */
    public VillageDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public Village dbObj;
    final private String idtable = "712";
    
    public VillageDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Village();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_village";        
        dbObj.pk_field = "t_health_village_id";
        dbObj.village_number ="village_number";
        dbObj.village_name ="village_name";
        dbObj.village_moo = "village_moo";   
        dbObj.village_location ="b_health_village_location_id";
        dbObj.village_tambon = "village_tambon";
        dbObj.village_ampur = "village_ampur";
        dbObj.village_changwat = "village_changwat";
        dbObj.village_staff_record ="village_staff_record";
        dbObj.village_staff_modify = "village_staff_modify";   
        dbObj.village_staff_cancel ="village_staff_cancel";
        dbObj.village_record_date_time ="village_record_date_time";
        dbObj.village_modify_date_time = "village_modify_date_time";   
        dbObj.village_cancel_date_time ="village_cancel_date_time";
        dbObj.village_active = "village_active";  
        
        return true;
    }
    
    public int insert(Village o) throws Exception
    {
        String sql="";
        Village p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.village_number
        + " ,"  + dbObj.village_name
        + " ,"  + dbObj.village_moo
        + " ,"  + dbObj.village_tambon
        + " ,"  + dbObj.village_ampur
        + " ,"  + dbObj.village_changwat
        + " ,"	+ dbObj.village_location
        + " ,"	+ dbObj.village_staff_record    
        + " ,"  + dbObj.village_staff_modify
        + " ,"  + dbObj.village_staff_cancel
        + " ,"	+ dbObj.village_record_date_time
        + " ,"	+ dbObj.village_modify_date_time  
        + " ,"  + dbObj.village_cancel_date_time
        + " ,"  + dbObj.village_active         
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.village_number
        + "','" + p.village_name
        + "','" + p.village_moo
        + "','" + p.village_tambon
        + "','" + p.village_ampur
        + "','" + p.village_changwat
        + "','" + p.village_location
        + "','" + p.village_staff_record
        + "','" + p.village_staff_modify
        + "','" + p.village_staff_cancel
        + "','" + p.village_record_date_time
        + "','" + p.village_modify_date_time
        + "','" + p.village_cancel_date_time
        + "','" + p.village_active
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(Village o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Village p=o;
        String field =""
        + "', " + dbObj.village_number + "='" + p.village_number
        + "', " + dbObj.village_name + "='" + p.village_name
        + "', " + dbObj.village_moo + "='" + p.village_moo
        + "', " + dbObj.village_tambon + "='" + p.village_tambon
        + "', " + dbObj.village_ampur + "='" + p.village_ampur
        + "', " + dbObj.village_changwat + "='" + p.village_changwat
        + "', " + dbObj.village_location + "='" + p.village_location
        + "', " + dbObj.village_staff_record + "='" + p.village_staff_record    
        + "', " + dbObj.village_staff_modify + "='" + p.village_staff_modify
        + "', " + dbObj.village_staff_cancel + "='" + p.village_staff_cancel
        + "', " + dbObj.village_record_date_time + "='" + p.village_record_date_time
        + "', " + dbObj.village_modify_date_time + "='" + p.village_modify_date_time        
        + "', " + dbObj.village_cancel_date_time + "='" + p.village_cancel_date_time
        + "', " + dbObj.village_active + "='" + p.village_active
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(Village o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listVillageByNameOrNumber(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            if(search.trim().length() !=0)
            {
                sql = sql + "(" +  
                dbObj.village_number + " like '%" + search + "%'" + " or " + 
                dbObj.village_moo + " like '%" + search + "%'" + " or " + 
                dbObj.village_name+ " like '%" + search + "%'" + ") and ";
            }
            sql = sql + dbObj.village_active + " = '1' order by " + dbObj.village_moo;
        
        return eQuery(sql);
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vVillage = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " where " + dbObj.village_active + " = '1' order by "
                + dbObj.village_name;//village_number;
        
        vVillage = veQuery(sql);
        
        if(vVillage.size()==0)
            return null;
        else
            return vVillage;        
    }
    
    public Vector selectActive() throws Exception
    {   
        String sql ="select * from " 
                + dbObj.table + " where " + dbObj.village_active + " = '1' order by "
                + dbObj.village_name;//village_number;
        return eQuery(sql);
    }
    public Village readVillageByVillageCode(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.village_number + " = '" + search + "' and "
                + dbObj.village_active + " = '1'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (Village)v.get(0);
    }
    public Village selectByMoo(String moo,String tambon) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.village_moo + " = '" + moo + "' and "
                + dbObj.village_tambon + " = '" + tambon + "' and "
                + dbObj.village_active + " = '1'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (Village)v.get(0);
    }
    
    public Vector selectEqMoo(String moo,String tambon) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.village_moo + " = '" + moo + "' and "
                + dbObj.village_tambon + " = '" + tambon + "' and "
                + dbObj.village_active + " = '1'";
        
        return eQuery(sql);
    }
    
    
    public Village selectMoo0() throws Exception
    {
        String sql="select * from " + dbObj.table +
                " where " + dbObj.village_moo + " = '0' " + 
                " and "  + dbObj.village_active + " = '1'";
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (Village)v.get(0);
    }
    
    public Village readVillageByVillageId(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.pk_field + " = '" + search + "' and "
                + dbObj.village_active + " = '1'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (Village)v.get(0);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        Village p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Village();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.village_number = rs.getString(dbObj.village_number);
            p.village_name = rs.getString(dbObj.village_name);
            p.village_moo = rs.getString(dbObj.village_moo);
            if(p.village_moo==null) 
                p.village_moo = "";
            p.village_tambon = rs.getString(dbObj.village_tambon);
            p.village_ampur = rs.getString(dbObj.village_ampur);
            p.village_changwat = rs.getString(dbObj.village_changwat);
            p.village_location = rs.getString(dbObj.village_location);
            p.village_staff_record = rs.getString(dbObj.village_staff_record);  
            p.village_staff_modify = rs.getString(dbObj.village_staff_modify);
            p.village_staff_cancel = rs.getString(dbObj.village_staff_cancel);
            p.village_record_date_time = rs.getString(dbObj.village_record_date_time);
            p.village_modify_date_time = rs.getString(dbObj.village_modify_date_time);  
            p.village_cancel_date_time = rs.getString(dbObj.village_cancel_date_time);
            p.village_active = rs.getString(dbObj.village_active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public Vector veQuery(String sql) throws Exception
    {
        ComboFix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.village_name);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*
     *@Author : henbe pongtorn
     *@date : 18/03/2549
     *@see : ค้นหมู่บ้านจากเลขที่หมู่
     */     
    public Village selectByNo(String no,String tambon) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.village_moo + " = '" + no+"'";
        if(no!=null && no.length() == 1) sql = sql + " OR "+dbObj.village_moo + " = '" + "0"+no+"'";
                sql = sql + " and "+ dbObj.village_tambon + " = '" + tambon +"'"
                + " and "+ dbObj.village_active + " = '1'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (Village)v.get(0);
    }    
    /*
     *@Author : henbe pongtorn
     *@date : 31/03/2549
     *@see : ค้นข้อมูลจาก PrimaryKey ของมัน
     */     
    public Village selectByPK(String village_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field + " = '" + village_id + "'";
        Vector v=eQuery(sql);
        if(v.isEmpty())
            return null;
        else
            return (Village)v.get(0); 
    } 
}
