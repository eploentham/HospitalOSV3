/*
 * HosDB.java
 *
 * Created on 28 �á�Ҥ� 2548, 13:26 �.
 */
package com.report18file.control;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.object.HosObject;
import com.hosv3.utility.connection.UpdateStatus;
import com.report18file.objdb.EpiDB;
import com.report18file.objdb.Rp18DB;
import com.report18file.utility.*;
import com.report18file.subject.GUISubject;
import com.reportcenter.gui.dialog.DialogResultRp;
import com.reportcenter.object.ResultRp;
import com.reportcenter.utility.FileWriter;
import com.reportcenter.utility.Util;
import java.io.File;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import com.reportcenter.utility.PrintShowReportLogControl;

/**
 *
//henbe comment 100253 kong ��������������¨�����ͧ�����к�������ҨѴ�������ҧ���
 * @author Noom
 */
public class Rp18Control implements Runnable {

    public static int EXP_ALL = 0;
    public static int EXP_PASS = 1;
    public static int EXP_FAIL = 2;
    Thread hosControlThread;
    public String current_file;
    public String startDate;
    public String endDate;
    public String pathFile;
    public String typeFile;
    protected int nhso_mode;
    UpdateStatus theUS;
    public HosObject theHO;
    public GUISubject theGUISubject;
    private int ex_all;
    public static int STD_GOV = 0;
    public static int STD_NHSO = 1;
    public static int STD_PP = 2;
    public static int STD_NHSO53 = 3;
    public static int STD_PP53 = 4;

//henbe comment 100253 kong �������Ţ���
//    public static int STD_GOV53 = 0;
    private Report18FileName theReport18File;
    private DBSuit theDBSuit;
    public final ConnectionInf theConnectionInf;
    private Vector vRR;
    private String backup_path = "history";
    private PrintShowReportLogControl printShowReportLogControl;
    private String during;

    public Rp18Control(ConnectionInf c, UpdateStatus us, HosObject ho) {
        theUS = us;
        theHO = ho;
        theGUISubject = new GUISubject();
        theConnectionInf = c;
        printShowReportLogControl = new PrintShowReportLogControl();

    }

