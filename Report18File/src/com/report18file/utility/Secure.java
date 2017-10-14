/*
 * Secure.java
 *
 * Created on 30 กรกฎาคม 2548, 14:06 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.utility;

/**
 *
 * @author Noom
 */
public class Secure {
    
    /** Creates a new instance of Secure */
    public Secure() {
    }

    public static String encode(String dat)
   {
        char[] text = dat.toCharArray();
        char[] crypt = new char[text.length];
        for(int i=0; i<text.length; i++)
        {
            //System.out.println(text[i]);
            crypt[text.length-i-1] = text[i];
        }
        for(int i=0; i<crypt.length; i++)
        {
            //System.out.println(crypt[i]);
            int letter = crypt[i];
            letter+=5;
            //System.out.println(letter);
            crypt[i] = (char)letter;
        }
        return new String(crypt);
    }

    public static String decode(String dat)
    {
      if(dat!=null)
      {
        char[] text = dat.toCharArray();
        char[] crypt = new char[text.length];
        for(int i=0; i<text.length; i++)
        {
            //System.out.println(text[i]);
            crypt[text.length-i-1] = text[i];
        }
        for(int i=0; i<crypt.length; i++)
        {
            //System.out.println(crypt[i]);
            int letter = crypt[i];
            letter-=5;
            //System.out.println(letter);
            crypt[i] = (char)letter;
        }
        return new String(crypt);
      }
      return null;
    }
  



    
}
