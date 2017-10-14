//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;

/*    public int update(ReceiptSubgroup o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        ReceiptSubgroup p=o;
        String field =""
        + "', " + dbObj.receipt_id + "='" + p.receipt_id
        + "', " + dbObj.billing_invoice_subgroup_id + "='" + p.billing_invoice_subgroup_id
        + "', " + dbObj.hn + "='" + p.hn
        + "', " + dbObj.vn + "='" + p.vn
        + "', " + dbObj.item_group_code_billing + "='" + p.item_group_code_billing
        + "', " + dbObj.paid + "='" + p.paid
        + "', " + dbObj.active + "='" + p.active
        + "', " + dbObj.draw + "='" + p.draw
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
*/
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.objdb.*;

public class ReceiptSubgroup2DB extends ReceiptSubgroupDB
{
    public ReceiptSubgroup2DB(ConnectionInf db){
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
