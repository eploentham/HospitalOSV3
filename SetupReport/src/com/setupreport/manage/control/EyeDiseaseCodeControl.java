/*
 * EyeDiseaseCodeControl.java
 *
 * Created on 26 ���Ҥ� 2548, 15:46 �.
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
public class EyeDiseaseCodeControl
{
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vcData;
    private Object objectIcd10;
    private int iresult;
    UpdateStatus theUS;
    /** Creates a new instance of EyeDiseaseCodeControl */
    public EyeDiseaseCodeControl(HosDB hdb,UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    /**
     * ��㹡�� ���� �����Ţͧ EyeDiseaseCode ��������ŷ��Ф���
     * ������� ��Ф�͸Ժ��
     * @param search �� String �ͧ��ͤ������Ф���
     * @return �� Vector �ͧ Object EyeDiseaseCode
     *  �������դ�Ҩ� return �� null
     */
    public Vector selectEyeDiseaseCodeByEyeGroupID(String key)
    {   vcData = null;
        try
        {
            theConnectionInf.open();
            vcData = (Vector)theHosDB.theEyeDiseaseCodeDB.selectByEyeGroupID(key);
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
     *�ѹ�֡������ EyeDiseaseCode
     * @param eyedisease �� Object �ͧ EyeDiseaseCode ����ͧ��úѹ�֡
     * @return �� Integer ����Ѻ�к���ҡ�úѹ�֡������������
     *  ��� return 1 �ѹ�֡�����
     *  ��� return 0 �ѹ�֡��������
     **/
    public int saveEyeDiseaseCode(EyeDiseaseCode eyedisease)
    {   iresult = 0;
        try
        {
            theConnectionInf.open();
            if(eyedisease.getObjectId() == null)
            {
                iresult = theHosDB.theEyeDiseaseCodeDB.insertData(eyedisease);
            }
            else
            {
                iresult = theHosDB.theEyeDiseaseCodeDB.updateData(eyedisease);
            } 
            theUS.setStatus("��úѹ�֡�����ä������ä���������",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus("��úѹ�֡�����ä������ä���������",UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return iresult;
    }
    
    /*
     *ź������ EyeDiseaseCode
     * @param eyedisease �� Object �ͧ EyeDiseaseCode ����ͧ��úѹ�֡
     * @return �� Integer ����Ѻ�к���ҡ�úѹ�֡������������
     *  ��� return 1 ź�����
     *  ��� return 0 ź��������
     */
    public int deleteEyeDiseaseCode(EyeDiseaseCode eyedisease)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            if(eyedisease.getObjectId() != null)
            {
                iresult = theHosDB.theEyeDiseaseCodeDB.deleteByKeyID(eyedisease.getObjectId());
            }
            theUS.setStatus("���ź�����ä������ä���������",UpdateStatus.COMPLETE);
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
    
    public int deleteEyeDiseaseCodeByEyeGroupID(String eyegroupid)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            
            iresult = theHosDB.theEyeDiseaseCodeDB.deleteEyeDiseaseByGroupID(eyegroupid);            
            theUS.setStatus("���ź�����ä������ä���������",UpdateStatus.COMPLETE);
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
    
   public Object selectIcd10BySearch(String icd10)
   {
        objectIcd10 = null;
        try
        {
            theConnectionInf.open();
            objectIcd10 = theHosDB.theICD10DB.selectBySearch(icd10);
            System.out.println("----------Control : " + objectIcd10);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        
        return objectIcd10;
   }
        
   /*public boolean checkHaveICD10(String icd10,String key_id)
   {
       objectIcd10 = null;
       try
       {
           theConnectionInf.open();
           objectIcd10 = theHosDB.theICD10DB.checkHaveICD10(icd10, key_id);
           System.out.println("----------Control : " + objectIcd10);
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       finally
       {
           theConnectionInf.close();
       }
       
       return objectIcd10;
   }
    */
}
