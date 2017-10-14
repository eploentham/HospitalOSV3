/*
 * OrderContinue2DB.java
 *
 * Created on 27 กรกฎาคม 2548, 16:07 น.
 */

package com.hosv3.objdb;
import com.hospital_os.objdb.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;
import java.util.*;

/**
 *
 * @author  kingland
 */
public class OrderContinue2DB extends OrderContinueDB{
    
    /** Creates a new instance of OrderContinue2DB */
    public OrderContinue2DB(ConnectionInf db) {
        super(db);
    }
    public OrderContinue selectByVidItn(String vid,String itname) throws Exception {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.visit_id + " = '" + vid + "' "
        + " and " + dbObj.common_name + " = '" + itname + "'";
        Vector v = eQuery(sql);
        if(v.isEmpty()) return null;
        else            return (OrderContinue)v.get(0);
   }
    public OrderContinue selectByOrid(String orid) throws Exception {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.order_item_id + " = '" + orid + "' ";
        Vector v = eQuery(sql);
        if(v.isEmpty()) return null;
        else            return (OrderContinue)v.get(0);
   }
    /*
     ค้นยาต่อเนื่องที่ยังสามารถสั่งได้ ยังไม่ได้ off โดยแพทย์
     */
    public Vector selectByVidDo(String vid,String date_off) throws Exception {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.visit_id + " = '" + vid + "' "
        + " and " + dbObj.date_off + " = '" + date_off + "'";
        return eQuery(sql);
   }    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
}
