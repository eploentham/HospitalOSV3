/*
 * DrugStockDB.java
 *
 * Created on 13 กรกฎาคม 2548, 14:54 น.
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
public class DrugStockDB {
    
    /** Creates a new instance of DrugStockDB */
    public DrugStockDB() {
    }
    
    
     public ConnectionInf theConnectionInf;
    
    public DrugStock dbObj;
    final private String idtable = "753";
    
    public DrugStockDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new DrugStock();
        initConfig(); 
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_drug_stock";        
        dbObj.pk_field = "t_health_drug_stock_id";        
        dbObj.office_id="b_visit_office_id";
        dbObj.item_id="b_item_id";
        dbObj.item_common_name="item_common_name";
        dbObj.amount="health_drug_amount";
        dbObj.staff_record="health_drug_staff_record";
        dbObj.staff_modify="health_drug_staff_modify";
        dbObj.staff_cancel="health_drug_staff_cancel";
        dbObj.record_date_time="record_date_time";
        dbObj.modify_date_time="modify_date_time";
        dbObj.cancel_date_time="cancel_date_time";
        dbObj.active="health_drug_active";
        return true;
    }
    
    public int insert(DrugStock o) throws Exception
    {
        String sql="";
        DrugStock p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field        
        + " ,"  + dbObj.office_id
        + " ,"  + dbObj.item_id
        + " ,"  + dbObj.item_common_name
        + " ,"  + dbObj.amount
        + " ,"  + dbObj.staff_record
        + " ,"  + dbObj.staff_modify
        + " ,"  + dbObj.staff_cancel
        + " ,"  + dbObj.record_date_time
        + " ,"  + dbObj.modify_date_time
        + " ,"  + dbObj.cancel_date_time
        + " ,"  + dbObj.active        
        + " ) values ('"
        + p.getObjectId()        
        + "','" + p.office_id
        + "','" + p.item_id
        + "','" + p.item_common_name
        + "','" + p.amount
        + "','" + p.staff_record
        + "','" + p.staff_modify
        + "','" + p.staff_cancel
        + "','" + p.record_date_time
        + "','" + p.modify_date_time
        + "','" + p.cancel_date_time
        + "','" + p.active   
        + "')";        
        return theConnectionInf.eUpdate(sql);
    }
    public int update(DrugStock o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        DrugStock p=o;
        String field =""        
        + "', " + dbObj.office_id + "='" + p.office_id
        + "', " + dbObj.item_id + "='" + p.item_id
        + "', " + dbObj.item_common_name + "='" + p.item_common_name
        + "', " + dbObj.amount + "='" + p.amount
        + "', " + dbObj.staff_record + "='" + p.staff_record
        + "', " + dbObj.staff_modify + "='" + p.staff_modify
        + "', " + dbObj.staff_cancel + "='" + p.staff_cancel
        + "', " + dbObj.record_date_time + "='" + p.record_date_time
        + "', " + dbObj.modify_date_time + "='" + p.modify_date_time
        + "', " + dbObj.cancel_date_time + "='" + p.cancel_date_time
        + "', " + dbObj.active + "='" + p.active
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
     public Vector selectAll() throws Exception
    {
        String sql="select * from " + dbObj.table + " where " + dbObj.active+ " = '1' Order by "+dbObj.record_date_time;
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
     
    public Vector selectByName(String search) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where UPPER(" + dbObj.item_common_name + ") like UPPER('%" + search.toUpperCase() + "%') or " + dbObj.amount
        + " like  '%" + search + "%' Order by "+dbObj.record_date_time;
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    } 
    
    public int delete(DrugStock o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
   
    public Vector eQuery(String sql) throws Exception
    {
        DrugStock p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new DrugStock();
            p.setObjectId(rs.getString(dbObj.pk_field));            
            p.office_id= rs.getString(dbObj.office_id);
            p.item_id= rs.getString(dbObj.item_id);
            p.item_common_name= rs.getString(dbObj.item_common_name);
            p.amount= rs.getString(dbObj.amount);
            p.staff_record= rs.getString(dbObj.staff_record);
            p.staff_modify= rs.getString(dbObj.staff_modify);
            p.staff_cancel= rs.getString(dbObj.staff_cancel);
            p.record_date_time= rs.getString(dbObj.record_date_time);
            p.modify_date_time= rs.getString(dbObj.modify_date_time);
            p.cancel_date_time= rs.getString(dbObj.cancel_date_time);
            p.active= rs.getString(dbObj.active);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
