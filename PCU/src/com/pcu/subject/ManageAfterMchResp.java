/*
 * ManageAfterMchResp.java
 *
 * Created on 29 �á�Ҥ� 2548, 15:50 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.subject;
import com.pcu.object.BornMch;
/**
 *
 * @author tong(Padungrat)
 */
public interface ManageAfterMchResp {
    
    /** ��㹡�����¡�����š������Ѻ��ԡ����ѧ��ʹ */
    public void notifyCallAfterMchMother(BornMch bornmch);
    /**��㹡�����¡������ ����ʴ� panel �ͧ�����š�ä�ʹ*/
    public void notifyCallAfterBornMchMother();
    /**����觢����š�Ѻ����� panel �����š������Ѻ��ԡ����ѧ��ʹ*/
    public void notifyGetDataBornMchMotherToAfterMchMother(BornMch bornmch);
    /** ��㹡�����¡������ ��� panel �ͧ ��������ѧ��ʹ �ʴ� ���úҧ���ҧ������ա�����͡ Panel �ͧ PCU*/
    public void notifyCallAfterBornMchMotherService(boolean inFirst);
}
