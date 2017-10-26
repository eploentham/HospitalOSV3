/*

 * PrintVisitslip.java

 *

 * Created on 11 กรกฎาคม 2547, 23:20 น.

 */



package com.printing.object.VisitSlip;

import java.util.*;

import com.printing.usecase.*;

/**

 *

 *  @author  ojika

 *  เป็น Object หนึ่งของ VisitSlip โดยการรับค่ามาแบบ set

 *  ข้อมูลออกคือ getData ส่งไปให้กับ Print

 */

public class PrintVisitSlip implements ObjectInf{

    

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

    public String pbp = "bp";

    public String phr = "hr";

    public String pr = "r";

    public String pt = "t";

    public String pbw = "bw";

    public String pht = "ht";

    public String pmainSymptom = "mainSymptom";

    public String pcurrent = "current";

    public String pdx = "dx";

    public String picd = "icd";

    public String plab = "lab";

    public String pxray = "xray";

    public String prx = "rx";

    public String pdoctor = "doctor";

    public String pdateVisit = "dateVisit";

    public String psex = "sex";

    public String pnutrition = "nutrition";

    public String pphysical = "physical";
    
    public String paddress = "address";
    

    public Map PrintVisitSlip;

    

    public PrintVisitSlip() 

    {

        PrintVisitSlip = new HashMap();

    }

    

    public void setAge(String name)

    {

        setMap(page,name);

    }

    

    public void setBp(String name)

    {

        setMap(pbp,name);

    }

    

    public void setBw(String name)

    {

        setMap(pbw,name);

    }

    

    public void setClinic(String name)

    {

        setMap(pclinic,name);

    }

    

    public void setCurrent(String name)

    {

        setMap(pcurrent,name);

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

    

    public void setHr(String name)

    {

        setMap(phr,name);

    }

    

    public void setHt(String name)

    {

        setMap(pht,name);

    }

    

    public void setIcd(String name)

    {

        setMap(picd,name);

    }

    

    public void setLab(String name)

    {

        setMap(plab,name);

    }

    

    public void setMainHospital(String name)

    {

        setMap(pmainHospital,name);

    }

    

    public void setMainSymptom(String name)

    {

        setMap(pmainSymptom,name);

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

    

    public void setR(String name)

    {

        setMap(pr,name);

    }    

    

    public void setRx(String name)

    {

        setMap(prx,name);

    }

    

    public void setT(String name)

    {

        setMap(pt,name);

    }

    

    public void setXray(String name)

    {

        setMap(pxray,name);

    }

    

    public void setSex(String name)

    {

        setMap(psex,name);

    }

    

    public void setNutrition(String name)

    {

        setMap(pnutrition,name);

    }

    

    public void setPhysical(String name)

    {

        setMap(pphysical,name);

    }

    

    public void setMap(String Param, String Data)

    {

        PrintVisitSlip.put(Param,Data);

    }

    public void setAddress(String name)
    {
        setMap(paddress,name);
    }

    public Map getData()

    {

        return PrintVisitSlip;

    }

    

}

