/*
 * RPPatientAdmitAndDischargeControl.java
 *
 * Created on 19 ตุลาคม 2548, 15:28 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.control;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalreport.objdb.HosDB;
import com.hosv3.control.HosControl;
import com.hosv3.object.UseCase;
import com.hosv3.utility.Config;
import java.util.Vector;
import java.io.File;
import javax.swing.JFrame;
/**
 *
 * @author tong(Padungrat)
 */
public class RPPatientAdmitAndDischargeControl {
    
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;
 
    private String startDate;
    private String finishDate;
    private   HosControl theHC;
    public RPPatientAdmitAndDischargeControl(com.hosv3.control.HosControl hc, HosDB hdb)
    {
        theHC = hc;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    public Vector selectPatientAdmitAndDischargByDate(String startDate, String endDate, boolean isDbBackup)
    {
        this.startDate = startDate;
        this.finishDate = endDate;
        return queryPatientAdmitAndDischargByDate(isDbBackup);
    }
    
    private Vector queryPatientAdmitAndDischargByDate(boolean isDbBackup)
    {
        vcData = null;
        if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theHosDB.theConnectionInf;
        }
        theConnectionInf.open();
        try
        {
            theHosDB.theRPPatientAdmitAndDischargeDB.theConnectionInf = theConnectionInf;
            vcData = theHosDB.theRPPatientAdmitAndDischargeDB.queryPatientAdmitAndDischargByDate(startDate, finishDate); 
            /*String[] headColumn;
            Vector vc;
            if(vcData != null)
            {
                headColumn = (String[])vcData.get(0);
                vc = (Vector)vcData.get(1);
                System.out.println(vc.size());
            }
             **/
           // System.out.println(vcData.size());
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "PatientAdmitAndDischarg",
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startDate+"-"+
                    finishDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "PatientAdmitAndDischarg", ex, theHC.theUS.ERROR);
           // theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }

}
