/*
 * EpiGroupItemControl.java
 *
 * Created on 7 �չҤ� 2549, 15:20 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage.control;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.setupreport.manage.control.HosDB;
import com.setupreport.object.*;
import java.util.Vector;
import com.hosv3.utility.connection.UpdateStatus;
/**
 *
 * @author Ojika
 */
public class EpiGroupItemControl
{
    
    /** Creates a new instance of EpiGroupItemControl */
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private int iresult;
    Vector vData;
    EpiGroupItem theEpiGroupItem;
    EpiGroup theEpiGroup;
    UpdateStatus theUS;
    
    public EpiGroupItemControl(HosDB hdb, UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    /**
     * ����Ѻ���ҡ�����Ѥ�չ�ҡ�Ӥ�
     * @Param keyword �繵���÷���纤�ҤӤ鹷�������͡
     * @Return Vector �ͧ EpiGroup
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public Vector selectEpiGroupByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theEpiGroupDB.selectByKeyword(keyword);
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
     * ����Ѻ������¡���Ѥ�չ �ͧ������Ѥ�չ�ҡ�Ӥ�
     * @Param epiGroupId ��  ���ʢͧ������Ѥ�չ
     * @Return Vector �ͧ EpiGroupItem
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public Vector selectEpiGroupItemByEpiGroupId(String epiGroupId)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theEpiGroupItemDB.selectByEpiGroupId(epiGroupId);
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
     * ����Ѻź��¡���Ѥ�չ�ͧ������Ѥ�չ��ô�����
     * @Param obj �� Object �ͧ��¡���Ѥ�չ�ͧ������Ѥ�չ��ô�����
     * @Return int ���ͺ͡ʶҹС��ź��¡�� 
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public int deleteEpiGroupItemByKeyId(EpiGroupItem obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theEpiGroupItemDB.deleteByKeyID(obj.getObjectId());    
            theUS.setStatus("���ź��¡���Ѥ�չ����������������", UpdateStatus.COMPLETE);   
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
     * ����Ѻ�ѹ�֡��¡���Ѥ�չ�ͧ������Ѥ�չ��ô�����
     * @Param obj �� Object �ͧ��¡���Ѥ�չ�ͧ������Ѥ�չ��ô�����
     * @Return int ���ͺ͡ʶҹС�úѹ�֡��¡�� 
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public int saveEpiGroupItem(EpiGroupItem obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theEpiGroupItemDB.insertData(obj);      
            theUS.setStatus("��úѹ�֡��¡���Ѥ�չ����������������", UpdateStatus.COMPLETE); 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus("��úѹ�֡��¡���Ѥ�չ��������硼Դ��Ҵ", UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        } 
        
        return iresult;
    }
    
    /**
     * ����Ѻ��Ǩ�ͺ�����ū����Ҩе�ͧ insert �����������
     * @Author Ojika
     * @Date 09/03/2549     
     **/
    public int checkSaveEpiGroupItem(Vector vEpiGroupItem)
    {
        iresult = 0;
        
        if(vEpiGroupItem != null && vEpiGroupItem.size() > 0)
        {
            for(int i=0,size=vEpiGroupItem.size();i<size;i++)
            {
                this.theEpiGroupItem = (EpiGroupItem)vEpiGroupItem.get(i);
                
                if(this.theEpiGroupItem.getObjectId() != null && !this.theEpiGroupItem.getObjectId().equalsIgnoreCase(""))
                {
                    // �觢������ insert 㹰ҹ������
                    iresult = saveEpiGroupItem(this.theEpiGroupItem);
                }
            }
        }
        
        return iresult;
    }
        
}
