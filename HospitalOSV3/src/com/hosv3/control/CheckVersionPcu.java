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
import com.hospital_os.object.*;
/**
 *
 * @author tong(Padungrat)
 * @Modify Ojika
 * @Modify henbe
 */
public class CheckVersionPcu extends AbsVersionControl3{
    
    ConnectionInf theConnectionInf;
    String server="";
    String dbname="";
    String uname="";
    final String directory = "config/report/update";
    public StringBuffer updateResult = new StringBuffer();
    ////////////////////////////////////////////////////////////////////////////////
    /**ใช้ในการตรวจสอบ Version ของโปรแกรม PCU*/

    public static String VERSION0 = "";
    public static String VERSION2 = "0.02.1048";
    public static String VERSION3 = "0.03.1148";
    public static String VERSION4 = "0.04.0449";
    public static String FN_VERSION2 = "database/pcu/update_pcu_ph2.sql";
    public static String FN_VERSION3 = "database/pcu/update_pcu_ph3.sql";
//    public static String FN_VERSION4 = "database/pcu/update_pcu_ph5.sql";hos จะเป็นผู้จัดการเอง

    HosDB theHosDB;
    
    public CheckVersionPcu(HosDB hosDB) {
        theHosDB = hosDB;
        theConnectionInf = hosDB.theSiteDB.theConnectionInf;
    }
    public Vector getFileUpdate(int index) {

        Vector v = new Vector();
        if(index==0){
            v.add(FN_VERSION2);
            v.add(FN_VERSION3);
        }
        else if(index==1){
            v.add(FN_VERSION3);
        }
        return v;
    }

    public java.lang.String getVersion(int index) {
        if(index==0)
            return VERSION0;
        else if(index==1)
            return VERSION2;
        else if(index==2)
            return VERSION3; 
        else if(index==3)
            return VERSION4; 
        return null;
    }

    public int getVersionCount() {
        return 4;
    }

    public String getCurrentVersion() {
        //ตรวจสอบว่ามีตารางนี้อยู่จริงหรือเปล่านะ
        if(this.current_version!=null && false)
            return current_version;
        try{
            Version version = this.theHosDB.thePcuVersionDB.selectCurrentVersion();
            current_version = version.db_code;
            return current_version;
        }
        catch(Exception e){
            return "";
        }
        
    }

    public boolean isSchemaUpdate(String cur_version) {
        if(cur_version.startsWith(VERSION0))
            return true;
        else if(cur_version.startsWith(VERSION2))
            return true;
        else if(cur_version.startsWith(VERSION3))
            return true;
        else if(cur_version.startsWith(VERSION4))
            return true;
        return false;
    }

    public String getFinalVersion() {
        return VERSION4;
    }

    public Vector getFileUpdate(String cur_version) {
        Vector v = new Vector();
        if(cur_version.startsWith(VERSION0)){
            v.add(FN_VERSION2);
            v.add(FN_VERSION3);
        }
        else if(cur_version.startsWith(VERSION2))
            v.add(FN_VERSION3);
        return v;
    }

}
