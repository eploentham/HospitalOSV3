/*
 * SpecialQueryBillingReceiptDB.java
 *
 * Created on 29 ¡’π“§¡ 2548, 09:51 π.
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
 * @author  ojika
 *
 */
public class SpecialQueryBillingReceiptDB
{
    
    /** Creates a new instance of SpecialQueueVisitDB */
    public ConnectionInf theConnectionInf;
    public SpecialQueryBillingReceiptDB(ConnectionInf db)
    {
        theConnectionInf = db;
    }
    
    public Vector selectBillingByVisitIdReceiptId(String visit_id,String receipt_id) throws Exception
    {
        String sql = " SELECT " +
        " b_item_billing_subgroup.item_billing_subgroup_description AS billing " +
        ",t_billing_receipt_item.billing_receipt_item_draw AS draw " +
        ",t_billing_receipt_item.t_payment_id AS plan" +
        ",sum(round(t_billing_receipt_item.billing_receipt_item_paid)) AS paid " +
        " FROM " +
        " t_billing_receipt_item " +
        ",b_item " +
        ",b_item_billing_subgroup " +
        " WHERE " +
        " b_item.b_item_id = t_billing_receipt_item.b_item_id " +
        " AND b_item.b_item_billing_subgroup_id = b_item_billing_subgroup.b_item_billing_subgroup_id  " +
        " AND t_billing_receipt_item.billing_receipt_item_active = '1' " +
        " AND t_billing_receipt_item.t_billing_receipt_id = '"+ receipt_id +"' " +
        " AND t_billing_receipt_item.t_visit_id = '"+ visit_id +"' " +
        " Group By billing,plan,draw " +
        " Order By billing ";
        
        Vector vc =  eQuery(sql);
        if(vc.size() == 0)
            return null;
        else
            return vc;
    }
    
    private Vector eQuery(String sql) throws Exception
    {
        SpecialQueryBillingReceipt p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        int i = 0;
        while(rs.next())
        {
            p = new SpecialQueryBillingReceipt();
            p.billing  = rs.getString(1);
            p.draw = rs.getString(2);
            p.plan =  rs.getString(3);
            p.paid =  rs.getString(4);
            
            list.add(p);
        }
        rs.close();
        return list;
    }
}
