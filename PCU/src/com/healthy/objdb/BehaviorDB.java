/*
 * BehaviorDB.java
 *
 * Created on 5 เมษายน 2549, 18:10 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.healthy.objdb;
import com.healthy.object.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author hospitalos5
 */
public class BehaviorDB {
    
    Behavior dbObj;
    public ConnectionInf theConnectionInf;
    final private String idtable = "ht001";
    
    /** Creates a new instance of BehaviorDB */
    public BehaviorDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new Behavior();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_behavior";
        
        dbObj.pk_field = "t_health_behavior_id";
        //dbObj.behavior_id = "t_health_behavior_id";
        dbObj.idy = "behavior_idy";
        dbObj.date_serv = "behavior_date_serv";
        dbObj.weight = "behavior_weight";
        dbObj.height = "behavior_height";
        dbObj.bmi = "behavior_bmi";
        dbObj.waist = "behavior_waist";
        dbObj.hx_ht = "behavior_hx_ht";
        dbObj.hx_dm = "behavior_hx_dm";
        dbObj.hx_hdd = "behavior_hx_hdd";
        dbObj.family_id = "t_health_family_id";
        dbObj.strain = "behavior_strain";
        dbObj.mood = "behavior_mood";
        dbObj.sexBehavior = "behavior_sex_behavior";
        dbObj.sexProtect = "behavior_sex_protect";
        dbObj.fat = "behavior_fat";
        dbObj.salt = "behavior_salt";
        dbObj.sweet = "behavior_sweet";
        dbObj.egg = "behavior_egg";
        dbObj.rare = "behavior_rare";
        dbObj.ciga = "behavior_ciga";
        dbObj.alcohol = "behavior_alcohol";
        dbObj.drug = "behavior_drug";
        dbObj.accident = "behavior_accident";
        dbObj.ht_result = "behavior_ht_result";
        dbObj.dm_result = "behavior_dm_result";
        dbObj.cancerv_result = "behavior_cancerv_result";
        dbObj.cabrea_result = "behavior_cabrea_result";
        dbObj.is_chronic = "behavior_is_chronic";
        dbObj.chronic = "behavior_chronic";
        dbObj.drug_other = "behavior_drug_other";
        dbObj.record_time = "behavior_record_time";
        dbObj.modify_time = "behavior_modify_time";
        dbObj.cancel_time = "behavior_cancel_time";
        dbObj.staff_record = "behavior_staff_record";
        dbObj.staff_modify = "behavior_staff_modify";
        dbObj.staff_cancel = "behavior_staff_cancel";
        dbObj.active = "behavior_active";
        
