/*
 * Serveil.java
 *
 * Created on 1 �ԧ�Ҥ� 2548, 10:14 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.object;

import com.linuxense.javadbf.DBFField;
import com.report18file.utility.Constant;
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
public class Serveil implements Rp18OI{
    /**����ʶҹ��ԡ��*/
    public String  pcucode = "";
    /** �Ţ���ѵû�ЪҪ�*/
    public String  cid = "";
    /**�Ţ HCIS*/
    public String  pid = "";
    /**�Ţ VN*/
    public String  seq = "";
    /**�ѹ���*/
    public String  date_serv = "";
    /** ���ʡ���ԹԨ���*/
    public String  diagcode = "";
    /**���� 506*/
    public String  code506 = "";
    /**�ѹ������������*/
    public String  illdate = "";
    /**��ҹ�Ţ���(��л���)*/
    public String  illhouse = "";
    /**���������ҹ(��л���)*/
    public String  illvill = "";
    /**���ʵӺ�(��л���)*/
    public String  illtamb = "";
    /**���������(��л���)*/
    public String  illampu = "";
    /**���ʨѧ��Ѵ(��л���)*/
    public String  illchan = "";
    /**��Ҿ������*/
    public String  ptstat = "";
    /**�ѹ�����*/
    public String  date_death = "";
    /**���˵ء�û���*/
    public String  complica = "";
    /**��Դ�ͧ�����ä*/
    public String  organism = "";
    
    /** Creates a new instance of Serveil */
    public Serveil() {
    }
    public String[] getHeaderArray() {
        return new String[]{
            
            "pcucode",
            "cid",
            "pid",
            "seq",
            "date_serv",
            "diagcode",
            "code506",
            "illdate",
            "illhouse",
            "illvill",
            "illtamb",
            "illampu",
            "illchan",
            "ptstat",
            "date_death",
            "complica",
            "organism"
        };
    }
    
    public String[] getWarningArray() {
        return new String[]{"ʶҹ��Һ��",
"�Ţ���ѵû�ЪҪ�",
"�Ţ HCIS",
"�Ţ VN",
"�ѹ���",
"���ʡ���ԹԨ���",
"���� 506",
"�ѹ������������",
"��ҹ�Ţ���(��л���)",
"���������ҹ(��л���)",
"���ʵӺ�(��л���)",
"���������(��л���)",
"���ʨѧ��Ѵ(��л���)",
"��Ҿ������",
"�ѹ�����",
"���˵ء�û���",
"��Դ�ͧ�����ä"
        };
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
            organism	
        };
    }
    
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) {
        BufferedReader in;
        
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode 	,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cid	,	13	,	false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid 	,	13	,	true,false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	seq 	,	8	,	true	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	date_serv	,	8	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	diagcode 	,	5	,	true,false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	code506	,	2	,	false	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	illdate	,	8	,	false	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	illhouse	,	75	,	false	)) { 	error[9	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	illvill	,	2	,	false	)) { 	error[10	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	illtamb	,	2	,	false	)) { 	error[11	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	illampu	,	2	,	false	)) { 	error[12	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	illchan	,	2	,	false	)) { 	error[13	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ptstat	,	1	,	false,true,Report18FileData.VALID_14	)) { 	error[14	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	date_death	,	8	,	false	)) { 	error[15	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	complica	,	3	,	false	)) { 	error[16	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	organism	,	3	,	false	)) { 	error[17	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
                sb.append("\n\t��¡�÷��Դ��Ҵ��� �Ţ HCIS:"+ pid + " �Ţ VN:" + seq);
        }
        return ret;
    }
    
    public boolean setValue(ResultSet rs) throws Exception {
        Serveil p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.cid = Report18FileData.getRsString(rs,2);
        p.pid = Report18FileData.getRsString(rs,3);
        p.seq = Report18FileData.getRsString(rs,4);
        while(p.seq.length()>8) p.seq = p.seq.substring(1);
        p.date_serv = Report18FileData.getRsString(rs,5);
        p.diagcode = Report18FileData.getRsString(rs,6);
        p.diagcode = Constant.removeDot(p.diagcode);
        p.code506 = Report18FileData.getRsString(rs,7);
        while(p.code506.length()>2) p.code506 = p.code506.substring(0,p.code506.length()-1);
        p.illdate = Report18FileData.getRsString(rs,8);
        p.illhouse = Report18FileData.getRsString(rs,9);
        p.illvill = Report18FileData.getRsString(rs,10);
        if(p.illvill!=null && p.illvill.trim().length()==1)
            p.illvill = "0" + p.illvill;
        p.illtamb = Report18FileData.getRsString(rs,11);
        p.illampu = Report18FileData.getRsString(rs,12);
        p.illchan = Report18FileData.getRsString(rs,13);
        p.ptstat = Report18FileData.getRsString(rs,14);
        p.date_death = Report18FileData.getRsString(rs,15);
//            p.date_death = selectDateServeilDeath(p.date_death);
        p.complica = Report18FileData.getRsString(rs,16);
        p.organism = Report18FileData.getRsString(rs,17);
        
/**
 *� Dtadict �ͧ ʹ�. ��ͧ��â���������
1 = ���
2 = ���
3 = �ѧ�ѡ������
4 = ����Һ 
��� HospitalOS �բ����Ŵѧ��� 
1 = ���
2 = ���
3 = �ѧ�ѡ������
4 = ����Һ 
5 = �ͨ�˹���/������ѧ                                
6 = �Ҵ����ѡ������ҵԴ����ա (��Һ��ҢҴ����ѡ��)     
7 = �ú����ѡ��                                        
8 = �ä���������ʧ�(inactive)����դ������繵�ͧ�ѡ�� 
9 = ����ʸ����ѡ��   
 */
        if(p.ptstat.equals("5")) p.ptstat = "3";
        else if(p.ptstat.equals("6")) p.ptstat = "3";
        else if(p.ptstat.equals("7")) p.ptstat = "4";
        else if(p.ptstat.equals("8")) p.ptstat = "3";
        else if(p.ptstat.equals("9")) p.ptstat = "4";
        else  p.ptstat = "4";
        return true;
    }
    
    public Rp18OI initInstant() {
        return new Serveil();
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        
        String sql = IOStream.readInputDefault("config/rp_18file/18file_surveil.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    
    public String getFileName() {
        return "SURVEIL";
    }
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,75);
        Report18FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(10,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(11,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(12,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(13,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(14,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(15,header,fields,DBFField.FIELD_TYPE_C,3);
        Report18FileData.initDBFField(16,header,fields,DBFField.FIELD_TYPE_C,3);
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Serveil p = this;
        rowData = new Object[17];
        rowData[0] = p.pcucode;
        rowData[1] = p.cid;
        rowData[2] = p.pid;
        rowData[3] = p.seq;
        rowData[4] = p.date_serv;
        rowData[5] = p.diagcode;
        rowData[6] = p.code506;
        rowData[7] = p.illdate;
        rowData[8] = p.illhouse;
        rowData[9] = p.illvill;
        rowData[10] = p.illtamb;
        rowData[11] = p.illampu;
        rowData[12] = p.illchan;
        rowData[13] = p.ptstat;
        rowData[14] = p.date_death;
        rowData[15] = p.complica;
        rowData[16] = p.organism;
        return rowData;
    }
}
