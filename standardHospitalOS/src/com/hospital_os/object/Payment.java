package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
public class Payment extends Persistent 
{
        public String visit_id= "";
        public String plan_kid= "";
        public String contract_kid= "";
        public String card_id= "";
        public String card_ins_date= "";
        public String card_exp_date= "";
        public String hosp_main = "";
        public String hosp_sub = "";
        public String priority= "";
        public String money_limit= "";
        public String use_money_limit = "0";
        public String visit_payment_staff_record = "";
        public String visit_payment_record_date_time = "";
        public String visit_payment_staff_update = "";
        public String visit_payment_update_date_time = "";
        public String visit_payment_staff_cancel = "";
        public String visit_payment_cancel_date_time = "";
        public String visit_payment_active = "1";
   
   /**
    * @roseuid 3F658BBB036E
    */
   public Payment() 
   {
    
   }

    public boolean isActive() {
        return visit_payment_active.equals("1");
    }
}
