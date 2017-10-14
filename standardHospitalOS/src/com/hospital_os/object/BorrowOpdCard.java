/*
 * BorrowOpdCard.java
 *
 * Created on 14 กุมภาพันธ์ 2549, 12:28 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Sumo
 */
public class BorrowOpdCard extends Persistent 
{
        public String patient_hn = "";
        public String borrower_opd_prefix = "";
        public String borrower_opd_name = "";
        public String borrower_opd_lastname = "";
        public String borrow_opd_date = "";
        public String amount_date_opd = "";
        public String return_opd_date = "";
        public String borrow_opd_status = "";
        public String permissibly_borrow_opd = "";
        public String borrow_opd_cause = "";
        public String borrow_opd_to = "";
        public String borrow_opd_to_other = "";
        public String date_serv_opd = "";
        public String borrow_opdcard_staff_record = "";
        public String borrow_opdcard_record_date_time = "";
        public String borrow_opdcard_staff_update = "";
        public String borrow_opdcard_update_date_time = "";
        public String borrow_opdcard_staff_cancel = "";
        public String borrow_opdcard_cancel_date_time = "";
        public String borrow_opdcard_active = "1";
        
    /** Creates a new instance of BorrowOpdCard */
    public BorrowOpdCard() {
    }
    
}
