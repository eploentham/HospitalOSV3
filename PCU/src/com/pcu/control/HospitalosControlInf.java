/*
 * HospitalosControlInf.java
 *
 * Created on 7 ����Ҿѹ�� 2549, 10:46 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.control;
import javax.swing.*;
import java.util.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;
import com.hosv3.control.LookupControl;
import com.hosv3.control.BillingControl;
import com.hosv3.control.SetupControl;
import com.hosv3.control.VisitControl;
import com.hosv3.control.HosControl;
import com.hosv3.subject.HosSubject;
/**
 *
 * @author Jao
 */

public interface HospitalosControlInf {
    
    /**
     *@Author : jao
     *@date : 07/02/2549
     *@see : �ѹ�֡������
     *@param : String=����, Patient=Object �ͧ������
     */
    public int savePatient(Patient p,String age);
    
    /**
     *@Author : jao
     *@date : 07/02/2549
     *@see : �Ŵ��ͤ������
     *@param : 
     */
    public void resetPatient();
        
    /**
     *@Author : jao
     *@date : 07/02/2549
     *@see : ���Ҽ����¨ҡ�Ţ�ѵû�ЪҪ�
     *@param : String pid = �Ţ�ѵû�ЪҪ�
     */
    public void readPatientByPatientID(String pid);
    
    /**
     *@Author : jao
     *@date : 07/02/2549
     *@see : ź������
     *@param : Patient=Object �ͧ������
     */
    public int deletePatient(Patient p);
    
    /**
     *@Author : jao
     *@date : 07/02/2549
     *@see : �Ҥ�� ConnectionInf �ͧ hosv3
     *@return : ConnectionInf �ͧ hosv3
     */
    public ConnectionInf getConnection();
    
    /**
     *@Author : jao
     *@date : 07/02/2549
     *@see : ���� Site 
     *@return : Object Site
     */
    public Site getSite();
    
    /**
     *@Author : jao
     *@date : 07/02/2549
     *@see : ���Ҥӹ�˹�Ҫ���
     *@param : String ���ͷ����㹡�ä���
     *@return : Object �ͧ�ӹ�˹�Ҫ���
     */
    public Relation readRelationByName(String name);
    
    /**
     *@Author : jao
     *@date : 07/02/2549
     *@see : �ѹ�֡�ӹ�˹�Ң���
     *@param : Relation=Object �ͧ�ӹ�˹�Ҫ���
     **/
    public void saveRelation(Relation r);
    
    /**
     *@Author : jao
     *@date : 07/02/2549
     *@see : �Ѻ����㹻Ѩ�غѹ
     *@return : String �ͧ����
     **/
    public String getTextCurrentDateTime();
    
    /**
     *@Author : jao
     *@date : 07/02/2549
     *@see : ������¡���� ���� key id �ͧ��
     *@param : itemid �� String ����� key id �ͧ��
     */
    public Item listItemByPk(String itemid);   
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : �ʴ�����͹����ͧ����ᶺ�ʴ�ʶҹТͧ hosv3
     *@param : String=�ӷ���ͧ����ʴ�, int=ʶҹз���ͧ����ʴ� �� normal=0,complete=1,waning=2,error=3
     */
    public void setStatus(String status,int istatus);
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : ���Ҽ����¨ҡHN
     *@param : String=hn
     *@return : Vector ������
     */
    public Vector listPatientByHn(String hn);
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : ���Ҽ����¨ҡ�Ţ�ѵû�ЪҪ�
     *@param : String=pid
     *@return : Vector ������
     */
    public Vector listPatientByPID(String pid);
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : ���Ҽ����¨ҡ����
     *@param : String=name , String= fname , String lname
     *@return : Vector ������
     */
    public Vector listPatientByName(String pname, String fname, String lname);
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : ����Employee�ҡ Primary Key
     *@param : String=pk
     *@return : Object �ͧ Employee
     */
    public Employee listEmployeeByPk(String pk);

    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : �Ѻ��� JFram �ҡ Hosv3 
     *@return : JFram
     */
    public JFrame getJFrame();
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : �Ѻ Object �ͧ Current Patient
     *@param : String=pk
     *@return : Object �ͧ Patient
     */
    public Patient getCurrentPatient();

    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : ���Ҥӹ�˹�Ҫ���
     *@param : String=id
     *@return : String �ӹ�˹�Ҫ���
     */
    public String listPrefix(String id);
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : �Ѻ LookupControlInf �ҡ Hosv3     
     *@return : LookupControlInf
     */
    public LookupControlInf getVitalTemplate();
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : �Ѻ LookupControl �ҡ Hosv3     
     *@return : LookupControl
     */
    public LookupControl getLookupControl();

    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : �����Ţ ICD10    
     *@param : String=icd10Id
     *@return : Object �ͧ ICD10
     */
    public ICD10 listIcd10ById(String icd10Id);
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : �Ѻ�ѹ���Ѩغѹ�ҡ Hosv3    
     *@return : String �ѹ���
     */

