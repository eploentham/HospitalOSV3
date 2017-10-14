/*
 * RPRevenueAndExpense.java
 *
 * Created on 11 ตุลาคม 2548, 15:30 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.control;

import com.hosv3.control.HosControl;
import java.util.Vector;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalreport.objdb.HosDB;
import com.hosv3.object.UseCase;
import com.hosv3.utility.Config;
import javax.swing.JFrame;
/**
 *
 * @author amp
 */
public class RPRevenueAndExpenseControl
{
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;    
    private Vector vcData;
    private   HosControl theHC;
    
    /** Creates a new instance of RPRevenueAndExpense */
    public RPRevenueAndExpenseControl(com.hosv3.control.HosControl hc, HosDB hdb)
    {
        theHC = hc;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    public Vector selectRevenueAndExpenseByDateTime(String startDate, String endDate, String timeFrom, String timeTo, String codeServicePoint, boolean isDbBackup)
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
            theHosDB.theRPRevenueAndExpenseDB.theConnectionInf = theConnectionInf;
           vcData = theHosDB.theRPRevenueAndExpenseDB.queryRevenueAndExpenseByDateTime(startDate, endDate, timeFrom, timeTo, codeServicePoint);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "RevenueAndExpense",
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "RevenueAndExpense", ex, theHC.theUS.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
    public Vector selectAccrualsByDateTime(String startDate, String endDate, String timeFrom, String timeTo, String codeServicePoint)
    {
        vcData = null;
        theConnectionInf.open();
        try
        {            
            vcData = theHosDB.theRPRevenueAndExpenseDB.queryAccrualsByDateTime(startDate, endDate, timeFrom, timeTo, codeServicePoint);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();          
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
     public Vector selectRevenueExpenseByDateTime(String startDate, String endDate, String timeFrom, String timeTo, String codeServicePoint)
    {
        vcData = null;
        theConnectionInf.open();
        try
        {            
            vcData = theHosDB.theRPRevenueAndExpenseDB.queryRevenueExpenseByDateTime(startDate, endDate, timeFrom, timeTo, codeServicePoint);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();          
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
}
