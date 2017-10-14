/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java
*/
package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
public class DiagIcd9DB
{
    public ConnectionInf theConnectionInf;
    public DiagIcd9 dbObj,theDiagIcd9Temp;
    final public String idtable = "141";/*"134";*/
    String sql ="";
    ResultSet rs;
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public DiagIcd9DB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new DiagIcd9();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="t_diag_icd9";
        dbObj.pk_field="t_diag_icd9_id";
        dbObj.vn   ="diag_icd9_vn";
        dbObj.icd9_code   ="diag_icd9_icd9_number";
        dbObj.time_in   ="diag_icd9_start_time";
        dbObj.time_out   ="diag_icd9_finish_time";
        dbObj.type   ="f_diagnosis_operation_type_id";
        dbObj.dischange_note   ="diag_icd9_notice";
        dbObj.doctor_kid   ="diag_icd9_staff_doctor";
        dbObj.clinic_kid   ="b_visit_clinic_id";
        dbObj.nurse_kid ="diag_icd9_staff_participate";
        dbObj.diag_icd9_staff_record ="diag_icd9_staff_record";
        dbObj.diag_icd9_record_date_time ="diag_icd9_record_date_time";
        dbObj.diag_icd9_staff_update ="diag_icd9_staff_update";
        dbObj.diag_icd9_update_date_time ="diag_icd9_update_date_time";
        dbObj.diag_icd9_staff_cancel ="diag_icd9_staff_cancel";
        dbObj.diag_icd9_cancel_date_time ="diag_icd9_cancel_date_time";
        dbObj.diag_icd9_active       ="diag_icd9_active";
        dbObj.primary_report = "primary_report";
        return true;
    }
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(DiagIcd9 o) throws Exception
    {
        sql="";
        DiagIcd9 p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.vn
        + " ,"	+ dbObj.icd9_code
        + " ,"	+ dbObj.time_in
        + " ,"	+ dbObj.time_out
        + " ,"	+ dbObj.type
        + " ,"	+ dbObj.dischange_note
        + " ,"	+ dbObj.doctor_kid
        + " ,"	+ dbObj.clinic_kid
        + " ,"	+ dbObj.nurse_kid
        + " ,"	+ dbObj.diag_icd9_staff_record
        + " ,"	+ dbObj.diag_icd9_record_date_time
        + " ,"	+ dbObj.diag_icd9_staff_update
        + " ,"	+ dbObj.diag_icd9_update_date_time
        + " ,"	+ dbObj.diag_icd9_staff_cancel
        + " ,"	+ dbObj.diag_icd9_cancel_date_time
        + " ,"	+ dbObj.diag_icd9_active   
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.vn
        + "','" + p.icd9_code
        + "','" + p.time_in
        + "','" + p.time_out
        + "','" + p.type
        + "','" + p.dischange_note
        + "','" + p.doctor_kid
        + "','" + p.clinic_kid
        + "','" + p.nurse_kid
        + "','" + p.diag_icd9_staff_record
        + "','" + p.diag_icd9_record_date_time
        + "','" + p.diag_icd9_staff_update
        + "','" + p.diag_icd9_update_date_time
        + "','" + p.diag_icd9_staff_cancel
        + "','" + p.diag_icd9_cancel_date_time
        + "','" + p.diag_icd9_active   
                + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        
        return theConnectionInf.eUpdate(sql);
        
    }
    public int update(DiagIcd9 o) throws Exception
    {
        sql="update " + dbObj.table + " set ";
        DiagIcd9 p=o;
        String field =""
        + "', " + dbObj.vn + "='" + p.vn
        + "', " + dbObj.icd9_code + "='" + p.icd9_code
        + "', " + dbObj.time_in + "='" + p.time_in
        + "', " + dbObj.time_out + "='" + p.time_out
        + "', " + dbObj.type + "='" + p.type
        + "', " + dbObj.dischange_note + "='" + p.dischange_note
        + "', " + dbObj.doctor_kid + "='" + p.doctor_kid
        + "', " + dbObj.clinic_kid + "='" + p.clinic_kid
        + "', " + dbObj.nurse_kid + "='" + p.nurse_kid
        + "', " + dbObj.diag_icd9_staff_record + "='" + p.diag_icd9_staff_record
        + "', " + dbObj.diag_icd9_record_date_time + "='" + p.diag_icd9_record_date_time
        + "', " + dbObj.diag_icd9_staff_update + "='" + p.diag_icd9_staff_update
        + "', " + dbObj.diag_icd9_update_date_time + "='" + p.diag_icd9_update_date_time
        + "', " + dbObj.diag_icd9_staff_cancel + "='" + p.diag_icd9_staff_cancel
        + "', " + dbObj.diag_icd9_cancel_date_time + "='" + p.diag_icd9_cancel_date_time
        + "', " + dbObj.diag_icd9_active    + "='" + p.diag_icd9_active
                + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public int updateActive(DiagIcd9 o) throws Exception
    {    
        sql = "UPDATE " + dbObj.table + 
              " SET " + dbObj.diag_icd9_active + " ='"  + o.diag_icd9_active + "'" +
               "," + dbObj.diag_icd9_cancel_date_time + " ='"  + o.diag_icd9_cancel_date_time + "'" +  
               "," + dbObj.diag_icd9_staff_cancel + " ='"  + o.diag_icd9_staff_cancel + "'" +
               " WHERE " + dbObj.pk_field + " ='" + o.getObjectId() + "'";
                
     
        /*sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'"; */
                
        return theConnectionInf.eUpdate(sql);
    }
        
    
    public int delete(DiagIcd9 o) throws Exception
    {
     
        sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'"; 
                
        return theConnectionInf.eUpdate(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public DiagIcd9 selectByPK(String pk) throws Exception
    {
        sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field + " = '" + pk + "'" +
        " AND " + dbObj.diag_icd9_active + " ='"+ Active.isEnable() +"'" +
        " order by " + dbObj.type;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (DiagIcd9)v.get(0);
    }
    /**
     * @deprecated henbe cal by selectByVid////////////////////////////////////////////////////////////////////////////
*/
    public Vector selectByVN(String pk) throws Exception
    {
        return selectByVid(pk,"1");
    }
    public Vector selectByVid(String vid,String active) throws Exception
    {
        sql="select * from " + dbObj.table +
                " where " + dbObj.vn + " = '" + vid + "' "+
                " AND " + dbObj.diag_icd9_active + " ='"+ active +"'"+
                " order by " + dbObj.type ;
        return eQuery(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector selectAll() throws Exception
    {
        sql="select * from " + dbObj.table;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    /**
     *@Author: amp
     *@date: 04/01/2550
     *@see: ตรวจสอบว่ามีการลงรหัส icd-9 แล้วยัง
     */
    public DiagIcd9 selectIcd9(String icd9_code,String visit_id) throws Exception
    {
        sql="select * from " + dbObj.table +
                " where " + dbObj.vn + " = '" + visit_id + "' " +
                " AND " + dbObj.icd9_code + " ='" + icd9_code + "' " +
                " AND " + dbObj.diag_icd9_active + " = '1'";
        
        Vector v = eQuery(sql);
        if(v.size() == 0)
            return null;
        else
            return (DiagIcd9)v.get(0);
    }
    
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector eQuery(String sql) throws Exception
    {
        
        Vector list = new Vector();
        rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            theDiagIcd9Temp = new DiagIcd9();
            theDiagIcd9Temp.setObjectId(rs.getString(dbObj.pk_field));
            theDiagIcd9Temp.vn = rs.getString(dbObj.vn);
            theDiagIcd9Temp.icd9_code = rs.getString(dbObj.icd9_code);
            theDiagIcd9Temp.time_in = rs.getString(dbObj.time_in);
            theDiagIcd9Temp.time_out = rs.getString(dbObj.time_out);
            theDiagIcd9Temp.type = rs.getString(dbObj.type);
            theDiagIcd9Temp.dischange_note = rs.getString(dbObj.dischange_note);
            theDiagIcd9Temp.doctor_kid = rs.getString(dbObj.doctor_kid);
            theDiagIcd9Temp.clinic_kid = rs.getString(dbObj.clinic_kid);
            theDiagIcd9Temp.nurse_kid = rs.getString(dbObj.nurse_kid);
            theDiagIcd9Temp.diag_icd9_staff_record = rs.getString(dbObj.diag_icd9_staff_record);
            theDiagIcd9Temp.diag_icd9_record_date_time = rs.getString(dbObj.diag_icd9_record_date_time);
            theDiagIcd9Temp.diag_icd9_staff_update = rs.getString(dbObj.diag_icd9_staff_update);
            theDiagIcd9Temp.diag_icd9_update_date_time = rs.getString(dbObj.diag_icd9_update_date_time);
            theDiagIcd9Temp.diag_icd9_staff_cancel = rs.getString(dbObj.diag_icd9_staff_cancel);
            theDiagIcd9Temp.diag_icd9_cancel_date_time = rs.getString(dbObj.diag_icd9_cancel_date_time);
            theDiagIcd9Temp.diag_icd9_active    = rs.getString(dbObj.diag_icd9_active);
            list.add(theDiagIcd9Temp);
            theDiagIcd9Temp = null;
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
}
