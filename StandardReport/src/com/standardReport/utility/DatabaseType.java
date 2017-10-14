/*
 * DatabaseType.java
 *
 * Created on 30 กรกฎาคม 2548, 14:08 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.utility;

/**
 *
 * @author Noom
 */
public class DatabaseType {

    final public String databasetype[];
    final private int num = 3;
    
    
     /** Creates a new instance of DatabaseType */
    public DatabaseType() {
        databasetype = new String[num];
        
        databasetype[0] = "postgreSQL";
        databasetype[1] = "SQL Server 2000";
        databasetype[2] = "My SQL";
    }
}
