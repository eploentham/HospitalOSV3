/*
 * CheckPID.java
 *
 * Created on 24 ธันวาคม 2546, 17:07 น.
 */

package com.hospital_os.utility;

/**
 *
 * @author  Administrator
 */
public class CheckPID {
    
    /** Creates a new instance of CheckPID */
    public CheckPID() {
    }
    
    /**
        ตรวจสอบเลขที่บัตรประชาชน ถ้าตรงกันจะ return 1
     *  ถ้าไม่ตรงกัน จะ return 0
     *  ถ้าไม่ครบจะ return -1
     */
    public int CheckPIDResult(String pid)
    {
        int a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13;
        int sum =0;
        int values =0;
        
        if(pid.length() !=13)
        {   
            return -1;
        }
        else
        {
            a13 = Integer.parseInt(pid.substring(0,1));
            a12 = Integer.parseInt(pid.substring(1,2));
            a11 = Integer.parseInt(pid.substring(2,3));
            a10 = Integer.parseInt(pid.substring(3,4));
            a9 = Integer.parseInt(pid.substring(4,5));
            a8 = Integer.parseInt(pid.substring(5,6));
            a7 = Integer.parseInt(pid.substring(6,7));
            a6 = Integer.parseInt(pid.substring(7,8));
            a5 = Integer.parseInt(pid.substring(8,9));
            a4 = Integer.parseInt(pid.substring(9,10));
            a3 = Integer.parseInt(pid.substring(10,11));
            a2 = Integer.parseInt(pid.substring(11,12));
            a1 = Integer.parseInt(pid.substring(12,13));

            sum = (13 * a13) + (12 *a12 ) + (11 * a11)+ (10 * a10) + ( 9 * a9 )+( 8 * a8 )+(7 * a7)+(6 * a6)+(5 * a5)+(4 * a4)+(3 * a3)+( 2 * a2);

            values = ((11 -  (sum%11)) % 10);
            if(values == a1)
            {   return 1;
            }
            else
            {   return 0;
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CheckPID c = new CheckPID();
     /*   Constant.println("Values : " + c.CheckPIDResult("3959900337049"));
*/
        
        
    }
    
}
