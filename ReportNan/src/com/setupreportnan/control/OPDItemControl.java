/*
 * OPDItemControl.java
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
import com.reportnan.object.OPDItem;
import java.util.Vector;
/**
 *
 * @author ojika
 */
public class OPDItemControl
{
    ConnectionInf theConnectionInf;
    ManageDB theManageDB;
    int iresult =0;
    Vector vData;
    
    public OPDItemControl(ManageDB mdb)
    {
        theManageDB = mdb;
        theConnectionInf = this.theManageDB.theConnectionInf;        
    }
    
    /**
     * สำหรับค้นหา รายการตรวจรักษา OPD
     * @Param keyword เป็น ค่าที่ผู้ใช้กรอกเพื่อค้นหา
     * @Return Vector ของ รายการตรวจรักษา OPD
     * @Author ojika
     * @Date 21/06/2549
     **/
    public Vector searchOPDItemByKeyword(String keyword)
    {
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theManageDB.theOPDItemDB.selectByKeyword(keyword); 
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
     * สำหรับลบรายการตรวจรักษาของ OPD
     * @Param OPDItemId เป็น รหัสของรายการตรวจรักษา OPD
     * @Return int เป็นสถานะการลบรายการ
     * @Author ojika
     * @Date 21/06/2549
     **/
    public int deleteOPDItem(String OPDItemId)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theManageDB.theOPDItemDB.deleteByKeyID(OPDItemId);             
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
     * สำหรับบันทึกรายการตรวจรักษาของ OPD
     * @Param theOPDItem เป็น Object รหัสของรายการตรวจรักษา OPD
     * @Return int เป็นสถานะการบันทึกรายการ
     * @Author ojika
     * @Date 21/06/2549
     **/
    public int saveOPDItem(OPDItem theOPDItem)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(theOPDItem != null)
            {
                if(theOPDItem.getObjectId() != null)
                {
                    // แก้ไขรายการ
                    iresult = theManageDB.theOPDItemDB.updateData(theOPDItem);             
                }
                else
                {
                    //บันทึกใหม่
                    iresult = theManageDB.theOPDItemDB.insertData(theOPDItem);    
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
