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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
/**
 *
 * @author tong(Padungrat)
 * @Modify Ojika
 * @Modify henbe
 */
public class CheckVersionHos extends AbsVersionControl3{
    
    ConnectionInf theConnectionInf;
    String server="";
    String dbname="";
    String uname="";
    final String directory = "config/report/update";
    public StringBuffer updateResult = new StringBuffer();
    ////////////////////////////////////////////////////////////////////////////////
    //change effect
    public static String VERSION0 = "";
    public static String VERSION24 = "2.04.0148";
    public static String VERSION28 = "2.07.0548";
    public static String VERSION36 = "3.13.1048";
    public static String VERSION36n = "3.14.300406";
    public static String VERSION37 = "3.14.270706";
    public static String VERSION371 = "3.15.061106";
    public static String VERSION374 = "3.16.201206:3.7.250706";
    public static String VERSION38 = "3.16.201206:3.8.101008";
    public static String VERSION381 = "3.16.201206:3.8.101008s";
//    public static String VERSION39 = "3.17.241108:3.8.16";
    public static String VERSION39 = "3.17.241108:3.9";
    public static String VERSION391 = "3.17.241108:3.9.1";
    public static String VERSION393 = "3.18.160410:3.9.3";
    public static String VERSION394 = "3.18.160410:3.9.4";
    public static String VERSION395 = "3.18.090910:3.9.5";
    public static String VERSION396 = "3.18.061010:3.9.6";
    public static String VERSION397 = "3.18.131010:3.9.7";
    public static String VERSION398 = "3.18.271010:3.9.8";
    public static String VERSION399 = "3.18.031210:3.9.9";
    public static String VERSION39_10 = "3.18.131210:3.9.10";
    public static String VERSION39_11 = "3.18.100311:3.9.11";
    //เป็นไฟล์ที่ไว้ใช้ update จากเวอร์ชันนี้ให้เป็นเวอร์ชันตามไฟล์
    public static String FN_VERSION0 = "database/hospitalOSV38.sql";
    public static String FN_VERSION28 = "database/updateV3.sql";
    public static String FN_VERSION36 = "database/updateV3_7.sql";
    public static String FN_VERSION36n = "database/updateV3_72.sql";
    public static String FN_VERSION37 = "database/updateV3_73.sql";
    public static String FN_VERSION374 = "database/updateV3_74.sql";
    public static String FN_VERSION3741 = "database/updateV3_741.sql";
    public static String FN_VERSION38 = "database/updateV3_8.sql";
    public static String FN_VERSION381 = "database/updateV3_81.sql";
    public static String FN_VERSION382 = "database/updateV3_82.sql";
    public static String FN_VERSION383 = "database/updateV3_83.sql";
    public static String FN_VERSION391 = "database/updateV3_91.sql";
    public static String FN_VERSION392 = "database/updateV3_92.sql";
    public static String FN_VERSION393 = "database/updateV3_93.sql";
    public static String FN_VERSION394 = "database/updateV3_94.sql";
    public static String FN_VERSION395 = "database/updateV3_95.sql";
    public static String FN_VERSION396 = "database/updateV3_96.sql";
    public static String FN_VERSION397 = "database/updateV3_97.sql";
    public static String FN_VERSION398 = "database/updateV3_98.sql";
    public static String FN_VERSION399 = "database/updateV3_99.sql";
    public static String FN_VERSION39_10 = "database/updateV3_9_10.sql";
    public static String FN_VERSION39_11 = "database/updateV3_9_11.sql";
    //update with 36 too
    public static String FN_PCU_VERSION4 = "database/pcu/update_pcu_ph5.sql";
    
    HosDB theHosDB;
    private String cur_version;
    
    public CheckVersionHos(HosDB hosDB) {
        theHosDB = hosDB;
        theConnectionInf = hosDB.theSiteDB.theConnectionInf;
    }
    @Override
    public String getFinalVersion() {
        return VERSION39_11;
    }

