/*
 * AccidentGroupDB.java
 *
 * Created on 25 พฤษภาคม 2549, 18:48 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 * ตอนนี้ยังไม่ได้ใช้งาน 
 * @author Padungrat(tong)
 * @date 31/05/49
 */
public class AccidentGroupDB {
    
    public ConnectionInf theConnectionInf;
    public Accident dbObj,TempAccidentGroup;
    final private String idtable = "312";
    Vector list;
    String sql="";
    ResultSet rs;
    /** Creates a new instance of AccidentGroupDB */
    public AccidentGroupDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Accident();
        initConfig();
    
    }
    public boolean initConfig()
    {
        dbObj.table = "t_accident_group";
        dbObj.pk_field = "t_accident_group_id";
        dbObj.acctime = "accident_group_time";
        dbObj.date_accident = "accident_group_date";
        dbObj.acc_rd = "f_accident_road_type_id";
        dbObj.name_rd = "accident_group_road_name";
        dbObj.in_out = "f_accident_highway_inout_type_id";
        dbObj.kilo = "accident_group_kilo";
        dbObj.accmu = "accident_group_moo";
        dbObj.acctb = "f_address_id_accident_tambon";
        dbObj.accam = "f_address_id_accident_amphur";
        dbObj.acccw = "f_address_id_accident_changwat";
        dbObj.ptmobie = "f_accident_patient_vechicle_type_id";
        dbObj.reporter = "accident_group_staff_record";
        dbObj.record_date_time = "accident_group_record_date_time";
        dbObj.staff_update = "accident_group_staff_update";
        dbObj.update_date_time = "accident_group_update_date_time";
        dbObj.staff_cancel = "accident_group_staff_cancel";
        dbObj.cancel_date_time = "accident_group_cancel_date_time";
        dbObj.active = "accident_group_active";
        
        return true;
    }
    
    public int insert(Accident o)throws Exception
    {
        sql="";
        TempAccidentGroup =o;
        TempAccidentGroup.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.acctime
        + " ,"  + dbObj.date_accident
        + " ,"  + dbObj.name_rd
        + " ,"  + dbObj.in_out
        + " ,"  + dbObj.kilo
        + " ,"  + dbObj.accmu
        + " ,"  + dbObj.acctb
        + " ,"  + dbObj.accam
        + " ,"  + dbObj.acccw
        + " ,"  + dbObj.ptmobie
        + " ,"  + dbObj.acc_rd
        + " ,"  + dbObj.reporter
        
        + " ,"  + dbObj.record_date_time
        + " ,"  + dbObj.staff_update
        + " ,"  + dbObj.update_date_time
        + " ,"  + dbObj.staff_cancel
        + " ,"  + dbObj.cancel_date_time
        + " ,"  + dbObj.active
        
        + " ) values ('"
        + TempAccidentGroup.getObjectId()
        + "','" + TempAccidentGroup.acctime
        + "','" + TempAccidentGroup.date_accident
        + "','" + TempAccidentGroup.name_rd
        + "','" + TempAccidentGroup.in_out
        + "','" + TempAccidentGroup.kilo
        + "','" + TempAccidentGroup.accmu
        + "','" + TempAccidentGroup.acctb
        + "','" + TempAccidentGroup.accam
        + "','" + TempAccidentGroup.acccw
        + "','" + TempAccidentGroup.ptmobie
        + "','" + TempAccidentGroup.acc_rd
        + "','" + TempAccidentGroup.reporter
        + "','" + TempAccidentGroup.record_date_time
        + "','" + TempAccidentGroup.staff_update
        + "','" + TempAccidentGroup.update_date_time
        + "','" + TempAccidentGroup.staff_cancel
        + "','" + TempAccidentGroup.cancel_date_time
        + "','" + TempAccidentGroup.active
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    
    public int update(Accident o) throws Exception
    {
        sql="update " + dbObj.table + " set ";
        TempAccidentGroup=o;
        String field =""
        + "', " + dbObj.acctime + "='" + TempAccidentGroup.acctime
        + "', " + dbObj.date_accident + "='" + TempAccidentGroup.date_accident
        + "', " + dbObj.name_rd + "='" + TempAccidentGroup.name_rd
        + "', " + dbObj.in_out + "='" + TempAccidentGroup.in_out
        + "', " + dbObj.kilo + "='" + TempAccidentGroup.kilo
        + "', " + dbObj.accmu + "='" + TempAccidentGroup.accmu
        + "', " + dbObj.acctb + "='" + TempAccidentGroup.acctb
        + "', " + dbObj.accam + "='" + TempAccidentGroup.accam
        + "', " + dbObj.acccw + "='" + TempAccidentGroup.acccw
        + "', " + dbObj.ptmobie + "='" + TempAccidentGroup.ptmobie
        + "', " + dbObj.acc_rd + "='" + TempAccidentGroup.acc_rd
        + "', " + dbObj.reporter + "='" + TempAccidentGroup.reporter
        + "', " + dbObj.record_date_time + "='" + TempAccidentGroup.record_date_time
        + "', " + dbObj.staff_update + "='" + TempAccidentGroup.staff_update
        + "', " + dbObj.update_date_time + "='" + TempAccidentGroup.update_date_time
        + "', " + dbObj.staff_cancel + "='" + TempAccidentGroup.staff_cancel
        + "', " + dbObj.cancel_date_time + "='" + TempAccidentGroup.cancel_date_time
        + "', " + dbObj.active + "='" + TempAccidentGroup.active
          
        + "' where " + dbObj.pk_field + "='" + TempAccidentGroup.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        
        return theConnectionInf.eUpdate(sql);
    }
    
    
    public Vector selectByAccidentDate(String startdate,String enddate) throws Exception
    {
        sql = "SELECT * FROM " + dbObj.table +
              " WHERE " + dbObj.date_accident + " BETWEEN " +
              " '" +startdate + "' AND '" + enddate + "';";
        list = eQuery(sql);
        if(list.size() == 0)
        {
            list = null;
        }
        return list;
    }
    
    public Vector eQuery(String sql) throws Exception
    {
      
        list = new Vector();
        rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            TempAccidentGroup = new Accident();
            TempAccidentGroup.setObjectId(rs.getString(dbObj.pk_field));
            TempAccidentGroup.acctime = rs.getString(dbObj.acctime);
            TempAccidentGroup.date_accident = rs.getString(dbObj.date_accident);
            TempAccidentGroup.name_rd = rs.getString(dbObj.name_rd);
            TempAccidentGroup.in_out = rs.getString(dbObj.in_out);
            TempAccidentGroup.kilo = rs.getString(dbObj.kilo);
            TempAccidentGroup.accmu = rs.getString(dbObj.accmu);
            TempAccidentGroup.acctb = rs.getString(dbObj.acctb);
            TempAccidentGroup.accam = rs.getString(dbObj.accam);
            TempAccidentGroup.acccw = rs.getString(dbObj.acccw);
            TempAccidentGroup.ptmobie = rs.getString(dbObj.ptmobie);
            TempAccidentGroup.acc_rd = rs.getString(dbObj.acc_rd);
            TempAccidentGroup.reporter = rs.getString(dbObj.reporter);
            TempAccidentGroup.record_date_time = rs.getString(dbObj.record_date_time);
            TempAccidentGroup.staff_update = rs.getString(dbObj.staff_update);
            TempAccidentGroup.update_date_time = rs.getString(dbObj.update_date_time);
            TempAccidentGroup.staff_cancel = rs.getString(dbObj.staff_cancel);
            TempAccidentGroup.cancel_date_time = rs.getString(dbObj.cancel_date_time);
            TempAccidentGroup.active = rs.getString(dbObj.active);
                    
            list.add(TempAccidentGroup);
            TempAccidentGroup = null;
        }
        rs.close();
        return list;
    }
    /**
     * คำสั่ง SQL ในการสร้างตาราง
    CREATE TABLE t_accident_group (
    t_accident_group_id character varying(255) NOT NULL,
    accident_group_time character varying(255),
    accident_group_date character varying(255),
    f_accident_road_type_id character varying(255),
    accident_group_road_name character varying(255),
    f_accident_highway_inout_type_id character varying(255),
    accident_group_kilo character varying(255),
    accident_group_moo character varying(255),
    f_address_id_accident_tambon character varying(255),
    f_address_id_accident_amphur character varying(255),
    f_address_id_accident_changwat character varying(255),
    f_accident_patient_vechicle_type_id character varying(255),
    accident_group_staff_record character varying(255),
    accident_group_record_date_time character varying(255),
    accident_group_staff_update character varying(255),
    accident_group_update_date_time character varying(255),
    accident_group_staff_cancel character varying(255),
    accident_group_cancel_date_time character varying(255),
    accident_group_active character varying(255) DEFAULT 1
);

ALTER TABLE ONLY t_accident_group
    ADD CONSTRAINT t_accident_group_id PRIMARY KEY (t_accident_group_id);
     */
}
