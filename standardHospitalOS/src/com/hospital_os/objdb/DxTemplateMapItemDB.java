/*
 * DxTemplateMapItemDB.java
 *
 * Created on 9 สิงหาคม 2549, 16:22 น.
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
 * @author Administrator
 */
public class DxTemplateMapItemDB
{
    public ConnectionInf theConnectionInf;
    public DxTemplateMapItem dbObj;
    final public String idtable = "292";
    
    /** Creates a new instance of DxTemplateMapItemDB */
    public DxTemplateMapItemDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new DxTemplateMapItem();
        initConfig();
    }
    
    public DxTemplateMapItemDB()
    {
        theConnectionInf = null;
        dbObj = new DxTemplateMapItem();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "b_template_dx_map_item";
        dbObj.pk_field = "b_template_dx_map_item_id";
        dbObj.template_dx_id = "b_template_dx_id";
        dbObj.item_id = "b_item_id";
        dbObj.code = "item_number";
        dbObj.description = "item_common_name";
        return true;
    }
    
    public int insert(DxTemplateMapItem o) throws Exception
    {
        String sql="";
        DxTemplateMapItem p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
                + dbObj.pk_field
                + " ,"	+ dbObj.template_dx_id
                + " ,"  + dbObj.item_id
                + " ) values ('"
                + p.getObjectId()
                + "','" + p.template_dx_id
                + "','" + p.item_id
                + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(DxTemplateMapItem o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        DxTemplateMapItem p=o;
        String field =""
                + "', " + dbObj.template_dx_id + "='" + p.template_dx_id
                + "', " + dbObj.item_id + "='" + p.item_id
                + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }

    public int delete(DxTemplateMapItem o) throws Exception
    {
        String sql="delete from " + dbObj.table
                + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int deleteItemDxByDxTemplate(String pk) throws Exception
    {
        String sql="delete from " + dbObj.table
                + " where " + dbObj.template_dx_id + "='" + pk +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectByDtid(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table 
                    + " where " + dbObj.template_dx_id +" = '"+ pk +"'";       
        return eQuery(sql);
    }
    
    public Vector specialQueryItemDx(String pk) throws Exception
    {
        String sql="select b_template_dx_map_item.b_template_dx_map_item_id as dx_map_item_id " +
                    ",b_template_dx_map_item.b_template_dx_id as template_dx " +     
                    ",b_template_dx_map_item.b_item_id as item_id " +
                    ",b_item.item_number as code " +
                    ",b_item.item_common_name as name " +
                    "from b_template_dx_map_item inner join b_item on " +
                    "b_template_dx_map_item.b_item_id = b_item.b_item_id " +
                    "where b_template_dx_map_item.b_template_dx_id = '" + pk + "'";
        Vector v=sEQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        DxTemplateMapItem p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new DxTemplateMapItem();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.template_dx_id = rs.getString(dbObj.template_dx_id);
            p.item_id = rs.getString(dbObj.item_id);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public Vector sEQuery(String sql) throws Exception
    {
        DxTemplateMapItem p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new DxTemplateMapItem();  
            p.setObjectId(rs.getString("dx_map_item_id"));
            p.template_dx_id = rs.getString("template_dx");
            p.item_id = rs.getString("item_id");           
            p.code = rs.getString("code");
            p.description = rs.getString("name");
            list.add(p);
        }
        rs.close();
        return list;
    }

}
