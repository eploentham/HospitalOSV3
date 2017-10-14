/*
 * ResidentAgeGroupControl.java
 *
 * Created on 2 �չҤ� 2549, 14:12 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage.control;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.connection.UpdateStatus;
import com.setupreport.manage.control.HosDB;
import com.setupreport.object.ResidentAgeGroup;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class ResidentAgeGroupControl
{ 
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;
    private Vector vAgePortion;
    private int iresult;
    private boolean bresult;
    UpdateStatus theUS;
    /** Creates a new instance of ResidentAgeGroupControl */
    
    public ResidentAgeGroupControl(HosDB hdb, UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
  
    /**
     * ��㹡�� ���� �����Ţͧ ResidentAgeGroup ��������ŷ��Ф���
     * ������� ��Ф�͸Ժ��
     * @param search �� String �ͧ��ͤ������Ф���
     * @return �� Vector �ͧ Object ResidentAgeGroup
     * �������դ�Ҩ� return �� null
     * @Date 02/03/2006
     * @Author pu
     */
    public Vector selectResidentAgeGroupByCodeOrDescription(String search)
    {   vcData = null;
        try
        {
            theConnectionInf.open();            
            vcData = (Vector)theHosDB.theResidentAgeGroupDB.selectBySearch(search);            
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
    
    /**
     *�ѹ�֡��ǧ���آͧ��Ъҡ� �µ�Ǩ�ͺ��� 
     *�繡�úѹ�֡���������� �����繡���Ѿവ���������
     *@param residentagegroup �� Object �ͧ ResidentAgeGroup ����ͧ��úѹ�֡
     *@return �� Integer ����Ѻ�к���ҡ�úѹ�֡������������
     *  ��� return 1 �ѹ�֡�����
     *  ��� return 0 �ѹ�֡��������
     */
    public int saveResidentAgeGroup(ResidentAgeGroup residentagegroup)
    {   iresult = 0;
        try
        {
            theConnectionInf.open();
            if(residentagegroup.getObjectId() == null)
            {
                iresult = theHosDB.theResidentAgeGroupDB.insertData(residentagegroup);
            }
            else
            {
                iresult = theHosDB.theResidentAgeGroupDB.updateData(residentagegroup);
            }
            theUS.setStatus("��úѹ�֡��ǧ��������Ѻ��§ҹ��Ъҡ��������", UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus("��úѹ�֡��ǧ��������Ѻ��§ҹ��ЪҡüԴ��Ҵ", UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return iresult;
    }
    
    public int deleteResidentAgeGroup(ResidentAgeGroup residentagegroup)
    {
        iresult = 0;
        try 
        {
            theConnectionInf.open();
            if(residentagegroup.getObjectId() != null)
            {
                iresult = theHosDB.theResidentAgeGroupDB.deleteByKeyID(residentagegroup.getObjectId());
            }            
            theUS.setStatus("���ź��ǧ��������Ѻ��§ҹ��Ъҡ��������", UpdateStatus.COMPLETE);
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
