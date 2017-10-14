/*
 * RP115Group1_2549Control.java
 *
 * Created on 12 กันยายน 2548, 9:36 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.control;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.control.HosControl;
import com.hosv3.object.UseCase;
import com.standardReport.object.RP115Group1IPD;
import com.standardReport.object.RP115Group1OPD;
import com.standardReport.object.RP115Group1PCU;
import com.standardReport.object.RP115Group1Refer;
import com.standardReport.object.RP115Group1_2549;
import com.standardReport.utility.*;
import java.util.Vector;
import java.io.File;
import javax.swing.JFrame;
/**
 *
 * @author nu_ojika
 */
public class RP115Group1_2549Control {
    
    /**
     * Creates a new instance of RP115Group1Control 
     */
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vc = null;
    private FileWriter fileWriter;
    
    private RP115Group1_2549 theRP115Group1_2549;
    private   HosControl theHC;
    
    public RP115Group1_2549Control() {
    }
    
    public RP115Group1_2549Control(com.hosv3.control.HosControl hc,ConnectionInf con,HosDB hdb)
    {
        theHC = hc;
        theConnectionInf = con;
        theHosDB = hdb;
        fileWriter = new FileWriter();
    }
    
    public Vector selectGroup1OPDByDate(String startDate, String endDate, boolean isDbBackup)
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
            theHosDB.theRP115Group1_2549DB.theConnectionInf = theConnectionInf;
            vc = theHosDB.theRP115Group1_2549DB.selectGroup1OPDByDate(startDate,endDate,startCheckDate,endCheckDate,isJan);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part1_opd.sql",
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part1_opd.sql", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector selectGroup1IPDByDate(String startDate, String endDate,boolean isDbBackup)
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
            theHosDB.theRP115Group1_2549DB.theConnectionInf = theConnectionInf;
            vc = theHosDB.theRP115Group1_2549DB.selectGroup1IPDByDate(startDate,endDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part1_ipd.sql",
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part1_ipd.sql", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector selectGroup1PCUByDate(String startDate, String endDate,boolean isDbBackup)
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
            theHosDB.theRP115Group1_2549DB.theConnectionInf = theConnectionInf;
            vc = theHosDB.theRP115Group1_2549DB.selectGroup1PCUByDate(startDate,endDate,startCheckDate,endCheckDate,isJan);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part1_pcu.sql",
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part1_pcu.sql", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector selectGroup1ReferByDate(String startDate, String endDate,boolean isDbBackup)
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
            theHosDB.theRP115Group1_2549DB.theConnectionInf = theConnectionInf;
            vc = theHosDB.theRP115Group1_2549DB.selectGroup1ReferByDate(startDate,endDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part1_refer.sql",
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part1_refer.sql", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector setVectorAllGroup1(Vector vGroup1OPD, Vector vGroup1IPD, Vector vGroup1Refer, Vector vGroup1PCU)
    {
        vc = new Vector();
        theRP115Group1_2549 = new RP115Group1_2549();
        
        RP115Group1OPD theRP115Group1OPD;
        RP115Group1IPD theRP115Group1IPD;
        RP115Group1PCU theRP115Group1PCU;
        RP115Group1Refer theRP115Group1Refer;
        
        if((vGroup1OPD != null && vGroup1OPD.size() > 0) && (vGroup1IPD != null && vGroup1IPD.size() > 0) && (vGroup1Refer != null && vGroup1Refer.size() > 0))
        {
            // รวม Vector
            for(int i=0,sizeReport=vGroup1OPD.size();i<sizeReport;i++)
            {
                // Object ของส่วนที่ 1 ทั้งหมด
                theRP115Group1_2549 = new RP115Group1_2549();
                theRP115Group1_2549.setInitData();
                
                // OPD
                theRP115Group1OPD = new RP115Group1OPD();
                theRP115Group1OPD = (RP115Group1OPD)vGroup1OPD.get(i);
                if(theRP115Group1OPD != null)
                {
                    theRP115Group1_2549.plan_type = theRP115Group1OPD.opd_plan_type;
                    theRP115Group1_2549.opd_new_patient_incup = theRP115Group1OPD.opd_new_patient_incup;
                    theRP115Group1_2549.opd_new_patient_outcup = theRP115Group1OPD.opd_new_patient_outcup;
                    theRP115Group1_2549.opd_visit_incup = theRP115Group1OPD.opd_visit_incup;
                    theRP115Group1_2549.opd_visit_outcup = theRP115Group1OPD.opd_visit_outcup;
                }
                theRP115Group1OPD = null;
                
                // IPD
                theRP115Group1IPD = new RP115Group1IPD();
                theRP115Group1IPD = (RP115Group1IPD)vGroup1IPD.get(i);
                if(theRP115Group1IPD != null)
                {
                    theRP115Group1_2549.ipd_discharge_incup = theRP115Group1IPD.ipd_discharge_incup;
                    theRP115Group1_2549.ipd_discharge_outcup = theRP115Group1IPD.ipd_discharge_outcup;
                    theRP115Group1_2549.ipd_day_stay_incup = theRP115Group1IPD.ipd_day_stay_incup;
                    theRP115Group1_2549.ipd_day_stay_outcup = theRP115Group1IPD.ipd_day_stay_outcup;
                }
                theRP115Group1IPD = null;
                
                // PCU
                // ยกเลิกก่อน รอดูว่าฐานข้อมูลเก็บอย่างไรกันแน่ 25 กันยายน 2548 ojika
                // ใช้ใหม่เพราะเปลี่ยนฐานข้อมูลให้ใช้ด้วยกัน 03 พย 48
                theRP115Group1PCU = new RP115Group1PCU();
                theRP115Group1PCU = (RP115Group1PCU)vGroup1PCU.get(i);
                if(theRP115Group1PCU != null)
                {
                    theRP115Group1_2549.pcu_new_patient_incup = theRP115Group1PCU.pcu_new_patient_incup;
                    theRP115Group1_2549.pcu_new_patient_outcup = theRP115Group1PCU.pcu_new_patient_outcup;
                    theRP115Group1_2549.pcu_visit_incup = theRP115Group1PCU.pcu_visit_incup;
                    theRP115Group1_2549.pcu_visit_outcup = theRP115Group1PCU.pcu_visit_outcup;
                }
                theRP115Group1PCU = null;
                
                // Refer
                theRP115Group1Refer = new RP115Group1Refer();
                theRP115Group1Refer = (RP115Group1Refer)vGroup1Refer.get(i);
                if(theRP115Group1Refer != null)
                {
                    theRP115Group1_2549.refer_in_incup = theRP115Group1Refer.refer_in_incup;
                    theRP115Group1_2549.refer_in_inchangwat = theRP115Group1Refer.refer_in_inchangwat;
                    theRP115Group1_2549.refer_in_outchangwat = theRP115Group1Refer.refer_in_outchangwat;
                    theRP115Group1_2549.refer_out_incup = theRP115Group1Refer.refer_out_incup;
                    theRP115Group1_2549.refer_out_inchangwat = theRP115Group1Refer.refer_out_inchangwat;
                    theRP115Group1_2549.refer_out_outchangwat = theRP115Group1Refer.refer_out_outchangwat;
                }
                theRP115Group1Refer = null;
                     
                vc.add(theRP115Group1_2549);
                theRP115Group1_2549 = null;
            }
        }
        
        
        return vc;
    }
    
}
