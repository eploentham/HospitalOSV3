/*
 * RPPatientInServicePointControl.java
 *
 * Created on 12 ตุลาคม 2548, 15:00 น.
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
 * @author americus
 */
public class RPPatientInServicePointControl
{
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;
    private String startDate;
    private String finishDate;
    private String servicepoint;
    private String doctor;
    private   HosControl theHC;
    /**
     * Creates a new instance of RPPatientInServicePointControl 
     */
    public RPPatientInServicePointControl(com.hosv3.control.HosControl hc, HosDB hdb)
    {
        theHC = hc;
        theConnectionInf = hdb.theConnectionInf;
        theHosDB = hdb;     
    }
    
    public Vector selectPatientInServicePointByDate(String startDate, String endDate, String sp, String dc, boolean isDbBackup)
    {
        this.startDate = startDate;
        this.finishDate = endDate;
        this.servicepoint = sp;
        this.doctor = dc;
        return queryPatientInServicePointByDate(isDbBackup);
    }
    
    public Vector queryPatientInServicePointByDate(boolean isDbBackup)
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
            theHosDB.theRPPatientInServicePointDB.theConnectionInf = theConnectionInf;
            vcData = theHosDB.theRPPatientInServicePointDB.queryPatientInServicePointByDate(startDate, finishDate, servicepoint, doctor );
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "PatientInServicePoint",
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "PatientInServicePoint", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
}
