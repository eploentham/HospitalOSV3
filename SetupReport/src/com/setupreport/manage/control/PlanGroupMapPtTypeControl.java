/*
 * PlanGroupMapPtTypeControl.java
 *
 * Created on 27 ตุลาคม 2548, 20:11 น.
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
public class PlanGroupMapPtTypeControl {
    
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;
    private int iresult;
    private boolean bresult;
    UpdateStatus theUS;
    public PlanGroupMapPtTypeControl(HosDB hdb, UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    
    public boolean checkSamePlanGroupID(String plangroupid,String key_id)
    {   bresult = false;
        try
        {
            theConnectionInf.open();
            bresult = theHosDB.thePlanGroupMapPtTypeDB.checkSamePlanGroupID(plangroupid);
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
    
     public boolean checkSamePlanGroupPtType(String pttype,String key_id)
    {   bresult = false;
        try
        {
            theConnectionInf.open();
            bresult = theHosDB.thePlanGroupMapPtTypeDB.checkSamePlanGroupPtType(pttype,key_id);
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
     * ใช้ในการ ค้นหา ข้อมูลของ PlanGroupMapPtType ตามข้อมูลที่จะค้นหา
     * ทั้งรหัส และคำอธิบาย
     * @param search เป็น String ของข้อความที่จะค้นหา
     * @return เป็น Vector ของ Object AricSricGroup
     *  ถ้าไม่มีค่าจะ return เป็น null
     */
    public Vector selectPlanGroupMapPtTypeByCodeOrDescription(String search)
    {   vcData = null;
        try
        {
            theConnectionInf.open();
            
            vcData = (Vector)theHosDB.thePlanGroupMapPtTypeDB.selectBySearch(search);
            
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
    
    public int savePlanGroupMapPtType(PlanGroupMapPtType plangroupmappttype)
    {   iresult = 0;
        try
        {
            theConnectionInf.open();
            if(plangroupmappttype.getObjectId() == null)
            {
                iresult = theHosDB.thePlanGroupMapPtTypeDB.insertData(plangroupmappttype);
            }
            else
            {
                iresult = theHosDB.thePlanGroupMapPtTypeDB.updateData(plangroupmappttype);
            }
            theUS.setStatus("การบันทึกการจับคู่ระหว่าง PTType กับกลุ่มสิทธิเสร็จสิ้น", UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus("การบันทึกการจับคู่ระหว่าง PTType กับกลุ่มสิทธิผิดพลาด", UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return iresult;
    }
    
    public int deletePlanGroupMapPtType(PlanGroupMapPtType plangroupmappttype)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(plangroupmappttype.getObjectId() != null)
            {
                iresult = theHosDB.thePlanGroupMapPtTypeDB.deleteByKeyID(plangroupmappttype.getObjectId());
            }
            theUS.setStatus("การลบการจับคู่ระหว่าง PTType กับกลุ่มสิทธิเสร็จสิ้น", UpdateStatus.COMPLETE);
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
