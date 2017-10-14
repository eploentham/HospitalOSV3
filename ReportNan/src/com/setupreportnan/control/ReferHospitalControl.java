/*
 * ReferHospitalControl.java
 *
 * Created on 20 มิถุนายน 2549, 18:06 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreportnan.control;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.setupreportnan.control.ManageDB;
import com.reportnan.object.ReferHospital;
import java.util.Vector;
/**
 *
 * @author ojika
 */
public class ReferHospitalControl
{
    ConnectionInf theConnectionInf;
    ManageDB theManageDB;
    int iresult =0;
    Vector vData;
    
    public ReferHospitalControl(ManageDB mdb)
    {
        theManageDB = mdb;
        theConnectionInf = this.theManageDB.theConnectionInf;        
    }
    
    /**
     * สำหรับค้นหา รายการสถานพยาบาลที่ต้องการดูข้อมูลการ Refer
     * @Param keyword เป็น ค่าที่ผู้ใช้กรอกเพื่อค้นหา
     * @Return Vector ของ รายการตรวจรักษาที่เป็นหัตถการ
     * @Author ojika
     * @Date 21/06/2549
     **/
    public Vector searchReferHospitalByKeyword(String keyword)
    {
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theManageDB.theReferHospitalDB.selectByKeyword(keyword);  
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
     * สำหรับลบรายการ สถานพยาบาลที่ต้องการดูข้อมูลการ Refer
     * @Param ReferHospitalId เป็น รหัสของรายการ สถานพยาบาลที่ต้องการดูข้อมูลการ Refer
     * @Return int เป็นสถานะการลบรายการ
     * @Author ojika
     * @Date 21/06/2549
     **/
    public int deleteReferHospital(String ReferHospitalId)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theManageDB.theReferHospitalDB.deleteByKeyID(ReferHospitalId);             
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
     * สำหรับบันทึกรายการ สถานพยาบาลที่ต้องการดูข้อมูลการ Refer
     * @Param theReferHospital เป็น Object รหัสของรายการ สถานพยาบาลที่ต้องการดูข้อมูลการ Refer
     * @Return int เป็นสถานะการบันทึกรายการ
     * @Author ojika
     * @Date 21/06/2549
     **/
    public int saveReferHospital(ReferHospital theReferHospital)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(theReferHospital != null)
            {
                if(theReferHospital.getObjectId() != null)
                {
                    // แก้ไขรายการ
                    iresult = theManageDB.theReferHospitalDB.updateData(theReferHospital);              
                }
                else
                {
                    //บันทึกใหม่
                    iresult = theManageDB.theReferHospitalDB.insertData(theReferHospital);    
                }
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
        
        return iresult;
    } 
}
