/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java*/
package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
public class VitalTemplateDB
{
    public ConnectionInf theConnectionInf;
    public VitalTemplate dbObj;
    final public String idtable = "261";/*"222";*/
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public VitalTemplateDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new VitalTemplate();
        initConfig();
    }
    
    public VitalTemplateDB()
    {
        theConnectionInf=null;
        dbObj = new VitalTemplate();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="b_template_sign_symptom";
        dbObj.pk_field="b_template_sign_symptom_id";
        dbObj.vital_template_id   ="template_sign_symptom_number";
        dbObj.description   ="template_sign_symptom_description";
        dbObj.service_point="b_service_point_id";
        /*
        dbObj.table = "vital_template";
        dbObj.pk_field = "key_id";
        dbObj.vital_template_id = "vital_template_id";
        dbObj.description = "description";
        dbObj.service_point ="service_point";
         */
        return true;
    }
    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(VitalTemplate o) throws Exception
    {
        VitalTemplate p=o;
        p.generateOID(idtable);
        StringBuffer sql = new StringBuffer("insert into " ).append( dbObj.table ).append( " ("
        ).append( dbObj.pk_field
        ).append( " ,"	).append( dbObj.vital_template_id
        ).append( " ,"	).append( dbObj.description
        ).append( " ,"  ).append( dbObj.service_point
        ).append( " ) values ('"
        ).append( p.getObjectId()
        ).append( "','" ).append( p.vital_template_id
        ).append( "','" ).append( p.description
        ).append( "','" ).append( p.service_point
        ).append( "')");
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*/////////////////////////////////////////////////////////////*/
    public int update(VitalTemplate o) throws Exception
    {
        VitalTemplate p=o;
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
        ).append( dbObj.vital_template_id ).append( "='" ).append( p.vital_template_id
        ).append( "', " ).append( dbObj.description ).append( "='" ).append( p.description
        ).append( "', " ).append( dbObj.service_point ).append( "='" ).append( p.service_point
        ).append( "' where " ).append( dbObj.pk_field ).append( "='" ).append( p.getObjectId() ).append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public int delete(VitalTemplate o) throws Exception
    {
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field ).append( "='" ).append( o.getObjectId() ).append("'");
        
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public VitalTemplate selectByPK(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field
        ).append( " = '" ).append( pk ).append( "'");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (VitalTemplate)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public VitalTemplate selectByCodeServicePoint(String code,String sp) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.service_point
        ).append( " = '" ).append( sp
        ).append( "' and "  ).append( dbObj.vital_template_id
        ).append( " = '" ).append( code ).append( "'");
        
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (VitalTemplate)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAllByName(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        
        if(pk.trim().length() !=0)
        {
            sql .append( " where" ).append( "( UPPER(" ).append(  dbObj.vital_template_id
            ).append( ") like UPPER('" ).append( pk.toUpperCase() ).append( "') " ).append( " or UPPER("
            ).append( dbObj.description ).append( ") like UPPER('" ).append( pk.toUpperCase() ).append( "')" ).append( " )");
        }
        
        sql .append(  " order by " ).append( dbObj.vital_template_id) ;
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAllByServicePoint(String pk,String point) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        
        if(pk.trim().length() !=0)
        {
            sql .append( " where" ).append( "(( UPPER(" ).append(  dbObj.vital_template_id
            ).append( ") like UPPER('" ).append( pk.toUpperCase() ).append( "') " ).append( " or UPPER("
            ).append( dbObj.description ).append( ") like UPPER('" ).append( pk.toUpperCase() ).append( "')" ).append( ") and ("
            ).append( dbObj.service_point ).append( " = '" ).append( point ).append( "' ))");
        }
        
        sql .append(  " order by " ).append( dbObj.vital_template_id );
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAll() throws Exception
    {   Vector vc = new Vector();
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table ).append( " order by ").append(
        dbObj.vital_template_id);
        vc = veQuery(sql.toString());
        if(vc.size()==0)
            return null;
        else
            return vc;
        
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    
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
        VitalTemplate p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        while(rs.next())
        {
            p = new VitalTemplate();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.vital_template_id = rs.getString(dbObj.vital_template_id);
            p.description = rs.getString(dbObj.description);
            p.service_point = rs.getString(dbObj.service_point);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
        
    public void alterTableFieldToV2B82()
    {
        StringBuffer sql = new StringBuffer( "");
                
        try{
            sql.append("ALTER TABLE " ).append( dbObj.table ).append( " RENAME " ).append( dbObj.description ).append( " TO " ).append(  dbObj.description ).append( "1" );
            this.theConnectionInf.eUpdate(sql.toString());
        }
        catch(Exception ex)
        {
        }
        try{
            sql .append("ALTER TABLE  " ).append( dbObj.table ).append( "  ADD COLUMN  " ).append( dbObj.description ).append( " varchar (3000) ");
            this.theConnectionInf.eUpdate(sql.toString());
        }
        catch(Exception ex)
        {
        }
        try{
            sql .append("UPDATE   " ).append( dbObj.table ).append( " SET " ).append( dbObj.description ).append( " = " ).append(  dbObj.description ).append( "1"  );
            this.theConnectionInf.eUpdate(sql.toString());
        }
        catch(Exception ex)
        {
        }
        try{
            sql .append( "Alter table   " ).append( dbObj.table ).append( " drop column  " ).append(  dbObj.description ).append( "1"  );
            this.theConnectionInf.eUpdate(sql.toString());
        }
        catch(Exception ex)
        {
        }
      
    }
}
