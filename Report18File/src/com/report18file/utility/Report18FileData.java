/*
 * Report18FileData.java
 *
 * Created on 19 �ԧ�Ҥ� 2548, 9:25 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.utility;

import com.hosv3.utility.ReportData;
import com.linuxense.javadbf.DBFField;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author Noom
 */
public class Report18FileData extends ReportData {

    /**
     * �����ŨѺ���
     */
    public static String[] MAP_EDUCATION = new String[]{"select " +
            " f_patient_education_type_id as id1,patient_education_type_description as des1 ,patient_education_type_description as text1" +
            ",id as id2,name as des2,name as text2" +
            ",case when id is not null then" +
            "    patient_education_type_description || name" +
            " else patient_education_type_description end as text3" +
            " from f_patient_education_type" +
            " left join r_rp1853_education on r_rp1853_education_id = id"
            ,"select id as id,name as des,name as text " +
            " from r_rp1853_education"
            ," update f_patient_education_type set r_rp1853_education_id = ? where f_patient_education_type_id = ?"
            ," update f_patient_education_type set r_rp1853_education_id = '' where f_patient_education_type_id = ?"
            ," select f_patient_education_type_id,r_rp1853_education_id from f_patient_education_type "
            ,"1"
    };
    public static String[] MAP_NATION = new String[]{"select " +
            " f_patient_nation_id as id1,patient_nation_description as des1 ,patient_nation_description as text1" +
            ",id as id2,name as des2,name as text2" +
            ",case when id is not null then" +
            "    patient_nation_description || name" +
            " else patient_nation_description end as text3" +
            " from f_patient_nation" +
            " left join r_rp1853_nation on r_rp1853_nation_id = id"
            ,"select id as id,name as des,name as text " +
            " from r_rp1853_nation"
            ," update f_patient_nation set r_rp1853_nation_id = ? where f_patient_nation_id = ?"
            ," update f_patient_nation set r_rp1853_nation_id = '' where f_patient_nation_id = ?"
            ," select f_patient_nation_id,r_rp1853_nation_id from f_patient_nation "
            ,"1"
    };
    public static String[] MAP_OCCUPATION = new String[]{"select " +
            " f_patient_occupation_id as id1,patient_occupation_description as des1 ,patient_occupation_description as text1" +
            ",id as id2,name as des2,name as text2" +
            ",case when id is not null then" +
            "    patient_occupation_description || name" +
            " else patient_occupation_description end as text3" +
            " from f_patient_occupation" +
            " left join r_rp1853_occupation on r_rp1853_occupation_id = id"
            ,"select id as id,name as des,name as text " +
            " from r_rp1853_occupation"
            ," update f_patient_occupation set r_rp1853_occupation_id = ? where f_patient_occupation_id = ?"
            ," update f_patient_occupation set r_rp1853_occupation_id = '' where f_patient_occupation_id = ?"
            ," select f_patient_occupation_id,r_rp1853_occupation_id from f_patient_occupation "
            ,"1"
    };
    public static String[] MAP_PREFIX = new String[]{"select" +
            "	f_patient_prefix.f_patient_prefix_id as id1," +
            "	f_patient_prefix.patient_prefix_description as des1," +
            "	f_patient_prefix.patient_prefix_description as text1," +
            "	r_rp1853_prefix.id as id2," +
            "	r_rp1853_prefix.name as des2," +
            "	r_rp1853_prefix.name||','||r_rp1853_prefix.description as text2 " +
            "	,case when id is not null then" +
            "        f_patient_prefix.patient_prefix_description ||','|| " +
            "       r_rp1853_prefix.name||','||r_rp1853_prefix.description " +
            " else f_patient_prefix.patient_prefix_description end as text3 " +
            " from" +
            "	public.f_patient_prefix f_patient_prefix " +
            "		left join public.r_rp1853_prefix r_rp1853_prefix " +
            "		on f_patient_prefix.r_rp1853_prefix_id = r_rp1853_prefix.id"
        ,"select" +
            "	r_rp1853_prefix.id as id,r_rp1853_prefix.name as des," +
            "	r_rp1853_prefix.name||','||r_rp1853_prefix.description as text " +
            " from	r_rp1853_prefix r_rp1853_prefix "
        ,"update f_patient_prefix set r_rp1853_prefix_id = ? where f_patient_prefix_id = ?"
        ,"update f_patient_prefix set r_rp1853_prefix_id = '' where f_patient_prefix_id = ?"
        ,"select f_patient_prefix_id,r_rp1853_prefix_id from f_patient_prefix"
            ,"1"
    };
    public static String[] MAP_PLAN = new String[]{"select"+
            "	b_contract_plans.b_contract_plans_id as id1," +
            "	b_contract_plans.contract_plans_description as des1," +
            "	b_contract_plans.contract_plans_description||' '||b_contract_plans.contract_plans_number as text1," +
            "	r_rp1853_instype.id as id2," +
            "	r_rp1853_instype.inscl_name as des2," +
            "	r_rp1853_instype.inscl_name||' '||r_rp1853_instype.inscl_dateexp as text2," +
            " case when inscl_name is not null then" +
            "	b_contract_plans.contract_plans_description||' '||" +
            "	b_contract_plans.contract_plans_number||' '||" +
            "	r_rp1853_instype.inscl_name||' '||" +
            "	r_rp1853_instype.inscl_dateexp " +
            " else b_contract_plans.contract_plans_description||' '||" +
            "	b_contract_plans.contract_plans_number end as text3" +
            " from" +
                " public.b_contract_plans b_contract_plans " +
                    " left join public.r_rp1853_instype r_rp1853_instype " +
                    " on b_contract_plans.r_rp1853_instype_id = r_rp1853_instype.id"
        ,"select	r_rp1853_instype.id as id,	r_rp1853_instype.inscl_name as des," +
            "	r_rp1853_instype.inscl_name||' '||r_rp1853_instype.inscl_dateexp as text " +
            "from		r_rp1853_instype r_rp1853_instype "
        ,"update b_contract_plans set r_rp1853_instype_id = ? where b_contract_plans_id = ?"
    ,"update b_contract_plans set r_rp1853_instype_id = '' where b_contract_plans_id = ?"
    ,"select  b_contract_plans_id,r_rp1853_instype_id from b_contract_plans"
            ,"1"
    };
    public static String[] MAP_DRUG = new String[]{"select  b_item.b_item_id as id1" +
            "                ,b_item.item_common_name as des1" +
            "                ,b_item.item_common_name as text1" +
            "                ,b_nhso_drugcode24.b_nhso_drugcode24_id as id2" +
            "                ,itemname as des2" +
            "                ,itemname||','||regno||','||company as text2" +
            "                ,case when b_nhso_drugcode24.b_nhso_drugcode24_id is not null then " +
            "                 item_subgroup_description ||b_item.item_common_name||regno||itemname||tradename" +
            "                else item_subgroup_description ||b_item.item_common_name end as text3  " +
            "                     from b_item inner join b_item_subgroup on b_item_subgroup.b_item_subgroup_id = b_item.b_item_subgroup_id" +
            "                     and f_item_group_id in ('1','4') " +
            "                     left join b_nhso_map_drug on b_nhso_map_drug.b_item_id = b_item.b_item_id" +
            "                     left join b_nhso_drugcode24 on b_nhso_map_drug.b_nhso_drugcode24_id = b_nhso_drugcode24.b_nhso_drugcode24_id" +
            "                   where item_active = '1' "
            //henbe comment 100253 kong ���������������ç��������ѹ����ҹ���ԧ�����������Ǩ��Դ�š�з����˹
            ,"select b_nhso_drugcode24_id,regno ||' '||itemname as des,itemname||','||regno||','||company as text , drugcode24 || REGNO as drugcode24 from b_nhso_drugcode24"
            ,"insert into b_nhso_map_drug values (?,?,?,?)"
            ,"delete from b_nhso_map_drug where b_item_id = ?"
            ,"select b_item_id,b_nhso_drugcode24_id,f_nhso_drug_id from b_nhso_map_drug "
            ,"0"
    };

