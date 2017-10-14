/*
 * AricDiseaseCodeControl.java
 *
 * Created on 26 ตุลาคม 2548, 18:46 น.
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
public class AricDiseaseCodeControl {
    
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData,vcDataTemp;
    private int iresult;
    UpdateStatus theUS;
    public AricDiseaseCodeControl(HosDB hdb,UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    
    
    /**
     * ใช้ในการ ค้นหา กลุ่มของรหัสโรคจาก aricgroup
     * @param aricgroup  เป็น รหัสของ aricgroup ที่ต้องการค้นหาว่า มีรหัสอะไรบ้าง
     * @return เป็น Vector ของ Object AricDiseaseCode
     *  ถ้าไม่มีค่าจะ return เป็น null
     */
    public Vector selectAricDiseaseCodeByAricGroup(String aricgroup)
    {   vcData = null;
        try
        {
            theConnectionInf.open();
            vcData = (Vector)theHosDB.theAricDiseaseCodeDB.selectByAricGroupID(aricgroup);
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
    
    public int saveAricDiseaseCode(AricDiseaseCode aricdiseasecode)
    {   iresult = 0;
        try
        {
            theConnectionInf.open();
            if(aricdiseasecode.getObjectId() == null)
            {
                iresult = theHosDB.theAricDiseaseCodeDB.insertData(aricdiseasecode);
            }
            else
            {
                iresult = theHosDB.theAricDiseaseCodeDB.updateData(aricdiseasecode);
            }
            theUS.setStatus("การบันทึกรหัสโรคกลุ่ม ARIC เสร็จสิ้น",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus("การบันทึกรหัสโรคกลุ่ม ARIC ผิดพลาด",UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return iresult;
    }
    
    public int deleteAricDiseaseCodeByAricGroupID(String aricgroupid)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
           
                iresult = theHosDB.theAricDiseaseCodeDB.deleteAricGroupID(aricgroupid);
           theUS.setStatus("การลบกลุ่มของ ARIC เสร็จสิ้น",UpdateStatus.COMPLETE);
            
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
    
    
    public int deleteAricDiseaseCode(AricDiseaseCode aricdiseasecode)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(aricdiseasecode.getObjectId() != null)
            {
                iresult = theHosDB.theAricDiseaseCodeDB.deleteByKeyID(aricdiseasecode.getObjectId());
            }
            theUS.setStatus("การลบรหัสโรคกลุ่ม ARIC เสร็จสิ้น",UpdateStatus.COMPLETE);
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
