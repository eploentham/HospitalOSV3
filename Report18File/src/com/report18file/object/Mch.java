/*
 * Mch.java
 *
 * Created on 1 สิงหาคม 2548, 10:06 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.object;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.ConnectionDBMgr;
import com.linuxense.javadbf.DBFField;
import com.report18file.utility.Constant;
import com.report18file.utility.Report18FileData;
import com.reportcenter.utility.IOStream;
import com.reportcenter.utility.StringDate;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Noom
 */
public class Mch  implements Rp18OI{
    /**สถานพยาบาล*/
    public String  pcucode = "";
    /**เลข HCIS*/
    public String  pid = "";
    /**ครรภ์ที่*/
    public String  gravida = "";
    /**วันแรกของการมีประจำเดือนครั้งท้าย*/
    public String  lmp = "";
    /**วันที่กำหนดคลอด*/
    public String  edc = "";
    /**ผลการตรวจ  VDRL_RS*/
    public String  vdrl_rs = "";
    /**ผลการตรวจ  HB_RS*/
    public String  hb_rs = "";
    /** ผลการตรวจ  HIV_RS*/
    public String  hiv_rs = "";
    /** วันที่ตรวจ HCT.*/
    public String  datehct = "";
    /**ผลการตรวจ  HCT*/
    public String  hct_rs = "";
    /** ผลการตรวจ THALASSAEMIA*/
    public String  thalass = "";
    /** ตรวจสุขภาพฟันและแนะนำ(หรือไม่)*/
    public String  dental = "";
    /** ฟันผุ(จำนวน)*/
    public String  tcaries = "";
    /**หินน้ำลาย(มีหรือไม่)*/
    public String  tartar = "";
    /** เหงือกอักเสบ(มีหรือไม่)*/
    public String  guminf = "";
    /**วันคลอด / วันสิ้นสุดการตั้งครรภ์*/
    public String  bdate = "";
    /**ผลสิ้นสุดการตั้งครรภ์*/
    public String  bresult = "";
    /**สถานที่คลอด*/
    public String  bplace = "";
    /**รหัสสถานพยาบาลที่คลอด*/
    public String  bhosp = "";
    /**วิธีการคลอด / สิ้นสุดการตั้งครรภ์*/
    public String  btype = "";
    /** ประเภทของผู้ทำคลอด*/
    public String  bdoctor = "";
    /**จำนวนเกิดมีชีพ*/
    public String  lborn  = "";
    /**จำนวนตายคลอด*/
    public String  sborn = "";
    /** วันที่ดูแลแม่ครั้งที่ 1*/
    public String  ppcare1 = "";
    /**วันที่ดูแลแม่ครั้งที่ 2*/
    public String  ppcare2 = "";
    /**วันที่ดูแลแม่ครั้งที่ 3*/
    public String  ppcare3 = "";
    /**ผลการตรวจมารดาหลังคลอด*/
    public String  ppres = "";
    /**วันเดือนปีที่ปรับปรุง*/
    public String  update = "";
    
    public String patient_id = "";
    
    private String d_update;

    protected ConnectionInf theConnectionInf;
    
