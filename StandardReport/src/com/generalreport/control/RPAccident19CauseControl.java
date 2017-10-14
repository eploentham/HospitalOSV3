/*
 * RPAccident19CauseControl.java
 *
 * Created on 1 พฤศจิกายน 2548, 18:49 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.control;

import com.generalreport.objdb.HosDB;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.control.HosControl;
import com.hosv3.object.UseCase;
import com.hosv3.utility.Config;
import java.util.Vector;
import java.io.File;
import javax.swing.JFrame;

/**
 *
 * @author nu_ojika
 */
public class RPAccident19CauseControl {
    
    /** Creates a new instance of RPAccident19CauseControl */
    private HosDB theHosDB;
    private ConnectionInf theConnectionInf;
    private Vector vcData;
    
    private String startDate = "";
    private String finishDate = "";
    private   HosControl theHC;
    
    public RPAccident19CauseControl(com.hosv3.control.HosControl hc, HosDB hdb)
    {
        theHC = hc;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    public boolean setDateForQuery(String startDate, String endDate)
    {
        this.startDate = startDate;
        this.finishDate = endDate;
        
        return true;
    }
    
    /**
     *  เป็น Function สำหรับการค้นหา รายงานอุบัติเหตุ 19 สาเหตุ
     *  
     **/
    public Vector queryAccident19CauseByDate(boolean isDbBackup)
    {
        vcData = new Vector();
        if(isDbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theHosDB.theConnectionInf;
        }
        theConnectionInf.open();
        try
        {
            theHosDB.theRPAccident19CauseDB.theConnectionInf = theConnectionInf;
            vcData = theHosDB.theRPAccident19CauseDB.queryAccident19CauseByDate(this.startDate, this.finishDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "Accident19Cause",
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "Accident19Cause", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
}
