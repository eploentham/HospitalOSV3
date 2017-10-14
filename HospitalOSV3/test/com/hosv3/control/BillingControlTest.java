/*
 * BillingControlTest.java
 * JUnit based test
 *
 * Created on 15 มีนาคม 2549, 15:17 น.
 */

package com.hosv3.control;

import junit.framework.*;
import com.hospital_os.object.*;
import com.hospital_os.object.specialQuery.*;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.object.*;
import com.hosv3.utility.*;
import com.hosv3.objdb.*;
import com.hosv3.subject.*;
import com.hosv3.utility.connection.*;
import java.util.*;
import java.text.*;
import javax.swing.*;

/**
 *
 * @author tuk
 */
public class BillingControlTest extends TestCase{
    
    HosControl theHC;
    
    public BillingControlTest(String testName) {
        super(testName);
        ConnectionDBMgr c = new ConnectionDBMgr("org.postgresql.Driver","jdbc:postgresql://192.168.1.19/t_test","postgres","postgres", "1");
        theHC = new HosControl(c);  
        theHC.theHO.theServicePoint2 = theHC.theLookupControl.readServicePointById("2409144269314");
        theHC.theHO.theServicePoint = theHC.theHO.theServicePoint2;
        
        theHC.theHO.theEmployee = theHC.theLookupControl.readEmployeeById("1574372927179");
        //theHC.theHO.theEmployee2 = new Employee2(theHC.theHO.theEmployee);
        System.out.println("_________________________"+ theHC.theHO.theServicePoint2.name);
        System.out.println("_________________________"+ theHC.theHO.theEmployee.employee_id);
        //theHC.setUpdateStatus(this);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(BillingControlTest.class);
        return suite;
    }

//    /**
//     * Test of setDepControl method, of class com.hosv3.control.BillingControl.
//     */
//    public void testSetDepControl() {
//        System.out.println("testSetDepControl");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of setUpdateStatus method, of class com.hosv3.control.BillingControl.
//     */
//    public void testSetUpdateStatus() {
//        System.out.println("testSetUpdateStatus");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBillingByVisitId method, of class com.hosv3.control.BillingControl.
//     */
//    public void testListBillingByVisitId() {
//        System.out.println("testListBillingByVisitId");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of getBillRemaining method, of class com.hosv3.control.BillingControl.
//     */
//    public void testGetBillRemaining() {
//        System.out.println("testGetBillRemaining");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of calBillingPatientRemainByPatientID method, of class com.hosv3.control.BillingControl.
//     */
//    public void testCalBillingPatientRemainByPatientID() {
//        System.out.println("testCalBillingPatientRemainByPatientID");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBillingByPatientId method, of class com.hosv3.control.BillingControl.
//     */
//    public void testListBillingByPatientId() {
//        System.out.println("testListBillingByPatientId");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listSubGroupByBillingId method, of class com.hosv3.control.BillingControl.
//     */
//    public void testListSubGroupByBillingId() {
//        System.out.println("testListSubGroupByBillingId");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of billFromPatient method, of class com.hosv3.control.BillingControl.
//     */
//    public void testBillFromPatient() {
//        System.out.println("testBillFromPatient");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of paybackDept method, of class com.hosv3.control.BillingControl.
//     */
//    public void testPaybackDept() {
//        System.out.println("testPaybackDept");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of calculateBill method, of class com.hosv3.control.BillingControl.
//     */
    public void testCalculateBill() {
        System.out.println("testCalculateBill");
        
        // เป็นบักอยู่ บักแน่นอน ไม่สามารถคิดเงินได้
        theHC.theVisitControl.readVisitPatientByVn("049004125");
        this.assertNotNull(theHC.theHO.theVisit);
        Vector v = theHC.theOrderControl.listOrderItemByVid(theHC.theHO.theVisit.getObjectId());    // เก็บรายการ Order ไว้ใน Vector
        int[] row = new  int[v.size()]; 
        
        for(int i=0; i<v.size();i++){
            row[i] = i;
        }
        
        theHC.theBillingControl.calculateBill(v, row);  // เป็นบัก บักชัวร์ บักแน่นอน
       
    }
//
//    /**
//     * Test of billingInvoice method, of class com.hosv3.control.BillingControl.
//     */
//    public void testBillingInvoice() {
//        System.out.println("testBillingInvoice");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBillingInvoiceByVisitID method, of class com.hosv3.control.BillingControl.
//     */
//    public void testListBillingInvoiceByVisitID() {
//        System.out.println("testListBillingInvoiceByVisitID");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readBillingInvoiceSubgroupByVisitIDBillingIDNULL method, of class com.hosv3.control.BillingControl.
//     */
//    public void testReadBillingInvoiceSubgroupByVisitIDBillingIDNULL() {
//        System.out.println("testReadBillingInvoiceSubgroupByVisitIDBillingIDNULL");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readBillingInvoiceByVisitIDBillingIDNULL method, of class com.hosv3.control.BillingControl.
//     */
//    public void testReadBillingInvoiceByVisitIDBillingIDNULL() {
//        System.out.println("testReadBillingInvoiceByVisitIDBillingIDNULL");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readBillingInvoiceSubgroupByVisitID method, of class com.hosv3.control.BillingControl.
//     */
//    public void testReadBillingInvoiceSubgroupByVisitID() {
//        System.out.println("testReadBillingInvoiceSubgroupByVisitID");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readBillingInvoiceSubgroupByVisitIDBillingID method, of class com.hosv3.control.BillingControl.
//     */
//    public void testReadBillingInvoiceSubgroupByVisitIDBillingID() {
//        System.out.println("testReadBillingInvoiceSubgroupByVisitIDBillingID");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of calculateAllBillingInvoice method, of class com.hosv3.control.BillingControl.
//     */
//    public void testCalculateAllBillingInvoice() {
//        System.out.println("testCalculateAllBillingInvoice");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of patientPaidMoney method, of class com.hosv3.control.BillingControl.
//     */
//    public void testPatientPaidMoney() {
//        System.out.println("testPatientPaidMoney");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of intPatientPaidMoneyInReceiptItem method, of class com.hosv3.control.BillingControl.
//     */
//    public void testIntPatientPaidMoneyInReceiptItem() {
//        System.out.println("testIntPatientPaidMoneyInReceiptItem");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listReceiptItemByVisitIdBillingInvoiceItemID method, of class com.hosv3.control.BillingControl.
//     */
//    public void testListReceiptItemByVisitIdBillingInvoiceItemID() {
//        System.out.println("testListReceiptItemByVisitIdBillingInvoiceItemID");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readBillingReceiptSubgroupByVisitIDBillingInvoiceGroupItem method, of class com.hosv3.control.BillingControl.
//     */
//    public void testReadBillingReceiptSubgroupByVisitIDBillingInvoiceGroupItem() {
//        System.out.println("testReadBillingReceiptSubgroupByVisitIDBillingInvoiceGroupItem");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of cancelBillingInvoice method, of class com.hosv3.control.BillingControl.
//     */
//    public void testCancelBillingInvoice() {
//        System.out.println("testCancelBillingInvoice");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBillingInvoiceItemByVisitIdBillingInvoiceID method, of class com.hosv3.control.BillingControl.
//     */
//    public void testListBillingInvoiceItemByVisitIdBillingInvoiceID() {
//        System.out.println("testListBillingInvoiceItemByVisitIdBillingInvoiceID");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBillingInvoiceSubgroupByVisitIdBillingInvoiceID method, of class com.hosv3.control.BillingControl.
//     */
//    public void testListBillingInvoiceSubgroupByVisitIdBillingInvoiceID() {
//        System.out.println("testListBillingInvoiceSubgroupByVisitIdBillingInvoiceID");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBillingInvoiceByVisitIdBillingID method, of class com.hosv3.control.BillingControl.
//     */
//    public void testListBillingInvoiceByVisitIdBillingID() {
//        System.out.println("testListBillingInvoiceByVisitIdBillingID");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBillingInvoiceSubgroupByVisitIdBillingID method, of class com.hosv3.control.BillingControl.
//     */
//    public void testListBillingInvoiceSubgroupByVisitIdBillingID() {
//        System.out.println("testListBillingInvoiceSubgroupByVisitIdBillingID");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBillingInvoiceItemByVisitIdBillingID method, of class com.hosv3.control.BillingControl.
//     */
//    public void testListBillingInvoiceItemByVisitIdBillingID() {
//        System.out.println("testListBillingInvoiceItemByVisitIdBillingID");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listReceiptByVisitIdBillingID method, of class com.hosv3.control.BillingControl.
//     */
//    public void testListReceiptByVisitIdBillingID() {
//        System.out.println("testListReceiptByVisitIdBillingID");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listReceiptSubgroupByVisitIdReceiptID method, of class com.hosv3.control.BillingControl.
//     */
//    public void testListReceiptSubgroupByVisitIdReceiptID() {
//        System.out.println("testListReceiptSubgroupByVisitIdReceiptID");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listReceiptItemByVisitIdReceiptID method, of class com.hosv3.control.BillingControl.
//     */
//    public void testListReceiptItemByVisitIdReceiptID() {
//        System.out.println("testListReceiptItemByVisitIdReceiptID");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of updateBillingInvoiceItem method, of class com.hosv3.control.BillingControl.
//     */
//    public void testUpdateBillingInvoiceItem() {
//        System.out.println("testUpdateBillingInvoiceItem");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of updateBillingInvoiceSubgroup method, of class com.hosv3.control.BillingControl.
//     */
//    public void testUpdateBillingInvoiceSubgroup() {
//        System.out.println("testUpdateBillingInvoiceSubgroup");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of deleteBillingInvoiceItem method, of class com.hosv3.control.BillingControl.
//     */
//    public void testDeleteBillingInvoiceItem() {
//        System.out.println("testDeleteBillingInvoiceItem");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of deleteBillingInvoiceSubgroup method, of class com.hosv3.control.BillingControl.
//     */
//    public void testDeleteBillingInvoiceSubgroup() {
//        System.out.println("testDeleteBillingInvoiceSubgroup");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of cancelBill method, of class com.hosv3.control.BillingControl.
//     */
//    public void testCancelBill() {
//        System.out.println("testCancelBill");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of convertDataForPrintBilling method, of class com.hosv3.control.BillingControl.
//     */
//    public void testConvertDataForPrintBilling() {
//        System.out.println("testConvertDataForPrintBilling");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of dataReceiptItemForPrintBilling method, of class com.hosv3.control.BillingControl.
//     */
//    public void testDataReceiptItemForPrintBilling() {
//        System.out.println("testDataReceiptItemForPrintBilling");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of dataForPrintBilling method, of class com.hosv3.control.BillingControl.
//     */
//    public void testDataForPrintBilling() {
//        System.out.println("testDataForPrintBilling");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBillingInvoiceItemByVisitIdBillingGroupItemId method, of class com.hosv3.control.BillingControl.
//     */
//    public void testListBillingInvoiceItemByVisitIdBillingGroupItemId() {
//        System.out.println("testListBillingInvoiceItemByVisitIdBillingGroupItemId");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBillingInvoiceItemByVisitIdBillingInvoiceIDBillingGroupItemId method, of class com.hosv3.control.BillingControl.
//     */
//    public void testListBillingInvoiceItemByVisitIdBillingInvoiceIDBillingGroupItemId() {
//        System.out.println("testListBillingInvoiceItemByVisitIdBillingInvoiceIDBillingGroupItemId");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBillingInvoiceSubgroupByPk method, of class com.hosv3.control.BillingControl.
//     */
//    public void testListBillingInvoiceSubgroupByPk() {
//        System.out.println("testListBillingInvoiceSubgroupByPk");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listReceiptByVisitIdReceiptIdOrderBy method, of class com.hosv3.control.BillingControl.
//     */
//    public void testListReceiptByVisitIdReceiptIdOrderBy() {
//        System.out.println("testListReceiptByVisitIdReceiptIdOrderBy");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listReceiptSubgroupByVisitIdBillingGroupItem method, of class com.hosv3.control.BillingControl.
//     */
//    public void testListReceiptSubgroupByVisitIdBillingGroupItem() {
//        System.out.println("testListReceiptSubgroupByVisitIdBillingGroupItem");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBillingByPatientID method, of class com.hosv3.control.BillingControl.
//     */
//    public void testListBillingByPatientID() {
//        System.out.println("testListBillingByPatientID");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of countBillingInvoiceNotCompleteByVisitID method, of class com.hosv3.control.BillingControl.
//     */
//    public void testCountBillingInvoiceNotCompleteByVisitID() {
//        System.out.println("testCountBillingInvoiceNotCompleteByVisitID");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of calBil method, of class com.hosv3.control.BillingControl.
//     */
//    public void testCalBil() {
//        System.out.println("testCalBil");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of calBillingVisit method, of class com.hosv3.control.BillingControl.
//     */
//    public void testCalBillingVisit() {
//        System.out.println("testCalBillingVisit");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of calBillingVisitCont method, of class com.hosv3.control.BillingControl.
//     */
//    public void testCalBillingVisitCont() {
//        System.out.println("testCalBillingVisitCont");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readSpecialContractAdjustByCGaCT method, of class com.hosv3.control.BillingControl.
//     */
//    public void testReadSpecialContractAdjustByCGaCT() {
//        System.out.println("testReadSpecialContractAdjustByCGaCT");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        fail("The test case is empty.");
//    }
//    
}
