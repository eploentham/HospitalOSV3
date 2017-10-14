package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;

public class LabSet extends Persistent 
{
        public String item_id;
        public String lab_group_id;
        
	public String item_name;
        
        public String item_common_name;
        public String has_sub;
   
   /**
    * @roseuid 3F658BBB036E
    */
   public LabSet() 
   {
         item_name = "";
    
   }
   public boolean isHasSub(){
       return has_sub.equals("1");
   }

}
