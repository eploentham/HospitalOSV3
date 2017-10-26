/*
 * AppointmentList.java
 *
 * Created on 10 �Զع�¹ 2547, 12:48 �.
 */

package com.printing.object.AppointmentList;
import java.util.*;
import com.printing.usecase.*;

/**
 *
 * @author  ojika
 */
public class PrintAppointmentList implements ObjectInf
{
    
    /** Creates a new instance of AppointmentList */
    public String phospital = "hospital";
    public String pservicePoint = "servicePoint";
    public String pstartDate = "startDate";
    public String pendDate = "endDate";
    public Map Appointmentlist;
    
    public PrintAppointmentList() 
    {
        Appointmentlist = new HashMap();
    }
    
    public void setHospital(String name)
    {
        setMap(phospital,name);
    }
    public void setServicePointAppList(String name)
    {
        setMap(pservicePoint,name);
    }
    public void setStartDate(String name)
    {
        setMap(pstartDate,name);
    }
    public void setEndDate(String name)
    {
        setMap(pendDate,name);
    }
    
    public void setMap(String Param, String Data)
    {
        Appointmentlist.put(Param,Data);
    }
    
    public Map getData()
    {
        return Appointmentlist;
    }
    
}
