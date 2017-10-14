package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;

public class ItemService extends Persistent 
{       
    public String item_id="";
    public String description="";
    public String icd9_code="";
    public String active = "";
    public String icd9_code_tmp = "";
    public String record_date_time = "";
    public String check = "0";
   /**
    * @roseuid 3F658BBB036E
    */
   public ItemService()    
   {
    
   }

}
