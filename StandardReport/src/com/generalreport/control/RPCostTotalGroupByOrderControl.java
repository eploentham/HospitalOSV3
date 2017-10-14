/*
 * RPCostTotalGroupByOrderControl.java
 *
 * Created on 13 ตุลาคม 2548, 14:54 น.
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
public class RPCostTotalGroupByOrderControl
{
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;
    private String startDate;
    private String finishDate;
    private Vector vStatus;
    private   HosControl theHC;
    /** Creates a new instance of RPCostTotalGroupByOrderControl */
    public RPCostTotalGroupByOrderControl(com.hosv3.control.HosControl hc, HosDB hdb)
    {
        theHC = hc;
        theConnectionInf = hdb.theConnectionInf;
        theHosDB = hdb;
    }
    
     public Vector selectCostTotalGroupByOrderByDate(String startDate, String endDate,Vector vSta, boolean isDbBackup)
    {
        this.startDate = startDate;
        this.finishDate = endDate;
        this.vStatus = vSta;
        return queryCostTotalGroupByOrderByDate(isDbBackup);
    }
    
    public Vector queryCostTotalGroupByOrderByDate(boolean isDbBackup)
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
            theHosDB.theRPCostTotalGroupByOrderDB.theConnectionInf = theConnectionInf;
            vcData = theHosDB.theRPCostTotalGroupByOrderDB.queryCostTotalGroupByOrderByDate(startDate, finishDate, vStatus);
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "CostTotalGroupByOrder",
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "CostTotalGroupByOrder", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
    
}
