/*
 * ManageDB.java
 *
 * Created on 3 มิถุนายน 2548, 13:44 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportquery.objectdb;


import java.util.Properties;
/**
 *
 * @author tong (padungrat)
 */
public class ManageDB {
    
    /**เป็น Object ของ Connection*/
    public com.hospital_os.usecase.connection.ConnectionInf theConnectionDBMgr = null;
   /**เป็น Object ของ SQLTemplateDB*/
    public SQLTemplateDB theSQLTemplateDB;
    public CheckSQLDB theCheckSQLDB;
    public ManageDB(com.hospital_os.usecase.connection.ConnectionInf connection) {
        
       // connection();
       theConnectionDBMgr = connection;
       
       theSQLTemplateDB = new SQLTemplateDB(theConnectionDBMgr);
       theCheckSQLDB = new CheckSQLDB(theConnectionDBMgr);
        
      
    }
    
    private void connection()
    {
        /*String driver = "org.postgresql.Driver";
        String user = "postgres";
        String password = "postgres";
        String port = "5432";
        String server = "192.168.1.6";
        String database = "develop_aoluk_test";
        String url = "jdbc:postgresql://" + server + ":"+port + "/" + database ;
         */
        /*
        setConnection();
        theConnectionDBMgr = new ConnectionDBMgr(driver,url,user,password,"1");
        theConnectionDBMgr.MultiConnection(multiconnection);
      */
    }
  
    
    
}
