/*
 * PrintReceipt.java
 *
 * Created on 25 ÁÔ¶Ø¹ÒÂ¹ 2547, 16:39 ¹.
 */

package com.printing.object.Receipt;
import java.util.*;
import com.printing.usecase.*;
/**
 *
 * @author  ojika
 */
public class PrintReceipt implements ObjectInf
{
    
    /** Creates a new instance of PrintReceipt */
    public String phospital = "hospital";
    public String phn = "hn";
    public String pvn = "vn";
    public String ppatient_name = "patient_name";
    public String pdate = "date";
    public String pplan = "plan";
    public String pdisease = "disease";
    public String psumOpen = "sumOpen";
    public String psumClose = "sumClose";
    public String psumReceive = "sumReceive";
    public String psumDetail = "sumDetail";
    public String premain = "remain";
    public String preceiver = "receiver";
    public String pposition = "position";
    public String prn = "rn";
    
    public Map printreceipt;
    
    public PrintReceipt() 
    {
        printreceipt = new HashMap();
    }
    public void setHospital(String name)
    {
        setMap(phospital,name);
    }
    public void setHn(String name)
    {
        setMap(phn,name);
    }
    public void setVn(String name)
    {
        setMap(pvn,name);
    }
     public void setPatientName(String name)
    {
        setMap(ppatient_name,name);
    }
     public void setDate(String name)
    {
        setMap(pdate,name);
    }
     public void setPlan(String name)
    {
        setMap(pplan,name);
    }
     public void setDisease(String name)
    {
        setMap(pdisease,name);
    }
     public void setSumOpen(String name)
    {
        setMap(psumOpen,name);
    }
     public void setSumClose(String name)
    {
        setMap(psumClose,name);
    }
     public void setSumReceive(String name)
    {
        setMap(psumReceive,name);
    }
     public void setSumDetail(String name)
    {
        setMap(psumDetail,name);
    }
     public void setRemain(String name)
    {
        setMap(premain,name);
    }
     public void setReceiver(String name)
    {
        setMap(preceiver,name);
    }
     public void setPosition(String name)
    {
        setMap(pposition,name);
    }
     
     public void setRn(String name)
     {
        setMap(prn,name);
     }
    
    public void setMap(String Param, String Data)
    {
        printreceipt.put(Param,Data);
    }
    
    public Map getData()
    {
        return printreceipt;
    }
    
}
