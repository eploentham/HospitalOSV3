/*
 * NCDGroupDB.java
 *
 * Created on 14 มิถุนายน 2549, 11:02 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.objdb;

import java.util.*;
import java.sql.*;
import java.text.*;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;

/**
 *
 * @author amp
 */
public class NCDGroupDB
{
    public ConnectionInf theConnectionInf;
    public NCDGroup dbObj;
    final public String idtable = "289";
    
    /** Creates a new instance of NCDGroupDB */    
    public NCDGroupDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new NCDGroup();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "b_ncd_group";
        dbObj.pk_field = "b_ncd_group_id";
        dbObj.number = "ncd_group_number";
        dbObj.description = "ncd_group_description";
        dbObj.pattern = "ncd_group_pattern";
        dbObj.value = "ncd_group_value";     
        dbObj.chronic_group = "b_group_chronic_id";
        return true;
    }
    
    public int insert(NCDGroup o) throws Exception
    {
        String sql="";
        NCDGroup p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.number        
        + " ,"	+ dbObj.description
        + " ,"	+ dbObj.pattern
        + " ,"	+ dbObj.value
        + " ,"	+ dbObj.chronic_group        
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.number
        + "','" + p.description
        + "','" + p.pattern
        + "','" + p.value
        + "','" + p.chronic_group        
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(NCDGroup o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        NCDGroup p=o;
        String field =""
        + "', " + dbObj.number + "='" + p.number        
        + "', " + dbObj.description + "='" + p.description
        + "', " + dbObj.pattern + "='" + p.pattern
        + "', " + dbObj.value + "='" + p.value
        + "', " + dbObj.chronic_group + "='" + p.chronic_group        
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(NCDGroup o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectBySearch(String data) throws Exception
    {        
        String sql="select * from " + dbObj.table
        + " where UPPER(" + dbObj.number
        + ") like UPPER('%" + data+ "%') "
        + " or UPPER(" + dbObj.description
        + ") like UPPER('%" + data+ "%') order by " + dbObj.description;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public NCDGroup selectByNumber(String code) throws Exception
    {
        Vector vc = new Vector();
        String sql ="select * from " + dbObj.table
        + " where " + dbObj.number
        + " = '" + code + "'";
        
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return (NCDGroup)vc.get(0);
    }
    
    public Vector selectAll() throws Exception
    {   
        String sql ="select * from " + dbObj.table
        + " order by " + dbObj.description;
        return eQuery(sql);
    }
    
    public String updateSequence(String str) throws Exception
    {
        String a = new String();
        //เอาค่าเก่ามาเก็บไว้ แล้วก็ update sequence ให้เป็นเลขถัดไป
        NCDGroup ncdGroup = selectByPK(str);
        int value = Integer.parseInt(ncdGroup.value);
        ncdGroup.value = String.valueOf(value + 1);
        update(ncdGroup);

        DecimalFormat d = new DecimalFormat();
        d.applyPattern("00000");
        
        a = ncdGroup.number + d.format(value);
        
        return a;
    }
    
    public void updateSequenceBack(String str) throws Exception
    {
        String a = new String();
        //เอาค่าเก่ามาเก็บไว้ แล้วก็ update sequence ให้เป็นเลขถัดไป
        NCDGroup ncdGroup = selectByPK(str);
        int value = Integer.parseInt(ncdGroup.value);
        ncdGroup.value = String.valueOf(value - 1);
        update(ncdGroup);
    }
    
    public NCDGroup selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (NCDGroup)v.get(0);
    }
    
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
    
    public Vector eQuery(String sql) throws Exception
    {
        NCDGroup p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new NCDGroup();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.number = rs.getString(dbObj.number);
            p.description = rs.getString(dbObj.description);
            p.pattern = rs.getString(dbObj.pattern);
            p.value = rs.getString(dbObj.value);
            p.chronic_group = rs.getString(dbObj.chronic_group);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
