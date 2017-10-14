/*
 * BillingInvoiceItem.java
 *
 * Created on 10 ÁÔ¶Ø¹ÒÂ¹ 2547, 16:14 ¹.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.utility.Constant;
/**
 *
 * @author  tong
 */
public class BillingInvoiceItem  extends Persistent{
    
    /** Creates a new instance of BillingInvoice */
    public String billing_invoice_item_id;
    public String billing_invoice_id;
    public String order_item_id;
    public String billing_group_item_id;
    public String item_id;
    public String patient_id;
    public String visit_id;
    public String payer_share;
    public String patient_share;
    public String payer_share_ceil;
    public String patient_share_ceil;
    public String total;
    public String payment_id;
    public String draw;
    
    public String active = "1";

    public BillingInvoiceItem() {
    }
    
    public static BillingInvoiceItem initFromBoi(BillingOrderItem boi) {
        
        BillingInvoiceItem bii =  new BillingInvoiceItem();
        bii.billing_invoice_id = "";
        bii.order_item_id = boi.order_item_id;
        bii.item_id = boi.item_id;
        bii.billing_group_item_id = boi.item_group_code_billing;
        bii.patient_id  = boi.patient_id;
        bii.visit_id  = boi.visit_id;
        bii.payer_share  = Constant.dicimal(boi.payershare);
        bii.patient_share  = Constant.dicimal(boi.patientshare);
        bii.payer_share_ceil  = Constant.calBil(boi.payershare);
        bii.patient_share_ceil  = Constant.calBil(boi.patientshare);
        double patientprice = Double.parseDouble(bii.payer_share);
        double payerprice = Double.parseDouble(bii.patient_share);
        bii.total = Constant.dicimal(String.valueOf(patientprice + payerprice));
        bii.payment_id = boi.payment_id;
        bii.draw = boi.draw;
        if(bii.draw == null) bii.draw = "0";
        bii.active = Active.isEnable();
        return bii;
    }
}
