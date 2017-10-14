/*
 * HosControl.java
 *
 * Created on 28 กรกฎาคม 2548, 13:26 น.
 */

package com.standardReport.control;

import java.util.Vector;

import com.hospital_os.usecase.connection.ConnectionInf;

/***/
import com.standardReport.subject.ManageReportFile;
import com.standardReport.object.FileName;
import com.standardReport.utility.*;
import com.standardReport.subject.HosSubject;

public class HosControl /*extends Thread*/ implements Runnable{
    Thread hosControlThread;
    public ConnectionInf theConnectionInf;
    public HosDB hdb;
    
    /****/
    public HosSubject theHosSubject;
    /****/
    
    public ExportControl theExportControl;
    public RP504Control theRP504Control;
    public RP505Control theRP505Control; 
    public RP506Control theRP506Control;
    public RP506SurveilControl theRP506SurveilControl;
    public RP115Group1_2549Control theRP115Group1_2549Control;
    public RP115Group2_2549Control theRP115Group2_2549Control;
    public RP115Group3_2549Control theRP115Group3_2549Control;
    public RP115Group4_2549Control theRP115Group4_2549Control;
    
   /******/ 
    public FileName theFileName;
    /*****/
    
    public ManageReportFile theManageReportFile;
    public String startDate;
    public String endDate;
    public String pathFile;
    public String typeFile;
    
    /** Vector สำหรับ Combobox*/
    public Vector vTreatStatus;
    private com.hosv3.control.HosControl theHC;
    
    
    
    public HosControl(com.hosv3.control.HosControl hc) {
        System.out.println("In HosControl");
        theHC = hc;
        theConnectionInf = theHC.theConnectionInf;
        theManageReportFile = null;
        theHosSubject = new HosSubject();
        hdb = new HosDB(theConnectionInf);
        theExportControl = new ExportControl(theConnectionInf,hdb);
        theRP504Control = new RP504Control(theHC,theConnectionInf,hdb);
        theRP505Control = new RP505Control(theHC,theConnectionInf,hdb);
        theRP506Control = new RP506Control(theHC,theConnectionInf,hdb);
        theRP506SurveilControl = new RP506SurveilControl(theHC,theConnectionInf,hdb);
        theRP115Group1_2549Control = new RP115Group1_2549Control(theHC,theConnectionInf,hdb);
        theRP115Group2_2549Control = new RP115Group2_2549Control(theHC,theConnectionInf,hdb);
        theRP115Group3_2549Control = new RP115Group3_2549Control(theHC,theConnectionInf,hdb);
        theRP115Group4_2549Control = new RP115Group4_2549Control(theHC,theConnectionInf,hdb);
        vTreatStatus = new Vector();
        setDataCombo();
    }
    
    public void start(){
       hosControlThread = new Thread(this);
       hosControlThread.start();
    }
    
    public void stop(){
      hosControlThread.stop();
      hosControlThread = null;
      System.out.println("In stop in HosControl");
      theManageReportFile.onShowStatus(Constant.STATUS_CANCEL_REPORT, java.awt.Color.red);
    }
     
    
    public void setDataExportFile(FileName theFileName,String startDate, String endDate,String pathFile,String typeFile){
        if(this.theFileName == null){
           this.theFileName = new FileName();
        }
        this.theFileName = theFileName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pathFile = pathFile;
        this.typeFile = typeFile;
    }
    
     public void setManageReport18File(ManageReportFile theManageReportFile) {
        this.theManageReportFile = theManageReportFile;
    }
    
    public void run(){
      /*  try
        {
            startExportFile();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
       **/
    }
    
    

 /*   public void startExportFile() throws Exception
    {
        System.out.println("In method startExportFile");
        
        if(theManageReportFile != null)
        {
            theManageReportFile.onShowStatus("กรุณารอสักครู่ ขณะนี้โปรแกรมกำลังออกรายงาน", java.awt.Color.white);
            theManageReportFile.onShowPicture("",true);
        }
        
        //System.out.println("* * * * * "+theFileName.Employee);
        if((Constant.SELECTED).equals(theFileName.Employee))
        {
            theEmployeeControl.startExport(startDate,endDate, pathFile, typeFile);
        }
        System.out.println("Finish export");
        if(theManageReportFile != null)
        {
            theManageReportFile.onShowStatus("ออกรายงานเรียบร้อยแล้ว path ไฟล์อยู่ที่ "+pathFile, java.awt.Color.BLUE);
            theManageReportFile.onShowPicture("",false);
        }
        this.stop();
        System.out.println("Stop -- Thread----");
    }
  **/
    
    public void setDataCombo()
    {
        ComboFix theComboFix = new ComboFix();        
        theComboFix.code = "0";
        theComboFix.name = "ทั้งหมด";        
        this.vTreatStatus = this.theExportControl.selectTreatStatusAll();        
        this.vTreatStatus.add(theComboFix);
        theComboFix = null;
    }
    
    public Vector getTreatStatus()
    {
        return this.vTreatStatus;
    }

    
    public static void main(String args[]) throws Exception{
        
        String url = "jdbc:postgresql://192.168.1.6:5432/develop_aoluk_test";
        String user = "postgres";
        String pass = "postgres";
        String dri = "org.postgresql.Driver";
        String type = "0";
        System.out.println("---finish");
    }
}
