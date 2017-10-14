/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java
*/
package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
public class PaymentDB
{
    public ConnectionInf theConnectionInf;
    public Payment dbObj;
    final public String idtable = "210";/*"186";*/
    Vector list = new Vector();
    String sql="";
    Payment PaymentTemp;
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public PaymentDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Payment();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="t_visit_payment";
        dbObj.pk_field="t_visit_payment_id";
        dbObj.visit_id   ="t_visit_id";
        dbObj.plan_kid   ="b_contract_plans_id";
        dbObj.contract_kid   ="b_contract_id";
        dbObj.card_id   ="visit_payment_card_number";
        dbObj.card_ins_date   ="visit_payment_card_issue_date";
        dbObj.card_exp_date   ="visit_payment_card_expire_date";
        dbObj.hosp_main   ="visit_payment_main_hospital";
        dbObj.hosp_sub   ="visit_payment_sub_hospital";
        dbObj.priority   ="visit_payment_priority";
        dbObj.money_limit   ="visit_payment_money_limit";
        dbObj.use_money_limit="visit_payment_used_money_limit";
        dbObj.visit_payment_staff_record ="visit_payment_staff_record";
        dbObj.visit_payment_record_date_time ="visit_payment_record_date_time";
        dbObj.visit_payment_staff_update ="visit_payment_staff_update";
        dbObj.visit_payment_update_date_time ="visit_payment_update_date_time";
        dbObj.visit_payment_staff_cancel ="visit_payment_staff_cancel";
        dbObj.visit_payment_cancel_date_time ="visit_payment_cancel_date_time";
        dbObj.visit_payment_active ="visit_payment_active";
        /*
        dbObj.table = "payment";
        dbObj.pk_field = "payment_id";
        dbObj.visit_id = "visit_id";
        dbObj.plan_kid = "plan_kid";
        dbObj.contract_kid = "contract_kid";
        dbObj.card_id = "card_id";
        dbObj.card_ins_date = "card_ins_date";
        dbObj.card_exp_date = "card_exp_date";
        dbObj.hosp_main = "hosp_main";
        dbObj.hosp_sub = "hosp_sub";
        dbObj.priority = "priority";
        dbObj.money_limit = "money_limit";
        dbObj.use_money_limit = "use_money_limit";
         */
        return true;
    }
    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(Payment o) throws Exception
    {
        sql="";
        PaymentTemp=o;
        PaymentTemp.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.visit_id
        + " ,"	+ dbObj.plan_kid
        + " ,"	+ dbObj.contract_kid
        + " ,"	+ dbObj.card_id
        + " ,"	+ dbObj.card_ins_date
        + " ,"	+ dbObj.card_exp_date
        + " ,"	+ dbObj.hosp_main
        + " ,"	+ dbObj.hosp_sub
        + " ,"	+ dbObj.priority
        + " ,"	+ dbObj.money_limit
        + " ,"	+ dbObj.use_money_limit
        + " ,"	+ dbObj.visit_payment_staff_record
        + " ,"	+ dbObj.visit_payment_record_date_time
        + " ,"	+ dbObj.visit_payment_staff_update
        + " ,"	+ dbObj.visit_payment_update_date_time
        + " ,"	+ dbObj.visit_payment_staff_cancel
        + " ,"	+ dbObj.visit_payment_cancel_date_time
        + " ,"	+ dbObj.visit_payment_active
        + " ) values ('"
        + PaymentTemp.getObjectId()
        + "','" + PaymentTemp.visit_id
        + "','" + PaymentTemp.plan_kid
        + "','" + PaymentTemp.contract_kid
        + "','" + PaymentTemp.card_id
        + "','" + PaymentTemp.card_ins_date
        + "','" + PaymentTemp.card_exp_date
        + "','" + PaymentTemp.hosp_main
        + "','" + PaymentTemp.hosp_sub
        + "','" + PaymentTemp.priority
        + "','" + PaymentTemp.money_limit
        + "','" + PaymentTemp.use_money_limit
        + "','" + PaymentTemp.visit_payment_staff_record
        + "','" + PaymentTemp.visit_payment_record_date_time
        + "','" + PaymentTemp.visit_payment_staff_update
        + "','" + PaymentTemp.visit_payment_update_date_time
        + "','" + PaymentTemp.visit_payment_staff_cancel
        + "','" + PaymentTemp.visit_payment_cancel_date_time
        + "','" + PaymentTemp.visit_payment_active
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int update(Payment o) throws Exception
    {
        sql="update " + dbObj.table + " set ";
        PaymentTemp=o;
        String field =""
        + "', " + dbObj.visit_id + "='" + PaymentTemp.visit_id
        + "', " + dbObj.plan_kid + "='" + PaymentTemp.plan_kid
        + "', " + dbObj.contract_kid + "='" + PaymentTemp.contract_kid
        + "', " + dbObj.card_id + "='" + PaymentTemp.card_id
        + "', " + dbObj.card_ins_date + "='" + PaymentTemp.card_ins_date
        + "', " + dbObj.card_exp_date + "='" + PaymentTemp.card_exp_date
        + "', " + dbObj.hosp_main + "='" + PaymentTemp.hosp_main
        + "', " + dbObj.hosp_sub + "='" + PaymentTemp.hosp_sub
        + "', " + dbObj.priority + "='" + PaymentTemp.priority
        + "', " + dbObj.money_limit + "='" + PaymentTemp.money_limit
        + "', " + dbObj.use_money_limit + "='" + PaymentTemp.use_money_limit
        + "', " + dbObj.visit_payment_staff_record + "='" + PaymentTemp.visit_payment_staff_record
        + "', " + dbObj.visit_payment_record_date_time + "='" + PaymentTemp.visit_payment_record_date_time
        + "', " + dbObj.visit_payment_staff_update + "='" + PaymentTemp.visit_payment_staff_update
        + "', " + dbObj.visit_payment_update_date_time + "='" + PaymentTemp.visit_payment_update_date_time
        + "', " + dbObj.visit_payment_staff_cancel + "='" + PaymentTemp.visit_payment_staff_cancel
        + "', " + dbObj.visit_payment_cancel_date_time + "='" + PaymentTemp.visit_payment_cancel_date_time
        + "', " + dbObj.visit_payment_active + "='" + PaymentTemp.visit_payment_active
        + "' where " + dbObj.pk_field + "='" + PaymentTemp.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        
        return theConnectionInf.eUpdate(sql);
    }
    public int updateMoneyLimit(Payment o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        PaymentTemp=o;
        String field =""
        + "', " + dbObj.money_limit + "='" + PaymentTemp.money_limit
        + "', " + dbObj.use_money_limit + "='" + PaymentTemp.use_money_limit
        + "' where " + dbObj.pk_field + "='" + PaymentTemp.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public int delete(Payment o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Payment selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        
        Vector v=eQuery(sql);
        sql = null;
        if(v.size()==0)
            return null;
        else
            return (Payment)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Payment selectMoneyLimitByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        
        Vector v=eQueryMoneyLimit(sql);
        sql = null;
        if(v.size()==0)
            return null;
        else
            return (Payment)v.get(0);
    }
    
    /**
     * ใช้ในการหาสิทธิการรักษาของผู้ป่วยที่รวมถึงรายการที่ถูกตกเลิกด้วย
     * @param visit_id เป็น รหัสหลักของตาราง t_visit
     * @return เป็น Vector ของ Object Payment ที่รวมรายการที่ยกเลิก
     */
    public Vector selectVisitPaymentCancelByVisitID(String visit_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.visit_id
        + " = '" + visit_id + "'" +
        " AND " + dbObj.visit_payment_active + "='0' " ;
        Vector v=eQuery(sql);
        sql =null;
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector selectByVisitId(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.visit_id
        + " = '" + pk + "'" +
        " AND " + dbObj.visit_payment_active + "='1' " + 
       " Order by " + dbObj.priority;
        return eQuery(sql);
    }
    
     /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectByPlanId(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.plan_kid + " = '" + pk + "'";
        return eQuery(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector selectAll() throws Exception
    {
        String sql="select * from " + dbObj.table;
        Vector v=eQuery(sql);
        sql = null;
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector eQuery(String sql) throws Exception
    {
        Payment p;
        list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Payment();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.visit_id = rs.getString(dbObj.visit_id);
            p.plan_kid = rs.getString(dbObj.plan_kid);
            p.contract_kid = rs.getString(dbObj.contract_kid);
            p.card_id = rs.getString(dbObj.card_id);
            p.card_ins_date = rs.getString(dbObj.card_ins_date);
            p.card_exp_date = rs.getString(dbObj.card_exp_date);
            p.hosp_main = rs.getString(dbObj.hosp_main);
            p.hosp_sub = rs.getString(dbObj.hosp_sub);
            p.priority = rs.getString(dbObj.priority);
            p.money_limit = rs.getString(dbObj.money_limit);
            p.use_money_limit = rs.getString(dbObj.use_money_limit);
            p.visit_payment_staff_record = rs.getString(dbObj.visit_payment_staff_record);
            p.visit_payment_record_date_time = rs.getString(dbObj.visit_payment_record_date_time);
            p.visit_payment_staff_update = rs.getString(dbObj.visit_payment_staff_update);
            p.visit_payment_update_date_time = rs.getString(dbObj.visit_payment_update_date_time);
            p.visit_payment_staff_cancel = rs.getString(dbObj.visit_payment_staff_cancel);
            p.visit_payment_cancel_date_time = rs.getString(dbObj.visit_payment_cancel_date_time);
            p.visit_payment_active = rs.getString(dbObj.visit_payment_active);
            list.add(p);
            p = null;
        }
        rs.close();
        p = null;
        rs = null;
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector eQueryMoneyLimit(String sql) throws Exception
    {
        Payment p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Payment();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.money_limit = rs.getString(dbObj.money_limit);
            p.use_money_limit = rs.getString(dbObj.use_money_limit);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
