/*
 * MaimTypeDB.java
 *
 * Created on 22 กุมภาพันธ์ 2549, 11:08 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
import com.hospital_os.utility.*;
/**
 *
 * @author Jao
 */
public class MaimTypeDB {
    
    /** Creates a new instance of MaimTypeDB */
    public MaimTypeDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public MaimType dbObj;
    final private String idtable = "785";
    
    public MaimTypeDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new MaimType();
        initConfig(); 
    }
    
    public boolean initConfig()
    {
        dbObj.table = "b_health_maim";        
        dbObj.pk_field = "b_health_maim_id"; 
        dbObj.maim_number = "health_maim_number";
        dbObj.description = "health_maim_description";
        dbObj.active="health_maim_active";
        
        return true;
    }
    
    public int insert(MaimType o) throws Exception
    {
        String sql="";
        MaimType p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field        
        + " ,"  + dbObj.maim_number
        + " ,"  + dbObj.description
        + " ,"  + dbObj.active
        + " ) values ('"
        + p.getObjectId()        
        + "','" + p.maim_number
        + "','" + p.description        
        + "','" + p.active
        + "')";        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(MaimType o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        MaimType p=o;
        String field =""        
        + "', " + dbObj.maim_number+ "='" + p.maim_number
        + "', " + dbObj.description+ "='" + p.description
        + "', " + dbObj.active+ "='" + p.active
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listMaimTypeByNameOrNumberAndActive(String search,String active) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            if(search.trim().length() !=0)
            {
                sql = sql + "(" +  dbObj.maim_number
                + " like '%" + search + "%'" + " or "
                + dbObj.description+ " like '%" + search + "%'" + ") and ";
            }
            sql = sql + dbObj.active + " = '" + active + "' order by " + dbObj.maim_number;
        
        return eQuery(sql);
    }
    
    public Vector selectMaimTypeNumber(String maintypenumber) throws Exception 
    {
        String sql = "select * from "+dbObj.table
                +" where "+dbObj.maim_number+" = '"+maintypenumber+"'";
        return this.eQuery(sql);
    }
    public Vector selectMaimType() throws Exception
    {   
        Vector vMaimType = new Vector();
        
         String sql ="select * from " 
                + dbObj.table + " where " + dbObj.active + " = '1' order by "
                + dbObj.maim_number;
        
        vMaimType = veQuery(sql);
        
        if(vMaimType.size()==0)
            return null;
        else
            return vMaimType;        
    }
    
    public Vector selectAllMaimType() throws Exception
    {   
        Vector vMaimType = new Vector();
        
         String sql ="select * from " 
                + dbObj.table + " where " + dbObj.active + " = '1' order by "
                + dbObj.maim_number;
        
        vMaimType = eQuery(sql);
        
        if(vMaimType.size()==0)
            return null;
        else
            return vMaimType;        
    }
    
    public int delete(MaimType o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        MaimType p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new MaimType();
            p.setObjectId(rs.getString(dbObj.pk_field));            
            p.maim_number= rs.getString(dbObj.maim_number);
            p.description= rs.getString(dbObj.description);
            p.active= rs.getString(dbObj.active);
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
