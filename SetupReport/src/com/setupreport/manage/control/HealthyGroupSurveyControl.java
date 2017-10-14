/*
 * HealthyGroupSurveyControl.java
 *
 * Created on 9 มีนาคม 2549, 14:24 น.
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
public class HealthyGroupSurveyControl
{
    
    /** Creates a new instance of HealthyGroupSurveyControl */
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private int iresult;
    Vector vData;
    HealthyGroupSurvey theHealthyGroupSurvey;
    HealthyGroup theHealthyGroup;
    UpdateStatus theUS;
    
    public HealthyGroupSurveyControl(HosDB hdb, UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    /**
     * สำหรับค้นหากลุ่มการตรวจร่างกาย
     * @Param keyword เป็นตัวแปรที่เก็บค่าคำค้นที่ผู้ใช้กรอก
     * @Return Vector ของ HealthyGroup
     * @Author Ojika
     * @Date 09/03/2549
     **/
    public Vector selectHealthyGroupByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theHealthyGroupDB.selectByKeyword(keyword);
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
     * สำหรับค้นหากลุ่มย่อยการตรวจร่างกาย
     * @Param keyword เป็นตัวแปรที่เก็บค่าคำค้นที่ผู้ใช้กรอก
     * @Return Vector ของ HealthySubgroup
     * @Author Ojika
     * @Date 14/03/2549
     **/
    public Vector selectHealthySubgroupByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theHealthySubgroupDB.selectByKeyword(keyword);
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
     * สำหรับค้นหารายการสำรวจที่อยู่ในกลุ่มการตรวจสุขภาพ
     * @Param healthyGroupId เป็น  รหัสของกลุ่มการตรวจสุขภาพ
     * @Return Vector ของ HealthyGroupSurvey
     * @Author Ojika
     * @Date 09/03/2549
     **/
    public Vector selectHealthyGroupSurveyByHealthySubgroupId(String healthySubgroupId)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theHealthyGroupSurveyDB.selectByHealthySubgroupId(healthySubgroupId); 
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
     * สำหรับลบรายการสำรวจที่อยู่ในกลุ่มการตรวจสุขภาพ
     * @Param obj เป็น Object ของรายการสำรวจที่อยู่ในกลุ่มการตรวจสุขภาพ
     * @Return int เพื่อบอกสถานะการลบรายการ 
     * @Author Ojika
     * @Date 09/03/2549
     **/
    public int deleteHealthyGroupSurveyByKeyId(HealthyGroupSurvey obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theHealthyGroupSurveyDB.deleteByKeyID(obj.getObjectId());      
            theUS.setStatus("การลบรายการสำรวจในกลุ่มตรวจสุขภาพเสร็จสิ้น", UpdateStatus.COMPLETE); 
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
     * สำหรับบันทึกรายการสำรวจที่อยู่ในกลุ่มการตรวจสุขภาพ
     * @Param obj เป็น Object ของรายการสำรวจที่อยู่ในกลุ่มการตรวจสุขภาพ
     * @Return int เพื่อบอกสถานะการบันทึกรายการ 
     * @Author Ojika
     * @Date 09/03/2549
     **/
    public int saveHealthyGroupSurvey(HealthyGroupSurvey obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theHealthyGroupSurveyDB.insertData(obj);     
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
     * สำหรับค้นหา แบบสำรวจที่มี
     * @Param keyword เป็นตัวแปรที่เก็บค่าคำค้นที่ผู้ใช้กรอก
     * @Return Vector ของ HealthySurvey
     * @Author Ojika
     * @Date 09/03/2549
     **/
    public Vector selectHealthySurveyByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theHealthySurveyDB.selectByKeyword(keyword);             
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
