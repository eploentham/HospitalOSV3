/*
 * HosControl.java
 *
 * Created on 21 ตุลาคม 2548, 16:44 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.connection.UpdateStatus;
import com.setupreport.manage.control.*;
import com.setupreport.manage.control.HosDB;
import com.setupreport.manage.subject.*;
/**
 *
 * @author tong(Padungrat)
 */
public class HosControl {
    
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    public AricGroupControl theAricGroupControl;
    public EyeGroupControl theEyeGroupControl;
    public EyeDiseaseCodeControl theEyeDiseaseCodeControl;
    public AricSubGroupControl theAricSubGroupControl;
    public AricDiseaseCodeControl theAricDiseaseCodeControl;

    public PlanGroupControl thePlanGroupControl;
    public PlanGroupMapPtTypeControl thePlanGroupMapPtTypeControl;

    public UCPlanGroupPttypeControl theUCPlanGroupPttypeControl;
    public AricAntibioticControl theAricAntibioticControl;
    public ResidentAgeGroupControl theResidentAgeGroupControl;
    /**สำหรับ 11รง5 ส่วนที่3 โดย Ojika*/
    public EpiMeaslesItemControl theEpiMeaslesItemControl;
    public EpiGroupItemControl theEpiGroupItemControl;
    public DentalProtectItemControl theDentalProtectItemControl;
    public HealthyGroupSurveyControl theHealthyGroupSurveyControl;
    public NutritionControl theNutritionControl;
    
    /**สำหรับ 11รง5 ส่วนที่4*/
    public ScreeningDiseaseControl theScreeningDiseaseControl;
    
    /**สำหรับ วัคซีน PCU*/
    public EpiTTItemControl theEpiTTItemControl;
    public EpiAgeGroupItemControl theEpiAgeGroupItemControl;
    
    public ComboboxControl theComboboxControl;
    
    public HosSubject theHosSubject;    
    
    UpdateStatus theUS;

    public HosControl(ConnectionInf c,UpdateStatus us) {
        theUS = us;
        theConnectionInf = c;
        theHosSubject = new HosSubject();
        theHosDB = new HosDB(theConnectionInf);
        theAricSubGroupControl = new AricSubGroupControl(theHosDB,theUS);
        theAricGroupControl = new AricGroupControl(theHosDB,theUS);
        theEyeGroupControl = new EyeGroupControl(theHosDB,theUS);
        theEyeDiseaseCodeControl = new EyeDiseaseCodeControl(theHosDB,theUS);        
        theAricDiseaseCodeControl = new AricDiseaseCodeControl(theHosDB,theUS);

        thePlanGroupControl = new PlanGroupControl(theHosDB,theUS);
        thePlanGroupMapPtTypeControl = new PlanGroupMapPtTypeControl(theHosDB,theUS);
        theUCPlanGroupPttypeControl = new UCPlanGroupPttypeControl(theHosDB,theUS);
        theAricAntibioticControl = new AricAntibioticControl(theHosDB,theUS);
        theResidentAgeGroupControl = new ResidentAgeGroupControl(theHosDB,theUS);      
        theEpiMeaslesItemControl = new EpiMeaslesItemControl(theHosDB,theUS);
        theEpiGroupItemControl = new EpiGroupItemControl(theHosDB,theUS);
        theDentalProtectItemControl = new DentalProtectItemControl(theHosDB,theUS);
        theHealthyGroupSurveyControl = new HealthyGroupSurveyControl(theHosDB,theUS);
        theScreeningDiseaseControl = new ScreeningDiseaseControl(theHosDB,theUS);
        theNutritionControl = new NutritionControl(theHosDB,theUS);   
//        theClinicControl = new ClinicControl(theHosDB);  
        theComboboxControl = new ComboboxControl(theHosDB,theUS);
        
        theEpiTTItemControl = new EpiTTItemControl(theHosDB,theUS);
        theEpiAgeGroupItemControl = new EpiAgeGroupItemControl(theHosDB,theUS);
    }
    
}
