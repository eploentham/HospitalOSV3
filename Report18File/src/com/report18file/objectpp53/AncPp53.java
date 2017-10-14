package com.report18file.objectpp53;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Rp18OI2;
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
public class AncPp53 extends Rp18OI2{

    /**����ʶҹ��ԡ��*/
    public String pcucode = "";
    /**���� �ؤ��*/
    public String pid = "";
    /**	�ӴѺ���*/
    public String seq = "";
    /**�ѹ���*/
    public String date_serv = "";
    /**����ʶҹ����Ǩ*/
    public String aplace= "";
    /**�������*/
    public String gravida= "";
    /**ANC ��ǧ���*/
    public String ancno= "";
    /**���ؤ����*/
    public String ga= "";
    /**�š�õ�Ǩ*/
    public String ancres= "";
    private String d_update;
    /** Creates a new instance of AncNh */
    public AncPp53() {
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        //config/rp_18file_nhso50/18file_nhso50_anc.sql
        String sql = IOStream.readInputDefault("config/rp_18file_pp53/18file_pp53_anc.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        pm.setString(3,startDate);
        pm.setString(4,endDate);
        pm.setString(5,startDate);
        pm.setString(6,endDate);
        return pm;
    }
    public Rp18OI2 initInstant() {
        return new AncPp53();
    }
    public String[] getValueArray() {
        return new String[]{
pcucode,
pid,
seq,
date_serv,
aplace,
gravida,
ancno,
ga,
ancres,
d_update
};
    }
    public boolean setValue(ResultSet rs) throws Exception {
        AncPp53 p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.pid = Report18FileData.getRsString(rs,2);
        p.seq = Report18FileData.getRsString(rs,3);
        p.date_serv = Report18FileData.getRsString(rs,4);
        p.aplace = Report18FileData.getRsString(rs,5);
        p.gravida = Report18FileData.getRsString(rs,6);
        p.ancno = Report18FileData.getRsString(rs,7);
        p.ga = Report18FileData.getRsString(rs,8);
        p.ancres = Report18FileData.getRsString(rs,9);
        p.d_update = Report18FileData.getRsString(rs,10);
        return true;
    }

    public String getFileName() {
        return "ANC";
    }

    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
RpField.initData("	pcucode	","	����ʶҹ��ԡ��	",	5	,	true	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	cid	","		",	13	,	true	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	seq	","	�ӴѺ���	",	16	,	true	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	date_serv	","	�ѹ���	",	8	,	true	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	aplace	","	����ʶҹ����Ǩ	",	5	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	gravida	","	�������	",	2	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	ancno	","	ANC ��ǧ���	",	1	,	false	,	true	,	Report18FileData.VALID_14	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	ga	","	���ؤ����	",	3	,	true	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	ancres	","	�š�õ�Ǩ	",	1	,	true	,	true	,	Report18FileData.VALID_12	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	d_update	","	�ѹ����Ѻ��ا������	",	14	,	false	,	true	,	Report18FileData.VALID_N13	,	DBFField.FIELD_TYPE_C	)
        };
        return theRPF;
    }
    
}
