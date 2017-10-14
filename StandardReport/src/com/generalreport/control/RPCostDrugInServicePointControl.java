/*
 * RPCostDrugInServicePointControl.java
 *
 * Created on 20 ตุลาคม 2548, 18:50 น.
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
public class RPCostDrugInServicePointControl {
    
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;
 
    private String startDate;
    private String finishDate;
    private String startTime;
    private String finishTime;
    private   HosControl theHC;
    public RPCostDrugInServicePointControl(com.hosv3.control.HosControl hc, HosDB hdb)
    {
        theHC = hc;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    public Vector selectCostDrugInServicePointByDate(String startDate, String endDate,String startTime, String endTime, boolean isDbBackup)
    {
        this.startDate = startDate;
        this.finishDate = endDate;
        this.startTime = startTime;
        this.finishTime = endTime;
        return queryCostDrugInServicePointByDate(isDbBackup);
    }
    
    private Vector queryCostDrugInServicePointByDate(boolean isDbBackup)
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
            theHosDB.theRPCostDrugInServicePointDB.theConnectionInf = theConnectionInf;
            vcData = theHosDB.theRPCostDrugInServicePointDB.queryCostDrugInServicePointByDate(startDate, finishDate,startTime, finishTime); 
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "CostDrugInServicePoint",
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "CostDrugInServicePoint", ex, theHC.theUS.ERROR);
           // theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
}
