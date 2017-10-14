//Source file: H:\\hospitalos\\com\\hospital_osv\\utility\\ConnectionDBMgr.java
package com.hosv3.utility;
import java.sql.*;
import com.hosv3.utility.connection.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.DBConnection;

public class ConnectionDBMgr1 implements  ConnectionInf2,ConnectionInf
{
   boolean debug = true;//System.getProperty("Debug")!=null;
   boolean isOpen = false;
   DBConnection theDBConnection;
   public String di;
   public String url;
   public String uname;
   public String passw;
   public String typeDatabase = "0";
   public int sql_total = 0;

    private boolean open_mode=true;
   /**
   @roseuid 3F73FB550305
    */
   public ConnectionDBMgr1(){
       initCon("jdbc:postgresql://localhost:5432/hosv39","huser","huser" ,0);
   }
   
   public ConnectionDBMgr1(String di,String url,String uname,String passw
   ,String type) 
   {
       this.di = di;
       initCon(url,uname,passw ,0);
   }
   
   public ConnectionDBMgr1(String server,String db,String uname,String passw) 
   {
       this.di = "org.postgresql.Driver";
       String url = "jdbc:postgresql://"+server+":5432/"+db;
        initCon(url,uname,passw ,0);
   }
/**
 * 0server 1database 2port 3user 4password 5remind 6type
 * @param target_db
 */
    public ConnectionDBMgr1(String[] target_db) {
       this.typeDatabase = target_db[6];
       this.di = "org.postgresql.Driver";
       String url = "jdbc:postgresql://"+target_db[0]+":" +target_db[2] + "/"+target_db[1];
       initCon(url,target_db[3],target_db[4] ,0);
    }
   public boolean nbegin()
   { 
//       if(theDBConnection.execSQLUpdate("BEGIN")!= 0)
//            return true;
//        else
             return false;
   }
   /**
   @roseuid 3F73FB060077
    */
   public boolean checkConnection() throws Exception
   { 
        //return theDBConnection.check();
        return true;
   }
   public boolean open()
   { 
       isOpen = true;
       
       if(open_mode)
       {
           if(debug)  Constant.println("theDBConnection.open();");
           return theDBConnection.open("");
       }
       else
           return true;
   }
   
   public boolean begin() 
   {   
       theDBConnection.open("");
//       theDBConnection.execSQLUpdate("BEGIN");
       return true;
   }
   /**
   @roseuid 3F73FB0F00A2
    */
   public boolean rollback() 
   {    
//        theDBConnection.execSQLUpdate("ROLLBACK");
        theDBConnection.close();
        return true;
   }
   
   /**
   @roseuid 3F73FB26016D
    */
   public boolean commit() 
   {    
//        theDBConnection.execSQLUpdate("COMMIT");
        theDBConnection.close();
        return true;
   }
   /**
   @param sql
   @return int
   @roseuid 3F73FB1D00FC
    */
   public int xUpdate(String sql) throws Exception
   {   
       if(debug) 
           Constant.print("x\t\t____" + sql + "_____");
       int ret = getConnection().createStatement().executeUpdate(sql);
       if(debug) 
           Constant.println("result:" + ret);
        return ret;
   }
   /**
   @param sql
   @return java.sql.ResultSet
   @roseuid 3F73FB2102B0
    */
   public java.sql.ResultSet xQuery(String sql) throws Exception
   {
       if(debug) Constant.println("x\t" + sql);
        return getConnection().createStatement().executeQuery(sql);    
   }  
   
   public java.sql.PreparedStatement ePQuery(String sql) 
   {
        return theDBConnection.execPSQLQuery(sql);    
   }
   /**
   @roseuid 3F74056B034E
    */
   public boolean close() 
   {   
       isOpen = false;
       if(open_mode)
       {
           if(debug) Constant.println("theDBConnection.close();");
           return theDBConnection.close();
       }
       else
           return true;
       
   }
   // for postgres
   public static boolean checkConnection(String url, String username, String password ,int typedatabase)
    {
        boolean res = false;
        try{
            switch(typedatabase){
                case 0 : Class.forName("org.postgresql.Driver");//DriverManager.registerDriver(new org.postgresql.Driver());   
                         break;
                case 1 : Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");//DriverManager.registerDriver(new com.microsoft.jdbc.sqlserver.SQLServerDriver()); 
                         break;
                case 2 : Class.forName("org.gjt.mm.mysql.Driver");//DriverManager.registerDriver(new org.gjt.mm.mysql.Driver()); 
                         break;
                case 3 : Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");//DriverManager.registerDriver(new org.gjt.mm.mysql.Driver()); 
                         break;
            }
            Connection connection = DriverManager.getConnection(url, username, password);
            if(connection!=null)        res = true;
        }
        catch(Exception ex){
            res = false;
            Constant.println("ex.getMessage():" + ex.getMessage());
        }
        return res;
    }
    
 
   public void initCon(String url, String uname
    , String passw ,int typedatabase)
    {
        this.url = url;
        this.uname = uname;
        this.passw = passw;
        if(di==null || di.equals(""))
            this.di = "org.postgresql.Driver";
        try{
            theDBConnection = new DBConnection();
            theDBConnection.create(url,uname,passw,di);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
         }
    }
   public String gettypeDatabase() {
        return typeDatabase;
    }
    public java.sql.Connection getConnection(){
        return theDBConnection.getConnection();
    }
    //HENBE_APR
    public boolean exit() {
        return theDBConnection.close();    
    }
    public boolean connect(String driver, String url) {
        Constant.println("ConnectionDBMgr.connect:not used");
        return false;
    }
    
    public void MultiConnection(boolean choose) {
    }
    
    public boolean close(boolean isclose) {
        return true;
    }
    
    public boolean open(boolean isclose) {
        return true;
    }
    
    public java.util.Properties getProperties() {
        return null;
    }
     
    public java.sql.ResultSet eQuery(String sql) throws Exception{
        try{

        sql_total++;
        if(debug) Constant.println("\t" + sql);
        java.sql.ResultSet rs = theDBConnection.execSQLQuery(sql);   
        return rs;
        }catch(Exception e){
            Constant.println("Exception:"+sql);
            throw e;
        }
    }  
    
    public int eUpdate(String sql) throws Exception {
        try{
        sql_total++;
        int ret = theDBConnection.execSQLUpdate(sql);
        if(debug) Constant.println(ret + "\t\t" + sql);
        if(ret==0){
            if(debug) Constant.println("result 0 record");
        }
        return ret;
        }catch(Exception e){
            Constant.println("Exception:"+sql);
            throw e;
        }
    }
    
    public ConnectionInf getClone(){
        return new ConnectionDBMgr1(this.di,this.url,this.uname,this.passw, this.typeDatabase);
    }
    /**
     * ตรวจสอบว่าได้ทำการเปิด Connection แล้วหรือไม่
     * @author kinland
     * @date 25/08/2549
     */
    public boolean isOpen()
    {
        return isOpen;
    }

    public String getURL() {
        return url;
    }

    public String getUsername() {
        return uname;
    }
    public void setOpenMode(boolean b){
        open_mode = b;
    }
    public boolean isOpenMode(){
        return open_mode;
    }
}
