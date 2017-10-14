/*
 * RPReportARICControl.java
 *
 * Created on 11 ตุลาคม 2548, 11:50 น.
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
public class RPReportARICControl implements Runnable
{
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;
    private Thread theThread;
    private String startDate;
    private String finishDate;
    private   HosControl theHC;
    
    /** Creates a new instance of RPReportARICControl */
    public RPReportARICControl(com.hosv3.control.HosControl hc, HosDB hdb)
    {
        theHC = hc;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    public Vector selectARICByDate(String startDate, String endDate, boolean isDbBackup)
    {
        this.startDate = startDate;
        this.finishDate = endDate;
        return queryARICByDate(isDbBackup);
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
    
    public Vector queryARICByDate(boolean isDbBackup)
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
            theHosDB.theRPReportARICDB.theConnectionInf = theConnectionInf;
            vcData = theHosDB.theRPReportARICDB.queryARICByDate(startDate, finishDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "ARIC",
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "ARIC", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
    public Vector checkDataSetup()
    {
        vcData = null;
        theConnectionInf.open();
        try
        {
            vcData = theHosDB.theRPReportARICDB.checkDataSetup();  
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
        return vcData;
    }
    
}
