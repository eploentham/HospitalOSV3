/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital_os.objdb;

import com.hospital_os.object.NotifyNote;
import com.hospital_os.usecase.connection.ConnectionInf;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Somprasong
 */
public class NotifyNoteDB {

    private ConnectionInf theConnectionInf;
    private NotifyNote dbObj;
    final public String idtable = "note1";/*"178";*/


    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public NotifyNoteDB(ConnectionInf db) {
        theConnectionInf = db;
        dbObj = new NotifyNote();
        this.initConfig();
    }

    public NotifyNote initConfig() {
        dbObj.table = "t_notify_note";
        dbObj.pk_field = "t_notify_note_id";
        dbObj.patient_hn = "t_patient_hn";
        dbObj.visit_id_rec = "t_visit_id_rec";
        dbObj.visit_id_last_view = "t_visit_id_last_view";
        dbObj.notify_type_id = "f_notify_type_id";
        dbObj.note_subject = "note_subject";
        dbObj.note_detail = "note_detail";
        dbObj.active = "active";
        dbObj.rec_staff = "rec_staff";
        dbObj.rec_datetime = "rec_datetime";
        dbObj.mod_datetime = "mod_datetime";
        dbObj.del_datetime = "del_datetime";
        dbObj.noter = "noter";
        return dbObj;
    }

    public int insert(NotifyNote p) throws Exception {
        p.generateOID(idtable);
        StringBuilder sql = new StringBuilder("insert into ");
        sql.append(dbObj.table);
        sql.append(" (");
        sql.append(dbObj.pk_field);
        sql.append(" ,");
        sql.append(dbObj.patient_hn);
        sql.append(" ,");
        sql.append(dbObj.visit_id_rec);
        sql.append(" ,");
        sql.append(dbObj.notify_type_id);
        sql.append(" ,");
        sql.append(dbObj.note_subject);
        sql.append(" ,");
        sql.append(dbObj.note_detail);
        sql.append(" ,");
        sql.append(dbObj.active);
        sql.append(" ,");
        sql.append("notify_count");
        sql.append(" ,");
        sql.append(dbObj.rec_staff);
        sql.append(" ,");
        sql.append(dbObj.rec_datetime);
        sql.append(" ) values ('");
        sql.append(p.getObjectId());
        sql.append("','");
        sql.append(p.patient_hn);
        sql.append("','");
        sql.append(p.visit_id_rec);
        sql.append("','");
        sql.append(p.notify_type_id);
        sql.append("','");
        sql.append(p.note_subject);
        sql.append("','");
        sql.append(p.note_detail);
        sql.append("','");
        sql.append(p.active);
        sql.append("',");
        sql.append(p.notify_count);
        sql.append(",'");
        sql.append(p.rec_staff);
        sql.append("','");
        sql.append(p.rec_datetime);
        sql.append("')");
        return theConnectionInf.eUpdate(sql.toString());
    }

    public int update(NotifyNote p) throws Exception {
        StringBuilder sql = new StringBuilder("update ");
        sql.append(dbObj.table);
        sql.append(" set ");
        sql.append(dbObj.notify_type_id);
        sql.append("='");
        sql.append(p.notify_type_id);
        sql.append("', ");
        sql.append(dbObj.note_subject);
        sql.append("='");
        sql.append(p.note_subject);
        sql.append("', ");
        sql.append(dbObj.note_detail);
        sql.append("='");
        sql.append(p.note_detail);
        sql.append("', ");
        sql.append(dbObj.mod_datetime);
        sql.append("='");
        sql.append(p.mod_datetime);
        sql.append("' ");
        sql.append(" where ");
        sql.append(dbObj.pk_field);
        sql.append("='");
        sql.append(p.getObjectId());
        sql.append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }

    public int inactive(NotifyNote p) throws Exception {
        StringBuilder sql = new StringBuilder("update ");
        sql.append(dbObj.table);
        sql.append(" set ");
        sql.append(dbObj.active);
        sql.append("='");
        sql.append(p.active);
        sql.append("', ");
        sql.append(dbObj.del_datetime);
        sql.append("='");
        sql.append(p.del_datetime);
        sql.append("' ");
        sql.append(" where ");
        sql.append(dbObj.pk_field);
        sql.append("='");
        sql.append(p.getObjectId());
        sql.append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }

