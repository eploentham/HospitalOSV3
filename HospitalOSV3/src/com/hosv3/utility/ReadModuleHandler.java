/*
 * ReadModuleHandler.java
 *
 * Created on 28 ÁÔ¶Ø¹ÒÂ¹ 2548, 13:16 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.utility;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

//import javax.swing.*;
//import java.util.Properties;

/**
 *
 * @author henbe
 */
public class ReadModuleHandler extends DefaultHandler{
    
   
    String dataString = null;
    
    String module = null;
    String index =null;
    boolean bmoduleReport = false;
    boolean bmoduleIndex = false;
  
    /**
     * Creates a new instance of ReadModuleHandler 
     */
    public ReadModuleHandler() {
        module = "";
        index = "";
    }
    public void startElement(String namespaceUri,String localName,
     String qualifiedName,Attributes attributes)
      throws SAXException {
          if(qualifiedName.equalsIgnoreCase("module-report"))
          {
              bmoduleReport = true;
          }
          if(qualifiedName.equalsIgnoreCase("module-index"))
          {
              bmoduleIndex = true;
          }

    }
    
    public void endElement(String namespaceUri,
                         String localName,
                         String qualifiedName)
      throws SAXException {
          if(qualifiedName.equalsIgnoreCase("module-report"))
          {
              bmoduleReport = false;
          }
          if(qualifiedName.equalsIgnoreCase("module-index"))
          {
              bmoduleIndex = false;
          }
          
    }
    public void characters(char[] chars,
                         int startIndex,
                         int length) {
        dataString = new String(chars, startIndex, length).trim();

        if(dataString.length() > 0)
        {   

           if(bmoduleReport)
           {
               module = dataString;
           }
           if(bmoduleIndex)
           {
               index = dataString;
           }

        }

    }
    
   
     public String getModule()
    {
        return module;
    }
     public String getIndex()
     {
         return index;
     }
    public void startDocument() throws SAXException {
         Constant.println("--------Start--------");
     }
     public void endDocument() throws SAXException {
         Constant.println("--------End--------");
     }
}
