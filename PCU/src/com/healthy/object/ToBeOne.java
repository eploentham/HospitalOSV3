/*
 * ToBeOne.java
 *
 * Created on 5 ����¹ 2549, 16:21 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.healthy.object;

import com.hospital_os.utility.XPersistent;

import java.util.*;
/**
 *
 * @author hospitalos5
 * @modify sumo 30/08/2549
 */
public class ToBeOne extends XPersistent {
    
    /**���� TobeOne*/
    public String toBe_id = "";
    /**���ʻ�Ъҡ�*/
    public String family_id = "";
    /**���ʶҹС������Ҫԡ 0-�����, 1-��*/
    public String is_member = "0";
    /**�ѹ������������Ҫԡ*/
    public String regisDate = "";
    /**ʶҹ�������Ѥ�*/
    public String workPlace = "";
    /**deprecate*/
    public String eduSys = "";
    /**�˵ؼš����Ѥ�*/
    public String reason = "";
    /**�����Դ��繢ͧ��Ҫԡ*/
    public String opinion = "";
    /**�ѹ���ҷ��ѹ�֡*/
    public String record_time = "";
    /**�ѹ���ҷ����䢢�����*/
    public String modify_time = "";
    /**�ѹ���ҷ��¡��ԡ*/
    public String cancel_time = "";
    /**���ѹ�֡������*/
    public String staff_record = "";
    /**�����䢢�����*/
    public String staff_modify = "";
    /**���ѹ�֡���¡��ԡ*/
    public String staff_cancel = "";
    /**ʶҹС����ҹ 0-¡��ԡ, 1-��ҹ����*/
    public String active = "1";
    
    /** Creates a new instance of ToBeOne */
    public ToBeOne() {
    }
    
}