    public int countNotify(NotifyNote p) throws Exception {
        StringBuilder sql = new StringBuilder("update ");
        sql.append(dbObj.table);
        sql.append(" set ");
        sql.append("notify_count");
        sql.append("=");
        sql.append(p.notify_count);
        sql.append(" ");
        sql.append(" where ");
        sql.append(dbObj.pk_field);
        sql.append("='");
        sql.append(p.getObjectId());
        sql.append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }

    public NotifyNote getById(String stringId) throws Exception {
        String sql = "SELECT * "
                + "FROM t_notify_note "
                + "Where t_notify_note.t_notify_note_id = '" + stringId + "' ";
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        NotifyNote object = null;
        if (rs.next()) {
            object = new NotifyNote();
            object.setObjectId(rs.getString(dbObj.pk_field));
            object.patient_hn = rs.getString(dbObj.patient_hn);
            object.visit_id_rec = rs.getString(dbObj.visit_id_rec);
            object.visit_id_last_view = rs.getString(dbObj.visit_id_last_view);
            object.notify_type_id = rs.getString(dbObj.notify_type_id);
            object.note_subject = rs.getString(dbObj.note_subject);
            object.note_detail = rs.getString(dbObj.note_detail);
            object.active = rs.getString(dbObj.active);
            object.rec_staff = rs.getString(dbObj.rec_staff);
            object.rec_datetime = rs.getString(dbObj.rec_datetime);
            object.mod_datetime = rs.getString(dbObj.mod_datetime);
            object.del_datetime = rs.getString(dbObj.del_datetime);
        }
        rs.close();

        return object;
    }

    public List<NotifyNote> listByHn(String hn) throws Exception {
        String sql = "SELECT * "
                + "FROM t_notify_note "
                + "Where t_notify_note.t_patient_hn = '" + hn + "' "
                + "Order by t_notify_note.rec_datetime desc";
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        List<NotifyNote> list = new ArrayList<NotifyNote>();
        while (rs.next()) {
            NotifyNote object = new NotifyNote();
            object.setObjectId(rs.getString(dbObj.pk_field));
            object.patient_hn = rs.getString(dbObj.patient_hn);
            object.visit_id_rec = rs.getString(dbObj.visit_id_rec);
            object.visit_id_last_view = rs.getString(dbObj.visit_id_last_view);
            object.notify_type_id = rs.getString(dbObj.notify_type_id);
            object.note_subject = rs.getString(dbObj.note_subject);
            object.note_detail = rs.getString(dbObj.note_detail);
            object.active = rs.getString(dbObj.active);
            object.rec_staff = rs.getString(dbObj.rec_staff);
            object.rec_datetime = rs.getString(dbObj.rec_datetime);
            object.mod_datetime = rs.getString(dbObj.mod_datetime);
            object.del_datetime = rs.getString(dbObj.del_datetime);
            list.add(object);
        }
        rs.close();

        return list;
    }

    public List<NotifyNote> listByHn2(String hn) throws Exception {
        String sql = "select t_notify_note.* "
                + ",b_employee.employee_firstname || ' ' || b_employee.employee_lastname as noter "
                + "from t_notify_note "
                + "inner join b_employee on b_employee.b_employee_id = t_notify_note.rec_staff "
                + "Where t_notify_note.t_patient_hn = '" + hn + "' "
                + "and t_notify_note.active = '1' "
                + "Order by t_notify_note.f_notify_type_id, t_notify_note.rec_datetime desc";
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        List<NotifyNote> list = new ArrayList<NotifyNote>();
        while (rs.next()) {
            NotifyNote object = new NotifyNote();
            object.setObjectId(rs.getString(dbObj.pk_field));
            object.patient_hn = rs.getString(dbObj.patient_hn);
            object.visit_id_rec = rs.getString(dbObj.visit_id_rec);
            object.visit_id_last_view = rs.getString(dbObj.visit_id_last_view);
            object.notify_type_id = rs.getString(dbObj.notify_type_id);
            object.note_subject = rs.getString(dbObj.note_subject);
            object.note_detail = rs.getString(dbObj.note_detail);
            object.active = rs.getString(dbObj.active);
            object.rec_staff = rs.getString(dbObj.rec_staff);
            object.rec_datetime = rs.getString(dbObj.rec_datetime);
            object.mod_datetime = rs.getString(dbObj.mod_datetime);
            object.del_datetime = rs.getString(dbObj.del_datetime);
            object.noter = rs.getString(dbObj.noter);
            list.add(object);
        }
        rs.close();

        return list;
    }

