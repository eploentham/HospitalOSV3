/*
 * RPOrderLabControl.java
 *
 * Created on 1 พฤศจิกายน 2548, 16:10 น.
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
import javax.swing.JFrame;

/**
 *
 * @author nu_ojika
 */
public class RPOrderLabControl {
    
    /**
     * Creates a new instance of RPOrderLabControl 
     */
    private HosDB theHosDB;
    private ConnectionInf theConnectionInf;
    private Vector vcData;
    
    private String startDate = "";
    private String finishDate = "";
    private   HosControl theHC;
    
    public RPOrderLabControl(com.hosv3.control.HosControl hc, HosDB hdb)
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
     *  เป็น Function สำหรับการค้นหารายการ Lab ที่ตรวจภายในโรงพยาบาล
     *  
     **/
    public Vector queryOrderLabInHospitalByDate(boolean isDbBackup)
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
            theHosDB.theRPOrderLabDB.theConnectionInf = theConnectionInf;
            vcData = theHosDB.theRPOrderLabDB.queryOrderLabInHospitalByDate(this.startDate, this.finishDate);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "OrderLabInHospital",
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "OrderLabInHospital", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
    /**
     *  เป็น Function สำหรับการค้นหารายการ Lab ที่ส่งตรวจยังโรงพยาบาลอื่น
     *  
     **/
    public Vector queryOrderLabReferOutByDate()
    {
        vcData = new Vector();
        theConnectionInf.open();
        try
        {          
            vcData = theHosDB.theRPOrderLabDB.queryOrderLabReferOutByDate(this.startDate, this.finishDate); 
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
    
    /**
     *  เป็น Function สำหรับการค้นหารายการ Lab ที่รับตรวจจากโรงพยาบาลอื่น
     *  
     **/
    public Vector queryOrderLabReferInByDate()
    {
        vcData = new Vector();
        theConnectionInf.open();
        try
        {          
            vcData = theHosDB.theRPOrderLabDB.queryOrderLabReferInByDate(this.startDate, this.finishDate); 
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
