/*

 * PrintOPDCard.java

 *

 * Created on 22 �á�Ҥ� 2547, 18:02 �.

 */



package com.printing.object.OPDCard;

import java.util.*;

import com.printing.usecase.*;

/**

 *

 * @author  Ojika

 */

public class PrintOPDCard implements ObjectInf

{    

    /** Creates a new instance of PrintOPDCard */



    public String pFName = "FName";

    public String pLName = "LName";

    public String pDate = "Date";

    

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
    
    public String pRelation = "Relation";

    public String pNation = "Nation";

    public String pRace = "Race";

    public String pReligion = "Religion";

    public String pTellerAddress = "TellerAddress";

    public String pPlan = "Plan";

    public String pPlanCode = "PlanCode";

    public String pAllergy = "Allergy";

    public String pExpPlan = "ExpPlan";
    
    public String pStartPlan = "StartPlan";

    public String pMainHospital = "MainHospital";

    public String pSubHospital = "SubHospital";

    public String pBarcode = "barcode";

    

    public Map printOPDCard;

    

    public PrintOPDCard() 

    {

        printOPDCard = new HashMap(); 

    }

    /*���ͼ�����*/

    public void setDate(String name)

    {

        setMap(pDate,name);

    }

    /*���ͼ�����*/

    public void setFName(String name)

    {

        setMap(pFName,name);

    }
    
    public void setStartPlan(String name)
    {
        setMap(pStartPlan,name);
    }
    
    public void setRelation(String name)
    {
        setMap(pRelation,name);
    }

    /*���ʡ�ż�����*/

    public void setLName(String name)

    {

        setMap(pLName,name);

    }

    /*�Ţ�������*/

    public void setHn(String name)

    {

        setMap(pHn,name);

    }

    /*�Ţ�ѵû�ЪҪ�*/

    public void setPid(String name)

    {

        setMap(pPid,name);

    }

    /*����-ʡ��*/

    public void setName(String name)

    {

        setMap(pName,name);

    }

    /*����*/

    public void setAge(String name)

    {

        setMap(pAge,name);

    }

    /*��*/

    public void setSex(String name)

    {

        setMap(pSex,name);

    }

    /*�������ʹ*/

    public void setBloodGroup(String name)

    {

        setMap(pBloodGroup,name);

    }

    /*�ѹ�Դ*/

    public void setBirthDate(String name)

    {

        setMap(pBirthDate,name);

    }

    /*ʶҹ�Ҿ����*/

    public void setMarry(String name)

    {

        setMap(pMarry,name);

    }

    /*������������*/

    public void setAddress(String name)

    {

        setMap(pAddress,name);

    }

    /*�Ҫվ*/

    public void setOccupation(String name)

    {

        setMap(pOccupation,name);

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

    /*���ͼ��Դ���*/

    public void setTeller(String name)

    {

        setMap(pTeller,name);

    }

    /*���ͪҵ�*/

    public void setNation(String name)

    {

        setMap(pNation,name);

    }

    /*�ѭ�ҵ�*/

    public void setRace(String name)

    {

        setMap(pRace,name);

    }

    /*��ʹ�*/

    public void setReligion(String name)

    {

        setMap(pReligion,name);

    }

    /*���������Դ���*/

    public void setTellerAddress(String name)

    {

        setMap(pTellerAddress,name);

    }

    /*�Է�Է����*/

    public void setPlan(String name)

    {

        setMap(pPlan,name);

    }

    /*�Ţ����Է��*/

    public void setPlanCode(String name)

    {

        setMap(pPlanCode,name);

    }

    /*����*/

    public void setAllergy(String name)

    {

        setMap(pAllergy,name);

    }

    /*�ѹ����������*/

    public void setExpPlan(String name)

    {

        setMap(pExpPlan,name);

    }

    /*ʶҹ��Һ����ѡ*/

    public void setMainHospital(String name)

    {

        setMap(pMainHospital,name);

    }

    /*ʶҹ��Һ���ͧ*/

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

        printOPDCard.put(Param,Data);

    }

    

    public Map getData()

    {

        return printOPDCard;

    }

        

}

