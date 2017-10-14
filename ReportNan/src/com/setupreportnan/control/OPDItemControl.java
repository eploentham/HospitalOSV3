/*
 * OPDItemControl.java
 *
 * Created on 20 �Զع�¹ 2549, 18:06 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreportnan.control;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.setupreportnan.control.ManageDB;
import com.reportnan.object.OPDItem;
import java.util.Vector;
/**
 *
 * @author ojika
 */
public class OPDItemControl
{
    ConnectionInf theConnectionInf;
    ManageDB theManageDB;
    int iresult =0;
    Vector vData;
    
    public OPDItemControl(ManageDB mdb)
    {
        theManageDB = mdb;
        theConnectionInf = this.theManageDB.theConnectionInf;        
    }
    
    /**
     * ����Ѻ���� ��¡�õ�Ǩ�ѡ�� OPD
     * @Param keyword �� ��ҷ�������͡���ͤ���
     * @Return Vector �ͧ ��¡�õ�Ǩ�ѡ�� OPD
     * @Author ojika
     * @Date 21/06/2549
     **/
    public Vector searchOPDItemByKeyword(String keyword)
    {
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theManageDB.theOPDItemDB.selectByKeyword(keyword); 
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
     * ����Ѻź��¡�õ�Ǩ�ѡ�Ңͧ OPD
     * @Param OPDItemId �� ���ʢͧ��¡�õ�Ǩ�ѡ�� OPD
     * @Return int ��ʶҹС��ź��¡��
     * @Author ojika
     * @Date 21/06/2549
     **/
    public int deleteOPDItem(String OPDItemId)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theManageDB.theOPDItemDB.deleteByKeyID(OPDItemId);             
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
     * ����Ѻ�ѹ�֡��¡�õ�Ǩ�ѡ�Ңͧ OPD
     * @Param theOPDItem �� Object ���ʢͧ��¡�õ�Ǩ�ѡ�� OPD
     * @Return int ��ʶҹС�úѹ�֡��¡��
     * @Author ojika
     * @Date 21/06/2549
     **/
    public int saveOPDItem(OPDItem theOPDItem)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(theOPDItem != null)
            {
                if(theOPDItem.getObjectId() != null)
                {
                    // �����¡��
                    iresult = theManageDB.theOPDItemDB.updateData(theOPDItem);             
                }
                else
                {
                    //�ѹ�֡����
                    iresult = theManageDB.theOPDItemDB.insertData(theOPDItem);    
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
