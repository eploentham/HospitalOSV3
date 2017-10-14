/*
 * PatientControlTest.java
 * JUnit based test
 *
 * Created on 13 �չҤ� 2549, 17:34 �.
 */

package com.hosv3.control;

import junit.framework.*;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.object.*;
import com.hospital_os.objdb.*;
import com.hosv3.utility.*;
import com.hosv3.utility.connection.*;
import com.hosv3.subject.*;
import com.hosv3.object.*;
import com.hosv3.objdb.*;
import java.util.*;
import java.text.*;
import javax.swing.*;

/**
 *
 * @author Administrator
 */
public class PatientControlTest extends TestCase implements UpdateStatus{
    
    HosControl theHC;

    public PatientControlTest(String testName) {
        super(testName);
        ConnectionDBMgr c = new ConnectionDBMgr("org.postgresql.Driver","jdbc:postgresql://192.168.1.19/t_test","postgres","postgres", "1");
        theHC = new HosControl(c);  
        theHC.theHO.theServicePoint2 = theHC.theLookupControl.readServicePointById("2409144269314");
        theHC.theHO.theServicePoint = theHC.theHO.theServicePoint2;
        
        theHC.theHO.theEmployee = theHC.theLookupControl.readEmployeeById("1574372927179");
        //theHC.theHO.theEmployee2 = new Employee2(theHC.theHO.theEmployee);
        System.out.println("_________________________"+ theHC.theHO.theServicePoint2.name);
        System.out.println("_________________________"+ theHC.theHO.theEmployee.employee_id);
        theHC.setUpdateStatus(this);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(PatientControlTest.class);
        return suite;
    }
    
//    /**
//     * Test of setUpdateStatus method, of class com.hosv3.control.PatientControl.
//     */
//    public void testSetUpdateStatus() {
//        System.out.println("testSetUpdateStatus");
//        
//        // TODO add your test code below by replacing the default call to fail.
////        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of setDepControl method, of class com.hosv3.control.PatientControl.
//     */
//    public void testSetDepControl() {
//        System.out.println("testSetDepControl");
//        
//        // TODO add your test code below by replacing the default call to fail.
////        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of resetPatient method, of class com.hosv3.control.PatientControl.
//        ������������͡�ҡ˹�Ҩ�
//     */
    public void testResetPatient() {
        System.out.println("testResetPatient");
        theHC.thePatientControl.readPatientByHn("2");
        theHC.thePatientControl.resetPatient();
        this.assertNull(theHC.theHO.thePatient);         // �������������
    
    }
//
//    /**
//     * Test of addItemDrugAllergy method, of class com.hosv3.control.PatientControl.
//     */
//    public void testAddItemDrugAllergy() {
//        System.out.println("testAddItemDrugAllergy");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readPatientByPatientID method, of class com.hosv3.control.PatientControl.
//     */
    public void testReadPatientByPatientID() {
        System.out.println("testReadPatientByPatientID");
        theHC.thePatientControl.readPatientByPatientID("206111880000000001");
        this.assertNotNull(theHC.theHO.thePatient);         // ���������ͧ�ҡ�� PID ���ç�Ѻ������
        System.out.println("______________����ö read Patient by Patient ID ��__________________");
        
        theHC.thePatientControl.readPatientByPatientID("206000000000000001");
        this.assertNull(theHC.theHO.thePatient);            // �������ö������ ���ͧ�ҡ�����Ţ PID ����͡�����
        System.out.println("______________�������ö read Patient ��__________________");
  
    }
//
//    /**
//     * Test of checkPatientPid method, of class com.hosv3.control.PatientControl.
//     */
//    public void testCheckPatientPid() {
//        System.out.println("testCheckPatientPid");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of intCheckPatientPid method, of class com.hosv3.control.PatientControl.
//     */
//    public void testIntCheckPatientPid() {
//        System.out.println("testIntCheckPatientPid");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listAppointmentByDateSP method, of class com.hosv3.control.PatientControl.
//     */
//    public void testListAppointmentByDateSP() {
//        System.out.println("testListAppointmentByDateSP");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBorrowFilmXrayByDate method, of class com.hosv3.control.PatientControl.
//     */
//    public void testListBorrowFilmXrayByDate() {
//        System.out.println("testListBorrowFilmXrayByDate");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listPatientByHn method, of class com.hosv3.control.PatientControl.
//     */
//    public void testListPatientByHn() {
//        System.out.println("testListPatientByHn");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listPatientByName method, of class com.hosv3.control.PatientControl.
//     */
//    public void testListPatientByName() {
//        System.out.println("testListPatientByName");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listPatientByPID method, of class com.hosv3.control.PatientControl.
//     */
//    public void testListPatientByPID() {
//        System.out.println("testListPatientByPID");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listPatientByXn method, of class com.hosv3.control.PatientControl.
//     */
//    public void testListPatientByXn() {
//        System.out.println("testListPatientByXn");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listPatientPaymentByPatientId method, of class com.hosv3.control.PatientControl.
//     */
//    public void testListPatientPaymentByPatientId() {
//        System.out.println("testListPatientPaymentByPatientId");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readPatientByPatientIdRet method, of class com.hosv3.control.PatientControl.
//     */
//    public void testReadPatientByPatientIdRet() {
//        System.out.println("testReadPatientByPatientIdRet");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of saveAppointment method, of class com.hosv3.control.PatientControl.
//     */
    public void testSaveAppointment() {
        System.out.println("_____________________________________________________");
        System.out.println("testSaveAppointment");
        
        // �ѹ�֡��ùѴ���������͡������ ��С�͡�����ŹѴ������ �ѹ������ҷ��Ѵ
        theHC.thePatientControl.readPatientByHn("25");
        this.assertNotNull(theHC.theHO.thePatient);     // ���͡�����������
        Appointment2 a = new Appointment2();
        a.patient_id = theHC.theHO.thePatient.getObjectId(); 
        a.aptype = "f/u";                               // �Ѵ������
        a.appoint_date = "2549-03-26";
        a.appoint_time = "10:00";
        a.clinic_code = "2409144269314";
        theHC.thePatientControl.saveAppointment(a, this);
        this.assertNotNull(a.getObjectId());
        
        Vector vapp = new Vector();
        vapp.add(a);
        
        String vnold = theHC.theVisitControl.readMaxVnByPatientId(a.patient_id);
        System.out.println("VN old : " + vnold);
        System.out.println(vapp.size());
//      this.assertNull(theHC.theHO.theVisit);
        theHC.theVisitControl.visitFromVAppointment(vapp, this);
        String vnnew = theHC.theVisitControl.readMaxVnByPatientId(a.patient_id);
        System.out.println("VN new : " + vnnew);
        
        System.out.println("-------------- ���ͺ���������ùѴ -------------------");
        System.out.println(str_status);
        theHC.thePatientControl.deleteAppointment(a);
                
        // �ѹ�֡��ùѴ���� ������͡�Ѵ������
        theHC.thePatientControl.readPatientByHn("125");
        this.assertNotNull(theHC.theHO.thePatient);
        a = new Appointment2();
        a.patient_id = theHC.theHO.thePatient.getObjectId();
        a.aptype = "";
        a.appoint_date = "2549-04-01";
        a.appoint_time = "09:00";
        a.clinic_code = "2409144269314";
        theHC.thePatientControl.saveAppointment(a, this);
        this.assertNull(a.getObjectId());
        theHC.thePatientControl.deleteAppointment(a);
        
//        
//        // �ѹ�֡��ùѴ���� ������͡�ѹ���Ѵ
        theHC.thePatientControl.readPatientByHn("30");
        this.assertNotNull(theHC.theHO.thePatient);
        a = new Appointment2();
        a.patient_id = theHC.theHO.thePatient.getObjectId();
        a.aptype = "���ҡ��";
        a.appoint_date = "";
        a.appoint_time = "10:00";
        a.clinic_code = "2409144269314";
        theHC.thePatientControl.saveAppointment(a, this);
        this.assertNull(a.getObjectId());
        theHC.thePatientControl.deleteAppointment(a);
        
        // �ѹ�֡��ùѴ���� ������͡���ҹѴ����
        theHC.thePatientControl.readPatientByHn("30");
        this.assertNotNull(theHC.theHO.thePatient);
        a = new Appointment2();
        a.patient_id = theHC.theHO.thePatient.getObjectId();
        a.aptype = "���ҡ��";
        a.appoint_date = "2549-03-31";
        a.appoint_time = "";
        a.clinic_code = "2409144269314";
        theHC.thePatientControl.saveAppointment(a, this);
        this.assertNull(a.getObjectId());
        theHC.thePatientControl.deleteAppointment(a);
        
        // �ѹ�֡��ùѴ���� �����͡�ѹ�֡��ùѴ�繪�ǧ
        vapp = new Vector();
        int s = 5;
        vapp.setSize(s);
        System.out.println("Size date = " + vapp.size());
   
        for(int i=0;i<vapp.size();i++){
            a = (Appointment2)vapp.get(i);
            theHC.thePatientControl.readPatientByHn("60");
            this.assertNotNull(theHC.theHO.thePatient);
            a = new Appointment2();
            a.patient_id = theHC.theHO.thePatient.getObjectId();
            a.aptype = "f/u";
            a.appoint_time = "10:00";
            a.clinic_code = "2409144269314";
            a.appoint_date = "2549-03-20";
            a.appoint_date = a.appoint_date + i;
            theHC.thePatientControl.saveAppointment(a, this);
            this.assertNotNull(a.getObjectId());
            theHC.thePatientControl.deleteAppointment(a);
        }
  
    }
//
//    /**
//     * Test of closeAppointment method, of class com.hosv3.control.PatientControl.
//     */
//    public void testCloseAppointment() {
//        System.out.println("testCloseAppointment");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of downPriorityPatientPayment method, of class com.hosv3.control.PatientControl.
//     */
//    public void testDownPriorityPatientPayment() {
//        System.out.println("testDownPriorityPatientPayment");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of upPriorityPatientPayment method, of class com.hosv3.control.PatientControl.
//     */
//    public void testUpPriorityPatientPayment() {
//        System.out.println("testUpPriorityPatientPayment");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of savePatientPayment method, of class com.hosv3.control.PatientControl.
//     */
//    public void testSavePatientPayment() {
//        System.out.println("testSavePatientPayment");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");        
//    }
//
//    /**
//     * Test of deletePatientPayment method, of class com.hosv3.control.PatientControl.
//     */
//    public void testDeletePatientPayment() {
//        System.out.println("testDeletePatientPayment");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of savePatientXN method, of class com.hosv3.control.PatientControl.
//     */
//    public void testSavePatientXN() {
//        System.out.println("testSavePatientXN");
//        ������� usecase savePatient 
//    }
//
//    /**
//     * Test of listOldPaymentVisitBypatientID method, of class com.hosv3.control.PatientControl.
//     */
//    public void testListOldPaymentVisitBypatientID() {
//        System.out.println("testListOldPaymentVisitBypatientID");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of savePatientAllergy method, of class com.hosv3.control.PatientControl.
//     */
//    public void testSavePatientAllergy() {
//        System.out.println("testSavePatientAllergy");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of deletePatientAllergy method, of class com.hosv3.control.PatientControl.
//     */
//    public void testDeletePatientAllergy() {
//        System.out.println("testDeletePatientAllergy");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of deleteAppointmentOrder method, of class com.hosv3.control.PatientControl.
//     */
//    public void testDeleteAppointmentOrder() {
//        System.out.println("testDeleteAppointmentOrder");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listAppointmentOrder method, of class com.hosv3.control.PatientControl.
//     */
//    public void testListAppointmentOrder() {
//        System.out.println("testListAppointmentOrder");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of deleteAppointment method, of class com.hosv3.control.PatientControl.
//     */
//    public void testDeleteAppointment() {
//        System.out.println("testDeleteAppointment");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readAppointmentByPK method, of class com.hosv3.control.PatientControl.
//     */
//    public void testReadAppointmentByPK() {
//        System.out.println("testReadAppointmentByPK");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of deletePatient method, of class com.hosv3.control.PatientControl.
//     */
    public void testDeletePatient() {
        System.out.println("________________________________________________________________________________");
        System.out.println("testDeletePatient");
        // ź�����ż����·�����ջ���ѵԡ������Ѻ��ԡ�� //////////////////////
        theHC.thePatientControl.readPatientByHn("125");
        theHC.thePatientControl.deletePatient(theHC.theHO.thePatient);
        this.assertNotNull(theHC.theHO.thePatient);
        this.assertEquals("1",theHC.theHO.thePatient.active);
        System.out.println(str_status);
        
        // ź�����ż����·��������ջ���ѵԡ������Ѻ��ԡ�� /////////////////////
        Patient p = new Patient();
        p.fname = "���ͺź";
        p.lname = "Patient";
        String age = "13";
        theHC.thePatientControl.savePatient(p,age);
        this.assertNotNull(p.getObjectId());
        theHC.thePatientControl.deletePatient(p);
        this.assertNotNull(p.getObjectId());
        this.assertEquals("0",p.active);
               
        // ź�����ż����·����ѧ����㹡�кǹ��� ///////////////////////////
        p = new Patient();
        p.fname = "dddd";
        p.lname = "jfjfj";
        theHC.thePatientControl.savePatient(p, age);
        this.assertNotNull(p.getObjectId());
        
        Visit v = new Visit();
        v.patient_id = p.getObjectId();
        Payment pa = new Payment();
        pa.plan_kid = Plan2.SELF_PAY;
        QueueVisit q = new QueueVisit();
        Vector vp = new Vector();
        vp.add(pa);                                    // �� Vector ��������㹡�� visit 1 ���駨�����ö���͡�Է�ԡ���ѡ���������Է��
        this.assertNull(theHC.theHO.theVisit);
        theHC.theVisitControl.visitPatient(v, vp, q);
        this.assertNotNull(theHC.theHO.theVisit);
        theHC.thePatientControl.deletePatient(p);
        this.assertNotNull(p.getObjectId());
      
    }
//
    /**
     * Test of savePatient method, of class com.hosv3.control.PatientControl.
     */
    public void testSavePatient() {
        System.out.println("___________________________________________________________________________________");        
        System.out.println("testSavePatient");
        
        //�ѹ�֡������Ե�ͧ��/////////////////////////////////////////
        Patient p = new Patient();
        p.fname = "���ͺ";
        p.lname = "aa";
        String age = "12";
        theHC.thePatientControl.savePatient(p,age);
        this.assertNotNull(p.getObjectId());
        this.assertEquals("1",p.active);
        this.assertNotSame("",p.record_date_time);        
        theHC.thePatientControl.deletePatient(p);
        
        //�ѹ�֡�·������繤����ҧ��ͧ�����/////////////////////////////////////////
        p = new Patient();
        p.fname = " ";
        p.lname = "aa";
        theHC.thePatientControl.savePatient(p,age);
        System.out.println(str_status);
        this.assertNull(p.getObjectId());
        theHC.thePatientControl.deletePatient(p);
        // Test �����Դ Exception ��Ҩ�������礷�� Control ��˹�Ҩ� GUI ������
        
        //�ѹ�֡�·����ʡ���繤����ҧ��ͧ�����/////////////////////////////////////////
        p = new Patient();
        p.fname = " �";
        p.lname = " ";
        theHC.thePatientControl.savePatient(p,age);
        System.out.println(str_status);
        this.assertNull(p.getObjectId());        
        theHC.thePatientControl.deletePatient(p);
        // Test �����Դ Exception ��Ҩ�������礷�� Control ��˹�Ҩ� GUI ������
        
        //�Ţ�ѵû�ЪҪ���ӵ�ͧ�����/////////////////////////////////////////
        p = new Patient();
        p.fname = " �";
        p.lname = "� ";
        p.pid = "1234567890124";
        theHC.thePatientControl.savePatient(p,age);
        this.assertNotNull(p.getObjectId());        //��ͧ�� notnull ��ͺѹ�֡��
        Patient p1 = new Patient();
        p1.fname = " �";
        p1.lname = "� ";
        p1.pid = "1234567890124";
        theHC.thePatientControl.savePatient(p1,age);
        this.assertNull(p1.getObjectId());        //��ͧ�� null ��ͺѹ�֡�����
        theHC.thePatientControl.deletePatient(p);
        theHC.thePatientControl.deletePatient(p1);
        
        //�ѹ�֡�·�������Ţ XN ��ӵ�ͧ�����/////////////////////////////////////////
        System.out.println("------- <<<<<<  ���ͺ XN ��� >>>>>> -------");
        p = new Patient();
        p.fname = "��";
        p.lname = "�ѡ��";
        p.xn = "X49000200";
        theHC.thePatientControl.savePatient(p,age);
        this.assertNotNull(p.getObjectId());       // ��ͧ�� notnull ��ͺѹ�֡�� 
        Patient p2 = new Patient();
        p2.fname = "tuk";
        p2.lname = "tuk";
        p2.xn = "X49000200";
        theHC.thePatientControl.savePatient(p2,age);
        this.assertNull(p2.getObjectId());          // ��ͧ�� null �ѹ�֡����� xn ���
        theHC.thePatientControl.deletePatient(p);
        theHC.thePatientControl.deletePatient(p2);
    }

