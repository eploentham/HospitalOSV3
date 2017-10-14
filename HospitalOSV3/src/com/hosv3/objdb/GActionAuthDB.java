//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;

import com.hospital_os.usecase.connection.*;
import com.hosv3.object.*;
import java.util.*;
import java.sql.*;

public class GActionAuthDB 
{
    ConnectionInf theConnectionInf;
    public GActionAuth dbObj;
    final public static String idtable = "906";

    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public GActionAuthDB(ConnectionInf db)
    {
        dbObj = new GActionAuth();
        theConnectionInf = db;
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="f_gui_action_authen";
        dbObj.pk_field="f_gui_action_authen_id";
        dbObj.gui_id ="f_gui_action_id";
        dbObj.gui_name ="gui_action_authen_gui_name";
        dbObj.auth_id ="f_authentication_id";
        dbObj.auth_name ="gui_action_authen_authentication_name";
        dbObj.is_read ="gui_action_authen_is_read";
        dbObj.is_write ="gui_action_authen_is_write";
        dbObj.note="gui_action_authen_note";
        return true;
    }
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insertByAid(Vector v,String authen_id) throws Exception {
        int ret=0;
        for(int i=0,size=v.size();i<size;i++){
            GActionAuth gaa = (GActionAuth)v.get(i);
            if(i==0)
                deleteByAid(gaa.auth_id);
            gaa.auth_id = authen_id;
            ret = ret + insert(gaa);
        }
        return ret;
    }
    public int insert(GActionAuth p) throws Exception
    {
        p.generateOID(idtable);
        String sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.gui_id
        + " ,"	+ dbObj.gui_name
        + " ,"	+ dbObj.auth_id
        + " ,"	+ dbObj.is_read
        + " ,"	+ dbObj.is_write
        + " ,"	+ dbObj.auth_name
        + " ,"	+ dbObj.note
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.gui_id
        + "','" + p.gui_name
        + "','" + p.auth_id
        + "','" + p.is_read
        + "','" + p.is_write
        + "','" + p.auth_name
        + "','" + p.note
        + "')";
        return theConnectionInf.eUpdate(sql);
    }
    public int update(GActionAuth p) throws Exception
    {
        String sql="update " + dbObj.table + " set "
        + dbObj.gui_id + "='" + p.gui_id
        + "', " + dbObj.gui_name + "='" + p.gui_name
        + "', " + dbObj.auth_id + "='" + p.auth_id
        + "', " + dbObj.is_read + "='" + p.is_read
        + "', " + dbObj.is_write + "='" + p.is_write
        + "', " + dbObj.auth_name + "='" + p.auth_name
        + "', " + dbObj.note + "='" + p.note
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public int delete(GActionAuth o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public int deleteByAid(String aid) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.auth_id + " ='" + aid +"'";
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll() throws Exception
    {
        String sql="select * from " + dbObj.table
        + " order by " + dbObj.auth_id;
        return eQuery(sql);
    }
    public Vector selectByAid(String auth_id) throws Exception
    {
        String sql="select f_gui_action_authen_id" +
                    ",f_gui_action.f_gui_action_id  as f_gui_action_id" +
                    ",gui_action_name as gui_action_authen_gui_name" +
                    ",f_authentication_id " +
                    ",gui_action_authen_authentication_name" +
                    ",gui_action_authen_is_read" +
                    ",gui_action_authen_is_write" +
                    ",gui_action_authen_note " +
                "from " +
                    "f_gui_action " +
                    "left join f_gui_action_authen on (f_gui_action.f_gui_action_id = f_gui_action_authen.f_gui_action_id " +
                        "and f_authentication_id = '"+auth_id+"' )" +
                "order by f_gui_action.f_gui_action_id";

        return eQuery(sql);
    }    
    public Vector eQuery(String sql) throws Exception
    {
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            GActionAuth p = new GActionAuth();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.gui_id = rs.getString(dbObj.gui_id);
            p.gui_name = rs.getString(dbObj.gui_name);
            p.auth_id = rs.getString(dbObj.auth_id);
            p.is_read = rs.getString(dbObj.is_read);
            p.is_write = rs.getString(dbObj.is_write);
            p.auth_name = rs.getString(dbObj.auth_name);
            p.note = rs.getString(dbObj.note); 
            //มีการ query หลายตารางทำให้มีข้อมูล null เกิดขึ้นเลยต้องกันด้วย
            if(p.gui_name==null) p.gui_name = "";
            if(p.is_read==null) p.is_read = "1";
            if(p.is_write==null) p.is_write = "1";
            if(p.auth_name==null) p.auth_name = "";
            if(p.note==null) p.note = "";
            list.add(p);
        }
        rs.close();
        return list;
    }
}
