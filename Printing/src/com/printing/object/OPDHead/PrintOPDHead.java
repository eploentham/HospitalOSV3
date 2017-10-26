/*
 * PrintOPDHead.java
 *
 * Created on 22 กรกฎาคม 2547, 18:17 น.
 */

package com.printing.object.OPDHead;
import java.util.*;
import com.printing.usecase.*;
/**
 *
 * @author  Ojika
 */
public class PrintOPDHead implements ObjectInf{
    
    /** Creates a new instance of PrintOPDHead */
    public String pHn = "Hn";
    public String pFName = "FName";
    public String pLName = "LName";
    public String pName = "Name";
    public String pBirthDate = "BirthDate";
    public String pAge = "Age";
    public String pDate = "Date";
    public String pFatherName = "FatherName";
    public String pMotherName = "MotherName";
    public String pAddress = "Address";
    public Map printOPDHead;
    
    public PrintOPDHead() 
    {
        printOPDHead = new HashMap(); 
    }
    /*เลขที่ทั่วไป*/
    public void setHn(String name)
    {
        setMap(pHn,name);
    }
    /*ชื่อผู้ป่วย*/
    public void setFName(String name)
    {
        setMap(pFName,name);
    }
    /*นามสกุลผู้ป่วย*/
    public void setLName(String name)
    {
        setMap(pLName,name);
    }
    /*ชื่อ-สกุล ผู้ป่วย*/
    public void setName(String name)
    {
        setMap(pName,name);
    }
    /*อายุ*/
    public void setAge(String name)
    {
        setMap(pAge,name);
    }
    /*วันเกิดผู้ป่วย*/
    public void setBirthDate(String name)
    {
        setMap(pBirthDate,name);
    }
    /*วันที่*/
    public void setDate(String name)
    {
        setMap(pDate,name);
    }
    /*บิดา*/
    public void setFatherName(String name)
    {
        setMap(pFatherName,name);
    }
    /*มารดา*/
    public void setMotherName(String name)
    {
        setMap(pMotherName,name);
    }
    /*ที่อยู่ของผู้ป่วย*/
    public void setAddress(String name)
    {
        setMap(pAddress,name);
    }
    
    public void setMap(String Param, String Data)
    {
        printOPDHead.put(Param,Data);
    }
    
    public Map getData()
    {
        return printOPDHead;
    }
    
}
