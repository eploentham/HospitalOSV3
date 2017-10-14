/*
 * AllComboBoxControl.java
 *
 * Created on 13 มิถุนายน 2548, 10:45 น.
 * Modified on 25 กันยายน 2551
 */

package com.pcu.control;

import com.hosv3.control.HosControl;
import com.hosv3.control.LookupControl;
import com.pcu.gui.panel.village.PanelActivityGroup;
import java.sql.ResultSet;
import java.util.*;
import com.hospital_os.usecase.connection.*;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.object.*;
import com.hospital_os.utility.Gutil;
import com.hospital_os.object.*;
import com.hospital_os.usecase.connection.CommonInf;
import com.hospital_os.utility.ComboFix;
/**
 *
 * @author amp
 * @modifier pu
 */
public class AllComboBoxControl
{
    
    public ConnectionInf theConnectionInf;
    UpdateStatus theUS;
    HosDB thePcuDB;
    private Vector vVector;
    private Vector vFamilyPlaningMethod;
    private Vector vPregnancyPregnantLevel;
    private Vector vAnswer1;
    private Vector vAnswer2;
    private Vector vAnswer3;
    private Vector vAnswer8;
    private Vector vAnswer9;
    private Vector vAnswer14;
    private Vector vVillageLocation;
    private Vector vWaterDrink;
    private Vector vWaterUse;
    private Vector vGarbageMethod;
    private Vector vHomeChar;
    private Vector vComChar;
    private Vector vDungMethod;
    private Vector vHomeZone;
    private Vector vPetSex;
    private Vector vSchoolMaxClass;
    private Vector vCompanyType;
    private Vector vWaterType;
    private Vector vNutritionType;
    private Vector vGumLevel;
    private Vector vFamilyPlaning;
    private Vector vPostpartumBirthPlace;
    private Vector vPostpartumStatusResult;
    private Vector vPostpartumGivebirthResult;
    private Vector vPregnancyBirthDoctorType;
    private Vector vPregnancyBirthHighRisk;
    private Vector vAncSection;
    private Vector vAddressCGW;
    private Vector vCommunityResorceOwner;
    private Vector vSchoolServiceType;
    private Vector vCommunityStandardType;
    private Vector vCommunityStandard;
    private Vector vGrow;
    private Vector vCheckResult;
    private Vector vTempleType;
    private Vector vTemplePersonel;
    private Vector vUterusLevel;
    private Vector vBabyStatus;
    private Vector vConduct;
    private Vector vAbNomal;
    private Vector vMarketType;
    private Vector vMarketTypeLock;
    private Vector vAGRCode;
    private Vector vAGRType;
    private Vector vQueryFamilyPlaningMethod;
    private Vector vQueryAllFamilyPlaningMethod;
    private Vector vQueryFamilyPlaningSupplyGroup;
    private Vector vQueryAllFamilyPlaningCause;
    private Vector vPostpartumEpisiotomyType;
    private Vector vResultGiveBirth;
    private Vector vResultStatus;
    private Vector vBDoctor;
    private Vector vPPResultDetail;
    private Vector vICD10Pregnant;
    private Vector vPPBreathingTime;
    private Vector vPPAspiration;
    private Vector vPPEyeDrug;
    private Vector vBirthMethod;
    private Vector vBirthMethodForPP;
    private Vector vPPState;
    private Vector vPPDeadType;
    private Vector vPPDeadCause;
    private Vector vTypeArea;
    private Vector vLabor;
    private Vector vDischarge;
    private Vector vMaimType;
    Vector vServiceType=null;
    //neung
    private Vector vSurvey;
    
    private Vector vTemp;
    private Hashtable tablePrefix = new Hashtable();
    private Hashtable tableCheckResult = new Hashtable();
    private Hashtable tableSex = new Hashtable();
    private Hashtable tableAnswer = new Hashtable();
    private Hashtable tableHealthAnswer = new Hashtable();
    private Hashtable tableAppearAnswer = new Hashtable();
    private Hashtable tableResultCheckAnswer = new Hashtable();
    private Hashtable tableNutritionType = new Hashtable();
    private Hashtable tableGumLevel = new Hashtable();
    private Hashtable tableSchoolClass = new Hashtable();
    private Hashtable tableSchoolServiceType = new Hashtable();
    private Hashtable tableServiceType = new Hashtable();
    private Hashtable tableCheckHealthYearActivity = new Hashtable();
    HospitalosControlInf theHosInf;
    HosControl theHC;
    LookupControl theLookupControl;
    
    /** Creates a new instance of AllComboBoxControl */
    public AllComboBoxControl()
    {
    }
    
    public AllComboBoxControl(ConnectionInf con,HosDB hdb,HosControl hc,UpdateStatus us)
    {
        theConnectionInf = con;
        theHC = hc;
        thePcuDB = hdb;
        theUS = us;
        theLookupControl = theHC.theLookupControl;
        setVectorComboBox();
    }
    
