/*
 * DefaultReadConfig.java
 *
 * Created on 28 �Զع�¹ 2548, 13:16 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.language.utility.language;
import com.hospital_os.utility.Constant;
import javax.xml.parsers.*;
import java.util.Properties;
/**
 *
 * @author tong(Padungrat)
 */
public class DefaultReadLanguage {
    
    /**path ��駵鹢ͧ��� Default ����˹�����ʴ��������� �������*/
    String defaultfilename = "config/language/Default.xml";
    
    /*config File ����˹�������ҹ�ҡ path � ����Ѻ module �����ͧ��� ����*/
    String filename= "config/language/"; //"module//";

    java.io.File file;
    /*Data*/
 
    Properties data ;
    String defaultdata;
    
    String datafilename;
    boolean result = false;
    boolean resultgc = false;
   
    
    /** 
     * Creates a new instance of DefaultReadConfig 
     * @deprecated henbe unused use next constructor
     **/
    public DefaultReadLanguage(String module_name) 
    {
        if(module_name !=null && module_name.trim().length() >0)
        {
             filename = filename + module_name + "/";
        }
        else
            filename = filename + "hospital_os/";
         getFilename();
         // ��˹�����������ͧ��è�������ҹ
         filename = filename + datafilename;
         Constant.println("Default Filename Read : " + filename);    
         file = new java.io.File(filename);
    }
    public DefaultReadLanguage(String module_name,String lng_file,String lng_path)
    {
        defaultfilename = lng_file;
        filename = lng_path;
        if(module_name !=null && module_name.trim().length() >0)
        {
             filename = filename + module_name + "/";
        }
        else
            filename = filename + "hospital_os/";
        
         getFilename();
         // ��˹�����������ͧ��è�������ҹ
         filename = filename + datafilename;
         Constant.println("Default Filename Read : " + filename);    
         file = new java.io.File(filename);
    }
    /**
     *  �ӡ����ҹ��� Default.xml ��������Һ��ҵ�ͧ�� ���� � module
     */
    public void getFilename()
    {
        java.io.File fi = new java.io.File(defaultfilename);
        if(checkXMLDefaultFile(fi))
        {
            readDefaultXML(fi);
        }
        
            
        
    }
    
    /**
     *  2
     * ��Ǩ�ͺ��� config �������������������
     * �������� return �� true 
     * �������� �ѹ�����ҧ��� config ��Ш� return �� false
     * ��Ше�ͧ�Դ�����������������ѹ��ҹ��� config �ա����
     */
    public boolean checkXMLDefaultFile(java.io.File file)
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
          //  createFile();
            return false;
        }
        return true;
    }
    
    /**
     *  3
     *  ��ҹ�����Ũҡ��� config ��зӡ����ŧ data
     */
    public void readDefaultXML(java.io.File filename)
    {
    
        ReadConfigDefaultHandler handler = new ReadConfigDefaultHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
              SAXParser parser = factory.newSAXParser();
              parser.parse(filename, handler);
              //�ӡ���Ѻ����������ͧ���
              datafilename = handler.theFixLanguage.filename;
        } catch(Exception e) {
            String errorMessage ="Error parsing " + filename + ": " + e;
            System.err.println(errorMessage);
            e.printStackTrace(Constant.getPrintStream());
        }
    }
    
    
     /**
      *����������ǹ�� 
     *  2.2
     *  �红����Ţͧ������ ���ͼ����ź��� config �͡ �ѹ�����ҧ����ͧ��йӢ���������ҷ��ŧ���
     */
    public String defaultData(String user,String password,String url,String multiconnection)
    {
        
     
        defaultdata = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n";
               defaultdata = defaultdata + "<!--"+ "\n";
               defaultdata = defaultdata + "Document   : connection.xml"+ "\n";
               defaultdata = defaultdata + "Created on : 28 �Զع�¹ 2548, 13:19 �."+ "\n";
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
   
    DefaultReadLanguage df = new DefaultReadLanguage("");
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
    
    
    /**
     *  �觢�������ٻẺ Properties ��觨�����������㹹��
     *@return Properties �� Properties �ͧ���ҷ���˹�
     */
    public Properties getProperties()
    {
        return data;
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
        {   
            Constant.println("File setting.xml exist");
            //createFile();
            return false;
        }
        return true;
    }
    
    /**
     *  2.1 
     *  ��㹡�����ҧ��� config ��� �Ӣ�����ŧ���
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
                fi.mkdir();
            }
            f.mkdir();
            
        }
            writeOutput(defaultData("", "", "",""),file.getPath());
     
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
         writeOutput(defaultData(user, password, url,multiconnection),file.getPath());
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
    
        Constant.println("_____________________________public void readXML()"+filename);
        ReadConfigHandler handler = new ReadConfigHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
              SAXParser parser = factory.newSAXParser();
              parser.parse(filename, handler);
              data = handler.getData();
        }
        catch(Exception e) {
            String errorMessage ="Error parsing " + filename + ": " + e;
            System.err.println(errorMessage);
            e.printStackTrace(Constant.getPrintStream());
        }
    }
    
}
