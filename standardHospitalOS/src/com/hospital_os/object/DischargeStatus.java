package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;

/**
 * 
 * @not deprecated henbe จำไม่ได้แล้วไม่รู้ว่าเพราะอะไรถึง deprecated
 */
public class DischargeStatus extends Persistent 
{

//1	หายขาด
//2	ดีขึ้น
//3	ไม่ดีขึ้น
//4	คลอดปกติ
//5	ยังไม่คลอด
//6	เด็กปกติ,กลับพร้อมมารดา
//7	เด็กปกติ,กลับแยกพร้อมมารดา
//8	ตายขณะคลอด
//9	ตาย
        
    public static String COMPLETE = "1";
    public static String IMPROVE = "2";
    public static String NOT_IMPROVE = "3";
    public static String NORMAL_BIRTH = "4";
    public static String NON_BIRTH = "5";
    public static String MOTHER_CHILD_HOME = "6";
    public static String MOTHER_CHILD_NON_HOME = "7";
    public static String BIRTH_DEATH = "8";
    public static String DEATH = "9";
    /**
     * description
     */
    public String description = "";
   
   /**
    * @roseuid 3F658BBB036E
    */
    public DischargeStatus() 
   {
        
   }
    public DischargeStatus(String code,String str) 
   {
        setObjectId(code);
        description = str;
   }

}
