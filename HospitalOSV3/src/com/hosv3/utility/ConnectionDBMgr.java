//Source file: H:\\hospitalos\\com\\hospital_osv\\utility\\ConnectionDBMgr.java
package com.hosv3.utility;
import com.hospital_os.usecase.connection.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.postgresql.util.PSQLException;

public class ConnectionDBMgr extends ConnectionDBMgr1 implements  ConnectionInf
{
    Connection theCon;
    Statement theST; 

   public ConnectionDBMgr(){
       initCon("jdbc:postgresql://localhost:5432/hosv39","huser","huser" ,0);
   }
   
   public ConnectionDBMgr(String di,String url,String uname,String passw
   ,String type)
   {
       this.di = di;
       initCon(url,uname,passw ,0);
   }

   public ConnectionDBMgr(String server,String db,String uname,String passw)
   {
       this.di = "org.postgresql.Driver";
       String url = "jdbc:postgresql://"+server+":5432/"+db;
        initCon(url,uname,passw ,0);
   }
/**
 * 0server 1database 2port 3user 4password 5remind 6type
 * @param target_db
 */
    public ConnectionDBMgr(String[] target_db) {
       this.di = "org.postgresql.Driver";
       String url1 = "jdbc:postgresql://"+target_db[0]+":" +target_db[2] + "/"+target_db[1];
       initCon(url1,target_db[3],target_db[4] ,0);
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

        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
         }
    }
    @Override
    public boolean open(){
        return true;
    }
    @Override
    public boolean close(){
        return true;
    }
    @Override
    public int eUpdate(String sql) throws Exception {
        try{
            sql_total++;
            int ret = executeUpdate(sql);
            if(debug) Constant.println(ret + "\t\t" + sql);
            return ret;
        }catch(Exception e){
            Constant.println("Exception:"+sql);
            throw e;
        }
    }
    @Override
    public java.sql.ResultSet eQuery(String sql) throws Exception{
        try{
            sql_total++;
            if(debug) Constant.println("\t" + sql);
            java.sql.ResultSet rs = executeQuery(sql);
            return rs;
        }catch(Exception e){
            Constant.println("Exception:"+sql);
            throw e;
        }
    }
    public ConnectionInf getClone(){
        return new ConnectionDBMgr(di,url,uname,passw, super.typeDatabase);
    }

    private Statement getST() throws SQLException, ClassNotFoundException {
        if(theST==null){
            theST = getConnection().createStatement();
        }
        return theST;
    }

    private int executeUpdate(String sql) throws Exception {
        try{
            return getST().executeUpdate(sql);
        }catch(PSQLException e){
            if(!e.getMessage().equals("An I/O error occured while sending to the backend."))
                throw e;
        }
        clear();
        return getST().executeUpdate(sql);
    }

    private ResultSet executeQuery(String sql) throws Exception {
        try{
            return getST().executeQuery(sql);
        }catch(PSQLException e){
            if(!e.getMessage().equals("An I/O error occured while sending to the backend."))
                throw e;
        }
        clear();
        return getST().executeQuery(sql);
    }

    private void clear() {
        theCon = null;
        theST = null;
    }
    @Override
    public Connection getConnection(){
        if(theCon==null){
            try {
                Class.forName(this.di); 
                Properties p = new Properties();
                theCon = DriverManager.getConnection(url, this.uname, this.passw);
                /*p.setProperty("PGCONNECT_TIMEOUT", "3");
                p.setProperty("PGUSER", uname);
                p.setProperty("PGPASSWORD", passw);
                theCon = DriverManager.getConnection(url, p);*/
            } catch (SQLException ex) {
                ex.printStackTrace(Constant.getPrintStream());
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace(Constant.getPrintStream());
            }
        }
        return theCon;
    }

   public boolean begin()
   {
       return true;
   }
   /**
   @roseuid 3F73FB0F00A2
    */
   public boolean rollback()
   {
        return true;
   }

   /**
   @roseuid 3F73FB26016D
    */
   public boolean commit()
   {     
        return true;
   }
   
    @Override
   public java.sql.PreparedStatement ePQuery(String sql)
   {
        try {
            return this.getConnection().prepareStatement(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
   }
}