    public static  final String[] reportName51 = {"PERSON"
            ,"DEATH"
            ,"CHRONIC"
            ,"CARD"
            ,"SERVICE"
            ,"DIAG"
            ,"APPOINT"
            ,"SURVEIL"
            ,"DRUG"
            ,"PROCED"
            ,"WOMEN"
            ,"FP"
            ,"EPI"
            ,"NUTRI"
            ,"ANC"
            ,"PP"
            ,"MCH"
            ,"HOME"};
    public static  final String[] reportExplain51 = {"��������´�����źؤ��"
            ,"��������´������ª��Ե�ͧ�ؤ��"
            ,"����ѵԡ���纻��´����ä������ѧ�ͧ�ؤ�� "
            ,"��ѡ��Сѹ�آ�Ҿ�ͧ�ؤ��"
            ,"��������´������Ѻ��ԡ�âͧ�ؤ�ŷء��"
            ,"��������´����ԹԨ����ä�ͧ�ؤ��"
            ,"��������´��ùѴ���Ѻ��ԡ�ä��駵��仢ͧ�ؤ�ŷ�����Ѻ��ԡ��"
            ,"��������´�ͧ�������ä����ͧ������ѧ�ҡ�ؤ�ŷ�����Ѻ��ԡ��"
            ,"��������´�������Ǫ�ѳ�����������Ѻ��ԡ��"
            ,"��������´�������ԡ���ѵ�������������Ѻ��ԡ��"
            ,"������˭ԧ�����ԭ�ѹ������觧ҹ��������Թ�Ѻ���� ���������ҧ 15 - 45 ��"
            ,"��������´�������ԡ���ҧἹ��ͺ����"
            ,"��������´�������ԡ�����ҧ��������Ԥ����ѹ�ä "
            ,"��������´��������ҡ�âͧ�����ص�ӡ��� 5 �� �ء���ࢵ�Ѻ�Դ�ͺ"
            ,"����������´�������ԡ�ýҡ�����"
            ,"��������´�������ԡ�á�ô�������ѧ��ʹ"
            ,"��������´����ѵԡ�õ�駤���� ��ä�ʹ ��С�ô�����ô���ѧ��ʹ"
            ,"��������´��������ѧ�����͹�ࢵ�Ѻ�Դ�ͺ"};

