/*
 * MarketTypeLockDB.java
 *
 * Created on 11 กรกฎาคม 2548, 9:54 น.
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
public class MarketTypeLockDB {
    
    /** Creates a new instance of MarketTypeLockDB */
    public MarketTypeLockDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public MarketTypeLock dbObj;
    final private String idtable = "748";
    
    public MarketTypeLockDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new MarketTypeLock();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "b_health_market_type_lock";        
        dbObj.pk_field = "b_health_market_type_lock_id";
        dbObj.number ="market_type_lock_number";
        dbObj.description = "market_type_lock_description";  
        dbObj.active = "market_type_lock_active";
        
        return true;
    }
    
    public int insert(MarketTypeLock o) throws Exception
    {
        String sql="";
        MarketTypeLock p=o;
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
    
    public int update(MarketTypeLock o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        MarketTypeLock p=o;
        String field =""
        + "', " + dbObj.number + "='" + p.number
        + "', " + dbObj.description + "='" + p.description   
        + "', " + dbObj.active + "='" + p.active   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(MarketTypeLock o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listMarketTypeLockByNameOrNumberAndActive(String search,String active) throws Exception
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
        Vector vMarketTypeLock = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " where " + dbObj.active + " = '1' order by "
                + dbObj.number;
        
        vMarketTypeLock = veQuery(sql);
        
        if(vMarketTypeLock.size()==0)
            return null;
        else
            return vMarketTypeLock;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        MarketTypeLock p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new MarketTypeLock();
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
