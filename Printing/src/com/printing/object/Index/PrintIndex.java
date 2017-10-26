/*
 * PrintIndex.java
 *
 * Created on 18 ÁÔ¶Ø¹ÒÂ¹ 2547, 17:16 ¹.
 */

package com.printing.object.Index;
import java.util.*;
import com.printing.usecase.*;
/**
 *
 * @author  ojika
 */
public class PrintIndex implements ObjectInf
{
    
    /** Creates a new instance of PrintIndex */
    public String phospital = "hospital";
    public String phn ="hn";
    public String pname = "name";
    public String pmotherName = "motherName";
    public String pbirthdate = "birthdate";
    public String paddress = "address";
    public Map printindex;
    
    public PrintIndex()
    {
        printindex = new HashMap();
    }
    
    public void setHospital(String name)
    {
        setMap(phospital,name);
    }
    
    public void setHn(String name)
    {
        setMap(phn,name);
    }
    
    public void setName(String name)
    {
        setMap(pname,name);
    }
    
    public void setMotherName(String name)
    {
        setMap(pmotherName,name);
    }
    
    public void setBirthdate(String name)
    {
        setMap(pbirthdate,name);
    }
    
    public void setAddress(String name)
    {
        setMap(paddress,name);
    }
    
    public void setMap(String Param, String Data)
    {
        printindex.put(Param,Data);
    }
    
    public Map getData()
    {
        return printindex;
    }
    
}
