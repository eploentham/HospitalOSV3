/*
 * HosControl.java
 *
 * Created on 7 กันยายน 2548, 15:49 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.control;
import com.report12file.objdb.HosDB;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.report12file.utility.*;
import com.report12file.subject.GUISubject;
/**
 *
 * @author tong(Padungrat)
 */
public class ReportControl implements Runnable{
    Thread hosControlThread;
    public ConnectionInf theConnectionInf;
    public GUISubject theGUISubject;
    /***/
    public File12Control theAerControl;
    public File12Control theChaControl;
    public File12Control theChtControl;
    public File12Control theIdxControl;
    public File12Control theInsControl;
    public File12Control theIopControl;
    public File12Control theIpdControl;
    public File12Control theIrfControl;
    public File12Control theOdxControl;
    public File12Control theOopControl;
    public File12Control theOpdControl;
    public File12Control theOrfControl;
    public File12Control thePatControl;
    public ClinicControl theClinicControl;
    /***/
    public HosDB hdb;
    
    public Report12FileName theReport12File;

    public String startDate;
    public String endDate;
    public String pathFile;
    public String typeFile;
    public int exportFor = 0;

    private boolean use_vp;

    private String current_file;
    /** Creates a new instance of HosControl */
    public ReportControl(ConnectionInf c) {
        theConnectionInf = c;
        
        theGUISubject = new GUISubject();
        hdb = new HosDB(theConnectionInf);
        init12FileControl();
    }

    public void init12FileControl() {
     //   theAncControl = new AncControl(theConnectionInf,hdb);
        
         theAerControl = new File12Control(theConnectionInf,hdb.theAerDB);
         theChaControl = new File12Control(theConnectionInf,hdb.theChaDB);
         theChtControl = new File12Control(theConnectionInf,hdb.theChtDB);
         theIdxControl = new File12Control(theConnectionInf,hdb.theIdxDB);
         theInsControl = new File12Control(theConnectionInf,hdb.theInsDB);
         theIopControl = new File12Control(theConnectionInf,hdb.theIopDB);
         theIpdControl = new File12Control(theConnectionInf,hdb.theIpdDB);
         theIrfControl = new File12Control(theConnectionInf,hdb.theIrfDB);
         theOdxControl = new File12Control(theConnectionInf,hdb.theOdxDB);
         theOopControl = new File12Control(theConnectionInf,hdb.theOopDB);
         theOpdControl = new File12Control(theConnectionInf,hdb.theOpdDB);
         theOrfControl = new File12Control(theConnectionInf,hdb.theOrfDB);
         thePatControl = new File12Control(theConnectionInf,hdb.thePatDB);
         theClinicControl = new ClinicControl(hdb);
     
    }
    
