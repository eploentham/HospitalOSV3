/*
 * SpecialQueryBorrowFilmXrayDB.java
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
public class SpecialQueryBorrowFilmXrayDB {
    
    public ConnectionInf theConnectionInf;
    private SpecialQueryBorrowFilmXray dbObj ;
    private String table1 = "t_patient";
    private String table2 = "t_borrow_film_xray";
    /** Creates a new instance of SpecialQueryBorrowFilmXrayDB */
    public SpecialQueryBorrowFilmXrayDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new SpecialQueryBorrowFilmXray();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.patient_hn = "patient_hn";
        dbObj.patient_firstname = "patient_firstname";
        dbObj.patient_lastname = "patient_lastname";
        dbObj.patient_xn = "patient_xn";
        dbObj.patient_prefix = "f_patient_prefix_id";
        dbObj.t_borrow_film_xray_id = "t_borrow_film_xray_id";
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
        dbObj.borrow_active = "borrow_active";
        return true;
    }
    
    public Vector queryDataOrderbyDate(boolean all_period,String datefrom, String dateto,String hn, String xn,String active) throws Exception
    {
        String sql = "SELECT " +
        "t_patient.patient_firstname, " +
        "t_patient.patient_lastname, " +
        "t_patient.f_patient_prefix_id, " +
        "t_borrow_film_xray.t_borrow_film_xray_id, " +
        "t_borrow_film_xray.patient_hn, " +
        "t_borrow_film_xray.patient_xn, " +
        "t_borrow_film_xray.borrower_prefix, " +
        "t_borrow_film_xray.borrower_name, " +
        "t_borrow_film_xray.borrower_lastname, " +
        "t_borrow_film_xray.borrow_film_date, " +
        "t_borrow_film_xray.amount_date, " +
        "t_borrow_film_xray.return_film_date, " +
        "t_borrow_film_xray.borrow_status, " +
        "t_borrow_film_xray.permissibly_borrow, " +
        "t_borrow_film_xray.borrow_cause, " +
        "t_borrow_film_xray.borrow_to, " +
        "t_borrow_film_xray.borrow_active " +
        "FROM (t_borrow_film_xray INNER JOIN t_patient ON t_borrow_film_xray.patient_hn = t_patient.patient_hn)" +
        " WHERE true " ;
        //ถ้า active เท่ากับ 1 ให้ค้นรายการที่ใช้งานอยู่
        if(active != null && !active.equals("") && active.equals("1"))
        {
            sql = sql + "AND t_borrow_film_xray.borrow_active = '" + active + "' ";
        }
        //ถ้า HN ไม่เท่ากับค่าว่าง ให้ค้นตาม HN
        if(hn != null && !hn.equals(""))
        {
            sql = sql + "AND t_borrow_film_xray.patient_hn like '" + hn + "' ";
            if(!all_period)
            {   
                sql = sql + 
                " AND t_borrow_film_xray.borrow_film_date >= '" + datefrom + "'" +
                " AND t_borrow_film_xray.borrow_film_date <= '" + dateto + "' ";
            }
            sql = sql + " order by "  +  dbObj.borrow_film_date  + " , " +dbObj.patient_hn;
        }
        //ถ้า HN เท่ากับค่าว่าง และ XN ไม่เท่ากับค่าว่าง ให้ค้นตาม XN
        else if((hn == null && hn.equals("")) && (xn != null && !xn.equals("")))
        {
            sql = sql +  "AND t_borrow_film_xray.patient_xn like '" + xn + "' ";
            if(!all_period)
            {   
                sql = sql + 
                " AND t_borrow_film_xray.borrow_film_date >= '" + datefrom + "'" +
                " AND t_borrow_film_xray.borrow_film_date <= '" + dateto + "' ";
            }
            sql = sql + " order by "  +  dbObj.borrow_film_date  + " , " +dbObj.patient_xn;
        }
        //ถ้า HN เท่ากับค่าว่าง และ XN เท่ากับค่าว่าง ให้ค้นทั้งหมด
        else if((hn == null || hn.equals("")) && (xn == null || xn.equals("")))
        {
            sql = sql +  "AND t_borrow_film_xray.patient_hn like '%' ";
            if(!all_period)
            {   
                sql = sql + 
                " AND t_borrow_film_xray.borrow_film_date >= '" + datefrom + "'" +
                " AND t_borrow_film_xray.borrow_film_date <= '" + dateto + "' ";
            }
            sql = sql + " order by "  +  dbObj.borrow_film_date  + " , " +dbObj.patient_hn;
        }
        
        Vector vc =  eQuery(sql);
        if(vc.size() ==0)
            return  null;
        else
            return vc;
    }
    
    
    private Vector eQuery(String sql) throws Exception
    {
        SpecialQueryBorrowFilmXray p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        int i = 0;
        while(rs.next())
        {
            p = new SpecialQueryBorrowFilmXray();
            p.patient_firstname = rs.getString(dbObj.patient_firstname);
            p.patient_lastname = rs.getString(dbObj.patient_lastname);
            p.patient_xn = rs.getString(dbObj.patient_xn);
            p.patient_prefix = rs.getString(dbObj.patient_prefix);
            p.patient_hn = rs.getString(dbObj.patient_hn);
            p.t_borrow_film_xray_id = rs.getString(dbObj.t_borrow_film_xray_id);
            p.borrower_prefix  = rs.getString(dbObj.borrower_prefix);
            p.borrower_name = rs.getString(dbObj.borrower_name);
            p.borrower_lastname= rs.getString(dbObj.borrower_lastname);
            p.borrow_film_date= rs.getString(dbObj.borrow_film_date);
            p.amount_date= rs.getString(dbObj.amount_date);
            p.return_film_date= rs.getString(dbObj.return_film_date);
            p.borrow_status = rs.getString(dbObj.borrow_status);
            p.permissibly_borrow= rs.getString(dbObj.permissibly_borrow);
            p.borrow_cause= rs.getString(dbObj.borrow_cause);
            p.borrow_to= rs.getString(dbObj.borrow_to);
            list.add(p);
            p = null;
            
        }
        rs.close();
        return list;
    }
    
}
