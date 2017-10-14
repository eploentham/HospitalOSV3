/*
 * ToBeOneDB.java
 *
 * Created on 5 เมษายน 2549, 17:23 น.
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
public class ToBeOneDB {
    
    ToBeOne dbObj;
    public ConnectionInf theConnectionInf;
    final private String idtable = "ht006";
    
    /** Creates a new instance of ToBeOneDB */
    public ToBeOneDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new ToBeOne();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_to_be";
        
        dbObj.pk_field = "t_health_to_be_id";
        //dbObj.toBe_id = "t_health_to_be_id";
        dbObj.family_id = "t_health_family_id";
        dbObj.is_member = "to_be_is_member";
        dbObj.regisDate = "to_be_regis_date";
        dbObj.workPlace = "to_be_work_place";
        //ยกเลิกใช้งาน sumo 30/08/2549
        //dbObj.eduSys = "to_be_edu_sys";
        dbObj.reason = "to_be_reason";
        dbObj.opinion = "to_be_opinion";
        dbObj.record_time = "to_be_record_time";
        dbObj.modify_time = "to_be_modify_time";
        dbObj.cancel_time = "to_be_cancel_time";
        dbObj.staff_record = "to_be_staff_record";
        dbObj.staff_modify = "to_be_staff_modify";
        dbObj.staff_cancel = "to_be_staff_cancel";
        dbObj.active = "to_be_active";
     
        return true;
    }
     
     public int insert(ToBeOne o) throws Exception
    {
        String sql="";
        ToBeOne p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        //+ " ,"  + dbObj.toBe_id
        + " ,"  + dbObj.family_id
        + " ,"  + dbObj.is_member
        + " ,"  + dbObj.regisDate
        + " ,"  + dbObj.workPlace
        //ยกเลิกใช้งาน sumo 30/08/2549
        //+ " ,"  + dbObj.eduSys
        + " ,"  + dbObj.reason
        + " ,"  + dbObj.opinion
        + " ,"  + dbObj.record_time
        + " ,"  + dbObj.modify_time
        + " ,"  + dbObj.cancel_time
        + " ,"  + dbObj.staff_record
        + " ,"  + dbObj.staff_modify
        + " ,"  + dbObj.staff_cancel
        + " ,"  + dbObj.active
        + " ) values ('"
        + p.getObjectId()
        //+ "','" + p.toBe_id
        + "','" + p.family_id
        + "','" + p.is_member
        + "','" + p.regisDate
        + "','" + p.workPlace
        //ยกเลิกใช้งาน sumo 30/08/2549
        //+ "','" + p.eduSys
        + "','" + p.reason
        + "','" + p.opinion
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
     
    public int update(ToBeOne o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        ToBeOne p=o;
        String field =""
        //+ "', " + dbObj.toBe_id + "='" + p.toBe_id
        + "', " + dbObj.family_id + "='" + p.family_id
        + "', " + dbObj.is_member + "='" + p.is_member
        + "', " + dbObj.regisDate + "='" + p.regisDate
        + "', " + dbObj.workPlace + "='" + p.workPlace
        //ยกเลิกใช้งาน sumo 30/08/2549
        //+ "', " + dbObj.eduSys + "='" + p.eduSys
        + "', " + dbObj.reason + "='" + p.reason
        + "', " + dbObj.opinion + "='" + p.opinion
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
        ToBeOne p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ToBeOne();
            p.setObjectId(rs.getString(dbObj.pk_field));
            //p.toBe_id = rs.getString(dbObj.toBe_id);
            p.family_id = rs.getString(dbObj.family_id);
            p.is_member = rs.getString(dbObj.is_member);
            p.regisDate = rs.getString(dbObj.regisDate);
            p.workPlace = rs.getString(dbObj.workPlace);
            //ยกเลิกใช้งาน sumo 30/08/2549
            //p.eduSys = rs.getString(dbObj.eduSys);
            p.reason = rs.getString(dbObj.reason);
            p.opinion = rs.getString(dbObj.opinion);
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
     
     public int delete(ToBeOne o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectByFamily(String family_id) throws Exception{
        String sql="select * from " + dbObj.table
            + " where " + dbObj.family_id + " like '" + family_id + "'";
            //+ " order by " + dbObj.getObjectId() + " desc " ;
        Constant.println(sql);
	return eQuery(sql);
    }
    
    public XPersistent getInstant() throws Exception {
        ToBeOne b = new ToBeOne();
        return b;
    }
}
