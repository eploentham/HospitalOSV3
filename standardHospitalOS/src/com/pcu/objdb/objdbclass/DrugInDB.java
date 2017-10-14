/*
 * DrugInDB.java
 *
 * Created on 4 ÁÔ¶Ø¹ÒÂ¹ 2548, 15:45 ¹.
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
public class DrugInDB {
    
    /** Creates a new instance of DrugInDB */
    public DrugInDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public DrugIn dbObj;
    final private String idtable = "800";
    
    public DrugInDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new DrugIn();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_drug_in";        
        dbObj.pk_field = "t_health_drug_in_id";
        dbObj.drug_in_invoice_number ="drug_in_invoice_number";
        dbObj.drug_in_date_time = "drug_in_date_time";   
        dbObj.drug_in_vendor ="drug_in_vendor";
        dbObj.drug_in_note = "drug_in_note";   
        
        return true;
    }
    
    public int insert(DrugIn o) throws Exception
    {
        String sql="";
        DrugIn p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.drug_in_invoice_number
        + " ,"  + dbObj.drug_in_date_time
        + " ,"	+ dbObj.drug_in_vendor
        + " ,"	+ dbObj.drug_in_note        
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.drug_in_invoice_number
        + "','" + p.drug_in_date_time
        + "','" + p.drug_in_vendor
        + "','" + p.drug_in_note        
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(DrugIn o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        DrugIn p=o;
        String field =""
        + "', " + dbObj.drug_in_invoice_number + "='" + p.drug_in_invoice_number
        + "', " + dbObj.drug_in_date_time + "='" + p.drug_in_date_time
        + "', " + dbObj.drug_in_vendor + "='" + p.drug_in_vendor
        + "', " + dbObj.drug_in_note + "='" + p.drug_in_note        
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(DrugIn o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        DrugIn p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new DrugIn();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.drug_in_invoice_number = rs.getString(dbObj.drug_in_invoice_number);
            p.drug_in_date_time = rs.getString(dbObj.drug_in_date_time);
            p.drug_in_vendor = rs.getString(dbObj.drug_in_vendor);
            p.drug_in_note = rs.getString(dbObj.drug_in_note);            
            list.add(p);
        }
        rs.close();
        return list;
    }
}
