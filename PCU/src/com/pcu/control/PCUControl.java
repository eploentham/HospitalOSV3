/*
 * HosControl.java
 *
 * Created on 2 มิถุนายน 2548, 13:58 น.
 */

package com.pcu.control;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.control.PatientControl;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.subject.HosSubject;
import java.util.*;
import com.hospital_os.object.*;
import com.hosv3.control.HosControl;
import com.pcu.object.*;
import com.hosv3.control.LookupControl;

/**
 *
 * @author amp
 */
public class PCUControl
{
    
    ////////////////////////////////////////////////////////////////////////////////
    /**ใช้ในการตรวจสอบ Version ของโปรแกรม PCU*/
    public static String VERSION0 = "";
    public static String VERSION3 = "0.03.1148";
    public static String VERSION4 = "0.04.0449";
    
    public HosSubject theHS;
    public ConnectionInf theConnectionInf;
    public AllComboBoxControl theAllComboBoxControl;
    public VillageControl theVillageControl;
    public HomeControl theHomeControl;
    public SetupPcuControl theSetupPcuControl;
    
    public HealthServiceControl theHealthServiceControl;
    public ExtraClinicServiceControl theExtraClinicServiceControl;
    public HealthSchoolServiceControl theHealthSchoolServiceControl;
    public SystemControl theSystemControl;
    public PatientControl thePatientControl;
    public LookupControl theLookupControl;
    public PrintControl thePrintControl;
    public com.hosv3.utility.connection.UpdateStatus theUS;
    
    /**tong*/
    public FamilyPlaningControl theFamilyPlaningControl;
    public AfterMCHMotherControl theAfterMCHMotherControl;
    //neung
    public UncontagiousControl theUncontagiousControl;
    public com.pcu.control.HosDB theHosDB;

    public HosControl theHcHospitalOS;
    /**
     * @deprecated henbe unused
     */
    public HospitalosControlInf theHosInf;
    public Vector balloon = new Vector();
    public com.hosv3.subject.HosSubject theHosSubject;
    public PCUObject thePO;
    
    /** Creates a new instance of HosControl */
    
    public PCUControl(com.hosv3.control.HosControl hhc,HospitalosControlInf hci
            ,PCUObject po,UpdateStatus us)
    {
        theConnectionInf = hhc.theConnectionInf;
        theHcHospitalOS = hhc;
        theHosInf = hci;
        theLookupControl = hhc.theLookupControl;
        theUS = us;
        theHosSubject = hhc.theHS;
        thePO = po;
        initControl();
    }
    
    public PCUControl(com.hosv3.control.HosControl hhc,PCUObject po,UpdateStatus us)
    {
        theConnectionInf = hhc.theConnectionInf;
        theHcHospitalOS = hhc;
        theLookupControl = hhc.theLookupControl;
        theUS = us;
        theHosSubject = hhc.theHS;
        thePO = po;
        initControl();
    }
    /**
     * for dental module using
     *
     **/
    public PCUControl(HosControl hhc)
    {
        theConnectionInf = hhc.theConnectionInf;
        theHcHospitalOS = hhc;
        theLookupControl = hhc.theLookupControl;
        thePatientControl =hhc.thePatientControl;
        theUS = hhc.theUS;
        initControl();
    }
    public void initControl()
    {
        theHosDB = new com.pcu.control.HosDB(theConnectionInf);
        /////////////////////////////////New////////////////////////////
        theAllComboBoxControl = new AllComboBoxControl(theConnectionInf,theHosDB,theHcHospitalOS,theUS);
        theVillageControl = new VillageControl(theConnectionInf,theHosDB,theHcHospitalOS,theUS);
        theHomeControl = new HomeControl(theConnectionInf,theHosDB,thePO,theHcHospitalOS,theUS);
        theSetupPcuControl = new SetupPcuControl(theConnectionInf,theHosDB,theHcHospitalOS,theUS);
        theHealthServiceControl = new HealthServiceControl(theConnectionInf,theHosDB,thePO,theHcHospitalOS,theUS);
        theExtraClinicServiceControl =new ExtraClinicServiceControl(theConnectionInf,theHosDB,thePO,theHcHospitalOS,theUS);
        theHealthSchoolServiceControl =new HealthSchoolServiceControl(theConnectionInf,theHosDB,theHcHospitalOS,theUS,thePO);
        theSystemControl = new SystemControl(theConnectionInf,theHosDB,theHcHospitalOS,theUS);
        thePrintControl = new PrintControl(theConnectionInf,theHosDB,theHcHospitalOS,theUS);
        theFamilyPlaningControl = new FamilyPlaningControl(theConnectionInf,theHosDB,theHcHospitalOS,thePO,theUS);
        theAfterMCHMotherControl = new AfterMCHMotherControl(theConnectionInf,theHosDB,thePO,theHcHospitalOS,theUS);
        theUncontagiousControl = new UncontagiousControl(theConnectionInf,theHosDB,theHcHospitalOS,theUS);
    }
    
