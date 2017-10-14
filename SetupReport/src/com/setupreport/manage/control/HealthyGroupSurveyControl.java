/*
 * HealthyGroupSurveyControl.java
 *
 * Created on 9 �չҤ� 2549, 14:24 �.
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
public class HealthyGroupSurveyControl
{
    
    /** Creates a new instance of HealthyGroupSurveyControl */
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private int iresult;
    Vector vData;
    HealthyGroupSurvey theHealthyGroupSurvey;
    HealthyGroup theHealthyGroup;
    UpdateStatus theUS;
    
    public HealthyGroupSurveyControl(HosDB hdb, UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    /**
     * ����Ѻ���ҡ������õ�Ǩ��ҧ���
     * @Param keyword �繵���÷���纤�ҤӤ鹷�������͡
     * @Return Vector �ͧ HealthyGroup
     * @Author Ojika
     * @Date 09/03/2549
     **/
    public Vector selectHealthyGroupByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theHealthyGroupDB.selectByKeyword(keyword);
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
     * ����Ѻ���ҡ�������¡�õ�Ǩ��ҧ���
     * @Param keyword �繵���÷���纤�ҤӤ鹷�������͡
     * @Return Vector �ͧ HealthySubgroup
     * @Author Ojika
     * @Date 14/03/2549
     **/
    public Vector selectHealthySubgroupByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theHealthySubgroupDB.selectByKeyword(keyword);
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
     * ����Ѻ������¡�����Ǩ�������㹡������õ�Ǩ�آ�Ҿ
     * @Param healthyGroupId ��  ���ʢͧ�������õ�Ǩ�آ�Ҿ
     * @Return Vector �ͧ HealthyGroupSurvey
     * @Author Ojika
     * @Date 09/03/2549
     **/
    public Vector selectHealthyGroupSurveyByHealthySubgroupId(String healthySubgroupId)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theHealthyGroupSurveyDB.selectByHealthySubgroupId(healthySubgroupId); 
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
     * ����Ѻź��¡�����Ǩ�������㹡������õ�Ǩ�آ�Ҿ
     * @Param obj �� Object �ͧ��¡�����Ǩ�������㹡������õ�Ǩ�آ�Ҿ
     * @Return int ���ͺ͡ʶҹС��ź��¡�� 
     * @Author Ojika
     * @Date 09/03/2549
     **/
    public int deleteHealthyGroupSurveyByKeyId(HealthyGroupSurvey obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theHealthyGroupSurveyDB.deleteByKeyID(obj.getObjectId());      
            theUS.setStatus("���ź��¡�����Ǩ㹡������Ǩ�آ�Ҿ�������", UpdateStatus.COMPLETE); 
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
     * ����Ѻ�ѹ�֡��¡�����Ǩ�������㹡������õ�Ǩ�آ�Ҿ
     * @Param obj �� Object �ͧ��¡�����Ǩ�������㹡������õ�Ǩ�آ�Ҿ
     * @Return int ���ͺ͡ʶҹС�úѹ�֡��¡�� 
     * @Author Ojika
     * @Date 09/03/2549
     **/
    public int saveHealthyGroupSurvey(HealthyGroupSurvey obj)
    {    
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theHosDB.theHealthyGroupSurveyDB.insertData(obj);     
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
     * ����Ѻ���� Ẻ���Ǩ�����
     * @Param keyword �繵���÷���纤�ҤӤ鹷�������͡
     * @Return Vector �ͧ HealthySurvey
     * @Author Ojika
     * @Date 09/03/2549
     **/
    public Vector selectHealthySurveyByKeyword(String keyword)
    {      
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theHosDB.theHealthySurveyDB.selectByKeyword(keyword);             
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
