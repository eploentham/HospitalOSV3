/*
 * EyeDiseaseCodeControl.java
 *
 * Created on 26 ตุลาคม 2548, 15:46 น.
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
public class EyeDiseaseCodeControl
{
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;
    private Object objectIcd10;
    private int iresult;
    UpdateStatus theUS;
    /** Creates a new instance of EyeDiseaseCodeControl */
    public EyeDiseaseCodeControl(HosDB hdb,UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    /**
     * ใช้ในการ ค้นหา ข้อมูลของ EyeDiseaseCode ตามข้อมูลที่จะค้นหา
     * ทั้งรหัส และคำอธิบาย
     * @param search เป็น String ของข้อความที่จะค้นหา
     * @return เป็น Vector ของ Object EyeDiseaseCode
     *  ถ้าไม่มีค่าจะ return เป็น null
     */
    public Vector selectEyeDiseaseCodeByEyeGroupID(String key)
    {   vcData = null;
        try
        {
            theConnectionInf.open();
            vcData = (Vector)theHosDB.theEyeDiseaseCodeDB.selectByEyeGroupID(key);
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
     *บันทึกข้อมูล EyeDiseaseCode
     * @param eyedisease เป็น Object ของ EyeDiseaseCode ที่ต้องการบันทึก
     * @return เป็น Integer สำหรับระบุว่าการบันทึกสำเร็จหรือไม่
     *  ถ้า return 1 บันทึกสำเร็จ
     *  ถ้า return 0 บันทึกไม่สำเร็จ
     **/
    public int saveEyeDiseaseCode(EyeDiseaseCode eyedisease)
    {   iresult = 0;
        try
        {
            theConnectionInf.open();
            if(eyedisease.getObjectId() == null)
            {
                iresult = theHosDB.theEyeDiseaseCodeDB.insertData(eyedisease);
            }
            else
            {
                iresult = theHosDB.theEyeDiseaseCodeDB.updateData(eyedisease);
            } 
            theUS.setStatus("การบันทึกรหัสโรคกลุ่มโรคตาเสร็จสิ้น",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus("การบันทึกรหัสโรคกลุ่มโรคตาเสร็จสิ้น",UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return iresult;
    }
    
    /*
     *ลบข้อมูล EyeDiseaseCode
     * @param eyedisease เป็น Object ของ EyeDiseaseCode ที่ต้องการบันทึก
     * @return เป็น Integer สำหรับระบุว่าการบันทึกสำเร็จหรือไม่
     *  ถ้า return 1 ลบสำเร็จ
     *  ถ้า return 0 ลบไม่สำเร็จ
     */
    public int deleteEyeDiseaseCode(EyeDiseaseCode eyedisease)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(eyedisease.getObjectId() != null)
            {
                iresult = theHosDB.theEyeDiseaseCodeDB.deleteByKeyID(eyedisease.getObjectId());
            }
            theUS.setStatus("การลบรหัสโรคกลุ่มโรคตาเสร็จสิ้น",UpdateStatus.COMPLETE);
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
    
    public int deleteEyeDiseaseCodeByEyeGroupID(String eyegroupid)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            
            iresult = theHosDB.theEyeDiseaseCodeDB.deleteEyeDiseaseByGroupID(eyegroupid);            
            theUS.setStatus("การลบรหัสโรคกลุ่มโรคตาเสร็จสิ้น",UpdateStatus.COMPLETE);
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
    
   public Object selectIcd10BySearch(String icd10)
   {
        objectIcd10 = null;
        try
        {
            theConnectionInf.open();
            objectIcd10 = theHosDB.theICD10DB.selectBySearch(icd10);
            System.out.println("----------Control : " + objectIcd10);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        
        return objectIcd10;
   }
        
   /*public boolean checkHaveICD10(String icd10,String key_id)
   {
       objectIcd10 = null;
       try
       {
           theConnectionInf.open();
           objectIcd10 = theHosDB.theICD10DB.checkHaveICD10(icd10, key_id);
           System.out.println("----------Control : " + objectIcd10);
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       finally
       {
           theConnectionInf.close();
       }
       
       return objectIcd10;
   }
    */
}
