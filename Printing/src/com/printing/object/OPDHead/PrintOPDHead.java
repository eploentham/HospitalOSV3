/*
 * PrintOPDHead.java
 *
 * Created on 22 �á�Ҥ� 2547, 18:17 �.
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
    /*�Ţ�������*/
    public void setHn(String name)
    {
        setMap(pHn,name);
    }
    /*���ͼ�����*/
    public void setFName(String name)
    {
        setMap(pFName,name);
    }
    /*���ʡ�ż�����*/
    public void setLName(String name)
    {
        setMap(pLName,name);
    }
    /*����-ʡ�� ������*/
    public void setName(String name)
    {
        setMap(pName,name);
    }
    /*����*/
    public void setAge(String name)
    {
        setMap(pAge,name);
    }
    /*�ѹ�Դ������*/
    public void setBirthDate(String name)
    {
        setMap(pBirthDate,name);
    }
    /*�ѹ���*/
    public void setDate(String name)
    {
        setMap(pDate,name);
    }
    /*�Դ�*/
    public void setFatherName(String name)
    {
        setMap(pFatherName,name);
    }
    /*��ô�*/
    public void setMotherName(String name)
    {
        setMap(pMotherName,name);
    }
    /*�������ͧ������*/
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
