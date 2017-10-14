/*
 * LookupObject.java
 *
 * Created on 23 กันยายน 2548, 14:30 น.
 */

package com.hosv3.object;

import com.hospital_os.usecase.connection.CommonInf;
import com.hospital_os.utility.ComboFix;
import com.pcu.object.Disease;
import java.util.*;
import com.hospital_os.object.*;
/**
 *
 * @author  Administrator
*/
public class LookupObject {
    
    public Vector thePrefix;
    public Vector theBillingGroupItem;//
    public Vector theCategoryGroupItem;//
    public Vector vDischargeIpd;//
    public Vector theDxtype;//
    public Vector theEmployee;//
    public AutoReportBug theAutoReportBug;
    public Option theOption;
    public Office theOffice;
    public Site theSite;
    public String sChangwat;
    public String sAmphur;
    public Vector theAccrdType;
    public Vector theAccproType;
    public Vector theAccuseType;
    public Vector theAncSection;
    public Vector theAmphur;
    public Vector theAuthentication;
    public Vector theAppointmentStatus;
    public Vector theBdoctor;
    public Vector theBillingGroup;
    public Vector theBlood;
    public Vector theCategoryGroup;
    public Vector theChangwat;
    public Vector theEducate;
    public Vector theFilmSize;
    public Vector theFstatus;
    public Vector theGarbage;
    public Vector theGAction;
    public Vector theGender;
    public Vector theICD10;
    public Vector theICD9;
    public Vector theInOutrdType;
    public Vector theLabor;
    public Vector theLevel;
    public Vector theReceiptMedel;
    public Vector theOrderSpecified;
    public Vector theMarriage;
    public Vector theNation;
    public Vector theOccupation;
    public Vector theOpdType;
    public Vector theOptype;
    public Vector theOrderItemStatus;
    public Vector thePrefixTlock2;
    public Vector thePtmobieType;
    public Vector thePtStatusType;
    public Vector theRace;
    public Vector theReligion;
    public Vector theTypeArea;  
    public Vector theTambon;
    public Vector thePlan;
    public Vector vQueueVisit;
    public Vector theDrugDosePrint;
    public Vector theServicePointWithOutXL;
    public Vector theServicePoint;
    public Vector vServicePoint; /*pu*/
    public Vector vTab;//sumo defult_tab
    public Vector theVisitStatus;
    public Vector theReferCause;// sumo สาเหตุการ refer
    public Vector theEmployee_loc;
    public Vector theServiceSubType;
    public Vector theServiceType;
    public Vector theSew;
    public Vector theNormal;
    public Vector theSQLTemplate;
    public Vector theSQLTemplateParam;
    public Vector theClinic; /// pee
    public Vector theDoctor; /// pee
    public Vector theNurse; /// pee
    public Vector theOpdCardFormat; /// pee
    public Vector thePayer; ///pee
    public Vector theContractMethod; ///pee
    public Vector theBilling; /// pee
    public Vector theCategoryGroupItemDrugAndSupply;//amp ใช้ในกองทุนยา
    public Vector theContract;
    public Vector theFpType;   //type of Birth Control
    public Vector theNofp;  //สาเหตุของการไม่คุมกำเนิด
    public Vector theAccRd;//amp
    public Vector theInOut;//amp
    public Vector thePtStatus;//amp
    public Vector thePtMobile;//amp
    public Vector theAccUse;//amp
    public Vector theAccPro;//amp
    public Vector thewatertype; // แหล่งน้ำ
    public Vector theGagBage; //ขยะ
    public Vector theHCharac;
    public Vector theComCharac;
    public Vector theLocalType;
    public Vector theIcd10GroupType;//pee
    public Vector theFilmXray; //tong
    public Vector theXrayPosition;
    public Vector theXrayLeteral;
   /**0 = ไม่ตรวจ 1 = ปกติ  2 = ผิดปกติ*/
    public Vector theResultStatus;
    /**0 = ไม่มี , 1 = มี */
    public Vector theAnswer;
    public Vector theHighRisk;    
    public Vector theNutritionType2V;///neung
    public Vector vItemlabset; ///neung
    public Vector theDrugInstructionAll; //ojika
    public Vector theDrugFrequencyAll; //ojika

    public Vector theUomAll; //ojika
//    public Vector theBDoctor;
    public Vector theBType;
    public Vector theTypeDish;
    public Vector theRelation;
  //where is relation
    public Vector theWard;
    public Vector vDischargeOpd;
    public Vector vDischargeType;
    public Vector theTlock;
    public Vector theBirthPlace;
    /** ท่าของเด็ก*/
    public Vector thePostureBaby;
    /** ระดับ*/
    public Vector thePregnantLevel;
    /** ระดับมดลูก*/
    public Vector theUterusLevel;
    /** ส่วนนำ*/
    public Vector theConduct;
    public Vector theAnswerHealth;
    /**ผลของการคลอด*/
    public Vector theResultGiveBirth;
    public Vector theICD10Pregnant;
    /**ฝีเย็บ*/
    public Vector theMilkSeep;
    public Vector vVitK;
    public Vector vAsphyxia;
    /**โรคเรื้อรัง*/
    public Vector theGroupChronic;
    /**โรคเฝ้าระวังทางระบาดวิทยา 506*/
    public Vector theGroupSurveil;
    public Vector theGroup504;
    public Vector theGroup505;
    public Vector theGroup506;
    public String date_time;
    public Vector vBodyOrgan;//amp:10/04/2549 ตัวช่วยอวัยวะ
    public Vector vDisease;//amp:18/04/2549 โรคทั่วไป    
    public Vector vNutritionType;//amp:27/04/2549 ระดับโภชนาการแบบใหม่
    public Vector vNutritionTypeMap;//amp:28/04/2549 ระดับโภชนาการแบบใหม่จับคู่กับแบบเก่า
    public Vector vTheEmployeeXray;//tong
    public Vector vTheAccidentGroupPatientType;//tong
    public Vector vTheAccidentType;//tong
    public Vector vTempAccidentType; //tong
    public Vector vItem16Group; //sumo รายการกลุ่มมาตรฐาน
    public Vector vNCDGroup; //amp:14/06/2549:โรค NCD
    public Vector vGuide; //sumo:04/08/2549 : คำแนะนำ
    public Vector vCalDateAppointment; // sumo:08/08/2549 : ตัวคำนวณวันที่นัด
    public Vector vDrugDoseShortcut;//pu : 03/08/2549 Dose ย่อ
    public String path_print;
//

    public SequenceData theSqdAN;
//
//    public Vector thePlanActive;
    
    public Vector theDrugInstruction; //ojika
    public Vector theDrugFrequency; //ojika
    public Vector theUom; //ojika

    public SequenceData theSqdVN;

    public SequenceData theSqdHN;

    public Vector thePlanActive; 


    
    /** Creates a new instance of LookupObject */
    public LookupObject() {
    }
    public void reset(){
        this.theDrugFrequency=null;
        this.theDrugInstruction=null;
        this.theUom=null;
    }

    public OptionDetail getOptionDetail(String string) {
        for(int i=0;i<theOption.vOption.size();i++){
            OptionDetail od = (OptionDetail)theOption.vOption.get(i);
            if(od.getObjectId().startsWith(string))
                return od;
        }
        return new OptionDetail();
    }


}
