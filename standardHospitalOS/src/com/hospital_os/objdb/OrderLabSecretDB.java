/*
 * OrderLabSecretDB.java
 *
 * Created on 2 ¡’π“§¡ 2549, 14:29 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.objdb;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;
import com.hospital_os.utility.*;
/**
 *
 * @author amp
 */
public class OrderLabSecretDB
{
    public ConnectionInf theConnectionInf;
    public OrderLabSecret dbObj;
    final public String idtable = "281";
    
    /** Creates a new instance of OrderLabSecretDB */
    public OrderLabSecretDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new OrderLabSecret();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_order_lab_secret";
        dbObj.pk_field = "t_order_lab_secret_id";
        dbObj.order_id = "t_order_id";
        dbObj.specimen_code = "order_lab_secret_specimen_code";              
        return true;
    }
    
    public int insert(OrderLabSecret o) throws Exception
    {
        String sql="";
        OrderLabSecret p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.order_id
        + " ,"	+ dbObj.specimen_code
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.order_id
        + "','" + p.specimen_code
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(OrderLabSecret o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        OrderLabSecret p=o;
        String field =""
        + "', " + dbObj.order_id + "='" + p.order_id
        + "', " + dbObj.specimen_code + "='" + p.specimen_code
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(OrderLabSecret o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public OrderLabSecret selectByOrderId(String orderId) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.order_id
        + " = '" + orderId + "'";
        
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (OrderLabSecret)v.get(0);
    }
    /**
     *@author henbe
     * @special_query
     */
    public Vector selectByVisitId(String visit_id) throws Exception
    {
        String sql="select t_order_lab_secret.* From t_order_lab_secret " +
                "left join t_order on t_order.t_order_id = t_order_lab_secret.t_order_id " +
                "where t_order.t_visit_id = '"+ visit_id +"'";
        return eQuery(sql);
    }

    public Vector eQuery(String sql) throws Exception
    {
        OrderLabSecret p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new OrderLabSecret();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.order_id = rs.getString(dbObj.order_id);
            p.specimen_code = rs.getString(dbObj.specimen_code);            
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
