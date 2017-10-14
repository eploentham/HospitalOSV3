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
public class EpiPp53 extends Rp18OI2{

    public String  pcucode = "";
    public String  pid = "";
    public String  seq = "";
    public String  date_serv = "";
    public String  vcctype = "";
    public String  vccplace = "";
    private String d_update;
    
    /** Creates a new instance of AncNh */
    public EpiPp53() {
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {

        String sql = IOStream.readInputDefault("config/rp_18file_pp53/18file_pp53_epi.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        pm.setString(3,startDate);
        pm.setString(4,endDate);
        pm.setString(5,startDate);
        pm.setString(6,endDate);
        pm.setString(7,startDate);
        pm.setString(8,endDate);
        return pm;
    }
    public Rp18OI2 initInstant() {
        return new EpiPp53();
    }
    public String getFileName() {
        return "EPI";
    }
//henbe comment 100253 kong ใครให้แป้งแก้แล้วไปแก้ใน datadict เอกสาร usecase แล้วหรือยัง
    public RpField[] getRpFields() {

        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[7];
        theRPF[0] = RpField.initData("pcucode","รหัสสถานบริการ",5,true,false,DBFField.FIELD_TYPE_C);
        theRPF[1] = RpField.initData("cid","รหัสบัตรประชาชน",13,true,true,Report18FileData.VALID_ALL,DBFField.FIELD_TYPE_C	);
        theRPF[2] = RpField.initData("seq","ลำดับที่",16,true,false,DBFField.FIELD_TYPE_C);
        theRPF[3] = RpField.initData("date_serv","วันที่",8,true,true,DBFField.FIELD_TYPE_C);
        theRPF[4] = RpField.initData("vcctype","รหัสวัคซีน",3,true,false,DBFField.FIELD_TYPE_C);
        theRPF[5] = RpField.initData("vccplace","สถานที่ฉีดวัคซีน",5 ,true,false ,DBFField.FIELD_TYPE_C);
        theRPF[6] = RpField.initData("d_update","วันที่ปรับปรุงข้อมูล",14 ,false,true ,DBFField.FIELD_TYPE_C);
        return theRPF;
    }

    public String[] getValueArray() {
        return new String[]{
pcucode	,
pid	,
seq	,
date_serv	,
vcctype	,
vccplace,
d_update
};
    }

    public boolean setValue(ResultSet rs) throws Exception {
        EpiPp53 p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.pid = Report18FileData.getRsString(rs,2);
        p.seq = Report18FileData.getRsString(rs,3);
        p.date_serv = Report18FileData.getRsString(rs,4);
        p.vcctype = Report18FileData.getRsString(rs,5);
        p.vccplace = Report18FileData.getRsString(rs,6);
        p.d_update = Report18FileData.getRsString(rs,7);
        return true;
    }
}
