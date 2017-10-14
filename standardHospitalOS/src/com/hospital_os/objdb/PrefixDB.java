/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java
*/

package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;

import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;

public class PrefixDB
{
    public ConnectionInf theConnectionInf;
    public Prefix dbObj;
    final public String idtable = "215";/*"190";
*/
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public PrefixDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Prefix();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.max_prefix_id = "max_prefix_id";
        dbObj.table="f_patient_prefix";
        dbObj.pk_field="f_patient_prefix_id";
        dbObj.description="patient_prefix_description";
        dbObj.sex="f_sex_id";
        dbObj.tlock="f_tlock_id";
        dbObj.active = "active";
        dbObj.prefix_id53 = "r_rp1853_prefix_id";
        /*
         
        dbObj.table = "prefix";
        dbObj.pk_field = "prefix_id";
         
        dbObj.description = "description";
        dbObj.sex = "sex";
        dbObj.tlock = "tlock";
         */
        
        return true;
    }
    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(Prefix o) throws Exception
    {
        String sql="";
        Prefix p=o;
        
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        
        + " ,"	+ dbObj.description
        + " ,"	+ dbObj.sex
        + " ,"	+ dbObj.tlock
        + " ,"	+ dbObj.active
        + " ,"	+ dbObj.prefix_id53
        + " ) values ('"
        + p.getObjectId()
        
        + "','" + p.description
        + "','" + p.sex
        + "','" + p.tlock
        + "','" + p.active
        + "','" + p.prefix_id53
        + "')";
        
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(Prefix o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Prefix p=o;
        
        String field =""
        
        + "', " + dbObj.description + "='" + p.description
        + "', " + dbObj.sex + "='" + p.sex
        + "', " + dbObj.tlock + "='" + p.tlock
        + "', " + dbObj.active + "='" + p.active
        + "', " + dbObj.prefix_id53 + "='" + p.prefix_id53
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    /*////////////////////////////////////////////////////////////////////////////
*/
    public int delete(Prefix o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Prefix selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (Prefix)v.get(0);
    }
    
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Prefix selectByName(String name) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.description
        + " like '" + name.trim() + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (Prefix)v.get(0);
    }
    
    
    /**
     *  ใช้ในการหาคำนำหน้าตาราง active ถ้าระบุเป็น 1 เอาเฉพาะที่ active ถ้าเป็น 0 เอาเฉพาะที่ไม่ active
     *  ถ้าจะใช้ทั้งหมด ให้เลือกเป็น 2
     *@param active เป็น String 
     *@return เป็น Vector
     *@author padungrat(tong)
     *@date 13/03/49,15:50
     */
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll(String active) throws Exception
    {
        String sql="select * from " + dbObj.table;
        if(active != null && (active.equals("1")||active.equals("0")  ))
        {
            sql = sql + " where " + dbObj.active + "='" + active.trim() + "'";
        }
        
        sql = sql + " order by " + dbObj.pk_field;
        return eQuery(sql);
            
    }
   //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll() throws Exception
    {
        return selectAll("1");
    }    
    
    /*////////////////////////////////////////////////////////////////////////////
*/
    
    /**
     *@deprecated henbe unused
     *
     */
    public Vector veQuery(String sql) throws Exception
    {
        ComboFix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.description);
            
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector eQuery(String sql) throws Exception
    {
        Prefix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            p = new Prefix();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);
            p.sex = rs.getString(dbObj.sex);
            p.tlock = rs.getString(dbObj.tlock);
            p.active = rs.getString(dbObj.active);
            p.prefix_id53 = rs.getString(dbObj.prefix_id53);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    
    public String selectMaxCode() throws Exception
    {
        String sql ="select max(" + dbObj.pk_field + ") as max_prefix_id from " + dbObj.table;
        
        Vector v = prefixQuery(sql);
        if(v.size()==0)
            return null;
        else
            return ((Prefix)v.get(0)).getObjectId();
    }
    
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector prefixQuery(String sql) throws Exception
    {
        Prefix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            p = new Prefix();
            p.setObjectId(rs.getString(dbObj.max_prefix_id));
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector selectTlcok2() throws Exception
    {
        Vector vc = new Vector();
        String sql="select * from " + dbObj.table
        + " where " + dbObj.tlock
        + " = 2 ";
        vc = veQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    
}
