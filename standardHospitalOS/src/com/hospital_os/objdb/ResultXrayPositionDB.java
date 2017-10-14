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
public class ResultXrayPositionDB
{
    public ConnectionInf theConnectionInf;
    public ResultXrayPosition dbObj;
    final public String idtable = "233";/*"325";
*/
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public ResultXrayPositionDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new ResultXrayPosition();
        initConfig();
    }
    public boolean initConfig()
    {
        
        dbObj.table="t_result_xray_position";
        dbObj.pk_field="t_result_xray_position_id";
        dbObj.xray_leteral_id   ="b_xray_side_id";
        dbObj.xray_position_id   ="b_xray_position_id";
        dbObj.order_result_xray_id   ="t_result_xray_id";
        dbObj.visit_id   ="t_visit_id";
        dbObj.order_item_id   ="t_order_id";
        dbObj.active   ="result_xray_position_active";
        dbObj.xray_result_size_id   ="t_result_xray_size_id";
        dbObj.kv   ="result_xray_position_kv";
        dbObj.ma   ="result_xray_position_ma";
        dbObj.second   ="result_xray_position_second";
        dbObj.mas   ="result_xray_position_mas";
        dbObj.ffd="result_xray_position_ffd";
        /*
        dbObj.table = "result_xray_position";
        dbObj.pk_field = "result_xray_position_id";
        dbObj.xray_leteral_id = "xray_leteral_id";
        dbObj.xray_position_id = "xray_position_id";
        dbObj.order_result_xray_id = "order_result_xray_id";
        dbObj.order_item_id = "order_item_id";
        dbObj.visit_id = "visit_id";
        dbObj.active = "active";
        dbObj.xray_result_size_id = "xray_result_size_id"; ¢π“¥ø‘≈¡Ï
        dbObj.kv = "kv";
        dbObj.ma = "ma";
        dbObj.second = "second";
        dbObj.mas = "mas";
        dbObj.ffd = "ffd";
         */
        
        return true;
    }
    
    public int insert(ResultXrayPosition o) throws Exception
    {
        ResultXrayPosition p=o;
        p.generateOID(idtable);
        StringBuffer sql = new StringBuffer("insert into " ).append( dbObj.table ).append( " ("
        ).append( dbObj.pk_field
        ).append( " ,"	).append( dbObj.xray_leteral_id
        ).append( " ,"	).append( dbObj.xray_position_id
        ).append( " ,"	).append( dbObj.order_result_xray_id
        ).append( " ,"	).append( dbObj.order_item_id
        ).append( " ,"	).append( dbObj.visit_id
        ).append( " ,"	).append( dbObj.xray_result_size_id
        ).append( " ,"	).append( dbObj.kv
        ).append( " ,"	).append( dbObj.ma
        ).append( " ,"	).append( dbObj.second
        ).append( " ,"	).append( dbObj.mas
        ).append( " ,"	).append( dbObj.ffd
        ).append( " ,"	).append( dbObj.active
        ).append( " ) values ('"
        ).append( p.getObjectId()
        ).append( "','" ).append( p.xray_leteral_id
        
        ).append( "','" ).append( p.xray_position_id
        ).append( "','" ).append( p.order_result_xray_id
        ).append( "','" ).append( p.order_item_id
        ).append( "','" ).append( p.visit_id
        ).append( "','" ).append( p.xray_result_size_id
        ).append( "','" ).append( p.kv
        ).append( "','" ).append( p.ma
        ).append( "','" ).append( p.second
        ).append( "','" ).append( p.mas
        ).append( "','" ).append( p.ffd
        ).append( "','" ).append( p.active
        
        ).append( "')");
        return theConnectionInf.eUpdate(sql.toString());
    }
    public int update(ResultXrayPosition o) throws Exception
    {
        ResultXrayPosition p=o;
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
        ).append( dbObj.xray_leteral_id ).append( "='" ).append( p.xray_leteral_id
        ).append( "', " ).append( dbObj.xray_position_id ).append( "='" ).append( p.xray_position_id
        ).append( "', " ).append( dbObj.order_result_xray_id ).append( "='" ).append( p.order_result_xray_id
        ).append( "', " ).append( dbObj.order_item_id ).append( "='" ).append( p.order_item_id
        ).append( "', " ).append( dbObj.visit_id ).append( "='" ).append( p.visit_id
        ).append( "', " ).append( dbObj.xray_result_size_id ).append( "='" ).append( p.xray_result_size_id
        ).append( "', " ).append( dbObj.kv ).append( "='" ).append( p.kv
        ).append( "', " ).append( dbObj.ma ).append( "='" ).append( p.ma
        ).append( "', " ).append( dbObj.second  ).append( "='" ).append( p.second
        ).append( "', " ).append( dbObj.mas ).append( "='" ).append( p.mas
        ).append( "', " ).append( dbObj.ffd ).append( "='" ).append( p.ffd
        ).append( "', " ).append( dbObj.active ).append( "='" ).append( p.active
        ).append( "' where " ).append( dbObj.pk_field ).append( "='" ).append( p.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    public int deleteAllResultXrayPositionbyXraySizeID(String xraysize_id) throws Exception
    {
        StringBuffer sql = new StringBuffer( "UPDATE " ).append( dbObj.table ).append( "" ).append(
        " SET " ).append( dbObj.active ).append( "= '0'" ).append(
        " Where " ).append( dbObj.xray_result_size_id ).append( "='" ).append( xraysize_id ).append( "'" );
        return theConnectionInf.eUpdate(sql.toString());
    }
    
    /*////////////////////////////////////////////////////////////////////////////
*/
    public int delete(ResultXrayPosition o) throws Exception
    {
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field ).append( "='" ).append( o.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public ResultXrayPosition selectByPK(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field
        ).append( " = '" ).append( pk ).append( "'");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (ResultXrayPosition)v.get(0);
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
    public Vector selectByResultXRayID(String result_xray_id) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.order_result_xray_id
        ).append( " = '" ).append( result_xray_id ).append( "'"
        ).append( " and " ).append( dbObj.active ).append( " = '" ).append( "1" ).append( "'");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector selectByResultXRaySizeID(String result_xray_size_id) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.xray_result_size_id
        ).append( " = '" ).append( result_xray_size_id ).append( "'"
        ).append( " and " ).append( dbObj.active ).append( " = '" ).append( "1" ).append( "'");
        
        return eQuery(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector selectByResultXRaySizeIDNotActive(String result_xray_size_id) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.xray_result_size_id
        ).append( " = '" ).append( result_xray_size_id ).append( "'"
        ).append( " and " ).append( dbObj.active ).append( " = '" ).append( "0" ).append( "'");
        
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
        ResultXrayPosition p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        while(rs.next())
        {
            p = new ResultXrayPosition();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.xray_leteral_id = rs.getString(dbObj.xray_leteral_id);
            p.xray_position_id = rs.getString(dbObj.xray_position_id);
            p.order_result_xray_id = rs.getString(dbObj.order_result_xray_id);
            p.order_item_id = rs.getString(dbObj.order_item_id);
            p.visit_id = rs.getString(dbObj.visit_id);
            p.active = rs.getString(dbObj.active);
            p.xray_result_size_id = rs.getString(dbObj.xray_result_size_id);
            p.kv = rs.getString(dbObj.kv);
            p.ma = rs.getString(dbObj.ma);
            p.second  = rs.getString(dbObj.second);
            p.mas = rs.getString(dbObj.mas);
            p.ffd = rs.getString(dbObj.ffd);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    
    
    
}
