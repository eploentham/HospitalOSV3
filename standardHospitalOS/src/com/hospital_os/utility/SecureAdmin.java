/*
 * secure.java
 *
 * Created on 23 ¡’π“§¡ 2547, 17:27 π.
 */

package com.hospital_os.utility;

/**
 *
 * @author  Administrator
 */
public class SecureAdmin {
    
    /** Creates a new instance of secure */
    
    final private String username = "|pexmtwsl|";
    final private String password = "|}kspsrlgixigvyswrits|";
    
    public SecureAdmin() {
    }
    
    
    public  boolean checkUserPassword(String user,String password)
    {
        boolean result = false;
        char[] as = user.toCharArray();
        int i = 0;
        for(i =0 ; i<as.length ; i++)
        {
            int l = as[i];
       /*     Constant.println(as[i] + " ----> " + l);
*/
            if(48<=l && l <= 57)
            {   break;
            }    
        }
     /*   Constant.println(i);
*/
        if(i > as.length)
        {/*   Constant.println(as.length);
*/
            result = false;
        }
        else
        {    
            String userchar = user.substring(0,i);
            String userint = user.substring(i);
        
            int mid = userint.length()/2;
            String userhead = userint.substring(mid);
            String userletter = userint.substring(0,mid);
            /*Constant.println("char :" + userchar);
*/
            /*Constant.println("int  :" + userint);
*/
            /*Constant.println("mid  :" + userhead + " " + userletter);
*/
            userchar = encode(userchar);
            userhead = encodei(userhead);
            userletter = encodei(userletter);
        
            String passwordend = userhead+userchar+userletter;
          /* Constant.println("password :" + passwordend);
*/
        
            if(password.equals(passwordend))
                result = true;
            userchar = null;
            userint = null;
            userhead = null;
            userletter = null;
            passwordend = null;
        }
           
        return result;
    }
    
    private  String encode(String dat)
    {
        char[] text = dat.toCharArray();
 
        for(int i=0; i<text.length; i++)
        {
            /*Constant.println(crypt[i]);
*/
            int letter = text[i];
            letter+=4;
            /*Constant.println(letter);
*/
            text[i] = (char)letter;
        }

        return new String(text);
    }
    private  String encodei(String dat)
    {
        char[] text = dat.toCharArray();

        for(int i=0; i<text.length; i++)
        {
            /*Constant.println(crypt[i]);
*/
            int letter = text[i];
            letter+=7;
            /*Constant.println(letter);
*/
            text[i] = (char)letter;
        }

        return new String(text);
    }
  
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SecureAdmin se = new SecureAdmin();
        String username = "tong";
        String password = "xsrk";/*"opensourcetechnology";
*/
   /*     Constant.println("|"+se.encode(username)+ "|");
*/
    /*    Constant.println("|"+se.encode(password)+ "|");
*/
 
        
  /*      Constant.println(se.checkUserPassword(username, password));
*/
    }
    
}
