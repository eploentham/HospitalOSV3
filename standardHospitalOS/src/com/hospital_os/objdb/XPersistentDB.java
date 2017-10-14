/*
 * XPersistentDB.java
 *
 * Created on 20 กุมภาพันธ์ 2552, 11:00 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hospital_os.objdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.usecase.connection.Persistent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author henbe
 */
public abstract class XPersistentDB {
    
    public ConnectionInf theConnectionInf;
    public Persistent persis;
    
    /**
     * =========================================================================
     */
    public int delete(Persistent o) throws Exception
    {
        String sql="delete from " + persis.table
        + " where " + persis.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    /**
     * =========================================================================
     */
    public Vector eQuery(String sql) throws Exception
    {
        ResultSet rs = theConnectionInf.eQuery(sql);
        return eQuery(rs);
    }
    /**
     * =========================================================================
     */
    public Persistent eQuery1(String sql) throws Exception {
        Vector v = eQuery(sql);
        if(!v.isEmpty())
            return (Persistent)v.get(0);
        return null;
    }
    /**
     * =========================================================================
     */
    public Vector eQuery(ResultSet rs) throws Exception
    {
        Vector list = new Vector();
        while(rs.next())
            list.add(rs2Object(rs));

        rs.close();
        return list;
    }
    /**
     * =========================================================================
     */
    public Persistent eQuery1(ResultSet rs) throws Exception {
        Vector v = eQuery(rs);
        if(!v.isEmpty())
            return (Persistent)v.get(0);
        return null;
    }
    /**
     * =========================================================================
     */
    protected PreparedStatement getPStatement(String sql, Object[] value) throws Exception {
        
        PreparedStatement ps = theConnectionInf.getConnection().prepareStatement(sql);
        for(int i=0;i<value.length;i++){
            if(value[i] instanceof String){
            ps.setString(i+1,(String)value[i]);
            }else if(value[i] instanceof Double){
                String data = String.valueOf(value[i]);
                double data1 = Double.parseDouble(data);
                ps.setDouble(i+1,data1);
            }
        }
        return ps;
    }
    /**
     * =========================================================================
     */
    public Persistent eQuery1(String sql, String[] value) throws Exception
    {   
        ResultSet rs = getPStatement(sql,value).executeQuery();
        return eQuery1(rs);
    }
    /**
     * =========================================================================
     */
    public Vector eQuery(String sql, String[] value) throws Exception
    {   
        ResultSet rs = getPStatement(sql,value).executeQuery();
        return eQuery(rs);
    }
    /**
     * =========================================================================
     */
    public int eUpdate(String sql, Object[] value) throws Exception {
        
        PreparedStatement ps = getPStatement(sql,value);
        System.err.println(ps.toString());
        return ps.executeUpdate();
    }
    /**
     * =========================================================================
     */
    
    public Vector selectAll() throws Exception
    {
        if(getSqlSelectAll()==null) 
        return null;
        else
        return eQuery(getSqlSelectAll());
    }
    /**
     * =========================================================================
     */
    public Persistent selectPK(String pk) throws Exception
    {
        String sql="select * from " + persis.table
        + " where " + persis.pk_field + " = '" + pk + "'";
        return eQuery1(sql);
    }
    /**
     * =========================================================================
     */
    public abstract Persistent rs2Object(ResultSet rs)  throws Exception;

    public abstract String getSqlSelectAll();
    
    public abstract boolean initConfig();
     
}
