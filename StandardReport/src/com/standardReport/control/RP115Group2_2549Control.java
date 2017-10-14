/*
 * RP115Group2_2549Control.java
 *
 * Created on 13 กันยายน 2548, 16:27 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.control;

import java.util.Vector;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.control.HosControl;
import com.hosv3.object.UseCase;
import com.standardReport.object.RP115Group2Family_2549;
import com.standardReport.object.RP115Group2Post_2549;
import com.standardReport.object.RP115Group2Pre_2549;
import com.standardReport.object.RP115Group2_2549;
import com.standardReport.object.Rp115_2;
import com.standardReport.utility.*;

import java.util.Vector;
import java.io.File;
import javax.swing.JFrame;
/**
 *
 * @author nu_ojika
 */
public class RP115Group2_2549Control {
    
    /** Creates a new instance of RP115Group2_2549Control */
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vc = null;
    private FileWriter fileWriter;
    
    private RP115Group2_2549 theRP115Group2_2549;
    private   HosControl theHC;
    
    public RP115Group2_2549Control() {
    }
    
    public RP115Group2_2549Control(com.hosv3.control.HosControl hc,ConnectionInf con,HosDB hdb)
    {
        theHC = hc;
        theConnectionInf = con;
        theHosDB = hdb;
        fileWriter = new FileWriter();
    }

    
    public Vector selectGroup2PreByDate(String startDate, String endDate, boolean isDbBackup)
    {
        vc = new Vector();
        if(isDbBackup){
            theConnectionInf = com.hosv3.utility.Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theHosDB.theConnectionInf;
        }
        theConnectionInf.open();
        try
        {
            theHosDB.theRP115Group2_2549DB.theConnectionInf = theConnectionInf;
            vc = theHosDB.theRP115Group2_2549DB.selectGroup2PreByDate(startDate,endDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part2_pre.sql",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    endDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part2_pre.sql", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector selectGroup2PostByDate(String startDate, String endDate, boolean isDbBackup)
    {
        vc = new Vector();
        if(isDbBackup){
            theConnectionInf = com.hosv3.utility.Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theHosDB.theConnectionInf;
        }
        theConnectionInf.open();
        try
        {
            theHosDB.theRP115Group2_2549DB.theConnectionInf = theConnectionInf;
            vc = theHosDB.theRP115Group2_2549DB.selectGroup2PostByDate(startDate,endDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part2_post.sql",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    endDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part2_post.sql", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
     public Vector selectGroup2FamilyByDate(String startDate, String endDate, boolean isDbBackup)
    {
        vc = new Vector();
        // ตัด String สำหรับตรวจสอบผู้ป่วยรายใหม่ในปี 
        String startCheckDate = "";
        String endCheckDate = "";
        boolean isJan = false;
        
        if(startDate.length() >= 10)
        {
            if(("01").equalsIgnoreCase(startDate.substring(5, 7)))
            {
                isJan = true;
            }
            else
            {
                startCheckDate = startDate.substring(0, 4) + "-01";
                endCheckDate = startDate.substring(0, 7);
                isJan = false;
            } 
        }
        if(isDbBackup){
            theConnectionInf = com.hosv3.utility.Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theHosDB.theConnectionInf;
        }
        theConnectionInf.open();
        try
        {
            theHosDB.theRP115Group2_2549DB.theConnectionInf = theConnectionInf;
            vc = theHosDB.theRP115Group2_2549DB.selectGroup2FamilyByDate(startDate,endDate, startCheckDate ,endCheckDate, isJan);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part2_familyplaning.sql",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    endDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part2_familyplaning.sql", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector setVectorAllGroup2(Vector vGroup2Pre, Vector vGroup2Post, Vector vGroup2Family)
    {
        vc = new Vector();
        theRP115Group2_2549 = new RP115Group2_2549();
        RP115Group2Pre_2549 theRP115Group2Pre_2549;
        RP115Group2Post_2549 theRP115Group2Post_2549;
        RP115Group2Family_2549 theRP115Group2Family_2549;
        
        if((vGroup2Pre != null && vGroup2Pre.size() > 0) && (vGroup2Post != null && vGroup2Post.size() > 0) && (vGroup2Family != null && vGroup2Family.size() > 0))
        {
            // รวม Vector
            System.out.println("********************vGroup2Pre.size()** "+ vGroup2Pre.size());
            for(int i=0,sizeReport=vGroup2Pre.size();i<sizeReport;i++)
            {
                // เซ็ทค่าเริ่มต้นของ Object ทั้งหมดก่อนในทุกรอบ
                theRP115Group2_2549 = new RP115Group2_2549();
                theRP115Group2_2549.setInitData();
                
                // ส่วนข้อมูลที่1 
                theRP115Group2Pre_2549 = new RP115Group2Pre_2549();
                theRP115Group2Pre_2549 = (RP115Group2Pre_2549)vGroup2Pre.get(i);
                if(theRP115Group2Pre_2549 != null)
                {
                    theRP115Group2_2549.plan_type = theRP115Group2Pre_2549.plan_type;
                    theRP115Group2_2549.abort = theRP115Group2Pre_2549.abort;
                    theRP115Group2_2549.birth_and_death = theRP115Group2Pre_2549.birth_and_death;
                    theRP115Group2_2549.baby_death = theRP115Group2Pre_2549.baby_death;
                    theRP115Group2_2549.mother_death = theRP115Group2Pre_2549.mother_death;
                    theRP115Group2_2549.ipd_death = theRP115Group2Pre_2549.ipd_death;
                    theRP115Group2_2549.opd_death = theRP115Group2Pre_2549.opd_death;
                }
                theRP115Group2Pre_2549 = null;
                
                // ส่วนข้อมูลที่2 
                theRP115Group2Post_2549 = new RP115Group2Post_2549();
                theRP115Group2Post_2549 = (RP115Group2Post_2549)vGroup2Post.get(i);
                if(theRP115Group2Post_2549 != null)
                {
                    theRP115Group2_2549.before_mch_patient = theRP115Group2Post_2549.before_mch_patient;
                    theRP115Group2_2549.before_mch_visit = theRP115Group2Post_2549.before_mch_visit;
                    theRP115Group2_2549.after_mch_patient = theRP115Group2Post_2549.after_mch_patient;
                    theRP115Group2_2549.after_mch_visit = theRP115Group2Post_2549.after_mch_visit;
                    theRP115Group2_2549.birth_usual_infant = theRP115Group2Post_2549.birth_usual_infant;
                    theRP115Group2_2549.birth_un_usual_infant = theRP115Group2Post_2549.birth_un_usual_infant;
                    theRP115Group2_2549.treatment_dental = theRP115Group2Post_2549.treatment_dental;
                }
                theRP115Group2Post_2549 = null;       
              
                 // ส่วนข้อมูล วางแผนครอบครัว
                theRP115Group2Family_2549 = new RP115Group2Family_2549();
                theRP115Group2Family_2549 = (RP115Group2Family_2549)vGroup2Family.get(i);
                if(theRP115Group2Family_2549 != null)
                {
                    System.out.println("*************************What Null "+ theRP115Group2_2549);
                    theRP115Group2_2549.childless_female = theRP115Group2Family_2549.childless_female;
                    theRP115Group2_2549.childless_male = theRP115Group2Family_2549.childless_male;
                    theRP115Group2_2549.childless_drug = theRP115Group2Family_2549.childless_drug;
                    theRP115Group2_2549.childless_other = theRP115Group2Family_2549.childless_other;
                }
                theRP115Group2Family_2549 = null;
                
                vc.add(theRP115Group2_2549);
                
                theRP115Group2_2549 = null;  
                
            }
        }
        System.out.println("****************VC *** "+ vc.size());
        return vc;
    }
}
