//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;

/*    public int update(ReceiptItem o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        ReceiptItem p=o;
        String field =""
        
        + "', " + dbObj.receipt_id + "='" + p.receipt_id
        + "', " + dbObj.billing_invoice_item_id + "='" + p.billing_invoice_item_id
        + "', " + dbObj.item_id + "='" + p.item_id
        + "', " + dbObj.draw + "='" + p.draw
        + "', " + dbObj.active + "='" + p.active
        + "', " + dbObj.payment_id + "='" + p.payment_id
        + "', " + dbObj.paid + "='" + p.paid
        + "', " + dbObj.vn + "='" + p.vn
        + "', " + dbObj.visit_id + "='" + p.visit_id
        + "', " + dbObj.hn + "='" + p.hn
        + "', " + dbObj.patient_id + "='" + p.patient_id
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        
        return theConnectionInf.eUpdate(sql);
    }
*/
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.objdb.*;

public class ReceiptItem2DB extends ReceiptItemDB
{
    
    public ReceiptItem2DB(ConnectionInf db){
        super(db);
    }
    
    //////////////////////////////////////////////////////////////////////////////
    public int updateAbyR(String active,String rid) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        String field = dbObj.active + "='" + active
        + "' where " + dbObj.receipt_id + "='" + rid +"'";
        sql = Gutil.convertSQLToMySQL(sql+field,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
}
