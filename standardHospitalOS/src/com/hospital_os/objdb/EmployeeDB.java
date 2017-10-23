/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java*/
package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
public class EmployeeDB
{
    public ConnectionInf theConnectionInf;
    public Employee dbObj;
    
    final public String idtable = "157";/*"149";*/
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     * @author ekapop
    * 1.  60-10-23 เรื่อง ห้อง     Hospital OS เข้าใจว่า ไม่มีห้อง
    * Modify doc 6.
    */
    public EmployeeDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Employee();
        initConfig();
    }
    /*อย่าเพิ่งลบน่ะ*/
    public EmployeeDB()
    {
        theConnectionInf= null;
        dbObj = new Employee();
        initConfig();
    }
    public boolean initConfig()
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
        dbObj.warning_dx ="employee_warning_dx";  
        dbObj.default_tab = "b_employee_default_tab";
        dbObj.status_admission   ="status_admission";       //+1
        return true;
    }
    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(Employee o) throws Exception
    {
        Employee p=o;
        p.generateOID(idtable);
        StringBuffer sql = new StringBuffer("insert into " ).append( dbObj.table ).append( " ("
        ).append( dbObj.pk_field
        ).append( " ,"	).append( dbObj.employee_id
        ).append( " ,"	).append( dbObj.password
        ).append( " ,"	).append( dbObj.fname
        ).append( " ,"	).append( dbObj.lname
        ).append( " ,"	).append( dbObj.employee_no
        ).append( " ,"	).append( dbObj.last_login
        ).append( " ,"	).append( dbObj.last_logout
        ).append( " ,"	).append( dbObj.active
        ).append( " ,"	).append( dbObj.default_service_id
        ).append( " ,"	).append( dbObj.level_id
        ).append( " ,"	).append( dbObj.rule_id
        ).append( " ,"	).append( dbObj.authentication_id
        ).append( " ,"	).append( dbObj.warning_dx
        ).append( " ,"	).append( dbObj.default_tab
                ).append( " ,"	).append( dbObj.status_admission
        ).append( " ) values ('"
        ).append( p.getObjectId()
        ).append( "','" ).append( p.employee_id
        ).append( "','" ).append( p.password
        ).append( "','" ).append( p.fname
        ).append( "','" ).append( p.lname
        ).append( "','" ).append( p.employee_no
        ).append( "','" ).append( p.last_login
        ).append( "','" ).append( p.last_logout
        ).append( "','" ).append( p.active
        ).append( "','" ).append( p.default_service_id
        ).append( "','" ).append( p.level_id
        ).append( "','" ).append( p.rule_id
        ).append( "','" ).append( p.authentication_id
        ).append( "','" ).append( p.warning_dx
        ).append( "','" ).append( p.default_tab
                ).append( "','" ).append( p.status_admission
        ).append( "')");
        return theConnectionInf.eUpdate(sql.toString());
    }
    public int update(Employee o) throws Exception
    {
        Employee p=o;
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
        ).append( dbObj.employee_id ).append( "='" ).append( p.employee_id
        ).append( "', " ).append( dbObj.password ).append( "='" ).append( p.password
        ).append( "', " ).append( dbObj.fname ).append( "='" ).append( p.fname
        ).append( "', " ).append( dbObj.lname ).append( "='" ).append( p.lname
        ).append( "', " ).append( dbObj.employee_no ).append( "='" ).append( p.employee_no
        ).append( "', " ).append( dbObj.last_login ).append( "='" ).append( p.last_login
        ).append( "', " ).append( dbObj.last_logout ).append( "='" ).append( p.last_logout
        ).append( "', " ).append( dbObj.active ).append( "='" ).append( p.active
        ).append( "', " ).append( dbObj.default_service_id ).append( "='" ).append( p.default_service_id
        ).append( "', " ).append( dbObj.level_id ).append( "='" ).append( p.level_id
        ).append( "', " ).append( dbObj.rule_id ).append( "='" ).append( p.rule_id
        ).append( "', " ).append( dbObj.authentication_id ).append( "='" ).append( p.authentication_id
        ).append( "', " ).append( dbObj.warning_dx ).append( "='" ).append( p.warning_dx
        ).append( "', " ).append( dbObj.default_tab ).append( "='" ).append( p.default_tab
                ).append( "', " ).append( dbObj.status_admission ).append( "='" ).append( p.status_admission
        ).append( "' where " ).append( dbObj.pk_field ).append( "='" ).append( p.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    public int updateLogout(Employee o) throws Exception
    {
        Employee p=o;
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
        ).append( dbObj.last_login ).append( "='" ).append( p.last_login
        ).append( "', " ).append( dbObj.last_logout ).append( "='" ).append( p.last_logout
        ).append( "' where " ).append( dbObj.pk_field ).append( "='" ).append( p.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public int delete(Employee o) throws Exception
    {
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field ).append( "='" ).append( o.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Employee selectByPK(String pk) throws Exception
    {   
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
         ).append( " where " ).append( dbObj.pk_field
         ).append( " = '" ).append( pk ).append( "'");
         
         Vector v=eQuery(sql.toString());
         if(v.size()==0)
             return null;
         else
             return (Employee)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public ComboFix selectComboBoxByPK(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field
        ).append( " = '" ).append( pk ).append( "' and "
        ).append( dbObj.active ).append( " = '" ).append( Active.isEnable() ).append( "'");
        
        Vector vc = veQuery(sql.toString());
        if(vc.size()==0)
            return null;
        else
            return (ComboFix)vc.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Employee selectByUsername(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
         ).append( " where " ).append( dbObj.employee_id
         ).append( " = '" ).append( pk ).append( "'" ).append(
         " AND " ).append( dbObj.active ).append( "='" ).append( Active.isEnable() ).append( "'");
         
         Vector v=eQuery(sql.toString());
         if(v.isEmpty())
             return null;
         else
             return (Employee)v.get(0);
    }

    /**
     * ใช้ในการหาข้อมูลของผู้ใช้จาก รหัสหลัก และ การแสดงหรือไม่ โดย 
     * active เป็น null จะไม่สนใจ,เป็น 1 จะเอาเฉพาะที่ active, เป็น 0 จะเอาเฉพาะที่ inactive
     * @param pk เป็น รหัสของตาราง 
     * @active เป็น string ถ้า เป็น null จะไม่สนใจ,เป็น 1 จะเอาเฉพาะที่ active, เป็น 0 จะเอาเฉพาะที่ inactive
     * @return เป็น Object ของ Employee ถ้าไม่มีค่าจะเป็น null
     */
    public Employee getEmployeeName(String pk,String active) throws Exception
    {    StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
         ).append( " where " ).append( dbObj.pk_field
         ).append( " = '" ).append( pk ).append( "'") ;
         if(active!=null)
         {
             sql.append( " AND " ).append( dbObj.active ).append( "='" ).append( active ).append( "'");
         }
         
         Vector v=eQuery(sql.toString());
         sql = null;
         if(v.size()==0)
             return null;
         else
             return (Employee)v.get(0);
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectNurse(String pk) throws Exception
    {
        
        Vector vc = new Vector();
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.authentication_id
        ).append( " = '" ).append( pk ).append( "'");
        
        vc = veQuery(sql.toString());
        if(vc.size()==0)
            return null;
        else
            return vc;
        
    }

    /*////////////////////////////////////////////////////////////////*/
    public Vector selectNurseAndDoctorAll() throws Exception
    {
        return selectNurseAndDoctorByPk("");
    }   
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectNurseAndDoctorByPk(String pk) throws Exception
    {
        Vector vc = new Vector();
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table ).append(
                " where (" ).append( dbObj.fname ).append( " like '%" ).append( pk ).append( "%'" ).append(
                " or " ).append( dbObj.lname ).append( " like '%" ).append( pk ).append( "%' )" ).append(
                " and (").append( dbObj.authentication_id ).append( " = '2'" ).append(
                " or " ).append( dbObj.authentication_id  ).append( " = '3')" ).append(
                " and (" ).append( dbObj.active ).append( " = '1' )");
        return eQuery(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    
    public int updatePassword(Employee o) throws Exception
    {
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
        ).append( dbObj.password ).append( " = '" ).append( o.password ).append( "' where "
        ).append( dbObj.pk_field ).append( " = '" ).append( o.getObjectId() ).append( "'");
        
        return theConnectionInf.eUpdate(sql.toString());
        
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAll(String pk,String active, String authen) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where ");
        if(pk.trim().length() !=0)
        {
            sql.append("(").append(dbObj.employee_id
            ).append( " like '%" ).append( pk ).append( "%'" ).append( " or "
            ).append( dbObj.lname ).append( " like '%" ).append( pk ).append( "%'" ).append( " or "
            ).append( dbObj.fname).append( " like '%" ).append( pk ).append( "%'" ).append( ") and ");
        }
        if(authen != null)
        {   sql.append( dbObj.authentication_id ).append( " = '" ).append( authen ).append( "'" ).append( " and ");
        }
        
        sql.append( dbObj.active ).append( " = '" ).append( active ).append( "'" ).append( " order by "
        ).append( dbObj.employee_id );
        
        return eQuery(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAllByName() throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table) ;
        sql.append(  " order by ").append( dbObj.fname);
        return eQuery(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectActive(String active) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table );
        sql.append( " where ").append( dbObj.active ).append( " = '").append(active).append("'");
        sql.append(  " order by ").append( dbObj.fname);
        
        return eQuery(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectIdnameEmployeeAll() throws Exception
    {
        StringBuffer sql = new StringBuffer("SELECT " ).append( dbObj.pk_field ).append( "," ).append( dbObj.fname ).append( "," ).append( dbObj.lname ).append( " " )
        .append(" FROM " ).append( dbObj.table ).append( " " ).append(
        " WHERE " ).append( dbObj.active ).append( "='" ).append( Active.isEnable() ).append( "'");      
        Vector v=veQuery(sql.toString());
        if(v.size()== 0)
            return null;
        else
            return v;
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/

    /**
     *@deprecated henbe unused
     *
     */    
    public Vector veQuery(String sql) throws Exception
    {
        ComboFix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        while(rs.next())
        {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.fname) +rs.getString(dbObj.lname);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector eQuery(String sql) throws Exception
    {
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        while(rs.next())
        {
            Employee p = new Employee();
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
            p.warning_dx = rs.getString(dbObj.warning_dx);
            p.default_tab = rs.getString(dbObj.default_tab);
            p.status_admission = rs.getString(dbObj.status_admission);
            list.add(p);            
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectDoctor(String pk) throws Exception
    {
        Vector vc = new Vector();
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.authentication_id ).append( " = '" ).append( pk
        ).append( "' AND " ).append( dbObj.active ).append( "='" ).append( Active.isEnable()
        ).append( "' order by " ).append( dbObj.fname);
        
        return eQuery(sql.toString());
    }
    public Vector selectDrugSetOwner() throws Exception
    {
        Vector vc = new Vector();
        StringBuffer sql = new StringBuffer( "select distinct(b_employee.*) from b_employee " ).append(
                " inner join b_item_group " ).append(
                " on b_item_group.item_group_staff_owner = b_employee.b_employee_id " ).append(
                " order by " ).append( dbObj.fname);
        return eQuery(sql.toString());
    }
    public Vector selectAuthenAllByName(String pk,String active,String authentication_id) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where ");
        if(pk.trim().length() !=0)
        {
            sql.append("(" ).append(  dbObj.employee_id
            ).append( " like '%" ).append( pk ).append( "%'" ).append( " or "
            ).append( dbObj.lname ).append( " like '%" ).append( pk ).append( "%'" ).append( " or "
            ).append( dbObj.fname).append( " like '%" ).append( pk ).append( "%'" ).append( ") and ");
        }
        
        sql = sql.append( dbObj.authentication_id ).append( " ='" ).append( authentication_id ).append( "' " ).append(
                " and ").append( dbObj.active ).append( " = '" ).append( active ).append( "'" ).append( "order by "
        ).append( dbObj.employee_id) ;
        
        return eQuery(sql.toString());
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAllByName(String pk,String active) throws Exception
    {
        String[] word = pk.split(" ");
        pk = word[0];
        String pk2 = "";
        
        StringBuffer sql = new StringBuffer( "select * from " ).append( dbObj.table ).append(
                " where (" ).append( dbObj.employee_id ).append( " ilike '" ).append( pk ).append( "%'" ).append(
                " or " ).append( dbObj.fname ).append( " ilike '" ).append( pk ).append( "%'" ).append(
                " or " ).append( dbObj.lname ).append( " ilike '" ).append( pk ).append( "%') ");
        
        if(word.length>1){
            pk2 = word[1];
            sql.append(" and (" ).append( dbObj.employee_id ).append( " = '" ).append( pk2 ).append( "' " ).append(
                " or " ).append( dbObj.fname ).append( " ilike '" ).append( pk2 ).append( "%'" ).append(
                " or " ).append( dbObj.lname ).append( " ilike '" ).append( pk2 ).append( "%') )");
        }
        sql.append(" and " ).append( dbObj.active ).append( " = '" ).append( 1 ).append( "' " ).append(
                " order by " ).append( dbObj.fname);
        return eQuery(sql.toString());
    }
    public static void main(String[] str){
        Employee dbObj = new Employee();
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
        dbObj.warning_dx ="employee_warning_dx";  
        dbObj.default_tab = "b_employee_default_tab";
        String pk = "Doctor แพ";
        String[] word = pk.split(" ");
        pk = word[0];
        String pk2 = "";
        
        StringBuffer sql = new StringBuffer( "select * from " ).append( dbObj.table ).append(
                " where (" ).append( dbObj.employee_id ).append( " ilike '" ).append( pk ).append( "%'" ).append(
                " or " ).append( dbObj.fname ).append( " ilike '" ).append( pk ).append( "%'" ).append(
                " or " ).append( dbObj.lname ).append( " ilike '" ).append( pk ).append( "%') ");
        
        if(word.length>1){
            pk2 = word[1];
            sql.append(" and (" ).append( dbObj.employee_id ).append( " = '" ).append( pk2 ).append( "' "
                ).append( dbObj.fname ).append( " ilike '" ).append( pk2 ).append( "%'"
                ).append( dbObj.lname ).append( " ilike '" ).append( pk2 ).append( "%') ");
        }
        sql.append(" and " ).append( dbObj.active ).append( " = '" ).append( 1 ).append( "' " ).append(
                " order by " ).append( dbObj.fname);
        Constant.println(sql.toString());
    }
}
