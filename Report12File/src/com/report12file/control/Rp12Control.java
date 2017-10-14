 /*
 * HosDB.java
 *
 * Created on 28 กรกฎาคม 2548, 13:26 น.
 */
package com.report12file.control;

import com.hospital_os.usecase.connection.ConnectionInf;

import com.hosv3.object.HosObject;
import com.hosv3.utility.DateUtil;
import com.hosv3.utility.connection.UpdateStatus;

import com.report12file.objdb.Rp12DB;
import com.report12file.object.*;
import com.report12file.objectnh.ChaNh;
import com.report12file.objectnh.ChtNh;
import com.report12file.objectnh.IdxNh;
import com.report12file.objectnh.InsNh;
import com.report12file.objectnh.IopNh;
import com.report12file.objectnh.IpdNh;
import com.report12file.objectnh.IrfNh;
import com.report12file.objectnh.OdxNh;
import com.report12file.objectnh.OopNh;
import com.report12file.objectnh.OpdNh;
import com.report12file.objectnh.OrfNh;
import com.report12file.objectnh.PatNh;
import com.report12file.objectnh52.AdpNh52;
import com.report12file.objectnh52.AerNh52;
import com.report12file.objectnh52.ChaNh52;
import com.report12file.objectnh52.ChtNh52;
import com.report12file.objectnh52.IdxNh52;
import com.report12file.objectnh52.InsNh52;
import com.report12file.objectnh52.IopNh52;
import com.report12file.objectnh52.IpdNh52;
import com.report12file.objectnh52.IrfNh52;
import com.report12file.objectnh52.OdxNh52;
import com.report12file.objectnh52.OopNh52;
import com.report12file.objectnh52.OpdNh52;
import com.report12file.objectnh52.OrfNh52;
import com.report12file.objectnh52.PatNh52;
import com.report12file.utility.*;
import com.report12file.subject.GUISubject;
import com.reportcenter.gui.dialog.DialogResultRp;
import com.reportcenter.object.ResultRp;
import com.reportcenter.utility.FileWriter;
import com.reportcenter.utility.Util;
import com.reportcenter.utility.PrintShowReportLogControl;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Noom
 */
public class Rp12Control implements Runnable {

    public static int EXP_ALL = 0;
    public static int EXP_PASS = 1;
    public static int EXP_FAIL = 2;
    public static int MODE_DRG = 0;
    public static int MODE_NHSO = 1;
    public static int MODE_ECLM = 2;
    public static int MODE_ECLM53 = 3;
    public static int MODE_OPPP53 = 4;
    Thread hosControlThread;
    public ConnectionInf theConnectionInf;
    public Rp12DB theAerDB;
    public Rp12DB theChaDB;
    public Rp12DB theChtDB;
    public Rp12DB theIopDB;
    public Rp12DB theIdxDB;
    public Rp12DB theIrfDB;
    public Rp12DB theIpdDB;
    public Rp12DB theOopDB;
    public Rp12DB theOdxDB;
    public Rp12DB theOrfDB;
    public Rp12DB theOpdDB;
    public Rp12DB thePatDB;
    public Rp12DB theInsDB;
    public Rp12DB theAdpDB;
    public String current_file;
    public String startDate;
    public String endDate;
    public String pathFile;
    public String typeFile;
    protected int nhso_mode;
    UpdateStatus theUS;
    private HosObject theHO;
    public GUISubject theGUISubject;
    private int ex_all;
    private StringBuffer theSB;
    /** Creates a new instance of HosControl */
    private boolean use_vp;
    private ClinicControl theCC;
    private int[] theRows;
    private String backup_path = "history";
    private PrintShowReportLogControl printShowReportLogControl;
    private Vector vRR;
    private String during;
    public Rp12Control() {
    } 

