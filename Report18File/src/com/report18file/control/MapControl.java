/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.report18file.control;

import com.hospital_os.usecase.connection.CommonInf;
import com.report18file.utility.Report18FileData;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.usecase.connection.LookupControlInf;

//henbe comment 100253 kong ไม่ใช้แล้วไมต้อง import
import com.hospital_os.utility.ComboFix;
import com.hosv3.control.MapCon;
import com.hosv3.utility.ConnectionDBMgr;
import com.hosv3.utility.connection.UpdateStatus;
import com.report18file.object.MapDrugCode24;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author henbe
 */
public class MapControl extends MapCon implements LookupControlInf  {
    public MapControl(String[] str,UpdateStatus us,ConnectionInf con){
        super(str,us,con) ;
    }
    /**
     * henbe comment 030310 kong พยายามปรับให้อิงของเดิมการเพิ่มฟังชันใหม่จะทำให้กระบวนการแก้ผิดจุดได้ หรือไม่ครอบคลุม
     * @param map
     * @param lookup
     * @return
     * @throws java.sql.SQLException
     */
    protected int intMapData(String[] map, String[] lookup) throws SQLException {

        int ret=0;
            PreparedStatement ps = theCon.getConnection().prepareStatement(sqlupdate);
            if(sqlupdate.equals(Report18FileData.MAP_DRUG[2])){
                PreparedStatement ps1 = theCon.getConnection().prepareStatement(sqldelete);
                ps1.setString(1, map[0]);
                ps1.executeUpdate();
//henbe comment 230210 kong เมื่อแก้แล้วให้ comment ตอบด้วย
                //konshow แก้กลับให้เหมือนเดิมแล้วครับ
                ps.setString(1, map[0]);
                ps.setString(2, lookup[3]);
                ps.setString(3, map[0]);
                ps.setString(4, lookup[0]);
                ret = ps.executeUpdate();
                System.err.println("sqlupdate : " + sqlupdate);
                System.err.println(map[0]);
                System.err.println(lookup[3]);
                System.err.println(map[0]);
                System.err.println(lookup[0]);
            }
            else{
                ps.setString(1, lookup[0]);
                ps.setString(2, map[0]);
                ret = ps.executeUpdate();
            }
            return ret;
    }


