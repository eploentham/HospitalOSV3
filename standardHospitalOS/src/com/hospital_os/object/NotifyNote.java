/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;

/**
 *
 * @author Somprasong
 */
public class NotifyNote extends Persistent {

    /**�Ţ hn*/
    public String patient_hn = "";
    /**�Ţ visit_id �͹�ѹ�֡*/
    public String visit_id_rec = "";
    /**�Ţ visit_id �����͹����ش*/
    public String visit_id_last_view = "";
    /**���ʪ�Դ�ͧ�����͹ 1 = ���駶Ѵ�, 2 = �ء����*/
    public String notify_type_id = "";
    /**�������ͧ*/
    public String note_subject = "";
    /**��ͤ���*/
    public String note_detail = "";
    /**1= act, 0 = inact*/
    public String active = "1";
    public String rec_staff = "";
    public String rec_datetime = "";
    public String mod_datetime = "";
    public String del_datetime = "";
    public int notify_count = 0;
    public String noter = "";

    @Override
    public String toString(){
        return getObjectId();
    }
}