    public Rp12Control(ConnectionInf c, UpdateStatus us, HosObject ho, ClinicControl cc) {
        theConnectionInf = c;
        theUS = us;
        theHO = ho;
        theCC = cc;
        theGUISubject = new GUISubject();
        printShowReportLogControl = new PrintShowReportLogControl();
    }

//สนย    
    private void initMinistry() {

        theInsDB = new Rp12DB(theConnectionInf, new Ins());
        thePatDB = new Rp12DB(theConnectionInf, new Pat());
        theOpdDB = new Rp12DB(theConnectionInf, new Opd());
        theOrfDB = new Rp12DB(theConnectionInf, new Orf());
        theOdxDB = new Rp12DB(theConnectionInf, new Odx());
        theOopDB = new Rp12DB(theConnectionInf, new Oop());
        theIpdDB = new Rp12DB(theConnectionInf, new Ipd());
        theIrfDB = new Rp12DB(theConnectionInf, new Irf());
        theIdxDB = new Rp12DB(theConnectionInf, new Idx());
        theIopDB = new Rp12DB(theConnectionInf, new Iop());
        theChtDB = new Rp12DB(theConnectionInf, new Cht());
        theChaDB = new Rp12DB(theConnectionInf, new Cha());
    }
//สปสช    

    private void initNhso() {

        theInsDB = new Rp12DB(theConnectionInf, new InsNh());
        thePatDB = new Rp12DB(theConnectionInf, new PatNh());
        theOpdDB = new Rp12DB(theConnectionInf, new OpdNh());
        theOrfDB = new Rp12DB(theConnectionInf, new OrfNh());
        theOdxDB = new Rp12DB(theConnectionInf, new OdxNh());
        theOopDB = new Rp12DB(theConnectionInf, new OopNh());
        theIpdDB = new Rp12DB(theConnectionInf, new IpdNh());
        theIrfDB = new Rp12DB(theConnectionInf, new IrfNh());
        theIdxDB = new Rp12DB(theConnectionInf, new IdxNh());
        theIopDB = new Rp12DB(theConnectionInf, new IopNh());
        theChtDB = new Rp12DB(theConnectionInf, new ChtNh());
        theChaDB = new Rp12DB(theConnectionInf, new ChaNh());
    }
//สปสช    

    private void initNhso52() {

        theInsDB = new Rp12DB(theConnectionInf, new InsNh52());
        thePatDB = new Rp12DB(theConnectionInf, new PatNh52());
        theOpdDB = new Rp12DB(theConnectionInf, new OpdNh52());
        theOrfDB = new Rp12DB(theConnectionInf, new OrfNh52());
        theOdxDB = new Rp12DB(theConnectionInf, new OdxNh52());
        theOopDB = new Rp12DB(theConnectionInf, new OopNh52());
        theIpdDB = new Rp12DB(theConnectionInf, new IpdNh52());
        theIrfDB = new Rp12DB(theConnectionInf, new IrfNh52());
        theIdxDB = new Rp12DB(theConnectionInf, new IdxNh52());
        theIopDB = new Rp12DB(theConnectionInf, new IopNh52());
        theChtDB = new Rp12DB(theConnectionInf, new ChtNh52());
        theChaDB = new Rp12DB(theConnectionInf, new ChaNh52());
        theAerDB = new Rp12DB(theConnectionInf, new AerNh52());
        theAdpDB = new Rp12DB(theConnectionInf, new AdpNh52());
    }

    public boolean setDataExport12File(int[] rows, String startDate, String endDate
            , String pathFile, String typeFile, int all, int mode, boolean use_vp) {

        this.use_vp = use_vp;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pathFile = pathFile;
        this.typeFile = typeFile;
        this.ex_all = all;
        this.theRows = rows;
        this.nhso_mode = mode;

        //set Date Format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        theHO.date_time = sdf.format(new Date());

        if (rows.length == 0) {
            theGUISubject.onShowStatus("กรุณาเลือกรายงานที่ต้องการ", java.awt.Color.YELLOW);
            return false;
        }
        if (startDate.compareTo(endDate) > 0) {
            theGUISubject.onShowStatus(Language.getTextBundle("Warning", 1), java.awt.Color.YELLOW);
            return false;
        }
        Vector all_map = theCC.selectClinicMap();
        if (all_map.isEmpty()) {
            theUS.setStatus("ข้อมูลคลินิกยังไม่มีการจับคู่ กรุณาจับคู่ข้อมูลคลินิกก่อนออกรายงาน", UpdateStatus.WARNING);
            return false;
        }
        String last_clinic = null;
        for (int i = 0; i < all_map.size(); i++) {
            ClinicMap cm = (ClinicMap) all_map.get(i);
            if (cm.t_report_clinic_12files_id == null) {
                theUS.setStatus("คลินิกมีการจับคู่ไม่ครบ กรุณาตรวจสอบคลินิก " + cm.visit_clinic_description, UpdateStatus.WARNING);
                return false;
            }
            if (cm.visit_clinic_number.equals(last_clinic)) {
                theUS.setStatus("คลินิกมีการจับคู่ซ้ำซ้อน กรุณาตรวจสอบคลินิก " + cm.visit_clinic_description, UpdateStatus.WARNING);
                return false;
            }
            last_clinic = cm.visit_clinic_number;
        }
        theGUISubject.setEnabled(true);
        if (nhso_mode == Rp12Control.MODE_DRG)             initMinistry();
         else if (nhso_mode == Rp12Control.MODE_NHSO)       initNhso();
         else if(nhso_mode==Rp12Control.MODE_ECLM)          initNhso52();
        return true;
    }

