/*
 * BillingInvoice.java
 *
 * Created on 25 ตุลาคม 2548, 14:43 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.object;
import com.generalreport.utility.StandardObject;
/**
 *
 * @author americus
 */
public class BillingInvoice extends Persist implements StandardObject
{
    /**รหัสรายการแจ้งหนี้*/
    public String billing_invoice_id;
    /**รหัสข้อมูลการเข้ารับบริการ*/
    public String visit_id;
    /**สิทธิชำระให้*/
    public String payer_share;
    /**สิทธิการรักษา*/
    public String payment_id;
    /**แสดงหรือไม่*/
    public String active;
    /**จำนวนการคิดเงิน*/
    public String quantity;
    /**รหัสรายการคิดเงิน*/
    public String billing_id;
    

    /** Creates a new instance of BillingInvoice */
    public BillingInvoice()
    {
        idTable ="122";
        tableName= "t_billing_invoice";
    }

    public void setInitData()
    {
        billing_invoice_id = "";
        visit_id = "";
        payer_share = "";
        payment_id = "";
        active = "";
        quantity = "";
        billing_id = "";
    }

    public static String getStringTable()
    {
        return "t_billing_invoice";
    }
    
    public static String getStringFieldPKTable()
    {
        return "t_billing_invoice_id";
    }
    
    public static String getStringFieldPayerShare()
    {
        return "billing_invoice_payer_share";
    }
    
    public static String getStringFieldActive()
    {
        return "billing_invoice_active";
    }
    
    public static String getStringFieldQuantity()
    {
        return "billing_invoice_quantity";
    }
    
    public static String getStringFieldBillingID()
    {
        return "t_billing_id";
    }
    public static String getStringFieldVisitID()
    {
        return "t_visit_id";
    }    
     public static String getStringFieldVisitPaymentID()
    {
        return "t_payment_id";
    } 
    
    public Object setInitDataFieldName()
    {
        this.pk_table = this.getStringFieldPKTable();
        this.visit_id = this.getStringFieldVisitID();
        payer_share = this.getStringFieldPayerShare();
        payment_id = this.getStringFieldVisitPaymentID();
        active = this.getStringFieldActive();
        quantity = this.getStringFieldQuantity();
        billing_id = this.getStringFieldBillingID();
        return this;
    }
    
}
