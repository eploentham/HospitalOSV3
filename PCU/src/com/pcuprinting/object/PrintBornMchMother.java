package com.pcuprinting.object;
import java.util.*;
/**
 *
 * @author Administrator
 */
public class PrintBornMchMother
{    
  /** Creates a new instance of PrintDrugRx */
    public String pHN = "hn";
    public String pPrefix = "prefix";
    public String pName = "firstname";
    public String pLastName = "lastname";
    public String pbornplace = "bornplace";
    public String pexecutor = "executor";
    public String pnameplaceborn = "nameplaceborn";
    public String pmethodgivebirth = "methodgivebirth";
    public String pabnormalinpregnant = "abnormalinpregnant";
    public String pabnormalbeforpregnant = "abnormalAfterpregnant";    
    public Map theMap;
    public PrintBornMchMother() 
    {
        theMap = new HashMap();
    }
    
    public void setHn(String name)
    {
        setMap(pHN,name);
    }
    
    public void setBornplace(String name)
    {
        setMap(pbornplace,name);
    }
    public void setExecutor(String name)
    {
        setMap(pexecutor,name);
    }
    public void setNameplaceborn(String name)
    {
        setMap(pnameplaceborn,name);
    }
    public void setMethodGiveBirth (String name)
    {
        setMap(pmethodgivebirth,name);
    }
    public void setAbnormalInPregnant(String name)
    {
        setMap(pabnormalinpregnant,name);
    }
    public void setAbnormalBeforPregnant(String name)
    {
        setMap(pabnormalbeforpregnant,name);
    }    
     public void setPrefix(String name)
    {
        setMap(pPrefix,name);
    }
    public void setFirstName(String name)
    {
        setMap(pName,name);
    }
    public void setLastName(String name)
    {
        setMap(pLastName,name);
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
