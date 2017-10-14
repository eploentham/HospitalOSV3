/*
 * BillingInvoiceSubgroup.java
 *
 * Created on 10 มิถุนายน 2547, 16:14 น.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
import java.util.Vector;
/**
 *
 * @author  tong
 */
public class BillingInvoiceSubgroup  extends Persistent{
    
    /** Creates a new instance of BillingInvoice */
    public String billing_invoice_subgroup_id;
    public String billing_invoice_id;
    public String category_group_item_id;
    public String billing_group_item_id;
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
    public String billing_id = "";

    public BillingInvoiceSubgroup() {
    }


    public static Vector initFromBoiV(Vector vin,Vector vBgi) 
    {
        Vector vbis = new Vector();
        //ทำการ copy vector เพื่อไม่ให้ข้อมูลหายไปในการทำงานของฟังชันนี้
        Vector vector = new Vector();
        for(int i=0;i<vin.size();i++)
            vector.add(vin.get(i));
        
//        Vector vBillingGroupItem = theLookupControl.listBillingGroupItem();
        BillingOrderItem boi1 = (BillingOrderItem)vector.get(0);
        //เพื่อหาว่า รายการนั้นมีกลุ่ม billing ตรงกันหรือไม่ถ้าตรงกันก็ให้ทำการบวกจนกว่าจะครบตามกว่า billing
        for(int k=0;k<vBgi.size();k++) 
        {
            int checkvalue_draw = 0;
            int checkvalue = 0;
            double patientprice = 0.00;
            double payerprice = 0.00;
            double patientprice_draw = 0.00;
            double payerprice_draw = 0.00;
            BillingGroupItem cgi = (BillingGroupItem)vBgi.get(k);
            for(int i=0;i<vector.size();)
            {
                BillingOrderItem boi = (BillingOrderItem)vector.get(i);
                if(boi.item_group_code_billing.equals(cgi.getObjectId())){
                    if(boi.draw.equals("0")){
                         patientprice = patientprice + Double.parseDouble(boi.patientshare);
                         payerprice =  payerprice + Double.parseDouble(boi.payershare);
                         checkvalue = checkvalue +1;
                    }
                    else{
                         patientprice_draw = patientprice_draw + Double.parseDouble(boi.patientshare);
                         payerprice_draw =  payerprice_draw + Double.parseDouble(boi.payershare);
                         checkvalue_draw = checkvalue_draw +1;
                    }
                    vector.remove(i);
                }
                else
                    i++;
            }
             //เมื่อครบแล้วก็ทำการให้ค่ากับกลุ่มรายการ
             //ตรวจสอบก่อนว่า การลนลูปนั้นมีข้อมูลอยู่หรือไม่เพื่อที่จะได้เฉพาะข้อมูลที่มีค่าเท่านั้น
            if(checkvalue > 0) 
             {
                 BillingInvoiceSubgroup bis =  new BillingInvoiceSubgroup();
                 bis.billing_group_item_id = cgi.getObjectId();
                 bis.category_group_item_id = "";
                 bis.billing_invoice_id = "";
                 bis.patient_id  = boi1.patient_id;
                 bis.visit_id = boi1.visit_id;
                 bis.payer_share  = String.valueOf(payerprice);
                 bis.patient_share  = String.valueOf(patientprice);
                 bis.payer_share_ceil = String.valueOf(payerprice);
                 bis.patient_share_ceil = String.valueOf(patientprice);
                 bis.total = String.valueOf(payerprice + patientprice);
                 bis.payment_id = boi1.payment_id;
                 bis.draw = "0";
                 bis.active = Active.isEnable();
                 vbis.add(bis);
             }
             if(checkvalue_draw > 0) 
             {
                 BillingInvoiceSubgroup bis =  new BillingInvoiceSubgroup();
                 bis.billing_group_item_id = cgi.getObjectId();
                 bis.category_group_item_id = "";
                 bis.billing_invoice_id = "";
                 bis.patient_id  = boi1.patient_id;
                 bis.visit_id = boi1.visit_id;
                 bis.payer_share  = String.valueOf(payerprice_draw);
                 bis.patient_share  = String.valueOf(patientprice_draw);
                 bis.payer_share_ceil = String.valueOf(payerprice_draw);
                 bis.patient_share_ceil = String.valueOf(patientprice_draw);
                 bis.total = String.valueOf(payerprice_draw + patientprice_draw);
                 bis.payment_id = boi1.payment_id;
                 bis.draw = "1";
                 bis.active = Active.isEnable();
                 vbis.add(bis);
             }
        }
        return vbis;
    }
    
}
