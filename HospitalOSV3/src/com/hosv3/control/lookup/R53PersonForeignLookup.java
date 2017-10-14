/*
 * AccidentTypeLookup.java
 *
 * Created on 2 มิถุนายน 2549, 13:28 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.control.lookup;
//import com.henbe.connection.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.ComboFix;
//import com.hosv3.utility.*;
import java.util.Vector;

/**
 *
 * @not deprecated because use henbe package
 * @author Padungrat(tong)
 */
public class R53PersonForeignLookup implements LookupControlInf{
    private Vector vret;
    public java.util.Vector listData(String str) { 
        if(vret!=null)
            return vret;
        vret = new Vector();
        vret.add(new ComboFix("","ไม่เป็นต่างด้าว"));
//        vret.add(new ComboFix("11","ที่มีเลข 13 หลัก ที่ขึ้นต้นด้วยเลข 6,7"));
//        vret.add(new ComboFix("12","ที่มีรหัสที่ขึ้นต้นด้วยเลข 0 และได้ Workpermitted"));
//        vret.add(new ComboFix("13","ขึ้นทะเบียนกับกระทรวงมหาดไทยที่มีรหัสที่ขึ้นต้นด้วยเลข 0"));
//        vret.add(new ComboFix("14","กระทรวงแรงงาน  โดยมี Passport/Visa "));
//        vret.add(new ComboFix("21","ต่างด้าวที่อพยพ และอยู่ในค่าย/ศูนย์พักพิง"));
//        vret.add(new ComboFix("22","ต่างด้าวที่ติดตามรหัส 11 และ 12 ได้แก่สามี/ภรรยา/บุตร/ญาติ"));
//        vret.add(new ComboFix("23","หมายถึงกลุ่มอื่นๆ"));
        vret.add(new ComboFix("01","ต่างด้าวขึ้นทะเบียน ในกลุ่มนักเรียนในสถานศึกษาที่รับสวัสดิการจากรัฐด้านการศึกษา"));
        vret.add(new ComboFix("02","ต่างด้าวขึ้นทะเบียน ในกลุ่มคนไร้รากเหง้า"));
        vret.add(new ComboFix("03","ต่างด้าวขึ้นทะเบียน ในกลุ่มคนที่มีประโยชน์แก่ประเทศชาติ"));
        vret.add(new ComboFix("11","ที่มีเลข 13 หลัก  ที่ขึ้นต้นด้วย 6"));
        vret.add(new ComboFix("12","ที่มีรหัสที่ขึ้นต้นด้วยเลข 0 และได้ Workpermitted"));
        vret.add(new ComboFix("13","ขึ้นทะเบียนกับกระทรวงมหาดไทยที่มีรหัสที่ขึ้นต้นด้วยเลข 0"));
        vret.add(new ComboFix("14","กระทรวงแรงงาน  โดยมี Passport/Visa"));
        vret.add(new ComboFix("15","ที่มีรหัสที่ขึ้นต้นด้วยเลข 3 และ 4 และไม่ใช่สัญชาติไทย"));
        vret.add(new ComboFix("16","ที่มีรหัสที่ขึ้นต้นด้วยเลข 5 และไม่ใช่สัญชาติไทย"));
        vret.add(new ComboFix("17","ที่มีรหัสที่ขึ้นต้นด้วยเลข 8 และ ไม่ใช่สัญชาติไทย"));
        vret.add(new ComboFix("18","ที่มีรหัสที่ขึ้นต้นด้วยเลข 7"));
        vret.add(new ComboFix("21","ต่างด้าวที่อพยพและอยู่ในค่าย/ศูนย์พักพิง"));
        vret.add(new ComboFix("22","ต่างด้าวที่เป็นผู้ติดตามของรหัส 11 ,12 ,15,16,17 ข้างต้นได้แก่ สามี/ภรรยา/บุตร/ญาติ"));
        vret.add(new ComboFix("23","หมายถึงกลุ่มอื่นๆ"));
        return vret;
    }
    public CommonInf readHosData(String str) {
        Vector v = listData(str);
        if(v.isEmpty())
            return null;
        return (ComboFix)v.get(0);
    }
}
