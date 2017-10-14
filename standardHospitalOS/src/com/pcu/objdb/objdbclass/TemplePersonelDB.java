/*
 * TemplePersonelDB.java
 *
 * Created on 4 กรกฎาคม 2548, 12:53 น.
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
public class TemplePersonelDB {
    
    /** Creates a new instance of TemplePersonelDB */
    public TemplePersonelDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public TemplePersonel dbObj;
    final private String idtable = "736";
    
    public TemplePersonelDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new TemplePersonel();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "b_health_temple_personel";        
        dbObj.pk_field = "b_health_temple_personel_id";
        dbObj.number ="temple_personel_number";
        dbObj.description = "temple_personel_description";  
        dbObj.active = "temple_personel_active";
        
        return true;
    }
    
    public int insert(TemplePersonel o) throws Exception
    {
        String sql="";
        TemplePersonel p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.number
        + " ,"  + dbObj.description 
        + " ,"  + dbObj.active
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.number
        + "','" + p.description 
        + "','" + p.active
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(TemplePersonel o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        TemplePersonel p=o;
        String field =""
        + "', " + dbObj.number + "='" + p.number
        + "', " + dbObj.description + "='" + p.description   
        + "', " + dbObj.active + "='" + p.active   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(TemplePersonel o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listTemplePersonelByNameOrNumberAndActive(String search,String active) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            if(search.trim().length() !=0)
            {
                sql = sql + "(" +  dbObj.number
                + " like '%" + search + "%'" + " or "
                + dbObj.description+ " like '%" + search + "%'" + ") and ";
            }
            sql = sql + dbObj.active + " = '" + active + "' order by " + dbObj.number;
        
        return eQuery(sql);
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vTemplePersonel = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " where " + dbObj.active + " = '1' order by "
                + dbObj.number;
        
        vTemplePersonel = veQuery(sql);
        
        if(vTemplePersonel.size()==0)
            return null;
        else
            return vTemplePersonel;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        TemplePersonel p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new TemplePersonel();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.number = rs.getString(dbObj.number);
            p.description = rs.getString(dbObj.description);           
            p.active = rs.getString(dbObj.active);
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
