//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.gui.component.nan;

import com.hospital_os.usecase.connection.*;
import java.util.*;
import java.sql.*;

public class DiseaseTemplateDB
{
    public ConnectionInf theConnectionInf;
    public DiseaseTemplate dbObj;
    final public static String idtable = "905";

    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public DiseaseTemplateDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new DiseaseTemplate();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="b_template_disease";
        dbObj.pk_field="b_template_disease_id";
        dbObj.name   ="template_disease_name";
        dbObj.note   ="template_disease_note";
        return true;
    }
    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(DiseaseTemplate o) throws Exception
    {
        String sql="";
        DiseaseTemplate p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.name
        + " ,"	+ dbObj.note
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.name
        + "','" + p.note
        + "')";
        return theConnectionInf.eUpdate(sql);
    }
    public int update(DiseaseTemplate p) throws Exception
    {
        String sql="update " + dbObj.table + " set "
        + dbObj.name + "='" + p.name
        + dbObj.note + "='" + p.note
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public int delete(DiseaseTemplate o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll() throws Exception
    {
        //Vector vc = new Vector();
        String sql="select * from " + dbObj.table
        + " order by " + dbObj.name;
        return eQuery(sql);
    } 
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectByName(String str) throws Exception
    {
        //Vector vc = new Vector();
        String sql="select * from " + dbObj.table + 
                " where " + dbObj.name +" like '" + str + "'"+
                " order by " + dbObj.name;
        return eQuery(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public DiseaseTemplate selectByPK(String str) throws Exception
    {
        Vector vc = new Vector();
        String sql="select * from " + dbObj.table + 
                " where " + dbObj.pk_field +" like '" + str + "'"+
                " order by " + dbObj.name;
        vc = eQuery(sql);
        if(vc.isEmpty())
            return null;
        else
            return (DiseaseTemplate)vc.get(0);
    }
    public Vector eQuery(String sql) throws Exception
    {
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            DiseaseTemplate p = new DiseaseTemplate();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.name = rs.getString(dbObj.name);
            p.note = rs.getString(dbObj.note);
            list.add(p);
        }
        rs.close();
        return list;
    }
        
}
