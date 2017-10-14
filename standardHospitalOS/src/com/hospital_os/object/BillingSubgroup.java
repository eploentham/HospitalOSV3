package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;

public class BillingSubgroup extends Persistent 
{


        public String visit_id;
        public String billing_id;
        public String patient_id;
        public String category_group_item_id;
        public String billing_group_item_id;
        public String patient_share;
        public String payer_share;
        public String total;
        public String draw;
   
   /**
    * @roseuid 3F658BBB036E
    */
   public BillingSubgroup() 
   {
    
   }

}
