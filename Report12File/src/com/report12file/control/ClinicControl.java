/*
 * ClinicControl.java
 *
 * Created on 16 �չҤ� 2549, 14:22 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.control;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.report12file.objdb.HosDB;
import com.report12file.object.Clinic12Files;
import com.report12file.object.ClinicMap;
import java.util.Vector;
/**
 *
 * @author Ojika
 */
public class ClinicControl {
    
    /** Creates a new instance of ClinicControl */
    private ConnectionInf theConnectionInf;
    private int iresult;
    Vector vData;
    ClinicMap theClinicMap;
    
    private HosDB theHosDB;
    
    public ClinicControl(HosDB hdb) {
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    /**
     * ����Ѻ���� clinic
     * @Param keyword �繵���÷���纤�ҤӤ鹷�������͡
     * @Return Vector �ͧ Clinic
     * @Author Ojika
     * @Date 27/01/2550
     **/
    public Vector selectClinicByKeyword(String keyword) {
        vData = new Vector();
        try {
            theConnectionInf.open();
            vData = theHosDB.theClinicDB.selectByKeyword(keyword);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
        
        return vData;
    }
    
    /**
     * ����Ѻ����  Clinic �ͧ��§ҹ 12 ���
     * @Param keyword �繵���÷���纤�ҤӤ鹷�������͡
     * @Return Vector �ͧ Clinic12Files
     * @Author Ojika
     * @Date 27/01/2550
     **/
    public Vector selectClinic12FilesByKeyword(String keyword) {
        vData = new Vector();
        try {
            theConnectionInf.open();
            vData = theHosDB.theClinic12FilesDB.selectByKeyword(keyword);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
        
        return vData;
    }
    
    /**
     * ����Ѻ������� Map Clinic �ҡ�Ӥ�
     * @Param clinic12FilesId ��  ���ʢͧ�������Դ��������ҡ��
     * @Return Vector �ͧ ClinicMap
     * @Author Ojika
     * @Date 27/01/2550
     **/
    public Vector selectClinicMapByClinic12FilesId(String clinic12FilesId) {
        vData = new Vector();
        try {
            theConnectionInf.open();
            vData = theHosDB.theClinicMapDB.selectByClinic12FilesId(clinic12FilesId);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
        
        return vData;
    }
    
    /**
     * ����Ѻź��¡�� Map Clinic
     * @Param obj �� Object �ͧ��¡�� Map Clinic
     * @Return int ���ͺ͡ʶҹС��ź��¡��
     * @Author Ojika
     * @Date 27/01/2550
     **/
    public int deleteClinicMapByKeyId(ClinicMap obj) {
        iresult = 0;
        try {
            theConnectionInf.open();
            iresult = theHosDB.theClinicMapDB.deleteByKeyID(obj.getObjectId());
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
        
        return iresult;
    }
    
    /**
     * ����Ѻ�ѹ�֡��¡�� Map Clinic
     * @Param obj �� Object �ͧ��¡�� Map Clinic
     * @Return int ���ͺ͡ʶҹС�úѹ�֡��¡��
     * @Author Ojika
     * @Date 27/01/2550
     **/
    public int saveClinicMap(ClinicMap obj) {
        iresult = 0;
        try {
            theConnectionInf.open();
            iresult = theHosDB.theClinicMapDB.insertData(obj);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
        
        return iresult;
    }
    
    /**
     * ����Ѻ��Ǩ�ͺ�����ū����Ҩе�ͧ insert �����������
     * @Author Ojika
     * @Date 27/01/2550
     **/
    public int checkSaveClinicMap(Clinic12Files c12f,Vector vClinicMap) {
        iresult = 0;
        try{
            theConnectionInf.open();
            iresult = theHosDB.theClinicMapDB.deleteByCid(c12f.getObjectId());
            for(int i=0,size=vClinicMap.size();i<size;i++) {
                this.theClinicMap = (ClinicMap)vClinicMap.get(i);
                theClinicMap.t_report_clinic_12files_id = c12f.t_report_clinic_12files_id;
                theClinicMap.setObjectId(c12f.t_report_clinic_12files_id);
                iresult = theHosDB.theClinicMapDB.insertData(theClinicMap);
            }
            return iresult;
        } catch(Exception e){
            e.printStackTrace();
            return 0;
        } finally{
            theConnectionInf.close();
        }
    }
    
    public Vector selectClinicMap() {
        try{
            theConnectionInf.open();
            return theHosDB.theClinicMapDB.selectMap();
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
        return vData;
    }
    
}
