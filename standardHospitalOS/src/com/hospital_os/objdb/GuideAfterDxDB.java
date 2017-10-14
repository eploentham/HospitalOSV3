/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java*/

package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;

import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;

public class GuideAfterDxDB
{
    public ConnectionInf theConnectionInf;
    public GuideAfterDx dbObj;
    final public String idtable = "266";
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public GuideAfterDxDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new GuideAfterDx();
        initConfig();
    }
    
    public GuideAfterDxDB()
    {
        theConnectionInf=null;
        dbObj = new GuideAfterDx();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="b_template_discharge_advice";
        dbObj.pk_field="b_template_discharge_advice_id";
        dbObj.guide_id   ="template_discharge_advice_number";
        dbObj.description="template_discharge_advice_description";
        /*
         
        dbObj.table = "guide_after_dx";
        dbObj.pk_field = "key_id";
         
        dbObj.guide_id = "guide_id";
        dbObj.description = "description";
         */
        return true;
    }
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    
    public int insert(GuideAfterDx o) throws Exception
    {
        GuideAfterDx p=o;
        p.generateOID(idtable);
        StringBuffer sql = new StringBuffer("insert into " ).append( dbObj.table ).append( " ("
        ).append( dbObj.pk_field
        ).append( " ,"	).append( dbObj.guide_id
        ).append( " ,"	).append( dbObj.description
        ).append( " ) values ('"
        ).append( p.getObjectId()
        ).append( "','" ).append( p.guide_id
        ).append( "','" ).append( p.description
        ).append( "')");
        
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////////////////*/
    public int update(GuideAfterDx o) throws Exception
    {
        GuideAfterDx p=o;
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
        ).append( dbObj.guide_id ).append( "='" ).append( p.guide_id
        ).append( "', " ).append( dbObj.description ).append( "='" ).append( p.description
        ).append( "' where " ).append( dbObj.pk_field ).append( "='" ).append( p.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public int delete(GuideAfterDx o) throws Exception
    {
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field ).append( "='" ).append( o.getObjectId() ).append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
   /*public GuideAfterDx selectByPK(String pk) throws Exception
   {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
            ).append( " where " ).append( dbObj.pk_field
            ).append( " = '" ).append( pk ).append( "'");
    
                Vector v=eQuery(sql.toString());
                if(v.size()==0)
                        return null;
                else
                        return (GuideAfterDx)v.get(0);
   }*/
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public GuideAfterDx selectByCode(String code) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.guide_id
        ).append( " = '" ).append( code ).append( "'");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (GuideAfterDx)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
   /*  public Vector selectAllByName(String pk) throws Exception
   {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table;
    
            if(pk.trim().length() !=0)
            {
                sql.append( " where" ).append( "(" ).append(  dbObj.pk_field
                      ).append( " like '%" ).append( pk ).append( "%'" ).append( " or "
                      ).append( dbObj.description ).append( " like '%" ).append( pk ).append( "%'" ).append( ") ");
    
            }
    
            sql.append(  " order by " ).append( dbObj.vital_template_id ;
    
                Vector v=eQuery(sql.toString());
                if(v.size()==0)
                        return null;
                else
                        return v;
   }
    */
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAll(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        if(pk.trim().length() !=0)
        {
            sql.append( " where " ).append( dbObj.pk_field
            ).append( " like '" ).append( pk ).append( "'" ).append( " or "
            ).append( dbObj.description ).append( " like '" ).append( pk ).append( "'");
        }
        sql.append(  " order by " ).append( dbObj.guide_id );
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    
   /* public Vector selectAll() throws Exception
    {   Vector vc = new Vector();
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table ).append( " order by ").append(
                    dbObj.vital_template_id;
        vc = veQuery(sql.toString());
        if(vc.size()==0)
            return null;
        else
            return vc;
    
    }
    
//////////////////////////////////////////////////////////////////////
    
    public Vector veQuery(String sql) throws Exception
        {
        ComboFix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
    
        while(rs.next()) {
               p = new ComboFix();
               p.code = rs.getString(dbObj.pk_field);
               p.name = rs.getString(dbObj.description);
               list.add(p);
           }
        rs.close();
        return list;
        }*/
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector eQuery(String sql) throws Exception
    {
        GuideAfterDx p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        
        while(rs.next())
        {
            p = new GuideAfterDx();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.guide_id = rs.getString(dbObj.guide_id);
            p.description = rs.getString(dbObj.description);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    
}
