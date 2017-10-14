/*
 * Report12FileData.java
 *
 * Created on 7 กันยายน 2548, 9:34 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.utility;

import com.linuxense.javadbf.DBFField;
import com.reportcenter.utility.ReportData;
import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;

/**
 *
 * @author tong(Padungrat)
 */
public class Report12FileData extends ReportData {
    /**
     * ข้อมูลจับคู่
     */
    public static String[] MAP_CHARITEM_12FILE = new String[]{"select " +
            " b_item_id as id1,item_common_name as des1 ,item_common_name as text1" +
            ",id as id2,name as des2,name as text2" +
            ",case when id is not null then" +
            "    item_common_name  || ',' ||  name  || ',' ||  b_item_subgroup.item_subgroup_description" +
            " else item_common_name  || ',' ||  b_item_subgroup.item_subgroup_description end as text3" +
            " from b_item" +
            " inner join b_item_subgroup on b_item.b_item_subgroup_id = b_item_subgroup.b_item_subgroup_id " +
            " left join r_rp1253_charitem on r_rp1253_charitem_id = id" +
            "          where item_active = '1'"
            ,"select id as id,name as des,name as text " +
            " from r_rp1253_charitem"
            ," update b_item set r_rp1253_charitem_id = ? where b_item_id = ?"
            ," update b_item set r_rp1253_charitem_id = '' where b_item_id = ?"
            ," select b_item_id,r_rp1253_charitem_id from b_item "
            ,"1"
    };
    public static String[] MAP_CLINIC_12FILE = new String[]{"select b_visit_clinic.b_visit_clinic_id as id1" +
                ",b_visit_clinic.visit_clinic_description as des1" +
                ",b_visit_clinic.visit_clinic_description as text1" +
                ",b_report_12files_std_clinic.b_report_12files_std_clinic_id as id2" +
                ",report_clinic_12files_description as des2" +
                ",report_clinic_12files_description || ' ' ||report_clinic_12files_description_en as text2" +
                ",case when report_clinic_12files_description is not null then" +
                "    b_visit_clinic.visit_clinic_description ||' '|| report_clinic_12files_description || ' ' ||report_clinic_12files_description_en" +
                " else b_visit_clinic.visit_clinic_description end as text3" +
                " from b_visit_clinic" +
                " left join b_report_12files_map_clinic  on " +
                " b_visit_clinic.b_visit_clinic_id = b_report_12files_map_clinic.b_visit_clinic_id" +
                " left join b_report_12files_std_clinic on " +
                " b_report_12files_std_clinic.b_report_12files_std_clinic_id = b_report_12files_map_clinic.b_report_12files_std_clinic_id"
            ,"select  b_report_12files_std_clinic_id" +
                ",report_clinic_12files_description as des" +
                ",report_clinic_12files_description || ',' || report_clinic_12files_description_en as text " +
                "from b_report_12files_std_clinic"
            ,"insert into b_report_12files_map_clinic values (?,?,?,?,?)"
            ,"delete from b_report_12files_map_clinic where b_visit_clinic_id = ?"
            ," select b_visit_clinic_id,b_report_12files_std_clinic_id from b_report_12files_map_clinic "
            ,"1"
    };
    public static String[] MAP_ADPCODE = new String[]{"select  b_item.b_item_id as id1" +
            "                ,b_item.item_common_name as des1" +
            "                ,b_item.item_common_name as text1" +
            "                ,r_rp1253_adpcode.id as id2" +
            "                ,r_rp1253_adpcode.name as des2" +
            "                ,r_rp1253_adpcode.id||','||r_rp1253_adpcode.name as text2" +
            "                ,case when r_rp1253_adpcode.name is not null then " +
            "                 r_rp1253_adpcode.id || ',' || r_rp1253_adpcode.name ||','||b_item.item_common_name ||','||" +
            "  b_item_subgroup.item_subgroup_description " +
            "                else b_item.item_common_name || b_item_subgroup.item_subgroup_description  end as text3  " +
            "          from b_item "+
            " inner join b_item_subgroup on b_item.b_item_subgroup_id = b_item_subgroup.b_item_subgroup_id " +
            "          left join r_rp1253_adpcode on r_rp1253_adpcode.id = b_item.r_rp1253_adpcode_id" +
            "          where item_active = '1'"
            ,"select id,name as des,r_rp1253_adpcode.id || ',' || r_rp1253_adpcode.name as text from r_rp1253_adpcode"
            ,"update b_item set r_rp1253_adpcode_id = ? where b_item_id = ?"
            ,"update b_item set r_rp1253_adpcode_id = '' where b_item_id = ?"
            ,"select b_item_id,r_rp1253_adpcode_id from b_item "
            ,"1"
    };

