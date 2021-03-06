/*
 * BillingInvoiceItemDB.java
 *
 * Created on 10 �Զع�¹ 2547, 16:48 �.
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
public class BillingInvoiceItemDB
{
    
    /** Creates a new instance of BillingInvoiceDB */
    public ConnectionInf theConnectionInf;
    public BillingInvoiceItem dbObj;
    final private String idtable = "123";/*"556";
*/
    public BillingInvoiceItemDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new BillingInvoiceItem();
        initConfig();
    }
    public boolean initConfig()
    {
        
        dbObj.table="t_billing_invoice_item";
        dbObj.pk_field="t_billing_invoice_item_id";
        dbObj.billing_invoice_id   ="t_billing_invoice_id";
        dbObj.patient_id   ="t_patient_id";/*"t_visit_id";*/
        dbObj.visit_id   ="t_visit_id";/*"t_patient_id";*/
        dbObj.order_item_id   ="t_order_item_id";
        dbObj.item_id   ="b_item_id";
        dbObj.payer_share   ="billing_invoice_item_payer_share";
        dbObj.patient_share   ="billing_invoice_item_patient_share";
        dbObj.payer_share_ceil   ="billing_invoice_item_payer_share_ceil";
        dbObj.patient_share_ceil   ="billing_invoice_item_patient_share_ceil";
        dbObj.total   ="billing_invoice_item_total";
        dbObj.payment_id   ="t_payment_id";
        dbObj.draw   ="billing_invoice_item_draw";
        dbObj.active   ="billing_invoice_item_active";
        dbObj.billing_group_item_id ="b_item_billing_subgroup_id";

        return true;
    }
     public static void updateField(BillingInvoiceItem obj)
    {
        obj.payer_share = Constant.getSDouble(obj.payer_share);
        obj.patient_share = Constant.getSDouble(obj.patient_share);
        obj.total = Constant.getSDouble(obj.total);
    }    
   
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(BillingInvoiceItem p) throws Exception
    {
        updateField(p);
        p.generateOID(idtable);
        String sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        
        
        + " ,"	+ dbObj.billing_invoice_id
        + " ,"	+ dbObj.order_item_id
        + " ,"	+ dbObj.billing_group_item_id
        + " ,"	+ dbObj.item_id
        + " ,"	+ dbObj.patient_id
        + " ,"	+ dbObj.visit_id
        + " ,"	+ dbObj.payer_share
        + " ,"	+ dbObj.patient_share
        + " ,"	+ dbObj.payer_share_ceil
        + " ,"	+ dbObj.patient_share_ceil
        + " ,"	+ dbObj.total
        + " ,"	+ dbObj.payment_id
        + " ,"	+ dbObj.draw
        + " ,"	+ dbObj.active
        
        + " ) values ('"
        + p.getObjectId()
        + "','" +  p.billing_invoice_id
        + "','" +  p.order_item_id
        + "','" +  p.billing_group_item_id
        + "','" +  p.item_id
        + "','" +  p.patient_id
        + "','" +  p.visit_id
        + "'," +  p.payer_share        //�ٴմչ�����ѹ�� double  by  henbe
        + "," +  p.patient_share        //�ٴմչ�����ѹ�� double  by  henbe
        + ",'" +  p.payer_share_ceil
        + "','" +  p.patient_share_ceil
        + "'," +  p.total        //�ٴմչ�����ѹ�� double  by  henbe
        + ",'" +  p.payment_id
        + "','" +  p.draw
        + "','" +  p.active
        
        
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int update(BillingInvoiceItem p) throws Exception
    {
        updateField(p);
        String sql="update " + dbObj.table + " set ";
        String field =""
        + "', " +  dbObj.billing_invoice_id+ "='" + p.billing_invoice_id
        + "', " +  dbObj.order_item_id+ "='" + p.order_item_id
        + "', " +  dbObj.billing_group_item_id +  "='" + p.billing_group_item_id
        + "', " +  dbObj.item_id+ "='" + p.item_id
        + "', " +  dbObj.patient_id+ "='" + p.patient_id
        + "', " +  dbObj.visit_id+ "='" + p.visit_id
        + "', " +  dbObj.payer_share + "=" + p.payer_share        //�ٴմչ�����ѹ�� double  by  henbe
        + ", " +  dbObj.patient_share + "=" + p.patient_share        //�ٴմչ�����ѹ�� double  by  henbe
        + ", " +  dbObj.payer_share_ceil+ "='" + p.payer_share_ceil
        + "', " +  dbObj.patient_share_ceil+ "='" + p.patient_share_ceil
        + "', " +  dbObj.total+ "=" + p.total        //�ٴմչ�����ѹ�� double  by  henbe
        + ", " +  dbObj.payment_id+ "='" + p.payment_id
        + "', " +  dbObj.draw+ "='" + p.draw
        + "', " +  dbObj.active+ "='" + p.active
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public int delete(BillingInvoiceItem o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public BillingInvoiceItem selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'" ;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (BillingInvoiceItem)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector selectAll() throws Exception
    {
        String sql="select * from " + dbObj.table;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector selectByPatientId(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.patient_id
        + " = '" + pk + "' order by " + dbObj.item_id + " desc"  ;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector selectByVisitId(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.visit_id
        + " = '" + pk + "' order by " + dbObj.item_id + " desc" ;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public Vector listBillingInvoiceItemByVisitIdBillingID(String visit_id,String billing_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.visit_id + " = '" + visit_id + "'"
        /*+ " and " + dbObj. + " = '" +  billing_id + "'"
*/
        + " order by " + dbObj.billing_group_item_id + " desc" ;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    
    public Vector listBillingInvoiceItemByVisitIdBillingGroupItemId(String visit_id, String billing_group_item_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.visit_id + " = '" + visit_id + "'"
        
        + " and " + dbObj.billing_group_item_id + " = '" + billing_group_item_id + "'"
        + " order by " + dbObj.item_id + " desc" ;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector listBillingInvoiceItemByVisitIdBillingInvoiceID(String visit_id,String billing_invoice_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.visit_id + " = '" + visit_id + "'"
        + " and " + dbObj.billing_invoice_id + " = '" +  billing_invoice_id + "'"
        + " order by " + dbObj.item_id + " desc" ;
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    /* �д֧�����Ũҡ billing_group_item_id, visit_id, billing_invoice_id
*/
    public Vector listBillingInvoiceItemByVisitIdBillingInvoiceIDBillingGroupItemId(String visit_id,String billing_invoice_id, String billing_group_item_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.visit_id + " = '" + visit_id + "'"
        + " and " + dbObj.billing_invoice_id + " = '" +  billing_invoice_id + "'"
        + " and " + dbObj.billing_group_item_id + " = '" + billing_group_item_id + "'"
        + " and " + dbObj.active + " = '1' "
        
        + " order by " + dbObj.item_id + " desc" ;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector eQuery(String sql) throws Exception
    {
        BillingInvoiceItem p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new BillingInvoiceItem();
            p.setObjectId(rs.getString(dbObj.pk_field));
            
            p.billing_invoice_id= rs.getString(dbObj.billing_invoice_id);
            p.order_item_id= rs.getString(dbObj.order_item_id);
            p.billing_group_item_id = rs.getString(dbObj.billing_group_item_id);
            p.item_id = rs.getString(dbObj.item_id);
            p.patient_id = rs.getString(dbObj.patient_id);
            p.visit_id = rs.getString(dbObj.visit_id);
            p.payer_share= rs.getString(dbObj.payer_share);
            p.patient_share= rs.getString(dbObj.patient_share);
            p.payer_share_ceil = rs.getString(dbObj.payer_share_ceil);
            p.patient_share_ceil = rs.getString(dbObj.patient_share_ceil);
            p.total = rs.getString(dbObj.total);
            p.payment_id = rs.getString(dbObj.payment_id);
            p.draw = rs.getString(dbObj.draw);
            p.active = rs.getString(dbObj.active);
            
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
}
