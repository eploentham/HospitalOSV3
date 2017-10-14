/*
 * CheckVersionControl.java
 *
 * Created on 2 พฤศจิกายน 2548, 15:59 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.control;

import java.util.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.gui.connection.*;
import com.hospital_os.object.*;
import com.hospital_os.objdb.*;
import com.hosv3.utility.Constant;
/**
 *
 * @author tong(Padungrat)
 * @Modify Ojika
 * @Modify henbe
 */
public class CheckVersionReport extends AbsVersionControl3{

    ConnectionInf theConnectionInf;
    ////////////////////////////////////////////////////////////////////////////////
    /**ใช้ในการตรวจสอบ Version ของโปรแกรม PCU*/
    public static String VERSION0 = "";
    public static String VERSION1 = "1.00.1048";
    public static String VERSION3 = "1.03.291206";
    public static String VERSION4 = "1.04.240306";
    public static String VERSION4_1 = "1.04.24032006";
    public static String VERSION5 = "1.05.290406";
    public static String VERSION6 = "1.06.220606";
    public static String VERSION7 = "1.7.310107";
    public static String VERSION8 = "1.7.200407";

    public static String FN_VERSION0   = "database/report/report_230606.sql";
    public static String FN_VERSION1   = "database/report/report01_1148.sql";
    public static String FN_VERSION3   = "database/report/report03_1248.sql";
    public static String FN_VERSION4   = "database/report/report04_0349.sql";
    public static String FN_VERSION5   = "database/report/report05_0449.sql";
    public static String FN_VERSION6   = "database/report/report06_0649.sql";
    public static String FN_VERSION7   = "database/report/report_update_070131.sql";
    public static String FN_VERSION8   = "database/report/report08_200407.sql";

    VersionDB theReportVersionDB;

    public CheckVersionReport(ConnectionInf c) {
        theReportVersionDB = new VersionDB(c,initConfigReportVersion());
    }

    public Version initConfigReportVersion()
    {
        Version dbObj = new Version();
        dbObj.table="s_report_version";
        dbObj.pk_field="s_report_version_id";
        dbObj.version_id   ="report_version_number";
        dbObj.description   ="report_version_description";
        dbObj.app_code   ="report_version_notice";
        dbObj.db_code   ="report_version_application_number";
        dbObj.update_time="report_version_update_date_time";
        return dbObj;
    }

    public String getCurrentVersion()
    {
        System.out.println("public String getCurrentVersion()");
//        if(this.current_version!=null)
//            return current_version;
        try{
            Version ver = theReportVersionDB.selectCurrentVersion();
            current_version = ver.db_code;
        }
        catch(Exception e){
            current_version = "";
            e.printStackTrace(Constant.getPrintStream());
        }
        System.out.println(current_version);
        return current_version;
    }



    public Vector getFileUpdate(int index) {

        Vector v = new Vector();
        if(index==0)
        {
            v.add(FN_VERSION0);
            v.add(FN_VERSION8);
        }

        else if(index==1){
            v.add(FN_VERSION3);
            v.add(FN_VERSION4);
            v.add(FN_VERSION5);
            v.add(FN_VERSION6);
            v.add(FN_VERSION7);
            v.add(FN_VERSION8);
        }
        else if(index==2){
            v.add(FN_VERSION3);
            v.add(FN_VERSION4);
            v.add(FN_VERSION5);
            v.add(FN_VERSION6);
            v.add(FN_VERSION7);
            v.add(FN_VERSION8);
        }
        else if(index==3 || index==4){
            v.add(FN_VERSION5);
            v.add(FN_VERSION6);
            v.add(FN_VERSION7);
            v.add(FN_VERSION8);
        }
        else if(index==5){
            v.add(FN_VERSION6);
            v.add(FN_VERSION7);
            v.add(FN_VERSION8);
        }
        else if(index==6){
            v.add(FN_VERSION7);
            v.add(FN_VERSION8);
        }
        else if(index==7){
            v.add(FN_VERSION8);
        }
        return v;
    }

    public java.lang.String getVersion(int index) {
        if(index==0)
            return VERSION0;
        else if(index==1)
            return VERSION1;
        else if(index==2)
            return VERSION3;
        else if(index==3)
            return VERSION4;
        else if(index==4)
            return VERSION4_1;
        else if(index==5)
            return VERSION5;
        else if(index==6)
            return VERSION6;
        else if(index==7)
            return VERSION7;
        else if(index==8)
            return VERSION8;
        else
            return null;
    }

    public int getVersionCount() {
        return 9;
    }

    public boolean isSchemaUpdate(String cur_version) {
        if(cur_version.startsWith(VERSION0))            return true;
        else if(cur_version.startsWith(VERSION1))       return true;
        else if(cur_version.startsWith(VERSION3))       return true;
        else if(cur_version.startsWith(VERSION4) || cur_version.startsWith(VERSION4_1))
            return true;
        else if(cur_version.startsWith(VERSION5))       return true;
        else if(cur_version.startsWith(VERSION6))       return true;
        else if(cur_version.startsWith(VERSION7))       return true;
        else if(cur_version.startsWith(VERSION8))       return false;
        return false;
    }

    public String getFinalVersion() {
        return VERSION8;
    }

    public Vector getFileUpdate(String cur_version) {
        Vector v = new Vector();
        if(cur_version.startsWith(VERSION0))
            v.add(FN_VERSION0);

        else if(cur_version.startsWith(VERSION1)){
            v.add(FN_VERSION3);
            v.add(FN_VERSION4);
            v.add(FN_VERSION5);
            v.add(FN_VERSION6);
            v.add(FN_VERSION7);
            v.add(FN_VERSION8);
        }
        else if(cur_version.startsWith(VERSION3)){
            v.add(FN_VERSION4);
            v.add(FN_VERSION5);
            v.add(FN_VERSION6);
            v.add(FN_VERSION7);
            v.add(FN_VERSION8);
        }
        else if(cur_version.startsWith(VERSION4) || cur_version.startsWith(VERSION4_1)){
            v.add(FN_VERSION5);
            v.add(FN_VERSION6);
            v.add(FN_VERSION7);
            v.add(FN_VERSION8);
        }
        else if(cur_version.startsWith(VERSION5)){
            v.add(FN_VERSION6);
            v.add(FN_VERSION7);
            v.add(FN_VERSION8);
        }
        else if(cur_version.startsWith(VERSION6)){
            v.add(FN_VERSION7);
            v.add(FN_VERSION8);
        }
        else if(cur_version.startsWith(VERSION7)){
            v.add(FN_VERSION8);
        }
        return v;
    }

}
