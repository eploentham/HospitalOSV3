/*
 * ReadFileDB.java
 *
 * Created on 16 มกราคม 2547, 23:02 น.
 */

package com.hospital_os.utility;
import java.util.*;
/**
 *
 * @author  tong
 */
public class ReadFileDB {
    
    /** Creates a new instance of ReadFileDB */
    Properties settings = new Properties();
    ConfigSQLServer cfsql;
    StringBuffer conf;
    String config;
    
    String connect_type;
    String server;
    String database;
    String port;
    String username;
    String password;
    String datasource;
    
    public ReadFileDB() {
       cfsql  = new ConfigSQLServer();
       conf = new StringBuffer();
    }
    
   public Properties readFileConfig(String fname)
    {   String conf = cfsql.readInputDefault(fname);
        if(conf != null)
        {   config = com.hospital_os.utility.Secure.decode(conf.toString());
       /*    Constant.println(config);
*/
            String tmp = new String();
            String value = new String();
            try{
                tmp = config.substring(0, config.indexOf("\n"));
                config = config.substring(config.indexOf("\n")+1);
                value = tmp.substring(tmp.indexOf("=")+1);
                /*settings.put("DONT_REMIND", value.trim());
*/
                
                tmp = config.substring(0, config.indexOf("\n"));
                config = config.substring(config.indexOf("\n")+1);
                value = tmp.substring(tmp.indexOf("=")+1);
                settings.put("SERVER", value.trim());
                this.server = value.trim();
                
                tmp = config.substring(0, config.indexOf("\n"));
                config = config.substring(config.indexOf("\n")+1);
                value = tmp.substring(tmp.indexOf("=")+1);
                settings.put("DATABASE", value.trim());
                this.database =  value.trim();
                
                tmp = config.substring(0, config.indexOf("\n"));
                config = config.substring(config.indexOf("\n")+1);
                value = tmp.substring(tmp.indexOf("=")+1);
                settings.put("PORT", value.trim());
                this.port  =  value.trim();
                
                tmp = config.substring(0, config.indexOf("\n"));
                config = config.substring(config.indexOf("\n")+1);
                value = tmp.substring(tmp.indexOf("=")+1);
                settings.put("USERNAME", value.trim());
                this.username  =  value.trim();
                
                tmp = config.substring(0, config.indexOf("\n"));
                config = config.substring(config.indexOf("\n")+1);
                value = tmp.substring(tmp.indexOf("=")+1);
                settings.put("PASSWORD", value.trim());
                this.password  =  value.trim();
                
                tmp = config.substring(0, config.indexOf("\n"));
                config = config.substring(config.indexOf("\n")+1);
                value = tmp.substring(tmp.indexOf("=")+1);
                settings.put("DATABASETYPE", value.trim());
                this.connect_type =  value.trim();
                
                tmp = null;
                value =null;
                
                return settings;
            }
            catch(Exception ex)
            {   ex.printStackTrace(Constant.getPrintStream());
                tmp = null;
                value =null;
                
                return null;
            }
            
        }
        else
            return null;
    }
    
  
    public static void main(String[] argv)
    {  /* ReadFileDB rfDB = new ReadFileDB();
*/
      /*  Properties setff= rfDB.readFileConfig(".hospital_os.cfg");
*/
        
        
      /*  Constant.println(setff.getProperty("SERVER"));
*/
      /*  rfDB.ShowData();
*/
    }
}
