/*
 * EpiMeaslesItemControl.java
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
public class EpiMeaslesItemControl
{
    
    /** Creates a new instance of EpiMeaslesItemControl */
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private int iresult;
    Vector vData;
    EpiMeaslesItem theEpiMeaslesItem;
    UpdateStatus theUS;
    
    public EpiMeaslesItemControl(HosDB hdb, UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    /**
     * ����Ѻ��Ǩ�ͺ keyword 㹡�ä�����¡��
     * @Param kyword �繵�������͵�Ǩ�ͺ��Ҩе�ͧ����Ẻ�� Keyword ������� 
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public Vector searchEpiMeaslesItem(String keyword)
    {
        if(keyword.equalsIgnoreCase(""))
        {
            // ���ҷ�����
            selectEpiMeaslesItemAll();
        }
        else
        {
            // ���Ҩҡ�Ӥ�
            selectEpiMeaslesItemByKeyword(keyword);
        }        
        
        return vData;
    }
    
    /**
     * ����Ѻ������¡���Ѥ�չ�ͧ�Ѵ�����ѹ �¤��ҷ�����
     * @Return Vector �ͧ EpiMeaslesItem 
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public Vector selectEpiMeaslesItemAll()
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theEpiMeaslesItemDB.selectAll();              
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
     * ����Ѻ������¡���Ѥ�չ�ͧ�Ѵ�����ѹ
     * @Param keyword �繵���÷���纤�ҤӤ鹷�������͡
     * @Return Vector �ͧ EpiMeaslesItem 
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public Vector selectEpiMeaslesItemByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theEpiMeaslesItemDB.selectByKeyword(keyword);            
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
     * ����Ѻź��¡���Ѥ�չ�ͧ�Ѵ�����ѹ
     * @Param obj �� Object �ͧ��¡���Ѥ�չ
     * @Return int ���ͺ͡ʶҹС��ź��¡�� 
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public int deleteEpiMeaslesItemByKeyId(EpiMeaslesItem obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theEpiMeaslesItemDB.deleteByKeyID(obj.getObjectId()); 
            theUS.setStatus("���ź��¡���Ѥ�չ�Ѵ�����ѹ (MMR)�������", UpdateStatus.COMPLETE);
            
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
     * ����Ѻ�ѹ�֡��¡���Ѥ�չ�ͧ�Ѵ�����ѹ
     * @Param obj �� Object �ͧ��¡���Ѥ�չ
     * @Return int ���ͺ͡ʶҹС��ź��¡�� 
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public int saveEpiMeaslesItem(EpiMeaslesItem obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theEpiMeaslesItemDB.insertData(obj);
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
     * @Date 08/03/2549
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
