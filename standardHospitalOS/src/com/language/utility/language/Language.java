/*
 * Language.java
 *
 * Created on 20 มิถุนายน 2548, 13:20 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.language.utility.language;
import com.hospital_os.utility.Constant;
import java.util.Properties;
/**
 *
 * @author tong(Padungrat)
 */
public class Language {

    private static String text= "";
    private static String language = "";
    private static Properties data = null;
    /** Creates a new instance of Language */
    public Language() {
    
    }
    
    /**ใช้ในครั้งแรกที่ต้องการ get ภาษาที่ต้องการใช้
     *@return Properties เป็น Object ของ Properties 
     *ถ้าไม่มีค่าหรือเกิดผิดพลาดจะ ให้ค่าเป็น null
     * @deprecated please useg getProperties(String module_name,String lng_file,String lng_path)
     */
    public static Properties getProperties(String module_name)
    {
        DefaultReadLanguage df = new DefaultReadLanguage(module_name);
        data = new Properties();
        boolean result = false;
        if(df.checkXMLFile())
        {   
            df.readXML();
            data =  df.getProperties();
        }
        return df.getProperties();
    }
    public static Properties getProperties(String module_name,String lng_file,String lng_path)
    {
        DefaultReadLanguage df = new DefaultReadLanguage(module_name,lng_file,lng_path);
        data = new Properties();
        boolean result = false;
        if(df.checkXMLFile())
        {   
            df.readXML();
            data =  df.getProperties();
        }
        return df.getProperties();
    }
   
    /**
     * ให้ค่าที่เป็นภาษาเมื่อใส่ข้อมูลของภาษาและต้องการให้แปลงเป็นภาษาไทยหรืออังกฤษ
     * @return ข้อมูลที่ถูกแปลงออกไป ตามข้อมูลที่รับ
     * @param str ข้อมูลที่ต้องการจะแปลงออกมา
     */
    public static String getTextBundle(String str)
    {   language = "";
        if(str.trim().equals(""))
        {
            language = "";
        }
        else
        {
            if(data !=null)
            {
                try
                {
                    language =  data.getProperty(str);
                }
                catch(Exception e)
                {  e.printStackTrace(Constant.getPrintStream());
                    language = str;
                }
                finally
                {

                }
            }
            
            if(language==null || language.trim().length() == 0)
            {
                language = str;
            }
            
        }
        return language;
    }
    
   
    public static void main(String[] argv)
    {
        Constant.println(Language.getTextBundle("Edit"));
    }

    public static Object getTextBundle(String string, int language) {
        return null;
    }
}
