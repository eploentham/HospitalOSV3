/*
 * PrintXrayIndex.java
 *
 * Created on 19 æƒ…¿“§¡ 2547, 16:43 π.
 */

package com.printing.object.Xray;
import java.util.*;
import com.printing.usecase.*;
/**
 *
 * @author  tong
 */
public class PrintXrayIndex implements ObjectInf
{
    
    /** Creates a new instance of PrintXrayIndex */
    public String pname = "name";
    public String page = "age" ;
    public String pxn = "xn" ;
    public String pxray_date = "xray_date" ;
    public String phospital = "hospital" ;
    public Map printxrayindex ;
    
    public PrintXrayIndex() {
        printxrayindex = new HashMap();
    }
    
    public void setName(String name)
    {
        setMap(pname,name);
    }
    public void setAge(String name)
    {
        setMap(page,name);
    }
    public void setXn(String name)
    {
        setMap(pxn,name);
    }
    public void setXRayDate(String name)
    {
        setMap(pxray_date,name);
    }
    public void setHospital(String name)
    {
        setMap(phospital,name);
    }
    
    
    
    
    public void setMap(String Param, String Data)
    {
        printxrayindex.put(Param,Data);
    }
    
    public Map getData()
    {
        return printxrayindex;
    }
}
