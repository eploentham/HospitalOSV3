/*
 * Pp.java
 *
 * Created on 1 สิงหาคม 2548, 10:10 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.object;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.linuxense.javadbf.DBFField;
import com.report18file.utility.Report18FileData;
import com.reportcenter.utility.IOStream;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Noom
 */
public class Pp  implements Rp18OI{
    /**สถานพยาบาล*/
    public String  pcucode = "";
    /**เลข HCIS(เด็ก)*/
    public String  pid = "";
    /**รหัสบุคคล(แม่)*/
    public String  mpid = "";
    /** ครรภ์ที่*/
    public String  gravida = "";
    /**วันที่คลอด*/
    public String  bdate = "";
    /**สถานที่คลอด*/
    public String  bplace = "";
    /**รหัสสถานพยาบาลที่คลอด*/
    public String  bhosp = "";
    /** วิธีการคลอด*/
    public String  btype = "";
    /**ประเภทของผู้ทำคลอด*/
    public String  bdoctor = "";
    /**น้ำหนักแรกคลอด(กรัม)*/
    public String  bweigth = "";
    /**ภาวการณ์ขาดออกซิเจน*/
    public String  asphyxia = "";
    /**ได้รับ VIT K หรือไม่*/
    public String  vitk = "";
    /**วันที่ดูแลลูกครั้งที่ 1*/
    public String  bcare1 = "";
    /** วันที่ดูแลลูกครั้งที่ 2*/
    public String  bcare2 = "";
    /**วันที่ดูแลลูกครั้งที่ 3*/
    public String  bcare3 = "";
    /** ผลการตรวจทารกหลังคลอด*/
    public String  bcres = "";
    /**วันเดือนปีที่ปรับปรุง*/
    public String  update = "";

    protected ConnectionInf theConnectionInf;
    
    /** Creates a new instance of Pp */
    public Pp() {
    }
    public String[] getHeaderArray() {
        return new String[]{
            
            "pcucode",
            "pid",
            "mpid",
            "gravida",
            "bdate",
            "bplace",
            "bhosp",
            "btype",
            "bdoctor",
            "bweigth",
            "asphyxia",
            "vitk",
            "bcare1",
            "bcare2",
            "bcare3",
            "bcres",
            "d_update"
        };
    }
    
