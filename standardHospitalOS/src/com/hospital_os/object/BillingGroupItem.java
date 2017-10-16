package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;

public class BillingGroupItem extends Persistent 
{
    /**
     * 60-10-14
     * 1.       เพิ่ม item_billing_subgroup_description_e, itmcod, itmastcod
     */
    public String billing_group_item_id;
    public String description;
    public String billing_group_code;
    public String active;
    public String description_e;        //+1
    public String itmcod;        //+1
    public String itmastcod;        //+1
   
   /**
    * @roseuid 3F658BBB036E
    */
   public BillingGroupItem() 
   {
    
   }

}
