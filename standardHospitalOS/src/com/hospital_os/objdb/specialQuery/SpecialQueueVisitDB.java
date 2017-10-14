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
public class SpecialQueueVisitDB
{
    
    /** Creates a new instance of SpecialQueueVisitDB */
    public ConnectionInf theConnectionInf;
    private MapQueueVisit mapQueueVisit;
    private QueueVisit queueVisit ;
    private SpecialQueueVisit dbObj;
    public SpecialQueueVisitDB(ConnectionInf db)
    {
        theConnectionInf = db;
        mapQueueVisit = new MapQueueVisit();
        queueVisit = new QueueVisit();
        dbObj = new SpecialQueueVisit();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.color = "visit_queue_setup_queue_color";
        dbObj.description = "visit_queue_setup_description";
        dbObj.queue = "visit_queue_map_queue";
        return true;
    }
    
    public SpecialQueueVisit selectByVisitID(String visit_id) throws Exception
    {
        String sql = "SELECT b_visit_queue_setup.visit_queue_setup_description, b_visit_queue_setup.visit_queue_setup_queue_color, t_visit_queue_map.visit_queue_map_queue, t_visit_queue_map.t_visit_id " +
        " FROM t_visit_queue_map , b_visit_queue_setup " +
        " WHERE (((t_visit_queue_map.visit_queue_map_active)='1') " +
        " and (t_visit_queue_map.b_visit_queue_setup_id = b_visit_queue_setup.b_visit_queue_setup_id) " +
        " and t_visit_queue_map.t_visit_id = '"+ visit_id+ "')";
        Vector vc =  eQuery(sql);
        if(vc.size() == 0)
            return null;
        else
            return (SpecialQueueVisit)vc.get(0);
    }
    
    private Vector eQuery(String sql) throws Exception
    {
        SpecialQueueVisit p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        int i = 0;
        while(rs.next())
        {
            p = new SpecialQueueVisit();
            p.color  = rs.getString(dbObj.color);
            p.description = rs.getString(dbObj.description);
            p.queue =  rs.getString(dbObj.queue);
            
            list.add(p);
            
        }
        rs.close();
        return list;
    }
}