    public List<NotifyNote> listByHnAbdVisitId(String hn, String visitId) throws Exception {
        /**
         * select t_notify_note.*
        ,b_employee.employee_firstname || ' ' || b_employee.employee_lastname as noter
        from t_notify_note
        inner join b_employee on b_employee.b_employee_id = t_notify_note.rec_staff
        Where t_notify_note.t_patient_hn = '1012561'
        and t_notify_note.active = '1'
        and t_notify_note.f_notify_type_id = '2'
        or t_notify_note.t_notify_note_id in
        (select t_notify_note.t_notify_note_id from t_notify_note where t_notify_note.t_patient_hn = '1012561'
        and t_notify_note.active = '1'
        and t_notify_note.f_notify_type_id = '1'
        and (t_notify_note.t_visit_id_last_view is null or t_notify_note.t_visit_id_last_view = '255232200817899554'))

        Order by t_notify_note.rec_datetime desc;
         */
        String sql = "select t_notify_note.* "
                + ",b_employee.employee_firstname || ' ' || b_employee.employee_lastname as noter "
                + "from t_notify_note "
                + "inner join b_employee on b_employee.b_employee_id = t_notify_note.rec_staff "
                + "Where t_notify_note.t_patient_hn = '" + hn + "' "
                + "and t_notify_note.active = '1' "
                + "and t_notify_note.f_notify_type_id = '2' "
                + "or t_notify_note.t_notify_note_id in "
                + "(select t_notify_note.t_notify_note_id from t_notify_note where t_notify_note.t_patient_hn = '" + hn + "' "
                + "and t_notify_note.active = '1' "
                + "and t_notify_note.f_notify_type_id = '1' "
                + "and (t_notify_note.t_visit_id_last_view is null or t_notify_note.t_visit_id_last_view = '' or t_notify_note.t_visit_id_last_view = '" + visitId + "')) "
                + "Order by t_notify_note.f_notify_type_id, t_notify_note.rec_datetime desc";
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        List<NotifyNote> list = new ArrayList<NotifyNote>();
        while (rs.next()) {
            NotifyNote object = new NotifyNote();
            object.setObjectId(rs.getString(dbObj.pk_field));
            object.patient_hn = rs.getString(dbObj.patient_hn);
            object.visit_id_rec = rs.getString(dbObj.visit_id_rec);
            object.visit_id_last_view = rs.getString(dbObj.visit_id_last_view);
            object.notify_type_id = rs.getString(dbObj.notify_type_id);
            object.note_subject = rs.getString(dbObj.note_subject);
            object.note_detail = rs.getString(dbObj.note_detail);
            object.active = rs.getString(dbObj.active);
            object.rec_staff = rs.getString(dbObj.rec_staff);
            object.rec_datetime = rs.getString(dbObj.rec_datetime);
            object.mod_datetime = rs.getString(dbObj.mod_datetime);
            object.del_datetime = rs.getString(dbObj.del_datetime);
            object.noter = rs.getString(dbObj.noter);
            list.add(object);
        }
        rs.close();

        for(NotifyNote object : list){
            sql = "update t_notify_note set t_visit_id_last_view = '" + visitId + "' where t_notify_note_id = '" + object.getObjectId() + "'";
            theConnectionInf.eUpdate(sql);
        }


        return list;
    }

    public List<Object[]> listNotifyType() throws Exception {
        String sql = "SELECT * FROM f_notify_type order by f_notify_type_id";
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        List<Object[]> list = new ArrayList<Object[]>();

        while (rs.next()) {
            Object[] object = new Object[]{rs.getString("f_notify_type_id"), rs.getString("description")};
            list.add(object);
        }
        rs.close();

        return list;
    }
}
