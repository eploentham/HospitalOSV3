package com.report18file.objectnh53;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Pp;
import com.report18file.object.Rp18OI;
import com.report18file.utility.Report18FileData;
import java.io.BufferedReader;
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
public class PpNh53 extends Pp implements Rp18OI{
    
    /** Creates a new instance of AncNh */
    public PpNh53() {
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
            "bweight",
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
"����ʶҹ��ԡ��",
"�Ţ���ѵû�ЪҪ���",
"�Ţ���ѵû�ЪҪ����",
"�������",
"�ѹ����ʹ",
"ʶҹ����ʹ",
"����ʶҹ��Һ�ŷ���ʹ",
"�Ըա�ä�ʹ",
"�������ͧ���Ӥ�ʹ",
"���˹ѡ�á��ʹ(����)",
"��ǡ�ó�Ҵ�͡��ਹ",
"���Ѻ VIT K �������",
"�ѹ�������١���駷�� 1",
"�ѹ�������١���駷�� 2",
"�ѹ�������١���駷�� 3",
"�š�õ�Ǩ��á��ѧ��ʹ",
"�ѹ����Ѻ��ا������"
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
    
    public boolean setValue(ResultSet rs) throws Exception {
        PpNh53 p = this;
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
        return true;
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {

        String sql = IOStream.readInputDefault("config/rp_18file_nhso53/18file_nhso53_pp.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }

    public Rp18OI initInstant() {
        PpNh53 mmm = new PpNh53();
        mmm.setConnectionInf(this.theConnectionInf);
        return mmm;
    }
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        BufferedReader in;
        PpNh53 file = this;
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode	,	5	,	true,false	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	true,false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	mpid	,	13	,	false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	gravida	,	2	,	true,false	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bdate	,	8	,	false,true	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bplace	,	1	,	false,true,Report18FileData.VALID_15	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bhosp	,	5	,	false	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	btype	,	1	,	false,true,Report18FileData.VALID_15	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bdoctor	,	1	,	false,true,Report18FileData.VALID_15	)) { 	error[9	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bweigth	,	4	,	false	)) { 	error[10	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	asphyxia	,	1	,	false,true,Report18FileData.VALID_01	)) { 	error[11	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	vitk	,	1	,	false,true,Report18FileData.VALID_01	)) { 	error[12	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bcare1	,	8	,	false,true	)) { 	error[13	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bcare2	,	8	,	false,true	)) { 	error[14	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bcare3	,	8	,	false,true	)) { 	error[15	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bcres	,	1	,	false,true,Report18FileData.VALID_12	)) { 	error[16	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	update	,	14	,	true	)) { 	error[17	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
            sb.append("\n\t��¡�÷��Դ��Ҵ��� �Ţ�ѵû�ЪҪ�:"+ file.pid + " �ӴѺ�����:"+ file.gravida);                                
        }
         return ret;
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
        Report18FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_C,4,0);
        Report18FileData.initDBFField(10,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(11,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(12,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(13,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(14,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(15,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(16,header,fields,DBFField.FIELD_TYPE_C,14);
        
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        PpNh53 p = this;
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
        rowData[9] = (p.bweigth.trim());
        rowData[10] = p.asphyxia;
        rowData[11] = p.vitk;
        rowData[12] = p.bcare1;
        rowData[13] = p.bcare2;
        rowData[14] = p.bcare3;
        rowData[15] = p.bcres;
        rowData[16] = p.update;
        return rowData;
    }
}
