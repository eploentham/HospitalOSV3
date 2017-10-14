/*
 * PlanGroupControl.java
 *
 * Created on 27 ตุลาคม 2548, 19:45 น.
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
import com.setupreport.utility.ComboFix;
import java.util.Vector;
/**
 *
 * @author tong(Padungrat)
 */
public class PlanGroupControl {
    
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData,vcDataTemp;
    private int iresult;
    private ComboFix theComboFix;
    boolean bresult;
    UpdateStatus theUS;
    public PlanGroupControl(HosDB hdb,UpdateStatus us)
    {
        theHosDB = hdb;
        theUS = us;
        theConnectionInf = theHosDB.theConnectionInf;
    }
 /**
     *  ใช้ในการตรวจสอบ หาว่า Description ซ้ำกันหรือไม่ ถ้ามีซ้ำกัน จะ ให้ค่าเป็น false ถ้าไม่ซ้ำจะให้ค่าเป็น true
     *  @return เป็น boolean ถ้าเป็น true จะไม่ซ้ำ ถ้าเป็น false จะซ้ำ
     */
    public boolean checkSamePlanGroupDescription(String plangroupdescription)
    {
        bresult = false;
        
        try
        {
            theConnectionInf.open();
            bresult = theHosDB.thePlanGroupDB.checkSamePlanGroupDescription(plangroupdescription);
            
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
    public boolean checkSamePlanGroupCode(String plangroupcode)
    {
        bresult = false;
        
        try
        {
            theConnectionInf.open();
            bresult = theHosDB.thePlanGroupDB.checkSamePlanGroupCode(plangroupcode);
            
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
    
    
    /**ใช้ในการ Query ข้อมูลที่ต้องการ มาเก็บไว้ใน ComboBox
     *  @return เป็น Vector ของ object ComboFix
     */
    public Vector selectPlanGroupShowComboBox()
    {
        int size = 0;
        theComboFix = new ComboFix();
        vcData = null;
        vcDataTemp = selectPlanGroupByCodeOrDescription("");
        if(vcDataTemp != null)
        {   PlanGroup ag = null;
            size = vcDataTemp.size();
            vcData = new Vector();
            for(int i=0 ; i < size ; i++)
            {
                theComboFix = new ComboFix();
                ag = (PlanGroup)vcDataTemp.get(i);
                theComboFix.code = ag.getObjectId();
                theComboFix.name = ag.description;
                vcData.add(theComboFix);
                ag = null;
            }
            ag = null;
        }
        vcDataTemp = null;
        return vcData;
    }
    
    /**
     * ใช้ในการ ค้นหา ข้อมูลของ PlanGroup ตามข้อมูลที่จะค้นหา
     * ทั้งรหัส และคำอธิบาย
     * @param search เป็น String ของข้อความที่จะค้นหา
     * @return เป็น Vector ของ Object PlanGroup
     *  ถ้าไม่มีค่าจะ return เป็น null
     */
    public Vector selectPlanGroupByCodeOrDescription(String search)
    {   vcData = null;
        try
        {
            theConnectionInf.open();
            vcData = (Vector)theHosDB.thePlanGroupDB.selectBySearch(search, "1");
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
    
    public int savePlanGroup(PlanGroup plangroup)
    {   iresult = 0;
        try
        {
            theConnectionInf.open();
            if(plangroup.getObjectId() == null)
            {
                iresult = theHosDB.thePlanGroupDB.insertData(plangroup);
            }
            else
            {
                iresult = theHosDB.thePlanGroupDB.updateData(plangroup);
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
    
    public int deletePlanGroup(PlanGroup plangroup)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(plangroup.getObjectId() != null)
            {
                iresult = theHosDB.thePlanGroupDB.deleteByKeyID(plangroup.getObjectId());
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
