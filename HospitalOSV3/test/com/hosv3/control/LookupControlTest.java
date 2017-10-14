/*
 * LookupControlTest.java
 * JUnit based test
 *
 * Created on 13 ¡’π“§¡ 2549, 17:47 π.
 */

package com.hosv3.control;

import junit.framework.*;
import javax.swing.Timer;
import javax.swing.*;
import com.hosv3.objdb.*;
import com.hosv3.subject.*;
import com.hosv3.object.*;
import com.hosv3.utility.*;
import com.hosv3.utility.connection.*;
import com.hospital_os.objdb.*;
import com.hospital_os.object.*;
import com.hospital_os.utility.ComboFix;
import com.hospital_os.utility.Gutil;
import com.hospital_os.usecase.connection.ConnectionInf;
import java.util.Vector;
import java.util.StringTokenizer;

/**
 *
 * @author Administrator
 */
public class LookupControlTest extends TestCase {

    ConnectionDBMgr c = new ConnectionDBMgr("org.postgresql.Driver","jdbc:postgresql://192.168.1.19/t_test","postgres","postgres", "1");
    HosControl theHC = new HosControl(c);

    public LookupControlTest(String testName) {
        super(testName);
    }
    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        System.setProperty("Debug","0");
        TestSuite suite = new TestSuite(LookupControlTest.class);
        return suite;
    }

//    /**
//     * Test of setUpdateStatus method, of class com.hosv3.control.LookupControl.
//     */
//    public void testSetUpdateStatus() {
//        System.out.println("testSetUpdateStatus");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readOption method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadOption() {
//        System.out.println("testReadOption");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listOptionAll method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListOptionAll() {
//        System.out.println("testListOptionAll");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }

    /**
     * Test of readAddressById method, of class com.hosv3.control.LookupControl.
     */
    public void testReadAddressById() {
        System.out.println("testReadAddressById");
        Patient pt = new Patient();
        pt.ampur = "830100";
        pt.changwat = "830000";
        pt.tambon = "830101";
        pt.house = "1";
        pt.village = "1";
        pt.road = "¡ß§≈ª√–™“";
        String output = theHC.theLookupControl.readPatientAddress(pt);
        this.assertEquals("1 À¡ŸË 1 ∂ππ ¡ß§≈ª√–™“ µ.µ≈“¥„À≠Ë Õ.‡¡◊Õß¿Ÿ‡°Áµ ®.¿Ÿ‡°Áµ",output);
    }

    /**
     * Test of readAddressString method, of class com.hosv3.control.LookupControl.
     */
//    public void testReadAddressString() {
//        System.out.println("testReadAddressString");
//        String output = theHC.theLookupControl.readAddressString(null, "830000");
//        this.assertEquals("¿Ÿ‡°Áµ",output);
//        output = theHC.theLookupControl.readAddressString(null, "830100");
//        this.assertEquals("‡¡◊Õß¿Ÿ‡°Áµ",output);
//        output = theHC.theLookupControl.readAddressString(null, "830101");
//        System.out.println(output);
//        this.assertEquals("µ≈“¥„À≠Ë",output);
//    }

    /**
     * Test of readMarryStatusById method, of class com.hosv3.control.LookupControl.
     */
    public void testReadMarryStatusById() {
        System.out.println("testReadMarryStatusById");
        MarryStatus ms = theHC.theLookupControl.readMarryStatusById("1");
        this.assertEquals("‚ ¥",ms.description);
    }

    /**
     * Test of readReligionById method, of class com.hosv3.control.LookupControl.
     */
    public void testReadReligionById() {
        System.out.println("testReadReligionById");
        Religion ms = theHC.theLookupControl.readReligionById("1");
        this.assertEquals("æÿ∑∏",ms.description);
    }

    /**
     * Test of readOccupatById method, of class com.hosv3.control.LookupControl.
     */
    public void testReadOccupatById() {
        System.out.println("testReadOccupatById");
        Occupat occ = theHC.theLookupControl.readOccupatById("000");
        this.assertEquals("‰¡Ë¡’Õ“™’æ",occ.description);
    }

