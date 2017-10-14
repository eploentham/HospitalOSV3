//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hospital_os.objdb;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;
import com.hospital_os.utility.*;
import java.util.*;
import java.sql.*;

public class OptionDetailDB 
{
    public ConnectionInf theConnectionInf;
    public OptionDetail dbObj;
    final public static String idtable = "905";

    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public OptionDetailDB(ConnectionInf db)
    {
        theConnectionInf=(ConnectionInf)db;
        dbObj = new OptionDetail();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="b_option_detail";
        dbObj.pk_field="b_option_detail_id";
        dbObj.name   ="option_detail_name";
        dbObj.note   ="option_detail_note";
        return true;
    }
    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(OptionDetail p) throws Exception
    {
        String sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field + " ,"	+ dbObj.name + " ,"	+ dbObj.note + " ) values (?,?,?)";
        
        Connection conn = theConnectionInf.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,p.getObjectId());
        ps.setString(2,p.name);
        ps.setString(3,p.note);
        Constant.println(ps.toString());
        return ps.executeUpdate();
    }
    public int update(OptionDetail p) throws Exception
    {
        String sql="update " + dbObj.table + " set "
        + dbObj.name + "='" + p.name + "',"
        + dbObj.note + "='" + p.note
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public int delete(OptionDetail o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public int deleteAll() throws Exception
    {
        String sql="delete from " + dbObj.table;
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll() throws Exception
    {
        Vector vc = new Vector();
        String sql="select * from " + dbObj.table
        + " order by " + dbObj.name;
        return eQuery(sql);
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
            OptionDetail p = new OptionDetail();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.name = rs.getString(dbObj.name);
            p.note = rs.getString(dbObj.note);
            list.add(p);
        }
        rs.close();
        return list;
    }
        
}
