/*
 * BorrowFilmXray.java
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
public class BorrowFilmXray extends Persistent 
{
        public String patient_hn = "";
        public String patient_xn = "";
        public String borrower_prefix = "";
        public String borrower_name = "";
        public String borrower_lastname = "";
        public String borrow_film_date = "";
        public String amount_date = "";
        public String return_film_date = "";
        public String borrow_status = "";
        public String permissibly_borrow = "";
        public String borrow_cause = "";
        public String borrow_to = "";
        public String borrow_to_other = "";
        public String date_serv = "";
        public String borrow_staff_record = "";
        public String borrow_record_date_time = "";
        public String borrow_staff_update = "";
        public String borrow_update_date_time = "";
        public String borrow_staff_cancel = "";
        public String borrow_cancel_date_time = "";
        public String borrow_active = "1";
    /** Creates a new instance of BorrowFilmXray */
    public BorrowFilmXray() {
    }
    
}
