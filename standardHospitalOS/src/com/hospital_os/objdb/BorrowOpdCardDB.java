/*
 * BorrowOpdCardDB.java
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
 * @author sumo
 */
public class BorrowOpdCardDB {
    public ConnectionInf theConnectionInf;
    public BorrowOpdCard dbObj;
    final public String idtable = "268";
    /** Creates a new instance of BorrowOpdCardDB */
    public BorrowOpdCardDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new BorrowOpdCard();
        initConfig();
    }
    public BorrowOpdCardDB()
    {
        theConnectionInf= null;
        dbObj = new BorrowOpdCard();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table = "t_borrow_opdcard";
        dbObj.pk_field = "t_borrow_opdcard_id";
        dbObj.patient_hn = "patient_hn";
        dbObj.borrower_opd_prefix = "borrower_opd_prefix";
        dbObj.borrower_opd_name = "borrower_opd_name";
        dbObj.borrower_opd_lastname = "borrower_opd_lastname";
        dbObj.borrow_opd_date = "borrow_opd_date";
        dbObj.amount_date_opd = "amount_date_opd";
        dbObj.return_opd_date = "return_opd_date";
        dbObj.borrow_opd_status = "borrow_opd_status";
        dbObj.permissibly_borrow_opd = "permissibly_borrow_opd";
        dbObj.borrow_opd_cause = "borrow_opd_cause";
        dbObj.borrow_opd_to = "borrow_opd_to";
        dbObj.borrow_opd_to_other = "borrow_opd_to_other";
        dbObj.date_serv_opd = "date_serv_opd";
        dbObj.borrow_opdcard_staff_record = "borrow_opdcard_staff_record";
        dbObj.borrow_opdcard_record_date_time = "borrow_opdcard_record_date_time";
        dbObj.borrow_opdcard_staff_update = "borrow_opdcard_staff_update";
        dbObj.borrow_opdcard_update_date_time = "borrow_opdcard_update_date_time";
        dbObj.borrow_opdcard_staff_cancel = "borrow_opdcard_staff_cancel";
        dbObj.borrow_opdcard_cancel_date_time = "borrow_opdcard_cancel_date_time";
        dbObj.borrow_opdcard_active = "borrow_opdcard_active";

        return true;
    }
    
    /**
     * @param cmd
     * @param o
     * @return int
     */
    public int insert(BorrowOpdCard o) throws Exception
    {
        String sql="";
        BorrowOpdCard p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ," + dbObj.patient_hn
        + " ," + dbObj.borrower_opd_prefix
        + " ," + dbObj.borrower_opd_name
        + " ," + dbObj.borrower_opd_lastname
        + " ," + dbObj.borrow_opd_date
        + " ," + dbObj.amount_date_opd
        + " ," + dbObj.return_opd_date
        + " ," + dbObj.borrow_opd_status
        + " ," + dbObj.permissibly_borrow_opd
        + " ," + dbObj.borrow_opd_cause
        + " ," + dbObj.borrow_opd_to
        + " ," + dbObj.borrow_opd_to_other
        + " ," + dbObj.date_serv_opd
        + " ," + dbObj.borrow_opdcard_staff_record
        + " ," + dbObj.borrow_opdcard_record_date_time
        + " ," + dbObj.borrow_opdcard_staff_update
        + " ," + dbObj.borrow_opdcard_update_date_time
        + " ," + dbObj.borrow_opdcard_staff_cancel
        + " ," + dbObj.borrow_opdcard_cancel_date_time
        + " ," + dbObj.borrow_opdcard_active
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.patient_hn
        + "','" + p.borrower_opd_prefix
        + "','" + p.borrower_opd_name
        + "','" + p.borrower_opd_lastname
        + "','" + p.borrow_opd_date
        + "','" + p.amount_date_opd
        + "','" + p.return_opd_date
        + "','" + p.borrow_opd_status
        + "','" + p.permissibly_borrow_opd
        + "','" + p.borrow_opd_cause
        + "','" + p.borrow_opd_to
        + "','" + p.borrow_opd_to_other
        + "','" + p.date_serv_opd
        + "','" + p.borrow_opdcard_staff_record
        + "','" + p.borrow_opdcard_record_date_time
        + "','" + p.borrow_opdcard_staff_update
        + "','" + p.borrow_opdcard_update_date_time
        + "','" + p.borrow_opdcard_staff_cancel
        + "','" + p.borrow_opdcard_cancel_date_time
        + "','" + p.borrow_opdcard_active
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int update(BorrowOpdCard o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        BorrowOpdCard p=o;
        String field =""
        + "', " + dbObj.patient_hn + "='" + p.patient_hn
        + "', " + dbObj.borrower_opd_prefix + "='" + p.borrower_opd_prefix
        + "', " + dbObj.borrower_opd_name + "='" + p.borrower_opd_name
        + "', " + dbObj.borrower_opd_lastname + "='" + p.borrower_opd_lastname
        + "', " + dbObj.borrow_opd_date + "='" + p.borrow_opd_date
        + "', " + dbObj.amount_date_opd + "='" + p.amount_date_opd
        + "', " + dbObj.return_opd_date + "='" + p.return_opd_date
        + "', " + dbObj.borrow_opd_status + "='" + p.borrow_opd_status
        + "', " + dbObj.permissibly_borrow_opd + "='" + p.permissibly_borrow_opd
        + "', " + dbObj.borrow_opd_cause + "='" + p.borrow_opd_cause
        + "', " + dbObj.borrow_opd_to + "='" + p.borrow_opd_to
        + "', " + dbObj.borrow_opd_to_other + "='" + p.borrow_opd_to_other
        + "', " + dbObj.date_serv_opd + "='" + p.date_serv_opd
        + "', " + dbObj.borrow_opdcard_staff_record + "='" + p.borrow_opdcard_staff_record
        + "', " + dbObj.borrow_opdcard_record_date_time + "='" + p.borrow_opdcard_record_date_time
        + "', " + dbObj.borrow_opdcard_staff_update + "='" + p.borrow_opdcard_staff_update
        + "', " + dbObj.borrow_opdcard_update_date_time + "='" + p.borrow_opdcard_update_date_time
        + "', " + dbObj.borrow_opdcard_staff_cancel + "='" + p.borrow_opdcard_staff_cancel
        + "', " + dbObj.borrow_opdcard_cancel_date_time + "='" + p.borrow_opdcard_cancel_date_time
        + "', " + dbObj.borrow_opdcard_active + "='" + p.borrow_opdcard_active
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public int delete(BorrowOpdCard o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAll() throws Exception
    {
        String sql="select * from " + dbObj.table ;
        sql = sql +  " order by "+ dbObj.borrow_opd_date;
        
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
        + " where " + dbObj.borrow_opd_date
        + " >= '" + datefrom + "' and " + dbObj.borrow_opd_date
        + " <= '" + dateto + "' and " + dbObj.patient_hn
        + " = '" + hn + "'"
        + " order by " + dbObj.borrow_opd_date;
        
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
        + " order by " + dbObj.borrow_opd_date;
        
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public BorrowOpdCard selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (BorrowOpdCard)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector eQuery(String sql) throws Exception
    {
        BorrowOpdCard p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new BorrowOpdCard();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.patient_hn = rs.getString(dbObj.patient_hn);
            p.borrower_opd_prefix = rs.getString(dbObj.borrower_opd_prefix);
            p.borrower_opd_name = rs.getString(dbObj.borrower_opd_name);
            p.borrower_opd_lastname = rs.getString(dbObj.borrower_opd_lastname);
            p.borrow_opd_date = rs.getString(dbObj.borrow_opd_date);
            p.amount_date_opd = rs.getString(dbObj.amount_date_opd);
            p.return_opd_date = rs.getString(dbObj.return_opd_date);
            p.borrow_opd_status = rs.getString(dbObj.borrow_opd_status);
            p.permissibly_borrow_opd = rs.getString(dbObj.permissibly_borrow_opd);
            p.borrow_opd_cause = rs.getString(dbObj.borrow_opd_cause);
            p.borrow_opd_to = rs.getString(dbObj.borrow_opd_to);
            p.borrow_opd_to_other = rs.getString(dbObj.borrow_opd_to_other);
            p.date_serv_opd = rs.getString(dbObj.date_serv_opd);
            p.borrow_opdcard_staff_record = rs.getString(dbObj.borrow_opdcard_staff_record);
            p.borrow_opdcard_record_date_time = rs.getString(dbObj.borrow_opdcard_record_date_time);
            p.borrow_opdcard_staff_update = rs.getString(dbObj.borrow_opdcard_staff_update);
            p.borrow_opdcard_update_date_time = rs.getString(dbObj.borrow_opdcard_update_date_time);
            p.borrow_opdcard_staff_cancel = rs.getString(dbObj.borrow_opdcard_staff_cancel);
            p.borrow_opdcard_cancel_date_time = rs.getString(dbObj.borrow_opdcard_cancel_date_time);
            p.borrow_opdcard_active = rs.getString(dbObj.borrow_opdcard_active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    
}
