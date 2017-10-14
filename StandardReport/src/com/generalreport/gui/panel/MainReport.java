/*
 * MainReport.java
 *
 * Created on 4 ตุลาคม 2548, 10:38 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.gui.panel;
import com.generalreport.gui.panel.report.*;
import com.generalreport.control.HosManage;

/**
 *  เป็นที่รวม panel ของ รายงานที่ต้องการจะใช้
 * @author tong(Padungrat)
 */
public class MainReport {
    
    /**รายงานทดสอบ*/
//    public PanelTestReport thePanelTestReport;
    public PanelReportDrugDispense thePanelReportDrugDispense;
    public PanelReportChronic thePanelReportChronic;
    public PanelReportCostPayment thePanelReportCostPayment;
    public PanelReportPaymentINOUTCup thePanelReportPaymentINOUTCup;
    public PanelReportClinicINOUTCup thePanelReportClinicINOUTCup;
    public PanelReportRevenueAndExpenseSummary thePanelReportRevenueAndExpenseSummary; 
    public PanelPatientInServicePoint thePanelPatientInServicePoint;
    public PanelCostTotalGroupByOrder thePanelCostTotalGroupByOrder;
    public PanelReportARIC thePanelReportARIC;
    public PanelPlentyDisease thePanelPlentyDisease;
    public PanelReportEyeDiseases thePanelReportEyeDiseases;
    public PanelCostPaymentShareOFA7INOUTHos thePanelCostPaymentShareOFA7INOUTHos; 
    public PanelPatientAdmitAndDischarge thePanelPatientAdmitAndDischarge;
    public PanelReportPatientOverService thePanelReportPatientOverService;
    public PanelCostDrugInServicePoint thePanelCostDrugInServicePoint;
    public PanelOrderLab thePanelOrderLab;
    public PanelAccident19Cause thePanelAccident19Cause;
    public MainReport(HosManage hosManage) {
        
    //    thePanelTestReport = new PanelTestReport(hosManage);
        thePanelReportDrugDispense = new PanelReportDrugDispense(hosManage);
        thePanelReportChronic = new PanelReportChronic(hosManage);
        thePanelReportCostPayment = new PanelReportCostPayment(hosManage); 
        thePanelReportPaymentINOUTCup = new PanelReportPaymentINOUTCup(hosManage);
        thePanelReportClinicINOUTCup = new PanelReportClinicINOUTCup(hosManage);
        thePanelReportCostPayment = new PanelReportCostPayment(hosManage);  
        thePanelReportRevenueAndExpenseSummary = new PanelReportRevenueAndExpenseSummary(hosManage); 
        thePanelPatientInServicePoint = new PanelPatientInServicePoint(hosManage);
        thePanelCostTotalGroupByOrder = new PanelCostTotalGroupByOrder(hosManage); 
        thePanelReportARIC = new PanelReportARIC(hosManage); 
        thePanelPlentyDisease = new PanelPlentyDisease(hosManage);
        thePanelReportEyeDiseases = new PanelReportEyeDiseases(hosManage);
        thePanelCostPaymentShareOFA7INOUTHos = new PanelCostPaymentShareOFA7INOUTHos(hosManage); 
        thePanelPatientAdmitAndDischarge = new PanelPatientAdmitAndDischarge(hosManage);
        thePanelReportPatientOverService = new PanelReportPatientOverService(hosManage);
        thePanelCostDrugInServicePoint = new PanelCostDrugInServicePoint(hosManage);
        thePanelOrderLab = new PanelOrderLab(hosManage); 
        thePanelAccident19Cause = new PanelAccident19Cause(hosManage);
    }
    
}
