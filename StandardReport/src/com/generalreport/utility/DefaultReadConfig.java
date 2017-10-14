/*
 * DefaultReadConfig.java
 *
 * Created on 28 มิถุนายน 2548, 13:16 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.utility;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

/**
 *
 * @author Administrator
 */
public class DefaultReadConfig {
    /*config File*/
    final String filename= "config//reportcenter//reportcenter.xml";
    String defaultdata ;
    java.util.Vector vModule ;

    java.io.File file;
    /*Data*/
  
    
    
    /** Creates a new instance of DefaultReadConfig */
    public DefaultReadConfig() {
         file = new java.io.File(filename);
         vModule = new java.util.Vector();
    }
    
     /**
     *  2.2
     *  เก็บข้อมูลของไฟล์ไว้ เพื่อผู้ใช้ลบไฟล์ config ออก มันจะสร้างไฟล์เองและนำข้อมูลเหล่าที่ลงไฟล์
     */
    public String defaultData(String user,String password,String url,String multiconnection)
    {
        
     
        defaultdata = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n";
               defaultdata = defaultdata + "<!--"+ "\n";
               defaultdata = defaultdata + "Document   : connection.xml"+ "\n";
               defaultdata = defaultdata + "Created on : 28 มิถุนายน 2548, 13:19 น."+ "\n";
               defaultdata = defaultdata + "Author     : tong"+ "\n";
               defaultdata = defaultdata + "Description:"+ "\n";
               defaultdata = defaultdata + "Purpose of the document follows."+ "\n";
               defaultdata = defaultdata + "-->"+ "\n";
               defaultdata = defaultdata + "<root>"+ "\n";
               defaultdata = defaultdata + "<multi-connection>"+ multiconnection +"</multi-connection>"+ "\n";
             //  defaultdata = defaultdata + "<server></server>"+ "\n";
           //    defaultdata = defaultdata + "<port></port>"+ "\n";
            //   defaultdata = defaultdata + "<database></database>"+ "\n";
            //   defaultdata = defaultdata + "<database-type></database-type>"+ "\n";
               defaultdata = defaultdata + "<user>"+user+"</user>"+ "\n";
               defaultdata = defaultdata + "<password>"+password+"</password>"+ "\n";
               defaultdata = defaultdata + "<url>"+url+"</url>"+ "\n";
               defaultdata = defaultdata + "</root>"+ "\n\n";
                              
                
               return defaultdata;
    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        /* TODO code application logic here
*/
   
    DefaultReadConfig df = new DefaultReadConfig();

    if(df.checkXMLFile())
    {   
        df.readXML();
        df.getModule();
    }
    
    
        
  /*  readXML(filename);
*/
    System.exit(0);
    }
  
    
    public java.util.Vector getModule()
    {
        return vModule;
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
        
   /*     System.out.println("dddddddd");
*/
        if(!file.exists())
        {   System.out.println("File setting.xml exist");
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
                fi.mkdir();
            }
            f.mkdir();
            
        }
            writeOutput(defaultData("", "", "",""),file.getPath());
     
        }
        catch(Exception ex)
        {
            ex.printStackTrace();

        }
        f = null;
        fi = null;
    }
    
    public void writeConnection(String url,String user,String password,String multiconnection)
    {
         writeOutput(defaultData(user, password, url,multiconnection),file.getPath());
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
           e.printStackTrace();
       }
   }
    
    /**
     *  3
     *  อ่านข้อมูลจากไฟล์ config และทำการเก็บลง data
     */
    public void readXML()
    {
    
        ReadConfigHandler handler = new ReadConfigHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
              SAXParser parser = factory.newSAXParser();
              parser.parse(filename, handler);
           
              vModule = handler.getVdata();
        } catch(Exception e) {
            String errorMessage ="Error parsing " + filename + ": " + e;
            System.err.println(errorMessage);
            e.printStackTrace();
        }
    }
    
}
