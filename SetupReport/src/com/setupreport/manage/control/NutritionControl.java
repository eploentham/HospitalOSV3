/*
 * NutritionControl.java
 *
 * Created on 16 มีนาคม 2549, 14:22 น.
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
public class NutritionControl
{
    
    /** Creates a new instance of NutritionControl */
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private int iresult;
    Vector vData;
    NutritionMap theNutritionMap;
    UpdateStatus theUS;
    
    public NutritionControl(HosDB hdb, UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    /**
     * สำหรับค้นหาภาวะโภชนาการ
     * @Param keyword เป็นตัวแปรที่เก็บค่าคำค้นที่ผู้ใช้กรอก
     * @Return Vector ของ NutritionLevel
     * @Author Ojika
     * @Date 16/03/2549
     **/
    public Vector selectNutritionLevelByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theNutritionLevelDB.selectByKeyword(keyword);
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
     * สำหรับค้นหาภาวะ ชนิดโภชนาการ
     * @Param keyword เป็นตัวแปรที่เก็บค่าคำค้นที่ผู้ใช้กรอก
     * @Return Vector ของ NutritionType
     * @Author Ojika
     * @Date 16/03/2549
     **/
    public Vector selectNutritionTypeByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theNutritionTypeDB.selectByKeyword(keyword);
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
     * สำหรับค้นหาราย Map ภาวะโภชนาการ จากคำค้น
     * @Param nutritionTypeId เป็น  รหัสของกลุ่มชนิดภาวะโภชนาการ
     * @Return Vector ของ NutritionMap
     * @Author Ojika
     * @Date 16/03/2549
     **/
    public Vector selectNutritionMapByNutritionTypeId(String nutritionTypeId)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theNutritionMapDB.selectByNutritionTypeId(nutritionTypeId);
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
     * สำหรับลบรายการ Map ภาวะโภชนาการ
     * @Param obj เป็น Object ของรายการ Map ภาวะโภชนาการ
     * @Return int เพื่อบอกสถานะการลบรายการ 
     * @Author Ojika
     * @Date 16/03/2549
     **/
    public int deleteNutritionMapByKeyId(NutritionMap obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theNutritionMapDB.deleteByKeyID(obj.getObjectId()); 
            theUS.setStatus("การลบภาวะโภชนาการเสร็จสิ้น", UpdateStatus.COMPLETE);      
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
     * สำหรับบันทึกรายการ Map ภาวะโภชนาการ
     * @Param obj เป็น Object ของรายการ Map ภาวะโภชนาการ
     * @Return int เพื่อบอกสถานะการบันทึกรายการ 
     * @Author Ojika
     * @Date 16/03/2549
     **/
    public int saveNutritionMap(NutritionMap obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theNutritionMapDB.insertData(obj);       
            theUS.setStatus("การบันทึกภาวะโภชนาการเสร็จสิ้น", UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus("การบันทึกภาวะโภชนาการผิดพลาด", UpdateStatus.ERROR);
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
     * @Date 16/03/2549     
     **/
    public int checkSaveNutritionMap(Vector vNutritionMap)
    {
        iresult = 0;
        
        if(vNutritionMap != null && vNutritionMap.size() > 0)
        {
            for(int i=0,size=vNutritionMap.size();i<size;i++)
            {
                this.theNutritionMap = (NutritionMap)vNutritionMap.get(i);
                
                if(this.theNutritionMap.getObjectId() != null && !this.theNutritionMap.getObjectId().equalsIgnoreCase(""))
                {
                    // ส่งข้อมูลไป insert ในฐานข้อมูล
                    iresult = saveNutritionMap(this.theNutritionMap);
                }
            }
        }
        return iresult;
    }
    
}
