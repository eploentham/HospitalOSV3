package com.generalreport.object;
import com.generalreport.utility.StandardObject;


public class Employee  extends Persist implements StandardObject 
{


        public String employee_id;
        public String password;
        public String fname;
        public String lname;
        public String employee_no;
        public String last_login;
        public String last_logout;
        public String active;
        public String default_service_id;
        public String level_id;
        public String rule_id;
        public String authentication_id;
   
   /**
    * @roseuid 3F658BBB036E
    */
   public Employee() 
   {
        idTable ="";
        tableName= "b_employee";  
   }

    public void setInitData()
    {
        employee_id = "";
        password = "";
        fname = "";
        lname = "";
        employee_no = "";
        last_login = "";
        last_logout = "";
        active = "";
        default_service_id = "";
        level_id = "";
        rule_id = "";
        authentication_id = "";
    }

   public static String getStringFieldPKTable() 
   {
        return "b_employee_id";    
   }
   
  
   public static String getStringFieldLogin() 
   {
        return "employee_login";    
   }
   
   public static String getStringFieldPassword() 
   {
        return "employee_password";    
   }
   
   public static String getStringFieldFirstName() 
   {
        return "employee_firstname";    
   }
   public static String getStringFieldLastName() 
   {
        return "employee_lastname";    
   }
    public static String getStringFieldEmployeeNumber() 
   {
        return "employee_number";    
   }
    public static String getStringFieldLastLogIn() 
   {
        return "employee_last_login";    
   }
   
     public static String getStringFieldLastLogOut() 
   {
        return "employee_last_logout";    
   }
   public static String getStringFieldActive() 
   {
        return "employee_active";    
   }
   public static String getStringFieldServicePointID() 
   {
        return "b_service_point_id";    
   }
   public static String getStringFieldLevel() 
   {
        return "f_employee_level_id";    
   }
   public static String getStringFieldRule() 
   {
        return "f_employee_rule_id";    
   }
   public static String getStringFieldAuthen() 
   {
        return "f_employee_authentication_id";    
   }
   
   
    public Object setInitDataFieldName()
    {
        this.pk_table = getStringFieldPKTable();
        employee_id = this.getStringFieldLastLogIn();
        password = getStringFieldPassword();
        fname = getStringFieldFirstName();
        lname = getStringFieldLastName();
        employee_no = getStringFieldEmployeeNumber();
        last_login = getStringFieldLastLogIn();
        last_logout = getStringFieldLastLogOut();
        active = getStringFieldActive();
        default_service_id = getStringFieldServicePointID();
        level_id = getStringFieldLevel();
        rule_id = getStringFieldRule();
        authentication_id = getStringFieldAuthen();
        return this;  
    }

}
