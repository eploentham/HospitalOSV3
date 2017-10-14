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
public class ChronicPp53 extends Rp18OI2{

    /**����ʶҹ��ԡ��*/
    public String  pcucode = "";
    /**�Ţ���ѵû�ЪҪ�*/
    public String  cid = "";
    /**���� �ؤ��*/
    public String  pid = "";
    /**�ѹ����ԹԨ��¤����á*/
    public String  datedx = "";
    /**�����Թԩ���ä������ѧ*/
    public String  chronic = "";
    /**�ѹ����˹���*/
    public String  datedis = "";
    /**��������è�˹��� ����ʶҹТͧ�����·���Һ����ѧ�ش*/
    public String  typedis = "";
    /**�ѹ��͹�շ���Ѻ��ا*/
    public String  update = "";
    /** Creates a new instance of AncNh */
    public ChronicPp53() {
    }

    public String[] getValueArray() {
        return new String[]{
            pcucode	,
            cid	,
            pid	,
            datedx	,
            chronic	,
            datedis	,
            typedis	,
            update	};
    }
    public boolean setValue(ResultSet rs) throws Exception {
        ChronicPp53 p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.cid = Report18FileData.getRsString(rs,2);
        p.pid = Report18FileData.getRsString(rs,3);
        p.datedx = Report18FileData.getRsString(rs,4);
        p.chronic = Report18FileData.getRsString(rs,5);
        p.chronic =Constant.removeDot(p.chronic);
        p.datedis = Report18FileData.getRsString(rs,6);
        p.typedis = Report18FileData.getRsString(rs,7);
        p.update = Report18FileData.getRsString(rs,8);
        return true;
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_18file_pp53/18file_pp53_chronic.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    public Rp18OI2 initInstant() {
        return new ChronicPp53();
    }



    public String getFileName() {
        return "Chronic";
    }

    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
 RpField.initData("	pcucode	","	����ʶҹ��ԡ��	",	5	,	true	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	cid	","	���ʺѵû�ЪҪ�	",	13	,	true	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	pid	","	���� �ؤ��	",	13	,	true	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	datedx	","	�ѹ����ԹԨ��¤����á	",	8	,	true	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	chronic	","	�����Թԩ���ä������ѧ	",	6	,	true	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	datedis	","	�ѹ����˹���	",	8	,	true	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	typedis	","	��������è�˹��� ����ʶҹТͧ�����·���Һ����ѧ�ش	",	1	,	true	,	true	,	Report18FileData.VALID_19	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	d_update	","	�ѹ����Ѻ��ا������	",	14	,	true	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
 };
       return theRPF;
    }
}
