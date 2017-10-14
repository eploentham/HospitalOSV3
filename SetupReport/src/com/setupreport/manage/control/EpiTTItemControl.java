/*
 * EpiTTItemControl.java
 *
 * Created on 28 �չҤ� 2549, 15:20 �.
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
public class EpiTTItemControl
{
    
    /** Creates a new instance of EpiTTItemControl */
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private int iresult;
    Vector vData;
    EpiTTItem theEpiTTItem;
    UpdateStatus theUS;
    
    public EpiTTItemControl(HosDB hdb, UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    /**
     * ����Ѻ��Ǩ�ͺ keyword 㹡�ä�����¡��
     * @Param kyword �繵�������͵�Ǩ�ͺ��Ҩе�ͧ����Ẻ�� Keyword ������� 
     * @Author Ojika
     * @Date 28/03/2549
     **/
    public Vector searchEpiTTItem(String keyword)
    {
        if(keyword.equalsIgnoreCase(""))
        {
            // ���ҷ�����
            selectEpiTTItemAll();
        }
        else
        {
            // ���Ҩҡ�Ӥ�
            selectEpiTTItemByKeyword(keyword);
        }        
        
        return vData;
    }
    
    /**
     * ����Ѻ������¡���Ѥ�չ�ͧ TT �¤��ҷ�����
     * @Return Vector �ͧ EpiTTItem 
     * @Author Ojika
     * @Date 28/03/2549
     **/
    public Vector selectEpiTTItemAll()
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theEpiTTItemDB.selectAll();              
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
     * ����Ѻ������¡���Ѥ�չ�ͧ TT
     * @Param keyword �繵���÷���纤�ҤӤ鹷�������͡
     * @Return Vector �ͧ EpiTTItem 
     * @Author Ojika
     * @Date 28/03/2549
     **/
    public Vector selectEpiTTItemByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theEpiTTItemDB.selectByKeyword(keyword);            
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
     * ����Ѻź��¡���Ѥ�չ�ͧ TT
     * @Param obj �� Object �ͧ��¡���Ѥ�չ
     * @Return int ���ͺ͡ʶҹС��ź��¡�� 
     * @Author Ojika
     * @Date 28/03/2549
     **/
    public int deleteEpiTTItemByKeyId(EpiTTItem obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theEpiTTItemDB.deleteByKeyID(obj.getObjectId());   
            theUS.setStatus("���ź��¡���Ѥ�չ�ͧ TT �������", UpdateStatus.COMPLETE);    
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
     * ����Ѻ�ѹ�֡��¡���Ѥ�չ�ͧ TT
     * @Param obj �� Object �ͧ��¡���Ѥ�չ
     * @Return int ���ͺ͡ʶҹС��ź��¡�� 
     * @Author Ojika
     * @Date 28/03/2549
     **/
    public int saveEpiTTItem(EpiTTItem obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theEpiTTItemDB.insertData(obj);        
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
     * ����Ѻ��������Ѥ�չ 
     * @Param keyword �繵���÷���纤�ҤӤ鹷�������͡
     * @Return Vector �ͧ Epi
     * @Author Ojika
     * @Date 28/03/2549
     **/
    public Vector selectEpiByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theEpiDB.selectByKeyword(keyword);             
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
    
}
