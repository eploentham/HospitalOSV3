/*
 * PPCareChildDB.java
 *
 * Created on 3 สิงหาคม 2548, 17:13 น.
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
public class PPCareChildDB
{
    
    /** Creates a new instance of PPCareChildDB */
    public PPCareChildDB()
    {
    }
    
    public ConnectionInf theConnectionInf;
    public PPCareChild dbObj;
//    final private String idtable = "772";
    
    public PPCareChildDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PPCareChild();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_pp_care_child";
        dbObj.pk_field = "t_health_pp_care_child_id";
        dbObj.t_health_pp_care_id = "t_health_pp_care_id";
        dbObj.t_visit_id = "t_visit_id";
        dbObj.t_health_family_id = "t_health_family_id";
        dbObj.bddoctor = "pp_care_child_bddoctor";
        dbObj.birth_date = "pp_care_child_birth_date";
        dbObj.birth_method = "pp_care_child_birth_method";
        dbObj.birth_place = "pp_care_child_birth_place";
        dbObj.born_at_hospital = "pp_care_child_born_at_hospital";
        dbObj.born_weight = "pp_care_child_born_weight";
        dbObj.get_vitk = "pp_care_child_get_vitk";
        dbObj.gravida = "pp_care_child_gravida";
        dbObj.mother_pid = "pp_care_child_mother_pid";
        dbObj.oxygen = "pp_care_child_oxygen";
        dbObj.result = "pp_care_child_result";
        return true;
    }
    
    public int insert(PPCareChild o) throws Exception
    {
        String sql="";
        PPCareChild p=o;
        if(p.getObjectId()==null)
            p.generateOID("");
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field  
         + " ," + dbObj.t_health_pp_care_id
         + " ," + dbObj.t_visit_id
         + " ," + dbObj.t_health_family_id
         + " ," + dbObj.bddoctor
         + " ," + dbObj.birth_date
         + " ," + dbObj.birth_method
         + " ," + dbObj.birth_place
         + " ," + dbObj.born_at_hospital
         + " ," + dbObj.born_weight
         + " ," + dbObj.get_vitk
         + " ," + dbObj.gravida
         + " ," + dbObj.mother_pid
         + " ," + dbObj.oxygen
         + " ," + dbObj.result
        + " ) values ('"
        + p.getObjectId()  
         + "','" + p.t_health_pp_care_id
         + "','" + p.t_visit_id
         + "','" + p.t_health_family_id
         + "','" + p.bddoctor
         + "','" + p.birth_date
         + "','" + p.birth_method
         + "','" + p.birth_place
         + "','" + p.born_at_hospital
         + "','" + p.born_weight
         + "','" + p.get_vitk
         + "','" + p.gravida
         + "','" + p.mother_pid
         + "','" + p.oxygen
         + "','" + p.result
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    
    public int update(PPCareChild o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        PPCareChild p=o;
        String field =""    
         + "', " + dbObj.t_health_pp_care_id + "='" + p.t_health_pp_care_id
         + "', " + dbObj.t_visit_id + "='" + p.t_visit_id
         + "', " + dbObj.t_health_family_id + "='" + p.t_health_family_id
         + "', " + dbObj.bddoctor + "='" + p.bddoctor
         + "', " + dbObj.birth_date + "='" + p.birth_date
         + "', " + dbObj.birth_method + "='" + p.birth_method
         + "', " + dbObj.birth_place + "='" + p.birth_place
         + "', " + dbObj.born_at_hospital + "='" + p.born_at_hospital
         + "', " + dbObj.born_weight + "='" + p.born_weight
         + "', " + dbObj.get_vitk + "='" + p.get_vitk
         + "', " + dbObj.gravida + "='" + p.gravida
         + "', " + dbObj.mother_pid + "='" + p.mother_pid
         + "', " + dbObj.oxygen + "='" + p.oxygen
         + "', " + dbObj.result + "='" + p.result
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        
        return theConnectionInf.eUpdate(sql);
    }
    
    
    public int delete(PPCareChild o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    
    public PPCareChild readByPPCare(String ppcare_id)  throws Exception {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.t_health_pp_care_id + " = '" + ppcare_id + "'";
        Vector v = eQuery(sql);
        if(v.isEmpty())
            return null;
        else
            return (PPCareChild)v.get(0);
    }
    public PPCareChild readByFid(String f_id)  throws Exception {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.t_health_family_id + " = '" + f_id + "' order by pp_care_child_gravida desc";
        Vector v = eQuery(sql);
        if(v.isEmpty())
            return null;
        else
            return (PPCareChild)v.get(0);
    }
    public PPCareChild readByFidAndGravida(String f_id,String gravida)  throws Exception {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.t_health_family_id + " = '" + f_id + "' and "
                + dbObj.gravida + " = '" + gravida + "'";
        Vector v = eQuery(sql);
        if(v.isEmpty())
            return null;
        else
            return (PPCareChild)v.get(0);
    }
    public Vector eQuery(String sql) throws Exception
    {
        PPCareChild p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PPCareChild();
            p.setObjectId(rs.getString(dbObj.pk_field));            
            p.t_health_pp_care_id = rs.getString(dbObj.t_health_pp_care_id);
            p.t_health_family_id = rs.getString(dbObj.t_health_family_id);
            p.t_visit_id = rs.getString(dbObj.t_visit_id);
            p.bddoctor = rs.getString(dbObj.bddoctor);
            p.birth_date = rs.getString(dbObj.birth_date);
            p.birth_method = rs.getString(dbObj.birth_method);
            p.birth_place = rs.getString(dbObj.birth_place);
            p.born_at_hospital = rs.getString(dbObj.born_at_hospital);
            p.born_weight = rs.getString(dbObj.born_weight);
            p.get_vitk = rs.getString(dbObj.get_vitk);
            p.gravida = rs.getString(dbObj.gravida);
            p.mother_pid = rs.getString(dbObj.mother_pid);
            p.oxygen = rs.getString(dbObj.oxygen);
            p.result = rs.getString(dbObj.result);
            list.add(p);
        }
        rs.close();
        return list;
    }
    

    
}
