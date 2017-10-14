/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java*/

package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;

import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**ไม่ได้ใช้แล้ว จะลบออกแล้ว  Date: 30/08/47  By: tong*/
public class OrderResultXrayDB
{
    public ConnectionInf theConnectionInf;
    public OrderResultXray dbObj;
    final public String idtable = "204";/*"182";*/
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public OrderResultXrayDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new OrderResultXray();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "order_result_xray";
        dbObj.pk_field = "result_xray_id";
        
        dbObj.order_id = "order_id";
        dbObj.xn = "xn";
        dbObj.filmno = "filmno";
        dbObj.xray_point = "xray_point";
        dbObj.size_film = "size_film";
        dbObj.execute_film = "execute_film";
        dbObj.record_time = "record_time";
        dbObj.description = "description";
        return true;
    }
    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(OrderResultXray o) throws Exception
    {
        String sql="";
        OrderResultXray p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        
        + " ,"	+ dbObj.order_id
        + " ,"	+ dbObj.xn
        + " ,"	+ dbObj.filmno
        + " ,"	+ dbObj.xray_point
        + " ,"	+ dbObj.size_film
        + " ,"	+ dbObj.execute_film
        + " ,"	+ dbObj.record_time
        + " ,"	+ dbObj.description
        + " ) values ('"
        + p.getObjectId()
        
        + "','" + p.order_id
        + "','" + p.xn
        + "','" + p.filmno
        + "','" + p.xray_point
        + "','" + p.size_film
        + "','" + p.execute_film
        + "','" + p.record_time
        + "','" + p.description
        + "')";
        
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(OrderResultXray o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        OrderResultXray p=o;
        
        String field =""
        
        + "', " + dbObj.order_id + "='" + p.order_id
        + "', " + dbObj.xn + "='" + p.xn
        + "', " + dbObj.filmno + "='" + p.filmno
        + "', " + dbObj.xray_point + "='" + p.xray_point
        + "', " + dbObj.size_film + "='" + p.size_film
        + "', " + dbObj.execute_film + "='" + p.execute_film
        + "', " + dbObj.record_time + "='" + p.record_time
        + "', " + dbObj.description + "='" + p.description
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public int delete(OrderResultXray o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public OrderResultXray selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (OrderResultXray)v.get(0);
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector eQuery(String sql) throws Exception
    {
        OrderResultXray p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            p = new OrderResultXray();
            p.setObjectId(rs.getString(dbObj.pk_field));
            
            p.order_id = rs.getString(dbObj.order_id);
            p.xn = rs.getString(dbObj.xn);
            p.filmno = rs.getString(dbObj.filmno);
            p.xray_point = rs.getString(dbObj.xray_point);
            p.size_film = rs.getString(dbObj.size_film);
            p.execute_film = rs.getString(dbObj.execute_film);
            p.record_time = rs.getString(dbObj.record_time);
            p.description = rs.getString(dbObj.description);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    
}
