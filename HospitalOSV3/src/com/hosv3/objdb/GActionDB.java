//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;

import com.hospital_os.usecase.connection.*;
import com.hosv3.object.*;
import java.util.*;
import java.sql.*;

public class GActionDB 
{
    ConnectionInf theConnectionInf;
    public GAction dbObj;
    final public static String idtable = "905";

    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public GActionDB(ConnectionInf db)
    {
        dbObj = new GAction();
        theConnectionInf = db;
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="f_gui_action";
        dbObj.pk_field="f_gui_action_id";
        dbObj.description   ="gui_action_name";
        dbObj.note="gui_action_note";
        return true;
    }
    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(GAction p) throws Exception
    {
        String sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.description
        + " ,"	+ dbObj.note
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.description
        + "','" + p.note
        + "')";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(GAction p) throws Exception
    {
        String sql="update " + dbObj.table + " set "
        + dbObj.description + "='" + p.description
        + "', " + dbObj.note + "='" + p.note
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public int delete(GAction o) throws Exception
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
        + " order by " + dbObj.description;
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
            GAction p = new GAction();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);
            p.note = rs.getString(dbObj.note);
            list.add(p);
        }
        rs.close();
        return list;
    }
        
}
