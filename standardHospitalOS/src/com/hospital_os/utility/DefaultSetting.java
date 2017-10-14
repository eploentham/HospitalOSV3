/*
 * DefaultLookAndFeel.java
 *
 * Created on 25 มกราคม 2548, 12:02 น.
 *  use Theme for formsdemo-1.0.5
 *
 */


package com.hospital_os.utility;
import javax.xml.parsers.*;
import java.util.Properties;
/**
 *
 * @author  tong
 */
public class DefaultSetting {
    
    /** Creates a new instance of DefaultLookAndFeel */
    /*config File*/
    final String filename= "config//gui//setting.xml";

    String multicon = "true";
    String gc = "false";
    java.io.File file;
    /*Data*/
    Properties data ;
    String defaultdata;
    boolean result = false;
    boolean resultgc = false;
    Object theme;
    /**
     *  1
     */
    public DefaultSetting() {
        file = new java.io.File(filename);
    }
    /**
     *  2.2
     *  เก็บข้อมูลของไฟล์ไว้ เพื่อผู้ใช้ลบไฟล์ config ออก มันจะสร้างไฟล์เองและนำข้อมูลเหล่าที่ลงไฟล์
     */
    public String defaultData()
    {
        defaultdata = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n";
               defaultdata = defaultdata + "<!--"+ "\n";
               defaultdata = defaultdata + "Document   : setting.xml"+ "\n";
               defaultdata = defaultdata + "Created on : 25 มกราคม 2548, 11:49 น."+ "\n";
               defaultdata = defaultdata + "Author     : tong"+ "\n";
               defaultdata = defaultdata + "Description:"+ "\n";
               defaultdata = defaultdata + "Purpose of the document follows."+ "\n";
               defaultdata = defaultdata + "-->"+ "\n";
               defaultdata = defaultdata + "<root>"+ "\n";
               defaultdata = defaultdata + "<multi-connection>true</multi-connection>"+ "\n";
               defaultdata = defaultdata + "<garbage-collation>false</garbage-collation>"+ "\n";
               defaultdata = defaultdata + "</root>"+ "\n\n";
                              
                
               return defaultdata;
    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        /* TODO code application logic here
*/
   
    DefaultSetting df = new DefaultSetting();
    boolean result = false;
    if(df.checkXMLFile())
    {   
        df.readXML();
        result = df.getMultiConnection();
    }
    
     
        
  /*  readXML(filename);
*/
    System.exit(0);
    }
    /**
     *  4
     * ใช้ runLookAndFeel เมื่อตรวจสอบไฟล์เสร็จเรียบร้อยแล้ว
     */
    public boolean getMultiConnection()
    {
        try{
         
            multicon = data.getProperty("MULTICONNECTION");
            if(multicon.trim().equalsIgnoreCase("true"))
                result = true;
            
        }
        catch(Exception ex)
        {
            result = false;
        }
        
        
        return result;
    }
    
    /**
     *  4
     * ใช้ runLookAndFeel เมื่อตรวจสอบไฟล์เสร็จเรียบร้อยแล้ว
     */
    public boolean getGC()
    {
        try{
         
            multicon = data.getProperty("GARBAGE");
            if(multicon.trim().equalsIgnoreCase("true"))
                resultgc = true;
            
        }
        catch(Exception ex)
        {
            resultgc = false;
        }
        
        
        return resultgc;
    }
    /**
     *  2
     * ตรวจสอบไฟล์ config ว่ามีไฟล์อยู่หรือไม่
     * ถ้ามีให้ return เป็น true 
     * ถ้าไม่มี มันจะสร้างไฟล์ config และจะ return เป็น false
     * และจะต้องปิดโปรแกรมใหม่เพื่อให้มันอ่านไฟล์ config อีกครั้ง
     */
    public boolean checkXMLFile()
    {
        String jaxpPropertyName ="javax.xml.parsers.SAXParserFactory";
   
        if (System.getProperty(jaxpPropertyName) == null) {
          String apacheXercesPropertyValue = "org.apache.xerces.jaxp.SAXParserFactoryImpl";
          System.setProperty(jaxpPropertyName,apacheXercesPropertyValue);
        }
        
   /*     Constant.println("dddddddd");
*/
        if(!file.exists())
        {   Constant.println("File setting.xml exist");
            createFile();
            return false;
        }
        return true;
    }
    
    /**
     *  2.1 
     *  ใช้ในการสร้างไฟล์ config และ นำข้อมูลลงไฟล์
     * @deprecated henbe unused
     */
    private void createFile()
    {  
        java.io.File f = new java.io.File(file.getParent());
        java.io.File fi = new java.io.File(f.getParent());
        
        try{
     
        if(!f.exists())
        {
            /**ต้อง สร้าง Folder gui แต่ต้องตรวจสอบก่อนว่ามี Folder ก่อนหน้านี้เปล่า*/
        
            if(!f.exists())
            {
                /**ต้อง สร้าง Folder config */
                //fi.mkdir();
            }
            //f.mkdir();
            
        }
            writeOutput(defaultData(),file.getPath());
     
        }
        catch(Exception ex)
        {
            /* ex.printStackTrace(Constant.getPrintStream());
*/
        }
        f = null;
        fi = null;
    }
    /**
     *  2.3
     *  จะนำข้อมูลลงไฟล์ config
     */
   private void writeOutput(String str, String filename) 
   {
       try 
       {
           java.io.FileOutputStream fos = new java.io.FileOutputStream(filename);
           java.io.Writer out = new java.io.OutputStreamWriter(fos, "UTF8");
           out.write(str);
           out.close();
           fos = null;
           out = null;
       } 
       catch (java.io.IOException e) 
       {
           e.printStackTrace(Constant.getPrintStream());
       }
   }
    
    /**
     *  3
     *  อ่านข้อมูลจากไฟล์ config และทำการเก็บลง data
     */
    public void readXML()
    {
    
        SettingHandler handler = new SettingHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
              SAXParser parser = factory.newSAXParser();
              parser.parse(filename, handler);
              data = handler.getData();
        } catch(Exception e) {
            String errorMessage ="Error parsing " + filename + ": " + e;
            System.err.println(errorMessage);
            e.printStackTrace(Constant.getPrintStream());
        }
    }
    
   
    
}
