/*
 * EpiGroupItemControl.java
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
public class EpiGroupItemControl
{
    
    /** Creates a new instance of EpiGroupItemControl */
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private int iresult;
    Vector vData;
    EpiGroupItem theEpiGroupItem;
    EpiGroup theEpiGroup;
    UpdateStatus theUS;
    
    public EpiGroupItemControl(HosDB hdb, UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    /**
     * สำหรับค้นหากลุ่มวัคซีนจากคำค้น
     * @Param keyword เป็นตัวแปรที่เก็บค่าคำค้นที่ผู้ใช้กรอก
     * @Return Vector ของ EpiGroup
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public Vector selectEpiGroupByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theEpiGroupDB.selectByKeyword(keyword);
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
     * สำหรับค้นหารายการวัคซีน ของกลุ่มวัคซีนจากคำค้น
     * @Param epiGroupId เป็น  รหัสของกลุ่มวัคซีน
     * @Return Vector ของ EpiGroupItem
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public Vector selectEpiGroupItemByEpiGroupId(String epiGroupId)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theEpiGroupItemDB.selectByEpiGroupId(epiGroupId);
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
     * สำหรับลบรายการวัคซีนของกลุ่มวัคซีนการดูแลเด็ก
     * @Param obj เป็น Object ของรายการวัคซีนของกลุ่มวัคซีนการดูแลเด็ก
     * @Return int เพื่อบอกสถานะการลบรายการ 
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public int deleteEpiGroupItemByKeyId(EpiGroupItem obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theEpiGroupItemDB.deleteByKeyID(obj.getObjectId());    
            theUS.setStatus("การลบรายการวัคซีนดูแลเด็กเล็กเสร็จสิ้น", UpdateStatus.COMPLETE);   
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
     * สำหรับบันทึกรายการวัคซีนของกลุ่มวัคซีนการดูแลเด็ก
     * @Param obj เป็น Object ของรายการวัคซีนของกลุ่มวัคซีนการดูแลเด็ก
     * @Return int เพื่อบอกสถานะการบันทึกรายการ 
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public int saveEpiGroupItem(EpiGroupItem obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theEpiGroupItemDB.insertData(obj);      
            theUS.setStatus("การบันทึกรายการวัคซีนดูแลเด็กเล็กเสร็จสิ้น", UpdateStatus.COMPLETE); 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus("การบันทึกรายการวัคซีนดูแลเด็กเล็กผิดพลาด", UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        } 
        
        return iresult;
    }
    
    /**
     * สำหรับตรวจสอบข้อมูลซ้ำว่าจะต้อง insert ใหม่หรือไม่
     * @Author Ojika
     * @Date 09/03/2549     
     **/
    public int checkSaveEpiGroupItem(Vector vEpiGroupItem)
    {
        iresult = 0;
        
        if(vEpiGroupItem != null && vEpiGroupItem.size() > 0)
        {
            for(int i=0,size=vEpiGroupItem.size();i<size;i++)
            {
                this.theEpiGroupItem = (EpiGroupItem)vEpiGroupItem.get(i);
                
                if(this.theEpiGroupItem.getObjectId() != null && !this.theEpiGroupItem.getObjectId().equalsIgnoreCase(""))
                {
                    // ส่งข้อมูลไป insert ในฐานข้อมูล
                    iresult = saveEpiGroupItem(this.theEpiGroupItem);
                }
            }
        }
        
        return iresult;
    }
        
}
