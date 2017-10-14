/*
 * ExportControl.java
 *
 * Created on 7 กันยายน 2548, 15:58 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.control;

import com.standardReport.object.RP115Group1_2549;
import com.standardReport.object.RP115Group2_2549;
import com.standardReport.object.RP115Group3_2549;
import com.standardReport.object.RP115Group4_2549;
import com.standardReport.object.RP504Group;
import com.standardReport.object.RP504Name;
import com.standardReport.object.RP505Group;
import com.standardReport.object.RP505Name;
import com.standardReport.object.RP506Group;
import com.standardReport.object.RP506Name;
import java.sql.ResultSet;

import java.util.Vector;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import com.standardReport.utility.*;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.standardReport.object.Rp115_1;
import com.standardReport.object.Rp115_2;
import com.standardReport.object.Rp115_3;
import com.standardReport.object.Rp115_4;
/**
 *
 * @author nu_ojika
 */
public class ExportControl { 
    
    /** Creates a new instance of ExportControl */
    ConnectionInf theConnectionInf;
    private Vector vc = null;
    private FileWriter fileWriter;
    HosDB theHosDB;
    
    public ExportControl(ConnectionInf c,HosDB hdb)
    {
        theConnectionInf = c;
        theHosDB = hdb;
    }

    public void startExportByVector(Vector vData,String pathFile,String typeFile,String fileName)
    {
        if(vData != null)
        {
            String msg =""; 
            String path = pathFile + fileName + "."+typeFile;
            System.out.println("---  Path Save----"+path);
            path = path.replace('\\','/');
            if(("xls").equalsIgnoreCase(typeFile) || ("csv").equalsIgnoreCase(typeFile))
            {
                System.out.println("----- XLS");
                try
                {
                    msg = convertDataToString(vData,true);
                    if(!("").equals(msg))
                    {
                        fileWriter = new FileWriter(path);
                        fileWriter.setPathFile(path);
                        fileWriter.writeData(msg);
                        fileWriter.closeFile();
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            else
            {
                //text and other file
                System.out.println("----- Text");
                try
                {
                    System.out.println("----- Text" + vData.size());
                    msg = convertDataToString(vData,true);
                    if(!("").equals(msg))
                    {
                        System.out.println("----- Text Writing ----");
                        fileWriter = new FileWriter(path);
                        fileWriter.setPathFile(path);
                        fileWriter.writeData(msg);
                        fileWriter.closeFile();
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        else
        {
            System.out.println("---  Not value in person");
        }
    }       
    
 public String convertDataToString(Vector v,boolean isGetColumnName)
    {
        if(v != null && v.size()>0)
        {
            Object obReport = v.get(0);  
            Report report;
            if(obReport instanceof RP504Group)
            {   report = (Report)Constant.Report.get(String.valueOf("1"));
                return theHosDB.theRP504DB.convertToString(v, isGetColumnName,report.ENG_NAME );//.REPORT_NAME_EN[0]);
            }
            else if(obReport instanceof RP505Group)
            {   report = (Report)Constant.Report.get(String.valueOf("2"));
                return theHosDB.theRP505DB.convertToString(v, isGetColumnName,report.ENG_NAME);//Constant.REPORT_NAME_EN[1]); 
            }
            else if(obReport instanceof RP506Group)
            {   report = (Report)Constant.Report.get(String.valueOf("3"));
                return theHosDB.theRP506DB.convertToString(v, isGetColumnName,report.ENG_NAME);//Constant.REPORT_NAME_EN[2]);  
            }
            else if(obReport instanceof RP504Name)
            {   report = (Report)Constant.Report.get(String.valueOf("4"));
                return theHosDB.theRP504DB.convertToString(v, isGetColumnName,report.ENG_NAME);//Constant.REPORT_NAME_EN[3]); 
            }
            else if(obReport instanceof RP505Name)
            {   report = (Report)Constant.Report.get(String.valueOf("5"));
                return theHosDB.theRP505DB.convertToString(v, isGetColumnName,report.ENG_NAME );//Constant.REPORT_NAME_EN[4]); 
            }
            else if(obReport instanceof RP506Name)
            {   report = (Report)Constant.Report.get(String.valueOf("6"));
                return theHosDB.theRP506DB.convertToString(v, isGetColumnName,report.ENG_NAME );//Constant.REPORT_NAME_EN[5]); 
            }
            else if(obReport instanceof RP115Group1_2549)
            {
                return theHosDB.theRP115Group1_2549DB.convertToString(v, isGetColumnName);  
            }
            else if(obReport instanceof RP115Group2_2549)
            {
                return theHosDB.theRP115Group2_2549DB.convertToString(v, isGetColumnName);  
            }
            else if(obReport instanceof RP115Group3_2549)
            {
                return theHosDB.theRP115Group3_2549DB.convertToString(v, isGetColumnName);
            }
            else if(obReport instanceof RP115Group4_2549)
            {
                return theHosDB.theRP115Group4_2549DB.convertToString(v, isGetColumnName);
            }
            else
            {
                return "";
            }
        }
        else
        {
            return "";
        }
        
    }   
    
    public Vector selectTreatStatusAll()
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = theHosDB.theTreatStatusDB.getTreatStatusAll();
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
        return vc;
    }
    
    public void startExportSurviel(Vector vData,String pathFile,String typeFile,String fileName)
    {
        if(vData != null)
        {
            String msg ="";
            String path = pathFile + fileName + "."+typeFile;
            System.out.println("---  Path Save----"+path);
            path = path.replace('\\','/');
            if(("xls").equalsIgnoreCase(typeFile) || ("cvs").equalsIgnoreCase(typeFile))
            {
                System.out.println("----- XLS");
                try
                {
                    msg = theHosDB.theRP506SurveilDB.convertToString(vData, true);
                    if(!("").equals(msg))
                    {
                        fileWriter = new FileWriter(path);
                        fileWriter.setPathFile(path);
                        fileWriter.writeData(msg);
                        fileWriter.closeFile();
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            else
            {
                //text and other file
                System.out.println("----- Text");
                try
                {
                    msg = theHosDB.theRP506SurveilDB.convertToString(vData, true);
                    if(!("").equals(msg))
                    {
                        System.out.println("----- Text Writing ----");
                        fileWriter = new FileWriter(path);
                        fileWriter.setPathFile(path);
                        fileWriter.writeData(msg);
                        fileWriter.closeFile();
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        else
        {
            System.out.println("---  Not value in person");
        }
    }
    public boolean print115_1(Vector vopd, Vector vipd, Vector vrefer, Vector vpcu
            ,String datefrom,String dateto) {
        try {
            //v จะมีจำนวน 6 รายการจากการคิวรีดังนั้นจึงใช้ forloop เดียวกันได้
            Rp115_1 ds = Rp115_1.initValue(vopd,vipd,vrefer,vpcu);
            JasperReport jr = JasperCompileManager.compileReport(Rp115_1.getReportFn());
            Map map = new HashMap();
            String sql = "select site_full_name as hospital" +
                ",site_house as address" +
                ",site_moo as moo" +
                ",tambon.address_description as tambon" +
                ",amphur.address_description as amphur" +
                ",changwat.address_description as province" +
                " from b_site" +
                " inner join f_address changwat on changwat.f_address_id = site_changwat" +
                " inner join f_address amphur on amphur.f_address_id = site_amphur" +
                " inner join f_address tambon on tambon.f_address_id = site_tambon";
            ResultSet rs = theConnectionInf.eQuery(sql);
            while(rs.next()){
                map.put("hospital",rs.getString(1));
                map.put("address",rs.getString(2));
                map.put("moo",rs.getString(3));
                map.put("tambon",rs.getString(4));
                map.put("amphur",rs.getString(5));
                map.put("province",rs.getString(6));
            }
            map.put("month",datefrom.substring(5,7));
            map.put("datebegin",DateUtil.getDateFromTextServer(datefrom));
            map.put("dateend",DateUtil.getDateFromTextServer(dateto));
            JasperPrint jp = JasperFillManager.fillReport(jr, map, ds);
            JasperViewer theJasperViewer = new JasperViewer(jp, false);
            theJasperViewer.setVisible(true);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean print115_2(Vector vGroup2Pre, Vector vGroup2Post, Vector vGroup2Family
            ,String datefrom,String dateto) {
        try {
            //v จะมีจำนวน 6 รายการจากการคิวรีดังนั้นจึงใช้ forloop เดียวกันได้
            Rp115_2 ds = Rp115_2.initValue(vGroup2Pre,vGroup2Post,vGroup2Family);
            Map map = new HashMap();
            JasperReport jr = JasperCompileManager.compileReport(Rp115_2.getReportFn());
            JasperPrint jp = JasperFillManager.fillReport(jr, map, ds);
            JasperViewer theJasperViewer = new JasperViewer(jp, false);
            theJasperViewer.setVisible(true);
            return true;
        } catch (JRException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean print115_3(Vector vGroup3Healthy, Vector vGroup3Nutrition
            , Vector vGroup3MMR, Vector vGroup3DentalProtect, Vector vGroup3Epi
            ,String datefrom,String dateto) {
        try {
            //v จะมีจำนวน 6 รายการจากการคิวรีดังนั้นจึงใช้ forloop เดียวกันได้
            Rp115_3 ds = Rp115_3.initValue(vGroup3Healthy,vGroup3Nutrition
            ,vGroup3MMR,vGroup3DentalProtect,vGroup3Epi);
            Map map = new HashMap();
            String sql = "select site_full_name as hospital" +
                ",site_house as address" +
                ",site_moo as moo" +
                ",tambon.address_description as tambon" +
                ",amphur.address_description as amphur" +
                ",changwat.address_description as province" +
                " from b_site" +
                " inner join f_address changwat on changwat.f_address_id = site_changwat" +
                " inner join f_address amphur on amphur.f_address_id = site_amphur" +
                " inner join f_address tambon on tambon.f_address_id = site_tambon";
            ResultSet rs = theConnectionInf.eQuery(sql);
            while(rs.next()){
                map.put("hospital",rs.getString(1));
                map.put("address",rs.getString(2));
                map.put("moo",rs.getString(3));
                map.put("tambon",rs.getString(4));
                map.put("amphur",rs.getString(5));
                map.put("province",rs.getString(6));
            }
            map.put("month",datefrom.substring(5,7));
            map.put("datebegin",DateUtil.getDateFromTextServer(datefrom));
            map.put("dateend",DateUtil.getDateFromTextServer(dateto));
            JasperReport jr = JasperCompileManager.compileReport(Rp115_3.getReportFn());
            JasperPrint jp = JasperFillManager.fillReport(jr, map, ds);
            JasperViewer theJasperViewer = new JasperViewer(jp, false);
            theJasperViewer.setVisible(true);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean print115_4(Vector vSchoolOutSite, Vector vHomeAmount, Vector vPersonAmount
            , Vector vEfficiency, Vector vTreatInfant, Vector vAbortPregnant
            , Vector vAssort, Vector vCheckConfirm
            ,String datefrom,String dateto) {
        try {
            //v จะมีจำนวน 6 รายการจากการคิวรีดังนั้นจึงใช้ forloop เดียวกันได้
            Rp115_4 ds = Rp115_4.initValue(vSchoolOutSite, vHomeAmount, vPersonAmount
            , vEfficiency, vTreatInfant, vAbortPregnant
            , vAssort, vCheckConfirm);
            Map map = new HashMap();
            JasperReport jr = JasperCompileManager.compileReport(Rp115_4.getReportFn());
            JasperPrint jp = JasperFillManager.fillReport(jr, map, ds);
            JasperViewer theJasperViewer = new JasperViewer(jp, false);
            theJasperViewer.setVisible(true);
            return true;
        } catch (JRException ex) {
            ex.printStackTrace();
            return false;
        }
    } 


}