    public String[] getWarningArray() {
        return new String[]{
"สถานพยาบาล",
"เลข HCIS(เด็ก)",
"เลข HCIS(แม่)",
"ครรภ์ที่",
"วันที่คลอด",
"สถานที่คลอด",
"สถานพยาบาลที่คลอด",
"วิธีการคลอด",
"ประเภทของผู้ทำคลอด",
"น้ำหนักแรกคลอด(กรัม)",
"ภาวการณ์ขาดออกซิเจน",
"ได้รับ VIT K หรือไม่",
"วันที่ดูแลลูกครั้งที่ 1",
"วันที่ดูแลลูกครั้งที่ 2",
"วันที่ดูแลลูกครั้งที่ 3",
"ผลการตรวจทารกหลังคลอด",
"วันเดือนปีที่ปรับปรุง"
        };
    }
    public String[] getValueArray() {
        return new String[]{
            pcucode	,
            pid	,
            mpid	,
            gravida	,
            bdate	,
            bplace	,
            bhosp	,
            btype	,
            bdoctor	,
            bweigth	,
            asphyxia	,
            vitk	,
            bcare1	,
            bcare2	,
            bcare3	,
            bcres	,
            update	};
    }
    
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) {
        BufferedReader in;
        
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode 	,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	true,false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	mpid	,	13	,	false,false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	gravida 	,	2	,	true	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bdate	,	8	,	false,true	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bplace	,	1	,	false,true,Report18FileData.VALID_15	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bhosp	,	5	,	false	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	btype	,	1	,	false,true,Report18FileData.VALID_15	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bdoctor	,	1	,	false,true,Report18FileData.VALID_15	)) { 	error[9	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	this.bweigth	,	4	,	false	)) { 	error[10	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	asphyxia	,	1	,	false,true,Report18FileData.VALID_01	)) { 	error[11	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	vitk	,	1	,	false,true,Report18FileData.VALID_01	)) { 	error[12	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bcare1	,	8	,	true,true	)) { 	error[13	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bcare2	,	8	,	false,true	)) { 	error[14	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bcare3	,	8	,	false,true	)) { 	error[15	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bcres	,	1	,	false,true,Report18FileData.VALID_12	)) { 	error[16	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	update	,	8	,	true	)) { 	error[17	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
                sb.append("\n\tรายการที่ผิดพลาดคือ เลข HCIS แม่:"+ mpid + " เลข HCIS ลูก:"+ pid);
        }
        return ret;
    }
    
    public void setConnectionInf(ConnectionInf con){
        theConnectionInf = con;
    }
    public boolean setValue(ResultSet rs) throws Exception {
        Pp p = this;
        p.pcucode = getString(rs,1);
        p.pid = getString(rs,2);
        p.mpid = getString(rs,3);
        p.gravida = getString(rs,4);
        if(p.gravida.length()==1)
            p.gravida = "0"+p.gravida;
        p.bdate = getString(rs,5);
        p.bplace = getString(rs,6);
        p.bhosp = getString(rs,7);
        p.btype = getString(rs,8);
        p.bdoctor = getString(rs,9);
        p.bweigth = getString(rs,10);
        p.asphyxia = getString(rs,11);
        p.vitk = getString(rs,12);
        p.bcare1 = getString(rs,13);
        p.bcare2 = getString(rs,14);
        p.bcare3 = getString(rs,15);
        System.out.println("pp.bcare1" + p.bcare1 + ";");
        if(isReadMore())
            readMorePPCare();
        p.bcres = getString(rs,16);
        p.update = getString(rs,17);
        String read_mpid = getString(rs,18);
        //ถ้าเป็นการบันทึกข้อมูล Visit ของลูก เลข pid ก็เป็นของลูก
        //ถ้าเป็นการบันทึกข้อมูล Visit ของแม เลข pid ก็เป็นของลูก
        if(!pid.equals("") && !mpid.equals("") && pid.equals(mpid)) {
            p.mpid = "";
            if(!read_mpid.equals("")){
                ResultSet rs1 = theConnectionInf.eQuery("select health_family_hn_hcis from " +
                        "t_health_family where patient_pid = '"+read_mpid+"'");
                if(rs1.next())
                    p.mpid = rs1.getString(1);
            }
        }
        return true;
    }
    public static String getString(ResultSet rs,int index) throws Exception {
        String str = Report18FileData.getRsString(rs,index);
        if(str==null)
            return "";
        return str;
    }
    public Rp18OI initInstant() {
        Pp mmm = new Pp();
        mmm.setConnectionInf(this.theConnectionInf);
        return mmm;
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        
        String sql = IOStream.readInputDefault("config/rp_18file/18file_pp.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    
    public String getFileName() {
        return "PP";
    }
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_C,4);
        Report18FileData.initDBFField(10,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(11,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(12,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(13,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(14,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(15,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(16,header,fields,DBFField.FIELD_TYPE_C,8);
        
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Pp p = this;
        rowData = new Object[17];
        rowData[0] = p.pcucode;
        rowData[1] = p.pid;
        rowData[2] = p.mpid;
        rowData[3] = p.gravida;
        rowData[4] = p.bdate;
        rowData[5] = p.bplace;
        rowData[6] = p.bhosp;
        rowData[7] = p.btype;
        rowData[8] = p.bdoctor;
        rowData[9] = p.bweigth;
        rowData[10] = p.asphyxia;
        rowData[11] = p.vitk;
        rowData[12] = p.bcare1;
        rowData[13] = p.bcare2;
        rowData[14] = p.bcare3;
        rowData[15] = p.bcres;
        rowData[16] = p.update;
        return rowData;
    }
     ///////////////////////////////////////////////////////
    public boolean isReadMore(){
        if(gravida.length()<2)
            return false;
        if(bcare1.equals("") && !bcare2.equals(""))
            return true;
        if(bcare1.equals("") && !bcare3.equals(""))
            return true;
        if(bcare2.equals("") && !bcare3.equals(""))
            return true;
        if(bcare1.equals("") && !bcare2.equals(""))
            return true;
        return false;
    }
    public boolean readMorePPCare()throws Exception {
        String sql = " select to_char(to_date(to_number(" +
                " substr(pp_care_record_date_time,1,4),'9999')-543 || " +
                " substr(pp_care_record_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd') as visit_begin_visit_time, " +
                " pp_care_number" +
                " from t_health_pp_care " +
                " inner join t_visit on t_visit.t_visit_id = t_health_pp_care.t_visit_id" +
                " inner join t_health_family on t_health_family.t_health_family_id = t_health_pp_care.t_health_family_id" +          
                " inner JOIN t_health_pp ON " +
                "   (t_health_pp.t_health_family_id = t_health_pp_care.t_health_family_id " +
                "   and t_health_pp.pp_active  = '1')"+
                " where health_family_hn_hcis = '"+ pid +"'"+
                " and t_health_pp.pp_gravida = '"+ gravida.substring(1) +"'";
        System.out.println("_________" + sql);
         ResultSet rs = theConnectionInf.getConnection().createStatement().executeQuery(sql);
         while(rs.next()){
             String visit_time = rs.getString("visit_begin_visit_time");
             String gravida =  rs.getString("pp_care_number");
             if(gravida.equals("1"))
                 bcare1 = visit_time;
             else if(gravida.equals("2"))
                 bcare2 = visit_time;
             else if(gravida.equals("3"))
                 bcare3 = visit_time;
         }
         return true;
    }      
}
