/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hospital_os.objdb;

import com.hospital_os.object.X39Persistent;
import com.hospital_os.usecase.connection.ConnectionInf;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author henbe
 */
public abstract class X39DB {
 
    protected ConnectionInf theConnectionInf;
    protected X39Persistent dbObjX;
    public X39Persistent selectPK(String pk) throws Exception
    {
        String sql="select * from " + dbObjX.table
        + " where " + dbObjX.getObjectId() + " = ?";
        return eQueryX1(sql,new String[]{pk});
    }
//    public Vector<X39Persistent> selectAll() throws Exception
//    {
//        String sql="select * from " + dbObjX.table;
//        Vector<X39Persistent> v = new Vector<X39Persistent>();
//        eQuery(sql,null,v);
//        return v;
//    }
    public int insert(X39Persistent p) throws Exception {
        String sql="insert into " + dbObjX.table + " (";
        try{
            p.generateOID();
            String[] arr = p.getStringArray();
            sql+=dbObjX.getStream(",");
            sql+= " ) values (";
            sql+=X39Persistent.getStream(arr.length,",");
            sql+=")";
            PreparedStatement ps = theConnectionInf.getConnection().prepareStatement(sql);
            for(int i=0;i<arr.length;i++){
                ps.setString(i+1, arr[i]);
            }
            System.out.println(ps.toString());
            return ps.executeUpdate();
        }
        catch(Exception e){
            System.out.println(sql);
            throw e;
        }
    }
    public int update(X39Persistent o) throws Exception {
        String sql="update " + dbObjX.table + " set ";
        try{
            sql+=dbObjX.getStream("=?,");
            sql+="=? where " + dbObjX.getObjectId()+ "='" + o.getObjectId() +"'";
            PreparedStatement ps = theConnectionInf.getConnection().prepareStatement(sql);
            String[] arr = o.getStringArray();
            for(int i=0;i<arr.length;i++){
                ps.setString(i+1, arr[i]);
            }
            System.out.println(ps.toString());
            return ps.executeUpdate();
        }
        catch(Exception e){
            System.out.println(sql);
            throw e;
        }
    }
    public int delete(X39Persistent o) throws Exception
    {
        String sql = "delete from  " + dbObjX.table +
                " WHERE " + dbObjX.getObjectId() + " ='" + o.getObjectId() + "'";
        return theConnectionInf.eUpdate(sql);
    }
    public int eUpdate(String sql, String[] str) throws SQLException
    {
        PreparedStatement ps = theConnectionInf.getConnection().prepareStatement(sql);
        for(int i=0;str!=null && i<str.length;i++)
            ps.setString(i+1,str[i]);
        return ps.executeUpdate();
    }
    ////////////////////////////////////////////////////////////////////////////
/**
 * @deprecated henbe unused old pattern
 * @param sql
 * @param str
 * @return
 * @throws SQLException
 */
    public Vector<X39Persistent> eQuery(String sql) throws Exception
    {
        ResultSet rs = theConnectionInf.eQuery(sql);
        Vector<X39Persistent> vRet = new Vector<X39Persistent>();
        String[] array = dbObjX.getStringArray();
        while(rs.next())
        {
            String[] strd = new String[array.length];
            for(int i=0;i<array.length;i++){
                strd[i] = rs.getString(array[i]);
            }
            vRet.add(dbObjX.getInstant(strd));
        }
        rs.close(); 
        return vRet;
    }
    protected Vector<X39Persistent> eQueryX(String sql, String[] str) throws SQLException
    {
        PreparedStatement ps = theConnectionInf.getConnection().prepareStatement(sql);
        Vector<X39Persistent> vRet = new Vector<X39Persistent>();
        for(int i=0;str!=null && i<str.length;i++)
            ps.setString(i+1,str[i]);
        ResultSet rs = ps.executeQuery();
        String[] array = dbObjX.getStringArray();
        while(rs.next())
        {
            String[] strd = new String[array.length];
            for(int i=0;i<array.length;i++){
                strd[i] = rs.getString(array[i]);
            }
            vRet.add(dbObjX.getInstant(strd));
        }
        rs.close();
        return vRet;
    }

    protected X39Persistent eQueryX1(String sql, String[] str) throws SQLException {
        Vector v = eQueryX(sql,str);
        if(v.size()==0)
            return null;
        else
            return (X39Persistent)v.get(0);
    }

    ////////////////////////////////////////////////////////////////////////////
}
