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
 *
 * @author tong(Padungrat)
 */
public class ChaNh53 extends Rp12OI2{

    /** character width 9 */
    public String hn;
    /** character width 9 */
    public String an;
    /** date width 8 */
    public String date;
    /** chrgitem width 2 */
    public String chrgitem;
    /** numeric width 20 */
    public String amount;
    public String pid;

    public String seq;
    public String draw;
    public ChaNh53() {
    }
    
    /**
     */
    public String[] getValueArray() {
        return new String[]{
            hn	,
            an	,
            date	,
            chrgitem	,
            amount	,
            pid,
            seq
        };
    }

    
    public boolean setValue(ResultSet rs) throws Exception {
        ChaNh53 p = this;
        p.	hn	 = Report12FileData.getRsString(rs,	1	);
        p.	an	 = Report12FileData.getRsString(rs,	2	);
        p.	date	 = Report12FileData.getRsString(rs,	3	);
//        String chrg 	 = Report12FileData.getRsString(rs,	4	);
        p.	chrgitem = Report12FileData.getRsString(rs,	4	);
        p.	amount	 = Report12FileData.getRsString(rs,	5	);
        p.	pid	 = Report12FileData.getRsString(rs,	6	);
        p.	seq	 = Report12FileData.getRsString(rs,	7	);
//        String draw	 = Report12FileData.getRsString(rs,	8	);
//        p.	chrgitem = getChrgItem(chrg,draw);
        return true;
    }
    /**
     *11	ค่าห้อง/ค่าอาหาร ...เบิกได้	STI01
12	ค่าห้อง/ค่าอาหาร ...เบิกไม่ได้	
	ค่าอวัยวะเทียมและอุปกรณ์บำบัดรักษาโรค 	STI02
31	ยาและสารอาหารทางเส้นเลือดที่ใช้ใน รพ.( )  ...เบิกได้	STI03
32	ยาและสารอาหารทางเส้นเลือดที่ใช้ใน รพ.( )  ...เบิกไม่ได้	
41	ยาที่นำไปใช้ต่อที่บ้าน ...เบิกได้	STI04_OH
42	ยาที่นำไปใช้ต่อที่บ้าน ...เบิกไม่ได้	
51	เวชภัณฑ์ที่ไม่ใช่ยา ...เบิกได้	STI05
52	เวชภัณฑ์ที่ไม่ใช่ยา ...เบิกไม่ได้	
61	บริการโลหิตและส่วนประกอบของโลหิต ...เบิกได้	STI06
62	บริการโลหิตและส่วนประกอบของโลหิต ...เบิกไม่ได้	
71	ตรวจวินิจฉัยทางเทคนิคการแพทย์และพยาธิวิทยา ...เบิกได้	STI07
72	ตรวจวินิจฉัยทางเทคนิคการแพทย์และพยาธิวิทยา ...เบิกไม่ได้	
81	ตรวจวินิจฉัยและรักษาทางรังสีวิทยา...เบิกได้	STI08
82	ตรวจวินิจฉัยและรักษาทางรังสีวิทยา...เบิกไม่ได้	
91	ตรวจวินิจฉัยโดยวิธีพิเศษอื่น ๆ ...เบิกได้	STI09
92	ตรวจวินิจฉัยโดยวิธีพิเศษอื่น ๆ ...เบิกไม่ได้	
A1	อุปกรณ์ของใช้และเครื่องมือทางการแพทย์...เบิกได้	STI10
A2	อุปกรณ์ของใช้และเครื่องมือทางการแพทย์...เบิกไม่ได้	
B1	ทำหัตถการ และบริการวิสัญญี ...เบิกได้	STI11
B2	ทำหัตถการ และบริการวิสัญญี ...เบิกไม่ได้	
C1	ค่าบริการทางการพยาบาล ...เบิกได้	STI12
C2	ค่าบริการทางการพยาบาล ...เบิกไม่ได้	
D1	บริการทางทันตกรรม ...เบิกได้	STI13
D2	บริการทางทันตกรรม ...เบิกไม่ได้	
E1	บริการทางกายภาพบำบัด และเวชกรรมฟื้นฟู...เบิกได้	STI14
E2	บริการทางกายภาพบำบัด และเวชกรรมฟื้นฟู...เบิกไม่ได้	
F1	บริการฝังเข็ม/การบำบัดของผู้ประกอบโรคศิลปะอื่น ๆ ...เบิกได้	STI15
F2	บริการฝังเข็ม/การบำบัดของผู้ประกอบโรคศิลปะอื่น ๆ ...เบิกไม่ได้	
	บริการอื่น ๆ	STI16
I1	ค่าธรรมเนียมบุคลากรมทางการแพทย์...เบิกได้
I2	ค่าธรรมเนียมบุคลากรมทางการแพทย์...เบิกไม่ได้

*/
    private String getChrgItem(String chg,String draw)
    {
	if((chg.equals("1") || chg.equals("STI01")) && draw.equals("1")) return "11";
	else if((chg.equals("1") || chg.equals("STI01")) && draw.equals("0")) return "12";
	else if((chg.equals("3") || chg.equals("STI03")) && draw.equals("1")) return "31";
	else if((chg.equals("3") || chg.equals("STI03")) && draw.equals("0")) return "32";
	else if((chg.equals("4") || chg.equals("STI04_OH")) && draw.equals("1")) return "41";
	else if((chg.equals("4") || chg.equals("STI04_OH")) && draw.equals("0")) return "42";
	else if((chg.equals("5") || chg.equals("STI05")) && draw.equals("1")) return "51";
	else if((chg.equals("5") || chg.equals("STI05")) && draw.equals("0")) return "52";
	else if((chg.equals("6") || chg.equals("STI06")) && draw.equals("1")) return "61";
	else if((chg.equals("6") || chg.equals("STI06")) && draw.equals("0")) return "62";
	else if((chg.equals("7") || chg.equals("STI07")) && draw.equals("1")) return "71";
	else if((chg.equals("7") || chg.equals("STI07")) && draw.equals("0")) return "72";
	else if((chg.equals("8") || chg.equals("STI08")) && draw.equals("1")) return "81";
	else if((chg.equals("8") || chg.equals("STI08")) && draw.equals("0")) return "82";
	else if((chg.equals("9") || chg.equals("STI09")) && draw.equals("1")) return "91";
	else if((chg.equals("9") || chg.equals("STI09")) && draw.equals("0")) return "92";
	else if((chg.equals("A") || chg.equals("STI10")) && draw.equals("1")) return "A1";
	else if((chg.equals("A") || chg.equals("STI10")) && draw.equals("0")) return "A2";
	else if((chg.equals("B") || chg.equals("STI11")) && draw.equals("1")) return "B1";
	else if((chg.equals("B") || chg.equals("STI11")) && draw.equals("0")) return "B2";
	else if((chg.equals("C") || chg.equals("STI12")) && draw.equals("1")) return "C1";
	else if((chg.equals("C") || chg.equals("STI12")) && draw.equals("0")) return "C2";
	else if((chg.equals("D") || chg.equals("STI13")) && draw.equals("1")) return "D1";
	else if((chg.equals("D") || chg.equals("STI13")) && draw.equals("0")) return "D2";
	else if((chg.equals("E") || chg.equals("STI14")) && draw.equals("1")) return "E1";
	else if((chg.equals("E") || chg.equals("STI14")) && draw.equals("0")) return "E2";
	else if((chg.equals("F") || chg.equals("STI15")) && draw.equals("1")) return "F1";
	else if((chg.equals("F") || chg.equals("STI15")) && draw.equals("0")) return "F2";
	else return "";
    }
    
    public Rp12OI2 initInstant() {
        return new ChaNh53();
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file53/12file53_cha.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        pm.setString(3,startDate);
        pm.setString(4,endDate);
        return pm;
    }


    public String getFileName() {
        return "CHA";
    }


    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
        RpField.initData("	hn	","	เลขประจำตัวผู้ป่วย	",	9	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	an	","	เลข AN	",	9	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	date	","	วันที่คิดค่ารักษา	",	8	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D	)
        ,RpField.initData("	chrgitem	","	ชนิดของบริการ	",	2	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	amount	","	จำนวนเงิน	",	7	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_N	)
        ,RpField.initData("	person_id	","	เลขประจำตัวบัตรประชาชน	",	13	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	seq	","	เลขการรับบริการ	",	15	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
         };
        return theRPF;
    }

}
