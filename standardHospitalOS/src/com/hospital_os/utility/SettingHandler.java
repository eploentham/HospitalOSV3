/*
 * LookAndFeelHandler.java
 *
 * Created on 25 มกราคม 2548, 11:52 น.
 */


package com.hospital_os.utility;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.Properties;
import java.util.Vector;
/**
 *
 * @author  Administrator
 */
public class SettingHandler extends DefaultHandler{
    
    /** Creates a new instance of LookAndFeelHandler */
    String dataValue = null;
    String dataString = null;
    String sclasspath = "MULTICONNECTION";
    String sgc = "GARBAGE";
    boolean classpath = false;
    boolean gc = false;
    Properties data ;
    public SettingHandler() {
        data = new Properties();
    }
    public void startElement(String namespaceUri,String localName,
     String qualifiedName,Attributes attributes)
      throws SAXException {
          if(qualifiedName.equalsIgnoreCase("multi-connection"))
              classpath = true;
          
           if(qualifiedName.equalsIgnoreCase("garbage-collation"))
              gc = true;
    }
    
    public void endElement(String namespaceUri,
                         String localName,
                         String qualifiedName)
      throws SAXException {
          if(qualifiedName.equalsIgnoreCase("multi-connection"))
              classpath = false;
          if(qualifiedName.equalsIgnoreCase("garbage-collation"))
              gc = false; 
          
    }
    public void characters(char[] chars,
                         int startIndex,
                         int length) {
    dataString = new String(chars, startIndex, length).trim();
   
    if(dataString.length() > 0)
    {   
        if(classpath)
            dataValue = sclasspath;
               
        if(gc)
            dataValue = sgc;
        
        this.data.setProperty(dataValue,dataString);
    }
    dataString = null;
    }
    
    public Properties getData()
    {   
        return data;
    }
     public Vector getVdata()
    {
        return null;
    }
    public void StartDocument() throws SAXException {
         Constant.println("--------Start--------");
     }
     public void endDocument() throws SAXException {
         Constant.println("--------End--------");
     }
}
