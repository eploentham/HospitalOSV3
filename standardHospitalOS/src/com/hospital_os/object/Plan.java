package com.hospital_os.object;

import com.hospital_os.usecase.connection.*;

public class Plan extends Persistent implements CommonInf
{
	public static String SELF_PAY="0000000000000";
        public String sort_index;   
        public String plan_id;
        public String description;
        public String active_from;
        public String active_to;
        public String active;
        public String pttype;
        public String money_limit;
        public String payer_id;
        public String contract_id;
        public String pttype53;
   
   /**
    * @roseuid 3F658BBB036E
    */
   public Plan() 
   {
        plan_id="";
        description="";
        active_from="";
        active_to="";
        active="";
        pttype="";
        money_limit="";
        payer_id="";
        contract_id="";  
        sort_index=""; 
        pttype53="";

   }
    public String getCode(){return getObjectId();}
    public String getName(){return description;}
    public String toString(){return description;}

    public boolean isActive() {
        return active.equals("1");
    }
}
