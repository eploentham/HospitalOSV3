/*
 * PlanGroupControl.java
 *
 * Created on 27 ���Ҥ� 2548, 19:45 �.
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
     *  ��㹡�õ�Ǩ�ͺ ����� Description ��ӡѹ������� ����ի�ӡѹ �� ������� false �������Ө�������� true
     *  @return �� boolean ����� true ������� ����� false �Ы��
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
     *  ��㹡�õ�Ǩ�ͺ ����� Code ��ӡѹ������� ����ի�ӡѹ �� ������� false �������Ө�������� true
     *  @return �� boolean ����� true ������� ����� false �Ы��
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
    
    
    /**��㹡�� Query �����ŷ���ͧ��� �������� ComboBox
     *  @return �� Vector �ͧ object ComboFix
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
     * ��㹡�� ���� �����Ţͧ PlanGroup ��������ŷ��Ф���
     * ������� ��Ф�͸Ժ��
     * @param search �� String �ͧ��ͤ������Ф���
     * @return �� Vector �ͧ Object PlanGroup
     *  �������դ�Ҩ� return �� null
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