    public static  final String[] reportName = {"PERSON"
            ,"DEATH"
            ,"CHRONIC"
            ,"CARD"
            ,"SERVICE"
            ,"DIAG"
            ,"APPOINT"
            ,"SURVEIL"
            ,"DRUG"
            ,"PROCED"
            ,"WOMEN"
            ,"FP"
            ,"EPI"
            ,"NUTRI"
            ,"ANC"
            ,"PP"
            ,"MCH"
            ,"HOME"};
    public static  final String[] reportExplain = {"��������´�����źؤ��"
            ,"��������´������ª��Ե�ͧ�ؤ��"
            ,"����ѵԡ���纻��´����ä������ѧ�ͧ�ؤ�� "
            ,"��ѡ��Сѹ�آ�Ҿ�ͧ�ؤ��"
            ,"��������´������Ѻ��ԡ�âͧ�ؤ�ŷء��"
            ,"��������´����ԹԨ����ä�ͧ�ؤ��"
            ,"��������´��ùѴ���Ѻ��ԡ�ä��駵��仢ͧ�ؤ�ŷ�����Ѻ��ԡ��"
            ,"��������´�ͧ�������ä����ͧ������ѧ�ҡ�ؤ�ŷ�����Ѻ��ԡ��"
            ,"��������´�������Ǫ�ѳ�����������Ѻ��ԡ��"
            ,"��������´�������ԡ���ѵ�������������Ѻ��ԡ��"
            ,"������˭ԧ�����ԭ�ѹ������觧ҹ��������Թ�Ѻ���� ���������ҧ 15 - 49 ��"
            ,"��������´�������ԡ���ҧἹ��ͺ����"
            ,"��������´�������ԡ�����ҧ��������Ԥ����ѹ�ä "
            ,"��������´��������ҡ�âͧ�����ص�ӡ��� 5 �� �ء���ࢵ�Ѻ�Դ�ͺ"
            ,"����������´�������ԡ�ýҡ�����"
            ,"��������´�������ԡ�á�ô�������ѧ��ʹ"
            ,"��������´����ѵԡ�õ�駤���� ��ä�ʹ ��С�ô�����ô���ѧ��ʹ"
            ,"��������´��������ѧ�����͹�ࢵ�Ѻ�Դ�ͺ"};

