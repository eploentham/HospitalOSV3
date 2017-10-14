/*
 * NutritionControl.java
 *
 * Created on 16 �չҤ� 2549, 14:22 �.
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
public class NutritionControl
{
    
    /** Creates a new instance of NutritionControl */
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private int iresult;
    Vector vData;
    NutritionMap theNutritionMap;
    UpdateStatus theUS;
    
    public NutritionControl(HosDB hdb, UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    /**
     * ����Ѻ������������ҡ��
     * @Param keyword �繵���÷���纤�ҤӤ鹷�������͡
     * @Return Vector �ͧ NutritionLevel
     * @Author Ojika
     * @Date 16/03/2549
     **/
    public Vector selectNutritionLevelByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theNutritionLevelDB.selectByKeyword(keyword);
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
     * ����Ѻ�������� ��Դ����ҡ��
     * @Param keyword �繵���÷���纤�ҤӤ鹷�������͡
     * @Return Vector �ͧ NutritionType
     * @Author Ojika
     * @Date 16/03/2549
     **/
    public Vector selectNutritionTypeByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theNutritionTypeDB.selectByKeyword(keyword);
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
     * ����Ѻ������� Map ��������ҡ�� �ҡ�Ӥ�
     * @Param nutritionTypeId ��  ���ʢͧ�������Դ��������ҡ��
     * @Return Vector �ͧ NutritionMap
     * @Author Ojika
     * @Date 16/03/2549
     **/
    public Vector selectNutritionMapByNutritionTypeId(String nutritionTypeId)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theNutritionMapDB.selectByNutritionTypeId(nutritionTypeId);
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
     * ����Ѻź��¡�� Map ��������ҡ��
     * @Param obj �� Object �ͧ��¡�� Map ��������ҡ��
     * @Return int ���ͺ͡ʶҹС��ź��¡�� 
     * @Author Ojika
     * @Date 16/03/2549
     **/
    public int deleteNutritionMapByKeyId(NutritionMap obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theNutritionMapDB.deleteByKeyID(obj.getObjectId()); 
            theUS.setStatus("���ź��������ҡ���������", UpdateStatus.COMPLETE);      
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
     * ����Ѻ�ѹ�֡��¡�� Map ��������ҡ��
     * @Param obj �� Object �ͧ��¡�� Map ��������ҡ��
     * @Return int ���ͺ͡ʶҹС�úѹ�֡��¡�� 
     * @Author Ojika
     * @Date 16/03/2549
     **/
    public int saveNutritionMap(NutritionMap obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theNutritionMapDB.insertData(obj);       
            theUS.setStatus("��úѹ�֡��������ҡ���������", UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus("��úѹ�֡��������ҡ�üԴ��Ҵ", UpdateStatus.ERROR);
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
     * @Date 16/03/2549     
     **/
    public int checkSaveNutritionMap(Vector vNutritionMap)
    {
        iresult = 0;
        
        if(vNutritionMap != null && vNutritionMap.size() > 0)
        {
            for(int i=0,size=vNutritionMap.size();i<size;i++)
            {
                this.theNutritionMap = (NutritionMap)vNutritionMap.get(i);
                
                if(this.theNutritionMap.getObjectId() != null && !this.theNutritionMap.getObjectId().equalsIgnoreCase(""))
                {
                    // �觢������ insert 㹰ҹ������
                    iresult = saveNutritionMap(this.theNutritionMap);
                }
            }
        }
        return iresult;
    }
    
}
