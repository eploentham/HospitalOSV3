/*
 * PrintBillingReport.java
 *
 * Created on 28 กรกฎาคม 2547, 14:32 น.
 */

package com.printing.object.BillingReport;
import java.util.*;
import com.printing.usecase.*;
/**
 *
 * @author  Ojika
 */
public class PrintBillingReport implements ObjectInf
{
    
    /** Creates a new instance of PrintBillingReport */
    public String ppageName = "pageName";
    public String pdateQuery = "dateQuery";
    public String ptimeQuery = "timeQuery";
    public String pdateNow = "dateNow";
    public String ppatientAll = "patientAll";
    public String ppatientOPD = "patientOPD";
    public String ppatientIPD = "patientIPD";
    public String pAllPaid = "AllPaid";
    public String pIPDPaid = "IPDPaid";
    public String pOPDPaid = "OPDPaid";
   
    public Map printBillingReport;
    public PrintBillingReport() 
    {
        printBillingReport = new HashMap();
    }
    
    public void setPageName(String name)
    {
        setMap(ppageName,name);
    }
    
    public void setDateQuery(String name)
    {
        setMap(pdateQuery,name);
    }
    
    public void setTimeQuery(String name)
    {
        setMap(ptimeQuery,name);
    }
    
    public void setDateNow(String name)
    {
        setMap(pdateNow,name);
    }
    
    public void setPatientAll(String name)
    {
        setMap(ppatientAll,name);
    }
    
    public void setPatientOPD(String name)
    {
        setMap(ppatientOPD,name);
    }
    
    public void setPatientIPD(String name)
    {
        setMap(ppatientIPD,name);
    }
    
    public void setAllPaid(String name)
    {
        setMap(pAllPaid,name);
    }
    
    public void setOPDPaid(String name)
    {
        setMap(pOPDPaid,name);
    }
    
    public void setIPDPaid(String name)
    {
        setMap(pIPDPaid,name);
    }
    
    public void setMap(String Param, String Data)
    {
        printBillingReport.put(Param,Data);
    }
    
    public Map getData()
    {
        return printBillingReport;
    }
    
}
