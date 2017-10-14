/*
 * HosControl.java
 *
 * Created on 3 ตุลาคม 2548, 11:07 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.control;
import com.generalreport.subject.HosSubject;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalreport.objdb.HosDB;
import java.util.Vector;
import com.generalreport.utility.ComboFix;

/**
 *
 * @author tong(Padungrat)
 */
public class HosControl {
    
    public HosSubject theHosSubject;
  //  public TestReportControl theTestReportControl;
    public RPDrugDispenseControl theRPDrugDispenseControl;
    public RPChronicControl theRPChronicControl;
    public RPCostPaymentControl theRPCostPaymentControl;
    public RPPaymentINOUTCupControl theRPPaymentINOUTCupControl;
    public RPClinicINOUTCupControl theRPClinicINOUTCupControl;
    public ExportControl theExportControl;
    public SystemControl theSystemControl;
    public RPPatientInServicePointControl theRPPatientInServicePointControl;
    public RPRevenueAndExpenseControl theRPRevenueAndExpenseControl;
    public RPCostTotalGroupByOrderControl theRPCostTotalGroupByOrderControl;
    public RPReportARICControl theRPReportARICControl;
    public RPPlentyDiseaseControl theRPPlentyDiseaseControl;
    public RPEyeDiseasesControl theRPEyeDiseasesControl;
    public RPPatientAdmitAndDischargeControl theRPPatientAdmitAndDischargeControl;
    public RPCostPaymentShareOFA7INOUTHosControl theRPCostPaymentShareOFA7INOUTHosControl;
    public RPPatientOverServiceControl theRPPatientOverServiceControl;
    public RPCostDrugInServicePointControl theRPCostDrugInServicePointControl;
    public RPOrderLabControl theRPOrderLabControl;
    public RPAccident19CauseControl theRPAccident19CauseControl;
    
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
        /** Vector สำหรับ Combobox*/
    public Vector vServicePoint;
    public Vector vDoctor;
    public Vector vSP;
    public Vector vcSPCheck;
    private com.hosv3.control.HosControl theHC;
    
    public HosControl(com.hosv3.control.HosControl hc, ConnectionInf connectionInf) {
        theHC = hc;
        theHosSubject = new HosSubject();
        theConnectionInf = connectionInf;
        theHosDB = new HosDB(theConnectionInf);
        theRPDrugDispenseControl = new RPDrugDispenseControl(theHC,theHosDB);
        theRPChronicControl = new RPChronicControl(theHC,theHosDB);
        theRPCostPaymentControl = new RPCostPaymentControl(theHC,theHosDB);
        theRPPaymentINOUTCupControl = new RPPaymentINOUTCupControl(theHC,theHosDB);
        theRPClinicINOUTCupControl = new RPClinicINOUTCupControl(theHC,theHosDB);
      //  theTestReportControl = new TestReportControl(theHosDB);
        theSystemControl = new SystemControl(theConnectionInf, theHosDB);
        theRPPatientInServicePointControl = new RPPatientInServicePointControl(theHC,theHosDB);
        theRPRevenueAndExpenseControl = new RPRevenueAndExpenseControl(theHC,theHosDB);
        theRPCostTotalGroupByOrderControl = new RPCostTotalGroupByOrderControl(theHC,theHosDB);
        theRPReportARICControl = new RPReportARICControl(theHC,theHosDB);
        theRPPlentyDiseaseControl = new RPPlentyDiseaseControl(theHC,theHosDB);
        theRPEyeDiseasesControl = new RPEyeDiseasesControl(theHC,theHosDB);
        theRPCostPaymentShareOFA7INOUTHosControl = new RPCostPaymentShareOFA7INOUTHosControl(theHC,theHosDB);
        theRPPatientAdmitAndDischargeControl = new RPPatientAdmitAndDischargeControl(theHC,theHosDB);
        theRPPatientOverServiceControl = new RPPatientOverServiceControl(theHC,theHosDB);
        theRPCostDrugInServicePointControl = new RPCostDrugInServicePointControl(theHC,theHosDB);
        theRPOrderLabControl = new RPOrderLabControl(theHC,theHosDB);
        theRPAccident19CauseControl = new RPAccident19CauseControl(theHC,theHosDB);
        
        theExportControl = new ExportControl();
        setDataCombo();
    }
    
    
    public void setDataCombo()
    {
        ComboFix theComboFix = new ComboFix();        
        theComboFix.code = "0";
        theComboFix.name = "ทั้งหมด";
        this.vServicePoint = this.theSystemControl.selectServicePointForComboBox();
        this.vServicePoint.add(theComboFix);
        theComboFix = null;
        
        this.vDoctor = this.theSystemControl.getNonDoctor();
    }
    
    public Vector getServicePoint()
    {
        return this.vServicePoint;
    }
    
   /* public Vector getTreatServicePointDoctor()
    {
       return this.vSP = this.theSystemControl.selectServicePointDoctor();
    }
    */
    
    public Vector getDoctor()
    {   
        System.out.println("**** "+this.vDoctor);
        return this.vDoctor;
    }
    
  /*  public Vector getNonDoctor()
    {
        Vector vc = new Vector();
        
        ComboFix theComboFix = new ComboFix();        
        theComboFix.code = "0000000000000";
        theComboFix.name = Language.getTextBundle("NotIdentify", 1);        
        vc.add(theComboFix);

        return  vc;
    }
   */
    
    /**
     *  Function
     *  list รายชื่อจุดบริการทั้งหมดของโรงพยาบาล 
     */
  /*  public Vector listServicePoint()
    {
        theConnectionInf.open();
        try
        {
            this.vcSPCheck = theHosDB.theSystemDB.selectServicePointForCheck();
            if(this.vcSPCheck != null)
            {
                System.out.println("---this.vcSPCheck : "+this.vcSPCheck.size());
            }
            else
            {
                System.out.println("---this.vcSPCheck : NuLL");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vcSPCheck;
    }
   **/

}
