package com.report18file.objectpp53;

import com.hospital_os.usecase.connection.ConnectionInf;
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
 * Created on 11 �ѹ��¹ 2551, 15:17 �.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author henbe
 */
public class PpPp53 extends Rp18OI2{

    /**ʶҹ��Һ��*/
    public String  pcucode = "";
    /**�Ţ HCIS(��)*/
    public String  pid = "";
    /**���ʺؤ��(���)*/
    public String  mpid = "";
    /** �������*/
    public String  gravida = "";
    /**�ѹ����ʹ*/
    public String  bdate = "";
    /**ʶҹ����ʹ*/
    public String  bplace = "";
    /**����ʶҹ��Һ�ŷ���ʹ*/
    public String  bhosp = "";
    /** �Ըա�ä�ʹ*/
    public String  btype = "";
    /**�������ͧ���Ӥ�ʹ*/
    public String  bdoctor = "";
    /**���˹ѡ�á��ʹ(����)*/
    public String  bweigth = "";
    /**��ǡ�ó�Ҵ�͡��ਹ*/
    public String  asphyxia = "";
    /**���Ѻ VIT K �������*/
    public String  vitk = "";
    /**�ѹ�������١���駷�� 1*/
    public String  bcare1 = "";
    /** �ѹ�������١���駷�� 2*/
    public String  bcare2 = "";
    /**�ѹ�������١���駷�� 3*/
    public String  bcare3 = "";
    /** �š�õ�Ǩ��á��ѧ��ʹ*/
    public String  bcres = "";
    /**�ѹ��͹�շ���Ѻ��ا*/
    public String  update = "";

    protected ConnectionInf theConnectionInf;
    /** Creates a new instance of AncNh */
    public PpPp53() {
    }

    public PpPp53(ConnectionInf con){
        theConnectionInf = con;
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {

        String sql = IOStream.readInputDefault("config/rp_18file_pp53/18file_pp53_pp.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }

    public Rp18OI2 initInstant() {
        PpPp53 mmm = new PpPp53(theConnectionInf);
        return mmm;
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
    public boolean setValue(ResultSet rs) throws Exception {
        PpPp53 p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.pid = Report18FileData.getRsString(rs,2);
        p.mpid = Report18FileData.getRsString(rs,3);
        p.gravida = Report18FileData.getRsString(rs,4);
        p.bdate = Report18FileData.getRsString(rs,5);
        p.bplace = Report18FileData.getRsString(rs,6);
        p.bhosp = Report18FileData.getRsString(rs,7);
        p.btype = Report18FileData.getRsString(rs,8);
        p.bdoctor = Report18FileData.getRsString(rs,9);
        p.bweigth = Report18FileData.getRsString(rs,10);
        p.asphyxia = Report18FileData.getRsString(rs,11);
        p.vitk = Report18FileData.getRsString(rs,12);
        p.bcare1 = Report18FileData.getRsString(rs,13);
        p.bcare2 = Report18FileData.getRsString(rs,14);
        p.bcare3 = Report18FileData.getRsString(rs,15);
        if(isReadMore())
            readMorePPCare();
        p.bcres = Report18FileData.getRsString(rs,16);
        p.update = Report18FileData.getRsString(rs,17); 
        return true;
    }

    public String getFileName() {
        return "PP";
    }
 
    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[17];
theRPF[0	]=RpField.initData("	pcucode	","	����ʶҹ��ԡ��	",	5	,	true	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	);
theRPF[1	]=RpField.initData("	cid	","	�Ţ���ѵû�ЪҪ���	",	13	,	true	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	);
theRPF[2	]=RpField.initData("	mcid	","	�Ţ���ѵû�ЪҪ����	",	13	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	);
theRPF[3	]=RpField.initData("	gravida	","	�������	",	2	,	true	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	);
theRPF[4	]=RpField.initData("	bdate	","	�ѹ����ʹ	",	8	,	false	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	);
theRPF[5	]=RpField.initData("	bplace	","	ʶҹ����ʹ	",	1	,	true	,	true	,	Report18FileData.VALID_15	,	DBFField.FIELD_TYPE_C	);
theRPF[6	]=RpField.initData("	bhosp	","	����ʶҹ��Һ�ŷ���ʹ	",	5	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	);
theRPF[7	]=RpField.initData("	btype	","	�Ըա�ä�ʹ	",	1	,	true	,	true	,	Report18FileData.VALID_15	,	DBFField.FIELD_TYPE_C	);
theRPF[8	]=RpField.initData("	bdoctor	","	�������ͧ���Ӥ�ʹ	",	1	,	true	,	true	,	Report18FileData.VALID_15	,	DBFField.FIELD_TYPE_C	);
theRPF[9	]=RpField.initData("	bweigth	","	���˹ѡ�á��ʹ(����)	",	4	,	true	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	);
theRPF[10	]=RpField.initData("	asphyxia	","	��ǡ�ó�Ҵ�͡��ਹ	",	1	,	false	,	true	,	Report18FileData.VALID_01	,	DBFField.FIELD_TYPE_C	);
theRPF[11	]=RpField.initData("	vitk	","	���Ѻ VIT K �������	",	1	,	false	,	true	,	Report18FileData.VALID_01	,	DBFField.FIELD_TYPE_C	);
theRPF[12	]=RpField.initData("	bcare1	","	�ѹ�������١���駷�� 1	",	8	,	true	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	);
theRPF[13	]=RpField.initData("	bcare2	","	�ѹ�������١���駷�� 2	",	8	,	true	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	);
theRPF[14	]=RpField.initData("	bcare3	","	�ѹ�������١���駷�� 3	",	8	,	true	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	);
theRPF[15	]=RpField.initData("	bcres	","	�š�õ�Ǩ��á��ѧ��ʹ	",	1	,	true	,	true	,	Report18FileData.VALID_12	,	DBFField.FIELD_TYPE_C	);
theRPF[16	]=RpField.initData("	d_update	","	�ѹ����Ѻ��ا������	",	14	,	false	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	);
return theRPF;
        
    } 
}
