/*
 * DatabaseType.java
 *
 * Created on 14 ธันวาคม 2546, 15:57 น.
 */

package com.hospital_os.object;

/**
 *
 * @author  tong
 * เก็บชนิดของฐานข้อมูลไว้
 */
public class DatabaseType {
    
    /** Creates a new instance of DatabaseType */
    final public String databasetype[];
    final private int num = 3;
    public DatabaseType() {
        databasetype = new String[num];
        
        databasetype[0] = "postgreSQL";
        databasetype[1] = "SQL Server 2000";
        databasetype[2] = "My SQL";
    }
    
    public static int getDataBaseType(String databaseType)
    {   
        DatabaseType theDatabaseType = new DatabaseType();
        int type =0;
        for(int i=0; i < theDatabaseType.databasetype.length ; i++)
        {
             if(databaseType.equals(theDatabaseType.databasetype[i]))
             {  type = i;
             }
        }
        databaseType = null;
        theDatabaseType = null;
        return type;
    }
}
