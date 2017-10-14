/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java*/
package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
public class CategoryGroupItemDB
{
    public ConnectionInf theConnectionInf;
    public CategoryGroupItem dbObj;
    final private String idtable = "130";/*"124";*/
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public CategoryGroupItemDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new CategoryGroupItem();
        initConfig();
    }
    
    public CategoryGroupItemDB()
    {
        theConnectionInf = null;
        dbObj = new CategoryGroupItem();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="b_item_subgroup";
        dbObj.pk_field="b_item_subgroup_id";
        dbObj.category_group_item_id   ="item_subgroup_number";
        dbObj.description   ="item_subgroup_description";
        dbObj.category_group_code   ="f_item_group_id";
        dbObj.active ="item_subgroup_active";
        /*
        dbObj.table = "category_group_item";
        dbObj.pk_field = "key_id";
        dbObj.category_group_item_id = "category_group_item_id";
        dbObj.description = "description";
        dbObj.category_group_code = "category_group_code";
        dbObj.active = "active";
         */
        return true;
    }
    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(CategoryGroupItem o) throws Exception
    {
        CategoryGroupItem p=o;
        p.generateOID(idtable);
        StringBuffer sql = new StringBuffer("insert into " ).append( dbObj.table ).append( " ("
        ).append( dbObj.pk_field
        ).append( " ,"	).append( dbObj.category_group_item_id
        ).append( " ,"	).append( dbObj.description
        ).append( " ,"	).append( dbObj.category_group_code
        ).append( " ,"	).append( dbObj.active
        ).append( " ) values('"
        ).append( p.getObjectId()
        ).append( "','" ).append( p.category_group_item_id
        ).append( "','" ).append( p.description
        ).append( "','" ).append( p.category_group_code
        ).append( "','" ).append( p.active
        ).append( "')");
        return theConnectionInf.eUpdate(sql.toString());
    }
    public int update(CategoryGroupItem o) throws Exception
    {
        CategoryGroupItem p=o;
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
        ).append( dbObj.category_group_item_id ).append( "='" ).append( p.category_group_item_id
        ).append( "', " ).append( dbObj.description ).append( "='" ).append( p.description
        ).append( "', " ).append( dbObj.category_group_code ).append( "='" ).append( p.category_group_code
        ).append( "', " ).append( dbObj.active ).append( "='" ).append( p.active
        ).append( "' where " ).append( dbObj.pk_field ).append( "='" ).append( p.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public int delete(CategoryGroupItem o) throws Exception
    {
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field ).append( "='" ).append( o.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public CategoryGroupItem selectByPK(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field
        ).append( " = '" ).append( pk ).append( "'");
        
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (CategoryGroupItem)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public CategoryGroupItem selectByCode(String code) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.category_group_item_id
        ).append( " = '" ).append( code ).append( "'");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (CategoryGroupItem)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectByCategoryGroupCode(String cgc) throws Exception
    {
        return selectByCategoryGroupCode(cgc,"");
    }
    public Vector selectByCategoryGroupCode(String cgc,String active) throws Exception
    {        
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        if(!cgc.equals(""))
           sql.append(" where " ).append( dbObj.category_group_code ).append( " = '" ).append( cgc ).append( "'");
        
        if(!active.equals(""))
            sql.append(" and " ).append( dbObj.active ).append( " = '" ).append( active ).append( "'");
        
        sql.append(" order by " ).append( dbObj.category_group_item_id);
        return eQuery(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAllByName(String pk,String active) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where ");
        if(pk.trim().length() !=0)
        {
            sql.append("(" ).append(  dbObj.category_group_item_id
            ).append( " like '%" ).append( pk ).append( "%'" ).append( " or UPPER("
            ).append( dbObj.description ).append( ") like UPPER('%" ).append( pk ).append( "%')" ).append( ") and ");
        }
        sql.append( dbObj.active ).append( " = '" ).append( active ).append( "'" ).append( "order by "
        ).append( dbObj.category_group_item_id);
        
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
            sql.append("(" ).append(  dbObj.category_group_item_id ).append( " like '" ).append( pk ).append( "'" ).append( "" ).append(
            " or UPPER(" ).append( dbObj.description ).append( ") like UPPER('" ).append( pk ).append( "')" ).append( ") and ");
        }
        sql.append( dbObj.active ).append( " = '" ).append( active ).append( "'" ).append( "order by "
        ).append( dbObj.category_group_item_id);
        return eQuery(sql.toString());
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAll() throws Exception
    {   Vector vc = new Vector();
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.active ).append( " = '1'"
         ).append( " order by ").append(  dbObj.description);
        vc = eQuery(sql.toString());
        if(vc.size()==0)
            return null;
        else
            return vc;
        
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectDrugAndSupply() throws Exception
    {
        Vector vc = new Vector();
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.category_group_code
        ).append( " = '1' or " ).append( dbObj.category_group_code
        ).append( " = '4' order by " ).append( dbObj.category_group_item_id);
        
        vc = eQuery(sql.toString());
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    
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
        CategoryGroupItem p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        while(rs.next())
        {
            p = new CategoryGroupItem();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.category_group_item_id = rs.getString(dbObj.category_group_item_id);
            p.description = rs.getString(dbObj.description);
            p.category_group_code = rs.getString(dbObj.category_group_code);
            p.active = rs.getString(dbObj.active);
            
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
}
