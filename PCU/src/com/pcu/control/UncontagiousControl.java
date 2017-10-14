/*
 * UncontagiousControl.java
 *
 * Created on 25 กุมภาพันธ์ 2549, 10:01 น.
 * Modified on 25 กันยายน 2551
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.control;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.control.DiagnosisControl;
import com.hosv3.control.HosControl;
import com.hosv3.control.LookupControl;
import java.util.*;

import com.pcu.utility.GutilPCU;
import com.hosv3.utility.connection.UpdateStatus;
import com.hospital_os.object.ICD10;
import com.hospital_os.object.Chronic;
import com.hosv3.object.UseCase;
import com.hosv3.utility.Constant;
import com.pcu.object.*;

/**
 *
 * @author kingland
 * @modifer pu
 */
public class UncontagiousControl
{
    ConnectionInf theConnectionInf;
    HosDB thePcuDB;
    HospitalosControlInf theHosInf;
    UpdateStatus theUS;
    HosControl theHC;
    private LookupControl theLookupControl;
    private DiagnosisControl theDiagnosisControl;
    /** Creates a new instance of UncontagiousControl */
    
    public UncontagiousControl(ConnectionInf con,HosDB hdb,HosControl hc,UpdateStatus us)
    {
        theConnectionInf = con;
        thePcuDB = hdb;
        theHC = hc;
        theUS = us;
        theLookupControl = hc.theLookupControl;
        theDiagnosisControl = hc.theDiagnosisControl;
    }
    /**@deprecated pu : จะยกเลิกการใช้ HospitalosControlInf*/
    public UncontagiousControl(ConnectionInf con,HosDB hdb,HospitalosControlInf inf,UpdateStatus us)
    {
        theConnectionInf = con;
        thePcuDB = hdb;
        theHosInf = inf;
        theUS = us;
    }
    /*deprecated pu: เซ็ต ใน constructor แล้ว*/
    public void setUpdateStatus(UpdateStatus us)
    {
        theUS = us;
    }
    /*deprecated pu: เซ็ต ใน constructor แล้ว*/
    public void setDepControl(LookupControl lc)
    {
        theLookupControl = lc;
    }
    
