/*
 * BillingOrderItem.java
 *
 * Created on 29 æƒ…¿“§¡ 2547, 18:34 π.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
import java.util.Vector;
/**
 *
 * @author  tong
 */
public class BillingOrderItem  extends Persistent 
{
    
    /** Creates a new instance of BillingOrderItem */
    public String order_item_id;
    public String item_id;
    public String common_name;
    public String payershare;
    public String patientshare;
    public String plan_id;
    public String payment_id;
    public String draw = "0";
    public String request;
    public String visit_id;
    public String patient_id;
    public String billing_id;
    public String item_group_code_category;
    public String charge_complete;
    public String item_group_code_billing;
    public OrderItem theOrderItem;

    public BillingOrderItem() {
    }

    public static Vector[] splitPayment(Vector vBoi, Vector vVisitPayment) {
        return null;
    }

    public static Vector sortCategory(Vector vector) {
        return null;
    }
    
}
