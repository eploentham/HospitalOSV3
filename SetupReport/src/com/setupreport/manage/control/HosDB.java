/*
 * HosDB.java
 *
 * Created on 22 ตุลาคม 2548, 12:11 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage.control;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.setupreport.objectdb.*;

/**
 *
 * @author tong(Padungrat)
 */
public class HosDB {
    
    public ConnectionInf theConnectionInf;
    
    /**ARIC*/
    public AricGroupDB theAricGroupDB;
    /**EYE Disease*/
    public EyeDiseaseCodeDB theEyeDiseaseCodeDB;
    public EyeGroupDB theEyeGroupDB;
    public ICD10DB theICD10DB;
    /**ARICSubGroup*/
    public AricSubGroupDB theAricSubGroupDB;
    
    public AricDiseaseCodeDB theAricDiseaseCodeDB;
    /**PlanGroup*/
    public PlanGroupDB thePlanGroupDB;    
    public PlanGroupMapPtTypeDB thePlanGroupMapPtTypeDB;    
    public UCPlanGroupPttypeDB theUCPlanGroupPttypeDB;
    public ItemDB theItemDB;

    public AricAntibioticItemDB theAricAntibioticItemDB;
    /**Resident Age Group*/
    public ResidentAgeGroupDB theResidentAgeGroupDB;
    
    /**11รง5 ส่วนที่ 3*/
    public DentalProtectItemDB theDentalProtectItemDB;
    public EpiGroupItemDB theEpiGroupItemDB;
    public EpiMeaslesItemDB theEpiMeaslesItemDB;
    public EpiDB theEpiDB;
    public EpiGroupDB theEpiGroupDB;
    public HealthyGroupDB theHealthyGroupDB;
    public HealthyGroupSurveyDB theHealthyGroupSurveyDB;
    public HealthySurveyDB theHealthySurveyDB;
    public HealthySubgroupDB theHealthySubgroupDB;
    public Rp115Group4DiseaseDB theRp115Group4DiseaseDB;
    public Rp115Group4ItemDB theRp115Group4ItemDB;
    public NutritionLevelDB theNutritionLevelDB;
    public NutritionMapDB theNutritionMapDB;
    public NutritionTypeDB theNutritionTypeDB;
    
    /**รายงานวัคซีน PCU*/
    public EpiTTItemDB theEpiTTItemDB;
    public EpiAgeGroupDB theEpiAgeGroupDB;
    public EpiAgeGroupItemDB theEpiAgeGroupItemDB;
    public EpiAgeGroupTypeDB theEpiAgeGroupTypeDB;
    
    public HosDB(ConnectionInf connectionInf) {
        theConnectionInf = connectionInf;        
        
        theAricGroupDB = new AricGroupDB(theConnectionInf);
        theEyeDiseaseCodeDB = new EyeDiseaseCodeDB(theConnectionInf);
        theEyeGroupDB = new EyeGroupDB(theConnectionInf);       
        theAricSubGroupDB = new AricSubGroupDB(theConnectionInf);
        theAricDiseaseCodeDB = new AricDiseaseCodeDB(theConnectionInf);

        thePlanGroupDB = new PlanGroupDB(theConnectionInf);
        thePlanGroupMapPtTypeDB = new PlanGroupMapPtTypeDB(theConnectionInf);
        
        theUCPlanGroupPttypeDB = new UCPlanGroupPttypeDB(theConnectionInf);
        theICD10DB = new ICD10DB(theConnectionInf);
        
        theItemDB = new ItemDB(theConnectionInf);
        
        theAricAntibioticItemDB = new AricAntibioticItemDB(theConnectionInf);
        theResidentAgeGroupDB = new ResidentAgeGroupDB(theConnectionInf);
        
        theDentalProtectItemDB = new DentalProtectItemDB(theConnectionInf);
        theEpiGroupItemDB = new EpiGroupItemDB(theConnectionInf);
        theEpiMeaslesItemDB = new EpiMeaslesItemDB(theConnectionInf);
        theEpiDB = new EpiDB(theConnectionInf);
        theEpiGroupDB = new EpiGroupDB(theConnectionInf);
        theHealthyGroupDB = new HealthyGroupDB(theConnectionInf);
        theHealthyGroupSurveyDB = new HealthyGroupSurveyDB(theConnectionInf);
        theHealthySurveyDB = new HealthySurveyDB(theConnectionInf);
        theHealthySubgroupDB = new HealthySubgroupDB(theConnectionInf);
        theRp115Group4DiseaseDB = new Rp115Group4DiseaseDB(theConnectionInf);
        theRp115Group4ItemDB = new Rp115Group4ItemDB(theConnectionInf);
        theNutritionLevelDB = new NutritionLevelDB(theConnectionInf);
        theNutritionMapDB = new NutritionMapDB(theConnectionInf);
        theNutritionTypeDB = new NutritionTypeDB(theConnectionInf);
        
        theEpiTTItemDB = new EpiTTItemDB(theConnectionInf);
        theEpiAgeGroupDB = new EpiAgeGroupDB(theConnectionInf);
        theEpiAgeGroupItemDB = new EpiAgeGroupItemDB(theConnectionInf);
        theEpiAgeGroupTypeDB = new EpiAgeGroupTypeDB(theConnectionInf);
    }
}