    /**
     * บันทึกรายการโรคไม่ติดต่อ
     * @param  object ของโรคไม่ติดต่อ
     * @return
     * @author kingland
     * @date 25-02-2549
     */
    public int saveUncontagious(Uncontagious uc)
    {
        int result = 0;
        if(uc == null)
        {
            theUS.setStatus(GutilPCU.getTextBundle("NotHaveUncontagious"), UpdateStatus.WARNING);
            return result;
        }
        theConnectionInf.open();
        try
        {
            if("".equals(uc.getObjectId()))
                result  = thePcuDB.theUncontagiousDB.insert(uc);
            else
                result = thePcuDB.theUncontagiousDB.update(uc);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return result;
    }
    /**
     * บันทึกรายการโรคไม่ติดต่อ
     * @param  Vector ของโรคไม่ติดต่อ
     * @return
     * @author kingland
     * @date 25-02-2549
     */
    public int saveUncontagious(Vector vUc)
    {
        int result = 0;
        if(vUc == null)
        {
            theUS.setStatus(GutilPCU.getTextBundle("NotHaveUncontagious"), UpdateStatus.WARNING);
            return result;
        }
        for(int i=0;i<vUc.size();i++)
        {
            Uncontagious maim = (Uncontagious)vUc.get(i);
            if(theLookupControl.isDateFuture(maim.survey_date))
            {
                theUS.setStatus("กรุณาระบุวันที่สำรวจเป็นวันที่ในอดีต", UpdateStatus.WARNING);
                return 0;
            }
        }
        try
        {
            for(int i=0;i<vUc.size();i++)
            {
                Uncontagious uc = (Uncontagious)vUc.get(i);
                ICD10 icd10 = theDiagnosisControl.listIcd10ById(uc.icd10.toUpperCase());
                if(icd10 == null)
                {
                    uc.icd10 = "";
                    theUS.setStatus(GutilPCU.getTextBundle("ลำดับโรคที่") +
                            " "+(i+1)+" " +
                            GutilPCU.getTextBundle("รหัสICD10ไม่ถูกต้อง"), UpdateStatus.WARNING);
                }
                theConnectionInf.open();
                if("".equals(uc.getObjectId()) || uc.getObjectId() == null)
                    result  = thePcuDB.theUncontagiousDB.insert(uc);
                else
                    result = thePcuDB.theUncontagiousDB.update(uc);
            }
            theUS.setStatus(GutilPCU.getTextBundle("SaveUncontagiousComplete"), UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            theUS.setStatus(GutilPCU.getTextBundle("SaveUncontagiousNotComplete"), UpdateStatus.ERROR);
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return result;
    }
    /**
     * ลบรายการโรคไม่ติดต่อ
     * @param  Vector ของรายการโรคติดต่อ,ตำแหน่งที่จะลบ
     * @return
     * @author kingland
     * @date 25-02-2549
     */
    public int delectUncontagious(Vector v,int[] select)
    {
        int result = 0;
        if(v == null)
        {
            theUS.setStatus(GutilPCU.getTextBundle("NotHaveUncontagious"), UpdateStatus.WARNING);
            return result;
        }
        theConnectionInf.open();
        try
        {
            for(int i = select.length-1;i>=0;i--)
            {
                Uncontagious uc = (Uncontagious)v.get(select[i]);
                if(!"".equals(uc.getObjectId()))
                {
                    result = thePcuDB.theUncontagiousDB.update(uc);
                }
                v.remove(select[i]);
            }
            theUS.setStatus(GutilPCU.getTextBundle("DeleteUncontagiousComplete"), UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            theUS.setStatus(GutilPCU.getTextBundle("DeleteUncontagiousNotComplete"), UpdateStatus.ERROR);
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return result;
    }
    /**
     * ค้นหารายการโรคไม่ติดต่อ
     * @param
     * @return Vector ของรายการโรคไม่ติดต่อ
     * @author kingland
     * @date 25-02-2549
     */
    public Vector listUncontagious(String familyID)
    {
        Vector v = null;
        theConnectionInf.open();
        try
        {
            if(!"".equals(familyID))
            {
                v = thePcuDB.theUncontagiousDB.selectByFamilyID(familyID);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return v;
    }
    /**
     * ค้นหารายการโรคไม่ติดต่อ
     * @param
     * @return Vector ของรายการโรคไม่ติดต่อ
     * @author kingland
     * @date 25-02-2549
     */
    public Uncontagious readUncontagiousByPk(String pk)
    {
        Uncontagious c = null;
        theConnectionInf.open();
        try
        {
            if(!"".equals(pk))
            {
                c = thePcuDB.theUncontagiousDB.selectByPK(pk);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return c;
    }
    /**
     * ค้นหารายการโรคเรื้อรัง
     * @param
     * @return Object of Chronic
     * @author kingland
     * @date 25-02-2549
     */
    public Chronic readChronicByPk(String pk)
    {
        Chronic c = null;
        theConnectionInf.open();
        try
        {
            c = thePcuDB.theChronicDB.selectByPK(pk);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return c;
    }
    /**
     * ค้นหารายการโรคเรื้อรัง
     * @param  familyid
     * @return Vector of Chronic
     * @author kingland
     * @date 28-02-2549
     */
    public Vector listChronicByFamily(String familyid)
    {
        if(familyid == null || "".equals(familyid))
        {
            return null;
        }
        Vector v = null;
        theConnectionInf.open();
        try
        {
            v = thePcuDB.theChronicDB.selectByFamilyID(familyid);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return v;
    }
    
    /**
     * บันทึกข้อมูลความพิการ
     * @param  Vector v
     * @return int result
     * @author jao
     * @date 04-03-2549
     */
    public int saveMaim(Vector v)
    {
        Constant.println(UseCase.UCID_saveMaim);
        String objectid =   null;
        int result = 0;
        if(v == null)
        {
            theUS.setStatus(GutilPCU.getTextBundle("NotHaveMaim"), UpdateStatus.WARNING);
            return result;
        }
        for(int i=0;i<v.size();i++)
        {
            Maim maim = (Maim)v.get(i);
            if(theLookupControl.isDateFuture(maim.survey_date))
            {
                theUS.setStatus("กรุณาระบุวันที่สำรวจเป็นวันที่ในอดีต", UpdateStatus.WARNING);
                return 0;
            }
        }
        theConnectionInf.open();
        try
        {
            for(int i=0;i<v.size();i++)
            {
                Maim maim = (Maim)v.elementAt(i);
                if(maim.getObjectId()==null)
                    result = thePcuDB.theMaimDB.insert(maim);
                else
                    result = thePcuDB.theMaimDB.update(maim);
            }
            theUS.setStatus(GutilPCU.getTextBundle("SaveMaimCompleate"), UpdateStatus.COMPLETE);
            theHC.theSystemControl.setStatus(UseCase.TH_saveMaim,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveMaim,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveMaim,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveMaim,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return result;
    }
    
    /**
     * ลบรายการความพิการ
     * @param  Vector ของรายการความ,ตำแหน่งที่จะลบ
     * @return
     * @author Ja0
     * @date 25-02-2549
     */
    public int delectMaim(Vector v,int[] select)
    {
        Constant.println(UseCase.UCID_deleteMaim);
        String objectid =   null;
        int result = 0;
        if(v == null)
        {
            theUS.setStatus(GutilPCU.getTextBundle("NotHaveMaim"), UpdateStatus.WARNING);
            return result;
        }
        theConnectionInf.open();
        try
        {
            for(int i = select.length-1;i>=0;i--)
            {
                Maim mm = (Maim)v.get(select[i]);
                if(!"".equals(mm.getObjectId()))
                {
                    result = thePcuDB.theMaimDB.update(mm);
                }
                v.remove(select[i]);
            }
            theUS.setStatus(GutilPCU.getTextBundle("DeleteMaimComplete"), UpdateStatus.COMPLETE);
            theHC.theSystemControl.setStatus(UseCase.TH_deleteMaim,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteMaim,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            theUS.setStatus(GutilPCU.getTextBundle("DeleteMaimNotComplete"), UpdateStatus.ERROR);
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteMaim,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteMaim,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return result;
    }
    
    /**
     * ค้นหารายการความพิการ
     * @param
     * @return Vector ของรายการความพิการ
     * @author Jao
     * @date 04-03-2549
     */
    public Vector listMaim(String familyID)
    {
        Vector v = null;
        theConnectionInf.open();
        try
        {
            if(!"".equals(familyID))
            {
                v = thePcuDB.theMaimDB.selectByFamilyID(familyID);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return v;
    }
    
    /**
     * ค้นหารายการความพิการ
     * @param
     * @return Vector ของรายการความพิการ
     * @author Jao
     * @date 06-03-2549
     */
    public Maim readMaimByPk(String pk)
    {
        Maim c = null;
        theConnectionInf.open();
        try
        {
            if(!"".equals(pk))
            {
                c = thePcuDB.theMaimDB.selectByPK(pk);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return c;
    }
    /**
     * แสดงรายการสำรวจตามช่วงอายุ
     * @param
     * @return Vector of AgeSurvey
     * @author kingland
     * @date 09-03-2549
     */
    public Vector listAgeSurveyByAge(String age)
    {
        Vector v = null;
        theConnectionInf.open();
        try
        {
            v = thePcuDB.theAgeSurveyDB.selectByAgeActive(age,"1");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return v;
    }
    /**
     * แสดงรายการสำรวจ ค้นหาโดยใช้ Primary key
     * @param
     * @return Vector of AgeSurvey
     * @author kingland
     * @date 11-03-2549
     */
    public AgeSurvey readAgeSurveyByPK(String pk)
    {
        AgeSurvey as = null;
        theConnectionInf.open();
        try
        {
            as = thePcuDB.theAgeSurveyDB.selectByPk(pk);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return as;
    }
    /**
     * แสดงรายการสำรวจ
     * @param
     * @return Vector of AgeSurvey
     * @author kingland
     * @date 11-03-2549
     */
    public CheckHealth readCheckHealthByPk(String pk)
    {
        CheckHealth ch = null;
        theConnectionInf.open();
        try
        {
            if(!"".equals(pk))
            {
                ch = thePcuDB.theCheckHealthDB.selectByPk(pk);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return ch;
    }
    /**
     * บันทึกรายการสำรวจตามช่วงอายุ
     * @param
     * @return
     * @author kingland
     * @date 11-03-2549
     */
    public int saveCheckHealth(Vector v)
    {
        Constant.println(UseCase.UCID_saveCheckHealth);
        String objectid =   null;
        int result = 0;
        if(v == null || v.size() == 0)
        {
            theUS.setStatus(GutilPCU.getTextBundle("NotHaveCheckHealth"), UpdateStatus.WARNING);
            return result;
        }
        for(int i=0;i<v.size();i++)
        {
            CheckHealth maim = (CheckHealth)v.get(i);
            if(maim.check_date.equals("")){
                theUS.setStatus("กรุณาระบุวันที่สำรวจ", UpdateStatus.WARNING);
                return 0;
            }
            if(theLookupControl.isDateFuture(maim.check_date)){
                theUS.setStatus("กรุณาระบุวันที่สำรวจเป็นวันที่ในอดีต", UpdateStatus.WARNING);
                return 0;
            }
        }
        theConnectionInf.open();
        try
        {
            for(int i=0;i<v.size();i++)
            {
                CheckHealth ch = (CheckHealth)v.elementAt(i);
                if(ch.getObjectId() == null)
                    result = thePcuDB.theCheckHealthDB.insert(ch);
                else
                    result = thePcuDB.theCheckHealthDB.update(ch);
            }
            theUS.setStatus(GutilPCU.getTextBundle("SaveCheckHealthCompleate"), UpdateStatus.COMPLETE);
            theHC.theSystemControl.setStatus(UseCase.TH_saveCheckHealth,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveCheckHealth,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveCheckHealth,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveCheckHealth,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return result;
    }
    /**
     * บันทึกรายการสำรวจตามช่วงอายุ
     * @param
     * @return
     * @author kingland
     * @date 11-03-2549
     */
    public void deleteChecHealth(Vector v)
    {
        Constant.println(UseCase.UCID_deleteCheckHealth);
        String objectid =   null;
        int result = 0;
        theConnectionInf.open();
        try
        {
            for(int i=0;i<v.size();i++)
            {
                CheckHealth ch = (CheckHealth)v.elementAt(i);
                if(ch.getObjectId()==null)
                    result = thePcuDB.theCheckHealthDB.insert(ch);
                else
                    result = thePcuDB.theCheckHealthDB.update(ch);
            }
            theUS.setStatus(GutilPCU.getTextBundle("DeleteCheckHealthComplete"), UpdateStatus.COMPLETE);
            theHC.theSystemControl.setStatus(UseCase.TH_deleteCheckHealth,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteCheckHealth,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteCheckHealth,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteCheckHealth,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    /**
     *
     * @param
     * @return Vector of CheckHealth
     * @author kingland
     * @date 11-03-2549
     */
    public Vector listCheckHealthByDate(String familyid,String startdate,String enddate)
    {
        Vector v = null;
        theConnectionInf.open();
        try
        {
            v = this.thePcuDB.theCheckHealthDB.selectByDate(familyid,startdate, enddate);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return v;
    }
}
