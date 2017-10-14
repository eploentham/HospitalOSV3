/*
 * AccidentTypeLookup.java
 *
 * Created on 2 �Զع�¹ 2549, 13:28 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.control.lookup;
//import com.henbe.connection.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.ComboFix;
//import com.hosv3.utility.*;
import java.util.Vector;

/**
 *
 * @not deprecated because use henbe package
 * @author Padungrat(tong)
 */
public class R53PersonForeignLookup implements LookupControlInf{
    private Vector vret;
    public java.util.Vector listData(String str) { 
        if(vret!=null)
            return vret;
        vret = new Vector();
        vret.add(new ComboFix("","����繵�ҧ����"));
//        vret.add(new ComboFix("11","������Ţ 13 ��ѡ ����鹵鹴����Ţ 6,7"));
//        vret.add(new ComboFix("12","��������ʷ���鹵鹴����Ţ 0 ����� Workpermitted"));
//        vret.add(new ComboFix("13","��鹷���¹�Ѻ��з�ǧ��Ҵ�·�������ʷ���鹵鹴����Ţ 0"));
//        vret.add(new ComboFix("14","��з�ǧ�ç�ҹ  ���� Passport/Visa "));
//        vret.add(new ComboFix("21","��ҧ���Ƿ��;¾ �������㹤���/�ٹ��ѡ�ԧ"));
//        vret.add(new ComboFix("22","��ҧ���Ƿ��Դ������� 11 ��� 12 ��������/�����/�ص�/�ҵ�"));
//        vret.add(new ComboFix("23","���¶֧���������"));
        vret.add(new ComboFix("01","��ҧ���Ǣ�鹷���¹ 㹡�����ѡ���¹�ʶҹ�֡�ҷ���Ѻ���ʴԡ�èҡ�Ѱ��ҹ����֡��"));
        vret.add(new ComboFix("02","��ҧ���Ǣ�鹷���¹ 㹡����������ҡ�˧��"));
        vret.add(new ComboFix("03","��ҧ���Ǣ�鹷���¹ 㹡����������ջ���ª�������Ȫҵ�"));
        vret.add(new ComboFix("11","������Ţ 13 ��ѡ  ����鹵鹴��� 6"));
        vret.add(new ComboFix("12","��������ʷ���鹵鹴����Ţ 0 ����� Workpermitted"));
        vret.add(new ComboFix("13","��鹷���¹�Ѻ��з�ǧ��Ҵ�·�������ʷ���鹵鹴����Ţ 0"));
        vret.add(new ComboFix("14","��з�ǧ�ç�ҹ  ���� Passport/Visa"));
        vret.add(new ComboFix("15","��������ʷ���鹵鹴����Ţ 3 ��� 4 ���������ѭ�ҵ���"));
        vret.add(new ComboFix("16","��������ʷ���鹵鹴����Ţ 5 ���������ѭ�ҵ���"));
        vret.add(new ComboFix("17","��������ʷ���鹵鹴����Ţ 8 ��� ������ѭ�ҵ���"));
        vret.add(new ComboFix("18","��������ʷ���鹵鹴����Ţ 7"));
        vret.add(new ComboFix("21","��ҧ���Ƿ��;¾�������㹤���/�ٹ��ѡ�ԧ"));
        vret.add(new ComboFix("22","��ҧ���Ƿ���繼��Դ����ͧ���� 11 ,12 ,15,16,17 ��ҧ������ ����/�����/�ص�/�ҵ�"));
        vret.add(new ComboFix("23","���¶֧���������"));
        return vret;
    }
    public CommonInf readHosData(String str) {
        Vector v = listData(str);
        if(v.isEmpty())
            return null;
        return (ComboFix)v.get(0);
    }
}
