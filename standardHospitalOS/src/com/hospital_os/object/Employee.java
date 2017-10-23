package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.usecase.connection.CommonInf;
/**
 *
 * @author ekapop
    * 1.  60-10-23 เรื่อง ห้อง     Hospital OS เข้าใจว่า ไม่มีห้อง
    * Modify doc 6.
    */
public class Employee extends Persistent  implements CommonInf
{
        public String employee_id = "";
        public String password = "";
        public String fname = "";
        public String lname = "";
        public String employee_no = "";
        public String last_login = "";
        public String last_logout = "";
        public String active = "";
        public String default_service_id = "";
        public String level_id = "";
        public String rule_id = "";
        public String authentication_id = "";
        public String warning_dx = "0";
        public String default_tab = "";
        
        public String status_admission = "";        //+1
   
   /**
    * @roseuid 3F658BBB036E
    */
   public Employee() 
   {
    
   }
   public Employee(String eid) 
   {
        setObjectId(eid);
   }
    public String getCode() {
        return this.getObjectId();
    }
    
    public String getName() {
        return this.fname + "  " + this.lname;
    }
    public String toString(){
        return getName();
    }
}
