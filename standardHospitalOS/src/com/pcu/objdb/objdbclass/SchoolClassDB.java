/*
 * SchoolClassDB.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2548, 17:19 ¹.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.pcu.object.SchoolClass;


import com.hospital_os.utility.*;
/**
 *
 * @author Noom
 */
public class SchoolClassDB {
    
    public ConnectionInf theConnectionInf;
    public SchoolClass dbObj;
    final private String idtable = "733";
    
    /** Creates a new instance of SchoolClassDB */
    public SchoolClassDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new SchoolClass();
        initConfig();
    }
    
    public boolean initConfig() {
        dbObj.table = "b_school_class";
        dbObj.pk_field = "b_school_class_id";
        dbObj.number ="school_class_number";
        dbObj.description = "school_class_description";
        dbObj.school_max_class_status ="school_max_class_status";
        dbObj.active = "school_class_active";
        return true;
    }
    
    public int insert(SchoolClass o) throws Exception {
        String sql="";
        SchoolClass p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
                + dbObj.pk_field
                + " ,"  + dbObj.number
                + " ,"  + dbObj.description
                + " ,"  + dbObj.school_max_class_status
                + " ,"	+ dbObj.active
                + " ) values ('"
                + p.getObjectId()
                + "','" + p.number
                + "','" + p.description
                + "','" + p.school_max_class_status
                + "','" + p.active
                + "')";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(SchoolClass o) throws Exception {
        String sql="update " + dbObj.table + " set ";
        SchoolClass p=o;
        sql += dbObj.number + "='" + p.number
                + "', " + dbObj.description + "='" + p.description
                + "', " + dbObj.school_max_class_status + "='" + p.school_max_class_status
                + "', " + dbObj.active + "='" + p.active
                + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(SchoolClass o) throws Exception {
        String sql="delete from " + dbObj.table
                + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listSchoolClassByNameOrNumberAndActive(String search,String active) throws Exception {
        String sql="select * from " + dbObj.table + " where ";
        if(search.trim().length() !=0) {
            sql = sql + "(" +  dbObj.number
                    + " like '%" + search + "%'" + " or "
                    + dbObj.description+ " like '%" + search + "%'" + ") and ";
        }
        sql = sql + dbObj.active + " = '" + active + "' order by " + dbObj.number;
        
        return eQuery(sql);
    }
    
    public Vector selectAll() throws Exception {
        Vector vSchoolClass = new Vector();
        
        String sql ="select * from "
                + dbObj.table + " where " + dbObj.active + " = '1' order by "
                + dbObj.number;
        
        vSchoolClass = veQuery(sql);
        
        if(vSchoolClass.size()==0)
            return null;
        else
            return vSchoolClass;
        
    }
    
      public Vector selectAllMaxClass() throws Exception {
        Vector vSchoolClass = new Vector();
        
        String sql ="select * from "
                + dbObj.table + " where " + dbObj.school_max_class_status + " = '1' and "+ dbObj.active + " = '1' order by "
                + dbObj.number;
        
        vSchoolClass = veQuery(sql);
        
        if(vSchoolClass.size()==0)
            return null;
        else
            return vSchoolClass;
        
    }
    
    
    public Vector selectByPK(String pk) throws Exception{
        Vector vSchoolClass = new Vector();
        String sql ="select * from "+ dbObj.table
                + " where " + dbObj.pk_field + "='" + pk +"'"
                + " and "+dbObj.active + " = '1' ";
        vSchoolClass = eQuery(sql);
        if(vSchoolClass.size()==0)
            return null;
        else
            return vSchoolClass;
    }
    
     public Vector selectLowerBySchoolClassNumber(String number) throws Exception{
        Vector vSchoolClass = new Vector();
        String sql ="select * from "+ dbObj.table
                + " where " + dbObj.number + "<='" + number +"'"
                + " and "+dbObj.active + " = '1' "
                + " order by " + dbObj.number;
        vSchoolClass = veQuery(sql);
        if(vSchoolClass.size()==0)
            return null;
        else
            return vSchoolClass;
    }
    
    public Vector eQuery(String sql) throws Exception {
        SchoolClass p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) {
            p = new SchoolClass();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.number = rs.getString(dbObj.number);
            p.description = rs.getString(dbObj.description);
            p.school_max_class_status = rs.getString(dbObj.school_max_class_status);
            p.active = rs.getString(dbObj.active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public Vector veQuery(String sql) throws Exception {
        ComboFix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.description);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    //test from noom (à´ÕèÂÇÅºÍÍ¡)
    public static void main(String args[]) throws Exception{
        String url = "jdbc:postgresql://192.168.1.6:5432/develop_support";
        String user = "postgres";
        String pass = "postgres";
        String dri = "org.postgresql.Driver";
        String type = "0"; //0 postgres 1 mysql 2 sqlserver
        ConnectionInf theConnectionInf = new ConnectionDBMgr(dri,url,user,pass,type);
        
        SchoolClassDB v = new SchoolClassDB(theConnectionInf);
        SchoolClass s = new SchoolClass();
        /*
        s.number = "41";
        s.description = "Á.4";
        s.active = "1";
        v.insert(s);
        
        s = new SchoolClass();
        s.number = "41";
        s.description = "»Çª.1";
        s.active = "1";
        v.insert(s);
        
        s = new SchoolClass();
        s.number = "42";
        s.description = "Á.5";
        s.active = "1";
        v.insert(s);
        
        s = new SchoolClass();
        s.number = "42";
        s.description = "»Çª.2";
        s.active = "1";
        v.insert(s);
        
        s = new SchoolClass();
        s.number = "43";
        s.description = "Á.6";
        s.active = "1";
        v.insert(s);
        
        s = new SchoolClass();
        s.number = "43";
        s.description = "»Çª.3";
        s.active = "1";
        v.insert(s);
        */
         //Vector t = v.selectAll();
        
        //s = (SchoolClass)v.selectByPK("725000006109021804").elementAt(0);
        //
        
        //v.update(s);
       Vector vec =  v.selectLowerBySchoolClassNumber("11");
       
        /*
        ComboFix c = new ComboFix();
        Vector vec = v.selectAllMaxClass();
        
        for(int i=0;i<vec.size();i++){
            c = (ComboFix)vec.elementAt(i);
            
            //
        }*/
        
    }
}
