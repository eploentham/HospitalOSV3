/*
 * DrugStandardDB.java
 *
 * Created on 14 ¡’π“§¡ 2549, 10:02 π.
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

public class DrugStandardDB
{
    public ConnectionInf theConnectionInf;
    public DrugStandard dbObj;
    final public String idtable = "282";
    
    /** Creates a new instance of DrugStandardDB */
    public DrugStandardDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new DrugStandard();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "b_item_drug_standard";        
        dbObj.pk_field = "b_item_drug_standard_id";
        dbObj.number = "item_drug_standard_number";
        dbObj.description = "item_drug_standard_description";
        dbObj.active = "item_drug_standard_active";        
        return true;
    }
    
    public int insert(DrugStandard o) throws Exception
    {
        DrugStandard p=o;
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
    
    public int update(DrugStandard o) throws Exception
    {
        DrugStandard p=o;
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
        ).append( dbObj.number ).append( "='" ).append( p.number
        ).append( "', " ).append( dbObj.description ).append( "='" ).append( p.description
        ).append( "', " ).append( dbObj.active ).append( "='" ).append( p.active
        ).append( "' where " ).append( dbObj.pk_field ).append( "='" ).append( p.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    
    public int delete(DrugStandard o) throws Exception
    {
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field ).append( "='" ).append( o.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
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
    
    public DrugStandard selectByNumber(String code) throws Exception
    {
        Vector vc = new Vector();
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.number
        ).append( " = '" ).append( code ).append( "'");
        
        vc = eQuery(sql.toString());
        if(vc.size()==0)
            return null;
        else
            return (DrugStandard)vc.get(0);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        DrugStandard p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        while(rs.next())
        {
            p = new DrugStandard();
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
