/*
 * Config.java
 *
 * Created on 20 มิถุนายน 2548, 13:20 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.utility;

/**
 *
 * @author Administrator
 */
public class Config {
    static final String Config = "com/generalpcu/property/Config";
  
    private static String text= "";
    private static String language = "";
    /**
     * Creates a new instance of Config 
     */
    public Config() {
    }
    
   
    /**
     * ให้ค่าที่เป็นภาษาเมื่อใส่ข้อมูลของภาษาและต้องการให้แปลงเป็นภาษาไทยหรืออังกฤษ
     * @return ข้อมูลที่ถูกแปลงออกไป ตามข้อมูลที่รับ
     * @param str ข้อมูลที่ต้องการจะแปลงออกมา
     * @param lang เป็น ตัวเลขที่ต้องการจะให้เปลงออกมาเป็นภาษาอะไร
     * 1 ให้ออกมาเป็นภาษาไทย
     * 2 ให้ออกมาเป็นภาษาอังกฤษ
     */
    public static String getTextBundle(String str)
    {   
        text = str;
     
        text = getTextThaiBundle(str);
          
        return text;
    }
    
    
    
    /**
     * เป็นการรับข้อมูลและแปลงออกมาเป็นภาษาไทย จำเป็นต้องมี property ไฟล์ก่อน
     * @param str เป็นชื่อภาษาที่ต้องการจะให้แปลงออกมาเป็นภาษาไทย
     * @return ให้ข้อมูลออกมาเป็น String ที่เป็นภาษาไทย
     */
    public static String getTextThaiBundle(String str)
    {    
         language = "";
         if(str.trim().equals(""))
         {
            language = "";
         }
        try
        {
            language = java.util.ResourceBundle.getBundle(Config).getString(str);
        }
        catch(Exception e)
        { 
           // e.printStackTrace();           
            language = str;
            
        }
         
         return language;
    }
    
}
