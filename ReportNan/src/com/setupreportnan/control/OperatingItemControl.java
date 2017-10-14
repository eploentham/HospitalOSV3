/*
 * OperatingItemControl.java
 *
 * Created on 20 �Զع�¹ 2549, 18:05 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreportnan.control;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.setupreportnan.control.ManageDB;
import com.reportnan.object.OperatingItem;
import java.util.Vector;
/**
 *
 * @author ojika
 */
public class OperatingItemControl
{
    ConnectionInf theConnectionInf;
    ManageDB theManageDB;
    int iresult =0;
    Vector vData;
    
    public OperatingItemControl(ManageDB mdb)
    {
        theManageDB = mdb;
        theConnectionInf = this.theManageDB.theConnectionInf;        
    }
    
    /**
     * ����Ѻ���� ��¡�õ�Ǩ�ѡ�ҷ�����ѵ����
     * @Param keyword �� ��ҷ�������͡���ͤ���
     * @Return Vector �ͧ ��¡�õ�Ǩ�ѡ�ҷ�����ѵ����
     * @Author ojika
     * @Date 21/06/2549
     **/
    public Vector searchOperatingItemByKeyword(String keyword)
    {
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theManageDB.theOperatingItemDB.selectByKeyword(keyword); 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        
        return vData;
    }   
    
    /**
     * ����Ѻź��¡�õ�Ǩ�ѡ�� ������ѵ����
     * @Param OperatingItemId �� ���ʢͧ��¡�õ�Ǩ�ѡ�� ������ѵ����
     * @Return int ��ʶҹС��ź��¡��
     * @Author ojika
     * @Date 21/06/2549
     **/
    public int deleteOperatingItem(String OperatingItemId)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theManageDB.theOperatingItemDB.deleteByKeyID(OperatingItemId);             
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
     * ����Ѻ�ѹ�֡��¡�õ�Ǩ�ѡ�Ңͧ ������ѵ����
     * @Param theOperatingItem �� Object ���ʢͧ��¡�õ�Ǩ�ѡ�� ������ѵ����
     * @Return int ��ʶҹС�úѹ�֡��¡��
     * @Author ojika
     * @Date 21/06/2549
     **/
    public int saveOperatingItem(OperatingItem theOperatingItem)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(theOperatingItem != null)
            {
                if(theOperatingItem.getObjectId() != null)
                {
                    // �����¡��
                    iresult = theManageDB.theOperatingItemDB.updateData(theOperatingItem);             
                }
                else
                {
                    //�ѹ�֡����
                    iresult = theManageDB.theOperatingItemDB.insertData(theOperatingItem);    
                }
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
