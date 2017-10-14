//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.objdb.*;
import java.util.*;

public class BillingInvoiceItem2DB extends BillingInvoiceItemDB
{
    
    public BillingInvoiceItem2DB(ConnectionInf db){
        super(db);
    }
    
    //////////////////////////////////////////////////////////////////////////////
    public int updateAbyBi(String active,String biid) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        String field =""
        + "', " +  dbObj.active+ "='" + active
        + "' where " + dbObj.billing_invoice_id+ "='" + biid +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectByBid(String bid,String active) throws Exception
    {
        String sql="select * from t_billing_invoice_item " +
                " left join t_billing_invoice on t_billing_invoice_item.t_billing_invoice_id = t_billing_invoice.t_billing_invoice_id" +
                " where t_billing_id = '"+ bid  +"' " +
                " and t_billing_invoice_item.billing_invoice_item_active = '"+active+"'";
        return eQuery(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectByBIid(String biid,String active) throws Exception
    {
        String sql="select * from "+ dbObj.table +
                " where " + dbObj.billing_invoice_id + " = '" + biid + "' " +
                " and " + dbObj.active+ " = '" + active + "'";
        return eQuery(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public int deletebyBi(String biid) throws Exception
    {
        String sql="delete from " + dbObj.table 
        + " where " + dbObj.billing_invoice_id+ "='" + biid +"'";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
}
