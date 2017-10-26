/*
 *
 * Created on 15 ÁÔ¶Ø¹ÒÂ¹ 2548, 15:58 ¹.
 */

package com.printing.object.stickerIPD;
import java.util.*;
import com.printing.usecase.*;
/**
 *
 * @author  ojika
 */
public class PrintIPD implements ObjectInf
{
    
    /** Creates a new instance of PrintAppointmentForPatient */
    public String pfname = "fname";
    public String plname = "lname";
    public String phn = "hn";
    public String psex = "sex";
    public String pprefix = "prefix";
    public String pan = "an";
    public String pdoctor = "doctor";
    public String pbirthday = "birthday";
    public String page = "age";
    public String page_long = "age_long";
    public String pward = "ward";
    public String pdate_admit = "date_admit";
    public String ptime_admit = "time_admit";
    
    public Map printIpd;
    
    public PrintIPD() {
        printIpd = new HashMap();
    }
    
    public void setFname(String name)
    {
        setMap(pfname,name);
    }
    public void setLname(String name)
    {
        setMap(plname,name);
    }
    public void setHn(String name)
    {
        setMap(phn,name);
    }
    public void setPrefix(String name)
    {
        setMap(pprefix,name);
    }
    public void setSex(String name)
    {
        setMap(psex,name);
    }
    public void setAn(String name)
    {
        setMap(pan,name);
    }
    public void setDoctor(String name)
    {
        setMap(pdoctor,name);
    }
    public void setBirthday(String name)
    {
        setMap(pbirthday,name);
    }
    public void setAge(String name)
    {
        setMap(page,name);
    }
    public void setAgeLong(String name)
    {
        setMap(page_long,name);
    }
    public void setWard(String name)
    {
        setMap(pward,name);
    }
    public void setDateAdmit(String name)
    {
        setMap(pdate_admit,name);
    }
    public void setTimeAdmit(String name)
    {
        setMap(ptime_admit,name);
    }
    
    
    
    public void setMap(String Param, String Data)
    {
        printIpd.put(Param,Data);
    }
    
    public Map getData()
    {
        return printIpd;
    }
}