    public void setDataExport18File(Report18FileName theReport18File, String startDate, String endDate, String pathFile, String type, int all, int mode, DBSuit db) {
        this.theReport18File = theReport18File;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pathFile = pathFile;
        this.typeFile = type;
        this.ex_all = all;
        this.nhso_mode = mode;
        theDBSuit = db;
        if (typeFile.equals("0")) {
            typeFile = Constant.DBF_INDEX;
        } else {
            typeFile = Constant.TXT_INDEX;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        theHO.date_time = sdf.format(new Date());
    }

    public void startExport18File() throws Exception {
        vRR = new Vector();
        System.out.println("In method startExport18File");
        if (theGUISubject != null) {
            System.out.println("In method theGUISubject");
            theGUISubject.setEnabled(false);
            theGUISubject.onShowStatus("��س����ѡ���� ��й����������ѧ�͡��§ҹ", java.awt.Color.white);
            theGUISubject.onShowPicture("", true);
        }
        if (Constant.SELECTED.equals(theReport18File.Person)) {
            dbExport(theDBSuit.thePersonDB, startDate, endDate);
        }
        if (Constant.SELECTED.equals(theReport18File.Death)) {
            dbExport(theDBSuit.theDeathDB, startDate, endDate);
        }
        if (Constant.SELECTED.equals(theReport18File.Chronic)) {
            dbExport(theDBSuit.theChronicDB, startDate, endDate);
        }
        if (Constant.SELECTED.equals(theReport18File.Card)) {
            dbExport(theDBSuit.theCardDB, startDate, endDate);
        }
        if (Constant.SELECTED.equals(theReport18File.Service)) {
            dbExport(theDBSuit.theServiceDB, startDate, endDate);
        }
        if (Constant.SELECTED.equals(theReport18File.Diag)) {
            dbExport(theDBSuit.theDiagDB, startDate, endDate);
        }
        if (Constant.SELECTED.equals(theReport18File.Appoint)) {
            dbExport(theDBSuit.theAppointDB, startDate, endDate);
        }
        if (Constant.SELECTED.equals(theReport18File.Serveil)) {
            dbExport(theDBSuit.theServeilDB, startDate, endDate);
        }
        if (Constant.SELECTED.equals(theReport18File.Drug)) {
            dbExport(theDBSuit.theDrugDB, startDate, endDate);
        }
        if (Constant.SELECTED.equals(theReport18File.Proced)) {
            dbExport(theDBSuit.theProcedDB, startDate, endDate);
        }
        /*----------------------- ���������Ǣ�ͧ�Ѻ PCU �µç -----------------------*/
        if (Constant.SELECTED.equals(theReport18File.Woman)) {
            dbExport(theDBSuit.theWomanDB, startDate, endDate);
        }
        if (Constant.SELECTED.equals(theReport18File.Fp)) {
            dbExport(theDBSuit.theFpDB, startDate, endDate);
        }
        if (Constant.SELECTED.equals(theReport18File.Epi)) {
            dbExport(theDBSuit.theEpiDB, startDate, endDate);
        }
        if (Constant.SELECTED.equals(theReport18File.Nutrition)) {
//            if(theNutritionDB.intCheckMap()){
            dbExport(theDBSuit.theNutritionDB, startDate, endDate);
//            }
        }
        if (Constant.SELECTED.equals(theReport18File.Anc)) {
            dbExport(theDBSuit.theAncDB, startDate, endDate);
        }
        if (Constant.SELECTED.equals(theReport18File.Pp)) {
            dbExport(theDBSuit.thePpDB, startDate, endDate);
        }
        if (Constant.SELECTED.equals(theReport18File.Mch)) {
            dbExport(theDBSuit.theMchDB, startDate, endDate);
        }
        // System.out.println("* * * * * "+theReport18File.Mch);
        if (Constant.SELECTED.equals(theReport18File.Home)) {
            dbExport(theDBSuit.theHomeDB, startDate, endDate);
        }
        while (!theDBSuit.isFinish()) {
            Thread.sleep(1000);
        }
        /*--------------------- �����������Ǣ�ͧ�Ѻ PCU �µç -----------------------*/

        System.out.println("Finish export");
        if (theGUISubject != null) {
            theGUISubject.setEnabled(true);
            theGUISubject.onShowStatus("�͡��§ҹ���º�������� path ��������� " + pathFile, java.awt.Color.BLUE, true);
            theGUISubject.onShowPicture("", false);
        }
//        DialogAlert.showDialog(theDBSuit.getResult(),UpdateStatus.WARNING,theUS.getJFrame());
        Report18FileData.saveResult(pathFile + "/result18file.txt", theDBSuit.getResult());
///////////////////////////////////////////////////////////////////////////////////////////////
        this.during = PrintShowReportLogControl.getDuring(startDate, endDate);
        String dbName = PrintShowReportLogControl.getDBName(theDBSuit.theConnectionInf);
        String str = ResultRp.parseHtml(vRR, theHO.date_time, this.during, dbName);
        saveLogExport(str);
        DialogResultRp.showResult(theUS.getJFrame(), str);
        FileWriter fileErrorWriter = new FileWriter(pathFile + "/Rp18_" + theHO.date_time + ".html");
        fileErrorWriter.writeData(str);
        fileErrorWriter.closeFile();
        if (backup_path != null) {
            File file = new File(backup_path);
            if (!file.exists()) {
                file.mkdirs();
            }
            fileErrorWriter = new FileWriter(backup_path + File.separator + "Rp18_" + theHO.date_time + ".html");
            fileErrorWriter.writeData(str);
            fileErrorWriter.closeFile();
        }

        //////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Stop -- Thread----");
    }

    public void dbExport(Rp18DB theFile18DB, String startDate, String endDate) throws Exception {
        if (theFile18DB == null) {
            return;
        }
        theUS.setStatus("��س����ѡ���� ��й����������ѧ�͡��§ҹ��� " + theFile18DB.getFileName(), UpdateStatus.WARNING);
        ResultRp rr = new ResultRp();
        rr.current_file = theFile18DB.getFileName();
        vRR.add(rr);
        String path = "";
        if (nhso_mode == Rp18Control.STD_NHSO53 || nhso_mode == Rp18Control.STD_PP53) {
            String fileName = rr.current_file;
            if(fileName.length()>3){
                fileName = fileName.substring(0, 3);
            }
            path = pathFile + Util.getHeadFile(startDate, theHO.date_time, theHO.theSite.off_id, fileName.toUpperCase(), nhso_mode, typeFile);
            String yearMonth[] = startDate.split("-");
            String yyyy = String.valueOf(Integer.parseInt(yearMonth[0]) - 543);
            String nowDate[] = com.hosv3.utility.DateUtil.getTextCurrentDate(theDBSuit.theConnectionInf).split("/");
            String nowYear = String.valueOf(Integer.parseInt(nowDate[0]) - 543);
            if (path.lastIndexOf(".dbf") > 0) {
                path = path.replaceAll(".dbf", "") + "_" + theHO.theSite.off_id + "_" + yyyy + yearMonth[1] + "_" + nowYear + nowDate[1] + nowDate[2] + ".dbf";
            } else if (path.lastIndexOf(".txt") > 0) {
                path = path.replaceAll(".txt", "") + "_" + theHO.theSite.off_id + "_" + yyyy + yearMonth[1] + "_" + nowYear + nowDate[1] + nowDate[2] + ".txt";
            }
        } else {
            path = pathFile + Util.getHeadFile(startDate, theHO.date_time, theHO.theSite.off_id, rr.current_file.toUpperCase(), nhso_mode, typeFile);

        }
        if (theFile18DB instanceof EpiDB) {
            EpiDB epidb = (EpiDB) theFile18DB;
            theFile18DB.setStringBuffer(new StringBuffer(), ex_all);
            epidb.exportFileEpi(startDate, endDate, path, typeFile, rr.current_file, rr, theUS);
        } else {
            theFile18DB.exportFile(startDate, endDate, path, typeFile, rr.current_file, rr, theUS);
            theFile18DB.setStringBuffer(new StringBuffer(), ex_all);
            theFile18DB.start();
        }
        printShowReportLogControl.addDocName(path);
    }

    public void start() {
        hosControlThread = new Thread(this);
        hosControlThread.start();
    }

    public void stop() {
        theDBSuit.stop();
        theGUISubject.onShowStatus(Constant.STATUS_CANCEL_REPORT, java.awt.Color.YELLOW);
    }

    public void run() {
        try {
            startExport18File();
        } catch (Exception e) {
            theGUISubject.onShowStatus("����͡��§ҹ " + current_file + " �Դ��Ҵ", java.awt.Color.RED);
            e.printStackTrace();
            //clear vector
            printShowReportLogControl.getAllDocName();

        }
    }

    /*ALTER TABLE "public"."r_rplog_export"
     *	ADD COLUMN "file_name" varchar(255) NULL
     *
     *ALTER TABLE "public"."r_rplog_export"
     *	ADD COLUMN "file_export_name" varchar(255) NULL
     */
    public int saveLogExport(String str) {
        try {
            theConnectionInf.open();
            StringBuffer sql = new StringBuffer("insert into r_rplog_export values('");
            sql.append(theHO.date_time);//id
            sql.append("','").append(theHO.date_time);//date_time
            sql.append("','").append(ResultRp.getNameAddress());//ip_address
            sql.append("','").append(theHO.theEmployee.getObjectId());//b_employee_id
            sql.append("',?");//result
            sql.append(",'").append(Constant.REPORT_NAME[0]);//file_name
            sql.append("','").append(printShowReportLogControl.getAllDocName());//file_export_name
            sql.append("','").append(this.during);//during
            sql.append("');");
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