/*
 * AricSubGroupControl.java
 *
 * Created on 24 ตุลาคม 2548, 12:07 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage.control;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.connection.UpdateStatus;
import com.setupreport.manage.control.HosDB;
import com.setupreport.object.*;
import java.util.Vector;
/**
 *
 * @author tong(Padungrat)
 */
public class AricSubGroupControl {
    
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;
    private int iresult;
    private boolean bresult;
    UpdateStatus theUS;
    public AricSubGroupControl(HosDB hdb,UpdateStatus us)
    {
        theHosDB = hdb;
        theUS = us;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    /**
     *  ใช้ในการตรวจสอบ หาว่า Description ซ้ำกันหรือไม่ ถ้ามีซ้ำกัน จะ ให้ค่าเป็น false ถ้าไม่ซ้ำจะให้ค่าเป็น true
     *  @return เป็น boolean ถ้าเป็น true จะไม่ซ้ำ ถ้าเป็น false จะซ้ำ
     */
    public boolean checkSameAricSubGroupDescription(String aricgroupdescription)
    {
        bresult = false;
        
        try
        {
            theConnectionInf.open();
            bresult = theHosDB.theAricSubGroupDB.checkSameAricGroupDescription(aricgroupdescription);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        
        
        return bresult;
    }
    
    /**
     *  ใช้ในการตรวจสอบ หาว่า Code ซ้ำกันหรือไม่ ถ้ามีซ้ำกัน จะ ให้ค่าเป็น false ถ้าไม่ซ้ำจะให้ค่าเป็น true
     *  @return เป็น boolean ถ้าเป็น true จะไม่ซ้ำ ถ้าเป็น false จะซ้ำ
     */
    public boolean checkSameAricSubGroupCode(String aricgroupcode)
    {
        bresult = false;
        
        try
        {
            theConnectionInf.open();
            bresult = theHosDB.theAricSubGroupDB.checkSameAricGroupCode(aricgroupcode);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        
        
        return bresult;
    }
    /**
     * ใช้ในการ ค้นหา ข้อมูลของ AricSubGroup ตามข้อมูลที่จะค้นหา
     * ทั้งรหัส และคำอธิบาย
     * @param search เป็น String ของข้อความที่จะค้นหา
     * @return เป็น Vector ของ Object AricSricGroup
     *  ถ้าไม่มีค่าจะ return เป็น null
     */
    public Vector selectAricSubGroupByCodeOrDescription(String search)
    {   vcData = null;
        try
        {
            theConnectionInf.open();
            
            vcData = (Vector)theHosDB.theAricSubGroupDB.selectBySearch(search);
            
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
    
    public int saveAricSubGroup(AricSubGroup aricsubgroup)
    {   iresult = 0;
        try
        {
            theConnectionInf.open();
            if(aricsubgroup.getObjectId() == null)
            {
                iresult = theHosDB.theAricSubGroupDB.insertData(aricsubgroup);
            }
            else
            {
                iresult = theHosDB.theAricSubGroupDB.updateData(aricsubgroup);
            }
            theUS.setStatus("การบันทึกกลุ่มย่อยของ ARIC เสร็จสิ้น",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus("การบันทึกกลุ่มย่อยของ ARIC ผิดพลาด",UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return iresult;
    }
    
    public int deleteAricSubGroup(AricSubGroup aricsubgroup)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(aricsubgroup.getObjectId() != null)
            {
                iresult = theHosDB.theAricSubGroupDB.deleteByKeyID(aricsubgroup.getObjectId());
            }
            theUS.setStatus("การลบกลุ่มย่อยของ ARIC เสร็จสิ้น",UpdateStatus.COMPLETE);
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
