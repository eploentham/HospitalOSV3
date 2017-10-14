/*
 * GuideDB.java
 *
 * Created on 4 สิงหาคม 2549, 16:44 น.
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
 * @author sumo
 */
public class GuideDB 
{
    
    public ConnectionInf theConnectionInf;
    public Guide dbObj;
    final public String idtable = "313";
    
    /** Creates a new instance of GuideDB */
    public GuideDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Guide();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table="b_guide";
        dbObj.pk_field="b_guide_id";
        dbObj.number="guide_number";
        dbObj.description="guide_description";
        dbObj.active="guide_active";
        
        return true;
    }
    public int insert(Guide o) throws Exception
    {
        Guide p=o;
        p.generateOID(idtable);
        StringBuffer sql = new StringBuffer("insert into " ).append( dbObj.table ).append( " ("
        ).append( dbObj.pk_field
        ).append( " ,"	).append( dbObj.number
        ).append( " ,"	).append( dbObj.description
        ).append( " ,"	).append( dbObj.active
        ).append( " ) values ('"
        ).append( p.getObjectId()
        ).append( "','" ).append( p.number
        ).append( "','" ).append( p.description
        ).append( "','" ).append( p.active
        ).append( "')");
        
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    public int update(Guide o) throws Exception
    {
        Guide p=o;
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
        ).append( dbObj.number ).append( "='" ).append( p.number
        ).append( "', " ).append( dbObj.description ).append( "='" ).append( p.description
        ).append( "', " ).append( dbObj.active ).append( "='" ).append( p.active
        ).append( "' where " ).append( dbObj.pk_field ).append( "='" ).append( p.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    
    public int delete(Guide o) throws Exception
    {
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field ).append( "='" ).append( o.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    
    public Guide selectByNumber(String code) throws Exception
    {
        Vector vc = new Vector();
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.number
        ).append( " = '" ).append( code ).append( "'");
        
        vc = eQuery(sql.toString());
        if(vc.size()==0)
            return null;
        else
            return (Guide)vc.get(0);
    }
    
    public Guide selectByName(String name) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where ( UPPER(" ).append( dbObj.description
        ).append( ") like UPPER('" ).append( name.trim() ).append( "'))");
        
        Vector v=eQuery(sql.toString());
        
        if(v.size()==0)
            return null;
        else
            return (Guide)v.get(0);
    }
    
    public Vector selectAllByName(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        
        if(pk.trim().length() !=0)
        {
            sql.append( " where" ).append( "( UPPER(" ).append(  dbObj.number
            ).append( ") like UPPER('%" ).append( pk.toUpperCase() ).append( "%') " ).append( " or UPPER("
            ).append( dbObj.description ).append( ") like UPPER('%" ).append( pk.toUpperCase() ).append( "%')" ).append( " )");
        }
        
        sql.append(  " order by " ).append( dbObj.number );
        
        Vector v=veQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public Vector selectByCodeName(String pk,String active) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where ");
        if(pk.trim().length() !=0)
        {
            sql.append( " ( UPPER(" ).append(  dbObj.number
            ).append( ") like UPPER('%" ).append( pk.toUpperCase() ).append( "%')" ).append( " or UPPER("
            ).append( dbObj.description  ).append( ") like UPPER('%" ).append( pk.toUpperCase() ).append( "%')" ).append( ") and ");
        }
        sql.append( dbObj.active ).append( " = '" ).append( active ).append( "'" ).append( " order by "
        ).append( dbObj.number);
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }

    public Vector selectAll() throws Exception
    {   
        Vector vc = new Vector();
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.active ).append( " = '1'"
        ).append( " order by ").append(dbObj.description);
        vc = veQuery(sql.toString());
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
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
        Guide p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        while(rs.next())
        {
            p = new Guide();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.number = rs.getString(dbObj.number);
            p.description = rs.getString(dbObj.description);
            p.active = rs.getString(dbObj.active);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
