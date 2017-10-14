/*
 * RPEyeDiseasesControl.java
 *
 * Created on 11 ตุลาคม 2548, 11:55 น.
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
public class RPEyeDiseasesControl
{
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;

    private String startDate;
    private String finishDate;
    private   HosControl theHC;
    
    /** Creates a new instance of RPEyeDiseasesControl */
    public RPEyeDiseasesControl(com.hosv3.control.HosControl hc, HosDB hdb)
    {
        theHC = hc;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    public Vector selectEyeDiseasesByDate(String startDate, String endDate, boolean isDbBackup)
    {
        this.startDate = startDate;
        this.finishDate = endDate;
        return queryEyeDiseasesByDate(isDbBackup);
    }
    
    public boolean checkStartDateAndFinishDate(String startDate,String endDate)
    {
        boolean result = true;
        //ตรวจสอบว่า ข้อมูลที่รับเข้ามาทั้ง 2 จะต้องไม่เป็น null
        if(startDate !=null && endDate != null)
        {   //ตรวจสอบ ว่า ข้อมูลที่รับเข้ามาทั้ง 2 จะต้องมีความยาวมากกว่า 4 ตัวอักษร
            if(startDate.length() > 4 && endDate.length() > 4)
            {
                int start = subStringYear(startDate);
                int end =  subStringYear(endDate);
                if((start-end) != 0)
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
        return result;
        
    }
   
    private int subStringYear(String date)
    {
        int number = 0;
        if(date!= null && date.length() > 4)
        {
            try{
                date = date.substring(0,4);
                number = Integer.parseInt(date);
            }
            catch(Exception ex)
            {
                number =0;
            }
        }
        
        return number;
    }
    public Vector queryEyeDiseasesByDate(boolean isDbBackup)
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
            theHosDB.theRPEyeDiseasesDB.theConnectionInf = theConnectionInf;
            vcData = theHosDB.theRPEyeDiseasesDB.queryEyeDiseasesByDate(startDate, finishDate); 
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "EyeDiseases",
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
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "EyeDiseases", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
}
