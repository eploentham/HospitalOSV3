/*
 * Timing.java
 *
 * Created on 18 ตุลาคม 2546, 13:28 น.
 */

package com.hospital_os.utility;
import com.hospital_os.usecase.connection.ConnectionInf;

/**
 *
 * @author  Administrator
 */
public class Timing {
    
    /** Creates a new instance of Timing */
    private static String year = "";
    public Timing() {
    }
    public static String getYear2Digit(ConnectionInf con)
    {
        /*
        Calendar c=Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        return String.valueOf(c.get(c.YEAR)).substring(2);
         */
        
        year = Gutil.getTextCurrentDate(con);
        year = year.substring(2,4);
        
        return year;
        
    }
    
}
