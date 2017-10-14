/*
 * FamilyPlaningSupplyDrugDoseDB.java
 *
 * Created on 30 ÁÔ¶Ø¹ÒÂ¹ 2548, 17:10 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
import com.hospital_os.utility.*;
/**
 *
 * @author tong
 */
public class FamilyPlaningSupplyDrugDoseDB {
    public ConnectionInf theConnectionInf;
    public FamilyPlaningSupplyDrugDose dbObj,p;
    final public String idtable = "738";
    String sql = "";
    ResultSet rs;
    Vector vc;
    /** Creates a new instance of FamilyPlaningSupplyDrugDoseDB */
    public FamilyPlaningSupplyDrugDoseDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new FamilyPlaningSupplyDrugDose();
        initConfig();
    }
    public boolean initConfig()
    {
     
        dbObj.table="b_health_family_planing_item_drug_setup";
        dbObj.pk_field = "b_health_family_planing_item_drug_setup_id";
        dbObj.b_health_family_planing_item_id = "b_health_family_planing_item_id";
        
        dbObj.b_health_family_planing_item_drug_setup_description 	 = "b_health_family_planing_item_drug_setup_description";
        dbObj.health_family_planing_item_drug_setup_use_uom  = "health_family_planing_item_drug_setup_use_uom";	
        dbObj.health_family_planning_item_drug_setup_purch_uom  = "health_family_planning_item_drug_setup_purch_uom";	
        dbObj.health_family_planning_item_drug_setup_caution  = "health_family_planning_item_drug_setup_caution";	
        dbObj.f_item_day_time_id 	 = "f_item_day_time_id";
        dbObj.health_family_planing_item_drug_setup_printable  = "health_family_planing_item_drug_setup_printable";	
        dbObj.b_item_drug_instruction_id 	 = "b_item_drug_instruction_id";
        dbObj.b_item_drug_frequency_id 	 = "b_item_drug_frequency_id";
        dbObj.health_family_planing_item_drug_setup_special_prescription  = "health_family_planing_item_drug_setup_special_prescription";	
        dbObj.health_family_planning_item_drug_setup_usage_text 	 = "health_family_planning_item_drug_setup_usage_text";
        dbObj.b_item_id 	 = "b_item_id";
        dbObj.health_family_planing_item_drug_setup_dose = "health_family_planing_item_drug_setup_dose";	
        dbObj.health_family_planing_item_drug_setup_qty = "health_family_planing_item_drug_setup_qty";
   
        /*
        dbObj.table = "epi_set_group";
        dbObj.pk_field = "key_id";
        dbObj.description = "description";
         */
        
        return true;
    }
    
    
    public int insert(FamilyPlaningSupplyDrugDose o) throws Exception
    {
        sql="";
     
        o.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.b_health_family_planing_item_id
        + " ,"	+ dbObj.b_health_family_planing_item_drug_setup_description
        + " ,"	+ dbObj.health_family_planing_item_drug_setup_use_uom
        + " ,"	+ dbObj.health_family_planning_item_drug_setup_purch_uom
        + " ,"	+ dbObj.health_family_planning_item_drug_setup_caution
        + " ,"	+ dbObj.f_item_day_time_id
        + " ,"	+ dbObj.health_family_planing_item_drug_setup_printable
        + " ,"	+ dbObj.b_item_drug_instruction_id
        + " ,"	+ dbObj.b_item_drug_frequency_id
        + " ,"	+ dbObj.health_family_planing_item_drug_setup_special_prescription	
        + " ,"	+ dbObj.health_family_planning_item_drug_setup_usage_text
        + " ,"	+ dbObj.b_item_id
        + " ,"	+ dbObj.health_family_planing_item_drug_setup_dose
        + " ,"	+ dbObj.health_family_planing_item_drug_setup_qty
        
        + " ) values ('"
        + o.getObjectId()
        
        + "','" + o.b_health_family_planing_item_id
        + "','" + o.b_health_family_planing_item_drug_setup_description
        + "','" + o.health_family_planing_item_drug_setup_use_uom
        + "','" + o.health_family_planning_item_drug_setup_purch_uom
        + "','" + o.health_family_planning_item_drug_setup_caution
        + "','" + o.f_item_day_time_id
        + "','" + o.health_family_planing_item_drug_setup_printable
        + "','" + o.b_item_drug_instruction_id
        + "','" + o.b_item_drug_frequency_id
        + "','" + o.health_family_planing_item_drug_setup_special_prescription	
        + "','" + o.health_family_planning_item_drug_setup_usage_text
        + "','" + o.b_item_id
        + "','" + o.health_family_planing_item_drug_setup_dose
        + "','" + o.health_family_planing_item_drug_setup_qty
                
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int update(FamilyPlaningSupplyDrugDose o) throws Exception
    {
        sql="update " + dbObj.table + " set ";

        String field =""
        + "', " + dbObj.b_health_family_planing_item_id + "='" + o.b_health_family_planing_item_id
        + "', " + dbObj.b_health_family_planing_item_drug_setup_description + "='" + o.b_health_family_planing_item_drug_setup_description
        + "', " + dbObj.health_family_planing_item_drug_setup_use_uom + "='" + o.health_family_planing_item_drug_setup_use_uom
        + "', " + dbObj.health_family_planning_item_drug_setup_purch_uom + "='" + o.health_family_planning_item_drug_setup_purch_uom
        + "', " + dbObj.health_family_planning_item_drug_setup_caution + "='" + o.health_family_planning_item_drug_setup_caution
        + "', " + dbObj.f_item_day_time_id + "='" + o.f_item_day_time_id
        + "', " + dbObj.health_family_planing_item_drug_setup_printable + "='" + o.health_family_planing_item_drug_setup_printable
        + "', " + dbObj.b_item_drug_instruction_id + "='" + o.b_item_drug_instruction_id
        + "', " + dbObj.b_item_drug_frequency_id + "='" + o.b_item_drug_frequency_id
        + "', " + dbObj.health_family_planing_item_drug_setup_special_prescription	 + "='" + o.health_family_planing_item_drug_setup_special_prescription
        + "', " + dbObj.health_family_planning_item_drug_setup_usage_text + "='" + o.health_family_planning_item_drug_setup_usage_text
        + "', " + dbObj.b_item_id + "='" + o.b_item_id
        + "', " + dbObj.health_family_planing_item_drug_setup_dose + "='" + o.health_family_planing_item_drug_setup_dose
        + "', " + dbObj.health_family_planing_item_drug_setup_qty    + "='" + o.health_family_planing_item_drug_setup_qty     
        
        
        + "' where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int deleteByKeyFpSet(String key_drugset) throws Exception
    {
        sql="delete from " + dbObj.table
        + " where " + dbObj.b_health_family_planing_item_id + "='" + key_drugset +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    
    public int delete(FamilyPlaningSupplyDrugDose o) throws Exception
    {
        sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public FamilyPlaningSupplyDrugDose selectByPK(String pk) throws Exception
    {
        sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        
        vc=eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return (FamilyPlaningSupplyDrugDose)vc.get(0);
    }
    
    public Vector selectAll() throws Exception
    {
        sql="select * from "+ dbObj.table;
        
        vc=eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
    public FamilyPlaningSupplyDrugDose selectByKeyFPSet(String key_drugset) throws Exception
    {
        sql="select * from " + dbObj.table
        + " where " + dbObj.b_health_family_planing_item_id
        + " = '" + key_drugset + "'";
        
        
        vc=eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return (FamilyPlaningSupplyDrugDose)vc.get(0);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
      
        vc = new Vector();
        rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new FamilyPlaningSupplyDrugDose();
            
            p.setObjectId(rs.getString(dbObj.pk_field));
            
            
            p.b_health_family_planing_item_id = rs.getString(dbObj.b_health_family_planing_item_id);
            p.b_health_family_planing_item_drug_setup_description = rs.getString(dbObj.b_health_family_planing_item_drug_setup_description);
            p.health_family_planing_item_drug_setup_use_uom = rs.getString(dbObj.health_family_planing_item_drug_setup_use_uom);
            p.health_family_planning_item_drug_setup_purch_uom = rs.getString(dbObj.health_family_planning_item_drug_setup_purch_uom);
            p.health_family_planning_item_drug_setup_caution = rs.getString(dbObj.health_family_planning_item_drug_setup_caution);
            p.f_item_day_time_id = rs.getString(dbObj.f_item_day_time_id);
            p.health_family_planing_item_drug_setup_printable  = rs.getString(dbObj.health_family_planing_item_drug_setup_printable);
            p.b_item_drug_instruction_id = rs.getString(dbObj.b_item_drug_instruction_id);
            p.b_item_drug_frequency_id = rs.getString(dbObj.b_item_drug_frequency_id);
            p.health_family_planing_item_drug_setup_special_prescription = rs.getString(dbObj.health_family_planing_item_drug_setup_special_prescription);
            p.health_family_planning_item_drug_setup_usage_text = rs.getString(dbObj.health_family_planning_item_drug_setup_usage_text);
            p.b_item_id = rs.getString(dbObj.b_item_id);
            p.health_family_planing_item_drug_setup_dose = rs.getString(dbObj.health_family_planing_item_drug_setup_dose);
            p.health_family_planing_item_drug_setup_qty  = rs.getString(dbObj.health_family_planing_item_drug_setup_qty);
            
            vc.add(p);
        }
        rs.close();
        return vc;
    }
    
}
