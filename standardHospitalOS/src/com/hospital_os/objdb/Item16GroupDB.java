/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java*/
package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
public class Item16GroupDB
{
    public ConnectionInf theConnectionInf;
    public Item16Group dbObj;
    final private String idtable = "312";
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public Item16GroupDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Item16Group();
        initConfig();
    }
    
    public Item16GroupDB()
    {
        theConnectionInf = null;
        dbObj = new Item16Group();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="b_item_16_group";
        dbObj.pk_field="b_item_16_group_id";
        dbObj.item_16_group_id   ="item_16_group_number";
        dbObj.description   ="item_16_group_description";
        dbObj.active ="item_16_group_active";
        return true;
    }
    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(Item16Group o) throws Exception
    {
        Item16Group p=o;
        p.generateOID(idtable);
        StringBuffer sql = new StringBuffer("insert into " ).append( dbObj.table ).append( " ("
        ).append( dbObj.pk_field
        ).append( " ,"	).append( dbObj.item_16_group_id
        ).append( " ,"	).append( dbObj.description
        ).append( " ,"	).append( dbObj.active
        ).append( " ) values('"
        ).append( p.getObjectId()
        ).append( "','" ).append( p.item_16_group_id
        ).append( "','" ).append( p.description
        ).append( "','" ).append( p.active
        ).append( "')");
        
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    public int update(Item16Group o) throws Exception
    {
        Item16Group p=o;
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
        ).append( dbObj.item_16_group_id ).append( "='" ).append( p.item_16_group_id
        ).append( "', " ).append( dbObj.description ).append( "='" ).append( p.description
        ).append( "', " ).append( dbObj.active ).append( "='" ).append( p.active
        ).append( "' where " ).append( dbObj.pk_field ).append( "='" ).append( p.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public int delete(Item16Group o) throws Exception
    {
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field ).append( "='" ).append( o.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Item16Group selectByPK(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field
        ).append( " = '" ).append( pk ).append( "'");
        
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (Item16Group)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Item16Group selectOrderHome() throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.item_16_group_id ).append( " like '%_OH'");

        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (Item16Group)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Item16Group selectByCode(String code) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.item_16_group_id
        ).append( " = '" ).append( code ).append( "'");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (Item16Group)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAllByName(String pk,String active) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where ");
        if(pk.trim().length() !=0)
        {
            sql.append("(" ).append(  dbObj.item_16_group_id
            ).append( " like '%" ).append( pk ).append( "%'" ).append( " or UPPER("
            ).append( dbObj.description ).append( ") like UPPER('%" ).append( pk ).append( "%')" ).append( ") and ");
        }
        sql.append( dbObj.active ).append( " = '" ).append( active ).append( "'" ).append( "order by "
        ).append( dbObj.item_16_group_id);
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)  return null;
        else    return v;
    }
    public Vector selectEqName(String pk,String active) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where ");
        if(pk.trim().length() !=0)
        {
            sql.append("(" ).append(  dbObj.item_16_group_id
            ).append( " like '" ).append( pk ).append( "'" ).append( " or UPPER("
            ).append( dbObj.description ).append( ") like UPPER('" ).append( pk ).append( "')" ).append( ") and ");
        }
        sql.append( dbObj.active ).append( " = '" ).append( active ).append( "'" ).append( "order by "
        ).append( dbObj.item_16_group_id);
        
        return eQuery(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAll() throws Exception
    {   Vector vc = new Vector();
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.active ).append( " = '1'");
//         ).append( " order by ").append(  dbObj.description;
        vc = eQuery(sql.toString());
        if(vc.size()==0)
            return null;
        else
            return vc;
        
    }
    
    /**
     *@deprecated henbe unused
     *
     */
    public Vector veQuery(String sql) throws Exception
    {
        ComboFix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
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
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector eQuery(String sql) throws Exception
    {
        Item16Group p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        while(rs.next())
        {
            p = new Item16Group();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.item_16_group_id = rs.getString(dbObj.item_16_group_id);
            p.description = rs.getString(dbObj.description);
            p.active = rs.getString(dbObj.active);
            
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
}
