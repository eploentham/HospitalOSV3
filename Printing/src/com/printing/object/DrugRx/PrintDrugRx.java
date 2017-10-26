/*

 * PrintDrugRx.java

 *

 * Created on 18 กรกฎาคม 2547, 19:33 น.

 */



package com.printing.object.DrugRx;

import java.util.*;

import com.printing.usecase.*;

/**

 *

 * @author  Ojika

 */

public class PrintDrugRx implements ObjectInf{

    

    /** Creates a new instance of PrintDrugRx */

    public String phospital = "hospital";

    public String pname = "name";

    public String page = "age";

    public String phn = "hn";

    public String pvn = "vn";

    public String pdateVisit = "dateVisit";

    public String ppatientType = "patientType";

    public String pdx = "dx";

    public String pdoctor = "doctor";

    public String pplan = "plan";

    public String pAddress = "address";
    
    public String pweight = "weight";
    public String pheight = "height";
    public Map PrintDrugRx;

    

    public PrintDrugRx() 

    {

        PrintDrugRx = new HashMap();

    }

    

    public void setHospital(String name)

    {

        setMap(phospital,name);

    }

    

    public void setName(String name)

    {

        setMap(pname,name);

    }

    

    public void setAge(String name)

    {

        setMap(page,name);

    }

    

    public void setHn(String name)

    {

        setMap(phn,name);

    }

    

    public void setVn(String name)

    {

        setMap(pvn,name);

    }

    

    public void setDateVisit(String name)

    {

        setMap(pdateVisit,name);

    }

    

    public void setPatientType(String name)

    {

        setMap(ppatientType,name);

    }

    

    public void setDx(String name)

    {

        setMap(pdx,name);

    }

    

    public void setDoctor(String name)

    {

        setMap(pdoctor,name);

    }

    

    public void setPlan(String name)

    {

        setMap(pplan,name);

    }

    

    public void setMap(String Param, String Data)

    {

        PrintDrugRx.put(Param,Data);

    }

    public void setAddress(String name)
    {
        setMap(pAddress,name);
    }
 
    public void setHeight(String name)
    {
        setMap(pheight,name);
    }

    public void setWeight(String name)
    {
        setMap(pweight,name);
    }

    public Map getData()

    {

        return PrintDrugRx;

    }

    

}

