/*
 * HosDB.java
 *
 * Created on 28 กรกฎาคม 2548, 13:26 น.
 */

package com.reportnan.control;
import com.hospital_os.usecase.connection.ConnectionInf;

import com.hosv3.object.HosObject;
import com.hosv3.utility.connection.UpdateStatus;

import com.reportcenter.utility.Constant; 
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Noom
 */
public class Rp18Control implements Runnable
{
    
    Thread hosControlThread;
    public ConnectionInf theConnectionInf;
    
    public String current_file;
    public String startDate;
    public String endDate;
    public String pathFile;
    public String typeFile;
    UpdateStatus theUS;
    private final HosObject theHO;
 
    
    public Rp18Control(ConnectionInf c,UpdateStatus us,HosObject ho) {
        theConnectionInf = c;
        theUS = us;
        theHO = ho;
    }
    
    public void start(){
       hosControlThread = new Thread(this);
       hosControlThread.start();
    }
    
    public void stop(){
      if(hosControlThread != null)
          hosControlThread.stop();
      
      hosControlThread = null;
      System.out.println("In stop in HosControl");
    }

    public void setDataExport18File(String startDate, String endDate, String pathFile, String type, int selectedRow)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        this.pathFile = pathFile;
        this.typeFile = type;
        if(typeFile.equals("0"))
            typeFile = Constant.DBF_INDEX;
        else
            typeFile = Constant.TXT_INDEX;
    }
    
    public void run(){
        try{
            startExport18File();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void startExport18File() throws Exception
    {
        System.out.println("In method startExport18File"); 
        theUS.setStatus("กรุณารอสักครู่ ขณะนี้โปรแกรมกำลังออกรายงาน",UpdateStatus.WARNING);
        theConnectionInf.open(); 
        //dbExport(theHomeDB,startDate,endDate);
        theConnectionInf.close();
        System.out.println("Finish export");
        theUS.setStatus("ออกรายงานเรียบร้อยแล้ว path ไฟล์อยู่ที่ ",UpdateStatus.WARNING);
        this.stop();
    }

    private String getFileName() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
