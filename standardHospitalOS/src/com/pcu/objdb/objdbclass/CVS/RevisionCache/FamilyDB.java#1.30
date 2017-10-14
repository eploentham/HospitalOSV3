/*
 * FamilyDB.java
 *
 * Created on 3 กันยายน 2548, 10:59 น.
 *
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
/**
 *
 * @author Jao
 */
public class FamilyDB {
    
    /** Creates a new instance of FamilyDB */
    public FamilyDB() {
    }
    public ConnectionInf theConnectionInf;
    public Family dbObj;
    final private String idtable = "705";
    
    public FamilyDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Family();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_family";        
        dbObj.pk_field = "t_health_family_id";
        dbObj.home_id = "t_health_home_id";
//        dbObj.patient_id = "t_patient_id";
//        dbObj.hn = "patient_hn";
        dbObj.family_number = "t_health_family_number";
        dbObj.pid = "patient_pid";
        dbObj.f_prefix_id = "f_prefix_id";
        dbObj.patient_name = "patient_name";
        dbObj.patient_last_name = "patient_last_name";
        dbObj.patient_birthday = "patient_birthday";
        dbObj.patient_birthday_true = "patient_birthday_true";
        dbObj.f_sex_id = "f_sex_id";
        dbObj.marriage_status_id = "f_patient_marriage_status_id";
        dbObj.education_type_id = "f_patient_education_type_id";
        dbObj.occupation_id = "f_patient_occupation_id";
        dbObj.nation_id = "f_patient_nation_id";
        dbObj.race_id = "f_patient_race_id";
        dbObj.religion_id = "f_patient_religion_id";
        dbObj.status_id = "f_patient_family_status_id";
        dbObj.father_firstname = "patient_father_firstname";
        dbObj.father_lastname = "patient_father_lastname";
        dbObj.father_pid = "patient_father_pid";
        dbObj.mother_firstname = "patient_mother_firstname";
        dbObj.mother_lastname = "patient_mother_lastname";
        dbObj.mother_pid = "patient_mother_pid";
        dbObj.couple_firstname = "patient_couple_firstname";
        dbObj.couple_lastname = "patient_couple_lastname";
        dbObj.couple_id = "patient_couple_id";
        dbObj.work_office = "patient_work_office";
        dbObj.blood_group_id = "f_patient_blood_group_id";
        dbObj.area_status_id = "f_patient_area_status_id";
        dbObj.record_date_time = "record_date_time";
        dbObj.modify_date_time = "modify_date_time";
        dbObj.cancel_date_time = "cancel_date_time";
        dbObj.staff_record = "health_family_staff_record";
        dbObj.staff_modify = "health_family_staff_modify";
        dbObj.staff_cancel = "health_family_staff_cancel";
        dbObj.active = "health_family_active";
        dbObj.hn_hcis = "health_family_hn_hcis";
        dbObj.discharge_status_id = "f_patient_discharge_status_id";
        dbObj.discharge_date_time = "patient_discharge_date_time";
        dbObj.move_in_date_time = "patient_move_in_date_time";
        dbObj.labor = "r_rp1853_foreign_id";
        dbObj.father_fid = "father_family_id";
        dbObj.mother_fid = "mother_family_id";
        dbObj.couple_fid = "couple_family_id";
        dbObj.foreigner_card_no = "health_family_foreigner_card_no";
        return true;
    }
    
    public int insert(Family p) throws Exception
    {
        System.out.println("fam.setObjectId(this.family_id);" + p.getObjectId());
        p.generateOID(idtable);
        System.out.println("fam.setObjectId(this.family_id);" + p.getObjectId());
        String sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.home_id
       // + " ,"  + dbObj.patient_id
//        + " ,"	+ dbObj.hn
        + " ,"  + dbObj.family_number
        + " ,"	+ dbObj.pid        
        + " ,"	+ dbObj.f_prefix_id
        + " ,"	+ dbObj.patient_name
        + " ,"	+ dbObj.patient_last_name
        + " ,"	+ dbObj.patient_birthday
        + " ,"	+ dbObj.patient_birthday_true
        + " ,"	+ dbObj.f_sex_id
        + " ,"	+ dbObj.marriage_status_id
        + " ,"	+ dbObj.education_type_id
        + " ,"	+ dbObj.occupation_id                
        + " ,"	+ dbObj.nation_id
        + " ,"	+ dbObj.race_id
        + " ,"	+ dbObj.religion_id
        + " ,"	+ dbObj.status_id
        + " ,"	+ dbObj.father_firstname
        + " ,"	+ dbObj.father_lastname
        + " ,"	+ dbObj.father_pid
        + " ,"	+ dbObj.mother_firstname
        + " ,"	+ dbObj.mother_lastname
        + " ,"	+ dbObj.mother_pid
        + " ,"	+ dbObj.couple_firstname
        + " ,"	+ dbObj.couple_lastname
        + " ,"	+ dbObj.couple_id
        + " ,"	+ dbObj.work_office
        + " ,"	+ dbObj.blood_group_id
        + " ,"	+ dbObj.area_status_id
        + " ,"	+ dbObj.record_date_time
        + " ,"	+ dbObj.modify_date_time
        + " ,"	+ dbObj.cancel_date_time
        + " ,"	+ dbObj.staff_record
        + " ,"	+ dbObj.staff_modify
        + " ,"	+ dbObj.staff_cancel
        + " ,"	+ dbObj.active
        + " ,"	+ dbObj.hn_hcis     
        + " ,"	+ dbObj.discharge_status_id     
        + " ,"	+ dbObj.discharge_date_time      
        + " ,"	+ dbObj.move_in_date_time
        + " ,"	+ dbObj.labor
        + " ,"	+ dbObj.father_fid
        + " ,"	+ dbObj.mother_fid
        + " ,"	+ dbObj.couple_fid
        + " ,"	+ dbObj.foreigner_card_no
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.home_id
        //+ "','" + p.patient_id
//        + "','" + p.hn  
        + "','" + p.family_number
        + "','" + p.pid              
        + "','" + p.f_prefix_id
        + "','" + p.patient_name
        + "','" + p.patient_last_name
        + "','" + p.patient_birthday
        + "','" + p.patient_birthday_true
        + "','" + p.f_sex_id
        + "','" + p.marriage_status_id
        + "','" + p.education_type_id
        + "','" + p.occupation_id
        + "','" + p.nation_id
        + "','" + p.race_id
        + "','" + p.religion_id
        + "','" + p.status_id
        + "','" + p.father_firstname
        + "','" + p.father_lastname
        + "','" + p.father_pid
        + "','" + p.mother_firstname
        + "','" + p.mother_lastname
        + "','" + p.mother_pid
        + "','" + p.couple_firstname
        + "','" + p.couple_lastname
        + "','" + p.couple_id
        + "','" + p.work_office
        + "','" + p.blood_group_id
        + "','" + p.area_status_id
        + "','" + p.record_date_time
        + "','" + p.modify_date_time
        + "','" + p.cancel_date_time
        + "','" + p.staff_record
        + "','" + p.staff_modify
        + "','" + p.staff_cancel
        + "','" + p.active
        + "','" + p.hn_hcis
        + "','" + p.discharge_status_id
        + "','" + p.discharge_date_time  
        + "','" + p.move_in_date_time
        + "','" + p.labor
        + "','" + p.father_fid
        + "','" + p.mother_fid
        + "','" + p.couple_fid
        + "','" + p.foreigner_card_no
        + "')";

        System.out.println("fam.setObjectId(this.family_id);" + p.getObjectId());
        return theConnectionInf.eUpdate(sql);
    }
    public int update(Family o) throws Exception
    {        
        String sql="update " + dbObj.table + " set ";
        Family p=o;
        String field =""
        + "', " +dbObj.home_id  + "='" + p.home_id 
       // + "', " +dbObj.patient_id  + "='" + p.patient_id
//        + "', " +dbObj.hn  + "='" + p.hn
        + "', " +dbObj.family_number + "='" + p.family_number     
        + "', " +dbObj.pid  + "='" + p.pid 
        + "', " +dbObj.f_prefix_id  + "='" + p.f_prefix_id 
        + "', " +dbObj.patient_name  + "='" + p.patient_name 
        + "', " +dbObj.patient_last_name  + "='" + p.patient_last_name 
        + "', " +dbObj.patient_birthday  + "='" + p.patient_birthday 
        + "', " +dbObj.patient_birthday_true  + "='" + p.patient_birthday_true 
        + "', " +dbObj.f_sex_id  + "='" + p.f_sex_id 
        + "', " +dbObj.marriage_status_id  + "='" + p.marriage_status_id 
        + "', " +dbObj.education_type_id  + "='" + p.education_type_id 
        + "', " +dbObj.occupation_id  + "='" + p.occupation_id 
        + "', " +dbObj.nation_id  + "='" + p.nation_id 
        + "', " +dbObj.race_id  + "='" + p.race_id 
        + "', " +dbObj.religion_id  + "='" + p.religion_id 
        + "', " +dbObj.status_id + "='" + p.status_id     
        + "', " +dbObj.father_firstname  + "='" + p.father_firstname 
        + "', " +dbObj.father_lastname  + "='" + p.father_lastname 
        + "', " +dbObj.father_pid  + "='" + p.father_pid 
        + "', " +dbObj.mother_firstname  + "='" + p.mother_firstname 
        + "', " +dbObj.mother_lastname  + "='" + p.mother_lastname 
        + "', " +dbObj.mother_pid  + "='" + p.mother_pid 
        + "', " +dbObj.couple_firstname  + "='" + p.couple_firstname 
        + "', " +dbObj.couple_lastname  + "='" + p.couple_lastname 
        + "', " +dbObj.couple_id  + "='" + p.couple_id 
        + "', " +dbObj.work_office  + "='" + p.work_office 
        + "', " +dbObj.blood_group_id  + "='" + p.blood_group_id 
        + "', " +dbObj.area_status_id  + "='" + p.area_status_id 
        + "', " +dbObj.record_date_time  + "='" + p.record_date_time 
        + "', " +dbObj.modify_date_time  + "='" + p.modify_date_time 
        + "', " +dbObj.cancel_date_time  + "='" + p.cancel_date_time 
        + "', " +dbObj.staff_record  + "='" + p.staff_record 
        + "', " +dbObj.staff_modify  + "='" + p.staff_modify 
        + "', " +dbObj.staff_cancel  + "='" + p.staff_cancel 
        + "', " +dbObj.active  + "='" + p.active 
        + "', " +dbObj.hn_hcis  + "='" + p.hn_hcis
        + "', " +dbObj.discharge_status_id  + "='" + p.discharge_status_id
        + "', " +dbObj.discharge_date_time  + "='" + p.discharge_date_time 
        + "', " +dbObj.move_in_date_time  + "='" + p.move_in_date_time
        + "', " +dbObj.labor  + "='" + p.labor
        + "', " +dbObj.father_fid  + "='" + p.father_fid
        + "', " +dbObj.mother_fid  + "='" + p.mother_fid
        + "', " +dbObj.couple_fid  + "='" + p.couple_fid
        + "', " +dbObj.foreigner_card_no  + "='" + p.foreigner_card_no
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(Family o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAllFamily() throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.pid + " <> '" + "'" + "  and "
                + dbObj.active + " = '1'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*
     *@author henbe pongtorn
     *@name ค้นผู้ป่วยตามชื่อ สกุลและบ้าน
     */
    public Vector selectByFnameLnameHome(String fname,String lname,String home_id) throws Exception
    {
        String sql="select * from " + dbObj.table + 
                " where " + dbObj.home_id + " = '" + home_id + "' " +
                " and " + dbObj.patient_name + " = '" + fname + "' " +
                " and " + dbObj.patient_last_name + " = '" + lname + "' " +
                " and " + dbObj.active + " ='1' ";
       return eQuery(sql);
    }    
    
    public Vector selectFamilyByHomeId(String home_id,int limit) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.home_id
        + " = '" + home_id + "' and " + dbObj.active + " ='1'" +
                "  order by " + dbObj.patient_name +
                " limit " + limit;
        return eQuery(sql);
    }
    public Vector selectFamilyByHomeId(String home_id) throws Exception
    {
        return selectFamilyByHomeId(home_id, 100);
    }
    
    public Vector selectByOtherHn(String number) throws Exception
    {
         String sql="select * from " + dbObj.table
        + " where " + dbObj.hn_hcis
        + " like '%" + number + "'"
        + " and " + dbObj.active + " ='1' " ;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public Vector setlectFamilyByName(String name,String lastname) throws Exception
    {
        Vector v = null;
        String sql = "select * from "+dbObj.table
                +" where "+dbObj.patient_name+" like '%"+name+"%'"
                +" and "+dbObj.patient_last_name+" like '%"+lastname+"%'";
        v = this.eQuery(sql);
        return v;
    }
    public Vector selectFamilyByPId(String pid) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pid
        + " = '" + pid + "' and " + dbObj.active + " ='1' Order by " + dbObj.record_date_time ;
        return eQuery(sql);
    }
    public Vector selectLikeHcis(String pid) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.hn_hcis
        + " like '" + pid + "' and " + dbObj.active + " ='1' Order by " + dbObj.record_date_time ;
        return eQuery(sql);
    }
    
    public Vector queryByFLName(String fname,String lname) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where "
        + dbObj.patient_name + " like '" + fname + "' and "
        + dbObj.patient_last_name + " like '" + lname + "' and " 
        + dbObj.active + " ='1'";
        
        
        /*sql = Gutil.convertSQLToMySQL(sql,"2");
        */
        return eQuery(sql);
    }
    
    public Vector queryByFName(String fname) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where "
        + dbObj.patient_name + " like '" + fname + "' and "
        + dbObj.active + " = '1' "
        + "order by "+ dbObj.patient_name;
        
        return eQuery(sql);
    }
    
    public Vector queryBySName(String lname) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where "
        + dbObj.patient_last_name + " like '" + lname + "' and "
        + dbObj.active + " = '1' ";
        
        return eQuery(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        Family p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Family();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.home_id = rs.getString(dbObj.home_id);
//            p.patient_id = rs.getString(dbObj.patient_id);
//            p.hn = rs.getString(dbObj.hn);
            p.family_number = rs.getString(dbObj.family_number);
            p.pid = rs.getString(dbObj.pid);
            p.f_prefix_id= rs.getString(dbObj.f_prefix_id); 
            p.patient_name = rs.getString(dbObj.patient_name);            
            p.patient_last_name = rs.getString(dbObj.patient_last_name);            
            p.patient_birthday = rs.getString(dbObj.patient_birthday);            
            p.patient_birthday_true = rs.getString(dbObj.patient_birthday_true);            
            p.f_sex_id = rs.getString(dbObj.f_sex_id);            
            p.marriage_status_id = rs.getString(dbObj.marriage_status_id);            
            p.education_type_id = rs.getString(dbObj.education_type_id);            
            p.occupation_id = rs.getString(dbObj.occupation_id);            
            p.nation_id = rs.getString(dbObj.nation_id);
            p.race_id = rs.getString(dbObj.race_id);
            p.religion_id = rs.getString(dbObj.religion_id);
            p.status_id = rs.getString(dbObj.status_id);
            p.father_firstname = rs.getString(dbObj.father_firstname);
            p.father_lastname = rs.getString(dbObj.father_lastname);
            p.father_pid = rs.getString(dbObj.father_pid);
            p.mother_firstname = rs.getString(dbObj.mother_firstname);
            p.mother_lastname = rs.getString(dbObj.mother_lastname);
            p.mother_pid = rs.getString(dbObj.mother_pid);
            p.couple_firstname = rs.getString(dbObj.couple_firstname);
            p.couple_lastname = rs.getString(dbObj.couple_lastname);
            p.couple_id = rs.getString(dbObj.couple_id);
            p.work_office = rs.getString(dbObj.work_office);
            p.blood_group_id = rs.getString(dbObj.blood_group_id);
            p.area_status_id = rs.getString(dbObj.area_status_id);
            p.record_date_time = rs.getString(dbObj.record_date_time);
            p.modify_date_time = rs.getString(dbObj.modify_date_time);
            p.cancel_date_time = rs.getString(dbObj.cancel_date_time);
            p.staff_record = rs.getString(dbObj.staff_record);
            p.staff_modify = rs.getString(dbObj.staff_modify);
            p.staff_cancel = rs.getString(dbObj.staff_cancel);
            p.active = rs.getString(dbObj.active);               
            p.hn_hcis = rs.getString(dbObj.hn_hcis);
            p.discharge_status_id = rs.getString(dbObj.discharge_status_id);
            p.discharge_date_time = rs.getString(dbObj.discharge_date_time); 
            p.move_in_date_time = rs.getString(dbObj.move_in_date_time);
            p.labor = rs.getString(dbObj.labor);
            p.father_fid = rs.getString(dbObj.father_fid);
            p.mother_fid = rs.getString(dbObj.mother_fid);
            p.couple_fid = rs.getString(dbObj.couple_fid);
            p.foreigner_card_no = rs.getString(dbObj.foreigner_card_no);
            list.add(p);
//        System.out.println("___________-p.labor:"+p.labor);
        }
        rs.close();
        return list;
    } 
    /*
     *@Author : henbe pongtorn
     *@date : 31/03/2549
     *@see : ค้นข้อมูลจาก PrimaryKey ของมัน
     */     
    public Family selectByPK(String family_id) throws Exception
    {
        if(family_id==null || family_id.length()==0 || family_id.equals("null"))
            return null;
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field + " = '" + family_id+"'";
        Vector v=eQuery(sql);
        if(v.isEmpty())
            return null;
        else
            return (Family)v.get(0); 
    } 
    /*
     *@Author : henbe pongtorn
     *@date : 18/03/2549
     *@see : ค้นประชากรจากเลขบัตรประชาชน
     *@deprecated มีปัญหาว่าถ้าค้นมาเจอหลายคนจะทำให้ผิดพลาดได้
     */     
    public Family selectByPid(String pid) throws Exception
    {
         String sql="select * from " + dbObj.table
        + " where " + dbObj.pid + " = '" + pid
        + "' and " + dbObj.active + " ='1'" ;
        Vector v=eQuery(sql);
        if(v.isEmpty())
            return null;
        else
            return (Family)v.get(0); 
    }  
    
    public Vector selectByPid(String pid,String active) throws Exception
    {
         String sql="select * from " + dbObj.table
        + " where " + dbObj.pid + " = '" + pid
        + "' and " + dbObj.active + " ='"+ active +"'" ;
        return eQuery(sql);
    }  
    /**
     * ค้นหาผู้ป่วยที่ยังไม่ได้ลงสิทธิ์ประจำตัว
     * @param offset = แถวเริ่มต้นที่ต้องการให้แสดง limit = จำนวนแถวที่ต้องการแสดง
     * @return Vector ของ Family
     * @author kingland
     * @date 29/05/2549
     */
    public Vector selectFamilyNotHaveFamilyPayment(String offset,String limit,String name,String lname) throws Exception
    {
        Vector v = null;
        String sql = "select "+dbObj.table+".* from "+dbObj.table +
                " where " + dbObj.pk_field + " not in " +
                "( select t_health_family_id from t_patient_payment " +
                "where t_health_family_id <> '' " +
                "and t_health_family_id is not null " +
                "group by t_health_family_id) " ;
        
        if(name != null && !"".equals(name))
        {
            sql = sql + " and "+dbObj.patient_name+" like '"+name+"%'";
        }
        if(lname != null && !"".equals(lname))
        {
            sql = sql + " and "+dbObj.patient_last_name+" like '"+lname+"%'";
        }
        if(!"".equals(offset) && !"".equals(limit))
        {
            sql = sql+" offset "+offset+" limit "+limit;
        }
        v = eQuery(sql);
        return v;
    }

    public int updatePidByFid(String patient_pid, String family_id) throws Exception
    {
        String sql="update " + dbObj.table + 
                " set " + dbObj.pid  + "='" + patient_pid + "'" +
                " where " + dbObj.pk_field + "='" + family_id +"'";
        return theConnectionInf.eUpdate(sql);
    }

    public int updateDischarge(Family family)  throws Exception
    {
         String sql="update " + dbObj.table + " set "
                 + dbObj.discharge_status_id + " = '" + family.discharge_status_id + "'"
                 +","+ dbObj.discharge_date_time + " = '" + family.discharge_date_time + "'"
                 + " where " + dbObj.pk_field + "='" + family.getObjectId()+"'";
         return theConnectionInf.eUpdate(sql);
    }
    /**
     * 
                family.work_office = " ย้ายประวัติไปเลขบัตร " + des_family_id;
            } 
            family.staff_cancel = theHO.theEmployee.getObjectId();
            family.cancel_date_time = theHO.date_time;
            family.active = "0"; 
     * @param family
     * @return
     * @throws Exception
     */
    public int updateActive(Family family)  throws Exception
    {
         String sql="update " + dbObj.table + " set "
                 + dbObj.staff_cancel + " = '" + family.staff_cancel + "'"
                 +","+ dbObj.cancel_date_time + " = '" + family.cancel_date_time + "'"
                 +","+ dbObj.active + " = '" + family.active + "'"
                 + " where " + dbObj.pk_field + "='" + family.getObjectId()+"'";
         return theConnectionInf.eUpdate(sql);
    }

    public Vector selectByHomeId(String home_id, String status_id) throws Exception 
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.home_id + " = '" +home_id+ "'" + "  and "
                + dbObj.status_id + " = '" +status_id+ "'" + "  and "
                + dbObj.active + " = '1'";
        return eQuery(sql);
    }

    public String selectMaxHCIS() throws Exception 
    {
        ResultSet rs = theConnectionInf.eQuery("select max("+ dbObj.hn_hcis
                +") as max_hcis from " + dbObj.table );
        String max = "0";
        if(rs.next())
            max = rs.getString("max_hcis");
        rs.close();
        return max;
    }
    public static void main(String[] str){
        Family fam = new Family();
        System.out.println(fam.getObjectId());
        fam.generateOID("11121");
        System.out.println(fam.getObjectId());
    }
}
