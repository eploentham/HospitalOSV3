/*
 * CompanyHistory.java
 *
 * Created on 10 ÁÔ¶Ø¹ÒÂ¹ 2548, 18:17 ¹.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class CompanyHistory extends Persistent{
    private static String init = "";
    public String company_id = init;
    public String register_id = init;
    public String company_type_id = init;    
    public String owner_name = init;
    public String home_number = init;    
    public String moo = init;
    public String road = init;    
    public String phone = init;
    public String tambol = init;    
    public String amphur = init;    
    public String changwat = init;  
    public String amount_man = init;
    public String amount_woman = init;    
    public String company_co = init;    
    public String company_co_date_time = init;
    public String standard_type_id = init;    
    public String standard_type_description = init;
    public String standard_id = init;   
    public String time_of_pass = init;    
    public String date_of_pass = init;
    public String period_of_pass = init;    
    public String in_market = init;    
    public String company_history_staff_record = init; 
    public String company_history_staff_modify = init; 
    public String company_history_record_date_time = init;
    public String company_history_modify_date_time = init;
    
    /** Creates a new instance of CompanyHistory */
    public CompanyHistory() {
    }
    
}