    public void start() {
        hosControlThread = new Thread(this);
        hosControlThread.start();
    }

    public void run() {
        try {
            startExport12File();
        } catch (Exception e) {
            theGUISubject.onShowStatus("การออกรายงาน " + current_file + " ผิดพลาด", java.awt.Color.RED);
            e.printStackTrace();
            printShowReportLogControl.getAllDocName();
            stop();
        }
    }

    public void stop() {
        if (hosControlThread != null) {
            theConnectionInf.close();
            hosControlThread.stop();
        }
        hosControlThread = null;
        System.out.println("In stop in HosControl");
        theGUISubject.setEnabled(true);
        theGUISubject.onShowStatus(Constant.STATUS_CANCEL_REPORT, java.awt.Color.GREEN);
    }

    public void dbExport(Rp12DB theFile12DB, String startDate, String endDate) throws Exception {
        if (theFile12DB == null) {
            return;
        }
        System.out.println("nhso_mode__________________________________________");
        current_file = theFile12DB.getFileName();
        //--เก็บค่าเพื่อแสดงสรุปใน HTML----
        ResultRp rr = new ResultRp();
        vRR.add(rr);
        rr.current_file = current_file;
       
        theFile12DB.setStringBuffer(theSB, ex_all);
        theSB.append("\r\n\r\n" + current_file);
        System.out.println(current_file);
        theGUISubject.onShowStatus("ขณะนี้กำลังออกรายงาน " + current_file + "...", java.awt.Color.white);
        String curdate = theHO.date_time;
        String path = "";
        String dotafter = "";
        String hid = theHO.theSite.off_id;

        if (typeFile.equals(Constant.DBF_INDEX))
            dotafter = ".dbf";
        else
            dotafter = ".txt";

        path = pathFile + theFile12DB.getFileName() + Util.getheadFile(startDate) + dotafter;
        if (nhso_mode == Rp12Control.MODE_NHSO) {
            path = pathFile + theFile12DB.getFileName() + Util.getHeadFile(startDate, curdate, hid) + dotafter;
        }
        int mode = 0;
        if (use_vp) {
            mode = 1;
        }
        theFile12DB.fail_only = ex_all;
        //เปลี่ยนการ export ไฟล์ใหม่
        theFile12DB.exportFile(startDate, endDate, path,typeFile,current_file,rr);
        printShowReportLogControl.addDocName(path);
    }

