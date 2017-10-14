/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java
*/
package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
public class PatientPaymentDB
{
    public ConnectionInf theConnectionInf;
    public PatientPayment dbObj;
    final public String idtable = "208";/*"184";
*/
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public PatientPaymentDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PatientPayment();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="t_patient_payment";
        dbObj.pk_field="t_patient_payment_id";
        dbObj.patient_id  ="t_patient_id";
        dbObj.plan_kid   ="b_contact_plans_id";
        dbObj.contract_kid   ="b_contact_id";
        dbObj.card_id   ="patient_payment_card_number";
        dbObj.card_ins_date   ="patient_payment_card_issue_date";
        dbObj.card_exp_date   ="patient_payment_card_expire_date";
        dbObj.hosp_main   ="patient_payment_main_hospital";
        dbObj.hosp_sub   ="patient_payment_sub_hospital";
        dbObj.priority   ="patient_payment_priority";
        dbObj.money_limit ="patient_payment_money_limit";
        dbObj.record_date = "patient_payment_record_date";
        dbObj.family_id = "t_health_family_id";
        dbObj.checkplan_date = "checkplan_date";
        /*
        dbObj.table = "patient_payment";
        dbObj.pk_field = "patient_payment_id";
        dbObj.patient_id = "patient_id";
        dbObj.plan_kid = "plan_kid";
        dbObj.contract_kid = "contract_kid";
        dbObj.card_id = "card_no";
        dbObj.card_ins_date = "card_ins_date";
        dbObj.card_exp_date = "card_exp_date";
        dbObj.hosp_main = "hosp_main";
        dbObj.hosp_sub = "hosp_sub";
        dbObj.priority = "priority";
        dbObj.money_limit = "money_limit";
         */
        return true;
    }
    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(PatientPayment o) throws Exception
    {
        String sql="";
        PatientPayment p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.patient_id
        + " ,"	+ dbObj.plan_kid
        + " ,"	+ dbObj.contract_kid
        + " ,"	+ dbObj.card_id
        + " ,"	+ dbObj.card_ins_date
        + " ,"	+ dbObj.card_exp_date
        + " ,"	+ dbObj.hosp_main
        + " ,"	+ dbObj.hosp_sub
        + " ,"	+ dbObj.priority
        + " ,"	+ dbObj.money_limit
        + " ,"	+ dbObj.record_date
        + " ,"	+ dbObj.family_id
        + " ,"	+ dbObj.checkplan_date
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.patient_id
        + "','" + p.plan_kid
        + "','" + p.contract_kid
        + "','" + p.card_id
        + "','" + p.card_ins_date
        + "','" + p.card_exp_date
        + "','" + p.hosp_main
        + "','" + p.hosp_sub
        + "','" + p.priority
        + "','" + p.money_limit
        + "','" + p.record_date
        + "','" + p.family_id
        + "','" + p.checkplan_date
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int update(PatientPayment o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        PatientPayment p=o;
        String field =""
        + "', " + dbObj.patient_id + "='" + p.patient_id
        + "', " + dbObj.plan_kid + "='" + p.plan_kid
        + "', " + dbObj.contract_kid + "='" + p.contract_kid
        + "', " + dbObj.card_id + "='" + p.card_id
        + "', " + dbObj.card_ins_date + "='" + p.card_ins_date
        + "', " + dbObj.card_exp_date + "='" + p.card_exp_date
        + "', " + dbObj.hosp_main + "='" + p.hosp_main
        + "', " + dbObj.hosp_sub + "='" + p.hosp_sub
        + "', " + dbObj.priority + "='" + p.priority
        + "', " + dbObj.money_limit + "='" + p.money_limit
        + "', " + dbObj.record_date + "='" + p.record_date
        + "', " + dbObj.family_id + "='" + p.family_id
        + "', " + dbObj.checkplan_date + "='" + p.checkplan_date
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    /*////////////////////////////////////////////////////////////////////////////////////*/
    public int updatePatientByPatient(String old_id,String new_id)throws Exception
    {   
        String sql = "Update " + dbObj.table + " set "
        + dbObj.patient_id + " = '" + new_id + "',"
        + dbObj.money_limit + " = "+dbObj.money_limit+"||'-'||'" + old_id + "'"
        + " where "
        + dbObj.patient_id + " = '" + old_id +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(PatientPayment o) throws Exception
    {
        return deleteByPK(o.getObjectId());
    }
    public int deleteByPK(String id) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + id +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public PatientPayment selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (PatientPayment)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector selectByPatientId(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.patient_id
        + " = '" + pk + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector selectAll( ) throws Exception
    {
        String sql="select * from " + dbObj.table;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector eQuery(String sql) throws Exception
    {
        PatientPayment p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PatientPayment();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.patient_id = rs.getString(dbObj.patient_id);
            p.plan_kid = rs.getString(dbObj.plan_kid);
            p.contract_kid = rs.getString(dbObj.contract_kid);
            p.card_id = rs.getString(dbObj.card_id);
            p.card_ins_date = rs.getString(dbObj.card_ins_date);
            p.card_exp_date = rs.getString(dbObj.card_exp_date);
            p.hosp_main = rs.getString(dbObj.hosp_main);
            p.hosp_sub = rs.getString(dbObj.hosp_sub);
            p.priority = rs.getString(dbObj.priority);
            p.money_limit = rs.getString(dbObj.money_limit);
            p.record_date = rs.getString(dbObj.record_date);
            p.family_id = rs.getString(dbObj.family_id);
            p.checkplan_date = rs.getString(dbObj.checkplan_date);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public Vector selectByFamilyId(String family_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.family_id
        + " = '" + family_id + "' order by "+dbObj.priority;
        return eQuery(sql);
    }
/////////////////////////////////////////////////////////////////////////////    
    public int updateFidByFid(String family_id,String family_from) throws Exception
    {
        String sql="update " + dbObj.table + " set " 
                + dbObj.family_id + "='" + family_id
                + "' where " + dbObj.family_id + "='" + family_from +"'";
        return theConnectionInf.eUpdate(sql);
    }  
    
/////////////////////////////////////////////////////////////////////////////    
    public int updateFidByPtid(String family_id,String patient_id) throws Exception
    {
        String sql="update " + dbObj.table + " set " 
                + dbObj.family_id + "='" + family_id
                + "' where " + dbObj.patient_id + "='" + patient_id +"'";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    } 
/////////////////////////////////////////////////////////////////////////////    
    public int updatePtidByFid(String patient_id,String family_id) throws Exception
    {
        String sql="update " + dbObj.table + " set " 
                + dbObj.patient_id + "='" + patient_id
                + "' where " + dbObj.family_id + "='" + family_id +"'";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }  
}
