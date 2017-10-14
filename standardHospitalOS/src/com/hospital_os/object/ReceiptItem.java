/*
 * receipt_item.java
 *
 * Created on 27 กรกฎาคม 2547, 16:28 น.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author  tong
 */
public class ReceiptItem  extends Persistent {
    
    /** Creates a new instance of receipt_item */
    public String receipt_id;
    public String billing_invoice_item_id;
    public String item_id;
    public String draw;
    public String active;
    public String payment_id;
    public String paid;
    public String vn;
    public String hn;
    public String visit_id;
    public String patient_id;
    
    public ReceiptItem() {
    }
    
}
