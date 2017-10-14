/*
 * RP506SurveilControl.java
 *
 * Created on 8 กันยายน 2548, 13:18 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.control;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.control.HosControl;
import com.hosv3.object.UseCase;
import com.standardReport.utility.*;
import java.util.Vector;
import java.io.File;
import javax.swing.JFrame;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author americus
 */
public class RP506SurveilControl
{
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vc = null;
    private FileWriter fileWriter;
    private   HosControl theHC;
    
    /**
     * Creates a new instance of RP506SurveilControl 
     */
    public RP506SurveilControl()
    {
    }
    
    public RP506SurveilControl(com.hosv3.control.HosControl hc,ConnectionInf con,HosDB hdb)
    {
        theHC = hc;
        theConnectionInf = con;
        theHosDB = hdb;
        fileWriter = new FileWriter();
    }

    public void printIReport(Vector vcDataQuery) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean printIReport(Vector vcDataQuery, int[] rows) {
        try {
            //v จะมีจำนวน 6 รายการจากการคิวรีดังนั้นจึงใช้ forloop เดียวกันได้
//            for(int i=0;i<rows.length;i++){
//                String[] str = (String[])vcDataQuery.get(rows[i]);
//                Rp506Surveil rp = new Rp506Surveil();
//                rp.setValues(str);
//                ds.add(rp);
//            }
            String[] str = (String[])vcDataQuery.get(rows[0]);
            Map map = new HashMap(); 
            map.put("visit_vn", str[13]);
            JasperReport jr = JasperCompileManager.compileReport("config/rp_standard/Patient_Card_506.xml");
            JasperPrint jp = JasperFillManager.fillReport(jr, map, theConnectionInf.getConnection());
            JasperViewer theJasperViewer = new JasperViewer(jp, false);
            theJasperViewer.setVisible(true);
            return true;
        } catch (JRException ex) {
            ex.printStackTrace();
            return false;
        }
    }
     
     public Vector selectRP506SurveilByDate(String startDate, String endDate,String treatType,boolean isDbBackup)
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
            theHosDB.theRP506SurveilDB.theConnectionInf = theConnectionInf;
            vc = theHosDB.theRP506SurveilDB.selectByDate(startDate, endDate,treatType);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_surveil_treatType.sql",
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_surveil_treatType.sql", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
}
