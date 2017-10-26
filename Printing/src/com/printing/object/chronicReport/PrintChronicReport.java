/*
 * PrintChronicReport.java
 *
 * Created on 08 ธันวาคม 2547, 15:10 น.
 */

package com.printing.object.chronicReport;
import java.util.*;
import com.printing.usecase.*;
/**
 *
 * @author  ojika
 */
public class PrintChronicReport implements ObjectInf
{
    
    /** Creates a new instance of PrintReceipt */
    public String pdate_start_query = "date_start_query";
    public String pdate_end_query = "date_end_query";
    public String pstatus_query = "status_query";
    public String ptoday = "today";
    public Map printChronicReport;
    
    public PrintChronicReport() 
    {
        printChronicReport = new HashMap();
    }
        
    public void setDateStartQuery(String name)
    {
        setMap(pdate_start_query,name);
    }
    public void setDateEndQuery(String name)
    {
        setMap(pdate_end_query,name);
    }
    public void setStatusQuery(String name)
    {
        setMap(pstatus_query,name);
    }
    
    public void setToday(String name)
    {
        setMap(ptoday,name);
    }
    
    public void setMap(String Param, String Data)
    {
        printChronicReport.put(Param,Data);
    }
    
    public Map getData()
    {
        return printChronicReport;
    }
    
} 
