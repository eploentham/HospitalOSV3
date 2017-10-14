/*
 * EyeGroupControl.java
 *
 * Created on 26 ตุลาคม 2548, 15:45 น.
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
 * @author americus
 */
public class EyeGroupControl
{
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;
    private int iresult;
    boolean bresult;
    UpdateStatus theUS;
    /** Creates a new instance of EyeGroupControl */
    public EyeGroupControl(HosDB hdb,UpdateStatus us)
    {
        theHosDB = hdb;
        theUS = us;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    /**
     * ใช้ในการ ค้นหา ข้อมูลของ EyeGroup ตามข้อมูลที่จะค้นหา
     * ทั้งรหัส และคำอธิบาย
     * @param search เป็น String ของข้อความที่จะค้นหา
     * @return เป็น Vector ของ Object EyeGroup
     *  ถ้าไม่มีค่าจะ return เป็น null
     */
    public Vector selectEyeGroupByCodeOrDescription(String search,String active)
    {   vcData = null;
        try
        {
            theConnectionInf.open();
            vcData = (Vector)theHosDB.theEyeGroupDB.selectBySearch(search,active);
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
    
    /*
     *บันทึกข้อมูล EyeGroup   
     * @param eyegroup เป็น Object ของ Eyegroup ที่ต้องการบันทึก
     * @return เป็น Integer สำหรับระบุว่าการบันทึกสำเร็จหรือไม่
     *  ถ้า return 1 บันทึกสำเร็จ
     *  ถ้า return 0 บันทึกไม่สำเร็จ
     **/
    public int saveEyeGroup(EyeGroup eyegroup)
    {   iresult = 0;
        try
        {
            theConnectionInf.open();
            if(eyegroup.getObjectId() == null)
            {
                iresult = theHosDB.theEyeGroupDB.insertData(eyegroup);
            }
            else
            {
                iresult = theHosDB.theEyeGroupDB.updateData(eyegroup);
            }
            theUS.setStatus("การบันทึกกลุ่มของรหัสโรคตาเสร็จสิ้น",UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            ex.printStackTrace();
            theUS.setStatus("การบันทึกกลุ่มของรหัสโรคตาผิดพลาด",UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return iresult;
    }
    /*
     *ลบข้อมูล EyeGroup 
     * @param eyegroup เป็น Object ของ Eyegroup ที่ต้องการบันทึก
     * @return เป็น Integer สำหรับระบุว่าการบันทึกสำเร็จหรือไม่
     *  ถ้า return 1 ลบสำเร็จ
     *  ถ้า return 0 ลบไม่สำเร็จ
     */
    public int deleteEyeGroup(EyeGroup eyegroup)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(eyegroup.getObjectId() != null)
            {
                iresult = theHosDB.theEyeGroupDB.deleteByKeyID(eyegroup.getObjectId());
            }
            theUS.setStatus("การลบกลุ่มของรหัสโรคตาเสร็จสิ้น",UpdateStatus.COMPLETE);
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
     *  ใช้ในการตรวจสอบ หาว่า Code ซ้ำกันหรือไม่ ถ้ามีซ้ำกัน จะ ให้ค่าเป็น false ถ้าไม่ซ้ำจะให้ค่าเป็น true
     *  @return เป็น boolean ถ้าเป็น true จะไม่ซ้ำ ถ้าเป็น false จะซ้ำ
     */
    public boolean checkSameEyeGroupCode(String eyegroupcode,String key_id)
    {
        bresult = false;
        
        try
        {
            theConnectionInf.open();
            bresult = theHosDB.theEyeGroupDB.checkSameEyeGroupCode(eyegroupcode, key_id);
            
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
     *  ใช้ในการตรวจสอบ หาว่า Description ซ้ำกันหรือไม่ ถ้ามีซ้ำกัน จะ ให้ค่าเป็น false ถ้าไม่ซ้ำจะให้ค่าเป็น true
     *  @return เป็น boolean ถ้าเป็น true จะไม่ซ้ำ ถ้าเป็น false จะซ้ำ
     */
    public boolean checkSameEyeGroupThaiDescription(String thaidescrip,String key_id)
    {
        bresult = false;
        
        try
        {
            theConnectionInf.open();
            bresult = theHosDB.theEyeGroupDB.checkSameEyeGroupThaiDescription(thaidescrip, key_id);
            
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
     *  ใช้ในการตรวจสอบ หาว่า Description ซ้ำกันหรือไม่ ถ้ามีซ้ำกัน จะ ให้ค่าเป็น false ถ้าไม่ซ้ำจะให้ค่าเป็น true
     *  @return เป็น boolean ถ้าเป็น true จะไม่ซ้ำ ถ้าเป็น false จะซ้ำ
     */
    public boolean checkSameEyeGroupEngDescription(String engdescrip,String key_id)
    {
        bresult = false;
        
        try
        {
            theConnectionInf.open();
            bresult = theHosDB.theEyeGroupDB.checkSameEyeGroupThaiDescription(engdescrip, key_id);
            
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
}
