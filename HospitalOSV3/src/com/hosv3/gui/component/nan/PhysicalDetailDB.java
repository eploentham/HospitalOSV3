//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.gui.component.nan;

import com.hospital_os.usecase.connection.*;
import java.util.*;
import java.sql.*;

public class PhysicalDetailDB 
{
    public ConnectionInf theConnectionInf;
    public PhysicalDetail dbObj;
    final public static String idtable = "905";

    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public PhysicalDetailDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PhysicalDetail();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="b_physical_detail";
        dbObj.pk_field="b_physical_detail_id";
        dbObj.description ="physical_detail_description";
        dbObj.physical_part_id ="b_physical_part_id";
        dbObj.employee_id ="b_employee_id";
        return true;
    }
    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(PhysicalDetail o) throws Exception
    {
        String sql="";
        PhysicalDetail p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.description
        + " ,"	+ dbObj.physical_part_id
        + " ,"	+ dbObj.employee_id
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.description
        + "','" + p.physical_part_id
        + "','" + p.employee_id
        + "')";
        return theConnectionInf.eUpdate(sql);
    }
    public int update(PhysicalDetail p) throws Exception
    {
        String sql="update " + dbObj.table + " set "
        + dbObj.description + "='" + p.description
        + "', " + dbObj.physical_part_id + "='" + p.physical_part_id
        + "', " + dbObj.employee_id + "='" + p.employee_id
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public int delete(PhysicalDetail o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }    //////////////////////////////////////////////////////////////////////////////
    public int deleteByPpid(String ppid) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.physical_part_id + "='" + ppid +"'";
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll() throws Exception
    {
        //Vector vc = new Vector();
        String sql="select * from " + dbObj.table
        + " order by " + dbObj.description;
        return eQuery(sql);
    }
     public PhysicalDetail selectByPK(String id) throws Exception
    {
        String sql="select * from " + dbObj.table +
                " where "+ dbObj.pk_field + " = '" +id+ "'" +
                " order by " + dbObj.description;
        Vector v = eQuery(sql);
        if(v.isEmpty())
            return null;
        else
            return (PhysicalDetail)v.get(0);
                   
    }   
//////////////////////////////////////////////////////////////////////////////
    public Vector selectByPpid(String ppid,String str) throws Exception
    {
        //Vector vc = new Vector();
        String sql="select * from " + dbObj.table + 
                " where "+ dbObj.physical_part_id + " like '" +ppid+ "'" +
                " and "+dbObj.description + " like '" +str+ "'" +
                " order by " + dbObj.description;
        return eQuery(sql);
    }
    public Vector eQuery(String sql) throws Exception
    {
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            PhysicalDetail p = new PhysicalDetail();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);
            p.physical_part_id = rs.getString(dbObj.physical_part_id);
            p.employee_id = rs.getString(dbObj.employee_id);
            list.add(p);
        }
        rs.close();
        return list;
    }
        
}
