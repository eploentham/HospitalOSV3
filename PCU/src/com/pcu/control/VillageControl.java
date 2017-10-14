/*
 * VillageControl.java
 *
 * Created on 13 มิถุนายน 2548, 11:14 น.
 * Modified on 25 กันยายน 2551
 */

package com.pcu.control;

import com.hosv3.control.LookupControl;
import java.util.*;
import com.hospital_os.usecase.connection.*;
import com.hosv3.control.HosControl;
import com.hosv3.object.*;
import com.hosv3.utility.Constant;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.object.*;
import com.pcu.utility.*;
/**
 *
 * @author amp
 * @modifier pu
 */
public class VillageControl
{
    
    ConnectionInf theConnectionInf;
    HosDB thePcuDB;
    HosObject theHO;
    UpdateStatus theUS;
    
    private Vector vVillageObserv = new Vector();
    private Vector vVector;
    AllComboBoxControl theAllComboBoxControl;
    private LookupControl theLookupControl;
    
    HosControl theHC;
    
    /** Creates a new instance of VillageControl */
    public VillageControl()
    {
    }
    
    public VillageControl(ConnectionInf con,HosDB hdb,HosControl hc,UpdateStatus us)
    {
        theHC = hc;
        theHO = hc.theHO;
        theUS = us;
        theConnectionInf = con;
        thePcuDB = hdb;
        theLookupControl = theHC.theLookupControl;
    }
    
