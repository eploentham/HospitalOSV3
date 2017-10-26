/*
 *
 * Created on 03 ÁÔ¶Ø¹ÒÂ¹ 2547, 16:12 ¹.
 */

package com.printing.object.AppointmentForPatient;
import java.util.*;
import com.printing.usecase.*;
/**
 *
 * @author  ojika
 */
public class PrintAppointmentForPatient implements ObjectInf
{
    
    /** Creates a new instance of PrintAppointmentForPatient */
    public String pname = "name";
    public String phn = "hn";
    public String pappoint_date = "appoint_date";
    public String pappoint_time = "appoint_time";
    public String pappoint_cause = "appoint_cause";
    public String pdoctor = "doctor";
    public String pdetail = "detail";
    public String phospital = "hospital";
    public Map printappointmentpatient;
    
    public PrintAppointmentForPatient() {
        printappointmentpatient = new HashMap();
    }
    
    public void setName(String name)
    {
        setMap(pname,name);
    }
    public void setHn(String name)
    {
        setMap(phn,name);
    }
    public void setAppointDate(String name)
    {
        setMap(pappoint_date,name);
    }
    public void setAppointTime(String name)
    {
        setMap(pappoint_time,name);
    }
    public void setAppointCause(String name)
    {
        setMap(pappoint_cause,name);
    }
    public void setDoctor(String name)
    {
        setMap(pdoctor,name);
    }
    public void setDetail(String name)
    {
        setMap(pdetail,name);
    }
    public void setHospital(String name)
    {
        setMap(phospital,name);
    }
    
    
    
    
    public void setMap(String Param, String Data)
    {
        printappointmentpatient.put(Param,Data);
    }
    
    public Map getData()
    {
        return printappointmentpatient;
    }
}
