/*
 * DrugDosePrintDB.java
 *
 * Created on 28 พฤษภาคม 2548, 21:11 น.
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
public class DrugDosePrintDB {  
    
    /** Creates a new instance of DrugDosePrintDB */
    public ConnectionInf theConnectionInf;
    public DrugDosePrint dbObj;
    final private String idtable = "274";
    
    public DrugDosePrintDB(ConnectionInf db) {
        theConnectionInf = db; 
        dbObj = new DrugDosePrint();
        initConfig();
    } 
    
    public boolean initConfig()
    {
        dbObj.table = "b_item_drug_dose";
        
        dbObj.pk_field = "b_item_drug_dose_id";
        dbObj.item_drug_dose_value ="item_drug_dose_value";
        dbObj.item_drug_dose_description = "item_drug_dose_description";
        dbObj.item_drug_dose_active = "item_drug_dose_active";
        
        return true;
    }
    
    public int insert(DrugDosePrint o) throws Exception
    {
        String sql="";
        DrugDosePrint p=o;
        p.generateOID(idtable);
        sql = "insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.item_drug_dose_value
        + " ,"  + dbObj.item_drug_dose_description
        + " ,"	+ dbObj.item_drug_dose_active
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.item_drug_dose_value
        + "','" + p.item_drug_dose_description
        + "','" + p.item_drug_dose_active
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(DrugDosePrint o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        DrugDosePrint p=o;
        String field =""
        + "', " + dbObj.item_drug_dose_value + "='" + p.item_drug_dose_value
        + "', " + dbObj.item_drug_dose_description + "='" + p.item_drug_dose_description
        + "', " + dbObj.item_drug_dose_active + "='" + p.item_drug_dose_active
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(DrugDosePrint o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    
    public DrugDosePrint selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (DrugDosePrint)v.get(0);
    }
    
    public Vector selectByAll() throws Exception
    {
        String sql="select * from " + dbObj.table
        ;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public Vector selectByKeyWord(String key,String active) throws Exception
    {
        String sql="select * from " + dbObj.table;
        
        if(key.trim().equalsIgnoreCase(""))
        {
            sql = sql + " where "
                  + dbObj.item_drug_dose_active
                  + " = '" + active + "' ";           
        }
        else
        {        
            sql = sql + " where (" + dbObj.item_drug_dose_value
            + " like '%" + key + "%' "
            + " or " + dbObj.item_drug_dose_description
            + " like '%" + key + "%') "
            + " and " + dbObj.item_drug_dose_active
            + " = '" + active + "' ";
        }
       //Constant.println(sql);
        Vector vc=eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        DrugDosePrint p;
        Vector vc = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new DrugDosePrint();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.item_drug_dose_value = rs.getString(dbObj.item_drug_dose_value);
            p.item_drug_dose_description = rs.getString(dbObj.item_drug_dose_description);
            p.item_drug_dose_active = rs.getString(dbObj.item_drug_dose_active);
            vc.add(p);
        }
        rs.close();
        return vc;
    }
    
    public void createTableForBuild8_1()
    {
        String SQL = "CREATE TABLE b_item_drug_dose (" +
        " b_item_drug_dose_id character varying(255) NOT NULL," +
        " item_drug_dose_value character varying(255)," +
        " item_drug_dose_description character varying(255)," +
        " item_drug_dose_active character varying(255));";        
        
         try
         {
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }
        
        SQL = "ALTER TABLE ONLY b_item_drug_dose  ADD CONSTRAINT b_item_drug_dose_id PRIMARY KEY (b_item_drug_dose_id);";
        try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }   
        
        SQL = "CREATE INDEX index_item_drug_dose_value ON b_item_drug_dose USING btree (item_drug_dose_value);";
        try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }   
    }
    
    public void insertDataToTableForBuild8_1()
    {
        String SQL = " INSERT INTO b_item_drug_dose " +
        "  ( b_item_drug_dose_id, " +
        "    item_drug_dose_value, " +
        "    item_drug_dose_description,  " +
        "    item_drug_dose_active " +
        "   )" +
        " values " +
        " ('2740000000001','0.5','ครึ่ง','1')" ;
        
        try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }
        
        SQL = " INSERT INTO b_item_drug_dose " +
        "  ( b_item_drug_dose_id, " +
        "    item_drug_dose_value, " +
        "    item_drug_dose_description,  " +
        "    item_drug_dose_active " +
        "   )" +
        " values " +
        " ('2740000000002','0.75','สามส่วนสี่','1')" ;
        try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }
        
        SQL = " INSERT INTO b_item_drug_dose " +
        "  ( b_item_drug_dose_id, " +
        "    item_drug_dose_value, " +
        "    item_drug_dose_description,  " +
        "    item_drug_dose_active " +
        "   )" +
        " values " +
        " ('2740000000003','0.25','เสี้ยว','1')" ;
        try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }
        
        SQL = " INSERT INTO b_item_drug_dose " +
        "  ( b_item_drug_dose_id, " +
        "    item_drug_dose_value, " +
        "    item_drug_dose_description,  " +
        "    item_drug_dose_active " +
        "   )" +
        " values " +
        " ('2740000000004','0.5','หนึ่งส่วนสอง','1')" ;
        try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }
    }

    public int insertV(Vector vprint) throws Exception
    {
        int ret = 0;
        for(int i=0;i<vprint.size();i++){
            DrugDosePrint ddp = (DrugDosePrint)vprint.get(i);
            ret +=insert(ddp);
        }
        return ret;
    }

}
