/*
 * ReferHospitalControl.java
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
import com.reportnan.object.ReferHospital;
import java.util.Vector;
/**
 *
 * @author ojika
 */
public class ReferHospitalControl
{
    ConnectionInf theConnectionInf;
    ManageDB theManageDB;
    int iresult =0;
    Vector vData;
    
    public ReferHospitalControl(ManageDB mdb)
    {
        theManageDB = mdb;
        theConnectionInf = this.theManageDB.theConnectionInf;        
    }
    
    /**
     * ����Ѻ���� ��¡��ʶҹ��Һ�ŷ���ͧ��ô٢����š�� Refer
     * @Param keyword �� ��ҷ�������͡���ͤ���
     * @Return Vector �ͧ ��¡�õ�Ǩ�ѡ�ҷ�����ѵ����
     * @Author ojika
     * @Date 21/06/2549
     **/
    public Vector searchReferHospitalByKeyword(String keyword)
    {
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = theManageDB.theReferHospitalDB.selectByKeyword(keyword);  
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
     * ����Ѻź��¡�� ʶҹ��Һ�ŷ���ͧ��ô٢����š�� Refer
     * @Param ReferHospitalId �� ���ʢͧ��¡�� ʶҹ��Һ�ŷ���ͧ��ô٢����š�� Refer
     * @Return int ��ʶҹС��ź��¡��
     * @Author ojika
     * @Date 21/06/2549
     **/
    public int deleteReferHospital(String ReferHospitalId)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theManageDB.theReferHospitalDB.deleteByKeyID(ReferHospitalId);             
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
     * ����Ѻ�ѹ�֡��¡�� ʶҹ��Һ�ŷ���ͧ��ô٢����š�� Refer
     * @Param theReferHospital �� Object ���ʢͧ��¡�� ʶҹ��Һ�ŷ���ͧ��ô٢����š�� Refer
     * @Return int ��ʶҹС�úѹ�֡��¡��
     * @Author ojika
     * @Date 21/06/2549
     **/
    public int saveReferHospital(ReferHospital theReferHospital)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(theReferHospital != null)
            {
                if(theReferHospital.getObjectId() != null)
                {
                    // �����¡��
                    iresult = theManageDB.theReferHospitalDB.updateData(theReferHospital);              
                }
                else
                {
                    //�ѹ�֡����
                    iresult = theManageDB.theReferHospitalDB.insertData(theReferHospital);    
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
