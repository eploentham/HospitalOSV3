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
//import java.net.URL;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.gui.connection.*;

/**
 *
 * @author tong(Padungrat)
 * @Modify Ojika
 * @Modify henbe
 */
public class CheckVersionHealthy extends AbsVersionControl3{
    
    ConnectionInf theConnectionInf;
    ////////////////////////////////////////////////////////////////////////////////
    /**ใช้ในการตรวจสอบ Version ของโปรแกรม PCU*/

    public static String VERSION0 = "";
    public static String VERSION1 = "0.01.0749";
    public static String FN_VERSION1 = "database/pcu/update_healthy.sql";
    HosDB theHosDB;
    private String cur_version;
    
    public CheckVersionHealthy(HosDB hosDB) {
        theHosDB = hosDB;
        theConnectionInf = hosDB.theSiteDB.theConnectionInf;
    }
    public Vector getFileUpdate(int index) {

        Vector v = new Vector();
        if(index==0)
            v.add(FN_VERSION1);
        return v;
    }

    public java.lang.String getVersion(int index) {
        if(index==0)
            return VERSION0;
        else if(index==1)
            return VERSION1;
        return null;
    }

    public int getVersionCount() {
        return 2;
    }

    public String getCurrentVersion() {
        //ตรวจสอบว่ามีตารางนี้อยู่จริงหรือเปล่านะ
        if(cur_version!=null && false)
            return cur_version;
        
        try{
            String sql = "select * from t_health_diabetes";
            java.sql.ResultSet rs = theConnectionInf.eQuery(sql);
            cur_version = VERSION1;
            return cur_version;
        }
        catch(Exception e){
            cur_version = VERSION0;
            return cur_version;
        }
    }

    public boolean isSchemaUpdate(String cur_version) {
        if(cur_version.startsWith(VERSION0))
            return true;
        return false;
    }

    public String getFinalVersion() {
        return VERSION1;
    }

    public Vector getFileUpdate(String cur_version) {
        Vector v = new Vector();
        if(cur_version.startsWith(VERSION0))
            v.add(FN_VERSION1);
        return v;
    }
}
