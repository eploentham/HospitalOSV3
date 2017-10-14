/*
 * Idx.java
 *
 * Created on 8 กันยายน 2548, 12:03 น.
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
public class IdxNh53 extends Rp12OI2{
    /** character width 9 */
    public String an;
    /** character width 5 */
    public String diag;
    /** character width 1 */
    public String dxtype;
    /** character width 6 */
    public String drdx;
    
    public IdxNh53() {
    }
    
  
    public Rp12OI2 initInstant() {
        return new IdxNh53();
    }

    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file53/12file53_idx.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    public boolean setValue(ResultSet rs) throws Exception {
        IdxNh53 p = this;
	p.	an	 = Report12FileData.getRsString(rs,	1	);
	p.	diag	 = Report12FileData.getRsString(rs,	2	);
	p.	dxtype	 = Report12FileData.getRsString(rs,	3	);
	p.	drdx	 = Report12FileData.getRsString(rs,	4	);
            return true;
    }


    public String getFileName() {
        return "IDX";
    }


    public String[] getValueArray() {
        return new String[]{
an	,
diag	,
dxtype	,
drdx
};
    }


    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
        RpField.initData("	an	","	เลข AN	",	9	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	diag	","	รหัสวินิจฉัยโรค	",	5	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	dxtype	","	ชนิดของโรค	",	1	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	drdx	","	เลขที่ใบประกอบวิชาชีพ	",	6	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        };
        return theRPF;
    }

}
