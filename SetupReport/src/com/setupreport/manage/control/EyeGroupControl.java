/*
 * EyeGroupControl.java
 *
 * Created on 26 ���Ҥ� 2548, 15:45 �.
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
public class EyeGroupControl
{
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;
    private int iresult;
    boolean bresult;
    UpdateStatus theUS;
    /** Creates a new instance of EyeGroupControl */
    public EyeGroupControl(HosDB hdb,UpdateStatus us)
    {
        theHosDB = hdb;
        theUS = us;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    /**
     * ��㹡�� ���� �����Ţͧ EyeGroup ��������ŷ��Ф���
     * ������� ��Ф�͸Ժ��
     * @param search �� String �ͧ��ͤ������Ф���
     * @return �� Vector �ͧ Object EyeGroup
     *  �������դ�Ҩ� return �� null
     */
    public Vector selectEyeGroupByCodeOrDescription(String search,String active)
    {   vcData = null;
        try
        {
            theConnectionInf.open();
            vcData = (Vector)theHosDB.theEyeGroupDB.selectBySearch(search,active);
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
    
    /*
     *�ѹ�֡������ EyeGroup   
     * @param eyegroup �� Object �ͧ Eyegroup ����ͧ��úѹ�֡
     * @return �� Integer ����Ѻ�к���ҡ�úѹ�֡������������
     *  ��� return 1 �ѹ�֡�����
     *  ��� return 0 �ѹ�֡��������
     **/
    public int saveEyeGroup(EyeGroup eyegroup)
    {   iresult = 0;
        try
        {
            theConnectionInf.open();
            if(eyegroup.getObjectId() == null)
            {
                iresult = theHosDB.theEyeGroupDB.insertData(eyegroup);
            }
            else
            {
                iresult = theHosDB.theEyeGroupDB.updateData(eyegroup);
            }
            theUS.setStatus("��úѹ�֡������ͧ�����ä���������",UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            ex.printStackTrace();
            theUS.setStatus("��úѹ�֡������ͧ�����ä�ҼԴ��Ҵ",UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return iresult;
    }
    /*
     *ź������ EyeGroup 
     * @param eyegroup �� Object �ͧ Eyegroup ����ͧ��úѹ�֡
     * @return �� Integer ����Ѻ�к���ҡ�úѹ�֡������������
     *  ��� return 1 ź�����
     *  ��� return 0 ź��������
     */
    public int deleteEyeGroup(EyeGroup eyegroup)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(eyegroup.getObjectId() != null)
            {
                iresult = theHosDB.theEyeGroupDB.deleteByKeyID(eyegroup.getObjectId());
            }
            theUS.setStatus("���ź������ͧ�����ä���������",UpdateStatus.COMPLETE);
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
     *  ��㹡�õ�Ǩ�ͺ ����� Code ��ӡѹ������� ����ի�ӡѹ �� ������� false �������Ө�������� true
     *  @return �� boolean ����� true ������� ����� false �Ы��
     */
    public boolean checkSameEyeGroupCode(String eyegroupcode,String key_id)
    {
        bresult = false;
        
        try
        {
            theConnectionInf.open();
            bresult = theHosDB.theEyeGroupDB.checkSameEyeGroupCode(eyegroupcode, key_id);
            
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
     *  ��㹡�õ�Ǩ�ͺ ����� Description ��ӡѹ������� ����ի�ӡѹ �� ������� false �������Ө�������� true
     *  @return �� boolean ����� true ������� ����� false �Ы��
     */
    public boolean checkSameEyeGroupThaiDescription(String thaidescrip,String key_id)
    {
        bresult = false;
        
        try
        {
            theConnectionInf.open();
            bresult = theHosDB.theEyeGroupDB.checkSameEyeGroupThaiDescription(thaidescrip, key_id);
            
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
     *  ��㹡�õ�Ǩ�ͺ ����� Description ��ӡѹ������� ����ի�ӡѹ �� ������� false �������Ө�������� true
     *  @return �� boolean ����� true ������� ����� false �Ы��
     */
    public boolean checkSameEyeGroupEngDescription(String engdescrip,String key_id)
    {
        bresult = false;
        
        try
        {
            theConnectionInf.open();
            bresult = theHosDB.theEyeGroupDB.checkSameEyeGroupThaiDescription(engdescrip, key_id);
            
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
}
