//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java
/*
   Henbe ย้ายไปอยู่ ใน patientDB แล้วครับ
 */
package com.hosv3.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;
import com.hospital_os.objdb.*;
import java.util.*;

/**
 * 
 * @deprecated ข้อมูลทั้งหมดย้ายไปอยู่ใน ResultLabDB แล้ว
 */
public class Patient2DB extends PatientDB
{
    
/**
 * 
 * @deprecated ข้อมูลทั้งหมดย้ายไปอยู่ใน ResultLabDB แล้ว
 */
    public Patient2DB(ConnectionInf db)
    {
        super(db);
    }
/**
 * 
 * @deprecated ข้อมูลทั้งหมดย้ายไปอยู่ใน ResultLabDB แล้ว
 */
    public Patient2DB(ConnectionInf db,Patient dbObj)
    {
        super(db);
        this.dbObj = dbObj;
    }
    public Vector selectLocking(String str1,String str2) throws Exception
    {
        String sql = "select * from t_patient" +
                " left join t_visit on t_patient.t_patient_id = t_visit.t_patient_id" + 
        " where (f_visit_status_id like '" + str1 + "'" +
        " or f_visit_status_id like '" + str2 + "')" +
        " and visit_locking = '1' " + 
        " order by visit_begin_visit_time desc  limit 500";
        
        Vector v=eQuery(sql);
        if(v.size()==0)         return null;
        else                    return v;
    }   
    public Vector selectLockingByHN(String hn) throws Exception
    {
        String sql = "select * from t_patient" +
                " left join t_visit on t_patient.t_patient_id = t_visit.t_patient_id" + 
                " where visit_locking = 1 " +
                " and t_visit.visit_hn like '" + hn + "'"+ 
                " order by t_visit.visit_vn desc ";
        
        Vector v=eQuery(sql);
        if(v.size()==0)         return null;
        else                    return v;
    }

   public static void main(String[] argc){
               String sql = "select * from t_patient" +
                " left join t_visit on t_patient.t_patient_id = t_visit.t_patient_id" + 
                " where visit_locking = 1 " +
                " and t_visit.visit_hn like '" + 1 + "'"+ 
                " order by t_visit.visit_vn desc ";
   }
}
