/*
 * BodyOrganDB.java
 *
 * Created on 7 เมษายน 2549, 9:50 น.
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
public class BodyOrganDB
{
    public ConnectionInf theConnectionInf;
    public BodyOrgan dbObj;
    final public String idtable = "287";
    
    /** Creates a new instance of BodyOrganDB */
    public BodyOrganDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new BodyOrgan();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table="b_body_organ";
        dbObj.pk_field="b_body_organ_id";
        dbObj.number="body_organ_number";
        dbObj.description="body_organ_description";
        dbObj.active="body_organ_active";
        dbObj.max_number="max_number";
        
        return true;
    }
    public int insert(BodyOrgan o) throws Exception
    {
        BodyOrgan p=o;
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
    public int update(BodyOrgan o) throws Exception
    {
        BodyOrgan p=o;
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
        ).append( dbObj.number ).append( "='" ).append( p.number
        ).append( "', " ).append( dbObj.description ).append( "='" ).append( p.description
        ).append( "', " ).append( dbObj.active ).append( "='" ).append( p.active
        ).append( "' where " ).append( dbObj.pk_field ).append( "='" ).append( p.getObjectId() ).append("'");

        return theConnectionInf.eUpdate(sql.toString());
    }
    
    public int delete(BodyOrgan o) throws Exception
    {
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field ).append( "='" ).append( o.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    
    public BodyOrgan selectByNumber(String code) throws Exception
    {
        Vector vc = new Vector();
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.number
        ).append( " = '" ).append( code ).append( "'");
        
        vc = eQuery(sql.toString());
        if(vc.size()==0)
            return null;
        else
            return (BodyOrgan)vc.get(0);
    }
    
    public BodyOrgan selectByName(String name) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where ( UPPER(" ).append( dbObj.description
        ).append( ") like UPPER('" ).append( name.trim() ).append( "'))");
        
        Vector v=eQuery(sql.toString());
        
        if(v.size()==0)
            return null;
        else
            return (BodyOrgan)v.get(0);
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
        
        sql.append(  " order by " ).append( dbObj.number) ;
        
        Vector v=veQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public Vector selectAllByNameActive(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        
        if(pk.trim().length() !=0)
        {
            sql.append( " where" ).append( "( UPPER(" ).append(  dbObj.number
                    ).append( ") like UPPER('%" ).append( pk.toUpperCase() ).append( "%') " ).append( " or UPPER("
                    ).append( dbObj.description ).append( ") like UPPER('%" ).append( pk.toUpperCase() ).append( "%'))");
        }
        sql.append( " and " ).append( dbObj.active ).append( " = '1' order by " ).append( dbObj.number );
        
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
    
    public String selectMaxCode() throws Exception
    {
        StringBuffer sql = new StringBuffer( "select max(" ).append( dbObj.number ).append( ") as max_number from " ).append( dbObj.table);
        
        Vector v = queryMaxCode(sql.toString());
        if(v.size()==0)
            return null;
        else
            return ((BodyOrgan)v.get(0)).number;
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
    
    public Vector eQuery(String sql) throws Exception
    {
        BodyOrgan p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        while(rs.next())
        {
            p = new BodyOrgan();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.number = rs.getString(dbObj.number);
            p.description = rs.getString(dbObj.description);
            p.active = rs.getString(dbObj.active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public Vector queryMaxCode(String sql) throws Exception
    {
        BodyOrgan p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        while(rs.next())
        {
            p = new BodyOrgan();
            p.number = rs.getString(dbObj.max_number);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
