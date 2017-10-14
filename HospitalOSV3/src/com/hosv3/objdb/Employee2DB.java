//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

//ย้ายไปไว้ใน Standard แล้วนะ henbe



package com.hosv3.objdb;
import com.hospital_os.usecase.connection.*;
//import com.hospital_os.utility.*;
//import com.hospital_os.object.*;

import com.hosv3.object.*;
import com.hospital_os.objdb.*;
import java.util.*;
//import java.sql.*;

/**
 * 
 * @deprecated ข้อมูลทั้งหมดย้ายไปอยู่ใน ResultLabDB แล้ว
 */
public class Employee2DB extends EmployeeDB
{
    public Employee2 dbObj;
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public Employee2DB(ConnectionInf db)
    {
        super(db);
        dbObj = new Employee2();
        initConfig();
    }
    //อย่าเพิ่งลบน่ะ
    public Employee2DB()
    {
        dbObj = new Employee2();
        initConfig();
    }   
    /**
     * 
     * ค้นหาผู้ใช้งานในจุดบริการที่ระบุมา จากตาราง b_service_point_doctor
     */
    public Vector selectXInSpid(String spid) throws Exception
    {
        String sql = "SELECT * FROM b_employee,b_service_point_doctor "
        + " where b_service_point_doctor.b_service_point_id = '"+ spid + "'"
        + " and b_employee.b_employee_id = b_service_point_doctor.service_point_doctor_staff"
        + " and employee_active = '1'";
        return eQuery(sql);
    }  
 /*   public boolean initConfig()
    {
        dbObj.table="b_employee";
        dbObj.pk_field="b_employee_id";
        dbObj.employee_id   ="employee_login";
        dbObj.password   ="employee_password";
        dbObj.fname   ="employee_firstname";
        dbObj.lname   ="employee_lastname";
        dbObj.employee_no   ="employee_number";
        dbObj.last_login   ="employee_last_login";
        dbObj.last_logout   ="employee_last_logout";
        dbObj.active   ="employee_active";
        dbObj.default_service_id   ="b_service_point_id";
        dbObj.level_id   ="f_employee_level_id";
        dbObj.rule_id   ="f_employee_rule_id";
        dbObj.authentication_id ="f_employee_authentication_id";
        dbObj.default_tab = "b_employee_default_tab";
        dbObj.warning_dx = "employee_warning_dx";
        return true;
    }
    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
  /*  public int insert(Employee2 o) throws Exception
    {
        String sql="";
        Employee2 p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.employee_id
        + " ,"	+ dbObj.password
        + " ,"	+ dbObj.fname
        + " ,"	+ dbObj.lname
        + " ,"	+ dbObj.employee_no
        + " ,"	+ dbObj.last_login
        + " ,"	+ dbObj.last_logout
        + " ,"	+ dbObj.active
        + " ,"	+ dbObj.default_service_id
        + " ,"	+ dbObj.level_id
        + " ,"	+ dbObj.rule_id
        + " ,"	+ dbObj.authentication_id
        + " ,"	+ dbObj.default_tab
        + " ,"	+ dbObj.warning_dx
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.employee_id
        + "','" + p.password
        + "','" + p.fname
        + "','" + p.lname
        + "','" + p.employee_no
        + "','" + p.last_login
        + "','" + p.last_logout
        + "','" + p.active
        + "','" + p.default_service_id
        + "','" + p.level_id
        + "','" + p.rule_id
        + "','" + p.authentication_id
        + "','" + p.default_tab
        + "','" + p.warning_dx        
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int update(Employee2 o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Employee2 p=o;
        String field =""
        + "', " + dbObj.employee_id + "='" + p.employee_id
        + "', " + dbObj.password + "='" + p.password
        + "', " + dbObj.fname + "='" + p.fname
        + "', " + dbObj.lname + "='" + p.lname
        + "', " + dbObj.employee_no + "='" + p.employee_no
        + "', " + dbObj.last_login + "='" + p.last_login
        + "', " + dbObj.last_logout + "='" + p.last_logout
        + "', " + dbObj.active + "='" + p.active
        + "', " + dbObj.default_service_id + "='" + p.default_service_id
        + "', " + dbObj.level_id + "='" + p.level_id
        + "', " + dbObj.rule_id + "='" + p.rule_id
        + "', " + dbObj.authentication_id + "='" + p.authentication_id
        + "', " + dbObj.default_tab + "='" + p.default_tab
        + "', " + dbObj.warning_dx + "='" + p.warning_dx        
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public int delete(Employee2 o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }

    //////////////////////////////////////////////////////////////////////////////
    public Employee2 select2ByPK(String pk) throws Exception
    {    String sql="select * from " + dbObj.table
         + " where " + dbObj.pk_field
         + " = '" + pk + "'" +
         " AND " + dbObj.active + "='" + Active.isEnable() + "'";
         
         Vector v=eQuery(sql);
         if(v.size()==0)
             return null;
         else
             return (Employee2)v.get(0);
    }
   
    //////////////////////////////////////////////////////////////////////////////
    public Employee2 select2ByUsername(String pk) throws Exception
    {    String sql="select * from " + dbObj.table
         + " where " + dbObj.employee_id
         + " = '" + pk + "'" +
         " AND " + dbObj.active + "='" + Active.isEnable() + "'";
         
         Vector v=eQuery(sql);
         if(v.size()==0)
             return null;
         else
             return (Employee2)v.get(0);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectDoctor(String pk) throws Exception
    {
        
        Vector vc = new Vector();
        String sql="select * from " + dbObj.table
        + " where " + dbObj.authentication_id + " = '" + pk
        + "' AND " + dbObj.active + "='" + Active.isEnable()
        + "' order by " + dbObj.fname;
        
        return eQuery(sql);
    }
    
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectNurse(String pk) throws Exception
    {
        
        Vector vc = new Vector();
        String sql="select * from " + dbObj.table
        + " where " + dbObj.level_id
        + " = '" + pk + "'";
        
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
        
    }
    
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectNurseAndDoctorAll() throws Exception
    {
        
        Vector vc = new Vector();
        String sql="select * from " + dbObj.table
        + " where (" + dbObj.authentication_id
        + " = '2' or " + dbObj.authentication_id
        + " = '3' ) and ("+ dbObj.active
        + " = '1' )";
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
        
    }
    
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectNurseAndDoctorByPk(String pk) throws Exception
    {
        Vector vc = new Vector();
        String sql="select * from " + dbObj.table
        + " where (" + dbObj.fname
        + " like '%" + pk + "%' or " + dbObj.lname
        + " like '%" + pk + "%' ) and ("+ dbObj.authentication_id
        + " = '2'  or " + dbObj.authentication_id
        + " = '3' ) and (" + dbObj.active
        + " = '1' )";
        
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
        
    }
    
    //////////////////////////////////////////////////////////////////////////////
    
    public int updatePassword(Employee2 o) throws Exception
    {
        String sql="update " + dbObj.table + " set "
        + dbObj.password + " = '" + o.password + "' where "
        + dbObj.pk_field + " = '" + o.getObjectId() + "'";
        
        return theConnectionInf.eUpdate(sql);
        
    }
    
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll(String pk,String active, String rule) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where ";
        if(pk.trim().length() !=0)
        {
            sql = sql +"(" +  dbObj.employee_id
            + " like '%" + pk + "%'" + " or "
            + dbObj.lname + " like '%" + pk + "%'" + " or "
            + dbObj.fname+ " like '%" + pk + "%'" + ") and ";
        }
        if(rule != null)
        {   sql = sql + dbObj.rule_id + " = '" + rule + "'" + " and ";
        }
        
        sql = sql + dbObj.active + " = '" + active + "'" + "order by "
        + dbObj.employee_id ;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }

    
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAllByName(String pk,String active) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where ";
        if(pk.trim().length() !=0)
        {
            sql = sql +"(" +  dbObj.employee_id
            + " like '%" + pk + "%'" + " or "
            + dbObj.lname + " like '%" + pk + "%'" + " or "
            + dbObj.fname+ " like '%" + pk + "%'" + ") and ";
        }
        sql = sql + dbObj.active + " = '" + active + "'" + "order by "
        + dbObj.employee_id ;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }

    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAllByName() throws Exception
    {
        String sql="select * from " + dbObj.table ;
        sql = sql +  " order by "+ dbObj.fname;
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectIdnameEmployeeAll() throws Exception
    {
        String sql = "SELECT " + dbObj.pk_field + "," + dbObj.fname + "," + dbObj.lname + " " +
        " FROM " + dbObj.table + " " +
        " WHERE " + dbObj.active + "='" + Active.isEnable() + "'";
        
        Vector v=eQuery(sql);
        if(v.size()== 0)
            return null;
        else
            return v;
    }
  
    //////////////////////////////////////////////////////////////////////////////
    public Vector eQuery(String sql) throws Exception
    {
        Employee2 p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Employee2();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.employee_id = rs.getString(dbObj.employee_id);
            p.password = rs.getString(dbObj.password);
            p.fname = rs.getString(dbObj.fname);
            p.lname = rs.getString(dbObj.lname);
            p.employee_no = rs.getString(dbObj.employee_no);
            p.last_login = rs.getString(dbObj.last_login);
            p.last_logout = rs.getString(dbObj.last_logout);
            p.active = rs.getString(dbObj.active);
            p.default_service_id = rs.getString(dbObj.default_service_id);
            p.level_id = rs.getString(dbObj.level_id);
            p.rule_id = rs.getString(dbObj.rule_id);
            p.authentication_id = rs.getString(dbObj.authentication_id);
            p.default_tab = rs.getString(dbObj.default_tab);
            p.warning_dx = rs.getString(dbObj.warning_dx);
            list.add(p);
        }
        rs.close();
        return list;
    }
*/    //////////////////////////////////////////////////////////////////////////////
}
