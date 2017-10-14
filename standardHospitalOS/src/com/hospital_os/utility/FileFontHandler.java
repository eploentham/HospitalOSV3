/*
 * FileFontHandler.java
 *
 * Created on 25 ���Ҥ� 2548, 18:37 �.
 */


package com.hospital_os.utility;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.Properties;

/**
 *
 * @author  tong(Padungrat)
 */
public class FileFontHandler extends DefaultHandler{
    
   
    String dataValue = null;
    String dataString = null;
    String fileprint = "PATHFONT";
 
    boolean bfileprint = false;
 
    Properties data ;
    
    public FileFontHandler() {
        data = new Properties();
    }
    /**�ӡ����ҹ tag �ͧ xml*/
    public void startElement(String namespaceUri,String localName,
     String qualifiedName,Attributes attributes)
      throws SAXException {
          if(qualifiedName.equalsIgnoreCase("path-font"))
          {    bfileprint = true;
          }
          
          
    }
    
    /**
     *  �������ҹ tag �ͧ xml
     */
    public void endElement(String namespaceUri,
                         String localName,
                         String qualifiedName)
      throws SAXException {
          if(qualifiedName.equalsIgnoreCase("path-font"))
          {
              bfileprint = false;
          }
         
          
    }
    /**
     *  ���������������� ���������ҧ �����ҹ tag �ͧ xml
     */
    public void characters(char[] chars,
                         int startIndex,
                         int length) {
    dataString = new String(chars, startIndex, length).trim();
   
    if(dataString.length() > 0)
    {   
        if(bfileprint)
        {
            dataValue = fileprint;
        }
        this.data.setProperty(dataValue,dataString);
    }
    dataString = null;
    }
    
    /**
     *  �����  data 
     */
    public Properties getData()
    {   
        return data;
    }
    
    public void StartDocument() throws SAXException {
         Constant.println("--------Start--------");
     }
     public void endDocument() throws SAXException {
         Constant.println("--------End--------");
     }
}
