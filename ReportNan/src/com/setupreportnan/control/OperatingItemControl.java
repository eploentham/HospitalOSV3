/*
 * OperatingItemControl.java
 *
 * Created on 20 มิถุนายน 2549, 18:05 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreportnan.control;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.setupreportnan.control.ManageDB;
import com.reportnan.object.OperatingItem;
import java.util.Vector;
/**
 *
 * @author ojika
 */
public class OperatingItemControl
{
    ConnectionInf theConnectionInf;
    ManageDB theManageDB;
    int iresult =0;
    Vector vData;
    
    public OperatingItemControl(ManageDB mdb)
    {
        theManageDB = mdb;
        theConnectionInf = this.theManageDB.theConnectionInf;        
    }
    
    /**
     * สำหรับค้นหา รายการตรวจรักษาที่เป็นหัตถการ
     * @Param keyword เป็น ค่าที่ผู้ใช้กรอกเพื่อค้นหา
     * @Return Vector ของ รายการตรวจรักษาที่เป็นหัตถการ
     * @Author ojika
     * @Date 21/06/2549
     **/
    public Vector searchOperatingItemByKeyword(String keyword)
    {
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theManageDB.theOperatingItemDB.selectByKeyword(keyword); 
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
     * สำหรับลบรายการตรวจรักษา ที่เป็นหัตถการ
     * @Param OperatingItemId เป็น รหัสของรายการตรวจรักษา ที่เป็นหัตถการ
     * @Return int เป็นสถานะการลบรายการ
     * @Author ojika
     * @Date 21/06/2549
     **/
    public int deleteOperatingItem(String OperatingItemId)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theManageDB.theOperatingItemDB.deleteByKeyID(OperatingItemId);             
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
     * สำหรับบันทึกรายการตรวจรักษาของ ที่เป็นหัตถการ
     * @Param theOperatingItem เป็น Object รหัสของรายการตรวจรักษา ที่เป็นหัตถการ
     * @Return int เป็นสถานะการบันทึกรายการ
     * @Author ojika
     * @Date 21/06/2549
     **/
    public int saveOperatingItem(OperatingItem theOperatingItem)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(theOperatingItem != null)
            {
                if(theOperatingItem.getObjectId() != null)
                {
                    // แก้ไขรายการ
                    iresult = theManageDB.theOperatingItemDB.updateData(theOperatingItem);             
                }
                else
                {
                    //บันทึกใหม่
                    iresult = theManageDB.theOperatingItemDB.insertData(theOperatingItem);    
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
