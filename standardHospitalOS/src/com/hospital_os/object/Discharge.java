package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;

public class Discharge extends Persistent 
{


        public String description;
   
   /**
    * @roseuid 3F658BBB036E
    */
   public Discharge() 
   {
    
   }
    public Discharge(String code,String str) 
   {
        setObjectId(code);
        description = str;
   }

}
