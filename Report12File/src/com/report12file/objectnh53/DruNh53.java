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
 * Created on 11 กันยายน 2551, 15:17 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.                                                                                                            การตรวจสอบ
 HCODE                  Text           5           0       รหสสถานพยาบาล (Left justified)
 HN                     Text           9           0       หมายเลขประจาตวผ?รับบริการ ควรใช?หมายเลข
                                                           เดมให?นานกว?า 5 ป? (Left justified)
 AN                     Text           9           0       หมายเลขประจาตวผ?ป?วยใน ไม?ควรใช?หมายเลขนี้
                                                           ซ้ํา (Left justified)
 CLINIC                 Text           4           0                                                             Y
                                                           รหัสคลินิกที่รับบริการ (ตามสนย.) (Left justified)
 PERSON_ID              Text          13           0       รหัสประจําตัวประชาชน ตามสํานักทะเบียนราษฏร?           Y
 DATE_SERV              Date           8           0       วันที่ที่รับบริการ บันทึก ป?ในค?าเป?น ค.ศ.            Y
 DID                    Text          30           0       รหัสยาเก?าตามระบบเดิมที่เก็บไว?                       N
 DIDNAME                Text         255           0       ชื่อยาที่ใช?อยู?ป?จจุบันสัมพันธ?กับ DID               N
 AMOUNT                 Text          12           0       จํานวนยาที่จ?าย                                       Y
 DRUGPRIC               Text          14           0       ราคาขาย                                               Y
 DRUGCOST               Text          14           0       ราคาทน    ุ                                           Y
 DIDSTD                 Text          24           0       รหสยามาตรฐาน 24 หลัก
                                                                   ั                                             Y
 UNIT                   Text          20           0       หน?วยนับของยาที่ใชในการจ?ายยา                         Y
 UNIT_PACKING           Text          20           0       ขนาดบรรจุต?อหน?วยนับ                                  Y
 SEQ                    Text          15           0       รหัสการบริการที่กําหนดโดยโปรแกรมเรียงลําดับ           N
                                                           ไม?ซ้ํากัน (ถ?ามจะต?องมีทุกแฟ?มไฟล?ที่เกี่ยวข?อง
                                                                               ี
                                                           กับผู?ป?วยนอก : OPD)
หมายเหตุ กรณีที่ส?งเป?น Text File FIELD TYPE ที่เป?น Date ต?องปรับเป?น Text (YYYYMMDD) และเป?น ค.ศ.

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
     * ปรับแก้ฟิลด์ date_serv ให้มีการเก็บค่าแบบ Date
     * @return
     */
    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
        RpField.initData("	hcode	","	รหัสสถานบริการ	",	5	,	true,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	hn	","	เลขผู้รับบริการ	",	9	,	true,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	an	","	เลขผู้ป่วยใน	",	9	,	false,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	clinic	","	รหัสคลินิก	",	4	,	true,true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	person_id	","	เลขบัตรประชาชน	",	13	,	true,true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	date_serv	","	วันที่รับบริการ	",	8	,	true,true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D	)
        ,RpField.initData("	did	","	รหัสยา	",	30	,	false,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	dname	","	จำนวน	",	255	,	false,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	amount	","	ราคาขาย	",	12	,	true,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	drugpric	","	ราคาทุน	",	14	,	true,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	drugcost	","	ชื่อยาเก่า	",	14	,	true,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	didstd	","	รหัสยา 24 หลัก	",	24	,	true,true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	unit	","	หน่วย	",	20	,	false,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	unit_packing	","	ขนาดต่อหน่วยนับ	",	20	,	false,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	seq	","	รหัสการบริการ	",	15	,	false,false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        };
        return theRPF;
    }
}
