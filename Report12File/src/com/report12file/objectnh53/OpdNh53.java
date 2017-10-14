/*
 * Opd.java
 *
 * Created on 8 �ѹ��¹ 2548, 12:06 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.objectnh53;
import com.linuxense.javadbf.DBFField;
import com.report12file.object.Rp12OI2;
import com.reportcenter.object.RpField;
import com.report12file.utility.Report12FileData;
import com.reportcenter.utility.IOStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author tong(Padungrat)
 */
public class OpdNh53 extends Rp12OI2{
    
    /** character width 9 */
    public String hn;
    /** character width 4 */
    public String clinic;
    /** date width 8 */
    public String dateopd;
    public String pid;
    public String seq;
    public String uuc;
    private String timeopd;
    private String idtype;
    
    public OpdNh53() {
    }
    public String[] getValueArray() {
        return new String[]{
hn	,
clinic	,
dateopd	,
timeopd	,//new
seq,
uuc
};
    }
    
    public boolean setValue(ResultSet rs) throws Exception {
        OpdNh53 p = this;
	p.	hn	 = Report12FileData.getRsString(rs,	1	);
	p.	clinic	 = Report12FileData.getRsString(rs,	2	);
	p.	dateopd	 = Report12FileData.getRsString(rs,	3	);
	p.	pid	 = Report12FileData.getRsString(rs,	4	);
	p.	seq	 = Report12FileData.getRsString(rs,	5	);
        p.	timeopd	 = Report12FileData.getRsString(rs,	6	);
        p.	uuc	 = Report12FileData.getRsString(rs,	7	);
         
        return true;
    }
    
    public Rp12OI2 initInstant() {
        return new OpdNh53();
    }

    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file53/12file53_opd.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }



    public String getFileName() {
        return "OPD";
    }


    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
         RpField.initData("	hn	","	�Ţ��Шӵ�Ǽ�����	",	9	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	clinic	","	���ʤ�Թԡ�������ԡ��	",	5	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	dateopd	","	�ѹ����Ѻ��ԡ��	",	8	,	true	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D	)
        ,RpField.initData("	timeopd	","	�����Ѻ��ԡ��	",	4	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	seq	","	�Ţ����Ѻ��ԡ��	",	15	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	uuc	","	������Է��	",	1	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        };
        return theRPF;
    }

}
