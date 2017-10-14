/*
 * AutoPaymentDB.java
 *
 * Created on 1 ÁÔ¶Ø¹ÒÂ¹ 2547, 16:01 ¹.
 */

package com.hospital_os.objdb;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  tong
 */
public class AutoPaymentDB
{
    
    public ConnectionInf theConnectionInf;
    public AutoPayment dbObj;
    final private String idtable = "114";/*"327";
*/
    /** Creates a new instance of AutoPaymentDB */
    public AutoPaymentDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new AutoPayment();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table="b_auto_payment";
        dbObj.pk_field="b_auto_payment_id";
        dbObj.plan_key_id ="b_contract_plans_id";
        /*
        dbObj.table = "auto_payment";
        dbObj.pk_field = "key_id";
        dbObj.plan_key_id = "plan_key_id";
         */
        return true;
    }
    
    
    public int insert(AutoPayment o) throws Exception
    {
        String sql="";
        AutoPayment p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.plan_key_id
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.plan_key_id
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(AutoPayment o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        AutoPayment p=o;
        String field =""
        + "', " + dbObj.plan_key_id + "='" + p.plan_key_id
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    /*
*/
    public int delete(AutoPayment o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    /*////////////////////////////////////////////////////////////////////////////
*/
    public AutoPayment selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (AutoPayment)v.get(0);
    }
    
    public AutoPayment selectAll() throws Exception
    {
        String sql="select * from " + dbObj.table ;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (AutoPayment)v.get(0);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        AutoPayment p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            p = new AutoPayment();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.plan_key_id = rs.getString(dbObj.plan_key_id);
            list.add(p);
        }
        rs.close();
        return list;
        
    }
    
    
}
