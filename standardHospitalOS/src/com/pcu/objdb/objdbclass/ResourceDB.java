/*
 * ResourceDB.java
 *
 * Created on 18 กรกฎาคม 2548, 16:31 น.
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
public class ResourceDB {
    
    /** Creates a new instance of ResourceDB */
    public ResourceDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public Resource dbObj;
    public CommunityResource dbObj2;
    final private String idtable = "763";
    
    public ResourceDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Resource();
        dbObj2 = new CommunityResource();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_resource";        
        dbObj.pk_field = "t_health_resource_id";
        dbObj.village_id ="t_health_village_id";
        dbObj.resource_number = "resource_number";   
        dbObj.resource_name ="resource_name";
        dbObj.resource_staff_record ="resource_staff_record";
        dbObj.resource_staff_modify = "resource_staff_modify";   
        dbObj.resource_staff_cancel ="resource_staff_cancel";
        dbObj.resource_record_date_time ="resource_record_date_time";
        dbObj.resource_modify_date_time = "resource_modify_date_time";   
        dbObj.resource_cancel_date_time ="resource_cancel_date_time";
        dbObj.resource_active = "resource_active";      
        
        dbObj2.table = "b_health_resource";        
        dbObj2.pk_field = "b_health_resource_id";
        dbObj2.number ="resource_number";
        dbObj2.description = "resource_description";  
        dbObj2.active = "resource_active";        
        dbObj2.max_communityresource_id = "max_communityresource_id";
        
        return true;
    }
    
    public int insert(Resource o) throws Exception
    {
        String sql="";
        Resource p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.village_id
        + " ,"  + dbObj.resource_number
        + " ,"	+ dbObj.resource_name
        + " ,"  + dbObj.resource_staff_record
        + " ,"	+ dbObj.resource_staff_modify
        + " ,"	+ dbObj.resource_staff_cancel  
        + " ,"  + dbObj.resource_record_date_time
        + " ,"  + dbObj.resource_modify_date_time
        + " ,"	+ dbObj.resource_cancel_date_time
        + " ,"  + dbObj.resource_active        
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.village_id
        + "','" + p.resource_number
        + "','" + p.resource_name
        + "','" + p.resource_staff_record
        + "','" + p.resource_staff_modify
        + "','" + p.resource_staff_cancel
        + "','" + p.resource_record_date_time
        + "','" + p.resource_modify_date_time
        + "','" + p.resource_cancel_date_time
        + "','" + p.resource_active        
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(Resource o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Resource p=o;
        String field =""
        + "', " + dbObj.village_id + "='" + p.village_id
        + "', " + dbObj.resource_number + "='" + p.resource_number
        + "', " + dbObj.resource_name + "='" + p.resource_name
        + "', " + dbObj.resource_staff_record + "='" + p.resource_staff_record
        + "', " + dbObj.resource_staff_modify + "='" + p.resource_staff_modify
        + "', " + dbObj.resource_staff_cancel + "='" + p.resource_staff_cancel        
        + "', " + dbObj.resource_record_date_time + "='" + p.resource_record_date_time
        + "', " + dbObj.resource_modify_date_time + "='" + p.resource_modify_date_time
        + "', " + dbObj.resource_cancel_date_time + "='" + p.resource_cancel_date_time
        + "', " + dbObj.resource_active + "='" + p.resource_active          
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(Resource o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int updateResourceToActiveZeroByVillageId(String villageId) throws Exception
    {
        String sql="update " + dbObj.table + " set";
        String field =""        
        + "', " + dbObj.resource_active + " = '0'"     
        + " where " + dbObj.village_id + " = '" + villageId +"'";
        sql = sql+field.substring(2);
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listResourceByNumber(String search,String villageId) throws Exception
    {
        String sql="select "+dbObj.table+".* from " + dbObj.table +" left join "+dbObj2.table
                +" on "+dbObj.table+"."+dbObj.resource_name+" = "+dbObj2.table+"." +dbObj2.pk_field
                + " where ";            
            if(search.trim().length() !=0)
            {
                sql = sql + dbObj.table+"."+dbObj.resource_number
                + " like '%" + search + "%' " 
                + " or "+dbObj2.description+" like '%"+search+"%' and " ;
            }
            if(!villageId.equals("0"))
            {
                sql = sql + dbObj.village_id + " = '" + villageId + "' and ";
            }
            sql = sql + dbObj.table+"."+dbObj.resource_active + " = '1' order by " + dbObj.table+"."+dbObj.resource_number;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        Resource p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Resource();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.village_id = rs.getString(dbObj.village_id);
            p.resource_number = rs.getString(dbObj.resource_number);
            p.resource_name = rs.getString(dbObj.resource_name);
            p.resource_staff_record = rs.getString(dbObj.resource_staff_record);
            p.resource_staff_modify = rs.getString(dbObj.resource_staff_modify);
            p.resource_staff_cancel = rs.getString(dbObj.resource_staff_cancel);  
            p.resource_record_date_time = rs.getString(dbObj.resource_record_date_time);
            p.resource_modify_date_time = rs.getString(dbObj.resource_modify_date_time);
            p.resource_cancel_date_time = rs.getString(dbObj.resource_cancel_date_time);
            p.resource_active = rs.getString(dbObj.resource_active);             
            list.add(p);
        }
        rs.close();
        return list;
    }

    public Vector selectByNumberVillage(String temple_number, String village_id) throws Exception  {
        String sql="select * from " + dbObj.table + 
                " where " + dbObj.resource_number + " = '" + temple_number + "' " +
                " and " + dbObj.village_id + " = '" + village_id + "' " +
                " and " + dbObj.resource_active + " = '1'" +
                " order by " + dbObj.resource_number;
        return eQuery(sql);
    }
}
