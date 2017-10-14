/*
 * Card.java
 *
 * Created on 1 �ԧ�Ҥ� 2548, 9:58 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.object;

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
public class Card  implements Rp18OI{
    /**����ʶҹ��ԡ��*/
    public String pcucode = "";
    /**�Ţ���ѵû�ЪҪ�*/
    public String cid = "";
    /**���� �ؤ��*/
    public String pid = "";
    /**�������ѵ��Է��*/
    public String instype = "";
    /**�Ţ���ѵ��Է��*/
    public String insid = "";
    /**�ѹ����͡�ѵ�*/
    public String start = "";
    /**�ѹ����������*/
    public String expir = "";
    /**ʶҹ��ԡ����ѡ*/
    public String main = "";
    /**ʶҹ��ԡ���ͧ*/
    public String sub = "";
    /**�ѹ��͹�շ���Ѻ��ا*/
    public String update = "";
    
    /** Creates a new instance of Card */
    public Card() {
    }
    public String[] getHeaderArray() {
        return new String[]{
            "pcucode",
            "cid",
            "pid",
            "instype",
            "insid",
            "start",
            "expir",
            "main",
            "sub",
            "d_update"
        };
    }
    
    public String[] getWarningArray() {
        return new String[]{
            "ʶҹ��Һ��",
            "�Ţ�ѵû�ЪҪ�",
            "�Ţ HCIS",
            "�������Է��",
            "�Ţ����Է��",
            "�ѹ�͡�ѵ�",
            "�ѹ�������",
            "ʶҹ��Һ����ѡ",
            "ʶҹ��Һ���ͧ",
            "�ѹ���ѹ�֡"
        };
    }
    public String[] getValueArray() {
        return new String[]{
            pcucode	,
            cid	,
            pid	,
            instype	,
            insid	,
            start	,
            expir	,
            main	,
            sub	,
            update	};
    }
    
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) {
        BufferedReader in;
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode 	,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cid	,	13	,	false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	true,false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	instype	,	2	,	false	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	insid	,	18	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	start	,	8	,	false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	expir	,	8	,	false	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	main	,	5	,	false	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	sub	,	5	,	false	)) { 	error[9	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	update	,	8	,	false	)) { 	error[10	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
                sb.append("\n\t��¡�÷��Դ��Ҵ��� �Ţ HCIS:"+ pid);
        }
        return ret;
    }
    
    public boolean setValue(ResultSet rs) throws Exception {
        Card p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.cid = Report18FileData.getRsString(rs,2);
        p.pid = Report18FileData.getRsString(rs,3);
        p.instype = Report18FileData.getRsString(rs,4);
        p.insid = Report18FileData.getRsString(rs,5);
        p.start = Report18FileData.getRsString(rs,6);
        p.expir = Report18FileData.getRsString(rs,7);
        p.main = Report18FileData.getRsString(rs,8);
        p.sub = Report18FileData.getRsString(rs,9);
        p.update = Report18FileData.getRsString(rs,10);
        return true;
    }
    
    public Rp18OI initInstant() {
        return new Card();
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        
        String sql = IOStream.readInputDefault("config/rp_18file/18file_card.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    
    public String getFileName() {
        return "CARD";
    }
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];        
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,18);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_C,8);
        
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Card p = this;
        rowData = new Object[10];
        rowData[0] = p.pcucode;
        rowData[1] = p.cid;
        rowData[2] = p.pid;
        rowData[3] = p.instype;
        rowData[4] = p.insid;
        rowData[5] = p.start;
        rowData[6] = p.expir;
        rowData[7] = p.main;
        rowData[8] = p.sub;
        rowData[9] = p.update;
        return rowData;
    }
}
