/*
 * HosDB.java
 *
 * Created on 3 พฤศจิกายน 2548, 14:22 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportcenter.control;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportcenter.objdb.VersionDB;
import com.reportcenter.objdb.VersionHealthDB;
import com.reportcenter.objdb.VersionReportDB;
/**
 *
 * @author tong(Padungrat)
 */
public class HosDB {
    
    /**ใช้ในการตรวจสอบ version ของ Report*/
    public VersionReportDB theVersionReportDB;
    /**ใช้ในการตรวจสอบ version ของ Health*/
    public VersionHealthDB theVersionHealthDB;
    /**ใช้ในการตรวจสอบ version ของ HospitalOS*/
    public VersionDB theVersionDB;
    /**ใช้ในการติดต่อฐานข้อมูล */
    public ConnectionInf theConnectionInf;
    public HosDB(ConnectionInf connectioninf) {
        theConnectionInf = connectioninf;
        theVersionReportDB = new VersionReportDB(theConnectionInf);
        theVersionHealthDB = new VersionHealthDB(theConnectionInf);
        theVersionDB = new VersionDB(theConnectionInf);
    }
    
}
