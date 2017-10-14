/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hospital_os.objdb;

import com.hospital_os.object.AutoReportBug;
import com.hospital_os.usecase.connection.ConnectionInf;

/**
 *
 * @author LionHearth
 */
public class AutoReportBugDB extends X39DB{
    protected AutoReportBug dbObj;
    public AutoReportBugDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = AutoReportBug.initConfig();
        dbObjX = dbObj;
    }

    public AutoReportBug selectAll() throws Exception
    {
        String sql="select * from " + dbObj.table;
        return (AutoReportBug)eQueryX1(sql,null);
    }
}
