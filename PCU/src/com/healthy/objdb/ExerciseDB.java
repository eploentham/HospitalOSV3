/*
 * ExerciseDb.java
 *
 * Created on 5 เมษายน 2549, 14:42 น.
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
public class ExerciseDB {

    Exercise dbObj;
    public ConnectionInf theConnectionInf;
    final private String idtable = "ht004";
    /** Creates a new instance of ExerciseDb */
    public ExerciseDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new Exercise();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_exercise";
        
        dbObj.pk_field = "t_health_exercise_id";
        //dbObj.exercise_id = "t_health_exercise_id";
        dbObj.idy = "exercise_idy";
        dbObj.date_serv = "exercise_date_serv";
        dbObj.exerciseType_id = "f_health_exercise_id";
        dbObj.family_id = "t_health_family_id";
        dbObj.record_time = "exercise_record_time";
        dbObj.modify_time = "exercise_modify_time";
        dbObj.cancel_time = "exercise_cancel_time";
        dbObj.staff_record = "exercise_staff_record";
        dbObj.staff_modify = "exercise_staff_modify";
        dbObj.staff_cancel = "exercise_staff_cancel";
        dbObj.active = "exercise_active";
     
        return true;
    }
     
     public int insert(Exercise o) throws Exception
    {
        String sql="";
        Exercise p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        //+ " ,"  + dbObj.exercise_id
        + " ,"  + dbObj.idy
        + " ,"  + dbObj.date_serv
        + " ,"  + dbObj.exerciseType_id
        + " ,"  + dbObj.family_id
        + " ,"  + dbObj.record_time
        + " ,"  + dbObj.modify_time
        + " ,"  + dbObj.cancel_time
        + " ,"  + dbObj.staff_record
        + " ,"  + dbObj.staff_modify
        + " ,"  + dbObj.staff_cancel
        + " ,"  + dbObj.active
        + " ) values ('"
        + p.getObjectId()
        //+ "','" + p.exercise_id
        + "','" + p.idy
        + "','" + p.date_serv
        + "','" + p.exerciseType_id
        + "','" + p.family_id
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
     
    public int update(Exercise o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Exercise p=o;
        String field =""
        //+ "', " + dbObj.exercise_id + "='" + p.exercise_id
        + "', " + dbObj.idy + "='" + p.idy
        + "', " + dbObj.date_serv + "='" + p.date_serv
        + "', " + dbObj.exerciseType_id + "='" + p.exerciseType_id
        + "', " + dbObj.family_id + "='" + p.family_id
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
        Exercise p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Exercise();
            p.setObjectId(rs.getString(dbObj.pk_field));
            //p.exercise_id = rs.getString(dbObj.exercise_id);
            p.idy = rs.getString(dbObj.idy);
            p.date_serv = rs.getString(dbObj.date_serv);
            p.exerciseType_id = rs.getString(dbObj.exerciseType_id);
            p.family_id = rs.getString(dbObj.family_id);
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
    
    public int delete(Exercise o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectByFamily(String family_id) throws Exception{
        String sql="select * from " + dbObj.table
            + " where " + dbObj.family_id + " like '" + family_id + "'";
            //+ " order by " + dbObj.getObjectId() + " desc " ;
	return eQuery(sql);
    }
    
    public XPersistent getInstant() throws Exception {
        Exercise b = new Exercise();
        return b;
    }
}
