/*
 * ElderAgrDB.java
 *
 * Created on 5 เมษายน 2549, 17:15 น.
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
public class ElderAgrDB {
    
    ElderAgr dbObj;
    public ConnectionInf theConnectionInf;
    final private String idtable = "ht003";
    
    /** Creates a new instance of ElderAgrDB */
    public ElderAgrDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new ElderAgr();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_elder_agr";
        
        dbObj.pk_field = "t_health_elder_agr_id";
        //dbObj.elderAgr_id = "t_health_elder_agr_id";
        dbObj.idy = "elder_agr_idy";
        dbObj.date_serv = "elder_agr_date_serv";
        dbObj.isJoin = "elder_agr_is_join";
        dbObj.agr_id = "t_health_agr_id";
        dbObj.family_id = "t_health_family_id";
        dbObj.record_time = "elder_agr_record_time";
        dbObj.modify_time = "elder_agr_modify_time";
        dbObj.cancel_time = "elder_agr_cancel_time";
        dbObj.staff_record = "elder_agr_staff_record";
        dbObj.staff_modify = "elder_agr_staff_modify";
        dbObj.staff_cancel = "elder_agr_staff_cancel";
        dbObj.active = "elder_agr_active";
        
        return true;
    }
    public int insert(ElderAgr o) throws Exception
    {
        String sql="";
        ElderAgr p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.idy
        + " ,"  + dbObj.date_serv
        + " ,"  + dbObj.isJoin
        + " ,"  + dbObj.agr_id
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
        + "','" + p.idy
        + "','" + p.date_serv
        + "','" + p.isJoin
        + "','" + p.agr_id
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
     
    public int update(ElderAgr o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        ElderAgr p=o;
        String field =""
        //+ "', " + dbObj.elderAgr_id + "='" + p.elderAgr_id
        + "', " + dbObj.idy + "='" + p.idy
        + "', " + dbObj.date_serv + "='" + p.date_serv
        + "', " + dbObj.isJoin + "='" + p.isJoin
        + "', " + dbObj.agr_id + "='" + p.agr_id
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
        ElderAgr p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ElderAgr();
            p.setObjectId(rs.getString(dbObj.pk_field));
            //p.elderAgr_id = rs.getString(dbObj.elderAgr_id);
            p.idy = rs.getString(dbObj.idy);
            p.date_serv = rs.getString(dbObj.date_serv);
            p.isJoin = rs.getString(dbObj.isJoin);
            p.agr_id = rs.getString(dbObj.agr_id);
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
    
    public int delete(ElderAgr o) throws Exception
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
        ElderAgr b = new ElderAgr();
        return b;
    }
    
}
