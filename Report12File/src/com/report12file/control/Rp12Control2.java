/*
 * HosDB.java
 *
 * Created on 28 กรกฎาคม 2548, 13:26 น.
 */

package com.report12file.control;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.report12file.objdb.Rp12DB2;
import com.hosv3.object.HosObject;
import com.hosv3.utility.DateUtil;
import com.hosv3.utility.connection.UpdateStatus;
import com.report12file.gui.DBSuit53;
import com.report12file.subject.GUISubject;
import com.report12file.utility.Language;
import com.report12file.utility.Constant;
import com.reportcenter.utility.PrintShowReportLogControl;
import com.report12file.utility.Report12FileData;
import com.reportcenter.gui.dialog.DialogResultRp;
import com.reportcenter.object.ResultRp;
import com.reportcenter.utility.FileWriter;
import com.reportcenter.utility.Util;
import java.io.File;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
/**
 * มาเขียนอธิบายด้วยว่ามีเพื่ออะไรแล้ว rp12control มีปัญหาอย่างไร
 * @author henbe
 */
public class Rp12Control2 implements Runnable
{

    Thread hosControlThread;
    public ConnectionInf theConnectionInf;
    public String current_file;
    public String startDate;
    public String endDate;
    public String pathFile;
    public String typeFile;
    protected int nhso_mode;
    public UpdateStatus theUS;
    private HosObject theHO;
    public GUISubject theGUISubject;
    private int ex_all;
    private DBSuit theDBSuit;
    private Vector vRR;
    private String backup_path = "history";
    private PrintShowReportLogControl printShowReportLogControl ;
    private int[] theRows;
    private String during;
   
    public Rp12Control2(ConnectionInf c,UpdateStatus us,HosObject ho) {
        theConnectionInf = c;
        theUS = us;
        theHO = ho;
        theGUISubject = new GUISubject();
        printShowReportLogControl = new PrintShowReportLogControl();
    }

    public boolean setDataExport12File(int[] rows,String startDate, String endDate
            ,String pathFile,String type,int all,int mode,UpdateStatus us,ConnectionInf con
            , boolean isDbBackup)
    { 
        this.startDate = startDate;
        this.endDate = endDate;
        this.pathFile = pathFile;
        this.typeFile = type;
        this.ex_all = all;
        this.theRows = rows;
        this.nhso_mode = mode;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        theHO.date_time = sdf.format(new Date());

        if (rows.length == 0) {
//            theGUISubject.onShowStatus("กรุณาเลือกรายงานที่ต้องการ", java.awt.Color.YELLOW);
            theUS.setStatus("กรุณาเลือกรายงานที่ต้องการ", UpdateStatus.WARNING);
            return false;
        }
        if (startDate.compareTo(endDate) > 0) {
//            theGUISubject.onShowStatus(Language.getTextBundle("Warning", 1), java.awt.Color.YELLOW);
            theUS.setStatus(Language.getTextBundle("Warning", 1), UpdateStatus.WARNING);
            return false;
        }
        if(isDbBackup){
            ConnectionInf theConnectionInfDBBackup = com.hosv3.utility.Config.getConnectionInfDBBackupFromFile(theUS.getJFrame());
            theDBSuit = DBSuit53.getDBSuit(us, theConnectionInfDBBackup);
        }else{
            theDBSuit = DBSuit53.getDBSuit(us, con);
        }
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
        theDBSuit.stop();
        theDBSuit = null;
        if (hosControlThread != null) {
//            theConnectionInf.close();
            hosControlThread.stop();
        }
        hosControlThread = null;
        System.out.println("In stop in HosControl");
        theGUISubject.setEnabled(true);
        theGUISubject.onShowStatus(Constant.STATUS_CANCEL_REPORT, java.awt.Color.GREEN);
    }
    
