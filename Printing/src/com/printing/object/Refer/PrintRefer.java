/*
 * PrintRefer.java
 *
 * Created on 14 มิถุนายน 2547, 14:11 น.
 */

package com.printing.object.Refer;
import java.util.*;
import com.printing.usecase.*;

/**
 *
 * @author  ojika
 *  เป็น object ของการพิมพ์ใบ Refer
 */
public class PrintRefer implements ObjectInf{
    
    /** Creates a new instance of PrintRefer */
    public String preferNo = "referNo";
    public String preferDate = "referDate";
    public String preferFrom = "from";
    public String ptel = "tel";
    public String preferTo = "to";
    public String pname = "name";
    public String psex = "sex";
    public String page = "age";
    public String phn = "hn";
    public String pan = "an";
    public String pban = "ban";
    public String pmoo = "moo";
    public String proad = "road";
    public String ptambon = "tambon";
    public String pamphur = "amphur";
    public String pchangwat = "province";
    public String pnearHome = "nearHome";
    public String phistoryFamily = "history";
    public String phistoryCurrent = "present";
    public String phistoryLab = "lab";
    public String pdx = "dx";
    public String ptreatment = "treat";
    public String pcauseRefer = "causeRefer";
    public String potherDetail = "otherDetail";
    public String preferDoctor = "referDoctor";
    
    /**
     * ข้อมูลของ ชนิดการ Refer ยังไม่ได้ทำเพราะเป้น checkbox 4 ชนิด
     * ข้อมูลของโรคติดต่อ เรื่องการแจ้งความยังไม่ทำ เป็น Radio 2 ตัว
     **/
    
    public Map printrefer;
    
    public PrintRefer() {
        printrefer = new HashMap();
    }
    
    public void setReferNo(String name)
    {
        setMap(preferNo,name);
    }
    
    public void setReferDate(String name)
    {
        setMap(preferDate,name);
    }
    
    public void setReferFrom(String name)
    {
        setMap(preferFrom,name);
    }
    
    public void setTel(String name)
    {
        setMap(ptel,name);
    }
    
    public void setReferTo(String name)
    {
        setMap(preferTo,name);
    }
 
    public void setName(String name)
    {
        setMap(pname,name);
    }
    
    public void setSex(String name)
    {
        setMap(psex,name);
    }
    
    public void setAge(String name)
    {
        setMap(page,name);
    }
    
    public void setHn(String name)
    {
        setMap(phn,name);
    }
    
    public void setAn(String name)
    {
        setMap(pan,name);
    }
    
    public void setBan(String name)
    {
        setMap(pban,name);
    }
    
    public void setMoo(String name)
    {
        setMap(pmoo,name);
    }
    
    public void setRoad(String name)
    {
        setMap(proad,name);
    }
   
    public void setTambon(String name)
    {
        setMap(ptambon,name);
    }
    
    public void setAmphur(String name)
    {
        setMap(pamphur,name);
    }
    
    public void setChangwat(String name)
    {
        setMap(pchangwat,name);
    }
    
    public void setNearHome(String name)
    {
        setMap(pnearHome,name);
    }
    
    public void setHistoryFamily(String name)
    {
        setMap(phistoryFamily,name);
    }
    
    public void setHistoryCurrent(String name)
    {
        setMap(phistoryCurrent,name);
    }
    
    public void setHistoryLab(String name)
    {
        setMap(phistoryLab,name);
    }
    
    public void setDx(String name)
    {
        setMap(pdx,name);
    }
    
    public void setTreatment(String name)
    {
        setMap(ptreatment,name);
    }
    
    public void setCauseRefer(String name)
    {
        setMap(pcauseRefer,name);
    }
    
    public void setOtherDetail(String name)
    {
        setMap(potherDetail,name);
    }
    
    public void setReferDoctor(String name)
    {
        setMap(preferDoctor,name);
    }
    
    public void setMap(String Param, String Data)
    {
        printrefer.put(Param,Data);
    }
    
    public Map getData()
    {
        return printrefer;
    }
    
}
