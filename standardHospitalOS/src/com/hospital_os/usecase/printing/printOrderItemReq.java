/*
 * printOrderItemReq.java
 *
 * Created on 2 ¾ÄÈ¨Ô¡ÒÂ¹ 2547, 17:18 ¹.
 */

package com.hospital_os.usecase.printing;
import com.hospital_os.object.*;

/**
 *
 * @author  tong
 */
public interface printOrderItemReq {
    
    /** Creates a new instance of printOrderItemReq */
    public void printOrderItem(Patient patient ,int array[], int valuePrint,boolean drug,boolean xray,boolean service,boolean lab,boolean supply,int typePrint,String employeeid,String nameDoctor,java.util.Vector vDoctor);
    
}
