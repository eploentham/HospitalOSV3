//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.gui.component.nan;

import com.hospital_os.usecase.connection.*;
import java.util.*;
import java.sql.*;

public class PhysicalPartDB 
{
    public ConnectionInf theConnectionInf;
    public PhysicalPart dbObj;
    final public static String idtable = "905";

    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public PhysicalPartDB(ConnectionInf db)
    {
        theConnectionInf=(ConnectionInf)db;
        dbObj = new PhysicalPart();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="b_physical_part";
        dbObj.pk_field="b_physical_part_id";
        dbObj.name   ="physical_part_name";
        return true;
    }
    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(PhysicalPart o) throws Exception
    {
        String sql="";
        PhysicalPart p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.name
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.name
        + "')";
        return theConnectionInf.eUpdate(sql);
    }
    public int update(PhysicalPart p) throws Exception
    {
        String sql="update " + dbObj.table + " set "
        + dbObj.name + "='" + p.name
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public int delete(PhysicalPart o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll() throws Exception
    {
        Vector vc = new Vector();
        String sql="select * from " + dbObj.table
        + " order by " + dbObj.name;
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    } 
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectByName(String str) throws Exception
    {
        Vector vc = new Vector();
        String sql="select * from " + dbObj.table + 
                " where " + dbObj.name +" like '" + str + "'"+
                " order by " + dbObj.name;
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    public Vector eQuery(String sql) throws Exception
    {
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            PhysicalPart p = new PhysicalPart();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.name = rs.getString(dbObj.name);
            list.add(p);
        }
        rs.close();
        return list;
    }
        
}
