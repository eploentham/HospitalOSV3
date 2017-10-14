/*
 * ServerPort.java
 *
 * Created on 27 เมษายน 2547, 21:14 น.
 */
package com.hospital_os.utility;
import java.net.*;
import java.io.*;

/**
 *
 * @author  tong
 */
public class ServerPort {
    
    /** Creates a new instance of ServerPort */
    public ServerPort() {
        
        int portNumber = 3003;
        boolean finished = false;
        try {
              ServerSocket listener = new ServerSocket( portNumber );
              while (!finished ) {
                Socket client = listener.accept(); /* we will pause here waiting*/
                InputStream in = client.getInputStream();
                OutputStream out = client.getOutputStream();
                /* read a byte from the client                                         */
                int aByte = in.read();
                /* read a newline delimited string*/
                BufferedReader bin = new BufferedReader ( new InputStreamReader( in ));
                String aString = bin.readLine();
              /*  Constant.println(aString);*/
                
                /* sever connection to client*/
                String data = new String();
                data = CmdExec(aString);
              /*  Constant.println(data);*/
                out.write(42);
                PrintWriter pout = new PrintWriter( out, true );
                pout.println(data);
                client.close();
                /*finished = true;*/
              }
              /* stop listening for new clients*/
              listener.close();
        } catch (IOException e) { 
        e.printStackTrace(Constant.getPrintStream());    
        }
    }
    
    public String  CmdExec(String cmdline) 
    {
       String data = new String();
        try {
              String line  = null;
            /* String[] cmd = {"ls","ls -al","date"};*/
             Process p = Runtime.getRuntime().exec(cmdline);
             p.waitFor();
             BufferedReader input =
               new BufferedReader
                 (new InputStreamReader(p.getInputStream()));
        /*     Constant.println(cmd[0]);*/
             while ((line = input.readLine()) != null) {
           /*    Constant.println(line);*/
                data = line;
               }
             input.close();
         }
        catch (Exception err) {
         err.printStackTrace(Constant.getPrintStream());
         }
         return data;
   }
        
        
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ServerPort();
       
        
        /* TODO code application logic here*/
    }
    
}
