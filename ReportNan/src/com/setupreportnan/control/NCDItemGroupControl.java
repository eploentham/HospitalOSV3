/*
 * NCDItemGroupControl.java
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
import com.reportnan.object.NCDItemGroupMapItem;
import java.util.Vector;
/**
 *
 * @author ojika
 */
public class NCDItemGroupControl
{
    ConnectionInf theConnectionInf;
    ManageDB theManageDB;
    int iresult =0;
    Vector vData;
    
    public NCDItemGroupControl(ManageDB mdb)
    {
        theManageDB = mdb;
        theConnectionInf = this.theManageDB.theConnectionInf;        
    }
    
    /**
     * สำหรับค้นหากลุ่มรายการ NCD 
     * @Param keyword เป็น ค่าที่ผู้ใช้กรอกเพื่อค้นหา
     * @Return Vector ของ กลุ่มรายการ NCD
     * @Author ojika
     * @Date 20/06/2549
     **/
    public Vector searchNCDItemGroupByKeyword(String keyword)
    {
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theManageDB.theNCDItemGroupDB.selectByKeyword(keyword);   
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
     * สำหรับค้นหารายการตรวจรักษาของ กลุ่มรายการ NCD 
     * @Param NCDItemGroupId เป็น รหัสกลุ่มของรายการ NCD
     * @Return Vector ของ รายการตรวจรักษาของกลุ่มรายการ NCD
     * @Author ojika
     * @Date 20/06/2549
     **/
    public Vector searchNCDItemGroupMapItemByNCDItemGroupId(String NCDItemGroupId)
    {
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theManageDB.theNCDItemGroupMapItemDB.selectByNCDItemGroupID(NCDItemGroupId);             
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
     * สำหรับลบรายการตรวจรักษาของ กลุ่มรายการ NCD 
     * @Param NCDItemGroupMapItemId เป็น รหัสกลุ่มของรายการ NCD
     * @Return int เป็นสถานะการลบรายการ
     * @Author ojika
     * @Date 20/06/2549
     **/
    public int deleteNCDItemGroupMapItem(String NCDItemGroupMapItemId)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theManageDB.theNCDItemGroupMapItemDB.deleteByKeyID(NCDItemGroupMapItemId);             
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
     * สำหรับบันทึกรายการตรวจรักษาของ กลุ่มรายการ NCD 
     * @Param Object ของ รายการตรวจรักษาของ กลุ่มรายการ NCD 
     * @Return int เป็นสถานะการบันทึกรายการ
     * @Author ojika
     * @Date 20/06/2549
     **/
    public int saveNCDItemGroupMapItem(NCDItemGroupMapItem theNCDItem)
    {
        iresult = 0;
        if(theNCDItem != null)
        {
            try
            {
                theConnectionInf.open();
                // สั่งบันทึกรายการตรวจรักษาของ กลุ่มรายการ NCD
                iresult = this.theManageDB.theNCDItemGroupMapItemDB.insertData(theNCDItem);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                theConnectionInf.close();
            }                                                
        }
        
        return iresult;
       
    }
    
}
