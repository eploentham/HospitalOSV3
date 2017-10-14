/*
 * Odx.java
 *
 * Created on 8 กันยายน 2548, 12:06 น.
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
public class OdxNh53 extends Rp12OI2{

    /** character width 9 */
    public String hn;
    /** date width 8 */
    public String datedx;
    /** character width 4 */
    public String clinic;
    /** character width 5 */
    public String diag;
    /** character width 1 */
    public String dxtype;
    /** character width 6 */
    public String drdx;
    public String pid;
    
    public String seq;
    public OdxNh53() {
    }

    public String[] getValueArray() {
        return new String[]{
            hn	,
            datedx	,
            clinic	,
            diag	,
            dxtype	,
            drdx	,
            pid,
            seq
        };
    }
    
    
    public boolean setValue(ResultSet rs) throws Exception {
        OdxNh53 p = this;
        p.	hn	 = Report12FileData.getRsString(rs,	1	);
        p.	datedx	 = Report12FileData.getRsString(rs,	2	);
        p.	clinic	 = Report12FileData.getRsString(rs,	3	);
        p.	diag	 = Report12FileData.getRsString(rs,	4	);
        p.	dxtype	 = Report12FileData.getRsString(rs,	5	);
        p.	drdx	 = Report12FileData.getRsString(rs,	6	);
        p.	pid	 = Report12FileData.getRsString(rs,	7	);
        p.	seq	 = Report12FileData.getRsString(rs,	8	);
        return true;
    }
    
    public Rp12OI2 initInstant() {
        return new OdxNh53();
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file53/12file53_odx.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate); 
        return pm;
    }


    public String getFileName() {
        return "ODX";
    }


    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
        RpField.initData("	hn	","	เลขประจำตัวผู้ป่วย	",	9	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	datedx	","	วันที่วินิจฉัยโรค	",	8	,	true	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D	)
        ,RpField.initData("	clinic	","	รหัสคลินิกที่ให้บริการ	",	4	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	diag	","	รหัสวินิจฉัยโรค	",	6	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	dxtype	","	ชนิดของโรค	",	1	,	true	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	drdx	","	เลขที่ใบประกอบวิชาชีพ	",	6	,	false	, false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	person_id	","	เลขประจำตัวบัตรประชาชน	",	13	,	true	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	seq	","	เลขการรับบริการ	",	15	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        };
        return theRPF;
    }
/*
    */
}