    public void setAllComboBoxControl(AllComboBoxControl acbc)
    {
        theVillageControl.setAllComboBoxControl(acbc);
    }
    /**
     * new Pattern by Henbe 07/06/2549
     * สงสัยอะไรให้ถามและให้ทำตามรูปแบบนี้ทั้งหมดในฟังชันที่เกี่ยวกับ list ทั้งหมด
     */
    public Vector listFamilyPlaning()
    {
        return thePO.vFamilyPlaning;
    }
    public Vector listFamilyPlaning(Patient patient,Family family)
    {
        theConnectionInf.open();
        try
        {
            return this.theHosDB.theFamilyPlaningDB.selectShowTableByFamilyID(family.getObjectId());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return new Vector();
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public Vector listCounselByType(Family family,Patient patient,String type)
    {
        theConnectionInf.open();
        try
        {
            Vector vc = null;
            if(vc==null && patient!=null && patient.getObjectId()!=null)
                vc = theHosDB.theCounselDB.selectByPatinetIDAndCounselType(patient.getObjectId(),type);
            if(vc==null && family!=null && family.getObjectId()!=null)
                vc = theHosDB.theCounselDB.selectByFamilyIDAndCounselType(family.getObjectId(),type);
            return vc;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return new Vector();
        }
        finally
        {
            theConnectionInf.close();
        }
    }

    public boolean readFamily(Family family)
    {
        return readFamilySuit(family,true);
    }
    public boolean readFamilySuit(Family family,boolean read_suit)
    {
        theConnectionInf.open();
        try
        {            
            if(family==null)
            {
                thePO.vPPCare = new Vector();
                thePO.vPregnancy = new Vector();
                thePO.vEfficiency = new Vector();
                thePO.vCheckHealthYear = new Vector();
                thePO.vVisitHome = new Vector();
                thePO.vCounsel = new Vector();
                thePO.vDental = new Vector();
                thePO.thePP =null;
                thePO.vBornMch = new Vector();
                thePO.vAfterMchMother = new Vector();
                thePO.vEpi = new Vector();
                thePO.vEpiOutSite = new Vector();
                thePO.vEpiDetail = new Vector();
                thePO.vNutrition = new Vector();
                thePO.vFamilyPlaning = new Vector();
                thePO.vGrowHistory = new Vector();
                return false;
            }
            if(read_suit)
            {
                thePO.vPPCare = theHosDB.thePPCareDB.listPPCareByFamilyID(family.getObjectId());/////saveok
                //ไม่เข้าใจว่าตัวนี้เอาไว้ทำอะไรเพราะไม่พบการดึง
                thePO.vPregnancy = theHosDB.thePregnancyDB.selectByFamilyID(family.getObjectId());/////saveok
                thePO.vEfficiency = theHosDB.theEfficiencyDB.selectByFamilyID(family.getObjectId());/////saveok
                thePO.vCheckHealthYear = theHosDB.theCheckHealthYearDB.selectByFamilyID(family.getObjectId());/////saveok
                thePO.vVisitHome = theHosDB.theVisitHomeDB.selectByFamilyID(family.getObjectId());////saveok
                thePO.vCounsel = theHosDB.theCounselDB.selectByFamilyID(family.getObjectId(),"");
                thePO.vDental = theHosDB.theDentalDB.selectByFamilyID(family.getObjectId());////saveok
                thePO.thePP = theHosDB.thePPDB.readPPByFamilyID(family.getObjectId());
                thePO.vBornMch = theHosDB.theBornMchDB.selectShowTableByFamily(family.getObjectId());////saveok
                thePO.vAfterMchMother = theHosDB.theAfterMchMotherDB.selectShowTableByFamilyID(family.getObjectId());//saveok
                thePO.vEpi = theHosDB.theEpiDB.selectByFamilyID(family.getObjectId());  ////saveok
                thePO.vEpiOutSite = theHosDB.theEpiOutSiteDB.selectByFamilyID(family.getObjectId());  ////saveok
                thePO.vEpiDetail = theHosDB.theEpiDetailDB.selectByFamilyID(family.getObjectId());
                thePO.vNutrition = theHosDB.theNutritionDB.selectByFamilyID(family.getObjectId()); ////saveok
                thePO.vFamilyPlaning = theHosDB.theFamilyPlaningDB.selectShowTableByFamilyID(family.getObjectId());
                thePO.vGrowHistory = theHosDB.theGrowHistoryDB.selectByFamilyID(thePO.getFamily().getObjectId());
                thePO.vGrowPcu = theHosDB.theGrowPcuDB.selectByFamilyID(thePO.getFamily().getObjectId());
            }
            return true;
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
    public void saveDrugIn(DrugIn drugIn)
    {
        theConnectionInf.open();
        try
        {
            if(drugIn.getObjectId() == null)
            {
                theHosDB.theDrugInDB.insert(drugIn);
            }
            else
            {
                theHosDB.theDrugInDB.update(drugIn);
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
    
    public Office selectOfficeByPK(String pk)
    {
        Office theOffice = null;
        theConnectionInf.open();
        try
        {
            theOffice = theHosDB.theOfficeDB.selectByPK(pk);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return theOffice;
    }
    
    /**
     *@author pu
     *
     */
    public void readPatientByPatientID(String family_id)
    {
        thePatientControl.readFamilyByFamilyId(family_id);
    }
    
    public Vector listPatientByHn(String hn)
    {
        return thePatientControl.listPatientByHn(hn);
    }
    public Vector listPatientByPID(String pid)
    {
        return thePatientControl.listPatientByPID(pid);
    }
    
    public Vector listPatientByName(String pname, String fname, String lname)
    {
        return thePatientControl.listPatientByName(pname,fname,lname);
    }

}
