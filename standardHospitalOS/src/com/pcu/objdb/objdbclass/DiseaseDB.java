/*
 * ContagiousDB.java
 *
 * Created on 22 กุมภาพันธ์ 2549, 9:45 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.ComboFix;
import com.pcu.object.*;
/**
 *
 * @author kingland
 */
public class DiseaseDB {
    /**
     * Connection
     */
    public ConnectionInf theConnectionInf;
    /**
     * Database Object
     */
    public Disease dbObj;
    final private String idtable = "783";
    /** Creates a new instance of ContagiousDB */
    public DiseaseDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Disease();
        initConfig();
    }
    /**
     * กำหนดฟิลด์ของตาราง
     * @param  
     * @return 
     * @author kingland
     * @date 22-02-2549
     */
    public boolean initConfig() 
    {
        dbObj.table = "b_health_disease";
        dbObj.pk_field = "b_health_disease_id";
        dbObj.active = "health_disease_active";
        dbObj.diseaseName = "health_disease_description";        
        dbObj.isContagiousDisease = "health_disease_contagious";
        dbObj.number = "health_disease_number";
        dbObj.standardName = "health_disease_common_name";
        return true;
    }
    /**
     * เพิ่มข้อมูลเข้าไปในตาราง
     * @param  
     * @return 
     * @author kingland
     * @date 22-02-2549
     */
    public int insert(Disease o) throws Exception 
    {
        String sql="";
        Disease p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
                + dbObj.pk_field
                + " ,"	+ dbObj.active
                + " ,"	+ dbObj.diseaseName                
                + " ,"	+ dbObj.isContagiousDisease
                + " ,"	+ dbObj.number
                + " ,"	+ dbObj.standardName
                + " ) values ('"
                + p.getObjectId()
                + "','" + p.active
                + "','" + p.diseaseName                
                + "','" + p.isContagiousDisease
                + "','" + p.number
                + "','" + p.standardName
                + "')";
        return theConnectionInf.eUpdate(sql);
    }
    /**
     * ใช้การคิวรีข้อมูล แล้วสร้างเป็น Object
     * @param  
     * @return 
     * @author kingland
     * @date 22-02-2549
     */        
    public Vector eQuery(String sql) throws Exception 
    {
        Disease p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) 
        {
            p = new Disease();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.active = rs.getString(dbObj.active);
            p.diseaseName = rs.getString(dbObj.diseaseName);            
            p.isContagiousDisease = rs.getString(dbObj.isContagiousDisease);
            p.number = rs.getString(dbObj.number);
            p.standardName = rs.getString(dbObj.standardName);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /**
     * ใช้การคิวรีข้อมูล แล้วสร้างเป็น Cobofix
     * @param  
     * @return 
     * @author kingland
     * @date 22-02-2549
     */     
     public Vector ceQuery(String sql) throws Exception 
     {
        ComboFix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) 
        {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.diseaseName);
            p.other = rs.getString(dbObj.isContagiousDisease);
            list.add(p);
        }
        rs.close();
        return list;
     }
     
     /**
     * ใช้การคิวรีข้อมูล แล้วสร้างเป็น Combofix
     * @param sql
     * @return Vector ของ Disease
     * @author amp
     * @date 18-04-2549
     */     
     public Vector veQuery(String sql) throws Exception 
     {
        ComboFix  p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) 
        {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.diseaseName);
            list.add(p);
        }
        rs.close();
        return list;
     }
     
    /**
     * อับเดตข้อมูลในตาราง
     * @param  
     * @return 
     * @author kingland
     * @date 22-02-2549
     */
     public int update(Disease o) throws Exception 
     {
        String sql="update " + dbObj.table + " set ";
        Disease p=o;
        String field = ""
                + "', " + dbObj.active + "='" + p.active
                + "', " + dbObj.diseaseName + "='" + p.diseaseName                
                + "', " + dbObj.isContagiousDisease + "='" + p.isContagiousDisease
                + "', " + dbObj.number + "='" + p.number
                + "', " + dbObj.standardName + "='" + p.standardName
                + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql + field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
     /**
     * ลบข้อมูลในตาราง
     * @param  
     * @return 
     * @author kingland
     * @date 22-02-2549
     */
    public int delete(Disease o) throws Exception 
    {
        String sql="delete from " + dbObj.table
                + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
     /**
     * ค้นหาข้อมูลทั้งหมดที่Active
     * @param  
     * @return 
     * @author kingland
     * @date 22-02-2549
     */
    public Vector selectAll() throws Exception
    {
        Vector v = null;
        String sql = "select * from "+dbObj.table
                +" where "+dbObj.active+" = '1'" 
                +" order by "+dbObj.number;
        v = eQuery(sql);
        return v;
    }
    
    /**
     * ค้นหาข้อมูลทั้งหมดที่ Active แสดงเป็น Combobox
     * @return Vector ของ Disease
     * @author amp
     * @date 18-04-2549
     */
    public Vector selectAllCombobox() throws Exception
    {
        Vector v = null;
        String sql = "select * from "+dbObj.table
                +" where "+dbObj.active+" = '1'" 
                +" order by "+dbObj.number;
        v = veQuery(sql);
        return v;
    }
    
    /**
     * ค้นหาข้อมูลทั้งหมดที่ไม่Active
     * @param  
     * @return 
     * @author kingland
     * @date 22-02-2549
     */
    public Vector selectAllNotActive() throws Exception
    {
        Vector v = null;
        String sql = "select * from "+dbObj.table
                +" where "+dbObj.active+" = '0'"
                +" order by "+dbObj.number;
        v = eQuery(sql);
        return v;
    }
    /**
     * ค้นหาข้อมูลโดยใช้คำค้นหา
     * @param  
     * @return 
     * @author kingland
     * @date 22-02-2549
     */
    public Vector selectDiseaseByName(String searchword) throws Exception
    {
        Vector v = null;
        String sql = "select * from "+dbObj.table
                +" where ("+dbObj.diseaseName+" like '%"+searchword+"%'"
                +" or "+dbObj.number+" like '%"+searchword+"%'"
                +" or "+dbObj.standardName+" like '%"+searchword+"%')"
                +" and "+dbObj.active+" = '1'"
                +" order by "+dbObj.number;
        v = eQuery(sql);
        return v;
    }
    /**
     * ค้นหาข้อมูลที่ไม่ Active โดยใช้คำค้นหา
     * @param  
     * @return 
     * @author kingland
     * @date 22-02-2549
     */
    public Vector selectDiseaseNotActiveByName(String searchword) throws Exception
    {
        Vector v = null;
        String sql = "select * from "+dbObj.table
                +" where ("+dbObj.diseaseName+" like '%"+searchword+"%'"
                +" or "+dbObj.standardName+" like '%"+searchword+"%'"
                +" or "+dbObj.standardName+" like '%"+searchword+"%')"
                +" and "+dbObj.active+" = '0'"
                +" order by "+dbObj.number;
        v = eQuery(sql);
        return v;
    }
    /**
     * ค้นหาโรคไม่ติดต่อและติดต่อ
     * @param  boolean (ture = โรคติดต่อ  false = โรคไม่ติดต่อ)
     * @return 
     * @author kingland
     * @date 24-02-2549
     */
    public Vector selectDisease(boolean isDiseaseContagious) throws Exception
    {
        Vector v = null;
        String sql = "";
        if(isDiseaseContagious == true)
        {
               sql = "select * from "+dbObj.table
                +" where "+dbObj.isContagiousDisease +" = '"+1+"'"
                +" and "+dbObj.active+" = '1'"
                +" order by "+dbObj.number;
               v = eQuery(sql);
        }
        else
        {
             sql = "select * from "+dbObj.table
                +" where "+dbObj.isContagiousDisease +" = '"+1+"'"
                +" and "+dbObj.active+" = '0'"
                +" order by "+dbObj.number;
             v = eQuery(sql);
        }
        return v;
    }
    /**
     * ค้นหาโรคไม่ติดต่อและติดต่อ
     * @param  boolean (ture = โรคติดต่อ  false = โรคไม่ติดต่อ)
     * @return Vector ของ ComboFix
     * @author kingland
     * @date 24-02-2549
     */
    public Vector selectConboFixDisease(boolean isDiseaseContagious) throws Exception
    {
        Vector v = null;
        String sql = "";
        if(isDiseaseContagious == true)
        {
               sql = "select * from "+dbObj.table
                +" where "+dbObj.isContagiousDisease +" = '"+1+"'"
                +" and "+dbObj.active+" = '1'"
                +" and "+dbObj.number+" <> '99'"
                +" order by "+dbObj.number;
               v = ceQuery(sql);
        }
        else
        {
             sql = "select * from "+dbObj.table
                +" where " + dbObj.active+ " = '1'"
                +" and "+dbObj.number+" <> '99'"
                +" order by "+dbObj.number;
             v = ceQuery(sql);
        }
        return v;
    }
    /**
     * ค้นหาโรคไม่ติดต่อและติดต่อโดยใช้ Primary Key
     * @param  รหัส Primary Key
     * @return Disease
     * @author kingland
     * @date 24-02-2549
     */
    public Disease selectByPK(String pk) throws Exception
    {
        Vector v = null;
        String sql = "select * from "+dbObj.table
                +" where "+dbObj.pk_field+" = '"+pk+"'";
        v = eQuery(sql);        
        //return  (Disease)v.get(0);
        //amp:18/04/2549
        if(v.size()==0)
            return null;
        else
            return (Disease)v.get(0);
    }
}
