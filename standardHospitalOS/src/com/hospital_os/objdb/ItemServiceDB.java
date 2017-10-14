/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java*/
package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
public class ItemServiceDB
{
    public ConnectionInf theConnectionInf;
    public ItemService dbObj;
    final public String idtable = "154";/*"146";*/
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public ItemServiceDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new ItemService();
        initConfig();
    }
    public ItemServiceDB()
    {
        theConnectionInf = null;
        dbObj = new ItemService();
        initConfig();
    }
    public boolean initConfig()
    {
        
        dbObj.table="b_item_service";
        dbObj.pk_field="b_item_service_id";
        dbObj.description   ="item_service_description";
        dbObj.icd9_code   ="icd9_number";
        dbObj.item_id = "b_item_id";
        dbObj.active = "item_service_active";
        dbObj.record_date_time = "item_service_record_date_time";
        return true;
    }
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    
    public int insert(ItemService o) throws Exception
    {
        ItemService p=o;
        p.generateOID(idtable);
        StringBuffer sql = new StringBuffer("insert into " ).append( dbObj.table ).append( " ("
        ).append( dbObj.pk_field
        ).append( " ,"	).append( dbObj.description
        ).append( " ,"  ).append( dbObj.icd9_code
        ).append( " ,"  ).append( dbObj.item_id
        ).append( " ,"  ).append( dbObj.record_date_time
        ).append( " ,"  ).append( dbObj.active
        ).append( " ) values ('"
        ).append( p.getObjectId()
        ).append( "','" ).append( p.description
        ).append( "','" ).append( p.icd9_code
        ).append( "','" ).append( p.item_id
        ).append( "','" ).append( p.record_date_time
        ).append( "','" ).append( p.active
        ).append( "')");
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////////////////////////////*/
    public int update(ItemService o) throws Exception
    {
        ItemService p=o;
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
        ).append( dbObj.description ).append( "='" ).append( p.description
        ).append( "', " ).append( dbObj.icd9_code ).append( "='" ).append( p.icd9_code
        ).append( "', " ).append( dbObj.item_id ).append( "='" ).append( p.item_id
        ).append( "', " ).append( dbObj.record_date_time ).append( "='" ).append( p.record_date_time
        ).append( "', " ).append( dbObj.active ).append( "='" ).append( p.active
        ).append( "' where " ).append( dbObj.pk_field ).append( "='" ).append( p.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    public int updateByItemID(String id) throws Exception
    {
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
        ).append( dbObj.active ).append( "='0' where " ).append( dbObj.item_id
        ).append( " = '" ).append( id ).append( "'" );
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public int delete(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field ).append( "='" ).append( pk ).append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public ItemService selectByPK(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field
        ).append( " = '" ).append( pk ).append( "' and " + dbObj.active + " = '1'");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (ItemService)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAllByName(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        
        if(pk.trim().length() !=0)
        {
            sql.append( " where" ).append( "( UPPER(" ).append(  dbObj.pk_field
            ).append( ") like UPPER('" ).append( pk.toUpperCase() ).append( "')" ).append( " or UPPER("
            ).append( dbObj.description ).append( ") like UPPER('" ).append( pk.toUpperCase() ).append( "')" 
            ).append( ")  and " + dbObj.active + " = '1'");
            
        }
        
        sql.append(  " order by " ).append( dbObj.description );
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public ItemService selectByName(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        
        if(pk.trim().length() !=0)
        {
            sql.append( " where" ).append( "(" ).append(  dbObj.description
            ).append( " = '" ).append( pk ).append( "') and " + dbObj.active + " = '1'");
            
        }
        sql.append(  " order by " ).append( dbObj.description );
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (ItemService)v.get(0);
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAll() throws Exception
    {   Vector vc = new Vector();
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table ).append
                ( " where " + dbObj.active + " = '1' order by ").append(
        dbObj.description);
        vc = veQuery(sql.toString());
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
        ItemService p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        while(rs.next())
        {
            p = new ItemService();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);
            p.icd9_code = rs.getString(dbObj.icd9_code);
            p.item_id = rs.getString(dbObj.item_id);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector queryItemService(String dx) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where "
        ).append( dbObj.pk_field ).append( " = '" ).append( dx ).append( "' and " + dbObj.active + " = '1'");
        return eQuery(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectByItem(String item) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.item_id
        ).append( " = '" ).append( item ).append( "' and " + dbObj.active + " = '1'");
        
        return eQuery(sql.toString());
    }
}
