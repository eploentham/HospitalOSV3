//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;


import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import com.hospital_os.objdb.*;

public class BillingInvoice2DB extends BillingInvoiceDB
{

    public BillingInvoice2DB(ConnectionInf db){
        super(db);
    }
    
    //////////////////////////////////////////////////////////////////////////////
    public int updateBC(BillingInvoice bi,String billing_id,String complete) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        BillingInvoice p=bi;
        p.billing_complete = complete;
        p.billing_id = billing_id;
        String field =""
        + "', " +  dbObj. billing_complete + "='" + p.billing_complete
        + "', " +  dbObj.billing_id + "='" + p.billing_id
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public int updateBCbyB(String billing_id,String complete,String bid)throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        String field =""
        + "', " +  dbObj. billing_complete + "='" + complete
        + "', " +  dbObj.billing_id + "='" + billing_id
        + "' where " + dbObj.billing_id + "='" + bid +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
}
