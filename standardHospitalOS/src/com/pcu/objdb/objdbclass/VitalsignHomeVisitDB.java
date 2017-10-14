/*
 * VitalsignHomeVisitDB.java
 *
 * Created on 9 สิงหาคม 2549, 14:52 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.pcu.object.VitalsignHomeVisit;
/**
 *
 * @author Administrator
 */
public class VitalsignHomeVisitDB {
    public ConnectionInf theConnectionInf;
    public VitalsignHomeVisit dbObj;
    final private String idtable = "787";
    /** Creates a new instance of VitalsignHomeVisitDB */
    public VitalsignHomeVisitDB() {
    }
    public VitalsignHomeVisitDB(ConnectionInf db) 
    {
        theConnectionInf=db;
        dbObj = new VitalsignHomeVisit();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table = "t_health_visit_home_vitalsign";
        dbObj.pk_field = "t_health_visit_home_vitalsign_id";       
        dbObj.height = "health_vitalsign_height";
        dbObj.weight = "health_vitalsign_weight";
        dbObj.pressure = "health_vitalsign_blood_presure";
        dbObj.temp = "health_vitalsign_temperature";
        dbObj.puls = "health_vitalsign_heart_rate";
        dbObj.res = "health_vitalsign_respiratory_rate";
        dbObj.nutrition = "f_visit_nutrition_level_id";
        dbObj.bmi = "health_vitalsign_bmi";
        dbObj.check_date = "health_vitalsign_check_date";
        dbObj.check_time = "health_vitalsign_check_time";
        dbObj.record_time = "health_vitalsign_record_time";
        dbObj.record_date = "health_vitalsign_record_date";
        dbObj.reporter = "health_vitalsign_staff_record";
        dbObj.staff_modify = "health_vitalsign_staff_modify";
        dbObj.modify_date_time = "health_vitalsign_modify_date_time";
        dbObj.cancle_datetime = "health_vitalsign_cancle_date_time";
        dbObj.staff_cancle = "health_vitalsign_staff_cancle";
        dbObj.active = "health_vitalsign_active";
        dbObj.visitHome_id = "t_health_visit_home_id";
        return true;
    }
    public int insert(VitalsignHomeVisit o) throws Exception 
    {
        String sql="";
        VitalsignHomeVisit p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
                + dbObj.pk_field
                + " ,"	+ dbObj.height
                + " ,"	+ dbObj.weight
                + " ,"	+ dbObj.pressure
                + " ,"	+ dbObj.temp
                + " ,"	+ dbObj.puls
                + " ,"	+ dbObj.res
                + " ,"	+ dbObj.nutrition
                + " ,"	+ dbObj.bmi
                + " ,"	+ dbObj.check_date
                + " ,"	+ dbObj.check_time
                + " ,"	+ dbObj.record_time
                + " ,"	+ dbObj.record_date
                + " ,"	+ dbObj.reporter
                + " ,"	+ dbObj.staff_modify
                + " ,"	+ dbObj.modify_date_time
                + " ,"	+ dbObj.cancle_datetime
                + " ,"	+ dbObj.staff_cancle
                + " ,"	+ dbObj.active
                + " ,"	+ dbObj.visitHome_id
                + " ) values ('"
                + p.getObjectId()
                + "','" + p.height
                + "','" + p.weight
                + "','" + p.pressure
                + "','" + p.temp
                + "','" + p.puls
                + "','" + p.res
                + "','" + p.nutrition
                + "','" + p.bmi
                + "','" + p.check_date
                + "','" + p.check_time
                + "','" + p.record_time
                + "','" + p.record_date
                + "','" + p.reporter
                + "','" + p.staff_modify
                + "','" + p.modify_date_time
                + "','" + p.cancle_datetime
                + "','" + p.staff_cancle
                + "','" + p.active
                + "','" + p.visitHome_id
                + "')";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        VitalsignHomeVisit p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) {
            p = new VitalsignHomeVisit();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.height = rs.getString(dbObj.height);
            p.weight = rs.getString(dbObj.weight);
            p.pressure = rs.getString(dbObj.pressure);
            p.temp = rs.getString(dbObj.temp);
            p.puls = rs.getString(dbObj.puls);
            p.res = rs.getString(dbObj.res);
            p.nutrition = rs.getString(dbObj.nutrition);
            p.bmi = rs.getString(dbObj.bmi);
            p.check_date = rs.getString(dbObj.check_date);
            p.check_time = rs.getString(dbObj.check_time);
            p.record_time = rs.getString(dbObj.record_time);
            p.record_date = rs.getString(dbObj.record_date);
            p.reporter = rs.getString(dbObj.reporter);
            p.staff_modify = rs.getString(dbObj.staff_modify);
            p.modify_date_time = rs.getString(dbObj.modify_date_time);
            p.cancle_datetime = rs.getString(dbObj.cancle_datetime);
            p.staff_cancle = rs.getString(dbObj.staff_cancle);
            p.active = rs.getString(dbObj.active);
            p.visitHome_id = rs.getString(dbObj.visitHome_id);
            list.add(p);
        }
        rs.close();
        return list;
    }
    public int update(VitalsignHomeVisit o) throws Exception 
    {
        String sql="update " + dbObj.table + " set ";
        VitalsignHomeVisit p=o;
        sql += dbObj.height + "='" + p.height
                + "', " + dbObj.weight + "='" + p.weight
                + "', " + dbObj.pressure + "='" + p.pressure
                + "', " + dbObj.temp + "='" + p.temp
                + "', " + dbObj.puls + "='" + p.puls
                + "', " + dbObj.res + "='" + p.res
                + "', " + dbObj.nutrition + "='" + p.nutrition
                + "', " + dbObj.bmi + "='" + p.bmi
                + "', " + dbObj.check_date + "='" + p.check_date
                + "', " + dbObj.check_time + "='" + p.check_time
                + "', " + dbObj.record_time + "='" + p.record_time
                + "', " + dbObj.record_date + "='" + p.record_date
                + "', " + dbObj.reporter + "='" + p.reporter
                + "', " + dbObj.staff_modify + "='" + p.staff_modify
                + "', " + dbObj.modify_date_time + "='" + p.modify_date_time
                + "', " + dbObj.cancle_datetime + "='" + p.cancle_datetime
                + "', " + dbObj.staff_cancle + "='" + p.staff_cancle
                + "', " + dbObj.active + "='" + p.active
                + "', " + dbObj.visitHome_id + "='" + p.visitHome_id
                + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(VitalsignHomeVisit o) throws Exception 
    {
        String sql="delete from " + dbObj.table
                + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
     public VitalsignHomeVisit selectByPK(String pk) throws Exception
     {
        Vector vVitalsignHomeVisit = null;
        String sql ="select * from "+ dbObj.table 
        + " where " + dbObj.pk_field + "='" + pk +"'" 
        + " and "+dbObj.active + " = '1' ";
        vVitalsignHomeVisit = eQuery(sql);
        if(vVitalsignHomeVisit.size()==0)
            return null;
        else
            return (VitalsignHomeVisit) vVitalsignHomeVisit.get(0);        
    }
     
     public Vector selectByVisitHome(String visithome_id) throws Exception
     {
         Vector result = null;
         String sql = "select * from "+dbObj.table
                 +" where "+dbObj.visitHome_id+ "= '"+visithome_id+"'"
                 +" and "+dbObj.active+" = '1' " 
                 +" order by "+dbObj.record_date+","+dbObj.record_time+" desc" ;
         result = eQuery(sql);
         return result;
     }
}
