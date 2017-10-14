/*
 * ResultXRayDB.java
 *
 * Created on 18 ตุลาคม 2546, 18:45 น.
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
public class ResultXRayDB
{
    public ConnectionInf theConnectionInf;
    public ResultXRay dbObj;
    final public String idtable = "232";/*"203";*/
    /** Creates a new instance of ResultXRayDB */
    public ResultXRayDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new ResultXRay();
        initConfig();
    }
    public ResultXRayDB()
    {
        theConnectionInf= null;
        dbObj = new ResultXRay();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="t_result_xray";
        dbObj.pk_field="t_result_xray_id";
        dbObj.xn   ="result_xray_xn";
        dbObj.hn   ="t_patient_id";
        dbObj.vn   ="t_visit_id";
        dbObj.xray_id   ="result_xray";
        dbObj.size_film   ="result_xray_film_size";
        dbObj.excetue_film   ="result_xray_staff_execute";
        dbObj.record_time   ="record_date_time";
        dbObj.description   ="result_xray_description";
        dbObj.order_item_id   ="t_order_id";
        dbObj.xray_point   ="result_xray_notice";
        dbObj.reporter   ="result_xray_staff_record";
        dbObj.active   ="result_xray_active";
        dbObj.result_complete  ="result_xray_complete";
        dbObj.t_patient_xn_id  ="t_patient_xn_id";
        dbObj.xray_time = "result_xray_time";
        
        /*
        dbObj.table = "result_xray";
        dbObj.pk_field = "result_xray_id";
        dbObj.xn = "xn";
        dbObj.hn = "hn";
        dbObj.vn = "vn";
        dbObj.xray_id = "xray_id";
        dbObj.size_film = "size_film";
        dbObj.excetue_film = "excetue_film";
        dbObj.record_time = "record_time";
        dbObj.description = "description";
        dbObj.order_item_id = "order_item_id";
        dbObj.xray_point = "xray_point";
        dbObj.reporter = "reporter";
        dbObj.active = "active";
        dbObj.result_complete = "result_complete";
         */
        return true;
    }
    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(ResultXRay o) throws Exception
    {
        ResultXRay p=o;
        p.generateOID(idtable);
        StringBuffer sql = new StringBuffer("insert into " ).append( dbObj.table ).append( " ("
        ).append( dbObj.pk_field
        ).append( " ,"	).append( dbObj.xn
        ).append( " ,"	).append( dbObj.hn
        ).append( " ,"	).append( dbObj.vn
        ).append( " ,"	).append( dbObj.xray_id
        ).append( " ,"	).append( dbObj.size_film
        ).append( " ,"	).append( dbObj.excetue_film
        ).append( " ,"	).append( dbObj.record_time
        ).append( " ,"	).append( dbObj.description
        ).append( " ,"	).append( dbObj.order_item_id
        ).append( " ,"	).append( dbObj.xray_point
        ).append( " ,"	).append( dbObj.reporter
        ).append( " ,"	).append( dbObj.active
        ).append( " ,"	).append( dbObj.result_complete
        ).append( " ,"	).append( dbObj.t_patient_xn_id
        ).append( " ,"  ).append( dbObj.xray_time
        ).append( " ) values ('"
        ).append( p.getObjectId()
        ).append( "','" ).append( p.xn
        ).append( "','" ).append( p.hn
        ).append( "','" ).append( p.vn
        ).append( "','" ).append( p.xray_id
        ).append( "','" ).append( p.size_film
        ).append( "','" ).append( p.excetue_film
        ).append( "','" ).append( p.record_time
        ).append( "','" ).append( p.description
        ).append( "','" ).append( p.order_item_id
        ).append( "','" ).append( p.xray_point
        ).append( "','" ).append( p.reporter
        ).append( "','" ).append( p.active
        ).append( "','" ).append( p.result_complete
        ).append( "','" ).append( p.t_patient_xn_id
        ).append( "','" ).append( p.xray_time
        ).append( "')");
        return theConnectionInf.eUpdate(sql.toString());
    }
    public int update(ResultXRay o) throws Exception
    {
        ResultXRay p=o;
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
        ).append( dbObj.xn ).append( "='" ).append( p.xn
        ).append( "', " ).append( dbObj.hn ).append( "='" ).append( p.hn
        ).append( "', " ).append( dbObj.vn ).append( "='" ).append( p.vn
        ).append( "', " ).append( dbObj.xray_id ).append( "='" ).append( p.xray_id
        ).append( "', " ).append( dbObj.size_film ).append( "='" ).append( p.size_film
        ).append( "', " ).append( dbObj.excetue_film ).append( "='" ).append( p.excetue_film
        ).append( "', " ).append( dbObj.record_time ).append( "='" ).append( p.record_time
        ).append( "', " ).append( dbObj.description ).append( "='" ).append( p.description
        ).append( "', " ).append( dbObj.order_item_id ).append( "='" ).append( p.order_item_id
        ).append( "', " ).append( dbObj.xray_point ).append( "='" ).append( p.xray_point
        ).append( "', " ).append( dbObj.reporter ).append( "='" ).append( p.reporter
        ).append( "', " ).append( dbObj.result_complete ).append( "='" ).append( p.result_complete
        ).append( "', " ).append( dbObj.active ).append( "='" ).append( p.active
        ).append( "', " ).append( dbObj.t_patient_xn_id ).append( "='" ).append( p.t_patient_xn_id
        ).append( "', " ).append( dbObj.xray_time ).append( "='" ).append( p.xray_time
        ).append( "' where " ).append( dbObj.pk_field ).append( "='" ).append( p.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*
    public int delete(ResultXRay o) throws Exception
    {
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field ).append( "='" ).append( o.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public ResultXRay selectByPK(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
         ).append( " where " ).append( dbObj.pk_field
         ).append( " = '" ).append( pk ).append( "' and " ).append( dbObj.active ).append( " = '1' ");
         
         Vector v=eQuery(sql.toString());
         if(v.size()==0)
             return null;
         else
         {
             return (ResultXRay)v.get(0);
         }
         
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAll() throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
         
         Vector v=eQuery(sql.toString());
         if(v.size()==0)
             return null;
         else
         {
             return v;
         }
         
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAll(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where ");
        if(pk.trim().length() !=0)
        {
            sql.append("(" ).append(  dbObj.hn
            ).append( " = '" ).append( pk ).append( "'" ).append( " or "
            ).append( dbObj.vn).append( " = '" ).append( pk ).append( "'" ).append( ")  where " ).append( dbObj.active ).append( " = '1' " );
        }
        
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
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
            p.name = rs.getString(dbObj.vn)+rs.getString(dbObj.size_film);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector eQuery(String sql) throws Exception
    {
        ResultXRay p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        while(rs.next())
        {
            p = new ResultXRay();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.xn = rs.getString(dbObj.xn);
            p.hn = rs.getString(dbObj.hn);
            p.vn = rs.getString(dbObj.vn);
            p.xray_id = rs.getString(dbObj.xray_id);
            p.size_film = rs.getString(dbObj.size_film);
            p.excetue_film = rs.getString(dbObj.excetue_film);
            p.record_time = rs.getString(dbObj.record_time);
            p.description = rs.getString(dbObj.description);
            p.order_item_id = rs.getString(dbObj.order_item_id);
            p.xray_point = rs.getString(dbObj.xray_point);
            p.reporter = rs.getString(dbObj.reporter);
            p.result_complete = rs.getString(dbObj.result_complete);
            p.active = rs.getString(dbObj.active);
            p.t_patient_xn_id = rs.getString(dbObj.t_patient_xn_id);
            p.xray_time = rs.getString(dbObj.xray_time);
            list.add(p);
            
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public ResultXRay selectOrderItemByVNItemId(String itemid ,String vn) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.order_item_id
        ).append( " = '" ).append( itemid ).append( "'"
        ).append( " and " ).append( dbObj.vn ).append( " = '" ).append( vn ).append( "' and " ).append( dbObj.active ).append( " = '1' ");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (ResultXRay)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/

    public Vector selectByPtid(String patient_id) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.hn ).append( " = '" ).append( patient_id ).append( "'");
        return eQuery(sql.toString());
    }
}
