/*
 * RPPlentyDiseaseControl.java
 *
 * Created on 18 ตุลาคม 2548, 10:09 น.
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
public class RPPlentyDiseaseControl 
{
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;
    private Thread theThread;
    private String startDate;
    private String finishDate;
    private String icd;
    private String limited;
    private String visit_type;
    private String group_disease;
    private   HosControl theHC;
    /** Creates a new instance of RPPlentyDiseaseControl */
    public RPPlentyDiseaseControl(com.hosv3.control.HosControl hc, HosDB hdb)
    {
        theHC = hc;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    public Vector selectPlentyDiseaseByDate(String startDate, String endDate,String icd,String limited,String visit_type,String group_disease, boolean isDbBackup)
    {
        this.startDate = startDate;
        this.finishDate = endDate;
        this.icd = icd;
        this.limited = limited;
        this.visit_type = visit_type;
        this.group_disease = group_disease;
        return queryPlentyDiseaseByDate(isDbBackup);
    }
    
    public Vector queryPlentyDiseaseByDate(boolean isDbBackup)
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
            theHosDB.theRPPlentyDiseaseDB.theConnectionInf = theConnectionInf;
            //vcData = theHosDB.theRPPlentyDiseaseDB.queryPlentyDiseaseByDate(startDate, finishDate, icd, limited, visit_type);
            vcData = theHosDB.theRPPlentyDiseaseDB.queryPlentyDiseaseByDate(startDate, finishDate, icd, limited, visit_type, group_disease);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "PlentyDisease",
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "PlentyDisease", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }   
}
