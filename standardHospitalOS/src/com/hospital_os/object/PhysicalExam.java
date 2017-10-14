/*
 * PhysicalExam.java
 *
 * Created on 20 ตุลาคม 2546, 12:48 น.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.usecase.connection.*;
import java.util.*;
/**
 *
 * @author  tong
 */
public class PhysicalExam extends Persistent 
{
    
    /** Creates a new instance of PhysicalExam */
    
    public String physical_body="";
    public String detail;
    public String visit_id;
    public String patient_id;
    public String date_time;
    public String executer;
    
    public PhysicalExam() 
    {
        
    }
    public static String getForTextArea(Vector vPhysicalExam)
    {
        //Constant.println("vPHysicalExam" + vPhysicalExam.size());
        return getForTextArea(vPhysicalExam, ":");
    }
    public static String getForTextArea(Vector vPhysicalExam,String separate)
    {
        String physical_ex = "";
        String body = "";
        for(int i=0;vPhysicalExam!=null && i<vPhysicalExam.size();i++)
        {
            PhysicalExam pe = (PhysicalExam)vPhysicalExam.get(i);
//            Constant.println(" physical_body" + pe.physical_body);  
//            Constant.println(" detail" + pe.detail);   
            if(physical_ex.equals(""))
                physical_ex = pe.physical_body.trim() + separate +" " + pe.detail.trim();
            else
            {
                if(pe.physical_body.equals(body))
                    physical_ex += ", " + pe.detail.trim();
                else
                    physical_ex += "\n" + pe.physical_body.trim() + separate +" " +  pe.detail.trim();
            }
            body = pe.physical_body;
        }

        return physical_ex;
    }
}
