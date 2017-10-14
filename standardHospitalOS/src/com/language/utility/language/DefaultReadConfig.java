/*
 * DefaultReadConfig.java
 *
 * Created on 28 มิถุนายน 2548, 13:16 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.language.utility.language;
import com.hospital_os.utility.Constant;
import javax.xml.parsers.*;
import java.util.Vector;
/**
 *
 * @author tong(Padungrat)
 *@deprecated henbe unsued
 */
public class DefaultReadConfig {
    
    String defaultfilename ="config//language//Default.xml";
    
    /*config File*/
    String filename= "config//language//Language.xml";

    String multicon = "true";

    java.io.File file;
    /*Data*/
    Vector data ;
    String defaultdata;
    
    String datafilename;
    boolean result = false;
    boolean resultgc = false;
    Object theme;
    
     String user;
     String password;
     String url;
    
    
    /** Creates a new instance of DefaultReadConfig */
    public DefaultReadConfig() {
         
              
         file = new java.io.File(filename);
    }
    
    public void setUseLanguage(FixLanguage fixlanguage)
    {
        java.io.File fdefault = new java.io.File(defaultfilename);
        writeOutput(defaultData(fixlanguage),fdefault.getPath());
    }
    
  
     /**
     *  2.2
     *  เก็บข้อมูลของไฟล์ไว้ เพื่อผู้ใช้ลบไฟล์ config ออก มันจะสร้างไฟล์เองและนำข้อมูลเหล่าที่ลงไฟล์
     */
    private String defaultData(FixLanguage fixlanguage)
    {
        
        defaultdata = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n";
        defaultdata = defaultdata + " <!--"+ "\n";
        defaultdata = defaultdata + "Document   : Default.xml"+ "\n";
        defaultdata = defaultdata + "Created on : 11 มกราคม 2549, 17:58 น."+ "\n";
        defaultdata = defaultdata + "Author     : tong"+ "\n";
        defaultdata = defaultdata + "Description:"+ "\n";
        defaultdata = defaultdata + "Purpose of the document follows."+ "\n";
        defaultdata = defaultdata + "-->"+ "\n";

        defaultdata = defaultdata + "<root>"+ "\n";
        defaultdata = defaultdata + "        <language-set-id>"+fixlanguage.id +"</language-set-id>"+ "\n";
        defaultdata = defaultdata + "        <language-set-description>"+fixlanguage.description +"</language-set-description>"+ "\n";
        defaultdata = defaultdata + "        <language-set-filename>"+fixlanguage.filename +"</language-set-filename>"+ "\n";
        defaultdata = defaultdata + "</root>"+ "\n";

     
                              
                
               return defaultdata;
    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        /* TODO code application logic here
*/
   
    DefaultReadConfig df = new DefaultReadConfig();
    boolean result = false;
    if(df.checkXMLFile())
    {   
        df.readXML();
      //  result = df.getMultiConnection();
    }
    
    
        
  /*  readXML(filename);
*/
    System.exit(0);
    }
   
    
    public Vector getVector()
    {
        return data;
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
                fi.mkdir();
            }
            f.mkdir();
            
        }
          //  writeOutput(defaultData("", "", "",""),file.getPath());
     
        }
        catch(Exception ex)
        {
            /* ex.printStackTrace(Constant.getPrintStream());
*/
        }
        f = null;
        fi = null;
    }
    
    public void writeConnection(String url,String user,String password,String multiconnection)
    {
       //  writeOutput(defaultData(user, password, url,multiconnection),file.getPath());
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
    
        ReadConfigDefaultHandler handler = new ReadConfigDefaultHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
              SAXParser parser = factory.newSAXParser();
              parser.parse(filename, handler);
              data = handler.getVector();
        } catch(Exception e) {
            String errorMessage ="Error parsing " + filename + ": " + e;
            System.err.println(errorMessage);
            e.printStackTrace(Constant.getPrintStream());
        }
    }
    
}
