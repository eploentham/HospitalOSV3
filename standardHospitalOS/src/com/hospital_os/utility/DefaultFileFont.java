/*
 * DefaultFileFont.java
 *
 * Created on 25 มกราคม 2548,  18:37 น.
 *  use Theme for formsdemo-1.0.5
 *
 */


package com.hospital_os.utility;
import javax.xml.parsers.*;
import java.util.Properties;
/**
 *
 * @author  tong(Padungrat)
 */
public class DefaultFileFont {
    
    
    /*config File*/
    String filename= "config//gui//pathfont.xml";

    String pathfont = "font";

    java.io.File file;
    /*Data*/
    Properties data ;
    String defaultdata;
    boolean result = false;

    Object theme;
    /**
     *  @deprecated henbe unused ,use next constructure
     */
    public DefaultFileFont() {
        file = new java.io.File(filename);
    }
    public DefaultFileFont(String f) {
        filename = f;
        file = new java.io.File(filename);
    }
    /**
     *  2.2
     *  เก็บข้อมูลของไฟล์ไว้ เพื่อผู้ใช้ลบไฟล์ config ออก มันจะสร้างไฟล์เองและนำข้อมูลเหล่าที่ลงไฟล์
     */
    private String defaultData()
    {
        defaultdata = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n";
               defaultdata = defaultdata + "<!--"+ "\n";
               defaultdata = defaultdata + "Document   : setting.xml"+ "\n";
               defaultdata = defaultdata + "Created on : 26 กันยายน 2548, 11:49 น."+ "\n";
               defaultdata = defaultdata + "Author     : tong"+ "\n";
               defaultdata = defaultdata + "Description:"+ "\n";
               defaultdata = defaultdata + "Purpose of the document follows."+ "\n";
               defaultdata = defaultdata + "-->"+ "\n";
               defaultdata = defaultdata + "<root>"+ "\n";
               defaultdata = defaultdata + "<path-font>font</path-font>"+ "\n";
               defaultdata = defaultdata + "</root>"+ "\n\n";
                              
                
               return defaultdata;
    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        /* TODO code application logic here
*/
   
    DefaultFileFont df = new DefaultFileFont();
    
    if(df.checkXMLFile())
    {   
        df.readXML();
        Constant.println(df.getPathFont());
    }
    
     
        
  /*  readXML(filename);
*/
    System.exit(0);
    }
   
    public String getPathFont()
    {
        try{
         
            pathfont = data.getProperty("PATHFONT");
            
        }
        catch(Exception ex)
        {
            pathfont = "font";
        }
        
        
        return pathfont;
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
        boolean result = true;
        if (System.getProperty(jaxpPropertyName) == null) {
          String apacheXercesPropertyValue = "org.apache.xerces.jaxp.SAXParserFactoryImpl";
          System.setProperty(jaxpPropertyName,apacheXercesPropertyValue);
        }
        
         if(!file.exists())
        {   Constant.println("File setting.xml exist");
            createFile();
            result = false;
        }
        return result;
    }
    
    /**
     *  2.1 
     *  ใช้ในการสร้างไฟล์ config และ นำข้อมูลลงไฟล์
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
        Constant.println("Write File Default Data : " + file.getPath());
        writeOutput(defaultData(),file.getPath());
     
        }
        catch(Exception ex)
        {
               Constant.println("Exception file font");
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
    
        FileFontHandler handler = new FileFontHandler();
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
