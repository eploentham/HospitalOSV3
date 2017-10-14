/*
 * BugCarrierDB.java
 *
 * Created on 11 ÁÔ¶Ø¹ÒÂ¹ 2548, 14:22 ¹.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
/**
 *
 * @author Administrator
 */
public class BugCarrierDB {
    
    /** Creates a new instance of BugCarrierDB */
    public BugCarrierDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public BugCarrier dbObj;
    final private String idtable = "720";
    
    public BugCarrierDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new BugCarrier();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_home_bug_control";        
        dbObj.pk_field = "t_health_home_bug_control_id";
        dbObj.sub_home_id ="t_health_sub_home_id";
        dbObj.rat_control = "health_home_rat_control";   
        dbObj.cockroach_control ="health_home_cockroach_control";
        dbObj.fly_control = "health_home_fly_control";
        dbObj.bug_control = "health_home_bug_control";
        dbObj.home_pet = "health_home_pet";
        dbObj.dung_control_id = "f_health_home_dung_control_id";
        dbObj.animal_control = "health_home_animal_control";
        
        return true;
    }
    
    public int insert(BugCarrier o) throws Exception
    {
        String sql="";
        BugCarrier p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.sub_home_id
        + " ,"  + dbObj.rat_control
        + " ,"	+ dbObj.cockroach_control
        + " ,"	+ dbObj.fly_control 
        + " ,"	+ dbObj.bug_control
        + " ,"	+ dbObj.home_pet
        + " ,"	+ dbObj.dung_control_id
        + " ,"	+ dbObj.animal_control
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.sub_home_id
        + "','" + p.rat_control
        + "','" + p.cockroach_control
        + "','" + p.fly_control
        + "','" + p.bug_control  
        + "','" + p.home_pet  
        + "','" + p.dung_control_id
        + "','" + p.animal_control
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(BugCarrier o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        BugCarrier p=o;
        String field =""
        + "', " + dbObj.sub_home_id + "='" + p.sub_home_id
        + "', " + dbObj.rat_control + "='" + p.rat_control
        + "', " + dbObj.cockroach_control + "='" + p.cockroach_control
        + "', " + dbObj.fly_control + "='" + p.fly_control 
        + "', " + dbObj.bug_control + "='" + p.bug_control        
        + "', " + dbObj.home_pet + "='" + p.home_pet        
        + "', " + dbObj.dung_control_id + "='" + p.dung_control_id        
        + "', " + dbObj.animal_control + "='" + p.animal_control        
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(BugCarrier o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listBugCarrierBySubHome(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            
                sql = sql + dbObj.sub_home_id
                + " like '%" + search + "%'";
          
        return eQuery(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        BugCarrier p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new BugCarrier();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.sub_home_id = rs.getString(dbObj.sub_home_id);
            p.rat_control = rs.getString(dbObj.rat_control);
            p.cockroach_control = rs.getString(dbObj.cockroach_control);
            p.fly_control = rs.getString(dbObj.fly_control); 
            p.bug_control = rs.getString(dbObj.bug_control); 
            p.home_pet = rs.getString(dbObj.home_pet); 
            p.dung_control_id = rs.getString(dbObj.dung_control_id); 
            p.animal_control = rs.getString(dbObj.animal_control); 
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
