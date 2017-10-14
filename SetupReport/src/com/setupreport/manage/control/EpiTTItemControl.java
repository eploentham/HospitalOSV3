/*
 * EpiTTItemControl.java
 *
 * Created on 28 มีนาคม 2549, 15:20 น.
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
public class EpiTTItemControl
{
    
    /** Creates a new instance of EpiTTItemControl */
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private int iresult;
    Vector vData;
    EpiTTItem theEpiTTItem;
    UpdateStatus theUS;
    
    public EpiTTItemControl(HosDB hdb, UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    /**
     * สำหรับตรวจสอบ keyword ในการค้นหารายการ
     * @Param kyword เป็นตัวแปรเพื่อตรวจสอบว่าจะต้องค้นหาแบบมี Keyword หรือไม่ 
     * @Author Ojika
     * @Date 28/03/2549
     **/
    public Vector searchEpiTTItem(String keyword)
    {
        if(keyword.equalsIgnoreCase(""))
        {
            // ค้นหาทั้งหมด
            selectEpiTTItemAll();
        }
        else
        {
            // ค้นหาจากคำค้น
            selectEpiTTItemByKeyword(keyword);
        }        
        
        return vData;
    }
    
    /**
     * สำหรับค้นหารายการวัคซีนของ TT โดยค้นหาทั้งหมด
     * @Return Vector ของ EpiTTItem 
     * @Author Ojika
     * @Date 28/03/2549
     **/
    public Vector selectEpiTTItemAll()
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theEpiTTItemDB.selectAll();              
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
     * สำหรับค้นหารายการวัคซีนของ TT
     * @Param keyword เป็นตัวแปรที่เก็บค่าคำค้นที่ผู้ใช้กรอก
     * @Return Vector ของ EpiTTItem 
     * @Author Ojika
     * @Date 28/03/2549
     **/
    public Vector selectEpiTTItemByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theEpiTTItemDB.selectByKeyword(keyword);            
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
     * สำหรับลบรายการวัคซีนของ TT
     * @Param obj เป็น Object ของรายการวัคซีน
     * @Return int เพื่อบอกสถานะการลบรายการ 
     * @Author Ojika
     * @Date 28/03/2549
     **/
    public int deleteEpiTTItemByKeyId(EpiTTItem obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theEpiTTItemDB.deleteByKeyID(obj.getObjectId());   
            theUS.setStatus("การลบรายการวัคซีนของ TT เสร็จสิ้น", UpdateStatus.COMPLETE);    
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
     * สำหรับบันทึกรายการวัคซีนของ TT
     * @Param obj เป็น Object ของรายการวัคซีน
     * @Return int เพื่อบอกสถานะการลบรายการ 
     * @Author Ojika
     * @Date 28/03/2549
     **/
    public int saveEpiTTItem(EpiTTItem obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theEpiTTItemDB.insertData(obj);        
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
     * @Date 28/03/2549
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
