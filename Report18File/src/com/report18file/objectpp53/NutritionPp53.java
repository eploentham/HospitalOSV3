package com.report18file.objectpp53;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Rp18OI2;
import com.report18file.utility.Report18FileData;
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
 * and open the template in the editor.
 */

/**
 *
 * @author henbe
 */
public class NutritionPp53 extends Rp18OI2 {

    public String pcucode = "";
    public String pid = "";
    public String seq = "";
    public String date_serv = "";
    public String agemonth = "";
    public String weight = "";
    public String height = "";
    public String nlevel = "";
    private String d_update = "";

    /** Creates a new instance of AncNh */
    public NutritionPp53() {
    }

    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {

        String sql = IOStream.readInputDefault("config/rp_18file_pp53/18file_pp53_nutrition.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        pm.setString(3,startDate);
        pm.setString(4,endDate);
        pm.setString(5,startDate);
        pm.setString(6,endDate);
        pm.setString(7,startDate);
        pm.setString(8,endDate);
        pm.setString(9,startDate);
        pm.setString(10,endDate);
        return pm;
    }

    public Rp18OI2 initInstant() {
        return new NutritionPp53();
    }

    public boolean setValue(ResultSet rs) throws Exception {
        NutritionPp53 p = this;
        p.pcucode = Report18FileData.getRsString(rs, 1);
        p.pid = Report18FileData.getRsString(rs, 2);
        p.seq = Report18FileData.getRsString(rs, 3);
        p.date_serv = Report18FileData.getRsString(rs, 4);
        p.agemonth = Report18FileData.getRsString(rs, 5);
        p.weight = Report18FileData.getRsString(rs, 6);
        if(p.weight.length()!=0 && p.weight.indexOf('.')==-1)
            p.weight += ".00";
        else if(p.weight.indexOf('.') == p.weight.length()-2)
        {
            p.weight += "0";
        }
        p.height = Report18FileData.getRsString(rs,7);
        if(p.height.length()!=0 && p.height.indexOf('.')==-1)
            p.height += ".00";
        else if(p.height.indexOf('.') == p.height.length()-2)
        {
            p.height += "0";
        }
        p.nlevel = Report18FileData.getRsString(rs, 8);
        p.d_update = Report18FileData.getRsString(rs, 9);
        return true;
    }

    public String getFileName() {
        return "NUTRI";
    }

    public String[] getValueArray() {
        return new String[]{
                    pcucode,
                    pid,
                    seq,
                    date_serv,
                    agemonth,
                    weight,
                    height,
                    nlevel,
                    d_update
                };
    }

    public RpField[] getRpFields() {
        if (theRPF != null) {
            return theRPF;
        }
        theRPF = new RpField[9];
        theRPF[0] = RpField.initData("pcucode", "รหัสสถานบริการ", 5, true, false, Report18FileData.VALID_ALL, DBFField.FIELD_TYPE_C);
        theRPF[1] = RpField.initData("cid", "รหัสบัตรประชาชน", 13, true, true, Report18FileData.VALID_ALL, DBFField.FIELD_TYPE_C);
        theRPF[2] = RpField.initData("seq", "ลำดับที่", 16, true, false, Report18FileData.VALID_ALL, DBFField.FIELD_TYPE_C);
        theRPF[3] = RpField.initData("date_serv", "วันที่", 8, true, true, Report18FileData.VALID_ALL, DBFField.FIELD_TYPE_C);
        theRPF[4] = RpField.initData("agemonth", "อายุขณะชั่งน้ำหนัก(เดือน)", 5, false, false, Report18FileData.VALID_ALL, DBFField.FIELD_TYPE_C);
        theRPF[5] = RpField.initData("weight", "น้ำหนัก(กิโลกรัม)", 6, true, false, Report18FileData.VALID_ALL, DBFField.FIELD_TYPE_C);
        theRPF[6] = RpField.initData("height", "ส่วนสูง(ซม.)", 6, true, false, Report18FileData.VALID_ALL, DBFField.FIELD_TYPE_C);
        theRPF[7] = RpField.initData("nlevel", "ระดับโภชนาการ", 1, false, true, Report18FileData.VALID_N13, DBFField.FIELD_TYPE_C);
        theRPF[8] = RpField.initData("d_update", "วันที่ปรับปรุงข้อมูล", 14, false, true, Report18FileData.VALID_N13, DBFField.FIELD_TYPE_C);
        return theRPF;
    }
}
