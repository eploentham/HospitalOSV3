/*
 * AricDiseaseCodeControl.java
 *
 * Created on 26 ���Ҥ� 2548, 18:46 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage.control;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.connection.UpdateStatus;
import com.setupreport.manage.control.HosDB;
import com.setupreport.object.*;
import java.util.Vector;
/**
 *
 * @author tong(Padungrat)
 */
public class AricDiseaseCodeControl {
    
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData,vcDataTemp;
    private int iresult;
    UpdateStatus theUS;
    public AricDiseaseCodeControl(HosDB hdb,UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    
    
    /**
     * ��㹡�� ���� ������ͧ�����ä�ҡ aricgroup
     * @param aricgroup  �� ���ʢͧ aricgroup ����ͧ��ä������ ���������ú�ҧ
     * @return �� Vector �ͧ Object AricDiseaseCode
     *  �������դ�Ҩ� return �� null
     */
    public Vector selectAricDiseaseCodeByAricGroup(String aricgroup)
    {   vcData = null;
        try
        {
            theConnectionInf.open();
            vcData = (Vector)theHosDB.theAricDiseaseCodeDB.selectByAricGroupID(aricgroup);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        
        return vcData;
    }
    
    public int saveAricDiseaseCode(AricDiseaseCode aricdiseasecode)
    {   iresult = 0;
        try
        {
            theConnectionInf.open();
            if(aricdiseasecode.getObjectId() == null)
            {
                iresult = theHosDB.theAricDiseaseCodeDB.insertData(aricdiseasecode);
            }
            else
            {
                iresult = theHosDB.theAricDiseaseCodeDB.updateData(aricdiseasecode);
            }
            theUS.setStatus("��úѹ�֡�����ä����� ARIC �������",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus("��úѹ�֡�����ä����� ARIC �Դ��Ҵ",UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return iresult;
    }
    
    public int deleteAricDiseaseCodeByAricGroupID(String aricgroupid)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
           
                iresult = theHosDB.theAricDiseaseCodeDB.deleteAricGroupID(aricgroupid);
           theUS.setStatus("���ź������ͧ ARIC �������",UpdateStatus.COMPLETE);
            
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
    
    
    public int deleteAricDiseaseCode(AricDiseaseCode aricdiseasecode)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(aricdiseasecode.getObjectId() != null)
            {
                iresult = theHosDB.theAricDiseaseCodeDB.deleteByKeyID(aricdiseasecode.getObjectId());
            }
            theUS.setStatus("���ź�����ä����� ARIC �������",UpdateStatus.COMPLETE);
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
