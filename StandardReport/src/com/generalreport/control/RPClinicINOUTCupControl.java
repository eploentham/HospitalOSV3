/*
 * RPClinicINOUTCupControl.java
 *
 * Created on 11 ตุลาคม 2548, 12:00 น.
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
public class RPClinicINOUTCupControl
{
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;
 
    private String startDate;
    private String finishDate;
    private final HosControl theHC;
    
    /** Creates a new instance of RPClinicINOUTCupControl */
    public RPClinicINOUTCupControl(com.hosv3.control.HosControl hc, HosDB hdb)
    {
        theHC = hc;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    public Vector selectClinicINOUTCupByDate(String startDate, String endDate, boolean isDbBackup)
    {
        this.startDate = startDate;
        this.finishDate = endDate;
        return queryClinicINOUTCupByDate(isDbBackup);
    }
    
    private Vector queryClinicINOUTCupByDate(boolean isDbBackup)
    {
        vcData = null;
        String startCheck = "";
        String endCheck = "";
        boolean isJan = false;
        if(startDate.length() >= 10)
        {
            if(("01").equalsIgnoreCase(this.startDate.substring(5, 7)))
            {
                isJan = true;
            }
            else
            {
                startCheck = startDate.substring(0, 4) + "-01";
                endCheck = startDate.substring(0, 7);
            } 
        }
        if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theHosDB.theConnectionInf;
        }
        theConnectionInf.open();
        try
        {
            theHosDB.theRPClinicINOUTCupDB.theConnectionInf = theConnectionInf;
            vcData = theHosDB.theRPClinicINOUTCupDB.queryClinicINOUTCupByDate(this.startDate,this.finishDate,startCheck,endCheck,isJan);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "ClinicINOUTCup",
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "ClinicINOUTCup", ex, theHC.theUS.ERROR);
           // theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }

    
}
