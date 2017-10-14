/*
 * ResultGiveBirth.java
 *
 * Created on 30 เมษายน 2547, 13:53 น.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author  tong
 */
public class ResultGiveBirth extends Persistent{
    
    /** Creates a new instance of ResultGiveBirth */
    public String description;
    public ResultGiveBirth() {
    }
    /**ปกติ เกิดมีชีพ*/
    public static String isNormal()
    {   
        return "1"; 
    }
    /**แท้ง*/
    public static String isAbort()
    {   
        return "2";
    }
    /**อื่นๆ*/
    public static String isOther()
    {
        return "3";
    }
}