    public String getDateTime();
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : �Ѻ BillingControl �ҡ Hosv3
     *@return : BillingControl
     */
    public BillingControl getBillingControl();
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : �Ѻ Vector BillingPatient �ҡ Hosv3
     *@return : Vector
     */
    public Vector getvBillingPatient();
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : �Ѻ Vector OrderItemReceiveDrug �ҡ Hosv3
     *@return : Vector
     */
    public Vector getvOrderItemReceiveDrug();

    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : �Ѻ Vector PatientPayment �ҡ Hosv3
     *@param : String = patient_id
     *@return : Vector
     */
    public Vector listPatientPaymentByPatientId(String patient_id);
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : �Ѻ SetupControl �ҡ Hosv3     
     *@return : SetupControl
     */
    public SetupControl getSetupControl();  
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : �Ѻ VisitControl �ҡ Hosv3     
     *@return : VisitControl
     */
    public VisitControl getVisitControl();
    
      
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : �ʴ���ͼ�����     
     *@param : String hn
     *@return : ʶҹС���ʴ���
     */
    public int readPatientByHn(String hn);

    
    /**
     *@Author : jao 
     *@date : 27/02/2549
     *@see : �Ѻ HosControl v3     
     *@param : 
     *@return : HosControl
     */
    public HosControl getHosControl();

    /**
     *@Author : jao 
     *@date : 27/02/2549
     *@see : �ѹ�֡ �ä������ѧ
     *@param : 
     *@return : 
     */
    public void saveCronic(Chronic chronic);
    
    /**
     *@Author : jao 
     *@date : 27/02/2549
     *@see : �ʴ�ʶҹ��Һ�ŵ���Ţ������
     *@param : String id
     *@return : Office 
     */
    public Office listOfficeByOffId(String id);
    
    /**
     *@Author : jao 
     *@date : 27/02/2549
     *@see : ����� Icd10 �ҡ�Ţ
     *@param : String code
     *@return : GroupIcd10 
     */
    public GroupIcd10 listGroupIcd10ByIcdCode(String code);
    
    /**
     *@Author : kingland
     *@date : 24/05/2549
     *@see : �觤�� �������ҹ���Ѩ�غѹ
     *@param : -
     *@return : Employee
     */
     public Employee getCurrentEmployee();
     
     /**
     *@Author : amp
     *@date : 27/05/2549
     *@see : ���дѺ����ҡ�������ҵðҹ����
     *@param : -
     *@return : int
     */
     public int calculateNutrition(String sex,String month,String weight);
     
     /**
     *@Author : amp
     *@date : 27/05/2549
     *@see : ���дѺ����ҡ�������ҵðҹ����
     *@param : -
     *@return : HosSubject
     */
     public HosSubject readHosSubject();
     
     /**
      *@Author : pu
      *@date : 18/09/2551
      *@see : �ʴ� Dialog �׹�ѹ��觷���ͧ��âͧ hosv3
      *@param : String=�ӷ���ͧ����ʴ�, int=ʶҹз���ͧ����ʴ� �� normal=0,complete=1,waning=2,error=3
      */
     public boolean confirmBox(String str,int status);
}

