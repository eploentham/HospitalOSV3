
/*
 * ComboBoxControl.java
 *
 * Created on 20 �Զع�¹ 2549, 11:44 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreportnan.control;

import java.util.*;
import com.hospital_os.usecase.connection.*;
import com.setupreportnan.control.ManageDB;
/**
 *
 * @author ojika
 */
public class ComboBoxControl
{
    private ConnectionInf theConnectionInf;
    private ManageDB theManageDB;
    
    /** ������ʶҹ��Һ�� */
    private Vector vHospitalType;
    
    public ComboBoxControl(ManageDB manageDB)
    {
        theManageDB = manageDB;
        theConnectionInf = theManageDB.theConnectionInf;       
        theConnectionInf.open();
        
        try
        {  
            // �����Ţͧ HospitalType
            vHospitalType = theManageDB.theReferHospitalTypeDB.selectAll();
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            theConnectionInf.close();
        }
    }
    
    /**
     * �����Ţͧ HospitalType
     * @Author Ojika
     * @Date 21/06/2549
     **/
    public Vector listReferHospitalType() 
    {
        if((vHospitalType != null))
        {
            return vHospitalType;
        }
        theConnectionInf.open();
        try
        {
            vHospitalType = new Vector();
            vHospitalType = theManageDB.theReferHospitalTypeDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vHospitalType;
    }
    
}
