/*
 * ResultGiveBirth.java
 *
 * Created on 30 ����¹ 2547, 13:53 �.
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
    /**���� �Դ�ժվ*/
    public static String isNormal()
    {   
        return "1"; 
    }
    /**��*/
    public static String isAbort()
    {   
        return "2";
    }
    /**����*/
    public static String isOther()
    {
        return "3";
    }
}
