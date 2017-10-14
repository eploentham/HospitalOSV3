/*
 * AccidentGroupPatientTypeDB.java
 *
 * Created on 27 พฤษภาคม 2549, 16:09 น.
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
public class AccidentGroupPatientTypeDB {
    public ConnectionInf theConnectionInf;
    public AccidentGroupPatientType dbObj,TempAccidentGroupPatientType;
    final private String idtable = "314";
    Vector list;
    String sql="";
    ResultSet rs;
    /** Creates a new instance of AccidentGroupPatientTypeDB */
    public AccidentGroupPatientTypeDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new AccidentGroupPatientType();
        initConfig();
    
    }
    public boolean initConfig()
    {
        dbObj.table = "b_accident_group_patient_type";
        dbObj.pk_field  = "b_accident_group_patient_type_id";
        dbObj.accident_group_patient_type_description = "accident_group_patient_type_description";
        dbObj.accident_group_patient_type_notice = "accident_group_patient_type_notice";
        dbObj.accident_group_patient_type_color_code = "accident_group_patient_type_color_code";
        dbObj.accident_group_patient_type_active = "accident_group_patient_type_active";
        
        return true;
    }
    
    public int insert(AccidentGroupPatientType o)throws Exception
    {
        sql="";
        TempAccidentGroupPatientType =o;
        TempAccidentGroupPatientType.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.accident_group_patient_type_description
        + " ,"  + dbObj.accident_group_patient_type_notice
        + " ,"  + dbObj.accident_group_patient_type_color_code      
        + " ,"  + dbObj.accident_group_patient_type_active 
                + " ) values ('"
        + TempAccidentGroupPatientType.getObjectId()
        + "','" + TempAccidentGroupPatientType.accident_group_patient_type_description
        + "','" + TempAccidentGroupPatientType.accident_group_patient_type_notice
        + "','" + TempAccidentGroupPatientType.accident_group_patient_type_color_code
        + "','" + TempAccidentGroupPatientType.accident_group_patient_type_active
                + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    
    public int update( AccidentGroupPatientType o) throws Exception
    {
        sql="update " + dbObj.table + " set ";
        TempAccidentGroupPatientType=o;
        String field =""
        + "', " + dbObj.accident_group_patient_type_description + "='" + TempAccidentGroupPatientType.accident_group_patient_type_description
        + "', " + dbObj.accident_group_patient_type_notice + "='" + TempAccidentGroupPatientType.accident_group_patient_type_notice
        + "', " + dbObj.accident_group_patient_type_color_code + "='" + TempAccidentGroupPatientType.accident_group_patient_type_color_code
        + "', " + dbObj.accident_group_patient_type_active + "='" + TempAccidentGroupPatientType.accident_group_patient_type_active
        
        + "' where " + dbObj.pk_field + "='" + TempAccidentGroupPatientType.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        
        return theConnectionInf.eUpdate(sql);
    }
    
    
    public Vector selectAll( ) throws Exception
    {
        sql = "SELECT * FROM " + dbObj.table +
              " WHERE " + dbObj.accident_group_patient_type_active + " ='"+Active.isEnable()+"';";
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
            TempAccidentGroupPatientType = new  AccidentGroupPatientType();
            TempAccidentGroupPatientType.setObjectId(rs.getString(dbObj.pk_field));
            TempAccidentGroupPatientType.accident_group_patient_type_description = rs.getString(dbObj.accident_group_patient_type_description);
            TempAccidentGroupPatientType.accident_group_patient_type_notice = rs.getString(dbObj.accident_group_patient_type_notice);
            TempAccidentGroupPatientType.accident_group_patient_type_color_code = rs.getString(dbObj.accident_group_patient_type_color_code);
            TempAccidentGroupPatientType.accident_group_patient_type_active = rs.getString(dbObj.accident_group_patient_type_active);
            
            list.add(TempAccidentGroupPatientType);
            TempAccidentGroupPatientType = null;
        }
        rs.close();
        return list;
    }
    /**
     --date 27/05/49 ประเภทของผู้เกิดอุบัตเหตุหมู่
CREATE TABLE b_accident_group_patient_type (
    b_accident_group_patient_type_id character varying(255) NOT NULL,
    accident_group_patient_type_description character varying(255) ,
    accident_group_patient_type_notice character varying(255),
    accident_group_patient_type_color_code character varying(255),
    accident_group_patient_type_active character varying(255) DEFAULT 1
);
ALTER TABLE ONLY b_accident_group_patient_type
    ADD CONSTRAINT b_accident_group_patient_type_id PRIMARY KEY (b_accident_group_patient_type_id);
     */
}
