/*
 * PrintOPDDetail.java
 *
 * Created on 22 กรกฎาคม 2547, 18:17 น.
 */

package com.printing.object.OPDDetail;
import java.util.*;
import com.printing.usecase.*;
/**
 *
 * @author  Ojika
 */
public class PrintOPDDetail implements ObjectInf{
    
    /** Creates a new instance of PrintOPDDetail */
    public String pHn = "Hn";
    public String pPid = "Pid";
    public String pName = "Name";
    public String pAge = "Age";
    public String pSex = "Sex";
    public String pBloodGroup = "BloodGroup";
    public String pBirthDate = "BirthDate";
    public String pMarry = "Marry";
    public String pAddress = "Address";
    public String pOccupation = "Occupation";
    public String pFatherName = "FatherName";
    public String pMotherName = "MotherName";
    public String pTeller = "Teller";
    public String pNation = "Nation";
    public String pRace = "Race";
    public String pReligion = "Religion";
    public String pTellerAddress = "TellerAddress";
    public String pPlan = "Plan";
    public String pPlanCode = "PlanCode";
    public String pAllergy = "Allergy";
    public String pExpPlan = "ExpPlan";
    public String pMainHospital = "MainHospital";
    public String pSubHospital = "SubHospital";
    public String pBarcode = "barcode";
    public Map printOPDDetail;
    
    public PrintOPDDetail() 
    {
        printOPDDetail = new HashMap(); 
    }
    /*เลขที่ทั่วไป*/
    public void setHn(String name)
    {
        setMap(pHn,name);
    }
    /*เลขบัตรประชาชน*/
    public void setPid(String name)
    {
        setMap(pPid,name);
    }
    /*ชื่อ-สกุล*/
    public void setName(String name)
    {
        setMap(pName,name);
    }
    /*อายุ*/
    public void setAge(String name)
    {
        setMap(pAge,name);
    }
    /*เพศ*/
    public void setSex(String name)
    {
        setMap(pSex,name);
    }
    /*กรุ๊ปเลือด*/
    public void setBloodGroup(String name)
    {
        setMap(pBloodGroup,name);
    }
    /*วันเกิด*/
    public void setBirthDate(String name)
    {
        setMap(pBirthDate,name);
    }
    /*สถานภาพสมรส*/
    public void setMarry(String name)
    {
        setMap(pMarry,name);
    }
    /*ที่อยู่ผู้ป่วย*/
    public void setAddress(String name)
    {
        setMap(pAddress,name);
    }
    /*อาชีพ*/
    public void setOccupation(String name)
    {
        setMap(pOccupation,name);
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
    /*ชื่อผู้ติดต่อ*/
    public void setTeller(String name)
    {
        setMap(pTeller,name);
    }
    /*เชื้อชาติ*/
    public void setNation(String name)
    {
        setMap(pNation,name);
    }
    /*สัญชาติ*/
    public void setRace(String name)
    {
        setMap(pRace,name);
    }
    /*ศาสนา*/
    public void setReligion(String name)
    {
        setMap(pReligion,name);
    }
    /*ที่อยู่ผู้ติดต่อ*/
    public void setTellerAddress(String name)
    {
        setMap(pTellerAddress,name);
    }
    /*สิทธิที่ใช้*/
    public void setPlan(String name)
    {
        setMap(pPlan,name);
    }
    /*เลขที่สิทธิ*/
    public void setPlanCode(String name)
    {
        setMap(pPlanCode,name);
    }
    /*แพ้ยา*/
    public void setAllergy(String name)
    {
        setMap(pAllergy,name);
    }
    /*วันที่หมดอายุ*/
    public void setExpPlan(String name)
    {
        setMap(pExpPlan,name);
    }
    /*สถานพยาบาลหลัก*/
    public void setMainHospital(String name)
    {
        setMap(pMainHospital,name);
    }
    /*สถานพยาบาลรอง*/
    public void setSubHospital(String name)
    {
        setMap(pSubHospital,name);
    }
    public void setBarcode(String name)
    {
        setMap(pBarcode,name);
    }
    public void setMap(String Param, String Data)
    {
        printOPDDetail.put(Param,Data);
    }
    
    public Map getData()
    {
        return printOPDDetail;
    }
    
}