    /**
     * Test of readPatientByHn method, of class com.hosv3.control.PatientControl.
     */
    public void testReadPatientByHn() {
        System.out.println("testReadPatientByHn");
        theHC.thePatientControl.readPatientByHn("1");
        this.assertNotNull(theHC.theHO.thePatient);
        System.out.println("______________����ö read Patient ��__________________");
        theHC.thePatientControl.readPatientByHn("50000");
        this.assertNull(theHC.theHO.thePatient);
        System.out.println("______________Can't read Patient______________________");
    }
//
//    /**
//     * Test of savePatientHistory method, of class com.hosv3.control.PatientControl.
//     */
//    public void testSavePatientHistory() {
//        System.out.println("testSavePatientHistory");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of deleteBorrowFilmXray method, of class com.hosv3.control.PatientControl.
//     */
//    public void testDeleteBorrowFilmXray() {
//        System.out.println("testDeleteBorrowFilmXray");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of saveBorrowFilmXray method, of class com.hosv3.control.PatientControl.
//     */
//    public void testSaveBorrowFilmXray() {
//        System.out.println("testSaveBorrowFilmXray");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of deletePersonalDisease method, of class com.hosv3.control.PatientControl.
//     */
//    public void testDeletePersonalDisease() {
//        System.out.println("testDeletePersonalDisease");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readPatientByHnToBorrowFilm method, of class com.hosv3.control.PatientControl.
//     */
//    public void testReadPatientByHnToBorrowFilm() {
//        System.out.println("testReadPatientByHnToBorrowFilm");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readPatientByXnToBorrowFilm method, of class com.hosv3.control.PatientControl.
//     */
//    public void testReadPatientByXnToBorrowFilm() {
//        System.out.println("testReadPatientByXnToBorrowFilm");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readBorrowFilmXrayByPK method, of class com.hosv3.control.PatientControl.
//     */
//    public void testReadBorrowFilmXrayByPK() {
//        System.out.println("testReadBorrowFilmXrayByPK");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readPatientByHnToBorrowOpd method, of class com.hosv3.control.PatientControl.
//     */
//    public void testReadPatientByHnToBorrowOpd() {
//        System.out.println("testReadPatientByHnToBorrowOpd");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readBorrowOpdCardByPK method, of class com.hosv3.control.PatientControl.
//     */
//    public void testReadBorrowOpdCardByPK() {
//        System.out.println("testReadBorrowOpdCardByPK");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of deleteBorrowOpdCard method, of class com.hosv3.control.PatientControl.
//     */
//    public void testDeleteBorrowOpdCard() {
//        System.out.println("testDeleteBorrowOpdCard");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of saveBorrowOpdCard method, of class com.hosv3.control.PatientControl.
//     */
//    public void testSaveBorrowOpdCard() {
//        System.out.println("testSaveBorrowOpdCard");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBorrowOpdCardByDate method, of class com.hosv3.control.PatientControl.
//     */
//    public void testListBorrowOpdCardByDate() {
//        System.out.println("testListBorrowOpdCardByDate");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listWaitAppointment method, of class com.hosv3.control.PatientControl.
//     */
//    public void testListWaitAppointment() {
//        System.out.println("testListWaitAppointment");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }

    public boolean confirmBox(String str, int status) {
        return true;
    }

    public JFrame getJFrame() {
        return new JFrame();
    }
    String str_status;
    public void setStatus(String str, int status) {
        str_status = str;
    }
     public static void main(java.lang.String[] argList) {
        junit.textui.TestRunner.run(suite());
    }
}