    @Override
    public  String getCurrentVersion() {
        //ตรวจสอบว่ามีตารางนี้อยู่จริงหรือเปล่านะ
        if(cur_version!=null && false)
            return cur_version;
        try{
            Version v = this.theHosDB.theVersionDB.selectCurrentVersion();
            cur_version = v.db_code + ":" + v.app_code;
        }
        catch(Exception e){
            cur_version = "";
        }
        return cur_version;
    }


    @Override
    public Vector getFileUpdate(String cur_version) {

        Vector v = new Vector();
        if(cur_version.equals(VERSION0)){
            v.add(FN_VERSION0);
        }
        else if(cur_version.startsWith(VERSION28) || cur_version.startsWith(VERSION24)){
            v.add(FN_VERSION28);
            v.add(FN_VERSION36);
            v.add(FN_PCU_VERSION4);
            v.add(FN_VERSION36n);
            v.add(FN_VERSION37);
            v.add(FN_VERSION374);
            v.add(FN_VERSION3741);
            v.add(FN_VERSION38);
            v.add(FN_VERSION381);
            v.add(FN_VERSION382);
            v.add(FN_VERSION383);
            v.add(FN_VERSION391);
            v.add(FN_VERSION392);
            v.add(FN_VERSION393);
            v.add(FN_VERSION394);
            v.add(FN_VERSION395);
            v.add(FN_VERSION396);
            v.add(FN_VERSION397);
            v.add(FN_VERSION398);
            v.add(FN_VERSION399);
            v.add(FN_VERSION39_10);
            v.add(FN_VERSION39_11);
        }
        else if(cur_version.startsWith(VERSION36)){
            v.add(FN_VERSION36);
            v.add(FN_PCU_VERSION4);
            v.add(FN_VERSION36n);
            v.add(FN_VERSION37);
            v.add(FN_VERSION374);
            v.add(FN_VERSION3741);
            v.add(FN_VERSION38);
            v.add(FN_VERSION381);
            v.add(FN_VERSION382);
            v.add(FN_VERSION383);
            v.add(FN_VERSION391);
            v.add(FN_VERSION392);
            v.add(FN_VERSION393);
            v.add(FN_VERSION394);
            v.add(FN_VERSION395);
            v.add(FN_VERSION396);
            v.add(FN_VERSION397);
            v.add(FN_VERSION398);
            v.add(FN_VERSION399);
            v.add(FN_VERSION39_10);
            v.add(FN_VERSION39_11);
        }
        else if(cur_version.startsWith(VERSION36n)){
            v.add(FN_VERSION36n);
            v.add(FN_VERSION37);
            v.add(FN_VERSION374);
            v.add(FN_VERSION3741);
            v.add(FN_VERSION38);
            v.add(FN_VERSION381);
            v.add(FN_VERSION382);
            v.add(FN_VERSION383);
            v.add(FN_VERSION391);
            v.add(FN_VERSION392);
            v.add(FN_VERSION393);
            v.add(FN_VERSION394);
            v.add(FN_VERSION395);
            v.add(FN_VERSION396);
            v.add(FN_VERSION397);
            v.add(FN_VERSION398);
            v.add(FN_VERSION399);
            v.add(FN_VERSION39_10);
            v.add(FN_VERSION39_11);
        }
        else if(cur_version.startsWith(VERSION37)){
            v.add(FN_VERSION37);
            v.add(FN_VERSION374);
            v.add(FN_VERSION3741);
            v.add(FN_VERSION38);
            v.add(FN_VERSION381);
            v.add(FN_VERSION382);
            v.add(FN_VERSION383);
            v.add(FN_VERSION391);
            v.add(FN_VERSION392);
            v.add(FN_VERSION393);
            v.add(FN_VERSION394);
            v.add(FN_VERSION395);
            v.add(FN_VERSION396);
            v.add(FN_VERSION397);
            v.add(FN_VERSION398);
            v.add(FN_VERSION399);
            v.add(FN_VERSION39_10);
            v.add(FN_VERSION39_11);
        }
        else if(cur_version.startsWith(VERSION371))
        {
            v.add(FN_VERSION374);
            v.add(FN_VERSION3741);
            v.add(FN_VERSION38);
            v.add(FN_VERSION381);
            v.add(FN_VERSION382);
            v.add(FN_VERSION383);
            v.add(FN_VERSION391);
            v.add(FN_VERSION392);
            v.add(FN_VERSION393);
            v.add(FN_VERSION394);
            v.add(FN_VERSION395);
            v.add(FN_VERSION396);
            v.add(FN_VERSION397);
            v.add(FN_VERSION398);
            v.add(FN_VERSION399);
            v.add(FN_VERSION39_10);
            v.add(FN_VERSION39_11);
        }     
        else if(cur_version.compareTo(VERSION374)>=0 && cur_version.compareTo(VERSION38)<0){
            v.add(FN_VERSION38);
            v.add(FN_VERSION381);
            v.add(FN_VERSION382);
            v.add(FN_VERSION383);
            v.add(FN_VERSION391);
            v.add(FN_VERSION392);
            v.add(FN_VERSION393);
            v.add(FN_VERSION394);
            v.add(FN_VERSION395);
            v.add(FN_VERSION396);
            v.add(FN_VERSION397);
            v.add(FN_VERSION398);
            v.add(FN_VERSION399);
            v.add(FN_VERSION39_10);
            v.add(FN_VERSION39_11);
        }    
        else if(cur_version.compareTo(VERSION38)>=0 && cur_version.compareTo(VERSION381)<0){
            v.add(FN_VERSION381);
            v.add(FN_VERSION382);
            v.add(FN_VERSION383);
            v.add(FN_VERSION391);
            v.add(FN_VERSION392);
            v.add(FN_VERSION393);
            v.add(FN_VERSION394);
            v.add(FN_VERSION395);
            v.add(FN_VERSION396);
            v.add(FN_VERSION397);
            v.add(FN_VERSION398);
            v.add(FN_VERSION399);
            v.add(FN_VERSION39_10);
            v.add(FN_VERSION39_11);
        }
        else if(cur_version.compareTo(VERSION381)>=0 && cur_version.compareTo(VERSION39)<0){
            v.add(FN_VERSION382);
            v.add(FN_VERSION383);
            v.add(FN_VERSION391);
            v.add(FN_VERSION392);
            v.add(FN_VERSION393);
            v.add(FN_VERSION394);
            v.add(FN_VERSION395);
            v.add(FN_VERSION396);
            v.add(FN_VERSION397);
            v.add(FN_VERSION398);
            v.add(FN_VERSION399);
            v.add(FN_VERSION39_10);
            v.add(FN_VERSION39_11);
        }
        else if(cur_version.compareTo(VERSION39)>=0 && cur_version.compareTo(VERSION391)<0){
            v.add(FN_VERSION391);
            v.add(FN_VERSION392);
            v.add(FN_VERSION393);
            v.add(FN_VERSION394);
            v.add(FN_VERSION395);
            v.add(FN_VERSION396);
            v.add(FN_VERSION397);
            v.add(FN_VERSION398);
            v.add(FN_VERSION399);
            v.add(FN_VERSION39_10);
            v.add(FN_VERSION39_11);
        }
        else if(cur_version.startsWith(VERSION391)){
            v.add(FN_VERSION392);
            v.add(FN_VERSION393);
            v.add(FN_VERSION394);
            v.add(FN_VERSION395);
            v.add(FN_VERSION396);
            v.add(FN_VERSION397);
            v.add(FN_VERSION398);
            v.add(FN_VERSION399);
            v.add(FN_VERSION39_10);
            v.add(FN_VERSION39_11);
        }
        else if(cur_version.startsWith(VERSION393)){
            v.add(FN_VERSION394);
            v.add(FN_VERSION395);
            v.add(FN_VERSION396);
            v.add(FN_VERSION397);
            v.add(FN_VERSION398);
            v.add(FN_VERSION399);
            v.add(FN_VERSION39_10);
            v.add(FN_VERSION39_11);
        }
        else if(cur_version.startsWith(VERSION394)){
            v.add(FN_VERSION395);
            v.add(FN_VERSION396);
            v.add(FN_VERSION397);
            v.add(FN_VERSION398);
            v.add(FN_VERSION399);
            v.add(FN_VERSION39_10);
            v.add(FN_VERSION39_11);
        }
        else if(cur_version.startsWith(VERSION395)){
            v.add(FN_VERSION396);
            v.add(FN_VERSION397);
            v.add(FN_VERSION398);
            v.add(FN_VERSION399);
            v.add(FN_VERSION39_10);
            v.add(FN_VERSION39_11);
        }
        else if(cur_version.startsWith(VERSION396)){
            v.add(FN_VERSION397);
            v.add(FN_VERSION398);
            v.add(FN_VERSION399);
            v.add(FN_VERSION39_10);
            v.add(FN_VERSION39_11);
        }
        else if(cur_version.startsWith(VERSION397)){
            v.add(FN_VERSION398);
            v.add(FN_VERSION399);
            v.add(FN_VERSION39_10);
            v.add(FN_VERSION39_11);
        }
        else if(cur_version.startsWith(VERSION398)){
            v.add(FN_VERSION399);
            v.add(FN_VERSION39_10);
            v.add(FN_VERSION39_11);
        }
        else if(cur_version.startsWith(VERSION399)){
            v.add(FN_VERSION39_10);
            v.add(FN_VERSION39_11);
        }
        else if(cur_version.startsWith(VERSION39_10)){
            v.add(FN_VERSION39_11);
        }
        return v; 
    }

