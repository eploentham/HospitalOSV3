/*
 * Company.java
 *
 * Created on 10 �Զع�¹ 2548, 18:13 �.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class Company extends Persistent{
    private static String init = "";
    public String village_id = init;
    public String company_number = init;
    public String company_name = init;     
    public String company_staff_record = init;
    public String company_staff_modify = init;
    public String company_staff_cancel = init;
    public String company_record_date_time = init;
    public String company_modify_date_time = init;
    public String company_cancel_date_time = init;
    public String company_close = init;
    public String company_close_date_time = init;
    public String company_close_note = init;
    public String company_active = init;
    
    /** Creates a new instance of Company */
    public Company() {
    }
    
}
