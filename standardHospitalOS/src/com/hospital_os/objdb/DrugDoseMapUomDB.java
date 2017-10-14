/*
 * DrugDosePrintDB.java
 *
 * Created on 28 æƒ…¿“§¡ 2548, 21:11 π.
 */

package com.hospital_os.objdb;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;
import com.hospital_os.utility.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  nu_ojika
 */
public class DrugDoseMapUomDB {  
    
    /** Creates a new instance of DrugDosePrintDB */
    public ConnectionInf theConnectionInf;
    public DrugDoseMapUom dbObj;
    final private String idtable = "275";
    
    public DrugDoseMapUomDB(ConnectionInf db) {
        theConnectionInf = db; 
        dbObj = new DrugDoseMapUom();
        initConfig();
    } 
    
    public boolean initConfig()
    {
        dbObj.table = "b_item_drug_dose_map_uom";
        
        dbObj.pk_field = "b_item_drug_dose_map_uom_id";
        dbObj.b_item_drug_uom_id ="b_item_drug_uom_id";
        dbObj.b_item_drug_dose_id = "b_item_drug_dose_id";
        
        return true;
    }
    
    public int insert(String uom_id,String drugDose_id) throws Exception
    {
        String sql="";
        DrugDoseMapUom p = new DrugDoseMapUom();
        p.generateOID(idtable); 
        sql = "insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.b_item_drug_uom_id
        + " ,"  + dbObj.b_item_drug_dose_id
        + " ) values ('"
        + p.getObjectId()
        + "','" + uom_id
        + "','" + drugDose_id
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }        
    
    public int delete(String uom_id) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.b_item_drug_uom_id + "='" + uom_id +"'";
        return theConnectionInf.eUpdate(sql);
    }      
    
    public DrugDoseMapUom selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (DrugDoseMapUom)v.get(0);
    }
    
    public DrugDoseMapUom selectByDrugDoseId(String ddid) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.b_item_drug_dose_id
        + " = '" + ddid + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (DrugDoseMapUom)v.get(0);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        DrugDoseMapUom p;
        Vector vc = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new DrugDoseMapUom();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.b_item_drug_uom_id = rs.getString(dbObj.b_item_drug_uom_id);
            p.b_item_drug_dose_id = rs.getString(dbObj.b_item_drug_dose_id);
            vc.add(p);
        }
        rs.close();
        return vc;
    }
    
    public void createTableForBuild8_1()
    {
        String SQL = "CREATE TABLE b_item_drug_dose_map_uom (" +
        " b_item_drug_dose_map_uom_id character varying(255) NOT NULL," +
        " b_item_drug_uom_id character varying(255)," +
        " b_item_drug_dose_id character varying(255));";        
        
         try
         {
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }
        
        SQL = "ALTER TABLE ONLY b_item_drug_dose_map_uom  ADD CONSTRAINT b_item_drug_dose_map_uom_id PRIMARY KEY (b_item_drug_dose_map_uom_id);";
        try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }     
        
        SQL = "CREATE INDEX index_item_drug_uom ON b_item_drug_dose_map_uom USING btree (b_item_drug_uom_id);";
        try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }  
    }

    public int insertV(Vector vprint, Uom uom)  throws Exception
    {
        int ret = 0;
        for(int i=0;i<vprint.size();i++){
            DrugDosePrint ddp = (DrugDosePrint)vprint.get(i);
            ret +=insert(uom.getObjectId(),ddp.getObjectId());
        }
        return ret;
    }
}
