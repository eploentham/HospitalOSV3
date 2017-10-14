/*
 * HosDB.java
 *
 * Created on 3 ��Ȩԡ�¹ 2548, 14:22 �.
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
    
    /**��㹡�õ�Ǩ�ͺ version �ͧ Report*/
    public VersionReportDB theVersionReportDB;
    /**��㹡�õ�Ǩ�ͺ version �ͧ Health*/
    public VersionHealthDB theVersionHealthDB;
    /**��㹡�õ�Ǩ�ͺ version �ͧ HospitalOS*/
    public VersionDB theVersionDB;
    /**��㹡�õԴ��Ͱҹ������ */
    public ConnectionInf theConnectionInf;
    public HosDB(ConnectionInf connectioninf) {
        theConnectionInf = connectioninf;
        theVersionReportDB = new VersionReportDB(theConnectionInf);
        theVersionHealthDB = new VersionHealthDB(theConnectionInf);
        theVersionDB = new VersionDB(theConnectionInf);
    }
    
}
