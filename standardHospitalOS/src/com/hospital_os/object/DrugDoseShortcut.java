/*
 * DrugDoseShortcut.java
 *
 * Created on 3 �ԧ�Ҥ� 2549, 16:02 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.usecase.connection.CommonInf;

public class DrugDoseShortcut extends Persistent implements CommonInf
{
/**
 *
 * @author Administrator
 */  
    /**������¡�� Dose ���*/
    public String drugdose_shortcut_id;
    /**�ӴѺ��¡�� Dose ���*/
    public String code;
    /**������¡�� Dose ���*/
    public String description;
    /**�Ը�����*/
    public String drug_frequency_id;
    /**�������㹡������*/
    public String drug_instruction_id;
    /**�ӹǹ�ҷ����*/
    public String qty;
    /**ʶҹС���ʴ���������ʴ���¡��*/
    public String active;
    
    /** Creates a new instance of DrugDoseShortcut */
    public DrugDoseShortcut()
    {
    }
    public static String emptyData()
    {
        return "2910000000001";
    }
    public String getCode()
    {
        return this.getObjectId();
    }

    public String getName()
    {
         return this.description;
    }
    public String toString(){
        return description;
    }
    
}
