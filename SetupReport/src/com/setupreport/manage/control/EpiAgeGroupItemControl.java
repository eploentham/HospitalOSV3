/*
 * EpiAgeGroupItemControl.java
 *
 * Created on 29 �չҤ� 2549, 15:20 �.
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
public class EpiAgeGroupItemControl
{
    
    /** Creates a new instance of EpiAgeGroupItemControl */
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private int iresult;
    Vector vData;
    EpiAgeGroupItem theEpiAgeGroupItem;
    EpiAgeGroup theEpiAgeGroup;
    UpdateStatus theUS;
    
    public EpiAgeGroupItemControl(HosDB hdb, UpdateStatus us)
    {
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
        theUS = us;
    }
    
    /**
     * ����Ѻ���Ҫ�ǧ���ء���Ѻ�Ѥ�չ�ҡ�Ӥ�
     * @Param keyword �繵���÷���纤�ҤӤ鹷�������͡
     * @Return Vector �ͧ EpiAgeGroup
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public Vector selectEpiAgeGroupByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theEpiAgeGroupDB.selectByKeyword(keyword);
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
     * ����Ѻ������¡���Ѥ�չ �ͧ��ǧ���ء���Ѻ�Ѥ�չ�ҡ�Ӥ�
     * @Param EpiAgeGroupId ��  ���ʢͧ��ǧ���ء���Ѻ�Ѥ�չ
     * @Return Vector �ͧ EpiAgeGroupItem
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public Vector selectEpiAgeGroupItemByEpiAgeGroupId(String EpiAgeGroupId)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theEpiAgeGroupItemDB.selectByEpiAgeGroupId(EpiAgeGroupId); 
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
     * ����Ѻź��¡���Ѥ�չ�ͧ��ǧ���ء���Ѻ�Ѥ�չ
     * @Param obj �� Object �ͧ��¡���Ѥ�չ�ͧ��ǧ���ء���Ѻ�Ѥ�չ
     * @Return int ���ͺ͡ʶҹС��ź��¡�� 
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public int deleteEpiAgeGroupItemByKeyId(EpiAgeGroupItem obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theEpiAgeGroupItemDB.deleteByKeyID(obj.getObjectId());    
            theUS.setStatus("���ź��ǧ���ء���Ѻ�Ѥ�չ�������", UpdateStatus.COMPLETE);   
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
     * ����Ѻź��¡���Ѥ�չ�ͧ��ǧ���ء���Ѻ�Ѥ�չ
     * @Param obj �� Object �ͧ��¡���Ѥ�չ�ͧ��ǧ���ء���Ѻ�Ѥ�չ
     * @Return int ���ͺ͡ʶҹС��ź��¡�� 
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public int deleteEpiAgeGroupItemByEpiAgeGroupID(String EpiAgeGroupID)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theEpiAgeGroupItemDB.deleteByEpiAgeGroupID(EpiAgeGroupID);    
            theUS.setStatus("���ź��ǧ���ء���Ѻ�Ѥ�չ�������", UpdateStatus.COMPLETE);   
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
     * ����Ѻ�ѹ�֡��¡���Ѥ�չ�ͧ��ǧ���ء���Ѻ�Ѥ�չ
     * @Param obj �� Object �ͧ��¡���Ѥ�չ�ͧ��ǧ���ء���Ѻ�Ѥ�չ
     * @Return int ���ͺ͡ʶҹС�úѹ�֡��¡�� 
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public int saveEpiAgeGroupItem(EpiAgeGroupItem obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theEpiAgeGroupItemDB.insertData(obj);         
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
     * ����Ѻ��Ǩ�ͺ�����ū����Ҩе�ͧ insert �����������
     * @Author Ojika
     * @Date 29/03/2549     
     **/
    public int checkSaveEpiAgeGroupItem(Vector vEpiAgeGroupItem)
    {
        iresult = 0;
        
        if(vEpiAgeGroupItem != null && vEpiAgeGroupItem.size() > 0)
        {
            for(int i=0,size=vEpiAgeGroupItem.size();i<size;i++)
            {
                this.theEpiAgeGroupItem = (EpiAgeGroupItem)vEpiAgeGroupItem.get(i);
                
                if(this.theEpiAgeGroupItem.getObjectId() != null && !this.theEpiAgeGroupItem.getObjectId().equalsIgnoreCase(""))
                {
                    // �觢������ insert 㹰ҹ������
                    iresult = saveEpiAgeGroupItem(this.theEpiAgeGroupItem);
                }
            }
        }
        
        return iresult;
    }
    
    /**
     * ����Ѻ���Ҫ�Դ�ͧ��ǧ����
     * @Param EpiAgeGroupTypeId ��  ���ʢͧ��Դ��ǧ���ء���Ѻ�Ѥ�չ
     * @Return Object �ͧ EpiAgeGroupType
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public EpiAgeGroupType selectEpiAgeGroupTypeById(String EpiAgeGroupTypeId)
    {      
        EpiAgeGroupType theEpiAgeGroupType = new EpiAgeGroupType();
        try
        {
            theConnectionInf.open();
            theEpiAgeGroupType = theHosDB.theEpiAgeGroupTypeDB.selectByPk(EpiAgeGroupTypeId); 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        } 
        
        return theEpiAgeGroupType;
    }
    
    /**
     * ����Ѻź��ǧ���ء���Ѻ�Ѥ�չ
     * @Param obj �� Object �ͧ��ǧ���ء���Ѻ�Ѥ�չ
     * @Return int ���ͺ͡ʶҹС��ź��¡�� 
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public int deleteEpiAgeGroupByKeyId(EpiAgeGroup obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theEpiAgeGroupDB.deleteByKeyID(obj.getObjectId());       
            theUS.setStatus("���ź��ǧ���ء���Ѻ�Ѥ�չ�������", UpdateStatus.COMPLETE);   
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
     * ��Ǩ�ͺ�����ū�ӡѹ�Ѻ㹰ҹ������
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public boolean checkSameNumber(String number)
    {        
        Vector v = new Vector();
        boolean checkSame = true;  
        
        v = selectEpiAgeGroupByNumber(number);
        
        if(v != null && v.size() > 0)
        {
            checkSame = true;
        }
        else
        {
            checkSame = false;
        }
        
        return checkSame;
    }
    
    /**
     * ����Ѻ���Ҫ�ǧ���ء���Ѻ�Ѥ�չ�ҡ number
     * @Param number
     * @Return Vector �ͧ EpiAgeGroup
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public Vector selectEpiAgeGroupByNumber(String number)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theEpiAgeGroupDB.selectByNumber(number); 
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
     * ����Ѻ�ѹ�֡���� ��� ��ǧ���ء���Ѻ�Ѥ�չ
     * @Param obj �� Object �ͧ��ǧ���ء���Ѻ�Ѥ�չ
     * @Return int ���ͺ͡ʶҹС��ź��¡�� 
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public int saveEpiAgeGroup(EpiAgeGroup obj,int saveNewEpiAgeGroup)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(saveNewEpiAgeGroup == 0)
            {
                iresult = theHosDB.theEpiAgeGroupDB.updateData(obj);       
            }
            else
            {
                iresult = theHosDB.theEpiAgeGroupDB.insertData(obj);       
            }      
            theUS.setStatus("��úѹ�֡��ǧ���ء���Ѻ�Ѥ�չ�������",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus("��úѹ�֡��ǧ���ء���Ѻ�Ѥ�չ�Դ��Ҵ",UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        } 
        
        return iresult;
    }
        
}