    public void importMapDrug(final File pathFile) {
        System.out.println("File Path : " + pathFile.getAbsolutePath());
        theUS.setStatus("กรุณารอสักครู่ . . .", theUS.WARNING);
        Thread t = new Thread() {
            int count = 0;
            int dep_count = 0;
            public void run() {
                try {
                    int oldDrugCode = 0;
                    int newDrugCode = 0;
                    int total = 0;
                    InputStream myxls = new FileInputStream(pathFile);
                    HSSFWorkbook wb = new HSSFWorkbook(myxls);
                    HSSFSheet sheet = wb.getSheetAt(0);       // first sheet

                    theCon.open();
                    total = sheet.getLastRowNum() - 1;
                    for(int i=0;i<1;i++)
                    {
                        HSSFRow row = sheet.getRow(i);
                        if(row.getLastCellNum()<18)
                        {
                            theUS.setStatus("ไฟล์ไม่ตรงตาม format", theUS.WARNING);
                            stop();
                            return;
                        }
                        /*
                         * column name 0 regno
                            column name 1 T_Code
                            column name 2 GPOcode
                            column name 3 GPOcheck
                            column name 4 comp
                            column name 5 tradename
                            column name 6 dosage_form
                            column name 7 dgdsfnm
                            column name 8 unit
                            column name 9 drugname
                            column name 10 STRENGTH
                            column name 11 manufacturer
                            column name 12 country
                            column name 13 STD_CODE
                            column name 14 UN_SPSC
                            column name 15 OK_record
                            column name 16 select
                            column name 17 version
                            column name 18 findCOMP
                         */
                        for (int j = 0; j < (int) row.getLastCellNum(); j++) {
                            HSSFCell cell = row.getCell((short) j);
                            String data = "";

                            if (cell == null) {
                                data = "";
                            } else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                data = cell.getStringCellValue();
                            }
                            if(j==0)
                            {
                                if(!data.equalsIgnoreCase("regno"))
                                {
                                    theUS.setStatus("ไฟล์ไม่ตรงตาม format", theUS.WARNING);
                                    stop();
                                    return;
                                }
                                    
                            }
                            if(j==1)
                            {
                                if(!data.equalsIgnoreCase("T_Code"))
                                {
                                    theUS.setStatus("ไฟล์ไม่ตรงตาม format", theUS.WARNING);
                                    stop();
                                    return;
                                }
                            }
                            if(j==2)
                            {
                                if(!data.equalsIgnoreCase("GPOcode"))
                                {
                                    theUS.setStatus("ไฟล์ไม่ตรงตาม format", theUS.WARNING);
                                    stop();
                                    return;
                                }
                            }
                            if(j==3)
                            {
                                if(!data.equalsIgnoreCase("GPOcheck"))
                                {
                                    theUS.setStatus("ไฟล์ไม่ตรงตาม format", theUS.WARNING);
                                    stop();
                                    return;
                                }
                            }
                            if(j==4)
                            {
                                if(!data.equalsIgnoreCase("comp"))
                                {
                                    theUS.setStatus("ไฟล์ไม่ตรงตาม format", theUS.WARNING);
                                    stop();
                                    return;
                                }
                            }
                            if(j==5)
                            {
                                if(!data.equalsIgnoreCase("tradename"))
                                {
                                    theUS.setStatus("ไฟล์ไม่ตรงตาม format", theUS.WARNING);
                                    stop();
                                    return;
                                }
                            }
                            if(j==6)
                            {
                                if(!data.equalsIgnoreCase("dosage_form"))
                                {
                                    theUS.setStatus("ไฟล์ไม่ตรงตาม format", theUS.WARNING);
                                    stop();
                                    return;
                                }
                            }
                            if(j==7)
                            {
                                if(!data.equalsIgnoreCase("dgdsfnm"))
                                {
                                    theUS.setStatus("ไฟล์ไม่ตรงตาม format", theUS.WARNING);
                                    stop();
                                    return;
                                }
                            }
                            if(j==8)
                            {
                                if(!data.equalsIgnoreCase("unit"))
                                {
                                    theUS.setStatus("ไฟล์ไม่ตรงตาม format", theUS.WARNING);
                                    stop();
                                    return;
                                }
                            }
                            if(j==9)
                            {
                                if(!data.equalsIgnoreCase("drugname"))
                                {
                                    theUS.setStatus("ไฟล์ไม่ตรงตาม format", theUS.WARNING);
                                    stop();
                                    return;
                                }
                            }
                            if(j==10)
                            {
                                if(!data.equalsIgnoreCase("STRENGTH"))
                                {
                                    theUS.setStatus("ไฟล์ไม่ตรงตาม format", theUS.WARNING);
                                    stop();
                                    return;
                                }
                            }
                            if(j==11)
                            {
                                if(!data.equalsIgnoreCase("manufacturer"))
                                {
                                    theUS.setStatus("ไฟล์ไม่ตรงตาม format", theUS.WARNING);
                                    stop();
                                    return;
                                }
                            }
                            if(j==12)
                            {
                                if(!data.equalsIgnoreCase("country"))
                                {
                                    theUS.setStatus("ไฟล์ไม่ตรงตาม format", theUS.WARNING);
                                    stop();
                                    return;
                                }
                            }
                            if(j==13)
                            {
                                if(!data.equalsIgnoreCase("STD_CODE"))
                                {
                                    theUS.setStatus("ไฟล์ไม่ตรงตาม format", theUS.WARNING);
                                    stop();
                                    return;
                                }
                            }
                            if(j==14)
                            {
                                if(!data.equalsIgnoreCase("UN_SPSC"))
                                {
                                    theUS.setStatus("ไฟล์ไม่ตรงตาม format", theUS.WARNING);
                                    stop();
                                    return;
                                }
                            }
                            if(j==15)
                            {
                                if(!data.equalsIgnoreCase("OK_record"))
                                {
                                    theUS.setStatus("ไฟล์ไม่ตรงตาม format", theUS.WARNING);
                                    stop();
                                    return;
                                }
                            }
                            if(j==16)
                            {
                                if(!data.equalsIgnoreCase("select"))
                                {
                                    theUS.setStatus("ไฟล์ไม่ตรงตาม format", theUS.WARNING);
                                    stop();
                                    return;
                                }
                                    
                            }
                            if(j==17)
                            {
                                if(!data.equalsIgnoreCase("version"))
                                {
                                    theUS.setStatus("ไฟล์ไม่ตรงตาม format", theUS.WARNING);
                                    stop();
                                    return;
                                }
                            }
                            if(j==18)
                            {
                                if(!data.equalsIgnoreCase("findCOMP"))
                                {
                                    theUS.setStatus("ไฟล์ไม่ตรงตาม format", theUS.WARNING);
                                    stop();
                                    return;
                                }
                            }
                        }
                    }
                    for (int i = 1; i < sheet.getLastRowNum(); i++) {
                        HSSFRow row = sheet.getRow(i);
//                        theUS.setStatus("กำลังนำเข้าไฟล์รหัสยา 24 หลักที่จับคู่กับรหัสยาในระบบแล้วเข้า " + i + "/" + total, theUS.WARNING);
//                        System.out.println("กำลังนำเข้าไฟล์รหัสยา 24 หลักที่จับคู่กับรหัสยาในระบบแล้วเข้า " + i + "/" + total);
//                        MapObject nd = new MapObject();
                        MapDrugCode24 mdc = new MapDrugCode24();
                        for (int j = 0; j < (int) row.getLastCellNum(); j++) {
                            HSSFCell cell = row.getCell((short) j);
                            String data = "";

                            if (cell == null) {
                                data = "";
                            } else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                data = cell.getStringCellValue();
                            } 
//                            else {
//                                if(i==9 || j==0 || j==5 || j==4 || j==13)
//                                JOptionPane.showMessageDialog(theUS.getJFrame(),
//                                        "มีประเภทของข้อมูลบางฟิลด์ในไฟล์ไม่เป็น Text \n" +
//                                        "กรุณาตั้งค่าประเภทข้อมูลทุกฟิลด์เป็น Text ก่อน\n" +
//                                        "แล้วจึงสามารถนำเข้ารหัสยามาตรฐานที่จับคู่แล้วเข้าได้",
//                                        "ไม่สามารถนำเข้าข้อมูลต่อได้", JOptionPane.ERROR_MESSAGE);
//                                theUS.setStatus("สามารถนำเข้าไฟล์รหัสยา 24 หลักที่จับคู่กับรหัสยาในระบบแล้วผิดพลาด", theUS.ERROR);
//                                stop();
//                                return;
//                            }
                            
                            if (j == 9) {
                                mdc.itemname = data;
                                mdc.itemname = mdc.itemname.replace("\'", "\\'");
//                                nd.std_id = data;
                            } else if (j == 0) {
                                mdc.regno = data;
                                mdc.regno = mdc.regno.replace("\'", "\\'");
//                                nd.drugcode40 = data;
                            } else if (j == 5) {
                                mdc.tradename = data;
                                mdc.tradename = mdc.tradename.replace("\'", "\\'");
//                                nd.regno = data;
                            }else if (j == 4) {
                                mdc.company = data;
                                mdc.company = mdc.company.replace("\'", "\\'");
//                                nd.regno = data;
                            }else if (j == 13) {
                                mdc.drugcode24 = data;
                                mdc.drugcode24 = mdc.drugcode24.replace("\'", "\\'");
//                                nd.regno = data;
                            }
                        }
                        String sql = "select * from b_nhso_drugcode24 where itemname = '"+mdc.itemname
                                +"' and regno = '" + mdc.regno + "' and tradename = '" + mdc.tradename
                                + "' and drugcode24 = '"+mdc.drugcode24+"'";
                        System.err.println("sql : " + sql);
                        ResultSet rs =  theCon.eQuery(sql);
                        mdc.generateOID("");
                        if(!rs.next())
                        {
                            count++;
                            sql = "insert into b_nhso_drugcode24 (b_nhso_drugcode24_id,itemname,regno,tradename,company,drugcode24)"
                                    + " values ('"+mdc.getObjectId()+"','"+mdc.itemname+"','"+mdc.regno
                                    +"','"+mdc.tradename+"','"+mdc.company
                                    +"','"+mdc.drugcode24+"')";
                            int res = theCon.eUpdate(sql);
                        }
                        theUS.setStatus("ปรับปรุงทะเบียนยา " + i + "/"+total, theUS.WARNING);
                        rs.close();
//                        Vector v = listNhsoDrugCodeRegno(nd.drugcode40, nd.regno);
//                        if (v.size() != 0 && listMapDrug(nd.drugcode40, nd.regno).size() == 0) {
//                            nd.drugcode40 += nd.regno;
//                            nd.ref_id = ((NhsoDrug) v.get(0)).getObjectId();
//                            saveMapDrug(nd);
//                            newDrugCode++;
//                        } else if (v.size() == 0) {
//                            JOptionPane.showMessageDialog(theUS.getJFrame(),
//                                    "รหัสมาตรฐานยายังไม่ครบ กรุณานำเข้าข้อมูลยา 24 หลักให้เรียบร้อยก่อนการนำเข้าข้อมูล map",
//                                    "ไม่สามารถนำเข้าข้อมูลต่อได้", JOptionPane.ERROR_MESSAGE);
//                            theUS.setStatus("สามารถนำเข้าไฟล์รหัสยา 24 หลักที่จับคู่กับรหัสยาในระบบแล้วผิดพลาด", theUS.ERROR);
//                            stop();
//                            return;
//                        } else {
//                            oldDrugCode++;
//                        }
                    }
                    theUS.setStatus("สามารถนำเข้าไฟล์รหัสยา 24 หลักที่จับคู่กับรหัสยาในระบบแล้วเสร็จสิ้น", theUS.COMPLETE);
                } catch (Exception ex) {
                    theUS.setStatus("ปรับปรุงทะเบียนยาผิดพลาด", theUS.ERROR);
                    ex.printStackTrace();
                } finally {
                    theCon.close();
                }
                stop();
            }
        };
        t.start();
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
            String confirm = "ยืนยันการแทนที่ข้อมูลจับคู่" +
                    "\nจากฐานข้อมูลที่เลือกจำนวน "+ target_map_total +
                    " รายการ ที่จับคู่แล้ว " +target_map +" รายการ "+
                    "\nเพื่อแทนที่ข้อมูลจับคู่ปัจจุบันจำนวน " + local_map_total +
                    " รายการ ที่จับคู่แล้ว " +local_map +" รายการ ";
            if(!match)
                confirm+="\n\nคำเตือน ข้อมูลของฐานที่เลือกกับข้อมูลปัจจุบันไม่ตรงกัน การนำเข้าข้อมูลจับคู่อาจไม่เกิดผลใดๆ";
            if (!theUS.confirmBox(confirm, UpdateStatus.WARNING)) {
                return;
            }
            rs = target_con.eQuery(sqlselect_map);
            ResultSetMetaData rm = rs.getMetaData();
            while (rs.next()) {
                count++;
                String map_id = rs.getString(1);
                String lookup_id = rs.getString(2);
                String drug24 ="";
                if(rm.getColumnCount()==3)
                    drug24 = rs.getString(3);
                String[] map = new String[]{map_id,map_id};
                String[] lookup = new String[]{lookup_id,lookup_id,lookup_id,drug24};
                intMapData(map,lookup);
                if(count%100==1)
                    System.out.print(".");
                if(count%1000==1)
                    System.out.println(".");
            }
            System.out.println("total map ="+count);
            theUS.setStatus("การนำเข้าข้อมูลจับคู่เสร็จสิ้น " + count + " รายการ",UpdateStatus.COMPLETE);

        } catch (Exception ex) {
            ex.printStackTrace();
            theUS.setStatus("การนำเข้าข้อมูลจับคู่ผิดพลาด",UpdateStatus.ERROR);
        } finally{
            target_con.close();
            theCon.close();
        }
    }

    public Vector listMap(String text,boolean unmap) {
        try {

            String key[] = text.split(" ");
            theCon.open();
            String sql = "";
            //Map Drug
            if(sqlselect_map.equals(Report18FileData.MAP_DRUG[2])){
                sql = "select * from (" + sqlmap + ")" +
                    " as query  where upper(text1) ilike upper('%" + key[0] + "%')";
                for (int i = 1; key.length > 1 && i < key.length; i++) {
                    sql += " and upper(text1) ilike upper('%" + key[i] + "%') ";
                }
                if (unmap) {
                    sql += " and text2 is null ";
                }

                sql += " order by des1 limit 500 ";
            } else {
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
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }finally{
            theCon.close();
        }
    }

    public CommonInf readHosData(String pk) {
        return super.readHosData(pk);
    }

    public Vector listData(String str) {
        return super.listData(str);
    }

    // henbe comment 230210 kong ฟังชันของยาห้ามใส่ในนี้ย้ายไป report18file
    // konshow ย้ายมาจาก mapcon
    public int mapDataDrugV(Vector mapV,String[] lookup) {
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
                    if(!theUS.confirmBox("ยืนยันการบันทึกทับข้อมูลจับคู่เดิมของ " + map[1],UpdateStatus.WARNING)){
                        continue;
                    }
                ret = intMapData(map,lookup);
            }
            if(ret==0)
                theUS.setStatus("ไม่มีการแก้ไขข้อมูลโปรดตรวจสอบฐานข้อมูล",UpdateStatus.WARNING);
            return ret;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        } finally{
            theCon.close();
        }
    }
    // konshow ย้ายมาจาก mapcon
//    protected int intMapDataDrug(String[] map, String[] lookup) throws SQLException {
//
//        int ret=0;
//            PreparedStatement ps = theCon.getConnection().prepareStatement(sqlupdate);
//            {
//                ps.setString(1, map[0]);
//                ps.setString(2, lookup[3]);
//                ps.setString(3, map[0]);
//                ps.setString(4, lookup[0]);
//                ret = ps.executeUpdate();
//            }
//            return ret;
//    }

}
