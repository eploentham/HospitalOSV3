package com.report18file.objectpp53;

import com.hospital_os.usecase.connection.ConnectionInf;
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
 * Created on 11 �ѹ��¹ 2551, 15:17 �.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author henbe
 */
public class MchPp53 extends Rp18OI2{

    /**ʶҹ��Һ��*/
    public String  pcucode = "";
    /**�Ţ HCIS*/
    public String  pid = "";
    /**�������*/
    public String  gravida = "";
    /**�ѹ�á�ͧ����ջ�Ш���͹���駷���*/
    public String  lmp = "";
    /**�ѹ����˹���ʹ*/
    public String  edc = "";
    /**�š�õ�Ǩ  VDRL_RS*/
    public String  vdrl_rs = "";
    /**�š�õ�Ǩ  HB_RS*/
    public String  hb_rs = "";
    /** �š�õ�Ǩ  HIV_RS*/
    public String  hiv_rs = "";
    /** �ѹ����Ǩ HCT.*/
    public String  datehct = "";
    /**�š�õ�Ǩ  HCT*/
    public String  hct_rs = "";
    /** �š�õ�Ǩ THALASSAEMIA*/
    public String  thalass = "";
    /** ��Ǩ�آ�Ҿ�ѹ����й�(�������)*/
    public String  dental = "";
    /** �ѹ��(�ӹǹ)*/
    public String  tcaries = "";
    /**�Թ������(���������)*/
    public String  tartar = "";
    /** �˧�͡�ѡ�ʺ(���������)*/
    public String  guminf = "";
    /**�ѹ��ʹ / �ѹ����ش��õ�駤����*/
    public String  bdate = "";
    /**������ش��õ�駤����*/
    public String  bresult = "";
    /**ʶҹ����ʹ*/
    public String  bplace = "";
    /**����ʶҹ��Һ�ŷ���ʹ*/
    public String  bhosp = "";
    /**�Ըա�ä�ʹ / ����ش��õ�駤����*/
    public String  btype = "";
    /** �������ͧ���Ӥ�ʹ*/
    public String  bdoctor = "";
    /**�ӹǹ�Դ�ժվ*/
    public String  lborn  = "";
    /**�ӹǹ��¤�ʹ*/
    public String  sborn = "";
    /** �ѹ�����������駷�� 1*/
    public String  ppcare1 = "";
    /**�ѹ�����������駷�� 2*/
    public String  ppcare2 = "";
    /**�ѹ�����������駷�� 3*/
    public String  ppcare3 = "";
    /**�š�õ�Ǩ��ô���ѧ��ʹ*/
    public String  ppres = "";
    /**�ѹ��͹�շ���Ѻ��ا*/
    public String  update = "";

    public String patient_id = ""; 

    protected ConnectionInf theConnectionInf;
    /** Creates a new instance of AncNh */
    public MchPp53() {
    }

    public MchPp53(ConnectionInf con) {
        theConnectionInf = con;
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {

        String sql = IOStream.readInputDefault("config/rp_18file_pp53/18file_pp53_mch.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }

    public boolean isReadMore(){
        MchPp53 pp = this;
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

        MchPp53 pp = this;
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

    public Rp18OI2 initInstant() {
        MchPp53 mmm = new MchPp53(theConnectionInf);
        return mmm;
    }
    public boolean setValue(ResultSet rs) throws Exception {
        MchPp53 p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.pid = Report18FileData.getRsString(rs,2);
        p.gravida = Report18FileData.getRsString(rs,3);
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
        p.bhosp = Report18FileData.getRsString(rs,19);
        p.btype = Report18FileData.getRsString(rs,20);
        p.bdoctor = Report18FileData.getRsString(rs,21);
        p.lborn = Report18FileData.getRsString(rs,22);
        p.sborn = Report18FileData.getRsString(rs,23);
        p.ppcare1 = Report18FileData.getRsString(rs,24);
        p.ppcare2 = Report18FileData.getRsString(rs,25);
        p.ppcare3 = Report18FileData.getRsString(rs,26);
        if(isReadMore())
            readMorePPCare();
        p.ppres = Report18FileData.getRsString(rs,27);
        p.update = Report18FileData.getRsString(rs,28);
        return true;
    }

    public String getFileName() {
        return "MCH";
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

    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
RpField.initData("	pcucode	","	����ʶҹ��ԡ��	",	5	,	true	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	cid	","	���ʺѵû�ЪҪ�	",	13	,	true	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	gravida	","	�������	",	2	,	true	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	lmp	","	�ѹ�á�ͧ����ջ�Ш���͹���駷���	",	8	,	true	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	edc	","	�ѹ����˹���ʹ	",	8	,	true	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	vdrl_rs	","	�š�õ�Ǩ VDRL_RS	",	1	,	true	,	true	,	Report18FileData.VALID_12	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	hb_rs	","	�š�õ�Ǩ HB_RS	",	1	,	true	,	true	,	Report18FileData.VALID_12	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	hiv_rs	","	�š�õ�Ǩ HIV_RS	",	1	,	false	,	true	,	Report18FileData.VALID_12	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	datehct	","	�ѹ����Ǩ HCT.	",	8	,	false	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	hct_rs	","	�š�õ�Ǩ HCT	",	2	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	thalass	","	�š�õ�Ǩ THALASSAEMIA	",	1	,	true	,	true	,	Report18FileData.VALID_12	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	dental	","	��Ǩ�آ�Ҿ�ѹ����й�(�������)	",	1	,	true	,	true	,	Report18FileData.VALID_01	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	tcaries	","	�ѹ��(�ӹǹ)	",	2	,	true	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	tartar	","	�Թ������(���������)	",	1	,	true	,	true	,	Report18FileData.VALID_01	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	guminf	","	�˧�͡�ѡ�ʺ(���������)	",	1	,	true	,	true	,	Report18FileData.VALID_01	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	bdate	","	�ѹ��ʹ/�ѹ����ش��õ�駤����	",	8	,	true	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	bresult	","	������ش��õ�駤����	",	6	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	bplace	","	ʶҹ����ʹ	",	1	,	true	,	true	,	Report18FileData.VALID_15	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	bhosp	","	����ʶҹ��Һ�ŷ���ʹ	",	5	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	btype	","	�Ըա�ä�ʹ/����ش��õ�駤����	",	1	,	true	,	true	,	Report18FileData.VALID_16	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	bdoctor	","	�������ͧ���Ӥ�ʹ	",	1	,	true	,	true	,	Report18FileData.VALID_15	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	lborn	","	�ӹǹ�Դ�ժվ	",	1	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	sborn	","	�ӹǹ��¤�ʹ	",	1	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	ppcare1	","	�ѹ�����������駷�� 1	",	8	,	true	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	ppcare2	","	�ѹ�����������駷�� 2	",	8	,	true	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	ppcare3	","	�ѹ�����������駷�� 3	",	8	,	true	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	ppres	","	�š�õ�Ǩ��ô���ѧ��ʹ	",	1	,	true	,	true	,	Report18FileData.VALID_12	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	d_update	","	�ѹ����Ѻ��ا������	",	14	,	false	,	true	,	Report18FileData.VALID_N13	,	DBFField.FIELD_TYPE_C	)
        };
        return theRPF;
   }


    
}
