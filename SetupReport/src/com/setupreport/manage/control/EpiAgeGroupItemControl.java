/*
 * EpiAgeGroupItemControl.java
 *
 * Created on 29 มีนาคม 2549, 15:20 น.
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
public class EpiAgeGroupItemControl
{
    
    /** Creates a new instance of EpiAgeGroupItemControl */
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private int iresult;
    Vector vData;
    EpiAgeGroupItem theEpiAgeGroupItem;
    EpiAgeGroup theEpiAgeGroup;
    UpdateStatus theUS;
    
    public EpiAgeGroupItemControl(HosDB hdb, UpdateStatus us)
    {
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
        theUS = us;
    }
    
    /**
     * สำหรับค้นหาช่วงอายุการรับวัคซีนจากคำค้น
     * @Param keyword เป็นตัวแปรที่เก็บค่าคำค้นที่ผู้ใช้กรอก
     * @Return Vector ของ EpiAgeGroup
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public Vector selectEpiAgeGroupByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theEpiAgeGroupDB.selectByKeyword(keyword);
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
     * สำหรับค้นหารายการวัคซีน ของช่วงอายุการรับวัคซีนจากคำค้น
     * @Param EpiAgeGroupId เป็น  รหัสของช่วงอายุการรับวัคซีน
     * @Return Vector ของ EpiAgeGroupItem
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public Vector selectEpiAgeGroupItemByEpiAgeGroupId(String EpiAgeGroupId)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theEpiAgeGroupItemDB.selectByEpiAgeGroupId(EpiAgeGroupId); 
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
     * สำหรับลบรายการวัคซีนของช่วงอายุการรับวัคซีน
     * @Param obj เป็น Object ของรายการวัคซีนของช่วงอายุการรับวัคซีน
     * @Return int เพื่อบอกสถานะการลบรายการ 
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public int deleteEpiAgeGroupItemByKeyId(EpiAgeGroupItem obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theEpiAgeGroupItemDB.deleteByKeyID(obj.getObjectId());    
            theUS.setStatus("การลบช่วงอายุการรับวัคซีนเสร็จสิ้น", UpdateStatus.COMPLETE);   
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
     * สำหรับลบรายการวัคซีนของช่วงอายุการรับวัคซีน
     * @Param obj เป็น Object ของรายการวัคซีนของช่วงอายุการรับวัคซีน
     * @Return int เพื่อบอกสถานะการลบรายการ 
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public int deleteEpiAgeGroupItemByEpiAgeGroupID(String EpiAgeGroupID)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theEpiAgeGroupItemDB.deleteByEpiAgeGroupID(EpiAgeGroupID);    
            theUS.setStatus("การลบช่วงอายุการรับวัคซีนเสร็จสิ้น", UpdateStatus.COMPLETE);   
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
     * สำหรับบันทึกรายการวัคซีนของช่วงอายุการรับวัคซีน
     * @Param obj เป็น Object ของรายการวัคซีนของช่วงอายุการรับวัคซีน
     * @Return int เพื่อบอกสถานะการบันทึกรายการ 
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public int saveEpiAgeGroupItem(EpiAgeGroupItem obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theEpiAgeGroupItemDB.insertData(obj);         
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
     * สำหรับตรวจสอบข้อมูลซ้ำว่าจะต้อง insert ใหม่หรือไม่
     * @Author Ojika
     * @Date 29/03/2549     
     **/
    public int checkSaveEpiAgeGroupItem(Vector vEpiAgeGroupItem)
    {
        iresult = 0;
        
        if(vEpiAgeGroupItem != null && vEpiAgeGroupItem.size() > 0)
        {
            for(int i=0,size=vEpiAgeGroupItem.size();i<size;i++)
            {
                this.theEpiAgeGroupItem = (EpiAgeGroupItem)vEpiAgeGroupItem.get(i);
                
                if(this.theEpiAgeGroupItem.getObjectId() != null && !this.theEpiAgeGroupItem.getObjectId().equalsIgnoreCase(""))
                {
                    // ส่งข้อมูลไป insert ในฐานข้อมูล
                    iresult = saveEpiAgeGroupItem(this.theEpiAgeGroupItem);
                }
            }
        }
        
        return iresult;
    }
    
    /**
     * สำหรับค้นหาชนิดของช่วงอายุ
     * @Param EpiAgeGroupTypeId เป็น  รหัสของชนิดช่วงอายุการรับวัคซีน
     * @Return Object ของ EpiAgeGroupType
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public EpiAgeGroupType selectEpiAgeGroupTypeById(String EpiAgeGroupTypeId)
    {      
        EpiAgeGroupType theEpiAgeGroupType = new EpiAgeGroupType();
        try
        {
            theConnectionInf.open();
            theEpiAgeGroupType = theHosDB.theEpiAgeGroupTypeDB.selectByPk(EpiAgeGroupTypeId); 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        } 
        
        return theEpiAgeGroupType;
    }
    
    /**
     * สำหรับลบช่วงอายุการรับวัคซีน
     * @Param obj เป็น Object ของช่วงอายุการรับวัคซีน
     * @Return int เพื่อบอกสถานะการลบรายการ 
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public int deleteEpiAgeGroupByKeyId(EpiAgeGroup obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theEpiAgeGroupDB.deleteByKeyID(obj.getObjectId());       
            theUS.setStatus("การลบช่วงอายุการรับวัคซีนเสร็จสิ้น", UpdateStatus.COMPLETE);   
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
     * ตรวจสอบข้อมูลซ้ำกันกับในฐานข้อมูล
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public boolean checkSameNumber(String number)
    {        
        Vector v = new Vector();
        boolean checkSame = true;  
        
        v = selectEpiAgeGroupByNumber(number);
        
        if(v != null && v.size() > 0)
        {
            checkSame = true;
        }
        else
        {
            checkSame = false;
        }
        
        return checkSame;
    }
    
    /**
     * สำหรับค้นหาช่วงอายุการรับวัคซีนจาก number
     * @Param number
     * @Return Vector ของ EpiAgeGroup
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public Vector selectEpiAgeGroupByNumber(String number)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theEpiAgeGroupDB.selectByNumber(number); 
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
     * สำหรับบันทึกหรือ แก้ไข ช่วงอายุการรับวัคซีน
     * @Param obj เป็น Object ของช่วงอายุการรับวัคซีน
     * @Return int เพื่อบอกสถานะการลบรายการ 
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public int saveEpiAgeGroup(EpiAgeGroup obj,int saveNewEpiAgeGroup)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(saveNewEpiAgeGroup == 0)
            {
                iresult = theHosDB.theEpiAgeGroupDB.updateData(obj);       
            }
            else
            {
                iresult = theHosDB.theEpiAgeGroupDB.insertData(obj);       
            }      
            theUS.setStatus("การบันทึกช่วงอายุการรับวัคซีนเสร็จสิ้น",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus("การบันทึกช่วงอายุการรับวัคซีนผิดพลาด",UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        } 
        
        return iresult;
    }
        
}
