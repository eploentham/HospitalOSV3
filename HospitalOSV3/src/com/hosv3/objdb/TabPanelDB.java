/*
 * TabDB.java
 *
 * Created on 28 กรกฎาคม 2548, 16:06 น.
 */
package com.hosv3.objdb;
import com.hospital_os.usecase.connection.*;
import java.util.*;
import java.sql.*;
import com.hosv3.object.TabPanel;
/**
 *
 * @author  sumo
 */
public class TabPanelDB 
{
    public ConnectionInf theConnectionInf;
    public TabPanel dbObj;
    final public String idtable = "904";
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public TabPanelDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new TabPanel();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="f_tab";
        dbObj.pk_field="f_tab_id";
        dbObj.description="tab_description";
        return true;
    }
    
   ////////////////////////////////////////////////////////////////////
    public Vector selectAllTab() throws Exception
    {
        String sql="select * from "+ dbObj.table;
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    //////////////////////////////////////////////////////////////////
   public Vector eQuery(String sql) throws Exception
    {
        TabPanel p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new TabPanel();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
