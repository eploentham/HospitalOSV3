/*
 * SpecialQueueVisitDB.java
 *
 * Created on 1 กันยายน 2547, 16:37 น.
 */
package com.hospital_os.objdb.specialQuery;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.specialQuery.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  tong
 *
 */
public class SpecialQueueDespenseDB
{
    /** Creates a new instance of SpecialQueueVisitDB */
    public ConnectionInf theConnectionInf;
    private MapQueueVisit mapQueueVisit;
    private QueueVisit queueVisit ;
    private SpecialQueueDespense dbObj;
    public SpecialQueueDespenseDB(ConnectionInf db)
    {
        theConnectionInf = db;
        mapQueueVisit = new MapQueueVisit();
        queueVisit = new QueueVisit();
        dbObj = new SpecialQueueDespense();
        initConfig();
    }
    public boolean initConfig()
    {    dbObj.t_visit_id = "t_visit_id";
         dbObj.visit_hn= "visit_hn";
         dbObj.visit_vn= "visit_vn";
         dbObj.patient_firstname= "patient_firstname";
         dbObj.patient_lastname= "patient_lastname";
         dbObj.service_point_description= "service_point_description";
         dbObj.t_visit_queue_despense_id= "t_visit_queue_despense_id";
         dbObj.assign_date_time= "assign_date_time";
         return true;
    }
    
    public Vector selectByDespense() throws Exception
    {
        String sql = "SELECT t_visit.t_visit_id, t_visit.visit_hn, t_visit.visit_vn, t_patient.patient_firstname, t_patient.patient_lastname, b_service_point.service_point_description, t_visit_queue_despense.t_visit_queue_despense_id, t_visit_queue_despense.assign_date_time " +
        " FROM ((t_visit_queue_despense INNER JOIN t_visit ON t_visit_queue_despense.t_visit_id = t_visit.t_visit_id) INNER JOIN b_service_point ON t_visit_queue_despense.b_service_point_id = b_service_point.b_service_point_id) INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id " +
        " GROUP BY t_visit.t_visit_id, t_visit.visit_hn, t_visit.visit_vn, t_patient.patient_firstname, t_patient.patient_lastname, b_service_point.service_point_description, t_visit_queue_despense.t_visit_queue_despense_id, t_visit_queue_despense.assign_date_time " +
        " ORDER BY t_visit_queue_despense.assign_date_time ";
        
        Vector vc =  eQuery(sql);
        if(vc.size() == 0)
            return null;
        else
            return vc;
    }
    
    private Vector eQuery(String sql) throws Exception
    {
        SpecialQueueDespense p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        int i = 0;
        while(rs.next())
        {
            p = new SpecialQueueDespense();
            p.t_visit_id = rs.getString(dbObj.t_visit_id);
            p.visit_hn= rs.getString(dbObj.visit_hn);
            p.visit_vn= rs.getString(dbObj.visit_vn);
            p.patient_firstname= rs.getString(dbObj.patient_firstname);
            p.patient_lastname= rs.getString(dbObj.patient_lastname);
            p.service_point_description= rs.getString(dbObj.service_point_description);
            p.t_visit_queue_despense_id= rs.getString(dbObj.t_visit_queue_despense_id);
            p.assign_date_time= rs.getString(dbObj.assign_date_time);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
