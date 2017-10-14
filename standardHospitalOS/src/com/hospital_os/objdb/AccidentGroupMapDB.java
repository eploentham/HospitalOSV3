/*
 * AccidentGroupMapDB.java
 *
 * Created on 27 พฤษภาคม 2549, 15:44 น.
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
public class AccidentGroupMapDB {
    public ConnectionInf theConnectionInf;
    public AccidentGroupMap dbObj,TempAccidentGroupMap;
    final private String idtable = "313";
    Vector list;
    String sql="";
    ResultSet rs;
    /** Creates a new instance of AccidentGroupMapDB */
    public AccidentGroupMapDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new AccidentGroupMap();
        initConfig();
    
    }
    public boolean initConfig()
    {
        dbObj.table = "t_accident_group_map_patient";
        dbObj.pk_field  = "t_accident_group_map_patient_id";
        dbObj.t_accident_group_id = "t_accident_group_id";
        dbObj.t_visit_id = "t_visit_id";
        dbObj.t_accident_id = "t_accident_id";
        dbObj.b_accident_group_patient_type_id = "b_accident_group_patient_type_id";
        
        
        return true;
    }
    
    public int insert(AccidentGroupMap o)throws Exception
    {
        sql="";
        TempAccidentGroupMap =o;
        TempAccidentGroupMap.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.t_accident_group_id
        + " ,"  + dbObj.t_visit_id
        + " ,"  + dbObj.t_accident_id
        + " ,"  + dbObj.b_accident_group_patient_type_id        
        + " ) values ('"
        + TempAccidentGroupMap.getObjectId()
        + "','" + TempAccidentGroupMap.t_accident_group_id
        + "','" + TempAccidentGroupMap.t_visit_id
        + "','" + TempAccidentGroupMap.t_accident_id
        + "','" + TempAccidentGroupMap.b_accident_group_patient_type_id
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    
    public int update(AccidentGroupMap o) throws Exception
    {
        sql="update " + dbObj.table + " set ";
        TempAccidentGroupMap=o;
        String field =""
        + "', " + dbObj.t_accident_group_id + "='" + TempAccidentGroupMap.t_accident_group_id
        + "', " + dbObj.t_visit_id + "='" + TempAccidentGroupMap.t_visit_id
        + "', " + dbObj.t_accident_id + "='" + TempAccidentGroupMap.t_accident_id
        + "', " + dbObj.b_accident_group_patient_type_id + "='" + TempAccidentGroupMap.b_accident_group_patient_type_id
        
        + "' where " + dbObj.pk_field + "='" + TempAccidentGroupMap.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        
        return theConnectionInf.eUpdate(sql);
    }
    
    
    public Vector selectByAccidentGroupID(String accidentgroup_id ) throws Exception
    {
        sql = "SELECT * FROM " + dbObj.table +
              " WHERE " + dbObj.t_accident_group_id + " ='" +accidentgroup_id + "';";
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
            TempAccidentGroupMap = new AccidentGroupMap();
            TempAccidentGroupMap.setObjectId(rs.getString(dbObj.pk_field));
            TempAccidentGroupMap.t_accident_group_id = rs.getString(dbObj.t_accident_group_id);
            TempAccidentGroupMap.t_visit_id = rs.getString(dbObj.t_visit_id);
            TempAccidentGroupMap.t_accident_id = rs.getString(dbObj.t_accident_id);
            TempAccidentGroupMap.b_accident_group_patient_type_id = rs.getString(dbObj.b_accident_group_patient_type_id);
            
            list.add(TempAccidentGroupMap);
            TempAccidentGroupMap = null;
        }
        rs.close();
        return list;
    }
 /**
     --date 27/05/49 map กลุ่มอุบัติเหตุกับอุบัติเหตุของผู้ป่วย
CREATE TABLE t_accident_group_map_patient (
    t_accident_group_map_patient_id character varying(255) NOT NULL,
    t_accident_group_id character varying(255)  default '',
    t_visit_id character varying(255)  default '',
    t_accident_id character varying(255)  default '',
    b_accident_group_patient_type_id character varying(255)  default ''
);
ALTER TABLE ONLY t_accident_group_map_patient
    ADD CONSTRAINT t_accident_group_map_patient_id PRIMARY KEY (t_accident_group_map_patient_id);
CREATE INDEX accident_group_map_accident ON t_accident_group_map_patient USING btree (t_accident_id);
CREATE INDEX map_accident_group ON t_accident_group_map_patient USING btree (t_visit_id);
CREATE INDEX t_accident_group_map_id_indx ON t_accident_group_map_patient USING btree (t_accident_group_id);

  */   
}
