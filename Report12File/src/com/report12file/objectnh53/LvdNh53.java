/*
 * Cha.java
 *
 * Created on 8 กันยายน 2548, 12:02 น.
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
 *                                                                                                       การตรวจสอบ
 SEQLVD             Text          3        0          ลําดับของการลากลับบ?าน                                N
 AN                 Text          9        0          หมายเลขประจาตวผ?ป?วยใน ไม?ควรใช?หมายเลขนี้ซ้ํา
                                                                       ํ ั ู                                N
                                                      (Left justified)
  DATEOUT           Date          8        0          วันเดือนป?ที่ลากลับบ?าน บันทึก ป?ในค?า ค.ศ.           N
  TIMEOUT           Text          4        0          เวลาสิ้นสุด บันทึกเป?น ชวโมง นาที ตามนาฬกาในระบบ
                                                                                ่ั                ิ         N
                                                      คอมพิวเตอร?
  DATEIN            Date          8        0          วันเดือนป??ที่ลากลับบ?าน บันทึก ป?ในค?า คศ.           N
  TIMEIN            Text          4        0          เวลาเริ่ม บันทึกเป?น ชวโมง นาที ตามนาฬกาในระบบ
                                                                             ่ั                 ิ           N
                                                      คอมพิวเตอร?
 QTYDAY             Text          3        0          หน?วย เป?นจํานวนวัน ในการลากลับบ?านของผู?ป?วย         N
หมายเหตุ กรณีที่ส?งเป?น Text File FIELD TYPE ที่เป?น Date ต?องปรับเป?น Text (YYYYMMDD) และเป?น ค.ศ.

 * @author tong(Padungrat)
 */
public class LvdNh53 extends Rp12OI2{

    public String seqlvd;
    public String an;
    public String dateout;
    public String timeout;
    public String datein;
    public String timein;
    public String qtyday;

    public LvdNh53() {
    }
    
    /**
     */
    public String[] getValueArray() {
        return new String[]{
            seqlvd,
            an,
            dateout,
            timeout,
            datein,
            timein,
            qtyday
        };
    }

    
    public boolean setValue(ResultSet rs) throws Exception {
        LvdNh53 p = this;
        p.	seqlvd	 = Report12FileData.getRsString(rs,	1	);
        p.	an	 = Report12FileData.getRsString(rs,	2	);
        p.	dateout	 = Report12FileData.getRsString(rs,	3	);
        p.	timeout	 = Report12FileData.getRsString(rs,	4	);
        p.	datein	 = Report12FileData.getRsString(rs,	5	);
        p.	timein	 = Report12FileData.getRsString(rs,	6	);
        p.	qtyday	 = Report12FileData.getRsString(rs,	7	);
        return true;
    }
    public Rp12OI2 initInstant() {
        return new LvdNh53();
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file53/12file53_lvd.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    public String getFileName() {
        return "LVD";
    }
    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
        RpField.initData("	seqlvd	","	ลำดับการลากลับบ้าน	",	3	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	an	","	เลข AN	",	9	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	dateout	","	วันที่ลากลับบ้าน ค.ศ.	",	8	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D	)
        ,RpField.initData("	timeout	","	เวลาที่กลับ	",	4	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	datein	","	วันที่กลับมา ค.ศ	",	8	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D	)
        ,RpField.initData("	timein	","	เวลาที่มา	",	4	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	qtyday	","	จำนวนวัน	",	3	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
         };
        return theRPF;
    }

}
