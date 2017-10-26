/*
 * PrintDrugfundReceipt.java
 *
 * Created on 08 ธันวาคม 2547, 15:10 น.
 */

package com.printing.object.drugFundReceipt;
import java.util.*;
import com.printing.usecase.*;
/**
 *
 * @author  ojika
 */
public class PrintDrugfundReceipt implements ObjectInf
{
    
    /** Creates a new instance of PrintReceipt */
    public String pdrugfund_name = "drugfund_name";
    public String pdrugfund_address = "drugfund_address";
    public String pdate_visit = "date_visit";
    public String pdrugfund_phone = "drugfund_phone";
    public String pdate_receipt = "date_receipt";
    public String preceipt_total = "receipt_total";
    public String premain = "remain";
    public String ptoday = "today";
    public String preceipt_total_text = "receipt_total_text";
    
    public Map printDrugfundReceipt;
    
    public PrintDrugfundReceipt() 
    {
        printDrugfundReceipt = new HashMap();
    }
        
    public void setReceiptTotalText(String name)
    {
        setMap(preceipt_total_text,name);
    }
    public void setDrugfundName(String name)
    {
        setMap(pdrugfund_name,name);
    }
    public void setDrugfundAddress(String name)
    {
        setMap(pdrugfund_address,name);
    }
     public void setDateVisit(String name)
    {
        setMap(pdate_visit,name);
    }
     public void setDrugfundPhone(String name)
    {
        setMap(pdrugfund_phone,name);
    }
     public void setDateReceipt(String name)
    {
        setMap(pdate_receipt,name);
    }
     public void setReceiptTotal(String name)
    {
        setMap(preceipt_total,name);
    }
     public void setRemain(String name)
    {
        setMap(premain,name);
    }
     public void setToday(String name)
    {
        setMap(ptoday,name);
    }
    public void setMap(String Param, String Data)
    {
        printDrugfundReceipt.put(Param,Data);
    }
    
    public Map getData()
    {
        return printDrugfundReceipt;
    }
    
}
