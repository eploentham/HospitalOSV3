/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reportcenter.object;

import com.linuxense.javadbf.DBFField;
import com.reportcenter.utility.ReportData;
import com.reportcenter.utility.StringDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author henbe
 */
public abstract class RpOI {

    protected RpField[] theRPF;
    /**
     * ��˹���Ҩҡ ResultSet
     * @param rs ��Ҩҡ�ҹ������
     * @return ��� instant �����ҡ resultset
     * @throws java.lang.Exception �ҡ�����ҹ��Ŵ�Դ��Ҵ ���ա���� error
     */
    public abstract boolean setValue(ResultSet rs) throws Exception;
    /**
     * ���������������� ��� SQL
     * @param con ��ͧ�ҧ�����������
     * @param startDate �ѹ���������§ҹ
     * @param endDate �ѹ����ش��§ҹ
     * @param mode ���͡Ẻ����ء��¡������੾�з���ҹ
     * @throws java.lang.Exception ��͹ exception
     * @return �觤�� preparestatement ���Ѻ�к�
     */
    public abstract PreparedStatement getPreparedStatement(Connection con
            ,String startDate,String endDate,int mode)throws Exception;
    /**
     * �������
     * @return String �������
     */
    public abstract String getFileName();
    /**
     * �线�Ŵ�������㹡�� config
     * @return
     */
    public abstract RpField[] getRpFields();
    /**
     * ����Ңͧ����ü�ҹ Array
     * @return
     */
    public abstract String[] getValueArray() ;
    /**
     *
     * @return
     */
    public String[] getHeaderArray(){
       String[] hd = new String[getRpFields().length];
       for(int i=0;i<hd.length;i++){
           hd[i] = getRpFields()[i].header;
       }
       return hd;
    }
    public String[] getWarningArray(){
       String[] hd = new String[getRpFields().length];
       for(int i=0;i<hd.length;i++){
           hd[i] = getRpFields()[i].warning;
       }
       return hd;
    }
    /**
     * ��Ǩ�ͺ�ҡ datadict
     * @param sb �ѿ�����红�����
     * @param error �ѿ������ error
     * @return �觼š�õ�Ǩ�ͺ boolean
     *////////////////////////////////////////////////////////addcheckData
    public static DBFField initDBFField(int index, String header,  byte type_field, int len,int dec) {
        DBFField fields = new DBFField();
        String head = header.toUpperCase();
        if(head.length()>10)
            head = head.substring(0,10);
        fields.setName(head);
        fields.setDataType( type_field);
        if(type_field==DBFField.FIELD_TYPE_C)
            fields.setFieldLength(len);
        else if(type_field==DBFField.FIELD_TYPE_N){
            fields.setFieldLength(len);
            fields.setDecimalCount(dec);
        }
        return fields;
    }

    /**
     * �����ͧ͢��Ŵ�����Ѻ DBF
     * @return DBFField[]
     * @throws java.lang.Exception �ҡ�ա�û�С�ȿ�Ŵ�Դ��Ҵ
     */
    public DBFField[] getDBFField() throws Exception {
        RpField[] rpf = getRpFields();
        DBFField[] fields = new DBFField[rpf.length];
        for(int i=0;i<rpf.length;i++)
            fields[i] = initDBFField(i,rpf[i].header,rpf[i].type,rpf[i].length,rpf[i].decimal);
        return fields;
    }

    /**
     * ��� ��� �ͧ��Ŵ�����Ѻ DBF
     * @return DBFField[]
     */
    public Object[] getDBFValue()
    {
        String[] values = this.getValueArray();
        Object[] rowData = new Object[getRpFields().length];
        for(int i=0;i<getRpFields().length;i++){
            if(getRpFields()[i].type==DBFField.FIELD_TYPE_N)
                rowData[i] = ReportData.getDouble(values[i]);
            else if(getRpFields()[i].type==DBFField.FIELD_TYPE_D)
                rowData[i] = StringDate.StringDateToDate(values[i]);
            else
                rowData[i] = values[i];
        }
        return rowData;
    }

    /**
     * ��Ǩ�ͺ��� Datadict
     * @param sb
     * @param error
     * @param theRes ��� error �ͧ��Ŵ����������ͧ���ú�ҧ
     * @return
     *
     */
    public boolean checkDatadict(StringBuffer sb,int[] error,boolean[][] theRes)
    {
        String[] values = getValueArray();
        RpField[] rpf = getRpFields();
        boolean ret = true;
        error[0]++;
        for(int i=0;i<rpf.length;i++){
            if(!ReportData.checkDataDict(	values[i],rpf[i].length
                    ,rpf[i].not_null,rpf[i].fix_length,0,theRes[i])) {
                error[i+1	]++;
                ret=false;
            }
        }
        if(!ret) {
            error[ReportData.MAX_COLUMN-1]++;
            if(error[ReportData.MAX_COLUMN-1]<ReportData.MAX_INCOMPLETE_ROW && sb!=null)
                sb.append("\n\t��¡�÷��Դ��Ҵ��� "+ rpf[1].header+"="+values[1]
                                       + " "+ rpf[2].header+"="+values[2]);
        }
         return ret;
    }
}
