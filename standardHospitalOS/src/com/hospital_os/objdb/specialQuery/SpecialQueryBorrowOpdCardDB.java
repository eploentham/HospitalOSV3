/*
 * SpecialQueryBorrowOpdCardDB.java
 *
 * Created on 15 กุมภาพันธ์ 2549, 17:54 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.objdb.specialQuery;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.specialQuery.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author sumo
 */
public class SpecialQueryBorrowOpdCardDB {
    
    public ConnectionInf theConnectionInf;
    private SpecialQueryBorrowOpdCard dbObj ;
    private String table1 = "t_patient";
    private String table2 = "t_borrow_opdcard";
    /** Creates a new instance of SpecialQueryBorrowOpdCardDB */
    public SpecialQueryBorrowOpdCardDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new SpecialQueryBorrowOpdCard();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.patient_hn = "patient_hn";
        dbObj.patient_firstname = "patient_firstname";
        dbObj.patient_lastname = "patient_lastname";
        dbObj.patient_prefix = "f_patient_prefix_id";
        dbObj.t_borrow_opdcard_id = "t_borrow_opdcard_id";
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
        dbObj.borrow_opdcard_active = "borrow_opdcard_active";
        return true;
    }
    
    public Vector queryDataOrderbyDate(boolean all_period,String datefrom, String dateto,String hn,String active) throws Exception
    {
        String sql = "SELECT " +
        "t_patient.patient_firstname, " +
        "t_patient.patient_lastname, " +
        "t_patient.f_patient_prefix_id, " +
        "t_borrow_opdcard.t_borrow_opdcard_id, " +
        "t_borrow_opdcard.patient_hn, " +
        "t_borrow_opdcard.borrower_opd_prefix, " +
        "t_borrow_opdcard.borrower_opd_name, " +
        "t_borrow_opdcard.borrower_opd_lastname, " +
        "t_borrow_opdcard.borrow_opd_date, " +
        "t_borrow_opdcard.amount_date_opd, " +
        "t_borrow_opdcard.return_opd_date, " +
        "t_borrow_opdcard.borrow_opd_status, " +
        "t_borrow_opdcard.permissibly_borrow_opd, " +
        "t_borrow_opdcard.borrow_opd_cause, " +
        "t_borrow_opdcard.borrow_opd_to, " +
        "t_borrow_opdcard.borrow_opdcard_active " +
        "FROM (t_borrow_opdcard INNER JOIN t_patient ON t_borrow_opdcard.patient_hn = t_patient.patient_hn)" +
        " WHERE true " ;
        //ถ้า active เท่ากับ 1 ให้ค้นรายการที่ใช้งานอยู่
        if(active != null && !active.equals("") && active.equals("1"))
        {
            sql = sql + "AND t_borrow_opdcard.borrow_opdcard_active = '" + active + "' ";
        }
        if(hn != null && !hn.equals(""))
        {
            sql = sql +  "AND t_borrow_opdcard.patient_hn like '" + hn + "' ";
            if(!all_period)
            {   
                sql = sql + 
                " AND t_borrow_opdcard.borrow_opd_date >= '" + datefrom + "'" +
                " AND t_borrow_opdcard.borrow_opd_date <= '" + dateto + "' ";
            }
            sql = sql + " order by "  +  dbObj.borrow_opd_date  + " , " +dbObj.patient_hn;
        }
        else
        {
            sql = sql +  "AND t_borrow_opdcard.patient_hn like '%' ";
            if(!all_period)
            {   
                sql = sql + 
                " AND t_borrow_opdcard.borrow_opd_date >= '" + datefrom + "'" +
                " AND t_borrow_opdcard.borrow_opd_date <= '" + dateto + "' ";
            }
            sql = sql + " order by "  +  dbObj.borrow_opd_date  + " , " +dbObj.patient_hn;
        }
        
        Vector vc =  eQuery(sql);
        if(vc.size() ==0)
            return  null;
        else
            return vc;
    }
    
    
    private Vector eQuery(String sql) throws Exception
    {
        SpecialQueryBorrowOpdCard p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        int i = 0;
        while(rs.next())
        {
            p = new SpecialQueryBorrowOpdCard();
            p.patient_firstname = rs.getString(dbObj.patient_firstname);
            p.patient_lastname = rs.getString(dbObj.patient_lastname);
            p.patient_prefix = rs.getString(dbObj.patient_prefix);
            p.patient_hn = rs.getString(dbObj.patient_hn);
            p.t_borrow_opdcard_id = rs.getString(dbObj.t_borrow_opdcard_id);
            p.borrower_opd_prefix  = rs.getString(dbObj.borrower_opd_prefix);
            p.borrower_opd_name = rs.getString(dbObj.borrower_opd_name);
            p.borrower_opd_lastname= rs.getString(dbObj.borrower_opd_lastname);
            p.borrow_opd_date= rs.getString(dbObj.borrow_opd_date);
            p.amount_date_opd= rs.getString(dbObj.amount_date_opd);
            p.return_opd_date= rs.getString(dbObj.return_opd_date);
            p.borrow_opd_status = rs.getString(dbObj.borrow_opd_status);
            p.permissibly_borrow_opd= rs.getString(dbObj.permissibly_borrow_opd);
            p.borrow_opd_cause= rs.getString(dbObj.borrow_opd_cause);
            p.borrow_opd_to= rs.getString(dbObj.borrow_opd_to);
            list.add(p);
            p = null;
            
        }
        rs.close();
        return list;
    }
    
}
