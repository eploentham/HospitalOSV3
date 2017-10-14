package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
public class OrderServiceDB
{
    public ConnectionInf theConnectionInf;
    public OrderService dbObj;
    final public String idtable = "154";/*"146";*/
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public OrderServiceDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new OrderService();
        initConfig();
    }
    public OrderServiceDB()
    {
        theConnectionInf = null;
        dbObj = new OrderService();
        initConfig();
    }
    public boolean initConfig()
    {
        
        dbObj.table="b_order_service";
        dbObj.pk_field="b_order_service_id";
        dbObj.description   ="_order_service_description";
        dbObj.icd9_code   ="b_icd9_code";
        dbObj.order_code = "b_order_code";
        /*
        dbObj.table = "dx_template";
        dbObj.pk_field = "dx_template_id";
        dbObj.description = "description";
        dbObj.icd_type ="icd_type";
        dbObj.icd_code ="icd_code";
        dbObj.guide_after_dx ="guideafterdx";
         */
        return true;
    }
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    
    public int insert(OrderService o) throws Exception
    {
        
        OrderService p=o;
        p.generateOID(idtable);
        StringBuffer sql = new StringBuffer("insert into " ).append( dbObj.table ).append( " ("
        ).append( dbObj.pk_field
        ).append( " ,"	).append( dbObj.description
        ).append( " ,"  ).append( dbObj.icd9_code
        ).append( " ,"  ).append( dbObj.order_code
        ).append( " ) values ('"
        ).append( p.getObjectId()
        ).append( "','" ).append( p.description
        ).append( "','" ).append( p.icd9_code
        ).append( "','" ).append( p.icd9_code
        ).append( "','" ).append( p.order_code
        ).append( "')");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////////////////////////////*/
    public int update(OrderService o) throws Exception
    {
         OrderService p=o;
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
                ).append( dbObj.description ).append( "='" ).append( p.description
        ).append( "', " ).append( dbObj.icd9_code ).append( "='" ).append( p.icd9_code
        ).append( "', " ).append( dbObj.order_code ).append( "='" ).append( p.order_code
        ).append( "' where " ).append( dbObj.pk_field ).append( "='" ).append( p.getObjectId() ).append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public int delete(OrderService o) throws Exception
    {
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field ).append( "='" ).append( o.getObjectId() ).append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public OrderService selectByPK(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field
        ).append( " = '" ).append( pk ).append( "'");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (OrderService)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAllByName(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        
        if(pk.trim().length() !=0)
        {
            sql .append( " where" ).append( "( UPPER(" ).append(  dbObj.pk_field
            ).append( ") like UPPER('" ).append( pk.toUpperCase() ).append( "')" ).append( " or UPPER("
            ).append( dbObj.description ).append( ") like UPPER('" ).append( pk.toUpperCase() ).append( "')" ).append( ") ");
            
        }
        
        sql .append(  " order by " ).append( dbObj.description );
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public OrderService selectByName(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        
        if(pk.trim().length() !=0)
        {
            sql .append( " where" ).append( "(" ).append(  dbObj.description
            ).append( " = '" ).append( pk ).append( "') ");
            
        }
        
        sql .append(  " order by " ).append( dbObj.description );
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (OrderService)v.get(0);
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAll() throws Exception
    {   Vector vc = new Vector();
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table ).append( " order by ").append(
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
        OrderService p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        while(rs.next())
        {
            p = new OrderService();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);
            p.icd9_code = rs.getString(dbObj.icd9_code);
            p.order_code = rs.getString(dbObj.order_code);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector queryOrderService(String dx) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where "
        ).append( dbObj.pk_field ).append( " = '" ).append( dx ).append( "'");
        return eQuery(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
}
