/*
 * HosDB.java
 *
 * Created on 4 ตุลาคม 2548, 14:04 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.objdb;
import com.generalreport.objdb.*;
import com.hospital_os.usecase.connection.ConnectionInf;

/**
 *
 * @author tong(Padungrat)
 */
public class HosDB {
    
    public TestReportDB theTestReportDB;
    public RPDrugDispenseDB theRPDrugDispenseDB; 
    public RPChronicDB theRPChronicDB;
    public RPCostPaymentDB theRPCostPaymentDB;

    public RPPaymentINOUTCupDB theRPPaymentINOUTCupDB;
    public RPReportARICDB theRPReportARICDB;
    public RPEyeDiseasesDB theRPEyeDiseasesDB;
    public RPClinicINOUTCupDB theRPClinicINOUTCupDB;
    public RPPatientInServicePointDB theRPPatientInServicePointDB;
    public RPRevenueAndExpenseDB theRPRevenueAndExpenseDB;
    public SystemDB theSystemDB;
    public RPCostTotalGroupByOrderDB theRPCostTotalGroupByOrderDB;
    public RPPlentyDiseaseDB theRPPlentyDiseaseDB;
    public RPCostPaymentShareOFA7INOUTHosDB theRPCostPaymentShareOFA7INOUTHosDB;
    public RPPatientAdmitAndDischargeDB theRPPatientAdmitAndDischargeDB;
    public RPPatientOverServiceDB theRPPatientOverServiceDB;
    public RPCostDrugInServicePointDB theRPCostDrugInServicePointDB;
    public RPOrderLabDB theRPOrderLabDB;
    public RPAccident19CauseDB theRPAccident19CauseDB;
    public ConnectionInf theConnectionInf;
    
    public HosDB(ConnectionInf connectionInf) {
        theConnectionInf = connectionInf;
        
        theTestReportDB = new TestReportDB(theConnectionInf);
        theRPDrugDispenseDB = new RPDrugDispenseDB(theConnectionInf);
        theRPChronicDB = new RPChronicDB(theConnectionInf);
        theRPCostPaymentDB = new RPCostPaymentDB(theConnectionInf);

        theRPPaymentINOUTCupDB = new RPPaymentINOUTCupDB(theConnectionInf);
        theRPReportARICDB = new RPReportARICDB(theConnectionInf);
        theRPEyeDiseasesDB = new RPEyeDiseasesDB(theConnectionInf);
        theRPClinicINOUTCupDB = new RPClinicINOUTCupDB(theConnectionInf);
        theRPPatientInServicePointDB = new RPPatientInServicePointDB(theConnectionInf);
        theRPRevenueAndExpenseDB = new RPRevenueAndExpenseDB(theConnectionInf);
        theSystemDB = new SystemDB(theConnectionInf);
        theRPCostTotalGroupByOrderDB = new RPCostTotalGroupByOrderDB(theConnectionInf);
        theRPPlentyDiseaseDB = new RPPlentyDiseaseDB(theConnectionInf);
        theRPCostPaymentShareOFA7INOUTHosDB = new RPCostPaymentShareOFA7INOUTHosDB(theConnectionInf);
        theRPPatientAdmitAndDischargeDB = new RPPatientAdmitAndDischargeDB(theConnectionInf);
        theRPPatientOverServiceDB = new RPPatientOverServiceDB(theConnectionInf);
        theRPCostDrugInServicePointDB = new RPCostDrugInServicePointDB(theConnectionInf);
        theRPOrderLabDB = new RPOrderLabDB(theConnectionInf);
        theRPAccident19CauseDB = new RPAccident19CauseDB(theConnectionInf);
    }
    
}