        return true;
    }
     public int insert(Behavior o) throws Exception
    {
        String sql="";
        Behavior p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        //+ " ,"  + dbObj.behavior_id
        + " ,"  + dbObj.idy
        + " ,"  + dbObj.date_serv
        + " ,"  + dbObj.weight
        + " ,"  + dbObj.height
        + " ,"  + dbObj.bmi
        + " ,"  + dbObj.waist
        + " ,"  + dbObj.hx_ht
        + " ,"  + dbObj.hx_dm
        + " ,"  + dbObj.hx_hdd
        + " ,"  + dbObj.family_id
        + " ,"  + dbObj.strain
        + " ,"  + dbObj.mood
        + " ,"  + dbObj.sexBehavior
        + " ,"  + dbObj.sexProtect
        + " ,"  + dbObj.fat
        + " ,"  + dbObj.salt
        + " ,"  + dbObj.sweet
        + " ,"  + dbObj.egg
        + " ,"  + dbObj.rare
        + " ,"  + dbObj.ciga
        + " ,"  + dbObj.alcohol
        + " ,"  + dbObj.drug
        + " ,"  + dbObj.accident
        + " ,"  + dbObj.ht_result
        + " ,"  + dbObj.dm_result
        + " ,"  + dbObj.cancerv_result
        + " ,"  + dbObj.cabrea_result
        + " ,"  + dbObj.is_chronic
        + " ,"  + dbObj.chronic
        + " ,"  + dbObj.drug_other
        + " ,"  + dbObj.record_time
        + " ,"  + dbObj.modify_time
        + " ,"  + dbObj.cancel_time
        + " ,"  + dbObj.staff_record
        + " ,"  + dbObj.staff_modify
        + " ,"  + dbObj.staff_cancel
        + " ,"  + dbObj.active
        + " ) values ('"
        + p.getObjectId()
        //+ "','" + p.behavior_id
        + "','" + p.idy
        + "','" + p.date_serv
        + "','" + p.weight
        + "','" + p.height
        + "','" + p.bmi
        + "','" + p.waist
        + "','" + p.hx_ht
        + "','" + p.hx_dm
        + "','" + p.hx_hdd
        + "','" + p.family_id
        + "','" + p.strain
        + "','" + p.mood
        + "','" + p.sexBehavior
        + "','" + p.sexProtect
        + "','" + p.fat
        + "','" + p.salt
        + "','" + p.sweet
        + "','" + p.egg
        + "','" + p.rare
        + "','" + p.ciga
        + "','" + p.alcohol
        + "','" + p.drug
        + "','" + p.accident
        + "','" + p.ht_result
        + "','" + p.dm_result
        + "','" + p.cancerv_result
        + "','" + p.cabrea_result
        + "','" + p.is_chronic
        + "','" + p.chronic
        + "','" + p.drug_other
        + "','" + p.record_time
        + "','" + p.modify_time
        + "','" + p.cancel_time
        + "','" + p.staff_record
        + "','" + p.staff_modify
        + "','" + p.staff_cancel
        + "','" + p.active
          + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
     }
     
    public int update(Behavior o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Behavior p=o;
        String field =""
        //+ "', " + dbObj.behavior_id + "='" + p.behavior_id
        + "', " + dbObj.idy + "='" + p.idy
        + "', " + dbObj.date_serv + "='" + p.date_serv
        + "', " + dbObj.weight + "='" + p.weight
        + "', " + dbObj.height + "='" + p.height
        + "', " + dbObj.bmi + "='" + p.bmi
        + "', " + dbObj.waist + "='" + p.waist
        + "', " + dbObj.hx_ht + "='" + p.hx_ht
        + "', " + dbObj.hx_dm + "='" + p.hx_dm
        + "', " + dbObj.hx_hdd + "='" + p.hx_hdd
        + "', " + dbObj.family_id + "='" + p.family_id
        + "', " + dbObj.strain + "='" + p.strain
        + "', " + dbObj.mood + "='" + p.mood
        + "', " + dbObj.sexBehavior + "='" + p.sexBehavior
        + "', " + dbObj.sexProtect + "='" + p.sexProtect
        + "', " + dbObj.fat + "='" + p.fat
        + "', " + dbObj.salt + "='" + p.salt
        + "', " + dbObj.sweet + "='" + p.sweet
        + "', " + dbObj.egg + "='" + p.egg
        + "', " + dbObj.rare + "='" + p.rare
        + "', " + dbObj.ciga + "='" + p.ciga
        + "', " + dbObj.alcohol + "='" + p.alcohol
        + "', " + dbObj.drug + "='" + p.drug
        + "', " + dbObj.accident + "='" + p.accident
        + "', " + dbObj.ht_result + "='" + p.ht_result
        + "', " + dbObj.dm_result + "='" + p.dm_result
        + "', " + dbObj.cancerv_result + "='" + p.cancerv_result
        + "', " + dbObj.cabrea_result + "='" + p.cabrea_result
        + "', " + dbObj.is_chronic + "='" + p.is_chronic
        + "', " + dbObj.chronic + "='" + p.chronic
        + "', " + dbObj.drug_other + "='" + p.drug_other
        + "', " + dbObj.record_time + "='" + p.record_time
        + "', " + dbObj.modify_time + "='" + p.modify_time
        + "', " + dbObj.cancel_time + "='" + p.cancel_time
        + "', " + dbObj.staff_record + "='" + p.staff_record
        + "', " + dbObj.staff_modify + "='" + p.staff_modify
        + "', " + dbObj.staff_cancel + "='" + p.staff_cancel
        + "', " + dbObj.active + "='" + p.active
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        Behavior p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Behavior();
            p.setObjectId(rs.getString(dbObj.pk_field));
            //p.behavior_id = rs.getString(dbObj.behavior_id);
            p.idy = rs.getString(dbObj.idy);
            p.date_serv = rs.getString(dbObj.date_serv);
            p.weight = rs.getString(dbObj.weight);
            p.height = rs.getString(dbObj.height);
            p.bmi = rs.getString(dbObj.bmi);
            p.waist = rs.getString(dbObj.waist);
            p.hx_ht = rs.getString(dbObj.hx_ht);
            p.hx_dm = rs.getString(dbObj.hx_dm);
            p.hx_hdd = rs.getString(dbObj.hx_hdd);
            p.family_id = rs.getString(dbObj.family_id);
            p.strain = rs.getString(dbObj.strain);
            p.mood = rs.getString(dbObj.mood);
            p.sexBehavior = rs.getString(dbObj.sexBehavior);
            p.sexProtect = rs.getString(dbObj.sexProtect);
            p.fat = rs.getString(dbObj.fat);
            p.salt = rs.getString(dbObj.salt);
            p.sweet = rs.getString(dbObj.sweet);
            p.egg = rs.getString(dbObj.egg);
            p.rare = rs.getString(dbObj.rare);
            p.ciga = rs.getString(dbObj.ciga);
            p.alcohol = rs.getString(dbObj.alcohol);
            p.drug = rs.getString(dbObj.drug);
            p.accident = rs.getString(dbObj.accident);
            p.ht_result = rs.getString(dbObj.ht_result);
            p.dm_result = rs.getString(dbObj.dm_result);
            p.cancerv_result = rs.getString(dbObj.cancerv_result);
            p.cabrea_result = rs.getString(dbObj.cabrea_result);
            p.is_chronic = rs.getString(dbObj.is_chronic);
            p.chronic = rs.getString(dbObj.chronic);
            p.drug_other = rs.getString(dbObj.drug_other);
            p.record_time = rs.getString(dbObj.record_time);
            p.modify_time = rs.getString(dbObj.modify_time);
            p.cancel_time = rs.getString(dbObj.cancel_time);
            p.staff_record = rs.getString(dbObj.staff_record);
            p.staff_modify = rs.getString(dbObj.staff_modify);
            p.staff_cancel = rs.getString(dbObj.staff_cancel);
            p.active = rs.getString(dbObj.active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public int delete(Behavior o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public XPersistent getInstant() throws Exception {
        Behavior b = new Behavior();
        return b;
    }
    public Vector selectByFamily(String family_id) throws Exception{
        String sql="select * from " + dbObj.table
            + " where " + dbObj.family_id + " like '" + family_id + "'";
            //+ " order by " + dbObj.getObjectId() + " desc " ;
        Constant.println(sql);
	return eQuery(sql);
    }  
    /*public boolean initConfig()
    {
        dbObj.table = "t_health_elder_agr";
        
        dbObj.pk_field = "t_health_elder_agr_id";
     
        return true;
    }
     
     public int insert(Behavior o) throws Exception
    {
        String sql="";
        Behavior p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.hn
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.hn                
          + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
     }
     
    public int update(Behavior o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Behavior p=o;
        String field =""
        + "', " + dbObj.hn + "='" + p.hn
         + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        Behavior p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Behavior();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.hn = rs.getString(dbObj.hn);
            list.add(p);
        }
        rs.close();
        return list;
    }
     
     public int delete(Behavior o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }*/
}
