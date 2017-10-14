package com.hospital_os.object;

//import com.hospital_os.utility.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.usecase.connection.Persistent;

public class Contract extends Persistent implements CommonInf
{


        public String contract_id;
        public String description;
        public String method;
   
   /**
    * @roseuid 3F658BBB036E
    */
   public Contract() 
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
