/*
 * ResultXraySize.java
 *
 * Created on 15 เมษายน 2547, 8:50 น.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author  tong
 */
public class ResultXraySize extends Persistent  {
    
    /** Creates a new instance of OrderResultXraySize */
    public String result_xray_id;
    public String visit_id;
    public String order_item_id;
    public String film_size;
    public String num_film;
    public String user;
    public String price;
    public String active;
    public String add_order;
    public String damaging_film;  
    
    public ResultXraySize() {
        
        film_size = new String();
        num_film = new String();
        user = new String();
        visit_id = new String();
        order_item_id = new String();
        active = "1";
        price = "0";
        add_order = "1";
        damaging_film = new String();
    }
    
}
