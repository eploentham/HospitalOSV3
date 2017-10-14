/*
 * RPPaymentINOUTCupControl.java
 *
 * Created on 11 ตุลาคม 2548, 11:38 น.
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
public class RPPaymentINOUTCupControl  implements Runnable
{
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;
    private Thread theThread;
    private String startDate;
    private String finishDate;
    private   HosControl theHC;
    public RPPaymentINOUTCupControl(com.hosv3.control.HosControl hc, HosDB hdb)
    {
        theHC = hc;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    public Vector selectPaymentINOUTCupByDate(String startDate, String endDate, boolean isDbBackup)
    {
        this.startDate = startDate;
        this.finishDate = endDate;
        return queryPaymentINOUTCupByDate(isDbBackup);
    }
     public void runThread()
    {   System.out.println("Call To DrugDispense");
        theThread = new Thread(this);
        theThread.start();
    }

    public void run()
    {        
        System.out.println("Run Thread");
    }
    
    public void stop()
    {
        theThread = null;
    }   
    
    public Vector queryPaymentINOUTCupByDate(boolean isDbBackup)
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
            theHosDB.theRPPaymentINOUTCupDB.theConnectionInf = theConnectionInf;
            vcData = theHosDB.theRPPaymentINOUTCupDB.queryPaymentINOUTCupByDate(this.startDate,this.finishDate,startCheck,endCheck,isJan);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "PaymentINOUTCup",
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "PaymentINOUTCup", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
}
