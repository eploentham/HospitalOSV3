/*
 * RP115Group3_2549Control.java
 *
 * Created on 16 �չҤ� 2549, 18:38 �.
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
import com.standardReport.object.RP115Group3DentalProtect_2549;
import com.standardReport.object.RP115Group3Epi_2549;
import com.standardReport.object.RP115Group3Healthy_2549;
import com.standardReport.object.RP115Group3MMR_2549;
import com.standardReport.object.RP115Group3Nutrition_2549;
import com.standardReport.object.RP115Group3_2549;
import com.standardReport.object.Rp115_3;
import com.standardReport.object.checkDataObject;
import com.standardReport.utility.*;

import java.util.Vector;
import java.io.File;
import javax.swing.JFrame;
/**
 *
 * @author Ojika
 */
public class RP115Group3_2549Control
{
    
    /** Creates a new instance of RP115Group3_2549Control */
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vc = null;
    private FileWriter fileWriter;
    
    private RP115Group3_2549 theRP115Group3_2549;
    private checkDataObject thecheckDataObject;
    private   HosControl theHC;
    
    public RP115Group3_2549Control(com.hosv3.control.HosControl hc,ConnectionInf con,HosDB hdb)
    {
        theHC = hc;
        theConnectionInf = con;
        theHosDB = hdb;
        fileWriter = new FileWriter();
    }

    
    /**
     * �֧�����š����������آ�Ҿ �� 0-5 �� , 6-14 �� ��� 15 �բ���
     * @Author Ojika
     * @Date 17/03/2549
     **/
    public Vector selectGroup3HealthyByDate(String startDate, String endDate, boolean isDbBackup)
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
            theHosDB.theRP115Group3_2549DB.theConnectionInf = theConnectionInf;
            vc = theHosDB.theRP115Group3_2549DB.selectGroup3HealthyByDate(startDate,endDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part3_healthy.sql",
                    "�ҹ������ : "+
                    theConnectionInf.getURL()+
                    " ��ǧ���� "+
                    startDate+"-"+
                    endDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part3_healthy.sql", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    /**
     * �֧������ �Ѥ�չ �� 0-5 �� 
     * @Author Ojika
     * @Date 17/03/2549
     **/
    public Vector selectGroup3EpiByDate(String startDate, String endDate, boolean isDbBackup)
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
            theHosDB.theRP115Group3_2549DB.theConnectionInf = theConnectionInf;
            vc = theHosDB.theRP115Group3_2549DB.selectGroup3EpiByDate(startDate,endDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part3_epi.sql",
                    "�ҹ������ : "+
                    theConnectionInf.getURL()+
                    " ��ǧ���� "+
                    startDate+"-"+
                    endDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part3_epi.sql", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    /**
     * �֧������ Dental Protect �� 0-5 �� , 6-14 �� 
     * @Author Ojika
     * @Date 17/03/2549
     **/
    public Vector selectGroup3DentalProtectByDate(String startDate, String endDate, boolean isDbBackup)
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
            theHosDB.theRP115Group3_2549DB.theConnectionInf = theConnectionInf;
            vc = theHosDB.theRP115Group3_2549DB.selectGroup3DentalProtectByDate(startDate,endDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part3_dentalprotect.sql",
                    "�ҹ������ : "+
                    theConnectionInf.getURL()+
                    " ��ǧ���� "+
                    startDate+"-"+
                    endDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part3_dentalprotect.sql", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    /**
     * �֧������ MMR 6-14 �� 
     * @Author Ojika
     * @Date 17/03/2549
     **/
    public Vector selectGroup3MMRByDate(String startDate, String endDate, boolean isDbBackup)
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
            theHosDB.theRP115Group3_2549DB.theConnectionInf = theConnectionInf;
            vc = theHosDB.theRP115Group3_2549DB.selectGroup3MMRByDate(startDate,endDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part3_MMR.sql",
                    "�ҹ������ : "+
                    theConnectionInf.getURL()+
                    " ��ǧ���� "+
                    startDate+"-"+
                    endDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part3_MMR.sql", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    /**
     * �֧������ ��������ҡ�� �� 0-5 �� , 6-14 �� ��� 15 �բ���
     * @Author Ojika
     * @Date 17/03/2549
     **/
    public Vector selectGroup3NutritionByDate(String startDate, String endDate, boolean isDbBackup)
    {
        vc = new Vector();
        
        // �Ѵ String ����Ѻ��Ǩ�ͺ�������������㹻� 
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
            theHosDB.theRP115Group3_2549DB.theConnectionInf = theConnectionInf;
            vc = theHosDB.theRP115Group3_2549DB.selectGroup3NutritionByDate(startDate,endDate,startCheckDate,endCheckDate, isJan);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part3_nutrition.sql",
                    "�ҹ������ : "+
                    theConnectionInf.getURL()+
                    " ��ǧ���� "+
                    startDate+"-"+
                    endDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part3_nutrition.sql", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    /**
     * �������������� Vector ���ǡѹ
     * @Author Ojika
     * @Date 17/03/2549
     **/
    public Vector setVectorAllGroup3(Vector vGroup3Healthy, Vector vGroup3Epi, Vector vGroup3DentalProtect, Vector vGroup3MMR, Vector vGroup3Nutrition)
    {
        vc = new Vector();
        theRP115Group3_2549 = new RP115Group3_2549();
        
        RP115Group3Healthy_2549 theRP115Group3Healthy_2549;
        RP115Group3Epi_2549 theRP115Group3Epi_2549;
        RP115Group3DentalProtect_2549 theRP115Group3DentalProtect_2549;
        RP115Group3MMR_2549 theRP115Group3MMR_2549;
        RP115Group3Nutrition_2549 theRP115Group3Nutrition_2549;
        
        if((vGroup3Healthy != null && vGroup3Healthy.size() > 0) 
            && (vGroup3Epi != null && vGroup3Epi.size() > 0) 
            && (vGroup3DentalProtect != null && vGroup3DentalProtect.size() > 0) 
            && (vGroup3MMR != null && vGroup3MMR.size() > 0)
            && (vGroup3Nutrition != null && vGroup3Nutrition.size() > 0))
        {
            // ��� Vector
            System.out.println("********************vGroup3Healthy.size()** "+ vGroup3Healthy.size());
            for(int i=0,sizeReport=vGroup3Healthy.size();i<sizeReport;i++)
            {
                // �緤��������鹢ͧ Object ��������͹㹷ء�ͺ
                theRP115Group3_2549 = new RP115Group3_2549();
                theRP115Group3_2549.setInitData(); 
                
                // ��ǹ������ Healthy
                theRP115Group3Healthy_2549 = new RP115Group3Healthy_2549();
                theRP115Group3Healthy_2549 = (RP115Group3Healthy_2549)vGroup3Healthy.get(i);
                if(theRP115Group3Healthy_2549 != null)
                {
                    theRP115Group3_2549.plan_type = theRP115Group3Healthy_2549.plan_type;
                    theRP115Group3_2549.wellbaby = theRP115Group3Healthy_2549.wellbaby;
                    theRP115Group3_2549.healthy614 = theRP115Group3Healthy_2549.healthy614;
                    theRP115Group3_2549.healthy15Up = theRP115Group3Healthy_2549.healthy15Up;
                }
                theRP115Group3Healthy_2549 = null;
                
                // ��ǹ������ Epi 
                theRP115Group3Epi_2549 = new RP115Group3Epi_2549();
                theRP115Group3Epi_2549 = (RP115Group3Epi_2549)vGroup3Epi.get(i);
                if(theRP115Group3Epi_2549 != null)
                {
                    theRP115Group3_2549.DPT = theRP115Group3Epi_2549.DPT;
                    theRP115Group3_2549.BCG = theRP115Group3Epi_2549.BCG;
                    theRP115Group3_2549.MMR = theRP115Group3Epi_2549.MMR;
                    theRP115Group3_2549.Hep = theRP115Group3Epi_2549.Hep;
                }
                theRP115Group3Epi_2549 = null;       
              
                // ��ǹ������ DentalProtect
                theRP115Group3DentalProtect_2549 = new RP115Group3DentalProtect_2549();
                theRP115Group3DentalProtect_2549 = (RP115Group3DentalProtect_2549)vGroup3DentalProtect.get(i);
                if(theRP115Group3DentalProtect_2549 != null)
                {
                    theRP115Group3_2549.dentalProtect_baby = theRP115Group3DentalProtect_2549.dentalProtect_baby;
                    theRP115Group3_2549.dentalProtect_child = theRP115Group3DentalProtect_2549.dentalProtect_child;
                }
                theRP115Group3DentalProtect_2549 = null;
                
                // ��ǹ������ MMR
                theRP115Group3MMR_2549 = new RP115Group3MMR_2549();
                theRP115Group3MMR_2549 = (RP115Group3MMR_2549)vGroup3MMR.get(i);
                if(theRP115Group3MMR_2549 != null)
                {
                    theRP115Group3_2549.MMR614 = theRP115Group3MMR_2549.MMR614;
                }
                theRP115Group3MMR_2549 = null;
                
                // ��ǹ������ Nutrition
                theRP115Group3Nutrition_2549 = new RP115Group3Nutrition_2549();
                theRP115Group3Nutrition_2549 = (RP115Group3Nutrition_2549)vGroup3Nutrition.get(i);
                if(theRP115Group3Nutrition_2549 != null)
                {
                    theRP115Group3_2549.baby_fail = theRP115Group3Nutrition_2549.baby_fail;
                    theRP115Group3_2549.baby_exceed = theRP115Group3Nutrition_2549.baby_exceed;
                    theRP115Group3_2549.child_fail = theRP115Group3Nutrition_2549.child_fail;
                    theRP115Group3_2549.child_exceed = theRP115Group3Nutrition_2549.child_exceed;
                    theRP115Group3_2549.man_fail = theRP115Group3Nutrition_2549.man_fail;
                    theRP115Group3_2549.man_exceed = theRP115Group3Nutrition_2549.man_exceed;
                }
                theRP115Group3Nutrition_2549 = null;
                
                vc.add(theRP115Group3_2549);
                
                theRP115Group3_2549 = null;  
                
            }
        }
       
        return vc;
    }
 
 /**
  * ��Ǩ�ͺ��¡�÷ѹ�������ͧ�ѹ
  * ��Ǩ�ͺ��¡���Ѥ�չ MMR
  * ��Ǩ�ͺ��¡���Ѥ�չ�����
  * Author Ojika
  **/
 public String checkDataPreQueryReport()
 {
     String messageCheck = "";

     Vector vCheckDataDentalProtect = new Vector();
     Vector vCheckDataMMR = new Vector();
     Vector vCheckDataEpiGroup = new Vector();
     
     
        theConnectionInf.open();
        try
        {
            // ��Ǩ�ͺ��¡�÷ѹ�������ͧ�ѹ checkDataDentalProtect()
            vCheckDataDentalProtect = theHosDB.theRP115Group3_2549DB.checkDataDentalProtect(); 
            // ��Ǩ�ͺ��¡���Ѥ�չ MMR checkDataMMR()
            vCheckDataMMR = theHosDB.theRP115Group3_2549DB.checkDataMMR(); 
             // ��Ǩ�ͺ��¡���Ѥ�չ����� checkDataEpiGroup()
            vCheckDataEpiGroup = theHosDB.theRP115Group3_2549DB.checkDataEpiGroup(); 
            // �� Vector ���������仵�Ǩ�ͺ���������� Return ��Ѻ���� Message �����觡�Ѻ��ѧ GUI �ա����
            messageCheck = checkDataPreQueryReportMessage(vCheckDataDentalProtect, vCheckDataMMR, vCheckDataEpiGroup);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
     
     return messageCheck;
 }
 
 private String checkDataPreQueryReportMessage(Vector vCheckDataDentalProtect, Vector vCheckDataMMR, Vector vCheckDataEpiGroup)
 {
     String messageCheck = "<html><body><br>";
     // ��Ǩ�ͺ��¡�÷ѹ�������ͧ�ѹ
     if(vCheckDataDentalProtect.isEmpty())
     {
        messageCheck = messageCheck + " ����� Setup ��������¡�÷���繷ѹ�������ͧ�ѹ ����ö���� Setup ���� <br>";
     }
     // ��Ǩ�ͺ��¡���Ѥ�չ MMR
     if(vCheckDataMMR.isEmpty())
     {
        messageCheck = messageCheck + " ����� Setup ��������¡���Ѥ�չ MMR ����ö���� Setup ���� <br>";
     }
     // ��Ǩ�ͺ��¡���Ѥ�չ�����
     if(vCheckDataEpiGroup.isEmpty())
     {
         messageCheck = messageCheck + " ����� Setup ��������¡���Ѥ�չ����� ����ö���� Setup ���� <br>";
     }
     else
     {
         for(int i=0;i<vCheckDataEpiGroup.size();i++)
         {
             thecheckDataObject = (checkDataObject)vCheckDataEpiGroup.get(i);
             
             if((Integer.parseInt(thecheckDataObject.code_item)) == 0)
             {
                 messageCheck = messageCheck + "  ����� Setup ��������¡���Ѥ�չ����� �ش�Ѥ�չ " + thecheckDataObject.code + " <br> ";
             }
         }
     }
     messageCheck = messageCheck + "</body></html>";
     return messageCheck;
 }
    
}
