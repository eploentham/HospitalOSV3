/*
 * BorrowFilmXrayDB.java
 *
 * Created on 14 กุมภาพันธ์ 2549, 12:34 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import java.util.*;
import java.sql.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
/**
 *
 * @author Administrator
 */
public class BorrowFilmXrayDB {
    public ConnectionInf theConnectionInf;
    public BorrowFilmXray dbObj;
    final public String idtable = "267";
    /** Creates a new instance of BorrowFilmXrayDB */
    public BorrowFilmXrayDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new BorrowFilmXray();
        initConfig();
    }
    public BorrowFilmXrayDB()
    {
        theConnectionInf= null;
        dbObj = new BorrowFilmXray();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table = "t_borrow_film_xray";
        dbObj.pk_field = "t_borrow_film_xray_id";
        dbObj.patient_hn = "patient_hn";
        dbObj.patient_xn = "patient_xn";
        dbObj.borrower_prefix = "borrower_prefix";
        dbObj.borrower_name = "borrower_name";
        dbObj.borrower_lastname = "borrower_lastname";
        dbObj.borrow_film_date = "borrow_film_date";
        dbObj.amount_date = "amount_date";
        dbObj.return_film_date = "return_film_date";
        dbObj.borrow_status = "borrow_status";
        dbObj.permissibly_borrow = "permissibly_borrow";
        dbObj.borrow_cause = "borrow_cause";
        dbObj.borrow_to = "borrow_to";
        dbObj.borrow_to_other = "borrow_to_other";
        dbObj.date_serv = "date_serv";
        dbObj.borrow_staff_record = "borrow_staff_record";
        dbObj.borrow_record_date_time = "borrow_record_date_time";
        dbObj.borrow_staff_update = "borrow_staff_update";
        dbObj.borrow_update_date_time = "borrow_update_date_time";
        dbObj.borrow_staff_cancel = "borrow_staff_cancel";
        dbObj.borrow_cancel_date_time = "borrow_cancel_date_time";
        dbObj.borrow_active = "borrow_active";

        return true;
    }
    
    /**
     * @param cmd
     * @param o
     * @return int
     */
    public int insert(BorrowFilmXray o) throws Exception
    {
        String sql="";
        BorrowFilmXray p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ," + dbObj.patient_hn
        + " ," + dbObj.patient_xn
        + " ," + dbObj.borrower_prefix
        + " ," + dbObj.borrower_name
        + " ," + dbObj.borrower_lastname
        + " ," + dbObj.borrow_film_date
        + " ," + dbObj.amount_date
        + " ," + dbObj.return_film_date
        + " ," + dbObj.borrow_status
        + " ," + dbObj.permissibly_borrow
        + " ," + dbObj.borrow_cause
        + " ," + dbObj.borrow_to
        + " ," + dbObj.borrow_to_other
        + " ," + dbObj.date_serv
        + " ," + dbObj.borrow_staff_record
        + " ," + dbObj.borrow_record_date_time
        + " ," + dbObj.borrow_staff_update
        + " ," + dbObj.borrow_update_date_time
        + " ," + dbObj.borrow_staff_cancel
        + " ," + dbObj.borrow_cancel_date_time
        + " ," + dbObj.borrow_active
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.patient_hn
        + "','" + p.patient_xn
        + "','" + p.borrower_prefix
        + "','" + p.borrower_name
        + "','" + p.borrower_lastname
        + "','" + p.borrow_film_date
        + "','" + p.amount_date
        + "','" + p.return_film_date
        + "','" + p.borrow_status
        + "','" + p.permissibly_borrow
        + "','" + p.borrow_cause
        + "','" + p.borrow_to
        + "','" + p.borrow_to_other
        + "','" + p.date_serv
        + "','" + p.borrow_staff_record
        + "','" + p.borrow_record_date_time
        + "','" + p.borrow_staff_update
        + "','" + p.borrow_update_date_time
        + "','" + p.borrow_staff_cancel
        + "','" + p.borrow_cancel_date_time
        + "','" + p.borrow_active
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int update(BorrowFilmXray o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        BorrowFilmXray p=o;
        String field =""
        + "', " + dbObj.patient_hn + "='" + p.patient_hn
        + "', " + dbObj.patient_xn + "='" + p.patient_xn
        + "', " + dbObj.borrower_prefix + "='" + p.borrower_prefix
        + "', " + dbObj.borrower_name + "='" + p.borrower_name
        + "', " + dbObj.borrower_lastname + "='" + p.borrower_lastname
        + "', " + dbObj.borrow_film_date + "='" + p.borrow_film_date
        + "', " + dbObj.amount_date + "='" + p.amount_date
        + "', " + dbObj.return_film_date + "='" + p.return_film_date
        + "', " + dbObj.borrow_status + "='" + p.borrow_status
        + "', " + dbObj.permissibly_borrow + "='" + p.permissibly_borrow
        + "', " + dbObj.borrow_cause + "='" + p.borrow_cause
        + "', " + dbObj.borrow_to + "='" + p.borrow_to
        + "', " + dbObj.borrow_to_other + "='" + p.borrow_to_other
        + "', " + dbObj.date_serv + "='" + p.date_serv
        + "', " + dbObj.borrow_staff_record + "='" + p.borrow_staff_record
        + "', " + dbObj.borrow_record_date_time + "='" + p.borrow_record_date_time
        + "', " + dbObj.borrow_staff_update + "='" + p.borrow_staff_update
        + "', " + dbObj.borrow_update_date_time + "='" + p.borrow_update_date_time
        + "', " + dbObj.borrow_staff_cancel + "='" + p.borrow_staff_cancel
        + "', " + dbObj.borrow_cancel_date_time + "='" + p.borrow_cancel_date_time
        + "', " + dbObj.borrow_active + "='" + p.borrow_active
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public int delete(BorrowFilmXray o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAll() throws Exception
    {
        String sql="select * from " + dbObj.table ;
        sql = sql +  " order by "+ dbObj.borrow_film_date;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*///////////////////////////////////////////////////////////////////////////////*/
    public Vector selectByDate(String datefrom, String dateto, String hn) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.borrow_film_date
        + " >= '" + datefrom + "' and " + dbObj.borrow_film_date
        + " <= '" + dateto + "' and " + dbObj.patient_hn
        + " = '" + hn + "'"
        + " order by " + dbObj.borrow_film_date;
        
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/ 
    public Vector selectByPId(String hn) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.patient_hn
        + " = '" + hn + "'"
        + " order by " + dbObj.borrow_film_date;
        
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public BorrowFilmXray selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (BorrowFilmXray)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector eQuery(String sql) throws Exception
    {
        BorrowFilmXray p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new BorrowFilmXray();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.patient_hn = rs.getString(dbObj.patient_hn);
            p.patient_xn = rs.getString(dbObj.patient_xn);
            p.borrower_prefix = rs.getString(dbObj.borrower_prefix);
            p.borrower_name = rs.getString(dbObj.borrower_name);
            p.borrower_lastname = rs.getString(dbObj.borrower_lastname);
            p.borrow_film_date = rs.getString(dbObj.borrow_film_date);
            p.amount_date = rs.getString(dbObj.amount_date);
            p.return_film_date = rs.getString(dbObj.return_film_date);
            p.borrow_status = rs.getString(dbObj.borrow_status);
            p.permissibly_borrow = rs.getString(dbObj.permissibly_borrow);
            p.borrow_cause = rs.getString(dbObj.borrow_cause);
            p.borrow_to = rs.getString(dbObj.borrow_to);
            p.borrow_to_other = rs.getString(dbObj.borrow_to_other);
            p.date_serv = rs.getString(dbObj.date_serv);
            p.borrow_staff_record = rs.getString(dbObj.borrow_staff_record);
            p.borrow_record_date_time = rs.getString(dbObj.borrow_record_date_time);
            p.borrow_staff_update = rs.getString(dbObj.borrow_staff_update);
            p.borrow_update_date_time = rs.getString(dbObj.borrow_update_date_time);
            p.borrow_staff_cancel = rs.getString(dbObj.borrow_staff_cancel);
            p.borrow_cancel_date_time = rs.getString(dbObj.borrow_cancel_date_time);
            p.borrow_active = rs.getString(dbObj.borrow_active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    
}
