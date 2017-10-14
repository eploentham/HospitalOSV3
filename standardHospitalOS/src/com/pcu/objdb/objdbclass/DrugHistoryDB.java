/*
 * DrugHistoryDB.java
 *
 * Created on 13 กรกฎาคม 2548, 15:12 น.
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
public class DrugHistoryDB {
    
    /** Creates a new instance of DrugHistoryDB */
    public DrugHistoryDB() {
    }
    
     
     public ConnectionInf theConnectionInf;
    
    public DrugHistory dbObj;
    final private String idtable = "754";
    
    public DrugHistoryDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new DrugHistory();
        initConfig(); 
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_drug_history";        
        dbObj.pk_field = "t_health_drug_history_id";        
        dbObj.drug_id="t_health_drug_id";
        dbObj.tranfer="health_drug_history_tranfer";
        dbObj.quantity="health_drug_history_quantity";
        dbObj.note="health_drug_history_note";
        dbObj.staff_record="health_drug_history_staff_record";
        dbObj.record_date_time="record_date_time";        
        return true;
    }
    
    public int insert(DrugHistory o) throws Exception
    {
        String sql="";
        DrugHistory p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field        
        + " ,"  + dbObj.drug_id
        + " ,"  + dbObj.tranfer
        + " ,"  + dbObj.quantity
        + " ,"  + dbObj.note
        + " ,"  + dbObj.staff_record
        + " ,"  + dbObj.record_date_time        
        + " ) values ('"
        + p.getObjectId()        
        + "','" + p.drug_id
        + "','" + p.tranfer
        + "','" + p.quantity
        + "','" + p.note
        + "','" + p.staff_record
        + "','" + p.record_date_time
        + "')";        
        return theConnectionInf.eUpdate(sql);
    }
    public int update(DrugHistory o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        DrugHistory p=o;
        String field =""        
        + "', " + dbObj.drug_id + "='" + p.drug_id
        + "', " + dbObj.tranfer + "='" + p.tranfer
        + "', " + dbObj.quantity + "='" + p.quantity
        + "', " + dbObj.note + "='" + p.note
        + "', " + dbObj.staff_record + "='" + p.staff_record
        + "', " + dbObj.record_date_time + "='" + p.record_date_time
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectByPk(String search) throws Exception
    {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.drug_id+ " = '"+ search +"'" +
                " Order by "+dbObj.record_date_time;
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public int delete(DrugHistory o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
   
    public Vector eQuery(String sql) throws Exception
    {
        DrugHistory p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new DrugHistory();
            p.setObjectId(rs.getString(dbObj.pk_field));            
            p.drug_id= rs.getString(dbObj.drug_id);
            p.tranfer= rs.getString(dbObj.tranfer);
            p.quantity= rs.getString(dbObj.quantity);
            p.note= rs.getString(dbObj.note);
            p.staff_record= rs.getString(dbObj.staff_record);
            p.record_date_time= rs.getString(dbObj.record_date_time);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
