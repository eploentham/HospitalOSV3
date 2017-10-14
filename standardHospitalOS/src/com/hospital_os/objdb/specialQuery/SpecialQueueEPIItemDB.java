/*
 * SpecialQueueEPIItemDB.java
 *
 * Created on 13 กันยายน 2547, 14:36 น.
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
 */
public class SpecialQueueEPIItemDB
{
    /** Creates a new instance of SpecialQueueEPIItemDB */
    public ConnectionInf theConnectionInf;
    private MapQueueVisit mapQueueVisit;
    private QueueVisit queueVisit ;
    private SpecialQueueVisit dbObj;
    public SpecialQueueEPIItemDB(ConnectionInf db)
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
    
    public Vector querySpecialEPIItem(String i) throws Exception
    {
        String sql = "select b_health_epi_item.b_item_id,b_health_epi_item.b_health_epi_item_id from b_health_epi_group,b_health_epi_item " +
        " where b_health_epi_item.b_health_epi_group_id = b_health_epi_group.b_health_epi_group_id " +
        " and b_health_epi_item.b_health_epi_group_id = '"+ i+ "'";
        
        Vector vc =  eQuery(sql);
        if(vc.size() == 0)
            return null;
        else
            return vc;
    }
    
    private Vector eQuery(String sql) throws Exception
    {
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        int i = 0;
        String item_id =null;
        while(rs.next())
        {
            item_id = new String();
            item_id = rs.getString("b_item_id") + "&" + rs.getString("b_health_epi_item_id");
            list.add(item_id);
        }
        rs.close();
        return list;
    }
}
