/*
 * AricGroupControl.java
 *
 * Created on 22 ���Ҥ� 2548, 15:30 �.
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
public class AricGroupControl {
    
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData,vcDataTemp;
    private int iresult;
    private ComboFix theComboFix;
    boolean bresult;
    UpdateStatus theUS;
    public AricGroupControl(HosDB hdb,UpdateStatus us)
    {
        theHosDB = hdb;
        theUS = us;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    /**
     *  ��㹡�õ�Ǩ�ͺ ����� Description ��ӡѹ������� ����ի�ӡѹ �� ������� false �������Ө�������� true
     *  @return �� boolean ����� true ������� ����� false �Ы��
     */
    public boolean checkSameAricGroupDescription(String aricgroupdescription,String key_id)
    {
        bresult = false;
        
        try
        {
            theConnectionInf.open();
            bresult = theHosDB.theAricGroupDB.checkSameAricGroupDescription(aricgroupdescription, key_id);
            
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
    public boolean checkSameAricGroupCode(String aricgroupcode,String key_id)
    {
        bresult = false;
        
        try
        {
            theConnectionInf.open();
            bresult = theHosDB.theAricGroupDB.checkSameAricGroupCode(aricgroupcode, key_id);
            
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
    public Vector selectAricGroupShowComboBox()
    {
        int size = 0;
        theComboFix = new ComboFix();
        vcData = null;
        vcDataTemp = selectAricGroupByCodeOrDescription("");
        if(vcDataTemp != null)
        {   AricGroup ag = null;
            size = vcDataTemp.size();
            vcData = new Vector();
            for(int i=0 ; i < size ; i++)
            {
                theComboFix = new ComboFix();
                ag = (AricGroup)vcDataTemp.get(i);
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
     * ��㹡�� ���� �����Ţͧ AricGroup ��������ŷ��Ф���
     * ������� ��Ф�͸Ժ��
     * @param search �� String �ͧ��ͤ������Ф���
     * @return �� Vector �ͧ Object AricGroup
     *  �������դ�Ҩ� return �� null
     */
    public Vector selectAricGroupByCodeOrDescription(String search)
    {   vcData = null;
        try
        {
            theConnectionInf.open();
            vcData = (Vector)theHosDB.theAricGroupDB.selectBySearch(search);
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
    
    public int saveAricGroup(AricGroup aricgroup)
    {   iresult = 0;
        try
        {
            theConnectionInf.open();
            if(aricgroup.getObjectId() == null)
            {
                iresult = theHosDB.theAricGroupDB.insertData(aricgroup);
            }
            else
            {
                iresult = theHosDB.theAricGroupDB.updateData(aricgroup);
            }
            theUS.setStatus("��úѹ�֡������ͧ ARIC �������",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus("��úѹ�֡������ͧ ARIC �Դ��Ҵ",UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return iresult;
    }
    
    public int deleteAricGroup(AricGroup aricgroup)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(aricgroup.getObjectId() != null)
            {
                iresult = theHosDB.theAricGroupDB.deleteByKeyID(aricgroup.getObjectId());
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
