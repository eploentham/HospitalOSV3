/*
 * ReadConfigHandler.java
 *
 * Created on 28 มิถุนายน 2548, 13:16 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.language.utility.language;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.Properties;

/**
 *
 * @author tong(Padungrat)
 */
public class ReadConfigHandler extends DefaultHandler{
    
    String dataValue = null;
    String dataString = null;
    String oldDes = null;
    
    Properties data ;
    /**อ่านไฟล์ภาษาที่กำหนด */
    public ReadConfigHandler() {
        data = new Properties();
    }
    public void startElement(String namespaceUri,String localName,
     String qualifiedName,Attributes attribut) throws SAXException {
        oldDes = "";
        dataString = "";
    }
    
    public void endElement(String namespaceUri,
                         String localName,
                         String qualifiedName)
      throws SAXException {

            if(qualifiedName.equals("language-key"))
                dataValue = dataString;
            else if(qualifiedName.equals("language-des"))
                this.data.setProperty(dataValue,dataString);
    }
    public void characters(char[] chars,
            int startIndex,
            int length) {
        oldDes = dataString;
        dataString = new String(chars, startIndex, length).trim();
        dataString = oldDes + dataString;
    }
    
    public Properties getData(){   
        return data;
    }
}