//    /**
//     * Test of readNationById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadNationById() {
//        System.out.println("testReadNationById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readPrefixByName method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadPrefixByName() {
//        System.out.println("testReadPrefixByName");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readRelationByName method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadRelationByName() {
//        System.out.println("testReadRelationByName");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readRelationById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadRelationById() {
//        System.out.println("testReadRelationById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readPlanString method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadPlanString() {
//        System.out.println("testReadPlanString");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readPrefixString method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadPrefixString() {
//        System.out.println("testReadPrefixString");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readPrefixById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadPrefixById() {
//        System.out.println("testReadPrefixById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readDrugInstructionById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadDrugInstructionById() {
//        System.out.println("testReadDrugInstructionById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readDrugInstructionByCode method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadDrugInstructionByCode() {
//        System.out.println("testReadDrugInstructionByCode");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readUomById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadUomById() {
//        System.out.println("testReadUomById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readDrugFrequencyById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadDrugFrequencyById() {
//        System.out.println("testReadDrugFrequencyById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readDrugFrequencyByCode method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadDrugFrequencyByCode() {
//        System.out.println("testReadDrugFrequencyByCode");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readContractById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadContractById() {
//        System.out.println("testReadContractById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readBloodGroupById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadBloodGroupById() {
//        System.out.println("testReadBloodGroupById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listAddressCGW method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListAddressCGW() {
//        System.out.println("testListAddressCGW");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listAllXrayLeteral method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListAllXrayLeteral() {
//        System.out.println("testListAllXrayLeteral");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listAllXrayPosition method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListAllXrayPosition() {
//        System.out.println("testListAllXrayPosition");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listAddressAmp method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListAddressAmp() {
//        System.out.println("testListAddressAmp");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listAddressTmp method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListAddressTmp() {
//        System.out.println("testListAddressTmp");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBillingGroup method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListBillingGroup() {
//        System.out.println("testListBillingGroup");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listGActionAuthByAid method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListGActionAuthByAid() {
//        System.out.println("testListGActionAuthByAid");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listAuthentication method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListAuthentication() {
//        System.out.println("testListAuthentication");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listAppointmentStatus method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListAppointmentStatus() {
//        System.out.println("testListAppointmentStatus");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listLevel method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListLevel() {
//        System.out.println("testListLevel");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listServicePoint method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListServicePoint() {
//        System.out.println("testListServicePoint");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listServicePointwithOutXL method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListServicePointwithOutXL() {
//        System.out.println("testListServicePointwithOutXL");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listServiceSubType method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListServiceSubType() {
//        System.out.println("testListServiceSubType");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listServiceType method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListServiceType() {
//        System.out.println("testListServiceType");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listCategoryGroup method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListCategoryGroup() {
//        System.out.println("testListCategoryGroup");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listCategoryGroupItem method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListCategoryGroupItem() {
//        System.out.println("testListCategoryGroupItem");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listCategoryGroupItemDrugAndSupply method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListCategoryGroupItemDrugAndSupply() {
//        System.out.println("testListCategoryGroupItemDrugAndSupply");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of getSChangwat method, of class com.hosv3.control.LookupControl.
//     */
//    public void testGetSChangwat() {
//        System.out.println("testGetSChangwat");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of getSAmphur method, of class com.hosv3.control.LookupControl.
//     */
//    public void testGetSAmphur() {
//        System.out.println("testGetSAmphur");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBillingGroupItem method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListBillingGroupItem() {
//        System.out.println("testListBillingGroupItem");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listDrugFrequency method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListDrugFrequency() {
//        System.out.println("testListDrugFrequency");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listDrugInstruction method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListDrugInstruction() {
//        System.out.println("testListDrugInstruction");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listUom method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListUom() {
//        System.out.println("testListUom");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBlood method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListBlood() {
//        System.out.println("testListBlood");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listFstatus method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListFstatus() {
//        System.out.println("testListFstatus");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listLabor method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListLabor() {
//        System.out.println("testListLabor");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listMarriage method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListMarriage() {
//        System.out.println("testListMarriage");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listNation method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListNation() {
//        System.out.println("testListNation");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listOccupation method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListOccupation() {
//        System.out.println("testListOccupation");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listOccupationByCodeName method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListOccupationByCodeName() {
//        System.out.println("testListOccupationByCodeName");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listPrefix method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListPrefix() {
//        System.out.println("testListPrefix");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listChangwat method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListChangwat() {
//        System.out.println("testListChangwat");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listAmpur method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListAmpur() {
//        System.out.println("testListAmpur");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listTambon method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListTambon() {
//        System.out.println("testListTambon");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listRace method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListRace() {
//        System.out.println("testListRace");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listRelation method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListRelation() {
//        System.out.println("testListRelation");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listReligion method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListReligion() {
//        System.out.println("testListReligion");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listLabResultGroup method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListLabResultGroup() {
//        System.out.println("testListLabResultGroup");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listGender method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListGender() {
//        System.out.println("testListGender");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listTypeArea method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListTypeArea() {
//        System.out.println("testListTypeArea");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listPlan method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListPlan() {
//        System.out.println("testListPlan");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listContractAdjustByContractId method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListContractAdjustByContractId() {
//        System.out.println("testListContractAdjustByContractId");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readEmployeeById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadEmployeeById() {
//        System.out.println("testReadEmployeeById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readOrderItemStatus method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadOrderItemStatus() {
//        System.out.println("testReadOrderItemStatus");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readEmployeeByUsername method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadEmployeeByUsername() {
//        System.out.println("testReadEmployeeByUsername");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listOfficeInCup method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListOfficeInCup() {
//        System.out.println("testListOfficeInCup");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readOfficeInCupByCode method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadOfficeInCupByCode() {
//        System.out.println("testReadOfficeInCupByCode");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readHospitalByCode method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadHospitalByCode() {
//        System.out.println("testReadHospitalByCode");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listClinic method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListClinic() {
//        System.out.println("testListClinic");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listDrugDosePrint method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListDrugDosePrint() {
//        System.out.println("testListDrugDosePrint");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listGAction method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListGAction() {
//        System.out.println("testListGAction");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listNutritionTypeCombofix method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListNutritionTypeCombofix() {
//        System.out.println("testListNutritionTypeCombofix");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listNutritionType2 method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListNutritionType2() {
//        System.out.println("testListNutritionType2");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listDischarge method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListDischarge() {
//        System.out.println("testListDischarge");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBGroup method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListBGroup() {
//        System.out.println("testListBGroup");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listWaterType method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListWaterType() {
//        System.out.println("testListWaterType");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listGarbage method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListGarbage() {
//        System.out.println("testListGarbage");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listHCharac method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListHCharac() {
//        System.out.println("testListHCharac");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listComCharac method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListComCharac() {
//        System.out.println("testListComCharac");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listPtStatusType method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListPtStatusType() {
//        System.out.println("testListPtStatusType");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listPtMobile method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListPtMobile() {
//        System.out.println("testListPtMobile");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listAccuseType method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListAccuseType() {
//        System.out.println("testListAccuseType");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listAccproType method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListAccproType() {
//        System.out.println("testListAccproType");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listAccinoutType method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListAccinoutType() {
//        System.out.println("testListAccinoutType");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listAccrdType method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListAccrdType() {
//        System.out.println("testListAccrdType");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listTypeDish method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListTypeDish() {
//        System.out.println("testListTypeDish");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listFpType method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListFpType() {
//        System.out.println("testListFpType");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listNofp method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListNofp() {
//        System.out.println("testListNofp");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listResultStatus method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListResultStatus() {
//        System.out.println("testListResultStatus");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBType method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListBType() {
//        System.out.println("testListBType");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBDoctor method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListBDoctor() {
//        System.out.println("testListBDoctor");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listHighRisk method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListHighRisk() {
//        System.out.println("testListHighRisk");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listDayTimeType method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListDayTimeType() {
//        System.out.println("testListDayTimeType");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listRule method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListRule() {
//        System.out.println("testListRule");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listDoctor method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListDoctor() {
//        System.out.println("testListDoctor");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listDoctorSP method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListDoctorSP() {
//        System.out.println("testListDoctorSP");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listEmployeeBySpid method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListEmployeeBySpid() {
//        System.out.println("testListEmployeeBySpid");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listNurse method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListNurse() {
//        System.out.println("testListNurse");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listDxType method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListDxType() {
//        System.out.println("testListDxType");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listOffice method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListOffice() {
//        System.out.println("testListOffice");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listContract method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListContract() {
//        System.out.println("testListContract");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readPlanById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadPlanById() {
//        System.out.println("testReadPlanById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readDoseText method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadDoseText() {
//        System.out.println("testReadDoseText");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readShortDoseText method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadShortDoseText() {
//        System.out.println("testReadShortDoseText");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readDrugDosePrintById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadDrugDosePrintById() {
//        System.out.println("testReadDrugDosePrintById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readDrugDosePrintByValue method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadDrugDosePrintByValue() {
//        System.out.println("testReadDrugDosePrintByValue");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readDischargeTypeById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadDischargeTypeById() {
//        System.out.println("testReadDischargeTypeById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readDischargeStatusById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadDischargeStatusById() {
//        System.out.println("testReadDischargeStatusById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readWardById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadWardById() {
//        System.out.println("testReadWardById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readClinicById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadClinicById() {
//        System.out.println("testReadClinicById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readBillingGroupItemById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadBillingGroupItemById() {
//        System.out.println("testReadBillingGroupItemById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readVisitStatusById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadVisitStatusById() {
//        System.out.println("testReadVisitStatusById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readOptypeById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadOptypeById() {
//        System.out.println("testReadOptypeById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readDxtypeById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadDxtypeById() {
//        System.out.println("testReadDxtypeById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of comboBoxDxType method, of class com.hosv3.control.LookupControl.
//     */
//    public void testComboBoxDxType() {
//        System.out.println("testComboBoxDxType");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listLocalType method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListLocalType() {
//        System.out.println("testListLocalType");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listLabSet method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListLabSet() {
//        System.out.println("testListLabSet");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listEducate method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListEducate() {
//        System.out.println("testListEducate");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listVisitStatus method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListVisitStatus() {
//        System.out.println("testListVisitStatus");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listAnswer method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListAnswer() {
//        System.out.println("testListAnswer");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listAncSection method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListAncSection() {
//        System.out.println("testListAncSection");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listOpdFormat method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListOpdFormat() {
//        System.out.println("testListOpdFormat");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listPayer method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListPayer() {
//        System.out.println("testListPayer");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listContractMethodReq method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListContractMethodReq() {
//        System.out.println("testListContractMethodReq");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listWard method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListWard() {
//        System.out.println("testListWard");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readSite method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadSite() {
//        System.out.println("testReadSite");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of getTextCurrentDate method, of class com.hosv3.control.LookupControl.
//     */
//    public void testGetTextCurrentDate() {
//        System.out.println("testGetTextCurrentDate");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of getTextCurrentTime method, of class com.hosv3.control.LookupControl.
//     */
//    public void testGetTextCurrentTime() {
//        System.out.println("testGetTextCurrentTime");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of getTextCurrentDateTime method, of class com.hosv3.control.LookupControl.
//     */
//    public void testGetTextCurrentDateTime() {
//        System.out.println("testGetTextCurrentDateTime");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listDischargeOpd method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListDischargeOpd() {
//        System.out.println("testListDischargeOpd");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listDischargeType method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListDischargeType() {
//        System.out.println("testListDischargeType");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listDischargeStatus method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListDischargeStatus() {
//        System.out.println("testListDischargeStatus");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listIcd10GroupType method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListIcd10GroupType() {
//        System.out.println("testListIcd10GroupType");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listPrefixWhereTlock2 method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListPrefixWhereTlock2() {
//        System.out.println("testListPrefixWhereTlock2");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listTlock method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListTlock() {
//        System.out.println("testListTlock");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listFilmSize method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListFilmSize() {
//        System.out.println("testListFilmSize");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listBirthPlace method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListBirthPlace() {
//        System.out.println("testListBirthPlace");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listConduct method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListConduct() {
//        System.out.println("testListConduct");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listPostureBaby method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListPostureBaby() {
//        System.out.println("testListPostureBaby");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listPregnantLevel method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListPregnantLevel() {
//        System.out.println("testListPregnantLevel");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listUterusLevel method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListUterusLevel() {
//        System.out.println("testListUterusLevel");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listVitalTemplate method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListVitalTemplate() {
//        System.out.println("testListVitalTemplate");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listResultGiveBirth method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListResultGiveBirth() {
//        System.out.println("testListResultGiveBirth");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listICD10Pregnant method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListICD10Pregnant() {
//        System.out.println("testListICD10Pregnant");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listSew method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListSew() {
//        System.out.println("testListSew");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listGroupCronic method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListGroupCronic() {
//        System.out.println("testListGroupCronic");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listGroup504 method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListGroup504() {
//        System.out.println("testListGroup504");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listGroup505 method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListGroup505() {
//        System.out.println("testListGroup505");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listGroup506 method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListGroup506() {
//        System.out.println("testListGroup506");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listOptype method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListOptype() {
//        System.out.println("testListOptype");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listXrayLeteral method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListXrayLeteral() {
//        System.out.println("testListXrayLeteral");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listXrayPosition method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListXrayPosition() {
//        System.out.println("testListXrayPosition");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listSQLTemplate method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListSQLTemplate() {
//        System.out.println("testListSQLTemplate");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listSQLTemplateParam method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListSQLTemplateParam() {
//        System.out.println("testListSQLTemplateParam");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readServicePointById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadServicePointById() {
//        System.out.println("testReadServicePointById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of countStaffDoctorInServicePointByServiceID method, of class com.hosv3.control.LookupControl.
//     */
//    public void testCountStaffDoctorInServicePointByServiceID() {
//        System.out.println("testCountStaffDoctorInServicePointByServiceID");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of selectIdnameEmployeeAll method, of class com.hosv3.control.LookupControl.
//     */
//    public void testSelectIdnameEmployeeAll() {
//        System.out.println("testSelectIdnameEmployeeAll");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listDoctorInServiceDoctor method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListDoctorInServiceDoctor() {
//        System.out.println("testListDoctorInServiceDoctor");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of savePrefix method, of class com.hosv3.control.LookupControl.
//     */
//    public void testSavePrefix() {
//        System.out.println("testSavePrefix");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of saveGActionAuth method, of class com.hosv3.control.LookupControl.
//     */
//    public void testSaveGActionAuth() {
//        System.out.println("testSaveGActionAuth");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of saveRelation method, of class com.hosv3.control.LookupControl.
//     */
//    public void testSaveRelation() {
//        System.out.println("testSaveRelation");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listEmployee method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListEmployee() {
//        System.out.println("testListEmployee");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listOrderStatus method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListOrderStatus() {
//        System.out.println("testListOrderStatus");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readPatientAddress method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadPatientAddress() {
//        System.out.println("testReadPatientAddress");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readContactAddress method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadContactAddress() {
//        System.out.println("testReadContactAddress");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readCategoryGroupById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadCategoryGroupById() {
//        System.out.println("testReadCategoryGroupById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readCategoryGroupItemById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadCategoryGroupItemById() {
//        System.out.println("testReadCategoryGroupItemById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listTab method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListTab() {
//        System.out.println("testListTab");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readItemById method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadItemById() {
//        System.out.println("testReadItemById");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of intConfirmDoctorTreatment method, of class com.hosv3.control.LookupControl.
//     */
//    public void testIntConfirmDoctorTreatment() {
//        System.out.println("testIntConfirmDoctorTreatment");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of intSaveTransaction method, of class com.hosv3.control.LookupControl.
//     */
//    public void testIntSaveTransaction() {
//        System.out.println("testIntSaveTransaction");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listDxTemplateByName method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListDxTemplateByName() {
//        System.out.println("testListDxTemplateByName");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of listDxTemplateByVid method, of class com.hosv3.control.LookupControl.
//     */
//    public void testListDxTemplateByVid() {
//        System.out.println("testListDxTemplateByVid");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of readDxTemplateByName method, of class com.hosv3.control.LookupControl.
//     */
//    public void testReadDxTemplateByName() {
//        System.out.println("testReadDxTemplateByName");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    /**
//     * Test of initComboSetup method, of class com.hosv3.control.LookupControl.
//     */
//    public void testInitComboSetup() {
//        System.out.println("testInitComboSetup");
//        
//        // TODO add your test code below by replacing the default call to fail.
//        //fail("The test case is empty.");
//    }
//
//    public static void main(java.lang.String[] argList) {
//        junit.textui.TestRunner.run(suite());
//    }    
}
