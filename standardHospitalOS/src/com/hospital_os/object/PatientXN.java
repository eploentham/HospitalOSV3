/*
 * PatientXN.java
 *
 * Created on 7 ����¹ 2549, 17:18 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;

/**
 * �红����Ţͧ�Ţ XN
 * @author tong(Padungrat)
 *
 */
public class PatientXN extends Persistent
{
    /**������ѡ�ͧ���ҧ*/
    public String t_patient_xn_id;
    /**�Ţ XN */
    public String patient_xray_number = "";
    /** �� �.�. ������Ţ XN ���*/
    public String patient_xn_year= "";
    /** ���ʢͧ ���ҧ t_patient */
    public String t_patient_id= "";
    /** ʶҹТͧ�����ҹ ����� 1 ����� ����� 0 �������� */
    public String patient_xn_active= "1";
    /** Creates a new instance of PatientXN */
    public PatientXN() {
    }
    
}
