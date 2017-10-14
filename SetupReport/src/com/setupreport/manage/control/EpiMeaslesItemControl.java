/*
 * EpiMeaslesItemControl.java
 *
 * Created on 7 มีนาคม 2549, 15:20 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage.control;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.setupreport.manage.control.HosDB;
import com.setupreport.object.*;
import java.util.Vector;
import com.hosv3.utility.connection.UpdateStatus;
/**
 *
 * @author Ojika
 */
public class EpiMeaslesItemControl
{
    
    /** Creates a new instance of EpiMeaslesItemControl */
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private int iresult;
    Vector vData;
    EpiMeaslesItem theEpiMeaslesItem;
    UpdateStatus theUS;
    
    public EpiMeaslesItemControl(HosDB hdb, UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    /**
     * สำหรับตรวจสอบ keyword ในการค้นหารายการ
     * @Param kyword เป็นตัวแปรเพื่อตรวจสอบว่าจะต้องค้นหาแบบมี Keyword หรือไม่ 
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public Vector searchEpiMeaslesItem(String keyword)
    {
        if(keyword.equalsIgnoreCase(""))
        {
            // ค้นหาทั้งหมด
            selectEpiMeaslesItemAll();
        }
        else
        {
            // ค้นหาจากคำค้น
            selectEpiMeaslesItemByKeyword(keyword);
        }        
        
        return vData;
    }
    
    /**
     * สำหรับค้นหารายการวัคซีนของหัดเยอรมัน โดยค้นหาทั้งหมด
     * @Return Vector ของ EpiMeaslesItem 
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public Vector selectEpiMeaslesItemAll()
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theEpiMeaslesItemDB.selectAll();              
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        } 
        
        return vData;
    }
    
    /**
     * สำหรับค้นหารายการวัคซีนของหัดเยอรมัน
     * @Param keyword เป็นตัวแปรที่เก็บค่าคำค้นที่ผู้ใช้กรอก
     * @Return Vector ของ EpiMeaslesItem 
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public Vector selectEpiMeaslesItemByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theEpiMeaslesItemDB.selectByKeyword(keyword);            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        } 
        
        return vData;
    }
    
    /**
     * สำหรับลบรายการวัคซีนของหัดเยอรมัน
     * @Param obj เป็น Object ของรายการวัคซีน
     * @Return int เพื่อบอกสถานะการลบรายการ 
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public int deleteEpiMeaslesItemByKeyId(EpiMeaslesItem obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theEpiMeaslesItemDB.deleteByKeyID(obj.getObjectId()); 
            theUS.setStatus("การลบรายการวัคซีนหัดเยอรมัน (MMR)เสร็จสิ้น", UpdateStatus.COMPLETE);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        } 
        
        return iresult;
    }
        
    /**
     * สำหรับบันทึกรายการวัคซีนของหัดเยอรมัน
     * @Param obj เป็น Object ของรายการวัคซีน
     * @Return int เพื่อบอกสถานะการลบรายการ 
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public int saveEpiMeaslesItem(EpiMeaslesItem obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theEpiMeaslesItemDB.insertData(obj);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        } 
        
        return iresult;
    }
    
    /**
     * สำหรับค้นหารายวัคซีน
     * @Param keyword เป็นตัวแปรที่เก็บค่าคำค้นที่ผู้ใช้กรอก
     * @Return Vector ของ Epi
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public Vector selectEpiByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theEpiDB.selectByKeyword(keyword);             
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        } 
        
        return vData;
    }
    
}
