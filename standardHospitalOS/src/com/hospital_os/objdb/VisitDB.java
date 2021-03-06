/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java*/
package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
public class VisitDB
{
    
    public ConnectionInf theConnectionInf;
    public Visit dbObj;
    final public String idtable = "255";/*"218";*/
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
      * 
    * @author ekapop
    * 1.  60-10-23 ����ͧ ��ͧ     Hospital OS ������� �������ͧ
    * Modify doc 6.
    */
    public VisitDB(ConnectionInf db)
    {   

        theConnectionInf=db;
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj = getMapObject();
        return true;
    }
    public static Visit getMapObject()
    {
        Visit dbObj = new Visit();
        dbObj.max_vn= "max_vn";
        
        dbObj.table="t_visit";
        dbObj.pk_field="t_visit_id";
        dbObj.hn   ="visit_hn";
        dbObj.vn   ="visit_vn";
        dbObj.visit_type   ="f_visit_type_id";
        dbObj.begin_visit_time   ="visit_begin_visit_time";
        dbObj.financial_discharge_time   ="visit_financial_discharge_time";
        dbObj.visit_note   ="visit_notice";
        dbObj.refer_in   ="b_visit_office_id_refer_in";
        dbObj.refer_out   ="b_visit_office_id_refer_out";
        dbObj.diagnosis_note   ="visit_diagnosis_notice";
        dbObj.discharge_opd_status   ="f_visit_opd_discharge_status_id";
        dbObj.discharge_ipd_type   ="f_visit_ipd_discharge_type_id";
        dbObj.discharge_ipd_status   ="f_visit_ipd_discharge_status_id";
        dbObj.locking   ="visit_locking";
        dbObj.lock_user   ="visit_staff_lock";
        dbObj.lock_time   ="visit_lock_date_time";
        dbObj.visit_status   ="f_visit_status_id";
        dbObj.pregnant   ="visit_pregnant";
        dbObj.admit_clinic   ="b_visit_clinic_id";
        dbObj.ward   ="b_visit_ward_id";
        dbObj.bed   ="visit_bed";
        dbObj.observe   ="visit_observe";
        dbObj.visit_clinic   ="visit_patient_type";
        dbObj.queue   ="visit_queue";
        dbObj.service   ="b_service_point_id";
        dbObj.observe_user   ="visit_staff_observe";
        dbObj.doctor_dx   ="visit_dx";
        dbObj.is_discharge_ipd   ="visit_ipd_discharge_status";
        dbObj.is_discharge_money   ="visit_money_discharge_status";
        dbObj.is_discharge_doctor   ="visit_doctor_discharge_status";
        dbObj.patient_id   ="t_patient_id";
        dbObj.financial_discharge_user   ="visit_staff_financial_discharge";
        dbObj.doctor_discharge_user   ="visit_staff_doctor_discharge";
        dbObj.doctor_discharge_time   ="visit_staff_doctor_discharge_date_time";
        dbObj.an   ="visit_an";
        dbObj.stat_dx   ="visit_dx_stat";
        dbObj.begin_admit_time   ="visit_begin_admit_date_time";
        dbObj.deny_allergy   ="visit_deny_allergy";
        dbObj.visit_patient_self_doctor = "visit_patient_self_doctor";
        dbObj.is_pcu_service = "visit_pcu_service";
        dbObj.is_hospital_service = "visit_hospital_service";
        dbObj.is_first = "visit_first_visit";
        dbObj.patient_age = "visit_patient_age";
        dbObj.queue_lab_status = "visit_lab_status_id";
        dbObj.refer_cause = "f_refer_cause_id";
        dbObj.emergency = "f_emergency_status_id";
        dbObj.emergency_staff = "visit_emergency_staff";
        dbObj.ncd = "visit_ncd";
        dbObj.ncd_group = "b_ncd_group_id";
        dbObj.have_appointment = "visit_have_appointment";
        dbObj.have_admit = "visit_have_admit";
        dbObj.have_refer = "visit_have_refer";
        dbObj.appointment_id = "t_patient_appointment_id";
        dbObj.cal_date_appointment = "visit_cal_date_appointment";
        dbObj.cause_appointment = "visit_cause_appointment";
        dbObj.visit_record_date_time = "visit_record_date_time";
        dbObj.visit_record_staff = "visit_record_staff";
        dbObj.visit_financial_record_date_time = "visit_financial_record_date_time";
        dbObj.visit_financial_record_staff = "visit_financial_record_staff";
        dbObj.service_location = "service_location";
        dbObj.bVisitBedId="b_visit_bed_id";     //+1
        dbObj.bVisitRoomId="b_visit_room_id";     //+1
        return dbObj;
    }
    /*////////////////////////////////////////////////////////////////////////////////////*/
    public int selectCount() throws Exception
    {
        StringBuffer sql = new StringBuffer("Select COUNT(" )
	.append(dbObj.pk_field )
	.append( ") as max From " )
	.append( dbObj.table);
        
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        int maxVN = 0;
        while(rs.next())
            maxVN = rs.getInt(1);
        return maxVN;
    }    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     * @deprecated henbe
     */
    /*////////////////////////////////////////////////////////////////////////////////////*/
    public Vector SelectVisitAfterEndYear(String date) throws Exception
    {
        StringBuffer sql = new StringBuffer("Select * From " )
	.append( dbObj.table )
	.append( "" )
	.append(" Where SUBSTRING(" )
	.append( dbObj.begin_visit_time )
	.append( ",0,11) < " )
	.append( date )
	.append( "" )
	.append( "Order by " )
	.append( dbObj.begin_visit_time);
        return null;
        
    }
    /*////////////////////////////////////////////////////////////////////////////////////*/
    public int updateUnlock(Visit o)throws Exception
    {   
        StringBuffer sql = new StringBuffer("Update " )
	.append( dbObj.table )
	.append( " set ")
	.append( dbObj.locking )
	.append( " = '" )
	.append( o.locking )
	.append( "'")
	.append( " where " )
	.append( dbObj.pk_field )
	.append( " = '" )
	.append( o.getObjectId() )
	.append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////////////*/
    public String selectMaxVN() throws Exception
    {
        StringBuffer sql = new StringBuffer("Select MAX(" )
	.append(dbObj.vn )
	.append( ") as max From " )
	.append( dbObj.table )
	.append( "" )
	.append(" where " )
	.append( dbObj.visit_type )
	.append( "= '" )
	.append( VisitType.OPD )
	.append( "'");

        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        String maxVN = null;
        while(rs.next())
        {
            maxVN = rs.getString(1);
        }
        sql = null;
        rs = null;
        return maxVN;
    }

    /*////////////////////////////////////////////////////////////////////////////////////*/
    public String selectMaxAN() throws Exception
    {
        StringBuffer sql = new StringBuffer("Select MAX(" )
	.append(dbObj.vn )
	.append( ") as max From " )
	.append( dbObj.table )
	.append( "" )
	.append(" where " )
	.append( dbObj.visit_type )
	.append( "= '" )
	.append( VisitType.IPD )
	.append( "'");
        
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        String maxAN = null;
        while(rs.next())
        {
            maxAN = rs.getString(1);
        }
        sql = null;
        rs = null;
        return maxAN;
    }
    /*////////////////////////////////////////////////////////////////////////////////////*/
    public int updateLock(Visit o)throws Exception
    {   
        StringBuffer sql = new StringBuffer("Update " )
	.append( dbObj.table )
	.append( " set " )
	.append( dbObj.locking )
	.append( " = '" )
	.append( o.locking )
	.append( "'," )
	.append( dbObj.lock_user)
	.append( " = '" )
	.append( o.lock_user )
	.append( "',"        )
	.append( dbObj.lock_time)
	.append( " = '" )
	.append( o.lock_time )
	.append( "'"        )
	.append( " where " )
	.append( dbObj.pk_field )
	.append( " = '" )
	.append( o.getObjectId() )
	.append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////////////*/
    public int updateDxByStat(Visit o)throws Exception
    {   
        StringBuffer sql = new StringBuffer("Update " )
	.append( dbObj.table )
	.append( " set "        )
	.append( dbObj.stat_dx )
	.append( " = '" )
	.append( o.stat_dx )
	.append( "'"        )
	.append( " where " )
	.append( dbObj.pk_field )
	.append( " = '" )
	.append( o.getObjectId() )
	.append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////////////*/
    public int updateDxNote(Visit o)throws Exception
    {   
        StringBuffer sql = new StringBuffer("Update " )
	.append( dbObj.table )
	.append( " set "        )
	.append( dbObj.diagnosis_note )
	.append( " = '" )
	.append( Gutil.CheckReservedWords(o.diagnosis_note) )
	.append( "'"        )
	.append( " where " )
	.append( dbObj.pk_field )
	.append( " = '" )
	.append( o.getObjectId() )
	.append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }

    /**
     * @deprecated henbe unused dangerous function
     * @param new_id
     * @param old_id
     * @return
     * @throws Exception
     */
    public int updatePatientByPatient(String new_id,String old_id)throws Exception
    {   
        String sql = "Update "+dbObj.table +" set "        +
	 dbObj.patient_id + " = '" + new_id + "'"        + "," +
	 dbObj.visit_note + " = "+dbObj.visit_note+"||'-'||'" + old_id + "'" +
	 " where " +
	 dbObj.patient_id + " = '" + old_id +"'";
        return theConnectionInf.eUpdate(sql.toString());
    }

    /*////////////////////////////////////////////////////////////////////////////*/
    public int delete(Visit o) throws Exception
    {   
        StringBuffer sql = new StringBuffer("delete from " )
	.append( dbObj.table        )
	.append( " where " )
	.append( dbObj.pk_field )
	.append( "='" )
	.append( o.getObjectId() )
	.append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public String getVisitByVisitID(String visit_id) throws Exception
    {
        StringBuffer sql = new StringBuffer("SELECT " )
	.append( dbObj.vn )
	.append(" FROM ")
	.append(  dbObj.table )
	.append( " WHERE " )
	.append( dbObj.pk_field )
	.append( "='")
	.append( visit_id )
	.append("'");
        return this.eQueryVisit(sql.toString());
    }
    /**
     *
     * @param vn
     * @return
     * @throws Exception
     */
    public Visit getVisitByVn(String vn) throws Exception {
        StringBuffer sql = new StringBuffer("select * from ").append(dbObj.table).append(" where ").append(dbObj.vn).append(" = '").append(vn).append("'");
        Vector v = eQuery(sql.toString());
        if (v.isEmpty()) {
            return null;
        } else {
            return (Visit) v.get(0);
        }
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Visit selectByVn(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table        )
	.append( " where " ).append( dbObj.vn ).append( " = '" ).append( pk ).append( "'")
        .append( " and " ).append( dbObj.visit_type ).append( " <>'S'" );
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (Visit)v.get(0);
    }
    public Vector selectByBed(String bed_no,String ward_id) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table                )
	.append( " where " )
	.append( dbObj.bed )
	.append( " = '" )
	.append( bed_no )
	.append( "'")
	.append( " and " )
	.append( dbObj.ward )
	.append( " = '" )
	.append( ward_id )
	.append( "'"                )
	.append( " and " )
	.append( dbObj.visit_status )
	.append( " = '1'");
        return eQuery(sql.toString());
    }
    public int updateVisitPregnant(String pregnant,String visit_id) throws Exception
    {
        StringBuffer sql = new StringBuffer(" Update "  )
	.append( dbObj.table )
	.append( " set "                )
	.append( dbObj.pregnant )
	.append( " ='" )
	.append( pregnant )
	.append( "' " )
	.append(        " Where " )
	.append( dbObj.pk_field )
	.append( " ='" )
	.append( visit_id )
	.append( "' ");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    
    
    public Visit selectVisitByPatientIDLast(String patient_id) throws Exception
    {
        StringBuffer sql = new StringBuffer("Select * from " )
	.append( dbObj.table        )
	.append( " Where " ).append( dbObj.patient_id ).append( " = '" ).append( patient_id ).append( "'" )
	.append( " and " ).append( dbObj.visit_type ).append( " <>'S'" )
	.append( " order by " ).append(  dbObj.begin_visit_time )
	.append( " DESC ");
        Vector v=eQuery(sql.toString());
        if(v.size()==0)		return null;
        else		return (Visit)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    /**
     * 
     * @deprecated �ѹ�鹨ҡ patient_id ����� Hn �Դ������������������ҷ��������������ҹ�������ź��͹
     */
    public Visit selectByHN(String pk) throws Exception
    {
        return selectByPtid(pk);
    }
        
    public Vector selectEqualHn(String hn) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table        )
	.append( " where " )
	.append( dbObj.hn )
	.append( " = '" )
	.append( hn )
	.append( "'")
        .append( " and " ).append( dbObj.visit_type ).append( " <>'S'" );
        return eQuery(sql.toString());
    }
    
    public Visit selectByPtid(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table        )
	.append( " where " )
	.append( dbObj.patient_id ).append( " = '" ).append( pk ).append( "'")
	.append( " and " ).append( dbObj.visit_status )	.append( " = '" ).append(  VisitStatus.isInProcess() ).append( "' ")
        .append( " and " ).append( dbObj.visit_type ).append( " <>'S'" )
	.append( " order by " )
	.append( dbObj.vn )
	.append( " DESC ");
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return  null;
        else
            return (Visit)v.get(0);
    }
    public Vector selectListByPtid(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table      )
	.append( " where " ).append( dbObj.patient_id ).append( " = '" ).append( pk ).append( "'")
        .append( " and " ).append( dbObj.visit_type ).append( " <>'S'" )
	.append( " order by " )
	.append( dbObj.begin_visit_time);
        return eQuery(sql.toString());
    }
    public Vector selectByPtidYear(String pk,String year) throws Exception
    {   
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table        )
	.append( " where " )
	.append( dbObj.patient_id )
	.append( " = '" )
	.append( pk )
	.append( "'"        )
	.append( " and " )
	.append( dbObj.begin_visit_time )
	.append( " > '" )
	.append(  year )
	.append( "' ").append( " and " ).append( dbObj.visit_type ).append( " <>'S'" );
        return eQuery(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Visit selectByHnOrderByDateTime(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table        )
	.append( " where " )
	.append( dbObj.patient_id )
	.append( " = '" )
	.append( pk )
	.append( "'"   ).append( " and " ).append( dbObj.visit_type ).append( " <>'S'" )
	.append( " order by "        )
	.append( dbObj.vn );
        
        Vector v=eQuery(sql.toString());
        
        if(v.size()==0)		return null;
        else		return (Visit)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectVisitByWard(String ward_id) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table        )
	.append( " where " )
	.append( dbObj.ward )
	.append( " = '" )
	.append( ward_id)
	.append( "'"        /*  )
	.append( " and " )
	.append( dbObj.is_discharge_doctor )
	.append( " = '0'"*/        )
	.append( " and " )
	.append( dbObj.is_discharge_money  )
	.append( " = '0'"        ).append( " and " ).append( dbObj.visit_type ).append( " <>'S'" )
	.append( " order by " )
	.append( dbObj.begin_admit_time);
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)		return null;
        else		return v;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Visit selectByPK(String pk) throws Exception
    {   /*amp:30/04/48*/        
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table        )
	.append( " where " )
	.append( dbObj.pk_field        )
	.append( " = '" )
	.append( pk )
	.append( "'");

        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (Visit)v.get(0);
    }
    public Visit selectByBedId(String pk) throws Exception
    {   /*amp:30/04/48*/        
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table        )
	.append( " where " )
	.append( dbObj.bVisitBedId        )
	.append( " = '" )
	.append( pk )
	.append( "'");

        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (Visit)v.get(0);
    }
    /*///////////////////////////////////////////////////////////////////////////////////////////////*/
    public Vector selectVisitInTransAfternoon() throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from "  )
	.append( dbObj.table        )
	.append( " where " )
	.append(   dbObj.visit_type )
	.append( " = '0' "        )
	.append( " and " )
	.append( dbObj.visit_status )
	.append( "='1' "        )
	.append( " and " )
	.append( dbObj.observe )
	.append( "<> '1'").append( " and " ).append( dbObj.visit_type ).append( " <>'S'" );
        Vector v=eQuery(sql.toString());
        
        if(v.size()==0)		return null;
        else		return v;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public String selectMaxVnByPK(String pId) throws Exception
    {
        StringBuffer sql = new StringBuffer("select max(" )
	.append( dbObj.vn )
	.append( ") as max_vn from " )
	.append( dbObj.table        )
	.append( " where " )
	.append( dbObj.patient_id        )
	.append( " = '" )
	.append( pId )
	.append( "'").append( " and " ).append( dbObj.visit_type ).append( " ='0'" );
        
        Vector v = maxVnQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return ((Visit)v.get(0)).vn;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector maxVnQuery(String sql) throws Exception
    {
        Visit p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        while(rs.next())
        {
            p = new Visit();
            p.vn = rs.getString(dbObj.max_vn);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*///////////////////////////////////////////////////////*/
    public Vector eQuery(String sql) throws Exception
    {
        Visit p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        while(rs.next())
        {
            p = new Visit();
            getObject(dbObj, p, rs);
            p.patient_id = rs.getString(dbObj.patient_id);
            list.add(p);
        }
        rs.close();
        return list;
    }
    public static boolean getObject(Visit dbObj,Visit p,ResultSet rs)throws Exception
    {
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.hn = rs.getString(dbObj.hn);
            p.vn = rs.getString(dbObj.vn);
            p.an = rs.getString(dbObj.an);
            p.visit_type = rs.getString(dbObj.visit_type);
            p.begin_visit_time = rs.getString(dbObj.begin_visit_time);
            p.financial_discharge_time = rs.getString(dbObj.financial_discharge_time);
            p.visit_note = rs.getString(dbObj.visit_note);
            p.refer_in = rs.getString(dbObj.refer_in);
            p.refer_out = rs.getString(dbObj.refer_out);
            p.diagnosis_note = rs.getString(dbObj.diagnosis_note);
            p.discharge_opd_status = rs.getString(dbObj.discharge_opd_status);
            p.discharge_ipd_type = rs.getString(dbObj.discharge_ipd_type);
            p.discharge_ipd_status = rs.getString(dbObj.discharge_ipd_status);
            /*p.admisstion = rs.getString(dbObj.admisstion);*/
            p.locking = rs.getString(dbObj.locking);
            p.lock_user = rs.getString(dbObj.lock_user);
            p.lock_time = rs.getString(dbObj.lock_time);
            p.visit_status = rs.getString(dbObj.visit_status);
            p.pregnant = rs.getString(dbObj.pregnant);
            p.admit_clinic = rs.getString(dbObj.admit_clinic);
            p.ward = rs.getString(dbObj.ward);
            p.bed = rs.getString(dbObj.bed);
            p.observe = rs.getString(dbObj.observe);
            p.visit_clinic = rs.getString(dbObj.visit_clinic);
            p.queue = rs.getString(dbObj.queue);
            p.service = rs.getString(dbObj.service);
            p.financial_discharge_user= rs.getString(dbObj.financial_discharge_user);
            p.doctor_discharge_user= rs.getString(dbObj.doctor_discharge_user);
            p.doctor_discharge_time= rs.getString(dbObj.doctor_discharge_time);
            p.is_discharge_doctor= rs.getString(dbObj.is_discharge_doctor);
            p.is_discharge_ipd = rs.getString(dbObj.is_discharge_ipd);
            p.is_discharge_money = rs.getString(dbObj.is_discharge_money);
            p.doctor_dx = rs.getString(dbObj.doctor_dx);
            p.observe_user = rs.getString(dbObj.observe_user);
            p.stat_dx = rs.getString(dbObj.stat_dx);
            p.begin_admit_time = rs.getString(dbObj.begin_admit_time);
            p.deny_allergy = rs.getString(dbObj.deny_allergy);
            p.visit_patient_self_doctor = rs.getString(dbObj.visit_patient_self_doctor);
            p.is_pcu_service = rs.getString(dbObj.is_pcu_service);
            p.is_hospital_service = rs.getString(dbObj.is_hospital_service);
            p.patient_age = rs.getString(dbObj.patient_age);
            p.queue_lab_status = rs.getString(dbObj.queue_lab_status);
            p.is_first = rs.getString(dbObj.is_first);
            p.refer_cause = rs.getString(dbObj.refer_cause);
            p.emergency = rs.getString(dbObj.emergency);
            p.emergency_staff = rs.getString(dbObj.emergency_staff);
            p.ncd = rs.getString(dbObj.ncd);
            p.ncd_group = rs.getString(dbObj.ncd_group);
            p.have_appointment = rs.getString(dbObj.have_appointment);
            p.have_admit = rs.getString(dbObj.have_admit);
            p.have_refer = rs.getString(dbObj.have_refer);
            p.appointment_id = rs.getString(dbObj.appointment_id);
            p.cal_date_appointment = rs.getString(dbObj.cal_date_appointment);
            p.cause_appointment = rs.getString(dbObj.cause_appointment);
            p.visit_record_date_time = rs.getString(dbObj.visit_record_date_time);
            p.visit_record_staff = rs.getString(dbObj.visit_record_staff);
            p.service_location = rs.getString(dbObj.service_location);
            p.bVisitBedId = rs.getString(dbObj.bVisitBedId);    //+1
            p.bVisitRoomId = rs.getString(dbObj.bVisitRoomId);  //+1
//            p.visit_financial_record_date_time = rs.getString(dbObj.visit_financial_record_date_time);
//            p.visit_financial_record_staff = rs.getString(dbObj.visit_financial_record_staff);
            return true;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public String eQueryVisit(String sql) throws Exception
    {
        String p = null;
        
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        while(rs.next())
        {
            p = rs.getString(dbObj.vn);
        }
        rs.close();
        rs = null;
        return p;
    }
    
    
    /**
     * @deprecated bad hn and patient_id field value
     * @param hn
     * @return
     * @throws Exception
     */
    public Vector selectVnByHn(String hn) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table        )
	.append( " where " )
	.append( dbObj.patient_id ).append( " = '" ).append( hn ).append( "' ")
        .append( " and " ).append( dbObj.visit_type ).append( " <>'S'" )
        .append(" order by " )
	.append( dbObj.begin_visit_time )
	.append( " desc");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /**
     * @deprecated bad hn and patient_id field value
     * @param hn
     * @return
     * @throws Exception
     */
    public Vector selectVnByHnAndNCD(String hn) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table        )
	.append( " where " ).append( dbObj.patient_id ).append( " = '")	.append( hn )
	.append( "' and " ).append( dbObj.ncd ).append( " = '1' ")
	.append( "order by " ).append( dbObj.begin_visit_time )
	.append( " desc");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public Vector selectVisitByPatientID(String patient_id) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table)
	.append( " where " ).append( dbObj.patient_id ).append( " = '").append( patient_id ).append( "'")
//        .append( " and " ).append( dbObj.visit_type ).append( " ='0'" )// Somprasong 090810 ������ʴ���� OPD& IPD
        .append(" order by " ).append( dbObj.vn ).append( " desc");
        return eQuery(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAnByPatientId(String hn) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table)
	.append( " where " ).append( dbObj.patient_id ).append( " = '").append( hn )
	.append( "' and " ).append( dbObj.visit_type ).append( " = '1' ");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /**
     * @deprecated ��͹�˹��
     * @return
     * @throws Exception
     */
    public Vector selectWhereForLife() throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table
        )
	.append( " where " )
	.append( dbObj.visit_status )
	.append( " = 1 and "
        )
	.append( dbObj.locking )
	.append( " = 1 "
        )
	.append( " order by " )
	.append( dbObj.vn);
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /**
     * @deprecated ������ѧ�ѹ����鴢Ҵ Out of Memory
     * @return
     * @throws Exception
     */
    public Vector selectAll() throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table);
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public int updateVisitStatus(Visit visit) throws Exception
    {
        StringBuffer sql = new StringBuffer("Update " )
	.append( dbObj.table )
	.append( " set ")
	.append( dbObj.visit_status )
	.append( " = '" )
	.append( visit.visit_status)
	.append( "'")
	.append( " where " )
	.append( dbObj.pk_field )
	.append( " = '" )
	.append( visit.getObjectId() )
	.append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    /**
     * @deprecated henbe unused old usage
     */
    public void alterTableFieldToV2B82()
    {
        try{
        StringBuffer sql = new StringBuffer(  "ALTER TABLE  " )
	.append( dbObj.table )
	.append( "  ADD COLUMN  " )
                    
	.append( dbObj.visit_patient_self_doctor )
	.append( " varchar (255) Default '' ");
            this.theConnectionInf.eUpdate(sql.toString());
        }
        catch(Exception ex)
        {
        }
    }
    
      public int insert(Visit p) throws Exception
    {
//          p.doctor_dx = Gutil.CheckReservedWords(p.doctor_dx);
//          //p.doctor_note = Gutil.CheckReservedWords(p.doctor_note);
//          p.diagnosis_note = Gutil.CheckReservedWords(p.diagnosis_note);

        p.generateOID(idtable);
        StringBuffer sql = new StringBuffer("insert into " )
	.append( dbObj.table )
	.append( " ("
        )
	.append( dbObj.pk_field
        )
	.append( " ,"	)
	.append( dbObj.hn
        )
	.append( " ,"	)
	.append( dbObj.vn
        )
	.append( " ,"	)
	.append( dbObj.an
        )
	.append( " ,"	)
	.append( dbObj.patient_id
        )
	.append( " ,"	)
	.append( dbObj.visit_type
        )
	.append( " ,"	)
	.append( dbObj.begin_visit_time
        )
	.append( " ,"	)
	.append( dbObj.financial_discharge_time
        )
	.append( " ,"	)
	.append( dbObj.visit_note
        )
	.append( " ,"	)
	.append( dbObj.refer_in
        )
	.append( " ,"	)
	.append( dbObj.refer_out
        )
	.append( " ,"	)
	.append( dbObj.diagnosis_note
        )
	.append( " ,"	)
	.append( dbObj.discharge_opd_status
        )
	.append( " ,"	)
	.append( dbObj.discharge_ipd_type
        )
	.append( " ,"	)
	.append( dbObj.discharge_ipd_status
        
        /*)
	.append( " ,"	)
	.append( dbObj.admisstion*/
        )
	.append( " ,"	)
	.append( dbObj.locking
        )
	.append( " ,"	)
	.append( dbObj.lock_user
        )
	.append( " ,"	)
	.append( dbObj.lock_time
        )
	.append( " ,"	)
	.append( dbObj.visit_status
        )
	.append( " ,"	)
	.append( dbObj.pregnant
        )

	.append( " ,"	)
	.append( dbObj.admit_clinic
        )
	.append( " ,"	)
	.append( dbObj.ward
        )
	.append( " ,"	)
	.append( dbObj.bed
        )
	.append( " ,"	)
	.append( dbObj.observe
        )
	.append( " ,"	)
	.append( dbObj.visit_clinic
        )
	.append( " ,"	)
	.append( dbObj.queue
        )
	.append( " ,"	)
	.append( dbObj.service
        )
	.append( " ,"	)
	.append( dbObj.observe_user
        )
	.append( " ,"	)
	.append( dbObj.doctor_dx
        )
	.append( " ,"	)
	.append( dbObj.is_discharge_ipd
        )
	.append( " ,"	)
	.append( dbObj.is_discharge_money
        )
	.append( " ,"	)
	.append( dbObj.doctor_discharge_user
        )
	.append( " ,"	)
	.append( dbObj.financial_discharge_user
        )
	.append( " ,"	)
	.append( dbObj.doctor_discharge_time
        )
	.append( " ,"	)
	.append( dbObj.is_discharge_doctor
        )
	.append( " ,"	)
	.append( dbObj.begin_admit_time
        )
	.append( " ,"  )
	.append( dbObj.stat_dx
        )
	.append( " ,"	)
	.append( dbObj.deny_allergy
        )
	.append( " ,"	)
	.append( dbObj.visit_patient_self_doctor
        )
	.append( " ,"	)
	.append( dbObj.is_pcu_service
        )
	.append( " ,"	)
	.append( dbObj.is_hospital_service
        )
	.append( " ,"	)
	.append( dbObj.is_first
        )
	.append( " ,"	)
	.append( dbObj.patient_age
        )
	.append( " ,"	)
	.append( dbObj.queue_lab_status
        )
	.append( " ,"  )
	.append( dbObj.refer_cause
        )
	.append( " ,"  )
	.append( dbObj.emergency
        )
	.append( " ,"  )
	.append( dbObj.emergency_staff
        )
	.append( " ,"  )
	.append( dbObj.ncd
        )
	.append( " ,"  )
	.append( dbObj.ncd_group
        )
	.append( " ,"  )
	.append( dbObj.have_appointment
        )
	.append( " ,"  )
	.append( dbObj.have_admit
        )
	.append( " ,"  )
	.append( dbObj.have_refer
        )
	.append( " ,"  )
	.append( dbObj.appointment_id
        )
	.append( " ,"  )
	.append( dbObj.cal_date_appointment
        )
	.append( " ,"  )
	.append( dbObj.cause_appointment
        )
        .append( " ,"  )
	.append( dbObj.visit_record_date_time
        )
        .append( " ,"  )
	.append( dbObj.visit_record_staff
        )
        .append( " ,"  )
	.append( dbObj.service_location
        )
	.append( " ) values ('"
        )
	.append( p.getObjectId()
        )
	.append( "','" )
	.append( p.hn
        )
	.append( "','" )
	.append( p.vn
        )
	.append( "','" )
	.append( p.an
        )
	.append( "','" )
	.append( p.patient_id
        )
	.append( "','" )
	.append( p.visit_type
        )
	.append( "','" )
	.append( p.begin_visit_time
        )
	.append( "','" )
	.append( p.financial_discharge_time
        )
	.append( "','" )
	.append( p.visit_note
        )
	.append( "','" )
	.append( p.refer_in
        )
	.append( "','" )
	.append( p.refer_out
        )
	.append( "','" )
	.append( Gutil.CheckReservedWords(p.diagnosis_note)
        )
	.append( "','" )
	.append( p.discharge_opd_status
        )
	.append( "','" )
	.append( p.discharge_ipd_type
        )
	.append( "','" )
	.append( p.discharge_ipd_status
        )
	.append( "','" )
	.append( p.locking
        )
	.append( "','" )
	.append( p.lock_user
        )
	.append( "','" )
	.append( p.lock_time
        )
	.append( "','" )
	.append( p.visit_status
        )
	.append( "','" )
	.append( p.pregnant
        )
	.append( "','" )
	.append( p.admit_clinic
        )
	.append( "','" )
	.append( p.ward
        )
	.append( "','" )
	.append( p.bed
        )
	.append( "','" )
	.append( p.observe
        )
	.append( "','" )
	.append( p.visit_clinic
        )
	.append( "','" )
	.append( p.queue
        )
	.append( "','" )
	.append( p.service
        )
	.append( "','" )
	.append( p.observe_user
        )
	.append( "','"	)
	.append( Gutil.CheckReservedWords(p.doctor_dx)
        )
	.append( "','"	)
	.append( p.is_discharge_ipd
        )
	.append( "','"	)
	.append( p.is_discharge_money
        )
	.append( "','"	)
	.append( p.doctor_discharge_user
        )
	.append( "','"	)
	.append( p.financial_discharge_user
        )
	.append( "','"	)
	.append( p.doctor_discharge_time
        )
	.append( "','"	)
	.append( p.is_discharge_doctor
        )
	.append( "','"	)
	.append( p.begin_admit_time
        )
	.append( "','" )
	.append( p.stat_dx
        )
	.append( "','" )
	.append( p.deny_allergy
        )
	.append( "','"	)
	.append( p.visit_patient_self_doctor

        )
	.append( "','"	)
	.append( p.is_pcu_service
        )
	.append( "','"	)
	.append( p.is_hospital_service
        )
	.append( "','"	)
	.append( p.is_first
        )
	.append( "','"	)
	.append( p.patient_age
        )
	.append( "','"	)
	.append( p.queue_lab_status
        )
	.append( "','"  )
	.append( p.refer_cause
        )
	.append( "','"  )
	.append( p.emergency
        )
	.append( "','"  )
	.append( p.emergency_staff
        )
	.append( "','"  )
	.append( p.ncd
        )
	.append( "','"  )
	.append( p.ncd_group
        )
	.append( "','"  )
	.append( p.have_appointment
        )
	.append( "','"  )
	.append( p.have_admit
        )
	.append( "','"  )
	.append( p.have_refer
        )
	.append( "','"  )
	.append( p.appointment_id
        )
	.append( "','"  )
	.append( p.cal_date_appointment
        )
	.append( "','"  )
	.append( p.cause_appointment
        )
        .append( "','"  )
	.append( p.visit_record_date_time
        )
        .append( "','"  )
	.append( p.visit_record_staff
        )
        .append( "','"  )
	.append( p.service_location
        )
	.append( "')");
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public int update(Visit p) throws Exception
    {
//          p.doctor_dx = Gutil.CheckReservedWords(p.doctor_dx);
//          //p.doctor_note = Gutil.CheckReservedWords(p.doctor_note);
//          p.diagnosis_note = Gutil.CheckReservedWords(p.diagnosis_note);
//          p.cause_appointment = Gutil.CheckReservedWords(p.cause_appointment);
        StringBuffer sql = new StringBuffer("update " )
	.append( dbObj.table )
	.append( " set "
        )
	.append( dbObj.hn )
	.append( "='" )
	.append( p.hn
        )
	.append( "', " )
	.append( dbObj.vn )
	.append( "='" )
	.append( p.vn
        )
	.append( "', " )
	.append( dbObj.an )
	.append( "='" )
	.append( p.an
        )
	.append( "', " )
	.append( dbObj.patient_id )
	.append( "='" )
	.append( p.patient_id
        )
	.append( "', " )
	.append( dbObj.visit_type )
	.append( "='" )
	.append( p.visit_type
        )
	.append( "', " )
	.append( dbObj.begin_visit_time )
	.append( "='" )
	.append( p.begin_visit_time
        )
	.append( "', " )
	.append( dbObj.financial_discharge_time )
	.append( "='" )
	.append( p.financial_discharge_time
        )
	.append( "', " )
	.append( dbObj.visit_note )
	.append( "='" )
	.append( p.visit_note
        )
	.append( "', " )
	.append( dbObj.refer_in )
	.append( "='" )
	.append( p.refer_in
        )
	.append( "', " )
	.append( dbObj.refer_out )
	.append( "='" )
	.append( p.refer_out
        )
	.append( "', " )
	.append( dbObj.diagnosis_note )
	.append( "='" )
	.append( Gutil.CheckReservedWords(p.diagnosis_note)
        )
	.append( "', " )
	.append( dbObj.discharge_opd_status )
	.append( "='" )
	.append( p.discharge_opd_status
        )
	.append( "', " )
	.append( dbObj.discharge_ipd_type )
	.append( "='" )
	.append( p.discharge_ipd_type
        )
	.append( "', " )
	.append( dbObj.discharge_ipd_status )
	.append( "='" )
	.append( p.discharge_ipd_status
        /*)
	.append( "', " )
	.append( dbObj.admisstion )
	.append( "='" )
	.append( p.admisstion*/
        )
	.append( "', " )
	.append( dbObj.locking )
	.append( "='" )
	.append( p.locking
        )
	.append( "', " )
	.append( dbObj.lock_user )
	.append( "='" )
	.append( p.lock_user
        )
	.append( "', " )
	.append( dbObj.lock_time )
	.append( "='" )
	.append( p.lock_time
        )
	.append( "', " )
	.append( dbObj.visit_status )
	.append( "='" )
	.append( p.visit_status
        )
	.append( "', " )
	.append( dbObj.pregnant )
	.append( "='" )
	.append( p.pregnant
        )
	.append( "', " )
	.append( dbObj.admit_clinic )
	.append( "='" )
	.append( p.admit_clinic
        )
	.append( "', " )
	.append( dbObj.ward )
	.append( "='" )
	.append( p.ward
        )
	.append( "', " )
	.append( dbObj.bed )
	.append( "='" )
	.append( p.bed
        )
	.append( "', " )
	.append( dbObj.observe )
	.append( "='" )
	.append( p.observe
        )
	.append( "', " )
	.append( dbObj.visit_clinic )
	.append( "='" )
	.append( p.visit_clinic
        )
	.append( "', " )
	.append( dbObj.queue )
	.append( "='" )
	.append( p.queue
        )
	.append( "', " )
	.append( dbObj.service )
	.append( "='" )
	.append( p.service
        )
	.append( "', "	)
	.append( dbObj.is_discharge_ipd )
	.append( "='" )
	.append( p.is_discharge_ipd
        )
	.append( "', "	)
	.append( dbObj.is_discharge_money )
	.append( "='" )
	.append( p.is_discharge_money
        )
	.append( "', "	)
	.append( dbObj.doctor_discharge_user  )
	.append( "='" )
	.append( p.doctor_discharge_user
        )
	.append( "', "	)
	.append( dbObj.financial_discharge_user  )
	.append( "='" )
	.append( p.financial_discharge_user
        )
	.append( "', "	)
	.append( dbObj.doctor_discharge_time  )
	.append( "='" )
	.append( p.doctor_discharge_time
        )
	.append( "', "	)
	.append( dbObj.is_discharge_doctor  )
	.append( "='" )
	.append( p.is_discharge_doctor
        )
	.append( "', "	)
	.append( dbObj.doctor_dx  )
	.append( "='" )
	.append( Gutil.CheckReservedWords(p.doctor_dx)
        )
	.append( "', "	)
	.append( dbObj.observe_user  )
	.append( "='" )
	.append( p.observe_user
        )
	.append( "', "	)
	.append( dbObj.begin_admit_time  )
	.append( "='" )
	.append( p.begin_admit_time
        )
	.append( "', " )
	.append( dbObj.stat_dx )
	.append( "='" )
	.append( p.stat_dx
        )
	.append( "', " )
	.append( dbObj.deny_allergy )
	.append( "='" )
	.append( p.deny_allergy
        )
	.append( "', " )
	.append( dbObj.visit_patient_self_doctor )
	.append( "='" )
	.append( p.visit_patient_self_doctor
        )
	.append( "', " )
	.append( dbObj.is_pcu_service )
	.append( "='" )
	.append( p.is_pcu_service
        )
	.append( "', " )
	.append( dbObj.is_hospital_service )
	.append( "='" )
	.append( p.is_hospital_service
        )
	.append( "', " )
	.append( dbObj.is_first )
	.append( "='" )
	.append( p.is_first
        )
	.append( "', " )
	.append( dbObj.patient_age )
	.append( "='" )
	.append( p.patient_age
        )
	.append( "', " )
	.append( dbObj.queue_lab_status )
	.append( "='" )
	.append( p.queue_lab_status
        )
	.append( "', " )
	.append( dbObj.refer_cause )
	.append( "='" )
	.append( p.refer_cause
        )
	.append( "', " )
	.append( dbObj.emergency )
	.append( "='" )
	.append( p.emergency
        )
	.append( "', " )
	.append( dbObj.emergency_staff )
	.append( "='" )
	.append( p.emergency_staff
        )
	.append( "', " )
	.append( dbObj.ncd )
	.append( "='" )
	.append( p.ncd
        )
	.append( "', " )
	.append( dbObj.ncd_group )
	.append( "='" )
	.append( p.ncd_group
        )
	.append( "', " )
	.append( dbObj.have_appointment )
	.append( "='" )
	.append( p.have_appointment
        )
	.append( "', " )
	.append( dbObj.have_admit )
	.append( "='" )
	.append( p.have_admit
        )
	.append( "', " )
	.append( dbObj.have_refer )
	.append( "='" )
	.append( p.have_refer
        )
	.append( "', " )
	.append( dbObj.appointment_id  )
	.append( "='" )
	.append( p.appointment_id
        )
	.append( "', " )
	.append( dbObj.cal_date_appointment  )
	.append( "='" )
	.append( p.cal_date_appointment
        )
	.append( "', " )
	.append( dbObj.cause_appointment  )
	.append( "='" )
	.append( Gutil.CheckReservedWords(p.cause_appointment)
        )
        .append( "', " )
	.append( dbObj.visit_financial_record_date_time  )
	.append( "='" )
	.append( p.visit_financial_record_date_time
        )
        .append( "', " )
	.append( dbObj.visit_financial_record_staff  )
	.append( "='" )
	.append( p.visit_financial_record_staff
        )
        .append( "', " )
	.append( dbObj.service_location  )
	.append( "='" )
	.append( p.service_location
        ).append( "', " ).append( dbObj.bVisitBedId  ).append( "='" ).append( p.bVisitBedId//  +1
        ).append( "', " ).append( dbObj.bVisitRoomId  ).append( "='" ).append( p.bVisitRoomId//  +1
        )
	.append( "' where " )
	.append( dbObj.pk_field )
	.append( "='" )
	.append( p.getObjectId() )
	.append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }    
    
   public int updateAge(Visit p) throws Exception
    {
        StringBuffer sql = new StringBuffer("update " )
	.append( dbObj.table )
	.append( " set "
         )
	.append( dbObj.patient_age )
	.append( "='" )
	.append( p.patient_age
        )
	.append( "' where " )
	.append( dbObj.pk_field )
	.append( "='" )
	.append( p.getObjectId() )
	.append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }     
    public int updateAdmit(Visit p) throws Exception
    {
        StringBuffer sql = new StringBuffer("update " )
	.append( dbObj.table )
	.append( " set "
        )
	.append( "   " )
	.append( dbObj.admit_clinic )
	.append( "='" )
	.append( p.admit_clinic
        )
	.append( "', " )
	.append( dbObj.ward )
	.append( "='" )
	.append( p.ward
        )
	.append( "', " )
	.append( dbObj.bed )
	.append( "='" )
	.append( p.bed
        )
	.append( "', " )
	.append( dbObj.visit_patient_self_doctor )
	.append( "='" )
	.append( p.visit_patient_self_doctor
        )
	.append( "', " )
	.append( dbObj.visit_type )
	.append( "='" )
	.append( p.visit_type
        )
	.append( "', " )
	.append( dbObj.an )
	.append( "='" )
	.append( p.an
        )
	.append( "', "	)
	.append( dbObj.begin_admit_time  )
	.append( "='" )
	.append( p.begin_admit_time
        )
	.append( "', " )
	.append( dbObj.vn )
	.append( "='" )
	.append( p.vn
        )
	.append( "' where " )
	.append( dbObj.pk_field )
	.append( "='" )
	.append( p.getObjectId() )
	.append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }     
    public int updateDropVisit(Visit p) throws Exception
    {
        StringBuffer sql = new StringBuffer("update " )
	.append( dbObj.table )
	.append( " set "
        )
	.append( "   " )
	.append( dbObj.is_discharge_money )
	.append( "='" )
	.append( p.is_discharge_money
        )
	.append( "', " )
	.append( dbObj.is_discharge_doctor )
	.append( "='" )
	.append( p.is_discharge_doctor
        )
	.append( "', " )
	.append( dbObj.locking )
	.append( "='" )
	.append( p.locking
        )
	.append( "', " )
	.append( dbObj.financial_discharge_user )
	.append( "='" )
	.append( p.financial_discharge_user
        )
	.append( "', " )
	.append( dbObj.financial_discharge_time )
	.append( "='" )
	.append( p.financial_discharge_time
        )
	.append( "', " )
	.append( dbObj.visit_status )
	.append( "='" )
	.append( p.visit_status
        )
	.append( "' where " )
	.append( dbObj.pk_field )
	.append( "='" )
	.append( p.getObjectId() )
	.append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }        
    public int updateRefer(Visit p) throws Exception
    {
        StringBuffer sql = new StringBuffer("update " )
	.append( dbObj.table )
	.append( " set "
        )
	.append( "   " )
	.append( dbObj.refer_out )
	.append( "='" )
	.append( p.refer_out
        )
	.append( "', " )
	.append( dbObj.refer_in )
	.append( "='" )
	.append( p.refer_in
        )
	.append( "' where " )
	.append( dbObj.pk_field )
	.append( "='" )
	.append( p.getObjectId() )
	.append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    } 
    public int updateNCD(Visit p) throws Exception
    {
        StringBuffer sql = new StringBuffer("update " )
	.append( dbObj.table )
	.append( " set "
        )
	.append( "   " )
	.append( dbObj.ncd )
	.append( "='" )
	.append( p.ncd
        )
	.append( "', " )
	.append( dbObj.ncd_group )
	.append( "='" )
	.append( p.ncd_group
        )
	.append( "' where " )
	.append( dbObj.pk_field )
	.append( "='" )
	.append( p.getObjectId() )
	.append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    public int updateObserv(Visit p) throws Exception
    {
        StringBuffer sql = new StringBuffer("update " )
	.append( dbObj.table )
	.append( " set "
        )
	.append( "   " )
	.append( dbObj.observe )
	.append( "='" )
	.append( p.observe
        )
	.append( "', " )
	.append( dbObj.observe_user )
	.append( "='" )
	.append( p.observe_user
        )
	.append( "' where " )
	.append( dbObj.pk_field )
	.append( "='" )
	.append( p.getObjectId() )
	.append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }  
    public int updateDischargeFinancial(Visit p) throws Exception
    {
        StringBuffer sql = new StringBuffer("update " )
	.append( dbObj.table )
	.append( " set "
        )
	.append( "   " )
	.append( dbObj.visit_status )
	.append( "='" )
	.append( p.visit_status
        )
	.append( "', " )
	.append( dbObj.is_discharge_money )
	.append( "='" )
	.append( p.is_discharge_money
        )
	.append( "', " )
	.append( dbObj.financial_discharge_user )
	.append( "='" )
	.append( p.financial_discharge_user
        )
	.append( "', " )
	.append( dbObj.financial_discharge_time )
	.append( "='" )
	.append( p.financial_discharge_time
        )
	.append( "', " )
	.append( dbObj.locking )
	.append( "='" )
	.append( p.locking 
        )
	.append( "' where " )
	.append( dbObj.pk_field )
	.append( "='" )
	.append( p.getObjectId() )
	.append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }  
    public int updateDischargeDoctor(Visit p) throws Exception
    {
        StringBuffer sql = new StringBuffer("update " )
	.append( dbObj.table )
	.append( " set "
        )
	.append( "   " )
	.append( dbObj.is_discharge_doctor )
	.append( "='" )
	.append( p.is_discharge_doctor
        )
	.append( "', " )
	.append( dbObj.doctor_discharge_time )
	.append( "='" )
	.append( p.doctor_discharge_time
        )
	.append( "', " )
	.append( dbObj.doctor_discharge_user )
	.append( "='" )
	.append( p.doctor_discharge_user
        )
	.append( "', " )
	.append( dbObj.discharge_ipd_type )
	.append( "='" )
	.append( p.discharge_ipd_type
        )
	.append( "', " )
	.append( dbObj.discharge_opd_status )
	.append( "='" )
	.append( p.discharge_opd_status
        )
	.append( "', " )
	.append( dbObj.discharge_ipd_status )
	.append( "='" )
	.append( p.discharge_ipd_status
        )
	.append( "', " )
	.append( dbObj.is_discharge_ipd )
	.append( "='" )
	.append( p.is_discharge_ipd
        )
	.append( "', " )
	.append( dbObj.visit_status )
	.append( "='" )
	.append( p.visit_status
        )
	.append( "', " )
	.append( dbObj.refer_out )
	.append( "='" )
	.append( p.refer_out
        )
	.append( "', " )
	.append( dbObj.locking )
	.append( "='" )
	.append( p.locking
        )
	.append( "' where " )
	.append( dbObj.pk_field )
	.append( "='" )
	.append( p.getObjectId() )
	.append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }         
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectLocking(String str1,String str2) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table )
	.append(" where (" )
	.append( dbObj.visit_status )
	.append( " like '" )
	.append( str1 )
	.append( "'" )
	.append(" or " )
	.append( dbObj.visit_status )
	.append( " like '" )
	.append( str2 )
	.append( "')" )
	.append(" and " )
	.append( dbObj.locking )
	.append( " = '1' " )
	.append( " order by " )
	.append( dbObj.begin_visit_time )
	.append( " desc  limit 500");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)         return null;
        else                    return v;
    }
     /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectLocking() throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table )
	.append( " where " )
	.append( dbObj.visit_status )
	.append( " like '%' and " )
	.append( dbObj.locking )
	.append( " = 1 " )
	.append( " order by " )
	.append( dbObj.begin_visit_time )
	.append( " desc  limit 100");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)         return null;
        else                    return v;
    }
     /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectLocking(String hn) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table      )
	.append( " where " )
	.append( dbObj.locking )
	.append( " = 1 and "   )
	.append( dbObj.hn )
	.append(" like '%" )
	.append( hn )
	.append( "'"     )
	.append( " order by " )
	.append( dbObj.vn )
	.append( " desc ");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)         return null;
        else                    return v;
    }
    /*//////////////////////////////////////////////////////////////////////////////////*/
    public int cleanTrans(Visit o,String user,String date_time) throws Exception
    {
        StringBuffer sql = new StringBuffer("Update " )
	.append( dbObj.table )
	.append( " Set "   )
	.append(  dbObj.visit_status )
	.append( " = '2' ,"
        )
	.append( dbObj.is_discharge_money )
	.append( " = '1' ,"
        )
	.append( dbObj.locking )
	.append( " = '0' ,"
        )
	.append( dbObj.financial_discharge_time )
	.append( " = '")
	.append( date_time  )
	.append( "',"
        )
	.append( dbObj.financial_discharge_user )
	.append( " = '" )
	.append( user )
	.append( "'"
        )
	.append( " where " )
	.append( dbObj.visit_type )
	.append( " = '0' "
        )
	.append( " and " )
	.append( dbObj.visit_status )
	.append( "='1' "
        )
	.append( " and " )
	.append( dbObj.observe )
	.append( "<> '1'"
        )
	.append( " and " )
	.append( dbObj.pk_field  )
	.append( "='" )
	.append( o.getObjectId() )
	.append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    /*//////////////////////////////////////////////////////////////////////////////////*/
    /**
     * @deprecated
     */
    public Visit selectStatByHN(String pk) throws Exception
    {
        return selectStatByPtid(pk);
    }
    /**
     *@deprecated ��駪��ͼԴ�ٻẺ��ͧ�к���Ҥ��¿�Ŵ��˹
     **/
    public Visit selectStatByPtid(String pk) throws Exception
    {   
        return selectStatByPtid(pk,VisitStatus.isInStat());
    }
    public Visit selectStatByPtid(String patient_id,String visit_status) throws Exception
    {   
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table   )
	.append( " where " ).append( dbObj.patient_id ).append( " = '" ).append( patient_id ).append( "'")
	.append( " and " ).append( dbObj.visit_status ).append( " = '" ).append(  visit_status).append( "' ")
        .append( " and " ).append( dbObj.visit_type ).append( " <>'S'" )
	.append( " order by " )	.append( dbObj.begin_visit_time ).append( " DESC ");

        Vector v=eQuery(sql.toString());
        if(v.size()==0) return null;
        else            return (Visit)v.get(0);
    }
    //////////////////////////////////////////////////////////////////////////////////////
   public Vector selectVnByDateHn(String from ,String to,String hn) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " )
	.append( dbObj.table    )
	.append( " where " ).append( dbObj.patient_id ).append( " = '" ).append( hn )
	.append( "' and (" ).append( dbObj.begin_visit_time ).append( " >= '" ).append(from).append(",00:00:00" )
	.append( "' and " ).append( dbObj.begin_visit_time ).append( " <= '" ).append(to).append(",23:59:59" )
	.append( "') order by " ).append( dbObj.begin_visit_time ).append( " desc");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return v;
    }
   
    ////////////////////////////////////////////////////////////////////////////
    public int updateLabStatus(Visit p) throws Exception
    {   
        StringBuffer sql = new StringBuffer("UPDATE " )
	.append( dbObj.table )
	.append( " SET " )
	.append( dbObj.queue_lab_status )
	.append( "='" )
	.append( p.queue_lab_status )
	.append( "'" )
	.append(" WHERE " )
	.append( dbObj.pk_field )
	.append( "='" )
	.append( p.getObjectId() )
	.append("'");

        return theConnectionInf.eUpdate(sql.toString());
    }
    ////////////////////////////////////////////////////////////////////////////
    public int updateLocking(Visit p) throws Exception
    {   
        StringBuffer sql = new StringBuffer("UPDATE " )
	.append( dbObj.table )
	.append(" SET " )
	.append( dbObj.locking )
	.append( "='" )
	.append( p.locking )
	.append( "'" )
	.append(" , " )
	.append( dbObj.lock_user )
	.append( "='" )
	.append( p.lock_user )
	.append( "'" )
	.append( " , " )
	.append( dbObj.lock_time )
	.append( "='" )
	.append( p.lock_time )
	.append( "'" )
	.append(" WHERE " )
	.append( dbObj.pk_field )
	.append( "='" )
	.append( p.getObjectId() )
	.append("'");

        return theConnectionInf.eUpdate(sql.toString());
    }
    ////////////////////////////////////////////////////////////////////////////
    public int updateDenyAllergy(Visit p) throws Exception
    {   
        StringBuffer sql = new StringBuffer("UPDATE " )
	.append( dbObj.table )
	.append(" SET " )
	.append( dbObj.deny_allergy )
	.append( "='" )
	.append( p.deny_allergy )
	.append( "'" )
	.append( " WHERE " )
	.append( dbObj.pk_field )
	.append( "='" )
	.append( p.getObjectId() )
	.append("'");

        return theConnectionInf.eUpdate(sql.toString());
    }
    ////////////////////////////////////////////////////////////////////////////
    public int updateServiceStation(Visit p) throws Exception
    {   
        StringBuffer sql = new StringBuffer("UPDATE " )
	.append( dbObj.table )
	.append(
        " SET " )
	.append( dbObj.is_hospital_service )
	.append( "='" )
	.append( p.is_hospital_service)
	.append( "'" )
	.append(
        " , " )
	.append( dbObj.is_pcu_service )
	.append( "='" )
	.append( p.is_pcu_service )
	.append( "'" )
	.append(
        " WHERE " )
	.append( dbObj.pk_field )
	.append( "='" )
	.append( p.getObjectId() )
	.append("'");

        return theConnectionInf.eUpdate(sql.toString());
    }
    ////////////////////////////////////////////////////////////////////////////
    public int updateDiagnosis(Visit p) throws Exception
    {
//        System.out.println("p.doctor_dx = " + p.doctor_dx);
//        System.out.println("p.diagnosis_note = " + p.diagnosis_note);
        StringBuffer sql = new StringBuffer("UPDATE " )
	.append( dbObj.table )
	.append(
        " SET " )
	.append( dbObj.diagnosis_note )
	.append( "='" )
	.append( Gutil.CheckReservedWords(p.diagnosis_note) )
	.append( "'" )
	.append(
        " , " )
	.append( dbObj.doctor_dx )
	.append( "='" )
	.append( Gutil.CheckReservedWords(p.doctor_dx) )
	.append( "'" )
	.append(
        " , " )
	.append( dbObj.visit_patient_self_doctor )
	.append( "='" )
	.append( p.visit_patient_self_doctor )
	.append( "'" )
	.append(
        " WHERE " )
	.append( dbObj.pk_field )
	.append( "='" )
	.append( p.getObjectId() )
	.append("'");

        return theConnectionInf.eUpdate(sql.toString());
    }
   //////////////////////////////////////////////////////////////////////////////
    // update ������� field visit_have_appointment sumo 07/08/2549
    public int updateVisitAppointment(String appointment,String visit_id) throws Exception
    {   
        StringBuffer sql = new StringBuffer(" Update "  )
	.append( dbObj.table )
	.append( " set "
                )
	.append( dbObj.have_appointment )
	.append( " ='" )
	.append( appointment )
	.append( "' " )
	.append(
        " Where " )
	.append( dbObj.pk_field )
	.append( " ='" )
	.append( visit_id )
	.append( "' ");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    //////////////////////////////////////////////////////////////////////////////
    // update ������� field visit_have_admit sumo 07/08/2549
    public int updateVisitAdmit(String admit,String visit_id) throws Exception
    {   
        StringBuffer sql = new StringBuffer(" Update "  )
	.append( dbObj.table )
	.append( " set "
                )
	.append( dbObj.have_admit )
	.append( " ='" )
	.append( admit )
	.append( "' " )
	.append(
        " Where " )
	.append( dbObj.pk_field )
	.append( " ='" )
	.append( visit_id )
	.append( "' ");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    //////////////////////////////////////////////////////////////////////////////
    // update ������� field visit_have_refer sumo 07/08/2549
    public int updateVisitRefer(String refer,String visit_id) throws Exception
    {   
        StringBuffer sql = new StringBuffer(" Update "  )
	.append( dbObj.table )
	.append( " set "
                )
	.append( dbObj.have_refer )
	.append( " ='" )
	.append( refer )
	.append( "' " )
	.append(
        " Where " )
	.append( dbObj.pk_field )
	.append( " ='" )
	.append( visit_id )
	.append( "' ");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    //////////////////////////////////////////////////////////////////////////////
    // update ������� field t_patient_appointment_id,visit_cal_date_appointment ��� visit_cause_appointment sumo 08/08/2549
    public int updateVisitPatientAppointment(Visit p) throws Exception
    {   
        StringBuffer sql = new StringBuffer(" Update "  )
	.append( dbObj.table )
	.append( " set "
                )
	.append( dbObj.appointment_id )
	.append( " ='" )
	.append( p.appointment_id )
	.append( "' " )
	.append(
        " , " )
	.append( dbObj.cal_date_appointment )
	.append( "='" )
	.append( p.cal_date_appointment )
	.append( "'" )
	.append(
        " , " )
	.append( dbObj.cause_appointment )
	.append( "='" )
	.append( Gutil.CheckReservedWords(p.cause_appointment) )
	.append( "'" )
	.append(
        " Where " )
	.append( dbObj.pk_field )
	.append( " ='" )
	.append( p.getObjectId() )
	.append( "' ");
        
        return theConnectionInf.eUpdate(sql.toString());
    }

    public int updateStatusStatDxByPatient(String status,String cause, String patient_id) throws Exception
    {
        StringBuffer sql = new StringBuffer(" Update "  )
	.append( dbObj.table )
	.append( " set ")

	.append( dbObj.visit_status )

	.append( " ='" )
	.append( status )
	.append( "', " )

	.append(dbObj.stat_dx )

	.append( " ='" )
	.append( cause )
	.append( "' " )

	.append(" Where " )
                
	.append( dbObj.patient_id )
	.append( " ='" )
	.append( patient_id )
	.append( "' ");
        return theConnectionInf.eUpdate(sql.toString());
    }
}
