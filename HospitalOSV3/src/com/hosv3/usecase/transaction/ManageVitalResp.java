/*
 * ManageVitalSignResp.java
 *
 * Created on 17 ����Ҥ� 2548, 18:55 �.
 */

package com.hosv3.usecase.transaction;

/**
 *
 * @author  americus
 */
public interface ManageVitalResp {
    
/** Creates a new instance of createPatientAllergy  */
    public void notifyManageVitalSign(String str, int status);   
    
    /*�ѹ�֡������� ����Ѻ�絤��� visittransfer pu*/
    public void notifyManagePhysicalExam(String str,int status);

    /*�����ҡ�����ͧ�� ����ҡ���Ӥѭ � TextArea ˹�� VitalSign �Ѻ notify pu*/
    public void notifyManagePrimarySymptom(String str, int status);
    
    /*ᾷ��ŧ Diagnosis ���˹�� Vital sign pu*/
    public void notifySaveDiagDoctor(String str,int status);
    
    /*ź��¡�� Map Dx pu*/
    public void notifyDeleteMapVisitDxByVisitId(String str,int status);
    
    /*ᾷ��������¡���ä �ҡ panel Searchsub ˹�� Vital �Ѻ notify pu*/
    public void notifyAddDxTemplate(String str,int status);
    
     /*������ա�������ҡ�ûѨ�غѹ�ҡDialog searchsub -neung*/
    public void notifyAddPrimarySymptom(String str,int status);
    
  
}
