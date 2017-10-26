/*

 * PrintVisitslip.java

 *

 * Created on 11 กรกฎาคม 2547, 23:20 น.

 */



package com.printing.object.VisitSlipNew;

import java.util.*;

import com.printing.usecase.*;

/**

 *

 *  @author  ojika

 *  เป็น Object หนึ่งของ VisitSlip โดยการรับค่ามาแบบ set

 *  ข้อมูลออกคือ getData ส่งไปให้กับ Print

 */

public class PrintVisitSlipNew implements ObjectInf{

    

    /** Creates a new instance of PrintVisitslip */

    public String phospital = "hospital";

    public String pclinic = "clinic";

    public String ppn = "pn";

    public String pname = "name";

    public String page = "age";

    public String ppid = "pid";

    public String phn = "hn";

    public String pplan = "plan";

    public String pplanCode = "planCode";

    public String pmainHospital = "mainHospital";    

    public String pdx = "dx";   

    public String pdoctor = "doctor";

    public String pdateVisit = "dateVisit";

    public String psex = "sex";

    public String ptoday = "today";
    
    public String pbirthday = "birthday";
    
    public String psubHospital = "subHospital";

    public String paddress = "address";
    
    public String ppatientAllergy = "patientAllergy";

    public Map PrintVisitSlip;

    

    public PrintVisitSlipNew() 

    {

        PrintVisitSlip = new HashMap();

    }

    
    public void setAge(String name)

    {

        setMap(page,name);

    }
    
    
    public void setPatientAllergy(String name)

    {

        setMap(ppatientAllergy,name);

    }

           

    public void setClinic(String name)

    {

        setMap(pclinic,name);

    }        

    

    public void setDateVisit(String name)

    {

        setMap(pdateVisit,name);

    }

    

    public void setDoctor(String name)

    {

        setMap(pdoctor,name);

    }

    

    public void setDx(String name)

    {

        setMap(pdx,name);

    }

    

    public void setHn(String name)

    {

        setMap(phn,name);

    }

    

    public void setHospital(String name)

    {

        setMap(phospital,name);

    }  

    

    public void setMainHospital(String name)

    {

        setMap(pmainHospital,name);

    }    

    

    public void setName(String name)

    {

        setMap(pname,name);

    }

    

    public void setPid(String name)

    {

        setMap(ppid,name);

    }

    

    public void setPlan(String name)

    {

        setMap(pplan,name);

    }

    

    public void setPlanCode(String name)

    {

        setMap(pplanCode,name);

    }

    

    public void setPn(String name)

    {

        setMap(ppn,name);

    }

        

    public void setSex(String name)

    {

        setMap(psex,name);

    }

    

    public void setToday(String name)

    {

        setMap(ptoday,name);

    }
    
    public void setBirthday(String name)

    {

        setMap(pbirthday,name);

    }
    
    public void setSubHospital(String name)

    {

        setMap(psubHospital,name);

    }

    public void setAddress(String name)
    {
        setMap(paddress,name);
    }
    

    public void setMap(String Param, String Data)

    {

        PrintVisitSlip.put(Param,Data);

    }

    

    public Map getData()

    {

        return PrintVisitSlip;

    }

    

}

