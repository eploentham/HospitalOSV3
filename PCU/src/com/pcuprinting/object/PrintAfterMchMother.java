/*
 * AttibuteBornMchMother.java
 *
 * Created on 8 กุมภาพันธ์ 2549, 17:00 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcuprinting.object;
import java.util.*;
/**
 *
 * @author Administrator
 */
public class PrintAfterMchMother
{    
  /** Creates a new instance of PrintDrugRx */
    public String pHN = "hn";
    public String pPrefix = "prefix";
    public String pName = "name";
    public String pLastName = "lastname";
    public String pAge = "age";
    public Map theMap;
    public PrintAfterMchMother() 
    {
        theMap = new HashMap();
    }    
    public void setHn(String name)
    {
        setMap(pHN,name);
    }     
    public void setName(String name)
    {
        setMap(pName, name);
    }
    public void setPrefix(String name)
    {
        setMap(pPrefix,name);
    }
    public void setLastName(String name)
    {
        setMap(pLastName, name);
    }
    public void setMap(String Param, String Data)
    {
        theMap.put(Param,Data);
    }    
    public Map getData()
    {
        return theMap;
    }    
}
