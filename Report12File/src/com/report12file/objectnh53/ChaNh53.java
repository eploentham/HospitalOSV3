/*
 * Cha.java
 *
 * Created on 8 �ѹ��¹ 2548, 12:02 �.
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
     *11	�����ͧ/�������� ...�ԡ��	STI01
12	�����ͧ/�������� ...�ԡ�����	
	�����������������ػ�ó�ӺѴ�ѡ���ä 	STI02
31	������������÷ҧ������ʹ������ þ.( )  ...�ԡ��	STI03
32	������������÷ҧ������ʹ������ þ.( )  ...�ԡ�����	
41	�ҷ�������ͷ���ҹ ...�ԡ��	STI04_OH
42	�ҷ�������ͷ���ҹ ...�ԡ�����	
51	�Ǫ�ѳ����������� ...�ԡ��	STI05
52	�Ǫ�ѳ����������� ...�ԡ�����	
61	��ԡ�����Ե�����ǹ��Сͺ�ͧ���Ե ...�ԡ��	STI06
62	��ԡ�����Ե�����ǹ��Сͺ�ͧ���Ե ...�ԡ�����	
71	��Ǩ�ԹԨ��·ҧ෤�Ԥ���ᾷ����о�Ҹ��Է�� ...�ԡ��	STI07
72	��Ǩ�ԹԨ��·ҧ෤�Ԥ���ᾷ����о�Ҹ��Է�� ...�ԡ�����	
81	��Ǩ�ԹԨ�������ѡ�ҷҧ�ѧ���Է��...�ԡ��	STI08
82	��Ǩ�ԹԨ�������ѡ�ҷҧ�ѧ���Է��...�ԡ�����	
91	��Ǩ�ԹԨ������Ըվ������� � ...�ԡ��	STI09
92	��Ǩ�ԹԨ������Ըվ������� � ...�ԡ�����	
A1	�ػ�ó�ͧ���������ͧ��ͷҧ���ᾷ��...�ԡ��	STI10
A2	�ػ�ó�ͧ���������ͧ��ͷҧ���ᾷ��...�ԡ�����	
B1	���ѵ���� ��к�ԡ�����ѭ�� ...�ԡ��	STI11
B2	���ѵ���� ��к�ԡ�����ѭ�� ...�ԡ�����	
C1	��Һ�ԡ�÷ҧ��þ�Һ�� ...�ԡ��	STI12
C2	��Һ�ԡ�÷ҧ��þ�Һ�� ...�ԡ�����	
D1	��ԡ�÷ҧ�ѹ����� ...�ԡ��	STI13
D2	��ԡ�÷ҧ�ѹ����� ...�ԡ�����	
E1	��ԡ�÷ҧ����Ҿ�ӺѴ ����Ǫ������鹿�...�ԡ��	STI14
E2	��ԡ�÷ҧ����Ҿ�ӺѴ ����Ǫ������鹿�...�ԡ�����	
F1	��ԡ�ýѧ���/��úӺѴ�ͧ����Сͺ�ä��Ż���� � ...�ԡ��	STI15
F2	��ԡ�ýѧ���/��úӺѴ�ͧ����Сͺ�ä��Ż���� � ...�ԡ�����	
	��ԡ����� �	STI16
I1	��Ҹ��������ؤ�ҡ���ҧ���ᾷ��...�ԡ��
I2	��Ҹ��������ؤ�ҡ���ҧ���ᾷ��...�ԡ�����

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
        RpField.initData("	hn	","	�Ţ��Шӵ�Ǽ�����	",	9	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	an	","	�Ţ AN	",	9	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	date	","	�ѹ���Դ����ѡ��	",	8	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D	)
        ,RpField.initData("	chrgitem	","	��Դ�ͧ��ԡ��	",	2	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	amount	","	�ӹǹ�Թ	",	7	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_N	)
        ,RpField.initData("	person_id	","	�Ţ��Шӵ�Ǻѵû�ЪҪ�	",	13	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	seq	","	�Ţ����Ѻ��ԡ��	",	15	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
         };
        return theRPF;
    }

}
