/*
 * PrintSummaryReport2.java
 *
 * Created on 9 กรกฎาคม 2551, 10:47 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hosv3.object.printobject;

import com.printing.object.SummaryReport.PrintSummaryReport;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Pu
 */
public class PrintSummaryReport2 extends PrintSummaryReport
{
    public String picd10_primary = "icd10_primary";
    public String picd10_comorbidity = "icd10_comorbidity";
    public String picd10_complication = "icd10_complication";
    public String picd10_external = "icd10_external";
    public String picd10_other = "icd10_other";
    public Map printSummaryReport2;
    /** Creates a new instance of PrintSummaryReport2 */
    public PrintSummaryReport2()
    {
        printSummaryReport2 = new HashMap();
    }
    
    public void setPrimaryICD10(String name)
    {
        setMap(picd10_primary,name);
    }
    public void setComorbidityICD10(String name)
    {
        setMap(picd10_comorbidity,name);
    }
    public void setComplicationICD10(String name)
    {
        setMap(picd10_complication,name);
    }
    public void setExternalICD10(String name)
    {
        setMap(picd10_external,name);
    }
    public void setOtherICD10(String name)
    {
        setMap(picd10_other,name);
    }
    
    public void setMap(String Param, String Data)
    {
        printSummaryReport2.put(Param,Data);
    }
    
    public Map getData()
    {
        return printSummaryReport2;
    }
}
