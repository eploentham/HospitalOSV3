/*
 * MoodBehaviorTypeDB.java
 *
 * Created on 10 เมษายน 2549, 12:00 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.healthy.objdb;
import com.healthy.object.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author hospitalos5
 */
public class MoodBehaviorTypeDB {
    public ConnectionInf theConnectionInf;
    public MoodBehaviorType dbObj;
    final public String idtable = "htf005";
    /**
     * Creates a new instance of MoodBehaviorTypeDB 
     */
    public MoodBehaviorTypeDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new MoodBehaviorType();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table="f_health_behav_mood";
        dbObj.pk_field="f_health_behav_mood_id";
        dbObj.description="behav_mood_name";

        return true;
    }
    
    public Vector selectAll() throws Exception
    {   Vector vc = new Vector();
        String sql ="select * from " + dbObj.table + " order by "+
        dbObj.pk_field;
        vc = veQuery(sql);
        
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
    public Vector veQuery(String sql) throws Exception
    {
        ComboFix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.description);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
