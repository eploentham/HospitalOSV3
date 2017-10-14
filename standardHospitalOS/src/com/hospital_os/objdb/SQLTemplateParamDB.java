/*
 * SQLTemplateParamDB.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2547, 14:30 ¹.
 */

package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  tong
 */
public class SQLTemplateParamDB implements TemplateDB
{
    
    public ConnectionInf theConnectionInf;
    public SQLTemplateParam dbObj;
    final public String idtable = "238";/*"335";
*/
    /** Creates a new instance of SQLTemplateParamDB */
    public SQLTemplateParamDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new SQLTemplateParam();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="b_sql_template_by_date";
        dbObj.pk_field="b_sql_template_by_date_id";
        dbObj.code   ="sql_template_by_date_number";
        dbObj.description   ="sql_template_by_date_description";
        dbObj.active   ="sql_template_by_date_active";
        dbObj.sql = "sql_template_by_date_sql";
        /*
         
        dbObj.table = "sql_template_param";
         
        dbObj.pk_field = "key_id";
        dbObj.code ="code";
        dbObj.description = "description";
        dbObj.sql = "sql";
        dbObj.active ="active";
         */
        return true;
    }
    
    public int delete(Object o) throws Exception
    {
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field ).append( "='" ).append( ((SQLTemplateParam)o).getObjectId() ).append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    
    public int insert(Object o) throws Exception
    {
        
        SQLTemplateParam p= (SQLTemplateParam)o;
        p.generateOID(idtable);
        StringBuffer sql = new StringBuffer("insert into " ).append( dbObj.table ).append( " ("
        ).append( dbObj.pk_field
        ).append( " ,"  ).append( dbObj.code
        ).append( " ,"  ).append( dbObj.description
        ).append( " ,"	).append( dbObj.sql
        ).append( " ,"	).append( dbObj.active
        ).append( " ) values ('"
        ).append( p.getObjectId()
        ).append( "','" ).append( p.code
        ).append( "','" ).append( p.description
        ).append( "','" ).append( p.sql
        ).append( "','" ).append( p.active
        
        ).append( "')");
        
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    
    public Object selectByPK(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field ).append( " = '" ).append( pk ).append( "'"
        ).append( " and " ).append( dbObj.active).append( " = '" ).append( "1" ).append( "'" );
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (SQLTemplateParam)v.get(0);
    }
    
    public Vector selectAll() throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.active).append( " = '" ).append( "1" ).append( "'" );
        Vector v=veQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public int update(Object o) throws Exception
    {
        SQLTemplateParam p= (SQLTemplateParam)o;
        
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
        ).append( dbObj.description ).append( "='" ).append( p.description
        ).append( "', " ).append( dbObj.code ).append( "='" ).append( p.code
        ).append( "', " ).append( dbObj.sql ).append( "='" ).append( p.sql
        ).append( "', " ).append( dbObj.active ).append( "='" ).append( p.active
        
        ).append( "' where " ).append( dbObj.pk_field ).append( "='" ).append( p.getObjectId() ).append("'");
        
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    public Object listByCode(String code) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.code ).append( " = '" ).append( code ).append( "'");
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (SQLTemplateParam)v.get(0);
    }
    public Vector listBySearch(String search,String active) throws Exception
    {
        StringBuffer sql = new StringBuffer( "Select * from " ).append( dbObj.table ).append( " where "  );
        if(!search.equalsIgnoreCase(""))
        {  sql.append( "UPPER(" ).append( dbObj.code ).append( ") like UPPER('%" ).append( search ).append( "%')"
           ).append( " or UPPER(" ).append( dbObj.description ).append( ") like UPPER('%" ).append( search ).append( "%')"
           ).append( " and " );
        }
        sql.append( dbObj.active ).append( "='" ).append( active ).append( "'");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////
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
    
    public Vector eQuery(String sql) throws Exception
    {
        SQLTemplateParam p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        while(rs.next())
        {
            p = new SQLTemplateParam();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.code = rs.getString(dbObj.code);
            p.description = rs.getString(dbObj.description);
            p.sql = rs.getString(dbObj.sql.toString());
            p.active = rs.getString(dbObj.active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    
}


