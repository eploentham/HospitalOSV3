/*
 * DoseEpiSetDB.java
 *
 * Created on 27 ÁÔ¶Ø¹ÒÂ¹ 2548, 16:30 ¹.
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
public class DoseEpiSetDB {
    
    /** Creates a new instance of DoseEpiSetDB */
    public DoseEpiSetDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public DoseEpiSet dbObj;
    final private String idtable = "769";//"139";
    public DoseEpiSetDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new DoseEpiSet();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="b_health_epi_item_drug_setup";
        dbObj.pk_field="b_health_epi_item_drug_setup_id";//"b_health_epi_set_drug_id";
        dbObj.epi_set_key_id ="b_health_epi_item_id";//"b_health_epi_set_id";
        dbObj.description ="b_health_epi_item_drug_setup_description";
        dbObj.dose="health_epi_item_drug_setup_dose";
        dbObj.use_uom="health_epi_item_drug_setup_use_uom";
        dbObj.qty="health_epi_item_drug_setup_qty";
        dbObj.purch_uom="health_epi_item_drug_setup_purch_uom";
        dbObj.caution="health_epi_item_drug_setup_caution";
        dbObj.day_time="f_item_day_time_id";
        dbObj.printting ="health_epi_item_drug_setup_printable";
        dbObj.instruction ="b_item_drug_instruction_id";
        dbObj.frequency ="b_item_drug_frequency_id";
        dbObj.usage_special ="health_epi_item_drug_setup_special_prescription";
        dbObj.usage_text ="health_epi_item_drug_setup_usage_text";
        dbObj.item_code="b_item_id";
       
        return true;
    }
    public int insert(DoseEpiSet o) throws Exception
    {
        String sql="";
        DoseEpiSet p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.epi_set_key_id
        + " ,"	+ dbObj.description
        + " ,"	+ dbObj.dose
        + " ,"	+ dbObj.use_uom
        + " ,"	+ dbObj.qty
        + " ,"	+ dbObj.purch_uom
        + " ,"	+ dbObj.caution
        + " ,"	+ dbObj.day_time
        + " ,"	+ dbObj.printting
        + " ,"	+ dbObj.instruction
        + " ,"	+ dbObj.frequency
        + " ,"	+ dbObj.usage_special
        + " ,"	+ dbObj.usage_text
        + " ,"	+ dbObj.item_code
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.epi_set_key_id
        + "','" + p.description
        + "','" + p.dose
        + "','" + p.use_uom
        + "','" + p.qty
        + "','" + p.purch_uom
        + "','" + p.caution
        + "','" + p.day_time
        + "','" + p.printting
        + "','" + p.instruction
        + "','" + p.frequency
        + "','" + p.usage_special
        + "','" + p.usage_text
        + "','" + p.item_code
        + "')";
  
        return theConnectionInf.eUpdate(sql);
    }
    public int update(DoseEpiSet o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        DoseEpiSet p=o;
        String field =""
        + "', " + dbObj.epi_set_key_id + "='" + p.epi_set_key_id
        + "', " + dbObj.description + "='" + p.description
        + "', " + dbObj.dose + "='" + p.dose
        + "', " + dbObj.use_uom + "='" + p.use_uom
        + "', " + dbObj.qty + "='" + p.qty
        + "', " + dbObj.purch_uom + "='" + p.purch_uom
        + "', " + dbObj.caution + "='" + p.caution
        + "', " + dbObj.day_time + "='" + p.day_time
        + "', " + dbObj.printting + "='" + p.printting
        + "', " + dbObj.instruction + "='" + p.instruction
        + "', " + dbObj.frequency + "='" + p.frequency
        + "', " + dbObj.usage_special + "='" + p.usage_special
        + "', " + dbObj.usage_text + "='" + p.usage_text
        + "', " + dbObj.item_code + "='" + p.item_code
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2); 
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public int delete(DoseEpiSet o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public DoseEpiSet selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (DoseEpiSet)v.get(0);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll() throws Exception
    {
        String sql="select * from "+ dbObj.table;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    //////////////////////////////////////////////////////////////////////////////
    public DoseEpiSet selectByKeyEpiSet(String key_drugset) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.epi_set_key_id
        + " = '" + key_drugset + "'";
        
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (DoseEpiSet)v.get(0);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector eQuery(String sql) throws Exception
    {
        DoseEpiSet p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new DoseEpiSet();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.epi_set_key_id = rs.getString(dbObj.epi_set_key_id);
            p.description = rs.getString(dbObj.description);
            p.dose = rs.getString(dbObj.dose);
            p.use_uom = rs.getString(dbObj.use_uom);
            p.qty = rs.getString(dbObj.qty);
            p.purch_uom = rs.getString(dbObj.purch_uom);
            p.caution = rs.getString(dbObj.caution);
            p.day_time = rs.getString(dbObj.day_time);
            p.printting = rs.getString(dbObj.printting);
            p.instruction = rs.getString(dbObj.instruction);
            p.frequency = rs.getString(dbObj.frequency);
            p.usage_special = rs.getString(dbObj.usage_special);
            p.usage_text = rs.getString(dbObj.usage_text);
            p.item_code = rs.getString(dbObj.item_code);
            list.add(p);
        }
        rs.close();
        return list;
    }
    //////////////////////////////////////////////////////////////////////////////
}
