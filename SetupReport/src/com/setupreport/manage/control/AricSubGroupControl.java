/*
 * AricSubGroupControl.java
 *
 * Created on 24 ���Ҥ� 2548, 12:07 �.
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
     *  ��㹡�õ�Ǩ�ͺ ����� Description ��ӡѹ������� ����ի�ӡѹ �� ������� false �������Ө�������� true
     *  @return �� boolean ����� true ������� ����� false �Ы��
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
     *  ��㹡�õ�Ǩ�ͺ ����� Code ��ӡѹ������� ����ի�ӡѹ �� ������� false �������Ө�������� true
     *  @return �� boolean ����� true ������� ����� false �Ы��
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
     * ��㹡�� ���� �����Ţͧ AricSubGroup ��������ŷ��Ф���
     * ������� ��Ф�͸Ժ��
     * @param search �� String �ͧ��ͤ������Ф���
     * @return �� Vector �ͧ Object AricSricGroup
     *  �������դ�Ҩ� return �� null
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
            theUS.setStatus("��úѹ�֡��������¢ͧ ARIC �������",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus("��úѹ�֡��������¢ͧ ARIC �Դ��Ҵ",UpdateStatus.ERROR);
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
            theUS.setStatus("���ź��������¢ͧ ARIC �������",UpdateStatus.COMPLETE);
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
