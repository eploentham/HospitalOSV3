/*
 * Aer.java
 *
 * Created on 8 �ѹ��¹ 2548, 12:01 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.objectnh53;

import com.linuxense.javadbf.DBFField;
import com.report12file.object.Rp12OI2;
import com.report12file.utility.Report12FileData;
import com.reportcenter.object.RpField;
import com.reportcenter.utility.IOStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author tong(Padungrat)
 */
public class AerNh53 extends Rp12OI2{
    
    public String hn;
    public String an;
    public String dateopd;
    public String authae;
    public String aedate;
    public String aetime;
    public String aetype;
    public String refmaini;
    public String ireftype;
    public String refmaino;
    public String oreftype;
    public String ucae;
    public String emtype;
    public String seq;

    /** Creates a new instance of Aer */
    public AerNh53() {
    }

    public String[] getValueArray() {
        return new String[]{
            hn,
            an,
            dateopd,
            authae,
            aedate,
            aetime,
            aetype,
            refmaini,
            ireftype,
            refmaino,
            oreftype,
            ucae,
            emtype,
            seq
        };
    }
    public String getFileName() {
        return "AER";
    }
    public Rp12OI2 initInstant() {
        return new AerNh53();
    }

    public boolean setValue(ResultSet rs) throws Exception {
            hn	 = Report12FileData.getRsString(rs,	1	);
            an	 = Report12FileData.getRsString(rs,	2	);
            dateopd	 = Report12FileData.getRsString(rs,	3	);
            authae	 = Report12FileData.getRsString(rs,	4	);
            aedate	 = Report12FileData.getRsString(rs,	5	);
            aetime	 = Report12FileData.getRsString(rs,	6	);
            aetype	 = Report12FileData.getRsString(rs,	7	);
            refmaini	 = Report12FileData.getRsString(rs,	8	);
            ireftype	 = Report12FileData.getRsString(rs,	9	);
            refmaino	 = Report12FileData.getRsString(rs,	10	);
            oreftype	 = Report12FileData.getRsString(rs,	11	);
            ucae	 = Report12FileData.getRsString(rs,	12	);
            emtype	 = Report12FileData.getRsString(rs,	13	);
            seq	 = Report12FileData.getRsString(rs,	14	);
        
        return true;
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file53/12file53_aer.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }


    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
        RpField.initData("	hn	","	�Ţ��Шӵ�Ǽ�����	",	9	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	an	","	�Ţ AN	",	9	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	dateopd	","	�ѹ����Ѻ��ԡ��	",	8	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D	)
        ,RpField.initData("	authae	","	Claim code	",	12	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	aedate	","	�ѹ����Դ�غѵ��˵�	",	8	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D	)
        ,RpField.initData("	aetime	","	���ҷ���Դ�غѵ��˵�	",	4	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	aetype	","	�Է�ԡ���ѡ�����	",	1	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	refmaini	","	�ç��Һ�ŷ������	",	5	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	ireftype	","	�ѵ�ػ��ʧ�����Ѻ Refer	",	4	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	refmaino	","	�ç��Һ���觵��	",	5	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	oreftype	","	�ѵ�ػ��ʧ�����觵��	",	4	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	ucae	","	�����غѵ��˵�/�ء�Թ	",	1	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	emtype	","	���ʢ�ͺ觪��óթء�Թ	",	1	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	seq	","	�Ţ����Ѻ��ԡ��	",	15	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        };
        return theRPF;
    }

}
