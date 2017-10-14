package com.report18file.objectpp53;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Rp18OI2;
import com.report18file.utility.Constant;
import com.report18file.utility.Report18FileData;
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
 * and open the template in the editor.
 */

/**
 *
 * @author henbe
 */
public class ServeilPp53 extends Rp18OI2{

    /**����ʶҹ��ԡ��*/
    public String  pcucode = "";
    /** �Ţ���ѵû�ЪҪ�*/
    public String  cid = "";
    /**�Ţ HCIS*/
    public String  pid = "";
    /**�Ţ VN*/
    public String  seq = "";
    /**�ѹ���*/
    public String  date_serv = "";
    /** ���ʡ���ԹԨ���*/
    public String  diagcode = "";
    /**���� 506*/
    public String  code506 = "";
    /**�ѹ������������*/
    public String  illdate = "";
    /**��ҹ�Ţ���(��л���)*/
    public String  illhouse = "";
    /**���������ҹ(��л���)*/
    public String  illvill = "";
    /**���ʵӺ�(��л���)*/
    public String  illtamb = "";
    /**���������(��л���)*/
    public String  illampu = "";
    /**���ʨѧ��Ѵ(��л���)*/
    public String  illchan = "";
    /**��Ҿ������*/
    public String  ptstat = "";
    /**�ѹ�����*/
    public String  date_death = "";
    /**���˵ء�û���*/
    public String  complica = "";
    /**��Դ�ͧ�����ä*/
    public String  organism = "";
    public String d_update = "";
    /** Creates a new instance of AncNh */
    public ServeilPp53() {
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {

        String sql = IOStream.readInputDefault("config/rp_18file_pp53/18file_pp53_surveil.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }

    public Rp18OI2 initInstant() {
        return new ServeilPp53();
    }

  

    public boolean setValue(ResultSet rs) throws Exception {
        ServeilPp53 p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.cid = Report18FileData.getRsString(rs,2);
        p.pid = Report18FileData.getRsString(rs,3);
        p.seq = Report18FileData.getRsString(rs,4);
        p.date_serv = Report18FileData.getRsString(rs,5);
        p.diagcode = Report18FileData.getRsString(rs,6);
        p.diagcode = Constant.removeDot(p.diagcode);
        p.code506 = Report18FileData.getRsString(rs,7);
        p.illdate = Report18FileData.getRsString(rs,8);
        p.illhouse = Report18FileData.getRsString(rs,9);
        p.illvill = Report18FileData.getRsString(rs,10);
        p.illtamb = Report18FileData.getRsString(rs,11);
        p.illampu = Report18FileData.getRsString(rs,12);
        p.illchan = Report18FileData.getRsString(rs,13);
        p.ptstat = Report18FileData.getRsString(rs,14);
        p.date_death = Report18FileData.getRsString(rs,15);
        p.complica = Report18FileData.getRsString(rs,16);
        p.organism = Report18FileData.getRsString(rs,17);
        p.d_update = Report18FileData.getRsString(rs,18);
        return true;
    }
    public String[] getValueArray() {
        return new String[]{
            pcucode	,
            cid	,
            pid	,
            seq	,
            date_serv	,
            diagcode	,
            code506	,
            illdate	,
            illhouse	,
            illvill	,
            illtamb	,
            illampu	,
            illchan	,
            ptstat	,
            date_death	,
            complica	,
            organism	,
            d_update
        };
    }

    public String getFileName() {
        return "Surveil";
    }

    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
 RpField.initData("	pcucode	","	����ʶҹ��ԡ��	",	5	,	false	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	cid	","	���ʺѵû�ЪҪ�	",	13	,	false	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	pid	","	���� �ؤ��	",	13	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	seq	","	�ӴѺ���	",	16	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	date_serv	","	�ѹ���	",	8	,	false	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	diagcode	","	���ʡ���ԹԨ���	",	6	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	code506	","	����506	",	2	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	illdate	","	�ѹ������������	",	8	,	false	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	illhouse	","	��ҹ�Ţ���(��л���)	",	75	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	illvill	","	���������ҹ(��л���)	",	2	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	illtamb	","	���ʵӺ�(��л���)	",	2	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	illampu	","	���������(��л���)	",	2	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	illchan	","	���ʨѧ��Ѵ(��л���)	",	2	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	ptstat	","	��Ҿ������	",	1	,	false	,	true	,	Report18FileData.VALID_14	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	date_death	","	�ѹ�����	",	8	,	false	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	complica	","	���˵ء�û���	",	3	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	organism	","	��Դ�ͧ�����ä	",	3	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	d_update	","	�ѹ����Ѻ��ا������	",	14	,	false	,	true	,	Report18FileData.VALID_N13	,	DBFField.FIELD_TYPE_C	)
        };
        return theRPF;
    }

    
}
