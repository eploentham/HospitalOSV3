package com.report12file.objectnh53;

import com.linuxense.javadbf.DBFField;
import com.report12file.object.Rp12OI2;
import com.report12file.utility.Report12FileData;
import com.reportcenter.object.RpField; 
import com.reportcenter.utility.IOStream;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
/*
 * AncNh.java
 *
 * Created on 11 �ѹ��¹ 2551, 15:17 �.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.                                                                                                            ��õ�Ǩ�ͺ
 HCODE                  Text           5           0       ���ʶҹ��Һ�� (Left justified)
 HN                     Text           9           0       �����Ţ��ШҵǼ?�Ѻ��ԡ�� ����?�����Ţ
                                                           ����?�ҹ��?� 5 �? (Left justified)
 AN                     Text           9           0       �����Ţ��ШҵǼ?�?��� ��?����?�����Ţ���
                                                           ���� (Left justified)
 CLINIC                 Text           4           0                                                             Y
                                                           ���ʤ�Թԡ����Ѻ��ԡ�� (���ʹ�.) (Left justified)
 PERSON_ID              Text          13           0       ���ʻ�Ш�ҵ�ǻ�ЪҪ� �����ҹѡ����¹��ɯ�?           Y
 DATE_SERV              Date           8           0       �ѹ������Ѻ��ԡ�� �ѹ�֡ �?㹤?��?� �.�.            Y
 DID                    Text          30           0       �������?ҵ���к�����������?                       N
 DIDNAME                Text         255           0       �����ҷ���?���?�?��غѹ����ѹ�?�Ѻ DID               N
 AMOUNT                 Text          12           0       ��ҹǹ�ҷ��?��                                       Y
 DRUGPRIC               Text          14           0       �ҤҢ��                                               Y
 DRUGCOST               Text          14           0       �Ҥҷ�    �                                           Y
 DIDSTD                 Text          24           0       ������ҵðҹ 24 ��ѡ
                                                                   �                                             Y
 UNIT                   Text          20           0       ˹?�¹Ѻ�ͧ�ҷ���㹡�è?����                         Y
 UNIT_PACKING           Text          20           0       ��Ҵ��èص?�˹?�¹Ѻ                                  Y
 SEQ                    Text          15           0       ���ʡ�ú�ԡ�÷����˹�����������§��ҴѺ           N
                                                           ��?���ҡѹ (�?���е?ͧ�շء�?���?�������Ǣ?ͧ
                                                                               �
                                                           �Ѻ��?�?�¹͡ : OPD)
�����˵� �óշ���?��?� Text File FIELD TYPE ����?� Date �?ͧ��Ѻ�?� Text (YYYYMMDD) ����?� �.�.

 */

public class DruNh53 extends Rp12OI2{
    private String hcode;
    private String hn;
    private String an;
    private String clinic;
    private String person_id;
    private String date_serv;
    private String did;
    private String dname;
    private String amount;
    private String drugpric;
    private String drugcost;
    private String didstd;
    private String unit;
    private String unit_packing;
    private String seq;

    public DruNh53() {
    }
    public String[] getValueArray() {
        return new String[]{
    hcode,
    hn,
    an,
    clinic,
    person_id,
    date_serv,
    did, 
    dname,
    amount,
    drugpric,
    drugcost,
    didstd,
    unit,
    unit_packing,
    seq
        };
    }
    
    public boolean setValue(ResultSet rs) throws Exception {
        DruNh53 p = this;
        p.hcode = Report12FileData.getRsString(rs,1);
        p.hn = Report12FileData.getRsString(rs,2);
        p.an = Report12FileData.getRsString(rs,3);
        p.clinic = Report12FileData.getRsString(rs,4);
        p.person_id = Report12FileData.getRsString(rs,5);
        p.date_serv = Report12FileData.getRsString(rs,6);
        p.did = Report12FileData.getRsString(rs,7);
        p.dname = Report12FileData.getRsString(rs,8);
        p.amount = Report12FileData.getRsString(rs,9);
        p.drugpric = Report12FileData.getRsString(rs,10);
        p.drugcost = Report12FileData.getRsString(rs,11);
        p.didstd = Report12FileData.getRsString(rs,12);
        p.unit = Report12FileData.getRsString(rs,13);
        p.unit_packing = Report12FileData.getRsString(rs,14);
        p.seq = Report12FileData.getRsString(rs,15);
        return true;
    }
    
    public Rp12OI2 initInstant() {
        return new DruNh53();
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        
        String sql = IOStream.readInputDefault("config/rp_12file53/12file53_drug.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }

    public String getFileName() {
        return "DRU";
    }
    /**
     * ��Ѻ���Ŵ� date_serv ����ա���纤��Ẻ Date
     * @return
     */
    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
        RpField.initData("	hcode	","	����ʶҹ��ԡ��	",	5	,	true,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	hn	","	�Ţ����Ѻ��ԡ��	",	9	,	true,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	an	","	�Ţ�������	",	9	,	false,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	clinic	","	���ʤ�Թԡ	",	4	,	true,true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	person_id	","	�Ţ�ѵû�ЪҪ�	",	13	,	true,true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	date_serv	","	�ѹ����Ѻ��ԡ��	",	8	,	true,true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D	)
        ,RpField.initData("	did	","	������	",	30	,	false,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	dname	","	�ӹǹ	",	255	,	false,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	amount	","	�ҤҢ��	",	12	,	true,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	drugpric	","	�Ҥҷع	",	14	,	true,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	drugcost	","	���������	",	14	,	true,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	didstd	","	������ 24 ��ѡ	",	24	,	true,true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	unit	","	˹���	",	20	,	false,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	unit_packing	","	��Ҵ���˹��¹Ѻ	",	20	,	false,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	seq	","	���ʡ�ú�ԡ��	",	15	,	false,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        };
        return theRPF;
    }
}
