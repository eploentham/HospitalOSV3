/*
 * ReadConfigHandler.java
 *
 * Created on 28 ÁÔ¶Ø¹ÒÂ¹ 2548, 13:16 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.utility;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import javax.swing.*;
import java.util.Properties;
import java.util.Vector;
/**
 *
 * @author Administrator
 */
public class ReadConfigHandler extends DefaultHandler{
    
    String dataValue = null;
    String dataString = null;
    String moduleReport = null;
    Vector vModule = null;
    boolean bmoduleReport = false;
    
    Properties data ;
    /** Creates a new instance of ReadConfigHandler */
    public ReadConfigHandler() {
        vModule = new Vector();
    }
    public void startElement(String namespaceUri,String localName,
     String qualifiedName,Attributes attributes)
      throws SAXException {
          if(qualifiedName.equalsIgnoreCase("module-report"))
          {
              bmoduleReport = true;
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
          
    }
    public void characters(char[] chars,
                         int startIndex,
                         int length) {
    dataString = new String(chars, startIndex, length).trim();

    if(dataString.length() > 0)
    {   
        
        System.out.println("Test : " + dataString);
        vModule.add(dataString);
    }

    }
    
    public Properties getData()
    {   
        return data;
    }
     public Vector getVdata()
    {
        return vModule;
    }
    public void StartDocument() throws SAXException {
         System.out.println("--------Start--------");
     }
     public void endDocument() throws SAXException {
         System.out.println("--------End--------");
     }
}