    public static  final String[] reportNamePP = {"CHRONIC"
            ,"SURVEIL"
            ,"WOMEN"
            ,"FP"
            ,"EPI"
            ,"NUTRI"
            ,"ANC"
            ,"PP"
            ,"MCH"};
    public static  final String[] reportExplainPP = {"����ѵԡ���纻��´����ä������ѧ�ͧ�ؤ�� "
            ,"��������´�ͧ�������ä����ͧ������ѧ�ҡ�ؤ�ŷ�����Ѻ��ԡ��"
            ,"������˭ԧ�����ԭ�ѹ������觧ҹ��������Թ�Ѻ���� ���������ҧ 15 - 49 ��"
            ,"��������´�������ԡ���ҧἹ��ͺ����"
            ,"��������´�������ԡ�����ҧ��������Ԥ����ѹ�ä "
            ,"��������´��������ҡ�âͧ�����ص�ӡ��� 5 �� �ء���ࢵ�Ѻ�Դ�ͺ"
            ,"����������´�������ԡ�ýҡ�����"
            ,"��������´�������ԡ�á�ô�������ѧ��ʹ "
            ,"��������´����ѵԡ�õ�駤���� ��ä�ʹ ��С�ô�����ô���ѧ��ʹ"};

   public static final int MAX_INCOMPLETE_ROW = 100;
   public static final int MAX_COLUMN = 140;
    public static String[] LOOK_COMPANY = new String[]{"",
            "select company as id" +
            " ,company  as des" +
            " ,company  as text" +
            " from r_rp1853_drugcompany order by des"
    };

    public static String getBeforeDot(String height){
        int dot = height.indexOf(".");
        if(dot!=-1)  height = height.substring(0,dot);
        return height;
    }
     
/**
 *
 */

    public static boolean initDBFField(int index, String[] header, DBFField[] fields, byte type_field, int len) {
        return initDBFField(index,header,fields,type_field,len,0);
    }

    public static boolean initDBFField(int index, String[] header, DBFField[] fields, byte type_field, int len,int dec) {
        fields[index] = new DBFField();
        String head="";
        if(header[index].length()>10){
            head = header[index].toUpperCase().substring(0, 10);
            JOptionPane.showMessageDialog(new JFrame(), "���Ϳ�Ŵ� " + header[index].toUpperCase()
                    + " �բ�Ҵ�ҡ���� 10 �ѡ��\n" + "��觨ж١�Ѵ����ͤ���� " + head,"�����ͷ�Һ",JOptionPane.INFORMATION_MESSAGE);
        }else{
            head = header[index].toUpperCase();
        }
        fields[index].setName(head);
        fields[index].setDataType( type_field);
        if(type_field==DBFField.FIELD_TYPE_C)
            fields[index].setFieldLength(len);
        else if(type_field==DBFField.FIELD_TYPE_N){
            fields[index].setFieldLength(len);
            fields[index].setDecimalCount(dec);
        }
        return true;
    }

    public static void main(String[] argc){
        System.out.println(getDouble("111,111"));
    }

    public static Double getDoubleCeil(String value) {
        try{
            double dvalue = Double.parseDouble(value.replaceAll(",",""));
            dvalue = Math.ceil(dvalue);
            return new Double(dvalue);
        }
        catch(Exception e){
//            System.out.println(e.getMessage());
            return null;
        }
    }
    public static void saveResult(String pathFile,String theSB) throws Exception {
        
        File file = new File(pathFile);
        if(!file.isFile())
            file.createNewFile();
        FileWriter fw = new FileWriter(file);
        fw.write(theSB);
        fw.close();
    }
}
