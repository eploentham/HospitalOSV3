/*
 * HosSubject.java
 *
 * Created on 28 �Զع�¹ 2548, 15:05 �.
 */

package com.pcu.subject;

/**
 *
 * @author Administrator
 */
public class HosSubject {    
    public SetupSubjectPcu theSetupSubjectPcu;
    public AfterMchSubject theAfterMchSubject;
    public PPSubject thePPSubject;
    public CommunityResourceSubject theCommunityResourceSubject;
    public HomePatientSubject theHomePatientSubject;
    public PersonSubject thePersonSubject;
    /** Creates a new instance of HosSubject */
    public HosSubject() 
    {
         theSetupSubjectPcu = new SetupSubjectPcu();
         theAfterMchSubject = new AfterMchSubject();
         thePPSubject = new PPSubject();
         theCommunityResourceSubject = new CommunityResourceSubject();
         theHomePatientSubject = new HomePatientSubject();
         thePersonSubject =  new PersonSubject();
         
    }    
}
