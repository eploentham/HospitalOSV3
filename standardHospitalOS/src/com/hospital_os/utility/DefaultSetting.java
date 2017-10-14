/*
 * DefaultLookAndFeel.java
 *
 * Created on 25 ���Ҥ� 2548, 12:02 �.
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
     *  �红����Ţͧ������ ���ͼ����ź��� config �͡ �ѹ�����ҧ����ͧ��йӢ���������ҷ��ŧ���
     */
    public String defaultData()
    {
        defaultdata = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n";
               defaultdata = defaultdata + "<!--"+ "\n";
               defaultdata = defaultdata + "Document   : setting.xml"+ "\n";
               defaultdata = defaultdata + "Created on : 25 ���Ҥ� 2548, 11:49 �."+ "\n";
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
     * �� runLookAndFeel ����͵�Ǩ�ͺ����������º��������
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
     * �� runLookAndFeel ����͵�Ǩ�ͺ����������º��������
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
     * ��Ǩ�ͺ��� config �������������������
     * �������� return �� true 
     * �������� �ѹ�����ҧ��� config ��Ш� return �� false
     * ��Ше�ͧ�Դ�����������������ѹ��ҹ��� config �ա����
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
     *  ��㹡�����ҧ��� config ��� �Ӣ�����ŧ���
     * @deprecated henbe unused
     */
    private void createFile()
    {  
        java.io.File f = new java.io.File(file.getParent());
        java.io.File fi = new java.io.File(f.getParent());
        
        try{
     
        if(!f.exists())
        {
            /**��ͧ ���ҧ Folder gui ���ͧ��Ǩ�ͺ��͹����� Folder ��͹˹�ҹ������*/
        
            if(!f.exists())
            {
                /**��ͧ ���ҧ Folder config */
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
     *  �йӢ�����ŧ��� config
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
     *  ��ҹ�����Ũҡ��� config ��зӡ����ŧ data
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
