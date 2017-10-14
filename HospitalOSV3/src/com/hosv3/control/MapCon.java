/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hosv3.control;

import com.hospital_os.usecase.connection.CommonInf;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.usecase.connection.LookupControlInf;
import com.hospital_os.utility.ComboFix;

import com.hosv3.utility.ConnectionDBMgr;
import com.hosv3.utility.Constant;
import com.hosv3.utility.GuiLang;
import com.hosv3.utility.connection.UpdateStatus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author henbe
 */
public class MapCon implements LookupControlInf{
    public static String[] LOOK_PERSON = new String[]{"",
            "select t_health_family_id as id" +
            " ,case when length(patient_birthday)>10 " +
            " then patient_name||' '|| patient_last_name || ' ' || sex_description || ' '  ||to_number(to_char(current_date,'yyyy'),'9999')- to_number(substr(patient_birthday,1,4),'9999')+543 || ' ปี, เลขบัตร '|| patient_pid" +
            " else  patient_name||' '|| patient_last_name || ' ' || sex_description || ', เลขบัตร '|| patient_pid end as des" +
            " ,case when length(patient_birthday)>10  " +
            " then  patient_name||' '|| patient_last_name || ' ' || sex_description || ' '  ||to_number(to_char(current_date,'yyyy'),'9999')- to_number(substr(patient_birthday,1,4),'9999')+543 || ' ปี, เลขบัตร '|| patient_pid" +
            " else  patient_name||' '|| patient_last_name || ' ' || sex_description || ', เลขบัตร '|| patient_pid end as text" +
            //henbe comment 100253 kong bad pattern ทำอะไรกับคลาสหลักรู้มั้ยจะเกิดอะไรกับคนอื่นที่นำอันนี้ไปใช้" , '' as d" +
            " from t_health_family inner join f_sex on t_health_family.f_sex_id = f_sex.f_sex_id "
            ,"","","","0"
};

    public static String[] LOOK_ITEMCATEGORY = new String[]{"",
            "select b_item_subgroup_id as id" +
            " ,item_subgroup_description  as des" +
            " ,item_subgroup_description  as text" +
            //henbe comment 100253 kong bad pattern" '' as d" +
            " from b_item_subgroup "
            ,"","","","1"
};
    protected UpdateStatus theUS;
    protected ConnectionInf theCon;
    protected String sqlmap;
    protected String sqllookup;
    protected String sqlupdate;
    protected String sqldelete;
    protected String sqlselect_map;
    protected boolean empty_search = true;


    public MapCon(String[] str,UpdateStatus us,ConnectionInf con){
        sqlmap = str[0];
        sqllookup = str[1];
        if(str.length>2)
            sqlupdate = str[2];
        if(str.length>3)
            sqldelete = str[3];
        if(str.length>4)
            sqlselect_map = str[4];
        if(str.length>5)
            empty_search = str[5].equals("1");
        theUS = us;
        theCon = con; 
    }

    public int deleteMap(String[] map) {
        if(!theUS.confirmBox(Constant.getTextBundle("ยืนยันการลบข้อมูลการจับคู่"),UpdateStatus.WARNING))
                return -1;
        try {
            theCon.open();
            PreparedStatement ps = theCon.getConnection().prepareStatement(sqldelete);
            ps.setString(1, map[0]);
            int ret = ps.executeUpdate();
            if(ret==0)
                theUS.setStatus("ไม่มีการแก้ไขข้อมูลโปรดตรวจสอบฐานข้อมูล",UpdateStatus.WARNING);
            return ret;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return -1;
        } finally{
            theCon.close();
        }
    }

