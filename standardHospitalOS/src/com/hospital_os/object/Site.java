package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 * 
 * @author ekapop
 * 1. 60-10-27 เรื่อง FTP scan เอกสารเก็บเข้า server
 * Modify doc 10.
 */
public class Site extends Persistent 
{


        public String off_id;
        public String off_name;
        public String full_name;
        public String address;
        public String village;
        public String tambon;
        public String amphor;
        public String changwat;
        public String postcode;
        public String admin;
        public String tel;
        public String opd_type;
        public String receipt_option;
        public String hn_for_drug_fund;
        public String server_image_reserve_name;        //+1
        public String local_folder_image_reserve_name;        //+1
   
   /**
    * @roseuid 3F658BBB036E
    */
   public Site() 
   {
    
   }

}
