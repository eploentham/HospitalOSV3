/*
 * RP506Control.java
 *
 * Created on 8 กันยายน 2548, 10:28 น.
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
/**
 *
 * @author americus
 */
public class RP506Control
{
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vc = null;
    private FileWriter fileWriter;
    private   HosControl theHC;
     
    public RP506Control(com.hosv3.control.HosControl hc,ConnectionInf con,HosDB hdb)
    {
        theHC = hc;
        theConnectionInf = con;
        theHosDB = hdb;
        fileWriter = new FileWriter();
    }
    public Vector selectRP506GroupByDate(String startDate, String endDate, boolean isDbBackup)
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
            theHosDB.theRP506DB.theConnectionInf = theConnectionInf;
            vc = theHosDB.theRP506DB.selectGroupByDate(startDate, endDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_506_group.sql",
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_506_group.sql", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
     public Vector selectRP506NameByDate(String startDate, String endDate,boolean isDbBackup)
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
            theHosDB.theRP506DB.theConnectionInf = theConnectionInf;
            vc = theHosDB.theRP506DB.selectNameByDate(startDate, endDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_506_name.sql",
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_506_name.sql", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }

}
