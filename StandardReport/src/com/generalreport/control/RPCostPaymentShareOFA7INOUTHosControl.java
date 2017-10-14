/*
 * RPCostPaymentShareOFA7INOUTHosControl.java
 *
 * Created on 18 ตุลาคม 2548, 18:40 น.
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
public class RPCostPaymentShareOFA7INOUTHosControl {
    
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;
 
    private String startDate;
    private String finishDate;
    private String visit_type;
    private   HosControl theHC;
    public RPCostPaymentShareOFA7INOUTHosControl(com.hosv3.control.HosControl hc, HosDB hdb)
    {
        theHC = hc;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    public Vector selectCostPaymentShareOFA7INOUTHosByDate(String startDate, String endDate,String visit_type,boolean isDbBackup)
    {
        this.startDate = startDate;
        this.finishDate = endDate;
        this.visit_type = visit_type;
        return queryCostPaymentShareOFA7INOUTHosByDate(isDbBackup);
    }
    
    private Vector queryCostPaymentShareOFA7INOUTHosByDate(boolean isDbBackup)
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
            theHosDB.theRPCostPaymentShareOFA7INOUTHosDB.theConnectionInf = theConnectionInf;
            vcData = theHosDB.theRPCostPaymentShareOFA7INOUTHosDB.queryCostPaymentShareOFA7INOUTHosByDate(startDate, finishDate,visit_type);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "CostPaymentShareOFA7INOUTHos",
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "CostPaymentShareOFA7INOUTHos", ex, theHC.theUS.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
}
