package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;

public class OrderService extends Persistent 
{       
    public String order_code;
    public String description;
    public String icd9_code;
        
   /**
    * @roseuid 3F658BBB036E
    */
   public OrderService()    
   {
    
   }

}