    /** Creates a new instance of Mch */
    public Mch() {
    }
    public void setConnectionInf(ConnectionInf con){
        theConnectionInf = con;
    }
    public String[] getHeaderArray() {
        return new String[]{
            "pcucode",
            "pid",
            "gravida",
            "lmp",
            "edc",
            "vdrl_rs",
            "hb_rs",
            "hiv_rs",
            "datehct",
            "hct_rs",
            "thalass",
            "dental",
            "tcaries",
            "tartar",
            "guminf",
            "bdate",
            "bresult",
            "bplace",
            "bhosp",
            "btype",
            "bdoctor",
            "lborn",
            "sborn",
            "ppcare1",
            "ppcare2",
            "ppcare3",
            "ppres",
            "d_update"
                    
        };
    }
    
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];        
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(10,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(11,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(12,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(13,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(14,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(15,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(16,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(17,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(18,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(19,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(20,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(21,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(22,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(23,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(24,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(25,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(26,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(27,header,fields,DBFField.FIELD_TYPE_C,8);
        return fields;
    }
    public String[] getWarningArray() {
        return new String[]{
"สถานพยาบาล",
"เลข HCIS",
"ครรภ์ที่",
"วันแรกของการมีประจำเดือนครั้งสุดท้าย",
"วันที่กำหนดคลอด",
"ผลการตรวจ  VDRL_RS",
"ผลการตรวจ  HB_RS",
"ผลการตรวจ  HIV_RS",
"วันที่ตรวจ HCT.",
"ผลการตรวจ  HCT",
"ผลการตรวจ THALASSAEMIA",
"ตรวจสุขภาพฟันและแนะนำ(หรือไม่)",
"ฟันผุ(จำนวน)",
"หินน้ำลาย (มีหรือไม่)",
"เหงือกอักเสบ(มีหรือไม่)",
"วันคลอด / วันสิ้นสุดการตั้งครรภ์",
"ผลสิ้นสุดการตั้งครรภ์",
"สถานที่คลอด",
"รหัสสถานพยาบาลที่คลอด",
"วิธีการคลอด / สิ้นสุดการตั้งครรภ์",
"ประเภทของผู้ทำคลอด",
"จำนวนเกิดมีชีพ",
"จำนวนตายคลอด",
"วันที่ดูแลแม่ครั้งที่ 1",
"วันที่ดูแลแม่ครั้งที่ 2",
"วันที่ดูแลแม่ครั้งที่ 3",
"ผลการตรวจมารดาหลังคลอด",
"วันเดือนปีที่ปรับปรุง"
        };
    }
    public String[] getValueArray() {
        return new String[]{
            pcucode	,
            pid	,
            gravida	,
            lmp	,
            edc	,
            vdrl_rs	,
            hb_rs	,
            hiv_rs	,
            datehct	,
            hct_rs	,
            thalass	,
            dental	,
            tcaries	,
            tartar	,
            guminf	,
            bdate	,
            bresult	,
            bplace	,
            bhosp	,
            btype	,
            bdoctor	,
            lborn	,
            sborn	,
            ppcare1	,
            ppcare2	,
            ppcare3	,
            ppres	,
            update
        };
    }
    
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) {
        BufferedReader in;
        
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode 	,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid 	,	13	,	true,false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	gravida 	,	2	,	true,false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	lmp	,	8	,	false,true	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	edc	,	8	,	false,true	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	vdrl_rs	,	1	,	false,true,Report18FileData.VALID_12_89	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	hb_rs	,	1	,	false,true,Report18FileData.VALID_12_89	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	hiv_rs	,	1	,	false,true,Report18FileData.VALID_12_89	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	datehct	,	8	,	false,true	)) { 	error[9	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	hct_rs	,	2	,	false	)) { 	error[10	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	thalass	,	1	,	false,true,Report18FileData.VALID_12_89	)) { 	error[11	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	dental	,	1	,	false,true,Report18FileData.VALID_01	)) { 	error[12	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	tcaries	,	2	,	false	)) { 	error[13	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	tartar	,	1	,	false,true,Report18FileData.VALID_01_8	)) { 	error[14	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	guminf	,	1	,	false,true,Report18FileData.VALID_01_8	)) { 	error[15	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bdate	,	8	,	false,true	)) { 	error[16	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bresult	,	5	,	false	)) { 	error[17	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bplace	,	1	,	false,true,Report18FileData.VALID_15	)) { 	error[18	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bhosp	,	5	,	false	)) { 	error[19	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	btype	,	1	,	false,true,Report18FileData.VALID_16	)) { 	error[20	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bdoctor	,	1	,	false,true,Report18FileData.VALID_15	)) { 	error[21	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	lborn	,	1	,	false	)) { 	error[22	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	sborn	,	1	,	false	)) { 	error[23	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ppcare1	,	8	,	true,true	)) { 	error[24	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ppcare2	,	8	,	false,true	)) { 	error[25	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ppcare3	,	8	,	false,true	)) { 	error[26	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ppres	,	1	,	false,true,Report18FileData.VALID_12	)) { 	error[27	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	update	,	8	,	true	)) { 	error[28	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
                sb.append("\n\tรายการที่ผิดพลาดคือ เลข HCIS:"+ pid);
        }
        return ret;
    }
    
    public boolean setValue(ResultSet rs) throws Exception {
        Mch p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.pid = Report18FileData.getRsString(rs,2);
        p.gravida = Report18FileData.getRsString(rs,3);
        if(p.gravida.length()==1) p.gravida = "0"+p.gravida;
        p.lmp = Report18FileData.getRsString(rs,4);
        p.edc = Report18FileData.getRsString(rs,5);
        p.vdrl_rs = Report18FileData.getRsString(rs,6);
        p.hb_rs = Report18FileData.getRsString(rs,7);
        p.hiv_rs = Report18FileData.getRsString(rs,8);
        p.datehct = Report18FileData.getRsString(rs,9);
        p.hct_rs = Report18FileData.getRsString(rs,10);
        p.thalass = Report18FileData.getRsString(rs,11);
        p.dental = Report18FileData.getRsString(rs,12);
        p.tcaries = Report18FileData.getRsString(rs,13);
        p.tartar = Report18FileData.getRsString(rs,14);
        p.guminf = Report18FileData.getRsString(rs,15);
        p.bdate = Report18FileData.getRsString(rs,16);
        p.bresult = Report18FileData.getRsString(rs,17);
        p.bresult = Constant.removeDot(p.bresult);
        p.bplace = Report18FileData.getRsString(rs,18);
        if(p.bplace==null) p.bplace = "";
        p.bhosp = Report18FileData.getRsString(rs,19);
        if(p.bhosp==null) p.bhosp = "";
        p.btype = Report18FileData.getRsString(rs,20);
        p.bdoctor = Report18FileData.getRsString(rs,21);
        if(p.bdoctor==null) p.bdoctor = "";
        p.lborn = Report18FileData.getRsString(rs,22);
        p.sborn = Report18FileData.getRsString(rs,23);
        p.ppcare1 = Report18FileData.getRsString(rs,24);
        p.ppcare2 = Report18FileData.getRsString(rs,25);
        p.ppcare3 = Report18FileData.getRsString(rs,26);
//        System.out.println("pp.ppcare1" + p.ppcare1 + ";");
        if(isReadMore())
            readMorePPCare();
        p.ppres = Report18FileData.getRsString(rs,27);
        p.update = Report18FileData.getRsString(rs,28); 
        return true;
    }
    
    public boolean isReadMore(){
        Mch pp = this;
        if(pp.ppcare1.equals("") && !pp.ppcare2.equals(""))
            return true;
        if(pp.ppcare1.equals("") && !pp.ppcare3.equals(""))
            return true;
        if(pp.ppcare2.equals("") && !pp.ppcare3.equals(""))
            return true;
        if(pp.ppcare1.equals("") && !pp.ppcare2.equals(""))
            return true;
        return false;
    }
    public boolean readMorePPCare()throws Exception {
        
        Mch pp = this;
        String sql = " select to_char(to_date(to_number(" +
                " substr(t_health_postpartum.record_date_time,1,4),'9999')-543 || " +
                " substr(t_health_postpartum.record_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd') as record_date_time, " +
                " health_postpartum_visit" +
                " from t_health_postpartum " +
                " inner join t_health_family on t_health_family.t_health_family_id = t_health_postpartum.t_health_family_id" +
                " where health_family_hn_hcis = '"+pp.pid+"'"+
                " and t_health_postpartum.health_postpartum_pregnant_number = '"+pp.gravida.substring(1)+"'";
        System.out.println("_________" + sql);
         ResultSet rs = theConnectionInf.getConnection().createStatement().executeQuery(sql);
         while(rs.next()){
             String visit_time = rs.getString("record_date_time");
             String gravida =  rs.getString("health_postpartum_visit");
             if(gravida.equals("1"))
                 pp.ppcare1 = visit_time;
             else if(gravida.equals("2"))
                 pp.ppcare2 = visit_time;
             else if(gravida.equals("3"))
                 pp.ppcare3 = visit_time;
         }
         return true;
    }   
    public Rp18OI initInstant() {
        Mch mmm = new Mch();
        mmm.setConnectionInf(this.theConnectionInf);
        return mmm;
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        
            System.out.println("con==null" + con==null);
        String sql = IOStream.readInputDefault("config/rp_18file/18file_mch.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    
    public String getFileName() {
        return "MCH";
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Mch p = this;
        rowData = new Object[28];
        rowData[0] = p.pcucode;
        rowData[1] = p.pid;
        rowData[2] = p.gravida;
        rowData[3] = p.lmp;
        rowData[4] = p.edc;
        rowData[5] = p.vdrl_rs;
        rowData[6] = p.hb_rs;
        rowData[7] = p.hiv_rs;
        rowData[8] = p.datehct;
        rowData[9] = p.hct_rs;
        rowData[10] = p.thalass;
        rowData[11] = p.dental;
        rowData[12] = p.tcaries;
        rowData[13] = p.tartar;
        rowData[14] = p.guminf;
        rowData[15]  = p.bdate;
        rowData[16]  = p.bresult;
        rowData[17]  = p.bplace;
        rowData[18]  = p.bhosp;
        rowData[19]  = p.btype;
        rowData[20]  = p.bdoctor;
        rowData[21] = p.lborn;
        rowData[22] = p.sborn;
        rowData[23]  = p.ppcare1;
        rowData[24]  = p.ppcare2;
        rowData[25]  = p.ppcare3;
        rowData[26]  = p.ppres;
        rowData[27]  = p.update;
        return rowData;
    }
}
