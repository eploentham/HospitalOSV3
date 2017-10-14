/*
 * DiabetesDB.java
 *
 * Created on 5 เมษายน 2549, 17:08 น.
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
public class DiabetesDB {
    
    Diabetes dbObj;
    public ConnectionInf theConnectionInf;
    final private String idtable = "ht002";
    
    /** Creates a new instance of DiabetesDB */
    public DiabetesDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new Diabetes();
        initConfig();
    }
    
     public boolean initConfig()
    {
        dbObj.table = "t_health_diabetes";
        
        dbObj.pk_field = "t_health_diabetes_id";
        //dbObj.diabetes_id = "t_health_diabetes_id";
        dbObj.idy = "diabetes_idy";
        dbObj.date_serv = "diabetes_date_serv";
        dbObj.verbal_result = "diabetes_verbal_scan_result";
        dbObj.bisugar = "diabetes_bisugar";
        dbObj.family_id = "t_health_family_id";
        dbObj.result = "diabetes_result_dm";
        dbObj.record_time = "diabetes_record_time";
        dbObj.modify_time = "diabetes_modify_time";
        dbObj.cancel_time = "diabetes_cancel_time";
        dbObj.staff_record = "diabetes_staff_record";
        dbObj.staff_modify = "diabetes_staff_modify";
        dbObj.staff_cancel = "diabetes_staff_cancel";
        dbObj.active = "diabetes_active";
        
        return true;
    }
     
     public int insert(Diabetes o) throws Exception
     {
        String sql="";
        Diabetes p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.idy
        + " ,"  + dbObj.date_serv
        + " ,"  + dbObj.verbal_result
        + " ,"  + dbObj.bisugar
        + " ,"  + dbObj.family_id
        + " ,"  + dbObj.result
        + " ,"  + dbObj.record_time
        + " ,"  + dbObj.modify_time
        + " ,"  + dbObj.cancel_time
        + " ,"  + dbObj.staff_record
        + " ,"  + dbObj.staff_modify
        + " ,"  + dbObj.staff_cancel
        + " ,"  + dbObj.active     
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.idy
        + "','" + p.date_serv
        + "','" + p.verbal_result
        + "','" + p.bisugar
        + "','" + p.family_id
        + "','" + p.result
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
     
     public int update(Diabetes o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Diabetes p=o;
        String field =""
         + "', " + dbObj.idy + "='" + p.idy
         + "', " + dbObj.date_serv + "='" + p.date_serv
         + "', " + dbObj.verbal_result + "='" + p.verbal_result
         + "', " + dbObj.bisugar + "='" + p.bisugar
         + "', " + dbObj.family_id + "='" + p.family_id
         + "', " + dbObj.result + "='" + p.result
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
     
     public int delete(Diabetes o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
     
     public Vector eQuery(String sql) throws Exception
    {
        Diabetes p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Diabetes();
            p.setObjectId(rs.getString(dbObj.pk_field));
            //p.behavior_id = rs.getString(dbObj.behavior_id);
             p.idy = rs.getString(dbObj.idy);
             p.date_serv = rs.getString(dbObj.date_serv);
             p.verbal_result = rs.getString(dbObj.verbal_result);
             p.bisugar = rs.getString(dbObj.bisugar);
             p.family_id = rs.getString(dbObj.family_id);
             p.result = rs.getString(dbObj.result);
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
     
    public Vector selectByFamily(String family_id) throws Exception{
        String sql="select * from " + dbObj.table
            + " where " + dbObj.family_id + " like '" + family_id + "'";
            //+ " order by " + dbObj.getObjectId() + " desc " ;
        Constant.println(sql);
	return eQuery(sql);
    }
    
    public XPersistent getInstant() throws Exception {
        Diabetes b = new Diabetes();
        return b;
    }
}
