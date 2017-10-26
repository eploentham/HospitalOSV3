/*
 * PrintResultLab.java
 *
 * Created on 16 ÁÔ¶Ø¹ÒÂ¹ 2547, 17:19 ¹.
 */

package com.printing.object.ResultLab;
import java.util.*;
import com.printing.usecase.*;
/**
 *
 * @author  ojika
 */
public class PrintResultLab implements ObjectInf{
    
    /** Creates a new instance of PrintResultLab */
    public String phospital = "hospital";
    public String pname = "name";
    public String page = "age";
    public String psex = "sex";
    public String phn = "hn";
    public String pvn = "vn";
    public String pdateVisit = "dateVisit";
    public String ppatientType = "patientType";
    public String pplan = "plan";
    public String pdatePrint = "datePrint";
    
    public Map PrintResultLab;
    
    public PrintResultLab() {
        PrintResultLab = new HashMap();
    }
    
    public void setHospital(String name)
    {
        setMap(phospital,name);
    }
    
    public void setName(String name)
    {
        setMap(pname,name);
    }
    
    public void setAge(String name)
    {
        setMap(page,name); 
    }
    
    public void setSex(String name)
    {
        setMap(psex,name);
    }
    
    public void setHn(String name)
    {
        setMap(phn,name);
    }
    
    public void setVn(String name)
    {
        setMap(pvn,name);
    }
    
    public void setDateVisit(String name)
    {
        setMap(pdateVisit,name);
    }
    
    public void setDatePrint(String name)
    {
        setMap(pdatePrint,name);
    }
    
    public void setPlan(String name)
    {
        setMap(pplan,name);
    }
    
    public void setPatientType(String name)
    {
        setMap(ppatientType,name);
    }
    
    public void setMap(String Param, String Data)
    {
        PrintResultLab.put(Param,Data);
    }
    
    public Map getData()
    {
        return PrintResultLab;
    }
    
}
