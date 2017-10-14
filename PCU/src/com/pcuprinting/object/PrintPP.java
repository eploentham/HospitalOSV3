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
public class PrintPP
{    
  /** Creates a new instance of PrintDrugRx */
    public String pHN = "hn";
    public String pPrefix = "prefix";
    public String pName = "firstname";
    public String pLaseName = "lastname";
    public String birthdatelong = "birthdatelong";
    public String birthdateshort = "birthdateshort";
    public String sex = "sex";
    public String wieght = "wieght";
    public String width = "width";
    public String pprimhead = "pprimhead";
    public String methodbearing = "methodbearing";
    public String oneminuteapgarscore = "oneminuteapgarscore";
    public String fiveminuteapgarscore = "fiveminuteapgarscore";
    public String complication = "complication";
    public String vitaminK = "vitaminK";
    public String initialcondition = "initialcondition";
    public String activity = "activity";
    public String color = "color";
    public String tenminuteapgarscore = "tenminuteapgarscore";
    public Map theMap;
    public PrintPP() 
    {
        theMap = new HashMap();
    }      
    public void setHn(String name)
    {
        setMap(pHN,name);
    }
    public void setPrefix(String name)
    {
        setMap(pPrefix,name);
    }
     public void setName(String name)
    {
        setMap(pName,name);
    }
     public void setLastName(String name)
    {
        setMap(pLaseName,name);
    }
    public void setBirthDateLong(String name)
    {
        setMap(birthdatelong,name);
    }
    public void setBirthDateShort(String name)
    {
        setMap(birthdateshort,name);
    }
    public void setSex(String name)
    {
        setMap(sex,name);
    }
    public void setWieght(String name)
    {
        setMap(wieght,name);
    }
    public void setWidth(String name)
    {
        setMap(width,name);
    }
    public void setPPrimhead(String name)
    {
        setMap(pprimhead,name);
    }
    public void setMethodBearing(String name)
    {
        setMap(methodbearing,name);
    }
    public void setOneMinuteApgarscore(String name)
    {
        setMap(oneminuteapgarscore,name);
    }
     public void setFiveMinuteApgarscore(String name)
    {
        setMap(fiveminuteapgarscore,name);
    }
    public void setComplication(String name)
    {
        setMap(complication,name);
    }
     public void setVitaminK(String name)
    {
        setMap(vitaminK,name);
    }
     public void setInitialCondition(String name)
     {
         setMap(initialcondition,name);
     }
     public void setActivity(String name)
     {
         setMap(activity,name);
     }
     public void setColor(String name)
     {
         setMap(color,name);
     }
     public void setTenMinuteApgarscore(String name)
     {
         setMap(tenminuteapgarscore,name);
     }
    public void setMap(String Param, String Data)
    {
        if(Data == null)
        {   
            Data = "";
        }
        theMap.put(Param,Data);
    }    
    public Map getData()
    {
        return theMap;
    }    
}