    /**Data ของ 12 File*/
    public static  final String[] reportName = {"INS","PAT","OPD","ORF","ODX","OOP","IPD","IRF","IDX","IOP","CHT","CHA"};
    /***/
    public static  final String[] reportExplain = {"มาตรฐานแฟ้มข้อมูลผู้มีสิทธิการรักษาพยาบาล",
                                                    "มาตรฐานแฟ้มข้อมูลผู้ป่วยกลาง",
                                                    "มาตรฐานแฟ้มข้อมูลการมารับบริการผู้ป่วยนอก",
                                                    "มาตรฐานแฟ้มข้อมูลผู้ป่วยนอกที่ต้องส่งต่อ",
                                                    "มาตรฐานแฟ้มข้อมูลวินิจฉัยโรคผู้ป่วยนอก",
                                                    "มาตรฐานแฟ้มข้อมูลหัตถการผู้ป่วยนอก",
                                                    "มาตรฐานแฟ้มข้อมูลผู้ป่วยใน",
                                                    "มาตรฐานแฟ้มข้อมูลผู้ป่วยในที่ต้องส่งต่อ",
                                                    "มาตรฐานแฟ้มข้อมูลวินิจฉัยโรคผู้ป่วยใน",
                                                    "มาตรฐานแฟ้มข้อมูลหัตถการผู้ป่วยใน",
                                                    "มาตรฐานแฟ้มข้อมูลการเงิน ค่ารักษาพยาบาลรวม",
                                                    "มาตรฐานแฟ้มข้อมูลการเงิน ค่ารักษาพยาบาลแยกตามรายชื่อ"};
     
         
//    public static  final String[] report_eClaim = {"INS","PAT","OPD","ORF","ODX","OOP","IPD","IRF","IDX","IOP","CHT","CHA","AER","ADP","LVD","DRU"};
    public static  final String[] report_eClaim = {"INS","PAT","OPD","ORF","ODX","OOP","IPD","IRF","IDX","IOP","CHT","CHA","AER","ADP","DRU"};
    /***/
    public static  final String[] report_eClaim_Explain = {"มาตรฐานแฟ้มข้อมูลผู้มีสิทธิการรักษาพยาบาล",
                                                    "มาตรฐานแฟ้มข้อมูลผู้ป่วยกลาง",
                                                    "มาตรฐานแฟ้มข้อมูลการมารับบริการผู้ป่วยนอก",
                                                    "มาตรฐานแฟ้มข้อมูลผู้ป่วยนอกที่ต้องส่งต่อ",
                                                    "มาตรฐานแฟ้มข้อมูลวินิจฉัยโรคผู้ป่วยนอก",
                                                    "มาตรฐานแฟ้มข้อมูลหัตถการผู้ป่วยนอก",
                                                    "มาตรฐานแฟ้มข้อมูลผู้ป่วยใน",
                                                    "มาตรฐานแฟ้มข้อมูลผู้ป่วยในที่ต้องส่งต่อ",
                                                    "มาตรฐานแฟ้มข้อมูลวินิจฉัยโรคผู้ป่วยใน",
                                                    "มาตรฐานแฟ้มข้อมูลหัตถการผู้ป่วยใน",
                                                    "มาตรฐานแฟ้มข้อมูลการเงิน ค่ารักษาพยาบาลรวม",
                                                    "มาตรฐานแฟ้มข้อมูลการเงิน ค่ารักษาพยาบาลแยกตามรายชื่อ",
                                                    "มาตรฐานแฟ้มอุบัติเหตุฉุกเฉิน",
                                                    "มาตรฐานแฟ้มค่าใช้จ่ายเพิ่มและบริการที่ยังไม่ได้จัดหมวด",
//                                                    "มาตรฐานแฟ้มข้อมูลกรณีที่ผู้ป่วยมีการลากลับบ้าน (Leaveday)",
                                                    "มาตรฐานแฟ้มข้อมูลการใช้ยา"
    };
     public static  final String[] report_eClaim_v = {"INS","PAT","OPD","ORF","ODX","OOP","IPD","IRF","IDX","IOP","CHT","CHA","AER","ADP"};
    /***/
    public static  final String[] report_eClaim_v_Explain = {"มาตรฐานแฟ้มข้อมูลผู้มีสิทธิการรักษาพยาบาล",
                                                    "มาตรฐานแฟ้มข้อมูลผู้ป่วยกลาง",
                                                    "มาตรฐานแฟ้มข้อมูลการมารับบริการผู้ป่วยนอก",
                                                    "มาตรฐานแฟ้มข้อมูลผู้ป่วยนอกที่ต้องส่งต่อ",
                                                    "มาตรฐานแฟ้มข้อมูลวินิจฉัยโรคผู้ป่วยนอก",
                                                    "มาตรฐานแฟ้มข้อมูลหัตถการผู้ป่วยนอก",
                                                    "มาตรฐานแฟ้มข้อมูลผู้ป่วยใน",
                                                    "มาตรฐานแฟ้มข้อมูลผู้ป่วยในที่ต้องส่งต่อ",
                                                    "มาตรฐานแฟ้มข้อมูลวินิจฉัยโรคผู้ป่วยใน",
                                                    "มาตรฐานแฟ้มข้อมูลหัตถการผู้ป่วยใน",
                                                    "มาตรฐานแฟ้มข้อมูลการเงิน ค่ารักษาพยาบาลรวม",
                                                    "มาตรฐานแฟ้มข้อมูลการเงิน ค่ารักษาพยาบาลแยกตามรายชื่อ",
                                                    "มาตรฐานแฟ้มอุบัติเหตุฉุกเฉิน",
                                                    "มาตรฐานแฟ้มค่าใช้จ่ายเพิ่มและบริการที่ยังไม่ได้จัดหมวด"
    };
    
 
    public static String getBeforeDot(String height){
        int dot = height.indexOf(".");
        if(dot!=-1)  height = height.substring(0,dot);
        return height;
    }
     
