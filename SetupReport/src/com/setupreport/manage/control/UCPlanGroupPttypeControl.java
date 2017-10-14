/*
 * UCPlanGroupPttypeControl.java
 *
 * Created on 28 ���Ҥ� 2548, 10:15 �.
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
 * @author americus
 */
public class UCPlanGroupPttypeControl {
    
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;
    private int iresult;
    private boolean bresult;
    UpdateStatus theUS;
    public UCPlanGroupPttypeControl(HosDB hdb,UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    
    public boolean checkSameUCPlanGroupID(String pttypenumber,String key_id)
    {   bresult = false;
        try
        {
            theConnectionInf.open();
            bresult = theHosDB.theUCPlanGroupPttypeDB.checkSameUCPlanGroupCode(pttypenumber,key_id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        
        return bresult;
    }
    
    public boolean checkSameUCPlanPtType(String pttype,String key_id)
    {   bresult = false;
        try
        {
            theConnectionInf.open();
            bresult = theHosDB.theUCPlanGroupPttypeDB.checkSameUCPlanPtType(pttype,key_id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        
        return bresult;
    }
    
    /**
     * ��㹡�� ���� �����Ţͧ UCPlanGroupPttype ��������ŷ��Ф���
     * ������� ��Ф�͸Ժ��
     * @param search �� String �ͧ��ͤ������Ф���
     * @return �� Vector �ͧ Object UCPlanGroupPttype
     *  �������դ�Ҩ� return �� null
     */
    public Vector selectUCPlanGroupMapPtTypeByCodeOrDescription(String search)
    {   vcData = null;
        try
        {
            theConnectionInf.open();
            
            vcData = (Vector)theHosDB.theUCPlanGroupPttypeDB.selectBySearch(search);
            
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
    
    public int saveUCPlanGroupMapPtType(UCPlanGroupPttype mappttype)
    {   iresult = 0;
        try
        {
            theConnectionInf.open();
            if(mappttype.getObjectId() == null)
            {
                iresult = theHosDB.theUCPlanGroupPttypeDB.insertData(mappttype);
            }
            else
            {
                iresult = theHosDB.theUCPlanGroupPttypeDB.updateData(mappttype);
            }
            theUS.setStatus("��úѹ�֡���� PTType �ͧ�Է�� UC �������",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus("��úѹ�֡���� PTType �ͧ�Է�� UC �Դ��Ҵ",UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return iresult;
    }
    
    public int deleteUCPlanGroupMapPtType(UCPlanGroupPttype mappttype)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(mappttype.getObjectId() != null)
            {
                iresult = theHosDB.theUCPlanGroupPttypeDB.deleteByKeyID(mappttype.getObjectId());
            }
            theUS.setStatus("���ź���� PTType �ͧ�Է�� UC �������",UpdateStatus.COMPLETE);
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
