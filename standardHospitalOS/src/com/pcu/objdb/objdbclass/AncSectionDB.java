/*
 * AncSectionDB.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2548, 14:40 ¹.
 */

package com.pcu.objdb.objdbclass;

import java.util.*;
import java.sql.*; 

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;

import com.pcu.object.*;
/**
 *
 * @author amp
 */
public class AncSectionDB {
    
    /** Creates a new instance of AncSectionDB */
    public AncSectionDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public AncSection dbObj;    
    
    public AncSectionDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new AncSection();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_anc_section";        
        dbObj.pk_field = "f_health_anc_section_id";
        dbObj.description ="health_anc_section_description";
        
        return true;
    }
    
    public int insert(AncSection o) throws Exception
    {
        String sql="";
        AncSection p=o;
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.description 
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.description 
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(AncSection o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        AncSection p=o;
        String field =""
        + "', " + dbObj.description + "='" + p.description   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(AncSection o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vAncSection = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vAncSection = veQuery(sql);
        
        if(vAncSection.size()==0)
            return null;
        else
            return vAncSection;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        AncSection p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new AncSection();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);           
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public Vector veQuery(String sql) throws Exception
    {
        ComboFix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.description);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
