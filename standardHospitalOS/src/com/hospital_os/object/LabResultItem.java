package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;

public class LabResultItem extends Persistent 
{


        public String lab_result_item_id;
        public String name;
        public String unit;
        public String item_id;
        public String default_value;
        public String resultType;
        public String number;
        public String min;
        public String max;
        public String labresult_id;
        public String ncd_fbs = "0";
        public String ncd_hct = "0";
        public String flag = "";
        
   
   /**  
    * @roseuid 3F658BBB036E
    */
   public LabResultItem() 
   {
        lab_result_item_id = "";
        name = "";
        unit = "";
        item_id = "";
        default_value = "";
        resultType = "";
        number = "";
        min = "";
        max = "";
        labresult_id = "";
        flag = "";
   }

    public void setNcdFbs(boolean b) {
        ncd_fbs = b?"1":"0";
    }

    public void setNcdHct(boolean b) {
        ncd_hct = b?"1":"0";
    }
}
