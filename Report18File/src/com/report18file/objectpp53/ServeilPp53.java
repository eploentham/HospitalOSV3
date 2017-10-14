package com.report18file.objectpp53;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Rp18OI2;
import com.report18file.utility.Constant;
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
public class ServeilPp53 extends Rp18OI2{

    /**รหัสสถานบริการ*/
    public String  pcucode = "";
    /** เลขที่บัตรประชาชน*/
    public String  cid = "";
    /**เลข HCIS*/
    public String  pid = "";
    /**เลข VN*/
    public String  seq = "";
    /**วันที่*/
    public String  date_serv = "";
    /** รหัสการวินิจฉัย*/
    public String  diagcode = "";
    /**รหัส 506*/
    public String  code506 = "";
    /**วันที่เริ่มป่วย*/
    public String  illdate = "";
    /**บ้านเลขที่(ขณะป่วย)*/
    public String  illhouse = "";
    /**รหัสหมู่บ้าน(ขณะป่วย)*/
    public String  illvill = "";
    /**รหัสตำบล(ขณะป่วย)*/
    public String  illtamb = "";
    /**รหัสอำเภอ(ขณะป่วย)*/
    public String  illampu = "";
    /**รหัสจังหวัด(ขณะป่วย)*/
    public String  illchan = "";
    /**สภาพผู้ป่วย*/
    public String  ptstat = "";
    /**วันที่ตาย*/
    public String  date_death = "";
    /**สาเหตุการป่วย*/
    public String  complica = "";
    /**ชนิดของเชื้อโรค*/
    public String  organism = "";
    public String d_update = "";
    /** Creates a new instance of AncNh */
    public ServeilPp53() {
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {

        String sql = IOStream.readInputDefault("config/rp_18file_pp53/18file_pp53_surveil.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }

    public Rp18OI2 initInstant() {
        return new ServeilPp53();
    }

  

    public boolean setValue(ResultSet rs) throws Exception {
        ServeilPp53 p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.cid = Report18FileData.getRsString(rs,2);
        p.pid = Report18FileData.getRsString(rs,3);
        p.seq = Report18FileData.getRsString(rs,4);
        p.date_serv = Report18FileData.getRsString(rs,5);
        p.diagcode = Report18FileData.getRsString(rs,6);
        p.diagcode = Constant.removeDot(p.diagcode);
        p.code506 = Report18FileData.getRsString(rs,7);
        p.illdate = Report18FileData.getRsString(rs,8);
        p.illhouse = Report18FileData.getRsString(rs,9);
        p.illvill = Report18FileData.getRsString(rs,10);
        p.illtamb = Report18FileData.getRsString(rs,11);
        p.illampu = Report18FileData.getRsString(rs,12);
        p.illchan = Report18FileData.getRsString(rs,13);
        p.ptstat = Report18FileData.getRsString(rs,14);
        p.date_death = Report18FileData.getRsString(rs,15);
        p.complica = Report18FileData.getRsString(rs,16);
        p.organism = Report18FileData.getRsString(rs,17);
        p.d_update = Report18FileData.getRsString(rs,18);
        return true;
    }
    public String[] getValueArray() {
        return new String[]{
            pcucode	,
            cid	,
            pid	,
            seq	,
            date_serv	,
            diagcode	,
            code506	,
            illdate	,
            illhouse	,
            illvill	,
            illtamb	,
            illampu	,
            illchan	,
            ptstat	,
            date_death	,
            complica	,
            organism	,
            d_update
        };
    }

    public String getFileName() {
        return "Surveil";
    }

    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
 RpField.initData("	pcucode	","	รหัสสถานบริการ	",	5	,	false	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	cid	","	รหัสบัตรประชาชน	",	13	,	false	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	pid	","	รหัส บุคคล	",	13	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	seq	","	ลำดับที่	",	16	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	date_serv	","	วันที่	",	8	,	false	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	diagcode	","	รหัสการวินิจฉัย	",	6	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	code506	","	รหัส506	",	2	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	illdate	","	วันที่เริ่มป่วย	",	8	,	false	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	illhouse	","	บ้านเลขที่(ขณะป่วย)	",	75	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	illvill	","	รหัสหมู่บ้าน(ขณะป่วย)	",	2	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	illtamb	","	รหัสตำบล(ขณะป่วย)	",	2	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	illampu	","	รหัสอำเภอ(ขณะป่วย)	",	2	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	illchan	","	รหัสจังหวัด(ขณะป่วย)	",	2	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	ptstat	","	สภาพผู้ป่วย	",	1	,	false	,	true	,	Report18FileData.VALID_14	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	date_death	","	วันที่ตาย	",	8	,	false	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	complica	","	สาเหตุการป่วย	",	3	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	organism	","	ชนิดของเชื้อโรค	",	3	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	d_update	","	วันที่ปรับปรุงข้อมูล	",	14	,	false	,	true	,	Report18FileData.VALID_N13	,	DBFField.FIELD_TYPE_C	)
        };
        return theRPF;
    }

    
}
