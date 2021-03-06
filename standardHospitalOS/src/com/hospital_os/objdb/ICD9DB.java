/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java*/

package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;

import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;

public class ICD9DB
{
    public ConnectionInf theConnectionInf;
    public ICD9 dbObj;
    final public String idtable = "170";/*"159";*/
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public ICD9DB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new ICD9();
        initConfig();
    }
    
    public ICD9DB()
    {
        theConnectionInf = null;
        dbObj = new ICD9();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table="b_icd9";
        dbObj.pk_field="b_icd9_id";
        dbObj.icd9_id   ="icd9_number";
        dbObj.description   ="icd9_description";
        dbObj.other_description="icd9_other_description";
        dbObj.cost53="cost";
        dbObj.icd_10_tm="icd_10_tm";
        
        return true;
    }
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(ICD9 o) throws Exception
    {
        
        ICD9 p=o;
        p.generateOID(idtable);
        StringBuffer sql = new StringBuffer("insert into " ).append( dbObj.table ).append( " ("
        ).append( dbObj.pk_field
        
        ).append( " ,"	).append( dbObj.icd9_id
        ).append( " ,"	).append( dbObj.description
        ).append( " ,"	).append( dbObj.other_description
        ).append( " ,"	).append( dbObj.cost53
        ).append( " ,"	).append( dbObj.icd_10_tm
        ).append( " ) values ('"
        ).append( p.getObjectId()
        
        ).append( "','" ).append( p.icd9_id
        ).append( "','" ).append( p.description
        ).append( "','" ).append( p.other_description
        ).append( "','" ).append( p.cost53
        ).append( "','" ).append( p.icd_10_tm
        ).append( "')");
        
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    
    public int update(ICD9 o) throws Exception
    {
        ICD9 p=o;
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
                ).append( dbObj.icd9_id ).append( "='" ).append( p.icd9_id
        ).append( "', " ).append( dbObj.description ).append( "='" ).append( p.description
        ).append( "', " ).append( dbObj.other_description ).append( "='" ).append( p.other_description
        ).append( "', " ).append( dbObj.cost53 ).append( "='" ).append( p.cost53
        ).append( "', " ).append( dbObj.icd_10_tm ).append( "='" ).append( p.icd_10_tm
        ).append( "' where " ).append( dbObj.pk_field ).append( "='" ).append( p.getObjectId() ).append("'");
        
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public int delete(ICD9 o) throws Exception
    {
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field ).append( "='" ).append( o.getObjectId() ).append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public ICD9 selectByPK(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field
        ).append( " = '" ).append( pk ).append( "'");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (ICD9)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAll() throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table )
                .append( " order by " ).append( dbObj.icd9_id ).append( " limit 300 ");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public ICD9 selectByCode(String code) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.icd9_id
        ).append( " = '" ).append( code ).append( "'");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (ICD9)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public ICD9 selectById(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.icd9_id
        ).append( " ilike '%" ).append( pk ).append( "%'");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (ICD9)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectByIdName(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        if(pk.trim().length() !=0)
        {
            sql.append( " where" ).append( "(" ).append(  dbObj.icd9_id ).append(" ilike '%" ).append( pk ).append( "%'"
            ).append( " or " ).append( dbObj.description ).append( " ilike '%" ).append( pk ).append( "%'" ).append( " "
            ).append( " or " ).append( dbObj.other_description ).append( " ilike '%" ).append( pk ).append( "%'" ).append( ") ");
        }
        sql .append(  " order by " ).append( dbObj.icd9_id  ).append( " limit 300 ");
        Vector v=eQuery(sql.toString());
        
        if(v.size()==0)
            return null;
        else
            return v;
    }/*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectLikeCodeName(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
            ).append( " where" ).append( "(" ).append(  dbObj.icd9_id ).append(" ilike '" ).append( pk ).append( "'"
            ).append( " or " ).append( dbObj.description ).append( " ilike '" ).append( pk ).append( "'" ).append( ") "
            ).append( " or " ).append( dbObj.other_description ).append( " ilike '" ).append( pk ).append( "'" ).append( ") "
            ).append(  " order by " ).append( dbObj.icd9_id) ;
        return eQuery(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector eQuery(String sql) throws Exception
    {
        ICD9 p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        
        int i=0;
        while(rs.next())
        {
            p = new ICD9();
            p.setObjectId(rs.getString(dbObj.pk_field));
            
            p.icd9_id = rs.getString(dbObj.icd9_id);
            p.description = rs.getString(dbObj.description);
            p.other_description = rs.getString(dbObj.other_description);
            p.cost53 = rs.getString(dbObj.cost53);
            p.icd_10_tm = rs.getString(dbObj.icd_10_tm);
            list.add(p);
            i++;
            if(i>100) break;
            
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public ICD9 selectEqCode(String pk) throws Exception 
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.icd9_id
        ).append( " = '" ).append( pk ).append( "'");

        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (ICD9)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    // ������¡�� Icd9 �¤��Ҩҡ���� Icd9
    // input String �ͧ�Ӥ��¤��Ҩҡ���� Icd9
    // return Vector �ͧ��¡�÷�������
    public Vector selectAllByName(String pk) throws Exception 
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        if(pk.trim().length() !=0)
        {
            sql .append( " where" ).append( "(" ).append(  dbObj.icd9_id ).append(" ilike '%" ).append( pk ).append( "%'"
            ).append( " or " ).append( dbObj.description ).append( " ilike '%" ).append( pk ).append( " %'" ).append( ") ");
        }
        sql .append(  " order by " ).append( dbObj.icd9_id ).append(" limit 300 ");
        Vector v=eQuery(sql.toString());
        
        if(v.size()==0)
            return null;
        else
            return v;
    }
}