    /**@deprecated pu : จะยกเลิกการใช้ HospitalosControlInf*/
    public AllComboBoxControl(ConnectionInf con,HosDB hdb,UpdateStatus us,HospitalosControlInf hosinf)
    {
        theConnectionInf = con;
        thePcuDB = hdb;
        theUS = us;
        theHosInf = hosinf;
        theLookupControl = theHosInf.getLookupControl();
        setVectorComboBox();
    }
    public String readVNbyVid(String vi_id){
        try{
            theConnectionInf.open();
            ResultSet rs = theConnectionInf.eQuery("select visit_vn from t_visit where t_visit_id = '"+vi_id+"'");
            String vn = "";
            while(rs.next()){
                vn = rs.getString(1);
            }
            return vn;
        }
        catch(Exception e){
            e.printStackTrace();
            return "";
        }
        finally{
            theConnectionInf.close();
        }
    }
    private void setVectorComboBox()
    {
        theConnectionInf.open();
        try
        {
            vFamilyPlaningMethod = thePcuDB.theFamilyPlaningMethodDB.selectAll();
            vPregnancyPregnantLevel = thePcuDB.thePregnancyPregnantLevelDB.selectAll();
            /*
            vAnswer1 = thePcuDB.thePcuAnswerDB.selectAll(1);
            vAnswer2 = thePcuDB.thePcuAnswerDB.selectAll(2);
            vAnswer3 = thePcuDB.thePcuAnswerDB.selectAll(3);
            vAnswer8 = thePcuDB.thePcuAnswerDB.selectAll(8);
            vAnswer9 = thePcuDB.thePcuAnswerDB.selectAll(9);
            vAnswer14 = thePcuDB.thePcuAnswerDB.selectAll(14);
             */
            vVillageLocation = thePcuDB.theVillageLocationDB.selectAll();
            vWaterDrink = thePcuDB.theWaterUseDB.selectWaterDrink();
            vWaterUse = thePcuDB.theWaterUseDB.selectWaterUse();
            vGarbageMethod = thePcuDB.theGarbageMethodDB.selectGarbageMethod();
            vHomeChar = thePcuDB.theHomeCharacterDB.selectHomeChar();
            vComChar = thePcuDB.theCommunityCharacterDB.selectComChar();
            vDungMethod = thePcuDB.theDungMethodDB.selectDungMethod();
            vHomeZone = thePcuDB.theHomeZoneDB.selectHomeZone();
            vPetSex = thePcuDB.thePetSexDB.selectPetSex();
            vSchoolMaxClass = thePcuDB.theSchoolClassDB.selectAllMaxClass();
            vCompanyType = thePcuDB.theCompanyTypeDB.selectAll();
            vWaterType = thePcuDB.theWaterTypeDB.selectAll();
            vNutritionType = thePcuDB.theNutritionTypeDB.selectAll();
            vGumLevel = thePcuDB.theGumLevelDB.selectAll();
            vFamilyPlaning = thePcuDB.theFamilyPlaningDB.selectAll();
            vPostpartumBirthPlace = thePcuDB.thePostpartumBirthPlaceDB.selectAll();
            vPostpartumStatusResult = thePcuDB.thePostpartumStatusResultDB.selectAll();
            vPostpartumGivebirthResult = thePcuDB.thePostpartumGivebirthResultDB.selectAll();
            vPregnancyBirthDoctorType = vPostpartumGivebirthResult;
            //vPregnancyBirthDoctorType = thePcuDB.thePregnancyBirthDoctorTypeDB.selectAll();
            vPregnancyBirthHighRisk = thePcuDB.thePregnancyBirthHighRiskDB.selectAll();
            vAncSection = thePcuDB.theAncSectionDB.selectAll();
            vAddressCGW = thePcuDB.theAddressDB.selectChangwat();
            vCommunityResorceOwner = thePcuDB.theCommunityResorceOwnerDB.selectAll();
            vSchoolServiceType = thePcuDB.theSchoolServiceTypeDB.selectAll();
            vCommunityStandardType = thePcuDB.theCommunityStandardTypeDB.selectAll();
            vCommunityStandard = thePcuDB.theCommunityStandardDB.selectAll();
            vGrow = thePcuDB.theGrowDB.selectAll();
            vCheckResult = thePcuDB.theCheckResultDB.selectAll();
            vTempleType = thePcuDB.theTempleTypeDB.selectAll();
            vTemplePersonel = thePcuDB.theTemplePersonelDB.selectAll();
            vUterusLevel = thePcuDB.theUterusLevelDB.selectAll();
            vBabyStatus = thePcuDB.theBabyStatusDB.selectAll();
            vConduct = thePcuDB.theConductDB.selectAll();
            vAbNomal = thePcuDB.theAbNomalDB.selectAll();
            vMarketType = thePcuDB.theMarketTypeDB.selectAll();
            vMarketTypeLock = thePcuDB.theMarketTypeLockDB.selectAll();
            vAGRCode = thePcuDB.theAGRCodeDB.selectAll();
            vAGRType = thePcuDB.theAGRTypeDB.selectAll();
            vQueryFamilyPlaningMethod = thePcuDB.theFamilyPlaningMethodDB.selectAllWithOut("0", true);
            vQueryAllFamilyPlaningMethod = thePcuDB.theFamilyPlaningMethodDB.selectAll();
            vQueryFamilyPlaningSupplyGroup = thePcuDB.theFamilyPlaningSupplyGroupDB.selectAll("1",true);
            vQueryAllFamilyPlaningCause = thePcuDB.theFamilyPlaningCauseDB.selectAll();
            vPostpartumEpisiotomyType = thePcuDB.thePostpartumEpisiotomyTypeDB.selectAll();
            vResultGiveBirth = thePcuDB.theResultGiveBirthDB.selectAll();
            vResultStatus = thePcuDB.theResultStatusDB.selectAll();
            vBDoctor = thePcuDB.theBDoctorDB.selectAll();
            vICD10Pregnant = thePcuDB.theICD10DB.selectByPregnant();
            vPPBreathingTime = thePcuDB.thePPBreathingTimeDB.selectAll();
            vPPAspiration = thePcuDB.thePPAspirationDB.selectAll();
            vPPEyeDrug = thePcuDB.thePPEyeDrugDB.selectAll();
            vBirthMethod = thePcuDB.theBirthMethodDB.selectAll();
            vBirthMethodForPP = thePcuDB.theBirthMethodDB.selectAllForPP();
            vPPState = thePcuDB.thePPStateDB.selectAll();
            vPPDeadType = thePcuDB.thePPDeadTypeDB.selectAll();
            vPPDeadCause = thePcuDB.thePPDeadCauseDB.selectAll();
            vTypeArea = thePcuDB.theTypeAreaDB.selectAll();
            vLabor = thePcuDB.theLaborDB.selectAll();
            vMaimType = thePcuDB.theMaimTypeDB.selectMaimType();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public Vector listOccupation()
    {
        return theLookupControl.listOccupation();
    }
    
    public Vector listRelation()
    {
        return theLookupControl.listRelation();
    }
    
    
    public Vector listLabor()
    {
        if((vLabor != null)) return vLabor;
        theConnectionInf.open();
        try
        {
            vLabor = thePcuDB.theLaborDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vLabor;
    }
    
    public Vector listVillageLocation()
    {
        if((vVillageLocation != null))
            return vVillageLocation;
        theConnectionInf.open();
        try
        {
            vVillageLocation = thePcuDB.theVillageLocationDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vVillageLocation;
    }
    
    public Vector listTypeArea()
    {
        if((vTypeArea != null))
            return vTypeArea;
        theConnectionInf.open();
        try
        {
            vTypeArea = thePcuDB.theTypeAreaDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vTypeArea;
    }
    
    public Vector listRace()
    {
        return theLookupControl.listRace();
    }
    
    public Vector listNation()
    {
        return theLookupControl.listNation();
    }
    
    public Vector listMarriage()
    {
        return theLookupControl.listMarriage();
        
    }
    
    public Vector listBlood()
    {
        return theLookupControl.listBlood();
    }
    
    
    public Vector listWaterDrink()
    {
        if((vWaterDrink != null))
            return vWaterDrink;
        theConnectionInf.open();
        try
        {
            vWaterDrink = thePcuDB.theWaterUseDB.selectWaterDrink();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vWaterDrink;
    }
    
    public Vector listWaterUse()
    {
        if((vWaterUse != null))
            return vWaterUse;
        theConnectionInf.open();
        try
        {
            vWaterUse = thePcuDB.theWaterUseDB.selectWaterUse();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vWaterUse;
    }
    
    public Vector listGarbageMethod()
    {
        if((vGarbageMethod != null))
            return vGarbageMethod;
        theConnectionInf.open();
        try
        {
            vGarbageMethod = thePcuDB.theGarbageMethodDB.selectGarbageMethod();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vGarbageMethod;
    }
    
    public Vector listHomeChar()
    {
        if((vHomeChar != null))
            return vHomeChar;
        theConnectionInf.open();
        try
        {
            vHomeChar = thePcuDB.theHomeCharacterDB.selectHomeChar();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vHomeChar;
    }
    
    public Vector listComChar()
    {
        if((vComChar != null))
            return vComChar;
        theConnectionInf.open();
        try
        {
            vComChar = thePcuDB.theCommunityCharacterDB.selectComChar();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vComChar;
    }
    
    public Vector listDungMethod()
    {
        if((vDungMethod != null))
            return vDungMethod;
        theConnectionInf.open();
        try
        {
            vDungMethod = thePcuDB.theDungMethodDB.selectDungMethod();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vDungMethod;
    }
    
    public Vector listHomeZone()
    {
        if((vHomeZone != null))
            return vHomeZone;
        theConnectionInf.open();
        try
        {
            vHomeZone = thePcuDB.theHomeZoneDB.selectHomeZone();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vHomeZone;
    }
    
    public Vector listPetSex()
    {
        if((vPetSex != null))
            return vPetSex;
        theConnectionInf.open();
        try
        {
            vPetSex = thePcuDB.thePetSexDB.selectPetSex();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vPetSex;
    }
    
    public Vector listSex()
    {
        return theLookupControl.listGender();
    }
    
    
    public Vector listSchoolMaxClass()
    {
        if((vSchoolMaxClass != null))
            return vSchoolMaxClass;
        theConnectionInf.open();
        try
        {
            vSchoolMaxClass = thePcuDB.theSchoolClassDB.selectAllMaxClass();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vSchoolMaxClass;
    }
    
    public Vector listCompanyType()
    {
        if((vCompanyType != null))
            return vCompanyType;
        theConnectionInf.open();
        try
        {
            vCompanyType = thePcuDB.theCompanyTypeDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vCompanyType;
    }
    
    public Vector listCheckHealthYearActivity()
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theCheckHealthYearActivityDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public Vector listWaterType()
    {
        if((vWaterType != null))
            return vWaterType;
        theConnectionInf.open();
        try
        {
            vWaterType = thePcuDB.theWaterTypeDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vWaterType;
    }
    
    public Vector listPetType()
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.thePetTypeDB.selectPetType();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public Vector vVillage = null;
    public void resetVillage()
    {
        vVillage = null;
        vVillage = listVillage();
    }
    public Vector listVillage()
    {
        //Constant.println("public Vector listVillage() ");
        if(vVillage!=null && !vVillage.isEmpty())
            return vVillage;
        
        //Constant.println("public Vector listVillage() is null");
        theConnectionInf.open();
        try
        {
            vVillage = thePcuDB.theVillageDB.selectAll();
            return vVillage;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return vVillage;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public Vector listVillage2()
    {
        //Constant.println("public Vector listVillage() is null");
        theConnectionInf.open();
        try
        {
            vVillage = thePcuDB.theVillageDB.selectAll();
            return vVillage;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return vVillage;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    /*public Vector listServicePlace()
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theVillageDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vVector;
    }*/
    
    public Vector listNutritionType()
    {
        if((vNutritionType != null))
            return vNutritionType;
        theConnectionInf.open();
        try
        {
            vNutritionType = thePcuDB.theNutritionTypeDB.selectAllCombofix();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vNutritionType;
    }
    
    public Vector listGumLevel()
    {
        if((vGumLevel != null))
            return vGumLevel;
        theConnectionInf.open();
        try
        {
            vGumLevel = thePcuDB.theGumLevelDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vGumLevel;
    }
    
    public Vector listFamilyPlaningMethod()
    {
        if((vFamilyPlaningMethod != null))
            return vFamilyPlaningMethod;
        theConnectionInf.open();
        try
        {
            vFamilyPlaningMethod = thePcuDB.theFamilyPlaningMethodDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vFamilyPlaningMethod;
    }
    
    public Vector listFamilyPlaning()
    {
        if((vFamilyPlaning != null))
            return vFamilyPlaning;
        theConnectionInf.open();
        try
        {
            vFamilyPlaning = thePcuDB.theFamilyPlaningDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vFamilyPlaning;
    }
    
    public Vector listPostpartumBirthPlace()
    {
        if((vPostpartumBirthPlace!= null))
            return vPostpartumBirthPlace;
        theConnectionInf.open();
        try
        {
            vPostpartumBirthPlace = thePcuDB.thePostpartumBirthPlaceDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vPostpartumBirthPlace;
    }
    
    public Vector listPregnancyPregnantLevel()
    {
        if((vPregnancyPregnantLevel != null))
            return vPregnancyPregnantLevel;
        theConnectionInf.open();
        try
        {
            vPregnancyPregnantLevel = thePcuDB.thePregnancyPregnantLevelDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vPregnancyPregnantLevel;
    }
    
    public Vector listPostpartumStatusResult()
    {
        if((vPostpartumStatusResult != null))
            return vPostpartumStatusResult;
        theConnectionInf.open();
        try
        {
            vPostpartumStatusResult = thePcuDB.thePostpartumStatusResultDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vPostpartumStatusResult;
    }
    
    public Vector listPostpartumGivebirthResult()
    {
        if((vPostpartumGivebirthResult != null))
            return vPostpartumGivebirthResult;
        theConnectionInf.open();
        try
        {
            vPostpartumGivebirthResult = thePcuDB.thePostpartumGivebirthResultDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vPostpartumGivebirthResult;
    }
    
    public Vector listPregnancyBirthDoctorType()
    {
        if((vPregnancyBirthDoctorType != null))
            return vPregnancyBirthDoctorType;
        theConnectionInf.open();
        try
        {
            vPregnancyBirthDoctorType = thePcuDB.thePregnancyBirthDoctorTypeDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vPregnancyBirthDoctorType;
    }
    
    public Vector listPregnancyBirthHighRisk()
    {
        if((vPregnancyBirthHighRisk != null))
            return vPregnancyBirthHighRisk;
        theConnectionInf.open();
        try
        {
            vPregnancyBirthHighRisk = thePcuDB.thePregnancyBirthHighRiskDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vPregnancyBirthHighRisk;
    }
    
    public Vector listAncSection()
    {
        if((vAncSection != null))
            return vAncSection;
        theConnectionInf.open();
        try
        {
            vAncSection = thePcuDB.theAncSectionDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vAncSection;
    }
    
    /*
    public Vector listPcuAnswer(int choose) {
        switch(choose) {
            case 1 :    if((vAnswer1 != null))
                return vAnswer1;
            break;
            case 2 :    if((vAnswer2 != null))
                return vAnswer2;
            break;
            case 3 :    if((vAnswer3 != null))
                return vAnswer3;
            break;
            case 8 :    if((vAnswer8 != null))
                return vAnswer8;
            break;
            case 9 :    if((vAnswer9 != null))
                return vAnswer9;
            break;
            case 14 :    if((vAnswer14 != null))
                return vAnswer14;
            break;
            default :   vVector = new Vector();
            break;
        }
        theConnectionInf.open();
        try {
            switch(choose) {
                case 1 :    vAnswer1 = thePcuDB.thePcuAnswerDB.selectAll(choose);
                Constant.println("* * * In case select "+choose);
                break;
                case 2 :    vAnswer2 = thePcuDB.thePcuAnswerDB.selectAll(choose);
                Constant.println("* * * In case select "+choose);
                break;
                case 3 :    vAnswer3 = thePcuDB.thePcuAnswerDB.selectAll(choose);
                Constant.println("* * * In case select "+choose);
                break;
                case 8 :    vAnswer8 = thePcuDB.thePcuAnswerDB.selectAll(choose);
                Constant.println("* * * In case select "+choose);
                break;
                case 9 :    vAnswer9 = thePcuDB.thePcuAnswerDB.selectAll(choose);
                Constant.println("* * * In case select "+choose);
                break;
                case 14 :    vAnswer14 = thePcuDB.thePcuAnswerDB.selectAll(choose);
                Constant.println("* * * In case select "+choose);
                break;
                default :   vVector = thePcuDB.thePcuAnswerDB.selectAll(choose);
                Constant.println("* * * In case select "+choose);
                break;
            }
     
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        switch(choose) {
            case 1 :    return vAnswer1;
            case 2 :    return vAnswer2;
            case 3 :    return vAnswer3;
            case 8 :    return vAnswer8;
            case 9 :    return vAnswer9;
            case 14 :    return vAnswer14;
            default :   return vVector;
        }
    }
     */
    
    public Vector listServiceType()
    {
//        if(vServiceType!=null && !vServiceType.isEmpty())
//            return vServiceType;
        
        theConnectionInf.open();
        try
        {
            vServiceType = thePcuDB.theServiceTypeDB.selectAll();
            return vServiceType;
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
    
    public Vector listAddressCGW()
    {
        if((vAddressCGW != null))
            return vAddressCGW;
        
        theConnectionInf.open();
        try
        {
            vAddressCGW = thePcuDB.theAddressDB.selectChangwat();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vAddressCGW;
    }
    
    public Vector listAddressTmp(String ch,String amp)
    {
        // if((vAddressCGW != null))
        //   return vAddressCGW;
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theAddressDB.selectTambon(amp);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public Vector listAddressAmp(String changwat)
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theAddressDB.selectAmphur(changwat);
            if(vVector==null) return new Vector();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public Vector listSchoolClass()
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theSchoolClassDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public Vector listSchoolName()
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theSchoolDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public Vector listCommunityResorceOwner()
    {
        if((vCommunityResorceOwner != null))
            return vCommunityResorceOwner;
        theConnectionInf.open();
        try
        {
            vCommunityResorceOwner = thePcuDB.theCommunityResorceOwnerDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vCommunityResorceOwner;
    }
    
    public Vector listSchoolServiceType()
    {
        if((vSchoolServiceType != null))
            return vSchoolServiceType;
        theConnectionInf.open();
        try
        {
            vSchoolServiceType = thePcuDB.theSchoolServiceTypeDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vSchoolServiceType;
    }
    
    public Vector listCommunityStandardType()
    {
        if((vCommunityStandardType != null))
            return vCommunityStandardType;
        theConnectionInf.open();
        try
        {
            vCommunityStandardType = thePcuDB.theCommunityStandardTypeDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vCommunityStandardType;
    }
    
    public Vector listCommunityStandard()
    {
        if((vCommunityStandard != null))
            return vCommunityStandard;
        theConnectionInf.open();
        try
        {
            vCommunityStandard = thePcuDB.theCommunityStandardDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vCommunityStandard;
    }
    
    /**
     * ใช้แสดงข้อมูลใน ComboBox ของ วิธิการคุมกำเนิด
     * @return เป็น Vector ของ Object ที่ใช้กับ ComboFix
     * @Author ผดุงรัฐ
     */
    public Vector queryFamilyPlaningMethod()
    {
        if((vQueryFamilyPlaningMethod != null))
            return vQueryFamilyPlaningMethod;
        theConnectionInf.open();
        try
        {
            vQueryFamilyPlaningMethod = thePcuDB.theFamilyPlaningMethodDB.selectAllWithOut("0", true);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vQueryFamilyPlaningMethod;
    }
    
    /**
     * ใช้แสดงข้อมูลใน ComboBox ของ วิธิการคุมกำเนิด
     * @return เป็น Vector ของ Object ที่ใช้กับ ComboFix
     * @author ผดุงรัฐ
     */
    public Vector queryAllFamilyPlaningMethod()
    {
        if((vQueryAllFamilyPlaningMethod != null))
            return vQueryAllFamilyPlaningMethod;
        theConnectionInf.open();
        
        try
        {
            vQueryAllFamilyPlaningMethod = thePcuDB.theFamilyPlaningMethodDB.selectNot09();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vQueryAllFamilyPlaningMethod;
    }
    
    /**
     * ใช้ในการ แสดงข้อมูลง ComboBox เท่านั้น
     * @return เป็น Vector ของ Object
     * @author ผดุงรัฐ
     */
    public Vector queryFamilyPlaningSupplyGroup()
    {
        if((vQueryFamilyPlaningSupplyGroup != null))
            return vQueryFamilyPlaningSupplyGroup;
        theConnectionInf.open();
        try
        {
            vQueryFamilyPlaningSupplyGroup = thePcuDB.theFamilyPlaningSupplyGroupDB.selectAll("1",true);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vQueryFamilyPlaningSupplyGroup;
    }
    
    /**
     * ใช้แสดงข้อมูลใน ComboBox ของ สาเหตุไม่คุมกำเนิด
     * @return เป็น Vector ของ Object ที่ใช้กับ ComboFix
     * @Author ผดุงรัฐ
     */
    public Vector queryAllFamilyPlaningCause()
    {
        if((vQueryAllFamilyPlaningCause != null))
            return vQueryAllFamilyPlaningCause;
        theConnectionInf.open();
        try
        {
            vQueryAllFamilyPlaningCause = thePcuDB.theFamilyPlaningCauseDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vQueryAllFamilyPlaningCause;
    }
    
    public Vector listUom()
    {
        return theLookupControl.listUom();
        
    }
    
    public Vector listDrugInstruction()
    {
        return theLookupControl.listDrugInstruction();
    }
    
    public Vector listDrugFrequency()
    {
        return theLookupControl.listDrugFrequency();
    }
    public Vector listGrow()
    {
        if((vGrow != null))
            return vGrow;
        theConnectionInf.open();
        try
        {
            vGrow = thePcuDB.theGrowDB.selectAllGrow();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vGrow;
    }
    public Vector listGrowComboFix()
    {
        Vector vGrowFix = new Vector();
        theConnectionInf.open();
        try
        {
            vGrowFix = thePcuDB.theGrowDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vGrowFix;
    }
    
    public Vector listGrow(String str)
    {
        
        theConnectionInf.open();
        str = Gutil.CheckReservedWords(str);
        try
        {
            vGrow = thePcuDB.theGrowDB.selectByCN("%" + str + "%");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vGrow;
    }
    
    public Grow readGrowById(String id)
    {
        Vector vp = listGrow();
        Grow p=null;
        for(int j=0;j<vp.size();j++)
        {
            p = (Grow)vp.get(j);
            if(p.getObjectId().equals(id))
            {
                break;
            }
        }
        if(p!=null && p.getObjectId().equals(id))
            return p;
        return null;
        
    }
    
    public OccupationPcu readOccupatById(String pk)
    {
        
        OccupationPcu theOccupationPcu = null;
        theConnectionInf.open();
        try
        {
            theOccupationPcu = thePcuDB.theOccupationPcuDB.select2ByPK(pk);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return theOccupationPcu;
    }
    
    public Vector listOccupationByCodeName(String str)
    {
        theConnectionInf.open();
        try
        {
            return thePcuDB.theOccupationPcuDB.selectByCN("%" + str + "%");
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
    
    public Vector listPrefix()
    {
        return theLookupControl.listPrefix();
    }
    
    public Vector listPrefix(String str)
    {
        theConnectionInf.open();
        try
        {
            return thePcuDB.thePrefixPcuDB.selectByCN("%"+str+"%");
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
    
    public PrefixPcu readPrefixById(String id)
    {
//        pf = thePcuDB.thePrefixDB.selectByPK(id);
        Vector vp = this.listPrefix();
        PrefixPcu p=null;
        for(int j=0;j<vp.size();j++)
        {
            try
            {
                p = (PrefixPcu)vp.get(j);
            }
            catch(Exception e)
            {
                ComboFix cf = (ComboFix)vp.get(j);
                p = new PrefixPcu();
                p.setObjectId(cf.code);
                p.description = cf.name;
            }
            if(p.getObjectId().equals(id))
            {
                break;
            }
        }
        return p;
    }
    
    public Vector listCheckResult()
    {
        if((vCheckResult != null))
            return vCheckResult;
        theConnectionInf.open();
        try
        {
            vCheckResult = thePcuDB.theCheckResultDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vCheckResult;
    }
    
    public Vector listTempleType()
    {
        if((vTempleType != null))
            return vTempleType;
        theConnectionInf.open();
        try
        {
            vTempleType = thePcuDB.theTempleTypeDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vTempleType;
    }
    
    public Vector listTemplePersonel()
    {
        if((vTemplePersonel != null))
            return vTemplePersonel;
        theConnectionInf.open();
        try
        {
            vTemplePersonel = thePcuDB.theTemplePersonelDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vTemplePersonel;
    }
    
    public Vector listReligion()
    {
        return theLookupControl.listReligion();
    }
    
    public Vector listEducate()
    {
        return theLookupControl.listEducate();
    }
    
    public Vector listUterusLevel()
    {
        if((vUterusLevel != null))
            return vUterusLevel;
        theConnectionInf.open();
        try
        {
            vUterusLevel = thePcuDB.theUterusLevelDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vUterusLevel;
    }
    
    public Vector listBabyStatus()
    {
        if((vBabyStatus != null))
            return vBabyStatus;
        theConnectionInf.open();
        try
        {
            vBabyStatus = thePcuDB.theBabyStatusDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vBabyStatus;
    }
    
    public Vector listConduct()
    {
        if((vConduct != null))
            return vConduct;
        theConnectionInf.open();
        try
        {
            vConduct = thePcuDB.theConductDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vConduct;
    }
    
    public Vector listAbNomal()
    {
        if((vAbNomal != null))
            return vAbNomal;
        theConnectionInf.open();
        try
        {
            vAbNomal = thePcuDB.theAbNomalDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vAbNomal;
    }
    
    public Vector listMarketType()
    {
        if((vMarketType != null))
            return vMarketType;
        theConnectionInf.open();
        try
        {
            vMarketType = thePcuDB.theMarketTypeDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vMarketType;
    }
    
    public Vector listMarketTypeLock()
    {
        if((vMarketTypeLock != null))
            return vMarketTypeLock;
        theConnectionInf.open();
        try
        {
            vMarketTypeLock = thePcuDB.theMarketTypeLockDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vMarketTypeLock;
    }
    
    public Vector listMarket()
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theMarketDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public Vector listResource()
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theCommunityResourceDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public Vector listAGRCode()
    {
        if((vAGRCode != null))
            return vAGRCode;
        theConnectionInf.open();
        try
        {
            vAGRCode = thePcuDB.theAGRCodeDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vAGRCode;
    }
    
    public Vector listAGRType()
    {
        if((vAGRType != null))
            return vAGRType;
        theConnectionInf.open();
        try
        {
            vAGRType = thePcuDB.theAGRTypeDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vAGRType;
    }
    /**
     *ใช้ในการหาผลของการตรวจฝีเย็บ
     *@return เป็น Vector ของ ComboFix ที่ใช้ในการจัดลง comboBox
     *@author padungrat (tong)
     */
    public Vector listPostpartumEpisiotomyType()
    {
        if((vPostpartumEpisiotomyType != null))
            return vPostpartumEpisiotomyType;
        theConnectionInf.open();
        try
        {
            vPostpartumEpisiotomyType = thePcuDB.thePostpartumEpisiotomyTypeDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            
        }
        finally
        {
            theConnectionInf.close();
        }
        return vPostpartumEpisiotomyType;
    }
    
    /**
     *  ใช้ในการแสดงข้อมูลของ ผลการคลอด ลงใน ComboBox
     *  @return เป็น Vector ของ ComboFix
     *  @author padungrat(tong)
     */
    public Vector listResultGiveBirth()
    {
        if((vResultGiveBirth != null))
            return vResultGiveBirth;
        theConnectionInf.open();
        try
        {
            vResultGiveBirth = thePcuDB.theResultGiveBirthDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vResultGiveBirth;
    }
    
    /**
     *  ใช้ในการแสดงข้อมูลของ ผลการตรวจหลังคลอด ลงใน ComboBox
     *  @return เป็น Vector ของ ComboFix
     *  @author padungrat(tong)
     */
    public Vector listResultStatus()
    {
        if((vResultStatus != null))
            return vResultStatus;
        theConnectionInf.open();
        try
        {
            vResultStatus = thePcuDB.theResultStatusDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vResultStatus;
    }
    /**
     *  ใช้ในการแสดงข้อมูลของ ประเภทผู้ทำคลอด ลงใน ComboBox
     *  @return เป็น Vector ของ ComboFix
     *  @author padungrat(tong)
     */
    public Vector listBDoctor()
    {
        if((vBDoctor != null))
            return vBDoctor;
        theConnectionInf.open();
        try
        {
            vBDoctor = thePcuDB.theBDoctorDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vBDoctor;
    }
    public Vector listPPResultDetail()
    {
        if((vPPResultDetail != null))
            return vPPResultDetail;
        theConnectionInf.open();
        try
        {
            vPPResultDetail = thePcuDB.thePPResultDetailDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vPPResultDetail;
    }
    
    /**
     *  ใช้ในการแสดงข้อมูลของ รหัส ICD10 ของการคลอด ลงใน ComboBox
     *  @return เป็น Vector ของ ComboFix
     *  @author padungrat(tong)
     */
    public Vector comboBoxViewICD10Pregnant()
    {
//        if((vICD10Pregnant != null)){
//            return vICD10Pregnant;
//        }
        theConnectionInf.open();
        try
        {
            
            vICD10Pregnant = thePcuDB.theICD10DB.selectByPregnant();
            ComboFix comf = new ComboFix();
            comf.code = "000000000000000000";
            comf.name = "ไม่ระบุ";
            vICD10Pregnant.add(0,comf);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            
        }
        finally
        {
            theConnectionInf.close();
        }
        return vICD10Pregnant;
    }
    
    
    public Vector listPPBreathingTime()
    {
        if((vPPBreathingTime != null))
            return vPPBreathingTime;
        theConnectionInf.open();
        try
        {
            vPPBreathingTime = thePcuDB.thePPBreathingTimeDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vPPBreathingTime;
    }
    
    public Vector listPPAspiration()
    {
        if((vPPAspiration != null))
            return vPPAspiration;
        theConnectionInf.open();
        try
        {
            vPPAspiration = thePcuDB.thePPAspirationDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vPPAspiration;
    }
    
    public Vector listPPEyeDrug()
    {
        if((vPPEyeDrug != null))
            return vPPEyeDrug;
        theConnectionInf.open();
        try
        {
            vPPEyeDrug = thePcuDB.thePPEyeDrugDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vPPEyeDrug;
    }
    
    public Vector listBirthMethod()
    {
        if((vBirthMethod != null))
            return vBirthMethod;
        theConnectionInf.open();
        try
        {
            vBirthMethod = thePcuDB.theBirthMethodDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vBirthMethod;
    }
    
    public Vector listBirthMethodForPP()
    {
        if((vBirthMethodForPP != null))
            return vBirthMethodForPP;
        theConnectionInf.open();
        try
        {
            vBirthMethodForPP = thePcuDB.theBirthMethodDB.selectAllForPP();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vBirthMethodForPP;
    }
    
    public Vector listPPState()
    {
        if((vPPState != null))
            return vPPState;
        theConnectionInf.open();
        try
        {
            vPPState = thePcuDB.thePPStateDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vPPState;
    }
    
    public Vector listPPDeadType()
    {
        if((vPPDeadType != null))
            return vPPDeadType;
        theConnectionInf.open();
        try
        {
            vPPDeadType = thePcuDB.thePPDeadTypeDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vPPDeadType;
    }
    
    public Vector listPPDeadCause()
    {
        if((vPPDeadCause != null))
            return vPPDeadCause;
        theConnectionInf.open();
        try
        {
            vPPDeadCause = thePcuDB.thePPDeadCauseDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vPPDeadCause;
    }
    
    public Vector listOffice(String ch, String amphur)
    {
        vVector= new Vector();
        theConnectionInf.open();
        try
        {
            if((amphur.length() == 6))
                amphur = amphur.substring(2,4);
            if((ch.length() == 6))
                ch = ch.substring(0,2);
            vVector = thePcuDB.theOfficeDB.selectByCA(ch,amphur);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vVector;
    }
    
    public Office readHospitalByCode(String code)
    {
        if((code == null)) return null;
        if((code.length() == 0)) return null;
        Office vc = null;
        
        theConnectionInf.open();
        try
        {
            vc = thePcuDB.theOfficeDB.selectByPK(code);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vc;
    }
    
    public Vector listVacineTT()
    {
        vVector = new Vector();
        theConnectionInf.open();
        try
        {
            vVector = thePcuDB.theEpiSetGroupDB.SelectTTByBeforeMch("TT");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vVector;
    }
    /**
     * ค้นหารายการโรคที่ติดต่อและไม่ติดต่อ
     * @param  true = โรคติดต่อ   false = โรคไม่ติดต่อ
     * @return Vector ของ Object Disease
     * @author kingland
     * @date 24-02-2549
     */
    public Vector listDisease(boolean isDiseaseContagious)
    {
        Vector v = null;
        theConnectionInf.open();
        try
        {
            v = thePcuDB.theDiseaseDB.selectConboFixDisease(isDiseaseContagious);
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
    
    public Vector listDischargeStatus()
    {
        if((vDischarge != null))
            return vDischarge;
        
        theConnectionInf.open();
        try
        {
            vDischarge = thePcuDB.theDischargeDB.selectAll();
            vDischarge.add(0,new ComboFix("","ไม่ระบุ"));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vDischarge;
    }
    
    public Vector listMaimType()
    {
        if((vMaimType != null))
            return vMaimType;
        theConnectionInf.open();
        try
        {
            vMaimType = thePcuDB.theMaimTypeDB.selectMaimType();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vMaimType;
    }
    public Vector listSurvey()
    {
        //if((this.vSurvey != null))
        //return vSurvey;
        theConnectionInf.open();
        try
        {
            vSurvey = thePcuDB.theSurveyDB.selectAllComboFix();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vSurvey;
    }
    //////////////////
    public void initFixDataToHashTable()
    {
        Vector tempTable = this.selectAllPrefix();
        CommonInf combofix;// = new CommonInf();
        if(tempTable != null)
        {
            for(int i=0;i<tempTable.size();i++)
            {
                combofix = (CommonInf)tempTable.elementAt(i);
                tablePrefix.put((String)combofix.getCode(), (String)combofix.getName());
            }
        }
        tempTable = null;
        
        tempTable = this.selectAllCheckResult();
        if(tempTable != null)
        {
            for(int i=0;i<tempTable.size();i++)
            {
                combofix = (CommonInf)tempTable.elementAt(i);
                tableCheckResult.put((String)combofix.getCode(), (String)combofix.getName());
            }
        }
        tempTable = null;
        
        tempTable = this.selectAllSex();
        if(tempTable != null)
        {
            for(int i=0;i<tempTable.size();i++)
            {
                combofix = (CommonInf)tempTable.elementAt(i);
                tableSex.put((String)combofix.getCode(), (String)combofix.getName());
            }
        }
        tempTable = null;
        /*
        tempTable = this.selectAllAnswer(9);
        if(tempTable != null){
            for(int i=0;i<tempTable.size();i++){
                combofix = (CommonInf)tempTable.elementAt(i);
                tableAnswer.put((String)combofix.getCode(), (String)combofix.getName());
            }
        }
        tempTable = null;
         
        tempTable = this.selectAllAnswer(8);
        if(tempTable != null){
            for(int i=0;i<tempTable.size();i++){
                combofix = (CommonInf)tempTable.elementAt(i);
                tableHealthAnswer.put((String)combofix.getCode(), (String)combofix.getName());
            }
        }
        tempTable = null;
         
         
        tempTable = this.selectAllAnswer(14);
        if(tempTable != null){
            for(int i=0;i<tempTable.size();i++){
                combofix = (CommonInf)tempTable.elementAt(i);
                tableAppearAnswer.put((String)combofix.getCode(), (String)combofix.getName());
            }
        }
        tempTable = null;
         
        //หมายเลข 3 หมายถึงกำหนดให้ดึงข้อมูลจาก f_answer.answer_description_b
        tempTable = this.selectAllAnswer(3);
        if(tempTable != null){
            for(int i=0;i<tempTable.size();i++){
                combofix = (CommonInf)tempTable.elementAt(i);
                tableResultCheckAnswer.put((String)combofix.getCode(), (String)combofix.getName());
            }
        }
        tempTable = null;
         */
        
        tempTable = this.selectAllNutritionType();
        if(tempTable != null)
        {
            for(int i=0;i<tempTable.size();i++)
            {
                combofix = (CommonInf)tempTable.elementAt(i);
                tableNutritionType.put((String)combofix.getCode(), (String)combofix.getName());
            }
        }
        tempTable = null;
        
        tempTable = this.selectAllGumLevel();
        if(tempTable != null)
        {
            for(int i=0;i<tempTable.size();i++)
            {
                combofix = (CommonInf)tempTable.elementAt(i);
                tableGumLevel.put((String)combofix.getCode(), (String)combofix.getName());
            }
        }
        tempTable = null;
        
        tempTable = this.selectAllSchoolClass();
        if(tempTable != null)
        {
            for(int i=0;i<tempTable.size();i++)
            {
                combofix = (CommonInf)tempTable.elementAt(i);
                tableSchoolClass.put((String)combofix.getCode(), (String)combofix.getName());
            }
        }
        tempTable = null;
        
        tempTable = this.selectAllSchoolServiceType();
        if(tempTable != null)
        {
            for(int i=0;i<tempTable.size();i++)
            {
                //Constant.println(tempTable.elementAt(i).getClass().toString());
                combofix = (CommonInf)tempTable.elementAt(i);
                tableSchoolServiceType.put((String)combofix.getCode(), (String)combofix.getName());
            }
        }
        tempTable = null;
        
        tempTable = this.selectAllServiceType();
        if(tempTable != null)
        {
            for(int i=0;i<tempTable.size();i++)
            {
                combofix = (CommonInf)tempTable.elementAt(i);
                tableServiceType.put((String)combofix.getCode(), (String)combofix.getName());
            }
        }
        tempTable = null;
        
        tempTable = this.selectAllCheckHealthYearActivity();
        if(tempTable != null)
        {
            for(int i=0;i<tempTable.size();i++)
            {
                combofix = (CommonInf)tempTable.elementAt(i);
                tableCheckHealthYearActivity.put((String)combofix.getCode(), (String)combofix.getName());
            }
        }
        tempTable = null;
        
    }
    
    
    public Vector selectAllPrefix()
    {
        vTemp = new Vector();
        try
        {
            vTemp = this.listPrefix();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vTemp;
    }
    
    
    public Vector selectAllCheckResult()
    {
        vTemp = new Vector();
        try
        {
            vTemp = this.listCheckResult();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vTemp;
    }
    
    public Vector selectAllServiceType()
    {
        vTemp = new Vector();
        try
        {
            vTemp = this.listServiceType();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vTemp;
    }
    
    public Vector selectAllCheckHealthYearActivity()
    {
        vTemp = new Vector();
        try
        {
            vTemp = this.listCheckHealthYearActivity();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vTemp;
    }
    
    public Vector selectAllSex()
    {
        vTemp = new Vector();
        try
        {
            vTemp = this.listSex();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vTemp;
    }
    
    public Vector selectAllSchoolServiceType()
    {
        vTemp = new Vector();
        try
        {
            vTemp = this.listServiceType();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vTemp;
    }
    
    /*
    public Vector selectAllAnswer(int index) {
        vTemp = new Vector();
        try {
            vTemp = this.listPcuAnswer(index);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return vTemp;
    }*/
    
    public Vector selectAllNutritionType()
    {
        vTemp = new Vector();
        try
        {
            vTemp = this.listNutritionType();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vTemp;
    }
    
    
    public Vector selectAllGumLevel()
    {
        vTemp = new Vector();
        try
        {
            vTemp = this.listGumLevel();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vTemp;
    }
    
    public Vector selectAllSchoolClass()
    {
        vTemp = new Vector();
        try
        {
            vTemp = this.listSchoolClass();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vTemp;
    }
    /**
     * เกือบ deprecated henbe ใช้ฟังชันจาก control แทนนะ
     *theAllComboBoxControl.getValueOfPrefix(
     *theHosInf.getHosControl().theLookupControl.readPrefixString(
     *
     *ปรับให้ใช้ฟังก์ชั่นจาก theLookupControl
     *@author pu
     */
    public String getValueOfPrefix(String index)
    {
        return theLookupControl.readPrefixString(index);
    }
    
    public String getValueOfCheckResult(String index)
    {
        return (String)tableCheckResult.get(index);
    }
    
    public String getValueOfSex(String index)
    {
        return (String)tableSex.get(index);
    }
    
    public String getValueOfAnswer(String index)
    {
        return (String)tableAnswer.get(index);
    }
    
    public String getValueOfHealthAnswer(String index)
    {
        return (String)tableHealthAnswer.get(index);
    }
    
    public String getValueOfResultCheckAnswer(String index)
    {
        return (String)tableResultCheckAnswer.get(index);
    }
    
    public String getValueOfNutritionType(String index)
    {
        return (String)tableNutritionType.get(index);
    }
    
    public String getValueOfGumLevel(String index)
    {
        return (String)tableGumLevel.get(index);
    }
    
    public String getValueOfAppearAnswer(String index)
    {
        return (String)tableAppearAnswer.get(index);
    }
    
    public String getValueOfSchoolClass(String index)
    {
        return (String)tableSchoolClass.get(index);
    }
    
    public String getValueOfSchoolServiceType(String index)
    {
        return (String)tableSchoolServiceType.get(index);
    }
    
    public String getValueOfServiceType(String index)
    {
        return (String)tableServiceType.get(index);
    }
    
    public String getValueOfCheckHealthYearActivity(String index)
    {
        return (String)tableCheckHealthYearActivity.get(index);
    }
    
    public BloodGroup readBloodById(String id)
    {
        Vector vBlood = theLookupControl.listBlood();
        for(int i=0;i<vBlood.size();i++)
        {
            BloodGroup bg = (BloodGroup)vBlood.get(i);
            if(bg.getObjectId().equals(id))
                return bg;
        }
        return null;
    }
    public boolean isFutureDate(String currentDate,String date)
    {
        String theCurrentDate = "";
        String theDate = "";
        boolean res = false;
        if(currentDate.length()>=10)
            theCurrentDate = currentDate.substring(0,10);
        if(date.length()>=10)
            theDate = date.substring(0,10);
        if(com.hosv3.utility.DateUtil.countDateDiff(theCurrentDate,theDate)> 0)
        {
            res = true;
        }
        else
        {
            res = false;
        }
        if(theCurrentDate.equals(theDate))
            res = true;
        return res;
    }

}