    public VillageControl(ConnectionInf con,HosDB hdb,HosObject ho,UpdateStatus us)
    {
        theHO = ho;
        theUS = us;
        theConnectionInf = con;
        thePcuDB = hdb;
    }
    /*deprecated pu: เซ็ต LookupControl ใน constructor แล้ว*/
    public void setDepControl(LookupControl lc)
    {
        theLookupControl = lc;
    }
    public void setAllComboBoxControl(AllComboBoxControl acbc)
    {
        theAllComboBoxControl = acbc;
    }
    public int deleteVillage(Village village,UpdateStatus theUS)
    {
        Constant.println(UseCase.UCID_deleteVillage);
        String objectid =   null;
        int result = 0;
        village.village_number = village.village_number.trim();
        village.village_moo = village.village_moo.trim();
        if(village.village_moo.equals("0"))
        {
            theUS.setStatus("ระบบไม่อนุญาติให้ยกเลิกหมู่บ้านนอกเขต หรือหมู่บ้าน 0 ได้",UpdateStatus.WARNING);
            return result;
        }
        theConnectionInf.open();
        try
        {
            Vector vhouse = thePcuDB.theHomeDB.selectByVillage(village.getObjectId());
            if(!vhouse.isEmpty())
            {
                theUS.setStatus("หมู่บ้านที่ต้องการลบมีบ้านอยู่ไม่สามารถลบได้",UpdateStatus.WARNING);
                return result;
            }
            if(!theUS.confirmBox(GutilPCU.getTextBundle("WarningBeforeDelete")
            +"\r\n"+GutilPCU.getTextBundle("WarningBeforeDelete1")+"\r\n"+GutilPCU.getTextBundle("WarningBeforeDelete2")
            +"\r\n"+GutilPCU.getTextBundle("WarningBeforeDelete3")+"\r\n"+GutilPCU.getTextBundle("WarningBeforeDelete4")
            +"\r\n"+GutilPCU.getTextBundle("WarningBeforeDelete5")
            ,UpdateStatus.WARNING))
            {
                return result;
            }
            village.village_staff_cancel = theHO.theEmployee.getObjectId();
            village.village_cancel_date_time = theHO.date_time;
            village.village_active = "0";
            result = thePcuDB.theVillageDB.update(village);
            updateSchoolTempleCompanyWaterToActiveZeroByVillageId(village.getObjectId());
            
            for(int i=0;i<vVillageObserv.size();i++){
                VillageObserv vo = (VillageObserv)vVillageObserv.get(i);
                vo.refreshVillage();
            }
            theUS.setStatus("การยกเลิกข้อมูลหมู่บ้านเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(village != null)
                objectid = village.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteVillage,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteVillage,objectid,null,UpdateStatus.COMPLETE);
            return result;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteVillage,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteVillage,objectid,ex,UpdateStatus.ERROR);
            return result;
        }
        finally
        {
            theConnectionInf.close();
        }
        
    }
    public int saveVillage(Village village,UpdateStatus theUS)
    {
        Constant.println(UseCase.UCID_saveVillage);
        String objectid =   null;
        int result = 0;
        village.village_number = village.village_number.trim();
        village.village_moo = village.village_moo.trim();
        if(village.village_number.equals("") || village.village_name.equals(""))
        {
            theUS.setStatus("กรุณากรอกข้อมูลรหัสหมู่บ้านและชื่อหมู่บ้าน",UpdateStatus.WARNING);
            return result;
        }
        theConnectionInf.open();
        try
        {
            if(village.getObjectId()==null)
            {   Village v = thePcuDB.theVillageDB.readVillageByVillageCode(village.village_number);
                if(v!=null)
                {   theUS.setStatus("รหัสหมู่บ้านซ้ำกับในฐานข้อมูลกรุณากรอกใหม่",UpdateStatus.WARNING);
                    return result;
                }
            }
            Vector v = thePcuDB.theVillageDB.selectEqMoo(village.village_moo,village.village_tambon);
            if(!v.isEmpty())
            {
                if(village.getObjectId()==null)
                {
                    theUS.setStatus("เลขหมู่ที่ซ้ำกรุณาตรวจสอบข้อมูลอีกครั้ง",UpdateStatus.WARNING);
                    return 0;
                }
                else if(v.size()>1)
                {
                    theUS.setStatus("เลขหมู่ที่ซ้ำกรุณาตรวจสอบข้อมูลอีกครั้ง",UpdateStatus.WARNING);
                    return 0;
                }
                else
                {//v.size==1
                    Village home = (Village)v.get(0);
                    if(!home.getObjectId().equals(village.getObjectId()))
                    {
                        theUS.setStatus("หมู่บ้านซ้ำกรุณาตรวจสอบข้อมูลอีกครั้ง",UpdateStatus.WARNING);
                        return 0;
                    }
                }
            }
            village.village_staff_modify = theHO.theEmployee.getObjectId();
            village.village_modify_date_time = theHO.date_time;
            village.village_name = village.village_name.trim();
            if(village.getObjectId() == null)
            {
                village.village_staff_record = theHO.theEmployee.getObjectId();
                village.village_record_date_time = theHO.date_time;
                village.village_active = "1";
                result = thePcuDB.theVillageDB.insert(village);
            }
            else
                result = thePcuDB.theVillageDB.update(village);
            
            this.theAllComboBoxControl.resetVillage();
            for(int i=0;i<vVillageObserv.size();i++){
                VillageObserv vo = (VillageObserv)vVillageObserv.get(i);
                vo.refreshVillage();
            }
            theUS.setStatus("การบันทึกข้อมูลหมู่บ้านเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(village != null)
                objectid = village.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_saveVillage,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveVillage,objectid,null,UpdateStatus.COMPLETE);
            return result;
        }
        catch(Exception ex)
        {    ex.printStackTrace();
             theHC.theSystemControl.setStatus(UseCase.TH_saveVillage,UpdateStatus.ERROR,ex);
             theHC.theSystemControl.saveLog(UseCase.UCID_saveVillage,objectid,ex,UpdateStatus.ERROR);
             return result;
        }
        finally
        {    theConnectionInf.close();
        }
    }
    
    public Vector listVillageByNameOrNumber(String search)
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theVillageDB.listVillageByNameOrNumber(search);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    public static boolean isObjectDuplicate(Persistent theSchool,Vector v)
    {
        if(v==null)
            return false;
        if(v.isEmpty())
            return false;
        if(v.size()>1)
            return true;
        if(theSchool.getObjectId()==null)
            return true;
        String id = ((Persistent)v.get(0)).getObjectId();
        if(theSchool.getObjectId().equals(id))
            return false;
        return true;
    }
    /**
     *@deprecated henbe unused
     */
    public boolean saveSchoolAndHistory(School theSchool,SchoolHistory schoolHistory)
    {
        return saveSchoolAndHistory(theSchool,schoolHistory,theUS);
    }
    /**
     *@deprecated henbe unused
     */
    public boolean saveSchoolAndHistory(School theSchool,SchoolHistory schoolHistory,UpdateStatus theUS)
    {
        return saveSchoolAndHistory(theSchool,schoolHistory,theUS,false);
    }
        
    public boolean saveSchoolAndHistory(School theSchool
            ,SchoolHistory schoolHistory,UpdateStatus theUS,boolean save_his)
    {
        Constant.println(UseCase.UCID_saveSchool);
        String objectid =   null;
        if(theSchool.school_name.equals(""))
        {
            theUS.setStatus("กรุณาระบุชื่อโรงเรียน",theUS.WARNING);
            return false;
        }
        if(theSchool.school_number.equals(""))
        {
            theUS.setStatus("กรุณาระบุหมายเลขโรงเรียน",theUS.WARNING);
            return false;
        }
        if(theSchool.school_number.equals("") || theSchool.school_name.equals(""))
        {
            theUS.setStatus("กรุณาระบุเลขลำดับของโรงเรียน",UpdateStatus.WARNING);
            return false;
        }
        if(schoolHistory!=null && theLookupControl.isDateFuture(schoolHistory.school_history_date_of_pass))
        {
            theUS.setStatus("กรุณาระบุวันที่เข้าร่วมโครงการเป็นวันที่ในอดีต",UpdateStatus.WARNING);
            return false;
        }
        if(theLookupControl.isDateFuture(theSchool.school_close_date_time))
        {
            theUS.setStatus("กรุณาระบุวันที่เลิกดำเนินกิจการเป็นวันที่ในอดีต",UpdateStatus.WARNING);
            return false;
        }
        theConnectionInf.open();
        try
        {
            if(theSchool.school_active.equals("1")){
                Vector v = thePcuDB.theSchoolDB.selectByNoVillage(theSchool.school_number,theSchool.village_id);
                if(isObjectDuplicate(theSchool,v)){
                    theUS.setStatus("พบข้อมูลโรงเรียนมีเลขที่ซ้ำกับที่มีในฐานข้อมูล",UpdateStatus.WARNING);
                    return false;
                }
            }
            theSchool.school_staff_modify = theHO.theEmployee.getObjectId();
            theSchool.school_modify_date_time = theHO.date_time;
            if(theSchool.getObjectId() == null)
            {
                theSchool.school_staff_record = theHO.theEmployee.getObjectId();
                theSchool.school_record_date_time = theHO.date_time;
                thePcuDB.theSchoolDB.insert(theSchool);
            }
            else
                thePcuDB.theSchoolDB.update(theSchool);
            /////////////////////////////////////////////////////////////////
            schoolHistory.school_history_staff_modify = theHO.theEmployee.getObjectId();
            schoolHistory.school_history_modify_date_time = theHO.date_time;
            if(save_his || schoolHistory.getObjectId()==null)
            {
                schoolHistory.school_id = theSchool.getObjectId();
                schoolHistory.school_history_staff_record = theHO.theEmployee.getObjectId();
                schoolHistory.school_history_record_date_time = theHO.date_time;
                thePcuDB.theSchoolHistoryDB.insert(schoolHistory);
            }
            else
                thePcuDB.theSchoolHistoryDB.update(schoolHistory);
            
            theUS.setStatus("การบันทึกข้อมูลโรงเรียนเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(theSchool != null)
                objectid = theSchool.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_saveSchool,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveSchool,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveSchool,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveSchool,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public boolean deleteSchool(School school,UpdateStatus us)
    {
        Constant.println(UseCase.UCID_deleteSchool);
        String objectid =   null;
        if (!theUS.confirmBox(GutilPCU.getTextBundle("VerifyBeforeDelete"),UpdateStatus.WARNING))
            return false;

        theConnectionInf.open();
        try{
            school.school_staff_cancel = theHO.theEmployee.getObjectId();
            school.school_cancel_date_time = theHO.date_time;
            school.school_active = "0";
            thePcuDB.theSchoolDB.update(school);
            theUS.setStatus("การลบข้อมูลโรงเรียนเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(school != null)
                objectid = school.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteSchool,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteSchool,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteSchool,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteSchool,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally {
            theConnectionInf.close();
        }
    }
    
    /**
     *@deprecated henbe unused
     */
    
    public void updateSchoolAndHistory(School school,SchoolHistory schoolHistory)
    {
        theConnectionInf.open();
        try
        {
            thePcuDB.theSchoolDB.update(school);
            thePcuDB.theSchoolHistoryDB.update(schoolHistory);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public Vector listSchoolByNameOrNumber(String search,String villageId)
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theSchoolDB.listSchoolByNameOrNumber(search,villageId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public Vector listSchoolHistoryBySchoolId(String search)
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theSchoolHistoryDB.listSchoolHistoryBySchoolId(search);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public Vector listSchoolHistoryBySchoolIdDesc(String search)
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theSchoolHistoryDB.listSchoolHistoryBySchoolIdDesc(search);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public Vector selectSchoolByPK(String pk)
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theSchoolDB.selectByPK(pk);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public SchoolHistory readSchoolHistoryBySchoolId(String search)
    {
        theConnectionInf.open();
        try
        {
            return thePcuDB.theSchoolHistoryDB.readSchoolHistoryBySchoolId(search);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    /**
     *@deprecated henbe unused
     */
    public boolean saveAboutTemple(Temple temple,TempleHistory templeHistory,Vector vTempleHistoryDetail)
    {
        return saveAboutTemple(temple,templeHistory,vTempleHistoryDetail,theUS);
    }
    public boolean saveAboutTemple(Temple temple,TempleHistory templeHistory,Vector vTempleHistoryDetail
            ,UpdateStatus theUS)
    {
        Constant.println(UseCase.UCID_saveTemple);
        String objectid =   null;
        if(temple.temple_number.equals("") || temple.temple_name.equals(""))
        {
            theUS.setStatus("กรุณาระบุเลขลำดับของวัด",UpdateStatus.WARNING);
            return false;
        }
        theConnectionInf.open();
        try{
            Vector v = thePcuDB.theTempleDB.selectByNumberVillage(temple.temple_number,temple.village_id);
            if(isObjectDuplicate(temple,v)){
                theUS.setStatus("พบข้อมูลวัดมีเลขที่ซ้ำกับที่มีในฐานข้อมูล",UpdateStatus.WARNING);
                return false;
            }
            temple.temple_staff_modify = theHO.theEmployee.getObjectId();
            temple.temple_modify_date_time = theHO.date_time;
            if(temple.getObjectId() == null)
            {
                temple.temple_staff_record = theHO.theEmployee.getObjectId();
                temple.temple_record_date_time = theHO.date_time;
                thePcuDB.theTempleDB.insert(temple);
            }
            else
            {
                Temple tmp = thePcuDB.theTempleDB.selectByPK(temple.getObjectId());
                if(!tmp.temple_number.equals(tmp.temple_number))
                {
                    theUS.setStatus(Constant.getTextBundle("หมายเลขวัด")+" "+ temple.temple_number +" " +
                            Constant.getTextBundle("ไม่สามารถเปลี่ยนแปลงได้"),UpdateStatus.WARNING);
                    return false;
                }
                thePcuDB.theTempleDB.update(temple);
            }
            if(templeHistory.getObjectId()==null)
            {
                templeHistory.temple_staff_modify = theHO.theEmployee.getObjectId();
                templeHistory.temple_modify_date_time = theHO.date_time;
                templeHistory.temple_staff_record = theHO.theEmployee.getObjectId();
                templeHistory.temple_record_date_time = theHO.date_time;
                templeHistory.temple_id = temple.getObjectId();
                thePcuDB.theTempleHistoryDB.insert(templeHistory);
                for(int i=0;vTempleHistoryDetail!=null && i<vTempleHistoryDetail.size();i++)
                {
                    TempleHistoryDetail theTempleHistoryDetail= (TempleHistoryDetail)vTempleHistoryDetail.get(i);
                    theTempleHistoryDetail.temple_history_id = templeHistory.getObjectId();
                    theTempleHistoryDetail.generateOID(i);
                    thePcuDB.theTempleHistoryDetailDB.insert(theTempleHistoryDetail);
                }
            }
            else{
                thePcuDB.theTempleHistoryDB.update(templeHistory);
                thePcuDB.theTempleHistoryDetailDB.deleteByTempleHistoryId(templeHistory.getObjectId());
                for(int i=0;vTempleHistoryDetail!=null && i<vTempleHistoryDetail.size();i++)
                {
                    TempleHistoryDetail theTempleHistoryDetail= (TempleHistoryDetail)vTempleHistoryDetail.get(i);
                    theTempleHistoryDetail.temple_history_id = templeHistory.getObjectId();
                    theTempleHistoryDetail.generateOID(i);
                    thePcuDB.theTempleHistoryDetailDB.insert(theTempleHistoryDetail);
                }
            }
            
            theUS.setStatus("การบันทึกข้อมูลวัดเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(temple != null)
                objectid = temple.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_saveTemple,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveTemple,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveTemple,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveTemple,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public boolean deleteTemple(Temple temple,UpdateStatus theUS)
    {
        Constant.println(UseCase.UCID_deleteTemple);
        String objectid =   null;
        if (!theUS.confirmBox(GutilPCU.getTextBundle("VerifyBeforeDelete"),UpdateStatus.WARNING))
        {
            return false;
        }
        theConnectionInf.open();
        try{
            temple.temple_cancel_date_time = theHO.date_time;
            temple.temple_staff_cancel = theHO.theEmployee.getObjectId();
            temple.temple_active = "0";
            thePcuDB.theTempleDB.update(temple);
            theUS.setStatus("การลบข้อมูลวัดเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(temple != null)
                objectid = temple.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteTemple,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteTemple,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteTemple,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteTemple,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    /**
     *@deprecated henbe unused
     */
    public void updateAboutTemple(Temple temple,TempleHistory templeHistory,Vector vthd)
    {
        theConnectionInf.open();
        try{
            thePcuDB.theTempleDB.update(temple);
            templeHistory.temple_staff_modify = theHO.theEmployee.getObjectId();
            templeHistory.temple_modify_date_time = theHO.date_time;
            thePcuDB.theTempleHistoryDB.update(templeHistory);
            if(vthd!=null && !vthd.isEmpty())
            {
                thePcuDB.theTempleHistoryDetailDB.deleteByTempleHistoryId(templeHistory.getObjectId());
                TempleHistoryDetail thd = new TempleHistoryDetail();
                for(int i=0;i<vthd.size();i++)
                {
                    TempleHistoryDetail theTempleHistoryDetail= (TempleHistoryDetail)vthd.get(i);
                    
                    if(theTempleHistoryDetail.getObjectId()==null)
                    {
                        theTempleHistoryDetail.temple_history_id = templeHistory.getObjectId();
                        theTempleHistoryDetail.generateOID(0);
                        thePcuDB.theTempleHistoryDetailDB.insert(theTempleHistoryDetail);
                    }
                    else
                    {
                        thd.setObjectId(theTempleHistoryDetail.getObjectId());
                        thd.temple_history_id = theTempleHistoryDetail.temple_history_id;
                        thd.temple_personel = theTempleHistoryDetail.temple_personel;
                        thd.temple_amount_personel = theTempleHistoryDetail.temple_amount_personel;
                        thePcuDB.theTempleHistoryDetailDB.insert(thd);
                    }
                }
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
    }
    
    public Vector listTempleByNameOrNumber(String search,String villageId)
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theTempleDB.listTempleByNameOrNumber(search,villageId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public boolean saveCompanyAndHistory(Company company,CompanyHistory companyHistory)
    {
        Constant.println(UseCase.UCID_saveCompany);
        String objectid =   null;
        if(companyHistory!=null && theLookupControl.isDateFuture(companyHistory.company_co_date_time))
        {
            theUS.setStatus("กรุณาระบุวันที่เข้าร่วมโครงการเป็นวันที่ในอดีต",UpdateStatus.WARNING);
            return false;
        }
        if(theLookupControl.isDateFuture(company.company_close_date_time))
        {
            theUS.setStatus("กรุณาระบุวันที่เลิกดำเนินกิจการเป็นวันที่ในอดีต",UpdateStatus.WARNING);
            return false;
        }
        theConnectionInf.open();
        try {
            company.company_staff_modify = theHO.theEmployee.getObjectId();
            company.company_modify_date_time = theHO.date_time;
            /***Company***/
            if(company.getObjectId() == null){
                company.company_staff_record = theHO.theEmployee.getObjectId();
                company.company_record_date_time = theHO.date_time;
                thePcuDB.theCompanyDB.insert(company);
            }
            else
                thePcuDB.theCompanyDB.update(company);
            
            /***CompanyHistory***/
            companyHistory.company_id = company.getObjectId();
            companyHistory.company_history_staff_record = theHO.theEmployee.getObjectId();
            companyHistory.company_history_record_date_time = theHO.date_time;
            companyHistory.company_history_staff_modify = theHO.theEmployee.getObjectId();
            companyHistory.company_history_modify_date_time = theHO.date_time;
            thePcuDB.theCompanyHistoryDB.insert(companyHistory);
            if(company != null)
                objectid = company.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_saveCompany,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveCompany,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveCompany,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveCompany,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public boolean deleteCompany(Company company)
    {
        Constant.println(UseCase.UCID_deleteCompany);
        String objectid =   null;
        if (!theUS.confirmBox(GutilPCU.getTextBundle("VerifyBeforeDelete"),theUS.WARNING))
        {
            return false;
        }
        company.company_staff_cancel = theHO.theEmployee.getObjectId();
        company.company_cancel_date_time = theHO.date_time;
        company.company_active = "0";
        theConnectionInf.open();
        try{
            thePcuDB.theCompanyDB.update(company);
            theUS.setStatus("การลบข้อมูลสถานประกอบการเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(company != null)
                objectid = company.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteCompany,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteCompany,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteCompany,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteCompany,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public Vector listCompanyByNameOrNumber(String search,String villageId)
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theCompanyDB.listCompanyByNameOrNumber(search,villageId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public Vector listCompanyHistoryByCompanyId(String search)
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theCompanyHistoryDB.listCompanyHistoryByCompanyId(search);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public boolean saveWaterAndHistory(Water water,WaterHistory waterHistory)
    {
        return saveWaterAndHistory(water,waterHistory,false);
    }
    public boolean saveWaterAndHistory(Water water,WaterHistory waterHistory,boolean his)
    {
        Constant.println(UseCase.UCID_saveWater);
        String objectid =   null;
        if(water.water_number.equals("")) {
            theUS.setStatus(GutilPCU.getTextBundle("FillNumber"),UpdateStatus.WARNING);
            return false;
        }
        theConnectionInf.open();
        try{
            Vector vc = thePcuDB.theWaterDB.selectByNumberVid(water.water_number, water.village_id);
            if(this.isObjectDuplicate(water,vc)) {
                theUS.setStatus(GutilPCU.getTextBundle("SameNumberInVillage"),UpdateStatus.WARNING);
                return false;
            }
            water.water_staff_modify = theHO.theEmployee.getObjectId();
            water.water_modify_date_time = theHO.date_time;
            /***Water***/
            if(water.getObjectId() == null)
            {
                water.water_staff_record = theHO.theEmployee.getObjectId();
                water.water_record_date_time = theHO.date_time;
                thePcuDB.theWaterDB.insert(water);
            }
            else{
                thePcuDB.theWaterDB.update(water);
            }
            
            waterHistory.water_history_staff_record = theHO.theEmployee.getObjectId();
            waterHistory.water_history_record_date_time = theHO.date_time;
            if(his)
                waterHistory.setObjectId(null);
            
            if(waterHistory.getObjectId()==null){
                waterHistory.water_id = water.getObjectId();
                thePcuDB.theWaterHistoryDB.insert(waterHistory);
            }
            else{
                thePcuDB.theWaterHistoryDB.update(waterHistory);
            }
            
            theUS.setStatus("การบันทึกแหล่งน้ำเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(water != null)
                objectid = water.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_saveWater,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveWater,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveWater,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveWater,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public boolean deleteWater(Water water)
    {
        Constant.println(UseCase.UCID_deleteWater);
        String objectid =   null;
        if(water==null){
            theUS.setStatus("กรุณาเลือกแหล่งน้ำ",UpdateStatus.WARNING);
            return false;
        }
        if(water.getObjectId()==null){
            theUS.setStatus("กรุณาเลือกแหล่งน้ำ",UpdateStatus.WARNING);
            return false;
        }
        if (!theUS.confirmBox(GutilPCU.getTextBundle("VerifyBeforeDelete"),UpdateStatus.WARNING))
        {
            return false;
        }
        theConnectionInf.open();
        try{
            water.water_staff_cancel = theHO.theEmployee.getObjectId();
            water.water_cancel_date_time = theHO.date_time;
            water.water_active = "0";
            thePcuDB.theWaterDB.update(water);
            theUS.setStatus("การยกเลิกแหล่งน้ำเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(water != null)
                objectid = water.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteWater,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteWater,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteWater,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteWater,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public Vector listWaterByNumber(String search,String villageId)
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theWaterDB.listWaterByNumber(search,villageId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public Vector listWaterHistoryByWaterId(String search)
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theWaterHistoryDB.listWaterHistoryByWaterId(search);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public void updateSchoolTempleCompanyWaterToActiveZeroByVillageId(String villageId)
    {
        theConnectionInf.open();
        try
        {
            thePcuDB.theSchoolDB.updateSchoolToActiveZeroByVillageId(villageId);
            thePcuDB.theTempleDB.updateTempleToActiveZeroByVillageId(villageId);
            thePcuDB.theCompanyDB.updateCompanyToActiveZeroByVillageId(villageId);
            thePcuDB.theWaterDB.updateWaterToActiveZeroByVillageId(villageId);
            thePcuDB.theMarketDB.updateMarketToActiveZeroByVillageId(villageId);
            thePcuDB.theResourceDB.updateResourceToActiveZeroByVillageId(villageId);
            thePcuDB.theAGRDB.updateAGRToActiveZeroByVillageId(villageId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public Home countOfHomeSumOfFamily(String villageId)
    {
        theConnectionInf.open();
        try
        {
            return thePcuDB.theHomeDB.countOfHomeSumOfFamily(villageId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public boolean saveMarketAndHistory(Market market,MarketHistory marketHistory)
    {
        Constant.println(UseCase.UCID_saveMarket);
        String objectid =   null;
        if(theLookupControl.isDateFuture(marketHistory.market_history_market_co_date_time))
        {
            theUS.setStatus("กรุณาระบุวันที่เข้าร่วมโครงการเป็นวันที่ในอดีต",UpdateStatus.WARNING);
            return false;
        }
        if(theLookupControl.isDateFuture(marketHistory.market_history_date_of_pass))
        {
            theUS.setStatus("กรุณาระบุวันที่ผ่านเกณฑ์เป็นวันที่ในอดีต",UpdateStatus.WARNING);
            return false;
        }
        if(theLookupControl.isDateFuture(market.market_close_date_time))
        {
            theUS.setStatus("กรุณาระบุวันที่เลิกดำเนินกิจการเป็นวันที่ในอดีต",UpdateStatus.WARNING);
            return false;
        }
        theConnectionInf.open();
        try{
            
            Vector v = thePcuDB.theMarketDB.selectByNumberVid(market.market_number,market.village_id);
            if(this.isObjectDuplicate(market,v))
            {
                theUS.setStatus("พบข้อมูลตลาดมีเลขที่ซ้ำกับที่มีในฐานข้อมูล",UpdateStatus.WARNING);
                return false;
            }
            market.market_staff_modify = theHO.theEmployee.getObjectId();
            market.market_modify_date_time = theHO.date_time;
            
            if(market.getObjectId() == null)
            {
                market.market_staff_record = theHO.theEmployee.getObjectId();
                market.market_record_date_time = theHO.date_time;
                thePcuDB.theMarketDB.insert(market);
            }
            else
            {
                thePcuDB.theMarketDB.update(market);
            }
            marketHistory.market_history_staff_record = theHO.theEmployee.getObjectId();
            marketHistory.market_history_record_date_time = theHO.date_time;
            marketHistory.market_history_staff_modify = theHO.theEmployee.getObjectId();
            marketHistory.market_history_modify_date_time = theHO.date_time;
            marketHistory.market_id = market.getObjectId();
            thePcuDB.theMarketHistoryDB.insert(marketHistory);
            theUS.setStatus("การบันทึกข้อมูลตลาดเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(market != null)
                objectid = market.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_saveMarket,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveMarket,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveMarket,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveMarket,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public boolean deleteMarket(Market market)
    {
        Constant.println(UseCase.UCID_deleteMarket);
        String objectid =   null;
        if (!theUS.confirmBox(GutilPCU.getTextBundle("VerifyBeforeDelete"),UpdateStatus.WARNING))
        {
            return false;
        }
        market.market_staff_cancel = theHO.theEmployee.getObjectId();
        market.market_cancel_date_time = theHO.date_time;
        market.market_active = "0";
        theConnectionInf.open();
        try{
            thePcuDB.theMarketDB.update(market);
            theUS.setStatus("การยกเลิกข้อมูลตลาดเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(market != null)
                objectid = market.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteMarket,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteMarket,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteMarket,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteMarket,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public boolean updateMarketAndHistory(Market market,MarketHistory marketHistory)
    {
        if(market.market_number.equals("") || market.market_name.equals(""))
        {
            theUS.setStatus(GutilPCU.getTextBundle("FillNumberAndName"),UpdateStatus.WARNING);
            return false;
        }
        theConnectionInf.open();
        try{            
            market.market_staff_modify = theHO.theEmployee.getObjectId();
            market.market_modify_date_time = theHO.date_time;
            thePcuDB.theMarketDB.update(market);
            marketHistory.market_history_staff_modify = theHO.theEmployee.getObjectId();
            marketHistory.market_history_modify_date_time = theHO.date_time;
            thePcuDB.theMarketHistoryDB.update(marketHistory);
            theUS.setStatus("การบันทึกข้อมูลตลาดเสร็จสิ้น",UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            theUS.setStatus("การบันทึกข้อมูลตลาดผิดพลาด",UpdateStatus.ERROR);
            ex.printStackTrace();
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public Vector listMarketByNameOrNumber(String search,String villageId)
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theMarketDB.listMarketByNameOrNumber(search,villageId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public MarketHistory readMarketHistoryByMarketId(String search)
    {
        theConnectionInf.open();
        try{
            return thePcuDB.theMarketHistoryDB.readMarketHistoryByMarketId(search);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public Vector listMarketHistoryByMarketId(String search)
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theMarketHistoryDB.listMarketHistoryByMarketId(search);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public CompanyHistory readCompanyHistoryByCompanyId(String search)
    {
        theConnectionInf.open();
        try{
            return thePcuDB.theCompanyHistoryDB.readCompanyHistoryByCompanyId(search);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public void updateCompanyAndHistory(Company company,CompanyHistory companyHistory)
    {
        Constant.println(UseCase.UCID_saveCompany);
        String objectid =   null;
        if(company.company_number.equals("") || company.company_name.equals(""))
        {
            theUS.setStatus(GutilPCU.getTextBundle("FillNumberAndName"),UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{
            company.company_staff_modify = theHO.theEmployee.getObjectId();
            company.company_modify_date_time = theHO.date_time;
            thePcuDB.theCompanyDB.update(company);
            
            companyHistory.company_history_staff_modify = theHO.theEmployee.getObjectId();
            companyHistory.company_history_modify_date_time = theHO.date_time;
            thePcuDB.theCompanyHistoryDB.update(companyHistory);
            theUS.setStatus("การบันทึกข้อมูลผู้ประกอบการเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(company != null)
                objectid = company.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_saveCompany,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveCompany,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveCompany,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveCompany,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public Vector listTempleHistoryDetailByTempleId(String search)
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            String templeHistoryId = thePcuDB.theTempleHistoryDB.readTempleHistoryByTempleId(search);
            vVector = thePcuDB.theTempleHistoryDetailDB.listTempleHistoryDetailByTempleHistoryId(templeHistoryId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public Vector listTempleHistoryDetailByTempleHistoryId(String search)
    {
        vVector = new Vector();
        theConnectionInf.open();
        try{
            vVector = thePcuDB.theTempleHistoryDetailDB.listTempleHistoryDetailByTempleHistoryId(search);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public Vector listTempleHistoryByTempleId(String search)
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theTempleHistoryDB.listTempleHistoryByTempleId(search);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    /**
     *@deprecated henbe unused
     */
    public boolean saveResourceAndHistory(Resource resource,ResourceHistory resourceHistory)
    {
        return saveResourceAndHistory(resource,resourceHistory,theUS);
    }
    public boolean saveResourceAndHistory(Resource resource,ResourceHistory resourceHistory
            ,UpdateStatus theUS)
    {
        Constant.println(UseCase.UCID_saveResource);
        String objectid =   null;
        if(resource.resource_number.equals(""))
        {
            theUS.setStatus("กรุณาระบุลำดับของทรัพยากร",UpdateStatus.WARNING);
            return false;
        }
        if(resourceHistory!=null && theLookupControl.isDateFuture(resourceHistory.resource_history_date_of_pass))
        {
            theUS.setStatus("กรุณาระบุวันที่ผ่านเกณฑ์เป็นวันที่ในอดีต",UpdateStatus.WARNING);
            return false;
        }
        theConnectionInf.open();
        try{
            if(resource.resource_active.equals("1")){
                Vector v = thePcuDB.theResourceDB.selectByNumberVillage(resource.resource_number,resource.village_id);
                if(isObjectDuplicate(resource,v)){
                    theUS.setStatus("พบข้อมูลทรัพยากรมีเลขที่ซ้ำกับที่มีในฐานข้อมูล",UpdateStatus.WARNING);
                    return false;
                }
            }
            resource.resource_staff_modify = theHO.theEmployee.getObjectId();
            resource.resource_modify_date_time = theHO.date_time;
            if(resource.getObjectId() == null)
            {
                resource.resource_staff_record = theHO.theEmployee.getObjectId();
                resource.resource_record_date_time = theHO.date_time;
                thePcuDB.theResourceDB.insert(resource);
            }
            else
                thePcuDB.theResourceDB.update(resource);
            
            resourceHistory.resource_history_staff_record = theHO.theEmployee.getObjectId();
            resourceHistory.resource_history_record_date_time = theHO.date_time;
            resourceHistory.resource_id = resource.getObjectId();
            thePcuDB.theResourceHistoryDB.insert(resourceHistory);
            theUS.setStatus("การบันทึกข้อมูลทรัพยากรเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(resource != null)
                objectid = resource.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_saveResource,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveResource,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveResource,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveResource,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public boolean updateResourceAndHistory(Resource resource,ResourceHistory resourceHistory)
    {
        Constant.println(UseCase.UCID_saveResource);
        String objectid =   null;
        theConnectionInf.open();
        try{
            resource.resource_staff_modify = theHO.theEmployee.getObjectId();
            resource.resource_modify_date_time = theHO.date_time;
            thePcuDB.theResourceDB.update(resource);

            resourceHistory.resource_history_staff_modify = theHO.theEmployee.getObjectId(); 
            resourceHistory.resource_history_modify_date_time = theHO.date_time;
            thePcuDB.theResourceHistoryDB.update(resourceHistory);
            theUS.setStatus("การบันทึกข้อมูลทรัพยากรเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(resource != null)
                objectid = resource.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_saveResource,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveResource,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveResource,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveResource,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public boolean deleteResource(Resource resource,UpdateStatus theUS)
    {
        Constant.println(UseCase.UCID_deleteResource);
        String objectid =   null;
        if (!theUS.confirmBox(GutilPCU.getTextBundle("VerifyBeforeDelete"),UpdateStatus.WARNING))
        {
            return false;
        }
        theConnectionInf.open();
        try{
            resource.resource_staff_cancel = theHO.theEmployee.getObjectId();
            resource.resource_cancel_date_time = theHO.date_time;
            resource.resource_active = "0";
            thePcuDB.theResourceDB.update(resource);
            theUS.setStatus("การยกเลิกข้อมูลทรัพยากรเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(resource != null)
                objectid = resource.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteResource,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteResource,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteResource,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteResource,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public Vector listResourceByNumber(String search,String villageId)
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theResourceDB.listResourceByNumber(search,villageId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public ResourceHistory readResourceHistoryByResourceId(String search)
    {
        theConnectionInf.open();
        try{
            return thePcuDB.theResourceHistoryDB.readResourceHistoryByResourceId(search);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public Vector listResourceHistoryByResourceId(String search)
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theResourceHistoryDB.listResourceHistoryByResourceId(search);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    /**
     *@deprecated henbe unused
     */
    public boolean saveAGRAndHistory(AGR aGR,AGRHistory aGRHistory)
    {
        return saveAGRAndHistory(aGR,aGRHistory,theUS);
    }
    public boolean saveAGRAndHistory(AGR aGR,AGRHistory aGRHistory,UpdateStatus theUS)
    {
        Constant.println(UseCase.UCID_saveAGR);
        String objectid =   null;
        if(aGR.agr_number.equals(""))
        {
            theUS.setStatus("กรุณาระบุลำดับของกลุ่มกิจกรรม",UpdateStatus.WARNING);
            return false;
        }
        if(aGRHistory!=null && theLookupControl.isDateFuture(aGRHistory.date_of_pass))
        {
            theUS.setStatus("กรุณาระบุวันที่ผ่านเกณฑ์เป็นวันที่ในอดีต",UpdateStatus.WARNING);
            return false;
        }
        if(theLookupControl.isDateFuture(aGR.agr_close_date_time))
        {
            theUS.setStatus("กรุณาระบุวันที่ยกเลิกในอดีตเท่านั้น",UpdateStatus.WARNING);
            return false;
        }
        theConnectionInf.open();
        try
        {
            if(aGR.agr_active.equals("1")){
                Vector v = thePcuDB.theAGRDB.selectByNumberVillage(aGR.agr_number,aGR.village_id);
                if(isObjectDuplicate(aGR,v)){
                    theUS.setStatus("พบข้อมูลกลุ่มกิจกรรมมีเลขที่ซ้ำกับที่มีในฐานข้อมูล",UpdateStatus.WARNING);
                    return false;
                }
            }
            aGR.agr_staff_modify = theHO.theEmployee.getObjectId();
            aGR.agr_modify_date_time = theHO.date_time;
            /***AGR***/
            if(aGR.getObjectId() == null)
            {
                aGR.agr_staff_record = theHO.theEmployee.getObjectId();
                aGR.agr_record_date_time = theHO.date_time;
                thePcuDB.theAGRDB.insert(aGR);
            }
            else
            {
                thePcuDB.theAGRDB.update(aGR);
            }
            /***AGRHistory***/
            aGRHistory.agr_history_staff_modify = theHO.theEmployee.getObjectId();
            aGRHistory.agr_history_modify_date_time = theHO.date_time;
            aGRHistory.agr_history_staff_record = theHO.theEmployee.getObjectId();
            aGRHistory.agr_history_record_date_time = theHO.date_time;
            aGRHistory.agr_id = aGR.getObjectId();
            thePcuDB.theAGRHistoryDB.insert(aGRHistory);
            theUS.setStatus("การบันทึกข้อมูลกลุ่มกิจกรรมเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(aGR != null)
                objectid = aGR.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_saveAGR,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveAGR,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveAGR,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveAGR,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public boolean updateAGRAndHistory(AGR aGR,AGRHistory aGRHistory)
    {
        Constant.println(UseCase.UCID_saveAGR);
        String objectid =   null;
        theConnectionInf.open();
        try{
            aGR.agr_staff_record = theHO.theEmployee.getObjectId();
            aGR.agr_record_date_time =  theHO.date_time;
            thePcuDB.theAGRDB.update(aGR);
            aGRHistory.agr_history_staff_modify = theHO.theEmployee.getObjectId();
            aGRHistory.agr_history_modify_date_time = theHO.date_time;
            thePcuDB.theAGRHistoryDB.update(aGRHistory);
            theUS.setStatus("การบันทึกข้อมูลกลุ่มกิจกรรมเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(aGR != null)
                objectid = aGR.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_saveAGR,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveAGR,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveAGR,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveAGR,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public boolean deleteAGR(AGR aGR,UpdateStatus theUS)
    {
        Constant.println(UseCase.UCID_deleteAGR);
        String objectid =   null;
        if(!theUS.confirmBox(GutilPCU.getTextBundle("VerifyBeforeDelete"),UpdateStatus.WARNING)){
            return false;
        }
        theConnectionInf.open();
        try{
            aGR.agr_staff_cancel = theHO.theEmployee.getObjectId();
            aGR.agr_cancel_date_time = theHO.date_time;
            aGR.agr_active = "0";
            thePcuDB.theAGRDB.update(aGR);
            theUS.setStatus("การยกเลิกข้อมูลกลุ่มกิจกรรมเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(aGR != null)
                objectid = aGR.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteAGR,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteAGR,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteAGR,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteAGR,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public Vector listAGRByNameOrNumber(String search,String villageId)
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theAGRDB.listAGRByNameOrNumber(search,villageId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public AGRHistory readAGRHistoryByAGRId(String search)
    {
        theConnectionInf.open();
        try{
            return thePcuDB.theAGRHistoryDB.readAGRHistoryByAGRId(search);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public Vector listAGRHistoryByAGRId(String search)
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theAGRHistoryDB.listAGRHistoryByAGRId(search);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    
    public boolean checkVillageCode(String search)
    {
        theConnectionInf.open();
        boolean temp = false;
        if(search.equals(""))return temp;
        try{
            Village theVillage = thePcuDB.theVillageDB.readVillageByVillageCode(search);
            if(theVillage == null)
                temp = false;
            else
                temp = true;
        }
        catch(Exception ex)
        {    ex.printStackTrace();
        }
        finally
        {   theConnectionInf.close();
        }
        return temp;
    }
    
    public boolean checkVillageMoo(Village village)
    {
        theConnectionInf.open();
        boolean temp = false;
        try
        {
            Village v = thePcuDB.theVillageDB.selectByMoo(village.village_moo, village.village_tambon);
            return (v!=null);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public CommunityResource readResourceName(String search)
    {
        theConnectionInf.open();
        try{
            return thePcuDB.theCommunityResourceDB.readCommunityResourceByResourceName(search);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public boolean checkWater(String waterNumber,String villageId)
    {
        theConnectionInf.open();
        boolean temp = false;
        try{
            Water theWater = thePcuDB.theWaterDB.readWaterByWaterNumberAngVillageId(waterNumber, villageId);
            if(theWater==null)
            {
                temp = false;
            }
            else
            {
                temp = true;
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
        return temp;
    }
    public void addHomeObserv(VillageObserv vo) {
        vVillageObserv.add(vo);
    }
}
