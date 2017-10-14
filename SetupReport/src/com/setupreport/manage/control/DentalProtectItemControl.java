/*
 * DentalProtectItem.java
 *
 * Created on 7 มีนาคม 2549, 15:19 น.
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
public class DentalProtectItemControl
{
    
    /** Creates a new instance of DentalProtectItem */
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private int iresult;
    Vector vData;
    DentalProtectItem theDentalProtectItem;
    UpdateStatus theUS;
    
    public DentalProtectItemControl(HosDB hdb, UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    /**
     * สำหรับตรวจสอบ keyword ในการค้นหารายการ
     * @Param kyword เป็นตัวแปรเพื่อตรวจสอบว่าจะต้องค้นหาแบบมี Keyword หรือไม่ 
     * @Author Ojika
     * @Date 07/03/2549
     **/
    public Vector searchDentalProtectItem(String keyword)
    {
        if(keyword.equalsIgnoreCase(""))
        {
            // ค้นหาทั้งหมด
            selectDentalProtectItemAll();
        }
        else
        {
            // ค้นหาจากคำค้น
            selectDentalProtectItemByKeyword(keyword);
        }        
        
        return vData;
    }
    
    /**
     * สำหรับค้นหารายการตรวจรักษาของทันตกรรมป้องกัน โดยค้นหาทั้งหมด
     * @Return Vector ของ DentalProtectItem 
     * @Author Ojika
     * @Date 07/03/2549
     **/
    public Vector selectDentalProtectItemAll()
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theDentalProtectItemDB.selectAll();             
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
     * สำหรับค้นหารายการตรวจรักษาของทันตกรรมป้องกัน
     * @Param keyword เป็นตัวแปรที่เก็บค่าคำค้นที่ผู้ใช้กรอก
     * @Return Vector ของ DentalProtectItem 
     * @Author Ojika
     * @Date 07/03/2549
     **/
    public Vector selectDentalProtectItemByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theDentalProtectItemDB.selectByKeyword(keyword);            
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
     * สำหรับลบรายการตรวจรักษาของทันตกรรมป้องกัน
     * @Param obj เป็น Object ของรายการทันตกรรมป้องกัน
     * @Return int เพื่อบอกสถานะการลบรายการ 
     * @Author Ojika
     * @Date 07/03/2549
     **/
    public int deleteDentalProtectItemByKeyId(DentalProtectItem obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theDentalProtectItemDB.deleteByKeyID(obj.getObjectId());  
            theUS.setStatus("การลบรายการตรวจรักษาของทันตกรรมป้องกันเสร็จสิ้น", UpdateStatus.COMPLETE);
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
     * สำหรับค้นหารายการตรวจรักษา
     * @Param keyword เป็นตัวแปรที่เก็บค่าคำค้นที่ผู้ใช้กรอก
     * @Return Vector ของ Item 
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public Vector selectItemByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theItemDB.selectBySearch(keyword);            
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
     * สำหรับบันทึกรายการตรวจรักษาของทันตกรรมป้องกัน
     * @Param obj เป็น Object ของรายการทันตกรรมป้องกัน
     * @Return int เพื่อบอกสถานะการลบรายการ 
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public int saveDentalProtectItem(DentalProtectItem obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theDentalProtectItemDB.insertData(obj);      
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
}
