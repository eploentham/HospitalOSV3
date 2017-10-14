/*
 * DrugStandardMapItemDB.java
 *
 * Created on 14 ¡’π“§¡ 2549, 15:57 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.objdb;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;

/**
 *
 * @author amp
 */
public class DrugStandardMapItemDB
{    
    public ConnectionInf theConnectionInf;
    public DrugStandardMapItem dbObj;
    final public String idtable = "283";
    
    /** Creates a new instance of DrugStandardMapItemDB */
    public DrugStandardMapItemDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new DrugStandardMapItem();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "b_item_drug_standard_map_item";        
        dbObj.pk_field = "b_item_drug_standard_map_item_id";
        dbObj.drug_standard_id = "b_item_drug_standard_id";
        dbObj.item_id = "b_item_id";         
        return true;
    }
    
    public int insert(DrugStandardMapItem o) throws Exception
    {
        String sql = "";
        DrugStandardMapItem p=o;
        p.generateOID(idtable);        
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.drug_standard_id
        + " ,"	+ dbObj.item_id
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.drug_standard_id
        + "','" + p.item_id
        + "')";
        
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(DrugStandardMapItem o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        DrugStandardMapItem p=o;
        String field =""
        + "', " + dbObj.drug_standard_id + "='" + p.drug_standard_id
        + "', " + dbObj.item_id + "='" + p.item_id
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(DrugStandardMapItem o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public DrugStandardMapItem selectByDrugStandardAndItem(String drugStandard,String item) throws Exception
    {
        Vector vc = new Vector();
        String sql ="select * from " + dbObj.table
        + " where " + dbObj.drug_standard_id
        + " = '" + drugStandard +"' and " 
        + dbObj.item_id + " = '" + item + "'";
        
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return (DrugStandardMapItem)vc.get(0);
    }
    
    public DrugStandardMapItem selectByItem(String item) throws Exception
    {
        Vector vc = new Vector();
        String sql ="select * from " + dbObj.table
        + " where " + dbObj.item_id + " = '" + item + "'";
        
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return (DrugStandardMapItem)vc.get(0);
    }
    
    public Vector listDrugStandardMapItem(String description,String active) throws Exception
    {
        String sql = "SELECT b_item_drug_standard_map_item.b_item_drug_standard_map_item_id" +
                ", b_item_drug_standard_map_item.b_item_drug_standard_id" +
                ", b_item_drug_standard_map_item.b_item_id" +
                ", b_item_drug_standard.item_drug_standard_description" +
                ", b_item.item_common_name" +
                " FROM b_item" +
                " INNER join b_item_subgroup on b_item.b_item_subgroup_id = b_item_subgroup.b_item_subgroup_id" +
                " LEFT JOIN b_item_drug_standard_map_item ON b_item_drug_standard_map_item.b_item_id = b_item.b_item_id" +
                " LEFT JOIN b_item_drug_standard ON b_item_drug_standard_map_item.b_item_drug_standard_id = b_item_drug_standard.b_item_drug_standard_id";
        if(active.equals("0"))
            sql += " WHERE b_item_drug_standard_map_item.b_item_drug_standard_id is null";
        else
            sql += " WHERE b_item_drug_standard_map_item.b_item_drug_standard_id is not null";
        
        sql +=" and b_item_subgroup.f_item_group_id = '1'" +
        " AND UPPER(b_item.item_common_name) like UPPER('%"+description+"%')" +
        "  ORDER BY b_item.item_common_name";
        
        return specialQuery(sql);
    }
    
    public DrugStandardMapItem selectByStandard(String standard) throws Exception
    {
        Vector vc = new Vector();
        String sql ="select * from " + dbObj.table
        + " where " + dbObj.drug_standard_id + " = '" + standard + "'";
        
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return (DrugStandardMapItem)vc.get(0);
    }
    
    public Vector selectItemByStandard(String standard) throws Exception
    {
        Vector vc = new Vector();
        String sql ="select * from " + dbObj.table
        + " where " + dbObj.drug_standard_id + " = '" + standard + "'";
        
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
    public Vector selectItemByStandardId(String standard) throws Exception
    {
        Vector vc = new Vector();
        
        String sql = "SELECT " +
                "b_item_drug_standard_map_item.b_item_drug_standard_map_item_id, " +
                "b_item_drug_standard_map_item.b_item_drug_standard_id, " +
                "b_item_drug_standard_map_item.b_item_id, " +
                "b_item_drug_standard.item_drug_standard_description, " +
                "b_item.item_common_name " +
                "FROM (b_item_drug_standard_map_item " +
                "LEFT JOIN b_item ON b_item_drug_standard_map_item.b_item_id " +
                "= b_item.b_item_id) " +
                "LEFT JOIN b_item_drug_standard ON " +
                "b_item_drug_standard_map_item.b_item_drug_standard_id " +
                "= b_item_drug_standard.b_item_drug_standard_id " + 
                "WHERE (b_item_drug_standard_map_item.b_item_drug_standard_id = '" + standard + "') " +
                "ORDER BY b_item.item_common_name";
        
        vc = specialQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        DrugStandardMapItem p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new DrugStandardMapItem();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.drug_standard_id = rs.getString(dbObj.drug_standard_id);
            p.item_id = rs.getString(dbObj.item_id);            
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public Vector specialQuery(String sql) throws Exception
    {
        DrugStandardMapItem p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new DrugStandardMapItem();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.drug_standard_id = rs.getString(dbObj.drug_standard_id);
            p.item_id = rs.getString(dbObj.item_id);
            p.drug_standard_description = rs.getString("item_drug_standard_description");
            p.item_description = rs.getString("item_common_name");
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
