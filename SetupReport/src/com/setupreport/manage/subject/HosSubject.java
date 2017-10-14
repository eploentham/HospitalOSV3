/*
 * HosSubject.java
 *
 * Created on 21 ตุลาคม 2548, 16:44 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage.subject;
/**
 *
 * @author tong(Padungrat)
 */
public class HosSubject 
{
    
    public AntibioticSubject theAntibioticSubject;
    public DentalProtectItemSubject theDentalProtectItemSubject;
    public EpiGroupItemSubject theEpiGroupItemSubject;
    public EpiMeaslesItemSubject theEpiMeaslesItemSubject;
    public HealthyGroupSurveySubject theHealthyGroupSurveySubject;
    public ScreeningDiseaseItemSubject theScreeningDiseaseItemSubject;
    public NutritionSubject theNutritionSubject;
    public EpiTTItemSubject theEpiTTItemSubject;
    public EpiAgeGroupItemSubject theEpiAgeGroupItemSubject;
    public ClinicSubject theClinicSubject;
    
    public HosSubject() 
    {
        theAntibioticSubject = new AntibioticSubject();
        theDentalProtectItemSubject = new DentalProtectItemSubject();
        theEpiGroupItemSubject = new EpiGroupItemSubject();
        theEpiMeaslesItemSubject = new EpiMeaslesItemSubject();
        theHealthyGroupSurveySubject = new HealthyGroupSurveySubject();
        theScreeningDiseaseItemSubject = new ScreeningDiseaseItemSubject();
        theNutritionSubject = new NutritionSubject();
        theEpiTTItemSubject = new EpiTTItemSubject();
        theEpiAgeGroupItemSubject = new EpiAgeGroupItemSubject();
        theClinicSubject = new ClinicSubject();
    }
    
}