    public void startExport12File() throws Exception {
        vRR = new Vector();
        String hid = "";
        System.out.println("In method startExport12File");
        theSB = new StringBuffer();
        if (theGUISubject != null) {
            System.out.println("In method theGUISubject");
            theGUISubject.setEnabled(false);
            theGUISubject.onShowStatus("กรุณารอสักครู่ ขณะนี้โปรแกรมกำลังออกรายงาน", java.awt.Color.white);
            theGUISubject.onShowPicture("", true);
        }
        theConnectionInf.open();
        System.out.print(" public void startExport(");
        String curdate = DateUtil.getTextDB(DateUtil.getDateFromText(DateUtil.getTextCurrentDate(theConnectionInf)), false);
        theConnectionInf.open();
        ResultSet rs = theConnectionInf.eQuery("select b_visit_office_id from b_site");
        if (rs.next()) {
            hid = rs.getString(1);
        }

        for (int i = 0; i < theRows.length; i++) {
            if (theRows[i] == 0) {
                dbExport(theInsDB, startDate, endDate);
            } else if (theRows[i] == 1) {
                dbExport(thePatDB, startDate, endDate);
            } else if (theRows[i] == 2) {
                dbExport(theOpdDB, startDate, endDate);
            } else if (theRows[i] == 3) {
                dbExport(theOrfDB, startDate, endDate);
            } else if (theRows[i] == 4) {
                dbExport(theOdxDB, startDate, endDate);
            } else if (theRows[i] == 5) {
                dbExport(theOopDB, startDate, endDate);
            } else if (theRows[i] == 6) {
                dbExport(theIpdDB, startDate, endDate);
            } else if (theRows[i] == 7) {
                dbExport(theIrfDB, startDate, endDate);
            } else if (theRows[i] == 8) {
                dbExport(theIdxDB, startDate, endDate);
            } else if (theRows[i] == 9) {
                dbExport(theIopDB, startDate, endDate);
            } else if (theRows[i] == 10) {
                dbExport(theChtDB, startDate, endDate);
            } else if (theRows[i] == 11) {
                dbExport(theChaDB, startDate, endDate);
            } else if (theRows[i] == 12) {
                dbExport(theAerDB, startDate, endDate);
            } else if (theRows[i] == 13) {
                dbExport(theAdpDB, startDate, endDate);
            }
        }
        theConnectionInf.close();
        /*--------------------- จบไฟล์ที่เกี่ยวข้องกับ PCU โดยตรง -----------------------*/
        System.out.println("Finish export");
        if (theGUISubject != null) {
            theGUISubject.setEnabled(true);
            theGUISubject.onShowStatus("ออกรายงานเรียบร้อยแล้ว path ไฟล์อยู่ที่ " + pathFile, java.awt.Color.BLUE, true);
            theGUISubject.onShowPicture("", false);
        }
        //DialogAlert.showDialog(theSB.toString(), UpdateStatus.WARNING, theUS.getJFrame());
        Report12FileData.saveResult(pathFile + "/result12file.txt", theSB);
        // insert log
        this.during = PrintShowReportLogControl.getDuring(startDate, endDate);
        String dbName = PrintShowReportLogControl.getDBName(theConnectionInf);
        String str = ResultRp.parseHtml(vRR,theHO.date_time,this.during,dbName);
        saveLogExport(str);
        //--โชว์ dialog เตือนด้วย HTML---
        DialogResultRp.showResult(theUS.getJFrame(), str);
        FileWriter fileErrorWriter = new FileWriter(pathFile + "/Rp12_"+theHO.date_time+".html");
        fileErrorWriter.writeData(str);
        fileErrorWriter.closeFile();
        if(backup_path!=null){
            File file = new File(backup_path);
            if(!file.exists())
                file.mkdirs();
            fileErrorWriter = new FileWriter(backup_path + File.separator + "Rp12_"+theHO.date_time+".html");
            fileErrorWriter.writeData(str);
            fileErrorWriter.closeFile();
        }
        this.stop();
        System.out.println("Stop -- Thread----");
    }

    public boolean isRunning() {
        return this.hosControlThread.isAlive();
    }

    /**
     * ALTER TABLE "public"."r_rplog_export"
     *	ADD COLUMN "file_name" varchar(255) NULL
     *
     *ALTER TABLE "public"."r_rplog_export"
     *	ADD COLUMN "file_export_name" varchar(255) NULL
     * ใส่ method saveLogExport
     **/
    public int saveLogExport(String str) {
        try {
            theConnectionInf.open();
            StringBuffer sql = new StringBuffer("insert into r_rplog_export values('");
            sql.append(theHO.date_time);
            sql.append("','").append(theHO.date_time);
            sql.append("','").append(ResultRp.getNameAddress());
            sql.append("','").append(theHO.theEmployee.getObjectId());
            sql.append("',?");
            sql.append(",'").append(Constant.REPORT_NAME[0]);
            sql.append("','").append(printShowReportLogControl.getAllDocName());
            sql.append("','").append(this.during);
            sql.append("');");
            System.out.println(sql);
            PreparedStatement ps = theConnectionInf.getConnection().prepareStatement(sql.toString());
            ps.setString(1, str);

            return ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        } finally {
            theConnectionInf.close();
        }
    } 
}