    public boolean isSchemaUpdate(String cur_version) 
    {
        if(cur_version.equals(VERSION0))
            return true;
        else if(cur_version.startsWith(VERSION28) || cur_version.startsWith(VERSION24))
            return true;
        else if(cur_version.startsWith(VERSION36))
            return true;
        else if(cur_version.startsWith(VERSION36n))
            return true;
        else if(cur_version.startsWith(VERSION37))
            return true;
        else if(cur_version.startsWith(VERSION371))
            return true;
        else if(cur_version.startsWith(VERSION374))
            return true;
        else if(cur_version.startsWith(VERSION38))
            return true;
        else if(cur_version.startsWith(VERSION381))
            return true;
        else if(cur_version.startsWith(VERSION39))
            return true;
        else if(cur_version.startsWith(VERSION391))
            return true;
        else if(cur_version.startsWith(VERSION393))
            return false;
        else if(cur_version.startsWith(VERSION394))
            return false;
        else if(cur_version.startsWith(VERSION395))
            return false;
        else if(cur_version.startsWith(VERSION396))
            return false;
        return true;
    }

    public static void main(String[] argc){

    }
    public static void loadFile(String surl,String file) throws MalformedURLException, IOException{

        System.out.print("LoadFile:...");
        URL url = new URL(surl);
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        InputStream input = url.openStream();
        File dist = new File(file);
        OutputStream out = new FileOutputStream(dist);
        byte[] buf = new byte[1024];
        int len;
        while ((len = input.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        input.close();
        out.close();
        System.out.println("complete");
    }
    public static void unzipFile(String zip, String des)
            throws IOException, ZipException {
        System.out.print("UnzipFile:...");
        int BUFFER = 2048;
        File sourceZipFile = new File(zip);
        File unzipDestinationDirectory = new File(des);
        if (!unzipDestinationDirectory.exists()) {
            unzipDestinationDirectory.mkdir();
        }
        if (!unzipDestinationDirectory.isDirectory()) {
            throw new ZipException("[ERROR] " + unzipDestinationDirectory.getPath() + " does not a directory");
        }
        ZipFile zipFile = new ZipFile(sourceZipFile, ZipFile.OPEN_READ);
        Enumeration zipFileEntries = zipFile.entries();
        while (zipFileEntries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
            String currentEntry = entry.getName();
            File destFile = new File(unzipDestinationDirectory, currentEntry);
            File destinationParent = destFile.getParentFile();
            destinationParent.mkdirs();
            if (!entry.isDirectory()) {
                BufferedInputStream is = new BufferedInputStream(zipFile.getInputStream(entry));
                int currentByte;
                byte[] data = new byte[BUFFER];
                FileOutputStream fos = new FileOutputStream(destFile);
                BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);
                while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
                    dest.write(data, 0, currentByte);
                }
                dest.flush();
                dest.close();
                is.close();
            }
        }
        zipFile.close();
        System.out.println("complete");
    }
}
