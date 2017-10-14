package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.usecase.connection.*;

public class OrderItemStatus extends Persistent implements CommonInf
{


        public String description;
   
   /**
    * @roseuid 3F658BBB036E
    */
   public OrderItemStatus() 
   {
    
   }

   public String getCode() {
       return getObjectId();
   }
   
   public String getName() {
       return description;
   }
   public String toString(){
       return description;
   }
   
}
