/*
 * IpdNh52.java
 *
 * Created on 8 กันยายน 2548, 12:05 น.
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
public class IpdNh53 extends Rp12OI2{

    /** character width 9 */
    public String hn;
    /** character width 9 */
    public String an;
    /** date width 8 */
    public String dateadm;
    /** character width 4 */
    public String timeadm;
    /** date width 8 */
    public String datedsc;
    /** character width 4 */
    public String timedsc;
    /** character width 1 */
    public String dischs;
    /** character width 1 */
    public String discht;
    /** character width 4 */
    public String warddsc;
    /** character width 2 */
    public String dept;
    /** numeric width 7 */
    public String adm_w;

    private String uuc;
    
    public IpdNh53() {
    }
    
    public Rp12OI2 initInstant() {
        return new IpdNh53();
    }

    public String[] getValueArray() {
        return new String[]{
hn	,
an	,
dateadm	,
timeadm	,
datedsc	,
timedsc	,
dischs	,
discht	,
warddsc	,
dept,
adm_w,
uuc
};
    }
    
    public boolean setValue(ResultSet rs) throws Exception {
        IpdNh53 p = this;
        p.	hn	 = Report12FileData.getRsString(rs,	1	);
        p.	an	 = Report12FileData.getRsString(rs,	2	);
        p.	dateadm	 = Report12FileData.getRsString(rs,	3	);
        p.	timeadm	 = Report12FileData.getRsString(rs,	4	);
        p.	datedsc	 = Report12FileData.getRsString(rs,	5	);
        p.	timedsc	 = Report12FileData.getRsString(rs,	6	);
        p.	dischs	 = Report12FileData.getRsString(rs,	7	);
        p.	discht	 = Report12FileData.getRsString(rs,	8	);
        p.	warddsc	 = Report12FileData.getRsString(rs,	9	);
        p.	dept	 = Report12FileData.getRsString(rs,	10	);
        p.	adm_w	 = Report12FileData.getRsString(rs,	11	);
        p.	uuc	 = Report12FileData.getRsString(rs,	12	);
        try{
            double weight = Double.parseDouble(p.adm_w);
        }
        catch(Exception e){
            p.adm_w = "0";
        }
         p.timeadm = p.timeadm.replaceAll(":","");
         p.timedsc = p.timedsc.replaceAll(":",""); 
        return true;
    }
  
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file53/12file53_ipd.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }


    public String getFileName() {
        return "IPD";
    }


    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{

        RpField.initData("	hn	","	เลขประจำตัวผู้ป่วย	",	9	,		false,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	an	","	เลข AN	",	9	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	dateadm	","	วันรับเข้า	",	8	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D	)
        ,RpField.initData("	timeadm	","	เวลารับเข้า	",	4	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	datedsc	","	วันจำหน่าย	",	8	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D	)
        ,RpField.initData("	timedsc	","	เวลาจำหน่าย	",	4	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	dischs	","	สถานภาพจำหน่าย	",	1	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	discht	","	วิธีการจำหน่าย	",	1	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	warddsc	","	ตึกที่จำหน่าย	",	4	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	dept	","	แผนกที่รักษา	",	2	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	adm_w	","	น้ำหนักแรกรับ	",	7,3	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_N	)
        ,RpField.initData("	uuc	","	การใช้สิทธิ	",	1	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        };
       
        return theRPF;
    }

}
