/*
 * ReadConfigHandler.java
 *
 * Created on 28 ÁÔ¶Ø¹ÒÂ¹ 2548, 13:16 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.language.utility.language;
import com.hospital_os.utility.Constant;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.Properties;
import java.util.Vector;
/**
 *
 * @author tong(Padungrat)
 */
public class ReadConfigDefaultHandler extends DefaultHandler{
    
    public FixLanguage theFixLanguage;
    Vector vc ;
    String dataString = null;
    

    boolean languageKey = false;
    boolean languageDes = false;
    boolean languageFile = false;
    Properties data ;
    /** Creates a new instance of ReadConfigHandler */
    public ReadConfigDefaultHandler() {
        data = new Properties();
        vc = new Vector();
    }
    public void startElement(String namespaceUri,String localName,
     String qualifiedName,Attributes attributes)
      throws SAXException {
          if(qualifiedName.equalsIgnoreCase("language-set-id"))
          {
              theFixLanguage = new FixLanguage();
              languageKey = true;
          }
          
           if(qualifiedName.equalsIgnoreCase("language-set-description"))
              languageDes = true;
          
           if(qualifiedName.equalsIgnoreCase("language-set-filename"))
              languageFile = true;
            
        //Constant.println(qualifiedName);
    }
    
    public void endElement(String namespaceUri,
                         String localName,
                         String qualifiedName)
      throws SAXException {
          if(qualifiedName.equalsIgnoreCase("language-set-id"))
              languageKey = false;
          
           if(qualifiedName.equalsIgnoreCase("language-set-description"))
              languageDes = false;
          
           if(qualifiedName.equalsIgnoreCase("language-set-filename"))
              languageFile = false;
    
    }
    public void characters(char[] chars,
                         int startIndex,
                         int length) 
    {
        dataString = new String(chars, startIndex, length).trim();

        if(dataString.length() > 0)
        {   
            if(languageKey)
            {    
                theFixLanguage.id = dataString;
            }      
            if(languageDes)
            {
                theFixLanguage.description = dataString;
            }
            if(languageFile)
            {
                theFixLanguage.filename = dataString;
                vc.add(theFixLanguage);
                
            }

            dataString = null;
        }
    }
    
    public Vector getVector()
    {
        return vc;
    }
    
    
    
    public void StartDocument() throws SAXException {
         Constant.println("--------Start--------");
     }
     public void endDocument() throws SAXException {
         Constant.println("--------End--------");
     }
}