    public Vector listLookup(String text) {
        try {
            Vector vData = new Vector();
            if(text.trim().length()==0 && !empty_search)
                return vData;
            String key[] = text.split(" ");
            if(key.length==0)
                key = new String[]{text};
            theCon.open();
//            String sql = "select * from (" + sqllookup + ") as query "
//                    + "inner join (select health_family_active,t_health_family_id from t_health_family) as family on family.t_health_family_id = query.id and family.health_family_active = '1' "
//                    + "where text ilike '%"+key[0]+"%'";
            String sql = "select * from (" + sqllookup + ") as query where text ilike '%"+key[0]+"%'";
            for(int i=1;key.length>1 && i<key.length;i++)
                sql+= " and text ilike '%" + key[i]+"%' ";
            sql+="order by des limit 500 ";

//            for(int i=1;key.length>1 && i<key.length;i++)
//                sql+= " and text ilike '%" + key[i]+"%' ";
//            sql+="order by des limit 500 ";
//            System.out.println(sql);
            ResultSet rs = theCon.eQuery(sql);
            while(rs.next()){
                String[] data = new String[]{
                rs.getString(1),
                rs.getString(2),
                rs.getString(3)
                //henbe comment 100253 kong bad pattern ทำอะไรกับคลาสหลักรู้มั้ยจะเกิดอะไรกับคนอื่นที่นำอันนี้ไปใช
                //henbe comment 100253 kong bad pattern,rs.getString(4)
                };
                vData.add(data);
            }
            return vData;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally{
            theCon.close();
        }
    }
    public Vector listParent(String text) {
        try {
            Vector vData = new Vector();
            if(text.trim().length()==0 && !empty_search)
                return vData;
            String key[] = text.split(" ");
            if(key.length==0)
                key = new String[]{text};
            theCon.open();
            String sql = "select * from (" + sqllookup + ") as query "
                    + "inner join (select health_family_active,t_health_family_id from t_health_family) as family on family.t_health_family_id = query.id and family.health_family_active = '1' "
                    + "where text ilike '%"+key[0]+"%'";

            for(int i=1;key.length>1 && i<key.length;i++)
                sql+= " and text ilike '%" + key[i]+"%' ";
            sql+="order by des limit 500 ";
            System.out.println(sql);
            ResultSet rs = theCon.eQuery(sql);
            while(rs.next()){
                String[] data = new String[]{
                rs.getString(1),
                rs.getString(2),
                rs.getString(3)
                //henbe comment 100253 kong bad pattern ทำอะไรกับคลาสหลักรู้มั้ยจะเกิดอะไรกับคนอื่นที่นำอันนี้ไปใช
                //henbe comment 100253 kong bad pattern,rs.getString(4)
                };
                vData.add(data);
            }
            return vData;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally{
            theCon.close();
        }
    }

    /**
     * konshow ใช้สำหรับการรับผล resultset 4 ตัว
     * @param text
     * @return
     */
    public Vector listLookup2(String text) {
        try {
            Vector vData = new Vector();
            if(text.trim().length()==0 && !empty_search)
                return vData;
            String key[] = text.split(" ");
            if(key.length==0)
                key = new String[]{text};
            theCon.open();
            String sql = "select * from (" + sqllookup + ") as query where text ilike '%"+key[0]+"%'";
            for(int i=1;key.length>1 && i<key.length;i++)
                sql+= " and text ilike '%" + key[i]+"%' ";
            sql+="order by des limit 500 ";
//            System.out.println(sql);
            ResultSet rs = theCon.eQuery(sql);
            while(rs.next()){
                String[] data = new String[]{
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4)
                };
                vData.add(data);
            }
            return vData;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally{
            theCon.close();
        }
    }
    public int mapData(String[] map,String[] lookup) {
        try {
            if(lookup==null){
                theUS.setStatus("กรุณาเลือกรายการจับคู่",UpdateStatus.WARNING);
                return 0;
            }
            if(map==null){
                theUS.setStatus("กรุณาเลือกรายการหลัก",UpdateStatus.WARNING);
                return 0;
            }
            if(map!=null && map[3]!=null)
                if(!theUS.confirmBox(Constant.getTextBundle("ยืนยันการบันทึกทับข้อมูลจับคู่เดิมของ") +
                " " + map[1],UpdateStatus.WARNING)){
                    return 0;
                }

            theCon.open();
            int ret = intMapData(map,lookup);
            if(ret==0)
                theUS.setStatus("ไม่มีการแก้ไขข้อมูลโปรดตรวจสอบฐานข้อมูล",UpdateStatus.WARNING);
            return ret;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return -1;
        } finally{
            theCon.close();
        }
    }
    
    // henbe comment 230210 kong ให้ใช้ชื่อเดียวกันกับ mapData parameter ที่ต่างกันจะเลือกเข้าฟังชันที่ต่างกันเอง
    // konshow แก้ให้เป็น mapData แล้ว
    public int mapData(Vector mapV,String[] lookup) {
        try {
            if(lookup==null){
                theUS.setStatus("กรุณาเลือกรายการจับคู่",UpdateStatus.WARNING);
                return 0;
            }
            if(mapV==null){
                theUS.setStatus("กรุณาเลือกรายการหลัก",UpdateStatus.WARNING);
                return 0;
            }
            theCon.open();
            int ret=0;
            for (int i = 0; i < mapV.size(); i++) {
                String[] map = (String[]) mapV.get(i);
                if(map!=null && map[3]!=null)
                    if(!theUS.confirmBox(Constant.getTextBundle("ยืนยันการบันทึกทับข้อมูลจับคู่เดิมของ") +
                    " " + map[1],UpdateStatus.WARNING))
                        continue;

                ret = intMapData(map,lookup);
            }
            if(ret==0)
                theUS.setStatus("ไม่มีการแก้ไขข้อมูลโปรดตรวจสอบฐานข้อมูล",UpdateStatus.WARNING);
            return ret;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return -1;
        } finally{
            theCon.close();
        }
    }

    public Vector listMap(String text,boolean unmap) {
        try {

            String key[] = text.split(" ");
            theCon.open();
            String sql = "";
            {
                sql = "select * from (" + sqlmap + ")" +
                    " as query  where text3 ilike '%" + key[0] + "%'";
                for (int i = 1; key.length > 1 && i < key.length; i++) {
                    sql += " and text3 ilike '%" + key[i] + "%' ";
                }
                if (unmap) {
                    sql += " and text2 is null ";
                }
                sql += " order by des1 limit 500 ";
            }
//            System.out.println(sql);
            ResultSet rs = theCon.eQuery(sql);
            Vector vData = new Vector();
            while(rs.next()){
                String[] data = new String[]{
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7)};
                vData.add(data);
            }
            return vData;
        }  catch (SQLException ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }finally{
            theCon.close();
        }
    }

    /**
     * อ่านจากฐานต้นทาง select local,match from match
     * ถ้าเป็นตารางใหม่ insert values
     * ถ้าเป็นตารางเก่า update set where
     * @param target_db
     */
    public void importMap(String[] target_db) {
        ConnectionInf target_con = new ConnectionDBMgr(target_db);
        int count=0;
        try {
            theCon.open();
            target_con.open();
            int target_map_total = 0,local_map_total = 0;
            int target_map = 0,local_map = 0;
            ResultSet rs = target_con.eQuery(sqlmap);
            String[] target = new String[10];
            String[] local = new String[10];
            while(rs.next()){
                if(target_map_total<10)
                    target[target_map_total] = rs.getString("id1");
                target_map_total++;
                if(rs.getString("id2")!=null)
                    target_map++;
            }
            rs = theCon.eQuery(sqlmap);
            while(rs.next()){
                if(local_map_total<10)
                    local[local_map_total] = rs.getString("id1");
                local_map_total++;
                if(rs.getString("id2")!=null)
                    local_map++;
            }
            if(target_map_total==0){
                theUS.setStatus("ไม่พบข้อมูลจับคู่ในฐานข้อมูลที่เลือก",UpdateStatus.WARNING);
                return;
            }
            boolean match = true;
            for(int i=0;i<target.length;i++){
                if(!target[i].equals(local[i])){
                    match = false;
                    break;
                }
            }
            String confirm = Constant.getTextBundle("ยืนยันการแทนที่ข้อมูลจับคู่") +
                    "\n" +
                    Constant.getTextBundle(" ") +
                    " "+ target_map_total +
                    " " +
                    Constant.getTextBundle("รายการ") +
                    " " +
                    Constant.getTextBundle("ที่จับคู่แล้ว") +
                    " " +target_map +" " +
                    Constant.getTextBundle("รายการ") +
                    " "+
                    "\n" +
                    Constant.getTextBundle("เพื่อแทนที่ข้อมูลจับคู่ปัจจุบันจำนวน") +
                    " " + local_map_total +
                    " " +
                    Constant.getTextBundle("รายการ") +
                    " " +
                    Constant.getTextBundle("ที่จับคู่แล้ว") +
                    " " +local_map +" " +
                    Constant.getTextBundle("รายการ") +
                    " ";
            if(!match)
                confirm+="\n\n" +
                        Constant.getTextBundle("คำเตือน") +
                        " " +
                        Constant.getTextBundle("ข้อมูลของฐานที่เลือกกับข้อมูลปัจจุบันไม่ตรงกัน") +
                        " " +
                        Constant.getTextBundle("การนำเข้าข้อมูลจับคู่อาจไม่เกิดผลใดๆ");
            if (!theUS.confirmBox(confirm, UpdateStatus.WARNING)) {
                return;
            }
            rs = target_con.eQuery(sqlselect_map);
            while (rs.next()) {
                count++;
                String map_id = rs.getString(1);
                String lookup_id = rs.getString(2);
                String[] map = new String[]{map_id,map_id};
                String[] lookup = new String[]{lookup_id,lookup_id};
                intMapData(map,lookup);
                if(count%100==1)
                    System.out.print(".");
                if(count%1000==1)
                    System.out.println(".");
            }
            System.out.println("total map ="+count);
            theUS.setStatus(Constant.getTextBundle("การนำเข้าข้อมูลจับคู่") + " " +
                    Constant.getTextBundle("เสร็จสิ้น") +
                    " " + count + " " +
                    Constant.getTextBundle("รายการ"),UpdateStatus.COMPLETE);
            
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การนำเข้าข้อมูลจับคู่") + " " +
                    Constant.getTextBundle("ผิดพลาด"),UpdateStatus.ERROR);
        } finally{
            target_con.close();
            theCon.close();
        }
    }

    protected int intMapData(String[] map, String[] lookup) throws SQLException {

        int ret=0;
            PreparedStatement ps = theCon.getConnection().prepareStatement(sqlupdate);
            {
                ps.setString(1, lookup[0]);
                ps.setString(2, map[0]);
                ret = ps.executeUpdate();
            }
            return ret;
    }
    
    public CommonInf readHosData(String pk) {
        Vector v = listData(pk);
        if(v.isEmpty())
            return null;
        return (CommonInf)v.get(0);
    }

    public Vector listData(String str) {
        try {
            if (str.length() == 0) 
                return null;
            
            theCon.open();
            ResultSet rs = theCon.eQuery("select id,des,text from (" + sqllookup
                    + ") query where des like '%" + str + "%'");
            Vector vret = new Vector();
            while (rs.next()) {
                ComboFix c = new ComboFix(rs.getString(1), rs.getString(2));
                vret.add(c);
            }
            return vret;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally{
            theCon.close();
        }
    }
}
