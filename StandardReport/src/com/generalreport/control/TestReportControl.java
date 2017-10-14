/*
 * TestReportControl.java
 *
 * Created on 4 ตุลาคม 2548, 11:07 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.control;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalreport.objdb.HosDB;
import java.util.Vector;
/**
 *
 * @author tong(Padungrat)
 */
public class TestReportControl implements Runnable {
    
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;
    private Thread theThread;
    private String startDate;
    private String finishDate;
    public TestReportControl(HosDB hosDB) {
        theHosDB = hosDB;
        theConnectionInf = theHosDB.theConnectionInf;
    }

    public Vector queryTestReportByDate(String startDate,String finishDate)
    {
        this.startDate = startDate;
        this.finishDate = finishDate;
        return queryTestReportByDate();
    }
    public Vector queryTestReportByDate()
    {
        vcData =null;               
        try
        {
            theConnectionInf.open();
            vcData = theHosDB.theTestReportDB.queryTestReportByDate(startDate, finishDate);
            String[] headColumn;
            Vector vc;
            if(vcData != null)
            {
                headColumn = (String[])vcData.get(0);
                vc = (Vector)vcData.get(1);
                System.out.println(vc.size());
            }
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

    
    public void runThread()
    {   System.out.println("Call To Test Report");
        theThread = new Thread(this);
        theThread.start();
    }
    public void run() {
       System.out.println("Run Thread");
    }
    
    public void stop()
    {
       theThread = null;
    }
}
