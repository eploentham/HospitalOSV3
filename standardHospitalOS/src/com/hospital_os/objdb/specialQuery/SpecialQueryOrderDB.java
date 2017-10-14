/*
 * SpecialQueueOrderDB.java
 *
 * Created on 25 ตุลาคม 2547, 20:58 น.
 */
package com.hospital_os.objdb.specialQuery;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.specialQuery.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  tong
 */
public class SpecialQueryOrderDB
{
    /** Creates a new instance of SpecialQueueOrderDB */
    public ConnectionInf theConnectionInf;
    private SpecialQueryOrder dbObj ;
    private String table1 = "t_order";
    private String table2 = "t_order_status";
    public SpecialQueryOrderDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new SpecialQueryOrder();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.b_item_id = "b_item_id";
        dbObj.order_common_name = "order_common_name";
        dbObj.order_conplete = "order_complete";
        dbObj.order_continue = "order_continue";
        dbObj.order_price = "order_price";
        dbObj.order_qty = "order_qty";
        dbObj.order_request = "order_request";
        dbObj.order_status_description = "order_status_description";
        dbObj.t_order_id = "t_order_id";
        dbObj.t_visit_id = "t_visit_id";
        dbObj.order_t_order_status_id = "f_order_status_id";
        dbObj.order_status_f_order_status_id = "f_order_status_id";
        
        return true;
    }
    /*////////////////////////////////////////////////////////////////////////*/
    public Vector queryDataOrderbyVisitID(String visit_id) throws Exception
    {
        String sql = "SELECT " +
        dbObj.t_order_id +  "," +
        dbObj.t_visit_id + "," +
        dbObj.b_item_id + "," +
        dbObj.order_common_name + "," +
        dbObj.order_status_description + "," +
        dbObj.order_qty + "," +
        dbObj.order_price + "," +
        dbObj.order_request + "," +
        dbObj.order_continue + "," +
        dbObj.order_conplete + "" +
        " FROM " +
        table1 + "" +
        " INNER JOIN " +
        table2 + "" +
        " ON t_order.f_order_status_id = t_order_status.f_order_status_id " +
        " where " + dbObj.t_visit_id +  "='" + visit_id + "';";
        Vector vc =  eQuery(sql);
        if(vc.size() ==0)
            return  null;
        else
            return vc;
    }
    /*/////////////////////////////////////////////////////////////////////////*/
    private Vector eQuery(String sql) throws Exception
    {
        SpecialQueryOrder p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        int i = 0;
        while(rs.next())
        {
            p = new SpecialQueryOrder();
            p.b_item_id = rs.getString(dbObj.b_item_id);
            p.order_common_name = rs.getString(dbObj.order_common_name);
            p.order_conplete = rs.getString(dbObj.order_conplete);
            p.order_continue = rs.getString(dbObj.order_continue);
            p.order_price = rs.getString(dbObj.order_price);
            p.order_qty = rs.getString(dbObj.order_qty);
            p.order_request = rs.getString(dbObj.order_request);
            p.order_status_description = rs.getString(dbObj.order_status_description);
            p.t_order_id = rs.getString(dbObj.t_order_id);
            p.t_visit_id = rs.getString(dbObj.t_visit_id);
            
            list.add(p);
            p = null;
            
        }
        rs.close();
        return list;
    }
}
