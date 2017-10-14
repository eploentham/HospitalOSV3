/*
 * AricAntibioticControl.java
 *
 * Created on 28 ตุลาคม 2548, 11:39 น.
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
public class AricAntibioticControl {
    
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData,vcDataTemp;
    private int iresult;
    UpdateStatus theUS;
    
    public AricAntibioticControl(HosDB hdb,UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }   
    
    public Vector selectItemDrugBySearch(String search)
    {
        vcData = null;
        try
        {
            theConnectionInf.open();
            vcData = (Vector)theHosDB.theItemDB.selectBySearchForDrug(search);
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
    
    /**
     * ใช้ในการ ค้นหา ข้อมูลของ UCPlanGroupPttype ตามข้อมูลที่จะค้นหา
     * ทั้งรหัส และคำอธิบาย
     * @param search เป็น String ของข้อความที่จะค้นหา
     * @return เป็น Vector ของ Object UCPlanGroupPttype
     *  ถ้าไม่มีค่าจะ return เป็น null
     */
    public Vector selectAricAntibioticByCodeOrDescription(String search)
    {   vcData = null;
        try
        {
            theConnectionInf.open();
            
            vcData = (Vector)theHosDB.theAricAntibioticItemDB.selectBySearch(search);
            System.out.println("+++++++++++++++++++ "+vcData);
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
    
    public int saveAricAntibioticItem(AricAntibioticItem antibiotic)
    {   iresult = 0;
        try
        {
            theConnectionInf.open();            
            iresult = theHosDB.theAricAntibioticItemDB.insertData(antibiotic);
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
    
    public int deleteAricAntibioticItem(AricAntibioticItem antibiotic)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(antibiotic.getObjectId() != null)
            {
                iresult = theHosDB.theAricAntibioticItemDB.deleteByKeyID(antibiotic.getObjectId());
            }
            theUS.setStatus("การลบรายการยาปฏิชีวนะตาเสร็จสิ้น",UpdateStatus.COMPLETE);
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
