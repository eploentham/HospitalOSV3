/*
 * EpiSetDB.java
 *
 * Created on 24 ÁÔ¶Ø¹ÒÂ¹ 2548, 16:45 ¹.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
import com.hospital_os.utility.*;
/**
 *
 * @author Administrator
 */
public class EpiSetDB {
    
    /** Creates a new instance of EpiSetDB */
    public EpiSetDB() {
    }
    
    
    public ConnectionInf theConnectionInf;
    public EpiSet dbObj;
    final public String idtable = "741";//"144";
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public EpiSetDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new EpiSet();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="b_health_epi_item";
        dbObj.pk_field="b_health_epi_item_id";
        dbObj.epi_set_group_key_id   ="b_health_epi_group_id";//"b_health_epi_set_id";
        dbObj.item_code="b_item_id";
        /*
        dbObj.table = "epi_set";
        dbObj.pk_field = "key_id";
        dbObj.item_code = "item_code";
        dbObj.epi_set_group_key_id  = "epi_set_group_key_id";
         */
        return true;
    }
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(EpiSet o) throws Exception
    {
        String sql="";
        EpiSet p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.epi_set_group_key_id
        + " ,"	+ dbObj.item_code
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.epi_set_group_key_id
        + "','" + p.item_code
        + "')";
       
        return theConnectionInf.eUpdate(sql);
    }
    public int update(EpiSet o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        EpiSet p=o;
        String field =""
        + "', " + dbObj.epi_set_group_key_id + "='" + p.epi_set_group_key_id
        + "', " + dbObj.item_code + "='" + p.item_code
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public int delete(EpiSet o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public int deleteByGroup(String  pk) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.epi_set_group_key_id + "='" + pk +"'";
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public EpiSet selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (EpiSet)v.get(0);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll( ) throws Exception
    {
        String sql="select * from " + dbObj.table;
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectByGroup(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.epi_set_group_key_id + " = '" + pk + "'";
        return eQuery(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectByGroupItemActive(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " inner join b_item on b_item.b_item_id = b_health_epi_item.b_item_id" +
        " where " + dbObj.epi_set_group_key_id  + " = '" + pk + "'" +
        " and item_active = '1'";
        return eQuery(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector eQuery(String sql) throws Exception
    {
        EpiSet p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new EpiSet();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.epi_set_group_key_id = rs.getString(dbObj.epi_set_group_key_id);
            p.item_code = rs.getString(dbObj.item_code);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