    /**ใช้ในการ run thread*/
    public void start() {
        hosControlThread = new Thread(this);
       hosControlThread.start();
    }
    
    
    
    
    /**ใช้ในการ stop thread*/
    public void stop(){
        try
        {
          if(hosControlThread != null)
          {
              hosControlThread.stop();
          }
          hosControlThread = null;
          System.out.println("In stop in HosControl");
          theGUISubject.setEnabled(true);
          //ยังไม่ได้ใช้
          //theManageReport12File.onShowStatus(Constant.STATUS_CANCEL_REPORT, java.awt.Color.red);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void run(){
        try{
            startExport12File();
        }catch(Exception e){
            theGUISubject.onShowStatus("การออกรายงาน "+current_file+" ผิดพลาด", java.awt.Color.RED);
            e.printStackTrace();
        }
    }
    
    
    /**ใช้ในการ Export ฐานข้อมูลออกเป็น fox*/
    public void startExport12File() throws Exception{
        if(theGUISubject != null){
               theGUISubject.setEnabled(false);
               theGUISubject.onShowStatus("กรุณารอสักครู่ ขณะนี้โปรแกรมกำลังออกรายงาน", java.awt.Color.white);
               theGUISubject.onShowPicture("",true);
        }
        if((Constant.SELECTED).equals(theReport12File.ins)){
            current_file = theInsControl.FileName;
            System.out.println(current_file);
            theGUISubject.onShowStatus("ขณะนี้กำลังออกรายงาน "+current_file+"...", java.awt.Color.white);
            hdb.theInsDB.setUseVP(use_vp);
            theInsControl.startExport(startDate,endDate, pathFile, typeFile, exportFor); 
        }
        if((Constant.SELECTED).equals(theReport12File.pat)){
            current_file = thePatControl.FileName;
            System.out.println(current_file);
            theGUISubject.onShowStatus("ขณะนี้กำลังออกรายงาน "+current_file+"...", java.awt.Color.white);
            thePatControl.startExport(startDate,endDate, pathFile, typeFile, exportFor);
        }
        if((Constant.SELECTED).equals(theReport12File.opd)){
            current_file = theOpdControl.FileName;
            System.out.println(current_file);
            theGUISubject.onShowStatus("ขณะนี้กำลังออกรายงาน "+current_file+"...", java.awt.Color.white);
            theOpdControl.startExport(startDate,endDate, pathFile, typeFile, exportFor); 
        }
        if((Constant.SELECTED).equals(theReport12File.orf)){
            current_file = theOrfControl.FileName;
            System.out.println(current_file);
            theGUISubject.onShowStatus("ขณะนี้กำลังออกรายงาน "+current_file+"...", java.awt.Color.white);
            theOrfControl.startExport(startDate,endDate, pathFile, typeFile, exportFor);
        }
        if((Constant.SELECTED).equals(theReport12File.odx)){
            current_file = theOdxControl.FileName;
            System.out.println(current_file);
            theGUISubject.onShowStatus("ขณะนี้กำลังออกรายงาน "+current_file+"...", java.awt.Color.white);
            theOdxControl.startExport(startDate,endDate, pathFile, typeFile, exportFor); 
        }
        if((Constant.SELECTED).equals(theReport12File.oop)){
            current_file = theOopControl.FileName;
            System.out.println(current_file);
            theGUISubject.onShowStatus("ขณะนี้กำลังออกรายงาน "+current_file+"...", java.awt.Color.white);
            theOopControl.startExport(startDate,endDate, pathFile, typeFile, exportFor); 
        }
        if((Constant.SELECTED).equals(theReport12File.ipd)){
            current_file = theIpdControl.FileName;
            System.out.println(current_file);
            theGUISubject.onShowStatus("ขณะนี้กำลังออกรายงาน "+current_file+"...", java.awt.Color.white);
            theIpdControl.startExport(startDate,endDate, pathFile, typeFile, exportFor); 
        }
        if((Constant.SELECTED).equals(theReport12File.irf)){
            current_file = theIrfControl.FileName;
            System.out.println(current_file);
            theGUISubject.onShowStatus("ขณะนี้กำลังออกรายงาน "+current_file+"...", java.awt.Color.white);
            theIrfControl.startExport(startDate,endDate, pathFile, typeFile, exportFor); 
        }
        if((Constant.SELECTED).equals(theReport12File.idx)){
            current_file = theIdxControl.FileName;
            System.out.println(current_file);
            theGUISubject.onShowStatus("ขณะนี้กำลังออกรายงาน "+current_file+"...", java.awt.Color.white);
            theIdxControl.startExport(startDate,endDate, pathFile, typeFile, exportFor); 
        }
        if((Constant.SELECTED).equals(theReport12File.iop)){
            current_file = theIopControl.FileName;
            System.out.println(current_file);
            theGUISubject.onShowStatus("ขณะนี้กำลังออกรายงาน "+current_file+"...", java.awt.Color.white);
            theIopControl.startExport(startDate,endDate, pathFile, typeFile, exportFor); 
        }
        if((Constant.SELECTED).equals(theReport12File.cht)){
            current_file = theChtControl.FileName;
            System.out.println(current_file);
            theGUISubject.onShowStatus("ขณะนี้กำลังออกรายงาน "+current_file+"...", java.awt.Color.white);
            theChtControl.startExport(startDate,endDate, pathFile, typeFile, exportFor);  
        }
        if((Constant.SELECTED).equals(theReport12File.cha)){
            current_file = theChaControl.FileName;
            System.out.println(current_file);
            theGUISubject.onShowStatus("ขณะนี้กำลังออกรายงาน "+current_file+"...", java.awt.Color.white);
            theChaControl.startExport(startDate,endDate, pathFile, typeFile, exportFor); 
        }
        if((Constant.SELECTED).equals(theReport12File.aer)){
            current_file = theAerControl.FileName;
            System.out.println(current_file);
            theGUISubject.onShowStatus("ขณะนี้กำลังออกรายงาน "+current_file+"...", java.awt.Color.white);
            theAerControl.startExport(startDate,endDate, pathFile, typeFile, exportFor); 
        }
        
        
        System.out.println("Finish export");
          if(theGUISubject != null){
            theGUISubject.setEnabled(true);
            theGUISubject.onShowStatus("ออกรายงานเรียบร้อยแล้ว path ไฟล์อยู่ที่ "+pathFile, java.awt.Color.BLUE,true);
            theGUISubject.onShowPicture("",false);
          }
        this.stop();
        System.out.println("Stop -- Thread----");
    }
    
   
    
    
    /**ใช้ในการกำหนดค่าก่อนการ query ข้อมูล
     *  @param theReport12File
     *  @param startDate
     *  @param endDate
     *  @param pathFile
     *  @param typeFile
     */
    public void setDataExport12File(Report12FileName theReport12File
            ,String startDate, String endDate,String pathFile,String typeFile, int exportFor
            ,boolean use_vp)
    {
        if(this.theReport12File == null){
           this.theReport12File = new Report12FileName();
        }
        this.theReport12File = theReport12File;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pathFile = pathFile;
        this.typeFile = typeFile;
        this.exportFor = exportFor;
        this.use_vp = use_vp;
    }
}
