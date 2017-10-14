/*
 * ResidentAgeGroupDB.java
 *
 * Created on 1 ¡’π“§¡ 2549, 18:18 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalpcu.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalpcu.object.ResidentAgeGroup;
import com.generalpcu.utility.ComboFix;
import java.sql.*;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class ResidentAgeGroupDB
{
    private ConnectionInf theConnectionInf;
    private Vector vAgeGroup;
    private String SQL = "";
    private ResultSet rs ;
    private ResidentAgeGroup dbObj;
    private ComboFix theComboFix;
    /**
     * Creates a new instance of ResidentAgeGroupDB 
     */
    public ResidentAgeGroupDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
        dbObj = new ResidentAgeGroup();
        dbObj.setInitDataFieldName();
    }
    
    public Vector selectAll() throws Exception
    {
        SQL ="select * from " + dbObj.tableName;
        Vector v=eQuery(SQL);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public Vector selectAllComboFix() throws Exception
    {
        SQL ="SELECT r_resident_age_group_id,resident_age_group_begin,resident_age_group_end " +
                " FROM " + dbObj.tableName + 
                " ORDER BY to_number(resident_age_group_begin,'99') ";
        Vector v=evQuery(SQL);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        ResidentAgeGroup p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ResidentAgeGroup();
            p.setObjectId(rs.getString(1));
            p.number = rs.getString(2);
            p.begin = rs.getString(3);
            p.end = rs.getString(4);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public Vector evQuery(String sql) throws Exception
    {
        ComboFix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ComboFix();
            p.code = rs.getString(1);
            p.name = rs.getString(2) + "-" + rs.getString(3);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
