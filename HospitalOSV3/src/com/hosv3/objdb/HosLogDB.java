/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hosv3.objdb;

import com.hospital_os.objdb.X39DB;
import com.hospital_os.usecase.connection.*;
import com.hosv3.object.HosLog;

public class HosLogDB extends X39DB
{
    public HosLog dbObj;
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public HosLogDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = HosLog.initConfig();
        dbObjX = dbObj;
    }
}
