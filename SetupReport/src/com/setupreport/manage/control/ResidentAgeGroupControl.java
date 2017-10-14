/*
 * ResidentAgeGroupControl.java
 *
 * Created on 2 มีนาคม 2549, 14:12 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage.control;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.connection.UpdateStatus;
import com.setupreport.manage.control.HosDB;
import com.setupreport.object.ResidentAgeGroup;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class ResidentAgeGroupControl
{ 
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;
    private Vector vAgePortion;
    private int iresult;
    private boolean bresult;
    UpdateStatus theUS;
    /** Creates a new instance of ResidentAgeGroupControl */
    
    public ResidentAgeGroupControl(HosDB hdb, UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
  
    /**
     * ใช้ในการ ค้นหา ข้อมูลของ ResidentAgeGroup ตามข้อมูลที่จะค้นหา
     * ทั้งรหัส และคำอธิบาย
     * @param search เป็น String ของข้อความที่จะค้นหา
     * @return เป็น Vector ของ Object ResidentAgeGroup
     * ถ้าไม่มีค่าจะ return เป็น null
     * @Date 02/03/2006
     * @Author pu
     */
    public Vector selectResidentAgeGroupByCodeOrDescription(String search)
    {   vcData = null;
        try
        {
            theConnectionInf.open();            
            vcData = (Vector)theHosDB.theResidentAgeGroupDB.selectBySearch(search);            
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
    
    /**
     *บันทึกช่วงอายุของประชากร โดยตรวจสอบว่า 
     *เป็นการบันทึกข้อมูลใหม่ หรือเป็นการอัพเดตข้อมูลเดิม
     *@param residentagegroup เป็น Object ของ ResidentAgeGroup ที่ต้องการบันทึก
     *@return เป็น Integer สำหรับระบุว่าการบันทึกสำเร็จหรือไม่
     *  ถ้า return 1 บันทึกสำเร็จ
     *  ถ้า return 0 บันทึกไม่สำเร็จ
     */
    public int saveResidentAgeGroup(ResidentAgeGroup residentagegroup)
    {   iresult = 0;
        try
        {
            theConnectionInf.open();
            if(residentagegroup.getObjectId() == null)
            {
                iresult = theHosDB.theResidentAgeGroupDB.insertData(residentagegroup);
            }
            else
            {
                iresult = theHosDB.theResidentAgeGroupDB.updateData(residentagegroup);
            }
            theUS.setStatus("การบันทึกช่วงอายุสำหรับรายงานประชากรเสร็จสิ้น", UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus("การบันทึกช่วงอายุสำหรับรายงานประชากรผิดพลาด", UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return iresult;
    }
    
    public int deleteResidentAgeGroup(ResidentAgeGroup residentagegroup)
    {
        iresult = 0;
        try 
        {
            theConnectionInf.open();
            if(residentagegroup.getObjectId() != null)
            {
                iresult = theHosDB.theResidentAgeGroupDB.deleteByKeyID(residentagegroup.getObjectId());
            }            
            theUS.setStatus("การลบช่วงอายุสำหรับรายงานประชากรเสร็จสิ้น", UpdateStatus.COMPLETE);
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
