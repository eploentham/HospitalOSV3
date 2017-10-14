/*
 * NCDItemGroupControl.java
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
import com.reportnan.object.NCDItemGroupMapItem;
import java.util.Vector;
/**
 *
 * @author ojika
 */
public class NCDItemGroupControl
{
    ConnectionInf theConnectionInf;
    ManageDB theManageDB;
    int iresult =0;
    Vector vData;
    
    public NCDItemGroupControl(ManageDB mdb)
    {
        theManageDB = mdb;
        theConnectionInf = this.theManageDB.theConnectionInf;        
    }
    
    /**
     * ����Ѻ���ҡ������¡�� NCD 
     * @Param keyword �� ��ҷ�������͡���ͤ���
     * @Return Vector �ͧ �������¡�� NCD
     * @Author ojika
     * @Date 20/06/2549
     **/
    public Vector searchNCDItemGroupByKeyword(String keyword)
    {
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theManageDB.theNCDItemGroupDB.selectByKeyword(keyword);   
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
     * ����Ѻ������¡�õ�Ǩ�ѡ�Ңͧ �������¡�� NCD 
     * @Param NCDItemGroupId �� ���ʡ�����ͧ��¡�� NCD
     * @Return Vector �ͧ ��¡�õ�Ǩ�ѡ�Ңͧ�������¡�� NCD
     * @Author ojika
     * @Date 20/06/2549
     **/
    public Vector searchNCDItemGroupMapItemByNCDItemGroupId(String NCDItemGroupId)
    {
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theManageDB.theNCDItemGroupMapItemDB.selectByNCDItemGroupID(NCDItemGroupId);             
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
     * ����Ѻź��¡�õ�Ǩ�ѡ�Ңͧ �������¡�� NCD 
     * @Param NCDItemGroupMapItemId �� ���ʡ�����ͧ��¡�� NCD
     * @Return int ��ʶҹС��ź��¡��
     * @Author ojika
     * @Date 20/06/2549
     **/
    public int deleteNCDItemGroupMapItem(String NCDItemGroupMapItemId)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theManageDB.theNCDItemGroupMapItemDB.deleteByKeyID(NCDItemGroupMapItemId);             
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
     * ����Ѻ�ѹ�֡��¡�õ�Ǩ�ѡ�Ңͧ �������¡�� NCD 
     * @Param Object �ͧ ��¡�õ�Ǩ�ѡ�Ңͧ �������¡�� NCD 
     * @Return int ��ʶҹС�úѹ�֡��¡��
     * @Author ojika
     * @Date 20/06/2549
     **/
    public int saveNCDItemGroupMapItem(NCDItemGroupMapItem theNCDItem)
    {
        iresult = 0;
        if(theNCDItem != null)
        {
            try
            {
                theConnectionInf.open();
                // ��觺ѹ�֡��¡�õ�Ǩ�ѡ�Ңͧ �������¡�� NCD
                iresult = this.theManageDB.theNCDItemGroupMapItemDB.insertData(theNCDItem);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                theConnectionInf.close();
            }                                                
        }
        
        return iresult;
       
    }
    
}