    public static boolean checkAppend(String field_name,int check_index_count,StringBuffer sb){
        if(check_index_count>0){
            sb.append("\r\n "+field_name+" missing:     "+check_index_count);
            return true;
        }
        return false;
    }
    /**
     * 151000999 -> 151   00999
     */
    public static String getVN8(String seq,int length) {
        return seq.substring(0,3)+seq.substring(seq.length()-length+3);
    }

    public Report12FileData() {
    }

    public static boolean initDBFField(int index, String[] header, DBFField[] fields, byte type_field, int len) {
        return initDBFField(index,header,fields,type_field,len,0);
    }

    public static boolean initDBFField(int index, String[] header, DBFField[] fields, byte type_field, int len,int dot) {
        fields[index] = new DBFField();
        fields[index].setName(header[index].toUpperCase());
        fields[index].setDataType( type_field);
        if(type_field==DBFField.FIELD_TYPE_C){
            fields[index].setFieldLength(len);
        }
        else if(type_field==DBFField.FIELD_TYPE_N){
            fields[index].setFieldLength(len);
            fields[index].setDecimalCount(dot);
        }
        return true;
    }

    public static Double getDouble(String adm_w) {
        try{
            return Double.valueOf(adm_w);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void saveResult(String pathFile,StringBuffer theSB) throws Exception {
        
        File file = new File(pathFile);
        if(!file.isFile())
            file.createNewFile();
        FileWriter fw = new FileWriter(file);
        fw.write(theSB.toString());
        fw.close();
    }

    public static boolean isAddDigit(String hn, int digit) {
        
        if(hn.length()<digit) return true;
        else if(hn.length()>digit) return true;
        return false;
    }
    /**
     * ฟังชั้นนี้ทำหน้าที่ตรวจสอบว่าจำนวนหลักของ hn ที่ต้องส่งมีค่าเท่าไหร่หากมีค่ามากก็จะแทรก 0 ไว้ตรงกลางให้
     * หากมีค่าน้อยก็จะตัด 0 ตรงกลางออกให้
     * แต่จะมีปัญหากรณีที่ เลข hn ของ รพ ไม่เท่ากันจะทำให้ข้อมูลซ้ำซ้อน
     * @param hn
     * @param digit
     * @return
     */
    public static String addDigit(String hn, int digit) {

        if(hn.length()<=4)
            return hn;
        String year = hn.substring(0,3);
        String check = year.substring(1,2);
        if(check.equals("4") || check.startsWith("5")|| check.startsWith("6"))
        {
            if(hn.length()<digit){ 
                while(hn.length()<digit)    hn = year+"0"+hn.substring(3);
            }
            else if(hn.length()>digit){ 
                while(hn.length()>digit)    hn = year+hn.substring(4);
            }
        }
        else{
            if(hn.length()<digit){ 
                while(hn.length()<digit)    hn = "0" + hn;
            }
            else if(hn.length()>digit){ 
                while(hn.length()>digit)    hn = hn.substring(1);
            }
        }
        return hn;
    }
public static void saveResult(String pathFile,String theSB) throws Exception {

        File file = new File(pathFile);
        if(!file.isFile())
            file.createNewFile();
        FileWriter fw = new FileWriter(file);
        fw.write(theSB);
        fw.close();
    }
    
    public static void main(String[] argc){
        String str1[] = new String[]{"011001123"
        ,"0550011233"
        ,"14500112"
        ,"1450011"
        ,"06600112333"};
        for(int i=0;i<str1.length;i++){
            System.out.println("datais " +
                    str1[i]+"  " + addDigit(str1[i],9));
        }
    }
/**
 *ช่วยบอกได้มั้ยว่าตอนคิวรีกันเครื่องหมาย \t , ไว้เพื่ออะไรกัน ตอนนี้ขอ comment ไว้ก่อนนะ
 */

    public static String getRsString(ResultSet rs, int index)  throws Exception {
         String str = rs.getString(index);
         if(str==null)
             str = "";
//         str = str.replaceAll("\t"," ");
////         str = str.replaceAll(","," ");
         str = str.trim();
         return str;
    }
}
