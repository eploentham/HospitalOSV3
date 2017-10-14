/*
 * WoundDB.java
 *
 * Created on 17 ÁÔ¶Ø¹ÒÂ¹ 2549, 17:39 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */


package com.hospital_os.objdb;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;
import java.util.*;
import com.hospital_os.utility.*;
/**
 *
 * @author Administrator
 */
public class WoundDB {
    public ConnectionInf theConnectionInf;
    public Wound dbObj;
    final public String idtable = "909";
    /** Creates a new instance of WoundDB */
    public WoundDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new Wound();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table = "t_wound";
        dbObj.pk_field = "t_wound_id";
        dbObj.deep = "wound_deep";
        dbObj.describe = "describe";
        dbObj.longs = "wound_long";
        dbObj.patient_id = "t_patient_id";
        dbObj.picture = "f_wound_picture";
        dbObj.position_x = "position_x";
        dbObj.position_y = "position_y";
        dbObj.visit_id = "t_visit_id";
        dbObj.width = "wound_width";
        dbObj.wound_type = "f_wound_type";
        dbObj.wound_position = "wound_position";
        dbObj.record_date_time = "record_date_time";
        dbObj.staff_record = "staff_record";
        dbObj.modify_date_time = "modify_date_time";
        dbObj.staff_modify = "staff_modify";
        return true;
    }
    public int insert(Wound o) throws Exception
    {
        String sql="";
        Wound p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.deep
        + " ,"	+ dbObj.describe
        + " ,"	+ dbObj.longs
        + " ,"	+ dbObj.patient_id
        + " ,"	+ dbObj.picture
        + " ,"	+ dbObj.position_x
        + " ,"	+ dbObj.position_y
        + " ,"	+ dbObj.visit_id
        + " ,"	+ dbObj.width
        + " ,"	+ dbObj.wound_type  
        + " ,"	+ dbObj.wound_position
        + " ,"	+ dbObj.staff_modify
        + " ,"	+ dbObj.staff_record
        + " ,"	+ dbObj.record_date_time
        + " ,"	+ dbObj.modify_date_time
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.deep        
        + "','" + p.describe
        + "','" + p.longs        
        + "','" + p.patient_id        
        + "','" + p.picture        
        + "','" + p.position_x 
        + "','" + p.position_y 
        + "','" + p.visit_id
        + "','" + p.width
        + "','" + p.wound_type
        + "','" + p.wound_position
        + "','" + p.staff_modify
        + "','" + p.staff_record
        + "','" + p.record_date_time
        + "','" + p.modify_date_time
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(Wound o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Wound p=o;
        String field =""
        + "', " + dbObj.deep + "='" + p.deep
        + "', " + dbObj.describe + "='" + p.describe
        + "', " + dbObj.longs + "='" + p.longs
        + "', " + dbObj.modify_date_time + "='" + p.modify_date_time        
        + "', " + dbObj.patient_id + "='" + p.patient_id
        + "', " + dbObj.picture + "='" + p.picture
        + "', " + dbObj.position_x + "='" + p.position_x
        + "', " + dbObj.position_y + "='" + p.position_y
        + "', " + dbObj.record_date_time + "='" + p.record_date_time
        + "', " + dbObj.staff_modify + "='" + p.staff_modify
        + "', " + dbObj.staff_record + "='" + p.staff_record
        + "', " + dbObj.visit_id + "='" + p.visit_id
        + "', " + dbObj.width + "='" + p.width
        + "', " + dbObj.wound_position + "='" + p.wound_position
        + "', " + dbObj.wound_type + "='" + p.wound_type
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(Wound o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        Wound p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Wound();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.deep = rs.getString(dbObj.deep);
            p.describe = rs.getString(dbObj.describe);
            p.longs = rs.getString(dbObj.longs);
            p.modify_date_time = rs.getString(dbObj.modify_date_time);
            p.patient_id = rs.getString(dbObj.patient_id);
            p.picture = rs.getString(dbObj.picture);            
            p.position_x = rs.getString(dbObj.position_x);
            p.position_y = rs.getString(dbObj.position_y);
            p.record_date_time = rs.getString(dbObj.record_date_time);
            p.staff_modify = rs.getString(dbObj.staff_modify);
            p.staff_record = rs.getString(dbObj.staff_record);
            p.visit_id = rs.getString(dbObj.visit_id);
            p.width = rs.getString(dbObj.width);
            p.wound_position = rs.getString(dbObj.wound_position);
            p.wound_type = rs.getString(dbObj.wound_type);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public Vector selectByVisit(String visitid) throws Exception
    {
        String sql = "select * from "+dbObj.table
                +" where "+dbObj.visit_id+" = '"+visitid+"'";
        return eQuery(sql);
    }
}
