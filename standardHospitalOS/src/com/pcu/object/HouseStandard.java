/*
 * HouseStandard.java
 *
 * Created on 11 ÁÔ¶Ø¹ÒÂ¹ 2548, 11:57 ¹.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class HouseStandard extends Persistent{
    private static String init = "";
    public String sub_home_id = init;
    public String durability = init;
    public String care = init;
    public String light = init;
    public String cleanness = init;
    public String ventilation = init;
    public String house_standard = init;
    
    /** Creates a new instance of HouseStandard */
    public HouseStandard() {
    }
    
}
