/*
 * theVitalTemplate2DB.java
 *
 * Created on 20 กันยายน 2548, 10:48 น.
 */

package com.hosv3.objdb;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.VitalTemplateDB;
import com.hosv3.object.VitalTemplate2;
/**
 *
 * @author  kingland
 */
public class VitalTemplate2DB extends VitalTemplateDB{
    
    /** Creates a new instance of theVitalTemplate2DB */
    public VitalTemplate2DB(ConnectionInf db) {
        super(db);
    }
    public Vector eQuery(String sql) throws Exception
    {
        VitalTemplate2 p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new VitalTemplate2();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.vital_template_id = rs.getString(dbObj.vital_template_id);
            p.description = rs.getString(dbObj.description);
            p.service_point = rs.getString(dbObj.service_point);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