    public void startExport12File()
    {
        try{
            vRR = new Vector();
            System.out.println("In method startExport12File");
            if(theGUISubject != null){
                System.out.println("In method theGUISubject");
                theGUISubject.setEnabled(false);
//                theGUISubject.onShowStatus("กรุณารอสักครู่ ขณะนี้โปรแกรมกำลังออกรายงาน", java.awt.Color.white);
                theUS.setStatus("กรุณารอสักครู่ ขณะนี้โปรแกรมกำลังออกรายงาน", UpdateStatus.WARNING);
                theGUISubject.onShowPicture("",true);
            }
            for (int i = 0; i < theRows.length; i++) {
                if (theRows[i] == 0) {
                    dbExport(theDBSuit.theInsDB, startDate, endDate);
                } else if (theRows[i] == 1) {
                    dbExport(theDBSuit.thePatDB, startDate, endDate);
                } else if (theRows[i] == 2) {
                    dbExport(theDBSuit.theOpdDB, startDate, endDate);
                } else if (theRows[i] == 3) {
                    dbExport(theDBSuit.theOrfDB, startDate, endDate);
                } else if (theRows[i] == 4) {
                    dbExport(theDBSuit.theOdxDB, startDate, endDate);
                } else if (theRows[i] == 5) {
                    dbExport(theDBSuit.theOopDB, startDate, endDate);
                } else if (theRows[i] == 6) {
                    dbExport(theDBSuit.theIpdDB, startDate, endDate);
                } else if (theRows[i] == 7) {
                    dbExport(theDBSuit.theIrfDB, startDate, endDate);
                } else if (theRows[i] == 8) {
                    dbExport(theDBSuit.theIdxDB, startDate, endDate);
                } else if (theRows[i] == 9) {
                    dbExport(theDBSuit.theIopDB, startDate, endDate);
                } else if (theRows[i] == 10) {
                    dbExport(theDBSuit.theChtDB, startDate, endDate);
                } else if (theRows[i] == 11) {
                    dbExport(theDBSuit.theChaDB, startDate, endDate);
                } else if (theRows[i] == 12) {
                    dbExport(theDBSuit.theAerDB, startDate, endDate);
                } else if (theRows[i] == 13) {
                    dbExport(theDBSuit.theAdpDB, startDate, endDate);
//                } else if (theRows[i] == 14) {
//                    dbExport(theDBSuit.theLvdDB, startDate, endDate);
                } else if (theRows[i] == 14) {
                    dbExport(theDBSuit.theDrgDB, startDate, endDate);
                }
            }
            while(!theDBSuit.isFinish()){
                Thread.sleep(1000);
           }
            /*--------------------- จบไฟล์ที่เกี่ยวข้องกับ PCU โดยตรง -----------------------*/

            System.out.println("Finish export");
              if(theGUISubject != null){
                    theGUISubject.setEnabled(true);
                   theGUISubject.onShowStatus("ออกรายงานเรียบร้อยแล้ว path ไฟล์อยู่ที่ "+pathFile, java.awt.Color.BLUE,true);
                   theGUISubject.onShowPicture("",false);
              }
            Report12FileData.saveResult(pathFile + "/result12file.txt",theDBSuit.getResult());
    ///////////////////////////////////////////////////////////////////////////////////////////////
            this.during = PrintShowReportLogControl.getDuring(startDate, endDate);
            String dbName = PrintShowReportLogControl.getDBName(theDBSuit.theConnectionInf);
            String str = ResultRp.parseHtml(vRR,theHO.date_time,this.during,dbName);
            saveLogExport(str);
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

           //////////////////////////////////////////////////////////////////////////////////////////////
            System.out.println("Stop -- Thread----");
        }
        catch(Exception e){
            theGUISubject.onShowStatus("การออกรายงาน "+current_file+" ผิดพลาด", java.awt.Color.RED);
            e.printStackTrace();
            //clear vector
            printShowReportLogControl.getAllDocName();

        }
    } 
    public void dbExport(Rp12DB2 theFile12DB,String startDate, String endDate) throws Exception
    {
        if(theFile12DB==null){
            return ;
        }
        theUS.setStatus("กรุณารอสักครู่ ขณะนี้โปรแกรมกำลังออกรายงานแฟ้ม " + theFile12DB.getFileName(), UpdateStatus.WARNING);
        ResultRp rr = new ResultRp();
        rr.current_file = theFile12DB.getFileName().toUpperCase();
        vRR.add(rr);
        String path = pathFile + Util.getHeadFile(startDate,theHO.date_time,theHO.theSite.off_id
                ,rr.current_file,nhso_mode,typeFile);

        if(nhso_mode == Rp12Control.MODE_ECLM53){
            String yearMonth[] = startDate.split("-");
            String yyyy = String.valueOf(Integer.parseInt(yearMonth[0])).substring(2, 4);
            if(path.lastIndexOf(".dbf") > 0){
                path = path.replaceAll(".dbf", "") + yyyy + yearMonth[1] + ".dbf";
            }else if(path.lastIndexOf(".txt") > 0){
                path = path.replaceAll(".txt", "") + yyyy + yearMonth[1] + ".txt";
            }
        } else if(nhso_mode == Rp12Control.MODE_OPPP53){
            String yearMonth[] = startDate.split("-");
            String yyyy = String.valueOf(Integer.parseInt(yearMonth[0]) - 543);
            String nowDate[] = DateUtil.getTextCurrentDate(theFile12DB.theConnectionInf).split("/");
            String nowYear = String.valueOf(Integer.parseInt(nowDate[0]) - 543);
            if(path.lastIndexOf(".dbf") > 0){
                path = path.replaceAll(".dbf", "") + "_" + theHO.theSite.off_id + "_" + yyyy + yearMonth[1] + "_" + nowYear + nowDate[1] + nowDate[2] + ".dbf";
            }else if(path.lastIndexOf(".txt") > 0){
                path = path.replaceAll(".txt", "") + "_" + theHO.theSite.off_id + "_" + yyyy + yearMonth[1] + "_" + nowYear + nowDate[1] + nowDate[2] + ".txt";
            }
        }
            theFile12DB.exportFile(startDate,endDate,path,typeFile,rr.current_file,rr,theUS);
            theFile12DB.setStringBuffer(new StringBuffer(),ex_all);
            theFile12DB.start();
        printShowReportLogControl.addDocName(path);
    }   

/*ALTER TABLE "public"."r_rplog_export"
*	ADD COLUMN "file_name" varchar(255) NULL
*
*ALTER TABLE "public"."r_rplog_export"
*	ADD COLUMN "file_export_name" varchar(255) NULL
*
*ALTER TABLE "public"."r_rplog_export"
*	ADD COLUMN "database_name" varchar(30) NULL
*
*ALTER TABLE "public"."r_rplog_export"
*	ADD COLUMN "during" varchar(25) NULL
*/

    public int saveLogExport(String str) {
        try {
            //String[] url= theDBSuit.theConnectionInf.getURL().split("/");
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
            ps.setString(1,str);

            return ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        } finally{
            theConnectionInf.close();
        }
    }

}
