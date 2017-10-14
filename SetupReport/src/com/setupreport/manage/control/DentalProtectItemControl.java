/*
 * DentalProtectItem.java
 *
 * Created on 7 �չҤ� 2549, 15:19 �.
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
public class DentalProtectItemControl
{
    
    /** Creates a new instance of DentalProtectItem */
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private int iresult;
    Vector vData;
    DentalProtectItem theDentalProtectItem;
    UpdateStatus theUS;
    
    public DentalProtectItemControl(HosDB hdb, UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    /**
     * ����Ѻ��Ǩ�ͺ keyword 㹡�ä�����¡��
     * @Param kyword �繵�������͵�Ǩ�ͺ��Ҩе�ͧ����Ẻ�� Keyword ������� 
     * @Author Ojika
     * @Date 07/03/2549
     **/
    public Vector searchDentalProtectItem(String keyword)
    {
        if(keyword.equalsIgnoreCase(""))
        {
            // ���ҷ�����
            selectDentalProtectItemAll();
        }
        else
        {
            // ���Ҩҡ�Ӥ�
            selectDentalProtectItemByKeyword(keyword);
        }        
        
        return vData;
    }
    
    /**
     * ����Ѻ������¡�õ�Ǩ�ѡ�Ңͧ�ѹ�������ͧ�ѹ �¤��ҷ�����
     * @Return Vector �ͧ DentalProtectItem 
     * @Author Ojika
     * @Date 07/03/2549
     **/
    public Vector selectDentalProtectItemAll()
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theDentalProtectItemDB.selectAll();             
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
     * ����Ѻ������¡�õ�Ǩ�ѡ�Ңͧ�ѹ�������ͧ�ѹ
     * @Param keyword �繵���÷���纤�ҤӤ鹷�������͡
     * @Return Vector �ͧ DentalProtectItem 
     * @Author Ojika
     * @Date 07/03/2549
     **/
    public Vector selectDentalProtectItemByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theDentalProtectItemDB.selectByKeyword(keyword);            
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
     * ����Ѻź��¡�õ�Ǩ�ѡ�Ңͧ�ѹ�������ͧ�ѹ
     * @Param obj �� Object �ͧ��¡�÷ѹ�������ͧ�ѹ
     * @Return int ���ͺ͡ʶҹС��ź��¡�� 
     * @Author Ojika
     * @Date 07/03/2549
     **/
    public int deleteDentalProtectItemByKeyId(DentalProtectItem obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theDentalProtectItemDB.deleteByKeyID(obj.getObjectId());  
            theUS.setStatus("���ź��¡�õ�Ǩ�ѡ�Ңͧ�ѹ�������ͧ�ѹ�������", UpdateStatus.COMPLETE);
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
     * ����Ѻ������¡�õ�Ǩ�ѡ��
     * @Param keyword �繵���÷���纤�ҤӤ鹷�������͡
     * @Return Vector �ͧ Item 
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public Vector selectItemByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theItemDB.selectBySearch(keyword);            
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
     * ����Ѻ�ѹ�֡��¡�õ�Ǩ�ѡ�Ңͧ�ѹ�������ͧ�ѹ
     * @Param obj �� Object �ͧ��¡�÷ѹ�������ͧ�ѹ
     * @Return int ���ͺ͡ʶҹС��ź��¡�� 
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public int saveDentalProtectItem(DentalProtectItem obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theDentalProtectItemDB.insertData(obj);      
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
