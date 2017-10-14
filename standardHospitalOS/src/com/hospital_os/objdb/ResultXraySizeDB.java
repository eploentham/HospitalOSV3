/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java
*/
/**
 * tong
 *
 *
 */
package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;

import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;

public class ResultXraySizeDB
{
    public ConnectionInf theConnectionInf;
    public ResultXraySize dbObj;
    final public String idtable = "234";

    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public ResultXraySizeDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new ResultXraySize();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table="t_result_xray_size";
        dbObj.pk_field="t_result_xray_size_id";
        dbObj.result_xray_id   ="t_result_xray_id";
        dbObj.film_size   ="f_xray_film_size_id";
        dbObj.num_film   ="xray_film_amount";
        dbObj.user   ="result_xray_size_staff_record";
        dbObj.active   ="result_xray_size_active";
        dbObj.price="result_xray_size_price";
        dbObj.add_order ="result_xray_size_add_order";
        dbObj.visit_id   ="t_visit_id";
        dbObj.order_item_id ="t_order_id";
        dbObj.damaging_film = "result_xray_size_damaging_xray_film_amount";
        return true;
    }
    
    public static void updateField(ResultXraySize obj)
    {
        obj.price = Constant.getSDouble(obj.price);
    }    
    
    public int insert(ResultXraySize p) throws Exception
    {
        updateField(p);
        p.generateOID(idtable);
        StringBuffer sql = new StringBuffer("insert into " ).append( dbObj.table ).append( " (" ).append( dbObj.pk_field
        ).append( " ,"	).append( dbObj.result_xray_id
        ).append( " ,"	).append( dbObj.visit_id
        ).append( " ,"	).append( dbObj.order_item_id
        ).append( " ,"	).append( dbObj.film_size
        ).append( " ,"	).append( dbObj.num_film
        ).append( " ,"	).append( dbObj.user
        ).append( " ,"	).append( dbObj.active
        ).append( " ,"	).append( dbObj.price
        ).append( " ,"	).append( dbObj.add_order
        ).append( " ,"	).append( dbObj.damaging_film
        ).append( " ) values ('"        ).append( p.getObjectId()
        ).append( "','" ).append( p.result_xray_id
        ).append( "','" ).append( p.visit_id
        ).append( "','" ).append( p.order_item_id
        ).append( "','" ).append( p.film_size
        ).append( "','" ).append( p.num_film
        ).append( "','" ).append( p.user
        ).append( "','" ).append( p.active
        ).append( "'," ).append( p.price       //ดูดีดีนะว่ามันเป็น double  by  henbe
        ).append( ",'" ).append( p.add_order
        ).append( "','" ).append( p.damaging_film
        ).append( "')");
        
        
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    
    public int update(ResultXraySize p) throws Exception
    {
        updateField(p);
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
        ) .append( dbObj.result_xray_id ).append( "='" ).append( p.result_xray_id
        ).append( "', " ).append( dbObj.visit_id ).append( "='" ).append( p.visit_id
        ).append( "', " ).append( dbObj.order_item_id ).append( "='" ).append( p.order_item_id
        ).append( "', " ).append( dbObj.film_size ).append( "='" ).append( p.film_size
        ).append( "', " ).append( dbObj.num_film ).append( "='" ).append( p.num_film
        ).append( "', " ).append( dbObj.user ).append( "='" ).append( p.user
        ).append( "', " ).append( dbObj.active ).append( "='" ).append( p.active
        ).append( "', " ).append( dbObj.price ).append( "=" ).append( p.price       //ดูดีดีนะว่ามันเป็น double  by  henbe
        ).append( ", " ).append( dbObj.add_order ).append( "='" ).append( p.add_order
        ).append( "',"	).append( dbObj.damaging_film ).append( "='" ).append( p.damaging_film
        
        ).append( "' where " ).append( dbObj.pk_field ).append( "='" ).append( p.getObjectId() ).append("'");

        
        return theConnectionInf.eUpdate(sql.toString());
    }
    
    /*////////////////////////////////////////////////////////////////////////////
*/
    public int delete(ResultXraySize o) throws Exception
    {
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field ).append( "='" ).append( o.getObjectId() ).append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    
    /*////////////////////////////////////////////////////////////////////////////
*/
    public ResultXraySize selectByPK(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field
        ).append( " = '" ).append( pk ).append( "'");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (ResultXraySize)v.get(0);
    }
    
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector selectByResultXRayID(String result_xray_id) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.result_xray_id
        ).append( " = '" ).append( result_xray_id ).append( "'"
        ).append( " and " ).append( dbObj.active ).append( " = '" ).append( "1" ).append( "'");
        return eQuery(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector selectAll() throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector eQuery(String sql) throws Exception
    {
        ResultXraySize p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        
        while(rs.next())
        {
            p = new ResultXraySize();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.visit_id = rs.getString(dbObj.visit_id);
            p.order_item_id= rs.getString(dbObj.order_item_id);
            p.result_xray_id = rs.getString(dbObj.result_xray_id);
            p.film_size = rs.getString(dbObj.film_size);
            p.num_film = rs.getString(dbObj.num_film);
            p.user = rs.getString(dbObj.user);
            p.active = rs.getString(dbObj.active);
            p.price = rs.getString(dbObj.price);
            p.add_order = rs.getString(dbObj.add_order);
            p.damaging_film = rs.getString(dbObj.damaging_film);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    
}
