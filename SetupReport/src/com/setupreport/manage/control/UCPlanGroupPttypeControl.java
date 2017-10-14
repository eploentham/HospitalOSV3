/*
 * UCPlanGroupPttypeControl.java
 *
 * Created on 28 ตุลาคม 2548, 10:15 น.
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
public class UCPlanGroupPttypeControl {
    
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;
    private int iresult;
    private boolean bresult;
    UpdateStatus theUS;
    public UCPlanGroupPttypeControl(HosDB hdb,UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    
    public boolean checkSameUCPlanGroupID(String pttypenumber,String key_id)
    {   bresult = false;
        try
        {
            theConnectionInf.open();
            bresult = theHosDB.theUCPlanGroupPttypeDB.checkSameUCPlanGroupCode(pttypenumber,key_id);
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
    
    public boolean checkSameUCPlanPtType(String pttype,String key_id)
    {   bresult = false;
        try
        {
            theConnectionInf.open();
            bresult = theHosDB.theUCPlanGroupPttypeDB.checkSameUCPlanPtType(pttype,key_id);
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
     * ใช้ในการ ค้นหา ข้อมูลของ UCPlanGroupPttype ตามข้อมูลที่จะค้นหา
     * ทั้งรหัส และคำอธิบาย
     * @param search เป็น String ของข้อความที่จะค้นหา
     * @return เป็น Vector ของ Object UCPlanGroupPttype
     *  ถ้าไม่มีค่าจะ return เป็น null
     */
    public Vector selectUCPlanGroupMapPtTypeByCodeOrDescription(String search)
    {   vcData = null;
        try
        {
            theConnectionInf.open();
            
            vcData = (Vector)theHosDB.theUCPlanGroupPttypeDB.selectBySearch(search);
            
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
    
    public int saveUCPlanGroupMapPtType(UCPlanGroupPttype mappttype)
    {   iresult = 0;
        try
        {
            theConnectionInf.open();
            if(mappttype.getObjectId() == null)
            {
                iresult = theHosDB.theUCPlanGroupPttypeDB.insertData(mappttype);
            }
            else
            {
                iresult = theHosDB.theUCPlanGroupPttypeDB.updateData(mappttype);
            }
            theUS.setStatus("การบันทึกรหัส PTType ของสิทธิ UC เสร็จสิ้น",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus("การบันทึกรหัส PTType ของสิทธิ UC ผิดพลาด",UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return iresult;
    }
    
    public int deleteUCPlanGroupMapPtType(UCPlanGroupPttype mappttype)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(mappttype.getObjectId() != null)
            {
                iresult = theHosDB.theUCPlanGroupPttypeDB.deleteByKeyID(mappttype.getObjectId());
            }
            theUS.setStatus("การลบรหัส PTType ของสิทธิ UC เสร็จสิ้น",UpdateStatus.COMPLETE);
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
