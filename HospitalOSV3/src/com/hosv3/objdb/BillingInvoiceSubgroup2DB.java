//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;

/*    public int update(BillingInvoiceSubgroup o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        BillingInvoiceSubgroup p=o;
        
        String field =""
        + "', " + dbObj.billing_invoice_id + "='" + p.billing_invoice_id
        + "', " + dbObj.category_group_item_id + "='" + p.category_group_item_id
        + "', " + dbObj.billing_group_item_id + "='" + p.billing_group_item_id
        + "', " + dbObj.draw + "='" + p.draw
        + "', " + dbObj.patient_id + "='" + p.patient_id
        + "', " + dbObj.visit_id + "='" + p.visit_id
        + "', " + dbObj.payer_share + "='" + p.payer_share
        + "', " + dbObj.patient_share + "='" + p.patient_share
        + "', " + dbObj.total + "='" + p.total
        + "', " + dbObj.payment_id + "='" + p.payment_id
        + "', " + dbObj.active + "='" + p.active
        + "', " + dbObj.billing_id + "='" + p.billing_id
        
        
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
*/
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.objdb.*;

public class BillingInvoiceSubgroup2DB extends BillingInvoiceSubgroupDB
{
    
    public BillingInvoiceSubgroup2DB(ConnectionInf db){
        super(db);
    }
    
    //////////////////////////////////////////////////////////////////////////////
    public int updateBbyBi(String bid,String biid) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        String field = dbObj.billing_id + "='" + bid
        + "' where " + dbObj.billing_invoice_id + "='" + biid + "'";
        sql = Gutil.convertSQLToMySQL(sql+field,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public int updateAbyBi(String active,String biid) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        String field = dbObj.active + "='" + active
        + "' where " + dbObj.billing_invoice_id + "='" + biid + "'";
        sql = Gutil.convertSQLToMySQL(sql+field,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public int updateBbyB(String bid,String billing_id) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        String field = dbObj.billing_id + "='" + bid
        + "' where " + dbObj.billing_id + "='" + billing_id + "'";
        sql = Gutil.convertSQLToMySQL(sql+field,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
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
