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
     * กำหนดค่าจาก ResultSet
     * @param rs ค่าจากฐานข้อมูล
     * @return ค่า instant ที่ได้จาก resultset
     * @throws java.lang.Exception หากการอ่านฟิลด์ผิดพลาด จะมีการแจ้ง error
     */
    public abstract boolean setValue(ResultSet rs) throws Exception;
    /**
     * เตรียมการเชื่อมต่อ และ SQL
     * @param con ช่องทางการเชื่อมต่อ
     * @param startDate วันเริ่มค้นรายงาน
     * @param endDate วันสิ้นสุดรายงาน
     * @param mode ส่งออกแบบหมดทุกรายการหรือเฉพาะที่ผ่าน
     * @throws java.lang.Exception เตือน exception
     * @return ส่งค่า preparestatement ให้กับระบบ
     */
    public abstract PreparedStatement getPreparedStatement(Connection con
            ,String startDate,String endDate,int mode)throws Exception;
    /**
     * ชื่อไฟล์
     * @return String ชื่อไฟล์
     */
    public abstract String getFileName();
    /**
     * เก็บฟิลด์ให้ง่ายในการ config
     * @return
     */
    public abstract RpField[] getRpFields();
    /**
     * ให้ค่าของตัวแปรผ่าน Array
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
     * ตรวจสอบจาก datadict
     * @param sb บัฟเฟอร์เก็บข้อมูล
     * @param error บัฟเฟอร์เก็บ error
     * @return ส่งผลการตรวจสอบ boolean
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
     * ให้ชื่อของฟิลด์สำหรับ DBF
     * @return DBFField[]
     * @throws java.lang.Exception หากมีการประกาศฟิลด์ผิดพลาด
     */
    public DBFField[] getDBFField() throws Exception {
        RpField[] rpf = getRpFields();
        DBFField[] fields = new DBFField[rpf.length];
        for(int i=0;i<rpf.length;i++)
            fields[i] = initDBFField(i,rpf[i].header,rpf[i].type,rpf[i].length,rpf[i].decimal);
        return fields;
    }

    /**
     * ให้ ค่า ของฟิลด์สำหรับ DBF
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
     * ตรวจสอบตาม Datadict
     * @param sb
     * @param error
     * @param theRes คือ error ของฟิลด์ว่ามีเรื่องอะไรบ้าง
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
                sb.append("\n\tรายการที่ผิดพลาดคือ "+ rpf[1].header+"="+values[1]
                                       + " "+ rpf[2].header+"="+values[2]);
        }
         return ret;
    }
}
